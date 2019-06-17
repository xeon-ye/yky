package com.deloitte.services.project.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.client.ApplicationClient;
import com.deloitte.platform.api.project.param.ApplicationQueryForm;
import com.deloitte.platform.api.project.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.project.common.enums.ProjectErrorType;
import com.deloitte.services.project.entity.Application;
import com.deloitte.services.project.service.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-26
 * @Description :   Application控制器实现类
 * @Modified :
 */
@Api(tags = "Application操作接口")
@Slf4j
@RestController
@RequestMapping("/project/application")
public class ApplicationController implements ApplicationClient {

    @Autowired
    public IApplicationService  applicationService;
    @Autowired
    private IAppDepartmentalBudgetService departmentalBudgetService;
    @Autowired
    private IAppScientificService scientificService;
    @Autowired
    private IAppEducationService educationService;

    @Override
    @ApiOperation(value = "新增Application", notes = "新增一个Application")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="applicationForm",value="新增Application的form表单",required=true)  ApplicationForm applicationForm) {
        log.info("form:",  applicationForm.toString());
        Application application =new BeanUtils<Application>().copyObj(applicationForm,Application.class);
        return Result.success( applicationService.save(application));
    }

    @Override
    @ApiOperation(value = "删除Application", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ApplicationID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        applicationService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改Application", notes = "修改指定Application信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "Application的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="applicationForm",value="修改Application的form表单",required=true)  ApplicationForm applicationForm) {
        Application application =new BeanUtils<Application>().copyObj(applicationForm,Application.class);
        application.setId(id);
        applicationService.saveOrUpdate(application);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取Application", notes = "获取指定ID的Application信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Application的ID", required = true, dataType = "long")
    public Result<ApplicationVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        Application application=applicationService.getById(id);
        ApplicationVo applicationVo=new BeanUtils<ApplicationVo>().copyObj(application,ApplicationVo.class);
        return new Result<ApplicationVo>().sucess(applicationVo);
    }

    @Override
    @ApiOperation(value = "列表查询Application", notes = "根据条件查询Application列表数据")
    public Result<List<ApplicationVo>> list(@Valid @RequestBody @ApiParam(name="applicationQueryForm",value="Application查询参数",required=true) ApplicationQueryForm applicationQueryForm) {
        log.info("search with applicationQueryForm:", applicationQueryForm.toString());
        List<Application> applicationList=applicationService.selectList(applicationQueryForm);
        List<ApplicationVo> applicationVoList=new BeanUtils<ApplicationVo>().copyObjs(applicationList,ApplicationVo.class);
        return new Result<List<ApplicationVo>>().sucess(applicationVoList);
    }


    @Override
    @ApiOperation(value = "分页查询Application", notes = "根据条件查询Application分页数据")
    public Result<IPage<ApplicationVo>> search(@Valid @RequestBody @ApiParam(name="applicationQueryForm",value="Application查询参数",required=true) ApplicationQueryForm applicationQueryForm) {
        log.info("search with applicationQueryForm:", applicationQueryForm.toString());
        IPage<Application> applicationPage=applicationService.selectPage(applicationQueryForm);
        IPage<ApplicationVo> applicationVoPage=new BeanUtils<ApplicationVo>().copyPageObjs(applicationPage,ApplicationVo.class);
        return new Result<IPage<ApplicationVo>>().sucess(applicationVoPage);
    }

    /**
     * 部门预算项目
     * 【医院服务能力建设、公共卫生专项任务经费、归口管理科学支出其他项目】
     * @param declarationsForm
     * @return
     */
    @ApiOperation(value = "新增部门预算项目【医院服务能力建设】、【公共卫生专项任务经费】、【归口管理科学支出其他项目】", notes = "新增")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @PostMapping(value = "/addDepartmentalBudget")
    public Result saveDepartmentalBudget(@Valid @RequestBody @ApiParam(name="declarationsForm",value="部门预算项目信息",required=true) DeclarationsForm declarationsForm) {
        log.info("form:", declarationsForm.toString());
        JSONObject callbackJsonObject = departmentalBudgetService.saveDepartmentalBudget(declarationsForm);
        return Result.success(callbackJsonObject);
    }

    @ApiOperation(value = "根据申报书ID获取部门预算项目【医院服务能力建设】、【公共卫生专项任务经费】、【归口管理科学支出其他项目】信息", notes = "获取")
    @ApiImplicitParam(paramType = "path", name = "applicationId", value = "applicationId", required = true, dataType = "string")
    @GetMapping(value = "/getDepartmentalBudget/{applicationId}")
    public Result<DeclarationsVO> getDepartmentalBudget(@PathVariable(value = "applicationId") String applicationId) {
        log.info("applicationId: ", applicationId);
        return new Result<DeclarationsVO>().sucess(departmentalBudgetService.getDepartmentBudget(applicationId));
    }

    @ApiOperation(value = "提交部门预算项目【医院服务能力建设】、【公共卫生专项任务经费】、【归口管理科学支出其他项目】", notes = "提交")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @PostMapping(value = "/submitDepartmentBudget")
    public Result submitDepartmentBudget(@Valid @RequestBody @ApiParam(name="declarationsForm",value="提交部门预算信息",required=true) DeclarationsForm declarationsForm) {
        log.info("declarationsForm: " + declarationsForm.toString());
        departmentalBudgetService.submitDepartmentBudget(declarationsForm);
        return Result.success();
    }

    @ApiOperation(value = "根据申报书ID导出部门预算项目【医院服务能力建设】、【公共卫生专项任务经费】、【归口管理科学支出其他项目】的PDF文件", notes = "导出PDF生成")
    @ApiImplicitParam(paramType = "path", name = "applicationId", value = "applicationId", required = true, dataType = "string")
    @PostMapping(value = "/generalBudgetPDF/{applicationId}")
    public Result generalDepartmentBudgetPDF(@PathVariable(value = "applicationId") String applicationId, HttpServletRequest request, HttpServletResponse response) {
        log.info("applicationId: {}", applicationId);
        JSONObject callback = departmentalBudgetService.generalDepartmentBudgetPDF(applicationId, request, response);
        return Result.success(callback);
    }

    /**
     * 中央级科学事业单位修缮购置项目
     * @param scientificResearchForm
     * @return
     */
    @ApiOperation(value = "新增中央级科学事业单位修缮购置项目", notes = "新增")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @PostMapping(value = "/addScientific")
    public Result saveScientificResearch(@Valid @RequestBody @ApiParam(name="scientificResearchForm",value="新增中央级科学事业单位修缮购置项目数据",required=true) ScientificResearchForm scientificResearchForm) {
        log.info("scientificResearchForm: " + scientificResearchForm.toString());
        JSONObject callback = scientificService.saveScientific(scientificResearchForm);
        return Result.success(callback);
    }

    @ApiOperation(value = "根据申报书ID获取中央级科学事业单位修缮购置项目", notes = "根据申报书ID获取")
    @ApiImplicitParam(paramType = "path", name = "applicationId", value = "applicationId", required = true, dataType = "string")
    @GetMapping(value = "/getScientific/{applicationId}")
    public Result<ScientificResearchVo> getScientificResearch(@PathVariable(value = "applicationId") String applicationId) {
        log.info("applicationId: " + applicationId);
        return new Result<ScientificResearchVo>().sucess(scientificService.getScientific(applicationId));
    }

    @ApiOperation(value = "提交中央级科学事业单位修缮购置项目", notes = "提交")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @PostMapping(value = "/submitScientific")
    public Result submitScientific(@Valid @RequestBody @ApiParam(name="scientificResearchForm",value="提交中央级科学事业单位修缮购置项目信息",required=true) ScientificResearchForm scientificResearchForm) {
        log.info("scientificResearchForm: " + scientificResearchForm.toString());
        scientificService.submitScientific(scientificResearchForm);
        return Result.success();
    }

    @ApiOperation(value = "根据申报书ID导出中央级科学事业单位修缮购置项目PDF生成文件", notes = "根据申报书ID导出PDF生成")
    @ApiImplicitParam(paramType = "path", name = "applicationId", value = "applicationId", required = true, dataType = "string")
    @PostMapping(value = "/generalScientificPDF/{applicationId}")
    public Result generalScientificPDF(@PathVariable(value = "applicationId") String applicationId, HttpServletRequest request, HttpServletResponse response) {
        log.info("applicationId: {}", applicationId);
        scientificService.generalScientificPDF(applicationId, request, response);
        return Result.success();
    }

    /**
     * 中央高校改善基本办学条件专项资金项目
     * @param educationForm
     * @return
     */
    @ApiOperation(value = "新增中央高校改善基本办学条件专项资金项目", notes = "新增")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @PostMapping(value = "/addEducation")
    public Result saveEducation(@Valid @RequestBody @ApiParam(name="educationForm",value="新增中央高校改善基本办学条件专项资金项目数据",required=true) EducationForm educationForm) {
        log.info("educationForm: " + educationForm.toString());
        JSONObject callback = educationService.saveEducation(educationForm);
        return Result.success(callback);
    }

    @ApiOperation(value = "根据申报书ID获取中央高校改善基本办学条件专项资金项目", notes = "根据申报书编号【获取】")
    @ApiImplicitParam(paramType = "path", name = "applicationId", value = "applicationId", required = true, dataType = "string")
    @GetMapping(value = "/getEducation/{applicationId}")
    public Result<EducationVo> getEducation(@PathVariable(value = "applicationId") String applicationId) {
        log.info("applicationId: " + applicationId);
        return new Result<EducationVo>().sucess(educationService.getEducation(applicationId));
    }

    @ApiOperation(value = "提交中央高校改善基本办学条件专项资金项目", notes = "【提交】")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @PostMapping(value = "/submitEducation")
    public Result submitEducation(@Valid @RequestBody @ApiParam(name="educationForm",value="中央高校改善基本办学条件专项资金项目数据",required=true) EducationForm educationForm) {
        log.info("educationForm: " + educationForm.toString());
        educationService.submitEducation(educationForm);
        return Result.success();
    }

    @ApiOperation(value = "根据申报书ID导出中央高校改善基本办学条件专项资金项目数据PDF生成文件", notes = "根据申报书编号（applicationId）导出PDF生成")
    @ApiImplicitParam(paramType = "path", name = "applicationId", value = "applicationId", required = true, dataType = "string")
    @PostMapping(value = "/generalEducationPDF/{applicationId}")
    public Result generalEducationPDF(@PathVariable(value = "applicationId") String applicationId, HttpServletRequest request, HttpServletResponse response) {
        log.info("applicationId: {}", applicationId);
        educationService.generalEducationPDF(applicationId, request, response);
        return Result.success();
    }

    @ApiOperation(value = "根据分页条件（页码、当前页显示条数）查询【项目申请-我的申请】分页数据", notes = "分页查询-我的申请")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "currentPage", value = "currentPage", required = true, dataType = "long"),
            @ApiImplicitParam(paramType = "path", name = "pageSize", value = "pageSize", required = true, dataType = "long")
    })
    @GetMapping(value = "/searchMyApplication/{currentPage}/{pageSize}")
    public Result<IPage<MyApplicationVo>> searchMyApplication(@PathVariable(value = "currentPage") Long currentPage, @PathVariable(value = "pageSize") Long pageSize) {
        IPage<MyApplicationVo> iPage = applicationService.searchMyApplication(currentPage, pageSize);
        return new Result<IPage<MyApplicationVo>>().sucess(iPage);
    }

    @ApiOperation(value = "根据项目类型Code删除指定申报书，需要指定项目状态作为判断依据", notes = "删除")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path",name = "projectTypeCode", value = "项目类型Code", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "path",name = "applicationId", value = "申报书ID", required = true, dataType = "String")
    })
    @PostMapping(value = "/removeMyApplication/{projectTypeCode}/{applicationId}")
    public Result removeMyApplication(@PathVariable(value = "projectTypeCode") String projectTypeCode, @PathVariable("applicationId") String applicationId) {
        log.info("projectTypeCode: {}", projectTypeCode);
        if (Objects.isNull(projectTypeCode) && Objects.isNull(applicationId)) {
            return Result.fail(ProjectErrorType.DATA_IS_NULL);
        } else {
            // 【医院服务能力建设】、【公共卫生专项任务经费】、【归口管理科学支出其他项目】
            if ("1001".equals(projectTypeCode) || "1002".equals(projectTypeCode) || "1003".equals(projectTypeCode)) {
                applicationService.removeApplication(applicationId);
                departmentalBudgetService.deleteDepeartalBudget(applicationId);
            }
            // 【中央级科学事业单位修缮购置项目】
            if ("1004".equals(projectTypeCode)) {
                applicationService.removeApplication(applicationId);
                scientificService.deleteScientific(applicationId);
            }
            // 【中央高校改善基本办学条件专项资金项目】
            if ("1005".equals(projectTypeCode)) {
                applicationService.removeApplication(applicationId);
                educationService.deleteEducation(applicationId);
            }
            return Result.success();
        }
    }

}



