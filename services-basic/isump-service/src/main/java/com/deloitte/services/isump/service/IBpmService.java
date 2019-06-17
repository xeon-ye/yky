package com.deloitte.services.isump.service;

import com.deloitte.platform.api.bpmservice.vo.NextNodeParamVo;
import com.deloitte.platform.api.isump.param.bpm.ApprovalForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.isump.entity.Dept;
import com.deloitte.services.isump.entity.User;

import java.util.ArrayList;
import java.util.Map;


/**
 * @author zhangdi
 * @Date 11/05/2019
 */
public interface IBpmService{

    /**
     * 发起流程（用户注册）
     * @param user
     * @param objectUrl
     * @return
     */
    Result submitProcess(User user, String objectUrl);

    /**
     * 审批（用户注册）
     * @param approvalForm
     * @return
     */
    Result approvalProcess(ApprovalForm approvalForm);

    /**
     * 发起流程
     * @return
     */
    Result submitProcessOrg(Map<String, String> processVariables, User user, Dept dept);

    /**
     * 流程终止
     * @param approvalForm
     * @return
     */
    Result stopProcess(ApprovalForm approvalForm);

    /**
     * 审批(机构注册)
     * @param approvalForm
     * @return
     */
    Result orgApprovalCheck(ApprovalForm approvalForm);

    /**
     * 获取下一个审批人
     * @return
     */
    Result buildBpmForm(Map<String, String> processVariables);
}
