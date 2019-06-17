package com.deloitte.services.srpmp.project.mpr.controller;

import com.alibaba.fastjson.JSON;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.WordImportReqVo;
import com.deloitte.platform.api.srpmp.project.mpr.MprUnitEvaInfoClient;
import com.deloitte.platform.api.srpmp.project.mpr.param.MprUnitEvaInfoQueryForm;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprUnitEvaInfoForm;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprUnitEvaInfoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.project.mpr.service.IMprUnitEvaInfoService;
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
 * @Description :   MprUnitEvaInfo控制器实现类
 * @Modified :
 */
@Api(tags = "MprUnitEvaInfo操作接口")
@Slf4j
@RestController
public class MprUnitEvaInfoController implements MprUnitEvaInfoClient {

    @Autowired
    public IMprUnitEvaInfoService  mprUnitEvaInfoService;

    @Override
    @ApiOperation(value = "导出Word文档", notes = "导出中期绩效报告（附件九）")
    @ApiImplicitParam(paramType = "path", name = "id", value = "id", required = true, dataType = "long")
    public Result exportWord(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
        String fileUrl = mprUnitEvaInfoService.exportWord(id);

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            String downName = "中国医学科学医学与健康科技创新工程承担单位中期绩效考评自评报告.docx";
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
    @ApiOperation(value = "导入Word文档", notes = "导入中期绩效报告（附件九）")
    public Result<MprUnitEvaInfoVo> importWord(@Valid @RequestBody @ApiParam(name = "WordImportReqVo", value = "导入中期绩效报告", required = true)WordImportReqVo reqVo) {
        // TODO 导入
        log.info("this word file url is: " + reqVo.getFileUrl());
        MprUnitEvaInfoVo mprUnitEvaInfoVo = mprUnitEvaInfoService.importWord(reqVo.getFileUrl());
        return new Result<MprUnitEvaInfoVo>().sucess(mprUnitEvaInfoVo);
    }

    @Override
    @ApiOperation(value = "保存和更新", notes = "保存和更新附件九 ")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ApiParam(name="MprUnitEvaInfoForm",value="保存和更新MprUnitEvaInfoForm",required=true)
    public Result saveAndUpdate(@Valid @RequestBody MprUnitEvaInfoForm unitEvaInfoForm, UserVo user, DeptVo dept) {
        // TODO 保存和更新
        log.info(JSON.toJSONString(unitEvaInfoForm));
        Long id = mprUnitEvaInfoService.saveAndUpdate(unitEvaInfoForm, user, dept);
        return Result.success(id + "");
    }

    @Override
    @ApiOperation(value = "获取单个MprProjEvaInfo对象", notes = "根据projectNo获取MprProjEvaInfo")
    @ApiParam(name="MprUnitEvaInfoQueryForm",value="查询MprUnitEvaInfoQueryForm",required=true)
    public Result<MprUnitEvaInfoVo> getOne(@Valid @RequestBody MprUnitEvaInfoQueryForm unitEvaInfoQueryForm, UserVo user, DeptVo dept) {
        return new Result<MprUnitEvaInfoVo>().sucess(mprUnitEvaInfoService.getOne(unitEvaInfoQueryForm, user, dept));
    }
}



