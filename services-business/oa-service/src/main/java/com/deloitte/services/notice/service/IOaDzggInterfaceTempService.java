package com.deloitte.services.notice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.oaservice.notice.param.OaDzggInterfaceTempQueryForm;
import com.deloitte.services.notice.entity.OaDzggInterfaceTemp;

import java.util.List;

/**
 * @Author : jianghaixun
 * @Date : Create in 2019-04-16
 * @Description : OaDzggInterfaceTemp服务类接口
 * @Modified :
 */
public interface IOaDzggInterfaceTempService extends IService<OaDzggInterfaceTemp> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<OaDzggInterfaceTemp>
     */
    IPage<OaDzggInterfaceTemp> selectPage(OaDzggInterfaceTempQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<OaDzggInterfaceTemp>
     */
    List<OaDzggInterfaceTemp> selectList(OaDzggInterfaceTempQueryForm queryForm);

}
