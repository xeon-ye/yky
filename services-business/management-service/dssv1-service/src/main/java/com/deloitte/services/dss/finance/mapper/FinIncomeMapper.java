package com.deloitte.services.dss.finance.mapper;

import com.deloitte.platform.api.dss.finance.vo.IncomeBudgetVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 收入 Mapper 接口
 * </p>
 *
 * @author chitose
 * @since 2019-04-09
 */
public interface FinIncomeMapper {

    /**
     * 查询 总收入
     * @return
     */
    List<IncomeBudgetVo> selectTotalIncome(Map map);
    //按单位机构查询总收入
    List<IncomeBudgetVo> selectIncomeTotal(Map map);

    /**
     * 查询部门总收入 年或月
     * @param map
     * @return
     */
    List<IncomeBudgetVo> selectComIncome(Map map);

    /**
     * 查询总收入 月
     * @param map
     * @return
     */
    List<IncomeBudgetVo> selectIncomeMonth(Map map);

    /**
     * 查询各部门月总收入
     * @param map
     * @return
     */
    List<IncomeBudgetVo> selectComIncomeMonth(Map map);

    /**
     * 查询当年每月总收入
     * @param map
     * @return
     */
    List<IncomeBudgetVo> selectComIncomePerMonth(Map map);


}
