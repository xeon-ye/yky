package com.deloitte.services.project.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.vo.DeptForm;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserForm;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.project.client.ExecutionClient;
import com.deloitte.platform.api.project.param.ExecutionQueryForm;
import com.deloitte.platform.api.project.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.project.common.util.DateUtil;
import com.deloitte.services.project.common.util.LocalDateUtils;
import com.deloitte.services.project.common.util.StringUtil;
import com.deloitte.services.project.entity.*;
import com.deloitte.services.project.service.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.regexp.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-17
 * @Description :   Execution控制器实现类
 * @Modified :
 */
@Api(tags = "Execution操作接口")
@Slf4j
@RestController
@RequestMapping("/project/execution")
public class ExecutionController implements ExecutionClient {

    @Autowired
    public IExecutionService  executionService;

    @Autowired
    public IProjectsService projectsService;

    @Autowired
    public IApplicationService applicationService;

    @Autowired
    public IReplyService replyService;

    @Autowired
    public IActService actService;
    @Autowired
    public ISubactService subActService;

    @Autowired
    public IBudgetService budgetService;

    @Autowired
    public IPerformanceService performanceService;

    @Autowired
    public IEconomicService economicService;

    @Autowired
    public IExeChangeService exeChangeService;

    @Autowired
    public IExeBudgetService exeBudgetService;

    @Autowired
    public IExePerformanceService exePerformanceService;
    @Autowired
    public IMaintReplyService maintReplyService;

    @Autowired
    public IMaintBudgetService maintBudgetService;

    @Autowired
    public IChangeProService changeProService;
    @Autowired
    public IChangeNoteService changeNoteService;


    @Override
    @ApiOperation(value = "新增Execution", notes = "新增一个Execution")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="executionForm",value="新增Execution的form表单",required=true)  ExecutionForm executionForm) {
        log.info("form:",  executionForm.toString());
        Execution execution =new BeanUtils<Execution>().copyObj(executionForm,Execution.class);
        return Result.success( executionService.save(execution));
    }


    @Override
    @ApiOperation(value = "删除Execution", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ExecutionID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        executionService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改Execution", notes = "修改指定Execution信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "Execution的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="executionForm",value="修改Execution的form表单",required=true)  ExecutionForm executionForm) {
        Execution execution =new BeanUtils<Execution>().copyObj(executionForm,Execution.class);
        execution.setId(id);
        executionService.saveOrUpdate(execution);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取Execution", notes = "获取指定ID的Execution信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Execution的ID", required = true, dataType = "long")
    public Result<ExecutionVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        Execution execution=executionService.getById(id);
        ExecutionVo executionVo=new BeanUtils<ExecutionVo>().copyObj(execution,ExecutionVo.class);
        return new Result<ExecutionVo>().sucess(executionVo);
    }

    @Override
    @ApiOperation(value = "列表查询Execution", notes = "根据条件查询Execution列表数据")
    public Result<List<ExecutionVo>> list(@Valid @RequestBody @ApiParam(name="executionQueryForm",value="Execution查询参数",required=true) ExecutionQueryForm executionQueryForm) {
        log.info("search with executionQueryForm:", executionQueryForm.toString());
        List<Execution> executionList=executionService.selectList(executionQueryForm);
        List<ExecutionVo> executionVoList=new BeanUtils<ExecutionVo>().copyObjs(executionList,ExecutionVo.class);
        return new Result<List<ExecutionVo>>().sucess(executionVoList);
    }


    @Override
    @ApiOperation(value = "分页查询Execution", notes = "根据条件查询Execution分页数据")
    public Result<IPage<ExecutionVo>> search(@Valid @RequestBody @ApiParam(name="executionQueryForm",value="Execution查询参数",required=true) ExecutionQueryForm executionQueryForm) {
        log.info("search with executionQueryForm:", executionQueryForm.toString());
        IPage<Execution> executionPage=executionService.selectPage(executionQueryForm);
        IPage<ExecutionVo> executionVoPage=new BeanUtils<ExecutionVo>().copyPageObjs(executionPage,ExecutionVo.class);
        return new Result<IPage<ExecutionVo>>().sucess(executionVoPage);
    }

    @ApiOperation(value = "执行情况获取Execution", notes = "获取执行情况相关的信息")
    @ApiImplicitParam(paramType = "path", name = "projectId", value = "项目ID", required = true, dataType = "string")
    @GetMapping(value = "/getExecution/{projectId}")
    public Result<ProjectExecutionVo> getExecution(@PathVariable String projectId) {
        ProjectExecutionVo vo = new ProjectExecutionVo();
        Projects projects = projectsService.getById(projectId);
        ProjectsVo projectsVo = new BeanUtils<ProjectsVo>().copyObj(projects,ProjectsVo.class);
        vo.setProjectId(projectId);
        vo.setProjectsVo(projectsVo);

        String mainMark = "80002";
        //是否为维护项目
        String projectMark = projects.getProjectMark();
        //批复新建
        String replyNewMark = projects.getReplyNewMark();
        String replyId = "";
        String mainId = "";
        String changeId = "";
        //项目维护
        if(StringUtils.isNotEmpty(projectMark)){
            //项目维护只有一条
           List<MaintReply> maintReplies = maintReplyService.getAllList(projectId);
           if(maintReplies.size() > 0) {
               mainId = maintReplies.get(0).getMaintId();
               MaintReplyVo maintReplyVo = new BeanUtils<MaintReplyVo>().copyObj(maintReplies.get(0),MaintReplyVo.class);
               vo.setMaintReplyVo(maintReplyVo);
           }
            mainMark = "80001";
        }else {
            Map map = new HashMap();
            map.put("projectId",projectId);
            map.put("replyStatusCode",2008);
            Reply reply = replyService.getNewRep(map);
            replyId = reply.getReplyDocumentId();
            ReplyVo replyVo = new BeanUtils<ReplyVo>().copyObj(reply,ReplyVo.class);
            vo.setReplyVo(replyVo);
            mainMark = "80002";
        }
        vo.setMainId(mainId);
        vo.setReplyId(replyId);

        //预算明细
        List<ExeBudgetVo> exeBudgetVos = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();
        if("80001".equals(mainMark)) {
            List<MaintBudget> maintBudgetList = maintBudgetService.getAllList(mainId);
            if(maintBudgetList.size() > 0) {
                for (MaintBudget maintBudget : maintBudgetList) {
                    ExeBudget exeBudget = new ExeBudget();
                    exeBudget.setBudgetCode(maintBudget.getActCode());
                    exeBudget.setBudgetName(maintBudget.getActName());
                    exeBudget.setBudgetAmount(maintBudget.getActAmount());
                    jsonArray.add(exeBudget);

                    ExeBudgetVo exeBudgetVo = new BeanUtils<ExeBudgetVo>().copyObj(exeBudget,ExeBudgetVo.class);
                    exeBudgetVos.add(exeBudgetVo);
                }
            }
        } else {
            List list = budgetService.getRepYearCount(replyId);
            if(list.size() > 0) {
                for(int i=0;i<list.size();i++) {
                    JSONObject jsonObject = new JSONObject();
                    JSONArray jsonArray1 = new JSONArray();
                    jsonObject.put("year",list.get(i));

                    if(list.get(i).equals(DateUtil.getCurrentYear())) {
                        jsonObject.put("nowYear",DateUtil.getCurrentYear());
                    }

                    Map map = new HashMap();
                    map.put("budgetYear",list.get(i));
                    map.put("replyId",replyId);
                    List<Budget> budgets = budgetService.getRepBudgetMap(map);

                    for (Budget budget : budgets) {
                        ExeBudget exeBudget = new ExeBudget();
                        long num = 0l;
                        String eco_code = "";
                        String year = "";
                        String eco_name = "";

                        num += NumberUtils.toLong(budget.getReplayDep())+NumberUtils.toLong(budget.getReplayOther())+NumberUtils.toLong(budget.getReplayCenter());
                        eco_code = budget.getExpenseProjectCode();
                        eco_name = budget.getExpenseProject();
                        year = budget.getBudgetaryYear();

                        //---------------------------接口哦
                        exeBudget.setBudgetAmount(String.valueOf(num));
                        exeBudget.setBudgetCode(eco_code);
                        exeBudget.setBudgetName(eco_name);
                        exeBudget.setBudgetYear(year);
                        jsonArray1.add(exeBudget);
                        ExeBudgetVo exeBudgetVo = new BeanUtils<ExeBudgetVo>().copyObj(exeBudget,ExeBudgetVo.class);
                        exeBudgetVos.add(exeBudgetVo);
                    }
                    jsonObject.put("exeBudget",jsonArray1);
                    jsonArray.add(jsonObject);
                }
            }
        }
        vo.setMainMark(mainMark);
        vo.setBudgetVoList(jsonArray);
        vo.setExeBudgetVoList(exeBudgetVos);

        //支出情况 财务获取



        //绩效 前面几级目标 财务获取
        List<ExePerformance> exePerformances = exePerformanceService.getAllList(replyId);
        List<ExePerformanceVo> exePerformanceVos = new ArrayList<>();
        if(exePerformances.size() >0)  {
            exePerformanceVos = new BeanUtils<ExePerformanceVo>().copyObjs(exePerformances, ExePerformanceVo.class);
        } else {
            if("0".equals(mainMark)) {
                List<Performance> performanceList = performanceService.getRepPerformanceList(replyId);
                ExePerformance exePerformance = new ExePerformance();
                if(performanceList.size() > 0) {
                    Performance performance = performanceList.get(0);
                    exePerformance.setProjectId(projectId);
                    exePerformance.setIndicators1(performance.getIndex1st());
                    exePerformance.setIndicators2(performance.getIndex2nd());
                    exePerformance.setIndicators3(performance.getIndex3st());
                    exePerformance.setExeConditionYear(performance.getIndexPer());
                    exePerformanceService.save(exePerformance);
                    ExePerformanceVo exePerformanceVo = new BeanUtils<ExePerformanceVo>().copyObj(exePerformance,ExePerformanceVo.class);
                    exePerformanceVos.add(exePerformanceVo);
                }
            }
        }
        vo.setExePerformanceVoList(exePerformanceVos);

        return new Result<ProjectExecutionVo>().sucess(vo);
    }

    @ApiOperation(value = "保存项目执行情况内容", notes = "保存项目执行情况内容")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @PostMapping(value = "/toSave")
    public Result toUpdateExecution(@Valid @RequestBody @ApiParam(name = "ProjectExecutionVo", value = "传入后端的值", required = true) ProjectExecutionVo projectExecutionVo) {
        String replyId = projectExecutionVo.getReplyId();
        if(projectExecutionVo.getMainMark().equals("80002")) {
            List<ExePerformance> exePerformances = exePerformanceService.getAllList(replyId);
            if(exePerformances.size() > 0) {
                exePerformanceService.deleteAllByRep(replyId);
            }
            List<ExePerformanceVo> exePerformanceVos = projectExecutionVo.getExePerformanceVoList();
            if(exePerformanceVos.size() > 0) {
                for (ExePerformanceVo exePerformanceVo : exePerformanceVos) {
                    ExePerformance exePerformance = new BeanUtils<ExePerformance>().copyObj(exePerformanceVo, ExePerformance.class);
                    exePerformance.setProjectId(projectExecutionVo.getProjectId());
                    exePerformance.setExecutionId(replyId);
                    exePerformanceService.save(exePerformance);
                }
            }
        }
        return Result.success();
    }
}



