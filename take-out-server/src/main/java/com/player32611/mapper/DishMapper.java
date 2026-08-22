package com.player32611.mapper;

import com.player32611.annotation.AutoFill;
import com.player32611.entity.Dish;
import com.player32611.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper {

    @AutoFill(value = OperationType.INSERT)
    Integer insert(Dish dish);
}
