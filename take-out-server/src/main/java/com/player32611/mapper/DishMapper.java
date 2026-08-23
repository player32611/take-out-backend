package com.player32611.mapper;

import com.github.pagehelper.Page;
import com.player32611.annotation.AutoFill;
import com.player32611.dto.DishPageDTO;
import com.player32611.entity.Dish;
import com.player32611.enumeration.OperationType;
import com.player32611.vo.DishPageVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishMapper {

    @AutoFill(value = OperationType.INSERT)
    Integer insert(Dish dish);

    Page<DishPageVO> page(DishPageDTO dishPageDTO);
}
