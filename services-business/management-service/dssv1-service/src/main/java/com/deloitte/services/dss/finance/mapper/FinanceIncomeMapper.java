package com.deloitte.services.dss.finance.mapper;

import com.deloitte.platform.api.dss.finance.vo.FinacenIncomeVo;

import java.util.List;

/**
 * <p>
 * 财务总览 Mapper 接口
 * </p>
 *
 * @author chitose
 * @since 2019-04-09
 */
public interface FinanceIncomeMapper {

    /**
     * 查询总收入 (年/月)
     * @return
     */
    List<FinacenIncomeVo> queryTotalIncome(FinacenIncomeVo finacenIncomeVo);


    /**
     * 查询部门总收入 (年/月)
     * @param finacenIncomeVo
     * @return
     */
    List<FinacenIncomeVo> queryComIncome(FinacenIncomeVo finacenIncomeVo);

    /**
     * 查询某年每月的总收入
     * @param finacenIncomeVo
     * @return
     */
    List<FinacenIncomeVo> queryIncomePerMonth(FinacenIncomeVo finacenIncomeVo);

    /**
     * 查询各部门某年总收入
     * @param finacenIncomeVo
     * @return
     */
    List<FinacenIncomeVo> queryComIncomeYear(FinacenIncomeVo finacenIncomeVo);

    /**
     * 查询当年总收入 ETL
     * @return
     */
    FinacenIncomeVo selectTotalIncome();

}
