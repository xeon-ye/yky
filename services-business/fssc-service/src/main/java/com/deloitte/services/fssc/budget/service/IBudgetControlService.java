package com.deloitte.services.fssc.budget.service;


/**
 * 预算控制 Service
 * @author jawjiang
 */
public interface IBudgetControlService {

    /**
     * 检查预算保留
     * @param documentId 单据id
     * @param documentTable 单据头表表名
     * @param warningFlag 是否检查预算超时80%,警告提交
     * @param forbidFlag 是否检查预算超时100%,禁止提交
     * @param updateBudgetFlag 是否更新预算
     * @return
     */
    boolean checkBudgetRemain(Long documentId,String documentTable,boolean warningFlag,
            boolean forbidFlag,boolean updateBudgetFlag);

    /**
     * 检查预算保留,默认带警告,禁止策略,不更新预算信息
     * @param documentId 单据id
     * @param documentTable 单据头表表名
     * @return
     */
    boolean checkBudgetRemain(Long documentId,String documentTable);

    /**
     * 执行预算保留,默认更新预算信息
     * @param documentId 单据id
     * @param documentTable 单据头表表名
     * @param forbidFlag 禁止策略
     * @return
     */
    boolean executeBudgetRemain(Long documentId,String documentTable,boolean warningFlag,boolean forbidFlag);

    /**
     * 审批通过后的处理逻辑,执行预算占用,默认带禁止策略,更新预算信息
     * @param documentId 单据id
     * @param documentTable 单据头表表名
     * @return
     */
    boolean executeBudgetOccupy(Long documentId,String documentTable);


    /**
     * 执行预算保留额度的释放,针对驳回到发起人、撤回操作
     * @param documentId 单据id
     * @param documentTable 单据头表表名
     * @return
     */
    boolean executeBudgetRemainFree(Long documentId,String documentTable);

    /**
     * 预算保留/占用额度的释放,只针对冲销的场景
     * @param documentId
     * @param documentTable
     * @return
     */
    boolean executeBudgetFree(Long documentId,String documentTable);
}
