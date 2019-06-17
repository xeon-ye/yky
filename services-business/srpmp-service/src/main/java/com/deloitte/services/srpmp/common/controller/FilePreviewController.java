package com.deloitte.services.srpmp.common.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.isump.feign.DeptFeignService;
import com.deloitte.platform.api.isump.feign.UserFeignService;
import com.deloitte.platform.api.isump.param.DeptQueryForm;
import com.deloitte.platform.api.isump.param.UserQueryForm;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.WordImportReqVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.common.service.IFilePreviewService;
import com.deloitte.services.srpmp.common.util.WordConvertUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.deloitte.platform.common.core.exception.PlatformErrorType.FILE_PREVIEW_COVERT_FAIL;

@Api(tags = "文件下载及预览通用接口", value = "申请书-创新工程-文件下载及预览通用接口")
@Slf4j
@RequestMapping("/srpmp")
@RestController
public class FilePreviewController {

    @Autowired
    private IFilePreviewService filePreviewService;

    /**
     * 文件转化pdf 预览
     * @param reqVo
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "文件预览", notes = "文件预览")
    @PostMapping(path = "/file/preview")
    public Result preview(@RequestBody WordImportReqVo reqVo){
        String preview = filePreviewService.preview(reqVo.getFileUrl());
        if (StringUtils.isBlank(preview)){
            return Result.fail(FILE_PREVIEW_COVERT_FAIL,"文件预览转换失败");
        }
        return Result.success(preview);
    }

    @ApiOperation(value = "下载文件", notes = "下载文件")
    @GetMapping(path = "/file/download")
    public Result exportPdf(@RequestParam String fileUrl,HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        File  soureFile = null;
        try {
            String sourePath = WordConvertUtil.downloadHttpUrl(fileUrl);
            if (StringUtils.isNotBlank(sourePath)){
                soureFile = new File(sourePath);
                String downName = StringUtils.substring(fileUrl,fileUrl.lastIndexOf("/")+1,fileUrl.length());
                response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(downName, "UTF-8"));
                is = new FileInputStream(sourePath);
                bis = new BufferedInputStream(is);
                os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            }

        } catch (Exception e) {
            log.error("文件读取异常！", e);
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR, e.getMessage(), e.getCause());
        } finally {
            IOUtils.closeQuietly(os);
            IOUtils.closeQuietly(bis);
            IOUtils.closeQuietly(is);
            if (null != soureFile && soureFile.exists()){
                soureFile.delete();
            }
        }
        return Result.success();
    }
}