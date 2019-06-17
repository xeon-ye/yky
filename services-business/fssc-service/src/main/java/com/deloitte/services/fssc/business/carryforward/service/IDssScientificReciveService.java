package com.deloitte.services.fssc.business.carryforward.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.carryforward.param.DssScientificReciveQueryForm;
import com.deloitte.services.fssc.business.carryforward.entity.DssScientificRecive;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : chenx
 * @Date : Create in 2019-06-11
 * @Description : DssScientificRecive服务类接口
 * @Modified :
 */
public interface IDssScientificReciveService extends IService<DssScientificRecive> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<DssScientificRecive>
     */
    IPage<DssScientificRecive> selectPage(DssScientificReciveQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<DssScientificRecive>
     */
    List<DssScientificRecive> selectList(DssScientificReciveQueryForm queryForm);
}
