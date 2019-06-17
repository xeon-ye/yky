package com.deloitte.services.srpmp.project.budget.controller;

import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyInnBcooSaveVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.WordImportReqVo;
import com.deloitte.platform.api.srpmp.project.budget.SrpmsProjectBudgetApplyClient;
import com.deloitte.platform.api.srpmp.project.budget.vo.ext.BudgetApplyVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.util.UUIDUtil;
import com.deloitte.services.srpmp.project.budget.service.ISrpmsProjectBudgetApplyService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
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
import java.util.Map;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-19
 * @Description :   SrpmsProjectBudgetApply控制器实现类
 * @Modified :
 */
@Api(tags = "预算书（申请书）-创新工程操作接口", value = "预算书（申请书）-创新工程操作接口")
@Slf4j
@RestController
public class SrpmsProjectBudgetApplyController implements SrpmsProjectBudgetApplyClient {

    @Autowired
    public ISrpmsProjectBudgetApplyService srpmsProjectBudgetApplyService;


    @Override
    @ApiOperation(value = "保存项目预算申请书", notes = "保存项目预算申请书")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result save(@RequestBody @ApiParam(name="srpmsProjectBudgetApplyForm",value="新增SrpmsProjectBudgetApply的form表单",required=true) BudgetApplyVo vo) {
        log.info("form:",  vo.toString());
        return Result.success(srpmsProjectBudgetApplyService.saveOrUpdateBudgetApply(vo));
    }

    @ApiOperation(value = "提交项目预算申请书", notes = "提交项目预算申请书")
    @Override
    public Result<Map<String, Object>> submit(@Valid @RequestBody @ApiParam(name="BudgetApplyVo",value="提交SrpmsProjectBudgetApply的form表单",required=true) BudgetApplyVo vo
                                                , UserVo userVo
                                                , DeptVo deptVo) {
        String reqId = UUIDUtil.getRandom32PK();
        log.info("SrpmsProjectBudgetApplyController.submit, reqId:{}, req:{}", reqId, JSONObject.toJSONString(vo));
        srpmsProjectBudgetApplyService.submitBudgetApply(vo, userVo, deptVo);
        Result r = Result.success();
        log.info("SrpmsProjectBudgetApplyController.submit, reqId:{}, req:{}", reqId, JSONObject.toJSONString(r));
        return r;
    }

    @Override
    @ApiOperation(value = "获取SrpmsProjectBudgetApply", notes = "获取指定ID的SrpmsProjectBudgetApply信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SrpmsProjectBudgetApply的ID", required = true, dataType = "long")
    public Result<BudgetApplyVo> get(@PathVariable long id, UserVo user, DeptVo dept) {
        log.info("get with id:{}", id);

        JSONObject json = srpmsProjectBudgetApplyService.queryById(id, user, dept);
        return new Result<BudgetApplyVo>().sucess(json);
    }

    @Override
    @ApiOperation(value = "导出预算申请书", notes = "导出预算申请书")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "long")
    public Result export(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo) {

        String fileUrl = srpmsProjectBudgetApplyService.exportApplyWordFile(id, userVo, deptVo);

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            String downName = "创新工程项目预算申请书_" + DateFormatUtils.format(new Date(), "yyyyMMdd")+".docx";
            if (id != null && id.equals(0L)){
                downName = "创新工程项目预算申请书模板.docx";
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
    @ApiOperation(value = "导出预算申请书", notes = "导出预算申请书")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "long")
    public Result exportPdf(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo) {

        String fileUrl = srpmsProjectBudgetApplyService.exportApplyPdfFile(id, userVo, deptVo);

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            String downName = "创新工程项目预算申请书_" + DateFormatUtils.format(new Date(), "yyyyMMdd")+".pdf";
            if (id != null && id.equals(0L)){
                downName = "创新工程项目预算申请书模板.pdf";
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
        BudgetApplyVo vo = srpmsProjectBudgetApplyService.importWord(reqVo.getFileUrl());
        return new Result<BudgetApplyVo>().success(vo);
    }
}



