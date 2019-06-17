package com.deloitte.platform.api.srpmp.project.mpr;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.TaskNodeActionVO;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprUnitEvaInfoForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Author:LIJUN
 * Date:03/04/2019
 * Description:
 */
public interface MprFlowClient {

    public static final String path="/srpmp/project/mpr/flow";

    /**
     * 提交中期绩效报告（流程A）
     * @param projectId
     * @param userVo
     * @param deptVo
     */
    @PostMapping(value = path+"/submitMprA/{projectId}")
    Result submitMprA(@PathVariable("projectId") Long projectId, UserVo userVo, DeptVo deptVo);

    /**
     * 提交中期绩效报告（流程B）
     * @param userVo
     * @param deptVo
     */
    @PostMapping(value = path+"/submitMprB")
    Result submitMprB(@Valid @ModelAttribute MprUnitEvaInfoForm unitEvaInfoForm, UserVo userVo, DeptVo deptVo);

    /**
     * 通过中期绩效报告
     * @param actionVO
     */
    @PostMapping(value = path+"/agreeMpr")
    Result agreeMpr(@Valid @ModelAttribute TaskNodeActionVO actionVO, DeptVo deptVo);

    /**
     * 拒绝中期绩效报告
     * @param actionVO
     */
    @PostMapping(value = path+"/refuseMpr")
    Result refuseMpr(@Valid @ModelAttribute TaskNodeActionVO actionVO);

    /**
     * 驳回中期绩效报告到发起人
     * @param actionVO
     */
    @PostMapping(value = path+"/rejectMprToFirst")
    Result rejectMprToFirst(@Valid @ModelAttribute TaskNodeActionVO actionVO);
}
