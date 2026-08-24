package com.player32611.controller.admin;

import com.player32611.dto.DishDTO;
import com.player32611.dto.SetmealDTO;
import com.player32611.dto.SetmealPageDTO;
import com.player32611.result.PageResult;
import com.player32611.result.Result;
import com.player32611.service.SetmealService;
import com.player32611.vo.SetmealPageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/page")
    private Result<PageResult<SetmealPageVO>> page(SetmealPageDTO setmealPageDTO){
        log.info("分页查询套餐请求: {}", setmealPageDTO);

        PageResult<SetmealPageVO> pageResult = setmealService.page(setmealPageDTO);

        return Result.success(pageResult);
    }
}
