package com.player32611.controller.admin;

import com.player32611.constant.JwtClaimsConstant;
import com.player32611.dto.EmployeeDTO;
import com.player32611.dto.EmployeeLoginDTO;
import com.player32611.dto.EmployeePageDTO;
import com.player32611.entity.Employee;
import com.player32611.properties.JwtProperties;
import com.player32611.result.PageResult;
import com.player32611.result.Result;
import com.player32611.service.EmployeeService;
import com.player32611.utils.JwtUtil;
import com.player32611.vo.EmployeeLoginVO;
import com.player32611.vo.EmployeeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录请求：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMPLOYEE_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .username(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    @PostMapping
    public Result<EmployeeVO> save(@RequestBody EmployeeDTO employeeDTO){
        log.info("新增员工请求：{}", employeeDTO);

        Employee employee = employeeService.save(employeeDTO);

        EmployeeVO employeeVO = EmployeeVO.builder().build();

        return Result.success(employeeVO);
    }

    @GetMapping("/page")
    public Result<PageResult<Employee>> page(EmployeePageDTO employeePageDTO){
        log.info("员工分页查询请求：{}", employeePageDTO);

        PageResult<Employee> pageResult = employeeService.page(employeePageDTO);

        return Result.success(pageResult);
    }
}
