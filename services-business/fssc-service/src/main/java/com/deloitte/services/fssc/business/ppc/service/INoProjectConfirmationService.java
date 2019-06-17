package com.deloitte.services.fssc.business.ppc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.ppc.param.NoProjectConfirmationQueryForm;
import com.deloitte.services.fssc.business.ppc.entity.NoProjectConfirmation;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-01
 * @Description : NoProjectConfirmation服务类接口
 * @Modified :
 */
public interface INoProjectConfirmationService extends IService<NoProjectConfirmation> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<NoProjectConfirmation>
     */
    IPage<NoProjectConfirmation> selectPage(NoProjectConfirmationQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<NoProjectConfirmation>
     */
    List<NoProjectConfirmation> selectList(NoProjectConfirmationQueryForm queryForm);

    /**
     * 收入大类是否被关联
     * @param incomeMainTypeIds
     * @return
     */
    boolean existsByReceivePayment(List<Long> incomeMainTypeIds);
}
