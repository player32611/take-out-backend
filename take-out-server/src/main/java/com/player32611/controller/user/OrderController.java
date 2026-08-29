package com.player32611.controller.user;

import com.player32611.dto.OrderHistoryDTO;
import com.player32611.dto.OrderPaymentDTO;
import com.player32611.dto.OrderSubmitDTO;
import com.player32611.result.PageResult;
import com.player32611.result.Result;
import com.player32611.service.OrderService;
import com.player32611.vo.OrderPaymentVO;
import com.player32611.vo.OrderVO;
import com.player32611.vo.OrderSubmitVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("userOrderController")
@RequestMapping("/user/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/submit")
    public Result<OrderSubmitVO> submit(@RequestBody OrderSubmitDTO orderSubmitDTO){
        log.info("用户下单请求: {}", orderSubmitDTO);

        OrderSubmitVO orderSubmitVO = orderService.submit(orderSubmitDTO);

        return Result.success(orderSubmitVO);
    }

    @GetMapping("/historyOrders")
    public Result<PageResult<OrderVO>> history(OrderHistoryDTO orderHistoryDTO){
        log.info("历史订单查询: {}", orderHistoryDTO);

        PageResult<OrderVO> pageResult = orderService.history(orderHistoryDTO);

        return Result.success(pageResult);
    }

    @GetMapping("/orderDetail/{id}")
    public Result<OrderVO> id(@PathVariable Long id){
        log.info("查询订单详情: {}", id);

        OrderVO orderVO = orderService.id(id);

        return Result.success(orderVO);
    }

    @PutMapping("/payment")
    public Result<OrderPaymentVO> pay(@RequestBody OrderPaymentDTO orderPaymentDTO){
        log.info("订单支付: {}", orderPaymentDTO);

        OrderPaymentVO orderPaymentVO = orderService.pay(orderPaymentDTO);

        return Result.success(orderPaymentVO);
    }

    @PutMapping("/cancel/{id}")
    public Result cancel(@PathVariable Long id){
        log.info("取消订单: {}", id);

        orderService.cancel(id);

        return Result.success();
    }
}
