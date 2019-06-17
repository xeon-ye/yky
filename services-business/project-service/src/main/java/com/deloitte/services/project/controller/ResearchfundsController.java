package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.ResearchfundsQueryForm;
import com.deloitte.platform.api.project.vo.ResearchfundsForm;
import com.deloitte.platform.api.project.vo.ResearchfundsVo;
import com.deloitte.platform.api.project.client.ResearchfundsClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.project.service.IResearchfundsService;
import com.deloitte.services.project.entity.Researchfunds;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-31
 * @Description :   Researchfunds控制器实现类
 * @Modified :
 */
@Api(tags = "Researchfunds操作接口")
@Slf4j
@RestController
public class ResearchfundsController implements ResearchfundsClient {

    @Autowired
    public IResearchfundsService  researchfundsService;


    @Override
    @ApiOperation(value = "新增Researchfunds", notes = "新增一个Researchfunds")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="researchfundsForm",value="新增Researchfunds的form表单",required=true)  ResearchfundsForm researchfundsForm) {
        log.info("form:",  researchfundsForm.toString());
        Researchfunds researchfunds =new BeanUtils<Researchfunds>().copyObj(researchfundsForm,Researchfunds.class);
        return Result.success( researchfundsService.save(researchfunds));
    }


    @Override
    @ApiOperation(value = "删除Researchfunds", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ResearchfundsID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        researchfundsService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改Researchfunds", notes = "修改指定Researchfunds信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "Researchfunds的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="researchfundsForm",value="修改Researchfunds的form表单",required=true)  ResearchfundsForm researchfundsForm) {
        Researchfunds researchfunds =new BeanUtils<Researchfunds>().copyObj(researchfundsForm,Researchfunds.class);
        researchfunds.setId(id);
        researchfundsService.saveOrUpdate(researchfunds);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取Researchfunds", notes = "获取指定ID的Researchfunds信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Researchfunds的ID", required = true, dataType = "long")
    public Result<ResearchfundsVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        Researchfunds researchfunds=researchfundsService.getById(id);
        ResearchfundsVo researchfundsVo=new BeanUtils<ResearchfundsVo>().copyObj(researchfunds,ResearchfundsVo.class);
        return new Result<ResearchfundsVo>().sucess(researchfundsVo);
    }

    @Override
    @ApiOperation(value = "列表查询Researchfunds", notes = "根据条件查询Researchfunds列表数据")
    public Result<List<ResearchfundsVo>> list(@Valid @RequestBody @ApiParam(name="researchfundsQueryForm",value="Researchfunds查询参数",required=true) ResearchfundsQueryForm researchfundsQueryForm) {
        log.info("search with researchfundsQueryForm:", researchfundsQueryForm.toString());
        List<Researchfunds> researchfundsList=researchfundsService.selectList(researchfundsQueryForm);
        List<ResearchfundsVo> researchfundsVoList=new BeanUtils<ResearchfundsVo>().copyObjs(researchfundsList,ResearchfundsVo.class);
        return new Result<List<ResearchfundsVo>>().sucess(researchfundsVoList);
    }


    @Override
    @ApiOperation(value = "分页查询Researchfunds", notes = "根据条件查询Researchfunds分页数据")
    public Result<IPage<ResearchfundsVo>> search(@Valid @RequestBody @ApiParam(name="researchfundsQueryForm",value="Researchfunds查询参数",required=true) ResearchfundsQueryForm researchfundsQueryForm) {
        log.info("search with researchfundsQueryForm:", researchfundsQueryForm.toString());
        IPage<Researchfunds> researchfundsPage=researchfundsService.selectPage(researchfundsQueryForm);
        IPage<ResearchfundsVo> researchfundsVoPage=new BeanUtils<ResearchfundsVo>().copyPageObjs(researchfundsPage,ResearchfundsVo.class);
        return new Result<IPage<ResearchfundsVo>>().sucess(researchfundsVoPage);
    }

}



