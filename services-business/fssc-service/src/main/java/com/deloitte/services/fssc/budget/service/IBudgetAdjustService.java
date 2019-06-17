package com.deloitte.services.fssc.budget.service;

public interface IBudgetAdjustService {

    /**
     * 调整前-预处理
     * @param documentId
     * @param documentTable
     * @return
     */
    boolean preAdjust(Long documentId,String documentTable);

    /**
     * 审批完成,执行调整
     * @param documentId
     * @param documentTable
     * @return
     */
    boolean toAdjust(Long documentId,String documentTable);

    /**
     * 取消调整
     * @param documentId
     * @param documentTable
     * @return
     */
    boolean unAdjust(Long documentId,String documentTable);
}
