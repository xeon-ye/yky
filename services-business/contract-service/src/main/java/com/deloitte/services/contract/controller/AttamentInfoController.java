package com.deloitte.services.contract.controller;

import com.deloitte.platform.api.fileservice.feign.FileOperatorFeignService;
import com.deloitte.platform.api.fileservice.vo.FileInfoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.exception.ServiceException;
import com.deloitte.services.contract.common.util.Fileutil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Objects;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :   文件上传控制器实现类
 * @Modified :
 */
@Api(tags = "文件上传操作接口")
@Slf4j
@RestController
@RequestMapping("/attament")
public class AttamentInfoController  {

    @Autowired
    private FileOperatorFeignService fileOperatorFeignService;

    @ApiOperation(value = "2-3-1单个文件上传", notes = "2-3-1单个文件上传")
    @PostMapping("/uploadFile")
    Result uploadFile(@RequestPart("file") MultipartFile file){
        Result<FileInfoVo> result =  fileOperatorFeignService.uploadFile(file);
        /*if(Result.SUCCESSFUL_CODE.equals(result.getCode())){
            throw new ServiceException(ContractErrorType.FILE_UPLOAD_FIAL);
        }*/
        return result;
    }

    @ApiOperation(value = "2-3-2批量文件上传", notes = "2-3-2批量文件上传")
    @PostMapping("/uploadFiles")
    Result<List<FileInfoVo>> uploadFiles(@RequestPart("files") MultipartFile[] files){
        Result<List<FileInfoVo>> result = fileOperatorFeignService.uploadFiles(files);
       /* if(Result.SUCCESSFUL_CODE.equals(result.getCode())){
            throw new ServiceException(ContractErrorType.FILE_UPLOAD_FIAL);
        }*/
        return result;
    }

    @ApiOperation(value = "2-3-3文件下载", notes = "2-3-3文件下载")
    @PostMapping("/downloadFile")
    Result downloadFile(long fileId){
        Result result = fileOperatorFeignService.downloadFile(fileId);
      /*  if(Result.SUCCESSFUL_CODE.equals(result.getCode())){
            throw new ServiceException(ContractErrorType.FILE_DOWNLOAD_FIAL);
        }*/
        return result;
    }

    @ApiOperation(value = "2-3-4删除文件", notes = "2-3-4删除文件")
    @PostMapping("/deleteFile")
    Result deleteFile(long fileId){
        Result result = fileOperatorFeignService.deleteFile(fileId);
        /*if(Result.SUCCESSFUL_CODE.equals(result.getCode())){
            throw new ServiceException(ContractErrorType.FILE_DELETE_FIAL);
        }*/
        return result;
    }

    @ApiOperation(value = "根据uri下载文件", notes = "根据uri下载文件")
    @GetMapping("/uploadByUrl")
    public void uploadByUrl(HttpServletRequest request, HttpServletResponse response){
        BufferedOutputStream bos = null;
        try {
            String url = request.getParameter("url");
            String fileName = request.getParameter("fileName");
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/octet-stream");
            String os = System.getProperty("os.name");
            if(os.toLowerCase().indexOf("windows") != -1){
                fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
            }else{
                //判断浏览器
                String userAgent = request.getHeader("User-Agent").toLowerCase();
                if(userAgent.indexOf("msie") > 0){
                    fileName = URLEncoder.encode(fileName, "ISO-8859-1");
                }
            }
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            bos = new BufferedOutputStream(response.getOutputStream());
            InputStream inputStream = Fileutil.getInputStreamFromUrl(url);
//            byte[] data = new byte[inputStream.available()];
//            inputStream.read(data);
//            bos.write(data);
            int byteCount = 0;
            byte[] data = new byte[1024];
            while ((byteCount = inputStream.read(data)) != -1) {
                bos.write(data, 0, byteCount);
            }
            inputStream.close();
            bos.close();
        } catch (IOException e) {
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR, "下载失败！");
        } finally {
            try {
                if (Objects.nonNull(bos)) {
                    bos.flush();
                    bos.close();
                }
            } catch (IOException e) {
                log.error("文件下载异常！", e);
                throw new BaseException(PlatformErrorType.SYSTEM_ERROR, "下载失败！");
            }
        }
    }
}



