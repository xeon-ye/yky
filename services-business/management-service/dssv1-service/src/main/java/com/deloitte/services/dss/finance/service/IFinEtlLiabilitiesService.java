package com.deloitte.services.dss.finance.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.services.dss.finance.entity.FinEtlPretreatment;

import java.util.List;
import java.util.Map;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-12
 * @Description : FinEtlLiabilities服务类接口
 * @Modified :
 */
public interface IFinEtlLiabilitiesService extends IService {

    /**
     * 查询某年资产负债率
     * @param map
     * @return
     */
    List<FinEtlPretreatment> queryLiabilities(Map map);

    /**
     * 查询 资产负债率与累计折旧率
     * @param map
     * @return
     */
    List<FinEtlPretreatment> queryLiaAndDep(Map map);
}
