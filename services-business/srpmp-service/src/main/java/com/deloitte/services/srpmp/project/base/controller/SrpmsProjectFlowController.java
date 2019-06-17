package com.deloitte.services.srpmp.project.base.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.SrpmsProjectFlowClient;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectApprovalForm;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.SrpmsProjectExpertSaveForm;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.TaskNodeActionVO;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.TaskNodeVO;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.exception.ServiceException;
import com.deloitte.services.srpmp.project.base.service.*;
import com.deloitte.services.srpmp.project.base.service.impl.SrpmsProjectBpmServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * @Author : pengchao
 * @Date : Create in 2019-02-19
 * @Description :   SrpmsProject控制器实现类
 * @Modified :
 */
@Api(tags = "项目流程操作接口", value = "项目流程操作接口")
@Slf4j
@RestController
public class SrpmsProjectFlowController implements SrpmsProjectFlowClient {

    @Autowired
    public ISrpmsProjectFlowService srpmsProjectService;

    @Autowired
    public ISrpmsProjectAttachmentService attachmentService;

    @Autowired
    public ISrpmsProjectExpertService expertService;

    @Autowired
    public ISrpmsProjectApprovalService approvalService;

    @Autowired
    public ISrpmsProjectDeptService deptService;

    @Autowired
    public SrpmsProjectBpmServiceImpl bpmService;

    /**
     * 审批项目操作控制器
     *
     * @param approvalForm
     * @return
     */
    @Override
    @ApiOperation(value = "审批项目操作", notes = "修改SrpmsProject的信息")
    public Result agree(@Valid @RequestBody @ApiParam(name="srpmsProjectForm",value="修改SrpmsProject的form表单",required=true) SrpmsProjectApprovalForm approvalForm) {

        srpmsProjectService.agree(approvalForm);
        return Result.success("");
    }

    @Override
    @ApiOperation(value = "项目审批拒绝（BPM）", notes = "项目审批拒绝(BPM)")
    public Result auditRefuse(@RequestBody @ApiParam(name="TaskNodeActionVO",value="TaskNodeActionVO",required=true)TaskNodeActionVO actionVO) {
        srpmsProjectService.refuseWitBpm(actionVO);
        return Result.success("");
    }

    /**
     * 审批撤回操作
     *
     * @param actionVO
     * @return
     */
    @Override
    @ApiOperation(value = "项目审批撤回操作", notes = "项目审批撤回操作")
    public Result approveRecall(@RequestBody @ApiParam(name="TaskNodeActionVO",value="TaskNodeActionVO",required=true) TaskNodeActionVO actionVO) {
        return Result.success(srpmsProjectService.approveRecall(actionVO));
    }

    /**
     * 审批驳回操作
     *
     * @param actionVO
     * @return
     */
    @Override
    @ApiOperation(value = "项目审批驳回操作", notes = "项目审批驳回操作")
    public Result approveReject(@RequestBody @ApiParam(name="TaskNodeActionVO",value="TaskNodeActionVO",required=true) TaskNodeActionVO actionVO) {
        return Result.success(srpmsProjectService.approveReject(actionVO));
    }

    /**
     * 关闭项目申请操作
     *
     * @param actionVO
     * @return
     */
    @Override
    @ApiOperation(value = "关闭项目申请操作", notes = "关闭项目申请操作")
    public Result approveClose(@RequestBody @ApiParam(name="TaskNodeActionVO",value="TaskNodeActionVO",required=true) TaskNodeActionVO actionVO) {
        return Result.success(srpmsProjectService.approveClose(actionVO));
    }

    @Override
    @ApiOperation(value = "项目审批通过（BPM）", notes = "项目审批通过(BPM)")
    public Result auditApprove(@RequestBody @ApiParam(name="TaskNodeActionVO",value="TaskNodeActionVO",required=true)TaskNodeActionVO actionVO, DeptVo deptVo) {
        srpmsProjectService.agreeWithBpm(actionVO, deptVo);
        return Result.success("");
    }

    /**
     * 项目变更审批通过操作
     *
     * @param actionVO
     * @param deptVo
     * @return
     */
    @Override
    @ApiOperation(value = "项目变更审批通过(BPM)", notes = "项目变更审批通过(BPM)")
    public Result auditApproveModify(@RequestBody @ApiParam(name="TaskNodeActionVO",value="TaskNodeActionVO",required=true)TaskNodeActionVO actionVO, DeptVo deptVo) {
        srpmsProjectService.modifyApprovePassBpm(actionVO, deptVo);
        return Result.success("");
    }

    /**
     * 项目变更审批拒绝操作
     *
     * @param actionVO
     * @return
     */
    @Override
    @ApiOperation(value = "项目变更审批拒绝(BPM)", notes = "项目变更审批拒绝(BPM)")
    public Result refuseModify(@RequestBody @ApiParam(name="TaskNodeActionVO",value="TaskNodeActionVO",required=true)TaskNodeActionVO actionVO) {
        srpmsProjectService.modifyRefuseBpm(actionVO);
        return Result.success("");
    }

    /**
     * 项目验收审批通过操作
     *
     * @param actionVO
     * @param deptVo
     * @return
     */
    @Override
    @ApiOperation(value = "项目验收审批通过操作(BPM)", notes = "项目验收审批通过操作(BPM)")
    public Result auditApproveAccept(@RequestBody @ApiParam(name="TaskNodeActionVO",value="TaskNodeActionVO",required=true)TaskNodeActionVO actionVO, DeptVo deptVo) {
        srpmsProjectService.acceptApprovePassBpm(actionVO, deptVo);
        return Result.success("");
    }

    /**
     * 项目验收审批拒绝操作
     *
     * @param actionVO
     * @return
     */
    @Override
    @ApiOperation(value = "项目验收审批拒绝操作(BPM)", notes = "项目验收审批拒绝操作(BPM)")
    public Result refuseAccept(@RequestBody @ApiParam(name="TaskNodeActionVO",value="TaskNodeActionVO",required=true)TaskNodeActionVO actionVO) {
        srpmsProjectService.acceptRefuseBpm(actionVO);
        return Result.success("");
    }

    @Override
    public Result refuse(@RequestBody SrpmsProjectApprovalForm approvalForm) {
        srpmsProjectService.refuse(approvalForm);
        return Result.success("");
    }


    @Override
    public Result redo(@RequestBody SrpmsProjectApprovalForm approvalForm) {

        srpmsProjectService.redo(approvalForm);

        return Result.success("");
    }

    @Override
    @ApiOperation(value = "审批项目操作", notes = "修改SrpmsProject的信息")
    public Result agreeTask(@Valid @RequestBody @ApiParam(name="srpmsProjectForm",value="修改SrpmsProject的form表单",required=true) SrpmsProjectApprovalForm approvalForm) {

        srpmsProjectService.agreeTask(approvalForm);
        return Result.success("");
    }

    @Override
    public Result refuseTask(@RequestBody SrpmsProjectApprovalForm approvalForm) {
        srpmsProjectService.refuseTask(approvalForm);

        return Result.success("");
    }

    @Override
    public Result redoTask(@RequestBody SrpmsProjectApprovalForm approvalForm) {

        srpmsProjectService.redoTask(approvalForm);
        return Result.success("");
    }

    @Override
    public Result addExpert(@Valid @RequestBody @ApiParam(name="专家的FORM",value="新增项目专家的FORM",required=true) SrpmsProjectExpertSaveForm expertForm) {
        expertService.cleanAndSave(expertForm);
        return Result.success("");
    }

    /**
     * 根据当前账号的单位ID获取项目列表控制器
     *
     * @return
     */
    @Override
    @ApiOperation(value = "根据当前账号的单位ID获取项目列表", notes = "获取指定ID的SrpmsProject信息")
    public Result listApproveSrpmsProject(){

        JSONArray jsonArray1 = JSONArray.parseArray(JSON.toJSONString(srpmsProjectService.getApproveList()));
        for (int i = 0; i < jsonArray1.size(); i ++) {
            jsonArray1.getJSONObject(i).put("id", jsonArray1.getJSONObject(i).getString("id"));
        }
        return Result.success(jsonArray1);
    }

    /**
     * 查询待办列表
     * @return
     */
    @Override
    @ApiOperation(value = "查询待办列表", notes = "查询待办列表")
    public Result<IPage<TaskNodeVO>> queryUserBackLog(@RequestBody BaseQueryForm queryForm, UserVo userVo){
        IPage<TaskNodeVO> page = bpmService.queryBackLogPage(queryForm, userVo);
        return Result.success(page);
    }


    /**
     * 查询已办列表
     * @return
     */
    @Override
    @ApiOperation(value = "查询已办列表", notes = "查询已办列表")
    public Result<IPage<TaskNodeVO>> queryUserDoneList(@RequestBody BaseQueryForm queryForm, UserVo userVo){
        IPage<TaskNodeVO> page = bpmService.queryDonePage(queryForm, userVo);
        return Result.success(page);
    }

    /**
     * 查询审核历史
     * @return
     */
    @Override
    @ApiOperation(value = "查询审核历史", notes = "查询审核历史")
    public Result<List<TaskNodeVO>> queryAuditHistory(@PathVariable(value = "objectId") String objectId) {
        List<TaskNodeVO> list = bpmService.queryAuditHistory(objectId);
        return Result.success(list);
    }


    @Override
    @ApiOperation(value = "根据项目ID生成公示文件", notes = "获取指定ID的SrpmsProject信息")
    public Result publishFile(@RequestParam("ids") List<String> projectIds, HttpServletRequest request, HttpServletResponse response) {
        if (projectIds==null || projectIds.size()==0){
            throw new ServiceException(PlatformErrorType.ARGUMENT_NOT_VALID, "未勾选需要公示的项目");
        }
        String filePath = srpmsProjectService.exportPublishFile(projectIds);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            String downName = "项目公示文件_" + DateFormatUtils.format(new Date(), "yyyyMMdd")+".docx";
            response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(downName, "UTF-8"));
            is = new FileInputStream(filePath);
            bis = new BufferedInputStream(is);
            os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (Exception e) {
            log.error("文件读取异常！", e);
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR, e.getMessage(), e.getCause());
        } finally {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(bis);
            IOUtils.closeQuietly(os);
        }
        return Result.success(projectIds);
    }

    @Override
    @ApiOperation(value = "根据项目ID生成批复文件", notes = "根据项目ID生成批复文件")
    public Result approveFile(@RequestParam("ids") List<String> projectIds, HttpServletRequest request, HttpServletResponse response) {
        if (projectIds==null || projectIds.size()==0){
            throw new ServiceException(PlatformErrorType.ARGUMENT_NOT_VALID, "未勾选需要批复的项目");
        }
        String filePath = srpmsProjectService.exportApproveFile(projectIds);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            String downName = "项目批复文件_" + DateFormatUtils.format(new Date(), "yyyyMMdd")+".docx";
            response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(downName, "UTF-8"));
            is = new FileInputStream(filePath);
            bis = new BufferedInputStream(is);
            os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (Exception e) {
            log.error("文件读取异常！", e);
            throw new ServiceException(PlatformErrorType.SYSTEM_ERROR, e.getMessage(), e.getCause());
        } finally {
            if (os != null) {
                try {
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    log.error("文件流关闭异常:", e);
                    throw new BaseException(PlatformErrorType.SYSTEM_ERROR, e.getMessage());
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    log.error("文件流关闭异常:", e);
                    throw new BaseException(PlatformErrorType.SYSTEM_ERROR, e.getMessage());
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    log.error("文件流关闭异常:", e);
                    throw new BaseException(PlatformErrorType.SYSTEM_ERROR, e.getMessage());
                }
            }
        }
        return Result.success(projectIds);
    }

    @Override
    public Result publicProject(@Valid @RequestBody @ApiParam(name="srpmsProjectForm",value="修改SrpmsProject的form表单",required=true) List<SrpmsProjectVo> expertForm) {
        srpmsProjectService.publicProject(expertForm);
        return Result.success("");
    }

    @Override
    public Result approvalProject(@Valid @RequestBody @ApiParam(name="srpmsProjectForm",value="修改SrpmsProject的form表单",required=true) List<SrpmsProjectVo> expertForm) {
        srpmsProjectService.approvalProject(expertForm);
        return Result.success("");
    }

    /**
     * 任务书批复撤回操作
     *
     * @param projectId
     * @return
     */
    @Override
    @ApiOperation(value = "任务书批复撤回操作", notes = "任务书批复撤回操作")
    public Result replyRecall(@PathVariable(value = "projectId") String projectId) {
        return Result.success(srpmsProjectService.replyRecall(projectId));
    }

    public static void main(String[] args){
        String  s = "['10','1001','100101','10010101']";
        JSONArray arr = JSONObject.parseArray(s);
        System.out.println(arr.getString(3));
    }

}