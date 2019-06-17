package com.deloitte.services.srpmp.common.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fileservice.feign.FileOperatorFeignService;
import com.deloitte.platform.api.fileservice.vo.FileInfoVo;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.util.UUIDUtil;
import com.deloitte.services.srpmp.common.entity.SerialNoCenter;
import com.deloitte.services.srpmp.common.mapper.SerialNoCenterMapper;
import com.deloitte.services.srpmp.common.service.IFilePreviewService;
import com.deloitte.services.srpmp.common.service.ISerialNoCenterService;
import com.deloitte.services.srpmp.common.util.ExcelConvertUtil;
import com.deloitte.services.srpmp.common.util.WordConvertUtil;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

/**
 * 文件转化为pdf 预览
 */
@Service
@Slf4j
@Transactional
public class FilePreviewImpl implements IFilePreviewService {

    @Autowired
    private FileOperatorFeignService fileOperatorFeignService;

    @Override
    public String preview(String fileUrl) {
        if (StringUtils.isBlank(fileUrl)) {
            return null;
        }
        try {
            String sourePath = WordConvertUtil.downloadHttpUrl(fileUrl);
            if (StringUtils.isBlank(sourePath)) {
                return null;
            }
            String jarPath = new File(ResourceUtils.getURL("").getPath()).getAbsolutePath();
            String suffix = StringUtils.substring(sourePath, sourePath.lastIndexOf(".") + 1, sourePath.length());
            String filePath = null;
            if (StringUtils.equals(suffix, "xls") || StringUtils.equals(suffix, "xlsx")) {
                filePath = jarPath + File.separator + UUIDUtil.getRandom32PK() + ".html";
                ExcelConvertUtil.readExcelToHtml(sourePath, filePath, true);
            }else
            {
                filePath = jarPath + File.separator + UUIDUtil.getRandom32PK() + ".pdf";
                WordConvertUtil.docx2PDF(sourePath, filePath);
            }
           return  upload(filePath);
        } catch (Exception e) {
            throw new BaseException(PlatformErrorType.FILE_PREVIEW_COVERT_FAIL);
        }
    }

    /**
     * 上传到文件服务器
     * @param filePath
     * @return
     */
    private String upload(String filePath) {
        if (StringUtils.isBlank(filePath)) {
            return null;
        }
        File uploadFile = null;
        FileInputStream fileInputStream = null;
        try {
            uploadFile = new File(filePath);
            fileInputStream = new FileInputStream(uploadFile);
            MultipartFile multipartFile = new MockMultipartFile("file", uploadFile.getName(),
                    ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
            Result<FileInfoVo> result = fileOperatorFeignService.uploadFile(multipartFile);
            if (result.isSuccess()) {
                FileInfoVo fileInfoVo = result.getData();
                if (fileInfoVo != null) {
                    return fileInfoVo.getFileUrl();
                }
                uploadFile.delete();
            } else {
                log.error("文件预览转化为PDF文件，上传文件服务器失败！");
            }

        } catch (Exception e) {
            throw new BaseException(PlatformErrorType.FILE_PREVIEW_COVERT_FAIL);
        } finally {
            IOUtils.closeQuietly(fileInputStream);
            if (uploadFile != null && uploadFile.exists()) {
                uploadFile.delete();
            }
        }
        return null;
    }
}

