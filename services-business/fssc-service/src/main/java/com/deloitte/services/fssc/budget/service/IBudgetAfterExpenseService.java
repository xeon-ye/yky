package com.deloitte.services.fssc.budget.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.budget.param.BudgetAfterExpenseQueryForm;
import com.deloitte.services.fssc.budget.entity.BudgetAfterExpense;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-12
 * @Description : BudgetAfterExpense服务类接口
 * @Modified :
 */
public interface IBudgetAfterExpenseService extends IService<BudgetAfterExpense> {

    /**
     * 根据单据或者预算是否报账信息
     * @param documentId
     * @param documentType
     * @return
     */
    BudgetAfterExpense getByDocument(Long documentId,String documentType);

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BudgetAfterExpense>
     */
    IPage<BudgetAfterExpense> selectPage(BudgetAfterExpenseQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BudgetAfterExpense>
     */
    List<BudgetAfterExpense> selectList(BudgetAfterExpenseQueryForm queryForm);
}
