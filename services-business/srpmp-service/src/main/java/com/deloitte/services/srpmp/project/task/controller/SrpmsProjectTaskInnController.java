package com.deloitte.services.srpmp.project.task.controller;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyInnPreSubmitVo;
import com.deloitte.platform.api.srpmp.project.base.SrpmsProjectTaskInnClient;
import com.deloitte.platform.api.srpmp.project.task.vo.ext.SrpmsProjectTaskInnExtVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.WordImportReqVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskInnService;

import java.io.*;
import java.util.Date;


/**
 * @Author : pengchao
 * @Date : Create in 2019-03-11
 * @Description :   SrpmsProjectTaskInn控制器实现类
 * @Modified :
 */
@Api(tags = "任务书-创新工程操作接口", value = "任务书-创新工程操作接口")
@Slf4j
@RestController
public class SrpmsProjectTaskInnController implements SrpmsProjectTaskInnClient {

    @Autowired
    public ISrpmsProjectTaskInnService  taskInnService;

    /**
     *  POST---保存
     * @param vo
     * @return
     */
    @Override
    public Result save(@Valid @RequestBody @ApiParam(name="srpmsProjectForm",value="修改SrpmsProject的form表单",required=true) SrpmsProjectTaskInnExtVo vo, UserVo user) {

        taskInnService.saveOrUpdate(vo, user);
        return Result.success();
    }

    @Override
    public Result submit(@Valid @RequestBody @ApiParam(name="srpmsProjectForm",value="修改SrpmsProject的form表单",required=true) SrpmsProjectTaskInnExtVo vo, UserVo user, DeptVo deptVo) {
        taskInnService.submit(vo, user, deptVo);
        return Result.success();
    }

    @Override
    public Result<SrpmsProjectTaskInnExtVo> get(@PathVariable long id) {
        return Result.success(taskInnService.queryById(id));
    }

    @Override
    public Result export(long id, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public Result<SrpmsProjectApplyInnPreSubmitVo> importWord(WordImportReqVo reqVo) {
        return null;
    }

    @Override
    @ApiOperation(value = "导入先导专项任务书", notes = "导入先导专项任务书")
    @ApiParam(name = "WordImportReqVo", value = "导入申请书", required = true)
    public Result<SrpmsProjectTaskInnExtVo> importTaskWord(@Valid @RequestBody WordImportReqVo reqVo) {
        return Result.success(taskInnService.importTaskWord(reqVo));
    }


    @Override
    @ApiOperation(value = "导入重大协同任务书", notes = "导入重大协同任务书")
    @ApiParam(name = "WordImportReqVo", value = "导入重大协同任务书", required = true)
    public Result<SrpmsProjectTaskInnExtVo> importBcooTaskWord(@Valid @RequestBody WordImportReqVo reqVo) {
        return new Result<SrpmsProjectTaskInnExtVo>().success(taskInnService.importBcooWord(reqVo.getFileUrl()));
    }


    @Override
    @ApiOperation(value = "导入协同创新任务书", notes = "导入协同创新任务书")
    @ApiParam(name = "WordImportReqVo", value = "导入协同创新任务书", required = true)
    public Result<SrpmsProjectTaskInnExtVo> importCooTaskWord(@Valid @RequestBody WordImportReqVo reqVo) {
        return new Result<SrpmsProjectTaskInnExtVo>().success(taskInnService.importCooWord(reqVo.getFileUrl()));
    }


    @Override
    @ApiOperation(value = "导出先导专项任务书", notes = "根据ID导入先导专项任务书")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "long")
    public Result exportTaskWord(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo) {
        String fileUrl = taskInnService.exportTaskWord(id, userVo, deptVo);

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            String downName = "先导专项任务书_" + DateFormatUtils.format(new Date(), "yyyyMMdd")+".docx";
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
    @ApiOperation(value = "导出重大协同任务书", notes = "根据ID导出重大协同任务书")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "long")
    public Result exportBcoo(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo) {
        String fileUrl = taskInnService.exportBcooTaskWord(id, userVo, deptVo);

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            String downName = "重大协同任务书_" + DateFormatUtils.format(new Date(), "yyyyMMdd")+".docx";
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
    @ApiOperation(value = "导出协同创新任务书", notes = "根据ID导出协同创新任务书")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "long")
    public Result exportCoo(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo) {
        String fileUrl = taskInnService.exportCooTaskWord(id, userVo, deptVo);

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            String downName = "协同创新任务书_" + DateFormatUtils.format(new Date(), "yyyyMMdd")+".docx";
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
    @ApiOperation(value = "导出先导专项任务书", notes = "根据ID导入先导专项任务书")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "long")
    public Result exportTaskWordPdf(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            String fileUrl = taskInnService.exportPdfTaskPre(id, userVo, deptVo);
            String downName = "先导专项任务书_" + DateFormatUtils.format(new Date(), "yyyyMMdd")+".pdf";
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
    @ApiOperation(value = "导出重大协同任务书", notes = "根据ID导出重大协同任务书")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "long")
    public Result exportBcooPdf(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo) {

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            String fileUrl = taskInnService.exportPdfTaskBcoo(id, userVo, deptVo);
            String downName = "重大协同任务书_" + DateFormatUtils.format(new Date(), "yyyyMMdd")+".pdf";
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
    @ApiOperation(value = "导出协同创新任务书", notes = "根据ID导出协同创新任务书")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "long")
    public Result exportCooPdf(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo) {

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            String fileUrl = taskInnService.exportPdfTaskCoo(id, userVo, deptVo);
            String downName = "协同创新任务书_" + DateFormatUtils.format(new Date(), "yyyyMMdd")+".pdf";
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



