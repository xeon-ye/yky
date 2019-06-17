package com.deloitte.services.srpmp.project.apply.controller;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.SrpmsProjectApplyInnPreClient;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyInnPreSaveVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyInnPreSubmitVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.WordImportReqVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.exception.ServiceException;
import com.deloitte.services.srpmp.common.enums.VoucherTypeEnums;
import com.deloitte.services.srpmp.project.apply.service.ISrpmsProjectApplyInnPreService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectFlowService;
import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author : lixin
 * @Date : Create in 2019-02-19
 * @Description :   SrpmsProjectApplyInnPre控制器实现类
 * @Modified :
 */
@Api(tags = "申请书-创新工程-先导专项操作接口", value = "申请书-创新工程-先导专项操作接口")
@Slf4j
@RestController
public class SrpmsProjectApplyInnPreController implements SrpmsProjectApplyInnPreClient {

    @Autowired
    public ISrpmsProjectApplyInnPreService srpmsProjectApplyInnPreService;



    /**
     * 保存项目申请书
     *
     * @param vo
     * @return 项目ID
     */
    @ApiOperation(value = "保存项目申请书", notes = "保存项目申请书")
    @Override
    public Result save(@RequestBody @ApiParam(name = "SrpmsProjectApplyInnPreSaveVo", value = "保存申请书", required = true) SrpmsProjectApplyInnPreSubmitVo vo,  DeptVo deptVo) {
        log.info("SrpmsProjectApplyInnPreController.save req:", vo.toString());
        //保存只校验项目名称
        if (vo == null || StringUtils.isBlank(vo.getProjectName())) {
            return Result.fail(PlatformErrorType.ARGUMENT_NOT_VALID);
        }
        Long projectId = srpmsProjectApplyInnPreService.saveOrUpdateApplyInnPre(vo, deptVo);
        return Result.success(projectId + "");
    }

    @ApiOperation(value = "提交项目申请书", notes = "提交项目申请书")
    @Override
    public Result<Map<String, Object>> submit(@Valid @RequestBody @ApiParam(name = "SrpmsProjectApplyInnPreSaveVo", value = "提交申请书", required = true) SrpmsProjectApplyInnPreSubmitVo vo,
                                              UserVo userVo,
                                              DeptVo deptVo) {
        log.info("SrpmsProjectApplyInnPreController.submit req:", vo.toString());
        srpmsProjectApplyInnPreService.submitApply(vo, userVo, deptVo);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "获取SrpmsProjectApplyInnPre", notes = "获取指定ID的SrpmsProjectApplyInnPre信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SrpmsProjectApplyInnPre的ID", required = true, dataType = "long")
    public Result<SrpmsProjectApplyInnPreSubmitVo> get(@PathVariable long id, UserVo user, DeptVo dept) {
        log.info("get with id:{}", id);
        return new Result<SrpmsProjectApplyInnPreSubmitVo>().sucess(srpmsProjectApplyInnPreService.queryApplyVoById(id, user, dept));
    }

    @Override
    @ApiOperation(value = "导出申请书", notes = "根据ID导出申请书")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "long")
    public Result export(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo) {
        String fileUrl = srpmsProjectApplyInnPreService.exportWordFile(id, userVo, deptVo);

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            String downName = "先导专项任务申报书_" + DateFormatUtils.format(new Date(), "yyyyMMdd")+".docx";
            if (id != null && id.equals(0L)){
                downName = "先导专项任务申报书模板.docx";
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
            if (os != null) {
                try {
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    log.error("文件流关闭异常:", e);
                    throw new BaseException(PlatformErrorType.SYSTEM_ERROR, e.getMessage());
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    log.error("文件流关闭异常:", e);
                    throw new BaseException(PlatformErrorType.SYSTEM_ERROR, e.getMessage());
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    log.error("文件流关闭异常:", e);
                    throw new BaseException(PlatformErrorType.SYSTEM_ERROR, e.getMessage());
                }
            }
        }
        return Result.success();
    }

    @Override
    @ApiOperation(value = "导出申请书PDF", notes = "根据ID导出申请书PDF")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "long")
    public Result exportPdf(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            String fileUrl = srpmsProjectApplyInnPreService.exportPdfFile(id, userVo, deptVo);
            String downName = "先导专项任务申报书_" + DateFormatUtils.format(new Date(), "yyyyMMdd")+".pdf";
            if (id != null && id.equals(0L)){
                downName = "先导专项任务申报书模板.pdf";
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


    @ApiOperation(value = "导入项目申请书", notes = "导入项目申请书")
    @Override
    public Result<SrpmsProjectApplyInnPreSubmitVo> importWord(@Valid @RequestBody @ApiParam(name = "WordImportReqVo", value = "导入申请书", required = true) WordImportReqVo reqVo) {
        SrpmsProjectApplyInnPreSubmitVo vo = srpmsProjectApplyInnPreService.importWord(reqVo.getFileUrl());
        return new Result<SrpmsProjectApplyInnPreSubmitVo>().success(vo);
    }
}


