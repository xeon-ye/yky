package com.deloitte.services.fssc.report.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.report.param.ReportIncomeExpensesSummaryQueryForm;
import com.deloitte.services.fssc.report.entity.ReportIncomeExpensesSummary;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-06-12
 * @Description : ReportIncomeExpensesSummary服务类接口
 * @Modified :
 */
public interface IReportIncomeExpensesSummaryService extends IService<ReportIncomeExpensesSummary> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<ReportIncomeExpensesSummary>
     */
    IPage<ReportIncomeExpensesSummary> selectPage(ReportIncomeExpensesSummaryQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<ReportIncomeExpensesSummary>
     */
    List<ReportIncomeExpensesSummary> selectList(ReportIncomeExpensesSummaryQueryForm queryForm);
}
