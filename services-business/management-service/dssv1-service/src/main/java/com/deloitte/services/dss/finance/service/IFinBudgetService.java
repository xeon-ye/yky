package com.deloitte.services.dss.finance.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.dss.finance.vo.AcceptVo;
import com.deloitte.platform.api.dss.finance.vo.IncomeBudgetVo;

import java.util.List;
import java.util.Map;


/**
 * @Author : chitose
 * @Date : Create in 2019-04-09
 * @Description : FinBudget服务类接口
 * @Modified :
 */
public interface IFinBudgetService extends IService {

    /**
     * 查询年预算
     * @param map
     * @return
     */
    List<List<IncomeBudgetVo>> selectBudget(Map map);

    /**
     * 查询机构年预算
     * @param map
     * @return
     */
    List<IncomeBudgetVo> selectComBudget(Map map);

    /**
     * 查询全年支出预算
     * @param incomeBudgetVo
     * @return
     */
    List<List<IncomeBudgetVo>> selectExpBudget(IncomeBudgetVo incomeBudgetVo);

    /**
     * 查询部门全年支出预算
     * @param map
     * @return
     */
    List<IncomeBudgetVo> selectComExpBudget(Map map);


}
