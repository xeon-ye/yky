package com.deloitte.services.fssc.report.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.report.param.ReportTotalQueryQueryForm;
import com.deloitte.services.fssc.report.entity.ReportTotalQuery;
import com.deloitte.services.fssc.report.mapper.ReportTotalQueryMapper;
import com.deloitte.services.fssc.report.service.IReportTotalQueryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-06-12
 * @Description :  ReportTotalQuery服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ReportTotalQueryServiceImpl extends ServiceImpl<ReportTotalQueryMapper, ReportTotalQuery> implements IReportTotalQueryService {


    @Autowired
    private ReportTotalQueryMapper reportTotalQueryMapper;

    @Override
    public IPage<ReportTotalQuery> selectPage(ReportTotalQueryQueryForm queryForm) {
        QueryWrapper<ReportTotalQuery> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return reportTotalQueryMapper.selectPage(new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()), queryWrapper);
    }

    @Override
    public List<ReportTotalQuery> selectList(ReportTotalQueryQueryForm queryForm) {
        QueryWrapper<ReportTotalQuery> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return reportTotalQueryMapper.selectList(queryWrapper);
    }

    @Override
    public ReportTotalQuery getByReport(Long reportId, String reportType) {
        QueryWrapper<ReportTotalQuery> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ReportTotalQuery.REPORT_ID, reportId);
        queryWrapper.eq(ReportTotalQuery.REPORT_TYPE, reportType);
        return this.getOne(queryWrapper);
    }

    /**
     * 通用查询
     *
     * @param queryWrapper,queryForm
     * @return
     **/
    public QueryWrapper<ReportTotalQuery> getQueryWrapper(QueryWrapper<ReportTotalQuery> queryWrapper, ReportTotalQueryQueryForm queryForm) {
        //条件拼接
        if (queryForm.getUnitId() != null) {
            queryWrapper.eq(ReportTotalQuery.UNIT_ID, queryForm.getUnitId());
        }
        if (StringUtils.isNotBlank(queryForm.getUnitCode())) {
            queryWrapper.eq(ReportTotalQuery.UNIT_CODE, queryForm.getUnitCode());
        }
        if (StringUtils.isNotBlank(queryForm.getReportType())) {
            queryWrapper.eq(ReportTotalQuery.REPORT_TYPE, queryForm.getReportType());
        }
        if (StringUtils.isNotBlank(queryForm.getReportName())) {
            queryWrapper.like(ReportTotalQuery.REPORT_NAME, queryForm.getReportName());
        }
        if (queryForm.getReportId() != null) {
            queryWrapper.eq(ReportTotalQuery.REPORT_ID, queryForm.getReportId());
        }
        if (StringUtils.isNotBlank(queryForm.getYear())) {
            queryWrapper.eq(ReportTotalQuery.YEAR, queryForm.getYear());
        }
        if (StringUtils.isNotBlank(queryForm.getMonth())) {
            queryWrapper.eq(ReportTotalQuery.MONTH, queryForm.getMonth());
        }
        if (StringUtils.isNotBlank(queryForm.getReportStatus())) {
            queryWrapper.eq(ReportTotalQuery.REPORT_STATUS, queryForm.getReportStatus());
        }
        if (StringUtils.isNotBlank(queryForm.getPeriodType())) {
            queryWrapper.eq(ReportTotalQuery.PERIOD_TYPE, queryForm.getPeriodType());
        }
        if (StringUtils.isNotBlank(queryForm.getMergeFlag())) {
            queryWrapper.eq(ReportTotalQuery.MERGE_FLAG, queryForm.getMergeFlag());
        }
        return queryWrapper;
    }
}

