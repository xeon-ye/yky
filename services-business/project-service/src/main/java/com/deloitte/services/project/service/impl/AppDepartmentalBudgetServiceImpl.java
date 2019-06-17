package com.deloitte.services.project.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.platform.api.fileservice.vo.FileInfoVo;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.project.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.project.common.enums.ProjectErrorType;
import com.deloitte.services.project.common.enums.ValueEnums;
import com.deloitte.services.project.common.enums.VoucherTypeEnums;
import com.deloitte.services.project.common.util.AssertUtils;
import com.deloitte.services.project.common.util.ExportWordUtils;
import com.deloitte.services.project.common.util.WordConvertUtil;
import com.deloitte.services.project.entity.*;
import com.deloitte.services.project.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @Author : JeaChen
 * @Date : Create in 2019/5/14 15:39
 * @Description : 部门预算项目申报业务实现类
 * @Modified:
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class AppDepartmentalBudgetServiceImpl implements IAppDepartmentalBudgetService {

    @Autowired
    private ApplicationServiceImpl applicationService;
    @Autowired
    private ProjectsServiceImpl projectsService;
    @Autowired
    private ProUserServiceImpl proUserService;
    @Autowired
    private PoServiceImpl poService;
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
    private EnclosureServiceImpl enclosureService;
    @Autowired
    private ServiceNumServiceImpl serviceNumService;
    @Autowired
    public IBPMService bpmService;
    @Autowired
    private ICommonService commonService;
    @Autowired
    private IApprovalVouchersService approvalVouchersService;
    @Autowired
    private IApprovalProcessService approvalProcessService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject saveDepartmentalBudget(DeclarationsForm declarationsForm) {
        // 检测传入表单是否为空
        AssertUtils.asserts(Objects.isNull(declarationsForm), ProjectErrorType.No_FROM);
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo = commonService.getOrganization();

        String projectId = null;
        // 保存项目信息
        ProjectsForm projectsForm = declarationsForm.getProjectsForm();
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
                    // 设置项目类型“项目预算”->【医院服务能力建设】、【公共卫生专项任务经费】、【归口管理科学支出其他项目】
                    String projectTypeCode = null;
                    String projectTypeName = null;
                    String typeCode = projectsForm.getProjectTypeCode();
                    if ("1001".equals(typeCode)) {
                        projectTypeCode = typeCode;
                        projectTypeName = "医院服务能力建设";
                    } else if ("1002".equals(typeCode)) {
                        projectTypeCode = typeCode;
                        projectTypeName = "公共卫生专项任务经费";
                    } else if ("1003".equals(typeCode)) {
                        projectTypeCode = typeCode;
                        projectTypeName = "归口管理科学支出其他项目";
                    }
                    projects.setProjectTypeCode(projectTypeCode);
                    projects.setProjectTypeName(projectTypeName);
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
                    // 设置项目类型“项目预算”->【医院服务能力建设】、【公共卫生专项任务经费】、【归口管理科学支出其他项目】
                    String projectTypeCode = null;
                    String projectTypeName = null;
                    String typeCode = projectsForm.getProjectTypeCode();
                    if ("1001".equals(typeCode)) {
                        projectTypeCode = typeCode;
                        projectTypeName = "医院服务能力建设";
                    } else if ("1002".equals(typeCode)) {
                        projectTypeCode = typeCode;
                        projectTypeName = "公共卫生专项任务经费";
                    } else if ("1003".equals(typeCode)) {
                        projectTypeCode = typeCode;
                        projectTypeName = "归口管理科学支出其他项目";
                    }
                    projects.setProjectTypeCode(projectTypeCode);
                    projects.setProjectTypeName(projectTypeName);
                    // 设置项目状态(保存 ---> 项目状态  7001 <--> 未申报)
                    projects.setProjectStatusCode(ValueEnums.PROJECT_UNDECLARED.getCode());
                    projects.setProjectStatusName(ValueEnums.PROJECT_UNDECLARED.getValue());
                    // 组织code
                    projects.setOrganizationCode(organizationVo.getCode());
                    // 赋值创建人（当前登录用户）
                    projects.setCreateUserId(userVo.getId());
                    projects.setCreateUserName(userVo.getName());
                    // 是否为关联项目 true 是  false 否
                    projects.setApplicationMark("false");
                    projectsService.update(projects, projectsQueryWrapper);
                    projectId = projects.getProjectId();
                }
            } else {
                // 设置项目类型“项目预算”->【医院服务能力建设】、【公共卫生专项任务经费】、【归口管理科学支出其他项目】
                String projectTypeCode = null;
                String projectTypeName = null;
                String typeCode = projectsForm.getProjectTypeCode();
                if ("1001".equals(typeCode)) {
                    projectTypeCode = typeCode;
                    projectTypeName = "医院服务能力建设";
                } else if ("1002".equals(typeCode)) {
                    projectTypeCode = typeCode;
                    projectTypeName = "公共卫生专项任务经费";
                } else if ("1003".equals(typeCode)) {
                    projectTypeCode = typeCode;
                    projectTypeName = "归口管理科学支出其他项目";
                }
                projects.setProjectTypeCode(projectTypeCode);
                projects.setProjectTypeName(projectTypeName);
                // 设置项目状态(保存 ---> 项目状态  7001 <--> 未申报)
                projects.setProjectStatusCode(ValueEnums.PROJECT_UNDECLARED.getCode());
                projects.setProjectStatusName(ValueEnums.PROJECT_UNDECLARED.getValue());
                // 组织code
                projects.setOrganizationCode(organizationVo.getCode());
                // 赋值创建人（当前登录用户）
                projects.setCreateUserId(userVo.getId());
                projects.setCreateUserName(userVo.getName());
                // 是否为关联项目 true 是  false 否
                projects.setApplicationMark("false");
                projectsService.save(projects);
                projectId = String.valueOf(projects.getId());
                projects.setProjectId(projectId);
                projectsService.saveOrUpdate(projects);
            }
        } else {
            // 设置项目类型“项目预算”->【医院服务能力建设】、【公共卫生专项任务经费】、【归口管理科学支出其他项目】
            String projectTypeCode = null;
            String projectTypeName = null;
            String typeCode = projectsForm.getProjectTypeCode();
            if ("1001".equals(typeCode)) {
                projectTypeCode = typeCode;
                projectTypeName = "医院服务能力建设";
            } else if ("1002".equals(typeCode)) {
                projectTypeCode = typeCode;
                projectTypeName = "公共卫生专项任务经费";
            } else if ("1003".equals(typeCode)) {
                projectTypeCode = typeCode;
                projectTypeName = "归口管理科学支出其他项目";
            }
            projects.setProjectTypeCode(projectTypeCode);
            projects.setProjectTypeName(projectTypeName);
            // 设置项目状态(保存 ---> 项目状态  7001 <--> 未申报)
            projects.setProjectStatusCode(ValueEnums.PROJECT_UNDECLARED.getCode());
            projects.setProjectStatusName(ValueEnums.PROJECT_UNDECLARED.getValue());
            // 组织code
            projects.setOrganizationCode(organizationVo.getCode());
            // 赋值创建人（当前登录用户）
            projects.setCreateUserId(userVo.getId());
            projects.setCreateUserName(userVo.getName());
            // 是否为关联项目 true 是  false 否
            projects.setApplicationMark("false");
            projectsService.save(projects);
            projectId = String.valueOf(projects.getId());
            projects.setProjectId(projectId);
            projectsService.saveOrUpdate(projects);
        }
        // 保存申报书信息
        ApplicationForm applicationForm = declarationsForm.getApplicationForm();
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

        deleteDepeartalBudget(applicationId);

        // 保存采购内容信息
        if (CollectionUtils.isNotEmpty(declarationsForm.getPoFormList())) {
            List<Po> poFormList = new BeanUtils<Po>().copyObjs(declarationsForm.getPoFormList(), Po.class);
            for (Po po : poFormList) {
                po.setApplicationId(applicationId);
            }
            poService.saveBatch(poFormList);
            for (Po po : poFormList) {
                po.setPoId(String.valueOf(po.getId()));
            }
            poService.saveOrUpdateBatch(poFormList);
        }
        // 保存活动总投资信息
        List<AllActForm> allActFormList = declarationsForm.getAllActFormList();
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
        List<Act> actList = new BeanUtils<Act>().copyObjs(declarationsForm.getActFormList(), Act.class);
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
        List<Subact> subactList = new BeanUtils<Subact>().copyObjs(declarationsForm.getSubactFormList(), Subact.class);
        if (CollectionUtils.isNotEmpty(subactList)) {
            QueryWrapper<Act> actQueryWrapper = new QueryWrapper<>();
            actQueryWrapper.eq(Act.APPLICATION_ID, applicationId);
            List<Act> queryActList = actService.list(actQueryWrapper);
            if (CollectionUtils.isNotEmpty(queryActList)) {
                for (Act act : queryActList) {
                    for (Subact subact : subactList) {
                        if (subact.getIsRelated().equals(act.getIsRelated())) {
                            subact.setActId(String.valueOf(act.getId()));
                            subact.setApplicationId(applicationId);
                            subact.setActName(act.getActName());
                            subact.setActAbs(act.getDescription());
                            subactService.save(subact);
                            subact.setSubactId(String.valueOf(subact.getId()));
                            subactService.saveOrUpdate(subact);
                        }
                    }
                }
            }
        }
        // 保存项目预算信息
        JSONArray budgetJsonArray = declarationsForm.getBudgetFormList();
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
        JSONArray performanceFormList = declarationsForm.getPerformanceFormList();
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
        List<FileVo> fileVoList = declarationsForm.getEnclosureFormList();
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
    public void deleteDepeartalBudget(String applicationId) {
        if (Objects.nonNull(applicationId)) {
            // 采购内容（多条）删除
            QueryWrapper<Po> poQueryWrapper = new QueryWrapper<>();
            poQueryWrapper.eq(Po.APPLICATION_ID, applicationId);
            poService.remove(poQueryWrapper);
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
    public DeclarationsVO getDepartmentBudget(String applicationId) {
        DeclarationsVO declarationsVO = new DeclarationsVO();
        AssertUtils.asserts(Objects.isNull(applicationId), ProjectErrorType.ID_CANT_BE_NULL);
        // 获取申报书信息
        QueryWrapper<Application> applicationQueryWrapper = new QueryWrapper<>();
        applicationQueryWrapper.eq(Application.APPLICATION_ID, applicationId);
        Application application = applicationService.getOne(applicationQueryWrapper);
        String projectId = null;
        if (Objects.nonNull(application)) {
            projectId = application.getProjectId();
            ApplicationVo applicationVo = new BeanUtils<ApplicationVo>().copyObj(application, ApplicationVo.class);
            declarationsVO.setApplicationVo(applicationVo);
        }
        // 获取项目基本信息
        QueryWrapper<Projects> projectsQueryWrapper = new QueryWrapper<>();
        projectsQueryWrapper.eq(Projects.ID, projectId);
        Projects projects = projectsService.getOne(projectsQueryWrapper);
        if (Objects.nonNull(projects)) {
            ProjectsVo projectsVo = new BeanUtils<ProjectsVo>().copyObj(projects, ProjectsVo.class);
            declarationsVO.setProjectsVo(projectsVo);
        }
        // 获取采购内容信息
        QueryWrapper<Po> poQueryWrapper = new QueryWrapper<>();
        poQueryWrapper.eq(Po.APPLICATION_ID, applicationId);
        List<Po> poList = poService.list(poQueryWrapper);
        if (CollectionUtils.isNotEmpty(poList)) {
            List<PoVo> poVoList = new BeanUtils<PoVo>().copyObjs(poList, PoVo.class);
            declarationsVO.setPoVoList(poVoList);
        }
        // 获取活动总投资信息
        QueryWrapper<AllAct> allActQueryWrapper = new QueryWrapper<>();
        allActQueryWrapper.eq(AllAct.APPLICATION_ID, applicationId);
        List<AllAct> allActList = allActService.list(allActQueryWrapper);
        if (CollectionUtils.isNotEmpty(allActList)) {
            List<AllActVo> allActVoList = new BeanUtils<AllActVo>().copyObjs(allActList, AllActVo.class);
            declarationsVO.setAllActVoList(allActVoList);
        }
        //  获取项目支出计划(多条)
        QueryWrapper<Act> actQueryWrapper = new QueryWrapper<>();
        actQueryWrapper.eq(Act.APPLICATION_ID, applicationId);
        List<Act> actList = actService.list(actQueryWrapper);
        if (CollectionUtils.isNotEmpty(actList)) {
            List<ActVo> actVoList = new BeanUtils<ActVo>().copyObjs(actList, ActVo.class);
            declarationsVO.setActVoList(actVoList);
        }
        //  获取项目支出计划子活动(多条)
        for(Act act: actList) {
            QueryWrapper<Subact> subactQueryWrapper = new QueryWrapper<>();
            subactQueryWrapper.eq(Subact.ACT_ID, act.getActId());
            List<SubactVo> subActVoList = new BeanUtils<SubactVo>().copyObjs(subactService.list(subactQueryWrapper),SubactVo.class);
            declarationsVO.setSubactsVoList(subActVoList);
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
            declarationsVO.setBudgetVoList(parentArray);
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
            declarationsVO.setPerformanceVoList(parentArray);
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
            declarationsVO.setEnclosureVoList(fileVoList);
        }
        return declarationsVO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void submitDepartmentBudget(DeclarationsForm declarationsForm) {
        // 检测传入表单是否为空
        AssertUtils.asserts(Objects.isNull(declarationsForm), ProjectErrorType.No_FROM);
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo = commonService.getOrganization();

        String projectId = null;
        // 保存项目信息
        ProjectsForm projectsForm = declarationsForm.getProjectsForm();
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
                    // 设置项目类型“项目预算”->【医院服务能力建设】、【公共卫生专项任务经费】、【归口管理科学支出其他项目】
                    String projectTypeCode = null;
                    String projectTypeName = null;
                    String typeCode = projectsForm.getProjectTypeCode();
                    if ("1001".equals(typeCode)) {
                        projectTypeCode = typeCode;
                        projectTypeName = "医院服务能力建设";
                    } else if ("1002".equals(typeCode)) {
                        projectTypeCode = typeCode;
                        projectTypeName = "公共卫生专项任务经费";
                    } else if ("1003".equals(typeCode)) {
                        projectTypeCode = typeCode;
                        projectTypeName = "归口管理科学支出其他项目";
                    }
                    projects.setProjectTypeCode(projectTypeCode);
                    projects.setProjectTypeName(projectTypeName);
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
                    // 设置项目类型“项目预算”->【医院服务能力建设】、【公共卫生专项任务经费】、【归口管理科学支出其他项目】
                    String projectTypeCode = null;
                    String projectTypeName = null;
                    String typeCode = projectsForm.getProjectTypeCode();
                    if ("1001".equals(typeCode)) {
                        projectTypeCode = typeCode;
                        projectTypeName = "医院服务能力建设";
                    } else if ("1002".equals(typeCode)) {
                        projectTypeCode = typeCode;
                        projectTypeName = "公共卫生专项任务经费";
                    } else if ("1003".equals(typeCode)) {
                        projectTypeCode = typeCode;
                        projectTypeName = "归口管理科学支出其他项目";
                    }
                    projects.setProjectTypeCode(projectTypeCode);
                    projects.setProjectTypeName(projectTypeName);
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
                // 设置项目类型“项目预算”->【医院服务能力建设】、【公共卫生专项任务经费】、【归口管理科学支出其他项目】
                String projectTypeCode = null;
                String projectTypeName = null;
                String typeCode = projectsForm.getProjectTypeCode();
                if ("1001".equals(typeCode)) {
                    projectTypeCode = typeCode;
                    projectTypeName = "医院服务能力建设";
                } else if ("1002".equals(typeCode)) {
                    projectTypeCode = typeCode;
                    projectTypeName = "公共卫生专项任务经费";
                } else if ("1003".equals(typeCode)) {
                    projectTypeCode = typeCode;
                    projectTypeName = "归口管理科学支出其他项目";
                }
                projects.setProjectTypeCode(projectTypeCode);
                projects.setProjectTypeName(projectTypeName);
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
            // 设置项目类型“项目预算”->【医院服务能力建设】、【公共卫生专项任务经费】、【归口管理科学支出其他项目】
            String projectTypeCode = null;
            String projectTypeName = null;
            String typeCode = projectsForm.getProjectTypeCode();
            if ("1001".equals(typeCode)) {
                projectTypeCode = typeCode;
                projectTypeName = "医院服务能力建设";
            } else if ("1002".equals(typeCode)) {
                projectTypeCode = typeCode;
                projectTypeName = "公共卫生专项任务经费";
            } else if ("1003".equals(typeCode)) {
                projectTypeCode = typeCode;
                projectTypeName = "归口管理科学支出其他项目";
            }
            projects.setProjectTypeCode(projectTypeCode);
            projects.setProjectTypeName(projectTypeName);
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
        ApplicationForm applicationForm = declarationsForm.getApplicationForm();
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
                    //serviceBusNo = queryApplication.getServiceNum();
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

        deleteDepeartalBudget(applicationId);

        // 保存采购内容信息
        if (CollectionUtils.isNotEmpty(declarationsForm.getPoFormList())) {
            List<Po> poFormList = new BeanUtils<Po>().copyObjs(declarationsForm.getPoFormList(), Po.class);
            for (Po po : poFormList) {
                po.setApplicationId(applicationId);
            }
            poService.saveBatch(poFormList);
            for (Po po : poFormList) {
                po.setPoId(String.valueOf(po.getId()));
            }
            poService.saveOrUpdateBatch(poFormList);
        }
        // 保存活动总投资信息
        List<AllActForm> allActFormList = declarationsForm.getAllActFormList();
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
        List<Act> actList = new BeanUtils<Act>().copyObjs(declarationsForm.getActFormList(), Act.class);
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
        List<Subact> subactList = new BeanUtils<Subact>().copyObjs(declarationsForm.getSubactFormList(), Subact.class);
        if (CollectionUtils.isNotEmpty(subactList)) {
            QueryWrapper<Act> actQueryWrapper = new QueryWrapper<>();
            actQueryWrapper.eq(Act.APPLICATION_ID, applicationId);
            List<Act> queryActList = actService.list(actQueryWrapper);
            if (CollectionUtils.isNotEmpty(queryActList)) {
                for (Act act : queryActList) {
                    for (Subact subact : subactList) {
                        if (subact.getIsRelated().equals(act.getIsRelated())) {
                            subact.setActId(String.valueOf(act.getId()));
                            subact.setApplicationId(applicationId);
                            subact.setActName(act.getActName());
                            subact.setActAbs(act.getDescription());
                            subactService.save(subact);
                            subact.setSubactId(String.valueOf(subact.getId()));
                            subactService.saveOrUpdate(subact);
                        }
                    }
                }
            }
        }
        // 保存项目预算信息
        JSONArray budgetJsonArray = declarationsForm.getBudgetFormList();
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
        JSONArray performanceFormList = declarationsForm.getPerformanceFormList();
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
                        performanceProduce.setApplicationId(applicationId);
                        performanceProduce.setProjectId(projectId);
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
                        performanceBenefit.setApplicationId(applicationId);
                        performanceBenefit.setProjectId(projectId);
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
                        performanceSatisfied.setApplicationId(applicationId);
                        performanceSatisfied.setProjectId(projectId);
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
        List<FileVo> fileVoList = declarationsForm.getEnclosureFormList();
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
            generalDepartmentBudgetPDF(applicationId, null, null);
        }
    }

    @Override
    public JSONObject generalDepartmentBudgetPDF(String applicationId, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        AssertUtils.asserts(Objects.isNull(applicationId), ProjectErrorType.ID_CANT_BE_NULL);
        // 一、 根据projectId获取部门预算数据
        DeclarationsVO budget = getDepartmentBudget(applicationId);
        AssertUtils.asserts(Objects.isNull(budget), ProjectErrorType.SQL_EXCEPTION);
        // 二、 将获取的数据进行指定Word模板数据填充
        ProjectsVo projectsVo = budget.getProjectsVo();
        ApplicationVo applicationVo = budget.getApplicationVo();
        List<PoVo> poVoList = budget.getPoVoList();
        List<AllActVo> allActVoList = budget.getAllActVoList();
        List<ActVo> actVoList = budget.getActVoList();
        List<SubactVo> subactsVoList = budget.getSubactsVoList();
        JSONArray budgetVoList = budget.getBudgetVoList();
        JSONArray performanceVoList = budget.getPerformanceVoList();
        // TODO 三、 获取填充数据的Word文件流，转换并最终生成PDF文件
        JSONObject dataPackage = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("projectName", projectsVo.getProjectName());
        data.put("projectCode", projectsVo.getProjectCode());
        data.put("organizationName", projectsVo.getOrganizationName());
        data.put("ouChargeStaffName", projectsVo.getOuChargeStaffName());
        data.put("projectHeaderName", projectsVo.getProjectHeaderName());
        data.put("finHeaderName", projectsVo.getFinHeaderName());
        data.put("projectCatgory", projectsVo.getProjectCatgory());
        data.put("cycle", projectsVo.getCycle());
        data.put("planYear", projectsVo.getPlanYear());
        data.put("projectHeaderPost", projectsVo.getProjectHeaderPost());
        data.put("projectConnectStaffName", projectsVo.getProjectConnectStaffName());
        data.put("proConnectStaffTel", projectsVo.getProConnectStaffTel());
        data.put("adress", projectsVo.getAdress());
        data.put("zipCode", projectsVo.getZipCode());
        data.put("budBasis", applicationVo.getBudBasis());
        data.put("budContent", applicationVo.getBudContent());
        data.put("budTargetMeasure", applicationVo.getBudTargetMeasure());
        data.put("budCondition", applicationVo.getBudCondition());
        dataPackage.put("app", data);
        Map dataMap = new HashMap();
        dataMap.put("data", dataPackage);
        String targetFileName = "附件2项目申报文本材料_" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS") + RandomUtils.nextInt(0, 9999) + ".docx";
        String fileUrl = ExportWordUtils.exportWord("template_dept_budget", dataMap, targetFileName);
        try {
            String rootPath = new File(ResourceUtils.getURL("").getPath()).getAbsolutePath();
            String pdfTemp = rootPath + File.separator + "tempDir";
            File targetFilePath = new File(pdfTemp);
            if (!targetFilePath.exists()) {
                targetFilePath.mkdirs();
            }
            String targetPdfFileName = "附件2项目申报文本材料_" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS") + RandomUtils.nextInt(0, 9999) + ".pdf";
            String targetFile = targetFilePath.getAbsolutePath()+File.separator+targetPdfFileName;
            WordConvertUtil.docx2PDF(fileUrl, targetFile);
            File file = new File(targetFile);
            if (file.exists() && file.isFile()) {
                FileInfoVo fileVo = commonService.uploadFile(file);
                Enclosure enclosure = new Enclosure();
                enclosure.setApplicationId(applicationId);
                enclosure.setEnclosureType(fileVo.getFileExt());
                enclosure.setEnclosureName(fileVo.getFileName());
                enclosure.setEnclosureUrl(fileVo.getFileUrl());
                enclosure.setFileId(fileVo.getId());
                enclosureService.save(enclosure);
                enclosure.setEnclosureId(String.valueOf(enclosure.getId()));
                enclosureService.saveOrUpdate(enclosure);
                jsonObject.put("fileId", fileVo.getId());
                jsonObject.put("fileUrl", fileVo.getFileUrl());
            }
        } catch (Exception e) {
            log.error("生成PDF文件异常", e.getCause(), e.getMessage());
        } finally {
        }
        return jsonObject;
    }
}
