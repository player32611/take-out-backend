package com.player32611.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DishPageDTO implements Serializable {
    private String name;
    private Integer page;
    private Integer pageSize;
    private Long categoryId;
    private Integer status;
}
