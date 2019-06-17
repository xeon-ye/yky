package com.deloitte.services.fssc.business.ppc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.ppc.param.ContractInformationQueryForm;
import com.deloitte.services.fssc.business.ppc.entity.ContractInformation;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-01
 * @Description : ContractInformation服务类接口
 * @Modified :
 */
public interface IContractInformationService extends IService<ContractInformation> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<ContractInformation>
     */
    IPage<ContractInformation> selectPage(ContractInformationQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<ContractInformation>
     */
    List<ContractInformation> selectList(ContractInformationQueryForm queryForm);
}
