package com.player32611.mapper;

import com.player32611.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class EmployeeMapperTest {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    public void testSelectAll() {
        List<Employee> employeeList = employeeMapper.selectAll();
        System.out.println(employeeList);
    }

    @Test
    public void testInsert() {
        Employee employee = new Employee(null,"test","test","123456","11111111111","男", "111111111111111111", 1, LocalDateTime.now(), LocalDateTime.now(), 1, 1);
        System.out.println(employeeMapper.insert(employee));
    }
}
