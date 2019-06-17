package com.deloitte.services.dss.finance.service;

import com.deloitte.platform.api.dss.finance.vo.AcceptVo;
import com.deloitte.platform.api.dss.finance.vo.IncomeBudgetVo;

import java.util.List;
import java.util.Map;


/**
 * @Author : chitose
 * @Date : Create in 2019-04-17
 * @Description : FinRate服务类接口
 * @Modified :
 */
public interface IFinRateService {

    /**
     * 查询总收入同比
     * @param map
     * @return
     */
    List<IncomeBudgetVo> selectRate(Map map);

    /**
     * 查询各机构 收入同比
     * @param map
     * @return
     */
    List<IncomeBudgetVo> selectComRate(Map map);

    /**
     * 查询总支出同比
     * @param map
     * @return
     */
    List<IncomeBudgetVo> selectExpRate(Map map);

    /**
     * 查询各机构 支出同比
     * @param map
     * @return
     */
    List<IncomeBudgetVo> selectComExpRate(Map map);

    /**
     * 查询总月 收入同比增长
     * @param map
     * @return
     */
    List<IncomeBudgetVo> selectRateMonth(Map map);

    /**
     * 查询各部门月总收入同比增长
     * @param map
     * @return
     */
    List<IncomeBudgetVo> selectComRateMonth(Map map);

    /**
     * 查询各个机构年总收入同比
     * @param map
     * @return
     */
    List<IncomeBudgetVo> selectComYearRate(Map map);

}
