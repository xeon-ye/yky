package com.deloitte.services.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.contract.param.ProcessExecuterTransferQueryForm;
import com.deloitte.services.contract.entity.ProcessExecuterTransfer;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description : ProcessExecuterTransfer服务类接口
 * @Modified :
 */
public interface IProcessExecuterTransferService extends IService<ProcessExecuterTransfer> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<ProcessExecuterTransfer>
     */
    IPage<ProcessExecuterTransfer> selectPage(ProcessExecuterTransferQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<ProcessExecuterTransfer>
     */
    List<ProcessExecuterTransfer> selectList(ProcessExecuterTransferQueryForm queryForm);
}
