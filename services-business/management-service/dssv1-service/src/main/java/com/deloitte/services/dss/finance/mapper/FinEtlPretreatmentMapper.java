package com.deloitte.services.dss.finance.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deloitte.services.dss.finance.entity.FinEtlPretreatment;

import java.math.BigDecimal;
import java.util.List;


/**
 * <p>
 * 财务指标预处理表 Mapper 接口
 * </p>
 *
 * @author chitose
 * @since 2019-04-17
 */
public interface FinEtlPretreatmentMapper extends BaseMapper<FinEtlPretreatment> {

    /**
     * 查询指定部门在特定期间的某指标YTD
     * @param finEtlPretreatment
     * @return
     */
    BigDecimal queryYtdByPeriod(FinEtlPretreatment finEtlPretreatment);

    /**
     * 合计并查询预处理表中数据
     * @param finEtlPretreatment
     * @return
     */
    List<FinEtlPretreatment> queryGroupBy(FinEtlPretreatment finEtlPretreatment);

    /**
     * 合计数据 收入预算
     * @param finEtlPretreatment
     * @return
     */
    List<FinEtlPretreatment> queryGroupByIncomeBud(FinEtlPretreatment finEtlPretreatment);

    /**
     * 合计数据 收入预算执行率
     * @param finEtlPretreatment
     * @return
     */
    List<FinEtlPretreatment> queryGroupByExe(FinEtlPretreatment finEtlPretreatment);

    /**
     * 合并数据 收入计划增长
     * @param finEtlPretreatment
     * @return
     */
    List<FinEtlPretreatment> queryGroupByGrowth (FinEtlPretreatment finEtlPretreatment);

    /**
     * 合并数据 支出预算
     * @param finEtlPretreatment
     * @return
     */
    List<FinEtlPretreatment> queryGroupByExpBud (FinEtlPretreatment finEtlPretreatment);

    /**
     * 合计数据 支出
     * @param finEtlPretreatment
     * @return
     */
    List<FinEtlPretreatment> queryGroupByExp(FinEtlPretreatment finEtlPretreatment);

    /**
     * 合并数据 支出预算执行率
     * @param finEtlPretreatment
     * @return
     */
    List<FinEtlPretreatment> queryGroupByExpExe(FinEtlPretreatment finEtlPretreatment);

    /**
     * 合并数据 支出计划增长
     * @param finEtlPretreatment
     * @return
     */
    List<FinEtlPretreatment> queryGroupByExpGrowth(FinEtlPretreatment finEtlPretreatment);

    /**
     * 合并数据 收入
     * @param finEtlPretreatment
     * @return
     */
    List<FinEtlPretreatment> queryGroupByIncome (FinEtlPretreatment finEtlPretreatment);

    /**
     * 清除预处理表重复数据
     * @param finEtlPretreatment
     */
    void deleteDate(FinEtlPretreatment finEtlPretreatment);

    /**
     * 合并数据 整体项目预算绩效
     * @param finEtlPretreatment
     * @return
     */
    List<FinEtlPretreatment> queryGroupByAchive (FinEtlPretreatment finEtlPretreatment);

    /**
     * 合并数据 各类型项目预算绩效
     * @param finEtlPretreatment
     * @return
     */
    List<FinEtlPretreatment> queryGroupByComAchive (FinEtlPretreatment finEtlPretreatment);

    /**
     * 合并数据 平均项目预算绩效同比增长
     * @param finEtlPretreatment
     * @return
     */
    List<FinEtlPretreatment> queryGroupByAvgAchive (FinEtlPretreatment finEtlPretreatment);

    /**
     * 合并数据 平均科研项目预算绩效
     * @param finEtlPretreatment
     * @return
     */
    List<FinEtlPretreatment> queryGroupByAvgSciAchive (FinEtlPretreatment finEtlPretreatment);

    /**
     * 合并数据 收入同比
     * @param finEtlPretreatment
     * @return
     */
    List<FinEtlPretreatment> queryGroupByIncomeRate (FinEtlPretreatment finEtlPretreatment);

    /**
     * 合并数据 支出同比
     * @param finEtlPretreatment
     * @return
     */
    List<FinEtlPretreatment> queryGroupByExpRate (FinEtlPretreatment finEtlPretreatment);

    /**
     * 合并数据 资产总额
     * @param finEtlPretreatment
     * @return
     */
    List<FinEtlPretreatment> queryGroupByLiab (FinEtlPretreatment finEtlPretreatment);

    /**
     * 合并数据 项目支出预算
     * @param finEtlPretreatment
     * @return
     */
    List<FinEtlPretreatment> queryGroupByProExpBud (FinEtlPretreatment finEtlPretreatment);

    /**
     * 合并数据 项目实际支出
     * @param finEtlPretreatment
     * @return
     */
    List<FinEtlPretreatment> queryGroupByProExp (FinEtlPretreatment finEtlPretreatment);

    /**
     * 合并数据 项目支出预算执行率
     * @param finEtlPretreatment
     * @return
     */
    List<FinEtlPretreatment> queryGroupByProExpBudExe (FinEtlPretreatment finEtlPretreatment);

    /**
     * 合并数据 项目支出预算执行率（分母）
     * @param finEtlPretreatment
     * @return
     */
    List<FinEtlPretreatment> queryGroupByProExpBudExeD (FinEtlPretreatment finEtlPretreatment);

    /**
     * 合并数据 资产负债率与累计折旧率
     * @param finEtlPretreatment
     * @return
     */
    List<FinEtlPretreatment> queryGroupByLiaAndDep (FinEtlPretreatment finEtlPretreatment);
}
