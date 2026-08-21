package com.player32611.service;

import com.player32611.dto.EmployeeDTO;
import com.player32611.dto.EmployeeLoginDTO;
import com.player32611.dto.EmployeePageDTO;
import com.player32611.entity.Employee;
import com.player32611.result.PageResult;

public interface EmployeeService {
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    void save(EmployeeDTO employeeDTO);

    PageResult<Employee> page(EmployeePageDTO employeePageDTO);

    void status(Integer status, Long id);
}
