package com.ffl.ahydboot.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 主备路由光缆清单表
 * @TableName t_plan_design_route_cable
 */
@TableName(value ="t_plan_design_route_cable")
@Data
public class PlanDesignRouteCable implements Serializable {
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
     * 光缆段
     */
    @TableField(value = "cable_seg_name")
    private String cableSegName;

    /**
     * A端站点
     */
    @TableField(value = "station_a")
    private String stationA;

    /**
     * Z端站点
     */
    @TableField(value = "station_z")
    private String stationZ;

    /**
     * 光纤信息
     */
    @TableField(value = "cable_name")
    private String cableName;

    /**
     * 主备标识
     */
    @TableField(value = "is_main_backup")
    private Integer isMainBackup;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}