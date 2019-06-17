package com.deloitte.services.project.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.client.ExeReplyClient;
import com.deloitte.platform.api.project.param.ExeReplyQueryForm;
import com.deloitte.platform.api.project.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.project.common.util.DateUtil;
import com.deloitte.services.project.entity.*;
import com.deloitte.services.project.service.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-17
 * @Description :   ExeReply控制器实现类
 * @Modified :
 */
@Api(tags = "ExeReply操作接口")
@Slf4j
@RestController
public class ExeReplyController implements ExeReplyClient {
    public static final String path="/project/exeReply";

    @Autowired
    public IExeReplyService  exeReplyService;
    @Autowired
    public IChangeProService changeProService;
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
    public IBudgetService budgetService;
    @Autowired
    public IBudgetBakService budgetBakService;
    @Autowired
    public ISubactBakService subactBakService;
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
    public IExeRepHisService exeRepHisService;
    @Autowired
    public IMaintBudgetService maintBudgetService;
    @Autowired
    public IMaintReplyService maintReplyService;


    @Override
    @ApiOperation(value = "新增ExeReply", notes = "新增一个ExeReply")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="exeReplyForm",value="新增ExeReply的form表单",required=true)  ExeReplyForm exeReplyForm) {
        log.info("form:",  exeReplyForm.toString());
        ExeReply exeReply =new BeanUtils<ExeReply>().copyObj(exeReplyForm,ExeReply.class);
        return Result.success( exeReplyService.save(exeReply));
    }


    @Override
    @ApiOperation(value = "删除ExeReply", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ExeReplyID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        exeReplyService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改ExeReply", notes = "修改指定ExeReply信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "ExeReply的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="exeReplyForm",value="修改ExeReply的form表单",required=true)  ExeReplyForm exeReplyForm) {
        ExeReply exeReply =new BeanUtils<ExeReply>().copyObj(exeReplyForm,ExeReply.class);
        exeReply.setId(id);
        exeReplyService.saveOrUpdate(exeReply);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取ExeReply", notes = "获取指定ID的ExeReply信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ExeReply的ID", required = true, dataType = "long")
    public Result<ExeReplyVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        ExeReply exeReply=exeReplyService.getById(id);
        ExeReplyVo exeReplyVo=new BeanUtils<ExeReplyVo>().copyObj(exeReply,ExeReplyVo.class);
        return new Result<ExeReplyVo>().sucess(exeReplyVo);
    }

    @Override
    @ApiOperation(value = "列表查询ExeReply", notes = "根据条件查询ExeReply列表数据")
    public Result<List<ExeReplyVo>> list(@Valid @RequestBody @ApiParam(name="exeReplyQueryForm",value="ExeReply查询参数",required=true) ExeReplyQueryForm exeReplyQueryForm) {
        log.info("search with exeReplyQueryForm:", exeReplyQueryForm.toString());
        List<ExeReply> exeReplyList=exeReplyService.selectList(exeReplyQueryForm);
        List<ExeReplyVo> exeReplyVoList=new BeanUtils<ExeReplyVo>().copyObjs(exeReplyList,ExeReplyVo.class);
        return new Result<List<ExeReplyVo>>().sucess(exeReplyVoList);
    }


    @Override
    @ApiOperation(value = "分页查询ExeReply", notes = "根据条件查询ExeReply分页数据")
    public Result<IPage<ExeReplyVo>> search(@Valid @RequestBody @ApiParam(name="exeReplyQueryForm",value="ExeReply查询参数",required=true) ExeReplyQueryForm exeReplyQueryForm) {
        log.info("search with exeReplyQueryForm:", exeReplyQueryForm.toString());
        IPage<ExeReply> exeReplyPage=exeReplyService.selectPage(exeReplyQueryForm);
        IPage<ExeReplyVo> exeReplyVoPage=new BeanUtils<ExeReplyVo>().copyPageObjs(exeReplyPage,ExeReplyVo.class);
        return new Result<IPage<ExeReplyVo>>().sucess(exeReplyVoPage);
    }

    @GetMapping(path + "/toGetList/{projectId}")
    @ApiOperation(value = "项目变更审批跳转", notes = "项目变更审批跳转")
    @ApiImplicitParam(paramType = "path", name = "projectId", value = "申报书的ID", required = true, dataType = "long")
    public Result<ProjectChangeVo> getOneChange(@PathVariable(value = "projectId") String projectId) {
        ProjectChangeVo vo = new ProjectChangeVo();
        Projects projects = projectsService.getById(projectId);
        ProjectsVo projectsVo = new BeanUtils<ProjectsVo>().copyObj(projects,ProjectsVo.class);
        vo.setProjectsVo(projectsVo);

        String projectMark = projects.getProjectMark(); //是否是项目维护
        String mainId = ""; //项目维护id
        String replyId =""; //项目批复id
        String mainMark ="80002";
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
            vo.setMainMark(mainMark);
            List<ChangePro> changeProList = changeProService.getChangeByMaintenceId(mainId);
            if(changeProList.size() > 0) {
                List<ChangeProVo> changeProVoList = new BeanUtils<ChangeProVo>().copyObjs(changeProList,ChangeProVo.class);
                vo.setChangeProVoList(changeProVoList);
            }

            //审批表
            List<ExeReply> exeReplies = exeReplyService.getAllByMainId(mainId);
            if(exeReplies.size() > 0) {
                List<ExeReplyVo> exeReplyVos = new BeanUtils<ExeReplyVo>().copyObjs(exeReplies,ExeReplyVo.class);
                vo.setExeReplyVos(exeReplyVos);
                String exeReplyId = exeReplyVos.get(0).getId();
                List<ExeRepHis> exeRepHis = exeRepHisService.getAllList(exeReplyId);
                if(exeRepHis.size() >0) {
                    List<ExeRepHisVo> exeRepHisVos = new BeanUtils<ExeRepHisVo>().copyObjs(exeRepHis,ExeRepHisVo.class);
                    vo.setExeRepHisVos(exeRepHisVos);
                }
            } else {
                ExeReply exeReply = new ExeReply();
                exeReply.setMaintenanceId(mainId);
                exeReplyService.save(exeReply);
                exeReply.setExeReplyId(String.valueOf(exeReply.getId()));
                exeReplyService.updateById(exeReply);
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
            vo.setProjectId(projectId);
            vo.setMainMark(mainMark);
            List<ChangePro> changeProList = changeProService.getChangeByReplyId(replyId);
            if(changeProList.size() > 0) {
                List<ChangeProVo> changeProVoList = new BeanUtils<ChangeProVo>().copyObjs(changeProList,ChangeProVo.class);
                vo.setChangeProVoList(changeProVoList);
            }
            //审批表
            List<ExeReply> exeReplies = exeReplyService.getAllByRepId(replyId);
            if(exeReplies.size() > 0) {
                List<ExeReplyVo> exeReplyVos = new BeanUtils<ExeReplyVo>().copyObjs(exeReplies,ExeReplyVo.class);
                vo.setExeReplyVos(exeReplyVos);
                String exeReplyId = exeReplyVos.get(0).getId();
                List<ExeRepHis> exeRepHis = exeRepHisService.getAllList(exeReplyId);
                if(exeRepHis.size() >0) {
                    List<ExeRepHisVo> exeRepHisVos = new BeanUtils<ExeRepHisVo>().copyObjs(exeRepHis,ExeRepHisVo.class);
                    vo.setExeRepHisVos(exeRepHisVos);
                }
            } else {
                ExeReply exeReply = new ExeReply();
                exeReply.setReplyId(replyId);
                exeReplyService.save(exeReply);
                exeReply.setExeReplyId(String.valueOf(exeReply.getId()));
                exeReplyService.updateById(exeReply);
            }
        }

       //不是项目维护
        if("80002".equals(mainMark)) {
            //计划支出
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

            List<ActBak> actBakList = actBakService.getListByRepId(replyId);
            List<SubactBakVo> subactBakVos = new ArrayList<>();
            if(actBakList.size() > 0) {
                for (ActBak actBak : actBakList) {
                    List<SubactBak> subactBaks = subactBakService.getAllList(String.valueOf(actBak.getId()));
                    if(subactBaks.size() >0) {
                        for (SubactBak subactBak : subactBaks) {
                            SubactBakVo subactBakVo = new BeanUtils<SubactBakVo>().copyObj(subactBak,SubactBakVo.class);
                            subactBakVos.add(subactBakVo);
                        }
                    }
                }
                List<ActBakVo> actBakVos = new BeanUtils<ActBakVo>().copyObjs(actBakList,ActBakVo.class);
                vo.setActBakVoList(actBakVos);
                vo.setSubBakLists(subactBakVos);
            }

            //预算
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

            JSONObject jsonObject1 = new JSONObject();
            List<BudgetBak> budgetBaks = budgetBakService.getListRep(map);
            if(budgetBaks.size() > 0) {
                List<BudgetBakVo> budgetBakVos = new BeanUtils<BudgetBakVo>().copyObjs(budgetBaks,BudgetBakVo.class);
                jsonObject1.put(String.valueOf(DateUtil.getCurrentYear()),budgetBakVos);
            }
            vo.setBudgetBakJsonObject(jsonObject1);

            //绩效
            List<Performance> performanceList = performanceService.getRepPerformanceList(replyId);
            List<PerformanceVo> performanceVos = new BeanUtils<PerformanceVo>().copyObjs(performanceList,PerformanceVo.class);

            List<PerformanceBak> performanceBaks = performanceBakService.getAllByRepId(replyId);
            List<PerformanceBakVo> performanceVoList = new BeanUtils<PerformanceBakVo>().copyObjs(performanceBaks,PerformanceBakVo.class);
            vo.setPerformanceVoList(performanceVos);
            vo.setPerformanceBakVoList(performanceVoList);

            //人员
            List<Person> personList = personService.getReplyList(replyId);
            List<PersonBak> personBaks = personBakService.getAllByRepId(replyId);
            List<PersonVo> personVos = new BeanUtils<PersonVo>().copyObjs(personList,PersonVo.class);
            List<PersonBakVo> personBakVos = new BeanUtils<PersonBakVo>().copyObjs(personBaks,PersonBakVo.class);
            vo.setPersonVoList(personVos);
            vo.setPersonBakVoList(personBakVos);
        } else {
           List<MaintBudget> maintBudgetList = maintBudgetService.getAllList(mainId);
           List<MaintBudgetVo> maintBudgetVos = new BeanUtils<MaintBudgetVo>().copyObjs(maintBudgetList,MaintBudgetVo.class);
           vo.setMaintBudgetVos(maintBudgetVos);

            List<ChangeBudgetBak> changeBudgetBaks = changeBudgetBakService.getList(mainId);
            List<ChangeBudgetBakVo> changeBudgetBakVos = new BeanUtils<ChangeBudgetBakVo>().copyObjs(changeBudgetBaks,ChangeBudgetBakVo.class);
            vo.setChangeBudgetBakVos(changeBudgetBakVos);

            List<Person> personList = personService.getMainList(projectId);
            List<PersonBak> personBaks = personBakService.getAllByProId(projectId);
            List<PersonVo> personVos = new BeanUtils<PersonVo>().copyObjs(personList,PersonVo.class);
            List<PersonBakVo> personBakVos = new BeanUtils<PersonBakVo>().copyObjs(personBaks,PersonBakVo.class);
            vo.setPersonVoList(personVos);
            vo.setPersonBakVoList(personBakVos);
        }
        return new Result<ProjectChangeVo>().sucess(vo);
    }

    @PostMapping(value = path+"/exeReply/toSubmit")
    @ApiOperation(value = "提交", notes = "提交")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result<ProjectChangeVo> toSubmit(@Valid @RequestBody @ApiParam ProjectChangeVo projectChangeVo) {
        ProjectChangeVo vo = this.exeReplyService.toSubmit(projectChangeVo);
        return new Result<ProjectChangeVo>().sucess(vo);
    }
}



