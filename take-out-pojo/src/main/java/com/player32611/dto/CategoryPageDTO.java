package com.player32611.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPageDTO implements Serializable {
    private String name;
    private Integer page;
    private Integer pageSize;
    private Integer type;
}
