package com.player32611.controller.admin;

import com.player32611.dto.ReportDTO;
import com.player32611.result.Result;
import com.player32611.service.ReportService;
import com.player32611.vo.ReportOrdersVO;
import com.player32611.vo.ReportTop10VO;
import com.player32611.vo.ReportTurnoverVO;
import com.player32611.vo.ReportUserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/report")
@Slf4j
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/turnoverStatistics")
    public Result<ReportTurnoverVO> turnover(ReportDTO reportDTO){
        log.info("营业额统计接口: {}", reportDTO);

        ReportTurnoverVO reportTurnoverVO = reportService.turnover(reportDTO);

        return Result.success(reportTurnoverVO);
    }

    @GetMapping("/userStatistics")
    public Result<ReportUserVO> user(ReportDTO reportDTO){
        log.info("用户统计接口: {}", reportDTO);

        ReportUserVO reportUserVO = reportService.user(reportDTO);

        return Result.success(reportUserVO);
    }

    @GetMapping("/ordersStatistics")
    public Result<ReportOrdersVO> orders(ReportDTO reportDTO){
        log.info("订单统计接口: {}", reportDTO);

        ReportOrdersVO reportOrdersVO = reportService.orders(reportDTO);

        return Result.success(reportOrdersVO);
    }

    @GetMapping("/top10")
    public Result<ReportTop10VO> top10(ReportDTO reportDTO){
        log.info("查询销量排名top10接口: {}", reportDTO);

        ReportTop10VO reportTop10VO = reportService.top10(reportDTO);

        return Result.success(reportTop10VO);
    }
}
