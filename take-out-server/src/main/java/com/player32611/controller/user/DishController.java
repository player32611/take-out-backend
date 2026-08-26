package com.player32611.controller.user;

import com.player32611.dto.DishListDTO;
import com.player32611.result.Result;
import com.player32611.service.DishService;
import com.player32611.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userDishController")
@RequestMapping("/user/dish")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping("/list")
    public Result<List<DishVO>> list(DishListDTO dishListDTO){
        log.info("根据分类id查询菜品请求: {}", dishListDTO);

        List<DishVO> dishVOList = dishService.list(dishListDTO);

        return Result.success(dishVOList);
    }
}
