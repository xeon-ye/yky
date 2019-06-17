package com.deloitte.services.srpmp.project.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.srpmp.project.base.param.SrpmsProjectPersonQueryForm;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectPerson;
import com.deloitte.services.srpmp.project.base.mapper.SrpmsProjectPersonMapper;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectPersonService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : wangyanyun
 * @Date : Create in 2019-02-16
 * @Description :  SrpmsProjectPerson服务实现类
 * @Modified :
 */
@Service
@Transactional
public class SrpmsProjectPersonServiceImpl extends ServiceImpl<SrpmsProjectPersonMapper, SrpmsProjectPerson> implements ISrpmsProjectPersonService {


    @Autowired
    private SrpmsProjectPersonMapper srpmsProjectPersonMapper;

    @Override
    public IPage<SrpmsProjectPerson> selectPage(SrpmsProjectPersonQueryForm queryForm ) {
        QueryWrapper<SrpmsProjectPerson> queryWrapper = new QueryWrapper <SrpmsProjectPerson>();
        //getQueryWrapper(queryWrapper,queryForm);
        return srpmsProjectPersonMapper.selectPage(new Page<SrpmsProjectPerson>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<SrpmsProjectPerson> selectList(SrpmsProjectPersonQueryForm queryForm) {
        QueryWrapper<SrpmsProjectPerson> queryWrapper = new QueryWrapper <SrpmsProjectPerson>();
        //getQueryWrapper(queryWrapper,queryForm);
        return srpmsProjectPersonMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<SrpmsProjectPerson> getQueryWrapper(QueryWrapper<SrpmsProjectPerson> queryWrapper,BaseQueryForm<SrpmsProjectPersonQueryParam> queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(srpmsProjectPerson.getPersonName())){
            queryWrapper.like(SrpmsProjectPerson.PERSON_NAME,srpmsProjectPerson.getPersonName());
        }
        if(StringUtils.isNotBlank(srpmsProjectPerson.getGender())){
            queryWrapper.like(SrpmsProjectPerson.GENDER,srpmsProjectPerson.getGender());
        }
        if(StringUtils.isNotBlank(srpmsProjectPerson.getBirthDate())){
            queryWrapper.like(SrpmsProjectPerson.BIRTH_DATE,srpmsProjectPerson.getBirthDate());
        }
        if(StringUtils.isNotBlank(srpmsProjectPerson.getPositionTitle())){
            queryWrapper.like(SrpmsProjectPerson.POSITION_TITLE,srpmsProjectPerson.getPositionTitle());
        }
        if(StringUtils.isNotBlank(srpmsProjectPerson.getEducation())){
            queryWrapper.like(SrpmsProjectPerson.EDUCATION,srpmsProjectPerson.getEducation());
        }
        if(StringUtils.isNotBlank(srpmsProjectPerson.getMajor())){
            queryWrapper.like(SrpmsProjectPerson.MAJOR,srpmsProjectPerson.getMajor());
        }
        if(StringUtils.isNotBlank(srpmsProjectPerson.getTelPhone())){
            queryWrapper.like(SrpmsProjectPerson.TEL_PHONE,srpmsProjectPerson.getTelPhone());
        }
        if(StringUtils.isNotBlank(srpmsProjectPerson.getMobile())){
            queryWrapper.like(SrpmsProjectPerson.MOBILE,srpmsProjectPerson.getMobile());
        }
        if(StringUtils.isNotBlank(srpmsProjectPerson.getFaxNumber())){
            queryWrapper.like(SrpmsProjectPerson.FAX_NUMBER,srpmsProjectPerson.getFaxNumber());
        }
        if(StringUtils.isNotBlank(srpmsProjectPerson.getEmail())){
            queryWrapper.like(SrpmsProjectPerson.EMAIL,srpmsProjectPerson.getEmail());
        }
        if(StringUtils.isNotBlank(srpmsProjectPerson.getIdCardType())){
            queryWrapper.like(SrpmsProjectPerson.ID_CARD_TYPE,srpmsProjectPerson.getIdCardType());
        }
        if(StringUtils.isNotBlank(srpmsProjectPerson.getIdCard())){
            queryWrapper.like(SrpmsProjectPerson.ID_CARD,srpmsProjectPerson.getIdCard());
        }
        if(StringUtils.isNotBlank(srpmsProjectPerson.getEducationCountry())){
            queryWrapper.like(SrpmsProjectPerson.EDUCATION_COUNTRY,srpmsProjectPerson.getEducationCountry());
        }
        if(StringUtils.isNotBlank(srpmsProjectPerson.getDeptName())){
            queryWrapper.like(SrpmsProjectPerson.DEPT_NAME,srpmsProjectPerson.getDeptName());
        }
        if(StringUtils.isNotBlank(srpmsProjectPerson.getWorkPerYear())){
            queryWrapper.like(SrpmsProjectPerson.WORK_PER_YEAR,srpmsProjectPerson.getWorkPerYear());
        }
        if(StringUtils.isNotBlank(srpmsProjectPerson.getAddressAndZip())){
            queryWrapper.like(SrpmsProjectPerson.ADDRESS_AND_ZIP,srpmsProjectPerson.getAddressAndZip());
        }
        if(StringUtils.isNotBlank(srpmsProjectPerson.getLiveBaseName())){
            queryWrapper.like(SrpmsProjectPerson.LIVE_BASE_NAME,srpmsProjectPerson.getLiveBaseName());
        }
        if(StringUtils.isNotBlank(srpmsProjectPerson.getTalentPlan())){
            queryWrapper.like(SrpmsProjectPerson.TALENT_PLAN,srpmsProjectPerson.getTalentPlan());
        }
        if(StringUtils.isNotBlank(srpmsProjectPerson.getCreateTime())){
            queryWrapper.like(SrpmsProjectPerson.CREATE_TIME,srpmsProjectPerson.getCreateTime());
        }
        if(StringUtils.isNotBlank(srpmsProjectPerson.getCreateBy())){
            queryWrapper.like(SrpmsProjectPerson.CREATE_BY,srpmsProjectPerson.getCreateBy());
        }
        if(StringUtils.isNotBlank(srpmsProjectPerson.getUpdateTime())){
            queryWrapper.like(SrpmsProjectPerson.UPDATE_TIME,srpmsProjectPerson.getUpdateTime());
        }
        if(StringUtils.isNotBlank(srpmsProjectPerson.getUpdateBy())){
            queryWrapper.like(SrpmsProjectPerson.UPDATE_BY,srpmsProjectPerson.getUpdateBy());
        }
        return queryWrapper;
    }
     */
}

