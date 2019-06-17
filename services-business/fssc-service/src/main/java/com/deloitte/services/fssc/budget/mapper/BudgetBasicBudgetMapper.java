package com.deloitte.services.fssc.budget.mapper;

import com.deloitte.services.fssc.budget.entity.BudgetBasicBudget;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jaws
 * @since 2019-03-20
 */
public interface BudgetBasicBudgetMapper extends BaseMapper<BudgetBasicBudget> {

    BudgetBasicBudget selectByUnitOffice(@Param("unitCode") String unitCode, @Param("officeCode") String officeCode,
                                         @Param("budgetAnnual") String budgetAnnual);
}
