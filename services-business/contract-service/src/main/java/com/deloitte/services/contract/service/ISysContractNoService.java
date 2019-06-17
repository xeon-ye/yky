package com.deloitte.services.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.contract.param.SysContractNoQueryForm;
import com.deloitte.services.contract.entity.BasicInfo;
import com.deloitte.services.contract.entity.SysContractNo;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import java.util.Map;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description : SysContractNo服务类接口
 * @Modified :
 */
public interface ISysContractNoService extends IService<SysContractNo> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<SysContractNo>
     */
    IPage<SysContractNo> selectPage(SysContractNoQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<SysContractNo>
     */
    List<SysContractNo> selectList(SysContractNoQueryForm queryForm);

    /**
     * 获取合同编号
     */
    String getContractNo(SysContractNoQueryForm sysContractNoQueryFormr);

    String getContractNoForChange(BasicInfo basicInfo, SysContractNoQueryForm sysContractNoQueryFormr);
}
