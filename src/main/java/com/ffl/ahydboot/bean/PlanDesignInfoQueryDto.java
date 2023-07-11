package com.ffl.ahydboot.bean;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: fanFengLi
 * @Date 2023/7/11 09:48
 * @Description:
 */
@Data
public class PlanDesignInfoQueryDto extends Page {
    private String planDesignName;

    /**
     * 类名 业务类型，枚举：1、 OTN业务
     */
    private Integer specId;

    /**
     * 设计人
     */
    private String designer;

    /**
     * 设计创建时间
     */
    private List<String> createTime;

}
