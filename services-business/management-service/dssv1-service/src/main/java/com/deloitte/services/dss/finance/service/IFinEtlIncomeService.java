package com.deloitte.services.dss.finance.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.services.dss.finance.entity.FinEtlPretreatment;

import java.util.List;
import java.util.Map;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-17
 * @Description : FinEtlIncome服务类接口
 * @Modified :
 */
public interface IFinEtlIncomeService extends IService {

    /**
     * 查询指标编号
     * @param map
     * @return
     */
    String queryIndexCode(Map map);

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
     * 查询收入同比增长率所需数据
     * @param finEtlPretreatment
     * @return
     */
    List<FinEtlPretreatment> queryInfoForRate(FinEtlPretreatment finEtlPretreatment);

    /**
     * 查询计划增长率数据
     * @param finEtlPretreatment
     * @return
     */
    List<FinEtlPretreatment> queryGrowth(FinEtlPretreatment finEtlPretreatment);
}
