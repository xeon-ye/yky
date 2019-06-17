package com.deloitte.services.fssc.business.ppc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.ppc.param.ProjectConfirmationQueryForm;
import com.deloitte.services.fssc.business.ppc.entity.ProjectConfirmation;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-01
 * @Description : ProjectConfirmation服务类接口
 * @Modified :
 */
public interface IProjectConfirmationService extends IService<ProjectConfirmation> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<ProjectConfirmation>
     */
    IPage<ProjectConfirmation> selectPage(ProjectConfirmationQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<ProjectConfirmation>
     */
    List<ProjectConfirmation> selectList(ProjectConfirmationQueryForm queryForm);

    /**
     * 收入大类是否被关联
     * @param incomeMainTypeIds
     * @return
     */
    boolean existsByReceivePayment(List<Long> incomeMainTypeIds);
}
