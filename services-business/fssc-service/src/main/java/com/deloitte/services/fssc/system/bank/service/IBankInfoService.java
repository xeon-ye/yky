package com.deloitte.services.fssc.system.bank.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.bank.param.BankInfoQueryForm;
import com.deloitte.services.fssc.system.bank.entity.BankInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import java.util.Map;

/**
 * @Author : qiliao
 * @Date : Create in 2019-02-26
 * @Description : BankInfo服务类接口
 * @Modified :
 */
public interface IBankInfoService extends IService<BankInfo> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BankInfo>
     */
    IPage<BankInfo> selectPage(BankInfoQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BankInfo>
     */
    List<BankInfo> selectList(BankInfoQueryForm queryForm);

    /**
     * 根据联合行号查询银行账号
     * @param interLineNum
     * @return
     */
    List<BankInfo> listByInterLineNum(String interLineNum);

    Map<Long,BankInfo> getBankIdBeanMap();
}
