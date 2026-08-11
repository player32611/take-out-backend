package com.player32611.result;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageResult<T> implements Serializable {
    private Integer total; //总记录数
    private List<T> records; //当前页数据集合
}
