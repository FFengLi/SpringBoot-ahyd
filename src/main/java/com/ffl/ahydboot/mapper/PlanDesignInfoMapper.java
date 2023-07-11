package com.ffl.ahydboot.mapper;

import com.ffl.ahydboot.bean.PlanDesignDto;
import com.ffl.ahydboot.bean.PlanDesignInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author fanFengLi
* @description 针对表【t_plan_design_info(规划设计评估主表)】的数据库操作Mapper
* @createDate 2023-07-10 16:45:49
* @Entity com.ffl.ahydboot.bean.PlanDesignInfo
*/
@Mapper
public interface PlanDesignInfoMapper extends BaseMapper<PlanDesignInfo> {

    List<String> getPlanBillNos();
    List<PlanDesignInfo> searchBill(PlanDesignDto planDesignDTO);
    int getPlanDesignCountByWhere(PlanDesignDto planDesignDTO);
}




