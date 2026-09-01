package com.player32611.service;

import com.player32611.vo.WorkspaceBusinessVO;
import com.player32611.vo.WorkspaceDishesVO;
import com.player32611.vo.WorkspaceOrdersVO;
import com.player32611.vo.WorkspaceSetmealsVO;

public interface WorkspaceService {
    WorkspaceBusinessVO business();

    WorkspaceOrdersVO orders();

    WorkspaceDishesVO dishes();

    WorkspaceSetmealsVO setmeals();
}
