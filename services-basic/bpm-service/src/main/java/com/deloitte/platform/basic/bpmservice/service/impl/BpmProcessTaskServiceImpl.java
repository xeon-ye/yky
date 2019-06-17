package com.deloitte.platform.basic.bpmservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryForm;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessImgInfoVo;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessImgTaskVo;
import com.deloitte.platform.api.bpmservice.vo.NextNodeParamVo;
import com.deloitte.platform.basic.bpmservice.entity.BpmProcessTask;
import com.deloitte.platform.basic.bpmservice.mapper.BpmProcessTaskMapper;
import com.deloitte.platform.basic.bpmservice.service.IBpmProcessTaskService;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.activiti.bpmn.model.*;

import org.activiti.bpmn.model.Process;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.event.ObjectChangeListener;
import java.io.InputStream;
import java.util.*;

/**
 * @Author : jackliu
 * @Date : Create in 2019-02-28
 * @Description :  BpmProcessTask服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BpmProcessTaskServiceImpl extends ServiceImpl<BpmProcessTaskMapper, BpmProcessTask> implements IBpmProcessTaskService {


    @Autowired
    private BpmProcessTaskMapper bpmProcessTaskMapper;

    @Override
    public IPage<BpmProcessTask> selectPage(BpmProcessTaskQueryForm queryForm ) {
        QueryWrapper<BpmProcessTask> queryWrapper = new QueryWrapper <BpmProcessTask>();
        getQueryWrapper2(queryWrapper,queryForm);
        return bpmProcessTaskMapper.selectPage(new Page<BpmProcessTask>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);
    }

    @Override
    public IPage<BpmProcessTask> selectPageByWrapper(int currentPage,int pageSize ,QueryWrapper queryWrapper){
        return bpmProcessTaskMapper.selectPage(new Page<BpmProcessTask>(currentPage, pageSize), queryWrapper);
    }

    @Override
    public IPage<BpmProcessTask> selectBackLog(BpmProcessTaskQueryForm queryForm ){
        QueryWrapper<BpmProcessTask> queryWrapper = new QueryWrapper <BpmProcessTask>();
        queryDoneOrBackLog(queryWrapper,queryForm,false);
        return bpmProcessTaskMapper.selectPage(new Page<BpmProcessTask>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);
    }

    @Override
    public IPage<BpmProcessTask> selectDnoe(BpmProcessTaskQueryForm queryForm ) {
        QueryWrapper<BpmProcessTask> queryWrapper = new QueryWrapper <BpmProcessTask>();
        queryDoneOrBackLog(queryWrapper,queryForm,true);
        return bpmProcessTaskMapper.selectPage(new Page<BpmProcessTask>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);
    }

    @Override
    public List<BpmProcessTask> selectList(BpmProcessTaskQueryForm queryForm) {
        QueryWrapper<BpmProcessTask> queryWrapper = new QueryWrapper <BpmProcessTask>();
        getQueryWrapper(queryWrapper,queryForm);
        return bpmProcessTaskMapper.selectList(queryWrapper);

    }

    @Override
    public void updateObjectStatusByProcessId(String objectStatus,String processId){
        bpmProcessTaskMapper.updateObjectStatusByProcessId(objectStatus,processId);
    }

    @Override
    public void updateTaskStatusByPrevious(String taskStatus,String previousTaskId,String processInstanceId){
        bpmProcessTaskMapper.updateTaskStatusByPrevious(taskStatus,previousTaskId,processInstanceId);
    }

    @Override
    public BpmProcessTask findBpmProcessTask(String processInstanceId,String taskId){
        return bpmProcessTaskMapper.findBpmProcessTask(processInstanceId,taskId);
    }

    public boolean workflowEnd(String processInstanceId){
        if(StringUtils.isNotBlank(processInstanceId)){
            if(bpmProcessTaskMapper.countBpmProcessTask(processInstanceId,"已通过")>0){
                return true;
            }
            return  false;
        }else{
            return  false;
        }
    }

    public boolean taskIsEnd(String processInstanceId,String taskId){
        if(StringUtils.isNotBlank(taskId)){
            BpmProcessTask history = findBpmProcessTask(processInstanceId,taskId);
            if(history==null){
                return true;
            }else{
                if("已通过".equals(history.getObjectStauts())){
                    return true;
                }else{
                    if("待重新提交".equals(history.getTaskStauts())){
                        return false;
                    }else if("待审批".equals(history.getTaskStauts())){
                        return false;
                    }else{
                        return true;
                    }
                }
            }
        }else{
            return  false;
        }
    }

    @Autowired
    RepositoryService repositoryService;
    @Autowired
    ProcessEngineConfiguration processEngineConfiguration;
    @Autowired
    ProcessEngineFactoryBean processEngine;
    @Autowired
    HistoryService historyService;

    /**
     * 根据流程实例Id,获取实时流程图片
     *
     * @param processInstanceId
     * @return
     */
    public InputStream getFlowImgByInstantId(String processInstanceId) {

        //获取历史流程实例
        HistoricProcessInstance processInstance =  historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        //获取流程图
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());

        ProcessEngineConfiguration processEngineConfiguration = processEngine.getProcessEngineConfiguration();
        Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);

        ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
        ProcessDefinitionEntity definitionEntity = (ProcessDefinitionEntity)repositoryService.getProcessDefinition(processInstance.getProcessDefinitionId());
        List<HistoricActivityInstance> highLightedActivitList =  historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();

        //高亮环节id集合
        List<String> highLightedActivitis = new ArrayList<String>();
        //高亮线路id集合
        List<String> highLightedFlows = getHighLightedFlows(definitionEntity,highLightedActivitList);

        for(HistoricActivityInstance tempActivity : highLightedActivitList){
            String activityId = tempActivity.getActivityId();
            highLightedActivitis.add(activityId);
        }

        //中文显示的是口口口，设置字体就好了
        //InputStream imageStream = diagramGenerator.generateDiagram(bpmnModel, "png", highLightedActivitis,highLightedFlows,"Microsoft YaHei","Microsoft YaHei","Microsoft YaHei",null,1.0);
        InputStream imageStream = diagramGenerator.generateDiagram(bpmnModel, "png", highLightedActivitis,highLightedFlows,"宋体","宋体","宋体",null,1.0);
        //单独返回流程图，不高亮显示
        //InputStream imageStream = diagramGenerator.generatePngDiagram(bpmnModel);
        return imageStream;

    }

    /**
     * 根据流程实例Id,获取实时流程图片信息
     *
     * @param processInstanceId
     * @return
     */
    public List<BpmProcessImgInfoVo> getFlowImgInfoByInstantId(String processInstanceId) {

        //获取历史流程实例
        HistoricProcessInstance processInstance =  historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        //获取流程图xml对象
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
        Map<String, GraphicInfo> locationMap = bpmnModel.getLocationMap();
        Process process  = bpmnModel.getMainProcess();
        //获取流程历史环节集合
        BpmProcessTaskQueryForm bpmProcessTaskQueryForm = new BpmProcessTaskQueryForm();
        bpmProcessTaskQueryForm.setProcessInstanceId(processInstanceId);
        List<BpmProcessTask> bpmProcessTasks = this.selectList(bpmProcessTaskQueryForm);
        Map<String,List<BpmProcessImgTaskVo>> map = new HashMap<String,List<BpmProcessImgTaskVo>>();
        for(int i=0;i<bpmProcessTasks.size();i++){
            BpmProcessImgTaskVo vo = new BeanUtils<BpmProcessImgTaskVo>().copyObj(bpmProcessTasks.get(i),BpmProcessImgTaskVo.class);
            String taskKey = bpmProcessTasks.get(i).getTaskKey();
            if(map.get(taskKey)==null){
                List<BpmProcessImgTaskVo> bpmProcessImgTaskVoList = new ArrayList<BpmProcessImgTaskVo>();
                bpmProcessImgTaskVoList.add(vo);
                map.put(bpmProcessTasks.get(i).getTaskKey(),bpmProcessImgTaskVoList);
            }else{
                List<BpmProcessImgTaskVo> bpmProcessImgTaskVoList = map.get(taskKey);
                bpmProcessImgTaskVoList.add(vo);
                map.put(taskKey,bpmProcessImgTaskVoList);
            }
        }
        List<BpmProcessImgInfoVo> bpmProcessImgInfoVos =  new ArrayList<BpmProcessImgInfoVo>();
        Iterator<String> iter = locationMap.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            FlowElement flowElement = process.getFlowElement(key);
            if(flowElement instanceof UserTask){
                GraphicInfo value = locationMap.get(key);
                BpmProcessImgInfoVo bpmProcessImgInfoVo = new BpmProcessImgInfoVo();
                bpmProcessImgInfoVo.setX(value.getX());
                bpmProcessImgInfoVo.setY(value.getY());
                bpmProcessImgInfoVo.setWidth(value.getWidth());
                bpmProcessImgInfoVo.setHeight(value.getHeight());
                bpmProcessImgInfoVo.setTaskKey(key);
                bpmProcessImgInfoVo.setTaskName(flowElement.getName());
                bpmProcessImgInfoVo.setParticipants(map.get(key));
                bpmProcessImgInfoVos.add(bpmProcessImgInfoVo);
            }
        }
        return bpmProcessImgInfoVos;

    }

    /**
     * 获取需要高亮的线
     * @param processDefinitionEntity
     * @param historicActivityInstances
     * @return
     */
    private List<String> getHighLightedFlows(
            ProcessDefinitionEntity processDefinitionEntity,
            List<HistoricActivityInstance> historicActivityInstances) {
        List<String> highFlows = new ArrayList<String>();// 用以保存高亮的线flowId
        for (int i = 0; i < historicActivityInstances.size() - 1; i++) {// 对历史流程节点进行遍历
            ActivityImpl activityImpl = processDefinitionEntity
                    .findActivity(historicActivityInstances.get(i)
                            .getActivityId());// 得到节点定义的详细信息
            List<ActivityImpl> sameStartTimeNodes = new ArrayList<ActivityImpl>();// 用以保存后需开始时间相同的节点
            ActivityImpl sameActivityImpl1 = processDefinitionEntity
                    .findActivity(historicActivityInstances.get(i + 1)
                            .getActivityId());
            // 将后面第一个节点放在时间相同节点的集合里
            sameStartTimeNodes.add(sameActivityImpl1);
            for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {
                HistoricActivityInstance activityImpl1 = historicActivityInstances
                        .get(j);// 后续第一个节点
                HistoricActivityInstance activityImpl2 = historicActivityInstances
                        .get(j + 1);// 后续第二个节点
                if (activityImpl1.getStartTime().equals(
                        activityImpl2.getStartTime())) {
                    // 如果第一个节点和第二个节点开始时间相同保存
                    ActivityImpl sameActivityImpl2 = processDefinitionEntity
                            .findActivity(activityImpl2.getActivityId());
                    sameStartTimeNodes.add(sameActivityImpl2);
                } else {
                    // 有不相同跳出循环
                    break;
                }
            }
            List<PvmTransition> pvmTransitions = activityImpl
                    .getOutgoingTransitions();// 取出节点的所有出去的线
            for (PvmTransition pvmTransition : pvmTransitions) {
                // 对所有的线进行遍历
                ActivityImpl pvmActivityImpl = (ActivityImpl) pvmTransition
                        .getDestination();
                // 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示
                if (sameStartTimeNodes.contains(pvmActivityImpl)) {
                    highFlows.add(pvmTransition.getId());
                }
            }
        }
        return highFlows;
    }

    public QueryWrapper<BpmProcessTask> queryDoneOrBackLog(QueryWrapper<BpmProcessTask> queryWrapper,BpmProcessTaskQueryForm queryForm, boolean isDone){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getApproverAcount())){
            queryWrapper.eq(BpmProcessTask.APPROVER_ACOUNT,queryForm.getApproverAcount());
        }
        if(StringUtils.isNotBlank(queryForm.getTaskKey())){
            String taskKey = queryForm.getTaskKey();
            if(taskKey.startsWith("not in")){
                taskKey = taskKey.substring( taskKey.indexOf("(")+1, taskKey.indexOf(")"));
                queryWrapper.notIn(BpmProcessTask.TASK_KEY,taskKey.split(","));
            }else if(taskKey.indexOf(",")>0){
                taskKey = taskKey.substring( taskKey.indexOf("(")+1, taskKey.indexOf(")"));
                queryWrapper.in(BpmProcessTask.TASK_KEY,taskKey.split(","));
            }else{
                queryWrapper.eq(BpmProcessTask.TASK_KEY,queryForm.getTaskKey());
            }

        }
        if(StringUtils.isNotBlank(queryForm.getSourceSystem())){
            //需要支持，查询多个系统的待办
            String sourceSystem = queryForm.getSourceSystem();
            if(sourceSystem.startsWith("not in")){
                sourceSystem = sourceSystem.substring( sourceSystem.indexOf("(")+1, sourceSystem.indexOf(")"));
                queryWrapper.notIn(BpmProcessTask.SOURCE_SYSTEM,sourceSystem.split(","));
            }else if(sourceSystem.indexOf(",")>0){
                sourceSystem = sourceSystem.substring( sourceSystem.indexOf("(")+1, sourceSystem.indexOf(")"));
                queryWrapper.in(BpmProcessTask.SOURCE_SYSTEM,sourceSystem.split(","));
            }else{
                queryWrapper.eq(BpmProcessTask.SOURCE_SYSTEM,queryForm.getSourceSystem());
            }
        }
        if(isDone){
            queryWrapper.notIn(BpmProcessTask.TASK_STAUTS,"待审批","待重新提交");
            queryWrapper.orderByDesc(BpmProcessTask.CREATE_TIME);
        }else{
            //财务系统不要“待重新提交”状态 FSSC
            queryWrapper.and(wrapper1 -> wrapper1.and(
                    wrapper  -> wrapper.eq(BpmProcessTask.SOURCE_SYSTEM,"FSSC").eq(BpmProcessTask.TASK_STAUTS,"待审批"))
                    .or(wrapper  -> wrapper.ne(BpmProcessTask.SOURCE_SYSTEM,"FSSC").in(BpmProcessTask.TASK_STAUTS,"待审批","待重新提交"))
            );
            //queryWrapper.in(BpmProcessTask.TASK_STAUTS,"待审批","待重新提交");
            queryWrapper.orderByDesc(new String[]{BpmProcessTask.EMERGENCY,BpmProcessTask.CREATE_TIME});
        }
        if(StringUtils.isNotBlank(queryForm.getObjectOrderNum())){
            queryWrapper.like(BpmProcessTask.OBJECT_ORDER_NUM,queryForm.getObjectOrderNum());
        }
        if(StringUtils.isNotBlank(queryForm.getObjectStauts())){
            queryWrapper.eq(BpmProcessTask.OBJECT_STAUTS,queryForm.getObjectStauts());
        }
        if(StringUtils.isNotBlank(queryForm.getObjectType())){
            queryWrapper.eq(BpmProcessTask.OBJECT_TYPE,queryForm.getObjectType());
        }
        if(StringUtils.isNotBlank(queryForm.getObjectDescription())){
            queryWrapper.like(BpmProcessTask.OBJECT_DESCRIPTION,queryForm.getObjectDescription());
        }
        if(StringUtils.isNotBlank(queryForm.getPreviousTaskId())){
            queryWrapper.eq(BpmProcessTask.PREVIOUS_TASK_ID,queryForm.getPreviousTaskId());
        }
        if(StringUtils.isNotBlank(queryForm.getEmergency())){
            queryWrapper.eq(BpmProcessTask.EMERGENCY,queryForm.getEmergency());
        }

        return queryWrapper;
    }


    public QueryWrapper<BpmProcessTask> getQueryWrapper2(QueryWrapper<BpmProcessTask> queryWrapper,BpmProcessTaskQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getProcessDefineKey())){
            queryWrapper.eq(BpmProcessTask.PROCESS_DEFINE_KEY,queryForm.getProcessDefineKey());
        }
        if(StringUtils.isNotBlank(queryForm.getProcessInstanceId())){
            queryWrapper.eq(BpmProcessTask.PROCESS_INSTANCE_ID,queryForm.getProcessInstanceId());
        }
        if(StringUtils.isNotBlank(queryForm.getTaskStauts())){
            queryWrapper.eq(BpmProcessTask.TASK_STAUTS,queryForm.getTaskStauts());
        }
        if(StringUtils.isNotBlank(queryForm.getApproverAcount())){
            queryWrapper.eq(BpmProcessTask.APPROVER_ACOUNT,queryForm.getApproverAcount());
        }
        if(StringUtils.isNotBlank(queryForm.getObjectId())){
            queryWrapper.eq(BpmProcessTask.OBJECT_ID,queryForm.getObjectId());
        }
        if(StringUtils.isNotBlank(queryForm.getObjectStauts())){
            queryWrapper.eq(BpmProcessTask.OBJECT_STAUTS,queryForm.getObjectStauts());
        }
        if(StringUtils.isNotBlank(queryForm.getPreviousTaskId())){
            queryWrapper.eq(BpmProcessTask.PREVIOUS_TASK_ID,queryForm.getPreviousTaskId());
        }
        if(StringUtils.isNotBlank(queryForm.getTaskKey())){
            queryWrapper.eq(BpmProcessTask.TASK_KEY,queryForm.getTaskKey());
        }
        if(StringUtils.isNotBlank(queryForm.getSubmitterOrg())){
            queryWrapper.eq(BpmProcessTask.SUBMITTER_ORG,queryForm.getSubmitterOrg());
        }
        if(StringUtils.isNotBlank(queryForm.getSubmitterCode())){
            queryWrapper.eq(BpmProcessTask.SUBMITTER_CODE,queryForm.getSubmitterCode());
        }
        if(StringUtils.isNotBlank(queryForm.getTaskId())){
            queryWrapper.eq(BpmProcessTask.TASK_ID,queryForm.getTaskId());
        }
        if(StringUtils.isNotBlank(queryForm.getEmergency())){
            queryWrapper.eq(BpmProcessTask.EMERGENCY,queryForm.getEmergency());
        }
        if(StringUtils.isNotBlank(queryForm.getSourceSystem())){
            //需要支持，查询多个系统的待办
            String sourceSystem = queryForm.getSourceSystem();
            if(sourceSystem.startsWith("not in")){
                sourceSystem = sourceSystem.substring( sourceSystem.indexOf("(")+1, sourceSystem.indexOf(")"));
                queryWrapper.notIn(BpmProcessTask.SOURCE_SYSTEM,sourceSystem.split(","));
            }else if(sourceSystem.indexOf(",")>0){
                sourceSystem = sourceSystem.substring( sourceSystem.indexOf("(")+1, sourceSystem.indexOf(")"));
                queryWrapper.in(BpmProcessTask.SOURCE_SYSTEM,sourceSystem.split(","));
            }else{
                queryWrapper.eq(BpmProcessTask.SOURCE_SYSTEM,queryForm.getSourceSystem());
            }
        }
        if(queryForm.getProcessCreateTime() != null && StringUtils.isNotBlank(queryForm.getProcessCreateTime().toString())){
            queryWrapper.eq(BpmProcessTask.PROCESS_CREATE_TIME,queryForm.getProcessCreateTime());
        }
        queryWrapper.orderByDesc(BpmProcessTask.CREATE_TIME);
        return queryWrapper;
    }

    /**
     *
     * 通用查询
     * @param queryWrapper,queryForm
     * @return
     * */

    public QueryWrapper<BpmProcessTask> getQueryWrapper(QueryWrapper<BpmProcessTask> queryWrapper,BpmProcessTaskQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getProcessDefineKey())){
            queryWrapper.eq(BpmProcessTask.PROCESS_DEFINE_KEY,queryForm.getProcessDefineKey());
        }
        if(StringUtils.isNotBlank(queryForm.getProcessInstanceId())){
            queryWrapper.eq(BpmProcessTask.PROCESS_INSTANCE_ID,queryForm.getProcessInstanceId());
        }
        if(StringUtils.isNotBlank(queryForm.getTaskId())){
            queryWrapper.eq(BpmProcessTask.TASK_ID,queryForm.getTaskId());
        }
        if(StringUtils.isNotBlank(queryForm.getTaskKey())){
            queryWrapper.eq(BpmProcessTask.TASK_KEY,queryForm.getTaskKey());
        }
        if(StringUtils.isNotBlank(queryForm.getTaskName())){
            queryWrapper.eq(BpmProcessTask.TASK_NAME,queryForm.getTaskName());
        }
        if(StringUtils.isNotBlank(queryForm.getTaskTitle())){
            queryWrapper.eq(BpmProcessTask.TASK_TITLE,queryForm.getTaskTitle());
        }
        if(StringUtils.isNotBlank(queryForm.getTaskDescription())){
            queryWrapper.eq(BpmProcessTask.TASK_DESCRIPTION,queryForm.getTaskDescription());
        }
        if(StringUtils.isNotBlank(queryForm.getTaskStauts())){
            queryWrapper.eq(BpmProcessTask.TASK_STAUTS,queryForm.getTaskStauts());
        }
        if(StringUtils.isNotBlank(queryForm.getApproverAcount())){
            queryWrapper.eq(BpmProcessTask.APPROVER_ACOUNT,queryForm.getApproverAcount());
        }
        if(StringUtils.isNotBlank(queryForm.getApproverName())){
            queryWrapper.eq(BpmProcessTask.APPROVER_NAME,queryForm.getApproverName());
        }
        if(StringUtils.isNotBlank(queryForm.getApproverStation())){
            queryWrapper.eq(BpmProcessTask.APPROVER_STATION,queryForm.getApproverStation());
        }
        if(StringUtils.isNotBlank(queryForm.getApproverDescription())){
            queryWrapper.eq(BpmProcessTask.APPROVER_DESCRIPTION,queryForm.getApproverDescription());
        }
        if(StringUtils.isNotBlank(queryForm.getObjectId())){
            queryWrapper.eq(BpmProcessTask.OBJECT_ID,queryForm.getObjectId());
        }
        if(StringUtils.isNotBlank(queryForm.getObjectOrderNum())){
            queryWrapper.like(BpmProcessTask.OBJECT_ORDER_NUM,queryForm.getObjectOrderNum());
        }
        if(StringUtils.isNotBlank(queryForm.getObjectStauts())){
            queryWrapper.eq(BpmProcessTask.OBJECT_STAUTS,queryForm.getObjectStauts());
        }
        if(StringUtils.isNotBlank(queryForm.getObjectType())){
            queryWrapper.eq(BpmProcessTask.OBJECT_TYPE,queryForm.getObjectType());
        }
        if(StringUtils.isNotBlank(queryForm.getObjectUrl())){
            queryWrapper.eq(BpmProcessTask.OBJECT_URL,queryForm.getObjectUrl());
        }
        if(StringUtils.isNotBlank(queryForm.getObjectDescription())){
            queryWrapper.like(BpmProcessTask.OBJECT_DESCRIPTION,queryForm.getObjectDescription());
        }
        if(StringUtils.isNotBlank(queryForm.getOpinion())){
            queryWrapper.eq(BpmProcessTask.OPINION,queryForm.getOpinion());
        }
        if(StringUtils.isNotBlank(queryForm.getPreviousTaskId())){
            queryWrapper.eq(BpmProcessTask.PREVIOUS_TASK_ID,queryForm.getPreviousTaskId());
        }
        if(StringUtils.isNotBlank(queryForm.getEmergency())){
            queryWrapper.eq(BpmProcessTask.EMERGENCY,queryForm.getEmergency());
        }
        if(queryForm.getCreateTime() != null && StringUtils.isNotBlank(queryForm.getCreateTime().toString())){
            queryWrapper.eq(BpmProcessTask.CREATE_TIME,queryForm.getCreateTime());
        }
        if(queryForm.getProcessCreateTime() != null && StringUtils.isNotBlank(queryForm.getProcessCreateTime().toString())){
            queryWrapper.eq(BpmProcessTask.PROCESS_CREATE_TIME,queryForm.getProcessCreateTime());
        }
        if(queryForm.getUpdateTime() != null && StringUtils.isNotBlank(queryForm.getUpdateTime().toString())){
            queryWrapper.eq(BpmProcessTask.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(queryForm.getDurationTime() != null && StringUtils.isNotBlank(queryForm.getDurationTime().toString())){
            queryWrapper.eq(BpmProcessTask.DURATION_TIME,queryForm.getDurationTime());
        }
        if(StringUtils.isNotBlank(queryForm.getSourceSystem())){
            //需要支持，查询多个系统的待办
            String sourceSystem = queryForm.getSourceSystem();
            if(sourceSystem.startsWith("not in")){
                sourceSystem = sourceSystem.substring( sourceSystem.indexOf("(")+1, sourceSystem.indexOf(")"));
                queryWrapper.notIn(BpmProcessTask.SOURCE_SYSTEM,sourceSystem.split(","));
            }else if(sourceSystem.indexOf(",")>0){
                sourceSystem = sourceSystem.substring( sourceSystem.indexOf("(")+1, sourceSystem.indexOf(")"));
                queryWrapper.in(BpmProcessTask.SOURCE_SYSTEM,sourceSystem.split(","));
            }else{
                queryWrapper.eq(BpmProcessTask.SOURCE_SYSTEM,queryForm.getSourceSystem());
            }
        }
        queryWrapper.orderByDesc(BpmProcessTask.CREATE_TIME);
        return queryWrapper;
    }


}

