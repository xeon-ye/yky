package com.deloitte.services.fssc.budget.service;

import com.deloitte.services.fssc.business.borrow.entity.BorrowMoneyInfo;
import com.deloitte.services.fssc.eums.FsscEums;

/**
 * 借款单 预算控制
 * @author jawjiang
 */
public interface IBudgetBorrowControlService {

    /**
     * 预算控制,包含预算检查,预算保留
     * @param borrowMoneyInfo
     * @param warningFlag
     * @param forbidFlag
     * @param updateBudgetFlag
     * @return
     */
    boolean borrowBudgetControl(BorrowMoneyInfo borrowMoneyInfo, boolean warningFlag,
            boolean forbidFlag, boolean updateBudgetFlag);

    /**
     * 预算保留转占用
     * @param borrowMoneyInfo
     * @param forbidFlag
     * @return
     */
    boolean borrowRemain2Occupy(BorrowMoneyInfo borrowMoneyInfo,boolean forbidFlag);

    /**
     * 预算保留/占用释放
     * @param budgetPhase 预算阶段
     * @param borrowMoneyInfo 借款单
     * @return
     */
    boolean borrowBudgetFree(FsscEums budgetPhase,BorrowMoneyInfo borrowMoneyInfo);


}
