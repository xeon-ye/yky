package com.deloitte.services.project.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.project.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.project.common.enums.ProjectErrorType;
import com.deloitte.services.project.common.enums.ValueEnums;
import com.deloitte.services.project.common.enums.VoucherTypeEnums;
import com.deloitte.services.project.common.util.AssertUtils;
import com.deloitte.services.project.entity.*;
import com.deloitte.services.project.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author : JeaChen
 * @Date : Create in 2019/5/14 15:45
 * @Description : 教育修购项目申报业务实现类
 * @Modified:
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class AppEducationServiceImpl implements IAppEducationService {

    @Autowired
    private ApplicationServiceImpl applicationService;
    @Autowired
    private ProjectsServiceImpl projectsService;
    @Autowired
    private AllActServiceImpl allActService;
    @Autowired
    private ActServiceImpl actService;
    @Autowired
    private SubactServiceImpl subactService;
    @Autowired
    private BudgetServiceImpl budgetService;
    @Autowired
    private PerformanceServiceImpl performanceService;
    @Autowired
    private ICommonService commonService;
    @Autowired
    private ServiceNumServiceImpl serviceNumService;
    @Autowired
    private EnclosureServiceImpl enclosureService;
    @Autowired
    private IBPMService bpmService;
    @Autowired
    private IApprovalVouchersService approvalVouchersService;
    @Autowired
    private IApprovalProcessService approvalProcessService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject saveEducation(EducationForm educationForm) {
        // 检测传入表单是否为空
        AssertUtils.asserts(Objects.isNull(educationForm), ProjectErrorType.No_FROM);
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo = commonService.getOrganization();

        String projectId = null;
        // 保存项目信息
        ProjectsForm projectsForm = educationForm.getProjectsForm();
        Projects projects = new BeanUtils<Projects>().copyObj(projectsForm, Projects.class);
        // projectId为空，则新增
        if (Objects.nonNull(projectsForm.getProjectId())) {
            QueryWrapper<Projects> projectsQueryWrapper = new QueryWrapper<>();
            projectsQueryWrapper.eq(Projects.PROJECT_ID, projectsForm.getProjectId());
            Projects queryProject = projectsService.getOne(projectsQueryWrapper);
            if (Objects.nonNull(queryProject)) {
                // 判断当前导入项目状态是否为 待评审  已立项，则为关联项目
                if (ValueEnums.PROJECT_REPLIED.getCode().equals(projectsForm.getProjectStatusCode())
                        || ValueEnums.PROJECT_ESTABLISHED.getCode().equals(projectsForm.getProjectStatusCode())) {
                    projects.setProjectTypeCode(projectsForm.getProjectTypeCode());
                    projects.setProjectTypeName(projectsForm.getProjectTypeName());
                    // 设置项目状态(保存 ---> 项目状态  7001 <--> 未申报)
                    projects.setProjectStatusCode(ValueEnums.PROJECT_UNDECLARED.getCode());
                    projects.setProjectStatusName(ValueEnums.PROJECT_UNDECLARED.getValue());
                    // 组织code
                    projects.setOrganizationCode(organizationVo.getCode());
                    projects.setApplicationMark("true");
                    // 赋值创建人（当前登录用户）
                    projects.setCreateUserId(userVo.getId());
                    projects.setCreateUserName(userVo.getName());
                    projectsService.save(projects);
                    projectId = String.valueOf(projects.getId());
                    projects.setProjectId(projectId);
                    projectsService.saveOrUpdate(projects);
                } else {
                    projects.setProjectTypeCode(projectsForm.getProjectTypeCode());
                    projects.setProjectTypeName(projectsForm.getProjectTypeName());
                    // 设置项目状态(保存 ---> 项目状态  7001 <--> 未申报)
                    projects.setProjectStatusCode(ValueEnums.PROJECT_UNDECLARED.getCode());
                    projects.setProjectStatusName(ValueEnums.PROJECT_UNDECLARED.getValue());
                    // 组织code
                    projects.setOrganizationCode(organizationVo.getCode());
                    // 是否为关联项目 true 是  false 否
                    projects.setApplicationMark("false");
                    // 赋值创建人（当前登录用户）
                    projects.setCreateUserId(userVo.getId());
                    projects.setCreateUserName(userVo.getName());
                    projectsService.update(projects, projectsQueryWrapper);
                    projectId = projects.getProjectId();
                }
            } else {
                projects.setProjectTypeCode(projectsForm.getProjectTypeCode());
                projects.setProjectTypeName(projectsForm.getProjectTypeName());
                // 设置项目状态(保存 ---> 项目状态  7001 <--> 未申报)
                projects.setProjectStatusCode(ValueEnums.PROJECT_UNDECLARED.getCode());
                projects.setProjectStatusName(ValueEnums.PROJECT_UNDECLARED.getValue());
                // 组织code
                projects.setOrganizationCode(organizationVo.getCode());
                // 是否为关联项目 true 是  false 否
                projects.setApplicationMark("false");
                // 赋值创建人（当前登录用户）
                projects.setCreateUserId(userVo.getId());
                projects.setCreateUserName(userVo.getName());
                projectsService.save(projects);
                projectId = String.valueOf(projects.getId());
                projects.setProjectId(projectId);
                projectsService.saveOrUpdate(projects);
            }
        } else {
            projects.setProjectTypeCode(projectsForm.getProjectTypeCode());
            projects.setProjectTypeName(projectsForm.getProjectTypeName());
            // 设置项目状态(保存 ---> 项目状态  7001 <--> 未申报)
            projects.setProjectStatusCode(ValueEnums.PROJECT_UNDECLARED.getCode());
            projects.setProjectStatusName(ValueEnums.PROJECT_UNDECLARED.getValue());
            // 组织code
            projects.setOrganizationCode(organizationVo.getCode());
            // 是否为关联项目 true 是  false 否
            projects.setApplicationMark("false");
            // 赋值创建人（当前登录用户）
            projects.setCreateUserId(userVo.getId());
            projects.setCreateUserName(userVo.getName());
            projectsService.save(projects);
            projectId = String.valueOf(projects.getId());
            projects.setProjectId(projectId);
            projectsService.saveOrUpdate(projects);
        }
        // 保存申报书信息
        ApplicationForm applicationForm = educationForm.getApplicationForm();
        String applicationId = null;
        String serviceBusNo = null;
        Application application = new BeanUtils<Application>().copyObj(applicationForm, Application.class);
        if (Objects.nonNull(applicationForm.getApplicationId())) {
            QueryWrapper<Application> applicationQueryWrapper = new QueryWrapper<>();
            applicationQueryWrapper.eq(Application.APPLICATION_ID, applicationForm.getApplicationId());
            Application queryApplication = applicationService.getOne(applicationQueryWrapper);
            if (Objects.nonNull(queryApplication)) {
                if (ValueEnums.PROJECT_REPLIED.getCode().equals(projectsForm.getProjectStatusCode())
                        || ValueEnums.PROJECT_ESTABLISHED.getCode().equals(projectsForm.getProjectStatusCode())) {
                    application.setProjectId(projectId);
                    // 申报年度，当前系统年
                    application.setTheApplicationYear(String.valueOf(LocalDateTime.now().getYear()));
                    // 设置申报状态（保存 --> 未申报）
                    application.setAppStateCode(ValueEnums.APPLICATION_UNDECLARED.getCode());
                    application.setAppStateName(ValueEnums.APPLICATION_UNDECLARED.getValue());
                    // 赋值创建人（当前登录用户）
                    application.setCreateUserId(userVo.getId());
                    application.setCreateUserName(userVo.getName());
                    applicationService.save(application);
                    applicationId = String.valueOf(application.getId());
                    // 保存业务单号
                    if (Objects.nonNull(projectId) && Objects.nonNull(applicationId)) {
                        serviceNumService.toSaveNum(projectId, applicationId);
                    }
                    serviceBusNo = applicationService.getById(applicationId).getServiceNum();
                    application.setApplicationId(applicationId);
                    applicationService.saveOrUpdate(application);
                } else {
                    serviceBusNo = queryApplication.getServiceNum();
                    application.setServiceNum(serviceBusNo);
                    application.setProjectId(projectId);
                    // 申报年度，当前系统年
                    application.setTheApplicationYear(String.valueOf(LocalDateTime.now().getYear()));
                    // 设置申报状态（保存 --> 未申报）
                    application.setAppStateCode(ValueEnums.APPLICATION_UNDECLARED.getCode());
                    application.setAppStateName(ValueEnums.APPLICATION_UNDECLARED.getValue());
                    // 赋值创建人（当前登录用户）
                    application.setCreateUserId(userVo.getId());
                    application.setCreateUserName(userVo.getName());
                    applicationService.update(application, applicationQueryWrapper);
                    applicationId = application.getApplicationId();
                }
            } else {
                application.setProjectId(projectId);
                // 申报年度，当前系统年
                application.setTheApplicationYear(String.valueOf(LocalDateTime.now().getYear()));
                // 设置申报状态（保存 --> 未申报）
                application.setAppStateCode(ValueEnums.APPLICATION_UNDECLARED.getCode());
                application.setAppStateName(ValueEnums.APPLICATION_UNDECLARED.getValue());
                // 赋值创建人（当前登录用户）
                application.setCreateUserId(userVo.getId());
                application.setCreateUserName(userVo.getName());
                applicationService.save(application);
                applicationId = String.valueOf(application.getId());
                // 保存业务单号
                if (Objects.nonNull(projectId) && Objects.nonNull(applicationId)) {
                    serviceNumService.toSaveNum(projectId, applicationId);
                }
                serviceBusNo = applicationService.getById(applicationId).getServiceNum();
                application.setApplicationId(applicationId);
                applicationService.saveOrUpdate(application);
            }
        } else {
            application.setProjectId(projectId);
            // 申报年度，当前系统年
            application.setTheApplicationYear(String.valueOf(LocalDateTime.now().getYear()));
            // 设置申报状态（保存 --> 未申报）
            application.setAppStateCode(ValueEnums.APPLICATION_UNDECLARED.getCode());
            application.setAppStateName(ValueEnums.APPLICATION_UNDECLARED.getValue());
            // 赋值创建人（当前登录用户）
            application.setCreateUserId(userVo.getId());
            application.setCreateUserName(userVo.getName());
            applicationService.save(application);
            applicationId = String.valueOf(application.getId());
            // 保存业务单号
            if (Objects.nonNull(projectId) && Objects.nonNull(applicationId)) {
                serviceNumService.toSaveNum(projectId, applicationId);
            }
            serviceBusNo = applicationService.getById(applicationId).getServiceNum();
            application.setApplicationId(applicationId);
            applicationService.saveOrUpdate(application);
        }

        deleteEducation(applicationId);

        // 保存活动总投资信息
        List<AllActForm> allActFormList = educationForm.getAllActFormList();
        if (CollectionUtils.isNotEmpty(allActFormList)) {
            List<AllAct> allActList = new BeanUtils<AllAct>().copyObjs(allActFormList, AllAct.class);
            for (AllAct allAct : allActList) {
                allAct.setApplicationId(applicationId);
            }
            allActService.saveBatch(allActList);
            for (AllAct allAct : allActList) {
                allAct.setAllActId(String.valueOf(allAct.getId()));
            }
            allActService.saveOrUpdateBatch(allActList);
        }
        // 保存项目支出计划总活动信息
        List<Act> actList = new BeanUtils<Act>().copyObjs(educationForm.getActFormList(), Act.class);
        if (CollectionUtils.isNotEmpty(actList)) {
            for (Act act : actList) {
                act.setApplicationId(applicationId);
            }
            actService.saveBatch(actList);
            for (Act act : actList) {
                act.setActId(String.valueOf(act.getId()));
            }
            actService.saveOrUpdateBatch(actList);
        }
        // 保存项目支出计划子活动信息
        List<Subact> subactList = new BeanUtils<Subact>().copyObjs(educationForm.getSubactFormList(), Subact.class);
        if (CollectionUtils.isNotEmpty(subactList)) {
            QueryWrapper<Act> actQueryWrapper = new QueryWrapper<>();
            actQueryWrapper.eq(Act.APPLICATION_ID, applicationId);
            List<Act> queryActList = actService.list(actQueryWrapper);
            for (Act act : queryActList) {
                for (Subact subact : subactList) {
                    if (subact.getIsRelated().equals(act.getIsRelated())) {
                        subact.setActId(String.valueOf(act.getId()));
                        subact.setActName(act.getActName());
                        subact.setActAbs(act.getDescription());
                        subactService.save(subact);
                        subact.setSubactId(String.valueOf(subact.getId()));
                        subactService.saveOrUpdate(subact);
                    }
                }
            }
        }
        // 保存项目预算信息
        JSONArray budgetJsonArray = educationForm.getBudgetFormList();
        List<Budget> budgetList = Lists.newArrayList();
        if (Objects.nonNull(budgetJsonArray)) {
            for (int i = 0; i < budgetJsonArray.size(); i++) {
                JSONObject jsonObject = budgetJsonArray.getJSONObject(i);
                String jsonString = jsonObject.toJSONString();
                String jsonTextEntity = jsonString.substring(jsonString.indexOf(":")+1, jsonString.length()-1);
                JSONArray jsonArray = JSON.parseArray(jsonTextEntity);
                for (int j = 0; j < jsonArray.size(); j++) {
                    JSONObject jsonObjectBudget = jsonArray.getJSONObject(j);
                    Budget budget = new Budget();
                    budget.setBudgetaryYear(jsonObjectBudget.getString("budgetaryYear"));
                    budget.setBasisEstimatingAppFunds(jsonObjectBudget.getString("basisEstimatingAppFunds"));
                    budget.setApplyTotal(jsonObjectBudget.getString("applyTotal"));
                    budget.setCentralFin(jsonObjectBudget.getString("centralFin"));
                    budget.setDepartment(jsonObjectBudget.getString("department"));
                    budget.setExpenseCodes(jsonObjectBudget.getString("expenseCodes"));
                    budget.setExpenseProjectCode(jsonObjectBudget.getString("expenseProjectCode"));
                    budget.setOther(jsonObjectBudget.getString("other"));
                    budgetList.add(budget);
                }
            }
            if (CollectionUtils.isNotEmpty(budgetList)) {
                for (Budget budget : budgetList) {
                    budget.setApplicationId(applicationId);
                    budget.setProjectId(projectId);
                }
                budgetService.saveBatch(budgetList);
                for (Budget budget : budgetList) {
                    budget.setBudgetId(String.valueOf(budget.getId()));
                }
                budgetService.saveOrUpdateBatch(budgetList);
            }
        }
        // 保存项目绩效信息
        JSONArray performanceFormList = educationForm.getPerformanceFormList();
        if (Objects.nonNull(performanceFormList)) {
            List<Performance> performanceList = Lists.newArrayList();
            for (int i = 0; i < performanceFormList.size(); i++) {
                Performance monthAndYearPerformance = new Performance();
                JSONObject jsonObject = performanceFormList.getJSONObject(i);
                String indexType = jsonObject.getString("indexType");
                String annualPerformanceTarget = null;
                monthAndYearPerformance.setIndexType(indexType);
                if (Objects.nonNull(jsonObject.getString("annualPerformanceTarget"))) {
                    annualPerformanceTarget = jsonObject.getString("annualPerformanceTarget");
                    monthAndYearPerformance.setAnnualPerformanceTarget(annualPerformanceTarget);
                }
                performanceList.add(monthAndYearPerformance);
                if (Objects.nonNull(jsonObject.getJSONArray("produce"))) {
                    JSONArray index1StProduceArr = jsonObject.getJSONArray("produce");
                    for (int j = 0; j < index1StProduceArr.size(); j++) {
                        Performance performanceProduce = new Performance();
                        JSONObject index1StProduceObj = index1StProduceArr.getJSONObject(j);
                        String index2NdCode = index1StProduceObj.getString("index2NdCode");
                        String index2Nd = index1StProduceObj.getString("index2Nd");
                        String index3StCode = index1StProduceObj.getString("index3StCode");
                        String index3St = index1StProduceObj.getString("index3St");
                        String indexPer = index1StProduceObj.getString("indexPer");
                        String per = index1StProduceObj.getString("per");
                        String perCode = index1StProduceObj.getString("perCode");
                        performanceProduce.setIndexType(indexType);
                        performanceProduce.setAnnualPerformanceTarget(annualPerformanceTarget);
                        performanceProduce.setIndex1st("produce");
                        performanceProduce.setIndex1stCode("1001");
                        performanceProduce.setIndex2ndCode(index2NdCode);
                        performanceProduce.setIndex2nd(index2Nd);
                        performanceProduce.setIndex3stCode(index3StCode);
                        performanceProduce.setIndex3st(index3St);
                        performanceProduce.setIndexPer(indexPer);
                        performanceProduce.setPer(per);
                        performanceProduce.setPerCode(perCode);
                        performanceList.add(performanceProduce);
                    }
                }
                if (Objects.nonNull(jsonObject.getJSONArray("benefit"))) {
                    JSONArray index1StBenefitArr = jsonObject.getJSONArray("benefit");
                    for (int j = 0; j < index1StBenefitArr.size(); j++) {
                        Performance performanceBenefit = new Performance();
                        JSONObject index1StBenefitObj = index1StBenefitArr.getJSONObject(j);
                        String index2NdCode = index1StBenefitObj.getString("index2NdCode");
                        String index2Nd = index1StBenefitObj.getString("index2Nd");
                        String index3StCode = index1StBenefitObj.getString("index3StCode");
                        String index3St = index1StBenefitObj.getString("index3St");
                        String indexPer = index1StBenefitObj.getString("indexPer");
                        String per = index1StBenefitObj.getString("per");
                        String perCode = index1StBenefitObj.getString("perCode");
                        performanceBenefit.setIndexType(indexType);
                        performanceBenefit.setAnnualPerformanceTarget(annualPerformanceTarget);
                        performanceBenefit.setIndex1st("benefit");
                        performanceBenefit.setIndex1stCode("1002");
                        performanceBenefit.setIndex2ndCode(index2NdCode);
                        performanceBenefit.setIndex2nd(index2Nd);
                        performanceBenefit.setIndex3stCode(index3StCode);
                        performanceBenefit.setIndex3st(index3St);
                        performanceBenefit.setIndexPer(indexPer);
                        performanceBenefit.setPer(per);
                        performanceBenefit.setPerCode(perCode);
                        performanceList.add(performanceBenefit);
                    }
                }
                if (Objects.nonNull(jsonObject.getJSONArray("satisfied"))) {
                    JSONArray index1StSatisfiedArr = jsonObject.getJSONArray("satisfied");
                    for (int j = 0; j < index1StSatisfiedArr.size(); j++) {
                        Performance performanceSatisfied = new Performance();
                        JSONObject index1StSatisfiedObj = index1StSatisfiedArr.getJSONObject(j);
                        String index2NdCode = index1StSatisfiedObj.getString("index2NdCode");
                        String index2Nd = index1StSatisfiedObj.getString("index2Nd");
                        String index3StCode = index1StSatisfiedObj.getString("index3StCode");
                        String index3St = index1StSatisfiedObj.getString("index3St");
                        String indexPer = index1StSatisfiedObj.getString("indexPer");
                        String per = index1StSatisfiedObj.getString("per");
                        String perCode = index1StSatisfiedObj.getString("perCode");
                        performanceSatisfied.setIndexType(indexType);
                        performanceSatisfied.setAnnualPerformanceTarget(annualPerformanceTarget);
                        performanceSatisfied.setIndex1st("satisfied");
                        performanceSatisfied.setIndex1stCode("1003");
                        performanceSatisfied.setIndex2ndCode(index2NdCode);
                        performanceSatisfied.setIndex2nd(index2Nd);
                        performanceSatisfied.setIndex3stCode(index3StCode);
                        performanceSatisfied.setIndex3st(index3St);
                        performanceSatisfied.setIndexPer(indexPer);
                        performanceSatisfied.setPer(per);
                        performanceSatisfied.setPerCode(perCode);
                        performanceList.add(performanceSatisfied);
                    }
                }
            }
            if (CollectionUtils.isNotEmpty(performanceList)) {
                for (Performance performance : performanceList) {
                    performance.setApplicationId(applicationId);
                    performance.setProjectId(projectId);
                }
                performanceService.saveBatch(performanceList, performanceList.size());
                for (Performance performance : performanceList) {
                    performance.setPerformanceId(String.valueOf(performance.getId()));
                }
                performanceService.saveOrUpdateBatch(performanceList, performanceList.size());
            }
        }
        // 保存附件信息
        List<FileVo> fileVoList = educationForm.getEnclosureFormList();
        List<Enclosure> enclosureList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(fileVoList)) {
            for (FileVo fileVo : fileVoList) {
                Enclosure enclosure = new Enclosure();
                enclosure.setApplicationId(applicationId);
                enclosure.setEnclosureType(fileVo.getFileExt());
                enclosure.setEnclosureName(fileVo.getFileName());
                enclosure.setEnclosureUrl(fileVo.getFileUrl());
                enclosure.setFileId(fileVo.getId());
                enclosureList.add(enclosure);
            }
            enclosureService.saveBatch(enclosureList);
            for (Enclosure enclosure : enclosureList) {
                enclosure.setEnclosureId(String.valueOf(enclosure.getId()));
            }
            enclosureService.saveOrUpdateBatch(enclosureList);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("projectId", projectId);
        jsonObject.put("applicationId", applicationId);
        jsonObject.put("serviceNum", serviceBusNo);
        return jsonObject;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteEducation(String applicationId) {
        if (Objects.nonNull(applicationId)) {
            // 活动总投资删除
            QueryWrapper<AllAct> allActQueryWrapper = new QueryWrapper<>();
            allActQueryWrapper.eq(AllAct.APPLICATION_ID, applicationId);
            allActService.remove(allActQueryWrapper);
            // 项目支出计划(多条)删除
            QueryWrapper<Act> actQueryWrapper = new QueryWrapper<>();
            actQueryWrapper.eq(Act.APPLICATION_ID, applicationId);
            List<Act> actList = actService.list(actQueryWrapper);
            for (Act act : actList) {
                // 项目支出计划子活动(多条)删除
                QueryWrapper<Subact> subactQueryWrapper = new QueryWrapper<>();
                subactQueryWrapper.eq(Subact.ACT_ID, act.getActId());
                List<Subact> subactList = subactService.list(subactQueryWrapper);
                for (Subact subact : subactList) {
                    if (subact.getActId().equals(act.getActId())) {
                        subactService.remove(subactQueryWrapper);
                        actService.remove(actQueryWrapper);
                    }
                }
            }
            // 项目预算(多条)删除
            QueryWrapper<Budget> budgetQueryWrapper = new QueryWrapper<>();
            budgetQueryWrapper.eq(Budget.APPLICATION_ID, applicationId);
            budgetService.remove(budgetQueryWrapper);
            // 项目绩效删除
            QueryWrapper<Performance> performanceQueryWrapper = new QueryWrapper<>();
            performanceQueryWrapper.eq(Performance.APPLICATION_ID, applicationId);
            performanceService.remove(performanceQueryWrapper);
            // 附件删除
            QueryWrapper<Enclosure> enclosureQueryWrapper = new QueryWrapper<>();
            enclosureQueryWrapper.eq(Enclosure.APPLICATION_ID, applicationId);
            enclosureService.remove(enclosureQueryWrapper);
        }
    }

    @Override
    public EducationVo getEducation(String applicationId) {
        EducationVo educationVo = new EducationVo();
        AssertUtils.asserts(Objects.isNull(applicationId), ProjectErrorType.ID_CANT_BE_NULL);
        // 获取申报书信息
        QueryWrapper<Application> applicationQueryWrapper = new QueryWrapper<>();
        applicationQueryWrapper.eq(Application.APPLICATION_ID, applicationId);
        Application application = applicationService.getOne(applicationQueryWrapper);
        String projectId = null;
        if (Objects.nonNull(application)) {
            projectId = application.getProjectId();
            ApplicationVo applicationVo = new BeanUtils<ApplicationVo>().copyObj(application, ApplicationVo.class);
            educationVo.setApplicationVo(applicationVo);
        }
        // 获取项目基本信息
        QueryWrapper<Projects> projectsQueryWrapper = new QueryWrapper<>();
        projectsQueryWrapper.eq(Projects.ID, projectId);
        Projects projects = projectsService.getOne(projectsQueryWrapper);
        if (Objects.nonNull(projects)) {
            ProjectsVo projectsVo = new BeanUtils<ProjectsVo>().copyObj(projects, ProjectsVo.class);
            educationVo.setProjectsVo(projectsVo);
        }
        // 获取活动总投资信息
        QueryWrapper<AllAct> allActQueryWrapper = new QueryWrapper<>();
        allActQueryWrapper.eq(AllAct.APPLICATION_ID, applicationId);
        List<AllAct> allActList = allActService.list(allActQueryWrapper);
        if (CollectionUtils.isNotEmpty(allActList)) {
            List<AllActVo> allActVoList = new BeanUtils<AllActVo>().copyObjs(allActList, AllActVo.class);
            educationVo.setAllActVoList(allActVoList);
        }
        //  获取项目支出计划(多条)
        QueryWrapper<Act> actQueryWrapper = new QueryWrapper<>();
        actQueryWrapper.eq(Act.APPLICATION_ID, applicationId);
        List<Act> actList = actService.list(actQueryWrapper);
        if (CollectionUtils.isNotEmpty(actList)) {
            List<ActVo> actVoList = new BeanUtils<ActVo>().copyObjs(actList, ActVo.class);
            educationVo.setActVoList(actVoList);
        }
        //  获取项目支出计划子活动(多条)
        for(Act act: actList) {
            QueryWrapper<Subact> subactQueryWrapper = new QueryWrapper<>();
            subactQueryWrapper.eq(Subact.ACT_ID, act.getActId());
            List<SubactVo> subActVoList = new BeanUtils<SubactVo>().copyObjs(subactService.list(subactQueryWrapper),SubactVo.class);
            educationVo.setSubactsVoList(subActVoList);
        }
        // 获取项目预算(多条)信息
        List<Application> applicationList = applicationService.getAllList(projectId);
        if (CollectionUtils.isNotEmpty(applicationList)) {
            JSONArray parentArray = new JSONArray();
            for (Application application1 : applicationList) {
                String apId = application1.getApplicationId();
                List<Budget> budgets = budgetService.getAppBudgetList(apId);
                List<Budget> budgetYearList = budgetService.getBudgetYearList(apId);
                if (CollectionUtils.isNotEmpty(budgets) && CollectionUtils.isNotEmpty(budgetYearList)) {
                    for (Budget s : budgetYearList) {
                        JSONObject parentJson = new JSONObject();
                        JSONArray childrenArray = new JSONArray();
                        for (Budget budget : budgets) {
                            if (Objects.nonNull(budget.getBudgetaryYear()) && Objects.nonNull(s.getBudgetaryYear())) {
                                if (s.getBudgetaryYear().equals(budget.getBudgetaryYear())) {
                                    childrenArray.add(budget);
                                }
                            }
                        }
                        parentJson.put(s.getBudgetaryYear(), childrenArray);
                        parentArray.add(parentJson);
                    }
                }
            }
            educationVo.setBudgetVoList(parentArray);
        }
        // 获取项目绩效信息
        QueryWrapper<Performance> performanceQueryWrapper = new QueryWrapper<>();
        performanceQueryWrapper.eq(Performance.APPLICATION_ID, applicationId);
        List<Performance> performanceList = performanceService.list(performanceQueryWrapper);
        List<Performance> performanceIndexTypeList = performanceService.getIndexTypeListWithDistinct(applicationId);
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
            educationVo.setPerformanceVoList(parentArray);
        }
        // 获取附件信息
        QueryWrapper<Enclosure> enclosureQueryWrapper = new QueryWrapper<>();
        enclosureQueryWrapper.eq(Enclosure.APPLICATION_ID, applicationId);
        List<Enclosure> enclosureList = enclosureService.list(enclosureQueryWrapper);
        if (CollectionUtils.isNotEmpty(enclosureList)) {
            List<EnclosureVo> enclosureVoList = new BeanUtils<EnclosureVo>().copyObjs(enclosureList, EnclosureVo.class);
            List<FileVo> fileVoList = Lists.newArrayList();
            for (EnclosureVo enclosureVo : enclosureVoList) {
                FileVo fileVo = new FileVo();
                fileVo.setId(enclosureVo.getFileId());
                fileVo.setFileName(enclosureVo.getEnclosureName());
                fileVo.setFileUrl(enclosureVo.getEnclosureUrl());
                fileVo.setFileExt(enclosureVo.getEnclosureType());

                fileVoList.add(fileVo);
            }
            educationVo.setEnclosureVoList(fileVoList);
        }
        return educationVo;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void submitEducation(EducationForm educationForm) {
        // 检测传入表单是否为空
        AssertUtils.asserts(Objects.isNull(educationForm), ProjectErrorType.No_FROM);
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo = commonService.getOrganization();

        String projectId = null;
        // 保存项目信息
        ProjectsForm projectsForm = educationForm.getProjectsForm();
        Projects projects = new BeanUtils<Projects>().copyObj(projectsForm, Projects.class);
        // projectId为空，则新增
        if (Objects.nonNull(projectsForm.getProjectId())) {
            QueryWrapper<Projects> projectsQueryWrapper = new QueryWrapper<>();
            projectsQueryWrapper.eq(Projects.PROJECT_ID, projectsForm.getProjectId());
            Projects queryProject = projectsService.getOne(projectsQueryWrapper);
            if (Objects.nonNull(queryProject)) {
                // 判断当前导入项目状态是否为 待评审  已立项，则为关联项目
                if (ValueEnums.PROJECT_REPLIED.getCode().equals(projectsForm.getProjectStatusCode())
                        || ValueEnums.PROJECT_ESTABLISHED.getCode().equals(projectsForm.getProjectStatusCode())) {
                    projects.setProjectTypeCode(projectsForm.getProjectTypeCode());
                    projects.setProjectTypeName(projectsForm.getProjectTypeName());
                    // 设置项目状态(提交 ---> 项目状态  7002 <--> 已申报)
                    projects.setProjectStatusCode(ValueEnums.PROJECT_DECLAARED.getCode());
                    projects.setProjectStatusName(ValueEnums.PROJECT_DECLAARED.getValue());
                    // 组织code
                    projects.setOrganizationCode(organizationVo.getCode());
                    projects.setApplicationMark("true");
                    // 赋值创建人（当前登录用户）
                    projects.setCreateUserId(userVo.getId());
                    projects.setCreateUserName(userVo.getName());
                    projectsService.save(projects);
                    projectId = String.valueOf(projects.getId());
                    projects.setProjectId(projectId);
                    projectsService.saveOrUpdate(projects);
                } else {
                    projects.setProjectTypeCode(projectsForm.getProjectTypeCode());
                    projects.setProjectTypeName(projectsForm.getProjectTypeName());
                    // 设置项目状态(提交 ---> 项目状态  7002 <--> 已申报)
                    projects.setProjectStatusCode(ValueEnums.PROJECT_DECLAARED.getCode());
                    projects.setProjectStatusName(ValueEnums.PROJECT_DECLAARED.getValue());
                    // 组织code
                    projects.setOrganizationCode(organizationVo.getCode());
                    // 是否为关联项目 true 是  false 否
                    projects.setApplicationMark("false");
                    // 赋值创建人（当前登录用户）
                    projects.setCreateUserId(userVo.getId());
                    projects.setCreateUserName(userVo.getName());
                    projectsService.update(projects, projectsQueryWrapper);
                    projectId = projects.getProjectId();
                }
            } else {
                projects.setProjectTypeCode(projectsForm.getProjectTypeCode());
                projects.setProjectTypeName(projectsForm.getProjectTypeName());
                // 设置项目状态(提交 ---> 项目状态  7002 <--> 已申报)
                projects.setProjectStatusCode(ValueEnums.PROJECT_DECLAARED.getCode());
                projects.setProjectStatusName(ValueEnums.PROJECT_DECLAARED.getValue());
                // 组织code
                projects.setOrganizationCode(organizationVo.getCode());
                // 是否为关联项目 true 是  false 否
                projects.setApplicationMark("false");
                // 赋值创建人（当前登录用户）
                projects.setCreateUserId(userVo.getId());
                projects.setCreateUserName(userVo.getName());
                projectsService.save(projects);
                projectId = String.valueOf(projects.getId());
                projects.setProjectId(projectId);
                projectsService.saveOrUpdate(projects);
            }
        } else {
            projects.setProjectTypeCode(projectsForm.getProjectTypeCode());
            projects.setProjectTypeName(projectsForm.getProjectTypeName());
            // 设置项目状态(提交 ---> 项目状态  7002 <--> 已申报)
            projects.setProjectStatusCode(ValueEnums.PROJECT_DECLAARED.getCode());
            projects.setProjectStatusName(ValueEnums.PROJECT_DECLAARED.getValue());
            // 组织code
            projects.setOrganizationCode(organizationVo.getCode());
            // 是否为关联项目 true 是  false 否
            projects.setApplicationMark("false");
            // 赋值创建人（当前登录用户）
            projects.setCreateUserId(userVo.getId());
            projects.setCreateUserName(userVo.getName());
            projectsService.save(projects);
            projectId = String.valueOf(projects.getId());
            projects.setProjectId(projectId);
            projectsService.saveOrUpdate(projects);
        }
        // 保存申报书信息
        ApplicationForm applicationForm = educationForm.getApplicationForm();
        String applicationId = null;
        String serviceBusNo = null;
        Application application = new BeanUtils<Application>().copyObj(applicationForm, Application.class);
        if (Objects.nonNull(applicationForm.getApplicationId())) {
            QueryWrapper<Application> applicationQueryWrapper = new QueryWrapper<>();
            applicationQueryWrapper.eq(Application.APPLICATION_ID, applicationForm.getApplicationId());
            Application queryApplication = applicationService.getOne(applicationQueryWrapper);
            if (Objects.nonNull(queryApplication)) {
                if (ValueEnums.PROJECT_REPLIED.getCode().equals(projectsForm.getProjectStatusCode())
                        || ValueEnums.PROJECT_ESTABLISHED.getCode().equals(projectsForm.getProjectStatusCode())) {
                    application.setProjectId(projectId);
                    // 申报年度，当前系统年
                    application.setTheApplicationYear(String.valueOf(LocalDateTime.now().getYear()));
                    // 设置申报状态（提交 --> 已申报）
                    application.setAppStateCode(ValueEnums.APPLICATION_DECLAARED.getCode());
                    application.setAppStateName(ValueEnums.APPLICATION_DECLAARED.getValue());
                    // 赋值创建人（当前登录用户）
                    application.setCreateUserId(userVo.getId());
                    application.setCreateUserName(userVo.getName());
                    applicationService.save(application);
                    applicationId = String.valueOf(application.getId());
                    // 保存业务单号
                    if (Objects.nonNull(projectId) && Objects.nonNull(applicationId)) {
                        serviceNumService.toSaveNum(projectId, applicationId);
                    }
                    //serviceBusNo = applicationService.getById(applicationId).getServiceNum();
                    application.setApplicationId(applicationId);
                    applicationService.saveOrUpdate(application);
                } else {
                    serviceBusNo = queryApplication.getServiceNum();
                    application.setServiceNum(serviceBusNo);
                    application.setProjectId(projectId);
                    // 申报年度，当前系统年
                    application.setTheApplicationYear(String.valueOf(LocalDateTime.now().getYear()));
                    // 设置申报状态（提交 --> 已申报）
                    application.setAppStateCode(ValueEnums.APPLICATION_DECLAARED.getCode());
                    application.setAppStateName(ValueEnums.APPLICATION_DECLAARED.getValue());
                    // 赋值创建人（当前登录用户）
                    application.setCreateUserId(userVo.getId());
                    application.setCreateUserName(userVo.getName());
                    applicationService.update(application, applicationQueryWrapper);
                    applicationId = application.getApplicationId();
                }
            } else {
                application.setProjectId(projectId);
                // 申报年度，当前系统年
                application.setTheApplicationYear(String.valueOf(LocalDateTime.now().getYear()));
                // 设置申报状态（提交 --> 已申报）
                application.setAppStateCode(ValueEnums.APPLICATION_DECLAARED.getCode());
                application.setAppStateName(ValueEnums.APPLICATION_DECLAARED.getValue());
                // 赋值创建人（当前登录用户）
                application.setCreateUserId(userVo.getId());
                application.setCreateUserName(userVo.getName());
                applicationService.save(application);
                applicationId = String.valueOf(application.getId());
                // 保存业务单号
                if (Objects.nonNull(projectId) && Objects.nonNull(applicationId)) {
                    serviceNumService.toSaveNum(projectId, applicationId);
                }
                //serviceBusNo = applicationService.getById(applicationId).getServiceNum();
                application.setApplicationId(applicationId);
                applicationService.saveOrUpdate(application);
            }
        } else {
            application.setProjectId(projectId);
            // 申报年度，当前系统年
            application.setTheApplicationYear(String.valueOf(LocalDateTime.now().getYear()));
            // 设置申报状态（提交 --> 已申报）
            application.setAppStateCode(ValueEnums.APPLICATION_DECLAARED.getCode());
            application.setAppStateName(ValueEnums.APPLICATION_DECLAARED.getValue());
            // 赋值创建人（当前登录用户）
            application.setCreateUserId(userVo.getId());
            application.setCreateUserName(userVo.getName());
            applicationService.save(application);
            applicationId = String.valueOf(application.getId());
            // 保存业务单号
            if (Objects.nonNull(projectId) && Objects.nonNull(applicationId)) {
                serviceNumService.toSaveNum(projectId, applicationId);
            }
            //serviceBusNo = applicationService.getById(applicationId).getServiceNum();
            application.setApplicationId(applicationId);
            applicationService.saveOrUpdate(application);
        }

        deleteEducation(applicationId);

        // 保存活动总投资信息
        List<AllActForm> allActFormList = educationForm.getAllActFormList();
        if (CollectionUtils.isNotEmpty(allActFormList)) {
            List<AllAct> allActList = new BeanUtils<AllAct>().copyObjs(allActFormList, AllAct.class);
            for (AllAct allAct : allActList) {
                allAct.setApplicationId(applicationId);
            }
            allActService.saveBatch(allActList);
            for (AllAct allAct : allActList) {
                allAct.setAllActId(String.valueOf(allAct.getId()));
            }
            allActService.saveOrUpdateBatch(allActList);
        }
        // 保存项目支出计划总活动信息
        List<Act> actList = new BeanUtils<Act>().copyObjs(educationForm.getActFormList(), Act.class);
        if (CollectionUtils.isNotEmpty(actList)) {
            for (Act act : actList) {
                act.setApplicationId(applicationId);
            }
            actService.saveBatch(actList);
            for (Act act : actList) {
                act.setActId(String.valueOf(act.getId()));
            }
            actService.saveOrUpdateBatch(actList);
        }
        // 保存项目支出计划子活动信息
        List<Subact> subactList = new BeanUtils<Subact>().copyObjs(educationForm.getSubactFormList(), Subact.class);
        if (CollectionUtils.isNotEmpty(subactList)) {
            QueryWrapper<Act> actQueryWrapper = new QueryWrapper<>();
            actQueryWrapper.eq(Act.APPLICATION_ID, applicationId);
            List<Act> queryActList = actService.list(actQueryWrapper);
            for (Act act : queryActList) {
                for (Subact subact : subactList) {
                    if (subact.getIsRelated().equals(act.getIsRelated())) {
                        subact.setActId(String.valueOf(act.getId()));
                        subact.setActName(act.getActName());
                        subact.setActAbs(act.getDescription());
                        subactService.save(subact);
                        subact.setSubactId(String.valueOf(subact.getId()));
                        subactService.saveOrUpdate(subact);
                    }
                }
            }
        }
        // 保存项目预算信息
        JSONArray budgetJsonArray = educationForm.getBudgetFormList();
        List<Budget> budgetList = Lists.newArrayList();
        if (Objects.nonNull(budgetJsonArray)) {
            for (int i = 0; i < budgetJsonArray.size(); i++) {
                JSONObject jsonObject = budgetJsonArray.getJSONObject(i);
                String jsonString = jsonObject.toJSONString();
                String jsonTextEntity = jsonString.substring(jsonString.indexOf(":")+1, jsonString.length()-1);
                JSONArray jsonArray = JSON.parseArray(jsonTextEntity);
                for (int j = 0; j < jsonArray.size(); j++) {
                    JSONObject jsonObjectBudget = jsonArray.getJSONObject(j);
                    Budget budget = new Budget();
                    budget.setBudgetaryYear(jsonObjectBudget.getString("budgetaryYear"));
                    budget.setBasisEstimatingAppFunds(jsonObjectBudget.getString("basisEstimatingAppFunds"));
                    budget.setApplyTotal(jsonObjectBudget.getString("applyTotal"));
                    budget.setCentralFin(jsonObjectBudget.getString("centralFin"));
                    budget.setDepartment(jsonObjectBudget.getString("department"));
                    budget.setExpenseCodes(jsonObjectBudget.getString("expenseCodes"));
                    budget.setExpenseProjectCode(jsonObjectBudget.getString("expenseProjectCode"));
                    budget.setOther(jsonObjectBudget.getString("other"));
                    budgetList.add(budget);
                }
            }
            if (CollectionUtils.isNotEmpty(budgetList)) {
                for (Budget budget : budgetList) {
                    budget.setApplicationId(applicationId);
                    budget.setProjectId(projectId);
                }
                budgetService.saveBatch(budgetList);
                for (Budget budget : budgetList) {
                    budget.setBudgetId(String.valueOf(budget.getId()));
                }
                budgetService.saveOrUpdateBatch(budgetList);
            }
        }
        // 保存项目绩效信息
        JSONArray performanceFormList = educationForm.getPerformanceFormList();
        if (Objects.nonNull(performanceFormList)) {
            List<Performance> performanceList = Lists.newArrayList();
            for (int i = 0; i < performanceFormList.size(); i++) {
                Performance monthAndYearPerformance = new Performance();
                JSONObject jsonObject = performanceFormList.getJSONObject(i);
                String indexType = jsonObject.getString("indexType");
                String annualPerformanceTarget = null;
                monthAndYearPerformance.setIndexType(indexType);
                if (Objects.nonNull(jsonObject.getString("annualPerformanceTarget"))) {
                    annualPerformanceTarget = jsonObject.getString("annualPerformanceTarget");
                    monthAndYearPerformance.setAnnualPerformanceTarget(annualPerformanceTarget);
                }
                performanceList.add(monthAndYearPerformance);
                if (Objects.nonNull(jsonObject.getJSONArray("produce"))) {
                    JSONArray index1StProduceArr = jsonObject.getJSONArray("produce");
                    for (int j = 0; j < index1StProduceArr.size(); j++) {
                        Performance performanceProduce = new Performance();
                        JSONObject index1StProduceObj = index1StProduceArr.getJSONObject(j);
                        String index2NdCode = index1StProduceObj.getString("index2NdCode");
                        String index2Nd = index1StProduceObj.getString("index2Nd");
                        String index3StCode = index1StProduceObj.getString("index3StCode");
                        String index3St = index1StProduceObj.getString("index3St");
                        String indexPer = index1StProduceObj.getString("indexPer");
                        String per = index1StProduceObj.getString("per");
                        String perCode = index1StProduceObj.getString("perCode");
                        performanceProduce.setIndexType(indexType);
                        performanceProduce.setAnnualPerformanceTarget(annualPerformanceTarget);
                        performanceProduce.setIndex1st("produce");
                        performanceProduce.setIndex1stCode("1001");
                        performanceProduce.setIndex2ndCode(index2NdCode);
                        performanceProduce.setIndex2nd(index2Nd);
                        performanceProduce.setIndex3stCode(index3StCode);
                        performanceProduce.setIndex3st(index3St);
                        performanceProduce.setIndexPer(indexPer);
                        performanceProduce.setPer(per);
                        performanceProduce.setPerCode(perCode);
                        performanceList.add(performanceProduce);
                    }
                }
                if (Objects.nonNull(jsonObject.getJSONArray("benefit"))) {
                    JSONArray index1StBenefitArr = jsonObject.getJSONArray("benefit");
                    for (int j = 0; j < index1StBenefitArr.size(); j++) {
                        Performance performanceBenefit = new Performance();
                        JSONObject index1StBenefitObj = index1StBenefitArr.getJSONObject(j);
                        String index2NdCode = index1StBenefitObj.getString("index2NdCode");
                        String index2Nd = index1StBenefitObj.getString("index2Nd");
                        String index3StCode = index1StBenefitObj.getString("index3StCode");
                        String index3St = index1StBenefitObj.getString("index3St");
                        String indexPer = index1StBenefitObj.getString("indexPer");
                        String per = index1StBenefitObj.getString("per");
                        String perCode = index1StBenefitObj.getString("perCode");
                        performanceBenefit.setIndexType(indexType);
                        performanceBenefit.setAnnualPerformanceTarget(annualPerformanceTarget);
                        performanceBenefit.setIndex1st("benefit");
                        performanceBenefit.setIndex1stCode("1002");
                        performanceBenefit.setIndex2ndCode(index2NdCode);
                        performanceBenefit.setIndex2nd(index2Nd);
                        performanceBenefit.setIndex3stCode(index3StCode);
                        performanceBenefit.setIndex3st(index3St);
                        performanceBenefit.setIndexPer(indexPer);
                        performanceBenefit.setPer(per);
                        performanceBenefit.setPerCode(perCode);
                        performanceList.add(performanceBenefit);
                    }
                }
                if (Objects.nonNull(jsonObject.getJSONArray("satisfied"))) {
                    JSONArray index1StSatisfiedArr = jsonObject.getJSONArray("satisfied");
                    for (int j = 0; j < index1StSatisfiedArr.size(); j++) {
                        Performance performanceSatisfied = new Performance();
                        JSONObject index1StSatisfiedObj = index1StSatisfiedArr.getJSONObject(j);
                        String index2NdCode = index1StSatisfiedObj.getString("index2NdCode");
                        String index2Nd = index1StSatisfiedObj.getString("index2Nd");
                        String index3StCode = index1StSatisfiedObj.getString("index3StCode");
                        String index3St = index1StSatisfiedObj.getString("index3St");
                        String indexPer = index1StSatisfiedObj.getString("indexPer");
                        String per = index1StSatisfiedObj.getString("per");
                        String perCode = index1StSatisfiedObj.getString("perCode");
                        performanceSatisfied.setIndexType(indexType);
                        performanceSatisfied.setAnnualPerformanceTarget(annualPerformanceTarget);
                        performanceSatisfied.setIndex1st("satisfied");
                        performanceSatisfied.setIndex1stCode("1003");
                        performanceSatisfied.setIndex2ndCode(index2NdCode);
                        performanceSatisfied.setIndex2nd(index2Nd);
                        performanceSatisfied.setIndex3stCode(index3StCode);
                        performanceSatisfied.setIndex3st(index3St);
                        performanceSatisfied.setIndexPer(indexPer);
                        performanceSatisfied.setPer(per);
                        performanceSatisfied.setPerCode(perCode);
                        performanceList.add(performanceSatisfied);
                    }
                }
            }
            if (CollectionUtils.isNotEmpty(performanceList)) {
                for (Performance performance : performanceList) {
                    performance.setApplicationId(applicationId);
                    performance.setProjectId(projectId);
                }
                performanceService.saveBatch(performanceList, performanceList.size());
                for (Performance performance : performanceList) {
                    performance.setPerformanceId(String.valueOf(performance.getId()));
                }
                performanceService.saveOrUpdateBatch(performanceList, performanceList.size());
            }
        }
        // 保存附件信息
        List<FileVo> fileVoList = educationForm.getEnclosureFormList();
        List<Enclosure> enclosureList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(fileVoList)) {
            for (FileVo fileVo : fileVoList) {
                Enclosure enclosure = new Enclosure();
                enclosure.setApplicationId(applicationId);
                enclosure.setEnclosureType(fileVo.getFileExt());
                enclosure.setEnclosureName(fileVo.getFileName());
                enclosure.setEnclosureUrl(fileVo.getFileUrl());
                enclosure.setFileId(fileVo.getId());
                enclosureList.add(enclosure);
            }
            enclosureService.saveBatch(enclosureList);
            for (Enclosure enclosure : enclosureList) {
                enclosure.setEnclosureId(String.valueOf(enclosure.getId()));
            }
            enclosureService.saveOrUpdateBatch(enclosureList);
        }

        Map<String, String> processVariables = new HashMap<>();
        processVariables.put("projectType",projects.getProjectTypeCode());
        Result result = commonService.startAuditProcess(applicationId,processVariables,VoucherTypeEnums.PROJECT_APPLY_PROCESS);
        if(result.isSuccess()){
            //generalEducationPDF(applicationId, null, null);
        }
    }

    @Override
    public void generalEducationPDF(String applicationId, HttpServletRequest request, HttpServletResponse response) {
        AssertUtils.asserts(Objects.isNull(applicationId), ProjectErrorType.ID_CANT_BE_NULL);
        // TODO 教育修购PDF导出

        // 一、 根据申报书ID获取当前与之对应的数据

        // 二、 将获取的数据进行指定Wor模板数据填充，并生成Word文档

        // 三、 获取生成的Word文档，并将其转换为PDF文件

        // 四、 将生成的PDF文件上传至文件服务器并将返回的数据值持久化申报书所对应的附件表

    }
}
