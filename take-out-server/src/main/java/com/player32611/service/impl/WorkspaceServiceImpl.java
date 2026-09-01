package com.player32611.service.impl;

import com.player32611.constant.StatusConstant;
import com.player32611.mapper.DishMapper;
import com.player32611.mapper.OrdersMapper;
import com.player32611.mapper.SetmealMapper;
import com.player32611.mapper.UserMapper;
import com.player32611.service.WorkspaceService;
import com.player32611.vo.WorkspaceBusinessVO;
import com.player32611.vo.WorkspaceDishesVO;
import com.player32611.vo.WorkspaceOrdersVO;
import com.player32611.vo.WorkspaceSetmealsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetmealMapper setmealMapper;

    @Override
    public WorkspaceBusinessVO business(){
        LocalDateTime beginTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime endTime = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);

        Integer newUsers = userMapper.selectSumByDate(beginTime, endTime);
        Integer validOrderCount = ordersMapper.selectSumByDateAndStatus(beginTime, endTime, StatusConstant.COMPLETED);
        Integer totalOrderCount = ordersMapper.selectSumByDateAndStatus(beginTime, endTime, null);
        BigDecimal turnover = ordersMapper.selectAmountSum(beginTime, endTime, StatusConstant.COMPLETED);
        turnover = turnover == null ? BigDecimal.valueOf(0.0) : turnover;

        double orderCompletionRate = 0.0;
        double unitPrice = 0.0;
        if(totalOrderCount != 0) orderCompletionRate = validOrderCount.doubleValue() / totalOrderCount.doubleValue();
        if(validOrderCount != 0) unitPrice = turnover.doubleValue() / validOrderCount.doubleValue();

        return WorkspaceBusinessVO.builder()
                .newUsers(newUsers)
                .orderCompletionRate(orderCompletionRate)
                .turnover(turnover)
                .unitPrice(unitPrice)
                .validOrderCount(validOrderCount)
                .build();
    }

    @Override
    public WorkspaceOrdersVO orders(){
        LocalDateTime beginTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime endTime = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);

        Integer allOrders = ordersMapper.selectSumByDateAndStatus(beginTime, endTime, null);
        Integer cancelledOrders = ordersMapper.selectSumByDateAndStatus(beginTime, endTime, StatusConstant.CANCELLED);
        Integer completedOrders = ordersMapper.selectSumByDateAndStatus(beginTime, endTime, StatusConstant.COMPLETED);
        Integer deliveredOrders = ordersMapper.selectSumByDateAndStatus(beginTime, endTime, StatusConstant.CONFIRMED);
        Integer waitingOrders = ordersMapper.selectSumByDateAndStatus(beginTime, endTime, StatusConstant.TO_BE_CONFIRMED);

        return WorkspaceOrdersVO.builder()
                .allOrders(allOrders)
                .cancelledOrders(cancelledOrders)
                .completedOrders(completedOrders)
                .deliveredOrders(deliveredOrders)
                .waitingOrders(waitingOrders)
                .build();
    }

    @Override
    public WorkspaceDishesVO dishes(){
        return WorkspaceDishesVO.builder()
                .discontinued(dishMapper.selectSumByStatus(StatusConstant.DISABLE))
                .sold(dishMapper.selectSumByStatus(StatusConstant.ENABLE))
                .build();
    }

    @Override
    public WorkspaceSetmealsVO setmeals(){
        return WorkspaceSetmealsVO.builder()
                .discontinued(setmealMapper.selectSumByStatus(StatusConstant.DISABLE))
                .sold(setmealMapper.selectSumByStatus(StatusConstant.ENABLE))
                .build();
    }
}
