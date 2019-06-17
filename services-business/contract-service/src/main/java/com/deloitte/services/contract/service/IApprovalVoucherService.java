package com.deloitte.services.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.contract.param.ApprovalVoucherQueryForm;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.services.contract.common.enums.VoucherTypeEnums;
import com.deloitte.services.contract.entity.ApprovalVoucher;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-27
 * @Description : ApprovalVoucher服务类接口
 * @Modified :
 */
public interface IApprovalVoucherService extends IService<ApprovalVoucher> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<ApprovalVoucher>
     */
    IPage<ApprovalVoucher> selectPage(ApprovalVoucherQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<ApprovalVoucher>
     */
    List<ApprovalVoucher> selectList(ApprovalVoucherQueryForm queryForm);
    /**
     * 根据项目编号和单据类型生成一个新单据
     * @param projectId
     * @return
     */
    ApprovalVoucher generateNewVoucher(Long projectId, VoucherTypeEnums typeEnums);

    ApprovalVoucher generateNewVoucher(Long projectId, VoucherTypeEnums typeEnums, String voucherName);

    /**
     * 根据合同id获取合同审批单据
     * @param id
     * @return
     */
    ApprovalVoucher getApprovalFrist(String id);
}
