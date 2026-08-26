package com.player32611.mapper;

import com.player32611.entity.SetmealDish;
import com.player32611.vo.SetmealDishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    @Select("select setmeal_id from setmeal_dish where dish_id = #{id}")
    List<Long> selectSetmealIdByDishId(Long id);

    List<Long> selectSetmealIdByDishIds(List<Long> dishIds);

    Integer insertBatch(List<SetmealDish> setmealDishes);

    @Select("select * from setmeal_dish where setmeal_id = #{setmealId}")
    List<SetmealDish> selectBySetmealId(Long setmealId);

    List<SetmealDishVO> selectSetmealDishVOBySetmealId(Long id);

    @Delete("delete from setmeal_dish where setmeal_id = #{setmealId}")
    void deleteBySetmealId(Long setmealId);

    void deleteBySetmealIds(List<Long> setmealIds);
}
