package com.deloitte.services.project.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.project.param.ReplyQueryForm;
import com.deloitte.platform.api.project.vo.*;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.project.common.enums.ValueEnums;
import com.deloitte.services.project.common.util.DateUtil;
import com.deloitte.services.project.entity.*;
import com.deloitte.services.project.mapper.ReplyMapper;
import com.deloitte.services.project.service.*;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springfox.documentation.spring.web.json.Json;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-14
 * @Description :  Reply服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ReplyServiceImpl extends ServiceImpl<ReplyMapper, Reply> implements IReplyService {


    @Autowired
    private ReplyMapper replyMapper;

    @Autowired
    public IApplicationService applicationService;

    @Autowired
    public IProjectsService projectsService;

    @Autowired
    public IAllActService allActService;

    @Autowired
    public IActService actService;

    @Autowired
    public ISubactService subactService;

    @Autowired
    public IBudgetService budgetService;

    @Autowired
    public IPerformanceService performanceService;

    @Autowired
    public IExpenseService expenseService;

    @Autowired
    private IEconomicService economicService;

    @Autowired
    private IPersonService personService;

    @Autowired
    private IServiceNumService serviceNumService;

    @Override
    public IPage<Reply> selectPage(ReplyQueryForm queryForm ) {
        QueryWrapper<Reply> queryWrapper = new QueryWrapper <Reply>();
        //getQueryWrapper(queryWrapper,queryForm);
        return replyMapper.selectPage(new Page<Reply>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public Reply getNewRep(Map map) {
        return replyMapper.getNewRep(map);
    }


    @Override
    public List<Reply> getAllListByRep(String projectId) {
        QueryWrapper<Reply> queryWrapper = new QueryWrapper <Reply>();
        queryWrapper.eq(Reply.PROJECT_ID,projectId);
        return replyMapper.selectList(queryWrapper);
    }

    @Override
    public List<Reply> selectList(ReplyQueryForm queryForm) {
        QueryWrapper<Reply> queryWrapper = new QueryWrapper <Reply>();
        //getQueryWrapper(queryWrapper,queryForm);
        return replyMapper.selectList(queryWrapper);
    }

    @Override
    public List<Reply> getReplyList(String applicationId) {
        QueryWrapper<Reply> queryWrapper = new QueryWrapper <Reply>();
        queryWrapper.eq(Reply.APPLICATION_ID,applicationId);
        return replyMapper.selectList(queryWrapper);
    }

    @Override
    public ProjectReplyVo toRemove(String replyId) {
        ProjectReplyVo vo = new ProjectReplyVo();
        Reply reply = replyMapper.selectById(replyId);
        if(StringUtils.isEmpty(reply.getApplicationId())) {
            reply.setReplyStatusCode("2001");
            reply.setReplyStatusCode("已取消");
            this.updateById(reply);
        }
        ReplyVo replyVo = new BeanUtils<ReplyVo>().copyObj(reply,ReplyVo.class);
        vo.setReplyVo(replyVo);
        return vo;
    }

    @Override
    public ProjectReplyVo toFindReply(String replyId) {
        ProjectReplyVo vo = new ProjectReplyVo();
        Reply reply = replyMapper.selectById(replyId);
        ReplyVo replyVo = new BeanUtils<ReplyVo>().copyObj(reply,ReplyVo.class);
        vo.setReplyVo(replyVo);
        String projectId = reply.getProjectId();
        Projects projects = projectsService.getById(projectId);
        ProjectsVo projectsVo = new BeanUtils<ProjectsVo>().copyObj(projects,ProjectsVo.class);
        vo.setProjectsVo(projectsVo);

        //支出计划
        List<Act> actList = actService.getRepActList(replyId);
        if(actList.size() > 0) {
            List<ActVo> actVos = new BeanUtils<ActVo>().copyObjs(actList, ActVo.class);
            //子活动
            List<SubactVo> subactVoList = new ArrayList<>();
            for (Act act : actList) {
                List<Subact> subactList = subactService.getSubActList(act.getActId());
                for (Subact subact : subactList) {
                    SubactVo subactVo = new BeanUtils<SubactVo>().copyObj(subact,SubactVo.class);
                    subactVoList.add(subactVo);
                }
            }
            vo.setActVoList(actVos);
            vo.setSubactsVoList(subactVoList);
        }

        //支出
        List<Expense> expenses = expenseService.getAllList(replyId);
        if(expenses.size() > 0) {
            List<ExpenseVo> expenseVos = new ArrayList<>();
            for (Expense expens : expenses) {
                ExpenseVo expenseVo = new BeanUtils<ExpenseVo>().copyObj(expens,ExpenseVo.class);
                expenseVo.setId(String.valueOf(expens.getId()));
                expenseVos.add(expenseVo);
            }
            vo.setExpenseVoList(expenseVos);
        }

        //预算
        Map map = new HashMap();
        map.put("replyId",replyId);
        map.put("budgetYear",DateUtil.getCurrentYear());
        List<Budget> budgets = budgetService.getRepBudgetMap(map);
        List<BudgetVo> budgetVos = new BeanUtils<BudgetVo>().copyObjs(budgets,BudgetVo.class);
        vo.setBudgetVoList(budgetVos);

        //人员
        List<Person> personList = personService.getReplyList(replyId);
        List<PersonVo> personVos = new ArrayList<>();
        for (Person person : personList) {
            PersonVo personVos1 = new BeanUtils<PersonVo>().copyObj(person, PersonVo.class);
            personVos1.setId(String.valueOf(person.getId()));
            personVos.add(personVos1);
        }
        vo.setPersonVoList(personVos);

        //绩效
        List<Performance> performanceList = performanceService.getRepPerformanceList(replyId);
        List<Performance> performanceIndexTypeList = performanceService.getIndexTypeListWithDistinctByRep(replyId);
        if (CollectionUtils.isNotEmpty(performanceList) && CollectionUtils.isNotEmpty(performanceIndexTypeList)) {
            JSONArray parentArray = new JSONArray();
            for (Performance indexType : performanceIndexTypeList) {
                JSONObject parentObject = new JSONObject();
                parentObject.put("indexType", indexType.getIndexType());
                parentObject.put("annualPerformanceTarget", indexType.getAnnualPerformanceTarget());
                JSONArray childrenProduceArray = new JSONArray();
                JSONArray childrenBenefitArray = new JSONArray();
                JSONArray childrenSatisfiedArray = new JSONArray();
                for (Performance performance : performanceList) {
                    if (performance.getIndexType().equals(indexType.getIndexType())) {
                        if ("produce".equals(performance.getIndex1st())) {
                            JSONObject childrenProduceObject = new JSONObject();
                            childrenProduceObject.put("index2NdCode", performance.getIndex2ndCode());
                            childrenProduceObject.put("index2Nd", performance.getIndex2nd());
                            childrenProduceObject.put("index3StCode", performance.getIndex3stCode());
                            childrenProduceObject.put("index3St", performance.getIndex3st());
                            childrenProduceObject.put("indexPer", performance.getIndexPer());
                            childrenProduceObject.put("per", performance.getPer());
                            childrenProduceObject.put("perCode", performance.getPerCode());
                            childrenProduceArray.add(childrenProduceObject);
                        }
                        if ("benefit".equals(performance.getIndex1st())) {
                            JSONObject childrenBenefitObject = new JSONObject();
                            childrenBenefitObject.put("index2NdCode", performance.getIndex2ndCode());
                            childrenBenefitObject.put("index2Nd", performance.getIndex2nd());
                            childrenBenefitObject.put("index3StCode", performance.getIndex3stCode());
                            childrenBenefitObject.put("index3St", performance.getIndex3st());
                            childrenBenefitObject.put("indexPer", performance.getIndexPer());
                            childrenBenefitObject.put("per", performance.getPer());
                            childrenBenefitObject.put("perCode", performance.getPerCode());
                            childrenBenefitArray.add(childrenBenefitObject);
                        }
                        if ("satisfied".equals(performance.getIndex1st())) {
                            JSONObject childrenSatisfiedObject = new JSONObject();
                            childrenSatisfiedObject.put("index2NdCode", performance.getIndex2ndCode());
                            childrenSatisfiedObject.put("index2Nd", performance.getIndex2nd());
                            childrenSatisfiedObject.put("index3StCode", performance.getIndex3stCode());
                            childrenSatisfiedObject.put("index3St", performance.getIndex3st());
                            childrenSatisfiedObject.put("indexPer", performance.getIndexPer());
                            childrenSatisfiedObject.put("per", performance.getPer());
                            childrenSatisfiedObject.put("perCode", performance.getPerCode());
                            childrenSatisfiedArray.add(childrenSatisfiedObject);
                        }
                    }
                }
                parentObject.put("produce", childrenProduceArray);
                parentObject.put("benefit", childrenBenefitArray);
                parentObject.put("satisfied", childrenSatisfiedArray);
                parentArray.add(parentObject);
            }
            vo.setPerformanceVoList(parentArray);
        }

        return vo;
    }

    @Override
    public ProjectReplyVo getOneRep(String projectId) {
        ProjectReplyVo vo = new ProjectReplyVo();
        Projects projects = projectsService.getById(NumberUtils.toLong(projectId));
        ProjectsVo projectsVo = new BeanUtils<ProjectsVo>().copyObj(projects,ProjectsVo.class);
        vo.setProjectsVo(projectsVo);

        //待批复或者已批复的项目批复书
        String replyId = "";
        Reply reply = replyMapper.getRepToRep(projectId);
        //待批复
        if(ObjectUtils.allNotNull(reply)) {
            replyId = reply.getReplyDocumentId();
        } else {
            Map map = new HashMap();
            map.put("year",DateUtil.getCurrentYear());
            map.put("projectId",projectId);
            reply = replyMapper.getRepByYear(map);
            if(ObjectUtils.allNotNull(reply)) {
                vo.setMsg("该项目本年已批复");
                replyId = reply.getReplyDocumentId();
            }else {
                //获取最新的新建批复项目，都是已批复的状态，周期大于1
                List<Reply> replies = this.getAllListByRep(projectId);
                int cycle = NumberUtils.toInt(projects.getCycle());
                if(cycle - replies.size() < 1) {
                    vo.setMsg("新建的项目已批复完成，只做查询");
                }
                reply = replyMapper.getOneRep(projectId);
                replyId = reply.getReplyDocumentId();
            }
        }
        ReplyVo replyVo = new BeanUtils<ReplyVo>().copyObj(reply,ReplyVo.class);
        vo.setReplyVo(replyVo);

        //保存判断
        vo.setIsReply("1");

        //支出计划
        List<Act> actList = actService.getRepActList(replyId);
        if(actList.size() > 0) {
            List<ActVo> actVos = new BeanUtils<ActVo>().copyObjs(actList, ActVo.class);
            //子活动
            List<SubactVo> subactVoList = new ArrayList<>();
            for (Act act : actList) {
                List<Subact> subactList = subactService.getSubActList(act.getActId());
                for (Subact subact : subactList) {
                    SubactVo subactVo = new BeanUtils<SubactVo>().copyObj(subact,SubactVo.class);
                    subactVoList.add(subactVo);
                }
            }
            vo.setActVoList(actVos);
            vo.setSubactsVoList(subactVoList);
        }

        //支出
        List<Expense> expenses = expenseService.getAllList(replyId);
        if(expenses.size() > 0) {
            List<ExpenseVo> expenseVos = new ArrayList<>();
            for (Expense expens : expenses) {
                ExpenseVo expenseVo = new BeanUtils<ExpenseVo>().copyObj(expens,ExpenseVo.class);
                expenseVo.setId(String.valueOf(expens.getId()));
                expenseVos.add(expenseVo);
            }
            vo.setExpenseVoList(expenseVos);
        }

        //预算
        Map map = new HashMap();
        map.put("replyId",replyId);
        map.put("budgetYear",DateUtil.getCurrentYear());
        List<Budget> budgets = budgetService.getRepBudgetMap(map);
        List<BudgetVo> budgetVos = new BeanUtils<BudgetVo>().copyObjs(budgets,BudgetVo.class);
        vo.setBudgetVoList(budgetVos);

        //人员
        List<Person> personList = personService.getReplyList(replyId);
        List<PersonVo> personVos = new ArrayList<>();
        for (Person person : personList) {
            PersonVo personVos1 = new BeanUtils<PersonVo>().copyObj(person, PersonVo.class);
            personVos1.setId(String.valueOf(person.getId()));
            personVos.add(personVos1);
        }
        vo.setPersonVoList(personVos);

        //绩效
        List<Performance> performanceList = performanceService.getRepPerformanceList(replyId);
        List<Performance> performanceIndexTypeList = performanceService.getIndexTypeListWithDistinctByRep(replyId);
        if (performanceList.size() > 0) {
            JSONArray jsonArray = this.getPerformance(performanceList,performanceIndexTypeList);
            vo.setPerformanceVoList(jsonArray);
        }
        return vo;
    }

    @Override
    public ProjectReplyVo getOneApp(String projectId) {
        ProjectReplyVo vo = new ProjectReplyVo();
        vo.setIsReply("2");
        Projects projects = projectsService.getById(NumberUtils.toLong(projectId));
        ProjectsVo projectsVo = new BeanUtils<ProjectsVo>().copyObj(projects,ProjectsVo.class);
        vo.setProjectsVo(projectsVo);
        //查询最新待批复或者已批复的申报书
        Application application = applicationService.getOneApp(projectId);
        String appStatusCode = application.getAppStateCode();
        String appId = "";
        String replyId = "";
        //关联查询，申报书状态待批复或已批复
        if("2006".equals(appStatusCode)) {
            appId = String.valueOf(application.getId());
        } else if("2007".equals(appStatusCode)) {
            int cycle = NumberUtils.toInt(projects.getCycle());
            List<Reply> replies = this.getAllListByRep(projectId);
            if(cycle - replies.size() < 1) {
                vo.setMsg("该项目已批复完成，只做查询");
            }

            //查看本年是否已批复，否则查询最新已批复书
            Map map = new HashMap();
            map.put("year",DateUtil.getCurrentYear());
            map.put("projectId",projectId);
            Reply reply = replyMapper.getRepByYear(map);
            if(ObjectUtils.allNotNull(reply)) {
                vo.setMsg("该项目本年已批复");
                replyId = reply.getReplyDocumentId();
            }else {
                Reply reply1 = this.replyMapper.getOneRep(projectId);
                replyId = String.valueOf(reply1.getId());
                appId = reply1.getApplicationId();
            }

            //支出计划
            List<Expense> expenses = expenseService.getAllList(replyId);
            if(expenses.size() > 0) {
                List<ExpenseVo> expenseVos = new ArrayList<>();
                for (Expense expens : expenses) {
                    ExpenseVo expenseVo = new BeanUtils<ExpenseVo>().copyObj(expens,ExpenseVo.class);
                    expenseVo.setId(String.valueOf(expens.getId()));
                    expenseVos.add(expenseVo);
                }
                vo.setExpenseVoList(expenseVos);
            }

            //人员
            List<Person> personList = personService.getReplyList(replyId);
            List<PersonVo> personVos = new ArrayList<>();
            for (Person person : personList) {
                PersonVo personVos1 = new BeanUtils<PersonVo>().copyObj(person, PersonVo.class);
                personVos1.setId(String.valueOf(person.getId()));
                personVos.add(personVos1);
            }
            vo.setPersonVoList(personVos);
        }

        vo.setApplicationId(appId);
        ApplicationVo applicationVo = new BeanUtils<ApplicationVo>().copyObj(application,ApplicationVo.class);
        vo.setApplicationVo(applicationVo);
        //支出活动
        List<Act> actList = actService.getActList(appId);
        if(actList.size() > 0) {
            List<SubactVo> subactVoList = new ArrayList<>();
            List<ActVo> actVos = new BeanUtils<ActVo>().copyObjs(actList, ActVo.class);
            for (Act act : actList) {
                List<Subact> subacts = subactService.getSubActList(act.getActId());
                for (Subact subact : subacts) {
                    SubactVo subactVo = new BeanUtils<SubactVo>().copyObj(subact,SubactVo.class);
                    subactVoList.add(subactVo);
                }
            }
            vo.setActVoList(actVos);
            vo.setSubactsVoList(subactVoList);
        }

        //预算
        Map map = new HashMap();
        map.put("applicationId",appId);
        map.put("budgetYear",DateUtil.getCurrentYear());
        List<Budget> budgets = budgetService.getAppBudgetMap(map);
        List<BudgetVo> budgetVos = new BeanUtils<BudgetVo>().copyObjs(budgets,BudgetVo.class);
        vo.setBudgetVoList(budgetVos);

        //绩效
        List<Performance> performanceList = performanceService.getPerformanceList(appId);
        List<Performance> performanceIndexTypeList = performanceService.getIndexTypeListWithDistinct(appId);
        if (performanceList.size() > 0) {
            JSONArray jsonArray = this.getPerformance(performanceList,performanceIndexTypeList);
            vo.setPerformanceVoList(jsonArray);
        }

        return vo;
    }

    @Override
    public ProjectReplyVo newReply(ProjectReplyVo projectReplyVo) {
        String replyCode = projectReplyVo.getReplyCode();
        String projectCode = null;
        String projectName = "";
        String applicationCode = null;
        String applicationName = "";

        if ("7003".equals(replyCode)) {
            //保存
            projectCode = ValueEnums.PROJECT_REPLIED.getCode();
            projectName = ValueEnums.PROJECT_REPLIED.getValue();
            applicationCode = ValueEnums.APPLICATION_HAVE_PASSED.getCode();
            applicationName = ValueEnums.APPLICATION_HAVE_PASSED.getValue();
        } else if ("7004".equals(replyCode)) {
            //提交
            projectCode = ValueEnums.PROJECT_ESTABLISHED.getCode();
            projectName = ValueEnums.PROJECT_ESTABLISHED.getValue();
            applicationCode = ValueEnums.APPLICATION_APPROVED.getCode();
            applicationName = ValueEnums.APPLICATION_APPROVED.getValue();
        }

        String projectId = "";
        String replyId = "";
        //新建
        ProjectsVo projectsVo = projectReplyVo.getProjectsVo();
        Projects projects = new BeanUtils<Projects>().copyObj(projectsVo,Projects.class);
        projects.setProjectStatusCode(projectCode);
        projects.setProjectStatusName(projectName);
        projects.setReplyNewMark("80001");
        //测试测试
        projects.setOrganizationId("20201314");

        projectsService.save(projects);
        projectId = String.valueOf(projects.getId());
        projects.setProjectId(projectId);
        projectsService.updateById(projects);

        ReplyVo replyVo = projectReplyVo.getReplyVo();
        Reply reply = new BeanUtils<Reply>().copyObj(replyVo,Reply.class);
        reply.setReplyStatusCode(applicationCode);
        reply.setReplyStatusName(applicationName);
        reply.setProjectId(projectId);
        reply.setReplyTime(LocalDateTime.now());
        //如果提交要设置serviceNum
        if("7004".equals(replyCode)) {
            reply.setServiceNum("REP000010000");
            reply.setReplyYear(String.valueOf(DateUtil.getCurrentYear()));
        }
        this.save(reply);
        replyId = String.valueOf(reply.getId());
        reply.setReplyDocumentId(replyId);
        this.updateById(reply);

        //支出活动
        List<Act> actList = actService.getRepActList(replyId);
        if(actList.size() > 0) {
            for (Act act : actList) {
                subactService.deleteSubActList(act.getActId());
            }
            actService.deleteRepList(replyId);
        }

        List<ActVo> actVos = projectReplyVo.getActVoList();
        List<SubactVo> subactVoList = projectReplyVo.getSubactsVoList();
        if(actVos.size() > 0) {
            for (ActVo actVo : actVos) {
                Act act = new BeanUtils<Act>().copyObj(actVo,Act.class);
                act.setReplyId(replyId);
                actService.save(act);
                act.setActId(String.valueOf(act.getId()));
                actService.updateById(act);
                for (SubactVo subactVo : subactVoList) {
                    if(subactVo.getIsRelated().equals(actVo.getIsRelated())) {
                        Subact subact = new BeanUtils<Subact>().copyObj(subactVo,Subact.class);
                        subact.setActId(act.getActId());
                        subact.setActAbs(act.getDescription());
                        subact.setActName(act.getActName());
                        subactService.save(subact);
                    }
                }
            }
        }

        //支出明细
        List<Expense> expenses = expenseService.getList(replyId);
        if(expenses.size() > 0){
            expenseService.delByRep(replyId);
        }
        List<ExpenseVo> expenseVoList = projectReplyVo.getExpenseVoList();
        for (ExpenseVo expenseVo : expenseVoList) {
            Expense expense = new BeanUtils<Expense>().copyObj(expenseVo, Expense.class);
            expense.setProjectApprovalId(replyId);
            expense.setProjectId(projectId);
            expenseService.save(expense);
        }

        //预算
        List<Budget> budgets= budgetService.getRepBudgetList(replyId);
        if(budgets.size() > 0) {
            budgetService.deleteByRep(replyId);
        }
        List<BudgetVo> budgetVos =  projectReplyVo.getBudgetVoList();
        for (BudgetVo budgetVo : budgetVos) {
            Budget budget = new BeanUtils<Budget>().copyObj(budgetVo,Budget.class);
            budget.setReplayId(replyId);
            budget.setBudgetaryYear(String.valueOf(DateUtil.getCurrentYear()));
            budgetService.save(budget);
        }

        //绩效
        List<Performance> performanceList1 = performanceService.getRepPerformanceList(replyId);
        if(performanceList1.size() > 0) {
            performanceService.deleteRepList(replyId);
        }
        JSONArray performanceFormList = projectReplyVo.getPerformanceVoList();
        if (Objects.nonNull(performanceFormList)) {
            this.toSavePerformance(performanceFormList,projectId,replyId);
        }

        //成员管理
        List<Person> personList = personService.getReplyList(replyId);
        if(personList.size() > 0){
            personService.deleteReplyList(replyId);
        }
        List<PersonVo> personVos = projectReplyVo.getPersonVoList();
        for (PersonVo personVo : personVos) {
            Person person = new BeanUtils<Person>().copyObj(personVo, Person.class);
            person.setReplyId(replyId);
            personService.save(person);
        }
        return null;
    }

    @Override
    public ProjectReplyVo repToUpdate(ProjectReplyVo projectReplyVo) {
        String replyCode = projectReplyVo.getReplyCode();
        String projectCode = null;
        String projectName = "";
        String applicationCode = null;
        String applicationName = "";

        if ("7003".equals(replyCode)) {
            //保存
            projectCode = ValueEnums.PROJECT_REPLIED.getCode();
            projectName = ValueEnums.PROJECT_REPLIED.getValue();
            applicationCode = ValueEnums.APPLICATION_HAVE_PASSED.getCode();
            applicationName = ValueEnums.APPLICATION_HAVE_PASSED.getValue();
        } else if ("7004".equals(replyCode)) {
            //提交
            projectCode = ValueEnums.PROJECT_ESTABLISHED.getCode();
            projectName = ValueEnums.PROJECT_ESTABLISHED.getValue();
            applicationCode = ValueEnums.APPLICATION_APPROVED.getCode();
            applicationName = ValueEnums.APPLICATION_APPROVED.getValue();
        }
        String replyId = projectReplyVo.getReplyId();
        Reply reply = replyMapper.selectById(replyId);
        String projectId = reply.getProjectId();

        //xiangmu
        ProjectsVo projectsVo = projectReplyVo.getProjectsVo();
        Projects projects = new BeanUtils<Projects>().copyObj(projectsVo,Projects.class);
        projects.setId(NumberUtils.toLong(projectId));
        projects.setProjectStatusCode(projectCode);
        projects.setProjectStatusName(projectName);
        projectsService.updateById(projects);

        //批复书
        ReplyVo replyVo = projectReplyVo.getReplyVo();
        Reply reply1 = new BeanUtils<Reply>().copyObj(replyVo,Reply.class);
        reply1.setId(NumberUtils.toLong(replyId));
        reply1.setReplyStatusCode(applicationCode);
        reply1.setReplyStatusName(applicationName);
        //如果提交要设置serviceNum,以及最新批复年度
        if("7004".equals(replyCode)) {
            reply.setServiceNum("REP000010000");
            reply.setReplyYear(String.valueOf(DateUtil.getCurrentYear()));
        }
        reply1.setReplyYear(String.valueOf(DateUtil.getCurrentYear()));
        reply1.setProjectId(projectId);
        this.updateById(reply1);

        //支出活动
        List<Act> actList = actService.getRepActList(replyId);
        if(actList.size() > 0) {
            for (Act act : actList) {
                subactService.deleteSubActList(act.getActId());
            }
            actService.deleteRepList(replyId);
        }

        List<ActVo> actVos = projectReplyVo.getActVoList();
        List<SubactVo> subactVoList = projectReplyVo.getSubactsVoList();
        if(actVos.size() > 0) {
            for (ActVo actVo : actVos) {
                Act act = new BeanUtils<Act>().copyObj(actVo,Act.class);
                act.setReplyId(replyId);
                actService.save(act);
                act.setActId(String.valueOf(act.getId()));
                actService.updateById(act);
                for (SubactVo subactVo : subactVoList) {
                    if(subactVo.getIsRelated().equals(actVo.getIsRelated())) {
                        Subact subact = new BeanUtils<Subact>().copyObj(subactVo,Subact.class);
                        subact.setActId(act.getActId());
                        subact.setActAbs(act.getDescription());
                        subact.setActName(act.getActName());
                        subactService.save(subact);
                    }
                }
            }
        }

        //支出明细
        List<Expense> expenses = expenseService.getList(replyId);
        if(expenses.size() > 0){
            expenseService.delByRep(replyId);
        }
        List<ExpenseVo> expenseVoList = projectReplyVo.getExpenseVoList();
        for (ExpenseVo expenseVo : expenseVoList) {
            Expense expense = new BeanUtils<Expense>().copyObj(expenseVo, Expense.class);
            expense.setProjectApprovalId(replyId);
            expense.setProjectId(projectId);
            expenseService.save(expense);
        }

        //预算
        List<Budget> budgets= budgetService.getRepBudgetList(replyId);
        if(budgets.size() > 0) {
            budgetService.deleteByRep(replyId);
        }
        List<BudgetVo> budgetVos =  projectReplyVo.getBudgetVoList();
        for (BudgetVo budgetVo : budgetVos) {
            Budget budget = new BeanUtils<Budget>().copyObj(budgetVo,Budget.class);
            budget.setReplayId(replyId);
            budget.setBudgetaryYear(String.valueOf(DateUtil.getCurrentYear()));
            budgetService.save(budget);
        }

        //绩效
        List<Performance> performanceList1 = performanceService.getRepPerformanceList(replyId);
        if(performanceList1.size() > 0) {
            performanceService.deleteRepList(replyId);
        }
        JSONArray performanceFormList = projectReplyVo.getPerformanceVoList();
        if (Objects.nonNull(performanceFormList)) {
            this.toSavePerformance(performanceFormList,projectId,replyId);
        }

        //成员管理
        List<Person> personList = personService.getReplyList(replyId);
        if(personList.size() > 0){
            personService.deleteReplyList(replyId);
        }
        List<PersonVo> personVos = projectReplyVo.getPersonVoList();
        for (PersonVo personVo : personVos) {
            Person person = new BeanUtils<Person>().copyObj(personVo, Person.class);
            person.setReplyId(replyId);
            personService.save(person);
        }
        return null;
    }

    @Override
    public ProjectReplyVo proToUpdate(ProjectReplyVo projectReplyVo) {
        ProjectReplyVo vo = new ProjectReplyVo();
        String replyCode = projectReplyVo.getReplyCode();
        String isReply = projectReplyVo.getIsReply();
        String projectCode = null;
        String projectName = "";
        String applicationCode = null;
        String applicationName = "";

        if ("7003".equals(replyCode)) {
            //保存
            projectCode = ValueEnums.PROJECT_REPLIED.getCode();
            projectName = ValueEnums.PROJECT_REPLIED.getValue();
            applicationCode = ValueEnums.APPLICATION_HAVE_PASSED.getCode();
            applicationName = ValueEnums.APPLICATION_HAVE_PASSED.getValue();
        } else if ("7004".equals(replyCode)) {
            //提交
            projectCode = ValueEnums.PROJECT_ESTABLISHED.getCode();
            projectName = ValueEnums.PROJECT_ESTABLISHED.getValue();
            applicationCode = ValueEnums.APPLICATION_APPROVED.getCode();
            applicationName = ValueEnums.APPLICATION_APPROVED.getValue();
        }

        ProjectsVo projectsVo = projectReplyVo.getProjectsVo();
        String proStatusCode = projectsVo.getProjectStatusCode();
        String projectId = projectsVo.getProjectId();
        String replyId = "";
        String appId = "";
        //新建批复项目处理
        if("1".equals(isReply)) {
            ReplyVo replyVo = projectReplyVo.getReplyVo();
            Reply reply = new BeanUtils<Reply>().copyObj(replyVo,Reply.class);
            reply.setReplyStatusCode(applicationCode);
            reply.setReplyStatusName(applicationName);
            reply.setProjectId(projectId);
            reply.setReplyTime(LocalDateTime.now());
            reply.setServiceNum("REP000010000");
            if("7004".equals(replyCode)) {
                reply.setReplyYear(String.valueOf(DateUtil.getCurrentYear()));
            }
            this.save(reply);
            replyId = String.valueOf(reply.getId());
            reply.setReplyDocumentId(replyId);
            this.updateById(reply);

            //支出活动
            List<Act> actList = actService.getRepActList(replyId);
            if(actList.size() > 0) {
                for (Act act : actList) {
                    subactService.deleteSubActList(act.getActId());
                }
                actService.deleteRepList(replyId);
            }
            List<ActVo> actVos = projectReplyVo.getActVoList();
            List<SubactVo> subactVoList = projectReplyVo.getSubactsVoList();
            if(actVos.size() > 0) {
                for (ActVo actVo : actVos) {
                    Act act = new BeanUtils<Act>().copyObj(actVo,Act.class);
                    act.setReplyId(replyId);
                    actService.save(act);
                    act.setActId(String.valueOf(act.getId()));
                    actService.updateById(act);
                    for (SubactVo subactVo : subactVoList) {
                        if(subactVo.getIsRelated().equals(actVo.getIsRelated())) {
                            Subact subact = new BeanUtils<Subact>().copyObj(subactVo,Subact.class);
                            subact.setActId(act.getActId());
                            subact.setActAbs(act.getDescription());
                            subact.setActName(act.getActName());
                            subactService.save(subact);
                        }
                    }
                }
            }

            //支出明细
            List<Expense> expenses = expenseService.getList(replyId);
            if(expenses.size() > 0){
                expenseService.delByRep(replyId);
            }
            List<ExpenseVo> expenseVoList = projectReplyVo.getExpenseVoList();
            for (ExpenseVo expenseVo : expenseVoList) {
                Expense expense = new BeanUtils<Expense>().copyObj(expenseVo, Expense.class);
                expense.setProjectApprovalId(replyId);
                expense.setProjectId(projectId);
                expenseService.save(expense);
            }

            //预算
            List<Budget> budgets= budgetService.getRepBudgetList(replyId);
            if(budgets.size() > 0) {
                budgetService.deleteByRep(replyId);
            }
            List<BudgetVo> budgetVos =  projectReplyVo.getBudgetVoList();
            for (BudgetVo budgetVo : budgetVos) {
                Budget budget = new BeanUtils<Budget>().copyObj(budgetVo,Budget.class);
                budget.setReplayId(replyId);
                budget.setBudgetaryYear(String.valueOf(DateUtil.getCurrentYear()));
                budgetService.save(budget);
            }
            //绩效
            List<Performance> performanceList1 = performanceService.getRepPerformanceList(replyId);
            if(performanceList1.size() > 0) {
                performanceService.deleteRepList(replyId);
            }
            JSONArray performanceFormList = projectReplyVo.getPerformanceVoList();
            if (Objects.nonNull(performanceFormList)) {
                this.toSavePerformance(performanceFormList,projectId,replyId);
            }

            //成员管理
            List<Person> personList = personService.getReplyList(replyId);
            if(personList.size() > 0){
                personService.deleteReplyList(replyId);
            }
            List<PersonVo> personVos = projectReplyVo.getPersonVoList();
            for (PersonVo personVo : personVos) {
                Person person = new BeanUtils<Person>().copyObj(personVo, Person.class);
                person.setReplyId(replyId);
                personService.save(person);
            }
        } else {
            //----------------------------------------------------------------------------------------------------------
            //申报项目处理
            ApplicationVo applicationVo = projectReplyVo.getApplicationVo();
            appId = applicationVo.getApplicationId();
            String appStatusCode = applicationVo.getAppStateCode();
            //项目更新
            Projects projects = new BeanUtils<Projects>().copyObj(projectsVo,Projects.class);
            //待批复的项目状态才更新
            if("7003".equals(proStatusCode)) {
                projects.setProjectStatusCode(projectCode);
                projects.setProjectStatusName(projectName);
            }
            projects.setReplyNewMark("80002");
            projects.setId(NumberUtils.toLong(projectId));
            projectsService.updateById(projects);
            //待批复--已通过
            if("2006".equals(appStatusCode)) {
                Application application = new BeanUtils<Application>().copyObj(applicationVo,Application.class);
                application.setAppStateCode(applicationCode);
                application.setAppStateName(applicationName);
                application.setId(NumberUtils.toLong(appId));
                applicationService.updateById(application);

                List<Reply> replies = this.getReplyList(appId);
                if(replies.size() == 0) {
                    ReplyVo replyVo = projectReplyVo.getReplyVo();
                    Reply reply = new BeanUtils<Reply>().copyObj(replyVo,Reply.class);
                    reply.setReplyStatusCode(applicationCode);
                    reply.setReplyStatusName(applicationName);
                    reply.setProjectId(projectId);
                    reply.setApplicationId(appId);
                    reply.setReplyTime(LocalDateTime.now());
                    reply.setBeginYear(String.valueOf(DateUtil.getCurrentYear()));
                    if(StringUtils.isNotEmpty(application.getServiceNum())) {
                        reply.setServiceNum(application.getServiceNum().replaceAll("APL","REP"));
                    }
                    //如果提交批复年度
                    if("7004".equals(replyCode)) {
                        reply.setReplyYear(String.valueOf(DateUtil.getCurrentYear()));
                    }
                    this.save(reply);
                    replyId = String.valueOf(reply.getId());
                    reply.setReplyDocumentId(replyId);
                    this.updateById(reply);
                }
            } else {
                //保存已批复的项目
                Reply reply1 = this.getReplyList(appId).get(0);
                int replyYear = NumberUtils.toInt(reply1.getReplyYear());
                int curYear = DateUtil.getCurrentYear();
                if(curYear > replyYear) {
                    ReplyVo replyVo = projectReplyVo.getReplyVo();
                    Reply reply = new BeanUtils<Reply>().copyObj(replyVo,Reply.class);
                    reply.setReplyStatusCode(applicationCode);
                    reply.setReplyStatusName(applicationName);
                    reply.setProjectId(projectId);
                    reply.setReplyTime(LocalDateTime.now());
                    reply.setBeginYear(String.valueOf(DateUtil.getCurrentYear()));
                    if(StringUtils.isNotEmpty(reply.getServiceNum())) {
                        reply.setServiceNum("REP000010000");
                    }
                    if("7004".equals(replyCode)) {
                        reply.setReplyYear(String.valueOf(DateUtil.getCurrentYear()));
                    }
                    this.save(reply);
                    replyId = String.valueOf(reply.getId());
                    reply.setReplyDocumentId(replyId);
                    this.updateById(reply);
                }
            }

            //新建
            List<Act> actList = actService.getActList(appId);
            if(actList.size() > 0) {
                for (Act act : actList) {
                    subactService.deleteSubActList(act.getActId());
                }
                actService.deleteList(appId);
            }

            List<ActVo> actVos = projectReplyVo.getActVoList();
            List<SubactVo> subactVoList = projectReplyVo.getSubactsVoList();
            if(actVos.size() > 0) {
                for (ActVo actVo : actVos) {
                    Act act = new BeanUtils<Act>().copyObj(actVo,Act.class);
                    act.setReplyId(replyId);
                    act.setApplicationId(appId);
                    actService.save(act);
                    act.setActId(String.valueOf(act.getId()));
                    actService.updateById(act);
                    for (SubactVo subactVo : subactVoList) {
                        if(subactVo.getIsRelated().equals(actVo.getIsRelated())) {
                            Subact subact = new BeanUtils<Subact>().copyObj(subactVo,Subact.class);
                            subact.setActId(act.getActId());
                            subact.setActAbs(act.getDescription());
                            subact.setActName(act.getActName());
                            subactService.save(subact);
                        }
                    }
                }
            }
            //支出明细
            List<Expense> expenses = expenseService.getList(replyId);
            if(expenses.size() > 0){
                expenseService.delByRep(replyId);
            }
            List<ExpenseVo> expenseVoList = projectReplyVo.getExpenseVoList();
            for (ExpenseVo expenseVo : expenseVoList) {
                Expense expense = new BeanUtils<Expense>().copyObj(expenseVo, Expense.class);
                expense.setProjectApprovalId(replyId);
                expense.setProjectId(projectId);
                expense.setApplicationId(appId);
                expenseService.save(expense);
            }

            //预算
            List<Budget> budgets= budgetService.getAppBudgetList(appId);
            if(budgets.size() > 0) {
                budgetService.deleteByApp(appId);
            }
            List<BudgetVo> budgetVos =  projectReplyVo.getBudgetVoList();
            for (BudgetVo budgetVo : budgetVos) {
                Budget budget = new BeanUtils<Budget>().copyObj(budgetVo,Budget.class);
                budget.setReplayId(replyId);
                budget.setApplicationId(appId);
                budget.setBudgetaryYear(String.valueOf(DateUtil.getCurrentYear()));
                budgetService.save(budget);
            }

            //绩效
            List<Performance> performanceList1 = performanceService.getPerformanceList(appId);
            if(performanceList1.size() > 0) {
                performanceService.deleteList(appId);
            }
            JSONArray performanceFormList = projectReplyVo.getPerformanceVoList();
            if (Objects.nonNull(performanceFormList)) {
                if (Objects.nonNull(performanceFormList)) {
                    for (int i = 0; i < performanceFormList.size(); i++) {
                        JSONObject jsonObject = performanceFormList.getJSONObject(i);
                        String indexType = jsonObject.getString("indexType");
                        String annualPerformanceTarget = jsonObject.getString("annualPerformanceTarget");
                        String mark = "0";
                        //产出指标
                        if (Objects.nonNull(jsonObject.getJSONArray("produce"))) {
                            JSONArray index1StProduceArr = jsonObject.getJSONArray("produce");
                            if(index1StProduceArr.size() > 0) {
                                mark = "1";
                                for (int j = 0; j < index1StProduceArr.size(); j++) {
                                    Performance performanceProduce = new Performance();
                                    JSONObject index1StProduceObj = index1StProduceArr.getJSONObject(j);
                                    performanceProduce.setIndex1st("produce");
                                    performanceProduce.setIndex1stCode("1001");
                                    performanceProduce.setIndexType(indexType);
                                    performanceProduce.setAnnualPerformanceTarget(annualPerformanceTarget);
                                    performanceProduce.setIndex2ndCode(index1StProduceObj.getString("index2NdCode"));
                                    performanceProduce.setIndex2nd(index1StProduceObj.getString("index2Nd"));
                                    performanceProduce.setIndex3stCode(index1StProduceObj.getString("index3StCode"));
                                    performanceProduce.setIndex3st(index1StProduceObj.getString("index3St"));
                                    performanceProduce.setIndexPer(index1StProduceObj.getString("indexPer"));
                                    performanceProduce.setPer(index1StProduceObj.getString("per"));
                                    performanceProduce.setPerCode(index1StProduceObj.getString("perCode"));
                                    performanceProduce.setReplayId(replyId);
                                    performanceProduce.setApplicationId(appId);
                                    performanceProduce.setProjectId(projectId);
                                    performanceService.save(performanceProduce);
                                    performanceProduce.setPerformanceId(String.valueOf(performanceProduce.getId()));
                                    performanceService.updateById(performanceProduce);
                                }
                            }
                        }

                        //效益指标
                        if (Objects.nonNull(jsonObject.getJSONArray("benefit"))){
                            JSONArray index1StBenefitArr = jsonObject.getJSONArray("benefit");
                            if(index1StBenefitArr.size() > 0) {
                                mark = "1";
                                for (int j = 0; j < index1StBenefitArr.size(); j++) {
                                    Performance performanceBenefit = new Performance();
                                    JSONObject index1StBenefitObj = index1StBenefitArr.getJSONObject(j);
                                    performanceBenefit.setIndex1st("benefit");
                                    performanceBenefit.setIndex1stCode("1002");
                                    performanceBenefit.setIndexType(indexType);
                                    performanceBenefit.setAnnualPerformanceTarget(annualPerformanceTarget);
                                    performanceBenefit.setIndex2ndCode(index1StBenefitObj.getString("index2NdCode"));
                                    performanceBenefit.setIndex2nd(index1StBenefitObj.getString("index2Nd"));
                                    performanceBenefit.setIndex3stCode(index1StBenefitObj.getString("index3StCode"));
                                    performanceBenefit.setIndex3st(index1StBenefitObj.getString("index3St"));
                                    performanceBenefit.setIndexPer(index1StBenefitObj.getString("indexPer"));
                                    performanceBenefit.setPer(index1StBenefitObj.getString("per"));
                                    performanceBenefit.setPerCode(index1StBenefitObj.getString("perCode"));
                                    performanceBenefit.setReplayId(replyId);
                                    performanceBenefit.setApplicationId(appId);
                                    performanceBenefit.setProjectId(projectId);
                                    performanceService.save(performanceBenefit);
                                    performanceBenefit.setPerformanceId(String.valueOf(performanceBenefit.getId()));
                                    performanceService.updateById(performanceBenefit);
                                }
                            }
                        }

                        //满意度指标
                        if (Objects.nonNull(jsonObject.getJSONArray("satisfied"))){
                            JSONArray index1StSatisfiedArr = jsonObject.getJSONArray("satisfied");
                            if(index1StSatisfiedArr.size() > 0) {
                                for (int j = 0; j < index1StSatisfiedArr.size(); j++) {
                                    mark = "1";
                                    Performance performanceSatisfied = new Performance();
                                    JSONObject index1StSatisfiedObj = index1StSatisfiedArr.getJSONObject(j);
                                    performanceSatisfied.setIndex1st("satisfied");
                                    performanceSatisfied.setIndex1stCode("1003");
                                    performanceSatisfied.setIndexType(indexType);
                                    performanceSatisfied.setAnnualPerformanceTarget(annualPerformanceTarget);
                                    performanceSatisfied.setIndex2ndCode(index1StSatisfiedObj.getString("index2NdCode"));
                                    performanceSatisfied.setIndex2nd(index1StSatisfiedObj.getString("index2Nd"));
                                    performanceSatisfied.setIndex3stCode(index1StSatisfiedObj.getString("index3StCode"));
                                    performanceSatisfied.setIndex3st(index1StSatisfiedObj.getString("index3St"));
                                    performanceSatisfied.setIndexPer(index1StSatisfiedObj.getString("indexPer"));
                                    performanceSatisfied.setPer(index1StSatisfiedObj.getString("per"));
                                    performanceSatisfied.setPerCode(index1StSatisfiedObj.getString("perCode"));
                                    performanceSatisfied.setReplayId(replyId);
                                    performanceSatisfied.setApplicationId(appId);
                                    performanceSatisfied.setProjectId(projectId);
                                    performanceService.save(performanceSatisfied);
                                    performanceSatisfied.setPerformanceId(String.valueOf(performanceSatisfied.getId()));
                                    performanceService.updateById(performanceSatisfied);
                                }
                            }
                        }

                        if("0".equals(mark)){
                            if(StringUtils.isNotEmpty(annualPerformanceTarget)) {
                                Performance performance = new Performance();
                                performance.setIndexType(indexType);
                                performance.setAnnualPerformanceTarget(annualPerformanceTarget);
                                performance.setReplayId(replyId);
                                performance.setApplicationId(appId);
                                performance.setProjectId(projectId);
                                performanceService.save(performance);
                                performance.setPerformanceId(String.valueOf(performance.getId()));
                                performanceService.updateById(performance);
                            }
                        }
                    }
                }
            }

            //成员管理
            List<Person> personList = personService.getReplyList(replyId);
            if(personList.size() > 0){
                personService.deleteReplyList(replyId);
            }
            List<PersonVo> personVos = projectReplyVo.getPersonVoList();
            for (PersonVo personVo : personVos) {
                Person person = new BeanUtils<Person>().copyObj(personVo, Person.class);
                person.setReplyId(replyId);
                person.setApplicationId(appId);
                personService.save(person);
            }
        }
        return null;
    }

    /**
     * 保存绩效
     * @param performanceFormList
     * @param projectId
     * @param appORrepId
     */
    public void toSavePerformance(JSONArray performanceFormList, String projectId, String appORrepId) {
        if (Objects.nonNull(performanceFormList)) {
            for (int i = 0; i < performanceFormList.size(); i++) {
                JSONObject jsonObject = performanceFormList.getJSONObject(i);
                String indexType = jsonObject.getString("indexType");
                String annualPerformanceTarget = jsonObject.getString("annualPerformanceTarget");
                String mark = "0";
                //产出指标
                if (Objects.nonNull(jsonObject.getJSONArray("produce"))) {
                    JSONArray index1StProduceArr = jsonObject.getJSONArray("produce");
                    if(index1StProduceArr.size() > 0) {
                        mark = "1";
                        for (int j = 0; j < index1StProduceArr.size(); j++) {
                            Performance performanceProduce = new Performance();
                            JSONObject index1StProduceObj = index1StProduceArr.getJSONObject(j);
                            performanceProduce.setIndex1st("produce");
                            performanceProduce.setIndex1stCode("1001");
                            performanceProduce.setIndexType(indexType);
                            performanceProduce.setAnnualPerformanceTarget(annualPerformanceTarget);
                            performanceProduce.setIndex2ndCode(index1StProduceObj.getString("index2NdCode"));
                            performanceProduce.setIndex2nd(index1StProduceObj.getString("index2Nd"));
                            performanceProduce.setIndex3stCode(index1StProduceObj.getString("index3StCode"));
                            performanceProduce.setIndex3st(index1StProduceObj.getString("index3St"));
                            performanceProduce.setIndexPer(index1StProduceObj.getString("indexPer"));
                            performanceProduce.setPer(index1StProduceObj.getString("per"));
                            performanceProduce.setPerCode(index1StProduceObj.getString("perCode"));
                            performanceProduce.setReplayId(appORrepId);
                            performanceProduce.setProjectId(projectId);
                            performanceService.save(performanceProduce);
                            performanceProduce.setPerformanceId(String.valueOf(performanceProduce.getId()));
                            performanceService.updateById(performanceProduce);
                        }
                    }
                }

                //效益指标
                if (Objects.nonNull(jsonObject.getJSONArray("benefit"))){
                    JSONArray index1StBenefitArr = jsonObject.getJSONArray("benefit");
                    if(index1StBenefitArr.size() > 0) {
                        mark = "1";
                        for (int j = 0; j < index1StBenefitArr.size(); j++) {
                            Performance performanceBenefit = new Performance();
                            JSONObject index1StBenefitObj = index1StBenefitArr.getJSONObject(j);
                            performanceBenefit.setIndex1st("benefit");
                            performanceBenefit.setIndex1stCode("1002");
                            performanceBenefit.setIndexType(indexType);
                            performanceBenefit.setAnnualPerformanceTarget(annualPerformanceTarget);
                            performanceBenefit.setIndex2ndCode(index1StBenefitObj.getString("index2NdCode"));
                            performanceBenefit.setIndex2nd(index1StBenefitObj.getString("index2Nd"));
                            performanceBenefit.setIndex3stCode(index1StBenefitObj.getString("index3StCode"));
                            performanceBenefit.setIndex3st(index1StBenefitObj.getString("index3St"));
                            performanceBenefit.setIndexPer(index1StBenefitObj.getString("indexPer"));
                            performanceBenefit.setPer(index1StBenefitObj.getString("per"));
                            performanceBenefit.setPerCode(index1StBenefitObj.getString("perCode"));
                            performanceBenefit.setReplayId(appORrepId);
                            performanceBenefit.setProjectId(projectId);
                            performanceService.save(performanceBenefit);
                            performanceBenefit.setPerformanceId(String.valueOf(performanceBenefit.getId()));
                            performanceService.updateById(performanceBenefit);
                        }
                    }
                }

                //满意度指标
                if (Objects.nonNull(jsonObject.getJSONArray("satisfied"))){
                    JSONArray index1StSatisfiedArr = jsonObject.getJSONArray("satisfied");
                    if(index1StSatisfiedArr.size() > 0) {
                        for (int j = 0; j < index1StSatisfiedArr.size(); j++) {
                            mark = "1";
                            Performance performanceSatisfied = new Performance();
                            JSONObject index1StSatisfiedObj = index1StSatisfiedArr.getJSONObject(j);
                            performanceSatisfied.setIndex1st("satisfied");
                            performanceSatisfied.setIndex1stCode("1003");
                            performanceSatisfied.setIndexType(indexType);
                            performanceSatisfied.setAnnualPerformanceTarget(annualPerformanceTarget);
                            performanceSatisfied.setIndex2ndCode(index1StSatisfiedObj.getString("index2NdCode"));
                            performanceSatisfied.setIndex2nd(index1StSatisfiedObj.getString("index2Nd"));
                            performanceSatisfied.setIndex3stCode(index1StSatisfiedObj.getString("index3StCode"));
                            performanceSatisfied.setIndex3st(index1StSatisfiedObj.getString("index3St"));
                            performanceSatisfied.setIndexPer(index1StSatisfiedObj.getString("indexPer"));
                            performanceSatisfied.setPer(index1StSatisfiedObj.getString("per"));
                            performanceSatisfied.setPerCode(index1StSatisfiedObj.getString("perCode"));
                            performanceSatisfied.setReplayId(appORrepId);
                            performanceSatisfied.setProjectId(projectId);
                            performanceService.save(performanceSatisfied);
                            performanceSatisfied.setPerformanceId(String.valueOf(performanceSatisfied.getId()));
                            performanceService.updateById(performanceSatisfied);
                        }
                    }
                }

                if("0".equals(mark)){
                    if(StringUtils.isNotEmpty(annualPerformanceTarget)) {
                        Performance performance = new Performance();
                        performance.setIndexType(indexType);
                        performance.setAnnualPerformanceTarget(annualPerformanceTarget);
                        performance.setReplayId(appORrepId);
                        performance.setProjectId(projectId);
                        performanceService.save(performance);
                        performance.setPerformanceId(String.valueOf(performance.getId()));
                        performanceService.updateById(performance);
                    }
                }
            }
        }
    }

    /**
     * 获取绩效
     * @param performanceList
     * @param performanceIndexTypeList
     * @return
     */
    public JSONArray getPerformance(List<Performance> performanceList,List<Performance> performanceIndexTypeList) {
        JSONArray parentArray = new JSONArray();
        //中期或年度指标
        for (Performance indexType : performanceIndexTypeList) {
            JSONObject parentObject = new JSONObject();
            parentObject.put("indexType", indexType.getIndexType());
            parentObject.put("annualPerformanceTarget", indexType.getAnnualPerformanceTarget());
            JSONArray childrenProduceArray = new JSONArray();
            JSONArray childrenBenefitArray = new JSONArray();
            JSONArray childrenSatisfiedArray = new JSONArray();
            for (Performance performance : performanceList) {
                if (performance.getIndexType().equals(indexType.getIndexType())) {
                    if ("produce".equals(performance.getIndex1st())) {
                        JSONObject childrenProduceObject = new JSONObject();
                        childrenProduceObject.put("index2NdCode", performance.getIndex2ndCode());
                        childrenProduceObject.put("index2Nd", performance.getIndex2nd());
                        childrenProduceObject.put("index3StCode", performance.getIndex3stCode());
                        childrenProduceObject.put("index3St", performance.getIndex3st());
                        childrenProduceObject.put("indexPer", performance.getIndexPer());
                        childrenProduceObject.put("per", performance.getPer());
                        childrenProduceObject.put("perCode", performance.getPerCode());
                        childrenProduceArray.add(childrenProduceObject);
                    }
                    if ("benefit".equals(performance.getIndex1st())) {
                        JSONObject childrenBenefitObject = new JSONObject();
                        childrenBenefitObject.put("index2NdCode", performance.getIndex2ndCode());
                        childrenBenefitObject.put("index2Nd", performance.getIndex2nd());
                        childrenBenefitObject.put("index3StCode", performance.getIndex3stCode());
                        childrenBenefitObject.put("index3St", performance.getIndex3st());
                        childrenBenefitObject.put("indexPer", performance.getIndexPer());
                        childrenBenefitObject.put("per", performance.getPer());
                        childrenBenefitObject.put("perCode", performance.getPerCode());
                        childrenBenefitArray.add(childrenBenefitObject);
                    }
                    if ("satisfied".equals(performance.getIndex1st())) {
                        JSONObject childrenSatisfiedObject = new JSONObject();
                        childrenSatisfiedObject.put("index2NdCode", performance.getIndex2ndCode());
                        childrenSatisfiedObject.put("index2Nd", performance.getIndex2nd());
                        childrenSatisfiedObject.put("index3StCode", performance.getIndex3stCode());
                        childrenSatisfiedObject.put("index3St", performance.getIndex3st());
                        childrenSatisfiedObject.put("indexPer", performance.getIndexPer());
                        childrenSatisfiedObject.put("per", performance.getPer());
                        childrenSatisfiedObject.put("perCode", performance.getPerCode());
                        childrenSatisfiedArray.add(childrenSatisfiedObject);
                    }
                }
            }
            parentObject.put("produce", childrenProduceArray);
            parentObject.put("benefit", childrenBenefitArray);
            parentObject.put("satisfied", childrenSatisfiedArray);
            parentArray.add(parentObject);
        }
        return  parentArray;
    }

}

