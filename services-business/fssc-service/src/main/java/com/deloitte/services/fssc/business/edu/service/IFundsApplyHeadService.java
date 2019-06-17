package com.deloitte.services.fssc.business.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.fssc.edu.param.FundsApplyHeadQueryForm;
import com.deloitte.services.fssc.business.edu.entity.FundsApplyHead;

import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-29
 * @Description : FundsApplyHead服务类接口
 * @Modified :
 */
public interface IFundsApplyHeadService extends IService<FundsApplyHead> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<FundsApplyHead>
     */
    IPage<FundsApplyHead> selectPage(FundsApplyHeadQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<FundsApplyHead>
     */
    List<FundsApplyHead> selectList(FundsApplyHeadQueryForm queryForm);


    void modifyAmount(  boolean isSubmit, Long documentId,String documentType);
}
