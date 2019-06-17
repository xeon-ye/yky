package com.deloitte.platform.basic.bpmservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.bpmservice.clinet.ProcessOperatorClient;
import com.deloitte.platform.api.bpmservice.vo.*;
import com.deloitte.platform.basic.bpmservice.core.IActivityService;
import com.deloitte.platform.basic.bpmservice.entity.BpmProcessTask;
import com.deloitte.platform.basic.bpmservice.exception.BpmErrorType;
import com.deloitte.platform.basic.bpmservice.service.IBpmProcessTaskService;
import com.deloitte.platform.basic.bpmservice.service.IProcessOperatorService;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Author : jackliu
 * @Date : Create in 11:26 21/02/2019
 * @Description :
 * @Modified :
 */
@Api(tags = "流程操作操作接口")
@Slf4j
@RestController
public class ProcessOperatorController implements ProcessOperatorClient {

    @Autowired
    private IProcessOperatorService processOperatorService;

    @Autowired
    private IBpmProcessTaskService bpmProcessTaskService;


    @Override
    @ApiOperation(value = "启动流程", notes = "启动一个新的流程")
    public Result startProcess(@Valid @RequestBody @ApiParam(name = "bpmProcessParamForm", value = "BPM流程处理参数", required = true) BpmProcessOperatorFormStart bpmProcessParamForm) {
        log.info("[BPM]ProcessOperatorController.startProcess req:{}", JSONObject.toJSONString(bpmProcessParamForm));
        BpmProcessTaskFormStart bpmProcessTaskForm = bpmProcessParamForm.getBpmProcessTaskForm();
        ArrayList<NextNodeParamVo> nextNodeParam = bpmProcessParamForm.getNextNodeParamVo();
        Map processVariables = bpmProcessParamForm.getProcessVariables();
        if(processVariables!=null){
            Iterator<String> it = processVariables.keySet().iterator();
            while (it.hasNext()){
                String key = it.next();
                Object obj = processVariables.get(key);
                if(obj!=null && NumberUtils.isNumber(obj.toString())){
                    if(obj.toString().indexOf(".")>0){
                        processVariables.put(key,Double.valueOf(obj.toString()));
                    }
                }
            }
        }
        BpmProcessTask bpmProcessTask = new BeanUtils<BpmProcessTask>().copyObj(bpmProcessTaskForm, BpmProcessTask.class);
        String processInstanceId = processOperatorService.start(nextNodeParam, bpmProcessTask, processVariables);
        return Result.success(processInstanceId);

    }

    @Override
    @ApiOperation(value = "审批通过", notes = "审批通过进入下一节点")
    public Result approveProcess(@Valid @RequestBody @ApiParam(name = "bpmProcessParamForm", value = "BPM流程处理参数", required = true) BpmProcessOperatorApprove bpmProcessParamForm) {
        log.info("[BPM]ProcessOperatorController.approveProcess req:{}", JSONObject.toJSONString(bpmProcessParamForm));
        BpmProcessTaskFormApprove bpmProcessTaskForm = bpmProcessParamForm.getBpmProcessTaskForm();
        ArrayList<NextNodeParamVo> nextNodeParam = bpmProcessParamForm.getNextNodeParamVo();
        Map processVariables = bpmProcessParamForm.getProcessVariables();

        if(processVariables!=null){
            Iterator<String> it = processVariables.keySet().iterator();
            while (it.hasNext()){
                String key = it.next();
                Object obj = processVariables.get(key);
                if(obj!=null && NumberUtils.isNumber(obj.toString())){
                    if(obj.toString().indexOf(".")>0){
                        processVariables.put(key,Double.valueOf(obj.toString()));
                    }
                }
            }
        }
        BpmProcessTask bpmProcessTask = new BeanUtils<BpmProcessTask>().copyObj(bpmProcessTaskForm, BpmProcessTask.class);
        String result = processOperatorService.approve(nextNodeParam, bpmProcessTask, processVariables);
        return Result.success(result);
    }

    @Override
    @ApiOperation(value = "审批通过", notes = "审批通过进入下一节点")
    public Result approveProcessToNode(@Valid @RequestBody @ApiParam(name = "bpmProcessParamForm", value = "BPM流程处理参数", required = true) BpmProcessOperatorApprove bpmProcessParamForm) {
        log.info("[BPM]ProcessOperatorController.approveProcessToNode req:{}", JSONObject.toJSONString(bpmProcessParamForm));
        BpmProcessTaskFormApprove bpmProcessTaskForm = bpmProcessParamForm.getBpmProcessTaskForm();
        ArrayList<NextNodeParamVo> nextNodeParam = bpmProcessParamForm.getNextNodeParamVo();
        Map processVariables = bpmProcessParamForm.getProcessVariables();

        if(processVariables!=null){
            Iterator<String> it = processVariables.keySet().iterator();
            while (it.hasNext()){
                String key = it.next();
                Object obj = processVariables.get(key);
                if(obj!=null && NumberUtils.isNumber(obj.toString())){
                    if(obj.toString().indexOf(".")>0){
                        processVariables.put(key,Double.valueOf(obj.toString()));
                    }
                }
            }
        }
        BpmProcessTask bpmProcessTask = new BeanUtils<BpmProcessTask>().copyObj(bpmProcessTaskForm, BpmProcessTask.class);
        String result = processOperatorService.jumpToNode(bpmProcessParamForm.getBpmProcessTaskForm().getDestTaskKey(), nextNodeParam, bpmProcessTask);
        return Result.success(result);
    }

    @Override
    @ApiOperation(value = "流程签收", notes = "依据流程历史id签收流程")
    public Result signTask(@PathVariable(value = "id") long id) {
        BpmProcessTask task = bpmProcessTaskService.getById(id);
        if (task != null && task.getSignTime() == null) {
            task.setSignTime(LocalDateTime.now());
            bpmProcessTaskService.updateById(task);
        }
        return Result.success();
    }

    @Override
    @ApiOperation(value = "审批驳回到发起人", notes = "将流程驳回到发起人")
    public Result rejectToFirstProcess(@Valid @RequestBody @ApiParam(name = "bpmProcessParamForm", value = "BPM流程处理参数", required = true) BpmProcessOperatorApprove bpmProcessParamForm) {
        log.info("[BPM]ProcessOperatorController.rejectToFirstProcess req:{}", JSONObject.toJSONString(bpmProcessParamForm));
        BpmProcessTaskFormApprove bpmProcessTaskForm = bpmProcessParamForm.getBpmProcessTaskForm();
        ArrayList<NextNodeParamVo> nextNodeParam = bpmProcessParamForm.getNextNodeParamVo();
        Map processVariables = bpmProcessParamForm.getProcessVariables();

        if(processVariables!=null){
            Iterator<String> it = processVariables.keySet().iterator();
            while (it.hasNext()){
                String key = it.next();
                Object obj = processVariables.get(key);
                if(obj!=null && NumberUtils.isNumber(obj.toString())){
                    if(obj.toString().indexOf(".")>0){
                        processVariables.put(key,Double.valueOf(obj.toString()));
                    }
                }
            }
        }
        BpmProcessTask bpmProcessTask = new BeanUtils<BpmProcessTask>().copyObj(bpmProcessTaskForm, BpmProcessTask.class);
        processVariables.put("reject", "start");
        processOperatorService.reject("start", nextNodeParam.get(0), bpmProcessTask, processVariables);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "审批驳回到指定人", notes = "将流程驳回到指定节点")
    @ApiImplicitParam(paramType = "path", name = "destTaskKey", value = "回退到的task key值", required = true, dataType = "String")
    public Result rejectProcess(@PathVariable String destTaskKey, @Valid @RequestBody @ApiParam(name = "bpmProcessParamForm", value = "BPM流程处理参数", required = true) BpmProcessOperatorApprove bpmProcessParamForm) {
        log.info("[BPM]ProcessOperatorController.rejectProcess req:{}", JSONObject.toJSONString(bpmProcessParamForm));
        BpmProcessTaskFormApprove bpmProcessTaskForm = bpmProcessParamForm.getBpmProcessTaskForm();
        ArrayList<NextNodeParamVo> nextNodeParam = bpmProcessParamForm.getNextNodeParamVo();
        Map processVariables = bpmProcessParamForm.getProcessVariables();

        if(processVariables!=null){
            Iterator<String> it = processVariables.keySet().iterator();
            while (it.hasNext()){
                String key = it.next();
                Object obj = processVariables.get(key);
                if(obj!=null && NumberUtils.isNumber(obj.toString())){
                    if(obj.toString().indexOf(".")>0){
                        processVariables.put(key,Double.valueOf(obj.toString()));
                    }
                }
            }
        }
        BpmProcessTask bpmProcessTask = new BeanUtils<BpmProcessTask>().copyObj(bpmProcessTaskForm, BpmProcessTask.class);
        BpmProcessTask hitsory = processOperatorService.reject(destTaskKey, nextNodeParam.get(0), bpmProcessTask, processVariables);
        return Result.success(hitsory);
    }

    @Override
    @ApiOperation(value = "转办流程", notes = "将流程节点的审批人替换成新的审批人")
    public Result turnToProcess(@Valid @RequestBody @ApiParam(name = "bpmProcessParamForm", value = "BPM流程处理参数", required = true) BpmProcessOperatorApprove bpmProcessParamForm) {
        log.info("[BPM]ProcessOperatorController.turnToProcess req:{}", JSONObject.toJSONString(bpmProcessParamForm));
        BpmProcessTaskFormApprove bpmProcessTaskForm = bpmProcessParamForm.getBpmProcessTaskForm();
        ArrayList<NextNodeParamVo> nextNodeParam = bpmProcessParamForm.getNextNodeParamVo();
        if (nextNodeParam.size() == 0) {
            return Result.fail(BpmErrorType.BPM_NEXT_APPROVER_IS_EMPTY);
        }
        BpmProcessTask bpmProcessTask = new BeanUtils<BpmProcessTask>().copyObj(bpmProcessTaskForm, BpmProcessTask.class);
        processOperatorService.turnTo(nextNodeParam.get(0), bpmProcessTask);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "终止流程", notes = "流程直接停止")
    public Result stopProcess(@Valid @RequestBody @ApiParam(name = "bpmProcessTaskForm", value = "流程变量对象", required = true) BpmProcessTaskFormApprove bpmProcessParamForm) {
        log.info("[BPM]ProcessOperatorController.stopProcess req:{}", JSONObject.toJSONString(bpmProcessParamForm));
        BpmProcessTask bpmProcessTask = new BeanUtils<BpmProcessTask>().copyObj(bpmProcessParamForm, BpmProcessTask.class);
        processOperatorService.stopProcess(bpmProcessTask);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "撤回流程", notes = "流程发起人在节点被下一审批人审批之前，撤回继续修改")
    public Result rollBackProcess(@Valid @RequestBody @ApiParam(name = "bpmProcessTaskForm", value = "流程变量对象", required = true) BpmProcessOperatorApprove bpmProcessParamForm) {
        log.info("[BPM]ProcessOperatorController.rollBackProcess req:{}", JSONObject.toJSONString(bpmProcessParamForm));
        BpmProcessTaskFormApprove bpmProcessTaskForm = bpmProcessParamForm.getBpmProcessTaskForm();
        ArrayList<NextNodeParamVo> nextNodeParam = bpmProcessParamForm.getNextNodeParamVo();
        Map processVariables = bpmProcessParamForm.getProcessVariables();

        if(processVariables!=null){
            Iterator<String> it = processVariables.keySet().iterator();
            while (it.hasNext()){
                String key = it.next();
                Object obj = processVariables.get(key);
                if(obj!=null && NumberUtils.isNumber(obj.toString())){
                    if(obj.toString().indexOf(".")>0){
                        processVariables.put(key,Double.valueOf(obj.toString()));
                    }
                }
            }
        }
        BpmProcessTask bpmProcessTask = new BeanUtils<BpmProcessTask>().copyObj(bpmProcessTaskForm, BpmProcessTask.class);
        BpmProcessTask history = processOperatorService.rollBack(bpmProcessTask, nextNodeParam.get(0), processVariables);
        return Result.success(history);
    }


    @Autowired
    IActivityService activityService;

    @ApiOperation(value = "获取节点可选流向", notes = "获取节点可选流向")
    public Result findNextNodeList(@Valid @RequestBody @ApiParam(name = "bpmTaskNextNodeForm", value = "taskId必填，processVariables传值后依据线条规则指定获取，如无，则无需提供", required = true) BpmTaskNextNodeForm bpmTaskNextNodeForm) {
        log.info("[BPM]ProcessOperatorController.findNextNodeList req:{}", JSONObject.toJSONString(bpmTaskNextNodeForm));
        return Result.success(activityService.findNextNodeList(bpmTaskNextNodeForm.getProcessDefId(), bpmTaskNextNodeForm.getProcessInstanceId(), bpmTaskNextNodeForm.getTaskId(), bpmTaskNextNodeForm.getProcessVariables()));
    }


}


