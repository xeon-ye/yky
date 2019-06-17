package com.deloitte.services.fssc.budget.service;

import com.deloitte.services.fssc.business.travle.entity.TasTravelApplyInfo;
import com.deloitte.services.fssc.business.travle.entity.TasTravelReimburse;
import com.deloitte.services.fssc.eums.FsscEums;

/**
 * 差旅申请 预算控制
 * @author jawjiang
 */
public interface IBudgetTravelExpenseControlService {

    /**
     * 预算控制,包含预算检查,预算保留
     * @param travelReimburse 差旅报账单
     * @param warningFlag
     * @param forbidFlag
     * @param updateBudgetFlag
     * @return
     */
    boolean travelExpenseBudgetControl(TasTravelReimburse travelReimburse, boolean warningFlag,
                                       boolean forbidFlag, boolean updateBudgetFlag);

    /**
     * 预算保留转占用
     * @param travelReimburse 差旅报账单
     * @param forbidFlag
     * @return
     */
    boolean travelExpenseRemain2Occupy(TasTravelReimburse travelReimburse, boolean forbidFlag);

    /**
     * 预算保留/占用释放
     * 流程中保留额度全部释放,撤销只释放可用的预算额度,已使用的额度不受影响
     * @param budgetPhase 预算阶段
     * @param travelReimburse 差旅报账单
     * @return
     */
    boolean travelExpenseBudgetFree(FsscEums budgetPhase,TasTravelReimburse travelReimburse);

}
