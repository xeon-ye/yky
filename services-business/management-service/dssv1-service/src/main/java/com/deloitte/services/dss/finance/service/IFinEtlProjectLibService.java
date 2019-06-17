package com.deloitte.services.dss.finance.service;


import com.deloitte.services.dss.finance.entity.FinEtlProjectPret;

import java.util.List;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-29
 * @Description : DssFinEtlProjectLib服务类接口
 * @Modified :
 */
public interface IFinEtlProjectLibService {

    /**
     * 查询三年项目库数据
     * @return
     */
    List<FinEtlProjectPret> queryInfo();
}
