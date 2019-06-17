package com.deloitte.services.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.contract.param.ProcessOperatorTransferQueryForm;
import com.deloitte.services.contract.entity.ProcessOperatorTransfer;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description : ProcessOperatorTransfer服务类接口
 * @Modified :
 */
public interface IProcessOperatorTransferService extends IService<ProcessOperatorTransfer> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<ProcessOperatorTransfer>
     */
    IPage<ProcessOperatorTransfer> selectPage(ProcessOperatorTransferQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<ProcessOperatorTransfer>
     */
    List<ProcessOperatorTransfer> selectList(ProcessOperatorTransferQueryForm queryForm);
}
