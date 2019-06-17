package com.deloitte.services.srpmp.project.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.srpmp.project.base.param.SrpmsProjectDeptQueryForm;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectDept;
import com.deloitte.services.srpmp.project.base.mapper.SrpmsProjectDeptMapper;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : lixin
 * @Date : Create in 2019-02-19
 * @Description :  SrpmsProjectDept服务实现类
 * @Modified :
 */
@Service
@Transactional
public class SrpmsProjectDeptServiceImpl extends ServiceImpl<SrpmsProjectDeptMapper, SrpmsProjectDept> implements ISrpmsProjectDeptService {


    @Autowired
    private SrpmsProjectDeptMapper srpmsProjectDeptMapper;

    @Override
    public IPage<SrpmsProjectDept> selectPage(SrpmsProjectDeptQueryForm queryForm ) {
        QueryWrapper<SrpmsProjectDept> queryWrapper = new QueryWrapper <SrpmsProjectDept>();
        //getQueryWrapper(queryWrapper,queryForm);
        return srpmsProjectDeptMapper.selectPage(new Page<SrpmsProjectDept>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<SrpmsProjectDept> selectList(SrpmsProjectDeptQueryForm queryForm) {
        QueryWrapper<SrpmsProjectDept> queryWrapper = new QueryWrapper <SrpmsProjectDept>();
        //getQueryWrapper(queryWrapper,queryForm);
        return srpmsProjectDeptMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<SrpmsProjectDept> getQueryWrapper(QueryWrapper<SrpmsProjectDept> queryWrapper,BaseQueryForm<SrpmsProjectDeptQueryParam> queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(srpmsProjectDept.getDeptName())){
            queryWrapper.like(SrpmsProjectDept.DEPT_NAME,srpmsProjectDept.getDeptName());
        }
        if(StringUtils.isNotBlank(srpmsProjectDept.getZipCode())){
            queryWrapper.like(SrpmsProjectDept.ZIP_CODE,srpmsProjectDept.getZipCode());
        }
        if(StringUtils.isNotBlank(srpmsProjectDept.getAddress())){
            queryWrapper.like(SrpmsProjectDept.ADDRESS,srpmsProjectDept.getAddress());
        }
        if(StringUtils.isNotBlank(srpmsProjectDept.getContactsName())){
            queryWrapper.like(SrpmsProjectDept.CONTACTS_NAME,srpmsProjectDept.getContactsName());
        }
        if(StringUtils.isNotBlank(srpmsProjectDept.getPhone())){
            queryWrapper.like(SrpmsProjectDept.PHONE,srpmsProjectDept.getPhone());
        }
        if(StringUtils.isNotBlank(srpmsProjectDept.getEmail())){
            queryWrapper.like(SrpmsProjectDept.EMAIL,srpmsProjectDept.getEmail());
        }
        if(StringUtils.isNotBlank(srpmsProjectDept.getFaxNumber())){
            queryWrapper.like(SrpmsProjectDept.FAX_NUMBER,srpmsProjectDept.getFaxNumber());
        }
        if(StringUtils.isNotBlank(srpmsProjectDept.getDeptQuality())){
            queryWrapper.like(SrpmsProjectDept.DEPT_QUALITY,srpmsProjectDept.getDeptQuality());
        }
        if(StringUtils.isNotBlank(srpmsProjectDept.getDeptManDepartment())){
            queryWrapper.like(SrpmsProjectDept.DEPT_MAN_DEPARTMENT,srpmsProjectDept.getDeptManDepartment());
        }
        if(StringUtils.isNotBlank(srpmsProjectDept.getDeptSubjection())){
            queryWrapper.like(SrpmsProjectDept.DEPT_SUBJECTION,srpmsProjectDept.getDeptSubjection());
        }
        if(StringUtils.isNotBlank(srpmsProjectDept.getDeptLegalPersonName())){
            queryWrapper.like(SrpmsProjectDept.DEPT_LEGAL_PERSON_NAME,srpmsProjectDept.getDeptLegalPersonName());
        }
        if(StringUtils.isNotBlank(srpmsProjectDept.getProvinceCode())){
            queryWrapper.like(SrpmsProjectDept.PROVINCE_CODE,srpmsProjectDept.getProvinceCode());
        }
        if(StringUtils.isNotBlank(srpmsProjectDept.getCityCode())){
            queryWrapper.like(SrpmsProjectDept.CITY_CODE,srpmsProjectDept.getCityCode());
        }
        if(StringUtils.isNotBlank(srpmsProjectDept.getCountyCode())){
            queryWrapper.like(SrpmsProjectDept.COUNTY_CODE,srpmsProjectDept.getCountyCode());
        }
        if(StringUtils.isNotBlank(srpmsProjectDept.getBankAccountNameLoose())){
            queryWrapper.like(SrpmsProjectDept.BANK_ACCOUNT_NAME_LOOSE,srpmsProjectDept.getBankAccountNameLoose());
        }
        if(StringUtils.isNotBlank(srpmsProjectDept.getBankNameLoose())){
            queryWrapper.like(SrpmsProjectDept.BANK_NAME_LOOSE,srpmsProjectDept.getBankNameLoose());
        }
        if(StringUtils.isNotBlank(srpmsProjectDept.getBankAccountNumberLoose())){
            queryWrapper.like(SrpmsProjectDept.BANK_ACCOUNT_NUMBER_LOOSE,srpmsProjectDept.getBankAccountNumberLoose());
        }
        if(StringUtils.isNotBlank(srpmsProjectDept.getBankAccountNameBase())){
            queryWrapper.like(SrpmsProjectDept.BANK_ACCOUNT_NAME_BASE,srpmsProjectDept.getBankAccountNameBase());
        }
        if(StringUtils.isNotBlank(srpmsProjectDept.getBankNameBase())){
            queryWrapper.like(SrpmsProjectDept.BANK_NAME_BASE,srpmsProjectDept.getBankNameBase());
        }
        if(StringUtils.isNotBlank(srpmsProjectDept.getBankAccountNumberBase())){
            queryWrapper.like(SrpmsProjectDept.BANK_ACCOUNT_NUMBER_BASE,srpmsProjectDept.getBankAccountNumberBase());
        }
        if(StringUtils.isNotBlank(srpmsProjectDept.getCreateTime())){
            queryWrapper.like(SrpmsProjectDept.CREATE_TIME,srpmsProjectDept.getCreateTime());
        }
        if(StringUtils.isNotBlank(srpmsProjectDept.getCreateBy())){
            queryWrapper.like(SrpmsProjectDept.CREATE_BY,srpmsProjectDept.getCreateBy());
        }
        if(StringUtils.isNotBlank(srpmsProjectDept.getUpdateTime())){
            queryWrapper.like(SrpmsProjectDept.UPDATE_TIME,srpmsProjectDept.getUpdateTime());
        }
        if(StringUtils.isNotBlank(srpmsProjectDept.getUpdateBy())){
            queryWrapper.like(SrpmsProjectDept.UPDATE_BY,srpmsProjectDept.getUpdateBy());
        }
        if(StringUtils.isNotBlank(srpmsProjectDept.getSourceDeptId())){
            queryWrapper.like(SrpmsProjectDept.SOURCE_DEPT_ID,srpmsProjectDept.getSourceDeptId());
        }
        return queryWrapper;
    }
     */
}

