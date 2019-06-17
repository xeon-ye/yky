package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.BudgetQueryForm;
import com.deloitte.platform.api.project.vo.BudgetForm;
import com.deloitte.platform.api.project.vo.BudgetVo;
import com.deloitte.platform.api.project.client.BudgetClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.project.service.IBudgetService;
import com.deloitte.services.project.entity.Budget;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-09
 * @Description :   Budget控制器实现类
 * @Modified :
 */
@Api(tags = "Budget操作接口")
@Slf4j
@RestController
public class BudgetController implements BudgetClient {

    @Autowired
    public IBudgetService  budgetService;


    @Override
    @ApiOperation(value = "新增Budget", notes = "新增一个Budget")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="budgetForm",value="新增Budget的form表单",required=true)  BudgetForm budgetForm) {
        log.info("form:",  budgetForm.toString());
        Budget budget =new BeanUtils<Budget>().copyObj(budgetForm,Budget.class);
        return Result.success( budgetService.save(budget));
    }


    @Override
    @ApiOperation(value = "删除Budget", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "BudgetID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        budgetService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改Budget", notes = "修改指定Budget信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "Budget的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="budgetForm",value="修改Budget的form表单",required=true)  BudgetForm budgetForm) {
        Budget budget =new BeanUtils<Budget>().copyObj(budgetForm,Budget.class);
        budget.setId(id);
        budgetService.saveOrUpdate(budget);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "测试测试测试", notes = "获取指定ID的Budget信息")
    @ApiImplicitParam(paramType = "path", name = "applicationId", value = "Budget的ID", required = true, dataType = "long")
    public Result get(@PathVariable String applicationId) {
        List list = budgetService.getAppYearCount(applicationId);
        return new Result<>().sucess(list);
    }

    @Override
    @ApiOperation(value = "列表查询Budget", notes = "根据条件查询Budget列表数据")
    public Result<List<BudgetVo>> list(@Valid @RequestBody @ApiParam(name="budgetQueryForm",value="Budget查询参数",required=true) BudgetQueryForm budgetQueryForm) {
        log.info("search with budgetQueryForm:", budgetQueryForm.toString());
        List<Budget> budgetList=budgetService.selectList(budgetQueryForm);
        List<BudgetVo> budgetVoList=new BeanUtils<BudgetVo>().copyObjs(budgetList,BudgetVo.class);
        return new Result<List<BudgetVo>>().sucess(budgetVoList);
    }


    @Override
    @ApiOperation(value = "分页查询Budget", notes = "根据条件查询Budget分页数据")
    public Result<IPage<BudgetVo>> search(@Valid @RequestBody @ApiParam(name="budgetQueryForm",value="Budget查询参数",required=true) BudgetQueryForm budgetQueryForm) {
        log.info("search with budgetQueryForm:", budgetQueryForm.toString());
        IPage<Budget> budgetPage=budgetService.selectPage(budgetQueryForm);
        IPage<BudgetVo> budgetVoPage=new BeanUtils<BudgetVo>().copyPageObjs(budgetPage,BudgetVo.class);
        return new Result<IPage<BudgetVo>>().sucess(budgetVoPage);
    }

}



