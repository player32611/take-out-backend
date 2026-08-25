package com.player32611.service;

import com.player32611.dto.UserLoginDTO;
import com.player32611.vo.UserLoginVO;

public interface UserService {

    UserLoginVO login(UserLoginDTO userLoginDTO);
}
