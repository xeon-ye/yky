package com.deloitte.services.fssc.system.file.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.file.param.FileQueryForm;
import com.deloitte.services.fssc.system.file.entity.File;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-03-04
 * @Description : File服务类接口
 * @Modified :
 */
public interface IFileService extends IService<File> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<File>
     */
    IPage<File> selectPage(FileQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<File>
     */
    List<File> selectList(FileQueryForm queryForm);


    List<File> selectListByType(FileQueryForm queryForm);
}
