package com.deloitte.services.srpmp.project.mpr.service;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.TaskNodeActionVO;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprUnitEvaInfoForm;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-04-02
 * @Description : 中期绩效报告流程接口
 */
public interface IMprFlowService {

    /**
     * 提交中期绩效报告（流程A）
     * @param projectId
     * @param userVo
     * @param deptVo
     */
    void submitMprA(Long projectId, UserVo userVo, DeptVo deptVo);

    /**
     * 提交中期绩效报告（流程B）
     * @param userVo
     * @param deptVo
     */
    void submitMprB(MprUnitEvaInfoForm mprUnitEvaInfoForm, UserVo userVo, DeptVo deptVo);

    /**
     * 通过中期绩效报告
     * @param actionVO
     */
    void agreeMpr(TaskNodeActionVO actionVO, DeptVo deptVo);

    /**
     * 拒绝中期绩效报告
     * @param actionVO
     */
    void refuseMpr(TaskNodeActionVO actionVO);

    /**
     * 驳回中期绩效报告到发起人
     * @param actionVO
     */
    void rejectMprToFirst(TaskNodeActionVO actionVO);

}
