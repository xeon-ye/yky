package com.deloitte.services.dss.finance.mapper;


import com.deloitte.services.dss.finance.entity.FinEtlPretreatment;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 绩效表 Mapper 接口
 * </p>
 *
 * @author chitose
 * @since 2019-04-27
 */
public interface FinEtlAchievementMapper {

    /**
     * 查询 整体项目预算绩效
     * @param finEtlPretreatment
     * @return
     */
    List<FinEtlPretreatment> queryInfoForAchive(FinEtlPretreatment finEtlPretreatment);

    /**
     * 查询 各类型项目预算绩效
     * @param map
     * @return
     */
    List<FinEtlPretreatment> queryInfoForComAchive(Map map);

    /**
     * 查询 平均项目预算绩效同比增长
     * @param map
     * @return
     */
    List<FinEtlPretreatment> queryInfoForAvgAchive(Map map);

    /**
     * 查询 平均科研项目预算绩效
     * @param map
     * @return
     */
    List<FinEtlPretreatment> queryInfoForAvgSciAchive(Map map);

}
