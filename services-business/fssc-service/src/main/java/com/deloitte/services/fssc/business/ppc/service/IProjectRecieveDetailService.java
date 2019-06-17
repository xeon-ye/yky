package com.deloitte.services.fssc.business.ppc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.ppc.param.ProjectRecieveDetailQueryForm;
import com.deloitte.services.fssc.business.ppc.entity.ProjectRecieveDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-01
 * @Description : ProjectRecieveDetail服务类接口
 * @Modified :
 */
public interface IProjectRecieveDetailService extends IService<ProjectRecieveDetail> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<ProjectRecieveDetail>
     */
    IPage<ProjectRecieveDetail> selectPage(ProjectRecieveDetailQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<ProjectRecieveDetail>
     */
    List<ProjectRecieveDetail> selectList(ProjectRecieveDetailQueryForm queryForm);

    /**
     * 收入小类是否被关联
     * @param incomeSubTypeIds
     * @return
     */
    boolean existsByReceivePayment(List<Long> incomeSubTypeIds);
}
