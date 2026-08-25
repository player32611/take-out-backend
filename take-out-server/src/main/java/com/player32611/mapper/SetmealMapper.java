package com.player32611.mapper;

import com.github.pagehelper.Page;
import com.player32611.annotation.AutoFill;
import com.player32611.dto.SetmealPageDTO;
import com.player32611.entity.Setmeal;
import com.player32611.enumeration.OperationType;
import com.player32611.vo.SetmealPageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SetmealMapper {

    @AutoFill(value = OperationType.INSERT)
    void insert(Setmeal setmeal);

    Page<SetmealPageVO> page(SetmealPageDTO setmealPageDTO);

    @AutoFill(value = OperationType.UPDATE)
    void update(Setmeal setmeal);

    @Select("select * from setmeal where id = #{id}")
    Setmeal selectById(Long id);
}
