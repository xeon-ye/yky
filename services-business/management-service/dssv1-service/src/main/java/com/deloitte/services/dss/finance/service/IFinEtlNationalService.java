package com.deloitte.services.dss.finance.service;


import com.deloitte.services.dss.finance.entity.FinEtlPretreatment;

import java.util.List;
import java.util.Map;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-27
 * @Description : DssFinEtlNational服务类接口
 * @Modified :
 */
public interface IFinEtlNationalService {

    /**
     * 查询项目实际支出数据
     * @param map
     * @return
     */
    List<FinEtlPretreatment> queryInfoForNational(Map map);

    /**
     * 项目支出预算执行率（分子）
     * @param map
     * @return
     */
    List<FinEtlPretreatment> queryInfoForNationalExeN(Map map);

    /**
     * 项目支出预算执行率（分母）
     * @param map
     * @return
     */
    List<FinEtlPretreatment> queryInfoForNationalExeD(Map map);
}
