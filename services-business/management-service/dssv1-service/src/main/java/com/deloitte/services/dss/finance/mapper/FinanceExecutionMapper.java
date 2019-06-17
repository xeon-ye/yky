package com.deloitte.services.dss.finance.mapper;

import com.deloitte.platform.api.dss.finance.vo.FinanceExecutionVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 整体预算执行率 Mapper 接口
 * </p>
 *
 * @author chitose
 * @since 2019-04-09
 */
public interface FinanceExecutionMapper{

    /**
     * 查询整体收入预算执行率
     * @return
     */
    List<FinanceExecutionVo> queryExecution(Map map);

    /**
     * 查询机构收入预算执行率
     * @return
     */
    List<FinanceExecutionVo> queryComExecution(Map map);

    /**
     * 查询整体支出预算执行率
     * @return
     */
    List<FinanceExecutionVo> queryExpExecution(Map map);

    /**
     * 查询机构支出预算执行率
     * @return
     */
    List<FinanceExecutionVo> queryExpComExecution(FinanceExecutionVo financeExecutionVo);

    /**
     * 查询各月收入预算执行率
     * @param map
     * @return
     */
    List<FinanceExecutionVo> selectExecutionMonth(Map map);

    /**
     * 查询各部门月总收入预算执行率
     * @param map
     * @return
     */
    List<FinanceExecutionVo> selectComExecutionMonth(Map map);
}
