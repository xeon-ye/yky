package com.deloitte.services.dss.finance.service;


import com.deloitte.services.dss.finance.entity.FinEtlPretreatment;

import java.util.List;
import java.util.Map;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-27
 * @Description : FinEtlProexp服务类接口
 * @Modified :
 */
public interface IFinEtlProexpService{

    /**
     * 查询项目支出数据
     * @param map
     * @return
     */
    List<FinEtlPretreatment> queryInfoForProExp(Map map);
}
