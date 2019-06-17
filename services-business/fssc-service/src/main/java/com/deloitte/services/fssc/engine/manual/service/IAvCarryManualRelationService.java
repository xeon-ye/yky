package com.deloitte.services.fssc.engine.manual.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.engine.manual.param.AvCarryManualRelationQueryForm;
import com.deloitte.services.fssc.engine.manual.entity.AvCarryManualRelation;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : chenx
 * @Date : Create in 2019-05-08
 * @Description : AvCarryManualRelation服务类接口
 * @Modified :
 */
public interface IAvCarryManualRelationService extends IService<AvCarryManualRelation> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<AvCarryManualRelation>
     */
    IPage<AvCarryManualRelation> selectPage(AvCarryManualRelationQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<AvCarryManualRelation>
     */
    List<AvCarryManualRelation> selectList(AvCarryManualRelationQueryForm queryForm);
}
