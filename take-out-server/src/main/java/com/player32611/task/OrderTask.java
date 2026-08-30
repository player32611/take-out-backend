package com.player32611.task;

import com.player32611.constant.StatusConstant;
import com.player32611.entity.Orders;
import com.player32611.mapper.OrdersMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class OrderTask {

    @Autowired
    private OrdersMapper ordersMapper;

    // 处理超时订单
    @Scheduled(cron = "0 * * * * ?")
    public void processTimeoutOrder(){
        log.info("定时处理超时订单: {}", LocalDateTime.now());

        List<Orders> ordersList = ordersMapper.selectByStatusAndOrderTimeLT(StatusConstant.PENDING_PAYMENT, LocalDateTime.now().plusMinutes(-15));
        if(ordersList != null && !ordersList.isEmpty()){
            for(Orders orders : ordersList){
                orders.setStatus(StatusConstant.CANCELLED);
                orders.setCancelReason("订单超时，自动取消");
                orders.setCancelTime(LocalDateTime.now());
                ordersMapper.update(orders);
            }
        }
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void processDeliveryOrder(){
        log.info("定时处理派送中的订单: {}", LocalDateTime.now());

        List<Orders> ordersList = ordersMapper.selectByStatusAndOrderTimeLT(StatusConstant.DELIVERY_IN_PROGRESS, LocalDateTime.now().plusMinutes(-60));
        if(ordersList != null && !ordersList.isEmpty()){
            for(Orders orders : ordersList){
                orders.setStatus(StatusConstant.COMPLETED);
                orders.setDeliveryTime(LocalDateTime.now());
                ordersMapper.update(orders);
            }
        }
    }
}
