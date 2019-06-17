package com.deloitte.services.applycenter.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.bpmservice.feign.BpmProcessTaskFeignService;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryForm;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.oaservice.applycenter.client.SendProcessTaskClient;
import com.deloitte.platform.api.oaservice.applycenter.param.SendProcessTaskQueryForm;
import com.deloitte.platform.api.oaservice.applycenter.vo.SendProcessTaskApprovesFrom;
import com.deloitte.platform.api.oaservice.applycenter.vo.SendProcessTaskByBpmForm;
import com.deloitte.platform.api.oaservice.applycenter.vo.SendProcessTaskForm;
import com.deloitte.platform.api.oaservice.applycenter.vo.SendProcessTaskVo;
import com.deloitte.platform.api.oaservice.news.vo.NewsVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.applycenter.entity.SendProcessTask;
import com.deloitte.services.applycenter.service.ISendProcessTaskService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


/**
 * @Author : yidaojun
 * @Date : Create in 2019-05-09
 * @Description :   SendProcessTask控制器实现类
 * @Modified :
 */
@Api(tags = "SendProcessTask操作接口")
@Slf4j
@RestController
public class SendProcessTaskController implements SendProcessTaskClient {

    @Autowired
    public ISendProcessTaskService sendProcessTaskService;

    @Autowired
    private BpmProcessTaskFeignService bpmProcessTaskFeignService;

    @Override
    @ApiOperation(value = "新增SendProcessTask", notes = "新增一个SendProcessTask")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name = "sendProcessTaskForm", value = "新增SendProcessTask的form表单", required = true) SendProcessTaskForm sendProcessTaskForm) {
        log.info("form:{}", sendProcessTaskForm.toString());
        return sendProcessTaskService.save(sendProcessTaskForm);
    }

    @Override
    @ApiOperation(value = "通过BPM生成待阅信息，需要在流程提交后调用", notes = "通过BPM生成待阅信息，需要在流程提交后调用")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result addByBpm(@Valid @RequestBody @ApiParam(name = "sendProcessTaskByBpmForm", value = "通过BPM生成待阅信息", required = true) SendProcessTaskByBpmForm sendProcessTaskByBpmForm) {
        BpmProcessTaskQueryForm bpmProcessTaskQueryForm = new BpmProcessTaskQueryForm();
        bpmProcessTaskQueryForm.setProcessDefineKey(sendProcessTaskByBpmForm.getProcessDefineKey());
        bpmProcessTaskQueryForm.setProcessInstanceId(sendProcessTaskByBpmForm.getProcessInstanceId());
        if ("start".equals(sendProcessTaskByBpmForm.getBpmTaskId())) {
            bpmProcessTaskQueryForm.setTaskKey("start");
        } else {
            bpmProcessTaskQueryForm.setPreviousTaskId(sendProcessTaskByBpmForm.getBpmTaskId());
        }
        Result<List<BpmProcessTaskVo>> result = bpmProcessTaskFeignService.list(bpmProcessTaskQueryForm);
        if (result.isSuccess()) {
            List<BpmProcessTaskVo> list = result.getData();
            if (list != null && list.size() > 0) {
                BpmProcessTaskVo vo = list.get(0);
                for (SendProcessTaskApprovesFrom approvesFrom : sendProcessTaskByBpmForm.getSendProcessTaskApprovesFroms()) {
                    SendProcessTask sendProcessTask = new BeanUtils<SendProcessTask>().copyObj(vo, SendProcessTask.class);
                    sendProcessTask.setId(null);
                    sendProcessTask.setTaskStauts("待阅");
                    sendProcessTask.setApproverAcount(approvesFrom.getApproverAcount());
                    sendProcessTask.setApproverName(approvesFrom.getApproverName());
                    sendProcessTask.setApproverOrg(approvesFrom.getApproverOrg());
                    sendProcessTask.setApproverStation(approvesFrom.getApproverStation());
                    sendProcessTask.setApproverDescription(approvesFrom.getApproverDescription());
                    sendProcessTask.setApproverName(approvesFrom.getApproverName());
                    sendProcessTask.setObjectUrl(approvesFrom.getObjectUrl());
                    sendProcessTask.setObjectAppUrl(approvesFrom.getObjectAppUrl());
                    sendProcessTask.setEmergency(sendProcessTaskByBpmForm.getEmergency());
                    sendProcessTask.setSourceSystem(sendProcessTaskByBpmForm.getSourceSystem());
                    boolean flag = sendProcessTaskService.save(sendProcessTask);
                    if (flag) {
                        sendProcessTask.setTaskId(String.valueOf(sendProcessTask.getId()));
                        sendProcessTaskService.updateById(sendProcessTask);
                    } else {
                        return Result.fail("待阅信息保存失败");
                    }
                }
                return Result.success();
            } else {
                return Result.fail("无法获取流程待办信息！");
            }
        } else {
            return result;
        }
    }


    @Override
    @ApiOperation(value = "删除SendProcessTask", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SendProcessTaskID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        sendProcessTaskService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改SendProcessTask", notes = "修改指定SendProcessTask信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SendProcessTask的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name = "sendProcessTaskForm", value = "修改SendProcessTask的form表单", required = true) SendProcessTaskForm sendProcessTaskForm) {
        SendProcessTask sendProcessTask = new BeanUtils<SendProcessTask>().copyObj(sendProcessTaskForm, SendProcessTask.class);
        sendProcessTask.setId(id);
        sendProcessTask.setTaskStauts("已阅");
        sendProcessTaskService.saveOrUpdate(sendProcessTask);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "修改SendProcessTask", notes = "修改指定SendProcessTask信息")
    public Result updateById(@RequestParam(name="id")long id) {
        SendProcessTask sendProcessTask = new SendProcessTask();
        sendProcessTask.setId(id);
        sendProcessTask.setTaskStauts("已阅");
        sendProcessTaskService.updateById(sendProcessTask);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取SendProcessTask", notes = "获取指定ID的SendProcessTask信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SendProcessTask的ID", required = true, dataType = "long")
    public Result<SendProcessTaskVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        SendProcessTask sendProcessTask = sendProcessTaskService.getById(id);
        SendProcessTaskVo sendProcessTaskVo = new BeanUtils<SendProcessTaskVo>().copyObj(sendProcessTask, SendProcessTaskVo.class);
        return new Result<SendProcessTaskVo>().sucess(sendProcessTaskVo);
    }

    @Override
    @ApiOperation(value = "列表查询SendProcessTask", notes = "根据条件查询SendProcessTask列表数据")
    public Result<List<SendProcessTaskVo>> list(@Valid @RequestBody @ApiParam(name = "sendProcessTaskQueryForm", value = "SendProcessTask查询参数", required = true) SendProcessTaskQueryForm sendProcessTaskQueryForm) {
        log.info("search with sendProcessTaskQueryForm:{}", sendProcessTaskQueryForm.toString());
        List<SendProcessTask> sendProcessTaskList = sendProcessTaskService.selectList(sendProcessTaskQueryForm);
        List<SendProcessTaskVo> sendProcessTaskVoList = new BeanUtils<SendProcessTaskVo>().copyObjs(sendProcessTaskList, SendProcessTaskVo.class);
        return new Result<List<SendProcessTaskVo>>().sucess(sendProcessTaskVoList);
    }


    @Override
    @ApiOperation(value = "分页查询SendProcessTask", notes = "根据条件查询SendProcessTask分页数据")
    public Result<GdcPage<SendProcessTaskVo>> search(@Valid @RequestBody @ApiParam(name = "sendProcessTaskQueryForm", value = "SendProcessTask查询参数", required = true) SendProcessTaskQueryForm sendProcessTaskQueryForm) {
        log.info("search with sendProcessTaskQueryForm:{}", sendProcessTaskQueryForm.toString());
        IPage<SendProcessTask> sendProcessTaskPage = sendProcessTaskService.selectPage(sendProcessTaskQueryForm);
        IPage<SendProcessTaskVo> sendProcessTaskVoPage = new BeanUtils<SendProcessTaskVo>().copyPageObjs(sendProcessTaskPage, SendProcessTaskVo.class);
        return new Result<GdcPage<SendProcessTaskVo>>().sucess(new GdcPage<>(sendProcessTaskVoPage));
    }

    @Override
    @ApiOperation(value = "同步泛微待办已办待阅已阅数据", notes = "同步泛微待办已办待阅已阅数据")
    public Result syncData(@Valid @RequestBody @ApiParam(name = "sendProcessTaskQueryForm", value = "SendProcessTask查询参数", required = true) SendProcessTaskQueryForm sendProcessTaskQueryForm) {
        log.info("search with sendProcessTaskQueryForm:{}", sendProcessTaskQueryForm.toString());
        return sendProcessTaskService.syncData(sendProcessTaskQueryForm);
    }

    @Override
    @ApiOperation(value = "分页查询SendProcessTask 多種條件判斷", notes = "分页查询SendProcessTask 多種條件判斷")
    public Result searchByFrom(@Valid @RequestBody @ApiParam(name = "sendProcessTaskQueryForm", value = "SendProcessTask查询参数", required = true) SendProcessTaskQueryForm sendProcessTaskQueryForm) {
        log.info("search with sendProcessTaskQueryForm:{}", sendProcessTaskQueryForm.toString());
        IPage<SendProcessTask> sendProcessTaskPage = sendProcessTaskService.selectPage2(sendProcessTaskQueryForm);
        IPage<SendProcessTaskVo> sendProcessTaskVoPage = new BeanUtils<SendProcessTaskVo>().copyPageObjs(sendProcessTaskPage, SendProcessTaskVo.class);
        return new Result<GdcPage<SendProcessTaskVo>>().sucess(new GdcPage<>(sendProcessTaskVoPage));
    }

}



