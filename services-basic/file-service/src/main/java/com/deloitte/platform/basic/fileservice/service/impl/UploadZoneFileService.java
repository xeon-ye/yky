package com.deloitte.platform.basic.fileservice.service.impl;

import com.deloitte.platform.basic.fileservice.entity.FileInfo;
import com.deloitte.platform.basic.fileservice.exception.FilePropertis;
import com.deloitte.platform.basic.fileservice.core.IFileOperatorClient;
import com.deloitte.platform.basic.fileservice.service.IFileInfoService;
import com.deloitte.platform.basic.fileservice.exception.FileErrorType;
import com.deloitte.platform.common.core.exception.ServiceException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.entity.ContentType;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @ClassName: UploadFileService
 * @Description:文件分块上传
 * @Auther: wangyanyun
 * @Date: 2019-01-17 11:41
 */
@Service
public class UploadZoneFileService {

    @Autowired
    public IFileInfoService fileInfoService;

    @Autowired
    private IFileOperatorClient fileOperatorClient;

    @Autowired
    private FilePropertis filePropertis;



    //得到文件所属目录路径
    private String getFileFolderPath(String fileMd5) {
        return filePropertis.getUploadLocation() + "/" + fileMd5 + "/";
    }

    //得到文件的路径
    private String getFilePath(String fileMd5, String fileName, String fileExt) {
        return filePropertis.getUploadLocation() + "/" + fileMd5 + "/" + fileName + "." + fileExt;
    }

    //得到块文件所属目录路径
    private String getChunkFileFolderPath(String fileMd5) {
        return filePropertis.getUploadLocation() + "/" + fileMd5 + "/chunk/";
    }

    /**
     * @methodName:register
     * @Description: 文件注册
     * @auther: wangyanyun
     * @date: 2019-01-18 14:29
     * @param fileMd5 MD5值
     * @param fileName 文件名
     * @param fileSize 文件大小
     * @param mimetype  文件类型
     * @param fileExt  文件扩展名
     * @return: com.deloitte.platform.fileservice.response.ResponseResult
     * @exception:
     */
    public void register(String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt) throws ServiceException {

        //1  检查文件在磁盘上是否存在
        //文件所属目录的路径
        String fileFolderPath = this.getFileFolderPath(fileMd5);
        //文件的路径
        String filePath = this.getFilePath(fileMd5, fileName, fileExt);
        File file = new File(filePath);
        //文件是否存在
        boolean exists = file.exists();

        //2 检查文件信息在数据库中是否存在
        FileInfo fileInfo = fileInfoService.getById(fileMd5);

        if (fileInfo!=null) {
            //文件存在
            throw new ServiceException(FileErrorType.FILE_EXSIT);
        }
        //文件不存在时作一些准备工作，检查文件所在目录是否存在，如果不存在则创建
        File fileFolder = new File(fileFolderPath);
        if (!fileFolder.exists()) {
            fileFolder.mkdirs();
        }
    }

    /**
     * @methodName:checkchunk
     * @Description: 分块检查
     * @auther: wangyanyun
     * @date: 2019-01-18 14:31
     * @param fileMd5   文件MD5
     * @param chunk     块的下标
     * @param chunkSize 块的大小
     * @return: com.deloitte.platform.fileservice.response.CheckChunkResult
     * @exception:
     */
    public boolean checkchunk(String fileMd5, Integer chunk, Integer chunkSize) {
        //检查分块文件是否存在
        //得到分块文件的所在目录
        String chunkFileFolderPath = this.getChunkFileFolderPath(fileMd5);
        //块文件
        File chunkFile = new File(chunkFileFolderPath + chunk);
        if (chunkFile.exists()) {
            //块文件存在
            return true;
        } else {
            //块文件不存在
            return false;
        }
    }


    /**
     * @methodName:uploadchunk
     * @Description: 上传分块
     * @auther: wangyanyun
     * @date: 2019-01-18 14:32
     * @param file     文件
     * @param fileMd5  文件MD5
     * @param chunk    块下标
     * @return: com.deloitte.platform.fileservice.response.ResponseResult
     * @exception:
     */
    public void uploadchunk(MultipartFile file, String fileMd5, Integer chunk) {
        //检查分块目录，如果不存在则要自动创建
        //得到分块目录
        String chunkFileFolderPath = this.getChunkFileFolderPath(fileMd5);
        //得到分块文件路径
        String chunkFilePath = chunkFileFolderPath + chunk;

        File chunkFileFolder = new File(chunkFileFolderPath);
        //如果不存在则要自动创建
        if (!chunkFileFolder.exists()) {
            chunkFileFolder.mkdirs();
        }
        //得到上传文件的输入流
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = file.getInputStream();
            outputStream = new FileOutputStream(new File(chunkFilePath));
            IOUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * @methodName:mergechunks
     * @Description: 合并文件
     * @auther: wangyanyun
     * @date: 2019-01-18 14:33
     * @param fileMd5  文件MD5
     * @param fileName 文件名
     * @param fileSize 文件大小
     * @param mimetype 文件mimetype类型
     * @param fileExt  文件扩展名
     * @return: com.deloitte.platform.fileservice.response.ResponseResult
     * @exception:
     */
    public void mergechunks(String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt) {

        //1、合并所有分块
        //得到分块文件的属目录
        String chunkFileFolderPath = this.getChunkFileFolderPath(fileMd5);
        File chunkFileFolder = new File(chunkFileFolderPath);
        //分块文件列表
        File[] files = chunkFileFolder.listFiles();
        List<File> fileList = Arrays.asList(files);

        //创建一个合并文件
        String filePath = this.getFilePath(fileMd5, fileName, fileExt);
        File mergeFile = new File(filePath);

        //执行合并
        mergeFile = this.mergeFile(fileList, mergeFile);
        if (mergeFile == null) {
            //合并文件失败
            throw new ServiceException(FileErrorType.FILE_MERGE_ERROR);
        }

        //2、校验文件的md5值是否和前端传入的md5一到
        boolean checkFileMd5 = this.checkFileMd5(mergeFile, fileMd5);
        if (!checkFileMd5) {
            //校验文件失败
            throw new ServiceException(FileErrorType.FILE_CHECK_ERROR);
        }
        //删除分块文件
        chunkFileFolder.delete();
        //3、将文件的信息写入数据库
        FileInfo  fileInfo = new FileInfo();
        //文件上传到fastdfs服务器上，删除磁盘文件

        try {
            FileInputStream fileInputStream = new FileInputStream(mergeFile);
            MultipartFile multipartFile = new MockMultipartFile(mergeFile.getName(),mergeFile.getName(),ContentType.APPLICATION_OCTET_STREAM.toString(),fileInputStream);
            String fileURL = fileOperatorClient.uploadFile(multipartFile);
            fileInfo.setFileUrl(fileURL);
            mergeFile.delete();

        } catch (Exception e) {
            throw new ServiceException(FileErrorType.FILE_UPLOAD_ERROR,e.getMessage());
        }

        fileInfo.setFileName(fileName + "." + fileExt);
        //文件路径保存相对路径
        String filePath1 = fileMd5 + "/" + fileName + "." + fileExt;
        fileInfo.setFilePath(filePath1);
        fileInfo.setFileSize(fileSize);
        fileInfo.setUploadTime(LocalDateTime.now());
        fileInfo.setMimeType(mimetype);
        fileInfo.setFileType(fileExt);
    //    fileInfo.setFileMd5(fileMd5);
   //     file.setServiceIp(service_ip);
        fileInfoService.save(fileInfo);

    }


    /**
     * @methodName:checkFileMd5
     * @Description: 校验文件
     * @auther: wangyanyun
     * @date: 2019-01-18 14:35
     * @param mergeFile  合并文件
     * @param md5        文件MD5
     * @return: boolean
     * @exception:
     */
    private boolean checkFileMd5(File mergeFile, String md5) {

        try {
            //创建文件输入流
            FileInputStream inputStream = new FileInputStream(mergeFile);
            //得到文件的md5
            String md5Hex = DigestUtils.md5Hex(inputStream);
            //和传入的md5比较
            if (md5.equalsIgnoreCase(md5Hex)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;

    }


    /**
     * @methodName:mergeFile
     * @Description: 合并文件
     * @auther: wangyanyun
     * @date: 2019-01-18 14:35
     * @param chunkFileList 分块集合
     * @param mergeFile     合并文件
     * @return: java.io.File
     * @exception:
     */
    private File mergeFile(List<File> chunkFileList, File mergeFile) {
        try {
            //如果合并文件已存在则删除，否则创建新文件
            if (mergeFile.exists()) {
                mergeFile.delete();
            } else {
                //创建一个新文件
                mergeFile.createNewFile();
            }

            //对块文件进行排序
            Collections.sort(chunkFileList, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    if (Integer.parseInt(o1.getName()) > Integer.parseInt(o2.getName())) {
                        return 1;
                    }
                    return -1;

                }
            });
            //创建一个写对象
            RandomAccessFile raf_write = new RandomAccessFile(mergeFile, "rw");
            byte[] b = new byte[1024];
            for (File chunkFile : chunkFileList) {
                RandomAccessFile raf_read = new RandomAccessFile(chunkFile, "r");
                int len = -1;
                while ((len = raf_read.read(b)) != -1) {
                    raf_write.write(b, 0, len);
                }
                raf_read.close();
            }
            raf_write.close();
            return mergeFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
