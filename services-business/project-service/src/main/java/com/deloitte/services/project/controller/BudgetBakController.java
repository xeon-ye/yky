package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.BudgetBakQueryForm;
import com.deloitte.platform.api.project.vo.BudgetBakForm;
import com.deloitte.platform.api.project.vo.BudgetBakVo;
import com.deloitte.platform.api.project.client.BudgetBakClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.project.service.IBudgetBakService;
import com.deloitte.services.project.entity.BudgetBak;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-09
 * @Description :   BudgetBak控制器实现类
 * @Modified :
 */
@Api(tags = "BudgetBak操作接口")
@Slf4j
@RestController
public class BudgetBakController implements BudgetBakClient {

    @Autowired
    public IBudgetBakService  budgetBakService;


    @Override
    @ApiOperation(value = "新增BudgetBak", notes = "新增一个BudgetBak")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="budgetBakForm",value="新增BudgetBak的form表单",required=true)  BudgetBakForm budgetBakForm) {
        log.info("form:",  budgetBakForm.toString());
        BudgetBak budgetBak =new BeanUtils<BudgetBak>().copyObj(budgetBakForm,BudgetBak.class);
        return Result.success( budgetBakService.save(budgetBak));
    }


    @Override
    @ApiOperation(value = "删除BudgetBak", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "BudgetBakID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        budgetBakService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改BudgetBak", notes = "修改指定BudgetBak信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "BudgetBak的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="budgetBakForm",value="修改BudgetBak的form表单",required=true)  BudgetBakForm budgetBakForm) {
        BudgetBak budgetBak =new BeanUtils<BudgetBak>().copyObj(budgetBakForm,BudgetBak.class);
        budgetBak.setId(id);
        budgetBakService.saveOrUpdate(budgetBak);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取BudgetBak", notes = "获取指定ID的BudgetBak信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "BudgetBak的ID", required = true, dataType = "long")
    public Result<BudgetBakVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        BudgetBak budgetBak=budgetBakService.getById(id);
        BudgetBakVo budgetBakVo=new BeanUtils<BudgetBakVo>().copyObj(budgetBak,BudgetBakVo.class);
        return new Result<BudgetBakVo>().sucess(budgetBakVo);
    }

    @Override
    @ApiOperation(value = "列表查询BudgetBak", notes = "根据条件查询BudgetBak列表数据")
    public Result<List<BudgetBakVo>> list(@Valid @RequestBody @ApiParam(name="budgetBakQueryForm",value="BudgetBak查询参数",required=true) BudgetBakQueryForm budgetBakQueryForm) {
        log.info("search with budgetBakQueryForm:", budgetBakQueryForm.toString());
        List<BudgetBak> budgetBakList=budgetBakService.selectList(budgetBakQueryForm);
        List<BudgetBakVo> budgetBakVoList=new BeanUtils<BudgetBakVo>().copyObjs(budgetBakList,BudgetBakVo.class);
        return new Result<List<BudgetBakVo>>().sucess(budgetBakVoList);
    }


    @Override
    @ApiOperation(value = "分页查询BudgetBak", notes = "根据条件查询BudgetBak分页数据")
    public Result<IPage<BudgetBakVo>> search(@Valid @RequestBody @ApiParam(name="budgetBakQueryForm",value="BudgetBak查询参数",required=true) BudgetBakQueryForm budgetBakQueryForm) {
        log.info("search with budgetBakQueryForm:", budgetBakQueryForm.toString());
        IPage<BudgetBak> budgetBakPage=budgetBakService.selectPage(budgetBakQueryForm);
        IPage<BudgetBakVo> budgetBakVoPage=new BeanUtils<BudgetBakVo>().copyPageObjs(budgetBakPage,BudgetBakVo.class);
        return new Result<IPage<BudgetBakVo>>().sucess(budgetBakVoPage);
    }

}



