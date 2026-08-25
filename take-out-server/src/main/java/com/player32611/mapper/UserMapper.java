package com.player32611.mapper;

import com.player32611.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user where openid = #{openid}")
    User selectByOpenid(String openid);

    void insert(User user);
}
