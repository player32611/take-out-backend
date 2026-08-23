package com.player32611.service;

import com.player32611.dto.DishDTO;
import com.player32611.dto.DishPageDTO;
import com.player32611.entity.Dish;
import com.player32611.result.PageResult;
import com.player32611.vo.DishPageVO;
import com.player32611.vo.DishVO;

import java.util.List;

public interface DishService {
    void save(DishDTO dishDTO);

    PageResult<DishPageVO> page(DishPageDTO dishPageDTO);

    void delete(List<Long> ids);

    DishVO id(Long id);
}
