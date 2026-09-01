package com.player32611.controller.admin;

import com.player32611.result.Result;
import com.player32611.service.WorkspaceService;
import com.player32611.vo.WorkspaceBusinessVO;
import com.player32611.vo.WorkspaceDishesVO;
import com.player32611.vo.WorkspaceOrdersVO;
import com.player32611.vo.WorkspaceSetmealsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/workspace")
@Slf4j
public class WorkspaceController {

    @Autowired
    private WorkspaceService workspaceService;

    @GetMapping("/businessData")
    public Result<WorkspaceBusinessVO> business(){
        log.info("查询今日运营数据");

        WorkspaceBusinessVO workspaceBusinessVO = workspaceService.business();

        return Result.success(workspaceBusinessVO);
    }

    @GetMapping("/overviewOrders")
    public Result<WorkspaceOrdersVO> orders(){
        log.info("查询订单管理数据");

        WorkspaceOrdersVO workspaceOrdersVO = workspaceService.orders();

        return Result.success(workspaceOrdersVO);
    }

    @GetMapping("/overviewDishes")
    public Result<WorkspaceDishesVO> dishes(){
        log.info("查询菜品总览");

        WorkspaceDishesVO workspaceDishesVO = workspaceService.dishes();

        return Result.success(workspaceDishesVO);
    }

    @GetMapping("/overviewSetmeals")
    public Result<WorkspaceSetmealsVO> setmeals(){
        log.info("查询套餐总览");

        WorkspaceSetmealsVO workspaceSetmealsVO = workspaceService.setmeals();

        return Result.success(workspaceSetmealsVO);
    }
}
