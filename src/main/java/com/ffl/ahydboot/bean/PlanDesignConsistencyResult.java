package com.ffl.ahydboot.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 系统规划一致性校验比对结果表
 * @TableName t_plan_design_consistency_result
 */
@TableName(value ="t_plan_design_consistency_result")
@Data
public class PlanDesignConsistencyResult implements Serializable {
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
     * 规划设计评估历史分析记录表id
     */
    @TableField(value = "plan_design_result_id")
    private Long planDesignResultId;

    /**
     * CAD图纸识别（数量）
     */
    @TableField(value = "analys_cad_num")
    private Integer analysCadNum;

    /**
     * Excel文件解析（数量）
     */
    @TableField(value = "analys_excel_num")
    private Integer analysExcelNum;

    /**
     * 分析结果 枚举，1一致，2不一致
     */
    @TableField(value = "analys_result")
    private String analysResult;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}