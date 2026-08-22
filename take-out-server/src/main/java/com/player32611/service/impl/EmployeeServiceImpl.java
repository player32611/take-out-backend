package com.player32611.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.player32611.constant.MessageConstant;
import com.player32611.constant.PasswordConstant;
import com.player32611.constant.StatusConstant;
import com.player32611.context.BaseContext;
import com.player32611.dto.EmployeeDTO;
import com.player32611.dto.EmployeeLoginDTO;
import com.player32611.dto.EmployeePageDTO;
import com.player32611.entity.Employee;
import com.player32611.exception.AccountLockedException;
import com.player32611.exception.AccountNotFoundException;
import com.player32611.exception.PasswordErrorException;
import com.player32611.mapper.EmployeeMapper;
import com.player32611.result.PageResult;
import com.player32611.service.EmployeeService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        Employee employee = employeeMapper.selectByUsername(username);

        if(employee == null){
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        if (!password.equals(employee.getPassword())) {
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (Objects.equals(employee.getStatus(), StatusConstant.DISABLE)) {
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        return employee;
    }

    @Override
    public void save(EmployeeDTO employeeDTO){
        Employee employee = new Employee();

        BeanUtils.copyProperties(employeeDTO, employee);
        employee.setStatus(StatusConstant.ENABLE);
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        employee.setCreateUser(BaseContext.getCurrentId());
        employee.setUpdateUser(BaseContext.getCurrentId());

        employeeMapper.insert(employee);
    }

    @Override
    public PageResult<Employee> page(EmployeePageDTO employeePageDTO){
        PageHelper.startPage(employeePageDTO.getPage(), employeePageDTO.getPageSize());

        Page<Employee> page;
        if(employeePageDTO.getName() == null) {
            page = (Page<Employee>) employeeMapper.selectAll();
        } else {
            page = (Page<Employee>) employeeMapper.selectByUsernameLike(employeePageDTO.getName());
        }

        Long total = page.getTotal();
        List<Employee> records = page.getResult();

        return new PageResult<>(total, records);
    }

    @Override
    public void status(Integer status, Long id){
        Employee employee = Employee.builder()
                .status(status)
                .id(id)
                .updateTime(LocalDateTime.now())
                .updateUser(BaseContext.getCurrentId())
                .build();

        employeeMapper.update(employee);
    }

    @Override
    public Employee id(Long id){
        Employee employee = employeeMapper.selectById(id);
        employee.setPassword("******");

        return employee;
    }

    @Override
    public void update(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);

        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(BaseContext.getCurrentId());

        employeeMapper.update(employee);
    }
}
