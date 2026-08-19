package com.player32611.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private Long id;
    private String name;
    private Integer type;
    private Integer sort;
    private Integer status;
    private LocalDateTime create_time;
    private LocalDateTime update_time;
    private Long create_user;
    private Long update_user;
}
