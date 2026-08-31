package com.player32611.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportDTO implements Serializable {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;
}
