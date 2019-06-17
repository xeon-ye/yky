package com.deloitte.services.fssc.report.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.report.param.ReportEduDoStatisQueryForm;
import com.deloitte.services.fssc.report.entity.ReportEduDoStatis;
import com.deloitte.services.fssc.report.mapper.ReportEduDoStatisMapper;
import com.deloitte.services.fssc.report.service.IReportEduDoStatisService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-06-14
 * @Description :  ReportEduDoStatis服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ReportEduDoStatisServiceImpl extends ServiceImpl<ReportEduDoStatisMapper, ReportEduDoStatis> implements IReportEduDoStatisService {


    @Autowired
    private ReportEduDoStatisMapper reportEduDoStatisMapper;

    @Override
    public IPage<ReportEduDoStatis> selectPage(ReportEduDoStatisQueryForm queryForm) {
        QueryWrapper<ReportEduDoStatis> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return reportEduDoStatisMapper.selectPage(new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()), queryWrapper);

    }

    @Override
    public List<ReportEduDoStatis> selectList(ReportEduDoStatisQueryForm queryForm) {
        QueryWrapper<ReportEduDoStatis> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return reportEduDoStatisMapper.selectList(queryWrapper);
    }


    /**
     * 通用查询
     *
     * @param queryWrapper,queryForm
     * @return
     **/
    public QueryWrapper<ReportEduDoStatis> getQueryWrapper(QueryWrapper<ReportEduDoStatis> queryWrapper, ReportEduDoStatisQueryForm queryForm) {
        //条件拼接
        if (queryForm.getUnitId() != null) {
            queryWrapper.eq(ReportEduDoStatis.UNIT_ID, queryForm.getUnitId());
        }
        if (StringUtils.isNotBlank(queryForm.getUnitCode())) {
            queryWrapper.eq(ReportEduDoStatis.UNIT_CODE, queryForm.getUnitCode());
        }
        if (StringUtils.isNotBlank(queryForm.getName())) {
            queryWrapper.eq(ReportEduDoStatis.NAME, queryForm.getName());
        }
        if (StringUtils.isNotBlank(queryForm.getYear())) {
            queryWrapper.eq(ReportEduDoStatis.YEAR, queryForm.getYear());
        }
        if (StringUtils.isNotBlank(queryForm.getMonth())) {
            queryWrapper.eq(ReportEduDoStatis.MONTH, queryForm.getMonth());
        }
        if (StringUtils.isNotBlank(queryForm.getStatus())) {
            queryWrapper.eq(ReportEduDoStatis.STATUS, queryForm.getStatus());
        }
        if (StringUtils.isNotBlank(queryForm.getPeriodType())) {
            queryWrapper.eq(ReportEduDoStatis.PERIOD_TYPE, queryForm.getPeriodType());
        }
        if (StringUtils.isNotBlank(queryForm.getMergeFlag())) {
            queryWrapper.eq(ReportEduDoStatis.MERGE_FLAG, queryForm.getMergeFlag());
        }
        if (queryForm.getCreateBy() != null) {
            queryWrapper.eq(ReportEduDoStatis.CREATE_BY, queryForm.getCreateBy());
        }
        if (queryForm.getUpdateBy() != null) {
            queryWrapper.eq(ReportEduDoStatis.UPDATE_BY, queryForm.getUpdateBy());
        }
        return queryWrapper;
    }
}

