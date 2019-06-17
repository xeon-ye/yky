package com.deloitte.platform.basic.fileservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fileservice.param.FileInfoQueryForm;
import com.deloitte.platform.basic.fileservice.entity.FileInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jackliu
 * @Date : Create in 2019-02-19
 * @Description : FileInfo服务类接口
 * @Modified :
 */
public interface IFileInfoService extends IService<FileInfo> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<FileInfo>
     */
    IPage<FileInfo> selectPage(FileInfoQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<FileInfo>
     */
    List<FileInfo> selectList(FileInfoQueryForm queryForm);
}
