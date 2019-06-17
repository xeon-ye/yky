package com.deloitte.services.fssc.business.travle.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.travle.param.TasLeaveaBjInformationQueryForm;
import com.deloitte.services.fssc.business.travle.entity.TasLeaveaBjInformation;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-04-01
 * @Description : TasLeaveaBjInformation服务类接口
 * @Modified :
 */
public interface ITasLeaveaBjInformationService extends IService<TasLeaveaBjInformation> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<TasLeaveaBjInformation>
     */
    IPage<TasLeaveaBjInformation> selectPage(TasLeaveaBjInformationQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<TasLeaveaBjInformation>
     */
    List<TasLeaveaBjInformation> selectList(TasLeaveaBjInformationQueryForm queryForm);
}
