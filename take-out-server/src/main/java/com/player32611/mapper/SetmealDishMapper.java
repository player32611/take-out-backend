package com.player32611.mapper;

import com.player32611.annotation.AutoFill;
import com.player32611.entity.SetmealDish;
import com.player32611.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    List<Long> selectSetmealIdByDishIds(List<Long> dishIds);

    Integer insertBatch(List<SetmealDish> setmealDishes);
}
