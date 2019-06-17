package com.deloitte.services.project.service;

import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.project.param.ProcessTaskQueryForm;
import com.deloitte.platform.api.project.vo.TaskNodeActionVO;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.project.entity.ApprovalVouchers;

import java.util.Map;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description : BPM服务类接口
 * @Modified :
 */
public interface IBPMService {

    /**
     * 发起审核流程
     * @param voucher
     */
    Result startAuditProcess(ApprovalVouchers voucher, UserVo userVo, OrganizationVo organizationVo, String processDefineKey, Map<String, String> processVariables);

    /**
     * 发起审核流程
     * @param voucher
     */
    Result autoStartAuditProcess(ApprovalVouchers voucher, UserVo userVo, OrganizationVo organizationVo, UserVo acceptVo, String processDefineKey, Map<String, String> processVariables);

    /**
     * 审核流程
     * @param actionVO
     */
    Result auditProcess(TaskNodeActionVO actionVO, UserVo userVo, OrganizationVo organizationVo, Map<String, String> processVariables);

    /**
     * 审核流程
     * @param actionVO
     */
    Result auditProcessWithOutMatrix(TaskNodeActionVO actionVO, UserVo userVo, OrganizationVo organizationVo, UserVo acceptVo, Map<String, String> processVariables);

    /**
     * 驳回
     * @param actionVO
     */
    Result rejectProcess(TaskNodeActionVO actionVO, UserVo userVo, UserVo acceptVo);

    /**
     * 待办
     * @param pageForm
     */
    Result<GdcPage<BpmProcessTaskVo>> searchBackLog(BaseQueryForm pageForm, UserVo userVo);

    /**
     * 已办
     * @param pageForm
     */
    Result<GdcPage<BpmProcessTaskVo>> searchDone(BaseQueryForm pageForm, UserVo userVo);

    /**
     *  转办
     */
    Result turnToProcess(TaskNodeActionVO actionVO, UserVo userVo, OrganizationVo organizationVo, String processDefineKey);


    /**
     * 待办
     * @param processTaskQueryForm
     */
    Result<GdcPage<BpmProcessTaskVo>> backLog(ProcessTaskQueryForm processTaskQueryForm, UserVo userVo);
    /**
     * 已办
     * @param processTaskQueryForm
     */
    Result<GdcPage<BpmProcessTaskVo>> done(ProcessTaskQueryForm processTaskQueryForm, UserVo userVo);
    /**
     * 已办
     * @param actionVO
     */
    Result rollBackProcess(TaskNodeActionVO actionVO, UserVo userVo);
    /**
     * 取消合同
     * @param actionVO
     */
    Result cancel(TaskNodeActionVO actionVO, UserVo userVo);
    /**
     * 获取当前节点可选路径
     * @param actionVO
     * @param processVariables
     * @return
     */
    Result findNextNodeList(TaskNodeActionVO actionVO, Map<String, String> processVariables);


    /**
     * 我的申请
     * @param userVo
     */
    Result<GdcPage<BpmProcessTaskVo>> myApply(ProcessTaskQueryForm processTaskQueryForm, UserVo userVo);
    /**
     * 我的审批
     * @param userVo
     */
    Result<GdcPage<BpmProcessTaskVo>> myApproval(ProcessTaskQueryForm processTaskQueryForm, UserVo userVo);

}
