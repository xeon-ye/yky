package com.deloitte.services.dss.finance.mapper;

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
public interface FinExecutionMapper {

    /**
     * 查询 全年预算达成率数据
     * @param map
     * @return
     */
   FinExecutionVo queryTotalExe(Map map);

}
