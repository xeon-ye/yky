/**
 *
 */
package com.deloitte.platform.basic.bpmservice.core.activiti;

import com.deloitte.platform.api.bpmservice.vo.BpmTaskNextNodeVo;
import com.deloitte.platform.basic.bpmservice.exception.BpmErrorType;
import com.deloitte.platform.common.core.common.IErrorType;
import com.deloitte.platform.common.core.exception.ServiceException;
import com.netflix.discovery.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.editor.language.json.converter.util.CollectionUtils;
import com.deloitte.platform.basic.bpmservice.properties.ActivitiProcessProperties;
import com.deloitte.platform.basic.bpmservice.core.IActivityService;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.MultiInstanceActivityBehavior;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.javax.el.ExpressionFactory;
import org.activiti.engine.impl.javax.el.ValueExpression;
import org.activiti.engine.impl.juel.ExpressionFactoryImpl;
import org.activiti.engine.impl.juel.SimpleContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * 环节相关service
 *
 * @author yoli
 *
 */
@Service
@Slf4j
public class ActivityServiceImpl implements IActivityService
{
    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    ActivitiProcessProperties activitiProcessProperties;

    @Autowired
    private RepositoryService repositoryService;

    /**
     * 查询所有的环节
     */
    @Override
    public List<ActivityImpl> getAllActivities(String processDefineKey)
    {
        List<ActivityImpl> activityImplList = new ArrayList<ActivityImpl>();

        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefineKey).latestVersion().singleResult();

        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(pd.getId());

        //筛选出人工任务
        if (CollectionUtils.isNotEmpty(processDefinition.getActivities()))
        {
            for(ActivityImpl activityImpl : processDefinition.getActivities())
            {
                //提交人的环节需要排除掉
                if("userTask".equals(activityImpl.getProperty("type")) && !"start".equals(activityImpl.getId()))
                {
                    activityImplList.add(activityImpl);
                }
            }
        }
        else
        {
            return null;
        }

        return activityImplList;
    }


    /**
     * 查询首环节
     *
     * @throws Exception
     */
    @Override
    public ActivityImpl getFirstActivity(Map<String, Object> params)
    {
        // 流程定义ID
        String processDefineKey = (String) params.get("processDefineKey");

        if (StringUtils.isEmpty(processDefineKey))
        {
            throw new ServiceException(BpmErrorType.BPM_PROCESS_DEFINE_KEY_IS_EMPTY);
        }

        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefineKey).latestVersion().singleResult();

        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(pd.getId());

        List<ActivityImpl> activitiList = processDefinition.getActivities();

        // 遍历所有节点信息
        for (ActivityImpl activityImpl : activitiList)
        {
            if ("start".equals(activityImpl.getId()))
            {
                // 获取提交任务节点的下一个节点信息
                return nextActivityImpl(activityImpl, activityImpl.getId(), params);
            }
        }
        return null;
    }

    public BpmTaskNextNodeVo getCurrentNodeInfo(String processDefId,String processInsId, String taskId){
        BpmTaskNextNodeVo nodeInfo = new BpmTaskNextNodeVo();
        String processInstanceId = processInsId;
        String definitionId = "";
        ProcessDefinitionEntity processDefinitionEntity = null;
        String currentTaskKey = "";
        if("start".equalsIgnoreCase(taskId)){
            //表示第一节点
            ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefId).latestVersion().singleResult();
            definitionId = pd.getId();
            currentTaskKey = taskId;
            nodeInfo.setFirstNode(true);
            nodeInfo.setEndNode(false);
        }else {
            if (processInstanceId == null || "".equals(processInstanceId)) {
                //获取流程实例id
                processInstanceId = taskService.createTaskQuery().taskId(taskId).singleResult().getProcessInstanceId();
            }
            //根据流程实例id查询流程实例对象
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).orderByProcessInstanceId().desc().singleResult();
            //获取流程定义id信息
            definitionId = processInstance.getProcessDefinitionId();
            currentTaskKey = processInstance.getActivityId();
        }

        //根据流程定义id得到流程定义对象
        processDefinitionEntity = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(definitionId);
        // 当前流程节点Id信息
        ActivityImpl currentActiviti = processDefinitionEntity.findActivity(currentTaskKey);
        String currentActivitiId=null;
        if(currentActiviti==null){
            //可能是多实例节点，无法通过processDefinitionEntity获取当前运行的节点
            Task task=taskService.createTaskQuery().taskId(taskId).singleResult();
            currentActivitiId=task.getTaskDefinitionKey();
            currentActiviti = processDefinitionEntity.findActivity(currentActivitiId);
        }else{
            currentActivitiId = currentActiviti.getId();
        }
        if("start".equals(currentActivitiId)){
            nodeInfo.setFirstNode(true);
            nodeInfo.setEndNode(false);
        }
        if(!"endEvent".equals(currentActiviti.getProperty("type"))) {
//							TaskDefinition taskDefinition = ((UserTaskActivityBehavior) destinationActivity.getActivityBehavior()).getTaskDefinition();
//							nodeInfo.setTaskNodeName(taskDefinition.getNameExpression().getExpressionText());
            TaskDefinition taskDefinition =(TaskDefinition) currentActiviti.getProperty("taskDefinition");
            nodeInfo.setTaskNodeName(taskDefinition.getNameExpression().getExpressionText());
            nodeInfo.setTaskKey(currentActivitiId);
        }else{
            nodeInfo.setFirstNode(false);
            nodeInfo.setEndNode(true);
            nodeInfo.setTaskNodeName("结束");
            nodeInfo.setFirstNode(false);
            nodeInfo.setEndNode(true);
            nodeInfo.setTaskKey("end");
        }

        Object multiInstance = currentActiviti.getProperty("multiInstance");
        //完成条件的表达式
        if (multiInstance != null) {
            nodeInfo.setMulti(true);
            nodeInfo.setReject(false);
        }else{
            nodeInfo.setMulti(false);
        }
        return nodeInfo;
    }

    public List<BpmTaskNextNodeVo> findNextNodeList(String processDefId,String processInsId, String taskId,Map processVariables){
        List<BpmTaskNextNodeVo> list = new ArrayList<BpmTaskNextNodeVo>();
        String processInstanceId = processInsId;
        boolean flag = false;
        if(processVariables==null||processVariables.size()==0){
            flag = true;
        }
        String definitionId = "";
        ProcessDefinitionEntity processDefinitionEntity = null;
        String currentTaskKey = "";
        if("start".equalsIgnoreCase(taskId)){
            //表示第一节点
            ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefId).latestVersion().singleResult();
            definitionId = pd.getId();
            currentTaskKey = taskId;
        }else {
            if (processInstanceId == null || "".equals(processInstanceId)) {
                //获取流程实例id
                processInstanceId = taskService.createTaskQuery().taskId(taskId).singleResult().getProcessInstanceId();
            }
            //根据流程实例id查询流程实例对象
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).orderByProcessInstanceId().desc().singleResult();
            //获取流程定义id信息
            definitionId = processInstance.getProcessDefinitionId();
            currentTaskKey = processInstance.getActivityId();
        }

        //根据流程定义id得到流程定义对象
        processDefinitionEntity = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(definitionId);
        // 当前流程节点Id信息
        ActivityImpl currentActiviti = processDefinitionEntity.findActivity(currentTaskKey);
        String currentActivitiId=null;
        if(currentActiviti==null){
            //可能是多实例节点，无法通过processDefinitionEntity获取当前运行的节点
            Task task=taskService.createTaskQuery().taskId(taskId).singleResult();
            currentActivitiId=task.getTaskDefinitionKey();
        }else{
            currentActivitiId = currentActiviti.getId();
        }
        // 获取流程所有节点信息
        List<ActivityImpl> activitiList = processDefinitionEntity.getActivities();

        Map<String, Object> variables = new HashMap<String, Object>();
        if(!"start".equalsIgnoreCase(taskId)) {
            variables = getProcessVariables(processInstanceId);
        }
        variables.putAll(processVariables);

        // 遍历所有节点信息
        for (ActivityImpl activityImpl : activitiList)
        {
            if (currentActivitiId.equals(activityImpl.getId()))
            { // 获取下一个节点信息
                //ActivityImpl destActivityImpl =
                if("start".equalsIgnoreCase(taskId)){
                    list.addAll(nextActivityAllImpl(activityImpl, activityImpl.getId(), variables, flag));
                }else{
                    BpmTaskNextNodeVo bpmTaskCurrentNodeVo = currentActivityEnd(activityImpl,processInstanceId);
                    if(bpmTaskCurrentNodeVo==null) {
                        //当前节点不是多实例串行
                        list.addAll(nextActivityAllImpl(activityImpl, activityImpl.getId(), variables, flag));
                    }else{
                        list.add(bpmTaskCurrentNodeVo);
                    }
                }
                break;
            }
        }
        return list;
    }

    private BpmTaskNextNodeVo currentActivityEnd(ActivityImpl activityImpl,String processInstanceId){
        //针对串行节点，判断当前节点是否结束
        Object multiInstance = activityImpl.getProperty("multiInstance");
        //完成条件的表达式
        if (multiInstance != null) {
            if ("sequential".equalsIgnoreCase(multiInstance.toString())) {
                String expression = ((MultiInstanceActivityBehavior)activityImpl.getActivityBehavior()).getCompletionConditionExpression().getExpressionText();
                //串行节点
                List<Execution> executionList = runtimeService.createExecutionQuery().activityId(activityImpl.getId()).processInstanceId(processInstanceId).list();
                if (executionList != null && executionList.size() == 1) {
                    Object nrOfInstances = runtimeService.getVariable(executionList.get(0).getId(), "nrOfInstances");
                    Object nrOfCompletedInstances = runtimeService.getVariable(executionList.get(0).getId(), "nrOfCompletedInstances");
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("nrOfCompletedInstances", new BigDecimal(nrOfCompletedInstances.toString()).add(new BigDecimal("1")).toString());
                    map.put("nrOfInstances", nrOfInstances);
                    if(isCondition("",expression,map)){
                        //满足提交到下一环节的条件，则返回null
                        return null;
                    }else{
                        //不满足，则返回当前节点
                        BpmTaskNextNodeVo nodeInfo = new BpmTaskNextNodeVo();
                        nodeInfo.setTaskKey(activityImpl.getId());
                        TaskDefinition taskDefinition =(TaskDefinition) activityImpl.getProperty("taskDefinition");
                        nodeInfo.setTaskNodeName(taskDefinition.getNameExpression().getExpressionText());
                        nodeInfo.setMulti(false);
                        nodeInfo.setReject(false);
                        nodeInfo.setEndNode(false);
                        nodeInfo.setFirstNode(false);
                        return nodeInfo;
                    }
                }
            }
        }
        return null;
    }

    private List<BpmTaskNextNodeVo> nextActivityAllImpl(ActivityImpl activityImpl, String activityId, Map<String, Object> variables, boolean flag)
    {
        PvmActivity ac = null;
        Object s = null;
        List<BpmTaskNextNodeVo> nextNextList = new ArrayList<BpmTaskNextNodeVo>();
        // 如果遍历节点为用户任务或者流程终止任务并且节点不是当前节点信息
        // 获取节点所有流向线路信息
        List<PvmTransition> outTransitions = activityImpl.getOutgoingTransitions();
        List<PvmTransition> outTransitionsTemp = null;
        for (PvmTransition tr : outTransitions)
        {
            ac = tr.getDestination();
            // 获取线路的终点节点 // 如果流向线路为排他网关
            if ("exclusiveGateway".equals(ac.getProperty("type")))
            {
                outTransitionsTemp = ac.getOutgoingTransitions();

                // 如果排他网关只有一条线路信息
                if (outTransitionsTemp.size() == 1)
                {
                    BpmTaskNextNodeVo nodeInfo = new BpmTaskNextNodeVo();
                    ActivityImpl destinationActivity = (ActivityImpl)outTransitionsTemp.get(0).getDestination();
                    Object multiInstance = destinationActivity.getProperty("multiInstance");
                    if (multiInstance != null) {
                        if ("sequential".equalsIgnoreCase(multiInstance.toString())) {
                            nodeInfo.setMulti(true);
                            nodeInfo.setReject(false);
                            nodeInfo.setEndNode(false);
                            nodeInfo.setFirstNode(false);
                        }else if("parallel".equalsIgnoreCase(multiInstance.toString())){
                            nodeInfo.setMulti(true);
                            nodeInfo.setReject(false);
                            nodeInfo.setEndNode(false);
                            nodeInfo.setFirstNode(false);
                        }else{
                            nodeInfo.setMulti(false);
                        }
                    }else{
                        nodeInfo.setMulti(false);
                    }
                    nodeInfo.setTaskKey(destinationActivity.getId());
                    if(!"endEvent".equals(destinationActivity.getProperty("type"))) {
//							TaskDefinition taskDefinition = ((UserTaskActivityBehavior) destinationActivity.getActivityBehavior()).getTaskDefinition();
//							nodeInfo.setTaskNodeName(taskDefinition.getNameExpression().getExpressionText());
                        TaskDefinition taskDefinition =(TaskDefinition) destinationActivity.getProperty("taskDefinition");
                        nodeInfo.setTaskNodeName(taskDefinition.getNameExpression().getExpressionText());
                    }else{
                        nodeInfo.setFirstNode(false);
                        nodeInfo.setEndNode(true);
                        nodeInfo.setTaskNodeName("结束");
                    }
                    String conditionText = s == null ? "" : s.toString().replace("$","");
                    nodeInfo.setConditionText(conditionText);
                    nextNextList.add(nodeInfo);
                    //return nextActivityImpl((ActivityImpl) outTransitionsTemp.get(0).getDestination(), activityId, variables);
                }
                else if (outTransitionsTemp.size() > 1)
                {
                    // 如果排他网关有多条线路信息
                    for (PvmTransition tr1 : outTransitionsTemp)
                    {
                        s = tr1.getProperty("conditionText");
                        //表示不传规则，直接获取所有流向
                        ActivityImpl destinationActivity = (ActivityImpl)tr1.getDestination();
                        BpmTaskNextNodeVo nodeInfo = new BpmTaskNextNodeVo();
                        Object multiInstance = destinationActivity.getProperty("multiInstance");
                        if (multiInstance != null) {
                            if ("sequential".equalsIgnoreCase(multiInstance.toString())) {
                                nodeInfo.setMulti(true);
                                nodeInfo.setReject(false);
                            }else if("parallel".equalsIgnoreCase(multiInstance.toString())){
                                nodeInfo.setMulti(true);
                                nodeInfo.setReject(false);
                            }else{
                                nodeInfo.setMulti(false);
                            }
                        }else{
                            nodeInfo.setMulti(false);
                        }
                        nodeInfo.setTaskKey(destinationActivity.getId());
                        if(!"endEvent".equals(destinationActivity.getProperty("type"))) {
//								TaskDefinition taskDefinition = ((UserTaskActivityBehavior)destinationActivity.getActivityBehavior()).getTaskDefinition();
//								nodeInfo.setTaskNodeName(taskDefinition.getNameExpression().getExpressionText());
                            TaskDefinition taskDefinition =(TaskDefinition) destinationActivity.getProperty("taskDefinition");
                            nodeInfo.setTaskNodeName(taskDefinition.getNameExpression().getExpressionText());
                        }else{
                            nodeInfo.setMulti(false);
                            nodeInfo.setFirstNode(false);
                            nodeInfo.setEndNode(true);
                            nodeInfo.setTaskNodeName("结束");
                        }
                        String conditionText = s == null ? "" : s.toString().replace("$","");
                        nodeInfo.setConditionText(conditionText);
                        if(flag){
                            nextNextList.add(nodeInfo);
                        }else {
                            // 获取排他网关线路判断条件信息判断el表达式是否成立
                            if (isCondition(ac.getId(), StringUtils.trim(s.toString()), variables)) {
                                nextNextList.add(nodeInfo);
                                //return nextActivityImpl((ActivityImpl) tr1.getDestination(), activityId, variables);
                            }
                        }
                    }
                }
            }
            else if ("userTask".equals(ac.getProperty("type")) || "endEvent".equals(ac.getProperty("type")))
            {
                BpmTaskNextNodeVo nodeInfo = new BpmTaskNextNodeVo();
                ActivityImpl destinationActivity = (ActivityImpl)ac;
                Object multiInstance = destinationActivity.getProperty("multiInstance");
                if (multiInstance != null) {
                    if ("sequential".equalsIgnoreCase(multiInstance.toString())) {
                        nodeInfo.setMulti(true);
                        nodeInfo.setReject(false);
                    }else if("parallel".equalsIgnoreCase(multiInstance.toString())){
                        nodeInfo.setMulti(true);
                        nodeInfo.setReject(false);
                    }else{
                        nodeInfo.setMulti(false);
                    }
                }else{
                    nodeInfo.setMulti(false);
                }
                nodeInfo.setTaskKey(destinationActivity.getId());
                if(!"endEvent".equals(ac.getProperty("type"))) {
//						TaskDefinition taskDefinition = ((UserTaskActivityBehavior) destinationActivity.getActivityBehavior()).getTaskDefinition();
//						nodeInfo.setTaskNodeName(taskDefinition.getNameExpression().getExpressionText());
                    TaskDefinition taskDefinition =(TaskDefinition) destinationActivity.getProperty("taskDefinition");
                    nodeInfo.setTaskNodeName(taskDefinition.getNameExpression().getExpressionText());
                }else{
                    nodeInfo.setMulti(false);
                    nodeInfo.setFirstNode(false);
                    nodeInfo.setEndNode(true);
                    nodeInfo.setTaskNodeName("结束");
                }
                String conditionText = s == null ? "" : s.toString().replace("$","");
                nodeInfo.setConditionText(conditionText);
                nextNextList.add(nodeInfo);
                //return (ActivityImpl) ac;
            }

        }
        return nextNextList;

    }

    /**
     * 根据当前任务ID查询下一节点
     */
    @Override
    public ActivityImpl getNextActivity(String taskId,Map<String, Object> params)
    {
        ProcessDefinitionEntity processDefinitionEntity = null;
        //获取流程实例Id信息
        Task taskInstance = taskService.createTaskQuery().taskId(taskId).singleResult();
        if(taskInstance == null){
            throw new ServiceException(BpmErrorType.BPM_TASK_COMPLETE);
        }
        String processInstanceId = taskInstance.getProcessInstanceId();
        //根据流程实例id查询流程实例对象
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).orderByProcessInstanceId().desc().singleResult();
        if(processInstance==null){
            throw new ServiceException(BpmErrorType.BPM_PROCESS_COMPLETE);
        }
        //获取流程定义id信息
        String definitionId = processInstance.getProcessDefinitionId();
        //根据流程定义id得到流程定义对象
        processDefinitionEntity = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(definitionId);
        // 当前流程节点Id信息
        ActivityImpl currentActiviti = processDefinitionEntity.findActivity(processInstance.getActivityId());
        String currentActivitiId=null;
        if(currentActiviti==null){
            //可能是多实例节点，无法通过processDefinitionEntity获取当前运行的节点
            Task task=taskService.createTaskQuery().taskId(taskId).singleResult();
            currentActivitiId=task.getTaskDefinitionKey();
        }else{
            currentActivitiId = currentActiviti.getId();
        }
        // 获取流程所有节点信息
        List<ActivityImpl> activitiList = processDefinitionEntity.getActivities();

        Map<String, Object> variables = getProcessVariables(processInstanceId);
        variables.putAll(params);

        // 遍历所有节点信息
        for (ActivityImpl activityImpl : activitiList)
        {
            if (currentActivitiId.equals(activityImpl.getId()))
            { // 获取下一个节点信息
                BpmTaskNextNodeVo bpmTaskCurrentNodeVo = currentActivityEnd(activityImpl,processInstanceId);
                if(bpmTaskCurrentNodeVo!=null) {
                    //当前节点为多实例串行节点，且未达到提交至下一节点条件
                    return activityImpl;
                }
                return nextActivityImpl(activityImpl, activityImpl.getId(), variables);
            }
        }

        return null;
    }


    /**
     * 下一个任务节点信息,
     *
     * 如果下一个节点为用户任务则直接返回,
     *
     * 如果下一个节点为排他网关, 获取排他网关Id信息, 根据排他网关Id信息和execution获取流程实例排他网关Id为key的变量值,
     * 根据变量值分别执行排他网关后线路中的el表达式, 并找到el表达式通过的线路后的用户任务
     *
     * @param activityImpl
     *            activityImpl 流程节点信息
     * @param activityId
     *            activityId 当前流程节点Id信息
     * @param variables
     *            elString 排他网关顺序流线段判断条件
     * @return
     */
    private ActivityImpl nextActivityImpl(ActivityImpl activityImpl, String activityId, Map<String, Object> variables)
    {
        PvmActivity ac = null;
        Object s = null;

        // 如果遍历节点为用户任务或者流程终止任务并且节点不是当前节点信息
        if (("userTask".equals(activityImpl.getProperty("type")) || "endEvent".equals(activityImpl.getProperty("type"))) && !activityId.equals(activityImpl.getId()))
        {
            return activityImpl;
        }
        else
        {
            // 获取节点所有流向线路信息
            List<PvmTransition> outTransitions = activityImpl.getOutgoingTransitions();
            List<PvmTransition> outTransitionsTemp = null;
            for (PvmTransition tr : outTransitions)
            {
                ac = tr.getDestination();
                // 获取线路的终点节点 // 如果流向线路为排他网关
                if ("exclusiveGateway".equals(ac.getProperty("type")))
                {
                    outTransitionsTemp = ac.getOutgoingTransitions();

                    // 如果排他网关只有一条线路信息
                    if (outTransitionsTemp.size() == 1)
                    {
                        return nextActivityImpl((ActivityImpl) outTransitionsTemp.get(0).getDestination(), activityId, variables);
                    }
                    else if (outTransitionsTemp.size() > 1)
                    {
                        // 如果排他网关有多条线路信息
                        for (PvmTransition tr1 : outTransitionsTemp)
                        {
                            s = tr1.getProperty("conditionText");

                            // 获取排他网关线路判断条件信息判断el表达式是否成立
                            if (isCondition(ac.getId(), StringUtils.trim(s.toString()), variables))
                            {
                                return nextActivityImpl((ActivityImpl) tr1.getDestination(), activityId, variables);
                            }
                        }
                    }
                }
                else if ("userTask".equals(ac.getProperty("type")) || "endEvent".equals(ac.getProperty("type")))
                {
                    return (ActivityImpl) ac;
                }

            }
            return null;
        }
    }

    /**
     * 根据key和value判断el表达式是否通过信息
     *
     * @param key
     *            key el表达式key信息
     * @param el
     *            el el表达式信息
     * @param variables
     *            value el表达式传入值信息
     * @return
     */
    @Override
    public boolean isCondition(String key, String el, Map<String, Object> variables)
    {

        ExpressionFactory factory = new ExpressionFactoryImpl();

        SimpleContext context = new SimpleContext();

        for (String k : variables.keySet())
        {
            if (variables.get(k) != null)
            {
                if(NumberUtils.isNumber(variables.get(k).toString())&&variables.get(k).toString().indexOf(".")>0){
                    context.setVariable(k.toString(), factory.createValueExpression(variables.get(k), Double.class));
                }else{
                    context.setVariable(k.toString(), factory.createValueExpression(variables.get(k), variables.get(k).getClass()));
                }

            }
        }

        ValueExpression e = factory.createValueExpression(context, el, boolean.class);

        return (Boolean) e.getValue(context);
    }

    public static void main(String[] args){
        Map map = new HashMap();
        ActivityServiceImpl s = new ActivityServiceImpl();
        map.put("nrOfCompletedInstances","2.33");
        map.put("nrOfInstances","2");
        System.out.println(s.isCondition("","${nrOfCompletedInstances>4}",map));
    }

    /**
     *  获取流程变量
     * @param processInstanceId 流程实例ID
     * @return
     */
    @Override
    public Map<String, Object> getProcessVariables(String processInstanceId)
    {
        Map<String, Object> variables=new HashMap<>();
        List<Execution>  executionList=runtimeService.createExecutionQuery().processInstanceId(processInstanceId).list();
        variables=runtimeService.getVariables(executionList.get(0).getId());
        //	Execution executionOne = runtimeService.createExecutionQuery().processInstanceId(processInstanceId).singleResult();
        return variables;
    }


    /**
     *  获取所有的节点
     * @param processDefinitionId 流程定义ID
     * @return
     */
    public Collection<FlowElement> getAllFlowElement(String processDefinitionId){
        BpmnModel model = repositoryService.getBpmnModel(processDefinitionId);

        if(model != null) {
            Collection<FlowElement> flowElements = model.getMainProcess().getFlowElements();
            /*
             for (FlowElement e : flowElements) {
				System.out.println("flowelement id:" + e.getId() + "  name:" + e.getName() + "   class:" + e.getClass().toString());
				flowelement id:startevent1  name:Start   class:class org.activiti.bpmn.model.StartEvent
				flowelement id:deptLeaderAudit  name:部门领导审批   class:class org.activiti.bpmn.model.UserTask
			}*/

            return flowElements;

        }
        return null;
    }

}
