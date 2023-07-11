package com.ffl.ahydboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ffl.ahydboot.bean.PlanDesignDto;
import com.ffl.ahydboot.bean.PlanDesignInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ffl.ahydboot.bean.PlanDesignInfoQueryDto;
import com.ffl.ahydboot.common.ResponseData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* @author fanFengLi
* @description 针对表【t_plan_design_info(规划设计评估主表)】的数据库操作Service
* @createDate 2023-07-10 16:45:49
*/
public interface PlanDesignInfoService extends IService<PlanDesignInfo> {

    String getPlanBillNo();

    ResponseData createBill(PlanDesignInfo planDesignInfo);

    ResponseData createBillAndAnalyse(PlanDesignInfo planDesignInfo);

    IPage<PlanDesignInfo> searchBill(PlanDesignInfoQueryDto queryDto);

    ResponseData upload(HttpServletRequest request, HttpServletResponse response);
}
