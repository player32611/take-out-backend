package com.player32611.controller.user;

import com.player32611.dto.SetmealListDTO;
import com.player32611.entity.Setmeal;
import com.player32611.result.Result;
import com.player32611.service.SetmealService;
import com.player32611.vo.SetmealDishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userSetmealController")
@RequestMapping("/user/setmeal")
@Slf4j
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    @GetMapping("/list")
    public Result<List<Setmeal>> list(SetmealListDTO setmealListDTO){
        log.info("根据分类id查询套餐请求: {}", setmealListDTO);

        List<Setmeal> setmealList = setmealService.list(setmealListDTO);

        return Result.success(setmealList);
    }

    @GetMapping("/dish/{id}")
    public Result<List<SetmealDishVO>> dish(@PathVariable Long id){
        log.info("根据套餐id查询包含的菜品请求: {}", id);

        List<SetmealDishVO> setmealDishVOList = setmealService.dish(id);

        return Result.success(setmealDishVOList);
    }
}