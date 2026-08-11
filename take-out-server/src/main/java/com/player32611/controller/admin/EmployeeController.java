package com.player32611.controller.admin;

import com.player32611.dto.EmployeeLoginDTO;
import com.player32611.entity.Employee;
import com.player32611.result.Result;
import com.player32611.service.EmployeeService;
import com.player32611.vo.EmployeeLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        if(employee != null) {
            EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                    .id(employee.getId())
                    .username(employee.getUsername())
                    .name(employee.getName())
                    .build();

            return Result.success(employeeLoginVO);
        }

        return Result.error("登录失败");
    }

}
