package com.deloitte.services.fssc.budget.service;

import com.deloitte.services.fssc.business.contract.entity.CrbContractReimburseBill;
import com.deloitte.services.fssc.eums.FsscEums;

/**
 * 合同报账单 预算控制
 * @author jawjiang
 */
public interface IBudgetContractControlService {

    /**
     * 预算控制,包含预算检查,预算保留
     * @param contractReimburseBill 合同报账单
     * @param warningFlag
     * @param forbidFlag
     * @param updateBudgetFlag
     * @return
     */
    boolean contractBudgetControl(CrbContractReimburseBill contractReimburseBill, boolean warningFlag,
                                  boolean forbidFlag, boolean updateBudgetFlag);

    /**
     * 预算保留转占用
     * @param contractReimburseBill 合同报账单
     * @param forbidFlag
     * @return
     */
    boolean contractRemain2Occupy(CrbContractReimburseBill contractReimburseBill, boolean forbidFlag);

    /**
     * 预算保留/占用释放
     * @param budgetPhase 预算阶段
     * @param contractReimburseBill 合同报账单
     * @return
     */
    boolean contractBudgetFree(FsscEums budgetPhase, CrbContractReimburseBill contractReimburseBill);

}
