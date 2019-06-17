package com.deloitte.services.dss.finance.mapper;

import com.deloitte.services.dss.finance.entity.FinEtlPretreatment;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 部门收支总表 Mapper 接口
 * </p>
 *
 * @author chitose
 * @since 2019-04-17
 */
public interface FinEtlIncomeMapper {

    /**
     * 查询收入所需数据
     * @param map
     * @return
     */
    List<FinEtlPretreatment> queryInfoForIncome(Map map);

    /**
     * 查询一月后收入所需数据
     * @param map
     * @return
     */
    List<FinEtlPretreatment> queryInfoForIncomeEt(Map map);


    /**
     * 查询预算所需数据
     * @return
     */
    List<FinEtlPretreatment> queryInfoForIncomeBud(Map map);

    /**
     * 查询预算达成率所需数据
     * @param map
     * @return
     */
    List<FinEtlPretreatment> queryInfoForExe(Map map);

    /**
     * 查询指标编号
     * @param map
     * @return
     */
    String queryIndexCode(Map map);

    /**
     * 查询机构编码
     * @param map
     * @return
     */
    Integer queryComCode(Map map);

    /**
     * 查询收入同比数据
     * @param map
     * @return
     */
    List<FinEtlPretreatment> queryInfoForRate(Map map);

    /**
     * 查询计划增长率数据
     * @param map
     * @return
     */
    List<FinEtlPretreatment> queryGrowth(Map map);
}
