package com.deloitte.services.srpmp.project.task.controller;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.WordImportReqVo;
import com.deloitte.platform.api.srpmp.project.task.SrpmsProjectTaskAcademyClient;
import com.deloitte.platform.api.srpmp.project.task.SrpmsProjectTaskReformClient;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskAcademyForm;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskAcademyVo;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskReformForm;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskReformVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.project.task.entity.SrpmsProjectTaskReform;
import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskAcademyService;
import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskReformService;
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


/**
 * @Author : Apeng
 * @Date : Create in 2019-03-13
 * @Description :   SrpmsProjectTaskAcademy控制器实现类
 * @Modified :
 */
@Api(tags = "任务书-科技体制改革操作接口", value = "任务书-科技体制改革操作接口")
@Slf4j
@RestController
public class SrpmsProjectTaskReformController implements SrpmsProjectTaskReformClient {

    @Autowired
    public ISrpmsProjectTaskReformService srpmsProjectTaskService;

    /**
     * 获取基科费任务书对应的申请书接口
     *
     * @param  id
     * @return
     */
    @Override
    @ApiOperation(value = "获取基科费任务书对应的申请书接口", notes = "获取任务书对应的申请书接口")
    public Result<SrpmsProjectTaskAcademyVo> get(@PathVariable long id) {
        return Result.success(srpmsProjectTaskService.queryById(id));
    }

    /**
     * 基科费任务书保存接口
     *
     * @param taskForm
     * @return
     */
    @Override
    @ApiOperation(value = "保存基科费任务书接口", notes = "保存基科费任务书")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result save(@RequestBody @ApiParam(name="SrpmsProjectTaskReformForm",value="新增SrpmsProjectTaskReform的form表单",required=true) SrpmsProjectTaskReformForm taskForm) {
        return srpmsProjectTaskService.saveSrpmsProjectTask(taskForm, true);
    }

    /**
     * 基科费任务书提交接口
     *
     * @param taskForm
     * @return
     */
    @Override
    @ApiOperation(value = "提交基科费任务书接口", notes = "提交基科费任务书")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result submit(@Valid @RequestBody @ApiParam(name="SrpmsProjectTaskReformForm",value="新增SrpmsProjectTaskReform的form表单",required=true) SrpmsProjectTaskReformForm taskForm, UserVo userVo, DeptVo deptVo) {
        return srpmsProjectTaskService.submitSrpmsProjectTask(taskForm, userVo, deptVo);
    }

    /**
     * 院基科任务书导出word文档接口 tanwx
     * @param id
     * @param request
     * @param response
     * @param userVo
     * @param deptVo
     * @return
     */
    @Override
    @ApiOperation(value = "导出任务书",notes = "根据ID导出任务书")
    @ApiImplicitParam(paramType = "path",name = "id",value = "id",required = true,dataType = "long")
    public Result export(@PathVariable(value = "id") Long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo) {
        String fileUrl = srpmsProjectTaskService.exportWordFile(id, userVo, deptVo);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;

        try {
            String downName = "医科科技体制改革项目任务书_" + DateFormatUtils.format(new Date(), "yyyyMMdd")+".docx";
            if (id != null && id.equals(0L)) {
                downName = "医科科技体制改革项目任务书模板.docx";
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
    /**
     * 院基科任务书导出word文档接口 tanwx
     * @param id
     * @param request
     * @param response
     * @param userVo
     * @param deptVo
     * @return
     */

    @Override
    @ApiOperation(value = "导出任务书",notes = "根据ID导出任务书")
    @ApiImplicitParam(paramType = "path",name = "id",value = "id",required = true,dataType = "long")
    public Result exportPdf(@PathVariable(value = "id") Long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;

        try {
            String fileUrl = srpmsProjectTaskService.exportPdf(id, userVo, deptVo);
            String downName = "医科科技体制改革项目任务书_" + DateFormatUtils.format(new Date(), "yyyyMMdd")+".pdf";
            if (id != null && id.equals(0L)) {
                downName = "医科科技体制改革项目任务书模板.pdf";
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
    @ApiOperation(value = "导入任务书",notes = "导入任务书")
    public Result<SrpmsProjectTaskReformVo> importWord(@Valid @RequestBody @ApiParam(name = "导入任务书", value = "WordImportReqVo", required = true) WordImportReqVo importReqVo) {
        SrpmsProjectTaskReformVo vo = srpmsProjectTaskService.importWord(importReqVo.getFileUrl());
        return new Result<SrpmsProjectTaskReformVo>().sucess(vo);
    }
}



