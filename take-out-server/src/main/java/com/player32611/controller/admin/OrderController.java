package com.player32611.controller.admin;

import com.player32611.dto.OrderCancelDTO;
import com.player32611.dto.OrderConfirmDTO;
import com.player32611.dto.OrderRejectionDTO;
import com.player32611.dto.OrderSearchDTO;
import com.player32611.result.PageResult;
import com.player32611.result.Result;
import com.player32611.service.OrderService;
import com.player32611.vo.OrderDetailsVO;
import com.player32611.vo.OrderSearchVO;
import com.player32611.vo.OrderStatisticsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("adminOrderController")
@RequestMapping("/admin/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/conditionSearch")
    public Result<PageResult<OrderSearchVO>> search(OrderSearchDTO orderSearchDTO){
        log.info("订单搜索: {}", orderSearchDTO);

        PageResult<OrderSearchVO> orderSearchVOPageResult = orderService.search(orderSearchDTO);

        return Result.success(orderSearchVOPageResult);
    }

    @PutMapping("/delivery/{id}")
    public Result delivery(@PathVariable Long id){
        log.info("派送订单: {}", id);

        orderService.delivery(id);

        return Result.success();
    }

    @PutMapping("/confirm")
    public Result confirm(@RequestBody OrderConfirmDTO orderConfirmDTO){
        log.info("接单: {}", orderConfirmDTO);

        orderService.confirm(orderConfirmDTO);

        return Result.success();
    }

    @PutMapping("/rejection")
    public Result rejection(@RequestBody OrderRejectionDTO orderRejectionDTO){
        log.info("拒单: {}", orderRejectionDTO);

        orderService.rejection(orderRejectionDTO);

        return Result.success();
    }

    @PutMapping("/complete/{id}")
    public Result complete(@PathVariable Long id){
        log.info("完成订单: {}", id);

        orderService.complete(id);

        return Result.success();
    }

    @GetMapping("/statistics")
    public Result<OrderStatisticsVO> statistics(){
        log.info("各个状态的订单数量统计");

        OrderStatisticsVO orderStatisticsVO = orderService.statistics();

        return Result.success(orderStatisticsVO);
    }

    @PutMapping("/cancel")
    public Result cancel(@RequestBody OrderCancelDTO orderCancelDTO){
        log.info("取消订单: {}", orderCancelDTO);

        orderService.cancel(orderCancelDTO);

        return Result.success();
    }

    @GetMapping("/details/{id}")
    public Result<OrderDetailsVO> details(@PathVariable Long id){
        log.info("查询订单详情: {}", id);

        OrderDetailsVO orderDetailsVO = orderService.details(id);

        return Result.success(orderDetailsVO);
    }
}
