package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.param.PersonBakQueryForm;
import com.deloitte.services.project.entity.PersonBak;
import com.deloitte.services.project.entity.ProjectsAndApplication;
import com.deloitte.services.project.mapper.PersonBakMapper;
import com.deloitte.services.project.service.IPersonBakService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
import java.util.Map;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description :  PersonBak服务实现类
 * @Modified :
 */
@Service
@Transactional
public class PersonBakServiceImpl extends ServiceImpl<PersonBakMapper, PersonBak> implements IPersonBakService {


    @Autowired
    private PersonBakMapper personBakMapper;

    @Override
    public IPage<PersonBak> selectPage(PersonBakQueryForm queryForm ) {
        QueryWrapper<PersonBak> queryWrapper = new QueryWrapper <PersonBak>();
        //getQueryWrapper(queryWrapper,queryForm);
        return personBakMapper.selectPage(new Page<PersonBak>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<PersonBak> selectList(PersonBakQueryForm queryForm) {
        QueryWrapper<PersonBak> queryWrapper = new QueryWrapper <PersonBak>();
        //getQueryWrapper(queryWrapper,queryForm);
        return personBakMapper.selectList(queryWrapper);
    }

    @Override
    public List<PersonBak> getAllList(String applicationId) {
        QueryWrapper<PersonBak> queryWrapper = new QueryWrapper <PersonBak>();
        queryWrapper.eq(PersonBak.APPLICATION_ID,applicationId);
        return personBakMapper.selectList(queryWrapper);
    }

    @Override
    public void deleteAllList(String applicationId) {
        QueryWrapper<PersonBak> queryWrapper = new QueryWrapper <PersonBak>();
        queryWrapper.eq(PersonBak.APPLICATION_ID,applicationId);
        personBakMapper.delete(queryWrapper);
    }

    @Override
    public List<PersonBak> getAllByRepId(String replyId) {
        QueryWrapper<PersonBak> queryWrapper = new QueryWrapper <PersonBak>();
        queryWrapper.eq(PersonBak.REPLY_ID,replyId);
        return  personBakMapper.selectList(queryWrapper);
    }

    @Override
    public void deleteAllByRepId(String replyId) {
        QueryWrapper<PersonBak> queryWrapper = new QueryWrapper <PersonBak>();
        queryWrapper.eq(PersonBak.REPLY_ID,replyId);
        personBakMapper.delete(queryWrapper);
    }

    @Override
    public List<PersonBak> getAllByProId(String projectId) {
        QueryWrapper<PersonBak> queryWrapper = new QueryWrapper <PersonBak>();
        queryWrapper.eq(PersonBak.PROJECT_ID,projectId);
        return  personBakMapper.selectList(queryWrapper);
    }

    @Override
    public void deleteAllByProId(String projectId) {
        QueryWrapper<PersonBak> queryWrapper = new QueryWrapper <PersonBak>();
        queryWrapper.eq(PersonBak.PROJECT_ID,projectId);
        personBakMapper.delete(queryWrapper);
    }

    @Override
    public List<PersonBak> selectListPage(Page<PersonBak> page, Map<String, Object> map) {
        return personBakMapper.selectListPage(page,map);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<PersonBak> getQueryWrapper(QueryWrapper<PersonBak> queryWrapper,PersonBakQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getManId())){
            queryWrapper.eq(PersonBak.MAN_ID,queryForm.getManId());
        }
        if(StringUtils.isNotBlank(queryForm.getReviewId())){
            queryWrapper.eq(PersonBak.REVIEW_ID,queryForm.getReviewId());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyId())){
            queryWrapper.eq(PersonBak.REPLY_ID,queryForm.getReplyId());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectId())){
            queryWrapper.eq(PersonBak.PROJECT_ID,queryForm.getProjectId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplicationId())){
            queryWrapper.eq(PersonBak.APPLICATION_ID,queryForm.getApplicationId());
        }
        if(StringUtils.isNotBlank(queryForm.getPerId())){
            queryWrapper.eq(PersonBak.PER_ID,queryForm.getPerId());
        }
        if(StringUtils.isNotBlank(queryForm.getPerName())){
            queryWrapper.eq(PersonBak.PER_NAME,queryForm.getPerName());
        }
        if(StringUtils.isNotBlank(queryForm.getPerPositionCode())){
            queryWrapper.eq(PersonBak.PER_POSITION_CODE,queryForm.getPerPositionCode());
        }
        if(StringUtils.isNotBlank(queryForm.getPerPositionName())){
            queryWrapper.eq(PersonBak.PER_POSITION_NAME,queryForm.getPerPositionName());
        }
        if(StringUtils.isNotBlank(queryForm.getPhone())){
            queryWrapper.eq(PersonBak.PHONE,queryForm.getPhone());
        }
        if(StringUtils.isNotBlank(queryForm.getEmail())){
            queryWrapper.eq(PersonBak.EMAIL,queryForm.getEmail());
        }
        if(StringUtils.isNotBlank(queryForm.getBeginTime())){
            queryWrapper.eq(PersonBak.BEGIN_TIME,queryForm.getBeginTime());
        }
        if(StringUtils.isNotBlank(queryForm.getEndTime())){
            queryWrapper.eq(PersonBak.END_TIME,queryForm.getEndTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(PersonBak.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(PersonBak.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(PersonBak.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(PersonBak.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(PersonBak.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(PersonBak.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(PersonBak.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(PersonBak.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(PersonBak.EXT5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(PersonBak.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(PersonBak.ORG_PATH,queryForm.getOrgPath());
        }
        return queryWrapper;
    }
     */
}

