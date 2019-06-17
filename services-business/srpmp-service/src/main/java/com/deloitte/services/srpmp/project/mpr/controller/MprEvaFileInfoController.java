package com.deloitte.services.srpmp.project.mpr.controller;

import com.alibaba.fastjson.JSON;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.mpr.MprEvaFileInfoClient;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprEvaFileInfoCompressDownForm;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprEvaFileInfoSaveOrUpdateForm;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprEvaFileInfoVoExt;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.common.util.ZipUtil;
import com.deloitte.services.srpmp.project.mpr.service.IMprEvaFileInfoService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


/**
 * @Author : LIJUN
 * @Date : Create in 2019-04-01
 * @Description :   MprEvaFileInfo控制器实现类
 * @Modified :
 */
@Api(tags = "中期绩效报告文件信息操作接口")
@Slf4j
@RestController
public class MprEvaFileInfoController implements MprEvaFileInfoClient {

    @Autowired
    public IMprEvaFileInfoService  mprEvaFileInfoService;

    @Override
    @ApiOperation(value = "保存或者更新文件信息", notes = "保存或者更新文件信息")
    @ApiParam(name="mprEvaFileInfoForm",value="修改MprEvaFileInfo的form表单",required=true)
    public Result saveOrUpdate(@Valid @RequestBody MprEvaFileInfoSaveOrUpdateForm mprEvaFileInfoSaveOrUpdateForm) {
        mprEvaFileInfoService.saveOrUpdateFileInfo(mprEvaFileInfoSaveOrUpdateForm);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "根据项目编号获取文件列表", notes = "根据项目编号获取文件列表")
    @ApiImplicitParam(paramType = "path", name = "projectId", value = "项目编号", required = true, dataType = "long")
    public Result<MprEvaFileInfoVoExt> getByProjectId(@PathVariable Long projectId) {
        log.info("get with projectId:{}", projectId);
        MprEvaFileInfoVoExt evaFileInfoVoExt = mprEvaFileInfoService.getByProjectId(projectId);
        return new Result<MprEvaFileInfoVoExt>().sucess(evaFileInfoVoExt);
    }

    @Override
    @ApiOperation(value = "导出模板", notes = "导出模板")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "projectNo", value = "projectNo", required = true, dataType = "string"),
            @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "long")}
    )
    public Result exportTemplate(@PathVariable(value = "projectNo") Long projectNo, @PathVariable(value = "id") Long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo) {
        String fileUrl = mprEvaFileInfoService.exportTemplate(id, projectNo, userVo, deptVo);
        Map<String, String> nameMap = mprEvaFileInfoService.getTemplateNameFileName(id);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            String downName = MapUtils.getString(nameMap, "fileName") + ".docx";
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
    @ApiOperation(value = "压缩并下载模板", notes = "压缩并下载模板")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "ids", value = "ids", required = true, dataType = "string"),
            @ApiImplicitParam(paramType = "path", name = "projectIds", value = "projectIds", required = true, dataType = "string")
    })
    public Result compressTemplateDownload(@PathVariable(value = "ids") String ids, @PathVariable(value = "projectIds") String projectIds
            , HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo) {
        List<File> files = mprEvaFileInfoService.compressTemplateDownload(ids, projectIds, userVo, deptVo);
        if (Objects.isNull(files)) {
            return Result.fail();
        } else {
            this.download(response, files);
            return Result.success();
        }
    }

    @Override
    @ApiOperation(value = "压缩并下载附件", notes = "压缩并下载附件")
    public Result compressEnclosureDownload(@Valid @RequestBody MprEvaFileInfoCompressDownForm mprEvaFileInfoCompressDownForm, HttpServletRequest request, HttpServletResponse response) {
        log.info(JSON.toJSONString(mprEvaFileInfoCompressDownForm));
        List<File> files = mprEvaFileInfoService.compressEnclosureDownload(mprEvaFileInfoCompressDownForm);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/octet-stream");

        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;

        String uuid = UUID.randomUUID().toString();
        String fileUrl = null;
        try {
            response.setHeader("Content-Disposition", "attachment;fileName=" + uuid +".zip");
            String rootPath = new File(ResourceUtils.getURL("").getPath()).getAbsolutePath();
            File tempPath = new File(rootPath + File.separator + "tempDir");
            File targetPath = new File(rootPath + File.separator + "targetZipDir");
            if (!targetPath.exists()) {
                targetPath.mkdirs();
            }
            if (tempPath.exists()) {
                ZipUtil.zip(tempPath.getAbsolutePath(), targetPath.getAbsolutePath()+File.separator+uuid+".zip");

                fileUrl = targetPath.getAbsolutePath()+File.separator+uuid+".zip";
                log.info("the file url is :" + fileUrl);

                is = new FileInputStream(fileUrl);
                bis = new BufferedInputStream(is);
                os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            }
            this.delete(files);
        } catch (Exception e) {
            log.info("zip打包异常", e);
        } finally {
            IOUtils.closeQuietly(os);
            IOUtils.closeQuietly(bis);
            IOUtils.closeQuietly(is);
        }
        return Result.success();
    }

    /**
     * 文件打包zip并下载
     * @param response
     * @param files
     */
    private void download(HttpServletResponse response, List<File> files) {
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/octet-stream");

        ZipOutputStream zipOutputStream = null;
        try {
            response.setHeader("Content-Disposition", "attachment;fileName=" + UUID.randomUUID() +".zip");
            zipOutputStream = new ZipOutputStream(new BufferedOutputStream(response.getOutputStream()));
            zipOutputStream.setMethod(ZipOutputStream.DEFLATED);
            for (int i = 0; i < files.size(); i++) {
                File file = files.get(i);
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] data = new byte[(int)file.length()];
                fileInputStream.read(data);
                zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                zipOutputStream.write(data);
                fileInputStream.close();
                zipOutputStream.closeEntry();
            }

            this.delete(files);
        } catch (IOException e) {
            log.info("文件下载失败！", e);
        } finally {
            try {
                if (Objects.nonNull(zipOutputStream)) {
                    zipOutputStream.flush();
                    zipOutputStream.close();
                }
            } catch (IOException e) {
                log.error("文件下载异常！", e);
                throw new BaseException(PlatformErrorType.SYSTEM_ERROR, "下载失败！");
            }
        }
    }

    /**
     * 删除本地临时文件
     * @param files file集合对象
     */
    private void delete(List<File> files) {
        for (File file : files) {
            if (file.isFile()) {
                file.delete();
            }
        }
    }

    @Override
    @ApiOperation(value = "打包下载附件", notes = "打包下载附件")
    public Result downloadZip(@RequestBody MprEvaFileInfoCompressDownForm mprEvaFileInfoCompressDownForm, HttpServletRequest request, HttpServletResponse response) {
        String url = mprEvaFileInfoService.downloadZip(mprEvaFileInfoCompressDownForm);
        return Result.success(url);
    }
}



