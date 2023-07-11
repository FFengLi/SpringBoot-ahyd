package com.ffl.ahydboot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ffl.ahydboot.bean.PlanDesignDto;
import com.ffl.ahydboot.bean.PlanDesignInfo;
import com.ffl.ahydboot.bean.PlanDesignInfoQueryDto;
import com.ffl.ahydboot.common.ResponseData;
import com.ffl.ahydboot.service.PlanDesignInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: fanFengLi
 * @Date: 2023/07/10/17:23
 * @Description:
 */
@RestController
public class PlanDesignController {
    private static Logger logger = LoggerFactory.getLogger(PlanDesignController.class);
    @Autowired
    private PlanDesignInfoService planDesignInfoService;


    @GetMapping("/getPlanBillNo")
    public ResponseData getPlanBillNo() {
        String planBillNo = planDesignInfoService.getPlanBillNo();
        if (!planBillNo.isEmpty()) {
            logger.info("工单号：" +planBillNo);
            return ResponseData.ok(planBillNo);
        } else {
            return ResponseData.fail();
        }
    }

    @PostMapping("/createBill")
    public ResponseData createBill(@RequestBody PlanDesignInfo planDesignInfo){
        logger.info("创建工单:{}",planDesignInfo);
        planDesignInfo.setReviewer("fanFengLi");
        ResponseData responseData = planDesignInfoService.createBill(planDesignInfo);
        return responseData;

    }

    @PostMapping("/createBillAndAnalyse")
    public ResponseData createBillAndAnalyse(@RequestBody PlanDesignInfo planDesignInfo){
        // 返回对三种文件（系统规划CAD图纸、系统规划文件excel、波道规划文件excel）的分析结果
        planDesignInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
        planDesignInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        ResponseData analyseResult = null;
        analyseResult = planDesignInfoService.createBillAndAnalyse(planDesignInfo);
        return analyseResult;
    }

    @PostMapping("/searchBill")
    public ResponseData searchBill(@RequestBody(required = false) PlanDesignInfoQueryDto queryDto){
        logger.info("搜索条件：{}", queryDto);
        IPage<PlanDesignInfo> planDesignInfoIPage = planDesignInfoService.searchBill(queryDto);
        return ResponseData.ok(planDesignInfoIPage);
    }

    @PostMapping("/upload")
    public ResponseData upload(HttpServletRequest request, HttpServletResponse response) {
        ResponseData upload = planDesignInfoService.upload(request, response);
        return upload;
    }

}
