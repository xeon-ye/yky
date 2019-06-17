package com.deloitte.platform.basic.fileservice.service;

import com.deloitte.platform.basic.fileservice.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author : jackliu
 * @Date : Create in 17:20 17/02/2019
 * @Description :
 * @Modified :
 */
public interface IFileOperatorService {

     /**
      * @Description: 上传文件
      * @param multipartFile
      * @return: String 保存文件返回的路径
      * @exception: ServiceException
      */
      FileInfo upload(MultipartFile multipartFile);

    /**
     * @Description: 批量上传文件
     * @param multipartFile
     * @return: String 保存文件返回的路径
     * @exception: ServiceException
     */
      List<FileInfo> uploads(MultipartFile[] multipartFile);

     /**
      * @Description:  下载文件
      * @param  request,response
      * @return: String 保存文件返回的路径
      * @exception: ServiceException
      * */
      void downloadFile(long fileInfoId, HttpServletRequest request, HttpServletResponse response);

      /**
      * @Description:  断点续传下载文件
      * @param fileInfoId,request,response
      * @return: String 保存文件返回的路径
      * @exception: ServiceException
      * */
      void downloadFilBreakPoint(long fileInfoId, HttpServletRequest request, HttpServletResponse response);


     /**
      * @Description:  删除服务器上的文件
      * @param fileInfoId 文件ID
      */
      void  deleteFile(long fileInfoId);

     /**
      * @Description:  删除本地的文件
      * @param fileId 文件ID
      */
     public void deleteFileLocal(long fileId);
}
