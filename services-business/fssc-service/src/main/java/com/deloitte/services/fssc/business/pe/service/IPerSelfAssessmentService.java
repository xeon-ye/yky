package com.deloitte.services.fssc.business.pe.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.pe.param.PerSelfAssessmentQueryForm;
import com.deloitte.services.fssc.business.pe.entity.PerSelfAssessment;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-05-10
 * @Description : PerSelfAssessment服务类接口
 * @Modified :
 */
public interface IPerSelfAssessmentService extends IService<PerSelfAssessment> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<PerSelfAssessment>
     */
    IPage<PerSelfAssessment> selectPage(PerSelfAssessmentQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<PerSelfAssessment>
     */
    List<PerSelfAssessment> selectList(PerSelfAssessmentQueryForm queryForm);
}
