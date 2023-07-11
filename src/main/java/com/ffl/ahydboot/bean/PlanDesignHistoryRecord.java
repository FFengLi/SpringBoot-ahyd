package com.ffl.ahydboot.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 规划设计评估历史分析记录表
 * @TableName t_plan_design_history_record
 */
@TableName(value ="t_plan_design_history_record")
@Data
public class PlanDesignHistoryRecord implements Serializable {
    /**
     * 编号
     */
    @TableId(value = "Id")
    private Long id;

    /**
     * 规划设计工单主表id
     */
    @TableField(value = "plan_design_id")
    private Long planDesignId;

    /**
     * 分析编号
     */
    @TableField(value = "analyse_no")
    private String analyseNo;

    /**
     * 分析状态1、正在解析  2、解析完成
     */
    @TableField(value = "analyse_status")
    private Integer analyseStatus;

    /**
     * 分析开始时间（调分析接口开始时间）
     */
    @TableField(value = "analyse_begin_time")
    private Date analyseBeginTime;

    /**
     * 分析结束时间（调分析接口结束时间）
     */
    @TableField(value = "analyse_end_time")
    private Date analyseEndTime;

    /**
     * 分析时长（秒） 单位：秒
     */
    @TableField(value = "analyse_time")
    private Integer analyseTime;

    /**
     * 系统规划CAD图纸附件Id
     */
    @TableField(value = "system_cad_file_id")
    private Integer systemCadFileId;

    /**
     * 系统规划CAD图纸附件名称
     */
    @TableField(value = "system_cad_file_name")
    private String systemCadFileName;

    /**
     * 系统规划CAD图纸附件URL
     */
    @TableField(value = "system_cad_file_url")
    private String systemCadFileUrl;

    /**
     * 系统规划Excel文件附件Id
     */
    @TableField(value = "system_excel_file_id")
    private Integer systemExcelFileId;

    /**
     * 系统规划Excel文件附件名称
     */
    @TableField(value = "system_excel_file_name")
    private String systemExcelFileName;

    /**
     * 系统规划Excel文件附件URL 
     */
    @TableField(value = "system_excel_file_url")
    private String systemExcelFileUrl;

    /**
     * 波道规划Excel文件附件Id
     */
    @TableField(value = "channel_excel_file_id")
    private Integer channelExcelFileId;

    /**
     * 波道规划Excel文件附件名称
     */
    @TableField(value = "channel_excel_file_name")
    private String channelExcelFileName;

    /**
     * 波道规划Excel文件附件URL
     */
    @TableField(value = "channel_excel_file_url")
    private String channelExcelFileUrl;

    /**
     * 分析日志
     */
    @TableField(value = "analyse_log")
    private String analyseLog;

    /**
     * 分析人id
     */
    @TableField(value = "analyse_staff_id")
    private Long analyseStaffId;

    /**
     * 分析人名称
     */
    @TableField(value = "analyse_staff_name")
    private String analyseStaffName;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}