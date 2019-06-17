package com.deloitte.services.dss.finance.service;


import com.deloitte.services.dss.finance.entity.FinEtlPretreatment;

import java.util.List;
import java.util.Map;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-27
 * @Description : DssFinEtlAchievement服务类接口
 * @Modified :
 */
public interface IFinEtlAchievementService {

    /**
     * 整体项目预算绩效
     * @param finEtlAchievementFact
     * @return
     */
    List<FinEtlPretreatment> queryInfoForAchive(FinEtlPretreatment finEtlAchievementFact);

    /**
     * 各类型项目预算绩效
     * @param map
     * @return
     */
    List<FinEtlPretreatment> queryInfoForComAchive(Map map);

    /**
     * 查询 平均项目预算绩效同比增长
     * @param finEtlPretreatment
     * @return
     */
    List<FinEtlPretreatment> queryInfoForAvgAchive(FinEtlPretreatment finEtlPretreatment);

    /**
     * 查询 平均科研项目预算绩效
     * @param finEtlPretreatment
     * @return
     */
    List<FinEtlPretreatment> queryInfoForAvgSciAchive(FinEtlPretreatment finEtlPretreatment);
}
