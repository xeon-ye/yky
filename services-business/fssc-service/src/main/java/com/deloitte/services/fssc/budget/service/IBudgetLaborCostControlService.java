package com.deloitte.services.fssc.budget.service;

import com.deloitte.services.fssc.business.contract.entity.CrbContractReimburseBill;
import com.deloitte.services.fssc.business.labor.entity.LcLaborCost;
import com.deloitte.services.fssc.eums.FsscEums;

/**
 * 劳务费报账单 预算控制
 * @author jawjiang
 */
public interface IBudgetLaborCostControlService {

    /**
     * 预算控制,包含预算检查,预算保留
     * @param laborCost 劳务费报账单
     * @param warningFlag
     * @param forbidFlag
     * @param updateBudgetFlag
     * @return
     */
    boolean laborCostBudgetControl(LcLaborCost laborCost, boolean warningFlag,
                                   boolean forbidFlag, boolean updateBudgetFlag);

    /**
     * 预算保留转占用
     * @param laborCost 劳务费报账单
     * @param forbidFlag
     * @return
     */
    boolean laborCostRemain2Occupy(LcLaborCost laborCost, boolean forbidFlag);

    /**
     * 预算保留/占用释放
     * @param budgetPhase 预算阶段
     * @param laborCost 劳务费报账单
     * @return
     */
    boolean laborCostBudgetFree(FsscEums budgetPhase, LcLaborCost laborCost);

}
