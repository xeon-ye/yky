package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.ExeBudgetQueryForm;
import com.deloitte.platform.api.project.vo.ExeBudgetForm;
import com.deloitte.platform.api.project.vo.ExeBudgetVo;
import com.deloitte.platform.api.project.client.ExeBudgetClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.project.service.IExeBudgetService;
import com.deloitte.services.project.entity.ExeBudget;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description :   ExeBudget控制器实现类
 * @Modified :
 */
@Api(tags = "ExeBudget操作接口")
@Slf4j
@RestController
public class ExeBudgetController implements ExeBudgetClient {

    @Autowired
    public IExeBudgetService  exeBudgetService;


    @Override
    @ApiOperation(value = "新增ExeBudget", notes = "新增一个ExeBudget")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="exeBudgetForm",value="新增ExeBudget的form表单",required=true)  ExeBudgetForm exeBudgetForm) {
        log.info("form:",  exeBudgetForm.toString());
        ExeBudget exeBudget =new BeanUtils<ExeBudget>().copyObj(exeBudgetForm,ExeBudget.class);
        return Result.success( exeBudgetService.save(exeBudget));
    }


    @Override
    @ApiOperation(value = "删除ExeBudget", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ExeBudgetID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        exeBudgetService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改ExeBudget", notes = "修改指定ExeBudget信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "ExeBudget的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="exeBudgetForm",value="修改ExeBudget的form表单",required=true)  ExeBudgetForm exeBudgetForm) {
        ExeBudget exeBudget =new BeanUtils<ExeBudget>().copyObj(exeBudgetForm,ExeBudget.class);
        exeBudget.setId(id);
        exeBudgetService.saveOrUpdate(exeBudget);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取ExeBudget", notes = "获取指定ID的ExeBudget信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ExeBudget的ID", required = true, dataType = "long")
    public Result<ExeBudgetVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        ExeBudget exeBudget=exeBudgetService.getById(id);
        ExeBudgetVo exeBudgetVo=new BeanUtils<ExeBudgetVo>().copyObj(exeBudget,ExeBudgetVo.class);
        return new Result<ExeBudgetVo>().sucess(exeBudgetVo);
    }

    @Override
    @ApiOperation(value = "列表查询ExeBudget", notes = "根据条件查询ExeBudget列表数据")
    public Result<List<ExeBudgetVo>> list(@Valid @RequestBody @ApiParam(name="exeBudgetQueryForm",value="ExeBudget查询参数",required=true) ExeBudgetQueryForm exeBudgetQueryForm) {
        log.info("search with exeBudgetQueryForm:", exeBudgetQueryForm.toString());
        List<ExeBudget> exeBudgetList=exeBudgetService.selectList(exeBudgetQueryForm);
        List<ExeBudgetVo> exeBudgetVoList=new BeanUtils<ExeBudgetVo>().copyObjs(exeBudgetList,ExeBudgetVo.class);
        return new Result<List<ExeBudgetVo>>().sucess(exeBudgetVoList);
    }


    @Override
    @ApiOperation(value = "分页查询ExeBudget", notes = "根据条件查询ExeBudget分页数据")
    public Result<IPage<ExeBudgetVo>> search(@Valid @RequestBody @ApiParam(name="exeBudgetQueryForm",value="ExeBudget查询参数",required=true) ExeBudgetQueryForm exeBudgetQueryForm) {
        log.info("search with exeBudgetQueryForm:", exeBudgetQueryForm.toString());
        IPage<ExeBudget> exeBudgetPage=exeBudgetService.selectPage(exeBudgetQueryForm);
        IPage<ExeBudgetVo> exeBudgetVoPage=new BeanUtils<ExeBudgetVo>().copyPageObjs(exeBudgetPage,ExeBudgetVo.class);
        return new Result<IPage<ExeBudgetVo>>().sucess(exeBudgetVoPage);
    }

}



