package com.deloitte.services.dss.finance.mapper;

import com.deloitte.platform.api.dss.finance.vo.BudgetVo;
import com.deloitte.platform.api.dss.finance.vo.FinanceBudgetVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 整体预算计划 Mapper 接口
 * </p>
 *
 * @author chitose
 * @since 2019-04-09
 */
public interface FinanceBudgetMapper {

    /**
     * 查询整体预算计划
     * @return
     */
    List<FinanceBudgetVo> queryTotalBudget(FinanceBudgetVo financeBudgetVo);

    /**
     * 查询部门预算计划
     * @param financeBudgetVo
     * @return 各部门某年的收入预算
     */
    List<FinanceBudgetVo> queryComBudget(FinanceBudgetVo financeBudgetVo);

    /**
     * 查询收入预算明细
     * @param map
     * @return
     */
    List<BudgetVo> queryIncomeBudget(Map map);

    /**
     * 查询支出预算明细
     * @param map
     * @return
     */
    List<BudgetVo> queryExpenditureBudget(Map map);

}
