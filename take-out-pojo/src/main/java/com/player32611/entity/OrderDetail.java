package com.player32611.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail implements Serializable {
    /** 主键 ID (PK, AI) */
    private Long id;

    /** 名称 */
    private String name;

    /** 图片地址 */
    private String image;

    /** 订单 ID (关联 orders 表) */
    private Long orderId;

    /** 菜品 ID */
    private Long dishId;

    /** 套餐 ID */
    private Long setmealId;

    /** 菜品口味 */
    private String dishFlavor;

    /** 数量 */
    private Integer number;

    /** 金额 (DECIMAL(10,2)) */
    private BigDecimal amount;
}
