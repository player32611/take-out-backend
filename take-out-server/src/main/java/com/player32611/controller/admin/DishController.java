package com.player32611.controller.admin;

import com.player32611.dto.DishDTO;
import com.player32611.dto.DishPageDTO;
import com.player32611.entity.Dish;
import com.player32611.result.PageResult;
import com.player32611.result.Result;
import com.player32611.service.DishService;
import com.player32611.vo.DishPageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/dish")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;

    @PostMapping
    public Result save(@RequestBody DishDTO dishDTO){
        log.info("新增菜品请求: {}", dishDTO);

        dishService.save(dishDTO);

        return Result.success();
    }

    @GetMapping("/page")
    public Result<PageResult<DishPageVO>> page(DishPageDTO dishPageDTO){
        log.info("菜品分页查询请求: {}", dishPageDTO);

        PageResult<DishPageVO> pageResult = dishService.page(dishPageDTO);

        return Result.success(pageResult);
    }
}
