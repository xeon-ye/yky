package com.deloitte.platform.api.fssc.budget.client;

import com.deloitte.platform.api.fssc.budget.vo.BudgetProjectBudgetForm;
import com.deloitte.platform.api.fssc.budget.vo.ProjectBudgetSummaryVo;
import com.deloitte.platform.api.fssc.performance.param.PerformanceIndexQueryForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformanceIndexForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformanceIndexVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-08
 * @Description :  BudgetProjectBudget控制器接口
 * @Modified :
 */
public interface BudgetProjectBudgetClient {

    String path="/budget/projectBudget";

    @GetMapping(value = path + "/getProjectBudgetSummary")
    Result<ProjectBudgetSummaryVo> getProjectBudgetSummary(@RequestParam(value = "projectCode",required = true) String projectCode,
                                                           @RequestParam(value = "taskCode",required = false) String taskCode,
                                                           @RequestParam(value = "budgetAnnual",required = false) String budgetAnnual);

}
