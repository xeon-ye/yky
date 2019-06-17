package com.deloitte.services.srpmp.project.mpr.controller;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.TaskNodeActionVO;
import com.deloitte.platform.api.srpmp.project.mpr.MprFlowClient;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprUnitEvaInfoForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.srpmp.project.mpr.service.IMprFlowService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.docx4j.wml.U;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author:LIJUN
 * Date:03/04/2019
 * Description:
 */
@Api(tags = "中期绩效报告审核操作接口")
@Slf4j
@RestController
public class MprFlowController implements MprFlowClient {

    @Autowired
    private IMprFlowService mprFlowService;

    @Override
    @ApiOperation(value = "提交中期绩效报告", notes = "提交中期绩效报告")
    @ApiImplicitParam(paramType = "path", name = "projectId", value = "项目编号", required = true, dataType = "long")
    public Result submitMprA(@PathVariable("projectId") Long projectId, UserVo userVo, DeptVo deptVo) {
        mprFlowService.submitMprA(projectId, userVo, deptVo);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "提交中期绩效报告", notes = "提交中期绩效报告")
    @ApiParam(name="unitEvaInfoForm",value="unitEvaInfoForm",required=true)
    public Result submitMprB(@RequestBody MprUnitEvaInfoForm unitEvaInfoForm, UserVo userVo, DeptVo deptVo) {
        mprFlowService.submitMprB(unitEvaInfoForm, userVo, deptVo);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "通过中期绩效报告", notes = "通过中期绩效报告")
    @ApiParam(name="actionVO",value="actionVO",required=true)
    public Result agreeMpr(@RequestBody TaskNodeActionVO actionVO, DeptVo deptVo) {
        mprFlowService.agreeMpr(actionVO, deptVo);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "拒绝中期绩效报告", notes = "拒绝中期绩效报告")
    @ApiParam(name="actionVO",value="actionVO",required=true)
    public Result refuseMpr(@RequestBody TaskNodeActionVO actionVO) {
        mprFlowService.refuseMpr(actionVO);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "驳回中期绩效报告到发起人", notes = "驳回中期绩效报告到发起人")
    @ApiParam(name="actionVO",value="actionVO",required=true)
    public Result rejectMprToFirst(@RequestBody TaskNodeActionVO actionVO) {
        mprFlowService.rejectMprToFirst(actionVO);
        return Result.success();
    }
}
