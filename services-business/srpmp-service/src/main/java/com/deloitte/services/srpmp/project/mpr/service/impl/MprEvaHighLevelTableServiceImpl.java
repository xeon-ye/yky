package com.deloitte.services.srpmp.project.mpr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaHighLevelTableQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaHighLevelTable;
import com.deloitte.services.srpmp.project.mpr.mapper.MprEvaHighLevelTableMapper;
import com.deloitte.services.srpmp.project.mpr.service.IMprEvaHighLevelTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description :  MprEvaHighLevelTable服务实现类
 * @Modified :
 */
@Service
@Transactional
public class MprEvaHighLevelTableServiceImpl extends ServiceImpl<MprEvaHighLevelTableMapper, MprEvaHighLevelTable> implements IMprEvaHighLevelTableService {


    @Autowired
    private MprEvaHighLevelTableMapper mprEvaHighLevelTableMapper;

    @Override
    public IPage<MprEvaHighLevelTable> selectPage(MprEvaHighLevelTableQueryForm queryForm ) {
        QueryWrapper<MprEvaHighLevelTable> queryWrapper = new QueryWrapper <MprEvaHighLevelTable>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprEvaHighLevelTableMapper.selectPage(new Page<MprEvaHighLevelTable>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<MprEvaHighLevelTable> selectList(MprEvaHighLevelTableQueryForm queryForm) {
        QueryWrapper<MprEvaHighLevelTable> queryWrapper = new QueryWrapper <MprEvaHighLevelTable>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprEvaHighLevelTableMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<MprEvaHighLevelTable> getQueryWrapper(QueryWrapper<MprEvaHighLevelTable> queryWrapper,MprEvaHighLevelTableQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getProjectNo())){
            queryWrapper.eq(MprEvaHighLevelTable.PROJECT_NO,queryForm.getProjectNo());
        }
        if(StringUtils.isNotBlank(queryForm.getName())){
            queryWrapper.eq(MprEvaHighLevelTable.NAME,queryForm.getName());
        }
        if(StringUtils.isNotBlank(queryForm.getTalentType())){
            queryWrapper.eq(MprEvaHighLevelTable.TALENT_TYPE,queryForm.getTalentType());
        }
        if(StringUtils.isNotBlank(queryForm.getApprovalNumber())){
            queryWrapper.eq(MprEvaHighLevelTable.APPROVAL_NUMBER,queryForm.getApprovalNumber());
        }
        if(StringUtils.isNotBlank(queryForm.getBatch())){
            queryWrapper.eq(MprEvaHighLevelTable.BATCH,queryForm.getBatch());
        }
        if(StringUtils.isNotBlank(queryForm.getElectedDate())){
            queryWrapper.eq(MprEvaHighLevelTable.ELECTED_DATE,queryForm.getElectedDate());
        }
        if(StringUtils.isNotBlank(queryForm.getUnit())){
            queryWrapper.eq(MprEvaHighLevelTable.UNIT,queryForm.getUnit());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(MprEvaHighLevelTable.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(MprEvaHighLevelTable.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(MprEvaHighLevelTable.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(MprEvaHighLevelTable.UPDATE_BY,queryForm.getUpdateBy());
        }
        return queryWrapper;
    }
     */
}

