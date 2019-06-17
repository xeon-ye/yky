package com.deloitte.services.srpmp.project.base.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.SrpmsProjectClient;
import com.deloitte.platform.api.srpmp.project.base.SrpmsProjectExtClient;
import com.deloitte.platform.api.srpmp.project.base.param.SrpmsProjectQueryForm;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.common.enums.SrpmsProjectStatusEnums;
import com.deloitte.services.srpmp.project.base.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-19
 * @Description :   SrpmsProject控制器实现类
 * @Modified :
 */
@Api(tags = "项目查询，任务书查询，项目删除接口", value = "项目查询，任务书查询，项目删除接口")
@Slf4j
@RestController
public class SrpmsProjectController implements SrpmsProjectClient, SrpmsProjectExtClient {

    @Autowired
    public ISrpmsProjectService projectService;

    @Autowired
    public ISrpmsProjectAndJoinService projectAndJoinService;

    @Autowired
    public ISrpmsProjectAttachmentService attachmentService;

    @Autowired
    public ISrpmsProjectExpertService expertService;

    @Autowired
    public ISrpmsProjectApprovalService approvalService;

    @Autowired
    public ISrpmsProjectDeptService deptService;

    @Override
    public Result<GdcPage<SrpmsProjectVo>> searchNoUser(@RequestBody SrpmsProjectQueryForm srpmsProjectQueryForm) {
        log.info("searchNoUser with srpmsProjectQueryForm:", srpmsProjectQueryForm.toString());
        IPage<SrpmsProjectVo> projectVoIPage = projectService.searchNoUser(srpmsProjectQueryForm);
        GdcPage<SrpmsProjectVo> pages = new GdcPage(projectVoIPage.getRecords(), projectVoIPage.getTotal(), (int) projectVoIPage.getCurrent(), (int) projectVoIPage.getSize());
        return new Result<GdcPage<SrpmsProjectVo>>().sucess(pages);
    }

    @Override
    public Result<List<SrpmsProjectVo>> listByProjectIds(@RequestBody String projectIds) {
        log.info("listByProjectIds with projectIds:", projectIds);
        List<SrpmsProjectVo> list = projectService.listByProjectIds(projectIds);
        return new Result<List<SrpmsProjectVo>>().sucess(list);
    }

    @Override
    @ApiOperation(value = "分页查询SrpmsProject(包含参与的项目)", notes = "根据条件查询科研项目(包含参与的项目)分页数据")
    public Result list(@Valid @RequestBody @ApiParam(name = "srpmsProjectQueryForm", value = "SrpmsProject查询参数", required = true) SrpmsProjectQueryForm srpmsProjectQueryForm, UserVo user, DeptVo dept) {
        log.info("search with srpmsProjectQueryForm:", srpmsProjectQueryForm.toString());

        return new Result<IPage<SrpmsProjectVo>>().sucess(projectAndJoinService.selectPage(srpmsProjectQueryForm, user, dept));
    }

    @Override
    @ApiOperation(value = "分页查询SrpmsProject", notes = "根据条件查询科研项目分页数据")
    public Result search(@Valid @RequestBody @ApiParam(name = "srpmsProjectQueryForm", value = "SrpmsProject查询参数", required = true) SrpmsProjectQueryForm srpmsProjectQueryForm, UserVo user, DeptVo dept) {
        log.info("search with srpmsProjectQueryForm:", srpmsProjectQueryForm.toString());

        return new Result<IPage<SrpmsProjectVo>>().sucess(projectService.selectPage(srpmsProjectQueryForm, user, dept));
    }

    @Override
    @ApiOperation(value = "项目变更查询SrpmsProject", notes = "根据条件查询科研变更项目分页数据")
    public Result searchModify(@Valid @RequestBody @ApiParam(name = "srpmsProjectQueryForm", value = "SrpmsProject查询参数", required = true) SrpmsProjectQueryForm srpmsProjectQueryForm, UserVo user, DeptVo dept) {
        log.info("search with srpmsProjectQueryForm:", srpmsProjectQueryForm.toString());
        srpmsProjectQueryForm.setStatus(SrpmsProjectStatusEnums.PEROJECT_TASK_PASS.getCode().concat(",").concat(SrpmsProjectStatusEnums.PEROJECT_SET_UP.getCode()));
        return new Result<IPage<SrpmsProjectVo>>().sucess(projectService.selectPage(srpmsProjectQueryForm, user, dept));
    }

    @Override
    @ApiOperation(value = "横纵项查询SrpmsProject", notes = "根据条件查询科研变横纵项项目分页数据")
    public Result searchCreate(@Valid @RequestBody @ApiParam(name = "srpmsProjectQueryForm", value = "SrpmsProject查询参数", required = true) SrpmsProjectQueryForm srpmsProjectQueryForm, UserVo user, DeptVo dept) {
        log.info("search with srpmsProjectQueryForm:", srpmsProjectQueryForm.toString());

        srpmsProjectQueryForm.setProjectFlag(SrpmsConstant.PROJECT_TYPE_1);
        if(srpmsProjectQueryForm.getTableFlag() == SrpmsConstant.TABLE_FLAG_0) {
            if (SrpmsConstant.SRPMS_ADMIN.equals(user.getHonor()) || SrpmsConstant.SRPMS_LEADER.equals(user.getHonor())) {
                srpmsProjectQueryForm.setStatus(SrpmsProjectStatusEnums.PEROJECT_TASK_PASS.getCode().concat(",").concat(SrpmsProjectStatusEnums.PEROJECT_SET_UP.getCode()));
            } else {
                srpmsProjectQueryForm.setStatus(SrpmsProjectStatusEnums.PEROJECT_NOT_SUBMIT.getCode());
            }
        }
        return new Result<IPage<SrpmsProjectVo>>().sucess(projectService.selectPage(srpmsProjectQueryForm, user, dept));
    }

    @Override
    public Result delete(@PathVariable long id) {
        projectService.delete(id);
        return Result.success("");
    }

    @Override
    @ApiOperation(value = "查询任务书列表")
    public Result<IPage<SrpmsProjectVo>> taskQuery(UserVo user, DeptVo dept) {

        return new Result<IPage<SrpmsProjectVo>>().sucess(projectService.queryTaskPage(user, dept));
    }

    @Override
    @ApiOperation(value = "获取SrpmsProject", notes = "获取指定ID的SrpmsProject信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SrpmsProject的ID", required = true, dataType = "long")
    public Result getProject(@PathVariable long id, @PathVariable(value = "type") String type, UserVo user, DeptVo dept) {
        log.info("get with id:{}", id);

        if ("1".equals(type)) {
            return new Result().sucess(projectService.getProjectApply(id));
        } else if ("2".equals(type)) {
            return new Result().sucess(projectService.getProjectTask(id));
        } else {
            return Result.fail();
        }
    }

    /**
     * 工作台根据ID删除评审专家数据
     *
     * @param id
     * @return
     */
    @Override
    @ApiOperation(value = "根据ID删除评审专家信息", notes = "根据ID删除评审专家信息")
    public Result deleteExpertById(@PathVariable long id) {
        expertService.delete(id);
        return Result.success("");
    }
}