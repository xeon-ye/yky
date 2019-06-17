package com.deloitte.services.fssc.budget.mapper;

import com.deloitte.platform.api.fssc.budget.vo.BudgetProjectBudgetVo;
import com.deloitte.services.fssc.budget.entity.BudgetProjectBudget;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jaws
 * @since 2019-03-20
 */
public interface BudgetProjectBudgetMapper extends BaseMapper<BudgetProjectBudget> {

    /**
     * 根据任务编码查询各个科目的汇总信息
     * @param taskCode
     * @param budgetAnnual
     * @return
     */
    BudgetProjectBudget getSummaryByTaskCode(@Param("projectCode") String projectCode,
                                             @Param("taskCode") String taskCode,
                                             @Param("budgetAnnual") String budgetAnnual);

    List<BudgetProjectBudgetVo> getSummaryAccountByTaskCode(@Param("projectCode") String projectCode,
                                                            @Param("taskCode") String taskCode,
                                                            @Param("budgetAnnual") String budgetAnnual);

    List<BudgetProjectBudgetVo> listVo(@Param("unitCode") String unitCode,@Param("budgetProjectId") Long budgetProjectId);


}
