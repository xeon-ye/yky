package com.deloitte.services.project.controller;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.client.ProjectsClient;
import com.deloitte.platform.api.project.param.ProjectsQueryForm;
import com.deloitte.platform.api.project.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.project.common.util.CommonUtil;
import com.deloitte.services.project.entity.ProjectEvaluation;
import com.deloitte.services.project.entity.Projects;
import com.deloitte.services.project.service.IProjectEvaluationService;
import com.deloitte.services.project.service.IProjectLibraryService;
import com.deloitte.services.project.service.IProjectsService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-26
 * @Description :   Projects控制器实现类
 * @Modified :
 */
@Api(tags = "Projects操作接口")
@Slf4j
@RestController
@RequestMapping("/project/projects")
public class ProjectsController implements ProjectsClient {

    @Autowired
    public IProjectsService  projectsService;
    @Autowired
    private IProjectLibraryService projectLibraryService;
    @Autowired
    private IProjectEvaluationService projectEvaluationService;

    @Override
    @ApiOperation(value = "新增Projects", notes = "新增一个Projects")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="projectsForm",value="新增Projects的form表单",required=true)  ProjectsForm projectsForm) {
        log.info("form:",  projectsForm.toString());
        Projects projects =new BeanUtils<Projects>().copyObj(projectsForm,Projects.class);
        return Result.success( projectsService.save(projects));
    }


    @Override
    @ApiOperation(value = "删除Projects", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ProjectsID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        projectsService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改Projects", notes = "修改指定Projects信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "Projects的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="projectsForm",value="修改Projects的form表单",required=true)  ProjectsForm projectsForm) {
        Projects projects =new BeanUtils<Projects>().copyObj(projectsForm,Projects.class);
        projects.setId(id);
        projectsService.saveOrUpdate(projects);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取Projects", notes = "获取指定ID的Projects信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Projects的ID", required = true, dataType = "long")
    public Result<ProjectsVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        Projects projects=projectsService.getById(id);
        ProjectsVo projectsVo=new BeanUtils<ProjectsVo>().copyObj(projects,ProjectsVo.class);
        return new Result<ProjectsVo>().sucess(projectsVo);
    }

    @Override
    @ApiOperation(value = "列表查询Projects", notes = "根据条件查询Projects列表数据")
    public Result<List<ProjectsVo>> list(@Valid @RequestBody @ApiParam(name="projectsQueryForm",value="Projects查询参数",required=true) ProjectsQueryForm projectsQueryForm) {
        log.info("search with projectsQueryForm:", projectsQueryForm.toString());
        List<Projects> projectsList=projectsService.selectList(projectsQueryForm);
        List<ProjectsVo> projectsVoList=new BeanUtils<ProjectsVo>().copyObjs(projectsList,ProjectsVo.class);
        return new Result<List<ProjectsVo>>().sucess(projectsVoList);
    }


    @Override
    @ApiOperation(value = "分页查询Projects", notes = "根据条件查询Projects分页数据")
    public Result<IPage<ProjectsVo>> search(@Valid @RequestBody @ApiParam(name="projectsQueryForm",value="Projects查询参数",required=true) ProjectsQueryForm projectsQueryForm) {
        log.info("search with projectsQueryForm:", projectsQueryForm.toString());
        IPage<Projects> projectsPage=projectsService.selectPage(projectsQueryForm);
        IPage<ProjectsVo> projectsVoPage=new BeanUtils<ProjectsVo>().copyPageObjs(projectsPage,ProjectsVo.class);
        return new Result<IPage<ProjectsVo>>().sucess(projectsVoPage);
    }

    @ApiOperation(value = "【取消项目申请】--保存", notes = "取消项目申请--【保存】")
    @PostMapping(value = "/saveCancelProject")
    public Result saveCancelProject(@Valid @RequestBody CancelProjectFrom cancelProjectFrom) {
        log.info("projectsForm: " + cancelProjectFrom.toString());
        projectsService.saveCancelProject(cancelProjectFrom);
        return Result.success();
    }

    @ApiOperation(value = "【取消项目申请】-- 提交", notes = "取消项目申请-- 【提交】")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @PostMapping(value = "/submitCancelProject")
    public Result submitCancelProject(@Valid @RequestBody CancelProjectFrom cancelProjectFrom) {
        log.info("projectsForm: " + cancelProjectFrom.toString());
        projectsService.submitCancelProject(cancelProjectFrom);
        return Result.success();
    }

    @ApiOperation(value = "【项目库】--列表查询ProjectLibrary", notes = "根据条件查询ProjectLibrary列表数据")
    @PostMapping(value = "/page/libraries")
    public Result<IPage<ProjectLibraryVo>> selectProjectLibraryList(@Valid @RequestBody @ApiParam(name="libraryQueryForm",value="ProjectLibrary查询参数",required=true) ProjectLibraryForm libraryQueryForm) {
        log.info("libraryQueryForm: {}", libraryQueryForm.toString());
        return new Result<IPage<ProjectLibraryVo>>().success(projectLibraryService.selectProjectLibraryLisPage(libraryQueryForm));
    }

    @ApiOperation(value = "【项目库】--项目库查询结果导出", notes = "根据条件查询进行Excel导出项目库，并附带查询条件导出")
    @PostMapping(value = "/export/excel")
    public Result exportExcelForProLib(@Valid @RequestBody @ApiParam(name = "libraryQueryForm", value = "libraryQueryForm查询条件参数", required = true) ProjectLibraryForm libraryQueryForm, HttpServletRequest request, HttpServletResponse response) {
        log.info("libraryQueryForm: {}", libraryQueryForm.toString());
        return Result.success(projectsService.exportExcelForProLib(libraryQueryForm, request, response));
    }

    @ApiOperation(value = "【项目评价】查询列表", notes = "项目评价查询")
    @PostMapping(value = "/page/getProjectEvaluations")
    Result<IPage<ProjectEvaluation>> searchProjectEvaluation(@Valid @RequestBody @ApiParam ProjectEvaluation projectEvaluation) {
        log.info("projectEvaluation: {}", projectEvaluation);
        Map<String, Object> map = CommonUtil.objectToMap(projectEvaluation);
        Page<ProjectEvaluation> page = new Page<>(projectEvaluation.getCurrentPage(), projectEvaluation.getPageSize());
        List<ProjectEvaluation> projectsAndApplicationList = projectEvaluationService.searchProjectEvaluation(page, map);
        IPage<ProjectEvaluation> iPage = page.setRecords(projectsAndApplicationList);
        return new Result<IPage<ProjectEvaluation>>().sucess(iPage);
    }

    @ApiOperation(value = "【项目评价】-撤回，结项 更改Projects的状态", notes = "【项目评价】-撤回，结项 更改Projects的状态")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "projectIds", value = "Projects的ID", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "path", name = "projectTypeCode", value = "projectTypeCode", required = true, dataType = "String")
    })
    @PostMapping(value = "/exchangeProjectStatus/{projectIds}/{projectTypeCode}")
    public Result<JSONArray> exchangeProjectStatus(@PathVariable(value = "projectIds") String projectIds, @PathVariable(value = "projectTypeCode") String projectTypeCode) {
        log.info("projectIds: {}", projectIds);
        JSONArray callback = projectsService.exchangeProjectStatus(projectIds, projectTypeCode);
        return Result.success(callback);
    }


}



