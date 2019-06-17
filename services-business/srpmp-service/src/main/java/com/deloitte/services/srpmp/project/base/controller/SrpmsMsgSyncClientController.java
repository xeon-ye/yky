package com.deloitte.services.srpmp.project.base.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.srpmp.project.base.SrpmsMsgSyncClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.mq.MqExchangeQueueEnum;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectSyncMsg;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectSyncMsgService;
import com.deloitte.services.srpmp.project.budget.entity.SrpmsProjectBudgetYear;
import com.deloitte.services.srpmp.project.budget.mapper.SrpmsProjectBudgetYearMapper;
import com.deloitte.services.srpmp.project.budget.service.ISrpmsProjectBudgetApplyYearService;
import com.deloitte.services.srpmp.project.update.entity.SrpmsProjectUpdate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author:LIJUN
 * Date:17/04/2019
 * Description:
 */
@RestController
public class SrpmsMsgSyncClientController implements SrpmsMsgSyncClient {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private ISrpmsProjectService projectService;

    @Autowired
    private SrpmsProjectBudgetYearMapper budgetYearMapper;

    @Autowired
    private ISrpmsProjectSyncMsgService projectSyncMsgService;

    @Override
    public Result resendFailMsg(@PathVariable(value = "msgId") Long msgId) {
        SrpmsProjectSyncMsg msg = projectSyncMsgService.getById(msgId);
        if (null != msg && StringUtils.equals(msg.getMsgStatus(), "0")) {
            amqpTemplate.convertAndSend(MqExchangeQueueEnum.EXCHANGE_TOPIC.getCode(), MqExchangeQueueEnum.ROUTINGKEY_SRPMP_FSSC.getCode(), msg.getMsgBody());
            msg.setMsgStatus("1");
            projectSyncMsgService.updateById(msg);
        }
        return Result.success();
    }

    @Override
    public Result sendMsg(@PathVariable(value = "projectId") Long projectId, @PathVariable(value = "msgType") Long msgType) {
        if (msgType == 1) {
            SrpmsProject project = projectService.getById(projectId);
            projectSyncMsgService.syncProjectTaskPass(project);
        }
        if (msgType == 2) {
            SrpmsProjectUpdate update = new SrpmsProjectUpdate();
            update.setProjectId(projectId);
            update.setUpdateNumber("TEST0002");
            String a = "[{\"personName\":\"\",\"gender\":\"\",\"birthDate\":\"\",\"degree\":\"\",\"deptName\":\"\",\"positionTitle\":\"\",\"phone\":\"\",\"index\":0},{\"personName\":\"王利\",\"gender\":\"女\",\"birthDate\":\"1995-01-05 22:18:20\",\"degree\":\"博士\",\"deptName\":\"中国医学科学院药物研究所\",\"positionTitle\":\"中级\",\"phone\":\"13100000397\",\"index\":1}]";
            JSONArray array = JSON.parseArray(a);
            projectSyncMsgService.syncModifyJoinPerson(update);
        }
        if (msgType == 3) {
            SrpmsProjectUpdate update = new SrpmsProjectUpdate();
            update.setProjectId(projectId);
            update.setUpdateNumber("TEST0003");
            String a = "{\"personName\":\"王利\",\"gender\":\"女\",\"birthDate\":\"1995-01-05 22:18:20\",\"degree\":\"博士\",\"deptName\":\"中国医学科学院药物研究所\",\"positionTitle\":\"中级\",\"phone\":\"13100000397\",\"index\":1}";
            JSONObject obj = JSON.parseObject(a);
            projectSyncMsgService.syncModifyLeaderPerson(update, obj);
        }
        if (msgType == 4) {
            SrpmsProject project = projectService.getById(projectId);
            projectSyncMsgService.syncModifyProjectStatus(project);
        }
        if (msgType == 5) {
            SrpmsProject project = projectService.getById(projectId);
            projectSyncMsgService.syncBudgetTask(project);
        }
        if (msgType == 6) {
            SrpmsProjectBudgetYear budgetYear = budgetYearMapper.selectById(projectId);
            projectSyncMsgService.syncBudgetYear(budgetYear);
        }
        if (msgType == 7) {
            projectSyncMsgService.syncModifyBudget(projectId);
        }
        return Result.success();
    }
}
