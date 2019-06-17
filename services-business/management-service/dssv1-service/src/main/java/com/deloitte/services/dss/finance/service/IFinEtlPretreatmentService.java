package com.deloitte.services.dss.finance.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.services.dss.finance.entity.FinEtlPretreatment;

import java.util.List;
import java.util.Map;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-17
 * @Description : FinEtlPretreatment服务类接口
 * @Modified :
 */
public interface IFinEtlPretreatmentService extends IService<FinEtlPretreatment>{

    /**
     * 清除预处理表重复数据
     * @param finEtlPretreatments
     */
    void deleteDate(List<FinEtlPretreatment> finEtlPretreatments);

    /**
     * 收入 来源表 --> 预处理表
     * @param finEtlPretreatment 期间
     * @return
     */
    boolean insertSrcToProIncome(FinEtlPretreatment finEtlPretreatment);

    /**
     * 收入预算  来源表 --> 预处理表
     * @param map
     * @return
     */
    boolean insertScrToProBud(Map map);

    /**
     * 收入预算达成率 来源表 --> 预处理表
     * @param map
     * @return
     */
    boolean insertSrcToProExe(Map map);

    /**
     * 收入同比增长率 来源表 --> 预处理表
     * @param finEtlPretreatment
     * @return
     */
    boolean insertSrcToProRate(FinEtlPretreatment finEtlPretreatment);

    /**
     * 支出预算 来源表 --> 预处理表
     * @param map
     * @return
     */
    boolean insertSrcToProExpBud(Map map);

    /**
     * 项目支出 来源表 --> 预处理表
     * @param finEtlPretreatment
     * @return
     */
    boolean insertSrcToProExp(FinEtlPretreatment finEtlPretreatment);

    /**
     * 支出预算达成率 来源表 --> 预处理表
     * @param map
     * @return
     */
    boolean insertSrcToProExeExp(Map map);

    /**
     * 收入计划增长率 来源表 --> 预处理表
     * @param finEtlPretreatment
     * @return
     */
    boolean insertSrcToProGrowth(FinEtlPretreatment finEtlPretreatment);

    /**
     * 支出同比 来源表 --> 预处理表
     * @param finEtlPretreatment
     * @return
     */
    boolean insertSrcToProExpRate(FinEtlPretreatment finEtlPretreatment);

    /**
     * 支出计划增长率 来源表 --> 预处理表
     * @param finEtlPretreatment
     * @return
     */
    boolean insertSrcToProExpGrowth(FinEtlPretreatment finEtlPretreatment);

    /**
     * 资产 来源表 --> 预处理表
     * @param map
     */
    boolean insertSrcToProLiabilities(Map map);

    /**
     * 支出 来源表 --> 预处理表
     * @param map
     * @return
     */
    boolean insertSrcToProProExp(Map map);

    /**
     * 项目实际支出 来源表 --> 预处理表
     * @param map
     * @return
     */
    boolean insertSrcToProNational(Map map);

    /**
     * 项目支出预算执行率（分子）  来源表 --> 预处理表
     * @param map
     * @return
     */
    boolean insertSrcToProNationalExeN(Map map );

    /**
     * 项目支出预算执行率（分母）  来源表 --> 预处理表
     * @param map
     * @return
     */
    boolean insertSrcToProNationalExeD(Map map);

    /**
     * 各类型项目预算绩效 来源表 --> 预处理表
     * @param map
     * @return
     */
    boolean insertSrcToProComAchive(Map map);

    /**
     * 整体项目预算绩效 来源表 --> 预处理表
     * @param finEtlPretreatment
     * @return
     */
    boolean insertSrcToProAchive(FinEtlPretreatment finEtlPretreatment);

    /**
     * 平均项目预算绩效同比增长 来源表 --> 预处理表
     * @param finEtlPretreatment
     * @return
     */
    boolean insertSrcToProAvgAchive(FinEtlPretreatment finEtlPretreatment);


    /**
     * 平均科研项目预算绩效 来源表 --> 预处理表
     * @param finEtlPretreatment
     * @return
     */
    boolean insertSrcToProAvgSciAchive(FinEtlPretreatment finEtlPretreatment);

    /**
     * 资产负债率与累计折旧率 来源表 --> 预处理表
     * @param map
     * @return
     */
    boolean insertSrcToLiaAndDep(Map map);








    /**
     * 合计并查询预处理表中数据
     * @param finEtlPretreatment
     * @return
     */
    List<FinEtlPretreatment> queryGroupBy(FinEtlPretreatment finEtlPretreatment);

    /**
     * 合计并数据 收入预算执行率
     * @param finEtlPretreatment
     * @return
     */
    List<FinEtlPretreatment> queryGroupByExe(FinEtlPretreatment finEtlPretreatment);

    /**
     * 合计数据 收入预算
     * @param finEtlPretreatment
     * @return
     */
    List<FinEtlPretreatment> queryGroupByIncomeBud(FinEtlPretreatment finEtlPretreatment);

    /**
     * 合计并数据 收入
     * @param finEtlPretreatment
     * @return
     */
    List<FinEtlPretreatment> queryGroupByIncome(FinEtlPretreatment finEtlPretreatment);

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
     * 合并数据 整体项目预算绩效
     * @param finEtlPretreatment
     * @return
     */
    List<FinEtlPretreatment> queryGroupByAchive(FinEtlPretreatment finEtlPretreatment);

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
     * 合并数据 支出计划增长
     * @param finEtlPretreatment
     * @return
     */
    List<FinEtlPretreatment> queryGroupByExpGrowth(FinEtlPretreatment finEtlPretreatment);

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
     * 合并数据 项目支出预算执行率（分子）
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
