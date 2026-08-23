package com.player32611.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    List<Long> selectSetmealIdByDishIds(List<Long> dishIds);
}
