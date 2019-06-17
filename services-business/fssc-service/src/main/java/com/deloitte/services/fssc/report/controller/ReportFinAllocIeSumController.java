package com.deloitte.services.fssc.report.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.report.param.ReportDeptExpenseSummaryQueryForm;
import com.deloitte.platform.api.fssc.report.param.ReportFinAllocIeSumQueryForm;
import com.deloitte.platform.api.fssc.report.vo.ReportFinAllocIeSumForm;
import com.deloitte.platform.api.fssc.report.vo.ReportFinAllocIeSumVo;
import com.deloitte.platform.api.fssc.report.client.ReportFinAllocIeSumClient;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.report.entity.ReportDeptExpenseSummary;
import com.deloitte.services.fssc.report.entity.ReportTotalQuery;
import com.deloitte.services.fssc.report.service.IReportTotalQueryService;
import com.deloitte.services.fssc.util.AssertUtils;
import com.deloitte.services.fssc.util.StringUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.fssc.report.service.IReportFinAllocIeSumService;
import com.deloitte.services.fssc.report.entity.ReportFinAllocIeSum;


/**
 * @Author : jaws
 * @Date : Create in 2019-06-14
 * @Description :   ReportFinAllocIeSum控制器实现类
 * @Modified :
 */
@Api(tags = "财政拨款收支总表操作接口")
@Slf4j
@RestController
@RequestMapping(value="/report/fin-allocle-sum")
public class ReportFinAllocIeSumController {

    @Autowired
    public IReportFinAllocIeSumService  reportFinAllocIeSumService;

    @Autowired
    private FsscCommonServices commonServices;

    @Autowired
    private IReportTotalQueryService totalQueryService;


    @PostMapping(value = "/doSaveOrUpdate")
    @ResponseBody
    @ApiOperation(value="草稿" ,notes="草稿")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @Transactional
    public  Result doSaveOrUpdate(@Valid@RequestBody ReportFinAllocIeSumForm reportDeptExpenseSummaryForm){
        log.info("reportDeptExpenseSummaryForm"+reportDeptExpenseSummaryForm);
        return  saveOrUpdate(reportDeptExpenseSummaryForm);
    }
    public Result saveOrUpdate(ReportFinAllocIeSumForm  reportFinAllocIeSumForm){
        ReportFinAllocIeSum reportFinAllocIeSum=new BeanUtils<ReportFinAllocIeSum>()
                .copyObj(reportFinAllocIeSumForm,ReportFinAllocIeSum.class);
        DeptVo currentDept = commonServices.getCurrentDept();
        UserVo currentUser = commonServices.getCurrentUser();
        if (reportFinAllocIeSumForm.getId() != null){
            ReportFinAllocIeSumQueryForm queryForm = new ReportFinAllocIeSumQueryForm();
            queryForm.setUnitCode(currentDept.getDeptCode());
            queryForm.setYear(reportFinAllocIeSumForm.getYear());
            if(!"".equals(reportFinAllocIeSumForm.getMonth())){
                queryForm.setMonth(reportFinAllocIeSumForm.getMonth());
            }
            queryForm.setPeriodType(FsscEums.PERIOD_TYPE_MONTH.getValue());
            List<ReportFinAllocIeSum> reportList = reportFinAllocIeSumService.selectList(queryForm);
            AssertUtils.asserts(CollectionUtils.isEmpty(reportList), FsscErrorType.DATA_EXISTS_ADD_FAIL);
            reportFinAllocIeSum.setUnitId(StringUtil.castTolong(currentDept.getId()));
            reportFinAllocIeSum.setUnitCode(currentDept.getCode());
            reportFinAllocIeSum.setCreateBy(StringUtil.castTolong(currentUser.getId()));
            reportFinAllocIeSum.setPeriodType(FsscEums.PERIOD_TYPE_ANNUAL.getValue());
            reportFinAllocIeSum.setMergeFlag(FsscEums.NO.getValue());
            reportFinAllocIeSum.setStatus(FsscEums.NEW.getValue());
            reportFinAllocIeSum.setName(FsscTableNameEums.REPORT_DEPT_EXPENSES_SUMMARY.getValue());
            reportFinAllocIeSumService.save(reportFinAllocIeSum);
            ReportTotalQuery totalQuery = new ReportTotalQuery();
            totalQuery.setUnitId(Long.valueOf(currentDept.getDeptId()));
            totalQuery.setUnitCode(currentDept.getDeptCode());
            totalQuery.setCreateBy(Long.valueOf(currentUser.getId()));
            totalQuery.setMergeFlag(FsscEums.NO.getValue());
            totalQuery.setReportId(reportFinAllocIeSum.getId());
            totalQuery.setReportType(FsscTableNameEums.REPORT_FIN_ALLOC_IE_SUM.getValue());
            totalQuery.setYear(reportFinAllocIeSum.getYear());
            totalQuery.setMonth(reportFinAllocIeSum.getMonth());
            totalQuery.setPeriodType(FsscEums.PERIOD_TYPE_ANNUAL.getValue());
            totalQuery.setReportStatus(FsscEums.NEW.getValue());
            return Result.success(totalQueryService.save(totalQuery));
        }else{
            ReportTotalQuery totalQuery = totalQueryService.getByReport(reportFinAllocIeSumForm.getId(),FsscTableNameEums.REPORT_FIN_ALLOC_IE_SUM.getValue());
            totalQuery.setUpdateBy(Long.valueOf(currentUser.getId()));
            totalQueryService.saveOrUpdate(totalQuery);
            return Result.success(reportFinAllocIeSumService.saveOrUpdate(reportFinAllocIeSum));
        }
    }

    @PostMapping(value="/submitReport")
    @ApiOperation(value="提交流程",notes = "财政拨款收支总表提交流程")
    @ResponseBody
    @Transactional
    public Result submitReport(@Valid@RequestBody ReportFinAllocIeSumForm reportFinAllocIeSumForm){
        Result<ReportFinAllocIeSum>  resDate=saveOrUpdate(reportFinAllocIeSumForm);
        ReportFinAllocIeSum reportFinAllocIeSum=resDate.getData();
        if(reportFinAllocIeSum!=null){
            reportFinAllocIeSum.setStatus(FsscEums.SUBMIT.getValue());
            reportFinAllocIeSumService.updateById(reportFinAllocIeSum);
        }
        return  Result.success();
    }

    @GetMapping(value="/queryReport")
    @ApiOperation(value = "根据id查询", notes = "根据id查询")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ResponseBody
    public Result<ReportFinAllocIeSumVo> queryReport(Long id){
        ReportFinAllocIeSum reportFinAllocIeSum=reportFinAllocIeSumService.getById(id);
        AssertUtils.asserts(reportFinAllocIeSum != null, FsscErrorType.DOCUMENT_NOT_FIND);
        ReportFinAllocIeSumVo reportFinAllocIeSumVo=new BeanUtils<ReportFinAllocIeSumVo>().copyObj(reportFinAllocIeSum,ReportFinAllocIeSumVo.class);
        return new Result<ReportFinAllocIeSumVo>().success(reportFinAllocIeSumVo);
    }

}



