package com.deloitte.services.dss.finance.service;

import com.deloitte.services.dss.finance.entity.FinEtlPretreatment;

import java.util.List;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-17
 * @Description : FinEtlFact服务类接口
 * @Modified :
 */
public interface IFinEtlFactService {

    /**
     * 删除重复数据
     * @param finEtlPretreatments
     */
    void deleteData(List<FinEtlPretreatment> finEtlPretreatments);

    /**
     * 预处理表 --> 事实表
     * @param finEtlPretreatment
     * @return
     */
    void insertProToFact(FinEtlPretreatment finEtlPretreatment);

    /**
     * 收入 预处理表 --> 事实表
     * @param finEtlPretreatment
     * @return
     */
    void insertProToFactIncome(FinEtlPretreatment finEtlPretreatment);

    /**
     * 收入预算 预处理表 --> 事实表
     * @param finEtlPretreatment
     * @return
     */
    void insertProToFactIncomeBud(FinEtlPretreatment finEtlPretreatment);

    /**
     * 收入预算执行率 预处理表 --> 事实表
     * @return
     */
    void insertProToFactExe(FinEtlPretreatment finEtlPretreatment);

    /**
     * 收入计划增长 预处理表 --> 事实表
     * @param finEtlPretreatment
     * @return
     */
    void insertProToFactGrowth(FinEtlPretreatment finEtlPretreatment);

    /**
     * 支出预算 预处理表 --> 事实表
     * @param finEtlPretreatment
     */
    void insertProToFactExpBud(FinEtlPretreatment finEtlPretreatment);

    /**
     * 支出 预处理表 --> 事实表
     * @param finEtlPretreatment
     */
    void insertProToFactExp(FinEtlPretreatment finEtlPretreatment);

    /**
     * 支出预算执行率 预处理表 --> 事实表
     * @param finEtlPretreatment
     */
    void insertProToFactExpExe(FinEtlPretreatment finEtlPretreatment);

    /**
     * 整体项目预算绩效 预处理表 --> 事实表
     * @param finEtlPretreatment
     */
    void insertProToFactAchive(FinEtlPretreatment finEtlPretreatment);

    /**
     * 各类型项目预算绩效 预处理表 --> 事实表
     * @param finEtlPretreatment
     */
    void insertProToFactComAchive(FinEtlPretreatment finEtlPretreatment);

    /**
     * 平均项目预算绩效同比增长 预处理表 --> 事实表
     * @param finEtlPretreatment
     */
    void insertProToFactAvgAchive(FinEtlPretreatment finEtlPretreatment);

    /**
     * 平均科研项目预算绩效 预处理表 --> 事实表
     * @param finEtlPretreatment
     */
    void insertProToFactAvgSciAchive(FinEtlPretreatment finEtlPretreatment);

    /**
     * 收入同比 预处理表 --> 事实表
     * @param finEtlPretreatment
     */
    void insertProToFactIncomeRate(FinEtlPretreatment finEtlPretreatment);

    /**
     * 支出同比 预处理表 --> 事实表
     * @param finEtlPretreatment
     */
    void insertProToFactExpRate(FinEtlPretreatment finEtlPretreatment);

    /**
     * 支出计划增长率 预处理表 --> 事实表
     * @param finEtlPretreatment
     */
    void insertProToFactExpGrowth(FinEtlPretreatment finEtlPretreatment);

    /**
     * 资产总额 预处理表 --> 事实表
     * @param finEtlPretreatment
     */
    void insertProToFactLiab(FinEtlPretreatment finEtlPretreatment);

    /**
     * 项目支出预算 预处理表 --> 事实表
     * @param finEtlPretreatment
     */
    void insertProToFactProExpBud(FinEtlPretreatment finEtlPretreatment);

    /**
     * 项目支出预算 预处理表 --> 事实表
     * @param finEtlPretreatment
     */
    void insertProToFactProExp(FinEtlPretreatment finEtlPretreatment);

    /**
     * 项目支出预算执行率 预处理表 --> 事实表
     * @param finEtlPretreatment
     */
    void insertProToFactProExpBudExe(FinEtlPretreatment finEtlPretreatment);

    /**
     * 项目支出预算执行率（分母) 预处理表 --> 事实表
     * @param finEtlPretreatment
     */
    void insertProToFactProExpBudExeD(FinEtlPretreatment finEtlPretreatment);

    /**
     * 资产负债率与累计折旧率 预处理表 --> 事实表
     * @param finEtlPretreatment
     */
    void insertProToFactLiaAndDep(FinEtlPretreatment finEtlPretreatment);

}
