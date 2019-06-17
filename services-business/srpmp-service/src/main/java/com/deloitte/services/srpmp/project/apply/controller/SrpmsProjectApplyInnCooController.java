package com.deloitte.services.srpmp.project.apply.controller;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.SrpmsProjectApplyInnCooClient;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyInnBcooSaveVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyInnCooSaveVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.WordImportReqVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.project.apply.service.ISrpmsProjectApplyInnCooService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.Date;

/**
 * @Author : pengchao
 * @Date : Create in 2019-03-04
 * @Description :   SrpmsProjectApplyInnCoo控制器实现类
 * @Modified :
 */
@Api(tags = "申请书-创新工程-协同创新操作接口", value = "申请书-创新工程-协同创新操作接口")
@Slf4j
@RestController
public class SrpmsProjectApplyInnCooController implements SrpmsProjectApplyInnCooClient {

    @Autowired
    public ISrpmsProjectApplyInnCooService srpmsProjectApplyInnCooService;

    @Override
    @ApiOperation(value = "保存重大创新", notes = "保存重大创新")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result save(@Valid @RequestBody @ApiParam(name="srpmsProjectApplyInnBcooForm",value="重大创新form表单",required=true) SrpmsProjectApplyInnCooSaveVo pageData,  DeptVo deptVo) {
        log.info("form:",  pageData.toString());

        long id = srpmsProjectApplyInnCooService.saveOrUpdate(pageData, deptVo);
        return Result.success(id + "");
    }

    @Override
    @ApiOperation(value = "提交重大创新", notes = "提交重大创新")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result submit(@Valid @RequestBody @ApiParam(name="srpmsProjectApplyInnBcooForm",value="重大创新form表单",required=true) SrpmsProjectApplyInnCooSaveVo pageData,UserVo user,  DeptVo deptVo) {
        log.info("form:",  pageData.toString());

        srpmsProjectApplyInnCooService.submit(pageData, user, deptVo);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "根据ID获取重大创新项目信息", notes = "根据ID获取重大创新项目信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "重大创新项目ID", required = true, dataType = "long")
    public Result<SrpmsProjectApplyInnCooSaveVo> get(@PathVariable long id, UserVo user, DeptVo dept) {
        log.info("get with id : {}", id);

        return new Result<SrpmsProjectApplyInnCooSaveVo>().sucess(srpmsProjectApplyInnCooService.get(id,user,dept));
    }


    @Override
    @ApiOperation(value = "导出申请书", notes = "根据ID导出申请书")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "long")
    public Result export(@PathVariable long id, HttpServletRequest request, HttpServletResponse response, UserVo user, DeptVo dept) {
        String fileUrl = srpmsProjectApplyInnCooService.exportWordFile(id,user,dept);

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            String downName = "协同创新申报书_" + DateFormatUtils.format(new Date(), "yyyyMMdd")+".docx";
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
    @ApiOperation(value = "导出申请书", notes = "根据ID导出申请书")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "long")
    public Result exportPdf(@PathVariable long id, HttpServletRequest request, HttpServletResponse response, UserVo user, DeptVo dept) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            String fileUrl = srpmsProjectApplyInnCooService.exportPdfFile(id,user,dept);
            String downName = "协同创新申报书_" + DateFormatUtils.format(new Date(), "yyyyMMdd")+".pdf";
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

    @ApiOperation(value = "导入项目申请书", notes = "导入项目申请书")
    @Override
    public Result<SrpmsProjectApplyInnCooSaveVo> importWord(@Valid @RequestBody @ApiParam(name = "WordImportReqVo", value = "导入申请书", required = true) WordImportReqVo reqVo) {
        SrpmsProjectApplyInnCooSaveVo vo = srpmsProjectApplyInnCooService.importWord(reqVo.getFileUrl());
        return new Result<SrpmsProjectApplyInnCooSaveVo>().success(vo);
    }
}



