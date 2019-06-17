package com.deloitte.services.fssc.report.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.report.param.ReportTotalQueryQueryForm;
import com.deloitte.platform.api.fssc.report.vo.ReportTotalQueryForm;
import com.deloitte.platform.api.fssc.report.vo.ReportTotalQueryVo;
import com.deloitte.platform.api.fssc.report.client.ReportTotalQueryClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.fssc.report.service.IReportTotalQueryService;
import com.deloitte.services.fssc.report.entity.ReportTotalQuery;


/**
 * @Author : jaws
 * @Date : Create in 2019-06-12
 * @Description :   ReportTotalQuery控制器实现类
 * @Modified :
 */
@Api(tags = "ReportTotalQuery操作接口")
@Slf4j
@RestController
public class ReportTotalQueryController implements ReportTotalQueryClient {

    @Autowired
    public IReportTotalQueryService  reportTotalQueryService;


    @Override
    @ApiOperation(value = "获取ReportTotalQuery", notes = "获取指定ID的ReportTotalQuery信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ReportTotalQuery的ID", required = true, dataType = "long")
    public Result<ReportTotalQueryVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        ReportTotalQuery reportTotalQuery=reportTotalQueryService.getById(id);
        ReportTotalQueryVo reportTotalQueryVo=new BeanUtils<ReportTotalQueryVo>().copyObj(reportTotalQuery,ReportTotalQueryVo.class);
        return new Result<ReportTotalQueryVo>().sucess(reportTotalQueryVo);
    }

    @Override
    @ApiOperation(value = "列表查询ReportTotalQuery", notes = "根据条件查询ReportTotalQuery列表数据")
    public Result<List<ReportTotalQueryVo>> list(@Valid @RequestBody @ApiParam(name="reportTotalQueryQueryForm",value="ReportTotalQuery查询参数",required=true) ReportTotalQueryQueryForm reportTotalQueryQueryForm) {
        log.info("search with reportTotalQueryQueryForm:", reportTotalQueryQueryForm.toString());
        List<ReportTotalQuery> reportTotalQueryList=reportTotalQueryService.selectList(reportTotalQueryQueryForm);
        List<ReportTotalQueryVo> reportTotalQueryVoList=new BeanUtils<ReportTotalQueryVo>().copyObjs(reportTotalQueryList,ReportTotalQueryVo.class);
        return new Result<List<ReportTotalQueryVo>>().sucess(reportTotalQueryVoList);
    }


    @Override
    @ApiOperation(value = "分页查询ReportTotalQuery", notes = "根据条件查询ReportTotalQuery分页数据")
    public Result<IPage<ReportTotalQueryVo>> search(@Valid @RequestBody @ApiParam(name="reportTotalQueryQueryForm",value="ReportTotalQuery查询参数",required=true) ReportTotalQueryQueryForm reportTotalQueryQueryForm) {
        log.info("search with reportTotalQueryQueryForm:", reportTotalQueryQueryForm.toString());
        IPage<ReportTotalQuery> reportTotalQueryPage=reportTotalQueryService.selectPage(reportTotalQueryQueryForm);
        IPage<ReportTotalQueryVo> reportTotalQueryVoPage=new BeanUtils<ReportTotalQueryVo>().copyPageObjs(reportTotalQueryPage,ReportTotalQueryVo.class);
        return new Result<IPage<ReportTotalQueryVo>>().sucess(reportTotalQueryVoPage);
    }

}



