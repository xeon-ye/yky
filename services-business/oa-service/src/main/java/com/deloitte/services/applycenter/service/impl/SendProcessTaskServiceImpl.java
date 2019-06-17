package com.deloitte.services.applycenter.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.bpmservice.feign.BpmProcessTaskFeignService;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskForm;
import com.deloitte.platform.api.isump.feign.UserFeignService;
import com.deloitte.platform.api.oaservice.applycenter.feign.SendProcessTaskFeignService;
import com.deloitte.platform.api.oaservice.applycenter.param.SendProcessTaskQueryForm;
import com.deloitte.platform.api.oaservice.applycenter.vo.SendProcessTaskForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.ServiceException;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.applycenter.entity.SendProcessTask;
import com.deloitte.services.applycenter.mapper.SendProcessTaskMapper;
import com.deloitte.services.applycenter.service.ISendProcessTaskService;
import com.deloitte.services.oa.exception.OaErrorType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * @Author : yidaojun
 * @Date : Create in 2019-05-09
 * @Description :  SendProcessTask服务实现类
 * @Modified :
 */
@Service
@Slf4j
@Transactional
public class SendProcessTaskServiceImpl extends ServiceImpl<SendProcessTaskMapper, SendProcessTask> implements ISendProcessTaskService {


    @Autowired
    private SendProcessTaskMapper sendProcessTaskMapper;

    @Autowired
    BpmProcessTaskFeignService bpmProcessTaskFeignService;

    @Autowired
    ISendProcessTaskService iSendProcessTaskService;

    @Autowired
    SendProcessTaskFeignService sendProcessTaskFeignService;

    @Autowired
    UserFeignService userFeignService;

    @Override
    public Result save(SendProcessTaskForm taskForm) {
        SendProcessTask sendProcessTask = new BeanUtils<SendProcessTask>().copyObj(taskForm, SendProcessTask.class);
        boolean flag = this.save(sendProcessTask);
        if (flag) {
            sendProcessTask.setTaskId(String.valueOf(sendProcessTask.getId()));
            this.updateById(sendProcessTask);
        }
        return Result.success();
    }

    @Override
    public IPage<SendProcessTask> selectPage(SendProcessTaskQueryForm queryForm) {
        QueryWrapper<SendProcessTask> queryWrapper = new QueryWrapper<SendProcessTask>();
        getQueryWrapper(queryWrapper, queryForm);
        return sendProcessTaskMapper.selectPage(new Page<SendProcessTask>(queryForm.getCurrentPage(), queryForm.getPageSize()), queryWrapper);

    }

    @Override
    public List<SendProcessTask> selectList(SendProcessTaskQueryForm queryForm) {
        QueryWrapper<SendProcessTask> queryWrapper = new QueryWrapper<SendProcessTask>();
        queryWrapper.eq(SendProcessTask.TASK_ID, queryForm.getTaskId());
        //getQueryWrapper(queryWrapper,queryForm);
        return sendProcessTaskMapper.selectList(queryWrapper);
    }


    @Override
    public Result syncData(SendProcessTaskQueryForm queryForm) {
        String taskStauts = queryForm.getTaskStauts();
        if (queryForm.getTaskId() != null && queryForm.getTaskId().startsWith("start")) {
            queryForm.setTaskKey("start");
        } else {
            queryForm.setTaskKey("OA_TASK");
        }
        queryForm.setObjectId("OA_OBJECT");
        //if ("pendApprova".equals(taskStauts) || "notRead".equals(taskStauts)) {
        Result info = this.getInfo(queryForm);
        if (info.isFail()) {
            return Result.fail(info.getMesg());
        }
        //}
        Result result = null;
        //待办
        if ("pendApprova".equals(taskStauts)) {
            BpmProcessTaskForm taskForm = new BeanUtils<BpmProcessTaskForm>().copyObj(queryForm, BpmProcessTaskForm.class);
            taskForm.setTaskStauts("待审批");
            result = bpmProcessTaskFeignService.add(taskForm);
        }
        //待阅
        if ("notRead".equals(taskStauts)) {
            List<SendProcessTask> sendProcessTasks = iSendProcessTaskService.selectList(queryForm);
            if (CollectionUtils.isNotEmpty(sendProcessTasks)) {
                return Result.success("该待阅数据已存在");
            }
            SendProcessTask sendProcessTask = new BeanUtils<SendProcessTask>().copyObj(queryForm, SendProcessTask.class);
            sendProcessTask.setTaskStauts("待阅");
            boolean flag = iSendProcessTaskService.save(sendProcessTask);
            if (flag) {
                result = Result.success(true);
            } else {
                result = Result.fail(false);
            }
        }
        //已办
        if ("approved".equals(taskStauts)) {
            //根据taskId和processInstanceId更新
            BpmProcessTaskForm taskForm = new BeanUtils<BpmProcessTaskForm>().copyObj(queryForm, BpmProcessTaskForm.class);
            taskForm.setSignTime(queryForm.getUpdateTime());
            if (queryForm.getTaskId() != null && queryForm.getTaskId().startsWith("start")) {
                taskForm.setTaskStauts("已提交");
            } else {
                taskForm.setTaskStauts("已批准");
            }
            result = bpmProcessTaskFeignService.updateHistoryByProcessInsIdAndTaskId(taskForm);
            if ("000000".equals(result.getCode())) {
                result = Result.success(true);
            } else {
                result = Result.fail(false);
            }
        }
        //已阅
        if ("read".equals(taskStauts)) {
            SendProcessTask sendProcessTask = new BeanUtils<SendProcessTask>().copyObj(queryForm, SendProcessTask.class);
            sendProcessTask.setTaskStauts("已阅");
            QueryWrapper<SendProcessTask> queryWrapper = new QueryWrapper<SendProcessTask>();
            queryWrapper.eq(SendProcessTask.TASK_ID, queryForm.getTaskId());
            int flag = sendProcessTaskMapper.update(sendProcessTask, queryWrapper);
            if (1 == flag) {
                result = Result.success(true);
            } else {
                result = Result.fail(false);
            }
        }
        return result;
    }

    /**
     * 根据员工编号获取员工信息
     *
     * @param queryForm
     * @return
     */
    private Result getInfo(SendProcessTaskQueryForm queryForm) {
        //泛微同步的SubmitterCode为员工编号
        Result submitInfo = userFeignService.getByEmpNo(queryForm.getSubmitterCode());
        log.info("submitInfo:{}", JSON.toJSONString(submitInfo));
        if (submitInfo.isSuccess()) {
            Object data = submitInfo.getData();
            if (null == data) {
                return Result.fail("根据员工编号，查无此人");
            }
            Map<String, Object> userInfo = (Map<String, Object>) data;
            queryForm.setSubmitterCode(String.valueOf(userInfo.get("id")));
            queryForm.setSubmitterName(String.valueOf(userInfo.get("name")));
            Object object = userInfo.get("currentDeputyAccount");
            if (object != null) {
                Map<String, Object> accountMap = (Map<String, Object>) object;
                queryForm.setSubmitterOrg(String.valueOf(accountMap.get("orgCode")));
            } else {
                throw new ServiceException(OaErrorType.USERINFO_ERROR, "获取用户部门信息失败 !");
            }
        } else {
            throw new ServiceException(OaErrorType.USERINFO_ERROR, submitInfo.getMesg());
        }
        //当前待办创建人
        Result createInfo = userFeignService.getByEmpNo(queryForm.getCreateBy());
        log.info("createInfo:{}", JSON.toJSONString(createInfo));
        if (createInfo.isSuccess()) {
            Object data = createInfo.getData();
            if (null == data) {
                return Result.fail("根据员工编号，查无此人");
            }
            Map<String, String> userInfo = (Map<String, String>) data;
            queryForm.setCreateBy(userInfo.get("id"));
            queryForm.setCreateByName(userInfo.get("name"));
            Object object = userInfo.get("currentDeputyAccount");
            if (object != null) {
                Map<String, Object> accountMap = (Map<String, Object>) object;
                queryForm.setCreateByOrgId(String.valueOf(accountMap.get("orgCode")));
            } else {
                throw new ServiceException(OaErrorType.USERINFO_ERROR, "获取用户部门信息失败 !");
            }
        } else {
            throw new ServiceException(OaErrorType.USERINFO_ERROR, submitInfo.getMesg());
        }
        //当前待办接收人
        Result receiveInfo = userFeignService.getByEmpNo(queryForm.getApproverAcount());
        log.info("receiveInfo:{}", JSON.toJSONString(receiveInfo));
        if (receiveInfo.isSuccess()) {
            Object data = receiveInfo.getData();
            if (null == data) {
                return Result.fail("根据员工编号，查无此人");
            }
            Map<String, String> userInfo = (Map<String, String>) data;
            queryForm.setApproverAcount(userInfo.get("id"));
            queryForm.setApproverName(userInfo.get("name"));
            Object object = userInfo.get("currentDeputyAccount");
            if (object != null) {
                Map<String, Object> accountMap = (Map<String, Object>) object;
                queryForm.setApproverOrg(String.valueOf(accountMap.get("orgCode")));
            } else {
                throw new ServiceException(OaErrorType.USERINFO_ERROR, "获取用户部门信息失败 !");
            }
        } else {
            throw new ServiceException(OaErrorType.USERINFO_ERROR, submitInfo.getMesg());
        }
        return Result.success();
    }

    /**
     * 通用查询
     *
     * @param queryWrapper
     * @param queryForm
     * @return
     */
    public QueryWrapper<SendProcessTask> getQueryWrapper(QueryWrapper<SendProcessTask> queryWrapper, SendProcessTaskQueryForm queryForm) {

        if (StringUtils.isNotBlank(queryForm.getObjectDescription())) {
            queryWrapper.like(SendProcessTask.OBJECT_DESCRIPTION, queryForm.getObjectDescription());
        }
        if (StringUtils.isNotBlank(queryForm.getTaskStauts())) {
            queryWrapper.eq(SendProcessTask.TASK_STAUTS, queryForm.getTaskStauts());
        }
        if (StringUtils.isNotBlank(queryForm.getApproverAcount())) {
            queryWrapper.eq(SendProcessTask.APPROVER_ACOUNT, queryForm.getApproverAcount());
        }
        if (StringUtils.isNotBlank(queryForm.getSourceSystem()) && !"otherSystem".equals(queryForm.getSourceSystem()) && !"OA".equals(queryForm.getSourceSystem())) {
            queryWrapper.eq(SendProcessTask.SOURCE_SYSTEM, queryForm.getSourceSystem());
        }
        if ("otherSystem".equals(queryForm.getSourceSystem())) {
            queryWrapper.notIn(SendProcessTask.SOURCE_SYSTEM, new String[]{"srpms", "FSSC", "oaservice", "OA", "HR"});
        }
        if (StringUtils.isBlank(queryForm.getSourceSystem())) {
            queryWrapper.notIn(SendProcessTask.SOURCE_SYSTEM, "srpms");
        }
        if ("OA".equals(queryForm.getSourceSystem())) {
            queryWrapper.in(SendProcessTask.SOURCE_SYSTEM, new String[]{"oaservice", "OA"});
        }
        queryWrapper.orderByDesc(SendProcessTask.CREATE_TIME);
        return queryWrapper;
    }

    @Override
    public IPage<SendProcessTask> selectPage2(SendProcessTaskQueryForm queryForm) {
        QueryWrapper<SendProcessTask> queryWrapper = new QueryWrapper<SendProcessTask>();
        if (StringUtils.isNotBlank(queryForm.getTaskStauts())) {
            queryWrapper.eq(SendProcessTask.TASK_STAUTS, queryForm.getTaskStauts());
        }
        if (StringUtils.isNotBlank(queryForm.getApproverAcount())) {
            queryWrapper.eq(SendProcessTask.APPROVER_ACOUNT, queryForm.getApproverAcount());
        }
        if (StringUtils.isNotBlank(queryForm.getSourceSystem())) {
            queryWrapper.eq(SendProcessTask.SOURCE_SYSTEM, queryForm.getSourceSystem());
        }
        if (StringUtils.isNotBlank(queryForm.getObjectDescription())) {
            queryWrapper.like(SendProcessTask.OBJECT_DESCRIPTION, queryForm.getObjectDescription());
        }
        if (null != queryForm.getStartTime() && null != queryForm.getEndTime()) {
            queryWrapper.between(SendProcessTask.CREATE_TIME, LocalDateTime.parse(queryForm.getStartTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), LocalDateTime.parse(queryForm.getEndTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        if (StringUtils.isNotBlank(queryForm.getObjectType())) {
            queryWrapper.eq(SendProcessTask.OBJECT_TYPE, queryForm.getObjectType());
        }
        queryWrapper.orderByDesc(SendProcessTask.CREATE_TIME);
        return sendProcessTaskMapper.selectPage(new Page<SendProcessTask>(queryForm.getCurrentPage(), queryForm.getPageSize()), queryWrapper);

    }
}

