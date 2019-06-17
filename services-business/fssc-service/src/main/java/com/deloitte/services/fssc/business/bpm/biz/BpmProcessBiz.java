package com.deloitte.services.fssc.business.bpm.biz;

import com.deloitte.platform.api.fssc.bpm.vo.ProcessPassOrRejectForm;
import com.deloitte.platform.api.fssc.bpm.vo.ProcessStartForm;
import com.deloitte.platform.api.fssc.bpm.vo.ReSubmitProcessForm;
import com.deloitte.services.fssc.business.pay.entity.PyPamentBusinessLine;

import java.time.LocalDateTime;

/**
 * 工作流 流程相关接口
 */
public interface BpmProcessBiz {
    /**
     * 启动流程接口
     * @param form
     */
    void startProcess(ProcessStartForm form);

    /**
     * 审批通过 被驳回再次提交也调用此接口
     * @param form
     */
    void passProcess(ProcessPassOrRejectForm form);

    /**
     * 阅读消息
     * @param documentId
     */
    void readMessage(Long documentId);
    /**
     * 重新提交
     * @param form
     */
    void reSubmit(ReSubmitProcessForm form);

    /**
     * 驳回给发起人
     * @param form
     */
    void rejectProcessToFirst(ProcessPassOrRejectForm form);

//    /**
//     * 驳回到指定人
//     * @param form
//     * @param destTaskKey 指定任务节点key
//     */
//    void rejectProcessToSomeone(ProcessPassOrRejectForm form,String destTaskKey);

    /**
     * 撤回流程
     * @param form
     */
    void rollBackProcess(ProcessPassOrRejectForm form);



    void doChargeAgainst(Long documentId, String documentType, LocalDateTime defaultEffectiveDate);


    void stopProcess(Long documentId);

    void paymentOrOverDocumentChange(String documentType, Long documentId);

    void carryForward(PyPamentBusinessLine py);
}
