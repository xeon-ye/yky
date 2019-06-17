package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.SubactQueryForm;
import com.deloitte.platform.api.project.vo.SubactForm;
import com.deloitte.platform.api.project.vo.SubactVo;
import com.deloitte.platform.api.project.client.SubactClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.project.service.ISubactService;
import com.deloitte.services.project.entity.Subact;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-14
 * @Description :   Subact控制器实现类
 * @Modified :
 */
@Api(tags = "Subact操作接口")
@Slf4j
@RestController
public class SubactController implements SubactClient {

    @Autowired
    public ISubactService  subactService;


    @Override
    @ApiOperation(value = "新增Subact", notes = "新增一个Subact")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="subactForm",value="新增Subact的form表单",required=true)  SubactForm subactForm) {
        log.info("form:",  subactForm.toString());
        Subact subact =new BeanUtils<Subact>().copyObj(subactForm,Subact.class);
        return Result.success( subactService.save(subact));
    }


    @Override
    @ApiOperation(value = "删除Subact", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SubactID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        subactService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改Subact", notes = "修改指定Subact信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "Subact的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="subactForm",value="修改Subact的form表单",required=true)  SubactForm subactForm) {
        Subact subact =new BeanUtils<Subact>().copyObj(subactForm,Subact.class);
        subact.setId(id);
        subactService.saveOrUpdate(subact);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取Subact", notes = "获取指定ID的Subact信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Subact的ID", required = true, dataType = "long")
    public Result<SubactVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        Subact subact=subactService.getById(id);
        SubactVo subactVo=new BeanUtils<SubactVo>().copyObj(subact,SubactVo.class);
        return new Result<SubactVo>().sucess(subactVo);
    }

    @Override
    @ApiOperation(value = "列表查询Subact", notes = "根据条件查询Subact列表数据")
    public Result<List<SubactVo>> list(@Valid @RequestBody @ApiParam(name="subactQueryForm",value="Subact查询参数",required=true) SubactQueryForm subactQueryForm) {
        log.info("search with subactQueryForm:", subactQueryForm.toString());
        List<Subact> subactList=subactService.selectList(subactQueryForm);
        List<SubactVo> subactVoList=new BeanUtils<SubactVo>().copyObjs(subactList,SubactVo.class);
        return new Result<List<SubactVo>>().sucess(subactVoList);
    }


    @Override
    @ApiOperation(value = "分页查询Subact", notes = "根据条件查询Subact分页数据")
    public Result<IPage<SubactVo>> search(@Valid @RequestBody @ApiParam(name="subactQueryForm",value="Subact查询参数",required=true) SubactQueryForm subactQueryForm) {
        log.info("search with subactQueryForm:", subactQueryForm.toString());
        IPage<Subact> subactPage=subactService.selectPage(subactQueryForm);
        IPage<SubactVo> subactVoPage=new BeanUtils<SubactVo>().copyPageObjs(subactPage,SubactVo.class);
        return new Result<IPage<SubactVo>>().sucess(subactVoPage);
    }

}



