package com.player32611.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.player32611.constant.MessageConstant;
import com.player32611.constant.StatusConstant;
import com.player32611.context.BaseContext;
import com.player32611.dto.*;
import com.player32611.entity.*;
import com.player32611.exception.AddressBookBusinessException;
import com.player32611.exception.OrderBusinessException;
import com.player32611.exception.ShoppingCartBusinessException;
import com.player32611.mapper.AddressBookMapper;
import com.player32611.mapper.OrderDetailMapper;
import com.player32611.mapper.OrdersMapper;
import com.player32611.mapper.ShoppingCartMapper;
import com.player32611.result.PageResult;
import com.player32611.service.OrderService;
import com.player32611.vo.*;
import com.player32611.websocket.WebSocketServer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private AddressBookMapper addressBookMapper;
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private WebSocketServer webSocketServer;

    @Override
    @Transactional
    public OrderSubmitVO submit(OrderSubmitDTO orderSubmitDTO){
        // 地址为空时抛出错误
        AddressBook addressBook = addressBookMapper.selectById(orderSubmitDTO.getAddressBookId());
        if(addressBook == null) throw new AddressBookBusinessException(MessageConstant.ADDRESS_BOOK_IS_NULL);

        // 购物车为空时抛出错误
        ShoppingCart shoppingCart = ShoppingCart.builder().userId(BaseContext.getCurrentId()).build();
        List<ShoppingCart> shoppingCartList = shoppingCartMapper.list(shoppingCart);
        if(shoppingCartList == null || shoppingCartList.isEmpty()) throw new ShoppingCartBusinessException(MessageConstant.SHOPPING_CART_IS_NULL);

        // 插入订单
        Orders orders = new Orders();
        BeanUtils.copyProperties(orderSubmitDTO, orders);
        orders.setOrderTime(LocalDateTime.now());
        orders.setPayStatus(StatusConstant.UN_PAID);
        orders.setStatus(StatusConstant.PENDING_PAYMENT);
        orders.setNumber(String.valueOf(System.currentTimeMillis()));
        orders.setPhone(addressBook.getPhone());
        orders.setConsignee(addressBook.getConsignee());
        orders.setUserId(BaseContext.getCurrentId());
        orders.setAddress(addressBook.getDetail());

        ordersMapper.insert(orders);

        // 插入订单明细
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (ShoppingCart cart : shoppingCartList){
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(cart, orderDetail);
            orderDetail.setOrderId(orders.getId());
            orderDetailList.add(orderDetail);
        }
        orderDetailMapper.insertBatch(orderDetailList);

        // 清空购物车
        shoppingCartMapper.deleteByUserId(BaseContext.getCurrentId());

        // 返回
        return OrderSubmitVO.builder()
                .id(orders.getId())
                .orderAmount(orders.getAmount())
                .orderNumber(orders.getNumber())
                .orderTime(orders.getOrderTime())
                .build();
    }

    @Override
    public PageResult<OrderVO> history(OrderHistoryDTO orderHistoryDTO) {

        PageHelper.startPage(
                orderHistoryDTO.getPage(),
                orderHistoryDTO.getPageSize()
        );

        Orders orders = Orders.builder()
                .userId(BaseContext.getCurrentId())
                .status(orderHistoryDTO.getStatus())
                .build();

        // 查询订单
        Page<Orders> ordersPage = ordersMapper.page(orders);

        // 转换 VO
        List<OrderVO> records = new ArrayList<>();

        for (Orders order : ordersPage) {
            List<OrderDetail> list = orderDetailMapper.selectByOrderId(order.getId());
            OrderVO orderVO = new OrderVO();
            BeanUtils.copyProperties(order, orderVO);
            orderVO.setOrderDetailList(list);
            records.add(orderVO);
        }

        return new PageResult<>(
                ordersPage.getTotal(),
                records
        );
    }

    @Override
    public OrderVO id(Long id){
        Orders orders = ordersMapper.selectById(id);

        List<OrderDetail> orderDetailList = orderDetailMapper.selectByOrderId(id);

        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(orders, orderVO);
        orderVO.setOrderDetailList(orderDetailList);

        return orderVO;
    }

    @Override
    public OrderPaymentVO pay(OrderPaymentDTO orderPaymentDTO){
        Orders orders = ordersMapper.selectByNumberAndUserId(orderPaymentDTO.getOrderNumber(), BaseContext.getCurrentId());

        orders.setStatus(StatusConstant.TO_BE_CONFIRMED);
        orders.setPayStatus(StatusConstant.PAID);
        orders.setCheckoutTime(LocalDateTime.now());

        ordersMapper.update(orders);

        Map map = new HashMap();
        map.put("type", StatusConstant.PAY); // 1 来单提醒
        map.put("orderId", orders.getId());
        map.put("message", "订单号: " + orders.getNumber());
        String json = JSON.toJSONString(map);
        webSocketServer.sentToAllClient(json);

        return OrderPaymentVO.builder()
                .estimatedDeliveryTime(LocalDateTime.now().plusMinutes(30))
                .build();
    }

    @Override
    public void cancel(Long id){
        Orders orders = ordersMapper.selectById(id);

        if(orders == null){
            throw new OrderBusinessException(MessageConstant.ORDER_NOT_EXIST);
        }

        if(orders.getStatus() > StatusConstant.DELIVERY_IN_PROGRESS){
            throw new OrderBusinessException(MessageConstant.ORDER_STATUS_ERROR);
        }

        if(orders.getStatus().equals(StatusConstant.TO_BE_CONFIRMED)){
            orders.setPayStatus(StatusConstant.REFUND);
        }

        orders.setStatus(StatusConstant.CANCELLED);
        orders.setCancelTime(LocalDateTime.now());
        orders.setCancelReason("用户取消");

        ordersMapper.update(orders);
    }

    @Override
    public void repetition(Long id){
        List<OrderDetail> orderDetailList = orderDetailMapper.selectByOrderId(id);

        List<ShoppingCart> shoppingCartList = new ArrayList<>();
        for(OrderDetail orderDetail : orderDetailList){
            ShoppingCart cart = new ShoppingCart();
            BeanUtils.copyProperties(orderDetail, cart);
            cart.setUserId(BaseContext.getCurrentId());
            cart.setCreateTime(LocalDateTime.now());
            shoppingCartList.add(cart);
        }

        shoppingCartMapper.deleteByUserId(BaseContext.getCurrentId());

        shoppingCartMapper.insertBatch(shoppingCartList);
    }

    @Override
    public void reminder(Long id){
        Orders orders = ordersMapper.selectById(id);

        if(orders == null) throw new OrderBusinessException(MessageConstant.ORDER_NOT_EXIST);

        Map map = new HashMap();
        map.put("type", StatusConstant.REMINDER);
        map.put("orderId", id);
        map.put("message", "订单号: " + orders.getNumber());
        String json = JSON.toJSONString(map);
        webSocketServer.sentToAllClient(json);
    }

    @Override
    public PageResult<OrderSearchVO> search(OrderSearchDTO orderSearchDTO){
        PageHelper.startPage(orderSearchDTO.getPage(), orderSearchDTO.getPageSize());


        Page<Orders> ordersPage = ordersMapper.search(orderSearchDTO);

        List<OrderSearchVO> records = new ArrayList<>();
        for (Orders order : ordersPage) {
            List<OrderDetail> list = orderDetailMapper.selectByOrderId(order.getId());
            OrderSearchVO orderSearchVO = new OrderSearchVO();
            BeanUtils.copyProperties(order, orderSearchVO);
            orderSearchVO.setOrderDishes(list.stream()
                    .map(OrderDetail::getName)
                    .collect(Collectors.joining(",")));
            records.add(orderSearchVO);
        }

        return new PageResult<>(
                ordersPage.getTotal(),
                records
        );
    }

    @Override
    public void delivery(Long id){
        Orders orders = ordersMapper.selectById(id);

        if(orders == null)throw new OrderBusinessException(MessageConstant.ORDER_NOT_EXIST);
        else if (!Objects.equals(orders.getStatus(), StatusConstant.CONFIRMED)) throw new OrderBusinessException(MessageConstant.ORDER_STATUS_ERROR);

        ordersMapper.update(Orders.builder().id(id).status(StatusConstant.DELIVERY_IN_PROGRESS).build());
    }

    @Override
    public void confirm(OrderConfirmDTO orderConfirmDTO){
        Orders orders = ordersMapper.selectById(orderConfirmDTO.getId());

        if(orders == null)throw new OrderBusinessException(MessageConstant.ORDER_NOT_EXIST);
        else if (!Objects.equals(orders.getStatus(), StatusConstant.TO_BE_CONFIRMED)) throw new OrderBusinessException(MessageConstant.ORDER_STATUS_ERROR);

        ordersMapper.update(Orders.builder().id(orderConfirmDTO.getId()).status(StatusConstant.CONFIRMED).build());
    }

    @Override
    public void rejection(OrderRejectionDTO orderRejectionDTO){
        Orders orders = ordersMapper.selectById(orderRejectionDTO.getId());

        if(orders == null)throw new OrderBusinessException(MessageConstant.ORDER_NOT_EXIST);
        else if(orderRejectionDTO.getRejectionReason() == null || orderRejectionDTO.getRejectionReason().isEmpty()) throw new OrderBusinessException(MessageConstant.REJECTION_REASON_EMPTY);
        else if (!Objects.equals(orders.getStatus(), StatusConstant.TO_BE_CONFIRMED) || !Objects.equals(orders.getPayStatus(), StatusConstant.PAID)) throw new OrderBusinessException(MessageConstant.ORDER_STATUS_ERROR);

        ordersMapper.update(Orders.builder()
                .id(orderRejectionDTO.getId())
                .status(StatusConstant.CANCELLED)
                .payStatus(StatusConstant.REFUND)
                .rejectionReason(orderRejectionDTO.getRejectionReason())
                .cancelTime(LocalDateTime.now())
                .build());
    }

    @Override
    public void complete(Long id){
        Orders orders = ordersMapper.selectById(id);

        if(orders == null)throw new OrderBusinessException(MessageConstant.ORDER_NOT_EXIST);
        else if (!Objects.equals(orders.getStatus(), StatusConstant.DELIVERY_IN_PROGRESS)) throw new OrderBusinessException(MessageConstant.ORDER_STATUS_ERROR);

        ordersMapper.update(Orders.builder()
                    .id(id)
                    .status(StatusConstant.COMPLETED)
                    .deliveryTime(LocalDateTime.now())
                    .build());
    }

    @Override
    public OrderStatisticsVO statistics(){
        return OrderStatisticsVO.builder()
                .confirmed(ordersMapper.selectByStatus(StatusConstant.CONFIRMED).size())
                .deliveryInProgress(ordersMapper.selectByStatus(StatusConstant.DELIVERY_IN_PROGRESS).size())
                .toBeConfirmed(ordersMapper.selectByStatus(StatusConstant.TO_BE_CONFIRMED).size())
                .build();
    }

    @Override
    public void cancel(OrderCancelDTO orderCancelDTO){
        Orders orders = ordersMapper.selectById(orderCancelDTO.getId());

        if(orders == null)throw new OrderBusinessException(MessageConstant.ORDER_NOT_EXIST);
        else if(orderCancelDTO.getCancelReason() == null || orderCancelDTO.getCancelReason().isEmpty()) throw new OrderBusinessException(MessageConstant.CANCEL_REASON_EMPTY);
        else if (Objects.equals(orders.getStatus(), StatusConstant.COMPLETED)) throw new OrderBusinessException(MessageConstant.ORDER_STATUS_ERROR);

        ordersMapper.update(Orders.builder()
                .id(orderCancelDTO.getId())
                .status(StatusConstant.CANCELLED)
                .payStatus(StatusConstant.REFUND)
                .cancelReason(orderCancelDTO.getCancelReason())
                .cancelTime(LocalDateTime.now())
                .build());
    }

    @Override
    public OrderDetailsVO details(Long id){
        Orders orders = ordersMapper.selectById(id);

        List<OrderDetail> orderDetailList = orderDetailMapper.selectByOrderId(id);

        OrderDetailsVO orderDetailsVO = new OrderDetailsVO();
        BeanUtils.copyProperties(orders, orderDetailsVO);
        orderDetailsVO.setOrderDetailList(orderDetailList);
        orderDetailsVO.setOrderDishes(
                orderDetailList.stream()
                        .map(OrderDetail::getName)
                        .collect(Collectors.joining(","))
        );

        return orderDetailsVO;
    }
}
