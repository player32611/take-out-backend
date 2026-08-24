package com.player32611.controller.admin;

import com.player32611.dto.DishDTO;
import com.player32611.dto.SetmealDTO;
import com.player32611.result.Result;
import com.player32611.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/setmeal")
@Slf4j
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    @PostMapping
    public Result save(@RequestBody SetmealDTO setmealDTO){
        log.info("新增套餐请求: {}", setmealDTO);

        setmealService.save(setmealDTO);

        return Result.success();
    }
}
