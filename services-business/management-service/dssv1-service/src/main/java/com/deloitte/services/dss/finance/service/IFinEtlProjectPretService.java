package com.deloitte.services.dss.finance.service;


import com.deloitte.services.dss.finance.entity.FinEtlProjectPret;

import java.util.List;
import java.util.Map;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-29
 * @Description : DssFinEtlProjectPret服务类接口
 * @Modified :
 */
public interface IFinEtlProjectPretService  {

    void deleteData(List<FinEtlProjectPret> finEtlProjectPrets);

    /**
     * 三年项目库 来源表 --> 预处理表
     */
    void insertIntoPret();

    /**
     * 合并数据 三年项目库
     * @return
     */
    List<FinEtlProjectPret> queryGroupBy();
}
