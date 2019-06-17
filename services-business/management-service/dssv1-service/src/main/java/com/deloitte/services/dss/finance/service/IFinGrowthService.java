package com.deloitte.services.dss.finance.service;

import com.deloitte.platform.api.dss.finance.vo.AcceptVo;
import com.deloitte.platform.api.dss.finance.vo.FinancePlannedGrowthVo;
import com.deloitte.platform.api.dss.finance.vo.GrowthVo;

import java.util.List;
import java.util.Map;


/**
 * @Author : chitose
 * @Date : Create in 2019-04-17
 * @Description : FinGrowth服务类接口
 * @Modified :
 */
public interface IFinGrowthService {

    /**
     * 查询 收入整体计划增长
     * @param map
     * @return
     */
    List<GrowthVo> quesryGrowth(Map map);
    /**
     * 查询 收入整体计划增长月
     * @param map
     * @return
     */
    List<GrowthVo> quesryGrowthMonth(Map map);
    /**
     * 查询 收入整体计划增长月
     * @param map
     * @return
     */
    List<GrowthVo> quesryGrowthPerMonth(Map map);

    /**
     * 查询 机构收入计划增长
     * @param map
     * @return
     */
    List<GrowthVo> quesryComGrowth(Map map);

    /**
     * 查询 支出整体计划增长
     * @param map
     * @return
     */
    List<GrowthVo> quesryExpGrowth(Map map);

    /**
     * 查询 机构支出计划增长
     * @param acceptVo
     * @return
     */
    List<List<GrowthVo>> quesryExpComGrowth(AcceptVo acceptVo);
}
