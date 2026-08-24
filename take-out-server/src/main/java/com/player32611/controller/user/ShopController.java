package com.player32611.controller.user;

import com.player32611.constant.ShopConstant;
import com.player32611.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("userShopController")
@Slf4j
@RequestMapping("/user/shop")
public class ShopController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/status")
    public Result<Integer> getStatus(){
        log.info("获取营业状态请求");

        Integer status = (Integer) redisTemplate.opsForValue().get(ShopConstant.STATUS);

        return Result.success(status);
    }
}
