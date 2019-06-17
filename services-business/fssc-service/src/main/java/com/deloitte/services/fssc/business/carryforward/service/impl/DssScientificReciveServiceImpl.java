package com.deloitte.services.fssc.business.carryforward.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.carryforward.param.DssScientificReciveQueryForm;
import com.deloitte.services.fssc.business.carryforward.entity.DssScientificRecive;
import com.deloitte.services.fssc.business.carryforward.mapper.DssScientificReciveMapper;
import com.deloitte.services.fssc.business.carryforward.service.IDssScientificReciveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : chenx
 * @Date : Create in 2019-06-11
 * @Description :  DssScientificRecive服务实现类
 * @Modified :
 */
@Service
@Transactional
public class DssScientificReciveServiceImpl extends ServiceImpl<DssScientificReciveMapper, DssScientificRecive> implements IDssScientificReciveService {


    @Autowired
    private DssScientificReciveMapper dssScientificReciveMapper;

    @Override
    public IPage<DssScientificRecive> selectPage(DssScientificReciveQueryForm queryForm ) {
        QueryWrapper<DssScientificRecive> queryWrapper = new QueryWrapper <DssScientificRecive>();
        //getQueryWrapper(queryWrapper,queryForm);
        return dssScientificReciveMapper.selectPage(new Page<DssScientificRecive>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<DssScientificRecive> selectList(DssScientificReciveQueryForm queryForm) {
        QueryWrapper<DssScientificRecive> queryWrapper = new QueryWrapper <DssScientificRecive>();
        //getQueryWrapper(queryWrapper,queryForm);
        return dssScientificReciveMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<DssScientificRecive> getQueryWrapper(QueryWrapper<DssScientificRecive> queryWrapper,DssScientificReciveQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getProCode())){
            queryWrapper.eq(DssScientificRecive.PRO_CODE,queryForm.getProCode());
        }
        if(StringUtils.isNotBlank(queryForm.getTaskCode())){
            queryWrapper.eq(DssScientificRecive.TASK_CODE,queryForm.getTaskCode());
        }
        if(StringUtils.isNotBlank(queryForm.getReciveDate())){
            queryWrapper.eq(DssScientificRecive.RECIVE_DATE,queryForm.getReciveDate());
        }
        if(StringUtils.isNotBlank(queryForm.getFunds())){
            queryWrapper.eq(DssScientificRecive.FUNDS,queryForm.getFunds());
        }
        if(StringUtils.isNotBlank(queryForm.getReciveDeptId())){
            queryWrapper.eq(DssScientificRecive.RECIVE_DEPT_ID,queryForm.getReciveDeptId());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(DssScientificRecive.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(DssScientificRecive.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(DssScientificRecive.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(DssScientificRecive.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getFromSystem())){
            queryWrapper.eq(DssScientificRecive.FROM_SYSTEM,queryForm.getFromSystem());
        }
        if(StringUtils.isNotBlank(queryForm.getBudgetYear())){
            queryWrapper.eq(DssScientificRecive.BUDGET_YEAR,queryForm.getBudgetYear());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentNum  ())){
            queryWrapper.eq(DssScientificRecive.DOCUMENT_NUM  ,queryForm.getDocumentNum  ());
        }
        if(StringUtils.isNotBlank(queryForm.getStatus())){
            queryWrapper.eq(DssScientificRecive.STATUS,queryForm.getStatus());
        }
        if(StringUtils.isNotBlank(queryForm.getCwLineId())){
            queryWrapper.eq(DssScientificRecive.CW_LINE_ID,queryForm.getCwLineId());
        }
        return queryWrapper;
    }
     */
}

