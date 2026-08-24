package com.player32611.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SetmealPageDTO implements Serializable {
    private String name;
    private Integer page;
    private Integer pageSize;
    private Long categoryId;
    private Integer status;
}
