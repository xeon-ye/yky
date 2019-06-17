package com.deloitte.services.news.service;

import com.deloitte.platform.common.core.entity.vo.Result;

/**
 * 院校新闻审批页面
 */
public interface ProcessApprovalService {

    /**
     * 查询流程状态
     * @param
     * @return
     */
    Result getProcessStatus(String processInstanceId, String sourceSystem);
}
