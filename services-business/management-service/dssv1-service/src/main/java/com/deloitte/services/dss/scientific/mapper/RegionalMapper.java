package com.deloitte.services.dss.scientific.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deloitte.platform.api.dss.scientific.vo.*;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 科研总体地区分布 mapper 接口
 */
public interface RegionalMapper {

    /**
     * 查询总的科研项目的个数
     * @param map
     * @return
     */
    RegionalTotalVo queryTotalNum(Map map);


    /**
     * 新申请项目个数
     * @param map
     * @return
     */
    Long queryApplayNum(Map map);


    /**
     * 总到位经费
     * @param map
     * @return
     */
    Double queryReceviceOutlay(Map map);


    /**
     * 任务地区项目数量-
     * @param map
     * @return
     */
    List<RegionalDataVo>  queryRegionProNum (Map map);

    /**
     * 任务地区项目金额-
     * @param map
     * @return
     */
    List<RegionalDataVo>  queryRegionProMoney (Map map);


    /**
     * 具体地区 项目到位经费、项目数量、任务数量
     * @param map
     * @return
     */
    List<RegionalDataVo>  queryRegionData (Map map);




    /**
     * 创新工程延续项目与新获项目-  项目规模模块
     * @param map
     * @return
     */
    RegionalProScaleVo queryScaleData (Map map);


    /**
     * 年龄分布 高级占比
     * @param map
     * @return
     */
    RegionalAgeVo queryAgeData (Map map);


    /**
     * 柱状图到位经费
     * @param map
     * @return
     */
    List<RegionalDataVo>  queryColumnarData (Map map);
}
