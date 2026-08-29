package com.player32611.service;

import com.player32611.dto.OrderHistoryDTO;
import com.player32611.dto.OrderPaymentDTO;
import com.player32611.dto.OrderSubmitDTO;
import com.player32611.result.PageResult;
import com.player32611.vo.OrderPaymentVO;
import com.player32611.vo.OrderVO;
import com.player32611.vo.OrderSubmitVO;

public interface OrderService {
    OrderSubmitVO submit(OrderSubmitDTO orderSubmitDTO);

    PageResult<OrderVO> history(OrderHistoryDTO orderHistoryDTO);

    OrderVO id(Long id);

    OrderPaymentVO pay(OrderPaymentDTO orderPaymentDTO);

    void cancel(Long id);
}
