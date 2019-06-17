package com.deloitte.platform.basic.bpmservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.basic.bpmservice.entity.BpmProcessTask;
import com.deloitte.platform.basic.bpmservice.entity.BpmProcessTaskApprove;
import com.deloitte.platform.basic.bpmservice.mapper.BpmProcessTaskApproveMapper;
import com.deloitte.platform.basic.bpmservice.service.IBpmProcessTaskApproveService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BpmProcessTaskApproveServiceImpl extends ServiceImpl<BpmProcessTaskApproveMapper, BpmProcessTaskApprove> implements IBpmProcessTaskApproveService{

    public List<BpmProcessTaskApprove> selectList(BpmProcessTaskApprove queryForm){
        QueryWrapper<BpmProcessTaskApprove> queryWrapper = new QueryWrapper <BpmProcessTaskApprove>();
        if(StringUtils.isNotBlank(queryForm.getProcessInstanceId())){
            queryWrapper.eq(BpmProcessTaskApprove.PROCESS_INSTANCE_ID,queryForm.getProcessInstanceId());
        }
        if(StringUtils.isNotBlank(queryForm.getPreviousTaskId())){
            queryWrapper.eq(BpmProcessTaskApprove.PREVIOUS_TASK_ID,queryForm.getPreviousTaskId());
        }
        if(StringUtils.isNotBlank(queryForm.getTaskKey())){
            queryWrapper.eq(BpmProcessTaskApprove.TASK_KEY,queryForm.getTaskKey());
        }
        if(StringUtils.isNotBlank(queryForm.getAcountId())){
            queryWrapper.eq(BpmProcessTaskApprove.ACOUNT_ID,queryForm.getAcountId());
        }
        if(StringUtils.isNotBlank(queryForm.getAgentId())){
            queryWrapper.eq(BpmProcessTaskApprove.AGENT_ID,queryForm.getAgentId());
        }
        if(StringUtils.isNotBlank(queryForm.getEndFlag())){
            queryWrapper.eq(BpmProcessTaskApprove.END_FLAG,queryForm.getEndFlag());
        }
        queryWrapper.orderByAsc(BpmProcessTaskApprove.ORDER_NUM);
        return  list(queryWrapper);
    }
}
