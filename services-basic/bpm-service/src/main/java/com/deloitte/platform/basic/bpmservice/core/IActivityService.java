/**
 * 
 */
package com.deloitte.platform.basic.bpmservice.core;

import com.deloitte.platform.api.bpmservice.vo.BpmTaskNextNodeVo;
import org.activiti.engine.impl.pvm.process.ActivityImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 环节相关service
 * @author yoli
 *
 */
public interface IActivityService
{
	/**
	 * 通过流程实例和任务id获取此任务可以流向的节点信息
	 * @param processInsId
	 * @param taskId
	 * @param processVariables
	 * @return
	 */
	List<BpmTaskNextNodeVo> findNextNodeList(String processDefId,String processInsId, String taskId, Map processVariables);

	/**
	 * 获取当前节点
	 * @param processDefId
	 * @param processInsId
	 * @param taskId
	 * @return
	 */
	BpmTaskNextNodeVo getCurrentNodeInfo(String processDefId,String processInsId, String taskId);

	/**
	 * 根据当前任务ID查询下一节点
	 */
	 ActivityImpl getNextActivity(String taskId,Map<String, Object> params);


	/**
	 * 查询首环节
	 */
	 ActivityImpl getFirstActivity(Map<String, Object> params) ;

	/**
	 * 查询所有的环节
	 */
	 List<ActivityImpl>  getAllActivities(String processDefineKey);

	/**
	 * 获取流程变量
	 */
	 Map<String, Object> getProcessVariables(String processInstanceId);

	/**
	 * 判断是否满足el表达式
	 * @param key
	 * @param el
	 * @param map
	 * @return
	 */
	 boolean isCondition(String key,String el,Map<String, Object> map);
}
