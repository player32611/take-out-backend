package com.player32611.controller.user;

import com.player32611.dto.ShoppingCartDTO;
import com.player32611.entity.ShoppingCart;
import com.player32611.result.Result;
import com.player32611.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/shoppingCart")
@Slf4j
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public Result add(@RequestBody ShoppingCartDTO shoppingCartDTO){
        log.info("添加购物车请求: {}", shoppingCartDTO);

        shoppingCartService.add(shoppingCartDTO);

        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<ShoppingCart>> list(){
        log.info("查看购物车请求");

        List<ShoppingCart> shoppingCartList = shoppingCartService.list();

        return Result.success(shoppingCartList);
    }

    @DeleteMapping("/clean")
    public Result clean(){
        log.info("清空购物车请求");

        shoppingCartService.clean();


        return Result.success();
    }
}
