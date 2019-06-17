package com.deloitte.platform.basic.fileservice.core;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author : jackliu
 * @Date : Create in 14:40 17/02/2019
 * @Description :
 * @Modified :
 */
public interface IFileOperatorClient {



    /**
     * 上传文件
     * @param file 文件对象
     * @return 文件访问地址
     * @throws IOException
     */
     String uploadFile(MultipartFile file) throws IOException;


    /**
     * 上传文件
     * @param file 文件对象
     * @return 文件访问地址
     * @throws IOException
     */
     String uploadFile(File file) throws IOException;


    /**
     * 删除文件
     * @param fileUrl 文件访问地址
     * @return
     */
     void deleteFile(String fileUrl);


}
