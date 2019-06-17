package com.deloitte.services.fssc.budget.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.budget.param.BudgetAdvanceBorrowLineQueryForm;
import com.deloitte.services.fssc.budget.entity.BudgetAdvanceBorrowLine;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-12
 * @Description : BudgetAdvanceBorrowLine服务类接口
 * @Modified :
 */
public interface IBudgetAdvanceBorrowLineService extends IService<BudgetAdvanceBorrowLine> {


    /**
     *
     *  根据支出小类id查询预算行数据
     * @param documentId
     * @param documentType
     * @param expenseSubTypeId
     * @return
     */
    BudgetAdvanceBorrowLine getByExpenseSubTypeId(Long documentId, String documentType, Long expenseSubTypeId);


    /**
     *  根据行号查询预算行数据
     * @param documentId
     * @param documentType
     * @param lineNumber
     * @return
     */
    BudgetAdvanceBorrowLine getByLineNumber(Long documentId, String documentType, Long lineNumber);

    /**
     * 获取预算行信息
     * @param documentId
     * @param documentType
     * @return
     */
    List<BudgetAdvanceBorrowLine> selectList(Long documentId, String documentType);

    /**
     * 根据单据删除行信息
     * @param documentId
     * @param documentType
     */
    void deleteByDocument(Long documentId, String documentType);

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BudgetAdvanceBorrowLine>
     */
    IPage<BudgetAdvanceBorrowLine> selectPage(BudgetAdvanceBorrowLineQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BudgetAdvanceBorrowLine>
     */
    List<BudgetAdvanceBorrowLine> selectList(BudgetAdvanceBorrowLineQueryForm queryForm);
}
