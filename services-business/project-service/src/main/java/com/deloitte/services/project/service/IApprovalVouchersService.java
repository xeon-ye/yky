package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.ApprovalVouchersQueryForm;
import com.deloitte.services.project.common.enums.VoucherTypeEnums;
import com.deloitte.services.project.entity.ApprovalVouchers;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-22
 * @Description : ApprovalVouchers服务类接口
 * @Modified :
 */
public interface IApprovalVouchersService extends IService<ApprovalVouchers> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<ApprovalVouchers>
     */
    IPage<ApprovalVouchers> selectPage(ApprovalVouchersQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<ApprovalVouchers>
     */
    List<ApprovalVouchers> selectList(ApprovalVouchersQueryForm queryForm);
    /**
     * 根据业务编号生成一个单据
     * @param businessId
     * @return
     */
    ApprovalVouchers generateNewVoucher(String businessId, VoucherTypeEnums typeEnums);
}
