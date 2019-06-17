package com.deloitte.services.fssc.budget.mq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.mq.param.SyncMsgQueryForm;
import com.deloitte.services.fssc.budget.mq.entity.SyncMsg;
import com.deloitte.services.fssc.budget.mq.mapper.SyncMsgMapper;
import com.deloitte.services.fssc.budget.mq.service.ISyncMsgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-05-29
 * @Description :  SyncMsg服务实现类
 * @Modified :
 */
@Service
@Transactional
public class SyncMsgServiceImpl extends ServiceImpl<SyncMsgMapper, SyncMsg> implements ISyncMsgService {


    @Autowired
    private SyncMsgMapper syncMsgMapper;

    @Override
    public IPage<SyncMsg> selectPage(SyncMsgQueryForm queryForm) {
        QueryWrapper<SyncMsg> queryWrapper = new QueryWrapper<SyncMsg>();
        //getQueryWrapper(queryWrapper,queryForm);
        return syncMsgMapper.selectPage(new Page<SyncMsg>(queryForm.getCurrentPage(), queryForm.getPageSize()), queryWrapper);
    }

    @Override
    public List<SyncMsg> selectList(SyncMsgQueryForm queryForm) {
        QueryWrapper<SyncMsg> queryWrapper = new QueryWrapper<SyncMsg>();
        //getQueryWrapper(queryWrapper,queryForm);
        return syncMsgMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return public QueryWrapper<SyncMsg> getQueryWrapper(QueryWrapper<SyncMsg> queryWrapper,SyncMsgQueryForm queryForm){
    //条件拼接
    if(StringUtils.isNotBlank(queryForm.getRelationId())){
    queryWrapper.eq(SyncMsg.RELATION_ID,queryForm.getRelationId());
    }
    if(StringUtils.isNotBlank(queryForm.getMsgType())){
    queryWrapper.eq(SyncMsg.MSG_TYPE,queryForm.getMsgType());
    }
    if(StringUtils.isNotBlank(queryForm.getMsgBody())){
    queryWrapper.eq(SyncMsg.MSG_BODY,queryForm.getMsgBody());
    }
    if(StringUtils.isNotBlank(queryForm.getMsgStatus())){
    queryWrapper.eq(SyncMsg.MSG_STATUS,queryForm.getMsgStatus());
    }
    if(StringUtils.isNotBlank(queryForm.getMsgErrorInfo())){
    queryWrapper.eq(SyncMsg.MSG_ERROR_INFO,queryForm.getMsgErrorInfo());
    }
    if(StringUtils.isNotBlank(queryForm.getCreateTime())){
    queryWrapper.eq(SyncMsg.CREATE_TIME,queryForm.getCreateTime());
    }
    if(StringUtils.isNotBlank(queryForm.getCreateBy())){
    queryWrapper.eq(SyncMsg.CREATE_BY,queryForm.getCreateBy());
    }
    if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
    queryWrapper.eq(SyncMsg.UPDATE_TIME,queryForm.getUpdateTime());
    }
    if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
    queryWrapper.eq(SyncMsg.UPDATE_BY,queryForm.getUpdateBy());
    }
    return queryWrapper;
    }
     */
}

