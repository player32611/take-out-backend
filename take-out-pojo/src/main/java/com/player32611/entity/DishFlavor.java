package com.player32611.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishFlavor implements Serializable {
    private Long id;
    private Long dishId;
    private String name;
    private String value;
}
