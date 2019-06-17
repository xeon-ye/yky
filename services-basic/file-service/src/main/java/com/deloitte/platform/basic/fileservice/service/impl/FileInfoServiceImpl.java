package com.deloitte.platform.basic.fileservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fileservice.param.FileInfoQueryForm;
import com.deloitte.platform.basic.fileservice.entity.FileInfo;
import com.deloitte.platform.basic.fileservice.mapper.FileInfoMapper;
import com.deloitte.platform.basic.fileservice.service.IFileInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : jackliu
 * @Date : Create in 2019-02-19
 * @Description :  FileInfo服务实现类
 * @Modified :
 */
@Service
@Transactional
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo> implements IFileInfoService {


    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Override
    public IPage<FileInfo> selectPage(FileInfoQueryForm queryForm ) {
        QueryWrapper<FileInfo> queryWrapper = new QueryWrapper <FileInfo>();
        //getQueryWrapper(queryWrapper,queryForm);
        return fileInfoMapper.selectPage(new Page<FileInfo>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<FileInfo> selectList(FileInfoQueryForm queryForm) {
        QueryWrapper<FileInfo> queryWrapper = new QueryWrapper <FileInfo>();
        //getQueryWrapper(queryWrapper,queryForm);
        return fileInfoMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<FileInfo> getQueryWrapper(QueryWrapper<FileInfo> queryWrapper,BaseQueryForm<FileInfoQueryParam> queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(fileInfo.getFileName())){
            queryWrapper.like(FileInfo.FILE_NAME,fileInfo.getFileName());
        }
        if(StringUtils.isNotBlank(fileInfo.getFilePath())){
            queryWrapper.like(FileInfo.FILE_PATH,fileInfo.getFilePath());
        }
        if(StringUtils.isNotBlank(fileInfo.getFileType())){
            queryWrapper.like(FileInfo.FILE_TYPE,fileInfo.getFileType());
        }
        if(StringUtils.isNotBlank(fileInfo.getMimeType())){
            queryWrapper.like(FileInfo.MIME_TYPE,fileInfo.getMimeType());
        }
        if(StringUtils.isNotBlank(fileInfo.getFileSize())){
            queryWrapper.like(FileInfo.FILE_SIZE,fileInfo.getFileSize());
        }
        if(StringUtils.isNotBlank(fileInfo.getUploadTime())){
            queryWrapper.like(FileInfo.UPLOAD_TIME,fileInfo.getUploadTime());
        }
        if(StringUtils.isNotBlank(fileInfo.getFileMd5())){
            queryWrapper.like(FileInfo.FILE_MD5,fileInfo.getFileMd5());
        }
        if(StringUtils.isNotBlank(fileInfo.getFileOwner())){
            queryWrapper.like(FileInfo.FILE_OWNER,fileInfo.getFileOwner());
        }
        if(StringUtils.isNotBlank(fileInfo.getFileVirtualPath())){
            queryWrapper.like(FileInfo.FILE_VIRTUAL_PATH,fileInfo.getFileVirtualPath());
        }
        if(StringUtils.isNotBlank(fileInfo.getFileState())){
            queryWrapper.like(FileInfo.FILE_STATE,fileInfo.getFileState());
        }
        if(StringUtils.isNotBlank(fileInfo.getModifyTime())){
            queryWrapper.like(FileInfo.MODIFY_TIME,fileInfo.getModifyTime());
        }
        if(StringUtils.isNotBlank(fileInfo.getFileDescription())){
            queryWrapper.like(FileInfo.FILE_DESCRIPTION,fileInfo.getFileDescription());
        }
        if(StringUtils.isNotBlank(fileInfo.getFileUrl())){
            queryWrapper.like(FileInfo.FILE_URL,fileInfo.getFileUrl());
        }
        if(StringUtils.isNotBlank(fileInfo.getDownloadCount())){
            queryWrapper.like(FileInfo.DOWNLOAD_COUNT,fileInfo.getDownloadCount());
        }
        if(StringUtils.isNotBlank(fileInfo.getExt1())){
            queryWrapper.like(FileInfo.EXT_1,fileInfo.getExt1());
        }
        if(StringUtils.isNotBlank(fileInfo.getExt2())){
            queryWrapper.like(FileInfo.EXT_2,fileInfo.getExt2());
        }
        if(StringUtils.isNotBlank(fileInfo.getExt3())){
            queryWrapper.like(FileInfo.EXT_3,fileInfo.getExt3());
        }
        if(StringUtils.isNotBlank(fileInfo.getExt4())){
            queryWrapper.like(FileInfo.EXT_4,fileInfo.getExt4());
        }
        if(StringUtils.isNotBlank(fileInfo.getExt5())){
            queryWrapper.like(FileInfo.EXT_5,fileInfo.getExt5());
        }
        if(StringUtils.isNotBlank(fileInfo.getFileExt())){
            queryWrapper.like(FileInfo.FILE_EXT,fileInfo.getFileExt());
        }
        if(StringUtils.isNotBlank(fileInfo.getFileEncrypt())){
            queryWrapper.like(FileInfo.FILE_ENCRYPT,fileInfo.getFileEncrypt());
        }
        if(StringUtils.isNotBlank(fileInfo.getFileKey())){
            queryWrapper.like(FileInfo.FILE_KEY,fileInfo.getFileKey());
        }
        return queryWrapper;
    }
     */
}

