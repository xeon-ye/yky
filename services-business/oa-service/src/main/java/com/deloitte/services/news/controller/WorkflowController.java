package com.deloitte.services.news.controller;

import com.deloitte.platform.api.oaservice.news.client.WorkflowClient;
import com.deloitte.platform.api.oaservice.news.vo.NewsForm;
import com.deloitte.platform.api.oaservice.news.vo.NewsProcessForm;
import com.deloitte.platform.api.oaservice.news.vo.RollBackProcessVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.news.service.ProcessApprovalService;
import com.deloitte.services.news.service.WorkflowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * @Author : yidaojun
 * @Date : Create in 2019-04-12
 * @Description :  新闻控制器实现类
 * @Modified :
 */
@Api(tags = "新闻发布审批接口")
@Slf4j
@RestController
@CrossOrigin
public class WorkflowController implements WorkflowClient {

    @Autowired
    private WorkflowService bpmService;

    @Autowired
    private ProcessApprovalService processApprovalService;

    @Override
    @ApiOperation(value = "新闻发布审批流程提交", notes = "新闻发布审批流程提交")
    public Result submitProcess(@Valid @RequestBody @ApiParam(name = "newsForm", value = "修改News的form表单", required = true) NewsForm newsForm, @RequestHeader(value = "token") String token) {
        return bpmService.submitProcess(newsForm, token);
    }

    @Override
    @ApiOperation(value = "新闻发布审批流程审批", notes = "新闻发布审批流程审批")
    public Result approval(@Valid @RequestBody @ApiParam(name = "newsProcessForm", value = "审批流程参数", required = true) NewsProcessForm newsProcessForm, @RequestHeader(value = "token") String token) {
        return bpmService.approval(newsProcessForm, token);
    }

    @Override
    @ApiOperation(value = "新闻发布审批流程撤回", notes = "新闻发布审批流程撤回")
    public Result rollBackProcess(@Valid @RequestBody @ApiParam(name = "rollBackProcessVo", value = "审批流程参数", required = true) RollBackProcessVo rollBackProcessVo) {
        return bpmService.rollBackProcess(rollBackProcessVo);
    }

    @Override
    @ApiOperation(value = "查询下一节点审批人", notes = "查询下一节点审批人")
    public Result getNextMatrix(@RequestParam Map<String, String> processVariables) {
        return bpmService.buildBpmForm(processVariables);
    }

    @Override
    @ApiOperation(value = "查询流程状态", notes = "查询流程状态")
    public Result processStatus(@RequestParam("processInstanceId") String processInstanceId, @RequestParam("sourceSystem") String sourceSystem) {
        return processApprovalService.getProcessStatus(processInstanceId, sourceSystem);
    }

    @Override
    @ApiOperation(value = "查询审批历史(剔除重复审批人)", notes = "查询审批历史(剔除重复审批人)")
    public Result getHistoryList(@RequestParam("processInstanceId") String processInstanceId, @RequestParam("taskKey") String taskKey) {
        return bpmService.getHistoryList(processInstanceId, taskKey);
    }

}
