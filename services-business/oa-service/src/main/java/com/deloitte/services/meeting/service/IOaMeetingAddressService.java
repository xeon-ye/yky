package com.deloitte.services.meeting.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.oaservice.meeting.param.OaMeetingAddressQueryForm;
import com.deloitte.services.meeting.entity.OaMeetingAddress;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-12
 * @Description : OaMeetingAddress服务类接口
 * @Modified :
 */
public interface IOaMeetingAddressService extends IService<OaMeetingAddress> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<OaMeetingAddress>
     */
    IPage<OaMeetingAddress> selectPage(OaMeetingAddressQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<OaMeetingAddress>
     */
    List<OaMeetingAddress> selectList(OaMeetingAddressQueryForm queryForm);
}
