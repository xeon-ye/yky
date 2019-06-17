package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.param.MaintReplyNoteQueryForm;
import com.deloitte.services.project.entity.MaintReplyNote;
import com.deloitte.services.project.mapper.MaintReplyNoteMapper;
import com.deloitte.services.project.service.IMaintReplyNoteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description :  MaintReplyNote服务实现类
 * @Modified :
 */
@Service
@Transactional
public class MaintReplyNoteServiceImpl extends ServiceImpl<MaintReplyNoteMapper, MaintReplyNote> implements IMaintReplyNoteService {


    @Autowired
    private MaintReplyNoteMapper maintReplyNoteMapper;

    @Override
    public IPage<MaintReplyNote> selectPage(MaintReplyNoteQueryForm queryForm ) {
        QueryWrapper<MaintReplyNote> queryWrapper = new QueryWrapper <MaintReplyNote>();
        //getQueryWrapper(queryWrapper,queryForm);
        return maintReplyNoteMapper.selectPage(new Page<MaintReplyNote>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<MaintReplyNote> selectList(MaintReplyNoteQueryForm queryForm) {
        QueryWrapper<MaintReplyNote> queryWrapper = new QueryWrapper <MaintReplyNote>();
        //getQueryWrapper(queryWrapper,queryForm);
        return maintReplyNoteMapper.selectList(queryWrapper);
    }

    @Override
    public List<MaintReplyNote> getAllList(String projectId) {
        QueryWrapper<MaintReplyNote> queryWrapper = new QueryWrapper <MaintReplyNote>();
        queryWrapper.eq(MaintReplyNote.PROJECT_ID,projectId);
        return maintReplyNoteMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<MaintReplyNote> getQueryWrapper(QueryWrapper<MaintReplyNote> queryWrapper,MaintReplyNoteQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getRelyId())){
            queryWrapper.eq(MaintReplyNote.RELY_ID,queryForm.getRelyId());
        }
        if(StringUtils.isNotBlank(queryForm.getMaintId())){
            queryWrapper.eq(MaintReplyNote.MAINT_ID,queryForm.getMaintId());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectId())){
            queryWrapper.eq(MaintReplyNote.PROJECT_ID,queryForm.getProjectId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplicationId())){
            queryWrapper.eq(MaintReplyNote.APPLICATION_ID,queryForm.getApplicationId());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyCode())){
            queryWrapper.eq(MaintReplyNote.REPLY_CODE,queryForm.getReplyCode());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyName())){
            queryWrapper.eq(MaintReplyNote.REPLY_NAME,queryForm.getReplyName());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyAdvice())){
            queryWrapper.eq(MaintReplyNote.REPLY_ADVICE,queryForm.getReplyAdvice());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyPartId())){
            queryWrapper.eq(MaintReplyNote.REPLY_PART_ID,queryForm.getReplyPartId());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyPartName())){
            queryWrapper.eq(MaintReplyNote.REPLY_PART_NAME,queryForm.getReplyPartName());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyPersonId())){
            queryWrapper.eq(MaintReplyNote.REPLY_PERSON_ID,queryForm.getReplyPersonId());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyPersonName())){
            queryWrapper.eq(MaintReplyNote.REPLY_PERSON_NAME,queryForm.getReplyPersonName());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyTime())){
            queryWrapper.eq(MaintReplyNote.REPLY_TIME,queryForm.getReplyTime());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyLastId())){
            queryWrapper.eq(MaintReplyNote.REPLY_LAST_ID,queryForm.getReplyLastId());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyLastName())){
            queryWrapper.eq(MaintReplyNote.REPLY_LAST_NAME,queryForm.getReplyLastName());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(MaintReplyNote.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(MaintReplyNote.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(MaintReplyNote.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(MaintReplyNote.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(MaintReplyNote.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(MaintReplyNote.EXT2,queryForm.getExt2());
        }
        return queryWrapper;
    }
     */
}

