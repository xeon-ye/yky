package com.deloitte.services.fssc.budget.service;

import com.deloitte.services.fssc.business.advance.entity.AdvancePaymentInfo;
import com.deloitte.services.fssc.eums.FsscEums;

/**
 * 对公预付款 预算控制
 *
 * @author jawjiang
 */
public interface IBudgetAdvanceControlService {

    /**
     * 
     */
    boolean advanceBudgetControl(AdvancePaymentInfo advancePaymentInfo,
            boolean warningFlag, boolean forbidFlag, boolean updateBudgetFlag);

    /**
     * 预算保留转占用
     * @param advancePaymentInfo
     * @param forbidFlag
     * @return
     */
    boolean advanceRemain2Occupy(AdvancePaymentInfo advancePaymentInfo,boolean forbidFlag);


    /**
     * 预算 保留/占用释放
     * @param budgetPhase
     * @param advancePaymentInfo
     */
    boolean advanceBudgetFree(FsscEums budgetPhase,AdvancePaymentInfo advancePaymentInfo);


}
