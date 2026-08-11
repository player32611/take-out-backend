package com.player32611.service;

import com.player32611.dto.EmployeeLoginDTO;
import com.player32611.entity.Employee;

public interface EmployeeService {
    Employee login(EmployeeLoginDTO employeeLoginDTO);
}
