package com.deloitte.services.fssc.report.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.report.param.ReportDeptBudgetDoStatisQueryForm;
import com.deloitte.platform.api.fssc.report.vo.ReportDeptBudgetDoStatisForm;
import com.deloitte.platform.api.fssc.report.vo.ReportDeptBudgetDoStatisVo;
import com.deloitte.platform.api.fssc.report.client.ReportDeptBudgetDoStatisClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.fssc.report.service.IReportDeptBudgetDoStatisService;
import com.deloitte.services.fssc.report.entity.ReportDeptBudgetDoStatis;


/**
 * @Author : jaws
 * @Date : Create in 2019-06-14
 * @Description :   ReportDeptBudgetDoStatis控制器实现类
 * @Modified :
 */
@Api(tags = "ReportDeptBudgetDoStatis操作接口")
@Slf4j
@RestController
public class ReportDeptBudgetDoStatisController implements ReportDeptBudgetDoStatisClient {

    @Autowired
    public IReportDeptBudgetDoStatisService  reportDeptBudgetDoStatisService;


    @Override
    @ApiOperation(value = "新增ReportDeptBudgetDoStatis", notes = "新增一个ReportDeptBudgetDoStatis")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="reportDeptBudgetDoStatisForm",value="新增ReportDeptBudgetDoStatis的form表单",required=true)  ReportDeptBudgetDoStatisForm reportDeptBudgetDoStatisForm) {
        log.info("form:",  reportDeptBudgetDoStatisForm.toString());
        ReportDeptBudgetDoStatis reportDeptBudgetDoStatis =new BeanUtils<ReportDeptBudgetDoStatis>().copyObj(reportDeptBudgetDoStatisForm,ReportDeptBudgetDoStatis.class);
        return Result.success( reportDeptBudgetDoStatisService.save(reportDeptBudgetDoStatis));
    }


    @Override
    @ApiOperation(value = "删除ReportDeptBudgetDoStatis", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ReportDeptBudgetDoStatisID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        reportDeptBudgetDoStatisService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改ReportDeptBudgetDoStatis", notes = "修改指定ReportDeptBudgetDoStatis信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "ReportDeptBudgetDoStatis的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="reportDeptBudgetDoStatisForm",value="修改ReportDeptBudgetDoStatis的form表单",required=true)  ReportDeptBudgetDoStatisForm reportDeptBudgetDoStatisForm) {
        ReportDeptBudgetDoStatis reportDeptBudgetDoStatis =new BeanUtils<ReportDeptBudgetDoStatis>().copyObj(reportDeptBudgetDoStatisForm,ReportDeptBudgetDoStatis.class);
        reportDeptBudgetDoStatis.setId(id);
        reportDeptBudgetDoStatisService.saveOrUpdate(reportDeptBudgetDoStatis);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取ReportDeptBudgetDoStatis", notes = "获取指定ID的ReportDeptBudgetDoStatis信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ReportDeptBudgetDoStatis的ID", required = true, dataType = "long")
    public Result<ReportDeptBudgetDoStatisVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        ReportDeptBudgetDoStatis reportDeptBudgetDoStatis=reportDeptBudgetDoStatisService.getById(id);
        ReportDeptBudgetDoStatisVo reportDeptBudgetDoStatisVo=new BeanUtils<ReportDeptBudgetDoStatisVo>().copyObj(reportDeptBudgetDoStatis,ReportDeptBudgetDoStatisVo.class);
        return new Result<ReportDeptBudgetDoStatisVo>().sucess(reportDeptBudgetDoStatisVo);
    }

    @Override
    @ApiOperation(value = "列表查询ReportDeptBudgetDoStatis", notes = "根据条件查询ReportDeptBudgetDoStatis列表数据")
    public Result<List<ReportDeptBudgetDoStatisVo>> list(@Valid @RequestBody @ApiParam(name="reportDeptBudgetDoStatisQueryForm",value="ReportDeptBudgetDoStatis查询参数",required=true) ReportDeptBudgetDoStatisQueryForm reportDeptBudgetDoStatisQueryForm) {
        log.info("search with reportDeptBudgetDoStatisQueryForm:", reportDeptBudgetDoStatisQueryForm.toString());
        List<ReportDeptBudgetDoStatis> reportDeptBudgetDoStatisList=reportDeptBudgetDoStatisService.selectList(reportDeptBudgetDoStatisQueryForm);
        List<ReportDeptBudgetDoStatisVo> reportDeptBudgetDoStatisVoList=new BeanUtils<ReportDeptBudgetDoStatisVo>().copyObjs(reportDeptBudgetDoStatisList,ReportDeptBudgetDoStatisVo.class);
        return new Result<List<ReportDeptBudgetDoStatisVo>>().sucess(reportDeptBudgetDoStatisVoList);
    }


    @Override
    @ApiOperation(value = "分页查询ReportDeptBudgetDoStatis", notes = "根据条件查询ReportDeptBudgetDoStatis分页数据")
    public Result<IPage<ReportDeptBudgetDoStatisVo>> search(@Valid @RequestBody @ApiParam(name="reportDeptBudgetDoStatisQueryForm",value="ReportDeptBudgetDoStatis查询参数",required=true) ReportDeptBudgetDoStatisQueryForm reportDeptBudgetDoStatisQueryForm) {
        log.info("search with reportDeptBudgetDoStatisQueryForm:", reportDeptBudgetDoStatisQueryForm.toString());
        IPage<ReportDeptBudgetDoStatis> reportDeptBudgetDoStatisPage=reportDeptBudgetDoStatisService.selectPage(reportDeptBudgetDoStatisQueryForm);
        IPage<ReportDeptBudgetDoStatisVo> reportDeptBudgetDoStatisVoPage=new BeanUtils<ReportDeptBudgetDoStatisVo>().copyPageObjs(reportDeptBudgetDoStatisPage,ReportDeptBudgetDoStatisVo.class);
        return new Result<IPage<ReportDeptBudgetDoStatisVo>>().sucess(reportDeptBudgetDoStatisVoPage);
    }

}



