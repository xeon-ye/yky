package com.deloitte.services.srpmp.project.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonJoinVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectTaskVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.*;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetDetailVo;
import com.deloitte.platform.api.srpmp.project.budget.vo.ext.BudgetYearVo;
import com.deloitte.platform.common.core.mq.MqExchangeQueueEnum;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.srpmp.common.enums.*;
import com.deloitte.services.srpmp.common.service.ISysDictService;
import com.deloitte.services.srpmp.common.util.WordConvertUtil;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectBudgetDetail;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectSyncMsg;
import com.deloitte.services.srpmp.project.base.mapper.SrpmsProjectSyncMsgMapper;
import com.deloitte.services.srpmp.project.base.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.srpmp.project.budget.entity.SrpmsProjectBudgetTask;
import com.deloitte.services.srpmp.project.budget.entity.SrpmsProjectBudgetYear;
import com.deloitte.services.srpmp.project.budget.service.ISrpmsProjectBudgetTaskService;
import com.deloitte.services.srpmp.project.update.entity.SrpmsProjectUpdate;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


/**
 * @Author : LIJUN
 * @Date : Create in 2019-04-19
 * @Description :  SrpmsProjectSyncMsg服务实现类
 * @Modified :
 */
@Service
@Transactional
@Slf4j
public class SrpmsProjectSyncMsgServiceImpl extends ServiceImpl<SrpmsProjectSyncMsgMapper, SrpmsProjectSyncMsg> implements ISrpmsProjectSyncMsgService {

    @Autowired
    private ISrpmsProjectBudgetDetailService budgetDetailService;

    @Autowired
    private ISrpmsProjectTaskService taskService;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private ISysDictService dictService;

    @Autowired
    private ISrpmsProjectPersonJoinService personJoinService;

    @Autowired
    private ISrpmsProjectBudgetTaskService budgetTaskService;

    @Autowired
    private ISrpmsProjectService projectService;

    @Override
    public void syncProjectTaskPass(SrpmsProject project) {
        syncProjectAndTaskInfo(project, SrpmsUpdateSyncFsscVo.TYPE_PROJECT_TASK_PASS);
    }

    @Override
    public void syncModifyJoinPerson(SrpmsProjectUpdate update) {
        if (null == update) {
            return;
        }
        SrpmsProjectSyncMsg syncMsg = new SrpmsProjectSyncMsg();
        syncMsg.setMsgType(SrpmsUpdateSyncFsscVo.TYPE_MODIFY_JOIN_PERSON);
        try {
            List<SrpmsProjectPersonJoinVo> personJoinList = personJoinService.queryPersonJoinListByJoinWay(PersonJoinWayEnums.TASK_MAIN_MEMBER, update.getProjectId());
            SrpmsUpdateSyncFsscVo syncFsscVo = new SrpmsUpdateSyncFsscVo();
            syncFsscVo.setMsgType(SrpmsUpdateSyncFsscVo.TYPE_MODIFY_JOIN_PERSON);
            SrpmsPersonUpdateSyncVo personUpdateSyncVo = new SrpmsPersonUpdateSyncVo();
            personUpdateSyncVo.setProjectId(update.getProjectId());
            SrpmsProject project = projectService.getById(update.getProjectId());
            personUpdateSyncVo.setProjectNum(project.getProjectNum());
            personUpdateSyncVo.setUpdateNumber(update.getUpdateNumber());
            personUpdateSyncVo.setPerson(JSON.toJSONString(personJoinList));
            syncFsscVo.setUpdateMemberVo(personUpdateSyncVo);

            syncMsg.setMsgBody(JSON.toJSONString(syncFsscVo));
            syncMsg.setMsgStatus("1");
            this.save(syncMsg);

            //发送消息
            amqpTemplate.convertAndSend(MqExchangeQueueEnum.EXCHANGE_TOPIC.getCode(), MqExchangeQueueEnum.ROUTINGKEY_SRPMP_FSSC.getCode(), JSON.toJSONString(syncFsscVo));
        } catch (Exception e) {
            log.error("syncModifyJoinPerson msg to mq error: ", e);
            syncMsg.setMsgStatus("0");
            syncMsg.setMsgErrorInfo(e.getMessage());
        }
        this.saveOrUpdate(syncMsg);
    }

    @Override
    public void syncModifyLeaderPerson(SrpmsProjectUpdate update, JSONObject leaderPerson) {
        if (null == leaderPerson) {
            return;
        }
        SrpmsProjectSyncMsg syncMsg = new SrpmsProjectSyncMsg();
        syncMsg.setMsgType(SrpmsUpdateSyncFsscVo.TYPE_MODIFY_LEAD_PERSON);
        try {
            SrpmsUpdateSyncFsscVo syncFsscVo = new SrpmsUpdateSyncFsscVo();
            syncFsscVo.setMsgType(SrpmsUpdateSyncFsscVo.TYPE_MODIFY_LEAD_PERSON);
            SrpmsPersonUpdateSyncVo personUpdateSyncVo = new SrpmsPersonUpdateSyncVo();
            personUpdateSyncVo.setProjectId(update.getProjectId());
            SrpmsProject project = projectService.getById(update.getProjectId());
            personUpdateSyncVo.setProjectNum(project.getProjectNum());
            personUpdateSyncVo.setUpdateNumber(update.getUpdateNumber());
            personUpdateSyncVo.setPerson(leaderPerson.toJSONString());
            syncFsscVo.setUpdateLeaderVo(personUpdateSyncVo);

            syncMsg.setMsgBody(JSON.toJSONString(syncFsscVo));
            syncMsg.setMsgStatus("1");

            //发送消息
            amqpTemplate.convertAndSend(MqExchangeQueueEnum.EXCHANGE_TOPIC.getCode(), MqExchangeQueueEnum.ROUTINGKEY_SRPMP_FSSC.getCode(), JSON.toJSONString(syncFsscVo));
        } catch (Exception e) {
            log.error("syncModifyLeaderPerson msg to mq error: ", e);
            syncMsg.setMsgStatus("0");
            syncMsg.setMsgErrorInfo(e.getMessage());
        }
        this.saveOrUpdate(syncMsg);
    }

    @Override
    public void syncModifyProjectStatus(SrpmsProject project) {
        if (null == project) {
            return;
        }
        SrpmsProjectSyncMsg syncMsg = new SrpmsProjectSyncMsg();
        syncMsg.setRelationId(project.getId());
        syncMsg.setMsgType(SrpmsUpdateSyncFsscVo.TYPE_MODIFY_PROJECT_STATUS);
        try {
            SrpmsUpdateSyncFsscVo syncFsscVo = new SrpmsUpdateSyncFsscVo();
            syncFsscVo.setMsgType(SrpmsUpdateSyncFsscVo.TYPE_MODIFY_PROJECT_STATUS);
            SrpmsProjectSyncVo projectSyncVo = new BeanUtils<SrpmsProjectSyncVo>().copyObj(project, SrpmsProjectSyncVo.class);
            if (StringUtils.isNotBlank(project.getLeadDept())) {
                projectSyncVo.setLeadDept(JSONObject.parseObject(project.getLeadDept()));
            }
            if (StringUtils.isNotBlank(project.getLeadPerson())) {
                projectSyncVo.setLeadPerson(JSONObject.parseObject(project.getLeadPerson()));
            }
            if (StringUtils.isNotBlank(project.getBothTopExpertPerson())) {
                projectSyncVo.setBothTopExpertPerson(JSONObject.parseObject(project.getBothTopExpertPerson()));
            }
            String projectCateGory = project.getProjectCategory();
            if (StringUtils.isNotBlank(projectCateGory)) {
                List<String> codeList = JSONArray.parseArray(projectCateGory, String.class);
                List<String> codeDescList = Lists.newArrayList();
                for (String code : codeList) {
                    String activeTypeName = dictService.selectValueByCode(SysDictEnums.PRO_TYPE, code);
                    codeDescList.add(activeTypeName);
                }
                projectSyncVo.setProjectCategoryDesc(JSON.toJSONString(codeDescList));
            }
            //参与人员
            List<SrpmsProjectPersonJoinVo> personJoinList = personJoinService.queryPersonJoinListByJoinWay(PersonJoinWayEnums.TASK_MAIN_MEMBER, project.getId());
            projectSyncVo.setProjectPersonJoinVoList(personJoinList);

            syncFsscVo.setSrpmsProjectSyncVo(projectSyncVo);

            syncMsg.setMsgBody(JSON.toJSONString(syncFsscVo));
            syncMsg.setMsgStatus("1");

            //发送消息
            amqpTemplate.convertAndSend(MqExchangeQueueEnum.EXCHANGE_TOPIC.getCode(), MqExchangeQueueEnum.ROUTINGKEY_SRPMP_FSSC.getCode(), JSON.toJSONString(syncFsscVo));
        } catch (Exception e) {
            log.error("syncModifyProjectStatus msg to mq error: ", e);
            syncMsg.setMsgStatus("0");
            syncMsg.setMsgErrorInfo(e.getMessage());
        }
        this.saveOrUpdate(syncMsg);
    }

    @Override
    public void syncBudgetTask(SrpmsProject project) {
        if (null == project) {
            return;
        }
        SrpmsProjectSyncMsg syncMsg = new SrpmsProjectSyncMsg();
        syncMsg.setMsgType(SrpmsUpdateSyncFsscVo.TYPE_BUDGET_TASK_SYNC);
        try {
            SrpmsUpdateSyncFsscVo syncFsscVo = new SrpmsUpdateSyncFsscVo();
            syncFsscVo.setMsgType(SrpmsUpdateSyncFsscVo.TYPE_BUDGET_TASK_SYNC);
            SrpmsProjectBudgetTask budgetTask = budgetTaskService.getById(project.getId());
            if (null != budgetTask) {
                syncMsg.setRelationId(budgetTask.getId());
                if (StringUtils.isNotBlank(budgetTask.getProjectTarget())) {
                    budgetTask.setProjectTarget(WordConvertUtil.htmlToText(budgetTask.getProjectTarget()));
                }
                SrpmsBudgetTaskSyncVo budgetTaskSyncVo = new BeanUtils<SrpmsBudgetTaskSyncVo>().copyObj(budgetTask, SrpmsBudgetTaskSyncVo.class);
                budgetTaskSyncVo.setSpecifSupport(WordConvertUtil.htmlToText(budgetTask.getSpecifSupport()));
                budgetTaskSyncVo.setSpecifMoneyPlan(WordConvertUtil.htmlToText(budgetTask.getSpecifMoneyPlan()));
                budgetTaskSyncVo.setSpecifFacility(WordConvertUtil.htmlToText(budgetTask.getSpecifFacility()));
                budgetTaskSyncVo.setSpecifMaterial(WordConvertUtil.htmlToText(budgetTask.getSpecifMaterial()));
                budgetTaskSyncVo.setSpecifTest(WordConvertUtil.htmlToText(budgetTask.getSpecifTest()));
                budgetTaskSyncVo.setSpecifFuel(WordConvertUtil.htmlToText(budgetTask.getSpecifFuel()));
                budgetTaskSyncVo.setSpecifTravel(WordConvertUtil.htmlToText(budgetTask.getSpecifTravel()));
                budgetTaskSyncVo.setSpecifPublish(WordConvertUtil.htmlToText(budgetTask.getSpecifPublish()));
                budgetTaskSyncVo.setSpecifLabour(WordConvertUtil.htmlToText(budgetTask.getSpecifLabour()));
                budgetTaskSyncVo.setSpecifConsult(WordConvertUtil.htmlToText(budgetTask.getSpecifConsult()));
                budgetTaskSyncVo.setSpecifOther(WordConvertUtil.htmlToText(budgetTask.getSpecifOther()));
                budgetTaskSyncVo.setProjectTarget(WordConvertUtil.htmlToText(budgetTask.getProjectTarget()));
                budgetTaskSyncVo.setLeadDeptId(project.getLeadDeptId());
                budgetTaskSyncVo.setLeadDept(project.getLeadDept());
                budgetTaskSyncVo.setProjectId(project.getId());
                syncFsscVo.setBudgetTask(budgetTaskSyncVo);
            }

            syncMsg.setMsgBody(JSON.toJSONString(syncFsscVo));
            syncMsg.setMsgStatus("1");

            //发送消息
            amqpTemplate.convertAndSend(MqExchangeQueueEnum.EXCHANGE_TOPIC.getCode(), MqExchangeQueueEnum.ROUTINGKEY_SRPMP_FSSC.getCode(), JSON.toJSONString(syncFsscVo));
        } catch (Exception e) {
            log.error("syncBudgetTask msg to mq error: ", e);
            syncMsg.setMsgStatus("0");
            syncMsg.setMsgErrorInfo(e.getMessage());
        }
        this.saveOrUpdate(syncMsg);
    }

    @Override
    public void syncBudgetYear(SrpmsProjectBudgetYear budgetYear) {
        if (null == budgetYear) {
            return;
        }
        SrpmsProject project = projectService.getById(budgetYear.getProjectId());
        syncProjectAndTaskInfo(project, SrpmsUpdateSyncFsscVo.TYPE_BUDGET_APPLY_SYNC);
    }

    @Override
    public void syncModifyBudget(Long projectId) {
        if (null == projectId) {
            return;
        }
        SrpmsProject project = projectService.getById(projectId);
        syncProjectAndTaskInfo(project, SrpmsUpdateSyncFsscVo.TYPE_MODIFY_BUDGET_SYNC);
    }

    private void syncProjectAndTaskInfo(SrpmsProject project, String msgType) {
        if (null == project) {
            return;
        }
        SrpmsProjectSyncMsg syncMsg = new SrpmsProjectSyncMsg();
        syncMsg.setRelationId(project.getId());
        syncMsg.setMsgType(msgType);
        try {

            SrpmsUpdateSyncFsscVo syncFsscVo = new SrpmsUpdateSyncFsscVo();
            syncFsscVo.setMsgType(msgType);
            SrpmsProjectSyncVo projectSyncVo = new BeanUtils<SrpmsProjectSyncVo>().copyObj(project, SrpmsProjectSyncVo.class);
            if (StringUtils.isNotBlank(project.getLeadDept())) {
                projectSyncVo.setLeadDept(JSONObject.parseObject(project.getLeadDept()));
            }
            if (StringUtils.isNotBlank(project.getLeadPerson())) {
                projectSyncVo.setLeadPerson(JSONObject.parseObject(project.getLeadPerson()));
            }
            if (StringUtils.isNotBlank(project.getBothTopExpertPerson())) {
                projectSyncVo.setBothTopExpertPerson(JSONObject.parseObject(project.getBothTopExpertPerson()));
            }
            String projectCateGory = project.getProjectCategory();
            if (StringUtils.isNotBlank(projectCateGory)) {
                List<String> codeList = JSONArray.parseArray(projectCateGory, String.class);
                List<String> codeDescList = Lists.newArrayList();
                for (String code : codeList) {
                    if (StringUtils.equals("10", code)) {
                        codeDescList.add("创新工程");
                        continue;
                    }
                    String activeTypeName = dictService.selectValueByCode(SysDictEnums.PRO_CAT, code);
                    codeDescList.add(activeTypeName);
                }
                projectSyncVo.setProjectCategoryDesc(JSON.toJSONString(codeDescList));
            }

            //参与人员
            List<SrpmsProjectPersonJoinVo> personJoinList = personJoinService.queryPersonJoinListByJoinWay(PersonJoinWayEnums.TASK_MAIN_MEMBER, project.getId());
            projectSyncVo.setProjectPersonJoinVoList(personJoinList);

            //创新工程（查询预算申请总预算）
            if (StringUtils.equals(project.getProjectCategory(), ProjectCategoryEnums.INNOVATE_BCOO.getCode())
                    || StringUtils.equals(project.getProjectCategory(), ProjectCategoryEnums.INNOVATE_COO.getCode())
                    || StringUtils.equals(project.getProjectCategory(), ProjectCategoryEnums.INNOVATE_PRE.getCode())
                    || StringUtils.equals(project.getProjectCategory(), ProjectCategoryEnums.INNOVATE_YOU.getCode())) {
                TaskCategoryEnums taskCategoryEnums;
                if (StringUtils.equals(msgType, SrpmsUpdateSyncFsscVo.TYPE_BUDGET_APPLY_SYNC)) {
                    //年度预算申请时，只更新了任务书，这里查询任务书
                    taskCategoryEnums = TaskCategoryEnums.TASK_INNOVATE_BUDGET_ALL;
                } else {
                    taskCategoryEnums = TaskCategoryEnums.APPLY_INNOVATE_BUDGET_ALL;
                }
                List<SrpmsProjectTaskVo> taskVoList = taskService.queryTaskListByTaskCategory(taskCategoryEnums, project.getId());
                List<SrpmsProjectTaskSyncVo> taskSyncVoList = Lists.newArrayList();
                for (SrpmsProjectTaskVo vo : taskVoList) {
                    SrpmsProjectTaskSyncVo taskSyncVo = new BeanUtils<SrpmsProjectTaskSyncVo>().copyObj(vo, SrpmsProjectTaskSyncVo.class);
                    taskSyncVo.setTaskCategoryDesc(TaskCategoryEnums.APPLY_INNOVATE_BUDGET_ALL.getDesc());
                    if (null != vo.getBudgetDetail()) {
                        taskSyncVo.setBudgetDetail(JSON.parseArray(vo.getBudgetDetail().toJSONString(), BudgetDetailVo.class));
                    }
                    taskSyncVoList.add(taskSyncVo);
                }
                projectSyncVo.setSrpmsProjectTaskSyncVoList(taskSyncVoList);
                syncFsscVo.setSrpmsProjectSyncVo(projectSyncVo);
            } else { //其他项目查询任务书预算明细
                BudgetCategoryEnums budgetCategoryEnum;
                if (StringUtils.equals(project.getProjectFlag(), "1")) { //横纵项
                    budgetCategoryEnum = BudgetCategoryEnums.TASK_TRAN_LONG_DETAIL;
                } else {
                    budgetCategoryEnum = BudgetCategoryEnums.TASK_YEAR_PLAN_DETAIL;
                }
                List<SrpmsProjectBudgetDetailVo> detailVoList = budgetDetailService.queryBudgetDetailListByCategory(budgetCategoryEnum, project.getId());
                for (SrpmsProjectBudgetDetailVo vo : detailVoList) {
                    if (null != vo.getBudgetDetail()) {
                        vo.setBudgetDetailVo(JSON.parseArray(vo.getBudgetDetail().toJSONString(), BudgetDetailVo.class));
                    }
                }
                projectSyncVo.setBudgetDetailVoList(detailVoList);
                syncFsscVo.setSrpmsProjectSyncVo(projectSyncVo);
            }
            syncMsg.setMsgBody(JSON.toJSONString(syncFsscVo));
            syncMsg.setMsgStatus("1");


            //发送消息
            amqpTemplate.convertAndSend(MqExchangeQueueEnum.EXCHANGE_TOPIC.getCode(), MqExchangeQueueEnum.ROUTINGKEY_SRPMP_FSSC.getCode(), JSON.toJSONString(syncFsscVo));
        } catch (Exception e) {
            log.error("sync " + msgType + " msg to mq error: ", e);
            syncMsg.setMsgStatus("0");
            syncMsg.setMsgErrorInfo(e.getMessage());
        }
        this.saveOrUpdate(syncMsg);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<SrpmsProjectSyncMsg> getQueryWrapper(QueryWrapper<SrpmsProjectSyncMsg> queryWrapper,SrpmsProjectSyncMsgQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getRelationId())){
            queryWrapper.eq(SrpmsProjectSyncMsg.RELATION_ID,queryForm.getRelationId());
        }
        if(StringUtils.isNotBlank(queryForm.getMsgType())){
            queryWrapper.eq(SrpmsProjectSyncMsg.MSG_TYPE,queryForm.getMsgType());
        }
        if(StringUtils.isNotBlank(queryForm.getMsgBody())){
            queryWrapper.eq(SrpmsProjectSyncMsg.MSG_BODY,queryForm.getMsgBody());
        }
        if(StringUtils.isNotBlank(queryForm.getMsgStatus())){
            queryWrapper.eq(SrpmsProjectSyncMsg.MSG_STATUS,queryForm.getMsgStatus());
        }
        if(StringUtils.isNotBlank(queryForm.getMsgErrorInfo())){
            queryWrapper.eq(SrpmsProjectSyncMsg.MSG_ERROR_INFO,queryForm.getMsgErrorInfo());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(SrpmsProjectSyncMsg.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(SrpmsProjectSyncMsg.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(SrpmsProjectSyncMsg.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(SrpmsProjectSyncMsg.UPDATE_BY,queryForm.getUpdateBy());
        }
        return queryWrapper;
    }
     */
}

