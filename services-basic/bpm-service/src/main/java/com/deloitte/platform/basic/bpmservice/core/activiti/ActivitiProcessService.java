package com.deloitte.platform.basic.bpmservice.core.activiti;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.platform.api.bpmservice.vo.NextNodeParamVo;
import com.deloitte.platform.basic.bpmservice.core.IActivityService;
import com.deloitte.platform.basic.bpmservice.entity.BpmProcessTask;
import com.deloitte.platform.basic.bpmservice.entity.BpmProcessTaskApprove;
import com.deloitte.platform.basic.bpmservice.exception.BpmErrorType;
import com.deloitte.platform.basic.bpmservice.properties.ActivitiProcessProperties;
import com.deloitte.platform.basic.bpmservice.service.IBpmProcessTaskApproveService;
import com.deloitte.platform.basic.bpmservice.service.IBpmProcessTaskService;
import com.deloitte.platform.basic.bpmservice.service.IProcessOperatorService;
import com.deloitte.platform.common.core.exception.ServiceException;
import com.deloitte.platform.common.core.util.BeanUtils;
import jdk.nashorn.internal.ir.ContinueNode;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.asyncexecutor.multitenant.TenantAwareAcquireAsyncJobsDueRunnable;
import org.activiti.engine.impl.bpmn.behavior.MultiInstanceActivityBehavior;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程审批Service
 *
 * @author yoli
 */
@Slf4j
@Service("processOperatorService")
@Transactional
public class ActivitiProcessService implements IProcessOperatorService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private IBpmProcessTaskService bpmProcessTaskService;

    @Autowired
    ActivitiProcessProperties activitiProcessProperties;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private IdentityService identityService;


    @Autowired
    private IActivityService activityService;

    @Autowired
    IBpmProcessTaskApproveService bpmProcessTaskApproveService;


    /**
     * 流程启动
     *
     * @param nextNodeParamList 下一环节参与者列表    流程会新增一个启动的task节点， 并将提交人设置成第一个task节点的处理人
     * @param bpmProcessTask    流程参数对象
     * @return visa-todo 必填字段    processDefineKey, acountId, acountName objId 审批对象ID  objUrl 审批对象Url
     */
    @Override
    public String start(ArrayList<NextNodeParamVo> nextNodeParamList, BpmProcessTask bpmProcessTask, Map processVariables) {
        //流程定义ID
        String processDefineKey = bpmProcessTask.getProcessDefineKey();
        if (StringUtils.isBlank(processDefineKey)) {
            throw new ServiceException(BpmErrorType.BPM_PROCESS_DEFINE_KEY_IS_EMPTY);
        }
        if (nextNodeParamList.size() == 0) {
            throw new ServiceException(BpmErrorType.BPM_NEXT_APPROVER_IS_EMPTY);
        }
        //流程启动者账号
        String startAcountId = bpmProcessTask.getSubmitterCode();
        if (CollectionUtils.isEmpty(nextNodeParamList)) {
            throw new ServiceException(BpmErrorType.BPM_NEXT_APPROVER_IS_EMPTY);
        }

        //设置流程参数
        ArrayList<String> userList = new ArrayList<String>();
        for (NextNodeParamVo nextNodeParam : nextNodeParamList) {
            userList.add(nextNodeParam.getAcountId());
        }
        processVariables.put("userList", userList);
        processVariables.put("objId", bpmProcessTask.getObjectId());


        // 设置流程发起人ID
        identityService.setAuthenticatedUserId(startAcountId);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefineKey, bpmProcessTask.getObjectId(), processVariables);
        // 启动流程后先把第一个节点代理人设置为提交人
        Task firstTask = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).singleResult();
        taskService.setAssignee(firstTask.getId(), startAcountId);
        // 提交环节自动审批
        taskService.complete(firstTask.getId(), processVariables);

        String comment = "请审批";
        //生成已办
        addProcessTask(comment, processInstance.getProcessDefinitionKey(), processInstance.getProcessInstanceId(), firstTask.getId(), firstTask.getTaskDefinitionKey(),
                firstTask.getName(), "已提交", "审批中", bpmProcessTask, bpmProcessTask, null);
        BpmProcessTask oldBpmProcessTask = new BeanUtils<BpmProcessTask>().copyObj(bpmProcessTask, BpmProcessTask.class);
        oldBpmProcessTask.setApproverAcount(bpmProcessTask.getSubmitterCode());
        oldBpmProcessTask.setApproverName(bpmProcessTask.getSubmitterName());
        oldBpmProcessTask.setApproverOrg(bpmProcessTask.getSubmitterCode());
        // 获取下一环节的所有任务列表
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).list();
        // 生成待办
        String nextTaskKey = "";
        boolean hasSaveApprove = false;
        for (int i = 0; i < tasks.size(); i++) {
            taskService.setAssignee(tasks.get(i).getId(), nextNodeParamList.get(i).getAcountId());
            String taskTitle = "";
            if (!nextTaskKey.equals(tasks.get(i).getTaskDefinitionKey())) {
                nextTaskKey = tasks.get(i).getTaskDefinitionKey();
                ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(processInstance.getProcessDefinitionId());
                ActivityImpl activityImpl = processDefinitionEntity.findActivity(nextTaskKey);
                Object multiInstance = activityImpl.getProperty("multiInstance");
                if (multiInstance != null) {
                    if ("sequential".equalsIgnoreCase(multiInstance.toString())) {
                        //串行多实例
                        taskTitle = "串行节点";
                        if (!hasSaveApprove) {
                            //保证只存储了一次
                            int index = 1;
                            for (NextNodeParamVo nextNodeParam : nextNodeParamList) {
                                BpmProcessTaskApprove approve = new BeanUtils<BpmProcessTaskApprove>().copyObj(nextNodeParam, BpmProcessTaskApprove.class);
                                approve.setProcessInstanceId(processInstance.getProcessInstanceId());
                                approve.setTaskKey(nextTaskKey);
                                approve.setPreviousTaskId(firstTask.getId());
                                approve.setOrderNum(index++);
                                approve.setEndFlag("0");
                                bpmProcessTaskApproveService.save(approve);
                            }
                            hasSaveApprove = true;
                        }
                    } else if ("parallel".equalsIgnoreCase(multiInstance.toString())) {
                        //并行多实例
                        taskTitle = "会签节点";
                    } else {
                        taskTitle = bpmProcessTask.getTaskTitle();
                    }
                }
            }
            //生成待办
            if (StringUtils.isEmpty(bpmProcessTask.getTaskTitle())) {
                bpmProcessTask.setTaskTitle(taskTitle);
            }
            addProcessTask(null, processInstance.getProcessDefinitionKey(), processInstance.getProcessInstanceId(), tasks.get(i).getId(), tasks.get(i).getTaskDefinitionKey(),
                    tasks.get(i).getName(), "待审批", "审批中", oldBpmProcessTask, bpmProcessTask, nextNodeParamList.get(i));
        }
        return processInstance.getProcessInstanceId();
    }

    /**
     * 审批通过
     *
     * @param nextNodeParamList   下一环节参与者列表
     * @param bpmProcessTaskParam 流程参数对象
     *                            visa-todo 必填字段    taskId, acountId, acountName objId 审批对象ID  objUrl 审批对象Url
     * @return
     */
    @Override
    public String approve(ArrayList<NextNodeParamVo> nextNodeParamList, BpmProcessTask bpmProcessTaskParam, Map processVariables) {
        String result = "";
        BpmProcessTask processTask = bpmProcessTaskService.getById(bpmProcessTaskParam.getId());
        String previousTask = processTask.getPreviousTaskId();
        if (processTask == null) {
            throw new ServiceException(BpmErrorType.BPM_PROCESS_TASK_NOT_EXIST, "ID:" + bpmProcessTaskParam.getId());
        }
        String taskID = processTask.getTaskId();

        if (CollectionUtils.isEmpty(nextNodeParamList)) {
            throw new ServiceException(BpmErrorType.BPM_NEXT_APPROVER_IS_EMPTY);
        }

        //设置流程参数
        Map<String, Object> variables = activityService.getProcessVariables(processTask.getProcessInstanceId());
        ArrayList<String> userList = new ArrayList<String>();
        for (NextNodeParamVo nextNodeParam : nextNodeParamList) {
            userList.add(nextNodeParam.getAcountId());
        }
        variables.put("userList", userList);
        variables.putAll(processVariables);

        String comment = StringUtils.isNotBlank(bpmProcessTaskParam.getOpinion()) ? bpmProcessTaskParam.getOpinion() : "同意";
        // 获取当前任务，并提交任务
        Task currentTask = taskService.createTaskQuery().taskId(taskID).singleResult();

        String currentTaskDefinitionKey = currentTask.getTaskDefinitionKey();

        String processInstanceId = currentTask.getProcessInstanceId();
        //获取当前节点的属性，判断是否为串行or并行多实例
        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(currentTask.getProcessDefinitionId());
        ActivityImpl activityImpl = processDefinitionEntity.findActivity(currentTaskDefinitionKey);
        Object multiInstance = activityImpl.getProperty("multiInstance");
        try {
            if (multiInstance != null) {
                if ("sequential".equalsIgnoreCase(multiInstance.toString())) {
                    //串行多实例
                    //如果是串行最后一次，需要把variables传入
                    String expression = ((MultiInstanceActivityBehavior)activityImpl.getActivityBehavior()).getCompletionConditionExpression().getExpressionText();
                    //串行节点
                    List<Execution> executionList = runtimeService.createExecutionQuery().activityId(activityImpl.getId()).processInstanceId(processInstanceId).list();
                    if (executionList != null && executionList.size() == 1) {
                        for(Execution ee : executionList) {
                            Object nrOfInstances = runtimeService.getVariable(ee.getId(), "nrOfInstances");
                            Object nrOfCompletedInstances = runtimeService.getVariable(ee.getId(), "nrOfCompletedInstances");
                            if (nrOfInstances == null) {
                                continue;
                            }
                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put("nrOfCompletedInstances", new BigDecimal(nrOfCompletedInstances.toString()).add(new BigDecimal("1")).toString());
                            map.put("nrOfInstances", nrOfInstances);
                            if (activityService.isCondition("", expression, map)) {
                                taskService.complete(taskID,variables);
                            }else{
                                taskService.complete(taskID);
                            }
                        }
                    }else{
                        taskService.complete(taskID);
                    }
                } else if ("parallel".equalsIgnoreCase(multiInstance.toString())) {
                    //并行多实例
                    taskService.complete(taskID, variables);
                } else {
                    taskService.complete(taskID, variables);
                }
            } else {
                taskService.complete(taskID, variables);
            }
        } catch (Exception e) {
            log.error("审批失败!", e);
            throw new ServiceException(BpmErrorType.BPM_APPROVE_FAILE, e.getMessage());
        }

        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
        //最后一个环节
        if (CollectionUtils.isEmpty(tasks)) {
            this.updateProcessTask("已批准", comment, "已通过", processTask);
            if(multiInstance!=null) {
                if ("parallel".equalsIgnoreCase(multiInstance.toString())) {
                    //并发抢单模式，没有将其他任务修改为已批准
                    bpmProcessTaskService.updateTaskStatusByPrevious("已批准",previousTask,processInstanceId);
                } else if ("sequential".equalsIgnoreCase(multiInstance.toString())) {
                    //串行抢单模式，没有将其他任务修改为已批准
                    bpmProcessTaskService.updateTaskStatusByPrevious("已批准",previousTask,processInstanceId);
                }
            }
            //将此实例下的objectStatuts修改为已通过
            bpmProcessTaskService.updateObjectStatusByProcessId("已通过", processInstanceId);
            result = "审批结束";
        } else {
            if ("start".equals(currentTaskDefinitionKey)) {
                this.updateProcessTask("已重新提交", comment, "审批中", processTask);
            } else {
                this.updateProcessTask("已批准", comment, "审批中", processTask);
            }
            result = "审批通过";
        }
        String nextTaskKey = "";
        boolean hasSaveApprove = false;
        for (int i = 0; i < tasks.size(); i++) {
            // 如果活动任务定义ID和当前任务定义ID一致 则不需要生成待办
            // (如果会签节点审批通过后，当前节点TaskDefinitionKey没有变化，说明没有进入下一节点，不需要生成下一节点的代办)
            //判断是否产生了新的待办，如果产生了，判断是否为多实例，如为，则需要将一块的待办均变为已办
            if(multiInstance!=null&&!tasks.get(i).getTaskDefinitionKey().equals(currentTask.getTaskDefinitionKey())) {
                if ("parallel".equalsIgnoreCase(multiInstance.toString())) {
                    //并发抢单模式，没有将其他任务修改为已批准
                    bpmProcessTaskService.updateTaskStatusByPrevious("已批准",previousTask,processInstanceId);
                } else if ("sequential".equalsIgnoreCase(multiInstance.toString())) {
                    //串行抢单模式，没有将其他任务修改为已批准
                    bpmProcessTaskService.updateTaskStatusByPrevious("已批准",previousTask,processInstanceId);
                }
            }
            //如果是串行的多实例，此时需要参数新的待办
            if (!nextTaskKey.equals(tasks.get(0).getTaskDefinitionKey())) {
                nextTaskKey = tasks.get(0).getTaskDefinitionKey();
                if (nextTaskKey.equals(currentTaskDefinitionKey)) {
                    //下一节点和当前节点相同，则不需要重新获取节点的配置信息
                    multiInstance = activityImpl.getProperty("multiInstance");
                } else {
                    //向下流转
                    ActivityImpl nextActive = processDefinitionEntity.findActivity(tasks.get(0).getTaskDefinitionKey());
                    multiInstance = nextActive.getProperty("multiInstance");
                    if (multiInstance != null && "sequential".equalsIgnoreCase(multiInstance.toString())) {
                        //将人员信息存储在一个独立的表中
                        if (!hasSaveApprove) {
                            //保证只存储了一次
                            int index = 1;
                            for (NextNodeParamVo nextNodeParam : nextNodeParamList) {
                                BpmProcessTaskApprove approve = new BeanUtils<BpmProcessTaskApprove>().copyObj(nextNodeParam, BpmProcessTaskApprove.class);
                                approve.setProcessInstanceId(processInstanceId);
                                approve.setTaskKey(nextTaskKey);
                                approve.setPreviousTaskId(taskID);
                                approve.setOrderNum(index++);
                                approve.setEndFlag("0");
                                bpmProcessTaskApproveService.save(approve);
                            }
                            hasSaveApprove = true;
                        }
                    }
                }
            }

            if (multiInstance != null) {
                if ("sequential".equalsIgnoreCase(multiInstance.toString())) {
                    if (tasks.get(i).getTaskDefinitionKey().equals(currentTask.getTaskDefinitionKey())) {
                        //没有流转到下一节点，则在此处防止串行过程中的值
                        //获取此时的审批成员：
                        List<Execution> executionList = runtimeService.createExecutionQuery().activityId(currentTaskDefinitionKey).processInstanceId(processInstanceId).list();
                        NextNodeParamVo nextNodeParamVo = new NextNodeParamVo();
                        if (executionList != null && executionList.size() == 1) {
                            String userId = runtimeService.getVariable(executionList.get(0).getId(), "user", String.class);
                            taskService.setAssignee(tasks.get(i).getId(), userId);
                            nextNodeParamVo.setAcountId(userId);
                            BpmProcessTaskApprove approve = new BpmProcessTaskApprove();
                            approve.setTaskKey(currentTaskDefinitionKey);
                            approve.setProcessInstanceId(processInstanceId);
                            approve.setPreviousTaskId(processTask.getPreviousTaskId());
                            approve.setAcountId(userId);
                            List<BpmProcessTaskApprove> approveList = bpmProcessTaskApproveService.selectList(approve);
                            if (approveList != null && approveList.size() == 1) {
                                BpmProcessTaskApprove bpmProcessTaskApprove = approveList.get(0);
                                bpmProcessTaskApprove.setEndFlag("1");
                                bpmProcessTaskApproveService.updateById(bpmProcessTaskApprove);
                                nextNodeParamVo = new BeanUtils<NextNodeParamVo>().copyObj(bpmProcessTaskApprove, NextNodeParamVo.class);
                            }
                        } else {
                            taskService.setAssignee(tasks.get(i).getId(), userList.get(i));
                            nextNodeParamVo = nextNodeParamList.get(i);
                        }
                        bpmProcessTaskParam.setTaskTitle("串行节点");
                        processTask.setTaskId(processTask.getPreviousTaskId());//因没有向下流转，则将上一任务节点设定为最先发起的任务id
                        addProcessTask(null, processTask.getProcessDefineKey(), processInstanceId, tasks.get(i).getId(), tasks.get(i).getTaskDefinitionKey(),
                                tasks.get(i).getName(), "待审批", "审批中", processTask, bpmProcessTaskParam, nextNodeParamVo);
                        continue;
                    } else {
                        //按原先逻辑执行
                    }
                } else if ("parallel".equalsIgnoreCase(multiInstance.toString())) {
                    //并行多实例
                } else {
                    //按原先逻辑执行
                }
            } else {
                //按原先逻辑执行
            }
            if (tasks.get(i).getTaskDefinitionKey().equals(currentTask.getTaskDefinitionKey())) {
                break;
            } else {
                taskService.setAssignee(tasks.get(i).getId(), userList.get(i));
                //生成待办
                if (tasks.size() > 1 && StringUtils.isEmpty(bpmProcessTaskParam.getTaskTitle())) {
                    bpmProcessTaskParam.setTaskTitle("会签节点");
                }
                addProcessTask(null, processTask.getProcessDefineKey(), processInstanceId, tasks.get(i).getId(), tasks.get(i).getTaskDefinitionKey(),
                        tasks.get(i).getName(), "待审批", "审批中", processTask, bpmProcessTaskParam, nextNodeParamList.get(i));
            }
        }
        return result;

    }

    public String jumpToNode(String destTaskId, ArrayList<NextNodeParamVo> nextNodeParamList, BpmProcessTask bpmProcessTaskParam) {
        String result = "";
        BpmProcessTask processTask = bpmProcessTaskService.getById(bpmProcessTaskParam.getId());
        if (processTask == null) {
            throw new ServiceException(BpmErrorType.BPM_PROCESS_TASK_NOT_EXIST, "ID:" + bpmProcessTaskParam.getId());
        }
        String taskId = processTask.getTaskId();
        String previousTask = processTask.getPreviousTaskId();
        ArrayList<String> userList = new ArrayList<String>();
        for (NextNodeParamVo nextNodeParam : nextNodeParamList) {
            userList.add(nextNodeParam.getAcountId());
        }
        String comment = StringUtils.isNotBlank(bpmProcessTaskParam.getOpinion()) ? bpmProcessTaskParam.getOpinion() : "同意";
        // 获取当前任务
        Task currentTask = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processInstanceId = currentTask.getProcessInstanceId();
        //设置流程参数
        List<Task> oldTasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
        Map<String, Object> variables = activityService.getProcessVariables(processTask.getProcessInstanceId());
        variables.put("userList", userList);
        for (Task task : oldTasks) {
            turnTransition(task, destTaskId, variables);
            if (!currentTask.getId().equals(task.getId())) {
                //对于其他同级的待办这里直接删除
                deleteProcessTask(currentTask.getProcessInstanceId(), task.getId(), "1");
            }
        }
//		Task newTask = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
        //最后一个环节
        if (CollectionUtils.isEmpty(tasks)) {
            this.updateProcessTask("已批准", comment, "已通过", processTask);
            //将此实例下的objectStatuts修改为已通过
            bpmProcessTaskService.updateObjectStatusByProcessId("已通过", processInstanceId);
            result = "审批结束";
        } else {
            this.updateProcessTask("已批准", comment, "审批中", processTask);
            result = "审批通过";
        }
//		taskService.setAssignee(newTask.getId(), nextNodeParam.getAcountId());
        for (int i = 0; i < tasks.size(); i++) {
            Task newTask = tasks.get(i);
            //判断新产生的task是否为串行审批，如果为则将审批人插入自定义表中，串行审批只会产生一个待办
            if (i == 0){
                ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(currentTask.getProcessDefinitionId());
                ActivityImpl nextActive = processDefinitionEntity.findActivity(tasks.get(0).getTaskDefinitionKey());
                Object multiInstance = nextActive.getProperty("multiInstance");
                if (multiInstance != null && "sequential".equalsIgnoreCase(multiInstance.toString())) {
                    //将人员信息存储在一个独立的表中
                    int index = 1;
                    for (NextNodeParamVo nextNodeParam : nextNodeParamList) {
                        BpmProcessTaskApprove approve = new BeanUtils<BpmProcessTaskApprove>().copyObj(nextNodeParam, BpmProcessTaskApprove.class);
                        approve.setProcessInstanceId(processInstanceId);
                        approve.setTaskKey(newTask.getId());
                        approve.setPreviousTaskId(taskId);
                        approve.setOrderNum(index++);
                        approve.setEndFlag("0");
                        bpmProcessTaskApproveService.save(approve);
                    }
                    bpmProcessTaskParam.setTaskTitle("串行节点");
                }
            }
            taskService.setAssignee(newTask.getId(), nextNodeParamList.get(i).getAcountId());
            //生成待办
            if (tasks.size() > 1 && StringUtils.isEmpty(bpmProcessTaskParam.getTaskTitle())) {
                bpmProcessTaskParam.setTaskTitle("会签节点");
            }
            addProcessTask(null, processTask.getProcessDefineKey(), newTask.getProcessInstanceId(), newTask.getId(), newTask.getTaskDefinitionKey(),
                    newTask.getName(), "待审批", "审批中", processTask, bpmProcessTaskParam, nextNodeParamList.get(i));
        }
        return result;
    }

    /**
     * 转办
     *
     * @param nextNodeParam       被转办者
     * @param bpmProcessTaskParam 流程参数对象
     *                            visa-todo 必填字段    taskId, acountId, acountName objId 审批对象ID  objUrl 审批对象Url
     * @return
     */
    @Override
    public void turnTo(NextNodeParamVo nextNodeParam, BpmProcessTask bpmProcessTaskParam) {

        BpmProcessTask processTask = bpmProcessTaskService.getById(bpmProcessTaskParam.getId());
        if (processTask == null) {
            throw new ServiceException(BpmErrorType.BPM_PROCESS_TASK_NOT_EXIST, "ID:" + bpmProcessTaskParam.getId());
        }
        String taskId = processTask.getTaskId();

        // 获取当前任务
        Task currentTask = taskService.createTaskQuery().taskId(taskId).singleResult();
        String comment = StringUtils.isNotBlank(bpmProcessTaskParam.getOpinion()) ? bpmProcessTaskParam.getOpinion() : "转办";

        taskService.setAssignee(taskId, nextNodeParam.getAcountId());

        //先生成已办
        updateProcessTask("已转办", comment, "审批中", processTask);
        //生成待办
        addProcessTask(null, processTask.getProcessDefineKey(), currentTask.getProcessInstanceId(), currentTask.getId(), currentTask.getTaskDefinitionKey(),
                currentTask.getName(), "待审批", "审批中", processTask, bpmProcessTaskParam, nextNodeParam);

    }

    /**
     * 审批驳回
     *
     * @param destTaskKey         目标环节定义ID
     *                            //* @param currentParticipant 当前审批者
     * @param nextNodeParam       目标环节参与者
     * @param bpmProcessTaskParam 流程参数对象
     *                            visa-todo 必填字段    taskId, acountId, acountName objId 审批对象ID  objUrl 审批对象Url
     * @return
     */
    @Override
    public BpmProcessTask reject(String destTaskKey, NextNodeParamVo nextNodeParam, BpmProcessTask bpmProcessTaskParam, Map processVariables) {
        BpmProcessTask processTask = bpmProcessTaskService.getById(bpmProcessTaskParam.getId());
        if (processTask == null) {
            throw new ServiceException(BpmErrorType.BPM_PROCESS_TASK_NOT_EXIST, "ID:" + bpmProcessTaskParam.getId());
        }
        String taskId = processTask.getTaskId();
        String comment = StringUtils.isNotBlank(bpmProcessTaskParam.getOpinion()) ? bpmProcessTaskParam.getOpinion() : "不同意";

        //设置流程参数
        Map<String, Object> variables = activityService.getProcessVariables(processTask.getProcessInstanceId());
        variables.putAll(processVariables);

        // 获取当前任务
        Task currentTask = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processInstanceId = currentTask.getProcessInstanceId();

        // 获取平级的所有任务 一起驳回
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
        boolean flag = false;
        for (Task task : tasks) {
            if(!flag) {
                //并行会签时，如设置了条件，条件达成后会生成待办，之前的任务将会被删除，无法再完成
                flag = turnTransition(task, destTaskKey, variables);
            }
            //对应当前任务需要生成已办
            if (currentTask.getId().equals(task.getId())) {
                updateProcessTask("已驳回", comment, "已驳回", processTask);
            } else {
                //对于其他同级的待办这里直接删除
                deleteProcessTask(currentTask.getProcessInstanceId(), task.getId(), "1");
            }
        }
        Task newTask = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        taskService.setAssignee(newTask.getId(), nextNodeParam.getAcountId());
        //生成待办
        //如果驳回的是到第一个节点，流程任务状态变成：重新提交。驳回到其他节点，流程状态变成：待审批
        if (processVariables.get("reject") != null && "start".equals(processVariables.get("reject"))) {
            addProcessTask(null, processTask.getProcessDefineKey(), newTask.getProcessInstanceId(), newTask.getId(), newTask.getTaskDefinitionKey(),
                    newTask.getName(), "待重新提交", "审批中", processTask, bpmProcessTaskParam, nextNodeParam);
        } else {
            addProcessTask(null, processTask.getProcessDefineKey(), newTask.getProcessInstanceId(), newTask.getId(), newTask.getTaskDefinitionKey(),
                    newTask.getName(), "待审批", "审批中", processTask, bpmProcessTaskParam, nextNodeParam);
        }
        return bpmProcessTaskParam;
    }

    /**
     * 终止流程
     * //	 * @param processInstanceId 流程实例ID
     * //	 * @param taskId 当前任务ID
     * //	 * @param currentParticipant 当前操作人
     *
     * @param bpmProcessTaskParam 流程参数对象
     *                            visa-todo 必填字段   processInstanceId, taskId, acountId, acountName objId 审批对象ID  objUrl 审批对象Url
     * @return
     */
    @Override
    public void stopProcess(BpmProcessTask bpmProcessTaskParam) {
        BpmProcessTask processTask = bpmProcessTaskService.getById(bpmProcessTaskParam.getId());
        if (processTask == null) {
            throw new ServiceException(BpmErrorType.BPM_PROCESS_TASK_NOT_EXIST, "ID:" + bpmProcessTaskParam.getId());
        }

        String processInstanceId = processTask.getProcessInstanceId();
        String taskId = processTask.getTaskId();

        String comment = StringUtils.isNotBlank(bpmProcessTaskParam.getOpinion()) ? bpmProcessTaskParam.getOpinion() : "审批拒绝";
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
        for (Task task : tasks) {
            //对应当前任务需要生成已办
            if (taskId.equals(task.getId())) {
                updateProcessTask("终止流程", comment, "停止审批", processTask);
            } else {
                //删除待办
                deleteProcessTask(processInstanceId, task.getId(), "1");
            }
        }
        //暂停流程
        //runtimeService.suspendProcessInstanceById(processInstanceId);
        //终止流程
        runtimeService.deleteProcessInstance(processInstanceId, "审批终止");

    }

    /**
     * 撤回流程
     * //	 * @param processInstanceId 流程实例ID
     * //	 * @param currentTaskKey 撤回环节定义ID
     * //	 * @param currentParticipant 当前操作人
     *
     * @param bpmProcessTaskParam 流程辅助信息对象
     *                            visa-todo 必填字段   processInstanceId, currentTaskKey, acountId, acountName objId 审批对象ID  objUrl 审批对象Url
     * @return
     */
    @Override
    public BpmProcessTask rollBack(BpmProcessTask bpmProcessTaskParam, NextNodeParamVo nextNodeParam, Map processVariables) {

        BpmProcessTask processTask = bpmProcessTaskService.getById(bpmProcessTaskParam.getId());
        if (processTask == null) {
            throw new ServiceException(BpmErrorType.BPM_PROCESS_TASK_NOT_EXIST, "ID:" + bpmProcessTaskParam.getId());
        }

        String processInstanceId = processTask.getProcessInstanceId();
        String currentTaskKey = processTask.getTaskKey();

        String comment = StringUtils.isNotBlank(bpmProcessTaskParam.getOpinion()) ? bpmProcessTaskParam.getOpinion() : "撤回";

        processVariables.put("objId", processTask.getObjectId());

        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
        if(tasks==null||tasks.size()==0){
            throw new ServiceException(BpmErrorType.BPM_PROCESS_COMPLETE, "processInstanceId:" + processInstanceId);
        }
        //当前任务处于并行节点和串行节点不能撤回
        String processDefId = tasks.get(0).getProcessDefinitionId();
        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(processDefId);
        ActivityImpl activity = processDefinitionEntity.findActivity(processTask.getTaskKey());
        Object multiInstance = activity.getProperty("multiInstance");
        if (multiInstance != null ){
            if("sequential".equalsIgnoreCase(multiInstance.toString())||"parallel".equalsIgnoreCase(multiInstance.toString())) {
                throw new ServiceException(BpmErrorType.BPM_ROLL_BACK_ERROR, "会签节点不允许撤回");
            }
        }
        for (Task task : tasks) {
            turnTransition(task, currentTaskKey, processVariables);
            //对于其他同级的待办这里直接删除
            //deleteProcessTask(processInstanceId, task.getId(), "1");
            //修改为，将其他统计的待办修改为撤回
            BpmProcessTask history = bpmProcessTaskService.findBpmProcessTask(processInstanceId,  task.getId());
            if(history!=null){
                updateProcessTask("撤回", comment, "审批中", history);
            }
        }
        Task newTask = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        taskService.setAssignee(newTask.getId(), processTask.getApproverAcount());

        //生成撤回已办
//        addProcessTask(comment, processTask.getProcessDefineKey(), newTask.getProcessInstanceId(), newTask.getId(), newTask.getTaskDefinitionKey(),
//                newTask.getName(), "撤回", "审批中", processTask, bpmProcessTaskParam, nextNodeParam);

        //生成待办
        addProcessTask(null, processTask.getProcessDefineKey(), newTask.getProcessInstanceId(), newTask.getId(), newTask.getTaskDefinitionKey(),
                newTask.getName(), "待重新提交", "审批中", processTask, bpmProcessTaskParam, nextNodeParam);

        return bpmProcessTaskParam;
    }

    /**
     * 转向指定的节点
     *
     * @param task        当前任务
     * @param destTaskKey 目标节点ID
     * @param map
     * @throws ServiceException
     */
    private boolean turnTransition(Task task, String destTaskKey, Map<String, Object> map) {
        // 当前任务key
        String taskDefKey = task.getTaskDefinitionKey();
        // 获得当前流程的定义模型
        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(task.getProcessDefinitionId());
        // 获得当前流程定义模型的所有任务节点
        List<ActivityImpl> activitilist = processDefinition.getActivities();
        // 当前活动节点
        ActivityImpl currActivity = null;
        // 驳回目标节点
        ActivityImpl destActivity = null;
        boolean  flag = false;
        for (ActivityImpl activityImpl : activitilist) {
            // 确定当前活动activity节点
            if (taskDefKey.equals(activityImpl.getId())) {
                currActivity = activityImpl;
            }
            if (destTaskKey.equals(activityImpl.getId())) {
                destActivity = activityImpl;
            }
            if (destActivity != null && currActivity != null) {
                // 如果两个节点都获得,退出跳出循环
                break;
            }
        }
        if(destTaskKey.equals(taskDefKey)){
            throw new ServiceException(BpmErrorType.BPM_ROLL_BACK_ERROR,"无法将流程提交至相同的节点");
        }
        if(destActivity==null){
            throw new ServiceException(BpmErrorType.BPM_APPROVE_FAILE,"目标节点不存在");
        }
        if(currActivity==null){
            throw new ServiceException(BpmErrorType.BPM_APPROVE_FAILE,"无法获取当前节点信息");
        }
        // 设置驳回意见
        taskService.saveTask(task);
        String taskId = task.getId();
        //处理串行审批，驳回功能，串行审批和并行审批不能撤回
        Object multiInstance = currActivity.getProperty("multiInstance");
        if (multiInstance != null) {
            if ("sequential".equalsIgnoreCase(multiInstance.toString())) {
                //如果当前为串行节点，判断是否到了最后一个提交人，如果到了，则走下面的逻辑，没到的话，就提交到最后一个人
                String expression = ((MultiInstanceActivityBehavior)currActivity.getActivityBehavior()).getCompletionConditionExpression().getExpressionText();
                //串行节点
                List<Execution> executionList = runtimeService.createExecutionQuery().activityId(currActivity.getId()).processInstanceId(task.getProcessInstanceId()).list();
                if (executionList != null && executionList.size() == 1) {
                    while(true) {
                        Object nrOfInstances = runtimeService.getVariable(executionList.get(0).getId(), "nrOfInstances");
                        Object nrOfCompletedInstances = runtimeService.getVariable(executionList.get(0).getId(), "nrOfCompletedInstances");
                        Map<String, Object> map2 = new HashMap<String, Object>();
                        map2.put("nrOfCompletedInstances", new BigDecimal(nrOfCompletedInstances.toString()).add(new BigDecimal("1")).toString());
                        map2.put("nrOfInstances", nrOfInstances);
                        List<Task> tasks = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).list();
                        if(tasks==null||tasks.size()==0){
                            //如果没有新产生的待办，则跳出循环
                            break;
                        }
                        if (activityService.isCondition("", expression, map2)) {
                            //满足提交到下一环节的条件，则跳出循环
                            taskId = tasks.get(0).getId();
                            break;
                        } else {
                            //不满足条件，则自动审批掉，至到满足条件为止
                            for (Task task2 : tasks) {
                                taskService.complete(task2.getId());
                            }
                        }
                    }
                }
            }else if ("Parallel".equalsIgnoreCase(multiInstance.toString())) {
                //判断是否为并行节点，如果为并行节点，需要判断有没有设置完成条件，如果设置了，需要提前终止循环
                Expression expressionObj = ((MultiInstanceActivityBehavior) currActivity.getActivityBehavior()).getCompletionConditionExpression();
                if(expressionObj!=null){
                    String expression = expressionObj.getExpressionText();
                    List<Execution> executionList = runtimeService.createExecutionQuery().activityId(currActivity.getId()).processInstanceId(task.getProcessInstanceId()).list();
                    if (executionList != null && executionList.size() >0) {
                        Object nrOfInstances = runtimeService.getVariable(executionList.get(0).getId(), "nrOfInstances");
                        Object nrOfCompletedInstances = runtimeService.getVariable(executionList.get(0).getId(), "nrOfCompletedInstances");
                        Map<String, Object> map2 = new HashMap<String, Object>();
                        map2.put("nrOfCompletedInstances", new BigDecimal(nrOfCompletedInstances.toString()).add(new BigDecimal("1")).toString());
                        map2.put("nrOfInstances", nrOfInstances);
                        if (activityService.isCondition("", expression, map2)) {
                            //判断是否满足审批条件，如果满足，则返回true
                            flag = true;
                        }
                    }
                }
            }
        }


        // 保存当前活动节点的流程想参数
        List<PvmTransition> hisPvmTransitionList = new ArrayList<PvmTransition>(0);
        for (PvmTransition pvmTransition : currActivity.getOutgoingTransitions()) {
            hisPvmTransitionList.add(pvmTransition);
        }
        // 清空当前活动几点的所有流出项
        currActivity.getOutgoingTransitions().clear();
        // 为当前节点动态创建新的流出项
        TransitionImpl newTransitionImpl = currActivity.createOutgoingTransition();
        // 为当前活动节点新的流出目标指定流程目标
        newTransitionImpl.setDestination(destActivity);

        // 设定驳回标志
        taskService.complete(taskId, map);
        // 清除目标节点的新流入项
        destActivity.getIncomingTransitions().remove(newTransitionImpl);
        // 清除原活动节点的临时流程项
        currActivity.getOutgoingTransitions().clear();
        // 还原原活动节点流出项参数
        currActivity.getOutgoingTransitions().addAll(hisPvmTransitionList);
        return flag;
    }

    /**
     * 新增审批流程任务
     *
     * @param comment           备注
     * @param taskId            任务ID
     * @param processInstanceId 流程实例ID
     * @param taskKey           当前环节定位Key
     * @param taskName          当前环节名字
     * @param objectStatus      审批对象状态
     * @param taskStatus        任务状态
     */
    private void addProcessTask(String comment, String processDefineKey, String processInstanceId, String taskId, String taskKey, String taskName, String taskStatus, String objectStatus, BpmProcessTask oldProcessTask, BpmProcessTask processTaskParam, NextNodeParamVo nextNodeParam) {
        if (processTaskParam.getId() != null) {
            processTaskParam.setId(null);
            processTaskParam.setCreateTime(null);
            processTaskParam.setUpdateTime(null);
            processTaskParam.setDurationTime(null);
        }

        if (!"已提交".equals(taskStatus)) {
            processTaskParam.setApproverAcount(nextNodeParam.getAcountId());
            processTaskParam.setApproverName(nextNodeParam.getAcountName());
            processTaskParam.setApproverStation(nextNodeParam.getStationId());
            processTaskParam.setApproverOrg(nextNodeParam.getOrgId());
            processTaskParam.setApproverDescription(nextNodeParam.getApproverDescription());
            processTaskParam.setObjectId(oldProcessTask.getObjectId() == null ? "" : oldProcessTask.getObjectId());
            processTaskParam.setObjectOrderNum(oldProcessTask.getObjectOrderNum() == null ? "" : oldProcessTask.getObjectOrderNum());
            processTaskParam.setObjectUrl(nextNodeParam.getObjectUrl());
            processTaskParam.setObjectAppUrl(nextNodeParam.getObjectAppUrl());
            processTaskParam.setObjectType(oldProcessTask.getObjectType());
            processTaskParam.setSourceSystem(oldProcessTask.getSourceSystem() == null ? "" : oldProcessTask.getSourceSystem());
            processTaskParam.setSubmitterCode(oldProcessTask.getSubmitterCode());
            processTaskParam.setSubmitterName(oldProcessTask.getSubmitterName());
            processTaskParam.setSubmitterStation(oldProcessTask.getSubmitterStation() == null ? "" : oldProcessTask.getSubmitterStation());
            processTaskParam.setSubmitterOrg(oldProcessTask.getSubmitterOrg());

            processTaskParam.setCreateBy(oldProcessTask.getApproverAcount());//任务创建人
            processTaskParam.setCreateByName(oldProcessTask.getApproverName());//创建人名称
            processTaskParam.setCreateByOrgId(oldProcessTask.getApproverOrg());//任务创建人所在部门
            processTaskParam.setProcessCreateTime(oldProcessTask.getProcessCreateTime());//流程创建时间
            processTaskParam.setPreviousTaskId(oldProcessTask.getTaskId());//上一任务id
        } else {
            //提交节点是否需要设置自己为审批人
            processTaskParam.setApproverAcount(oldProcessTask.getSubmitterCode());
            processTaskParam.setApproverName(oldProcessTask.getSubmitterName());
            processTaskParam.setApproverStation(oldProcessTask.getSubmitterStation());
            processTaskParam.setApproverOrg(oldProcessTask.getSubmitterOrg());
            //新增字段 add by fq 2019-05-09
            processTaskParam.setProcessCreateTime(LocalDateTime.now());//流程创建时间
            processTaskParam.setEmergency(oldProcessTask.getEmergency());//紧急程度
            processTaskParam.setPreviousTaskId("0");//上一任务id
            processTaskParam.setCreateBy(oldProcessTask.getSubmitterCode());//任务创建人
            processTaskParam.setCreateByName(oldProcessTask.getSubmitterName());//创建人名称
            processTaskParam.setCreateByOrgId(oldProcessTask.getSubmitterOrg());//任务创建人所在部门

        }
        processTaskParam.setProcessDefineKey(processDefineKey);
        processTaskParam.setProcessInstanceId(processInstanceId);

        processTaskParam.setTaskId(taskId);
        processTaskParam.setTaskKey(taskKey);
        processTaskParam.setTaskName(taskName);
        processTaskParam.setTaskStauts(taskStatus);

        processTaskParam.setObjectStauts(objectStatus);
        processTaskParam.setOpinion(comment);
        if (processTaskParam.getEmergency() == null || "".equals(processTaskParam.getEmergency())) {
            processTaskParam.setEmergency("0");
        }
        bpmProcessTaskService.save(processTaskParam);

    }

    /**
     * 修改流程任务
     *
     * @param comment      备注
     * @param objectStatus 审批对象状态
     * @param taskStatus   任务状态
     */
    private void updateProcessTask(String taskStatus, String comment, String objectStatus, BpmProcessTask processTask) {
        processTask.setOpinion(comment);
        processTask.setObjectStauts(objectStatus);
        processTask.setTaskStauts(taskStatus);
        if(processTask.getSignTime()==null){
            processTask.setSignTime(LocalDateTime.now());
        }
        processTask.setUpdateTime(LocalDateTime.now());
        processTask.setDurationTime(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")) - processTask.getCreateTime().toEpochSecond(ZoneOffset.of("+8")));
        bpmProcessTaskService.updateById(processTask);
    }

    /**
     * 终止流程任务
     *
     * @param processInsId
     * @param taskId          当前任务ID
     * @param currentlyStauts
     */
    private void deleteProcessTask(String processInsId, String taskId, String currentlyStauts) {
        QueryWrapper<BpmProcessTask> queryWrapper = new QueryWrapper<BpmProcessTask>();
        if (StringUtils.isNotBlank(processInsId)) {
            queryWrapper.eq(BpmProcessTask.PROCESS_INSTANCE_ID, processInsId);
        }
        if (StringUtils.isNotBlank(taskId)) {
            queryWrapper.eq(BpmProcessTask.TASK_ID, taskId);
        }
        BpmProcessTask processTask = bpmProcessTaskService.getOne(queryWrapper);
        if (processTask == null) {
            throw new ServiceException(BpmErrorType.BPM_PROCESS_TASK_NOT_EXIST);
        }
        bpmProcessTaskService.removeById(processTask.getId());
    }

}
