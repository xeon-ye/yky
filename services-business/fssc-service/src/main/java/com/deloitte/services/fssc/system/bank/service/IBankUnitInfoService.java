package com.deloitte.services.fssc.system.bank.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.fssc.bank.param.BankUnitInfoQueryForm;
import com.deloitte.platform.api.fssc.bank.vo.BankUnitInfoVo;
import com.deloitte.services.fssc.system.bank.entity.BankUnitInfo;

import java.util.List;
import java.util.Map;

/**
 * @Author : qiliao
 * @Date : Create in 2019-02-25
 * @Description : BankInfo服务类接口
 * @Modified :
 */
public interface IBankUnitInfoService extends IService<BankUnitInfo> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BankUnitInfo>
     */
    IPage<BankUnitInfoVo> selectPage(BankUnitInfoQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BankUnitInfo>
     */
    List<BankUnitInfo> selectList(BankUnitInfoQueryForm queryForm);

    /**
     *  条件查询
     * @param bankAccount 银行账户
     * @return List<BankUnitInfo>
     */
    List<BankUnitInfoVo> selectListByBankAccount(String bankAccount);

    /**
     * 返回银行账户和Vo的映射
     * @return
     */
    Map<String,List<BankUnitInfoVo>> getBankAccountVoMap();

    /**
     * 根据联行号和单位编码
     * @param interLineNum
     * @param unitId
     * @return
     */
    List<BankUnitInfo> listByInterLineNum(String interLineNum, String unitId);

    /**
     *
     * @param bankNum
     * @return
     */
    Integer countByBankNum(String bankNum);

    /**
     *
     * @param bankNum
     * @return
     */
    BankUnitInfo getBankUnitInfo(String bankNum);

    /**
     * 返回银行账户和实体的映射
     * @return
     */
    Map<String,BankUnitInfo> getBankNumBeanMap();
}
