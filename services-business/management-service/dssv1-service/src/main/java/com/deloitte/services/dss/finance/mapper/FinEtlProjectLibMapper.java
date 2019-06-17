package com.deloitte.services.dss.finance.mapper;


import com.deloitte.services.dss.finance.entity.FinEtlProjectPret;

import java.util.List;

/**
 * <p>
 * 三年项目库 Mapper 接口
 * </p>
 *
 * @author chitose
 * @since 2019-04-29
 */
public interface FinEtlProjectLibMapper {

    /**
     * 查询三年项目库数据
     * @return
     */
    List<FinEtlProjectPret> queryInfo();
}
