package com.deloitte.services.fssc.report.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.report.param.ReportMergeRelationQueryForm;
import com.deloitte.platform.api.fssc.report.vo.ReportMergeRelationForm;
import com.deloitte.platform.api.fssc.report.vo.ReportMergeRelationVo;
import com.deloitte.platform.api.fssc.report.client.ReportMergeRelationClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.fssc.report.service.IReportMergeRelationService;
import com.deloitte.services.fssc.report.entity.ReportMergeRelation;


/**
 * @Author : jaws
 * @Date : Create in 2019-06-12
 * @Description :   ReportMergeRelation控制器实现类
 * @Modified :
 */
@Api(tags = "ReportMergeRelation操作接口")
@Slf4j
@RestController
public class ReportMergeRelationController implements ReportMergeRelationClient {

    @Autowired
    public IReportMergeRelationService  reportMergeRelationService;


    @Override
    @ApiOperation(value = "新增ReportMergeRelation", notes = "新增一个ReportMergeRelation")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="reportMergeRelationForm",value="新增ReportMergeRelation的form表单",required=true)  ReportMergeRelationForm reportMergeRelationForm) {
        log.info("form:",  reportMergeRelationForm.toString());
        ReportMergeRelation reportMergeRelation =new BeanUtils<ReportMergeRelation>().copyObj(reportMergeRelationForm,ReportMergeRelation.class);
        return Result.success( reportMergeRelationService.save(reportMergeRelation));
    }


    @Override
    @ApiOperation(value = "删除ReportMergeRelation", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ReportMergeRelationID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        reportMergeRelationService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改ReportMergeRelation", notes = "修改指定ReportMergeRelation信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "ReportMergeRelation的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="reportMergeRelationForm",value="修改ReportMergeRelation的form表单",required=true)  ReportMergeRelationForm reportMergeRelationForm) {
        ReportMergeRelation reportMergeRelation =new BeanUtils<ReportMergeRelation>().copyObj(reportMergeRelationForm,ReportMergeRelation.class);
        reportMergeRelation.setId(id);
        reportMergeRelationService.saveOrUpdate(reportMergeRelation);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取ReportMergeRelation", notes = "获取指定ID的ReportMergeRelation信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ReportMergeRelation的ID", required = true, dataType = "long")
    public Result<ReportMergeRelationVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        ReportMergeRelation reportMergeRelation=reportMergeRelationService.getById(id);
        ReportMergeRelationVo reportMergeRelationVo=new BeanUtils<ReportMergeRelationVo>().copyObj(reportMergeRelation,ReportMergeRelationVo.class);
        return new Result<ReportMergeRelationVo>().sucess(reportMergeRelationVo);
    }

    @Override
    @ApiOperation(value = "列表查询ReportMergeRelation", notes = "根据条件查询ReportMergeRelation列表数据")
    public Result<List<ReportMergeRelationVo>> list(@Valid @RequestBody @ApiParam(name="reportMergeRelationQueryForm",value="ReportMergeRelation查询参数",required=true) ReportMergeRelationQueryForm reportMergeRelationQueryForm) {
        log.info("search with reportMergeRelationQueryForm:", reportMergeRelationQueryForm.toString());
        List<ReportMergeRelation> reportMergeRelationList=reportMergeRelationService.selectList(reportMergeRelationQueryForm);
        List<ReportMergeRelationVo> reportMergeRelationVoList=new BeanUtils<ReportMergeRelationVo>().copyObjs(reportMergeRelationList,ReportMergeRelationVo.class);
        return new Result<List<ReportMergeRelationVo>>().sucess(reportMergeRelationVoList);
    }


    @Override
    @ApiOperation(value = "分页查询ReportMergeRelation", notes = "根据条件查询ReportMergeRelation分页数据")
    public Result<IPage<ReportMergeRelationVo>> search(@Valid @RequestBody @ApiParam(name="reportMergeRelationQueryForm",value="ReportMergeRelation查询参数",required=true) ReportMergeRelationQueryForm reportMergeRelationQueryForm) {
        log.info("search with reportMergeRelationQueryForm:", reportMergeRelationQueryForm.toString());
        IPage<ReportMergeRelation> reportMergeRelationPage=reportMergeRelationService.selectPage(reportMergeRelationQueryForm);
        IPage<ReportMergeRelationVo> reportMergeRelationVoPage=new BeanUtils<ReportMergeRelationVo>().copyPageObjs(reportMergeRelationPage,ReportMergeRelationVo.class);
        return new Result<IPage<ReportMergeRelationVo>>().sucess(reportMergeRelationVoPage);
    }

}



