package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.ImprovementsQueryForm;
import com.deloitte.platform.api.project.vo.ImprovementsForm;
import com.deloitte.platform.api.project.vo.ImprovementsVo;
import com.deloitte.platform.api.project.client.ImprovementsClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.project.service.IImprovementsService;
import com.deloitte.services.project.entity.Improvements;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :   Improvements控制器实现类
 * @Modified :
 */
@Api(tags = "Improvements操作接口")
@Slf4j
@RestController
public class ImprovementsController implements ImprovementsClient {

    @Autowired
    public IImprovementsService  improvementsService;


    @Override
    @ApiOperation(value = "新增Improvements", notes = "新增一个Improvements")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="improvementsForm",value="新增Improvements的form表单",required=true)  ImprovementsForm improvementsForm) {
        log.info("form:",  improvementsForm.toString());
        Improvements improvements =new BeanUtils<Improvements>().copyObj(improvementsForm,Improvements.class);
        return Result.success( improvementsService.save(improvements));
    }


    @Override
    @ApiOperation(value = "删除Improvements", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ImprovementsID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        improvementsService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改Improvements", notes = "修改指定Improvements信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "Improvements的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="improvementsForm",value="修改Improvements的form表单",required=true)  ImprovementsForm improvementsForm) {
        Improvements improvements =new BeanUtils<Improvements>().copyObj(improvementsForm,Improvements.class);
        improvements.setId(id);
        improvementsService.saveOrUpdate(improvements);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取Improvements", notes = "获取指定ID的Improvements信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Improvements的ID", required = true, dataType = "long")
    public Result<ImprovementsVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        Improvements improvements=improvementsService.getById(id);
        ImprovementsVo improvementsVo=new BeanUtils<ImprovementsVo>().copyObj(improvements,ImprovementsVo.class);
        return new Result<ImprovementsVo>().sucess(improvementsVo);
    }

    @Override
    @ApiOperation(value = "列表查询Improvements", notes = "根据条件查询Improvements列表数据")
    public Result<List<ImprovementsVo>> list(@Valid @RequestBody @ApiParam(name="improvementsQueryForm",value="Improvements查询参数",required=true) ImprovementsQueryForm improvementsQueryForm) {
        log.info("search with improvementsQueryForm:", improvementsQueryForm.toString());
        List<Improvements> improvementsList=improvementsService.selectList(improvementsQueryForm);
        List<ImprovementsVo> improvementsVoList=new BeanUtils<ImprovementsVo>().copyObjs(improvementsList,ImprovementsVo.class);
        return new Result<List<ImprovementsVo>>().sucess(improvementsVoList);
    }


    @Override
    @ApiOperation(value = "分页查询Improvements", notes = "根据条件查询Improvements分页数据")
    public Result<IPage<ImprovementsVo>> search(@Valid @RequestBody @ApiParam(name="improvementsQueryForm",value="Improvements查询参数",required=true) ImprovementsQueryForm improvementsQueryForm) {
        log.info("search with improvementsQueryForm:", improvementsQueryForm.toString());
        IPage<Improvements> improvementsPage=improvementsService.selectPage(improvementsQueryForm);
        IPage<ImprovementsVo> improvementsVoPage=new BeanUtils<ImprovementsVo>().copyPageObjs(improvementsPage,ImprovementsVo.class);
        return new Result<IPage<ImprovementsVo>>().sucess(improvementsVoPage);
    }

}



