package com.player32611.service.impl;

import com.player32611.constant.MessageConstant;
import com.player32611.constant.StatusConstant;
import com.player32611.dto.ReportDTO;
import com.player32611.dto.TopGoodDTO;
import com.player32611.exception.ReportBusinessException;
import com.player32611.mapper.OrdersMapper;
import com.player32611.mapper.UserMapper;
import com.player32611.service.ReportService;
import com.player32611.vo.ReportOrdersVO;
import com.player32611.vo.ReportTop10VO;
import com.player32611.vo.ReportTurnoverVO;
import com.player32611.vo.ReportUserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
