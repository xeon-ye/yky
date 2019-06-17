package com.deloitte.services.dss.finance.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.dss.finance.vo.AcceptVo;
import com.deloitte.platform.api.dss.finance.vo.IncomeBudgetVo;

import java.util.List;
import java.util.Map;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-12
 * @Description : FinanceLiabilities服务类接口
 * @Modified :
 */
public interface IFinanceLiabilitiesService extends IService {

    /**
     * 查询整体负债率、折旧率 及同比
     * @param map
     * @return
     */
    List<IncomeBudgetVo> queryLiabilities(Map map);
    //查询整体负债率和折旧率
    List<IncomeBudgetVo> selectLiabilities(Map map);

    /**
     * 查询各机构负债率、折旧率
     * @param myData
     * @return
     */
    List<List<IncomeBudgetVo>> queryComLiabilities(AcceptVo myData);

    /**
     * 计算各机构负债率、折旧率平均值
     * @param myData
     * @return
     */
    List<List<IncomeBudgetVo>> queryComLiabilitiesSum(AcceptVo myData);
/*
    *//**
     * 计算各机构负债率、折旧率同比
     * @param myData
     * @return
     *//*
    List<List<IncomeBudgetVo>> queryComLiabilitiesRate(AcceptVo myData);*/
}
