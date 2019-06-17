package com.deloitte.services.fssc.budget.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.budget.param.BudgetBasicBudgetQueryForm;
import com.deloitte.services.fssc.budget.entity.BudgetAmount;
import com.deloitte.services.fssc.budget.entity.BudgetBasicBudget;
import com.baomidou.mybatisplus.extension.service.IService;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-20
 * @Description : BudgetBasicBudget服务类接口
 * @Modified :
 */
public interface IBudgetBasicBudgetService extends IService<BudgetBasicBudget> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BudgetBasicBudget>
     */
    IPage<BudgetBasicBudget> selectPage(BudgetBasicBudgetQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BudgetBasicBudget>
     */
    List<BudgetBasicBudget> selectList(BudgetBasicBudgetQueryForm queryForm);

    /**
     * 获取基础预算
     * @param unitCode 组织单位ID
     * @param deptCode 部门(暂时默认是二级处室)
     * @param budgetAccountCode 预算科目ID
     * @param budgetAnnual 预算年度
     * @return BudgetBasicBudget
     */
    BudgetBasicBudget selectByKeyWord(String unitCode, String deptCode,
            String budgetAccountCode, String budgetAnnual);

    /**
     * 获取基础预算
     * @param unitId 单位编码
     * @param deptCode 部门(暂时默认是二级处室)
     * @param budgetAccountCode 预算科目编码
     * @param budgetAnnual 预算年度
     * @return
     * @deprecated
     */
    BudgetBasicBudget selectByKeyWord(Long unitId, String deptCode,
            String budgetAccountCode, String budgetAnnual);

    /**
     * 获取月预算数据
     * @param unitCode
     * @param deptCode
     * @param budgetAccountCode
     * @param budgetAnnual
     * @return
     */
    BudgetBasicBudget selectMonthByKeyWord(String unitCode, String deptCode,
            String budgetAccountCode, String budgetAnnual,String budgetPeriod);

    /**
     * 根据单位和处室统计预算总额
     * @param unitCode 单位编码
     * @param officeCode 处室编码
     * @param budgetAnnual 预算年度
     * @return
     */
    BudgetBasicBudget selectByUnitOffice(String unitCode,String officeCode,String budgetAnnual);

    /**
     *  根据单位和处室统计每个科目的预算信息
     * @param unitCode
     * @param officeCode
     * @param budgetAnnual
     * @return
     */
    List<BudgetBasicBudget> selectAccountByUnitOffice(String unitCode,String officeCode,String budgetAnnual);

    /**
     * 更新基本预算
     * @param newRemainAmount
     * @param newOccupyAmount
     * @param newUsableAmount
     * @param basicBudget
     */
    void updateBasicBudget(BigDecimal newRemainAmount, BigDecimal newOccupyAmount,
            BigDecimal newUsableAmount, BudgetBasicBudget basicBudget);

    /**
     * 更新基本预算
     * @param budgetAmount
     * @param basicBudget
     */
    void updateBasicBudget(BudgetAmount budgetAmount, BudgetBasicBudget basicBudget);

    /**
     * 根据预算科目编码Ids查询数量
     * @param ids
     * @return
     */
    Integer countByBudgetAccountIds(List<Long> ids);
}
