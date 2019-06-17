package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.MaintBudgetQueryForm;
import com.deloitte.platform.api.project.vo.MaintBudgetForm;
import com.deloitte.platform.api.project.vo.MaintBudgetVo;
import com.deloitte.platform.api.project.client.MaintBudgetClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.project.service.IMaintBudgetService;
import com.deloitte.services.project.entity.MaintBudget;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description :   MaintBudget控制器实现类
 * @Modified :
 */
@Api(tags = "MaintBudget操作接口")
@Slf4j
@RestController
public class MaintBudgetController implements MaintBudgetClient {

    @Autowired
    public IMaintBudgetService  maintBudgetService;


    @Override
    @ApiOperation(value = "新增MaintBudget", notes = "新增一个MaintBudget")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="maintBudgetForm",value="新增MaintBudget的form表单",required=true)  MaintBudgetForm maintBudgetForm) {
        log.info("form:",  maintBudgetForm.toString());
        MaintBudget maintBudget =new BeanUtils<MaintBudget>().copyObj(maintBudgetForm,MaintBudget.class);
        return Result.success( maintBudgetService.save(maintBudget));
    }


    @Override
    @ApiOperation(value = "删除MaintBudget", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "MaintBudgetID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        maintBudgetService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改MaintBudget", notes = "修改指定MaintBudget信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "MaintBudget的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="maintBudgetForm",value="修改MaintBudget的form表单",required=true)  MaintBudgetForm maintBudgetForm) {
        MaintBudget maintBudget =new BeanUtils<MaintBudget>().copyObj(maintBudgetForm,MaintBudget.class);
        maintBudget.setId(id);
        maintBudgetService.saveOrUpdate(maintBudget);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取MaintBudget", notes = "获取指定ID的MaintBudget信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "MaintBudget的ID", required = true, dataType = "long")
    public Result<MaintBudgetVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        MaintBudget maintBudget=maintBudgetService.getById(id);
        MaintBudgetVo maintBudgetVo=new BeanUtils<MaintBudgetVo>().copyObj(maintBudget,MaintBudgetVo.class);
        return new Result<MaintBudgetVo>().sucess(maintBudgetVo);
    }

    @Override
    @ApiOperation(value = "列表查询MaintBudget", notes = "根据条件查询MaintBudget列表数据")
    public Result<List<MaintBudgetVo>> list(@Valid @RequestBody @ApiParam(name="maintBudgetQueryForm",value="MaintBudget查询参数",required=true) MaintBudgetQueryForm maintBudgetQueryForm) {
        log.info("search with maintBudgetQueryForm:", maintBudgetQueryForm.toString());
        List<MaintBudget> maintBudgetList=maintBudgetService.selectList(maintBudgetQueryForm);
        List<MaintBudgetVo> maintBudgetVoList=new BeanUtils<MaintBudgetVo>().copyObjs(maintBudgetList,MaintBudgetVo.class);
        return new Result<List<MaintBudgetVo>>().sucess(maintBudgetVoList);
    }


    @Override
    @ApiOperation(value = "分页查询MaintBudget", notes = "根据条件查询MaintBudget分页数据")
    public Result<IPage<MaintBudgetVo>> search(@Valid @RequestBody @ApiParam(name="maintBudgetQueryForm",value="MaintBudget查询参数",required=true) MaintBudgetQueryForm maintBudgetQueryForm) {
        log.info("search with maintBudgetQueryForm:", maintBudgetQueryForm.toString());
        IPage<MaintBudget> maintBudgetPage=maintBudgetService.selectPage(maintBudgetQueryForm);
        IPage<MaintBudgetVo> maintBudgetVoPage=new BeanUtils<MaintBudgetVo>().copyPageObjs(maintBudgetPage,MaintBudgetVo.class);
        return new Result<IPage<MaintBudgetVo>>().sucess(maintBudgetVoPage);
    }

}



