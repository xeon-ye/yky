package com.deloitte.services.dss.finance.mapper;


import com.deloitte.services.dss.finance.entity.FinEtlPretreatment;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资产负债率 Mapper 接口
 * </p>
 *
 * @author chitose
 * @since 2019-04-12
 */
public interface FinEtlLiabilitiesMapper {

    /**
     * 查询 资产总额、负债总额、净资产总额、固定资产总额、累计折旧与固定资产净值
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
