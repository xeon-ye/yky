package com.deloitte.services.news.service;

import com.deloitte.platform.api.oaservice.news.vo.NewsForm;
import com.deloitte.platform.api.oaservice.news.vo.NewsProcessForm;
import com.deloitte.platform.api.oaservice.news.vo.RollBackProcessVo;
import com.deloitte.platform.common.core.entity.vo.Result;

import java.util.Map;

/**
 * 新闻发布审批流程
 */
public interface WorkflowService {

    /**
     * 新闻审批流程提交
     *
     * @param newsForm
     * @return
     */
    Result submitProcess(NewsForm newsForm, String token);

    /**
     * 审批以及结果
     *
     * @param newsProcessForm
     */
    Result approval(NewsProcessForm newsProcessForm, String token);

    /**
     * 流程撤回
     *
     * @return
     */
    Result rollBackProcess(RollBackProcessVo rollBackProcessVo);

    /**
     * 查询下一节点审批人
     * @param processVariables
     * @return
     */
    Result buildBpmForm(Map<String, String> processVariables);

    /**
     * 查询审批历史（剔除重复审批人）
     * @param processInstanceId
     * @param taskKey
     * @return
     */
    Result getHistoryList(String processInstanceId, String taskKey);


}
