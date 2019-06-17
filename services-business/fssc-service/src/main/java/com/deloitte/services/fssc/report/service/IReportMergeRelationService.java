package com.deloitte.services.fssc.report.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.report.param.ReportMergeRelationQueryForm;
import com.deloitte.services.fssc.report.entity.ReportMergeRelation;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-06-12
 * @Description : ReportMergeRelation服务类接口
 * @Modified :
 */
public interface IReportMergeRelationService extends IService<ReportMergeRelation> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<ReportMergeRelation>
     */
    IPage<ReportMergeRelation> selectPage(ReportMergeRelationQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<ReportMergeRelation>
     */
    List<ReportMergeRelation> selectList(ReportMergeRelationQueryForm queryForm);
}
