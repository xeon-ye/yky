package com.deloitte.services.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.contract.param.ArchiveQueryForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.contract.entity.Archive;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : yangyq
 * @Date : Create in 2019-06-10
 * @Description : Archive服务类接口
 * @Modified :
 */
public interface IArchiveService extends IService<Archive> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<Archive>
     */
    IPage<Archive> selectPage(ArchiveQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<Archive>
     */
    List<Archive> selectList(ArchiveQueryForm queryForm);

    Result saveArchiveByContractId(Archive archive);
}
