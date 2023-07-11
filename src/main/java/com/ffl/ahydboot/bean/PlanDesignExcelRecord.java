package com.ffl.ahydboot.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * Excel文件解析清单表
 * @TableName t_plan_design_excel_record
 */
@TableName(value ="t_plan_design_excel_record")
@Data
public class PlanDesignExcelRecord implements Serializable {
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
     * 系统名称
     */
    @TableField(value = "sys_name")
    private String sysName;

    /**
     * 光缆段名称
     */
    @TableField(value = "cable_seg_name")
    private String cableSegName;

    /**
     * A端站点
     */
    @TableField(value = "station_a")
    private String stationA;

    /**
     * A站点类型
     */
    @TableField(value = "station_type_a")
    private String stationTypeA;

    /**
     * Z端站点
     */
    @TableField(value = "station_z")
    private String stationZ;

    /**
     * Z站点类型
     */
    @TableField(value = "station_type_z")
    private String stationTypeZ;

    /**
     * 光纤信息
     */
    @TableField(value = "cable_name")
    private String cableName;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}