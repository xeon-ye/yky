package com.deloitte.services.fssc.business.bpm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.bpm.param.EventDefQueryForm;
import com.deloitte.services.fssc.business.bpm.entity.EventDef;
import com.deloitte.services.fssc.business.bpm.mapper.EventDefMapper;
import com.deloitte.services.fssc.business.bpm.service.IEventDefService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : qiliao
 * @Date : Create in 2019-03-18
 * @Description :  EventDef服务实现类
 * @Modified :
 */
@Service
@Transactional
public class EventDefServiceImpl extends ServiceImpl<EventDefMapper, EventDef> implements IEventDefService {


    @Autowired
    private EventDefMapper eventDefMapper;

    @Override
    public IPage<EventDef> selectPage(EventDefQueryForm queryForm ) {
        QueryWrapper<EventDef> queryWrapper = new QueryWrapper <EventDef>();
        //getQueryWrapper(queryWrapper,queryForm);
        return eventDefMapper.selectPage(new Page<EventDef>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<EventDef> selectList(EventDefQueryForm queryForm) {
        QueryWrapper<EventDef> queryWrapper = new QueryWrapper <EventDef>();
        //getQueryWrapper(queryWrapper,queryForm);
        return eventDefMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<EventDef> getQueryWrapper(QueryWrapper<EventDef> queryWrapper,EventDefQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(EventDef.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateUserName())){
            queryWrapper.eq(EventDef.CREATE_USER_NAME,queryForm.getCreateUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(EventDef.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(EventDef.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(EventDef.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getVersion())){
            queryWrapper.eq(EventDef.VERSION,queryForm.getVersion());
        }
        if(StringUtils.isNotBlank(queryForm.getEx1())){
            queryWrapper.eq(EventDef.EX1,queryForm.getEx1());
        }
        if(StringUtils.isNotBlank(queryForm.getEx2())){
            queryWrapper.eq(EventDef.EX2,queryForm.getEx2());
        }
        if(StringUtils.isNotBlank(queryForm.getEx3())){
            queryWrapper.eq(EventDef.EX3,queryForm.getEx3());
        }
        if(StringUtils.isNotBlank(queryForm.getEx4())){
            queryWrapper.eq(EventDef.EX4,queryForm.getEx4());
        }
        if(StringUtils.isNotBlank(queryForm.getEx5())){
            queryWrapper.eq(EventDef.EX5,queryForm.getEx5());
        }
        if(StringUtils.isNotBlank(queryForm.getEx6())){
            queryWrapper.eq(EventDef.EX6,queryForm.getEx6());
        }
        if(StringUtils.isNotBlank(queryForm.getEx7())){
            queryWrapper.eq(EventDef.EX7,queryForm.getEx7());
        }
        if(StringUtils.isNotBlank(queryForm.getEx8())){
            queryWrapper.eq(EventDef.EX8,queryForm.getEx8());
        }
        if(StringUtils.isNotBlank(queryForm.getEx9())){
            queryWrapper.eq(EventDef.EX9,queryForm.getEx9());
        }
        if(StringUtils.isNotBlank(queryForm.getEx10())){
            queryWrapper.eq(EventDef.EX10,queryForm.getEx10());
        }
        if(StringUtils.isNotBlank(queryForm.getEx11())){
            queryWrapper.eq(EventDef.EX11,queryForm.getEx11());
        }
        if(StringUtils.isNotBlank(queryForm.getEx12())){
            queryWrapper.eq(EventDef.EX12,queryForm.getEx12());
        }
        if(StringUtils.isNotBlank(queryForm.getEx13())){
            queryWrapper.eq(EventDef.EX13,queryForm.getEx13());
        }
        if(StringUtils.isNotBlank(queryForm.getEx14())){
            queryWrapper.eq(EventDef.EX14,queryForm.getEx14());
        }
        if(StringUtils.isNotBlank(queryForm.getEx15())){
            queryWrapper.eq(EventDef.EX15,queryForm.getEx15());
        }
        if(StringUtils.isNotBlank(queryForm.getIsValid())){
            queryWrapper.eq(EventDef.IS_VALID,queryForm.getIsValid());
        }
        if(StringUtils.isNotBlank(queryForm.getDoClassName())){
            queryWrapper.eq(EventDef.DO_CLASS_NAME,queryForm.getDoClassName());
        }
        if(StringUtils.isNotBlank(queryForm.getDoMethod())){
            queryWrapper.eq(EventDef.DO_METHOD,queryForm.getDoMethod());
        }
        if(StringUtils.isNotBlank(queryForm.getDoParams())){
            queryWrapper.eq(EventDef.DO_PARAMS,queryForm.getDoParams());
        }
        if(StringUtils.isNotBlank(queryForm.getEventDescription())){
            queryWrapper.eq(EventDef.EVENT_DESCRIPTION,queryForm.getEventDescription());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentType())){
            queryWrapper.eq(EventDef.DOCUMENT_TYPE,queryForm.getDocumentType());
        }
        if(StringUtils.isNotBlank(queryForm.getDoOrder())){
            queryWrapper.eq(EventDef.DO_ORDER,queryForm.getDoOrder());
        }
        if(StringUtils.isNotBlank(queryForm.getBeforeOrAfter())){
            queryWrapper.eq(EventDef.BEFORE_OR_AFTER,queryForm.getBeforeOrAfter());
        }
        return queryWrapper;
    }
     */
}

