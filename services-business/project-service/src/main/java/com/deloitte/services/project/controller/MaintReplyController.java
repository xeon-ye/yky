package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.vo.OrganizationForm;
import com.deloitte.platform.api.isump.vo.UserForm;
import com.deloitte.platform.api.project.param.MaintReplyQueryForm;
import com.deloitte.platform.api.project.vo.*;
import com.deloitte.platform.api.project.client.MaintReplyClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.project.entity.*;
import com.deloitte.services.project.service.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description :   MaintReply控制器实现类
 * @Modified :
 */
@Api(tags = "MaintReply操作接口")
@Slf4j
@RestController
public class MaintReplyController implements MaintReplyClient {
    public static final String path="/project/maint-reply";

    @Autowired
    public IMaintReplyService  maintReplyService;

    @Autowired
    public IMaintenanceService maintenanceService;

    @Autowired
    public IProjectsService projectsService;

    @Autowired
    public IPersonService personService;

    @Autowired
    public IMaintBudgetService maintBudgetService;

    @Autowired
    public IMaintReplyNoteService maintReplyNoteService;

    @Override
    @ApiOperation(value = "新增MaintReply", notes = "新增一个MaintReply")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="maintReplyForm",value="新增MaintReply的form表单",required=true)  MaintReplyForm maintReplyForm) {
        log.info("form:",  maintReplyForm.toString());
        MaintReply maintReply =new BeanUtils<MaintReply>().copyObj(maintReplyForm,MaintReply.class);
        return Result.success( maintReplyService.save(maintReply));
    }


    @Override
    @ApiOperation(value = "删除MaintReply", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "MaintReplyID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        maintReplyService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改MaintReply", notes = "修改指定MaintReply信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "MaintReply的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="maintReplyForm",value="修改MaintReply的form表单",required=true)  MaintReplyForm maintReplyForm) {
        MaintReply maintReply =new BeanUtils<MaintReply>().copyObj(maintReplyForm,MaintReply.class);
        maintReply.setId(id);
        maintReplyService.saveOrUpdate(maintReply);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取MaintReply", notes = "获取指定ID的MaintReply信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "MaintReply的ID", required = true, dataType = "long")
    public Result<MaintReplyVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        MaintReply maintReply=maintReplyService.getById(id);
        MaintReplyVo maintReplyVo=new BeanUtils<MaintReplyVo>().copyObj(maintReply,MaintReplyVo.class);
        return new Result<MaintReplyVo>().sucess(maintReplyVo);
    }

    @Override
    @ApiOperation(value = "列表查询MaintReply", notes = "根据条件查询MaintReply列表数据")
    public Result<List<MaintReplyVo>> list(@Valid @RequestBody @ApiParam(name="maintReplyQueryForm",value="MaintReply查询参数",required=true) MaintReplyQueryForm maintReplyQueryForm) {
        log.info("search with maintReplyQueryForm:", maintReplyQueryForm.toString());
        List<MaintReply> maintReplyList=maintReplyService.selectList(maintReplyQueryForm);
        List<MaintReplyVo> maintReplyVoList=new BeanUtils<MaintReplyVo>().copyObjs(maintReplyList,MaintReplyVo.class);
        return new Result<List<MaintReplyVo>>().sucess(maintReplyVoList);
    }


    @Override
    @ApiOperation(value = "分页查询MaintReply", notes = "根据条件查询MaintReply分页数据")
    public Result<IPage<MaintReplyVo>> search(@Valid @RequestBody @ApiParam(name="maintReplyQueryForm",value="MaintReply查询参数",required=true) MaintReplyQueryForm maintReplyQueryForm) {
        log.info("search with maintReplyQueryForm:", maintReplyQueryForm.toString());
        IPage<MaintReply> maintReplyPage=maintReplyService.selectPage(maintReplyQueryForm);
        IPage<MaintReplyVo> maintReplyVoPage=new BeanUtils<MaintReplyVo>().copyPageObjs(maintReplyPage,MaintReplyVo.class);
        return new Result<IPage<MaintReplyVo>>().sucess(maintReplyVoPage);
    }


    @GetMapping(value = path + "/{projectId}")
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

        //项目审批
        List<MaintReply> maintReplies = maintReplyService.getAllList(projectId);
        if(maintReplies.size() > 0) {
            List<MaintReplyVo> maintReplyVos = new BeanUtils<MaintReplyVo>().copyObjs(maintReplies,MaintReplyVo.class);
            vo.setMaintReplyVoList(maintReplyVos);

            List<MaintReplyNote> maintReplyNotes = maintReplyNoteService.getAllList(projectId);
            List<MaintReplyNoteVo> maintReplyNoteVos = new BeanUtils<MaintReplyNoteVo>().copyObjs(maintReplyNotes,MaintReplyNoteVo.class);
            vo.setMaintReplyNoteVos(maintReplyNoteVos);
        } else {
            MaintReply maintReply = new MaintReply();
            maintReply.setProjectId(projectId);
            maintReplyService.save(maintReply);
        }

        return new Result<MainProVo>().sucess(vo);
    }

    @PostMapping(value = path+"/toSave")
    @ApiOperation(value = "保存", notes = "保存")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result<MainProVo> toSave(@Valid @RequestBody @ApiParam MainProVo mainProVo, UserForm userForm, OrganizationForm organizationForm) {
        mainProVo.setProjectStatusCode("7003");
        mainProVo.setProjectStatusName("待批复");
        MainProVo vo = this.getMain(mainProVo,userForm,organizationForm);
        return new Result<MainProVo>().sucess(vo);
    }


    @PostMapping(value = path+"/toSubmit")
    @ApiOperation(value = "提交", notes = "提交")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result<MainProVo> toSubmit(@Valid @RequestBody @ApiParam MainProVo mainProVo, UserForm userForm, OrganizationForm organizationForm) {
        mainProVo.setProjectStatusCode("7004");
        mainProVo.setProjectStatusName("已立项");
        MainProVo vo = this.getMain(mainProVo,userForm,organizationForm);
        return new Result<MainProVo>().sucess(vo);
    }

    public MainProVo getMain(MainProVo mainProVo, UserForm userForm, OrganizationForm organizationForm){
        String projectId = mainProVo.getProjectId();

        List<MaintReply> maintReplies = maintReplyService.getAllList(projectId);
        MaintReplyVo maintReplyVo = mainProVo.getMaintReplyVoList().get(0);
        for (MaintReply maintReply : maintReplies) {
            maintReply.setReplyCode(maintReplyVo.getReplyCode());
            maintReply.setReplyName(maintReplyVo.getReplyName());
            maintReply.setReplyAdvice(maintReplyVo.getReplyAdvice());
            maintReplyService.updateById(maintReply);
        }

        MaintReplyNote maintReplyNote = new MaintReplyNote();
        maintReplyNote.setProjectId(projectId);

        maintReplyNoteService.save(maintReplyNote);

        return mainProVo;
    }

}



