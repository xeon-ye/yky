package com.deloitte.services.fssc.business.pay.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.pay.param.PyPamentPrivateLineQueryForm;
import com.deloitte.services.fssc.business.pay.entity.PyPamentPrivateLine;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-22
 * @Description : PyPamentPrivateLine服务类接口
 * @Modified :
 */
public interface IPyPamentPrivateLineService extends IService<PyPamentPrivateLine> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<PyPamentPrivateLine>
     */
    IPage<PyPamentPrivateLine> selectPage(PyPamentPrivateLineQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<PyPamentPrivateLine>
     */
    List<PyPamentPrivateLine> selectList(PyPamentPrivateLineQueryForm queryForm);
}
