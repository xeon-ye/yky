package com.deloitte.services.dss.finance.mapper;


import com.deloitte.services.dss.finance.entity.FinEtlPretreatment;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 国库系统（实际项目支出） Mapper 接口
 * </p>
 *
 * @author chitose
 * @since 2019-04-27
 */
public interface FinEtlNationalMapper{

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
