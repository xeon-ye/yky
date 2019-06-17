package com.deloitte.services.dss.finance.mapper;


import com.deloitte.services.dss.finance.entity.FinEtlProjectPret;

/**
 * <p>
 * 三年项目库事实表 Mapper 接口
 * </p>
 *
 * @author chitose
 * @since 2019-04-29
 */
public interface FinEtlProjectFactMapper {

    void insertIntoFact(FinEtlProjectPret finEtlProjectPret);

    void deleteData(FinEtlProjectPret finEtlProjectPret);
}
