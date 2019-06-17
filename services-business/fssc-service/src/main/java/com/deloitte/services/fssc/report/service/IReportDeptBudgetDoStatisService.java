package com.deloitte.services.fssc.report.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.report.param.ReportDeptBudgetDoStatisQueryForm;
import com.deloitte.services.fssc.report.entity.ReportDeptBudgetDoStatis;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-06-14
 * @Description : ReportDeptBudgetDoStatis服务类接口
 * @Modified :
 */
public interface IReportDeptBudgetDoStatisService extends IService<ReportDeptBudgetDoStatis> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<ReportDeptBudgetDoStatis>
     */
    IPage<ReportDeptBudgetDoStatis> selectPage(ReportDeptBudgetDoStatisQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<ReportDeptBudgetDoStatis>
     */
    List<ReportDeptBudgetDoStatis> selectList(ReportDeptBudgetDoStatisQueryForm queryForm);
}
