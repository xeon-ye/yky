package com.deloitte.services.fssc.system.file.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.platform.api.fileservice.feign.FileOperatorFeignService;
import com.deloitte.platform.api.fileservice.vo.FileInfoVo;
import com.deloitte.platform.api.fssc.file.param.FileQueryForm;
import com.deloitte.platform.api.fssc.file.vo.FileForm;
import com.deloitte.platform.api.fssc.file.vo.FileVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.system.file.entity.File;
import com.deloitte.services.fssc.system.file.service.IFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author : qiliao
 * @Date : Create in 2019-03-04
 * @Description :   File控制器实现类
 * @Modified :
 */
@Api(tags = "文件上传操作接口")
@Slf4j
@RestController
public class FileController {

    @Autowired
    public IFileService fileService;

    @Autowired
    private FileOperatorFeignService fileOperatorFeignService;


    @DeleteMapping(value = "fssc/deleteFileById")
    @ApiOperation(value = "删除文件", notes = "删除文件")
    @ResponseBody
    @Transactional
    public Result deleteFileById(@RequestBody List<Long> fileIds){
        if(CollectionUtils.isNotEmpty(fileIds)){
            boolean flag=true;
            for (Long id:fileIds){
                File byId = fileService.getById(id);
                if(byId!=null){
                    Result result = fileOperatorFeignService.deleteFile(byId.getRemoteFileId());
                    if(result!=null){
                        log.info("远程文件删除:{}"+result.getMesg());
                    }
                }
                boolean b = fileService.removeById(id);
                if (!b){
                    flag=false;
                }
            }
            if (flag){
                return Result.success();
            }
        }

        throw new FSSCException(FsscErrorType.FILE_DELETE_FIAL);
    }


    @GetMapping(value = "fssc/findFileByDocument")
    @ApiOperation(value = "分页查询文件", notes = "分页查询文件")
    @ResponseBody
    public Result<List<FileVo>> findFilePage(FileQueryForm fileQueryForm){
        QueryWrapper<File> fileQueryWrapper=new QueryWrapper<>();
        fileQueryWrapper.eq(File.DOCUMENT_ID,fileQueryForm.getDocumentId())
                .eq(File.DOCUMENT_TYPE, fileQueryForm.getDocumentType());
        return Result.success(new BeanUtils<FileVo>().copyObjs(fileService.list(fileQueryWrapper),FileVo.class));
    }

    @ApiOperation("批量文件上传")
    @PostMapping(value = "fssc/uploadFile")
    @Transactional
    public Result uploadFile(@RequestPart("files") MultipartFile[] files, FileForm form) {
        if (files != null) {
            Result<List<FileInfoVo>> listResult = fileOperatorFeignService.uploadFiles(files);
            if(!Result.SUCCESSFUL_CODE.equals(listResult.getCode())){
                throw new FSSCException(FsscErrorType.FILE_UPLOAD_FIAL);
            }
            List<FileInfoVo> fileInfoVos = listResult.getData();
            List<File> boFiles = new ArrayList<>();
            for (FileInfoVo vo : fileInfoVos) {
                File file = new File();
                file.setFileName(vo.getFileName());
                file.setFileType(vo.getFileExt());
                file.setFileUrl(vo.getFileUrl());
                file.setDocumentType(form.getDocumentType());
                file.setRemoteFileId(NumberUtils.toLong(vo.getId()));
                file.setDocumentId(form.getDocumentId());
                file.setFileDefLineId(form.getFileDefLineId());
                boFiles.add(file);
            }
            fileService.saveOrUpdateBatch(boFiles);
            return Result.success();
        }

        throw new FSSCException(FsscErrorType.FILE_IS_NULL);
    }


    @ApiOperation("上传单个文件")
    @PostMapping(value = "fssc/uploadTemplateFile")
    @Transactional
    public Result<FileVo> uploadTemplateFile(@RequestPart("file") MultipartFile uploadFile, FileForm form) {
        log.info("上传文件开始:{}",form.toString());
        if (uploadFile != null) {
            Result<FileInfoVo> result = fileOperatorFeignService.uploadFile(uploadFile);
            if (Result.SUCCESSFUL_CODE.equals(result.getCode())) {
                FileInfoVo vo=result.getData();
                File file = new File();
                file.setFileName(vo.getFileName());
                file.setFileType(vo.getFileExt());
                file.setFileUrl(vo.getFileUrl());
                file.setDocumentType(form.getDocumentType());
                file.setRemoteFileId(NumberUtils.toLong(vo.getId()));
                file.setDocumentId(form.getDocumentId());
                file.setFileDefLineId(form.getFileDefLineId());
                fileService.saveOrUpdate(file);
                log.info("上传文件完成:{}",form.toString());
                return Result.success(new BeanUtils<FileVo>().copyObj(fileService.getById(file.getId()),FileVo.class));
            }
        }
        throw new FSSCException(FsscErrorType.FILE_UPLOAD_FIAL);
    }



}



