package com.deloitte.services.dss.finance.service;


import com.deloitte.services.dss.finance.entity.FinEtlPretreatment;

import java.util.List;
import java.util.Map;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-22
 * @Description : FinEtlExpenditure服务类接口
 * @Modified :
 */
public interface IFinEtlExpenditureService{

    /**
     * 查询 总支出预算所需数据
     * @param map
     * @return
     */
    List<FinEtlPretreatment> queryInfoExpBud(Map map);

    /**
     * 查询 总支出所需数据
     * @param map
     * @return
     */
    List<FinEtlPretreatment> queryInfoExp(Map map);

    /**
     * 查询一月后收入所需数据
     * @param map
     * @return
     */
    List<FinEtlPretreatment> queryInfoForExpEt(Map map);

    /**
     * 查询预算达成率所需数据 分母
     * @param map
     * @return
     */
    List<FinEtlPretreatment> queryInfoForExeExp(Map map);

    /**
     * 查询收入同比数据
     * @param finEtlPretreatment
     * @return
     */
    List<FinEtlPretreatment> queryInfoForExpRate(FinEtlPretreatment finEtlPretreatment);

    /**
     * 查询计划增长率数据
     * @param finEtlPretreatment
     * @return
     */
    List<FinEtlPretreatment> queryExpGrowth(FinEtlPretreatment finEtlPretreatment);

}
