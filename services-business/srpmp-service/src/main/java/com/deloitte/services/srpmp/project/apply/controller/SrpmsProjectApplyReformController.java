package com.deloitte.services.srpmp.project.apply.controller;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.SrpmsProjectApplyReformClient;
import com.deloitte.platform.api.srpmp.project.apply.vo.SrpmsProjectApplyReformForm;
import com.deloitte.platform.api.srpmp.project.apply.vo.SrpmsProjectApplyReformVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.WordImportReqVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.project.apply.service.ISrpmsProjectApplyReformService;
import com.google.common.collect.ImmutableBiMap;
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
 * @Author : lixin
 * @Date : Create in 2019-02-25
 * @Description :   SrpmsProjectApplyReformController
 * @Modified :
 */
@Api(tags = "科技体制改革项目操作接口")
@Slf4j
@RestController
public class SrpmsProjectApplyReformController implements SrpmsProjectApplyReformClient{

    @Autowired
    private ISrpmsProjectApplyReformService  applyReformService;

    @Override
    @ApiOperation(value = "保存科技体制改革", notes = "保存科技体制改革")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result save(@RequestBody @ApiParam(name = "SrpmsProjectApplyReformController",required = true) SrpmsProjectApplyReformForm vo, DeptVo deptVo) {
        Long projectId =applyReformService.saveAndUpdatReform(vo, deptVo);
        return Result.success(projectId+"");
    }

    @Override
    @ApiOperation(value = "提交科技体制改革", notes = "提交科技体制改革")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result submit(@RequestBody @ApiParam(name = "SrpmsProjectApplyReformController",required = true) SrpmsProjectApplyReformForm vo, UserVo userVo, DeptVo deptVo) {
        applyReformService.submitApply(vo, userVo, deptVo);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "根据ID查询", notes = "根据ID查询")
    @ApiImplicitParam(paramType = "path", name = "id", value = "id", required = true, dataType = "long")
    public Result<SrpmsProjectApplyReformVo> get(@PathVariable long id, UserVo user, DeptVo dept) {
        return new Result<SrpmsProjectApplyReformVo>().sucess(applyReformService.queryApplyVoById(id, user, dept));
    }

    @Override
    @ApiOperation(value = "删除SrpmsProjectApplyReform", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SrpmsProjectApplyReformID", required = true, dataType = "long")
    public Result delete(@PathVariable  long id) {
        applyReformService.deleteAcademyById(id);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "导出申请书",notes = "根据ID导出申请书")
    @ApiImplicitParam(paramType = "path",name = "id",value = "id",required = true,dataType = "long")
    public Result export(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo) {
        String fileUrl = applyReformService.exportWordFile(id,userVo,deptVo);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;

        try {
            String downName = "科技体制改革项目申请书_" + DateFormatUtils.format(new Date(), "yyyyMMdd")+".docx";
            if (id != null && id.equals(0L)) {
                downName = "科技体制改革项目申请书模板.docx";
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
    @ApiOperation(value = "导出申请书",notes = "根据ID导出申请书")
    @ApiImplicitParam(paramType = "path",name = "id",value = "id",required = true,dataType = "long")
    public Result exportPdf(@PathVariable  Long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            String fileUrl = applyReformService.exportPdfFile(id,userVo,deptVo);
            String downName = "科技体制改革项目申请书_" + DateFormatUtils.format(new Date(), "yyyyMMdd")+".pdf";
            if (id != null && id.equals(0L)) {
                downName = "科技体制改革项目申请书模板.pdf";
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
    @ApiOperation(value = "导入项目申请书", notes = "导入项目申请书")
    public Result<SrpmsProjectApplyReformVo> importWord(@Valid @RequestBody  @ApiParam(name = "WordImportReqVo", value = "导入申请书", required = true)  WordImportReqVo importReqVo) {
        SrpmsProjectApplyReformVo vo = applyReformService.importWord(importReqVo.getFileUrl());
        return new Result<SrpmsProjectApplyReformVo>().success(vo);
    }
}



