package com.deloitte.services.fssc.report.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.report.param.ReportEduDoStatisQueryForm;
import com.deloitte.platform.api.fssc.report.vo.ReportEduDoStatisForm;
import com.deloitte.platform.api.fssc.report.vo.ReportEduDoStatisVo;
import com.deloitte.platform.api.fssc.report.client.ReportEduDoStatisClient;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.report.entity.ReportTotalQuery;
import com.deloitte.services.fssc.report.service.IReportTotalQueryService;
import com.deloitte.services.fssc.util.AssertUtils;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.fssc.report.service.IReportEduDoStatisService;
import com.deloitte.services.fssc.report.entity.ReportEduDoStatis;


/**
 * @Author : jaws
 * @Date : Create in 2019-06-14
 * @Description :   ReportEduDoStatis控制器实现类
 * @Modified :
 */
@Api(tags = "ReportEduDoStatis操作接口")
@Slf4j
@RestController
public class ReportEduDoStatisController implements ReportEduDoStatisClient {

    @Autowired
    private IReportEduDoStatisService reportService;

    @Autowired
    private FsscCommonServices commonServices;

    @Autowired
    private IReportTotalQueryService totalQueryService;

    @Override
    @ApiOperation(value = "新增ReportEduDoStatis", notes = "新增一个ReportEduDoStatis")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ApiParam(name="form",value="新增ReportEduDoStatis的form表单",required=true)
    @Transactional
    public Result addOrUpdate(@Valid @RequestBody ReportEduDoStatisForm form) {
        log.info("form:",  form.toString());
        ReportEduDoStatis reportEduDoStatis = new BeanUtils<ReportEduDoStatis>().copyObj(form,ReportEduDoStatis.class);
        DeptVo deptVo = commonServices.getCurrentDept();
        UserVo userVo = commonServices.getCurrentUser();
        if (form.getId() != null){
            ReportEduDoStatisQueryForm queryForm = new ReportEduDoStatisQueryForm();
            queryForm.setUnitCode(deptVo.getDeptCode());
            queryForm.setYear(form.getYear());
            queryForm.setMonth(form.getMonth());
            queryForm.setPeriodType(FsscEums.PERIOD_TYPE_MONTH.getValue());
            List<ReportEduDoStatis> reportList = reportService.selectList(queryForm);
            AssertUtils.asserts(CollectionUtils.isEmpty(reportList), FsscErrorType.DATA_EXISTS_ADD_FAIL);
            reportEduDoStatis.setUnitId(Long.valueOf(deptVo.getDeptId()));
            reportEduDoStatis.setUnitCode(deptVo.getDeptCode());
            reportEduDoStatis.setCreateBy(Long.valueOf(userVo.getId()));
            reportEduDoStatis.setPeriodType(FsscEums.PERIOD_TYPE_MONTH.getValue());
            reportEduDoStatis.setMergeFlag(FsscEums.NO.getValue());
            reportService.save(reportEduDoStatis);
            ReportTotalQuery totalQuery = new ReportTotalQuery();
            totalQuery.setUnitId(Long.valueOf(deptVo.getDeptId()));
            totalQuery.setUnitCode(deptVo.getDeptCode());
            totalQuery.setCreateBy(Long.valueOf(userVo.getId()));
            totalQuery.setMergeFlag(FsscEums.NO.getValue());
            totalQuery.setReportId(reportEduDoStatis.getId());
            totalQuery.setReportType(FsscTableNameEums.REPORT_EDU_DO_STATIS.getValue());
            totalQuery.setYear(form.getYear());
            totalQuery.setMonth(form.getMonth());
            totalQuery.setPeriodType(FsscEums.PERIOD_TYPE_MONTH.getValue());
            totalQuery.setReportStatus(FsscEums.NEW.getValue());
            return Result.success(totalQueryService.save(totalQuery));
        }else{
            ReportTotalQuery totalQuery = totalQueryService.getByReport(form.getId(),FsscTableNameEums.REPORT_EDU_DO_STATIS.getValue());
            totalQuery.setUpdateBy(Long.valueOf(userVo.getId()));
            totalQueryService.saveOrUpdate(totalQuery);
            return Result.success(reportService.saveOrUpdate(reportEduDoStatis));
        }
    }

    @Override
    @ApiOperation(value = "获取ReportEduDoStatis", notes = "获取指定ID的ReportEduDoStatis信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ReportEduDoStatis的ID", required = true, dataType = "long")
    public Result<ReportEduDoStatisVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        ReportEduDoStatis reportEduDoStatis= reportService.getById(id);
        ReportEduDoStatisVo reportEduDoStatisVo=new BeanUtils<ReportEduDoStatisVo>().copyObj(reportEduDoStatis,ReportEduDoStatisVo.class);
        return new Result<ReportEduDoStatisVo>().sucess(reportEduDoStatisVo);
    }

}



