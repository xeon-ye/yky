package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.param.ChangeNoteQueryForm;
import com.deloitte.services.project.entity.ChangeNote;
import com.deloitte.services.project.mapper.ChangeNoteMapper;
import com.deloitte.services.project.service.IChangeNoteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description :  ChangeNote服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ChangeNoteServiceImpl extends ServiceImpl<ChangeNoteMapper, ChangeNote> implements IChangeNoteService {


    @Autowired
    private ChangeNoteMapper changeNoteMapper;

    @Override
    public IPage<ChangeNote> selectPage(ChangeNoteQueryForm queryForm ) {
        QueryWrapper<ChangeNote> queryWrapper = new QueryWrapper <ChangeNote>();
        //getQueryWrapper(queryWrapper,queryForm);
        return changeNoteMapper.selectPage(new Page<ChangeNote>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<ChangeNote> selectList(ChangeNoteQueryForm queryForm) {
        QueryWrapper<ChangeNote> queryWrapper = new QueryWrapper <ChangeNote>();
        //getQueryWrapper(queryWrapper,queryForm);
        return changeNoteMapper.selectList(queryWrapper);
    }

    @Override
    public List<ChangeNote> getOneChangeNote(String projectId) {
        QueryWrapper<ChangeNote> queryWrapper = new QueryWrapper <ChangeNote>();
        queryWrapper.eq(ChangeNote.PROJECT_ID,projectId);
        return changeNoteMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<ChangeNote> getQueryWrapper(QueryWrapper<ChangeNote> queryWrapper,ChangeNoteQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getChangeId())){
            queryWrapper.eq(ChangeNote.CHANGE_ID,queryForm.getChangeId());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectId())){
            queryWrapper.eq(ChangeNote.PROJECT_ID,queryForm.getProjectId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplicationId())){
            queryWrapper.eq(ChangeNote.APPLICATION_ID,queryForm.getApplicationId());
        }
        if(StringUtils.isNotBlank(queryForm.getExecutionId())){
            queryWrapper.eq(ChangeNote.EXECUTION_ID,queryForm.getExecutionId());
        }
        if(StringUtils.isNotBlank(queryForm.getChangeAdv())){
            queryWrapper.eq(ChangeNote.CHANGE_ADV,queryForm.getChangeAdv());
        }
        if(StringUtils.isNotBlank(queryForm.getChangeStatusCode())){
            queryWrapper.eq(ChangeNote.CHANGE_STATUS_CODE,queryForm.getChangeStatusCode());
        }
        if(StringUtils.isNotBlank(queryForm.getChangeStatusName())){
            queryWrapper.eq(ChangeNote.CHANGE_STATUS_NAME,queryForm.getChangeStatusName());
        }
        if(StringUtils.isNotBlank(queryForm.getHisTime())){
            queryWrapper.eq(ChangeNote.HIS_TIME,queryForm.getHisTime());
        }
        if(StringUtils.isNotBlank(queryForm.getHisBy())){
            queryWrapper.eq(ChangeNote.HIS_BY,queryForm.getHisBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(ChangeNote.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(ChangeNote.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(ChangeNote.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(ChangeNote.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(ChangeNote.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(ChangeNote.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(ChangeNote.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(ChangeNote.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(ChangeNote.ORG_PATH,queryForm.getOrgPath());
        }
        return queryWrapper;
    }
     */
}

