package com.deloitte.services.fssc.budget.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.budget.param.BudgetBasicBudgetQueryForm;
import com.deloitte.platform.api.fssc.budget.vo.BudgetBasicBudgetForm;
import com.deloitte.platform.api.fssc.budget.vo.BudgetBasicBudgetVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.base.entity.BaseBudgetAccount;
import com.deloitte.services.fssc.budget.entity.BudgetBasicBudget;
import com.deloitte.services.fssc.budget.service.IBudgetBasicBudgetService;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.handler.FSSCException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.HttpMethod;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author : jaws
 * @Date : Create in 2019-03-20
 * @Description :   基本预算 控制器实现类
 * @Modified :
 */
@Api(tags = "基本预算 操作接口")
@Slf4j
@RestController
@RequestMapping(value = "/budget/basicBudget")
public class BudgetBasicBudgetController {

    @Autowired
    public IBudgetBasicBudgetService basicBudgetService;


    @ApiOperation(value = "新增/修改 基本预算", notes = "新增/修改一个基本预算",httpMethod = "POST")
    @ApiParam(name = "basicBudgetForm", value = "新增/修改基本预算的form表单", required = true)
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @PostMapping(value = "addOrUpdateBasicBudget")
    public Result addOrUpdateBasicBudget(@Valid @RequestBody BudgetBasicBudgetForm basicBudgetForm) throws Exception{
        log.info("form:", basicBudgetForm.toString());
        BudgetBasicBudget basicBudget = basicBudgetService.selectByKeyWord(basicBudgetForm.getOrgUnitCode(),
                basicBudgetForm.getLevel2OfficeCode(),basicBudgetForm.getBudgetAccountCode(),
                basicBudgetForm.getBudgetAnnual());
            if (basicBudget != null) {
                //修改
                BigDecimal newBudgetAmount = basicBudget.getBudgetAmount().add(basicBudgetForm.getBudgetAmount());
                BigDecimal newRemainAndOccupyAmount = basicBudget.getBudgetRemainAmount().add(basicBudget.getBudgetOccupyAmount());
                basicBudget.setBudgetAmount(newBudgetAmount);
                BigDecimal newUsableAmount = newBudgetAmount.subtract(newRemainAndOccupyAmount);
                basicBudget.setBudgetUsableAmount(newUsableAmount);
                return Result.success(basicBudgetService.updateById(basicBudget));
            } else {
                // 新增
                basicBudget = (BudgetBasicBudget)BeanUtils.copyProperties(basicBudgetForm,new BudgetBasicBudget());
                basicBudget.setBudgetRemainAmount(new BigDecimal(0));
                basicBudget.setBudgetOccupyAmount(new BigDecimal(0));
                basicBudget.setBudgetUsableAmount(basicBudget.getBudgetAmount());
                return Result.success(basicBudgetService.saveOrUpdate(basicBudget));
        }
    }

    @ApiOperation(value = "获取 基本预算", notes = "获取指定ID的基本预算信息",httpMethod = "POST")
    @ApiImplicitParam(paramType = "path", name = "id", value = "基本预算的ID", required = true, dataType = "long")
    @GetMapping("get/{id}")
    public Result<BudgetBasicBudgetVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        BudgetBasicBudget budgetBasicBudget = basicBudgetService.getById(id);
        BudgetBasicBudgetVo budgetBasicBudgetVo = new BeanUtils<BudgetBasicBudgetVo>()
                .copyObj(budgetBasicBudget, BudgetBasicBudgetVo.class);
        return new Result<BudgetBasicBudgetVo>().sucess(budgetBasicBudgetVo);
    }

    @ApiOperation(value = "列表查询基本预算", notes = "根据条件查询基本预算列表数据", httpMethod = "POST")
    @ApiParam(name = "basicBudgetQueryForm", value = "基本预算查询参数", required = true)
    @PostMapping("/list/conditions")
    public Result<List<BudgetBasicBudgetVo>> list(
            @Valid @RequestBody BudgetBasicBudgetQueryForm basicBudgetQueryForm) {
        log.info("search with basicBudgetQueryForm:", basicBudgetQueryForm.toString());
        List<BudgetBasicBudget> budgetBasicBudgetList = basicBudgetService
                .selectList(basicBudgetQueryForm);
        List<BudgetBasicBudgetVo> budgetBasicBudgetVoList = new BeanUtils<BudgetBasicBudgetVo>()
                .copyObjs(budgetBasicBudgetList, BudgetBasicBudgetVo.class);
        return new Result<List<BudgetBasicBudgetVo>>().sucess(budgetBasicBudgetVoList);
    }

    @ApiOperation(value = "分页查询基本预算", notes = "根据条件查询基本预算分页数据", httpMethod = "POST")
    @ApiParam(name = "budgetBasicBudgetQueryForm", value = "基本预算查询参数", required = true)
    @PostMapping("/page/conditions")
    public Result<IPage<BudgetBasicBudgetVo>> search(
            @Valid @RequestBody BudgetBasicBudgetQueryForm budgetBasicBudgetQueryForm) {
        log.info("search with budgetBasicBudgetQueryForm:", budgetBasicBudgetQueryForm.toString());
        IPage<BudgetBasicBudget> budgetBasicBudgetPage = basicBudgetService
                .selectPage(budgetBasicBudgetQueryForm);
        IPage<BudgetBasicBudgetVo> budgetBasicBudgetVoPage = new BeanUtils<BudgetBasicBudgetVo>()
                .copyPageObjs(budgetBasicBudgetPage, BudgetBasicBudgetVo.class);
        return new Result<IPage<BudgetBasicBudgetVo>>().sucess(budgetBasicBudgetVoPage);
    }

}



