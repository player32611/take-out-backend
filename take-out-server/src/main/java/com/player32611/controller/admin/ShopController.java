package com.player32611.controller.admin;

import com.player32611.constant.ShopConstant;
import com.player32611.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController("adminShopController")
@Slf4j
@RequestMapping("/admin/shop")
public class ShopController {

    @Autowired
    private RedisTemplate redisTemplate;

    @PutMapping("/{status}")
    public Result setStatus(@PathVariable Integer status){
        log.info("设置营业状态请求: {}", status);

        redisTemplate.opsForValue().set(ShopConstant.STATUS, status);

        return Result.success();
    }

    @GetMapping("/status")
    public Result<Integer> getStatus(){
        log.info("获取营业状态请求");

        Integer status = (Integer) redisTemplate.opsForValue().get(ShopConstant.STATUS);

        return Result.success(status);
    }
}
