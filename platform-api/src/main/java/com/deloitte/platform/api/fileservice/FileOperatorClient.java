package com.deloitte.platform.api.fileservice;

import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.fileservice.vo.FileInfoForm;
import com.deloitte.platform.api.fileservice.vo.FileInfoVo;
import com.deloitte.platform.api.fileservice.vo.ResultImageFile;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author : jackliu
 * @Date : Create in 22:08 17/02/2019
 * @Description :
 * @Modified :
 */
public interface FileOperatorClient {

    public static final String path="/fileservice/fileOperator";

    /**
     * @description 文件上传
     * @param  file
     * @return Result
     */
    @PostMapping(value = path+"/uploadFile")
    Result<FileInfoVo> uploadFile(@RequestPart("file") MultipartFile file);


    /**
     * @description 图片上传
     * @param  file
     * @return Result
     */
    @PostMapping(value = path+"/uploadImage/controller.jsp")
    ResultImageFile uploadImage(@RequestParam("action")String action, @RequestPart("upfile") MultipartFile file);

    /**
     * @description 图片上传
     * @return Result
     */
    @GetMapping(value = path+"/uploadImage/controller.jsp")
    void uploadImageConfig(@RequestParam("action")String action, @RequestParam("callback")String callback);


    /**
     * @description 批量文件上传
     * @param  files
     * @return Result
     */
    @PostMapping(value = path+"/uploadFiles")//,consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Result<List<FileInfoVo>> uploadFiles(@RequestPart("files") MultipartFile[] files);

    /**
     * @description 文件下载
     * @param  fileId,response,response
     * @return Result
     */
    @GetMapping(value = path+"/downloadFile/{id}")
    Result downloadFile(@PathVariable(value="id") long fileId);

    /**
     * @description 删除文件
     * @param  fileId 文件ID
     * @return Result
     */
    @DeleteMapping(value = path+"/{id}")
    Result deleteFile(@PathVariable(value="id") long fileId);


}
