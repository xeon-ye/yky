package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.SubactBakQueryForm;
import com.deloitte.platform.api.project.vo.SubactBakForm;
import com.deloitte.platform.api.project.vo.SubactBakVo;
import com.deloitte.platform.api.project.client.SubactBakClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.project.service.ISubactBakService;
import com.deloitte.services.project.entity.SubactBak;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-14
 * @Description :   SubactBak控制器实现类
 * @Modified :
 */
@Api(tags = "SubactBak操作接口")
@Slf4j
@RestController
public class SubactBakController implements SubactBakClient {

    @Autowired
    public ISubactBakService  subactBakService;


    @Override
    @ApiOperation(value = "新增SubactBak", notes = "新增一个SubactBak")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="subactBakForm",value="新增SubactBak的form表单",required=true)  SubactBakForm subactBakForm) {
        log.info("form:",  subactBakForm.toString());
        SubactBak subactBak =new BeanUtils<SubactBak>().copyObj(subactBakForm,SubactBak.class);
        return Result.success( subactBakService.save(subactBak));
    }


    @Override
    @ApiOperation(value = "删除SubactBak", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SubactBakID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        subactBakService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改SubactBak", notes = "修改指定SubactBak信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "SubactBak的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="subactBakForm",value="修改SubactBak的form表单",required=true)  SubactBakForm subactBakForm) {
        SubactBak subactBak =new BeanUtils<SubactBak>().copyObj(subactBakForm,SubactBak.class);
        subactBak.setId(id);
        subactBakService.saveOrUpdate(subactBak);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取SubactBak", notes = "获取指定ID的SubactBak信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SubactBak的ID", required = true, dataType = "long")
    public Result<SubactBakVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        SubactBak subactBak=subactBakService.getById(id);
        SubactBakVo subactBakVo=new BeanUtils<SubactBakVo>().copyObj(subactBak,SubactBakVo.class);
        return new Result<SubactBakVo>().sucess(subactBakVo);
    }

    @Override
    @ApiOperation(value = "列表查询SubactBak", notes = "根据条件查询SubactBak列表数据")
    public Result<List<SubactBakVo>> list(@Valid @RequestBody @ApiParam(name="subactBakQueryForm",value="SubactBak查询参数",required=true) SubactBakQueryForm subactBakQueryForm) {
        log.info("search with subactBakQueryForm:", subactBakQueryForm.toString());
        List<SubactBak> subactBakList=subactBakService.selectList(subactBakQueryForm);
        List<SubactBakVo> subactBakVoList=new BeanUtils<SubactBakVo>().copyObjs(subactBakList,SubactBakVo.class);
        return new Result<List<SubactBakVo>>().sucess(subactBakVoList);
    }


    @Override
    @ApiOperation(value = "分页查询SubactBak", notes = "根据条件查询SubactBak分页数据")
    public Result<IPage<SubactBakVo>> search(@Valid @RequestBody @ApiParam(name="subactBakQueryForm",value="SubactBak查询参数",required=true) SubactBakQueryForm subactBakQueryForm) {
        log.info("search with subactBakQueryForm:", subactBakQueryForm.toString());
        IPage<SubactBak> subactBakPage=subactBakService.selectPage(subactBakQueryForm);
        IPage<SubactBakVo> subactBakVoPage=new BeanUtils<SubactBakVo>().copyPageObjs(subactBakPage,SubactBakVo.class);
        return new Result<IPage<SubactBakVo>>().sucess(subactBakVoPage);
    }

}



