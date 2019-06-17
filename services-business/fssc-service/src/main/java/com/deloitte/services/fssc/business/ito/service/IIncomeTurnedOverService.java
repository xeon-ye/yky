package com.deloitte.services.fssc.business.ito.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.ito.param.IncomeTurnedOverQueryForm;
import com.deloitte.platform.api.fssc.ito.vo.IncomeTurnedOverVo;
import com.deloitte.services.fssc.business.ito.entity.IncomeTurnedOver;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-15
 * @Description : IncomeTurnedOver服务类接口
 * @Modified :
 */
public interface IIncomeTurnedOverService extends IService<IncomeTurnedOver> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<IncomeTurnedOver>
     */
    IPage<IncomeTurnedOver> selectPage(IncomeTurnedOverQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<IncomeTurnedOver>
     */
    List<IncomeTurnedOver> selectList(IncomeTurnedOverQueryForm queryForm);

    IncomeTurnedOverVo findInfoById(Long id);
}
