package com.deloitte.services.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.contract.param.ProcessTaskQueryForm;
import com.deloitte.services.contract.entity.ProcessTask;
import com.deloitte.services.contract.mapper.ProcessTaskMapper;
import com.deloitte.services.contract.service.IProcessTaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-02
 * @Description :  ProcessTask服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ProcessTaskServiceImpl extends ServiceImpl<ProcessTaskMapper, ProcessTask> implements IProcessTaskService {


    @Autowired
    private ProcessTaskMapper processTaskMapper;

    @Override
    public IPage<ProcessTask> selectPage(ProcessTaskQueryForm queryForm ) {
        QueryWrapper<ProcessTask> queryWrapper = new QueryWrapper <ProcessTask>();
        //getQueryWrapper(queryWrapper,queryForm);
        return processTaskMapper.selectPage(new Page<ProcessTask>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<ProcessTask> selectList(ProcessTaskQueryForm queryForm) {
        QueryWrapper<ProcessTask> queryWrapper = new QueryWrapper <ProcessTask>();
        //getQueryWrapper(queryWrapper,queryForm);
        return processTaskMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<ProcessTask> getQueryWrapper(QueryWrapper<ProcessTask> queryWrapper,ProcessTaskQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getProcessDefineKey())){
            queryWrapper.eq(ProcessTask.PROCESS_DEFINE_KEY,queryForm.getProcessDefineKey());
        }
        if(StringUtils.isNotBlank(queryForm.getProcessInstanceId())){
            queryWrapper.eq(ProcessTask.PROCESS_INSTANCE_ID,queryForm.getProcessInstanceId());
        }
        if(StringUtils.isNotBlank(queryForm.getTaskId())){
            queryWrapper.eq(ProcessTask.TASK_ID,queryForm.getTaskId());
        }
        if(StringUtils.isNotBlank(queryForm.getTaskKey())){
            queryWrapper.eq(ProcessTask.TASK_KEY,queryForm.getTaskKey());
        }
        if(StringUtils.isNotBlank(queryForm.getTaskName())){
            queryWrapper.eq(ProcessTask.TASK_NAME,queryForm.getTaskName());
        }
        if(StringUtils.isNotBlank(queryForm.getTaskTitle())){
            queryWrapper.eq(ProcessTask.TASK_TITLE,queryForm.getTaskTitle());
        }
        if(StringUtils.isNotBlank(queryForm.getTaskDescription())){
            queryWrapper.eq(ProcessTask.TASK_DESCRIPTION,queryForm.getTaskDescription());
        }
        if(StringUtils.isNotBlank(queryForm.getTaskStauts())){
            queryWrapper.eq(ProcessTask.TASK_STAUTS,queryForm.getTaskStauts());
        }
        if(StringUtils.isNotBlank(queryForm.getApproverAcount())){
            queryWrapper.eq(ProcessTask.APPROVER_ACOUNT,queryForm.getApproverAcount());
        }
        if(StringUtils.isNotBlank(queryForm.getApproverName())){
            queryWrapper.eq(ProcessTask.APPROVER_NAME,queryForm.getApproverName());
        }
        if(StringUtils.isNotBlank(queryForm.getApproverStation())){
            queryWrapper.eq(ProcessTask.APPROVER_STATION,queryForm.getApproverStation());
        }
        if(StringUtils.isNotBlank(queryForm.getApproverDescription())){
            queryWrapper.eq(ProcessTask.APPROVER_DESCRIPTION,queryForm.getApproverDescription());
        }
        if(StringUtils.isNotBlank(queryForm.getObjectId())){
            queryWrapper.eq(ProcessTask.OBJECT_ID,queryForm.getObjectId());
        }
        if(StringUtils.isNotBlank(queryForm.getObjectOrderNum())){
            queryWrapper.eq(ProcessTask.OBJECT_ORDER_NUM,queryForm.getObjectOrderNum());
        }
        if(StringUtils.isNotBlank(queryForm.getObjectStauts())){
            queryWrapper.eq(ProcessTask.OBJECT_STAUTS,queryForm.getObjectStauts());
        }
        if(StringUtils.isNotBlank(queryForm.getObjectType())){
            queryWrapper.eq(ProcessTask.OBJECT_TYPE,queryForm.getObjectType());
        }
        if(StringUtils.isNotBlank(queryForm.getObjectUrl())){
            queryWrapper.eq(ProcessTask.OBJECT_URL,queryForm.getObjectUrl());
        }
        if(StringUtils.isNotBlank(queryForm.getObjectDescription())){
            queryWrapper.eq(ProcessTask.OBJECT_DESCRIPTION,queryForm.getObjectDescription());
        }
        if(StringUtils.isNotBlank(queryForm.getOpinion())){
            queryWrapper.eq(ProcessTask.OPINION,queryForm.getOpinion());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(ProcessTask.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(ProcessTask.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getDurationTime())){
            queryWrapper.eq(ProcessTask.DURATION_TIME,queryForm.getDurationTime());
        }
        if(StringUtils.isNotBlank(queryForm.getSubmitterCode())){
            queryWrapper.eq(ProcessTask.SUBMITTER_CODE,queryForm.getSubmitterCode());
        }
        if(StringUtils.isNotBlank(queryForm.getSubmitterName())){
            queryWrapper.eq(ProcessTask.SUBMITTER_NAME,queryForm.getSubmitterName());
        }
        if(StringUtils.isNotBlank(queryForm.getSubmitterOrg())){
            queryWrapper.eq(ProcessTask.SUBMITTER_ORG,queryForm.getSubmitterOrg());
        }
        if(StringUtils.isNotBlank(queryForm.getApproverOrg())){
            queryWrapper.eq(ProcessTask.APPROVER_ORG,queryForm.getApproverOrg());
        }
        return queryWrapper;
    }
     */
}

