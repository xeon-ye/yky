package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.vo.DeptForm;
import com.deloitte.platform.api.isump.vo.OrganizationForm;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.isump.vo.UserForm;
import com.deloitte.platform.api.project.param.MaintenanceQueryForm;
import com.deloitte.platform.api.project.vo.*;
import com.deloitte.platform.api.project.client.MaintenanceClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.project.common.util.StringUtil;
import com.deloitte.services.project.entity.MaintBudget;
import com.deloitte.services.project.entity.Person;
import com.deloitte.services.project.entity.Projects;
import com.deloitte.services.project.service.IMaintBudgetService;
import com.deloitte.services.project.service.IPersonService;
import com.deloitte.services.project.service.IProjectsService;
import com.netflix.discovery.converters.Auto;
import io.swagger.annotations.*;
import jdk.nashorn.internal.ir.ReturnNode;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

import com.deloitte.services.project.service.IMaintenanceService;
import com.deloitte.services.project.entity.Maintenance;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description :   Maintenance控制器实现类
 * @Modified :
 */
@Api(tags = "Maintenance操作接口")
@Slf4j
@RestController
public class MaintenanceController implements MaintenanceClient {
    public static final String path="/project/maintenance";

    @Autowired
    public IMaintenanceService  maintenanceService;

    @Autowired
    public IProjectsService projectsService;

    @Autowired
    public IPersonService personService;

    @Autowired
    public IMaintBudgetService maintBudgetService;

    @Override
    @ApiOperation(value = "新增Maintenance", notes = "新增一个Maintenance")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="maintenanceForm",value="新增Maintenance的form表单",required=true)  MaintenanceForm maintenanceForm) {
        log.info("form:",  maintenanceForm.toString());
        Maintenance maintenance =new BeanUtils<Maintenance>().copyObj(maintenanceForm,Maintenance.class);
        return Result.success( maintenanceService.save(maintenance));
    }


    @Override
    @ApiOperation(value = "删除Maintenance", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "MaintenanceID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        maintenanceService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改Maintenance", notes = "修改指定Maintenance信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "Maintenance的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="maintenanceForm",value="修改Maintenance的form表单",required=true)  MaintenanceForm maintenanceForm) {
        Maintenance maintenance =new BeanUtils<Maintenance>().copyObj(maintenanceForm,Maintenance.class);
        maintenance.setId(id);
        maintenanceService.saveOrUpdate(maintenance);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取Maintenance", notes = "获取指定ID的Maintenance信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Maintenance的ID", required = true, dataType = "long")
    public Result<MaintenanceVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        Maintenance maintenance=maintenanceService.getById(id);
        MaintenanceVo maintenanceVo=new BeanUtils<MaintenanceVo>().copyObj(maintenance,MaintenanceVo.class);
        return new Result<MaintenanceVo>().sucess(maintenanceVo);
    }

    @Override
    @ApiOperation(value = "列表查询Maintenance", notes = "根据条件查询Maintenance列表数据")
    public Result<List<MaintenanceVo>> list(@Valid @RequestBody @ApiParam(name="maintenanceQueryForm",value="Maintenance查询参数",required=true) MaintenanceQueryForm maintenanceQueryForm) {
        log.info("search with maintenanceQueryForm:", maintenanceQueryForm.toString());
        List<Maintenance> maintenanceList=maintenanceService.selectList(maintenanceQueryForm);
        List<MaintenanceVo> maintenanceVoList=new BeanUtils<MaintenanceVo>().copyObjs(maintenanceList,MaintenanceVo.class);
        return new Result<List<MaintenanceVo>>().sucess(maintenanceVoList);
    }


    @Override
    @ApiOperation(value = "分页查询Maintenance", notes = "根据条件查询Maintenance分页数据")
    public Result<IPage<MaintenanceVo>> search(@Valid @RequestBody @ApiParam(name="maintenanceQueryForm",value="Maintenance查询参数",required=true) MaintenanceQueryForm maintenanceQueryForm) {
        log.info("search with maintenanceQueryForm:", maintenanceQueryForm.toString());
        IPage<Maintenance> maintenancePage=maintenanceService.selectPage(maintenanceQueryForm);
        IPage<MaintenanceVo> maintenanceVoPage=new BeanUtils<MaintenanceVo>().copyPageObjs(maintenancePage,MaintenanceVo.class);
        return new Result<IPage<MaintenanceVo>>().sucess(maintenanceVoPage);
    }

    @GetMapping(value = path + "/toGetMain/{projectId}")
    @ApiOperation(value = "获取MainProVoy", notes = "获取指定ID的MainProVo信息")
    @ApiImplicitParam(paramType = "path", name = "projectId", value = "项目id", required = true, dataType = "string")
    Result<MainProVo> getOneMain(@PathVariable(value="projectId") String projectId) {
        MainProVo vo = new MainProVo();
        Projects projects = projectsService.getById(NumberUtils.toLong(projectId));
        ProjectsVo projectsVo = new BeanUtils<ProjectsVo>().copyObj(projects,ProjectsVo.class);
        vo.setProjectsVo(projectsVo);

        //项目维护表
        List<Maintenance> maintenances = maintenanceService.getOneList(projectId);
        List<MaintenanceVo> maintenanceVos = new BeanUtils<MaintenanceVo>().copyObjs(maintenances,MaintenanceVo.class);
        vo.setMaintenanceVoList(maintenanceVos);
        String mainId = maintenances.get(0).getMaintenanceId();

        //项目成员
        List<Person> personList = personService.getMainList(projectId);
        List<PersonVo> personVos = new BeanUtils<PersonVo>().copyObjs(personList,PersonVo.class);
        vo.setPersonVoList(personVos);

        //支出预算
        List<MaintBudget> maintBudgetList = maintBudgetService.getAllList(mainId);
        List<MaintBudgetVo> maintBudgetVos = new BeanUtils<MaintBudgetVo>().copyObjs(maintBudgetList,MaintBudgetVo.class);
        vo.setMaintBudgetVoList(maintBudgetVos);

        return new Result<MainProVo>().sucess(vo);
    }

    @PostMapping(value = path+"/toSave")
    @ApiOperation(value = "保存", notes = "保存")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result<MainProVo> toSave(@Valid @RequestBody @ApiParam MainProVo mainProVo, UserForm userForm, OrganizationForm organizationForm) {
        mainProVo.setProjectStatusCode("7001");
        mainProVo.setProjectStatusName("未申报");
        MainProVo vo = this.getMain(mainProVo,userForm,organizationForm);
        return new Result<MainProVo>().sucess(vo);
    }


    @PostMapping(value = path+"/toSubmit")
    @ApiOperation(value = "提交", notes = "提交")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result<MainProVo> toSubmit(@Valid @RequestBody @ApiParam MainProVo mainProVo, UserForm userForm, OrganizationForm organizationForm) {
        mainProVo.setProjectStatusCode("7002");
        mainProVo.setProjectStatusName("已申报");
        MainProVo vo = this.getMain(mainProVo,userForm,organizationForm);
        return new Result<MainProVo>().sucess(vo);
    }

    public MainProVo getMain(MainProVo mainProVo, UserForm userForm, OrganizationForm organizationForm){
        MainProVo vo = new MainProVo();
        String statusCode = mainProVo.getProjectStatusCode();
        String statusName = mainProVo.getProjectStatusName();
        String projectId = mainProVo.getProjectId();

        //项目表
        Projects projects = projectsService.getById(NumberUtils.toLong(projectId));
        if(Objects.isNull(projects)){
            projects = new BeanUtils<Projects>().copyObj(mainProVo.getProjectsVo(),Projects.class);
        }
        projects.setProjectStatusName(statusName);
        projects.setProjectStatusCode(statusCode);
        projectsService.saveOrUpdate(projects);
        projectId = String.valueOf(projects.getId());

        //维护表
        List<Maintenance> maintenances = maintenanceService.getOneList(projectId);
        List<MaintenanceVo> maintenanceVos = mainProVo.getMaintenanceVoList();
        Maintenance maintenance = null;
        if(maintenances.size()>0){
            maintenance = maintenances.get(0);
            maintenance.setMainNote(maintenanceVos.get(0).getMainNote());
        }else {
            maintenance = new BeanUtils<Maintenance>().copyObj(maintenanceVos.get(0),Maintenance.class);
            maintenance.setProjectId(projectId);
        }
        maintenanceService.saveOrUpdate(maintenance);
        String maintId = String.valueOf(maintenance.getId());

        List<Maintenance> maintenances1 = maintenanceService.getOneList(projectId);
        List<MaintenanceVo> maintenanceVos1 = new BeanUtils<MaintenanceVo>().copyObjs(maintenances1,MaintenanceVo.class);
        vo.setMaintenanceVoList(maintenanceVos1);

        //项目成员
        List<Person> personList = personService.getMainList(projectId);
        List<PersonVo> personVos = mainProVo.getPersonVoList();
        if(personList.size() > 0){
            personService.deleteMainList(projectId);
        } else {
            personList = new BeanUtils<Person>().copyObjs(personVos,Person.class);
        }
        for (Person person : personList) {
            person.setProjectId(projectId);
            personService.saveOrUpdate(person);
        }
        List<Person> personList1 = personService.getMainList(projectId);
        List<PersonVo> personVos1 = new BeanUtils<PersonVo>().copyObjs(personList1,PersonVo.class);
        vo.setPersonVoList(personVos1);

        //项目支出预算
        List<MaintBudget> maintBudgetList = maintBudgetService.getAllList(maintId);
        List<MaintBudgetVo> maintBudgetVos = mainProVo.getMaintBudgetVoList();
        if(maintBudgetList.size() > 0) {
            maintBudgetService.deleteAllList(maintId);
        }else {
            maintBudgetList = new BeanUtils<MaintBudget>().copyObjs(maintBudgetVos,MaintBudget.class);
        }

        for (MaintBudget maintBudget : maintBudgetList) {
            maintBudget.setMaintId(maintId);
            maintBudgetService.saveOrUpdate(maintBudget);
        }
        List<MaintBudget> maintBudgetList1 = maintBudgetService.getAllList(maintId);
        List<MaintBudgetVo> maintBudgetVos1 = new BeanUtils<MaintBudgetVo>().copyObjs(maintBudgetList1,MaintBudgetVo.class);
        vo.setMaintBudgetVoList(maintBudgetVos1);
        return vo;
    }
}



