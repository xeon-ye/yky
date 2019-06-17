package com.deloitte.services.dss.finance.mapper;


import com.deloitte.services.dss.finance.entity.FinEtlPretreatment;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 一般公共预算项目支出表 Mapper 接口
 * </p>
 *
 * @author chitose
 * @since 2019-04-27
 */
public interface FinEtlProexpMapper {

    /**
     * 查询项目支出数据
     * @param map
     * @return
     */
    List<FinEtlPretreatment> queryInfoForProExp(Map map);
}
