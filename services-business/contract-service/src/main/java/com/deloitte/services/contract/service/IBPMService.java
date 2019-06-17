package com.deloitte.services.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.bpmservice.param.BpmConductListQueryForm;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryForm;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryParam;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessOperatorApprove;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.bpmservice.vo.NextNodeParamVo;
import com.deloitte.platform.api.contract.param.BasicInfoQueryForm;
import com.deloitte.platform.api.contract.param.ProcessTaskQueryForm;
import com.deloitte.platform.api.contract.vo.ProcessTaskForm;
import com.deloitte.platform.api.contract.vo.TaskNodeActionVO;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.contract.common.enums.VoucherTypeEnums;
import com.deloitte.services.contract.entity.ApprovalVoucher;
import com.deloitte.services.contract.entity.BasicInfo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
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
    Result startAuditProcess(ApprovalVoucher voucher, UserVo userVo, OrganizationVo organizationVo, String processDefineKey, Map<String,String> processVariables);

    /**
     * 发起审核流程
     * @param voucher
     */
    Result autoStartAuditProcess(ApprovalVoucher voucher, UserVo userVo, OrganizationVo organizationVo, UserVo acceptVo, String processDefineKey, Map<String,String> processVariables);

    /**
     * 审核流程
     * @param actionVO
     */
    Result auditProcess(TaskNodeActionVO actionVO, UserVo userVo, OrganizationVo organizationVo, Map<String,String> processVariables);

    /**
     * 审核流程
     * @param actionVO
     */
    Result auditProcessWithOutMatrix(TaskNodeActionVO actionVO, UserVo userVo, OrganizationVo organizationVo,UserVo acceptVo, Map<String,String> processVariables);
    /**
     * 审核流程
     * @param actionVO
     */
    Result auditProcessWithOutMatrix(TaskNodeActionVO actionVO, UserVo userVo, OrganizationVo organizationVo, ArrayList<NextNodeParamVo> nextNodeParamVo, Map<String,String> processVariables);


    /**
     * 驳回
     * @param actionVO
     */
    Result rejectProcess(TaskNodeActionVO actionVO, UserVo userVo,UserVo acceptVo);

    /**
     * 待办
     * @param pageForm
     */
    Result<GdcPage<BpmProcessTaskVo>> searchBackLog(BaseQueryForm pageForm, UserVo userVo);

    /**
     * 已办
     * @param pageForm
     */
    Result<GdcPage<BpmProcessTaskVo>> searchDone(BaseQueryForm pageForm,UserVo userVo);

    /**
     *  转办
     */
    Result turnToProcess(TaskNodeActionVO actionVO, UserVo userVo, OrganizationVo organizationVo,String processDefineKey);


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
    Result findNextNodeList(TaskNodeActionVO actionVO,Map<String,String> processVariables);

}
