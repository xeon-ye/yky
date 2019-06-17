package com.deloitte.services.dss.scientific.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.dss.scientific.vo.PersonnelColumnarVo;
import com.deloitte.platform.api.dss.scientific.vo.PersonnelVo;
import com.deloitte.platform.api.fssc.base.param.BaseIncomeSubTypeQueryForm;

import java.util.List;
import java.util.Map;

/**
 * 科研人才数据统计
 */
public interface IPersonnelService extends IService{

    /**
     * 职级分组获取各个职级总人数
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
