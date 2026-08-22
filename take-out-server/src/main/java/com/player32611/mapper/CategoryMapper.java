package com.player32611.mapper;

import com.player32611.annotation.AutoFill;
import com.player32611.entity.Category;
import com.player32611.entity.Employee;
import com.player32611.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Select("select * from category order by sort")
    List<Category> selectAll();

    List<Category> selectByNameLikeAndType(String name, Integer type);

    @AutoFill(value = OperationType.INSERT)
    @Insert("""
    INSERT INTO category
    (name, type, sort, status, create_time, update_time, create_user, update_user)
    VALUES
    (#{name}, #{type}, #{sort}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})
    """)
    Integer insert(Category category);

    @AutoFill(value = OperationType.UPDATE)
    Integer update(Category category);

    @Delete("DELETE FROM category where id = #{id}")
    Integer delete(Long id);
}
