package com.player32611.dto;

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
public class OrderSubmitDTO implements Serializable {
    private Long addressBookId;
    private BigDecimal amount;
    private Integer deliveryStatus;
    private LocalDateTime estimatedDeliveryTime;
    private BigDecimal packAmount;
    private Integer payMethod;
    private String remark;
    private Integer tablewareNumber;
    private Integer tablewareStatus;
}
