package com.deloitte.services.fssc.budget.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.budget.param.BudgetAdvanceBorrowQueryForm;
import com.deloitte.services.fssc.budget.entity.BudgetAdvanceBorrow;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-12
 * @Description : BudgetAdvanceBorrow服务类接口
 * @Modified :
 */
public interface IBudgetAdvanceBorrowService extends IService<BudgetAdvanceBorrow> {


    /**
     * 根据单据获取预算信息
     * @param documentId
     * @param documentType
     * @return
     */
    BudgetAdvanceBorrow getByDocument(Long documentId,String documentType);

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BudgetAdvanceBorrow>
     */
    IPage<BudgetAdvanceBorrow> selectPage(BudgetAdvanceBorrowQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BudgetAdvanceBorrow>
     */
    List<BudgetAdvanceBorrow> selectList(BudgetAdvanceBorrowQueryForm queryForm);
}
