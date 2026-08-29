package com.player32611.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.player32611.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderVO implements Serializable {

    /** 主键 ID */
    private Long id;

    /** 订单编号 */
    private String number;

    /** 状态 */
    private Integer status;

    /** 用户 ID */
    private Long userId;

    /** 地址簿 ID */
    private Long addressBookId;

    /** 下单时间 (图中为 string) */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderTime;

    /** 结账时间 (图中为 string) */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime checkoutTime;

    /** 支付方式 */
    private Integer payMethod;

    /** 支付状态 */
    private Integer payStatus;

    /** 金额 */
    private BigDecimal amount;

    /** 备注 */
    private String remark;

    /** 用户名 (图中允许为 null) */
    private String userName;

    /** 手机号 */
    private String phone;

    /** 地址 */
    private String address;

    /** 收货人 */
    private String consignee;

    /** 取消原因 (允许为 null) */
    private String cancelReason;

    /** 拒绝原因 (允许为 null) */
    private String rejectionReason;

    /** 取消时间 (允许为 null) */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime cancelTime;

    /** 预计送达时间 (图中为 string) */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime estimatedDeliveryTime;

    /** 配送状态 */
    private Integer deliveryStatus;

    /** 送达时间 (允许为 null) */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deliveryTime;

    /** 打包数量 */
    private Integer packAmount;

    /** 餐具数量 */
    private Integer tablewareNumber;

    /** 餐具状态 */
    private Integer tablewareStatus;

    /**
     * 订单明细列表
     */
    private List<OrderDetail> orderDetailList;
}
