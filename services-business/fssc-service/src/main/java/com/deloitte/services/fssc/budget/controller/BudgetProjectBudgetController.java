package com.deloitte.services.fssc.budget.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.budget.client.BudgetProjectBudgetClient;
import com.deloitte.platform.api.fssc.budget.param.BudgetProjectBudgetQueryForm;
import com.deloitte.platform.api.fssc.budget.vo.BudgetProjectBudgetForm;
import com.deloitte.platform.api.fssc.budget.vo.BudgetProjectBudgetVo;
import com.deloitte.platform.api.fssc.budget.vo.ProjectBudgetSummaryVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.budget.entity.BudgetProjectBudget;
import com.deloitte.services.fssc.eums.FsscEums;
import io.swagger.annotations.*;
import java.math.BigDecimal;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.deloitte.services.fssc.budget.service.IBudgetProjectBudgetService;


/**
 * @Author : jaws
 * @Date : Create in 2019-03-20
 * @Description :   项目预算 控制器实现类
 * @Modified :
 */
@Api(tags = "项目预算 操作接口")
@Slf4j
@RestController
@RequestMapping(value = "/budget/projectBudget")
public class BudgetProjectBudgetController implements BudgetProjectBudgetClient{

    @Autowired
    public IBudgetProjectBudgetService projectBudgetService;

    @Override
    @ApiOperation(value = "获取项目任务预算汇总信息", notes = "获取项目任务预算汇总信息,包含每个科目的预算数据")
    @GetMapping(value = "/getProjectBudgetSummary")
    public Result<ProjectBudgetSummaryVo> getProjectBudgetSummary(String projectCode, String taskCode, String budgetAnnual) {
        ProjectBudgetSummaryVo summaryVo = new ProjectBudgetSummaryVo();
        if (StringUtils.isBlank(taskCode)){
            taskCode = projectCode;
        }
        BudgetProjectBudget taskBudget = projectBudgetService.getSummaryByTask(projectCode,taskCode,budgetAnnual);
        if (taskBudget == null){
            return Result.success(summaryVo);
        }
        BudgetProjectBudgetVo taskBudgetVo = new BeanUtils<BudgetProjectBudgetVo>().copyObj(taskBudget,BudgetProjectBudgetVo.class);
        BigDecimal budgetUsedAmount = taskBudgetVo.getBudgetRemainAmount().add(taskBudgetVo.getBudgetOccupyAmount());
        taskBudgetVo.setBudgetUsedAmount(budgetUsedAmount);
        summaryVo.setTaskVo(taskBudgetVo);
        List<BudgetProjectBudgetVo> accountBudgetVoList = projectBudgetService.getSummaryAccountByTaskCode(projectCode,taskCode,budgetAnnual);
        if(CollectionUtils.isNotEmpty(accountBudgetVoList)){
            for (BudgetProjectBudgetVo accountBudgetVo : accountBudgetVoList){
                BigDecimal accountBudgetUsedAmount = accountBudgetVo.getBudgetRemainAmount().add(accountBudgetVo.getBudgetOccupyAmount());
                accountBudgetVo.setBudgetUsedAmount(accountBudgetUsedAmount);
            }
        }
        summaryVo.setAccountVoList(accountBudgetVoList);
        return Result.success(summaryVo);
    }

    @ApiOperation(value = "新增/修改 项目预算", notes = "新增/修改一个项目预算",httpMethod = "POST")
    @ApiParam(name = "projectBudgetForm", value = "新增/修改项目预算的form表单", required = true)
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @PostMapping(value = "addOrUpdateProjectBudget")
    public Result addOrUpdateProjectBudget(@Valid @RequestBody BudgetProjectBudgetForm projectBudgetForm) throws Exception{
        log.info("form:", projectBudgetForm.toString());
        BudgetProjectBudget projectBudget = projectBudgetService.selectByKeyWord(projectBudgetForm.getOrgUnitCode(),
                projectBudgetForm.getBudgetProjectId(),projectBudgetForm.getBudgetAccountCode(),
                projectBudgetForm.getBudgetAnnual());
        if (projectBudget != null) {
            //修改
            BigDecimal newBudgetAmount = projectBudget.getBudgetAmount().add(projectBudgetForm.getBudgetAmount());
            BigDecimal newRemainAndOccupyAmount = projectBudget.getBudgetRemainAmount().add(projectBudget.getBudgetOccupyAmount());
            projectBudget.setBudgetAmount(newBudgetAmount);
            BigDecimal newUsableAmount = newBudgetAmount.subtract(newRemainAndOccupyAmount);
            projectBudget.setBudgetUsableAmount(newUsableAmount);
            return Result.success(projectBudgetService.updateById(projectBudget));
        } else {
            // 新增
            projectBudget = (BudgetProjectBudget) BeanUtils.copyProperties(projectBudgetForm,new BudgetProjectBudget());
            projectBudget.setBudgetRemainAmount(BigDecimal.ZERO);
            projectBudget.setBudgetOccupyAmount(BigDecimal.ZERO);
            projectBudget.setBudgetUsableAmount(projectBudget.getBudgetAmount());
            return Result.success(projectBudgetService.saveOrUpdate(projectBudget));
        }
    }

    @ApiOperation(value = "获取 项目预算", notes = "获取指定ID的项目预算信息",httpMethod = "POST")
    @ApiImplicitParam(paramType = "path", name = "id", value = "项目预算的ID", required = true, dataType = "long")
    @GetMapping("get/{id}")
    public Result<BudgetProjectBudgetVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        BudgetProjectBudget budgetProjectBudget = projectBudgetService.getById(id);
        BudgetProjectBudgetVo budgetProjectBudgetVo = new BeanUtils<BudgetProjectBudgetVo>()
                .copyObj(budgetProjectBudget, BudgetProjectBudgetVo.class);
        return new Result<BudgetProjectBudgetVo>().sucess(budgetProjectBudgetVo);
    }

    @ApiOperation(value = "列表查询项目预算", notes = "根据条件查询项目预算列表数据", httpMethod = "POST")
    @ApiParam(name = "projectBudgetQueryForm", value = "项目预算查询参数", required = true)
    @PostMapping("/list/conditions")
    public Result<List<BudgetProjectBudgetVo>> list(
            @Valid @RequestBody BudgetProjectBudgetQueryForm projectBudgetQueryForm) {
        log.info("search with projectBudgetQueryForm:", projectBudgetQueryForm.toString());
        List<BudgetProjectBudget> budgetProjectBudgetList = projectBudgetService
                .selectList(projectBudgetQueryForm);
        List<BudgetProjectBudgetVo> budgetProjectBudgetVoList = new BeanUtils<BudgetProjectBudgetVo>()
                .copyObjs(budgetProjectBudgetList, BudgetProjectBudgetVo.class);
        return new Result<List<BudgetProjectBudgetVo>>().sucess(budgetProjectBudgetVoList);
    }

    @ApiOperation(value = "分页查询项目预算", notes = "根据条件查询项目预算分页数据", httpMethod = "POST")
    @ApiParam(name = "budgetProjectBudgetQueryForm", value = "项目预算查询参数", required = true)
    @PostMapping("/page/conditions")
    public Result<IPage<BudgetProjectBudgetVo>> search(
            @Valid @RequestBody BudgetProjectBudgetQueryForm budgetProjectBudgetQueryForm) {
        log.info("search with budgetProjectBudgetQueryForm:", budgetProjectBudgetQueryForm.toString());
        IPage<BudgetProjectBudget> budgetProjectBudgetPage = projectBudgetService
                .selectPage(budgetProjectBudgetQueryForm);
        IPage<BudgetProjectBudgetVo> budgetProjectBudgetVoPage = new BeanUtils<BudgetProjectBudgetVo>()
                .copyPageObjs(budgetProjectBudgetPage, BudgetProjectBudgetVo.class);
        return new Result<IPage<BudgetProjectBudgetVo>>().sucess(budgetProjectBudgetVoPage);
    }

}



