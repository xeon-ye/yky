package com.deloitte.services.fssc.business.ppc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.ppc.param.ProjectPaymentLineDetaiQueryForm;
import com.deloitte.services.fssc.business.ppc.entity.ProjectPaymentLineDetai;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-01
 * @Description : ProjectPaymentLineDetai服务类接口
 * @Modified :
 */
public interface IProjectPaymentLineDetaiService extends IService<ProjectPaymentLineDetai> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<ProjectPaymentLineDetai>
     */
    IPage<ProjectPaymentLineDetai> selectPage(ProjectPaymentLineDetaiQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<ProjectPaymentLineDetai>
     */
    List<ProjectPaymentLineDetai> selectList(ProjectPaymentLineDetaiQueryForm queryForm);

    /**
     * 收入小类是否被关联
     * @param incomeSubTypeIds
     * @return
     */
    boolean existsByReceivePayment(List<Long> incomeSubTypeIds);
}
