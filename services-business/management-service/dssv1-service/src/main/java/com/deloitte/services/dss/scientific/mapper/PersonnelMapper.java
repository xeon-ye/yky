package com.deloitte.services.dss.scientific.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deloitte.platform.api.dss.scientific.vo.PersonnelColumnarVo;
import com.deloitte.platform.api.dss.scientific.vo.PersonnelVo;

import java.util.List;
import java.util.Map;

/**
 * 科研人才 mapper 接口
 */
public interface PersonnelMapper{

    List<Map> queryPersonNum();

    /**
     * 各职级本年度45岁占比
     * @param map
     * @return
     */
    List<PersonnelVo> queryAgeProp (Map map);

    /**
     * 柱状图数据
     * @param map
     * @return
     */
    List<PersonnelColumnarVo> queryColumnarData(Map map);
}
