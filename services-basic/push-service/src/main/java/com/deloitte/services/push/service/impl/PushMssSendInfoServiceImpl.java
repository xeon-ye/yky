package com.deloitte.services.push.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.push.param.PushMssSendInfoForm;
import com.deloitte.services.push.entity.PushMssSendInfo;
import com.deloitte.services.push.mapper.PushMssSendInfoMapper;
import com.deloitte.services.push.service.IPushMssSendInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-03
 * @Description :  OaMssSendInfo服务实现类
 * @Modified :
 */
@Service
@Transactional
public class PushMssSendInfoServiceImpl extends ServiceImpl<PushMssSendInfoMapper, PushMssSendInfo> implements IPushMssSendInfoService {

    @Autowired
    PushMssSendInfoMapper oaMssSendInfoMapper;

    @Override
    public IPage<PushMssSendInfo> selectPage(PushMssSendInfoForm queryForm ) {
        QueryWrapper<PushMssSendInfo> queryWrapper = new QueryWrapper <PushMssSendInfo>();
        getQueryWrapper(queryWrapper,queryForm);
        return oaMssSendInfoMapper.selectPage(new Page<PushMssSendInfo>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<PushMssSendInfo> selectList(PushMssSendInfoForm queryForm) {
        QueryWrapper<PushMssSendInfo> queryWrapper = new QueryWrapper <PushMssSendInfo>();
        getQueryWrapper(queryWrapper,queryForm);
        return oaMssSendInfoMapper.selectList(queryWrapper);
    }

    @Override
    public Long getMssMonthTotal(String date){
        return oaMssSendInfoMapper.getMssMonthTotal(date);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     * */
    public QueryWrapper<PushMssSendInfo> getQueryWrapper(QueryWrapper<PushMssSendInfo> queryWrapper, PushMssSendInfoForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getMssTitle())){
            queryWrapper.like(PushMssSendInfo.MSS_TITLE,queryForm.getMssTitle());
        }
        if(StringUtils.isNotBlank(queryForm.getSendUserId())){
            queryWrapper.eq(PushMssSendInfo.SEND_USER_ID,queryForm.getSendUserId());
        }
        if(StringUtils.isNotBlank(queryForm.getSendUserName())){
            queryWrapper.eq(PushMssSendInfo.SEND_USER_NAME,queryForm.getSendUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getRequestId())){
            queryWrapper.eq(PushMssSendInfo.REQUEST_ID,queryForm.getRequestId());
        }
        if(StringUtils.isNotBlank(queryForm.getReceiveId())){
            queryWrapper.eq(PushMssSendInfo.RECEIVE_ID,queryForm.getReceiveId());
        }
        if(StringUtils.isNotBlank(queryForm.getMssType())){
            queryWrapper.eq(PushMssSendInfo.MSS_TYPE,queryForm.getMssType());
        }
        if(StringUtils.isNotBlank(queryForm.getReceiveName())){
            queryWrapper.eq(PushMssSendInfo.RECEIVE_NAME,queryForm.getReceiveName());
        }
        if(StringUtils.isNotBlank(queryForm.getReceiveTelephone())){
            queryWrapper.like(PushMssSendInfo.RECEIVE_TELEPHONE,queryForm.getReceiveTelephone());
        }
        if(StringUtils.isNotBlank(queryForm.getIsSend())){
            queryWrapper.like(PushMssSendInfo.IS_SEND,queryForm.getIsSend());
        }

        if(StringUtils.isNotBlank(queryForm.getResourceSystem())){
            queryWrapper.like(PushMssSendInfo.RESOURCE_SYSTEM,queryForm.getResourceSystem());
        }

        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(PushMssSendInfo.CREATE_BY,queryForm.getCreateBy());
        }
        if(queryForm.getCreateTime()!=null){
            queryWrapper.eq(PushMssSendInfo.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(PushMssSendInfo.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(queryForm.getUpdateTime()!=null){
            queryWrapper.eq(PushMssSendInfo.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(PushMssSendInfo.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(PushMssSendInfo.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(PushMssSendInfo.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(PushMssSendInfo.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(PushMssSendInfo.EXT5,queryForm.getExt5());
        }
        return queryWrapper;
    }

}

