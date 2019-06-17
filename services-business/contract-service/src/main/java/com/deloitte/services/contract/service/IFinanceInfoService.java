package com.deloitte.services.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.contract.param.FinanceInfoQueryForm;
import com.deloitte.platform.api.contract.vo.BasicInfoVo;
import com.deloitte.platform.api.contract.vo.FinanceInfoVo;
import com.deloitte.platform.api.contract.vo.FinanceInfoVoToFssc;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.contract.entity.FinanceInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description : FinanceInfo服务类接口
 * @Modified :
 */
public interface IFinanceInfoService extends IService<FinanceInfo> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<FinanceInfo>
     */
    IPage<FinanceInfo> selectPage(FinanceInfoQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<FinanceInfo>
     */
    List<FinanceInfo> selectList(FinanceInfoQueryForm queryForm);

    //根据contractId查询财务信息
    List<FinanceInfoVo> selectFinanceInfo(String contractId);

    List<FinanceInfoVo> saveFinanceInfoList(BasicInfoVo basicInfoVo, UserVo userVo);

    /**
     * 保存财务系统返回财务信息
     * @param listFinanceInfoVo
     * @return
     */
    boolean saveFinanceInfo(List<FinanceInfoVoToFssc> listFinanceInfoVo);

    /**
     * 查询合同履行计划财务信息推送财务系统
     * @param contractId
     */
    Result selectFinanceToFssc(String contractId);
}
