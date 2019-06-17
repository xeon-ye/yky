package com.deloitte.services.srpmp.project.mpr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaMedicineTableQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaMedicineTable;
import com.deloitte.services.srpmp.project.mpr.mapper.MprEvaMedicineTableMapper;
import com.deloitte.services.srpmp.project.mpr.service.IMprEvaMedicineTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description :  MprEvaMedicineTable服务实现类
 * @Modified :
 */
@Service
@Transactional
public class MprEvaMedicineTableServiceImpl extends ServiceImpl<MprEvaMedicineTableMapper, MprEvaMedicineTable> implements IMprEvaMedicineTableService {


    @Autowired
    private MprEvaMedicineTableMapper mprEvaMedicineTableMapper;

    @Override
    public IPage<MprEvaMedicineTable> selectPage(MprEvaMedicineTableQueryForm queryForm ) {
        QueryWrapper<MprEvaMedicineTable> queryWrapper = new QueryWrapper <MprEvaMedicineTable>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprEvaMedicineTableMapper.selectPage(new Page<MprEvaMedicineTable>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<MprEvaMedicineTable> selectList(MprEvaMedicineTableQueryForm queryForm) {
        QueryWrapper<MprEvaMedicineTable> queryWrapper = new QueryWrapper <MprEvaMedicineTable>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprEvaMedicineTableMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<MprEvaMedicineTable> getQueryWrapper(QueryWrapper<MprEvaMedicineTable> queryWrapper,MprEvaMedicineTableQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getProjectNo())){
            queryWrapper.eq(MprEvaMedicineTable.PROJECT_NO,queryForm.getProjectNo());
        }
        if(StringUtils.isNotBlank(queryForm.getProductName())){
            queryWrapper.eq(MprEvaMedicineTable.PRODUCT_NAME,queryForm.getProductName());
        }
        if(StringUtils.isNotBlank(queryForm.getProductType())){
            queryWrapper.eq(MprEvaMedicineTable.PRODUCT_TYPE,queryForm.getProductType());
        }
        if(StringUtils.isNotBlank(queryForm.getRegistrationType())){
            queryWrapper.eq(MprEvaMedicineTable.REGISTRATION_TYPE,queryForm.getRegistrationType());
        }
        if(StringUtils.isNotBlank(queryForm.getCertNo())){
            queryWrapper.eq(MprEvaMedicineTable.CERT_NO,queryForm.getCertNo());
        }
        if(StringUtils.isNotBlank(queryForm.getJoinPerson())){
            queryWrapper.eq(MprEvaMedicineTable.JOIN_PERSON,queryForm.getJoinPerson());
        }
        if(StringUtils.isNotBlank(queryForm.getApprovalDate())){
            queryWrapper.eq(MprEvaMedicineTable.APPROVAL_DATE,queryForm.getApprovalDate());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(MprEvaMedicineTable.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(MprEvaMedicineTable.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(MprEvaMedicineTable.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(MprEvaMedicineTable.UPDATE_BY,queryForm.getUpdateBy());
        }
        return queryWrapper;
    }
     */
}

