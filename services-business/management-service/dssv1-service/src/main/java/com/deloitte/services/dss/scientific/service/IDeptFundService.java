package com.deloitte.services.dss.scientific.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.dss.scientific.vo.DeptFundDetailVo;
import com.deloitte.platform.api.dss.scientific.vo.DeptFundVo;

import java.util.List;
import java.util.Map;

/**
 * 单位预算执行情况接口
 */
public interface IDeptFundService extends IService {


    /**
     * 单位-项目年度预算与支出
     * @param map
     * @return
     */
    List<DeptFundVo> queryDeptProAmountPay(Map map);


    /**
     * 单位-项目预算与支出明细
     * @param map
     * @return
     */
    Map queryDeptProFundDetail(Map map);

}
