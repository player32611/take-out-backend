package com.player32611.service;

import com.player32611.dto.SetmealDTO;
import com.player32611.dto.SetmealListDTO;
import com.player32611.dto.SetmealPageDTO;
import com.player32611.entity.Setmeal;
import com.player32611.result.PageResult;
import com.player32611.vo.SetmealDishVO;
import com.player32611.vo.SetmealPageVO;
import com.player32611.vo.SetmealVO;

import java.util.List;

public interface SetmealService {

    void save(SetmealDTO setmealDTO);

    PageResult<SetmealPageVO> page(SetmealPageDTO setmealPageDTO);

    void status(Integer status, Long id);

    SetmealVO id(Long id);

    void update(SetmealDTO setmealDTO);

    void delete(List<Long> ids);

    List<Setmeal> list(SetmealListDTO setmealListDTO);

    List<SetmealDishVO> dish(Long id);
}
