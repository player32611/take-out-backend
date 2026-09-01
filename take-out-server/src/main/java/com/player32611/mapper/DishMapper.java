package com.player32611.mapper;

import com.github.pagehelper.Page;
import com.player32611.annotation.AutoFill;
import com.player32611.dto.DishPageDTO;
import com.player32611.entity.Dish;
import com.player32611.enumeration.OperationType;
import com.player32611.vo.DishPageVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


@Mapper
public interface DishMapper {

    @AutoFill(value = OperationType.INSERT)
    Integer insert(Dish dish);

    Page<DishPageVO> page(DishPageDTO dishPageDTO);

    @Select("select * from dish where id = #{id}")
    Dish selectById(Long id);

    @Delete("delete from dish where id = #{id}")
    void deleteById(Long id);

    void deleteByIds(List<Long> ids);

    @AutoFill(value = OperationType.UPDATE)
    void update(Dish dish);

    @Select("select * from dish where category_id = #{categoryId}")
    List<Dish> selectByCategoryId(Long categoryId);

    @Select("select count(*) from dish where status = #{status}")
    Integer selectSumByStatus(Integer status);
}
