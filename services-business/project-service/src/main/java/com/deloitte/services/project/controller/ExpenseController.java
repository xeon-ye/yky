package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.ExpenseQueryForm;
import com.deloitte.platform.api.project.vo.ExpenseForm;
import com.deloitte.platform.api.project.vo.ExpenseVo;
import com.deloitte.platform.api.project.client.ExpenseClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.project.service.IExpenseService;
import com.deloitte.services.project.entity.Expense;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :   Expense控制器实现类
 * @Modified :
 */
@Api(tags = "Expense操作接口")
@Slf4j
@RestController
public class ExpenseController implements ExpenseClient {

    @Autowired
    public IExpenseService  expenseService;


    @Override
    @ApiOperation(value = "新增Expense", notes = "新增一个Expense")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="expenseForm",value="新增Expense的form表单",required=true)  ExpenseForm expenseForm) {
        log.info("form:",  expenseForm.toString());
        Expense expense =new BeanUtils<Expense>().copyObj(expenseForm,Expense.class);
        return Result.success( expenseService.save(expense));
    }


    @Override
    @ApiOperation(value = "删除Expense", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ExpenseID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        expenseService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改Expense", notes = "修改指定Expense信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "Expense的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="expenseForm",value="修改Expense的form表单",required=true)  ExpenseForm expenseForm) {
        Expense expense =new BeanUtils<Expense>().copyObj(expenseForm,Expense.class);
        expense.setId(id);
        expenseService.saveOrUpdate(expense);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取Expense", notes = "获取指定ID的Expense信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Expense的ID", required = true, dataType = "long")
    public Result<ExpenseVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        Expense expense=expenseService.getById(id);
        ExpenseVo expenseVo=new BeanUtils<ExpenseVo>().copyObj(expense,ExpenseVo.class);
        return new Result<ExpenseVo>().sucess(expenseVo);
    }

    @Override
    @ApiOperation(value = "列表查询Expense", notes = "根据条件查询Expense列表数据")
    public Result<List<ExpenseVo>> list(@Valid @RequestBody @ApiParam(name="expenseQueryForm",value="Expense查询参数",required=true) ExpenseQueryForm expenseQueryForm) {
        log.info("search with expenseQueryForm:", expenseQueryForm.toString());
        List<Expense> expenseList=expenseService.selectList(expenseQueryForm);
        List<ExpenseVo> expenseVoList=new BeanUtils<ExpenseVo>().copyObjs(expenseList,ExpenseVo.class);
        return new Result<List<ExpenseVo>>().sucess(expenseVoList);
    }


    @Override
    @ApiOperation(value = "分页查询Expense", notes = "根据条件查询Expense分页数据")
    public Result<IPage<ExpenseVo>> search(@Valid @RequestBody @ApiParam(name="expenseQueryForm",value="Expense查询参数",required=true) ExpenseQueryForm expenseQueryForm) {
        log.info("search with expenseQueryForm:", expenseQueryForm.toString());
        IPage<Expense> expensePage=expenseService.selectPage(expenseQueryForm);
        IPage<ExpenseVo> expenseVoPage=new BeanUtils<ExpenseVo>().copyPageObjs(expensePage,ExpenseVo.class);
        return new Result<IPage<ExpenseVo>>().sucess(expenseVoPage);
    }

}



