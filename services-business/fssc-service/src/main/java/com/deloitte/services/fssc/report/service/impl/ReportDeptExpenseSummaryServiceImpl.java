package com.deloitte.services.fssc.report.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.report.param.ReportDeptExpenseSummaryQueryForm;
import com.deloitte.services.fssc.common.util.FsscCommonUtil;
import com.deloitte.services.fssc.report.entity.ReportDeptExpenseSummary;
import com.deloitte.services.fssc.report.mapper.ReportDeptExpenseSummaryMapper;
import com.deloitte.services.fssc.report.service.IReportDeptExpenseSummaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.fssc.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : jaws
 * @Date : Create in 2019-06-12
 * @Description :  ReportDeptExpenseSummary服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ReportDeptExpenseSummaryServiceImpl extends ServiceImpl<ReportDeptExpenseSummaryMapper, ReportDeptExpenseSummary> implements IReportDeptExpenseSummaryService {


    @Autowired
    private ReportDeptExpenseSummaryMapper reportDeptExpenseSummaryMapper;

    @Override
    public IPage<ReportDeptExpenseSummary> selectPage(ReportDeptExpenseSummaryQueryForm queryForm ) {
        QueryWrapper<ReportDeptExpenseSummary> queryWrapper = new QueryWrapper <ReportDeptExpenseSummary>();
        getQueryWrapper(queryWrapper,queryForm);
        return reportDeptExpenseSummaryMapper.selectPage(new Page<ReportDeptExpenseSummary>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);
        }

    @Override
    public List<ReportDeptExpenseSummary> selectList(ReportDeptExpenseSummaryQueryForm queryForm) {
        QueryWrapper<ReportDeptExpenseSummary> queryWrapper = new QueryWrapper <ReportDeptExpenseSummary>();
        getQueryWrapper(queryWrapper,queryForm);
        return reportDeptExpenseSummaryMapper.selectList(queryWrapper);
    }



    public QueryWrapper<ReportDeptExpenseSummary> getQueryWrapper(QueryWrapper<ReportDeptExpenseSummary> queryWrapper,ReportDeptExpenseSummaryQueryForm queryForm){
        //条件拼接
        if(queryForm.getId()!=null){
            queryWrapper.eq(ReportDeptExpenseSummary.ID,queryForm.getId());
        }
        if(queryForm.getUnitId()!=null){
            queryWrapper.eq(ReportDeptExpenseSummary.UNIT_ID,queryForm.getUnitId());
        }
        if(StringUtils.isNotEmpty(queryForm.getUnitCode())){
            queryWrapper.eq(ReportDeptExpenseSummary.UNIT_CODE,queryForm.getUnitCode());
        }
        if(StringUtils.isNotEmpty(queryForm.getYear())){
            queryWrapper.eq(ReportDeptExpenseSummary.YEAR,queryForm.getYear());
        }
        if(StringUtils.isNotEmpty(queryForm.getMonth())){
            queryWrapper.eq(ReportDeptExpenseSummary.MONTH,queryForm.getMonth());
        }
        if(StringUtils.isNotEmpty(queryForm.getStatus())){
            queryWrapper.eq(ReportDeptExpenseSummary.STATUS,queryForm.getStatus());
        }
        if(StringUtils.isNotBlank(queryForm.getPeriodType())){
            queryWrapper.eq(ReportDeptExpenseSummary.PERIOD_TYPE,queryForm.getPeriodType());
        }
        if(StringUtils.isNotEmpty(queryForm.getMergeFlag())){
            queryWrapper.eq(ReportDeptExpenseSummary.MERGE_FLAG,queryForm.getMergeFlag());
        }

        return queryWrapper;
    }

}

