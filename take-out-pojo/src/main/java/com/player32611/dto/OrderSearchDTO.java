package com.player32611.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderSearchDTO implements Serializable {
    private LocalDateTime beginTime;
    private LocalDateTime endTime;
    private String number;
    private Integer page;
    private Integer pageSize;
    private String phone;
    private Integer status;
}
