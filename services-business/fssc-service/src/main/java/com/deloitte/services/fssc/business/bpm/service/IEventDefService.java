package com.deloitte.services.fssc.business.bpm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.bpm.param.EventDefQueryForm;
import com.deloitte.services.fssc.business.bpm.entity.EventDef;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-03-18
 * @Description : EventDef服务类接口
 * @Modified :
 */
public interface IEventDefService extends IService<EventDef> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<EventDef>
     */
    IPage<EventDef> selectPage(EventDefQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<EventDef>
     */
    List<EventDef> selectList(EventDefQueryForm queryForm);
}
