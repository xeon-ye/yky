package com.deloitte.services.dss.finance.service;


import com.deloitte.platform.api.dss.finance.vo.IncomeBudgetVo;
import com.deloitte.services.dss.finance.entity.FinEtlPretreatment;

import java.util.List;
import java.util.Map;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-17
 * @Description : FinRate服务类接口
 * @Modified :
 */
public interface IFinAchivementService {


    /**
     * 平均项目预算绩效 科研项目预算绩效
     * @param map
     * @return
     */
    List<IncomeBudgetVo> selectAvgAchive(Map map);

    /**
     * 平均项目预算绩效 科研项目预算绩效 同比
     * @param map
     * @return
     */
    List<IncomeBudgetVo> selectAvgAchiveRate(Map map);

}
