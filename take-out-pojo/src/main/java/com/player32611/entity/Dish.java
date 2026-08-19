package com.player32611.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dish {
    private Long id;
    private String name;
    private Long category_id;
    private BigDecimal price;
    private String image;
    private String description;
    private Integer status;
    private LocalDateTime create_time;
    private LocalDateTime update_time;
    private Long create_user;
    private Long update_user;
}
