package com.player32611.controller.admin;

import com.player32611.dto.DishDTO;
import com.player32611.dto.DishPageDTO;
import com.player32611.entity.Dish;
import com.player32611.result.PageResult;
import com.player32611.result.Result;
import com.player32611.service.DishService;
import com.player32611.vo.DishPageVO;
import com.player32611.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping
    public Result delete(@RequestParam List<Long> ids){
        log.info("批量删除菜品请求: {}", ids);

        dishService.delete(ids);

        return Result.success();
    }


    @GetMapping("/{id}")
    public Result<DishVO> id(@PathVariable Long id){
        log.info("根据id查询菜品: {}", id);

        DishVO dishVo = dishService.id(id);

        return Result.success(dishVo);
    }
}
