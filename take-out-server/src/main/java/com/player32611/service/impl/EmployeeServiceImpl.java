package com.player32611.service.impl;

import com.player32611.dto.EmployeeLoginDTO;
import com.player32611.entity.Employee;
import com.player32611.mapper.EmployeeMapper;
import com.player32611.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        Employee employee = employeeMapper.selectByUsernameAndPassword(username, password);


        return employee;
    }
}
