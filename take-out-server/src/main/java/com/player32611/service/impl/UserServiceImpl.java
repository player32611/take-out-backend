package com.player32611.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.player32611.constant.JwtClaimsConstant;
import com.player32611.constant.MessageConstant;
import com.player32611.constant.WechatConstant;
import com.player32611.dto.UserLoginDTO;
import com.player32611.entity.User;
import com.player32611.exception.LoginFailedException;
import com.player32611.mapper.UserMapper;
import com.player32611.properties.JwtProperties;
import com.player32611.properties.WeChatProperties;
import com.player32611.service.UserService;
import com.player32611.utils.HttpClientUtil;
import com.player32611.utils.JwtUtil;
import com.player32611.vo.UserLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private WeChatProperties weChatProperties;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserLoginVO login(UserLoginDTO userLoginDTO){

        // 发起微信登录请求
        Map<String, String> map = new HashMap<>();
        map.put(WechatConstant.APPID, weChatProperties.getAppid());
        map.put(WechatConstant.SECRET, weChatProperties.getSecret());
        map.put(WechatConstant.JS_CODE, userLoginDTO.getCode());
        map.put(WechatConstant.GRANT_TYPE, WechatConstant.AUTHORIZATION_CODE);
        String result = HttpClientUtil.doGet(WechatConstant.LOGIN_URL, map);

        // 解析结果
        JSONObject jsonObject = JSONObject.parseObject(result);
        String openid = jsonObject.getString(WechatConstant.OPENID);

        if(openid == null){
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }

        User user = userMapper.selectByOpenid(openid);

        // 新用户注册
        if(user == null){
            user = User.builder()
                    .openid(openid)
                    .createTime(LocalDateTime.now())
                    .build();
            userMapper.insert(user);
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);

        return UserLoginVO.builder()
                .id(user.getId())
                .openid(user.getOpenid())
                .token(token)
                .build();
    }
}
