package com.deloitte.services.fssc.system.file.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.file.param.FileQueryForm;
import com.deloitte.services.fssc.system.file.entity.File;
import com.deloitte.services.fssc.system.file.mapper.FileMapper;
import com.deloitte.services.fssc.system.file.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : qiliao
 * @Date : Create in 2019-03-04
 * @Description :  File服务实现类
 * @Modified :
 */
@Service
@Transactional
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements IFileService {


    @Autowired
    private FileMapper fileMapper;

    @Override
    public IPage<File> selectPage(FileQueryForm queryForm ) {
        QueryWrapper<File> queryWrapper = new QueryWrapper <File>();
        //getQueryWrapper(queryWrapper,queryForm);
        return fileMapper.selectPage(new Page<File>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<File> selectList(FileQueryForm queryForm) {
        QueryWrapper<File> queryWrapper = new QueryWrapper <File>();
        //getQueryWrapper(queryWrapper,queryForm);
        return fileMapper.selectList(queryWrapper);
    }

    public List<File> selectListByType(FileQueryForm queryForm){
        QueryWrapper<File> queryWrapper = new QueryWrapper <File>();
        queryWrapper.eq(File.DOCUMENT_TYPE,queryForm.getDocumentType());
        queryWrapper.eq(File.DOCUMENT_ID,queryForm.getDocumentId());
        return fileMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<File> getQueryWrapper(QueryWrapper<File> queryWrapper,BaseQueryForm<FileQueryParam> queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(file.getCreateBy())){
            queryWrapper.like(File.CREATE_BY,file.getCreateBy());
        }
        if(StringUtils.isNotBlank(file.getCreateUserName())){
            queryWrapper.like(File.CREATE_USER_NAME,file.getCreateUserName());
        }
        if(StringUtils.isNotBlank(file.getUpdateBy())){
            queryWrapper.like(File.UPDATE_BY,file.getUpdateBy());
        }
        if(StringUtils.isNotBlank(file.getUpdateTime())){
            queryWrapper.like(File.UPDATE_TIME,file.getUpdateTime());
        }
        if(StringUtils.isNotBlank(file.getCreateTime())){
            queryWrapper.like(File.CREATE_TIME,file.getCreateTime());
        }
        if(StringUtils.isNotBlank(file.getEx1())){
            queryWrapper.like(File.EX1,file.getEx1());
        }
        if(StringUtils.isNotBlank(file.getEx2())){
            queryWrapper.like(File.EX2,file.getEx2());
        }
        if(StringUtils.isNotBlank(file.getEx3())){
            queryWrapper.like(File.EX3,file.getEx3());
        }
        if(StringUtils.isNotBlank(file.getEx4())){
            queryWrapper.like(File.EX4,file.getEx4());
        }
        if(StringUtils.isNotBlank(file.getEx5())){
            queryWrapper.like(File.EX5,file.getEx5());
        }
        if(StringUtils.isNotBlank(file.getVersion())){
            queryWrapper.like(File.VERSION,file.getVersion());
        }
        if(StringUtils.isNotBlank(file.getFileType())){
            queryWrapper.like(File.FILE_TYPE,file.getFileType());
        }
        if(StringUtils.isNotBlank(file.getFileUrl())){
            queryWrapper.like(File.FILE_URL,file.getFileUrl());
        }
        if(StringUtils.isNotBlank(file.getObjectId())){
            queryWrapper.like(File.OBJECT_ID,file.getObjectId());
        }
        if(StringUtils.isNotBlank(file.getObjectType())){
            queryWrapper.like(File.OBJECT_TYPE,file.getObjectType());
        }
        return queryWrapper;
    }
     */
}

