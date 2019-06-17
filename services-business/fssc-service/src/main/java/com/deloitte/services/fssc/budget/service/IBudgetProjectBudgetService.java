package com.deloitte.services.fssc.budget.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.budget.param.BudgetProjectBudgetQueryForm;
import com.deloitte.platform.api.fssc.budget.vo.BudgetProjectBudgetVo;
import com.deloitte.services.fssc.budget.entity.BudgetAmount;
import com.deloitte.services.fssc.budget.entity.BudgetProjectBudget;
import com.baomidou.mybatisplus.extension.service.IService;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-20
 * @Description : BudgetProjectBudget服务类接口
 * @Modified :
 */
public interface IBudgetProjectBudgetService extends IService<BudgetProjectBudget> {

    /**
     * 分页查询
     * @param queryForm
     * @return IPage<BudgetProjectBudget>
     */
    IPage<BudgetProjectBudget> selectPage(BudgetProjectBudgetQueryForm queryForm);

    /**
     * 条件查询
     * @param queryForm
     * @return List<BudgetProjectBudget>
     */
    List<BudgetProjectBudget> selectList(BudgetProjectBudgetQueryForm queryForm);

    /**
     * 查询
     * @param queryForm
     * @return
     */
    List<BudgetProjectBudgetVo> selectVoList(BudgetProjectBudgetQueryForm queryForm);

    /**
     *  根据关键字查询项目预算信息
     * @param unitCode 组织单位编码
     * @param budgetProjectId 预算项目id(对应最小颗粒度的任务)
     * @param budgetAccountCode 预算科目编码
     * @param budgetAnnual 预算年度
     * @return
     */
    BudgetProjectBudget selectByKeyWord(String unitCode, Long budgetProjectId,
            String budgetAccountCode, String budgetAnnual);

    /**
     *  根据关键字查询项目预算信息
     * @param unitCode 组织单位编码
     * @param taskCode 任务编码
     * @param budgetAccountCode 预算科目编码
     * @param budgetAnnual 预算年度
     * @return
     */
    BudgetProjectBudget selectByKeyWord(String unitCode,String projectCode ,String taskCode,
            String budgetAccountCode, String budgetAnnual);


    /**
     *  根据关键字查询项目预算信息
     * @param unitId 组织单位id
     * @param budgetProjectId 预算项目ID
     * @param budgetAccountCode 预算科目编码
     * @param budgetAnnual 预算年度
     * @return
     * @deprecated
     */
    BudgetProjectBudget selectByKeyWord(Long unitId, Long budgetProjectId,
            String budgetAccountCode, String budgetAnnual);


    /**
     * 查询某个会计期间的预算
     * @param unitCode 单位
     * @param taskCode 任务编码
     * @param budgetAccountCode 预算科目编码
     * @param budgetAnnual 年度
     * @param budgetPeriod 月份
     * @return
     */
    BudgetProjectBudget selectMonthByKeyWord(String unitCode, String projectCode ,String taskCode,
            String budgetAccountCode, String budgetAnnual,String budgetPeriod);
    /**
     * 更新项目预算
     * @param newRemainAmount
     * @param newOccupyAmount
     * @param newUsableAmount
     * @param projectBudget
     */
    void updateProjectBudget(BigDecimal newRemainAmount, BigDecimal newOccupyAmount,
            BigDecimal newUsableAmount, BudgetProjectBudget projectBudget) ;

    /**
     * 更新项目预算
     * @param budgetAmount
     * @param projectBudget
     */
    void updateProjectBudget(BudgetAmount budgetAmount, BudgetProjectBudget projectBudget) ;

    /**
     * 根据任务和预算年度查询预算汇总数据
     * @param projectCode
     * @param taskCode
     * @param budgetAnnual
     * @return
     */
    BudgetProjectBudget getSummaryByTask(String projectCode,String taskCode,String budgetAnnual);

    /**
     *
     * @param projectCode
     * @param taskCode
     * @param budgetAnnual
     * @return
     */
    List<BudgetProjectBudgetVo> getSummaryAccountByTaskCode(String projectCode, String taskCode, String budgetAnnual);

    /**
     * 根据预算科目编码Ids查询数量
     * @param ids
     * @return
     */
    Integer countByBudgetAccountIds(List<Long> ids);
}
