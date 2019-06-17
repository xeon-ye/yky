package com.deloitte.services.fssc.report.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.report.param.ReportTotalQueryQueryForm;
import com.deloitte.services.fssc.report.entity.ReportTotalQuery;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-06-12
 * @Description : ReportTotalQuery服务类接口
 * @Modified :
 */
public interface IReportTotalQueryService extends IService<ReportTotalQuery> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<ReportTotalQuery>
     */
    IPage<ReportTotalQuery> selectPage(ReportTotalQueryQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<ReportTotalQuery>
     */
    List<ReportTotalQuery> selectList(ReportTotalQueryQueryForm queryForm);

    /**
     *
     * @param reportId
     * @param reportType
     * @return
     */
    ReportTotalQuery getByReport(Long reportId,String reportType);
}
