package com.deloitte.services.srpmp.project.task.controller;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.task.SrpmsProjectTaskSchTeacherClient;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskSchTeacherForm;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskSchTeacherVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskSchStudentService;
import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskSchTeacherService;
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
import java.io.*;
import java.util.Date;


/**
 * @Author : Apeng
 * @Date : Create in 2019-03-14
 * @Description :   SrpmsProjectTaskSchTeacher控制器实现类
 * @Modified :
 */
@Api(tags = "任务书-校基科费-教师操作接口", value = "任务书-校基科费-教师操作接口")
@Slf4j
@RestController
public class SrpmsProjectTaskSchTeacherController implements SrpmsProjectTaskSchTeacherClient {

    @Autowired
    public ISrpmsProjectTaskSchTeacherService  srpmsProjectTaskService;

    @Autowired
    public ISrpmsProjectTaskSchStudentService taskSchStudentService;

    /**
     * 获取基科费任务书对应的申请书接口
     *
     * @param  id
     * @return
     */
    @Override
    @ApiOperation(value = "获取基科费任务书对应的申请书接口", notes = "获取任务书对应的申请书接口")
    public Result<SrpmsProjectTaskSchTeacherVo> get(@PathVariable long id) {
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
    public Result save(@RequestBody @ApiParam(name="SrpmsProjectTaskSchTeacherForm",value="新增SrpmsProjectTaskSchTeacher的form表单",required=true) SrpmsProjectTaskSchTeacherForm taskForm) {
        return Result.success(srpmsProjectTaskService.saveSrpmsProjectTask(taskForm, true));
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
    public Result submit(@Valid @RequestBody @ApiParam(name="SrpmsProjectTaskSchTeacherForm",value="新增SrpmsProjectTaskSchTeacher的form表单",required=true)
                                     SrpmsProjectTaskSchTeacherForm taskForm, UserVo userVo, DeptVo deptVo) {
        return Result.success(srpmsProjectTaskService.submitSrpmsProjectTask(taskForm, userVo, deptVo));
    }

    @Override
    @ApiOperation(value = "导出任务书", notes = "根据ID导出任务书")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "long")
    public Result export(@PathVariable long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo) {
        String fileUrl = taskSchStudentService.exportWordFile(id,"template_task_sch_teach",userVo,deptVo);

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            String downName = "校基科费教师项目任务书_" + DateFormatUtils.format(new Date(), "yyyyMMdd")+".docx";
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
    @ApiOperation(value = "导出任务书", notes = "根据ID导出任务书")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "long")
    public Result exportPdf(@PathVariable long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo) {

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            String fileUrl = taskSchStudentService.exportPdf(id,"template_task_sch_teach",userVo,deptVo);
            String downName = "校基科费教师项目任务书_" + DateFormatUtils.format(new Date(), "yyyyMMdd")+".pdf";
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
}



