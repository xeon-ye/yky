package com.deloitte.platform.api.bpmservice.clinet;

import com.deloitte.platform.api.bpmservice.vo.BpmProcessOperatorApprove;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessOperatorFormStart;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskFormApprove;
import com.deloitte.platform.api.bpmservice.vo.BpmTaskNextNodeForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @Author : jackliu
 * @Date : Create in 11:27 21/02/2019
 * @Description :
 * @Modified :
 */
public interface ProcessOperatorClient {

    public static final String path="/bpmservice/bpmProcessTask/";
    /**
     *  流程启动
     */
    @PostMapping(value= path+"startProcess")
    Result startProcess(BpmProcessOperatorFormStart bpmProcessParamForm);

     /**
      *  审批通过
      */
    @PostMapping(value = path+"approveProcess")
    Result approveProcess(BpmProcessOperatorApprove bpmProcessParamForm);

    /**
     *  审批提交至任意节点
     */
    @PostMapping(value = path+"approveProcessToNode")
    Result approveProcessToNode(BpmProcessOperatorApprove bpmProcessParamForm);

    /**
     *  流程签收
     */
    @PostMapping(value = path+"signTask/{id}")
    Result signTask(@PathVariable(value="id") long id);

    /**
     *  审批驳回给发起人
     */
    @PostMapping(value = path+"rejectToFirstProcess")
    Result rejectToFirstProcess(BpmProcessOperatorApprove bpmProcessParamForm);

    /**
     *  审批驳回
     */
    @PostMapping(value = path+"rejectProcess/{destTaskKey}")
    Result rejectProcess(@PathVariable(value="destTaskKey")String destTaskKey, @RequestBody BpmProcessOperatorApprove bpmProcessParamForm);


    /**
     *  转办
     */
    @PostMapping(value = path+"turnToProcess")
    Result turnToProcess(BpmProcessOperatorApprove bpmProcessParamForm);


    /**
     *  终止流程
     */
    @PostMapping(value = path+"stopProcess")
    Result stopProcess( BpmProcessTaskFormApprove bpmProcessTask);

    /**
     *  撤回流程
     */
    @PostMapping(value = path+"rollBackProcess")
    Result rollBackProcess(BpmProcessOperatorApprove bpmProcessTask);

    /**
     * 获取当前节点可选路径
     * @param bpmTaskNextNodeForm
     * @return
     */
    @PostMapping(value=path+"findNextNode")
    Result findNextNodeList(@Valid @RequestBody BpmTaskNextNodeForm bpmTaskNextNodeForm);

}
