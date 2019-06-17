package com.deloitte.services.dss.finance.service;


import com.deloitte.services.dss.finance.entity.FinEtlAchievementFact;

import java.util.List;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-27
 * @Description : DssFinEtlAchievementFact服务类接口
 * @Modified :
 */
public interface IFinEtlAchievementFactService {

    /**
     * 整体绩效 插入绩效实际表
     * @param finEtlAchievementFact
     */
    void insertIntoAchiFact(FinEtlAchievementFact finEtlAchievementFact);

    /**
     * 清除重复数据
     * @param finEtlAchievementFacts
     */
    void deleteData(List<FinEtlAchievementFact> finEtlAchievementFacts);





}