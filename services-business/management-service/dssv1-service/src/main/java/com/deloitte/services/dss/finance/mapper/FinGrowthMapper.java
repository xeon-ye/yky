package com.deloitte.services.dss.finance.mapper;


import com.deloitte.platform.api.dss.finance.vo.FinancePlannedGrowthVo;
import com.deloitte.platform.api.dss.finance.vo.GrowthVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 整体收入计划增长 Mapper 接口
 * </p>
 *
 * @author chitose
 * @since 2019-04-10
 */
public interface FinGrowthMapper{

    /**
     * 查询 收入整体计划增长
     * @param map
     * @return
     */
    List<GrowthVo> quesryGrowth(Map map);

    /**
     * 查询 收入整体计划增长
     * @param map
     * @return
     */
    List<GrowthVo> quesryGrowthPerMonth(Map map);

    /**
     * 查询 收入整体计划增长按月
     * @param map
     * @return
     */
    List<GrowthVo> quesryGrowthMonth(Map map);

    /**
     * 查询 机构收入计划增长
     * @param map
     * @return
     */
    List<GrowthVo> quesryComGrowth(Map map);

    /**
     * 查询 支出整体计划增长
     * @param map
     * @return
     */
    List<GrowthVo> quesryExpGrowth(Map map);

    /**
     * 查询 机构支出计划增长
     * @param growthVo
     * @return
     */
    List<GrowthVo> quesryExpComGrowth(GrowthVo growthVo);

}
