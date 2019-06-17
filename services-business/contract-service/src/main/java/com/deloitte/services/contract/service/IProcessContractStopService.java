package com.deloitte.services.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.contract.param.ProcessContractStopQueryForm;
import com.deloitte.services.contract.entity.ProcessContractStop;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description : ProcessContractStop服务类接口
 * @Modified :
 */
public interface IProcessContractStopService extends IService<ProcessContractStop> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<ProcessContractStop>
     */
    IPage<ProcessContractStop> selectPage(ProcessContractStopQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<ProcessContractStop>
     */
    List<ProcessContractStop> selectList(ProcessContractStopQueryForm queryForm);
}
