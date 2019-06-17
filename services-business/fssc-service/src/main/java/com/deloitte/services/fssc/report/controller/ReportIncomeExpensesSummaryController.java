package com.deloitte.services.fssc.report.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.report.param.ReportIncomeExpensesSummaryQueryForm;
import com.deloitte.platform.api.fssc.report.vo.ReportIncomeExpensesSummaryForm;
import com.deloitte.platform.api.fssc.report.vo.ReportIncomeExpensesSummaryVo;
import com.deloitte.platform.api.fssc.report.client.ReportIncomeExpensesSummaryClient;
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

import com.deloitte.services.fssc.report.service.IReportIncomeExpensesSummaryService;
import com.deloitte.services.fssc.report.entity.ReportIncomeExpensesSummary;


/**
 * @Author : jaws
 * @Date : Create in 2019-06-12
 * @Description :   ReportIncomeExpensesSummary控制器实现类
 * @Modified :
 */
@Api(tags = "部门收支总表报表操作接口")
@Slf4j
@RestController
@RequestMapping(value = "/report/income-expense-summary")
public class ReportIncomeExpensesSummaryController {

    @Autowired
    public IReportIncomeExpensesSummaryService  reportIncomeExpensesSummaryService;

    @Autowired
    private FsscCommonServices commonServices;


    @Autowired
    private IReportTotalQueryService totalQueryService;


    @PostMapping(value="/doSaveOrUpdate")
    @ApiOperation(value="草稿" ,notes="草稿")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ResponseBody
    @Transactional
    public Result doSaveOrUpdate(@Valid @RequestBody ReportIncomeExpensesSummaryForm reportIncomeExpensesSummaryForm){
       log.info("reportIncomeExpensesSummaryForm"+reportIncomeExpensesSummaryForm);
        return saveOrUpdate(reportIncomeExpensesSummaryForm);
    }

    public Result saveOrUpdate(ReportIncomeExpensesSummaryForm reportIncomeExpensesSummaryForm){
        ReportIncomeExpensesSummary reportIncomeExpensesSummary=new BeanUtils<ReportIncomeExpensesSummary>()
                .copyObj(reportIncomeExpensesSummaryForm,ReportIncomeExpensesSummary.class);
        DeptVo currentDept = commonServices.getCurrentDept();
        UserVo currentUser = commonServices.getCurrentUser();
        if (reportIncomeExpensesSummaryForm.getId() != null){
            ReportIncomeExpensesSummaryQueryForm queryForm = new ReportIncomeExpensesSummaryQueryForm();
            reportIncomeExpensesSummary.setUnitCode(currentDept.getDeptCode());
            reportIncomeExpensesSummary.setYear(reportIncomeExpensesSummaryForm.getYear());
            if(!"".equals(reportIncomeExpensesSummaryForm.getMonth())){
                queryForm.setMonth(reportIncomeExpensesSummaryForm.getMonth());
            }
            queryForm.setPeriodType(FsscEums.PERIOD_TYPE_MONTH.getValue());
            List<ReportIncomeExpensesSummary> reportList = reportIncomeExpensesSummaryService.selectList(queryForm);
            AssertUtils.asserts(CollectionUtils.isEmpty(reportList), FsscErrorType.DATA_EXISTS_ADD_FAIL);
            reportIncomeExpensesSummary.setUnitId(StringUtil.castTolong(currentDept.getId()));
            reportIncomeExpensesSummary.setUnitCode(currentDept.getCode());
            reportIncomeExpensesSummary.setCreateBy(StringUtil.castTolong(currentUser.getId()));
            reportIncomeExpensesSummary.setPeriodType(FsscEums.PERIOD_TYPE_ANNUAL.getValue());
            reportIncomeExpensesSummary.setMergeFlag(FsscEums.NO.getValue());
            reportIncomeExpensesSummary.setName(FsscTableNameEums.REPORT_INCOME_EXPENSES_SUMMARY.getValue());
            reportIncomeExpensesSummary.setStatus(FsscEums.NEW.getValue());
            reportIncomeExpensesSummaryService.save(reportIncomeExpensesSummary);
            ReportTotalQuery totalQuery = new ReportTotalQuery();
            totalQuery.setUnitId(Long.valueOf(currentDept.getDeptId()));
            totalQuery.setUnitCode(currentDept.getDeptCode());
            totalQuery.setCreateBy(Long.valueOf(currentUser.getId()));
            totalQuery.setMergeFlag(FsscEums.NO.getValue());
            totalQuery.setReportId(reportIncomeExpensesSummary.getId());
            totalQuery.setReportType(FsscTableNameEums.REPORT_INCOME_EXPENSES_SUMMARY.getValue());
            totalQuery.setYear(reportIncomeExpensesSummary.getYear());
            totalQuery.setMonth(reportIncomeExpensesSummary.getMonth());
            totalQuery.setPeriodType(FsscEums.PERIOD_TYPE_ANNUAL.getValue());
            totalQuery.setReportStatus(FsscEums.NEW.getValue());
            return Result.success(totalQueryService.save(totalQuery));
        }else{
            ReportTotalQuery totalQuery = totalQueryService.getByReport(reportIncomeExpensesSummaryForm.getId(),FsscTableNameEums.REPORT_INCOME_EXPENSES_SUMMARY.getValue());
            totalQuery.setUpdateBy(Long.valueOf(currentUser.getId()));
            totalQueryService.saveOrUpdate(totalQuery);
            return Result.success(reportIncomeExpensesSummaryService.saveOrUpdate(reportIncomeExpensesSummary));
        }
    }

    @PostMapping(value = "/submitReport")
    @ApiOperation(value = "提交流程", notes="提交流程")
    @Transactional
    @ResponseBody
    public Result submitReport(@Valid@RequestBody ReportIncomeExpensesSummaryForm reportIncomeExpensesSummaryForm){
        Result<ReportIncomeExpensesSummary>  resDate=saveOrUpdate(reportIncomeExpensesSummaryForm);
        ReportIncomeExpensesSummary reportIncomeExpensesSummary=resDate.getData();
        if(reportIncomeExpensesSummary!=null){
            reportIncomeExpensesSummary.setStatus(FsscEums.SUBMIT.getValue());
            reportIncomeExpensesSummaryService.updateById(reportIncomeExpensesSummary);
        }
        return  Result.success();
    }

    @GetMapping(value="/queryReport")
    @ApiOperation(value = "根据id查询", notes = "根据id查询")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ResponseBody
    public Result<ReportIncomeExpensesSummaryVo> queryReport(Long id){
        ReportIncomeExpensesSummary reportIncomeExpensesSummary=reportIncomeExpensesSummaryService.getById(id);
        AssertUtils.asserts(reportIncomeExpensesSummary != null, FsscErrorType.DOCUMENT_NOT_FIND);
        ReportIncomeExpensesSummaryVo reportIncomeExpensesSummaryVo=new BeanUtils<ReportIncomeExpensesSummaryVo>().copyObj(reportIncomeExpensesSummary,ReportIncomeExpensesSummaryVo.class);
        return new Result<ReportIncomeExpensesSummaryVo>().success(reportIncomeExpensesSummaryVo);
    }

}



