package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.AchieveResearchQueryForm;
import com.deloitte.platform.api.project.vo.AchieveResearchForm;
import com.deloitte.platform.api.project.vo.AchieveResearchVo;
import com.deloitte.platform.api.project.client.AchieveResearchClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.project.service.IAchieveResearchService;
import com.deloitte.services.project.entity.AchieveResearch;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-31
 * @Description :   AchieveResearch控制器实现类
 * @Modified :
 */
@Api(tags = "AchieveResearch操作接口")
@Slf4j
@RestController
public class AchieveResearchController implements AchieveResearchClient {

    @Autowired
    public IAchieveResearchService  achieveResearchService;


    @Override
    @ApiOperation(value = "新增AchieveResearch", notes = "新增一个AchieveResearch")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="achieveResearchForm",value="新增AchieveResearch的form表单",required=true)  AchieveResearchForm achieveResearchForm) {
        log.info("form:",  achieveResearchForm.toString());
        AchieveResearch achieveResearch =new BeanUtils<AchieveResearch>().copyObj(achieveResearchForm,AchieveResearch.class);
        return Result.success( achieveResearchService.save(achieveResearch));
    }


    @Override
    @ApiOperation(value = "删除AchieveResearch", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "AchieveResearchID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        achieveResearchService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改AchieveResearch", notes = "修改指定AchieveResearch信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "AchieveResearch的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="achieveResearchForm",value="修改AchieveResearch的form表单",required=true)  AchieveResearchForm achieveResearchForm) {
        AchieveResearch achieveResearch =new BeanUtils<AchieveResearch>().copyObj(achieveResearchForm,AchieveResearch.class);
        achieveResearch.setId(id);
        achieveResearchService.saveOrUpdate(achieveResearch);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取AchieveResearch", notes = "获取指定ID的AchieveResearch信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "AchieveResearch的ID", required = true, dataType = "long")
    public Result<AchieveResearchVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        AchieveResearch achieveResearch=achieveResearchService.getById(id);
        AchieveResearchVo achieveResearchVo=new BeanUtils<AchieveResearchVo>().copyObj(achieveResearch,AchieveResearchVo.class);
        return new Result<AchieveResearchVo>().sucess(achieveResearchVo);
    }

    @Override
    @ApiOperation(value = "列表查询AchieveResearch", notes = "根据条件查询AchieveResearch列表数据")
    public Result<List<AchieveResearchVo>> list(@Valid @RequestBody @ApiParam(name="achieveResearchQueryForm",value="AchieveResearch查询参数",required=true) AchieveResearchQueryForm achieveResearchQueryForm) {
        log.info("search with achieveResearchQueryForm:", achieveResearchQueryForm.toString());
        List<AchieveResearch> achieveResearchList=achieveResearchService.selectList(achieveResearchQueryForm);
        List<AchieveResearchVo> achieveResearchVoList=new BeanUtils<AchieveResearchVo>().copyObjs(achieveResearchList,AchieveResearchVo.class);
        return new Result<List<AchieveResearchVo>>().sucess(achieveResearchVoList);
    }


    @Override
    @ApiOperation(value = "分页查询AchieveResearch", notes = "根据条件查询AchieveResearch分页数据")
    public Result<IPage<AchieveResearchVo>> search(@Valid @RequestBody @ApiParam(name="achieveResearchQueryForm",value="AchieveResearch查询参数",required=true) AchieveResearchQueryForm achieveResearchQueryForm) {
        log.info("search with achieveResearchQueryForm:", achieveResearchQueryForm.toString());
        IPage<AchieveResearch> achieveResearchPage=achieveResearchService.selectPage(achieveResearchQueryForm);
        IPage<AchieveResearchVo> achieveResearchVoPage=new BeanUtils<AchieveResearchVo>().copyPageObjs(achieveResearchPage,AchieveResearchVo.class);
        return new Result<IPage<AchieveResearchVo>>().sucess(achieveResearchVoPage);
    }

}



