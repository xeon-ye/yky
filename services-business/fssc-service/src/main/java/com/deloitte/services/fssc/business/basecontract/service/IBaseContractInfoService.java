package com.deloitte.services.fssc.business.basecontract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.fssc.basecontract.param.BaseContractInfoQueryForm;
import com.deloitte.platform.api.fssc.basecontract.vo.BaseContractInfoForm;
import com.deloitte.services.fssc.business.basecontract.entity.BaseContractInfo;

import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-17
 * @Description : BaseContractInfo服务类接口
 * @Modified :
 */
public interface IBaseContractInfoService extends IService<BaseContractInfo> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BaseContractInfo>
     */
    IPage<BaseContractInfo> selectPage(BaseContractInfoQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BaseContractInfo>
     */
    List<BaseContractInfo> selectList(BaseContractInfoQueryForm queryForm);

    /**
     * 合同报账完成之后调用的接口
     *
     */
    void sendPlanToContract(Long documentId, String documentType);

    boolean updateBaseContract(BaseContractInfoForm form);


}
