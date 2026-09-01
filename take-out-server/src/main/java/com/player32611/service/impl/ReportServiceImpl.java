package com.player32611.service.impl;

import com.player32611.constant.MessageConstant;
import com.player32611.constant.StatusConstant;
import com.player32611.dto.ReportDTO;
import com.player32611.dto.TopGoodDTO;
import com.player32611.exception.ReportBusinessException;
import com.player32611.mapper.OrdersMapper;
import com.player32611.mapper.UserMapper;
import com.player32611.service.ReportService;
import com.player32611.utils.ExcelUtil;
import com.player32611.vo.ReportOrdersVO;
import com.player32611.vo.ReportTop10VO;
import com.player32611.vo.ReportTurnoverVO;
import com.player32611.vo.ReportUserVO;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public ReportTurnoverVO turnover(ReportDTO reportDTO){
        LocalDate begin = reportDTO.getBegin();
        LocalDate end = reportDTO.getEnd();

        if(begin.isAfter(end)) throw new ReportBusinessException(MessageConstant.BEGIN_DATE_OVER_END_DATE);

        List<LocalDate> localDateList = new ArrayList<>();
        localDateList.add(begin);
        while (!begin.equals(end)){
            begin = begin.plusDays(1);
            localDateList.add(begin);
        }

        List<BigDecimal> turnoverList = new ArrayList<>();
        for(LocalDate localDate : localDateList){
            LocalDateTime beginTime = LocalDateTime.of(localDate, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(localDate, LocalTime.MAX);

            BigDecimal turnover = ordersMapper.selectAmountSum(beginTime, endTime, StatusConstant.COMPLETED);
            turnover = turnover == null ? BigDecimal.valueOf(0.0) : turnover;
            turnoverList.add(turnover);
        }

        return ReportTurnoverVO.builder()
                .dateList(StringUtils.join(localDateList, ","))
                .turnoverList(StringUtils.join(turnoverList, ","))
                .build();
    }

    @Override
    public ReportUserVO user(ReportDTO reportDTO){
        LocalDate begin = reportDTO.getBegin();
        LocalDate end = reportDTO.getEnd();

        if(begin.isAfter(end)) throw new ReportBusinessException(MessageConstant.BEGIN_DATE_OVER_END_DATE);

        List<LocalDate> localDateList = new ArrayList<>();
        localDateList.add(begin);
        while (!begin.equals(end)){
            begin = begin.plusDays(1);
            localDateList.add(begin);
        }

        List<Integer> newUserList = new ArrayList<>();
        List<Integer> totalUserList = new ArrayList<>();
        for(LocalDate localDate : localDateList){
            LocalDateTime beginTime = LocalDateTime.of(localDate, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(localDate, LocalTime.MAX);

            Integer totalUser = userMapper.selectSumByDate(null, endTime);
            Integer newUser = userMapper.selectSumByDate(beginTime, endTime);

            newUserList.add(newUser);
            totalUserList.add(totalUser);
        }

        return ReportUserVO.builder()
                .dateList(StringUtils.join(localDateList, ","))
                .newUserList(StringUtils.join(newUserList, ","))
                .totalUserList(StringUtils.join(totalUserList, ","))
                .build();
    }

    @Override
    public ReportOrdersVO orders(ReportDTO reportDTO){
        LocalDate begin = reportDTO.getBegin();
        LocalDate end = reportDTO.getEnd();

        if(begin.isAfter(end)) throw new ReportBusinessException(MessageConstant.BEGIN_DATE_OVER_END_DATE);

        List<LocalDate> localDateList = new ArrayList<>();
        localDateList.add(begin);
        while (!begin.equals(end)){
            begin = begin.plusDays(1);
            localDateList.add(begin);
        }

        List<Integer> orderCountList = new ArrayList<>();
        List<Integer> validOrderCountList = new ArrayList<>();
        for(LocalDate localDate : localDateList){
            LocalDateTime beginTime = LocalDateTime.of(localDate, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(localDate, LocalTime.MAX);

            Integer totalOrderCount = ordersMapper.selectSumByDateAndStatus(beginTime, endTime, null);
            Integer validOrderCount = ordersMapper.selectSumByDateAndStatus(beginTime, endTime, StatusConstant.COMPLETED);

            orderCountList.add(totalOrderCount);
            validOrderCountList.add(validOrderCount);
        }

        Integer totalOrderCount = orderCountList.stream().reduce(Integer::sum).get();
        Integer validOrderCount = validOrderCountList.stream().reduce(Integer::sum).get();
        double orderCompletionRate = 0.0;
        if(totalOrderCount != 0) orderCompletionRate = validOrderCount.doubleValue() / totalOrderCount.doubleValue();

        return ReportOrdersVO.builder()
                .dateList(StringUtils.join(localDateList, ","))
                .orderCompletionRate(orderCompletionRate)
                .orderCountList(StringUtils.join(orderCountList, ","))
                .totalOrderCount(totalOrderCount)
                .validOrderCount(validOrderCount)
                .validOrderCountList(StringUtils.join(validOrderCountList, ","))
                .build();
    }

    @Override
    public ReportTop10VO top10(ReportDTO reportDTO){
        LocalDateTime begin = LocalDateTime.of(reportDTO.getBegin(), LocalTime.MIN);
        LocalDateTime end = LocalDateTime.of(reportDTO.getEnd(), LocalTime.MAX);

        List<TopGoodDTO> topGoodDTOList = ordersMapper.selectTop10ByDate(begin, end);

        List<String> names = topGoodDTOList.stream().map(TopGoodDTO::getName).toList();
        List<Integer> numbers = topGoodDTOList.stream().map(TopGoodDTO::getNumber).toList();

        return ReportTop10VO.builder()
                .nameList(StringUtils.join(names, ","))
                .numberList(StringUtils.join(numbers, ","))
                .build();
    }

    @Override
    public void export(HttpServletResponse response){
        LocalDate beginDate = LocalDate.now().minusDays(30);
        LocalDate endDate = LocalDate.now().minusDays(1);
        LocalDateTime beginTime = LocalDateTime.of(beginDate, LocalTime.MIN);
        LocalDateTime endTime = LocalDateTime.of(endDate, LocalTime.MAX);


        Integer newUsers = userMapper.selectSumByDate(beginTime, endTime);
        Integer validOrderCount = ordersMapper.selectSumByDateAndStatus(beginTime, endTime, StatusConstant.COMPLETED);
        Integer totalOrderCount = ordersMapper.selectSumByDateAndStatus(beginTime, endTime, null);
        BigDecimal turnover = ordersMapper.selectAmountSum(beginTime, endTime, StatusConstant.COMPLETED);
        turnover = turnover == null ? BigDecimal.valueOf(0.0) : turnover;

        double orderCompletionRate = 0.0;
        double unitPrice = 0.0;
        if(totalOrderCount != 0) orderCompletionRate = validOrderCount.doubleValue() / totalOrderCount.doubleValue();
        if(validOrderCount != 0) unitPrice = turnover.doubleValue() / validOrderCount.doubleValue();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("运营数据");

        // 创建样式
        CellStyle titleStyle = ExcelUtil.createTitleStyle(workbook);
        CellStyle sectionStyle = ExcelUtil.createSectionStyle(workbook);
        CellStyle dataStyle = ExcelUtil.createDataStyle(workbook);
        CellStyle headerStyle = ExcelUtil.createHeaderStyle(workbook);

        // 设置宽度
        ExcelUtil.setColumnWidth(sheet, new int[]{15,15,15,15,15,15});

        // 创建标题
        ExcelUtil.createMergeTitle(sheet, "运营数据报表", 0, 0, 5, titleStyle);

        // 概览
        ExcelUtil.createMergeTitle(sheet, "概览数据", 2, 0, 5, sectionStyle);

        // 数据
        ExcelUtil.createRow(sheet, 3, new String[]{
                "营业额", "￥" + turnover,
                "订单完成率", orderCompletionRate * 100 + "%",
                "新增用户", newUsers.toString()
        }, dataStyle);
        ExcelUtil.createRow(sheet, 4, new String[]{
                "有效订单", validOrderCount.toString(),
                "平均客单价", "￥" + unitPrice
        }, dataStyle);

        // 明细
        ExcelUtil.createMergeTitle(sheet, "明细数据", 5, 0, 5, sectionStyle);
        ExcelUtil.createRow(sheet, 6, new String[]{"日期", "营业额", "有效订单", "订单完成率", "平均客单价", "新增用户"}, headerStyle);

        for(int i = 0; i < 30; i++){
            LocalDate date = beginDate.plusDays(i);
            LocalDateTime begin = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime end = LocalDateTime.of(date, LocalTime.MAX);

            Integer todayNewUsers = userMapper.selectSumByDate(begin, end);
            Integer todayValidOrderCount = ordersMapper.selectSumByDateAndStatus(begin, end, StatusConstant.COMPLETED);
            Integer todayTotalOrderCount = ordersMapper.selectSumByDateAndStatus(begin, end, null);
            BigDecimal todayTurnover = ordersMapper.selectAmountSum(begin, end, StatusConstant.COMPLETED);
            todayTurnover = todayTurnover == null ? BigDecimal.valueOf(0.0) : todayTurnover;

            double todayOrderCompletionRate = 0.0;
            double todayUnitPrice = 0.0;
            if(todayTotalOrderCount != 0) todayOrderCompletionRate = todayValidOrderCount.doubleValue() / todayTotalOrderCount.doubleValue();
            if(todayValidOrderCount != 0) todayUnitPrice = todayTurnover.doubleValue() / todayValidOrderCount.doubleValue();

            ExcelUtil.createRow(sheet, 7 + i, new String[]{
                    date.toString(),
                    "￥" + todayTurnover,
                    todayValidOrderCount.toString(),
                    todayOrderCompletionRate * 100 + "%",
                    "￥" + todayUnitPrice,
                    todayNewUsers.toString()
            }, dataStyle);
        }

        // 输出
        try{
            ServletOutputStream out = response.getOutputStream();
            workbook.write(out);

            out.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
