package com.deloitte.services.dss.finance.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.dss.finance.vo.AcceptVo;
import com.deloitte.platform.api.dss.finance.vo.FinanceExpenditureVo;
import com.deloitte.platform.api.dss.finance.vo.IncomeBudgetVo;

import java.util.List;
import java.util.Map;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-23
 * @Description : Expenditrue服务类接口
 * @Modified :
 */
public interface IFinExpenditureSerivice extends IService {

    /**
     * 查询 总支出
     * @return
     */
    List<IncomeBudgetVo> selectTotalExp(Map map);

    /**
     * 查询部门支出
     * @param myData
     * @return
     */
    List<List<IncomeBudgetVo>> selectComExp (AcceptVo myData);

    /**
     * 截止当月总支出数据
     * @param map
     * @return
     */
    List<IncomeBudgetVo> selectExpMonth (Map map);

    /**
     * 截止各部门当月总支出数据
     * @param myData
     * @return
     */
    List<List<IncomeBudgetVo>> selectComExpMonth (AcceptVo myData);





    /**
     * 支出分析 - 当月支出
     * @param map
     * @return
     */
    List<IncomeBudgetVo> selectExpPerMonth (Map map);

    /**
     * 支出分析 - 累计支出 柱状图
     * @param map
     * @return
     */
    List<IncomeBudgetVo> expComPerMonth(Map map);

    /**
     * 支出同比增长 折线图
     * @param map
     * @return
     */
    List<IncomeBudgetVo> selectExpComRate(Map map);

    /**
     * 机构支出同比增长 y坐标
     * @param map
     * @return
     */
    List<IncomeBudgetVo> selectExpComRateY(Map map);

    /**
     * 机构支出计划增长 X坐标
     * @param map
     * @return
     */
    List<IncomeBudgetVo> selectExpComGrowthX(Map map);

    /**
     * 支出 圆点面积
     * @param map
     * @return
     */
    List<IncomeBudgetVo> selectExpCarcle(Map map);

    //获取本年一月到当前月
    String selectMonth(Map map);
}
