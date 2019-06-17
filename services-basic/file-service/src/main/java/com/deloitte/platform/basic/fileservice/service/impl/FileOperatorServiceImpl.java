package com.deloitte.platform.basic.fileservice.service.impl;

import com.deloitte.platform.basic.fileservice.Util.FileUtils;
import com.deloitte.platform.basic.fileservice.core.IFileOperatorClient;
import com.deloitte.platform.basic.fileservice.entity.FileInfo;
import com.deloitte.platform.basic.fileservice.service.IFileInfoService;
import com.deloitte.platform.common.core.exception.ServiceException;
import com.deloitte.platform.common.core.util.Encodes;
import com.deloitte.platform.basic.fileservice.exception.FilePropertis;
import com.deloitte.platform.basic.fileservice.exception.FileErrorType;
import com.deloitte.platform.basic.fileservice.service.IFileOperatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : jackliu
 * @Date : Create in 16:35 17/02/2019
 * @Description :
 * @Modified :
 */
@Service
@Slf4j
public class FileOperatorServiceImpl implements IFileOperatorService {

     @Autowired
     private IFileOperatorClient fileOperatorClient;

     @Autowired
     private IFileInfoService fileInfoService;

     @Autowired
     private FilePropertis filePropertis;


    /**
     * @Description: 上传文件
     * @param multipartFile
     * @return: String 保存文件返回的路径
     * @exception: ServiceException
     */
    @Override
    public FileInfo upload(MultipartFile multipartFile) {
        try {

            //得到文件的原始名称
            String originalFilename = multipartFile.getOriginalFilename();
            //得到文件扩展名
            String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            String urlPath = fileOperatorClient.uploadFile(multipartFile);

            FileInfo fileInfo=new FileInfo();
            fileInfo.setFileName(originalFilename);
            fileInfo.setFileUrl(urlPath);
            fileInfo.setFileSize(multipartFile.getSize());
            fileInfo.setFileExt(ext);
            fileInfo.setUploadTime(LocalDateTime.now());
            String md5=FileUtils.getMD5(multipartFile.getBytes());
            fileInfo.setFileMd5(md5 );

            fileInfoService.save(fileInfo);
            return fileInfo;
        } catch (Exception e) {
            throw new ServiceException(FileErrorType.FILE_UPLOAD_ERROR,e.getMessage());
        }
    }



    @Override
    public List<FileInfo> uploads(MultipartFile[] multipartFile){
         List<FileInfo>  fileInfoList=new ArrayList();
         for(MultipartFile multipartFile1:multipartFile){
             FileInfo fileInfo=upload(multipartFile1);
             fileInfoList.add(fileInfo);
         }
         return fileInfoList;
    }

    /**
     * @Description:  下载文件
     * @param  request,response
     * @return: String 保存文件返回的路径
     * @exception: ServiceException
     * */
    @Override
    public void downloadFile(long fileInfoId,HttpServletRequest request, HttpServletResponse response)
    {
        FileInfo filesInfo = fileInfoService.getById(fileInfoId);
        if(filesInfo==null){
            throw new ServiceException(FileErrorType.FILE_NOT_EXSIT);
        }
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            String downName = encodeDownloadFilename(filesInfo.getFileName(), request.getHeader("User-Agent"));
            response.setHeader("Content-Disposition", "attachment;fileName=" + downName);
            URL url = new URL(filesInfo.getFileUrl());
            URLConnection conn = url.openConnection();
            is = conn.getInputStream();
            bis = new BufferedInputStream(is);
            os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        }
        catch (FileNotFoundException e) {
            throw new ServiceException(FileErrorType.FILE_NOT_EXSIT, e.getMessage());
        }
        catch (IOException e) {
            log.error("文件读取异常！", e);
            throw new ServiceException(FileErrorType.FILE_READ_ERROR, e.getMessage(),e.getCause());
        }
        finally {
            if (os != null) {
                try {
                    os.flush();
                    os.close();
                }
                catch (IOException e) {
                    log.error("文件流关闭异常:", e);
                    throw new ServiceException(FileErrorType.FILE_CLOSE_ERROR, e.getMessage());
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                }
                catch (IOException e) {
                    log.error("文件流关闭异常:", e);
                    throw new ServiceException(FileErrorType.FILE_CLOSE_ERROR, e.getMessage());
                }
            }
            if (is != null) {
                try {
                    is.close();
                }
                catch (IOException e){
                    log.error("文件流关闭异常:", e);
                    throw new ServiceException(FileErrorType.FILE_CLOSE_ERROR, e.getMessage());
                }
            }
        }
    }


    /**
     * @Description:  断点续传下载文件
     * @param fileInfoId,request,response
     * @return: String 保存文件返回的路径
     * @exception: ServiceException
     * */
    @Override
    public void downloadFilBreakPoint(long fileInfoId, HttpServletRequest request, HttpServletResponse response) {
        FileInfo fileInfo = fileInfoService.getById(fileInfoId);
        if (fileInfo != null ) {
            throw new ServiceException(FileErrorType.FILE_NOT_EXSIT);
        }
            // 解析断点续传相关信息
        response.setHeader("Accept-Ranges", "bytes");
        long downloadSize = Long.valueOf(fileInfo.getFileSize());
        long fromPos = 0, toPos = 0;
        if (request.getHeader("Range") == null) {
            response.setHeader("Content-Length", downloadSize + "");
        } else {
            // 若客户端传来Range，说明之前下载了一部分，设置206状态(SC_PARTIAL_CONTENT)
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
            String range = request.getHeader("Range");
            String bytes = range.replaceAll("bytes=", "");
            String[] ary = bytes.split("-");
            fromPos = Long.parseLong(ary[0]);
            if (ary.length == 2) {
                toPos = Long.parseLong(ary[1]);
            }
            int size;
            if (toPos > fromPos) {
                size = (int) (toPos - fromPos);
            } else {
                size = (int) (downloadSize - fromPos);
            }
            response.setHeader("Content-Length", size + "");
            downloadSize = size;
        }
        RandomAccessFile in = null;
        OutputStream out = null;

        File downloadFile = new File(filePropertis.getUploadLocation() + fileInfo.getFilePath());
        try {
            in = new RandomAccessFile(downloadFile, "rw");
            // 设置下载起始位置
            if (fromPos > 0) {
                in.seek(fromPos);
            }
            // 缓冲区大小
            int bufLen = (int) (downloadSize < 2048 ? downloadSize : 2048);
            byte[] buffer = new byte[bufLen];
            int num;
            int count = 0; // 当前写到客户端的大小
            out = response.getOutputStream();
            while ((num = in.read(buffer)) != -1) {
                out.write(buffer, 0, num);
                count += num;
                //处理最后一段，计算不满缓冲区的大小
                if (downloadSize - count < bufLen) {
                    bufLen = (int) (downloadSize - count);
                    if (bufLen == 0) {
                        break;
                    }
                    buffer = new byte[bufLen];
                }
            }
            response.flushBuffer();
        } catch (IOException e) {
            throw new ServiceException(FileErrorType.FILE_UPLOAD_ERROR,e.getMessage());
        } finally {
            this.close(in,out);
        }

    }

    /**
     * @Description:  删除服务器上的文件
     * @param fileId 文件ID
     */
    @Override
    public void  deleteFile(long fileId){
        FileInfo filesInfo = fileInfoService.getById(fileId);
        if(filesInfo==null){
            throw new ServiceException(FileErrorType.FILE_NOT_EXSIT);
        }
        fileOperatorClient.deleteFile(filesInfo.getFilePath());
        fileInfoService.removeById(fileId);
    }

    /**
     * @Description:  删除本地的文件
     * @param fileId 文件ID
     */
    @Override
    public void deleteFileLocal(long fileId){
        FileInfo fileInfo = fileInfoService.getById(fileId);
        if(fileInfo==null){
            throw new ServiceException(FileErrorType.FILE_NOT_EXSIT);
        }
        File file= new File(filePropertis.getUploadLocation() + fileInfo.getFilePath());
        file.delete();
    }


    /**
     * * @Description:  下载文件时,文件名出现乱码
     * @param filename,agent
     * @return: String 获取文件名
     * @exception: ServiceException
     */
    private String encodeDownloadFilename(String filename, String agent) throws IOException
    {
        if (agent == null)
        {
            return filename;
        }
        if (agent.contains("Firefox"))
        { // 火狐浏览器
            filename = "=?UTF-8?B?" + Encodes.encodeBase64(filename.getBytes("utf-8")) + "?=";
            filename = filename.replaceAll("\r\n", "");
        }
        else
        { // IE及其他浏览器
            filename = URLEncoder.encode(filename, "utf-8");
            filename = filename.replace("+", " ");
        }
        return filename;
    }


    private void close(RandomAccessFile in ,OutputStream out ){
        if (null != out) {
            try {
                out.close();
            } catch (IOException e) {
                throw new ServiceException(FileErrorType.FILE_UPLOAD_ERROR,e.getMessage());
            }
        }
        if (null != in) {
            try {
                in.close();
            } catch (IOException e) {
                throw new ServiceException(FileErrorType.FILE_UPLOAD_ERROR,e.getMessage());
            }
        }
    }

}
