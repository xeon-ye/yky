package com.deloitte.services.srpmp.project.budget.controller;

import com.deloitte.platform.api.fssc.performance.feign.PerformanceIndexFeignService;
import com.deloitte.platform.api.fssc.performance.feign.PerformanceIndexLibraryFeignService;
import com.deloitte.platform.api.fssc.performance.param.PerformanceIndexLibraryQueryForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformanceIndexLibraryVo;
import com.deloitte.platform.api.fssc.performance.vo.PerformanceIndexVo;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.BudgetTaskSubmitVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.WordImportReqVo;
import com.deloitte.platform.api.srpmp.project.budget.SrpmsProjectBudgetTaskClient;
import com.deloitte.platform.api.srpmp.project.budget.vo.ext.BudgetApplyVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.project.budget.service.ISrpmsProjectBudgetApplyService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;


/**
 * @Author : lixin
 * @Date : Create in 2019-02-19
 * @Description :   SrpmsProjectBudgetApply控制器实现类
 * @Modified :
 */
@Api(tags = "预算书（任务书）-创新工程操作接口", value = "预算书（任务书）-创新工程操作接口")
@Slf4j
@RestController
public class SrpmsProjectBudgetTaskController implements SrpmsProjectBudgetTaskClient {

    @Autowired
    public ISrpmsProjectBudgetApplyService budgetService;

    @Autowired
    private PerformanceIndexLibraryFeignService performanceIndexLibraryFeignService;

    @Autowired
    private PerformanceIndexFeignService performanceIndexFeignService;

    @Override
    @ApiOperation(value = "保存任务书对应的申请书", notes = "保存任务书对应的申请书")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result save(@RequestBody @ApiParam(name="srpmsProjectBudgetApplyForm",value="新增SrpmsProjectBudgetTask的form表单",required=true) BudgetApplyVo vo) {
        log.info("form:",  vo.toString());
        return Result.success(budgetService.saveOrUpdateBudgetApply(vo, true));
    }

    @ApiOperation(value = "提交任务对应的申请书", notes = "提交任务对应的申请书")
    @Override
    public Result submit(@Valid @RequestBody @ApiParam(name="BudgetApplyVo",value="提交SrpmsProjectBudgetTask的form表单",required=true) BudgetApplyVo vo, UserVo userVo, DeptVo deptVo) {
        budgetService.submitTask(vo, userVo, deptVo);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "获取任务书对应的预算书接口", notes = "获取任务书对应的预算书接口")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SrpmsProjectBudgetApply的ID", required = true, dataType = "long")
    public Result<BudgetTaskSubmitVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        return new Result<BudgetTaskSubmitVo>().sucess(budgetService.queryBudgetTaskById(id));
    }

    @Override
    @ApiOperation(value = "导出任务预算书", notes = "导出任务预算书")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "long")
    public Result export(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo) {
        String fileUrl = budgetService.exportTaskWordFile(id, userVo, deptVo);

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            String downName = "创新工程项目预算任务书_" + DateFormatUtils.format(new Date(), "yyyyMMdd")+".docx";
            if (id != null && id.equals(0L)){
                downName = "创新工程项目预算任务书模板.docx";
            }
            response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(downName, "UTF-8"));
            is = new FileInputStream(fileUrl);
            bis = new BufferedInputStream(is);
            os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (Exception e) {
            log.error("文件读取异常！", e);
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR, e.getMessage(), e.getCause());
        } finally {
            IOUtils.closeQuietly(os);
            IOUtils.closeQuietly(bis);
            IOUtils.closeQuietly(is);
        }
        return Result.success();
    }


    @Override
    @ApiOperation(value = "导出任务预算书", notes = "导出任务预算书")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "long")
    public Result exportPdf(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo) {
        String fileUrl = budgetService.exportTaskPdfFile(id, userVo, deptVo);

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            String downName = "创新工程项目预算任务书_" + DateFormatUtils.format(new Date(), "yyyyMMdd")+".pdf";
            if (id != null && id.equals(0L)){
                downName = "创新工程项目预算任务书模板.pdf";
            }
            response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(downName, "UTF-8"));
            is = new FileInputStream(fileUrl);
            bis = new BufferedInputStream(is);
            os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (Exception e) {
            log.error("文件读取异常！", e);
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR, e.getMessage(), e.getCause());
        } finally {
            IOUtils.closeQuietly(os);
            IOUtils.closeQuietly(bis);
            IOUtils.closeQuietly(is);
        }
        return Result.success();
    }

    @ApiOperation(value = "导入项目预算书", notes = "导入项目预算书")
    @Override
    public Result<BudgetApplyVo> importWord(@Valid @RequestBody @ApiParam(name = "WordImportReqVo", value = "导入项目预算书", required = true) WordImportReqVo reqVo) {
        BudgetApplyVo vo = budgetService.importWord(reqVo.getFileUrl());
        return new Result<BudgetApplyVo>().success(vo);
    }

    @Override
    @ApiParam(name = "PerformanceIndexLibraryQueryForm", value = "查询指标库Form", required = true )
    @ApiOperation(value = "查询指标库", notes = "查询指标库")
    public Result<List<PerformanceIndexLibraryVo>> listPerformanceIndexLibrary(@Valid @RequestBody PerformanceIndexLibraryQueryForm queryForm) {
        Result<List<PerformanceIndexLibraryVo>> result = performanceIndexLibraryFeignService.list(queryForm);
        return performanceIndexLibraryFeignService.list(queryForm);
    }

    @Override
    @ApiOperation(value = "根据指标库查询一、二、三级指标的数据型结构数据", notes = "根据指标库查询一、二、三级指标的数据型结构数据")
    @ApiImplicitParam(paramType = "path", name = "libraryId", value = "libraryId", required = true, dataType = "long")
    public Result<List<PerformanceIndexVo>> searchPerformanceIndex(@PathVariable(value="libraryId")  Long libraryId) {
        return performanceIndexFeignService.search(libraryId);
    }
}



