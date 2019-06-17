package com.deloitte.services.dss.finance.mapper;


import com.deloitte.services.dss.finance.entity.FinEtlAchievementFact;

/**
 * <p>
 * 绩效事实表 Mapper 接口
 * </p>
 *
 * @author chitose
 * @since 2019-04-27
 */
public interface FinEtlAchievementFactMapper{

    /**
     * 整体绩效 插入绩效实际表
     * @param finEtlAchievementFact
     */
    void insertIntoAchiFact(FinEtlAchievementFact finEtlAchievementFact);

    /**
     * 清除重复数据
     * @param finEtlAchievementFact
     */
    void deleteData(FinEtlAchievementFact finEtlAchievementFact);

}
