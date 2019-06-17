package com.deloitte.services.dss.finance.mapper;

import com.deloitte.platform.api.dss.finance.vo.IncomeBudgetVo;

import java.util.List;
import java.util.Map;


public interface IncomeBudgetExecutionMapper {

    /**
     * 支出预算执行率 折线图
     * @param map
     * @return
     */
    List<IncomeBudgetVo> expBudExeMonth(Map map);

    /**
     * 支出同比增长 折线图
     * @param map
     * @return
     */
    List<IncomeBudgetVo> expRateMonth(Map map);

    /**
     * 收入预算达成率 折线图
     * @param map
     * @return
     */
    List<IncomeBudgetVo> queryIncomeBudExe(Map map);

    /**
     * 收入同比增长 折线图
     * @param map
     * @return
     */
    List<IncomeBudgetVo> queryIncomeRate(Map map);
    /**
     * 机构收入预算执行率 X坐标
     * @param map
     * @return
     */
    List<IncomeBudgetVo> queryComIncomeBudExe(Map map);
    /**
     * 机构收入 圆点数据
     * @param map
     * @return
     */
    List<IncomeBudgetVo> queryComIncome(Map map);

    /**
     * 机构收入预算 圆点数据
     * @param map
     * @return
     */
    List<IncomeBudgetVo> queryComIncomeBud(Map map);

    /**
     * 收入类型预算执行率 X坐标
     * @param map
     * @return
     */
    List<IncomeBudgetVo> queryTypeIncomeBudExe(Map map);

    /**
     * 整体支出预算执行率 虚线
     * @param map
     * @return
     */
    List<IncomeBudgetVo> queryTotalExpExe(Map map);
    /**
     * 支出预算执行率 柱状图
     * @param map
     * @return
     */
    List<IncomeBudgetVo> queryComExpBudExe(Map map);
    /**
     * 机构支出同比增长 Y坐标
     * @param map
     * @return
     */
    List<IncomeBudgetVo> queryComExpRate(Map map);

    /**
     * 机构支出计划增长 X坐标
     * @param map
     * @return
     */
    List<IncomeBudgetVo> queryComExpGrowth(Map map);
    /**
     * 机构支出 圆点数据
     * @param map
     * @return
     */
    List<IncomeBudgetVo> queryComExp(Map map);
    /**
     * 项目支出同比增长 Y坐标
     * @param map
     * @return
     */
    List<IncomeBudgetVo> queryProExpRate(Map map);
    /**
     * 支出类型同比增长 Y坐标
     * @param map
     * @return
     */
    List<IncomeBudgetVo> queryExpTypeRate(Map map);

    /**
     * 支出类型支出计划增长 X坐标
     * @param map
     * @return
     */
    List<IncomeBudgetVo> queryExpTypeGrowthRate(Map map);
    /**
     * 支出类型支出 圆点数据
     * @param map
     * @return
     */
    List<IncomeBudgetVo> queryExpTypeExp(Map map);


}
