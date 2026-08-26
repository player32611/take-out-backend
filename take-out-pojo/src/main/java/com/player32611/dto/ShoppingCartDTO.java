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
public class ShoppingCartDTO implements Serializable {
    private Long setmealId;
    private Long dishId;
    private String dishFlavor;
}
