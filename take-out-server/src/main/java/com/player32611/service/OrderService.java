package com.player32611.service;

import com.player32611.dto.*;
import com.player32611.result.PageResult;
import com.player32611.vo.*;

public interface OrderService {
    OrderSubmitVO submit(OrderSubmitDTO orderSubmitDTO);

    PageResult<OrderVO> history(OrderHistoryDTO orderHistoryDTO);

    OrderVO id(Long id);

    OrderPaymentVO pay(OrderPaymentDTO orderPaymentDTO);

    void cancel(Long id);

    void repetition(Long id);

    void reminder(Long id);

    PageResult<OrderSearchVO>  search(OrderSearchDTO orderSearchDTO);

    void delivery(Long id);

    void confirm(OrderConfirmDTO orderConfirmDTO);

    void rejection(OrderRejectionDTO orderRejectionDTO);

    void complete(Long id);

    OrderStatisticsVO statistics();

    void cancel(OrderCancelDTO orderCancelDTO);

    OrderDetailsVO details(Long id);
}
