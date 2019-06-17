package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.param.MaintReplyQueryForm;
import com.deloitte.services.project.entity.MaintReply;
import com.deloitte.services.project.mapper.MaintReplyMapper;
import com.deloitte.services.project.service.IMaintReplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
import java.util.Map;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description :  MaintReply服务实现类
 * @Modified :
 */
@Service
@Transactional
public class MaintReplyServiceImpl extends ServiceImpl<MaintReplyMapper, MaintReply> implements IMaintReplyService {


    @Autowired
    private MaintReplyMapper maintReplyMapper;

    @Override
    public IPage<MaintReply> selectPage(MaintReplyQueryForm queryForm ) {
        QueryWrapper<MaintReply> queryWrapper = new QueryWrapper <MaintReply>();
        //getQueryWrapper(queryWrapper,queryForm);
        return maintReplyMapper.selectPage(new Page<MaintReply>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<MaintReply> selectList(MaintReplyQueryForm queryForm) {
        QueryWrapper<MaintReply> queryWrapper = new QueryWrapper <MaintReply>();
        //getQueryWrapper(queryWrapper,queryForm);
        return maintReplyMapper.selectList(queryWrapper);
    }

    @Override
    public List<MaintReply> getAllList(String projectId) {
        QueryWrapper<MaintReply> queryWrapper = new QueryWrapper <MaintReply>();
        queryWrapper.eq(MaintReply.PROJECT_ID,projectId);
        return maintReplyMapper.selectList(queryWrapper);
    }

    @Override
    public MaintReply getNewMainReply(Map map) {
        return maintReplyMapper.getNewMainReply(map);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<MaintReply> getQueryWrapper(QueryWrapper<MaintReply> queryWrapper,MaintReplyQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getRelyId())){
            queryWrapper.eq(MaintReply.RELY_ID,queryForm.getRelyId());
        }
        if(StringUtils.isNotBlank(queryForm.getMaintId())){
            queryWrapper.eq(MaintReply.MAINT_ID,queryForm.getMaintId());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectId())){
            queryWrapper.eq(MaintReply.PROJECT_ID,queryForm.getProjectId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplicationId())){
            queryWrapper.eq(MaintReply.APPLICATION_ID,queryForm.getApplicationId());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyCode())){
            queryWrapper.eq(MaintReply.REPLY_CODE,queryForm.getReplyCode());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyName())){
            queryWrapper.eq(MaintReply.REPLY_NAME,queryForm.getReplyName());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyAdvice())){
            queryWrapper.eq(MaintReply.REPLY_ADVICE,queryForm.getReplyAdvice());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyPartId())){
            queryWrapper.eq(MaintReply.REPLY_PART_ID,queryForm.getReplyPartId());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyPartName())){
            queryWrapper.eq(MaintReply.REPLY_PART_NAME,queryForm.getReplyPartName());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyPersonId())){
            queryWrapper.eq(MaintReply.REPLY_PERSON_ID,queryForm.getReplyPersonId());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyPersonName())){
            queryWrapper.eq(MaintReply.REPLY_PERSON_NAME,queryForm.getReplyPersonName());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyTime())){
            queryWrapper.eq(MaintReply.REPLY_TIME,queryForm.getReplyTime());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyLastId())){
            queryWrapper.eq(MaintReply.REPLY_LAST_ID,queryForm.getReplyLastId());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyLastName())){
            queryWrapper.eq(MaintReply.REPLY_LAST_NAME,queryForm.getReplyLastName());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(MaintReply.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(MaintReply.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(MaintReply.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(MaintReply.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(MaintReply.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(MaintReply.EXT2,queryForm.getExt2());
        }
        return queryWrapper;
    }
     */
}

