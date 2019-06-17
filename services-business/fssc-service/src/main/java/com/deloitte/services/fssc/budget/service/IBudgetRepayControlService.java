package com.deloitte.services.fssc.budget.service;

import com.deloitte.services.fssc.business.poi.entity.PepaymentOrderInfo;
import com.deloitte.services.fssc.eums.FsscEums;

/**
 * 还款单的预算处理
 */
public interface IBudgetRepayControlService {
    /**
     * 预算控制,实际操作是减少借款单的可用预算保量金额
     * @param repayOrderInfo 还款单
     * @param warningFlag
     * @param forbidFlag
     * @param updateBudgetFlag
     * @return
     */
    boolean repayBudgetControl(PepaymentOrderInfo repayOrderInfo, boolean warningFlag,
                                       boolean forbidFlag, boolean updateBudgetFlag);

    /**
     * 预算保留转占用,实际操作是还原科目上的预算保量金额
     * @param repayOrderInfo 还款单
     * @param forbidFlag
     * @return
     */
    boolean repayRemain2Occupy(PepaymentOrderInfo repayOrderInfo,boolean forbidFlag);

    /**
     * 预算保留/占用释放,实际操作是还原借款单上的可核销金额或科目上的预算保量金额
     * @param budgetPhase 预算阶段
     * @param repayOrderInfo 还款单
     * @return
     */
    boolean repayBudgetFree(FsscEums budgetPhase, PepaymentOrderInfo repayOrderInfo);
}
