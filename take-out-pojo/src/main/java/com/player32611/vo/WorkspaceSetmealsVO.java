package com.player32611.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkspaceSetmealsVO implements Serializable {
    private Integer discontinued;
    private Integer sold;
}
