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
        Employee employee = Employee.builder()
                .id(null)
                .name("testiest")
                .username("testiest")
                .password("123456")
                .phone("11111111111")
                .sex("女")
                .idNumber("111111111111111111")
                .status(1)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .createUser(2L)
                .updateUser(2L)
                .build();
        System.out.println(employeeMapper.insert(employee));
    }
}
