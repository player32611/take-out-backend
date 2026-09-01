package com.player32611.vo;

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
public class WorkspaceBusinessVO implements Serializable {
    private Integer newUsers;
    private Double orderCompletionRate;
    private BigDecimal turnover;
    private Double unitPrice;
    private Integer validOrderCount;
}
