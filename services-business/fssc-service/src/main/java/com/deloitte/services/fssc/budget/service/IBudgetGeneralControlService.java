package com.deloitte.services.fssc.budget.service;

import com.deloitte.services.fssc.business.general.entity.GeGeneralExpense;
import com.deloitte.services.fssc.eums.FsscEums;

/**
 * 通用报账单 预算控制
 * @author jawjiang
 */
public interface IBudgetGeneralControlService {

    /**
     * 预算控制,包含预算检查,预算保留
     * @param generalExpense 通用报账单
     * @param warningFlag
     * @param forbidFlag
     * @param updateBudgetFlag
     * @return
     */
    boolean generalBudgetControl(GeGeneralExpense generalExpense, boolean warningFlag,
            boolean forbidFlag, boolean updateBudgetFlag);

    /**
     * 预算保留转占用
     * @param generalExpense 通用报账单
     * @param forbidFlag
     * @return
     */
    boolean generalRemain2Occupy(GeGeneralExpense generalExpense,boolean forbidFlag);

    /**
     * 预算保留/占用释放
     * @param budgetPhase 预算阶段
     * @param generalExpense 通用报账单
     * @return
     */
    boolean generalBudgetFree(FsscEums budgetPhase,GeGeneralExpense generalExpense);

}
