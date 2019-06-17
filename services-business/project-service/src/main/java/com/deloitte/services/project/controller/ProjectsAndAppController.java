package com.deloitte.services.project.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.isump.vo.UserForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.project.common.util.CommonUtil;
import com.deloitte.services.project.entity.ProjectsAndApplication;
import com.deloitte.services.project.service.ICommonService;
import com.deloitte.services.project.service.IProjectsAndApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @Author : tanwenxin
 * @Date : Created by 2019/05/12
 * @Description : 通用综合查询控制类
 * @Modified :
 */
@Api(tags = "项目综合多数据查询")
@Slf4j
@RestController
@RequestMapping("/project/projectsAndApp")
public class ProjectsAndAppController{

    @Autowired
    IProjectsAndApplicationService projectsAndApplicationService;

    @Autowired
    ICommonService commonService;

    /**
     * testtesttest
     * @param id
     * @return
     */
    @RequestMapping("/hi/{id}")
    @ApiOperation(value = "获取ProjectBudget", notes = "获取指定ID的ProjectBudget信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ProjectBudget的ID", required = true, dataType = "long")
    public Result<ProjectsAndApplication> get(@PathVariable long id) {
        return null;
    }

    /**
     * 综合查询
     * @param app
     * @param userForm
     * @param deptForm
     * @return
     */
    @PostMapping(value = "/pageList/all")
    @ApiOperation(value = "综合查询分页", notes = "综合查询分页")
    Result<IPage<ProjectsAndApplication>> searchAll( @Valid @RequestBody @ApiParam ProjectsAndApplication app){
        Map<String,Object> map = CommonUtil.objectToMap(app);
        Page<ProjectsAndApplication> page = new Page<>(app.getCurrentPage(), app.getPageSize());
        String replyCode =  app.getReplyCode();
        List<ProjectsAndApplication> list = null;
        if(StringUtils.isEmpty(replyCode)){
            list = projectsAndApplicationService.selectAllListPage(page, map);
        } else {
            list = projectsAndApplicationService.selectAllReplyListPage(page, map);
        }
        IPage<ProjectsAndApplication> userIPage = page.setRecords(list);
       return new Result<IPage<ProjectsAndApplication>>().sucess(userIPage);
    }

    /**
     *项目申报前导页面以及关联页面
     * @return
     */
    @PostMapping(value = "/pageList/getApps")
    @ApiOperation(value = "项目关联查询分页", notes = "项目关联查询分页")
    Result<IPage<ProjectsAndApplication>> selectAllApps(@Valid @RequestBody @ApiParam ProjectsAndApplication app){
        Map<String,Object> map = CommonUtil.objectToMap(app);
        Page<ProjectsAndApplication> page = new Page<>(app.getCurrentPage(), app.getPageSize());
        List<ProjectsAndApplication> list = projectsAndApplicationService.selectAllApps(page, map);
        IPage<ProjectsAndApplication> userIPage = page.setRecords(list);
        return new Result<IPage<ProjectsAndApplication>>().sucess(userIPage);
    }

    /**
     *项目评审前导页面
     * @return
     */
    @PostMapping(value = "/pageList/review")
    @ApiOperation(value = "项目评审查询分页", notes = "项目评审查询分页")
    Result<IPage<ProjectsAndApplication>> searchReview(@Valid @RequestBody @ApiParam ProjectsAndApplication app){
        //评审只带当前年度
        String year = new SimpleDateFormat("yyyy").format(new Date());
        app.setTheApplicationYear(year);
        Map<String,Object> map = CommonUtil.objectToMap(app);
        Page<ProjectsAndApplication> page = new Page<>(app.getCurrentPage(), app.getPageSize());
        List<ProjectsAndApplication> list = projectsAndApplicationService.selectReviewListPage(page, map);
        IPage<ProjectsAndApplication> userIPage = page.setRecords(list);
        return new Result<IPage<ProjectsAndApplication>>().sucess(userIPage);
    }

    @PostMapping(value = "/pageList/startReply")
    @ApiOperation(value = "立项批复查询前导分页", notes = "立项批复查询前导分页")
    Result<IPage<ProjectsAndApplication>> searchStartReply(@Valid @RequestBody @ApiParam ProjectsAndApplication app){
        Map<String,Object> map = CommonUtil.objectToMap(app);
        Page<ProjectsAndApplication> page = new Page<>(app.getCurrentPage(), app.getPageSize());
        List<ProjectsAndApplication> list = null;
        list = projectsAndApplicationService.selectStartReplyPage(page, map);
        IPage<ProjectsAndApplication> userIPage = page.setRecords(list);
        return new Result<IPage<ProjectsAndApplication>>().sucess(userIPage);
    }

    @PostMapping(value = "/pageList/reply")
    @ApiOperation(value = "立项批复关联查询分页", notes = "立项批复关联查询分页")
    Result<IPage<ProjectsAndApplication>> searchReply(@Valid @RequestBody @ApiParam ProjectsAndApplication app){
        Map<String,Object> map = CommonUtil.objectToMap(app);
        Page<ProjectsAndApplication> page = new Page<>(app.getCurrentPage(), app.getPageSize());
        //流程申报查询
        List<ProjectsAndApplication> list = projectsAndApplicationService.selectAllReplyListPage(page, map);
        IPage<ProjectsAndApplication> userIPage = page.setRecords(list);
        return new Result<IPage<ProjectsAndApplication>>().sucess(userIPage);
    }

    @PostMapping(value = "/pageList/getExecution")
    @ApiOperation(value = "项目执行前导查询", notes = "项目执行前导查询")
    Result<IPage<ProjectsAndApplication>> searchExecutionPages(@Valid @RequestBody @ApiParam ProjectsAndApplication app){
        //传入项目状态 project_type_code 7004 已立项
        //项目执行审批未通过的，也要查询出来，未实现
        Map<String,Object> map = CommonUtil.objectToMap(app);
        Page<ProjectsAndApplication> page = new Page<>(app.getCurrentPage(), app.getPageSize());
        List<ProjectsAndApplication> list = null;
        list = projectsAndApplicationService.searchExecutionPages(page, map);
        IPage<ProjectsAndApplication> userIPage = page.setRecords(list);
        return new Result<IPage<ProjectsAndApplication>>().sucess(userIPage);
    }

    @PostMapping(value = "/pageList/getChangePages")
    @ApiOperation(value = "项目变更审批列表", notes = "项目变更审批列表")
    Result<IPage<ProjectsAndApplication>> searchChangePages(@Valid @RequestBody @ApiParam ProjectsAndApplication app){
        Map<String,Object> map = CommonUtil.objectToMap(app);
        Page<ProjectsAndApplication> page = new Page<>(app.getCurrentPage(), app.getPageSize());
        List<ProjectsAndApplication> list = null;
        list = projectsAndApplicationService.searchChangePages(page, map);
        IPage<ProjectsAndApplication> userIPage = page.setRecords(list);
        return new Result<IPage<ProjectsAndApplication>>().sucess(userIPage);
    }

    @PostMapping(value = "/pageList/getMaintenance")
    @ApiOperation(value = "项目维护查询列表", notes = "项目维护查询列表")
    Result<IPage<ProjectsAndApplication>> searchMaintenancePages(@Valid @RequestBody @ApiParam ProjectsAndApplication app){
        Map<String,Object> map = CommonUtil.objectToMap(app);
        Page<ProjectsAndApplication> page = new Page<>(app.getCurrentPage(), app.getPageSize());
        List<ProjectsAndApplication> list = null;
        list = projectsAndApplicationService.searchMaintenancePages(page, map);
        IPage<ProjectsAndApplication> userIPage = page.setRecords(list);
        return new Result<IPage<ProjectsAndApplication>>().sucess(userIPage);
    }

}
