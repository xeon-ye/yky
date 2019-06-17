package com.deloitte.services.fssc.report.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.report.param.ReportEduDoStatisQueryForm;
import com.deloitte.services.fssc.report.entity.ReportEduDoStatis;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-06-14
 * @Description : ReportEduDoStatis服务类接口
 * @Modified :
 */
public interface IReportEduDoStatisService extends IService<ReportEduDoStatis> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<ReportEduDoStatis>
     */
    IPage<ReportEduDoStatis> selectPage(ReportEduDoStatisQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<ReportEduDoStatis>
     */
    List<ReportEduDoStatis> selectList(ReportEduDoStatisQueryForm queryForm);

}
