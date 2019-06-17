package com.deloitte.services.srpmp.project.base.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectSyncMsg;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.services.srpmp.project.budget.entity.SrpmsProjectBudgetYear;
import com.deloitte.services.srpmp.project.update.entity.SrpmsProjectUpdate;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-04-19
 * @Description : SrpmsProjectSyncMsg服务类接口
 * @Modified :
 */
public interface ISrpmsProjectSyncMsgService extends IService<SrpmsProjectSyncMsg> {

    /**
     * 立项后同步数据
     * @param project
     */
    void syncProjectTaskPass(SrpmsProject project);

    /**
     * 参与人员变更后同步数据
     * @param update
     */
    void syncModifyJoinPerson(SrpmsProjectUpdate update);

    /**
     * 负责人变更后同步数据
     * @param update
     * @param leaderPerson
     */
    void syncModifyLeaderPerson(SrpmsProjectUpdate update, JSONObject leaderPerson);

    /**
     * 项目状态变更后同步数据
     * @param project
     */
    void syncModifyProjectStatus(SrpmsProject project);

    /**
     * 任务书审批通过后同步绩效目标
     * @param project
     */
    void syncBudgetTask(SrpmsProject project);

    /**
     * 年度预算审批通过后同步
     * @param budgetYear
     */
    void syncBudgetYear(SrpmsProjectBudgetYear budgetYear);

    /**
     * 预算变更后同步数据
     * @param projectId
     */
    void syncModifyBudget(Long projectId);
}
