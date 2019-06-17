package com.deloitte.services.fssc.business.bpm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.bpm.param.ProcessDefQueryForm;
import com.deloitte.services.fssc.business.bpm.entity.ProcessDef;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-03-18
 * @Description : ProcessDef服务类接口
 * @Modified :
 */
public interface IProcessDefService extends IService<ProcessDef> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<ProcessDef>
     */
    IPage<ProcessDef> selectPage(ProcessDefQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<ProcessDef>
     */
    List<ProcessDef> selectList(ProcessDefQueryForm queryForm);
}
