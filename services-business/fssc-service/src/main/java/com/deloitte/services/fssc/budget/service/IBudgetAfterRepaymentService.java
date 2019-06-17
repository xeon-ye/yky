package com.deloitte.services.fssc.budget.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.budget.param.BudgetAfterRepaymentQueryForm;
import com.deloitte.services.fssc.budget.entity.BudgetAfterRepayment;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-05-28
 * @Description : BudgetAfterRepayment服务类接口
 * @Modified :
 */
public interface IBudgetAfterRepaymentService extends IService<BudgetAfterRepayment> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BudgetAfterRepayment>
     */
    IPage<BudgetAfterRepayment> selectPage(BudgetAfterRepaymentQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BudgetAfterRepayment>
     */
    List<BudgetAfterRepayment> selectList(BudgetAfterRepaymentQueryForm queryForm);

    /**
     *
     * @param documentId
     * @param documentType
     * @return
     */
    BudgetAfterRepayment getByDocument(Long documentId,String documentType);
}
