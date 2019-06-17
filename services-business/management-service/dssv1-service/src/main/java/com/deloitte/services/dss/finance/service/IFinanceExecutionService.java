package com.deloitte.services.dss.finance.service;


import com.deloitte.platform.api.dss.finance.vo.*;

import java.util.List;
import java.util.Map;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-09
 * @Description : FinanceExecution服务类接口
 * @Modified :
 */
public interface IFinanceExecutionService {

    /**
     * 查询整体预算计划执行率
     * @return
     */
    List<FinanceExecutionVo> queryExecution(Map map);

    /**
     * 查询整体预算执行率
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
    List<List<FinanceExecutionVo>> queryExpComExecution(AcceptVo acceptVo);

    /**
     * 查询机构收入预算整体数据
     * @param map
     * @return
     */
    List<IncomeBudgetVo> selectComBudgetAll(Map map);

    /**
     * 查询机构支出预算整体数据
     * @param acceptVo
     * @return
     */
    List<IncomeBudgetVo> selectComExpBudgetAll(AcceptVo acceptVo);

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
