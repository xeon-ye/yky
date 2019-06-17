package com.deloitte.services.oaservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.oaservice.param.OaScheduleHistoryQueryForm;
import com.deloitte.services.oaservice.entity.OaScheduleHistory;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-03-26
 * @Description : OaScheduleHistory服务类接口
 * @Modified :
 */
public interface IOaScheduleHistoryService extends IService<OaScheduleHistory> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<OaScheduleHistory>
     */
    IPage<OaScheduleHistory> selectPage(OaScheduleHistoryQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<OaScheduleHistory>
     */
    List<OaScheduleHistory> selectList(OaScheduleHistoryQueryForm queryForm);
}
