package com.ffl.ahydboot.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 业务同路由分析表
 * @TableName t_plan_design_business_route
 */
@TableName(value ="t_plan_design_business_route")
@Data
public class PlanDesignBusinessRoute implements Serializable {
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
     * 环信息
     */
    @TableField(value = "circle_name")
    private String circleName;

    /**
     * 
     */
    @TableField(value = "business_type")
    private String businessType;

    /**
     * 业务波道
     */
    @TableField(value = "business_channel")
    private String businessChannel;

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
     * 电路性质：政企，其他
     */
    @TableField(value = "business_character")
    private String businessCharacter;

    /**
     * 主路由
     */
    @TableField(value = "main_route")
    private String mainRoute;

    /**
     * 备路由
     */
    @TableField(value = "backup_route")
    private String backupRoute;

    /**
     * 同路由详情
     */
    @TableField(value = "same_route_detail")
    private String sameRouteDetail;

    /**
     * 同站点信息
     */
    @TableField(value = "same_station_name")
    private String sameStationName;

    /**
     * 是否同路由
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