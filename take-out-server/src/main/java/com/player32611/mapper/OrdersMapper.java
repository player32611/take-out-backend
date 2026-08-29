package com.player32611.mapper;

import com.github.pagehelper.Page;
import com.player32611.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrdersMapper {

    void insert(Orders orders);

    Page<Orders> page(Orders orders);

    @Select("select * from orders where id = #{id}")
    Orders selectById(Long id);

    @Select("select * from orders where number = #{number} and user_id = #{userId}")
    Orders selectByNumberAndUserId(String number, Long userId);

    void update(Orders orders);
}
