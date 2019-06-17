package com.deloitte.platform.api.srpmp.project.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectApprovalForm;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.SrpmsProjectExpertSaveForm;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.TaskNodeActionVO;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.TaskNodeVO;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author : pengchao
 * @Date : Create in 2019-03-11
 * @Description :  SrpmsProject控制器接口
 * @Modified :
 */
public interface SrpmsProjectFlowClient {

    public static final String path="/srpmp/project";

    /**
     * 添加专家
     *
     * @return
     */
    @PostMapping(value = path+"/expertList")
    Result addExpert(SrpmsProjectExpertSaveForm expertForm);

    /**
     * GET----根据待审核项目列表
     *
     * @return
     */
    @GetMapping(value = path+"/list/approveSrpmsProject")
    Result listApproveSrpmsProject();

    /**
     * GET----查询待办列表
     *
     * @return
     */
    @PostMapping(value = path+"/list/backlog")
    Result<IPage<TaskNodeVO>> queryUserBackLog(@RequestBody BaseQueryForm queryForm, UserVo userVo);

    /**
     * GET----查询待办列表
     *
     * @return
     */
    @PostMapping(value = path+"/list/done")
    Result<IPage<TaskNodeVO>> queryUserDoneList(@RequestBody BaseQueryForm queryForm, UserVo userVo);

    /**
     * GET----查询审核历史
     *
     * @return
     */
    @GetMapping(value = path+"/list/auditHistory/{objectId}")
    Result<List<TaskNodeVO>> queryAuditHistory(@PathVariable(value = "objectId") String objectId);


    /**
     * 项目审批同意
     *
     * @return
     */
    @PostMapping(value = path+"/agree")
    Result agree(@Valid @RequestBody SrpmsProjectApprovalForm approvalForm);

    /**
     * 项目审批通过
     */
    @PostMapping(value = path+"/audit/approve")
    Result auditApprove(@Valid @RequestBody TaskNodeActionVO actionVO, DeptVo deptVo);

    /**
     * 项目变更审批通过操作
     *
     * @param actionVO
     * @param deptVo
     * @return
     */
    @PostMapping(value = path+"/audit/approve/modify")
    Result auditApproveModify(@Valid @RequestBody TaskNodeActionVO actionVO, DeptVo deptVo);

    /**
     * 项目变更审批拒绝操作
     *
     * @param actionVO
     * @return
     */
    @PostMapping(value = path+"/refuse/modify")
    Result refuseModify(@Valid @RequestBody TaskNodeActionVO actionVO);

    /**
     * 项目变更审批通过操作
     *
     * @param actionVO
     * @param deptVo
     * @return
     */
    @PostMapping(value = path+"/audit/approve/accept")
    Result auditApproveAccept(@Valid @RequestBody TaskNodeActionVO actionVO, DeptVo deptVo);

    /**
     * 项目变更审批拒绝操作
     *
     * @param actionVO
     * @return
     */
    @PostMapping(value = path+"/refuse/accept")
    Result refuseAccept(@Valid @RequestBody TaskNodeActionVO actionVO);


    /**
     *  项目审批拒绝
     * @param  approvalForm
     * @return
     */
    @PostMapping(value = path+"/refuse")
    Result refuse(@Valid @RequestBody SrpmsProjectApprovalForm approvalForm);

    /**
     *  项目审批拒绝
     * @param  actionVO
     * @return
     */
    @PostMapping(value = path+"/audit/refuse")
    Result auditRefuse(@Valid @RequestBody TaskNodeActionVO actionVO);


    /**
     *  项目审批驳回
     * @param  approvalForm
     * @return
     */
    @PostMapping(value = path+"/redo")
    Result redo(@Valid @RequestBody SrpmsProjectApprovalForm approvalForm);


    /**
     * 项目审批同意
     *
     * @return
     */
    @PostMapping(value = path+"/agreeTask")
    Result agreeTask(@Valid @RequestBody SrpmsProjectApprovalForm approvalForm);

    /**
     *  项目审批拒绝
     * @param  approvalForm
     * @return
     */
    @PostMapping(value = path+"/refuseTask")
    Result refuseTask(@Valid @RequestBody SrpmsProjectApprovalForm approvalForm);

    /**
     *  项目审批驳回
     * @param  approvalForm
     * @return
     */
    @PostMapping(value = path+"/redoTask")
    Result redoTask(@Valid @RequestBody SrpmsProjectApprovalForm approvalForm);


    /**
     * 项目公式
     *
     * @return
     */
    @PostMapping(value = path+"/public")
    Result publicProject(List<SrpmsProjectVo> projectList);

    /**
     * 项目批复
     *
     * @return
     */
    @PostMapping(value = path+"/approval")
    Result approvalProject(List<SrpmsProjectVo> projectList);

    /**
     * 生成公示文件
     * @param projectIds
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = path+"/publish/file")
    Result publishFile(List<String> projectIds, HttpServletRequest request, HttpServletResponse response);

    /**
     * 生成批复文件
     * @param projectIds
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = path+"/approve/file")
    Result approveFile(List<String> projectIds, HttpServletRequest request, HttpServletResponse response);

    /**
     * 任务书批复撤回
     *
     * @param projectId
     * @return
     */
    @GetMapping(value = path+"/reply/recall/{projectId}")
    Result replyRecall(@PathVariable(value = "projectId") String projectId);

    /**
     * 审批撤回
     *
     * @param actionVO
     * @return
     */
    @PostMapping(value = path+"/approve/recall")
    Result approveRecall(@Valid @RequestBody TaskNodeActionVO actionVO);
    /**
     * 审批驳回
     *
     * @param actionVO
     * @return
     */
    @PostMapping(value = path+"/approve/reject")
    Result approveReject(@Valid @RequestBody TaskNodeActionVO actionVO);

    /**
     * 关闭申请
     *
     * @param actionVO
     * @return
     */
    @PostMapping(value = path+"/approve/close")
    Result approveClose(@Valid @RequestBody TaskNodeActionVO actionVO);
}
