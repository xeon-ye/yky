package com.deloitte.services.contract.service;

import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.contract.param.ProcessTaskQueryForm;
import com.deloitte.platform.api.contract.vo.TaskNodeActionVO;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.contract.common.enums.VoucherTypeEnums;
import com.deloitte.services.contract.entity.ApprovalVoucher;

import java.util.Map;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description : BPM服务类接口
 * @Modified :
 */
public interface ICommonService {


    /**
     * 获取合同信息
     * @param contractId
     */
    Result getContractByContractId(String contractId);
    /**
     * 获取合同信息
     * @param processId
     */
    Result getContractByProcessId(String processId);
    /**
     * 获取合同信息
     * @param objectId
     */
    Result getContractByObjectId(String objectId);
    /**
     * 获取当前用户信息
     * @param
     */
    UserVo getCurrentUser();
    /**
     * 获取当前用户单位
     * @param
     */
    DeptVo getCurrentDept();

    /**
     * 获取当前用户部门
     * @param
     */
    OrganizationVo getOrganization();

    /**
     * 判断是否能跳转审批
     * @param approvalVoucher
     * @return
     */
    Result getCanjump(ApprovalVoucher approvalVoucher);


    /**
     * 发起一条待阅信息
     * @param userVo
     * @return
     */
    Result sendProcessTask(UserVo userVo, String businessId, VoucherTypeEnums typeEnums);

    Result sendProcessTask(UserVo userVo, String businessId, VoucherTypeEnums typeEnums, String voucherName);
}
