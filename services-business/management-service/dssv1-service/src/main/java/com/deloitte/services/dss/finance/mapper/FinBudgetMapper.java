package com.deloitte.services.dss.finance.mapper;

import com.deloitte.platform.api.dss.finance.vo.IncomeBudgetVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 收入预算 Mapper 接口
 * </p>
 *
 * @author chitose
 * @since 2019-04-09
 */
public interface FinBudgetMapper {

    /**
     * 查询年预算
     * @return
     */
    List<IncomeBudgetVo> selectBudget(Map map);

    /**
     * 查询机构年预算
     * @param map
     * @return
     */
    List<IncomeBudgetVo> selectComBudget(Map map);

    /**
     * 查询全年支出预算
     * @param incomeBudgetVo
     * @return
     */
    List<IncomeBudgetVo> selectExpBudget(IncomeBudgetVo incomeBudgetVo);

    /**
     * 查询部门全年支出预算
     * @param map
     * @return
     */
    List<IncomeBudgetVo> selectComExpBudget(Map map);

    /**
     * 查询收入预算指标
     * @return
     */
    List<IncomeBudgetVo> selectIncomeIndexCode();

    /**
     * 查询支出预算指标
     * @return
     */
    List<IncomeBudgetVo> selectExpIndexCode();

}
