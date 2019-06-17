package com.deloitte.services.fssc.budget.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.budget.param.BudgetAfterRepaymentLineQueryForm;
import com.deloitte.services.fssc.budget.entity.BudgetAfterRepaymentLine;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-05-28
 * @Description : BudgetAfterRepaymentLine服务类接口
 * @Modified :
 */
public interface IBudgetAfterRepaymentLineService extends IService<BudgetAfterRepaymentLine> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BudgetAfterRepaymentLine>
     */
    IPage<BudgetAfterRepaymentLine> selectPage(BudgetAfterRepaymentLineQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BudgetAfterRepaymentLine>
     */
    List<BudgetAfterRepaymentLine> selectList(BudgetAfterRepaymentLineQueryForm queryForm);

    /**
     * 根据还款单查询 预算-事后还款的行信息
     * @param documentId
     * @param documentType
     * @return
     */
    List<BudgetAfterRepaymentLine> listByDocument(Long documentId, String documentType);
}
