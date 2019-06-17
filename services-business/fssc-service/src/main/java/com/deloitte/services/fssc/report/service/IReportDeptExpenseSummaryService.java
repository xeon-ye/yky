package com.deloitte.services.fssc.report.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.report.param.ReportDeptExpenseSummaryQueryForm;
import com.deloitte.services.fssc.report.entity.ReportDeptExpenseSummary;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-06-12
 * @Description : ReportDeptExpenseSummary服务类接口
 * @Modified :
 */
public interface IReportDeptExpenseSummaryService extends IService<ReportDeptExpenseSummary> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<ReportDeptExpenseSummary>
     */
    IPage<ReportDeptExpenseSummary> selectPage(ReportDeptExpenseSummaryQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<ReportDeptExpenseSummary>
     */
    List<ReportDeptExpenseSummary> selectList(ReportDeptExpenseSummaryQueryForm queryForm);
}
