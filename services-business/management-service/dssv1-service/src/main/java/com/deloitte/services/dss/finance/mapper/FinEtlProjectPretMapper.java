package com.deloitte.services.dss.finance.mapper;


import com.deloitte.services.dss.finance.entity.FinEtlProjectPret;

import java.util.List;

/**
 * <p>
 * 三年项目库预处理表 Mapper 接口
 * </p>
 *
 * @author chitose
 * @since 2019-04-29
 */
public interface FinEtlProjectPretMapper  {

    void insertIntoPret(FinEtlProjectPret finEtlProjectPret);

    void deleteData(FinEtlProjectPret finEtlProjectPret);

    /**
     * 合并数据 三年项目库
     * @return
     */
    List<FinEtlProjectPret> queryGroupBy();
}
