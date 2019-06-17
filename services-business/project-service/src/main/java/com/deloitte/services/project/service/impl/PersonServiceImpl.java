package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.param.PersonQueryForm;
import com.deloitte.services.project.entity.Person;
import com.deloitte.services.project.mapper.PersonMapper;
import com.deloitte.services.project.service.IPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-15
 * @Description :  Person服务实现类
 * @Modified :
 */
@Service
@Transactional
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements IPersonService {


    @Autowired
    private PersonMapper personMapper;

    @Override
    public IPage<Person> selectPage(PersonQueryForm queryForm ) {
        QueryWrapper<Person> queryWrapper = new QueryWrapper <Person>();
        //getQueryWrapper(queryWrapper,queryForm);
        return personMapper.selectPage(new Page<Person>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<Person> selectList(PersonQueryForm queryForm) {
        QueryWrapper<Person> queryWrapper = new QueryWrapper <Person>();
        //getQueryWrapper(queryWrapper,queryForm);
        return personMapper.selectList(queryWrapper);
    }
    @Override
    public List<Person> getReplyList(String replyId) {
        QueryWrapper<Person> queryWrapper = new QueryWrapper <Person>();
        queryWrapper.eq(Person.REPLY_ID, replyId);
        return personMapper.selectList(queryWrapper);
    }

    @Override
    public void deleteReplyList(String replyId) {
        QueryWrapper<Person> queryWrapper = new QueryWrapper <Person>();
        queryWrapper.eq(Person.REPLY_ID, replyId);
        personMapper.delete(queryWrapper);
    }

    @Override
    public List<Person> getMainList(String projectId) {
        QueryWrapper<Person> queryWrapper = new QueryWrapper <Person>();
        queryWrapper.eq(Person.PROJECT_ID,projectId);
        return personMapper.selectList(queryWrapper);
    }

    @Override
    public void deleteMainList(String projectId) {
        QueryWrapper<Person> queryWrapper = new QueryWrapper <Person>();
        queryWrapper.eq(Person.PROJECT_ID,projectId);
        personMapper.delete(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<Person> getQueryWrapper(QueryWrapper<Person> queryWrapper,PersonQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getManId())){
            queryWrapper.eq(Person.MAN_ID,queryForm.getManId());
        }
        if(StringUtils.isNotBlank(queryForm.getReviewId())){
            queryWrapper.eq(Person.REVIEW_ID,queryForm.getReviewId());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyId())){
            queryWrapper.eq(Person.REPLY_ID,queryForm.getReplyId());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectId())){
            queryWrapper.eq(Person.PROJECT_ID,queryForm.getProjectId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplicationId())){
            queryWrapper.eq(Person.APPLICATION_ID,queryForm.getApplicationId());
        }
        if(StringUtils.isNotBlank(queryForm.getPerId())){
            queryWrapper.eq(Person.PER_ID,queryForm.getPerId());
        }
        if(StringUtils.isNotBlank(queryForm.getPerName())){
            queryWrapper.eq(Person.PER_NAME,queryForm.getPerName());
        }
        if(StringUtils.isNotBlank(queryForm.getPerPositionCode())){
            queryWrapper.eq(Person.PER_POSITION_CODE,queryForm.getPerPositionCode());
        }
        if(StringUtils.isNotBlank(queryForm.getPerPositionName())){
            queryWrapper.eq(Person.PER_POSITION_NAME,queryForm.getPerPositionName());
        }
        if(StringUtils.isNotBlank(queryForm.getPhone())){
            queryWrapper.eq(Person.PHONE,queryForm.getPhone());
        }
        if(StringUtils.isNotBlank(queryForm.getEmail())){
            queryWrapper.eq(Person.EMAIL,queryForm.getEmail());
        }
        if(StringUtils.isNotBlank(queryForm.getBeginTime())){
            queryWrapper.eq(Person.BEGIN_TIME,queryForm.getBeginTime());
        }
        if(StringUtils.isNotBlank(queryForm.getEndTime())){
            queryWrapper.eq(Person.END_TIME,queryForm.getEndTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(Person.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(Person.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(Person.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(Person.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(Person.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(Person.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(Person.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(Person.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(Person.EXT5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(Person.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(Person.ORG_PATH,queryForm.getOrgPath());
        }
        return queryWrapper;
    }
     */
}

