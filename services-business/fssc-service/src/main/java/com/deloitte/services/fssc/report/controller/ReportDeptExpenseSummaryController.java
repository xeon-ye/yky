package com.deloitte.services.fssc.report.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.report.param.ReportDeptExpenseSummaryQueryForm;
import com.deloitte.platform.api.fssc.report.vo.ReportDeptExpenseSummaryForm;
import com.deloitte.platform.api.fssc.report.vo.ReportDeptExpenseSummaryVo;
import com.deloitte.platform.api.fssc.report.client.ReportDeptExpenseSummaryClient;
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

import com.deloitte.services.fssc.report.service.IReportDeptExpenseSummaryService;
import com.deloitte.services.fssc.report.entity.ReportDeptExpenseSummary;


/**
 * @Author : jaws
 * @Date : Create in 2019-06-12
 * @Description :   ReportDeptExpenseSummary控制器实现类
 * @Modified :
 */
@Api(tags = "部门支出总表操作接口")
@Slf4j
@RestController
@RequestMapping(value="/report/dept-expense-summary")
public class ReportDeptExpenseSummaryController{

    @Autowired
    public IReportDeptExpenseSummaryService  reportDeptExpenseSummaryService;

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
    public  Result doSaveOrUpdate(@Valid@RequestBody ReportDeptExpenseSummaryForm reportDeptExpenseSummaryForm){
       log.info("reportDeptExpenseSummaryForm"+reportDeptExpenseSummaryForm);
       return  saveOrUpdate(reportDeptExpenseSummaryForm);
   }
   public Result saveOrUpdate(ReportDeptExpenseSummaryForm  reportDeptExpenseSummaryForm){
       ReportDeptExpenseSummary reportDeptExpenseSummary=new BeanUtils<ReportDeptExpenseSummary>()
               .copyObj(reportDeptExpenseSummaryForm,ReportDeptExpenseSummary.class);
       DeptVo currentDept = commonServices.getCurrentDept();
       UserVo currentUser = commonServices.getCurrentUser();
       if (reportDeptExpenseSummaryForm.getId() != null){
           ReportDeptExpenseSummaryQueryForm queryForm = new ReportDeptExpenseSummaryQueryForm();
           queryForm.setUnitCode(currentDept.getDeptCode());
           queryForm.setYear(reportDeptExpenseSummaryForm.getYear());
           if(!"".equals(reportDeptExpenseSummaryForm.getMonth())){
               queryForm.setMonth(reportDeptExpenseSummaryForm.getMonth());
           }
           queryForm.setPeriodType(FsscEums.PERIOD_TYPE_MONTH.getValue());
           List<ReportDeptExpenseSummary> reportList = reportDeptExpenseSummaryService.selectList(queryForm);
           AssertUtils.asserts(CollectionUtils.isEmpty(reportList), FsscErrorType.DATA_EXISTS_ADD_FAIL);
           reportDeptExpenseSummary.setUnitId(StringUtil.castTolong(currentDept.getId()));
           reportDeptExpenseSummary.setUnitCode(currentDept.getCode());
           reportDeptExpenseSummary.setCreateBy(StringUtil.castTolong(currentUser.getId()));
           reportDeptExpenseSummary.setPeriodType(FsscEums.PERIOD_TYPE_ANNUAL.getValue());
           reportDeptExpenseSummary.setMergeFlag(FsscEums.NO.getValue());
           reportDeptExpenseSummary.setStatus(FsscEums.NEW.getValue());
           reportDeptExpenseSummary.setName(FsscTableNameEums.REPORT_DEPT_EXPENSES_SUMMARY.getValue());
           reportDeptExpenseSummaryService.save(reportDeptExpenseSummary);
           ReportTotalQuery totalQuery = new ReportTotalQuery();
           totalQuery.setUnitId(Long.valueOf(currentDept.getDeptId()));
           totalQuery.setUnitCode(currentDept.getDeptCode());
           totalQuery.setCreateBy(Long.valueOf(currentUser.getId()));
           totalQuery.setMergeFlag(FsscEums.NO.getValue());
           totalQuery.setReportId(reportDeptExpenseSummary.getId());
           totalQuery.setReportType(FsscTableNameEums.REPORT_DEPT_EXPENSES_SUMMARY.getValue());
           totalQuery.setYear(reportDeptExpenseSummaryForm.getYear());
           totalQuery.setMonth(reportDeptExpenseSummaryForm.getMonth());
           totalQuery.setPeriodType(FsscEums.PERIOD_TYPE_ANNUAL.getValue());
           totalQuery.setReportStatus(FsscEums.NEW.getValue());
           return Result.success(totalQueryService.save(totalQuery));
       }else{
           ReportTotalQuery totalQuery = totalQueryService.getByReport(reportDeptExpenseSummaryForm.getId(),FsscTableNameEums.REPORT_DEPT_EXPENSES_SUMMARY.getValue());
           totalQuery.setUpdateBy(Long.valueOf(currentUser.getId()));
           totalQueryService.saveOrUpdate(totalQuery);
           return Result.success(reportDeptExpenseSummaryService.saveOrUpdate(reportDeptExpenseSummary));
       }

   }

   @PostMapping(value="/submitReport")
   @ApiOperation(value="提交流程",notes = "部门支出总表提交流程")
   @ResponseBody
   @Transactional
    public Result submitReport(@Valid@RequestBody ReportDeptExpenseSummaryForm reportDeptExpenseSummaryForm){
       Result<ReportDeptExpenseSummary>  resDate=saveOrUpdate(reportDeptExpenseSummaryForm);
       ReportDeptExpenseSummary reportDeptExpenseSummary=resDate.getData();
       if(reportDeptExpenseSummary!=null){
           reportDeptExpenseSummary.setStatus(FsscEums.SUBMIT.getValue());
           reportDeptExpenseSummaryService.updateById(reportDeptExpenseSummary);
       }
       return  Result.success();
   }

    @GetMapping(value="/queryReport")
    @ApiOperation(value = "根据id查询", notes = "根据id查询")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ResponseBody
    public Result<ReportDeptExpenseSummaryVo> queryReport(Long id){
        ReportDeptExpenseSummary reportIncomeExpensesSummary=reportDeptExpenseSummaryService.getById(id);
        AssertUtils.asserts(reportIncomeExpensesSummary != null, FsscErrorType.DOCUMENT_NOT_FIND);
        ReportDeptExpenseSummaryVo reportIncomeExpensesSummaryVo=new BeanUtils<ReportDeptExpenseSummaryVo>().copyObj(reportIncomeExpensesSummary,ReportDeptExpenseSummaryVo.class);
        return new Result<ReportDeptExpenseSummaryVo>().success(reportIncomeExpensesSummaryVo);
    }


}



