package com.deloitte.services.dss.finance.mapper;


import com.deloitte.services.dss.finance.entity.FinEtlPretreatment;


/**
 * <p>
 * 财务指标事实表 Mapper 接口
 * </p>
 *
 * @author chitose
 * @since 2019-04-17
 */
public interface FinEtlFactMapper {

    /**
     * 预处理表 --> 事实表
     * @param finEtlPretreatment
     * @return
     */
    int insertProToFact(FinEtlPretreatment finEtlPretreatment);

    /**
     * 删除重复数据
     * @param finEtlPretreatment
     */
    void deleteData(FinEtlPretreatment finEtlPretreatment);



}
