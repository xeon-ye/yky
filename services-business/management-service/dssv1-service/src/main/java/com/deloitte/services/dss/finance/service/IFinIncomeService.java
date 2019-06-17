package com.deloitte.services.dss.finance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.dss.finance.vo.AcceptVo;
import com.deloitte.platform.api.dss.finance.vo.IncomeBudgetVo;

import java.util.List;
import java.util.Map;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-09
 * @Description : FinIncome服务类接口
 * @Modified :
 */
public interface IFinIncomeService extends IService {

    /**
     * 查询当年总收入 及 同比
     * @return
     */
    List<IncomeBudgetVo> selectTotalIncome(Map map);
    //按单位机构查询当年总收入
    List<IncomeBudgetVo> selectIncomeTotal(Map map);

    /**
     * 查询部门总收入 及 同比
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
     * @param acceptVo
     * @return
     */
    List<List<IncomeBudgetVo>> selectComIncomeMonth(AcceptVo acceptVo);

    /**
     * 查询每月总收入
     * @param map
     * @return
     */
    List<IncomeBudgetVo> selectComIncomeMonth(Map map);
}
