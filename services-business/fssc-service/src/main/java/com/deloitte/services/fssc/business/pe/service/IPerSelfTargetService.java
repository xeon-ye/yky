package com.deloitte.services.fssc.business.pe.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.pe.param.PerSelfTargetQueryForm;
import com.deloitte.services.fssc.business.pe.entity.PerSelfTarget;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-05-10
 * @Description : PerSelfTarget服务类接口
 * @Modified :
 */
public interface IPerSelfTargetService extends IService<PerSelfTarget> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<PerSelfTarget>
     */
    IPage<PerSelfTarget> selectPage(PerSelfTargetQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<PerSelfTarget>
     */
    List<PerSelfTarget> selectList(PerSelfTargetQueryForm queryForm);
}
