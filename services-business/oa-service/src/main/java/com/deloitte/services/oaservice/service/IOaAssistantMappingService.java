package com.deloitte.services.oaservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.oaservice.param.OaAssistantMappingQueryForm;
import com.deloitte.services.oaservice.entity.OaAssistantMapping;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-03
 * @Description : OaAssistantMapping服务类接口
 * @Modified :
 */
public interface IOaAssistantMappingService extends IService<OaAssistantMapping> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<OaAssistantMapping>
     */
    IPage<OaAssistantMapping> selectPage(OaAssistantMappingQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<OaAssistantMapping>
     */
    List<OaAssistantMapping> selectList(OaAssistantMappingQueryForm queryForm);
}
