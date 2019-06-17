package com.deloitte.services.fssc.budget.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.budget.param.BudgetAfterExpenseLineQueryForm;
import com.deloitte.services.fssc.budget.entity.BudgetAfterExpenseLine;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-12
 * @Description : BudgetAfterExpenseLine服务类接口
 * @Modified :
 */
public interface IBudgetAfterExpenseLineService extends IService<BudgetAfterExpenseLine> {

    /**
     * 根据单据获取事后报账行预算信息
     * @param documentId
     * @param documentType
     * @return
     */
    List<BudgetAfterExpenseLine> listByDocument(Long documentId,String documentType);

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BudgetAfterExpenseLine>
     */
    IPage<BudgetAfterExpenseLine> selectPage(BudgetAfterExpenseLineQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BudgetAfterExpenseLine>
     */
    List<BudgetAfterExpenseLine> selectList(BudgetAfterExpenseLineQueryForm queryForm);
}
