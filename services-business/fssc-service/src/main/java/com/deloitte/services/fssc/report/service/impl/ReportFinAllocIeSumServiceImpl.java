package com.deloitte.services.fssc.report.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.report.param.ReportFinAllocIeSumQueryForm;
import com.deloitte.services.fssc.report.entity.ReportFinAllocIeSum;
import com.deloitte.services.fssc.report.mapper.ReportFinAllocIeSumMapper;
import com.deloitte.services.fssc.report.service.IReportFinAllocIeSumService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : jaws
 * @Date : Create in 2019-06-14
 * @Description :  ReportFinAllocIeSum服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ReportFinAllocIeSumServiceImpl extends ServiceImpl<ReportFinAllocIeSumMapper, ReportFinAllocIeSum> implements IReportFinAllocIeSumService {


    @Autowired
    private ReportFinAllocIeSumMapper reportFinAllocIeSumMapper;

    @Override
    public IPage<ReportFinAllocIeSum> selectPage(ReportFinAllocIeSumQueryForm queryForm ) {
        QueryWrapper<ReportFinAllocIeSum> queryWrapper = new QueryWrapper <ReportFinAllocIeSum>();
        //getQueryWrapper(queryWrapper,queryForm);
        return reportFinAllocIeSumMapper.selectPage(new Page<ReportFinAllocIeSum>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<ReportFinAllocIeSum> selectList(ReportFinAllocIeSumQueryForm queryForm) {
        QueryWrapper<ReportFinAllocIeSum> queryWrapper = new QueryWrapper <ReportFinAllocIeSum>();
        getQueryWrapper(queryWrapper,queryForm);
        return reportFinAllocIeSumMapper.selectList(queryWrapper);
    }


    public QueryWrapper<ReportFinAllocIeSum> getQueryWrapper(QueryWrapper<ReportFinAllocIeSum> queryWrapper,ReportFinAllocIeSumQueryForm queryForm){
        //条件拼接
        if(queryForm.getUnitId()!=null){
            queryWrapper.eq(ReportFinAllocIeSum.UNIT_ID,queryForm.getUnitId());
        }
        if(StringUtils.isNotBlank(queryForm.getUnitCode())){
            queryWrapper.eq(ReportFinAllocIeSum.UNIT_CODE,queryForm.getUnitCode());
        }
        if(StringUtils.isNotBlank(queryForm.getName())){
            queryWrapper.eq(ReportFinAllocIeSum.NAME,queryForm.getName());
        }
        if(StringUtils.isNotBlank(queryForm.getYear())){
            queryWrapper.eq(ReportFinAllocIeSum.YEAR,queryForm.getYear());
        }
        if(StringUtils.isNotBlank(queryForm.getMonth())){
            queryWrapper.eq(ReportFinAllocIeSum.MONTH,queryForm.getMonth());
        }
        if(StringUtils.isNotBlank(queryForm.getStatus())){
            queryWrapper.eq(ReportFinAllocIeSum.STATUS,queryForm.getStatus());
        }
        if(StringUtils.isNotBlank(queryForm.getPeriodType())){
            queryWrapper.eq(ReportFinAllocIeSum.PERIOD_TYPE,queryForm.getPeriodType());
        }
        if(StringUtils.isNotBlank(queryForm.getMergeFlag())){
            queryWrapper.eq(ReportFinAllocIeSum.MERGE_FLAG,queryForm.getMergeFlag());
        }

        return queryWrapper;
    }

}

