package com.player32611.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String phone;
    private String sex;
    private String id_number;
    private Integer status;
    private LocalDateTime create_time;
    private LocalDateTime update_time;
    private Integer create_user;
    private Integer update_user;
}
