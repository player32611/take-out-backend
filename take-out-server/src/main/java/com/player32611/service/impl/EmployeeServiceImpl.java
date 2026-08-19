package com.player32611.service.impl;

import com.player32611.constant.MessageConstant;
import com.player32611.constant.PasswordConstant;
import com.player32611.constant.StatusConstant;
import com.player32611.context.BaseContext;
import com.player32611.dto.EmployeeDTO;
import com.player32611.dto.EmployeeLoginDTO;
import com.player32611.entity.Employee;
import com.player32611.exception.AccountLockedException;
import com.player32611.exception.AccountNotFoundException;
import com.player32611.exception.PasswordErrorException;
import com.player32611.mapper.EmployeeMapper;
import com.player32611.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.Objects;

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
    public Employee save(EmployeeDTO employeeDTO){
        Employee employee = new Employee();

        BeanUtils.copyProperties(employeeDTO, employee);
        employee.setStatus(StatusConstant.ENABLE);
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));
        employee.setCreate_time(LocalDateTime.now());
        employee.setUpdate_time(LocalDateTime.now());
        employee.setCreate_user(BaseContext.getCurrentId());
        employee.setUpdate_user(BaseContext.getCurrentId());

        employeeMapper.insert(employee);

        return employee;
    }

}
