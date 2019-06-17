package com.deloitte.services.srpmp.project.mpr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaPatentTableQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaPatentTable;
import com.deloitte.services.srpmp.project.mpr.mapper.MprEvaPatentTableMapper;
import com.deloitte.services.srpmp.project.mpr.service.IMprEvaPatentTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description :  MprEvaPatentTable服务实现类
 * @Modified :
 */
@Service
@Transactional
public class MprEvaPatentTableServiceImpl extends ServiceImpl<MprEvaPatentTableMapper, MprEvaPatentTable> implements IMprEvaPatentTableService {


    @Autowired
    private MprEvaPatentTableMapper mprEvaPatentTableMapper;

    @Override
    public IPage<MprEvaPatentTable> selectPage(MprEvaPatentTableQueryForm queryForm ) {
        QueryWrapper<MprEvaPatentTable> queryWrapper = new QueryWrapper <MprEvaPatentTable>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprEvaPatentTableMapper.selectPage(new Page<MprEvaPatentTable>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<MprEvaPatentTable> selectList(MprEvaPatentTableQueryForm queryForm) {
        QueryWrapper<MprEvaPatentTable> queryWrapper = new QueryWrapper <MprEvaPatentTable>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprEvaPatentTableMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<MprEvaPatentTable> getQueryWrapper(QueryWrapper<MprEvaPatentTable> queryWrapper,MprEvaPatentTableQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getProjectNo())){
            queryWrapper.eq(MprEvaPatentTable.PROJECT_NO,queryForm.getProjectNo());
        }
        if(StringUtils.isNotBlank(queryForm.getPatentName())){
            queryWrapper.eq(MprEvaPatentTable.PATENT_NAME,queryForm.getPatentName());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyAuthNum())){
            queryWrapper.eq(MprEvaPatentTable.APPLY_AUTH_NUM,queryForm.getApplyAuthNum());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyApprovalCountry())){
            queryWrapper.eq(MprEvaPatentTable.APPLY_APPROVAL_COUNTRY,queryForm.getApplyApprovalCountry());
        }
        if(StringUtils.isNotBlank(queryForm.getPatentType())){
            queryWrapper.eq(MprEvaPatentTable.PATENT_TYPE,queryForm.getPatentType());
        }
        if(StringUtils.isNotBlank(queryForm.getCompletePerson())){
            queryWrapper.eq(MprEvaPatentTable.COMPLETE_PERSON,queryForm.getCompletePerson());
        }
        if(StringUtils.isNotBlank(queryForm.getCompleteDate())){
            queryWrapper.eq(MprEvaPatentTable.COMPLETE_DATE,queryForm.getCompleteDate());
        }
        if(StringUtils.isNotBlank(queryForm.getPatentStatus())){
            queryWrapper.eq(MprEvaPatentTable.PATENT_STATUS,queryForm.getPatentStatus());
        }
        if(StringUtils.isNotBlank(queryForm.getIsUse())){
            queryWrapper.eq(MprEvaPatentTable.IS_USE,queryForm.getIsUse());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(MprEvaPatentTable.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(MprEvaPatentTable.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(MprEvaPatentTable.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(MprEvaPatentTable.UPDATE_BY,queryForm.getUpdateBy());
        }
        return queryWrapper;
    }
     */
}

