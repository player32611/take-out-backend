package com.player32611.mapper;

import com.github.pagehelper.Page;
import com.player32611.dto.OrderSearchDTO;
import com.player32611.dto.TopGoodDTO;
import com.player32611.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface OrdersMapper {

    void insert(Orders orders);

    Page<Orders> page(Orders orders);

    @Select("select * from orders where id = #{id}")
    Orders selectById(Long id);

    @Select("select * from orders where number = #{number} and user_id = #{userId}")
    Orders selectByNumberAndUserId(String number, Long userId);

    void update(Orders orders);

    Page<Orders> search(OrderSearchDTO orderSearchDTO);

    @Select("select * from orders where status = #{status}")
    List<Orders> selectByStatus(Integer status);

    @Select("select * from orders where status = #{status} and order_time < #{orderTime}")
    List<Orders> selectByStatusAndOrderTimeLT(Integer status, LocalDateTime orderTime);

    BigDecimal selectAmountSum(LocalDateTime begin, LocalDateTime end, Integer status);

    Integer selectSumByDateAndStatus(LocalDateTime begin, LocalDateTime end, Integer status);

    List<TopGoodDTO> selectTop10ByDate(LocalDateTime begin, LocalDateTime end);
}
