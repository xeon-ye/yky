package com.deloitte.services.fssc.engine.automatic.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.engine.automatic.param.AvLedgerUnitRelationQueryForm;
import com.deloitte.services.fssc.engine.automatic.entity.AvLedgerUnitRelation;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : chenx
 * @Date : Create in 2019-03-30
 * @Description : AvLedgerUnitRelation服务类接口
 * @Modified :
 */
public interface IAvLedgerUnitRelationService extends IService<AvLedgerUnitRelation> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<AvLedgerUnitRelation>
     */
    IPage<AvLedgerUnitRelation> selectPage(AvLedgerUnitRelationQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<AvLedgerUnitRelation>
     */
    List<AvLedgerUnitRelation> selectList(AvLedgerUnitRelationQueryForm queryForm);

    /**
     * 用公司编码查询账薄ID
     * @param unitCode
     * @return
     */
    Long getLedgerId(String unitCode);
}
