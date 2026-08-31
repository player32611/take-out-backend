package com.player32611.service;

import com.player32611.dto.ReportDTO;
import com.player32611.vo.ReportOrdersVO;
import com.player32611.vo.ReportTop10VO;
import com.player32611.vo.ReportTurnoverVO;
import com.player32611.vo.ReportUserVO;

public interface ReportService {
    ReportTurnoverVO turnover(ReportDTO reportDTO);

    ReportUserVO user(ReportDTO reportDTO);

    ReportOrdersVO orders(ReportDTO reportDTO);

    ReportTop10VO top10(ReportDTO reportDTO);
}
