package com.ffl.ahydboot.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 物理同路由分析表
 * @TableName t_plan_design_physics_route
 */
@TableName(value ="t_plan_design_physics_route")
@Data
public class PlanDesignPhysicsRoute implements Serializable {
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
     * 主用路由
     */
    @TableField(value = "main_route")
    private String mainRoute;

    /**
     * 备用路由
     */
    @TableField(value = "backup_route")
    private String backupRoute;

    /**
     * 同路由详情
     */
    @TableField(value = "same_route_detail")
    private String sameRouteDetail;

    /**
     * 是否同路由 枚举，1是，2否
     */
    @TableField(value = "is_same_route")
    private Integer isSameRoute;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}