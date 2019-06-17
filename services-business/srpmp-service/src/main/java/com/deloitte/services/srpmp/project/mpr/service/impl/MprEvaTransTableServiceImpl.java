package com.deloitte.services.srpmp.project.mpr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaTransTableQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaTransTable;
import com.deloitte.services.srpmp.project.mpr.mapper.MprEvaTransTableMapper;
import com.deloitte.services.srpmp.project.mpr.service.IMprEvaTransTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description :  MprEvaTransTable服务实现类
 * @Modified :
 */
@Service
@Transactional
public class MprEvaTransTableServiceImpl extends ServiceImpl<MprEvaTransTableMapper, MprEvaTransTable> implements IMprEvaTransTableService {


    @Autowired
    private MprEvaTransTableMapper mprEvaTransTableMapper;

    @Override
    public IPage<MprEvaTransTable> selectPage(MprEvaTransTableQueryForm queryForm ) {
        QueryWrapper<MprEvaTransTable> queryWrapper = new QueryWrapper <MprEvaTransTable>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprEvaTransTableMapper.selectPage(new Page<MprEvaTransTable>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<MprEvaTransTable> selectList(MprEvaTransTableQueryForm queryForm) {
        QueryWrapper<MprEvaTransTable> queryWrapper = new QueryWrapper <MprEvaTransTable>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprEvaTransTableMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<MprEvaTransTable> getQueryWrapper(QueryWrapper<MprEvaTransTable> queryWrapper,MprEvaTransTableQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getProjectNo())){
            queryWrapper.eq(MprEvaTransTable.PROJECT_NO,queryForm.getProjectNo());
        }
        if(StringUtils.isNotBlank(queryForm.getOutcomeName())){
            queryWrapper.eq(MprEvaTransTable.OUTCOME_NAME,queryForm.getOutcomeName());
        }
        if(StringUtils.isNotBlank(queryForm.getTechTrans())){
            queryWrapper.eq(MprEvaTransTable.TECH_TRANS,queryForm.getTechTrans());
        }
        if(StringUtils.isNotBlank(queryForm.getUnionDev())){
            queryWrapper.eq(MprEvaTransTable.UNION_DEV,queryForm.getUnionDev());
        }
        if(StringUtils.isNotBlank(queryForm.getTechService())){
            queryWrapper.eq(MprEvaTransTable.TECH_SERVICE,queryForm.getTechService());
        }
        if(StringUtils.isNotBlank(queryForm.getContractSignYear())){
            queryWrapper.eq(MprEvaTransTable.CONTRACT_SIGN_YEAR,queryForm.getContractSignYear());
        }
        if(StringUtils.isNotBlank(queryForm.getContractAmount())){
            queryWrapper.eq(MprEvaTransTable.CONTRACT_AMOUNT,queryForm.getContractAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(MprEvaTransTable.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(MprEvaTransTable.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(MprEvaTransTable.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(MprEvaTransTable.UPDATE_BY,queryForm.getUpdateBy());
        }
        return queryWrapper;
    }
     */
}

