package com.deloitte.services.fssc.report.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.report.param.ReportIncomeExpensesSummaryQueryForm;
import com.deloitte.services.fssc.report.entity.ReportIncomeExpensesSummary;
import com.deloitte.services.fssc.report.mapper.ReportIncomeExpensesSummaryMapper;
import com.deloitte.services.fssc.report.service.IReportIncomeExpensesSummaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : jaws
 * @Date : Create in 2019-06-12
 * @Description :  ReportIncomeExpensesSummary服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ReportIncomeExpensesSummaryServiceImpl extends ServiceImpl<ReportIncomeExpensesSummaryMapper, ReportIncomeExpensesSummary> implements IReportIncomeExpensesSummaryService {


    @Autowired
    private ReportIncomeExpensesSummaryMapper reportIncomeExpensesSummaryMapper;

    @Override
    public IPage<ReportIncomeExpensesSummary> selectPage(ReportIncomeExpensesSummaryQueryForm queryForm ) {
        QueryWrapper<ReportIncomeExpensesSummary> queryWrapper = new QueryWrapper <ReportIncomeExpensesSummary>();
        //getQueryWrapper(queryWrapper,queryForm);
        return reportIncomeExpensesSummaryMapper.selectPage(new Page<ReportIncomeExpensesSummary>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<ReportIncomeExpensesSummary> selectList(ReportIncomeExpensesSummaryQueryForm queryForm) {
        QueryWrapper<ReportIncomeExpensesSummary> queryWrapper = new QueryWrapper <ReportIncomeExpensesSummary>();
        getQueryWrapper(queryWrapper,queryForm);
        return reportIncomeExpensesSummaryMapper.selectList(queryWrapper);
    }


    public QueryWrapper<ReportIncomeExpensesSummary> getQueryWrapper(QueryWrapper<ReportIncomeExpensesSummary> queryWrapper,ReportIncomeExpensesSummaryQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getName())){
            queryWrapper.eq(ReportIncomeExpensesSummary.NAME,queryForm.getName());
        }
        if(StringUtils.isNotBlank(queryForm.getYear())){
            queryWrapper.eq(ReportIncomeExpensesSummary.YEAR,queryForm.getYear());
        }

        if(StringUtils.isNotBlank(queryForm.getStatus())){
            queryWrapper.eq(ReportIncomeExpensesSummary.STATUS,queryForm.getStatus());
        }
        if(queryForm.getCreateBy()!=null){
            queryWrapper.eq(ReportIncomeExpensesSummary.CREATE_BY,queryForm.getCreateBy());
        }
        if(queryForm.getUpdateBy()!=null){
            queryWrapper.eq(ReportIncomeExpensesSummary.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(queryForm.getUnitId()!=null){
            queryWrapper.eq(ReportIncomeExpensesSummary.UNIT_ID,queryForm.getUnitId());
        }
        if(StringUtils.isNotBlank(queryForm.getPeriodType())){
            queryWrapper.eq(ReportIncomeExpensesSummary.PERIOD_TYPE,queryForm.getPeriodType());
        }

        if(StringUtils.isNotBlank(queryForm.getMonth())){
            queryWrapper.eq(ReportIncomeExpensesSummary.MONTH,queryForm.getMonth());
        }
        if(StringUtils.isNotBlank(queryForm.getMergeFlag())){
            queryWrapper.eq(ReportIncomeExpensesSummary.MERGE_FLAG,queryForm.getMergeFlag());
        }
        return queryWrapper;
    }

}

