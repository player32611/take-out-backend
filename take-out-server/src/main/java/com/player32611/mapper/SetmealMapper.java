package com.player32611.mapper;

import com.player32611.annotation.AutoFill;
import com.player32611.entity.Setmeal;
import com.player32611.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SetmealMapper {

    @AutoFill(value = OperationType.INSERT)
    void insert(Setmeal setmeal);
}
