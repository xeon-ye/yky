package com.deloitte.services.srpmp.project.mpr.controller;

import com.deloitte.platform.api.srpmp.project.base.vo.ext.WordImportReqVo;
import com.deloitte.platform.api.srpmp.project.mpr.MprProjEvaInfoClient;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprProjEvaInfoForm;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprProjEvaInfoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.outline.util.CommonUtil;
import com.deloitte.services.srpmp.project.mpr.service.IMprProjEvaInfoService;
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
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description :   MprProjEvaInfo控制器实现类
 * @Modified :
 */
@Api(tags = "MprProjEvaInfo操作接口")
@Slf4j
@RestController
public class MprProjEvaInfoController implements MprProjEvaInfoClient {

    @Autowired
    public IMprProjEvaInfoService  mprProjEvaInfoService;

    @Override
    @ApiOperation(value = "导出Word文档", notes = "导出中期绩效报告（附件六）")
    @ApiImplicitParam(paramType = "path", name = "projectId", value = "projectId", required = true, dataType = "long")
    public Result exportWord(@PathVariable Long projectId, HttpServletRequest request, HttpServletResponse response) {
        String fileUrl = mprProjEvaInfoService.exportWord(projectId);

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            String downName = "中国医学科学院医学与健康科技创新工程项目中期绩效考评自评报告.docx";
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
        CommonUtil.deleteFile(fileUrl);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "导入Word文档", notes = "导入中期绩效报告（附件六）")
    public Result<MprProjEvaInfoVo> importWord(@Valid @RequestBody @ApiParam(name = "WordImportReqVo", value = "导入中期绩效报告", required = true)WordImportReqVo reqVo) {
        MprProjEvaInfoVo mprProjEvaInfoVo = mprProjEvaInfoService.importWord(reqVo.getFileUrl());
        return new Result<MprProjEvaInfoVo>().sucess(mprProjEvaInfoVo);
    }

    @Override
    @ApiOperation(value = "保存和更新", notes = "保存和更新MprUnitEvaInfo ")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result saveAndUpdate(@Valid @RequestBody @ApiParam(name="MprProjEvaInfoForm",value="保存和更新MprProjEvaInfoForm",required=true) MprProjEvaInfoForm projEvaInfoForm) {
        // 保存和更新
        Long id = mprProjEvaInfoService.saveAndUpdate(projEvaInfoForm);
        return Result.success(id + "");
    }

    @Override
    @ApiOperation(value = "获取单个MprProjEvaInfo对象", notes = "根据projectNo获取MprProjEvaInfo")
    @ApiImplicitParam(paramType = "path", name = "projectId", value = "projectId", required = true, dataType = "long")
    public Result<MprProjEvaInfoVo> getOne(@PathVariable Long projectId) {
        return new Result<MprProjEvaInfoVo>().success(mprProjEvaInfoService.getOne(projectId));
    }
}



