package com.deloitte.services.dss.finance.service;


import com.deloitte.platform.api.dss.finance.vo.FinExecutionVo;

import java.util.Map;

/**
 * <p>
 * 预算执行率 Mapper 接口
 * </p>
 *
 * @author chitose
 * @since 2019-04-17
 */
public interface IFinExecutionService {

    /**
     * 查询 计算总体收入预算执行率
     * @param map
     * @return
     */
    FinExecutionVo queryTotalExe(Map map);
}
