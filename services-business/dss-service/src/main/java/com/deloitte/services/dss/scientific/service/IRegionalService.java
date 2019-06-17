package com.deloitte.services.dss.scientific.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.dss.scientific.vo.PersonnelColumnarVo;
import com.deloitte.platform.api.dss.scientific.vo.PersonnelVo;
import com.deloitte.platform.api.dss.scientific.vo.RegionalDataVo;

import java.util.List;
import java.util.Map;

/**
 * 科研人才数据统计
 */
public interface IRegionalService extends IService{

    /**
     * 本年相关金额数据
     * @return
     */
    Map queryCurYearData(Map map);

    /**
     * 查询柱状图数据
     * @param map
     * @return
     */
    List<RegionalDataVo>  queryColumnarData(Map map);

}
