package com.deloitte.services.dss.finance.service;


import com.deloitte.services.dss.finance.entity.FinEtlProjectPret;

import java.util.List;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-29
 * @Description : DssFinEtlProjectFact服务类接口
 * @Modified :
 */
public interface IFinEtlProjectFactService {

    void deleteData(List<FinEtlProjectPret> finEtlProjectPrets);

    /**
     * 三年项目库 预处理表 --> 事实表
     */
    void insertIntoFact();

}
