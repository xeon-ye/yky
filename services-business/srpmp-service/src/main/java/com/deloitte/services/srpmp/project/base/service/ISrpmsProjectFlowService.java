package com.deloitte.services.srpmp.project.base.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectApprovalForm;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.TaskNodeActionVO;
import com.deloitte.services.srpmp.common.enums.VoucherTypeEnums;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.entity.SrpmsVoucher;
import com.deloitte.services.srpmp.project.budget.entity.SrpmsProjectBudgetYear;
import com.deloitte.services.srpmp.project.update.entity.SrpmsProjectUpdate;

import java.util.List;

/**
 * @Author : pengchao
 * @Date : Create in 2019-03-11
 * @Description : SrpmsProject服务类接口
 * @Modified :
 */
public interface ISrpmsProjectFlowService extends IService<SrpmsProject> {

    /**
     * 申请书同意
     * @param approvalForm
     */
    void agree(SrpmsProjectApprovalForm approvalForm);

    public void agreeWithBpm(TaskNodeActionVO actionVO, DeptVo deptVo);

    void modifyApprovePassBpm(TaskNodeActionVO actionVO, DeptVo deptVo);

    void modifyRefuseBpm(TaskNodeActionVO actionVO);

    void acceptApprovePassBpm(TaskNodeActionVO actionVO, DeptVo deptVo);

    void acceptRefuseBpm(TaskNodeActionVO actionVO);

    void refuseWitBpm(TaskNodeActionVO actionVO);

    /**
     * 开启审核流程
     * @param projectId
     */
    void startAuditProcess(Long projectId, VoucherTypeEnums typeEnums, UserVo userVo, DeptVo deptVo);

    void startModifyAuditProcess(SrpmsProjectUpdate update, VoucherTypeEnums typeEnums, UserVo userVo, DeptVo deptVo);

    void startBudgetAuditProcess(SrpmsProjectBudgetYear budgetYear, VoucherTypeEnums typeEnums, UserVo userVo, DeptVo deptVo);

    /**
     * 任务书拒绝
     * @param approvalForm
     */
    void agreeTask(SrpmsProjectApprovalForm approvalForm);

    /**
     * 申请书拒绝
     * @param approvalForm
     */
    void refuse(SrpmsProjectApprovalForm approvalForm);

    /**
     * 任务书拒绝
     * @param approvalForm
     */
    void refuseTask(SrpmsProjectApprovalForm approvalForm);

    /**
     * 申请书驳回
     * @param approvalForm
     */
    void redo(SrpmsProjectApprovalForm approvalForm);

    /**
     * 任务书驳回
     * @param approvalForm
     */
    void redoTask(SrpmsProjectApprovalForm approvalForm);

    /**
     *  分页查询
     * @return IPage<SrpmsProject>
     */
    JSONArray getApproveList();

    /**
     * 公示一个项目
     * @param expertForm
     * @return
     */
    void publicProject(List<SrpmsProjectVo> expertForm);

    /**
     * 批复一个项目
     * @param expertForm
     * @return
     */
    void approvalProject(List<SrpmsProjectVo> expertForm);

    /**
     * 导出公示文件
     * @param projectIds
     * @return 文件绝对路径地址
     */
    String exportPublishFile(List<String> projectIds);

    /**
     * 导出公示文件
     * @param projectIds
     * @return 文件绝对路径地址
     */
    String exportApproveFile(List<String> projectIds);

    /**
     * 任务书批复撤回操作service接口
     *
     * @param projectId
     * @return
     */
    String replyRecall(String projectId);

    /**
     * 审批撤回操作service接口
     *
     * @param actionVO
     * @return
     */
    String approveRecall(TaskNodeActionVO actionVO);
    /**
     * 审批驳回操作service接口
     *
     * @param actionVO
     * @return
     */
    String approveReject(TaskNodeActionVO actionVO);
    /**
     * 关闭项目申请操作service接口
     *
     * @param actionVO
     * @return
     */
    String approveClose(TaskNodeActionVO actionVO);

    /**
     * @title againSubmit
     * @description 重新提交操作数据service接口
     * @auther Mr.Carlos
     * @date 2019.06.05 10:02
     * @param voucher
     * @param deptVo
     */
    void againSubmit(SrpmsVoucher voucher, DeptVo deptVo, boolean stopFlag);
}
