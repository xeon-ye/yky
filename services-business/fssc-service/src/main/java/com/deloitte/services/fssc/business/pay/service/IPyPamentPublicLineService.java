package com.deloitte.services.fssc.business.pay.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.pay.param.PyPamentPublicLineQueryForm;
import com.deloitte.services.fssc.business.pay.entity.PyPamentPublicLine;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-22
 * @Description : PyPamentPublicLine服务类接口
 * @Modified :
 */
public interface IPyPamentPublicLineService extends IService<PyPamentPublicLine> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<PyPamentPublicLine>
     */
    IPage<PyPamentPublicLine> selectPage(PyPamentPublicLineQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<PyPamentPublicLine>
     */
    List<PyPamentPublicLine> selectList(PyPamentPublicLineQueryForm queryForm);
}
