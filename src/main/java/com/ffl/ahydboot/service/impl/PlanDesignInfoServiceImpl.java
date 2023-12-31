package com.ffl.ahydboot.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ffl.ahydboot.bean.*;
import com.ffl.ahydboot.common.ResponseData;
import com.ffl.ahydboot.mapper.PlanDesignHistoryRecordMapper;
import com.ffl.ahydboot.service.PlanDesignInfoService;
import com.ffl.ahydboot.mapper.PlanDesignInfoMapper;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;


/**
* @author fanFengLi
* @description 针对表【t_plan_design_info(规划设计评估主表)】的数据库操作Service实现
* @createDate 2023-07-10 16:45:49
*/
@Service
public class PlanDesignInfoServiceImpl extends ServiceImpl<PlanDesignInfoMapper, PlanDesignInfo>
    implements PlanDesignInfoService{

    private static Logger logger = LoggerFactory.getLogger(PlanDesignInfoServiceImpl.class);
    @Autowired
    private PlanDesignInfoMapper planDesignInfoMapper;

    @Autowired
    private PlanDesignHistoryRecordMapper planDesignHistoryRecordMapper;

    @Override
    public String getPlanBillNo() {
        List<String> planBillNumbers = planDesignInfoMapper.getPlanBillNos();
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String nowDate = sdf.format(now);

        if (planBillNumbers.size() != 0) {
            String planBillNo = planBillNumbers.get(0);
            String planBillNoDate = planBillNo.substring(9,17);

            // 判断当天是否有工单号
            if (planBillNoDate.equals(nowDate)) {
                logger.info("当天有工单号");
                String number = planBillNo.substring(18);
                int no = Integer.parseInt(number);
                no++;
                number = String.valueOf(no);
                if (number.length() == 1) {
                    return "AHYD-PMS-" + planBillNoDate + "-00" + number;
                } else if (number.length() == 2) {
                    return "AHYD-PMS-" + planBillNoDate + "-0" + number;
                } else {
                    return "AHYD-PMS-" + planBillNoDate + "-" + number;
                }
            } else {
                // 当天没有工单号
                logger.info("当天没有工单号");
                return "AHYD-PMS-" + nowDate + "-001";
            }
        } else {
            // 数据库表中无工单号
            logger.info("数据库表中无工单号");
            return "AHYD-PMS-" + nowDate + "-001";
        }
    }

    @Override
    public ResponseData createBill(PlanDesignInfo planDesignInfo) {
        // 获取当前时间
        LocalDateTime currentDateTime = LocalDateTime.now();

        // 格式化日期时间
        Timestamp now = Timestamp.valueOf(currentDateTime);

        logger.info("工单创建时间："+currentDateTime);
        logger.info("工单创建时间戳："+now);

        planDesignInfo.setCreateTime(now);
        planDesignInfo.setUpdateTime(now);

        String uuid = UUID.randomUUID().toString().replace("-", "");
        uuid = uuid.replaceAll("[a-z]", "").substring(0, 15);
        Long id = Long.parseLong(uuid);
        planDesignInfo.setId(id);


        int save =planDesignInfoMapper.insert(planDesignInfo);
        if(save==1){
            logger.info("工单创建成功");
            return  new ResponseData("工单创建成功");
        }
        logger.info("工单创建失败");

        return new ResponseData("工单创建失败");
    }

    @Transactional
    @Override
    public ResponseData createBillAndAnalyse(PlanDesignInfo planDesignInfo) {
        Map<String, Object> map = new HashMap<>(8);

        map.put("systemCADFilePath", planDesignInfo.getSystemCadFileUrl());
        map.put("systemExcelFilePath", planDesignInfo.getSystemExcelFileUrl());
        map.put("channelExcelFilePath", planDesignInfo.getChannelExcelFileUrl());
        map.put("planBillNo", planDesignInfo.getPlanBillNo());
        map.put("cadCoordLeft", planDesignInfo.getCadCoordLeft());
        map.put("cadCoordTop", planDesignInfo.getCadCoordTop());
        map.put("cadCoordRight", planDesignInfo.getCadCoordRight());
        map.put("cadCoordBottom", planDesignInfo.getCadCoordBottom());
        String queryJson = new Gson().toJson(map);
        Map<String, String> heads = new HashMap<>(1);
        heads.put("Content-Type", "application/json;charset=UTF-8");

        HttpResponse response = HttpRequest.post("http://localhost:9999/analyseCADCallApi")
                .headerMap(heads, false)
                .body(queryJson)
                .timeout(5 * 60 * 1000)
                .execute();
        ResponseData responseData = (ResponseData) new Gson().fromJson(response.body(), ResponseData.class);
        logger.info("分析业务返回："+responseData);
        // JSON转Map
        Map<String, Object> map2 = JSONUtil.parseObj(responseData.getData());

        PlanDesignHistoryRecord historyRecord = new PlanDesignHistoryRecord();
        // 先获取分析号
        historyRecord.setAnalyseNo((String) map2.get("analyseNo"));
        historyRecord.setAnalyseStatus(1);
        historyRecord.setAnalyseBeginTime(new Timestamp(System.currentTimeMillis()));
        historyRecord.setSystemCadFileUrl(planDesignInfo.getSystemCadFileUrl());
        historyRecord.setSystemCadFileName(planDesignInfo.getSystemCadFileName());
        historyRecord.setChannelExcelFileUrl(planDesignInfo.getSystemExcelFileUrl());
        historyRecord.setSystemExcelFileName(planDesignInfo.getSystemExcelFileName());
        historyRecord.setChannelExcelFileUrl(planDesignInfo.getChannelExcelFileUrl());
        historyRecord.setChannelExcelFileName(planDesignInfo.getChannelExcelFileName());
        historyRecord.setCreateTime(new Timestamp(System.currentTimeMillis()));


        // 保存工单
        String uuid = UUID.randomUUID().toString().replace("-", "");
        uuid = uuid.replaceAll("[a-z]", "").substring(0, 15);
        Long id = Long.parseLong(uuid);
        planDesignInfo.setId(id);

        planDesignInfoMapper.insert(planDesignInfo);
//        Long planDesignId = planDesignInfoMapper.getPlanDesignIdByPlanBiilNo(planDesignInfo.getPlanBillNo());
//        LambdaQueryWrapper<PlanDesignInfo> wrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<PlanDesignInfo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(PlanDesignInfo::getPlanBillNo, planDesignInfo.getPlanBillNo());

        PlanDesignInfo planDesignInfoByBillNo = planDesignInfoMapper.selectOne(wrapper);
        Long planDesignId = planDesignInfoByBillNo.getId();

        // 保存历史记录
        historyRecord.setPlanDesignId(planDesignId);
        String uuid2 = UUID.randomUUID().toString().replace("-", "");
        uuid = uuid2.replaceAll("[a-z]", "").substring(0, 15);
        Long id2 = Long.parseLong(uuid);
        historyRecord.setId(id2);
        planDesignHistoryRecordMapper.insert(historyRecord);
        return new ResponseData("保存成功");
    }

    @Override
    public IPage<PlanDesignInfo> searchBill(PlanDesignInfoQueryDto queryDto) {

        Page<PlanDesignInfo> page = new Page<>(queryDto.getCurrent(), queryDto.getSize());
        logger.info("当前页首记录索引：{}",queryDto.getCurrent());
        logger.info("页大小：{}",queryDto.getSize());
        LambdaQueryWrapper<PlanDesignInfo> lambdaQueryWrapper = Wrappers.lambdaQuery();
        if (!StringUtils.isBlank(queryDto.getPlanDesignName())){
            lambdaQueryWrapper.like(PlanDesignInfo::getPlanDesignName,queryDto.getPlanDesignName());
        }
        if (queryDto.getSpecId() != null){
            lambdaQueryWrapper.eq(PlanDesignInfo::getSpecId,queryDto.getSpecId());
        }
        if (!StringUtils.isBlank(queryDto.getDesigner())){
            lambdaQueryWrapper.like(PlanDesignInfo::getDesigner,queryDto.getDesigner());
        }
        if (CollectionUtil.isNotEmpty(queryDto.getCreateTime())){
            lambdaQueryWrapper.between(PlanDesignInfo::getCreateTime,queryDto.getCreateTime().get(0),queryDto.getCreateTime().get(1));
        }
        long totalRow = planDesignInfoMapper.selectCount(lambdaQueryWrapper);
        IPage<PlanDesignInfo> planDesignInfoIPage = planDesignInfoMapper.selectPage(page, lambdaQueryWrapper);
        planDesignInfoIPage.setSize(queryDto.getSize());
        planDesignInfoIPage.setCurrent(queryDto.getCurrent());
        planDesignInfoIPage.setTotal(totalRow);
        logger.info("总记录数：{}",totalRow);
        long pages = 0;
        if (totalRow % queryDto.getSize() == 0) {
            pages = totalRow / queryDto.getSize();
        } else {
            pages = totalRow / queryDto.getSize() + 1;
        }
        planDesignInfoIPage.setPages(pages);
        logger.info("页数：{}",pages);
        return planDesignInfoIPage;

    }

    @Override
    public ResponseData upload(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String dir=System.getProperty("user.dir");
        dir=dir.substring(0,dir.lastIndexOf("\\"));

        Gson gson = new Gson();
        String realPath = dir + "/webapps/upload";
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart == true) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> items = null;
            try {
                items = upload.parseRequest((RequestContext) request);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            Iterator<FileItem> itr = items.iterator();
            while (itr.hasNext()) {
                FileItem item = (FileItem) itr.next();
                if (!item.isFormField()) {
                    File fullFile = new File(item.getName());
                    File savedFile = new File(realPath + "/", fullFile.getName());
                    try {
                        item.write(savedFile);
                        String url = "http://localhost:8080/upload/" + fullFile.getName();
                        String[] strs = {url};
                        logger.info("上传业务返回：");
                        Arrays.asList(strs).stream().forEach(System.out::println);
                        return ResponseData.ok(strs);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.out.print("the enctype must be multipart/form-data");
        }
        return null;
    }
}




