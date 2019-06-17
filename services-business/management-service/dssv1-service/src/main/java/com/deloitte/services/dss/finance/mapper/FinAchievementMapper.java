package com.deloitte.services.dss.finance.mapper;


import com.deloitte.platform.api.dss.finance.vo.IncomeBudgetVo;
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
public interface FinAchievementMapper {



    /**
     * 平均项目预算绩效 科研项目预算绩效
     * @param map
     * @return
     */
    List<IncomeBudgetVo> selectAvgAchive(Map map);

    /**
     * 平均项目预算绩效 科研项目预算绩效 同比
     * @param map
     * @return
     */
    List<IncomeBudgetVo> selectAvgAchiveRate(Map map);

}
