package com.player32611.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orders implements Serializable {
    /** 主键 ID (PK, AI) */
    private Long id;

    /** 订单编号 */
    private String number;

    /** 状态 (Int) */
    private Integer status;

    /** 用户 ID (BIGINT) */
    private Long userId;

    /** 地址簿 ID (BIGINT) */
    private Long addressBookId;

    /** 下单时间 (DATETIME) */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderTime;

    /** 结账时间 (DATETIME) */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime checkoutTime;

    /** 支付方式 (Int) */
    private Integer payMethod;

    /** 支付状态 (TINYINT) */
    private Integer payStatus;

    /** 金额 (DECIMAL(10,2)) */
    private BigDecimal amount;

    /** 备注 (VARCHAR(100)) */
    private String remark;

    /** 手机号 (VARCHAR(11)) */
    private String phone;

    /** 地址 (VARCHAR(255)) */
    private String address;

    /** 用户名 (VARCHAR(32)) */
    private String userName;

    /** 收货人 (VARCHAR(32)) */
    private String consignee;

    /** 取消原因 (VARCHAR(255)) */
    private String cancelReason;

    /** 拒绝原因 (VARCHAR(255)) */
    private String rejectionReason;

    /** 取消时间 (DATETIME) */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime cancelTime;

    /** 预计送达时间 (DATETIME) */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime estimatedDeliveryTime;

    /** 配送状态 (TINYINT) */
    private Integer deliveryStatus;

    /** 送达时间 (DATETIME) */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deliveryTime;

    /** 打包数量 (INT) */
    private Integer packAmount;

    /** 餐具数量 (INT) */
    private Integer tablewareNumber;

    /** 餐具状态 (TINYINT) */
    private Integer tablewareStatus;
}
