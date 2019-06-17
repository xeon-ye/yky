package com.deloitte.services.oaservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.oaservice.entity.OaMssSendInfo;
import com.deloitte.services.oaservice.entity.OaMssSendInfoForm;
import com.deloitte.services.oaservice.mapper.OaMssSendInfoMapper;
import com.deloitte.services.oaservice.service.IOaMssSendInfoService;
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
public class OaMssSendInfoServiceImpl extends ServiceImpl<OaMssSendInfoMapper, OaMssSendInfo> implements IOaMssSendInfoService {

    @Autowired
    OaMssSendInfoMapper oaMssSendInfoMapper;

    @Override
    public IPage<OaMssSendInfo> selectPage(OaMssSendInfoForm queryForm ) {
        QueryWrapper<OaMssSendInfo> queryWrapper = new QueryWrapper <OaMssSendInfo>();
        getQueryWrapper(queryWrapper,queryForm);
        return oaMssSendInfoMapper.selectPage(new Page<OaMssSendInfo>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<OaMssSendInfo> selectList(OaMssSendInfoForm queryForm) {
        QueryWrapper<OaMssSendInfo> queryWrapper = new QueryWrapper <OaMssSendInfo>();
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
    public QueryWrapper<OaMssSendInfo> getQueryWrapper(QueryWrapper<OaMssSendInfo> queryWrapper,OaMssSendInfoForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getMssTitle())){
            queryWrapper.like(OaMssSendInfo.MSS_TITLE,queryForm.getMssTitle());
        }
        if(StringUtils.isNotBlank(queryForm.getSendUserId())){
            queryWrapper.eq(OaMssSendInfo.SEND_USER_ID,queryForm.getSendUserId());
        }
        if(StringUtils.isNotBlank(queryForm.getSendUserName())){
            queryWrapper.eq(OaMssSendInfo.SEND_USER_NAME,queryForm.getSendUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getRequestId())){
            queryWrapper.eq(OaMssSendInfo.REQUEST_ID,queryForm.getRequestId());
        }
        if(StringUtils.isNotBlank(queryForm.getReceiveId())){
            queryWrapper.eq(OaMssSendInfo.RECEIVE_ID,queryForm.getReceiveId());
        }
        if(StringUtils.isNotBlank(queryForm.getMssType())){
            queryWrapper.eq(OaMssSendInfo.MSS_TYPE,queryForm.getMssType());
        }
        if(StringUtils.isNotBlank(queryForm.getReceiveName())){
            queryWrapper.eq(OaMssSendInfo.RECEIVE_NAME,queryForm.getReceiveName());
        }
        if(StringUtils.isNotBlank(queryForm.getReceiveTelephone())){
            queryWrapper.like(OaMssSendInfo.RECEIVE_TELEPHONE,queryForm.getReceiveTelephone());
        }
        if(StringUtils.isNotBlank(queryForm.getIsSend())){
            queryWrapper.like(OaMssSendInfo.IS_SEND,queryForm.getIsSend());
        }

        if(StringUtils.isNotBlank(queryForm.getResourceSystem())){
            queryWrapper.like(OaMssSendInfo.RESOURCE_SYSTEM,queryForm.getResourceSystem());
        }

        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(OaMssSendInfo.CREATE_BY,queryForm.getCreateBy());
        }
        if(queryForm.getCreateTime()!=null){
            queryWrapper.eq(OaMssSendInfo.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(OaMssSendInfo.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(queryForm.getUpdateTime()!=null){
            queryWrapper.eq(OaMssSendInfo.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(OaMssSendInfo.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(OaMssSendInfo.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(OaMssSendInfo.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(OaMssSendInfo.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(OaMssSendInfo.EXT5,queryForm.getExt5());
        }
        return queryWrapper;
    }

}

