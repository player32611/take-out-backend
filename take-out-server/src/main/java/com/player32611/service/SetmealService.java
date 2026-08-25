package com.player32611.service;

import com.player32611.dto.SetmealDTO;
import com.player32611.dto.SetmealPageDTO;
import com.player32611.result.PageResult;
import com.player32611.vo.SetmealPageVO;
import com.player32611.vo.SetmealVO;

public interface SetmealService {

    void save(SetmealDTO setmealDTO);

    PageResult<SetmealPageVO> page(SetmealPageDTO setmealPageDTO);

    void status(Integer status, Long id);

    SetmealVO id(Long id);

    void update(SetmealDTO setmealDTO);
}
