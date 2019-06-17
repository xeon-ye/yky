/**
 * 
 */
package com.deloitte.platform.basic.bpmservice.service;

import com.deloitte.platform.api.bpmservice.vo.NextNodeParamVo;
import com.deloitte.platform.basic.bpmservice.entity.BpmProcessTask;
import org.activiti.engine.impl.pvm.process.ActivityImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 流程服务
 * 
 * @author yoli
 *
 */
public interface IProcessOperatorService
{
	/**
	 * 流程启动
	 * @param participantList 下一环节参与者列表
	 * @param bpmProcessTask 流程参数对象
	 * @return String  ProcessInstanceId 流程实例ID
	 */
	 String start(ArrayList<NextNodeParamVo> participantList, BpmProcessTask bpmProcessTask, Map processVariables);

	/**
	 * 审批通过
//	 * @param taskId 当前任务ID
//	 * @param currentParticipant 当前审批者
	 * @param participantList 下一环节参与者列表
	 * @param bpmProcessTaskParam 流程参数对象
	 * @return
	 */
	String approve(ArrayList<NextNodeParamVo> participantList, BpmProcessTask bpmProcessTaskParam, Map processVariables);


	/**
	 * 任意跳转至审批节点
	 * @param destTaskKey
	 * @param nextNodeParamList
	 * @param bpmProcessTaskParam
	 * @return
	 */
	String jumpToNode(String destTaskKey,ArrayList<NextNodeParamVo> nextNodeParamList,BpmProcessTask bpmProcessTaskParam);


	/**
	 * 审批驳回
//	 * @param taskId 当前任务ID
//	 * @param currentParticipant 当前审批者
	 * @param participant 目标环节参与者
	 * @param  bpmProcessTaskParam 流程参数对象
	 * @return
	 */
	BpmProcessTask reject(String destTaskKey, NextNodeParamVo participant, BpmProcessTask bpmProcessTaskParam, Map processVariables);

	/**
	 * 转办
	 * @param participant 被转办者
	 * @param bpmProcessTask 流程参数对象
	 * @return
	 */
     void turnTo(NextNodeParamVo participant, BpmProcessTask bpmProcessTask);

	/**
	 * 终止流程
	 * @param bpmProcessTask 流程参数对象
	 * @return
	 */
	void stopProcess( BpmProcessTask bpmProcessTask);
	/**
	 * 撤回流程
	 * @param bpmProcessTaskParam 流程参数对象
	 * @param processVariables 流程变量
	 * @return
	 */
	BpmProcessTask rollBack(BpmProcessTask bpmProcessTaskParam,NextNodeParamVo nextNodeParam,Map processVariables);

}
