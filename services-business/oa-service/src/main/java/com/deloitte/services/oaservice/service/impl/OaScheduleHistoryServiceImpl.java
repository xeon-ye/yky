package com.deloitte.services.oaservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.oaservice.param.OaScheduleHistoryQueryForm;
import com.deloitte.services.oaservice.entity.OaScheduleHistory;
import com.deloitte.services.oaservice.mapper.OaScheduleHistoryMapper;
import com.deloitte.services.oaservice.service.IOaScheduleHistoryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @Author : fuqiao
 * @Date : Create in 2019-03-26
 * @Description :  OaScheduleHistory服务实现类
 * @Modified :
 */
@Service
@Transactional
public class OaScheduleHistoryServiceImpl extends ServiceImpl<OaScheduleHistoryMapper, OaScheduleHistory> implements IOaScheduleHistoryService {


    @Autowired
    private OaScheduleHistoryMapper oaScheduleHistoryMapper;

    @Override
    public IPage<OaScheduleHistory> selectPage(OaScheduleHistoryQueryForm queryForm ) {
        QueryWrapper<OaScheduleHistory> queryWrapper = new QueryWrapper <OaScheduleHistory>();
        getQueryWrapper(queryWrapper,queryForm);
        return oaScheduleHistoryMapper.selectPage(new Page<OaScheduleHistory>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<OaScheduleHistory> selectList(OaScheduleHistoryQueryForm queryForm) {
        QueryWrapper<OaScheduleHistory> queryWrapper = new QueryWrapper <OaScheduleHistory>();
        getQueryWrapper(queryWrapper,queryForm);
        return oaScheduleHistoryMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     * */
    public QueryWrapper<OaScheduleHistory> getQueryWrapper(QueryWrapper<OaScheduleHistory> queryWrapper,OaScheduleHistoryQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getBusinessId())){
            queryWrapper.eq(OaScheduleHistory.BUSINESS_ID,queryForm.getBusinessId());
        }
        if(StringUtils.isNotBlank(queryForm.getWorkId())){
            queryWrapper.eq(OaScheduleHistory.WORK_ID,queryForm.getWorkId());
        }
        if(StringUtils.isNotBlank(queryForm.getWorkName())){
            queryWrapper.eq(OaScheduleHistory.WORK_NAME,queryForm.getWorkName());
        }
        if(StringUtils.isNotBlank(queryForm.getWorkDesc())){
            queryWrapper.eq(OaScheduleHistory.WORK_DESC,queryForm.getWorkDesc());
        }
        if(StringUtils.isNotBlank(queryForm.getWorkStatus())){
            queryWrapper.eq(OaScheduleHistory.WORK_STATUS,queryForm.getWorkStatus());
        }
        if(StringUtils.isNotBlank(queryForm.getUserId())){
            queryWrapper.eq(OaScheduleHistory.USER_ID,queryForm.getUserId());
        }
        if(StringUtils.isNotBlank(queryForm.getUserName())){
            queryWrapper.eq(OaScheduleHistory.USER_NAME,queryForm.getUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getDeptId())){
            queryWrapper.eq(OaScheduleHistory.DEPT_ID,queryForm.getDeptId());
        }
        if(StringUtils.isNotBlank(queryForm.getDeptName())){
            queryWrapper.eq(OaScheduleHistory.DEPT_NAME,queryForm.getDeptName());
        }
        if(!org.springframework.util.StringUtils.isEmpty((queryForm.getStartTime()))){
            queryWrapper.eq(OaScheduleHistory.START_TIME,queryForm.getStartTime());
        }
        if(!org.springframework.util.StringUtils.isEmpty((queryForm.getEndTime()))){
            queryWrapper.eq(OaScheduleHistory.END_TIME,queryForm.getEndTime());
        }
        if(!org.springframework.util.StringUtils.isEmpty((queryForm.getCreateBy()))){
            queryWrapper.eq(OaScheduleHistory.CREATE_BY,queryForm.getCreateBy());
        }
        if(!org.springframework.util.StringUtils.isEmpty((queryForm.getCreateTime()))){
            queryWrapper.eq(OaScheduleHistory.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(OaScheduleHistory.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(!org.springframework.util.StringUtils.isEmpty((queryForm.getUpdateTime()))){
            queryWrapper.eq(OaScheduleHistory.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getPersonType())){
            queryWrapper.eq(OaScheduleHistory.PERSON_TYPE,queryForm.getPersonType());
        }
        if(StringUtils.isNotBlank(queryForm.getNoticeType())){
            queryWrapper.eq(OaScheduleHistory.NOTICE_TYPE,queryForm.getNoticeType());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(OaScheduleHistory.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(OaScheduleHistory.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(OaScheduleHistory.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(OaScheduleHistory.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(OaScheduleHistory.EXT5,queryForm.getExt5());
        }
        return queryWrapper;
    }

}

