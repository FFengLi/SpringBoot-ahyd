package com.ffl.ahydboot.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 规划设计评估主表
 * @TableName t_plan_design_info
 */
@TableName(value ="t_plan_design_info")
@Data
public class PlanDesignInfo implements Serializable {
    /**
     * 编号
     */
    @TableId(value = "Id")
    private Long id;

    /**
     * 规划工单编号
     */
    @TableField(value = "plan_bill_no")
    private String planBillNo;

    /**
     * 规划设计名称
     */
    @TableField(value = "plan_design_name")
    private String planDesignName;

    /**
     * 设计单位
     */
    @TableField(value = "design_company")
    private String designCompany;

    /**
     * 类名 业务类型，枚举：1、 OTN业务
     */
    @TableField(value = "spec_id")
    private Integer specId;

    /**
     * 项目总负责人
     */
    @TableField(value = "project_director")
    private String projectDirector;

    /**
     * 专业负责人
     */
    @TableField(value = "spec_leader")
    private String specLeader;

    /**
     * 设计人
     */
    @TableField(value = "designer")
    private String designer;

    /**
     * 校审人 当前用户
     */
    @TableField(value = "reviewer")
    private String reviewer;

    /**
     * 工单来源 枚举：1自建（默认） 2外部工单
     */
    @TableField(value = "source")
    private Integer source;

    /**
     * 系统规划CAD图纸附件Id
     */
    @TableField(value = "system_cad_file_id")
    private Integer systemCadFileId;

    /**
     * 系统规划CAD文件名
     */
    @TableField(value = "system_cad_file_name")
    private String systemCadFileName;

    /**
     * 系统规划CAD文件URL
     */
    @TableField(value = "system_cad_file_url")
    private String systemCadFileUrl;

    /**
     * 系统规划Excel文件附件Id
     */
    @TableField(value = "system_excel_file_id")
    private Integer systemExcelFileId;

    /**
     * 系统规划Excel文件附件名
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
     * 波道规划Excel文件附件名
     */
    @TableField(value = "channel_excel_file_name")
    private String channelExcelFileName;

    /**
     * 波道规划Excel文件附件URL
     */
    @TableField(value = "channel_excel_file_url")
    private String channelExcelFileUrl;

    /**
     * CAD图纸坐标(左)
     */
    @TableField(value = "cad_coord_left")
    private Integer cadCoordLeft;

    /**
     * CAD图纸坐标(上)
     */
    @TableField(value = "cad_coord_top")
    private Integer cadCoordTop;

    /**
     * CAD图纸坐标(右)
     */
    @TableField(value = "cad_coord_right")
    private Integer cadCoordRight;

    /**
     * CAD图纸坐标(下)
     */
    @TableField(value = "cad_coord_bottom")
    private Integer cadCoordBottom;

    /**
     * 工单创建人id
     */
    @TableField(value = "staff_id")
    private Integer staffId;

    /**
     * 工单创建人名称
     */
    @TableField(value = "staff_name")
    private String staffName;

    /**
     * 设计新建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}