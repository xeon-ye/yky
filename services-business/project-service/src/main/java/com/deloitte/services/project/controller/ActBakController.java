package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.ActBakQueryForm;
import com.deloitte.platform.api.project.vo.ActBakForm;
import com.deloitte.platform.api.project.vo.ActBakVo;
import com.deloitte.platform.api.project.client.ActBakClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.project.service.IActBakService;
import com.deloitte.services.project.entity.ActBak;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-14
 * @Description :   ActBak控制器实现类
 * @Modified :
 */
@Api(tags = "ActBak操作接口")
@Slf4j
@RestController
public class ActBakController implements ActBakClient {

    @Autowired
    public IActBakService  actBakService;


    @Override
    @ApiOperation(value = "新增ActBak", notes = "新增一个ActBak")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="actBakForm",value="新增ActBak的form表单",required=true)  ActBakForm actBakForm) {
        log.info("form:",  actBakForm.toString());
        ActBak actBak =new BeanUtils<ActBak>().copyObj(actBakForm,ActBak.class);
        return Result.success( actBakService.save(actBak));
    }


    @Override
    @ApiOperation(value = "删除ActBak", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ActBakID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        actBakService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改ActBak", notes = "修改指定ActBak信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "ActBak的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="actBakForm",value="修改ActBak的form表单",required=true)  ActBakForm actBakForm) {
        ActBak actBak =new BeanUtils<ActBak>().copyObj(actBakForm,ActBak.class);
        actBak.setId(id);
        actBakService.saveOrUpdate(actBak);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取ActBak", notes = "获取指定ID的ActBak信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ActBak的ID", required = true, dataType = "long")
    public Result<ActBakVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        ActBak actBak=actBakService.getById(id);
        ActBakVo actBakVo=new BeanUtils<ActBakVo>().copyObj(actBak,ActBakVo.class);
        return new Result<ActBakVo>().sucess(actBakVo);
    }

    @Override
    @ApiOperation(value = "列表查询ActBak", notes = "根据条件查询ActBak列表数据")
    public Result<List<ActBakVo>> list(@Valid @RequestBody @ApiParam(name="actBakQueryForm",value="ActBak查询参数",required=true) ActBakQueryForm actBakQueryForm) {
        log.info("search with actBakQueryForm:", actBakQueryForm.toString());
        List<ActBak> actBakList=actBakService.selectList(actBakQueryForm);
        List<ActBakVo> actBakVoList=new BeanUtils<ActBakVo>().copyObjs(actBakList,ActBakVo.class);
        return new Result<List<ActBakVo>>().sucess(actBakVoList);
    }


    @Override
    @ApiOperation(value = "分页查询ActBak", notes = "根据条件查询ActBak分页数据")
    public Result<IPage<ActBakVo>> search(@Valid @RequestBody @ApiParam(name="actBakQueryForm",value="ActBak查询参数",required=true) ActBakQueryForm actBakQueryForm) {
        log.info("search with actBakQueryForm:", actBakQueryForm.toString());
        IPage<ActBak> actBakPage=actBakService.selectPage(actBakQueryForm);
        IPage<ActBakVo> actBakVoPage=new BeanUtils<ActBakVo>().copyPageObjs(actBakPage,ActBakVo.class);
        return new Result<IPage<ActBakVo>>().sucess(actBakVoPage);
    }

}



