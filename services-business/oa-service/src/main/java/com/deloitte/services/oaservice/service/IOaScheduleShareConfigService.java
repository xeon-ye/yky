package com.deloitte.services.oaservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.oaservice.param.OaScheduleShareConfigQueryForm;
import com.deloitte.services.oaservice.entity.OaScheduleShareConfig;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-23
 * @Description : OaScheduleShareConfig服务类接口
 * @Modified :
 */
public interface IOaScheduleShareConfigService extends IService<OaScheduleShareConfig> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<OaScheduleShareConfig>
     */
    IPage<OaScheduleShareConfig> selectPage(OaScheduleShareConfigQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<OaScheduleShareConfig>
     */
    List<OaScheduleShareConfig> selectList(OaScheduleShareConfigQueryForm queryForm);
}
