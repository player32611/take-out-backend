package com.player32611.controller.admin;

import com.player32611.dto.SetmealDTO;
import com.player32611.dto.SetmealPageDTO;
import com.player32611.result.PageResult;
import com.player32611.result.Result;
import com.player32611.service.SetmealService;
import com.player32611.vo.SetmealPageVO;
import com.player32611.vo.SetmealVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/setmeal")
@Slf4j
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    @PostMapping
    @CacheEvict(value = "setmealCache", key = "#setmealDTO.categoryId")
    public Result save(@RequestBody SetmealDTO setmealDTO){
        log.info("新增套餐请求: {}", setmealDTO);

        setmealService.save(setmealDTO);

        return Result.success();
    }

    @GetMapping("/page")
    public Result<PageResult<SetmealPageVO>> page(SetmealPageDTO setmealPageDTO){
        log.info("分页查询套餐请求: {}", setmealPageDTO);

        PageResult<SetmealPageVO> pageResult = setmealService.page(setmealPageDTO);

        return Result.success(pageResult);
    }

    @PostMapping("/status/{status}")
    @CacheEvict(value = "setmealCache", allEntries = true)
    public Result status(@PathVariable Integer status, Long id){
        log.info("套餐起售、停售请求: {}, {}", status, id);

        setmealService.status(status, id);

        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<SetmealVO> id(@PathVariable Long id){
        log.info("根据id查询菜品请求: {}", id);

        SetmealVO setmealVO = setmealService.id(id);

        return Result.success(setmealVO);
    }

    @PutMapping
    @CacheEvict(value = "setmealCache", allEntries = true)
    public Result update(@RequestBody SetmealDTO setmealDTO){
        log.info("修改套餐请求: {}", setmealDTO);

        setmealService.update(setmealDTO);

        return Result.success();
    }

    @DeleteMapping
    @CacheEvict(value = "setmealCache", allEntries = true)
    public Result delete(@RequestParam List<Long> ids){
        log.info("批量删除套餐请求: {}", ids);

        setmealService.delete(ids);

        return Result.success();
    }
}
