package com.player32611.controller.user;

import com.player32611.dto.UserLoginDTO;
import com.player32611.entity.User;
import com.player32611.result.Result;
import com.player32611.service.UserService;
import com.player32611.vo.UserLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("userUserController")
@RequestMapping("/user/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO){
        log.info("用户登录请求: {}", userLoginDTO);

        UserLoginVO userLoginVO = userService.login(userLoginDTO);

        return Result.success(userLoginVO);
    }
}
