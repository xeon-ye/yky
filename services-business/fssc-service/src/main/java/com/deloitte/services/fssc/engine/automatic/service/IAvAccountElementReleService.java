package com.deloitte.services.fssc.engine.automatic.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.engine.automatic.param.AvAccountElementReleQueryForm;
import com.deloitte.services.fssc.engine.automatic.entity.AvAccountElementRele;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : chenx
 * @Date : Create in 2019-03-27
 * @Description : AvAccountElementRele服务类接口
 * @Modified :
 */
public interface IAvAccountElementReleService extends IService<AvAccountElementRele> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<AvAccountElementRele>
     */
    IPage<AvAccountElementRele> selectPage(AvAccountElementReleQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<AvAccountElementRele>
     */
    List<AvAccountElementRele> selectList(AvAccountElementReleQueryForm queryForm);
}
