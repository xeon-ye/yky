package com.deloitte.services.dss.finance.mapper;


import com.deloitte.services.dss.finance.entity.FinEtlPretreatment;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 一般公共预算支出表 Mapper 接口
 * </p>
 *
 * @author chitose
 * @since 2019-04-22
 */
public interface FinEtlExpenditureMapper{

    /**
     * 查询 支出预算 所需数据
     * @param map
     * @return
     */
    List<FinEtlPretreatment> queryInfoExpBud(Map map);

    /**
     * 查询 支出 所需数据
     * @param map
     * @return
     */
    List<FinEtlPretreatment> queryInfoExp(Map map);

    /**
     * 查询一月后支出所需数据
     * @param map
     * @return
     */
    List<FinEtlPretreatment> queryInfoForExpEt(Map map);

    /**
     * 查询预算支出达成率所需数据
     * @param map
     * @return
     */
    List<FinEtlPretreatment> queryInfoForExeExp(Map map);

    /**
     * 查询收入同比数据
     * @param map
     * @return
     */
    List<FinEtlPretreatment> queryInfoForExpRate(Map map);

    /**
     * 查询计划增长率数据
     * @param map
     * @return
     */
    List<FinEtlPretreatment> queryExpGrowth(Map map);

}
