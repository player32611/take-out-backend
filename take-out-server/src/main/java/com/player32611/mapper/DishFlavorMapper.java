package com.player32611.mapper;

import com.player32611.entity.DishFlavor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    Integer insertBatch(List<DishFlavor> flavors);
}
