package com.deloitte.services.fssc.business.pay.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.pay.param.PyPamentBusinessLineQueryForm;
import com.deloitte.services.fssc.business.pay.entity.PyPamentBusinessLine;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-22
 * @Description : PyPamentBusinessLine服务类接口
 * @Modified :
 */
public interface IPyPamentBusinessLineService extends IService<PyPamentBusinessLine> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<PyPamentBusinessLine>
     */
    IPage<PyPamentBusinessLine> selectPage(PyPamentBusinessLineQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<PyPamentBusinessLine>
     */
    List<PyPamentBusinessLine> selectList(PyPamentBusinessLineQueryForm queryForm);
}
