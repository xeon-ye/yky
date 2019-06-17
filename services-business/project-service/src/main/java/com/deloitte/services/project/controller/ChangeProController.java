package com.deloitte.services.project.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.client.ChangeProClient;
import com.deloitte.platform.api.project.param.ChangeProQueryForm;
import com.deloitte.platform.api.project.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.project.common.enums.ValueEnums;
import com.deloitte.services.project.common.util.DateUtil;
import com.deloitte.services.project.entity.*;
import com.deloitte.services.project.service.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description :   ChangePro控制器实现类
 * @Modified :
 */
@Api(tags = "ChangePro操作接口")
@Slf4j
@RestController
public class ChangeProController implements ChangeProClient {
    @Autowired
    public IChangeProService  changeProService;

    @Autowired
    public IProjectsService projectsService;

    @Autowired
    public IApplicationService applicationService;

    @Autowired
    public IReplyService replyService;

    @Autowired
    public IChangeNoteService changeNoteService;

    @Autowired
    public IActService actService;
    @Autowired
    public ISubactService subactService;
    @Autowired
    public IActBakService actBakService;
    @Autowired
    public ISubactBakService subactBakService;

    @Autowired
    public IBudgetService budgetService;
    @Autowired
    public IBudgetBakService budgetBakService;
    @Autowired
    public IEconomicService economicService;
    @Autowired
    public IChangeBudgetBakService changeBudgetBakService;

    @Autowired
    public IPerformanceService performanceService;
    @Autowired
    public IPerformanceBakService performanceBakService;

    @Autowired
    public IPersonService personService;
    @Autowired
    public IPersonBakService personBakService;
    @Autowired
    public IMaintBudgetService maintBudgetService;
    @Autowired
    public IMaintReplyService maintReplyService;

    @Override
    @ApiOperation(value = "新增ChangePro", notes = "新增一个ChangePro")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="changeProForm",value="新增ChangePro的form表单",required=true)  ChangeProForm changeProForm) {
        log.info("form:",  changeProForm.toString());
        ChangePro changePro =new BeanUtils<ChangePro>().copyObj(changeProForm,ChangePro.class);
        return Result.success( changeProService.save(changePro));
    }

    @Override
    @ApiOperation(value = "删除ChangePro", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ChangeProID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        changeProService.removeById(id);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "修改ChangePro", notes = "修改指定ChangePro信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "ChangePro的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="changeProForm",value="修改ChangePro的form表单",required=true)  ChangeProForm changeProForm) {
        ChangePro changePro =new BeanUtils<ChangePro>().copyObj(changeProForm,ChangePro.class);
        changePro.setId(id);
        changeProService.saveOrUpdate(changePro);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "获取ChangePro", notes = "获取指定ID的ChangePro信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ChangePro的ID", required = true, dataType = "long")
    public Result<ChangeProVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        ChangePro changePro=changeProService.getById(id);
        ChangeProVo changeProVo=new BeanUtils<ChangeProVo>().copyObj(changePro,ChangeProVo.class);
        return new Result<ChangeProVo>().sucess(changeProVo);
    }

    @Override
    @ApiOperation(value = "列表查询ChangePro", notes = "根据条件查询ChangePro列表数据")
    public Result<List<ChangeProVo>> list(@Valid @RequestBody @ApiParam(name="changeProQueryForm",value="ChangePro查询参数",required=true) ChangeProQueryForm changeProQueryForm) {
        log.info("search with changeProQueryForm:", changeProQueryForm.toString());
        List<ChangePro> changeProList=changeProService.selectList(changeProQueryForm);
        List<ChangeProVo> changeProVoList=new BeanUtils<ChangeProVo>().copyObjs(changeProList,ChangeProVo.class);
        return new Result<List<ChangeProVo>>().sucess(changeProVoList);
    }


    @Override
    @ApiOperation(value = "分页查询ChangePro", notes = "根据条件查询ChangePro分页数据")
    public Result<IPage<ChangeProVo>> search(@Valid @RequestBody @ApiParam(name="changeProQueryForm",value="ChangePro查询参数",required=true) ChangeProQueryForm changeProQueryForm) {
        log.info("search with changeProQueryForm:", changeProQueryForm.toString());
        IPage<ChangePro> changeProPage=changeProService.selectPage(changeProQueryForm);
        IPage<ChangeProVo> changeProVoPage=new BeanUtils<ChangeProVo>().copyPageObjs(changeProPage,ChangeProVo.class);
        return new Result<IPage<ChangeProVo>>().sucess(changeProVoPage);
    }

    @Override
    @ApiOperation(value = "项目变更跳转", notes = "项目变更跳转")
    @ApiImplicitParam(paramType = "path", name = "projectId", value = "项目的ID", required = true, dataType = "string")
    public Result<ProjectChangeVo> getOneChange(@PathVariable(value = "projectId") String projectId) {
        ProjectChangeVo vo = new ProjectChangeVo();
        Projects projects = projectsService.getById(projectId);
        ProjectsVo projectsVo = new BeanUtils<ProjectsVo>().copyObj(projects,ProjectsVo.class);
        vo.setProjectsVo(projectsVo);

        String projectMark = projects.getProjectMark();
        String mainId = "";
        String replyId ="";
        String mainMark ="80002"; //项目维护默认是否
        //项目维护
        if(StringUtils.isNotEmpty(projectMark)){
            Map map = new HashMap();
            map.put("projectId",projectId);
            map.put("replyStatusCode",2008);
            MaintReply maintReply = maintReplyService.getNewMainReply(map);
            mainId = maintReply.getMaintId();
            MaintReplyVo maintReplyVo = new BeanUtils<MaintReplyVo>().copyObj(maintReply,MaintReplyVo.class);
            vo.setMaintReplyVo(maintReplyVo);
            mainMark = "80001";
            vo.setProjectId(projectId);
            vo.setMainId(mainId);

            List<ChangePro> changeProList = changeProService.getChangeByMaintenceId(mainId);
            if(changeProList.size() > 0) {
                List<ChangeProVo> changeProVo = new BeanUtils<ChangeProVo>().copyObjs(changeProList,ChangeProVo.class);
                vo.setChangeProVoList(changeProVo);
            }else {
                ChangePro changePro = new ChangePro();
                changePro.setProjectId(projectId);
                changePro.setMaintenceid(mainId);
                changePro.setChangeStatusCode(ValueEnums.SUB_CODE_APP.getCode());
                changePro.setChangeStatusName(ValueEnums.SUB_CODE_APP.getValue());
                changeProService.save(changePro);
                changePro.setChangeId(String.valueOf(changePro.getId()));
                changeProService.updateById(changePro);
            }
        }else {
            Map map = new HashMap();
            map.put("projectId",projectId);
            map.put("replyStatusCode",2008);
            Reply reply = replyService.getNewRep(map);
            replyId = reply.getReplyDocumentId();
            ReplyVo replyVo = new BeanUtils<ReplyVo>().copyObj(reply,ReplyVo.class);
            vo.setReplyVo(replyVo);
            vo.setReplyId(replyId);

            List<ChangePro> changeProList = changeProService.getChangeByReplyId(replyId);
            if(changeProList.size() > 0) {
                List<ChangeProVo> changeProVo = new BeanUtils<ChangeProVo>().copyObjs(changeProList,ChangeProVo.class);
                vo.setChangeProVoList(changeProVo);
            }else {
                ChangePro changePro = new ChangePro();
                changePro.setProjectId(projectId);
                changePro.setReplyId(replyId);
                changePro.setChangeStatusCode(ValueEnums.SUB_CODE_APP.getCode());
                changePro.setChangeStatusName(ValueEnums.SUB_CODE_APP.getValue());
                changeProService.save(changePro);
                changePro.setChangeId(String.valueOf(changePro.getId()));
                changeProService.updateById(changePro);
            }
        }

        //记录表 查询项目所有的
        List<ChangeNote> changeNotes = changeNoteService.getOneChangeNote(projectId);
        if(changeNotes.size() >0) {
            List<ChangeNoteVo> changeNoteVos = new BeanUtils<ChangeNoteVo>().copyObjs(changeNotes,ChangeNoteVo.class);
            vo.setChangeNoteVoList(changeNoteVos);
        }

        //项目维护
        vo.setMainMark(mainMark);
        if("80001".equals(mainMark)){
            List<MaintBudget> maintBudgetList = maintBudgetService.getAllList(mainId);
            List<MaintBudgetVo> maintBudgetVos = new BeanUtils<MaintBudgetVo>().copyObjs(maintBudgetList,MaintBudgetVo.class);
            vo.setMaintBudgetVos(maintBudgetVos);

            List<Person> personList = personService.getMainList(projectId);
            List<PersonVo> personVos = new BeanUtils<PersonVo>().copyObjs(personList,PersonVo.class);
            vo.setPersonVoList(personVos);
        } else {
            //支出计划
            List<Act> actList = actService.getRepActList(replyId);
            List<SubactVo> subactVoList = new ArrayList<>();
            if(actList.size() > 0) {
                for(Act act: actList){
                    List<Subact> subacts =subactService.getSubActList(act.getActId());
                    if(subacts.size() > 0) {
                        for (Subact subact : subacts) {
                            SubactVo subactVo = new BeanUtils<SubactVo>().copyObj(subact,SubactVo.class);
                            subactVoList.add(subactVo);
                        }
                    }
                }
                List<ActVo> actVoList = new BeanUtils<ActVo>().copyObjs(actList,ActVo.class);
                vo.setActVoList(actVoList);
                vo.setSubactsVoList(subactVoList);
            }

            //预算明细
            JSONObject jsonObject = new JSONObject();
            Map map = new HashMap();
            map.put("replyId",replyId);
            map.put("budgetYear",String.valueOf(DateUtil.getCurrentYear()));
            List<Budget> budgets = budgetService.getRepBudgetMap(map);
            if(budgets.size() > 0) {
                List<BudgetVo> budgetVos = new BeanUtils<BudgetVo>().copyObjs(budgets,BudgetVo.class);
                jsonObject.put(String.valueOf(DateUtil.getCurrentYear()),budgetVos);
            }
            vo.setBudgetJsonObject(jsonObject);

            //绩效
            List<Performance> performanceList = performanceService.getRepPerformanceList(replyId);
            List<PerformanceVo> performanceVos = new BeanUtils<PerformanceVo>().copyObjs(performanceList, PerformanceVo.class);
            vo.setPerformanceVoList(performanceVos);

            //人员
            List<Person> personList = personService.getReplyList(replyId);
            List<PersonVo> personVos = new BeanUtils<PersonVo>().copyObjs(personList,PersonVo.class);
            vo.setPersonVoList(personVos);
        }

        return new Result<ProjectChangeVo>().sucess(vo);
    }

    @PostMapping(value = "/changeNoteHis/toGetList")
    @ApiOperation(value = "变更历史查询明细", notes = "变更历史查询明细")
    public Result searchChangePages(@Valid @RequestBody @ApiParam ChangeNoteVo changeNoteVo){
        ProjectChangeVo vo = this.getNoteHis(changeNoteVo);
        return  new Result().sucess(vo);
    }


    @PostMapping(value = "/project/changePro/toSubmit")
    @ApiOperation(value = "提交", notes = "提交")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result toSubmit(@Valid @RequestBody @ApiParam ProjectChangeVo projectChangeVo) {
        String projectId = projectChangeVo.getProjectId();
        String mainId ="";
        String replyId = "";
        String changeId = "";
        String mainMark = projectChangeVo.getMainMark();
        List<ChangePro> changeProList = null;
        ChangeProVo changeProVo = projectChangeVo.getChangeProVoList().get(0);
        //项目维护
        if("80001".equals(mainMark)) {
            mainId = projectChangeVo.getMainId();
            changeProList = changeProService.getChangeByMaintenceId(mainId);
            for (ChangePro changePro : changeProList) {
                changeId = changePro.getChangeId();
                changePro.setChangeCode(changeProVo.getChangeCode());
                changePro.setChangeName(changeProVo.getChangeName());
                changePro.setChangeAdv(changeProVo.getChangeAdv());
                changePro.setChangeStatusCode(ValueEnums.SUB_CODE_PAS.getCode());
                changePro.setChangeStatusName(ValueEnums.SUB_CODE_PAS.getValue());
                changePro.setMaintenceid(mainId);
                changeProService.updateById(changePro);
            }
        } else {
            replyId = projectChangeVo.getReplyId();
            changeProList = changeProService.getChangeByReplyId(replyId);
            for (ChangePro changePro : changeProList) {
                changeId = changePro.getChangeId();
                changePro.setChangeCode(changeProVo.getChangeCode());
                changePro.setChangeName(changeProVo.getChangeName());
                changePro.setChangeAdv(changeProVo.getChangeAdv());
                changePro.setChangeStatusCode(ValueEnums.SUB_CODE_PAS.getCode());
                changePro.setChangeStatusName(ValueEnums.SUB_CODE_PAS.getValue());
                changePro.setReplyId(replyId);
                //业务单号
                if(StringUtils.isBlank(changePro.getChangeName())){
                    Reply reply = replyService.getById(replyId);
                    String service_num = reply.getServiceNum();
                    service_num.replaceAll("REP","CHR");
                    changePro.setServiceNum(service_num);
                }
                changeProService.updateById(changePro);
            }
        }

        //历史表 提交就新增
        ChangeNote changeNote = new ChangeNote();
        changeNote.setChangeStatusCode(changeProVo.getChangeCode());
        changeNote.setChangeStatusName(changeProVo.getChangeName());
        changeNote.setChangeAdv(changeProVo.getChangeAdv());
        changeNote.setProjectId(projectId);
        //设置批复id或者维护id
        changeNote.setMaintid(mainId);
        changeNote.setReplyId(replyId);
        changeNoteService.save(changeNote);

        //保存备份表
        changeProService.toSaveBak(projectChangeVo);

        ProjectChangeVo vo = new ProjectChangeVo();
        return new Result().sucess(vo);
    }


    @PostMapping(value ="/project/changePro/toSave")
    @ApiOperation(value = "保存", notes = "保存")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result toSave(@Valid @RequestBody @ApiParam ProjectChangeVo projectChangeVo) {
        String mainId ="";
        String replyId = "";
        String mainMark = projectChangeVo.getMainMark();
        List<ChangePro> changeProList = null;
        ChangeProVo changeProVo = projectChangeVo.getChangeProVoList().get(0);
        //项目维护
        if("80001".equals(mainMark)) {
            mainId = projectChangeVo.getMainId();
            changeProList = changeProService.getChangeByMaintenceId(mainId);
            for (ChangePro changePro : changeProList) {
                changePro.setChangeCode(changeProVo.getChangeCode());
                changePro.setChangeName(changeProVo.getChangeName());
                changePro.setChangeAdv(changeProVo.getChangeAdv());
                changeProService.updateById(changePro);
            }
        } else {
            replyId = projectChangeVo.getReplyId();
            changeProList = changeProService.getChangeByReplyId(replyId);
            for (ChangePro changePro : changeProList) {
                changePro.setChangeCode(changeProVo.getChangeCode());
                changePro.setChangeName(changeProVo.getChangeName());
                changePro.setChangeAdv(changeProVo.getChangeAdv());
                //业务单号
                if(StringUtils.isBlank(changePro.getServiceNum())){
                    Reply reply = replyService.getById(replyId);
                    String service_num = reply.getServiceNum();
                    service_num.replaceAll("REP","CHR");
                    changePro.setServiceNum(service_num);
                }
                changeProService.updateById(changePro);
            }
        }

        //保存备份表
        changeProService.toSaveBak(projectChangeVo);
        return new Result().sucess(projectChangeVo);
    }

    public ProjectChangeVo getNoteHis(ChangeNoteVo changeNoteVo) {
        ProjectChangeVo vo = new ProjectChangeVo();
        String projectId = changeNoteVo.getProjectId();
        Projects projects = projectsService.getById(projectId);
        ProjectsVo projectsVo = new BeanUtils<ProjectsVo>().copyObj(projects,ProjectsVo.class);
        vo.setProjectsVo(projectsVo);

        String projectMark = projects.getProjectMark();
        String mainId = changeNoteVo.getMaintid();
        String replyId = changeNoteVo.getReplyId();
        String mainMark ="80002"; //项目维护默认是否
        //项目维护
        if(StringUtils.isNotEmpty(projectMark)){
            mainMark= projectMark;
            List<ChangePro> changeProList = changeProService.getChangeByMaintenceId(mainId);
            if(changeProList.size() > 0) {
                List<ChangeProVo> changeProVo = new BeanUtils<ChangeProVo>().copyObjs(changeProList,ChangeProVo.class);
                vo.setChangeProVoList(changeProVo);
            }
        }else {
            List<ChangePro> changeProList = changeProService.getChangeByReplyId(replyId);
            if(changeProList.size() > 0) {
                List<ChangeProVo> changeProVo = new BeanUtils<ChangeProVo>().copyObjs(changeProList,ChangeProVo.class);
                vo.setChangeProVoList(changeProVo);
            }
        }

        //项目维护
        vo.setMainMark(mainMark);
        if("80001".equals(mainMark)){
            List<MaintBudget> maintBudgetList = maintBudgetService.getAllList(mainId);
            List<MaintBudgetVo> maintBudgetVos = new BeanUtils<MaintBudgetVo>().copyObjs(maintBudgetList,MaintBudgetVo.class);
            vo.setMaintBudgetVos(maintBudgetVos);

            List<Person> personList = personService.getMainList(projectId);
            List<PersonVo> personVos = new BeanUtils<PersonVo>().copyObjs(personList,PersonVo.class);
            vo.setPersonVoList(personVos);
        } else {
            //支出计划
            List<Act> actList =  actService.getRepActList(replyId);
            List<SubactVo> subactVoList = new ArrayList<>();
            if(actList.size() > 0) {
                for(Act act: actList){
                    List<Subact> subacts =subactService.getSubActList(act.getActId());
                    if(subacts.size() > 0) {
                        for (Subact subact : subacts) {
                            SubactVo subactVo = new BeanUtils<SubactVo>().copyObj(subact,SubactVo.class);
                            subactVoList.add(subactVo);
                        }
                    }
                }
                List<ActVo> actVoList = new BeanUtils<ActVo>().copyObjs(actList,ActVo.class);
                vo.setActVoList(actVoList);
                vo.setSubactsVoList(subactVoList);
            }

            //预算明细
            JSONObject jsonObject = new JSONObject();
            Map map = new HashMap();
            map.put("replyId",replyId);
            map.put("budgetYear",String.valueOf(DateUtil.getCurrentYear()));
            List<Budget> budgets = budgetService.getRepBudgetMap(map);
            if(budgets.size() > 0) {
                List<BudgetVo> budgetVos = new BeanUtils<BudgetVo>().copyObjs(budgets,BudgetVo.class);
                jsonObject.put(String.valueOf(DateUtil.getCurrentYear()),budgetVos);
            }
            vo.setBudgetJsonObject(jsonObject);

            //绩效
            List<Performance> performanceList = performanceService.getRepPerformanceList(replyId);
            List<PerformanceVo> performanceVos = new BeanUtils<PerformanceVo>().copyObjs(performanceList, PerformanceVo.class);
            vo.setPerformanceVoList(performanceVos);

            //人员
            List<Person> personList = personService.getReplyList(replyId);
            List<PersonVo> personVos = new BeanUtils<PersonVo>().copyObjs(personList,PersonVo.class);
            vo.setPersonVoList(personVos);
        }

        return vo;
    }
}



