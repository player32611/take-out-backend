package com.player32611.mapper;

import com.player32611.entity.AddressBook;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AddressBookMapper {

    void insert(AddressBook addressBook);

    @Select("select * from address_book where user_id = #{userId}")
    List<AddressBook> selectByUserId(Long userId);

    void update(AddressBook addressBook);

    @Update("update address_book set is_default = #{isDefault} where user_id = #{userId}")
    void updateIsDefaultByUserId(Long userId, Integer isDefault);

    @Select("select * from address_book where id = #{id}")
    AddressBook selectById(Long id);

    @Delete("delete from address_book where id = #{id}")
    void deleteById(Long id);
}
