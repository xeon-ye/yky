package com.deloitte.services.fssc.report.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.report.param.ReportFinAllocIeSumQueryForm;
import com.deloitte.services.fssc.report.entity.ReportFinAllocIeSum;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-06-14
 * @Description : ReportFinAllocIeSum服务类接口
 * @Modified :
 */
public interface IReportFinAllocIeSumService extends IService<ReportFinAllocIeSum> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<ReportFinAllocIeSum>
     */
    IPage<ReportFinAllocIeSum> selectPage(ReportFinAllocIeSumQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<ReportFinAllocIeSum>
     */
    List<ReportFinAllocIeSum> selectList(ReportFinAllocIeSumQueryForm queryForm);
}
