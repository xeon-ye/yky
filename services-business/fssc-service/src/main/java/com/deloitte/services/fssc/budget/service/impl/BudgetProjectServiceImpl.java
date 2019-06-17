package com.deloitte.services.fssc.budget.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.bpm.param.BudgetProjectJoinPersonQueryForm;
import com.deloitte.platform.api.fssc.budget.param.BudgetProjectQueryForm;
import com.deloitte.platform.api.fssc.budget.param.BudgetProjectQueryParam;
import com.deloitte.platform.api.fssc.budget.vo.BudgetProjectVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonJoinVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.*;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetDetailVo;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.ExceptionUtil;
import com.deloitte.services.fssc.budget.entity.BudgetProject;
import com.deloitte.services.fssc.budget.entity.BudgetProjectBudget;
import com.deloitte.services.fssc.budget.entity.BudgetProjectJoinPerson;
import com.deloitte.services.fssc.budget.mapper.BudgetProjectBudgetMapper;
import com.deloitte.services.fssc.budget.mapper.BudgetProjectMapper;
import com.deloitte.services.fssc.budget.service.IBudgetProjectBudgetService;
import com.deloitte.services.fssc.budget.service.IBudgetProjectJoinPersonService;
import com.deloitte.services.fssc.budget.service.IBudgetProjectService;
import com.deloitte.services.fssc.business.pe.entity.PerSelfAssessment;
import com.deloitte.services.fssc.business.pe.entity.PerSelfTarget;
import com.deloitte.services.fssc.business.pe.service.IPerSelfAssessmentService;
import com.deloitte.services.fssc.business.pe.service.IPerSelfTargetService;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.util.DateUtil;
import com.deloitte.services.fssc.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-20
 * @Description :  BudgetProject服务实现类
 * @Modified :
 */
@Service
@Transactional
@Slf4j
public class BudgetProjectServiceImpl extends
        ServiceImpl<BudgetProjectMapper, BudgetProject> implements IBudgetProjectService {

    @Autowired
    private BudgetProjectMapper budgetProjectMapper;

    @Autowired
    private BudgetProjectBudgetMapper budgetProjectBudgetMapper;

    @Autowired
    private IBudgetProjectBudgetService projectBudgetService;

    @Autowired
    private IPerSelfAssessmentService perSelfAssessmentService;

    @Autowired
    private IPerSelfTargetService perSelfTargetService;

    @Autowired
    private IBudgetProjectJoinPersonService projectJoinPersonService;

    @Override
    public Integer countByBudgetAccountIds(List<Long> ids) {
        QueryWrapper<BudgetProject> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(BudgetProject.BUDGET_ACCOUNT_ID, ids);
        return this.count(queryWrapper);
    }

    @Override
    public IPage<BudgetProject> selectPage(BudgetProjectQueryForm queryForm) {
        QueryWrapper<BudgetProject> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return budgetProjectMapper.selectPage(
                new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()),
                queryWrapper);
    }

    @Override
    public IPage<BudgetProject> pageConditions(BudgetProjectQueryParam projectQueryParam) {
        QueryWrapper<BudgetProject> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtil.isNotEmpty(projectQueryParam.getProjectCode()),
                BudgetProject.PROJECT_CODE, projectQueryParam.getProjectCode());

        queryWrapper.like(StringUtil.isNotEmpty(projectQueryParam.getProjectName()),
                BudgetProject.PROJECT_NAME, projectQueryParam.getProjectName());

        queryWrapper.like(StringUtil.isNotEmpty(projectQueryParam.getConnectNum()),
                BudgetProject.CONNECT_NUM, projectQueryParam.getConnectNum());

        queryWrapper.like(StringUtil.isNotEmpty(projectQueryParam.getFsscCode()),
                BudgetProject.FSSC_CODE, projectQueryParam.getFsscCode());

        queryWrapper.like(StringUtil.isNotEmpty(projectQueryParam.getPlanNum()),
                BudgetProject.PLAN_NUM, projectQueryParam.getPlanNum());

        queryWrapper.like(StringUtil.isNotEmpty(projectQueryParam.getProjectName()),
                BudgetProject.PROJECT_NAME, projectQueryParam.getProjectName());

        queryWrapper.like(StringUtil.isNotEmpty(projectQueryParam.getProjectDuty()),
                BudgetProject.PROJECT_DUTY, projectQueryParam.getProjectDuty());

        queryWrapper.like(StringUtil.isNotEmpty(projectQueryParam.getProjectStatus()),
                BudgetProject.PROJECT_STATUS, projectQueryParam.getProjectStatus());

        queryWrapper.like(StringUtil.isNotEmpty(projectQueryParam.getProjectType()),
                BudgetProject.PROJECT_TYPE, projectQueryParam.getProjectType());

        queryWrapper.like(StringUtil.isNotEmpty(projectQueryParam.getResponsibleUnitName()),
                BudgetProject.RESPONSIBLE_UNIT_NAME, projectQueryParam.getResponsibleUnitName());

        queryWrapper.ge(projectQueryParam.getStartTime() != null,
                BudgetProject.PROJECT_START_TIME, projectQueryParam.getStartTime());

        queryWrapper.le(projectQueryParam.getEndTime() != null,
                "PROJECT_END_TIME", projectQueryParam.getEndTime());

        queryWrapper.ge(projectQueryParam.getMoneyStart() != null,
                BudgetProject.TOTAL_AMOUNT, projectQueryParam.getMoneyStart());

        queryWrapper.le(projectQueryParam.getMoneyEnd() != null,
                BudgetProject.TOTAL_AMOUNT, projectQueryParam.getMoneyEnd());

        queryWrapper.like(StringUtils.isNotBlank(projectQueryParam.getAccountCode()), BudgetProject.ACCOUNT_CODE, projectQueryParam.getAccountCode());

        queryWrapper.eq(projectQueryParam.getParentId() != null,BudgetProject.PARENT_ID,projectQueryParam.getParentId());
        queryWrapper.eq(projectQueryParam.getUnitId() != null,BudgetProject.ORG_UNIT_ID,projectQueryParam.getUnitId());
        IPage<BudgetProject> budgetProjectIPage = budgetProjectMapper.selectPage(new Page<>(projectQueryParam.getCurrentPage(), projectQueryParam.getPageSize()),
                queryWrapper);
        return budgetProjectIPage;
    }


    @Override
    public List<BudgetProject> selectList(BudgetProjectQueryForm queryForm) {
        QueryWrapper<BudgetProject> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return budgetProjectMapper.selectList(queryWrapper);
    }

    @Override
    public List<BudgetProjectVo> selectVoList(BudgetProjectQueryForm queryForm) {
        return budgetProjectMapper.listVo(queryForm);
    }

    @Override
    public Map<String, BudgetProject> getProjectCodeBeanMap(String unitCode) {
        QueryWrapper<BudgetProject> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull(BudgetProject.PARENT_ID);
        queryWrapper.eq(BudgetProject.ORG_UNIT_CODE, unitCode);
        List<BudgetProject> projectList = this.list(queryWrapper);
        if (CollectionUtils.isEmpty(projectList)) {
            return Collections.EMPTY_MAP;
        }
        Map<String, BudgetProject> codeBeanMap = new HashMap<>(projectList.size());
        for (BudgetProject project : projectList) {
            codeBeanMap.put(project.getProjectCode(), project);
        }
        return codeBeanMap;
    }

    @Override
    public Map<String, BudgetProject> getTaskCodeBeanMap(String unitCode) {
        QueryWrapper<BudgetProject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BudgetProject.PARENT_FLAG, FsscEums.NO.getValue());
        queryWrapper.eq(BudgetProject.ORG_UNIT_CODE, unitCode);
        List<BudgetProject> projectList = this.list(queryWrapper);
        if (CollectionUtils.isEmpty(projectList)) {
            return Collections.EMPTY_MAP;
        }
        Map<String, BudgetProject> codeBeanMap = new HashMap<>(projectList.size());
        for (BudgetProject project : projectList) {
            codeBeanMap.put(project.getProjectCode(), project);
        }
        return codeBeanMap;
    }

    @Override
    public BudgetProject getBudgetProject(String unitCode, String projectCode, String taskCode) {
        QueryWrapper<BudgetProject> queryProjectWrapper = new QueryWrapper<>();
        queryProjectWrapper.eq(BudgetProject.ORG_UNIT_CODE, unitCode);
        queryProjectWrapper.eq(BudgetProject.PROJECT_CODE, taskCode);
        queryProjectWrapper.isNull(BudgetProject.PARENT_ID);
        queryProjectWrapper.eq(BudgetProject.PARENT_FLAG, FsscEums.NO.getValue());
        BudgetProject project = this.getOne(queryProjectWrapper);
        if (project != null) {
            return project;
        }
        QueryWrapper<BudgetProject> queryParentWrapper = new QueryWrapper<>();
        queryParentWrapper.eq(BudgetProject.ORG_UNIT_CODE, unitCode);
        queryParentWrapper.eq(BudgetProject.PROJECT_CODE, projectCode);
        queryParentWrapper.isNull(BudgetProject.PARENT_ID);
        queryParentWrapper.eq(BudgetProject.PARENT_FLAG, FsscEums.YES.getValue());
        BudgetProject parentProject = this.getOne(queryParentWrapper);
        if (parentProject == null) {
            return null;
        }
        QueryWrapper<BudgetProject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BudgetProject.PARENT_FLAG, FsscEums.NO.getValue());
        queryWrapper.eq(BudgetProject.PARENT_ID, parentProject.getProjectId());
        queryWrapper.eq(BudgetProject.ORG_UNIT_CODE, unitCode);
        queryWrapper.eq(BudgetProject.PROJECT_CODE, taskCode);
        return this.getOne(queryWrapper);
    }

    @Override
    public BudgetProject getProjectById(Long projectId) {
        QueryWrapper<BudgetProject> queryWrapper = new QueryWrapper();
        queryWrapper.eq(BudgetProject.PROJECT_ID, projectId);
        return this.getOne(queryWrapper);
    }

    @Override
    public Map<Long, BudgetProject> selectByProjectByIds(List<Long> ids) {
        QueryWrapper<BudgetProject> queryWrapper = new QueryWrapper();
        queryWrapper.in(BudgetProject.PROJECT_ID, ids);
        List<BudgetProject> projectList = this.list(queryWrapper);
        if (CollectionUtils.isEmpty(projectList)){
            return Collections.EMPTY_MAP;
        }
        Map<Long, BudgetProject> idBeanMap = new HashMap<>();
        for (BudgetProject project : projectList){
            idBeanMap.put(project.getProjectId(),project);
        }
        return idBeanMap;
    }

    /**
     * 保存预算项目,及预算信息
     *
     * @param vo
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveBudgetProject(SrpmsUpdateSyncFsscVo vo) {
        log.info("科研项目接口开始....");
        String msgType = vo.getMsgType();
        //项目立项
        if (SrpmsUpdateSyncFsscVo.TYPE_PROJECT_TASK_PASS.equalsIgnoreCase(msgType)) {
            log.info("立项......");
            SrpmsProjectSyncVo projectSyncVo = vo.getSrpmsProjectSyncVo();
            List<SrpmsProjectPersonJoinVo> personJoinVoList = projectSyncVo.getProjectPersonJoinVoList();
            BudgetProject budgetProject;
            if (projectSyncVo != null) {
                budgetProject = this.getProjectById(projectSyncVo.getId());
                if (budgetProject != null) {
                    log.error("项目已立项并推送,拒绝接受,项目编码:{}", projectSyncVo.getProjectNum());
                    throw new RuntimeException("项目已立项并推送,拒绝接受,项目编码:"+ projectSyncVo.getProjectNum());
                }else {
                    budgetProject = doProject(projectSyncVo);
                }
                //参与人员
                doProjectJoinPerson(personJoinVoList, budgetProject.getProjectCode());
                if (CollectionUtils.isNotEmpty(projectSyncVo.getBudgetDetailVoList())){
                    //非创新工程,可能会有多年预算
                    List<SrpmsProjectBudgetDetailVo> innovationBudgetDetailVoList = projectSyncVo.getBudgetDetailVoList();
                    for (SrpmsProjectBudgetDetailVo yearBudgetDetail : innovationBudgetDetailVoList){
                        doNoInnovationBudget(budgetProject, yearBudgetDetail);
                    }
                }else{
                    //创新工程,只有当年预算
                    List<SrpmsProjectTaskSyncVo> noInnovationTask = projectSyncVo.getSrpmsProjectTaskSyncVoList();
                    if (noInnovationTask == null){
                        log.error("项目已立项并推送,缺少预算信息,项目编码:{}",projectSyncVo.getProjectNum());
                        throw new RuntimeException("项目已立项并推送,缺少预算信息,项目编码:"+ projectSyncVo.getProjectNum());
                    }
                    doInnovationBudget(budgetProject, noInnovationTask.get(0));
                }
            }
        }
        //项目状态变更
        if (SrpmsUpdateSyncFsscVo.TYPE_MODIFY_PROJECT_STATUS.equalsIgnoreCase(msgType)) {
            log.info("项目状态变更......");
            SrpmsProjectSyncVo updateStatusVo = vo.getSrpmsProjectSyncVo();
            QueryWrapper<BudgetProject> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(BudgetProject.PROJECT_ID, updateStatusVo.getId());
            queryWrapper.isNull(BudgetProject.PARENT_ID);
            BudgetProject project = this.getOne(queryWrapper);
            if (project != null) {
                project.setProjectStatus(updateStatusVo.getStatus());
                this.updateById(project);
                QueryWrapper<BudgetProject> queryTaskWrapper = new QueryWrapper<>();
                queryTaskWrapper.eq(BudgetProject.PARENT_ID, updateStatusVo.getId());
                List<BudgetProject> taskList = this.list(queryTaskWrapper);
                if (CollectionUtils.isNotEmpty(taskList)){
                    for(BudgetProject task : taskList){
                        task.setProjectStatus(project.getProjectStatus());
                    }
                }
                this.saveOrUpdateBatch(taskList);
            } else {
                log.error("项目不存在,项目编码:{}", updateStatusVo.getProjectNum());
                throw new RuntimeException("项目不存在,项目编码:"+ updateStatusVo.getProjectNum());
            }
        }

        //项目成员变更
        if (SrpmsUpdateSyncFsscVo.TYPE_MODIFY_JOIN_PERSON.equalsIgnoreCase(msgType)) {
            log.info("项目成员变更......");
            SrpmsPersonUpdateSyncVo updatePersonVo = vo.getUpdateMemberVo();
            List<SrpmsProjectPersonJoinVo> newJoinPersonList = JSONArray.parseArray(updatePersonVo.getPerson(), SrpmsProjectPersonJoinVo.class);
            this.doProjectJoinPerson(newJoinPersonList, updatePersonVo.getProjectNum());
        }

        //项目负责人变更
        if (SrpmsUpdateSyncFsscVo.TYPE_MODIFY_LEAD_PERSON.equalsIgnoreCase(msgType)) {
            log.info("项目负责人变更......");
            SrpmsPersonUpdateSyncVo updateLeaderVo = vo.getUpdateLeaderVo();
            QueryWrapper<BudgetProject> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(BudgetProject.PROJECT_ID, updateLeaderVo.getProjectId());
            queryWrapper.isNull(BudgetProject.PARENT_ID);
            BudgetProject budgetProject = this.getOne(queryWrapper);
            if (budgetProject != null) {
                String jsonStr = updateLeaderVo.getPerson();
                JSONObject leaderJson = JSONObject.parseObject(jsonStr);
                budgetProject.setProjectDuty(leaderJson.getString("id"));
                this.updateById(budgetProject);
            }
        }

        //年度预算
        if (SrpmsUpdateSyncFsscVo.TYPE_BUDGET_APPLY_SYNC.equalsIgnoreCase(msgType)) {
            log.info("年度预算......");
            SrpmsProjectSyncVo projectSyncVo = vo.getSrpmsProjectSyncVo();
            BudgetProject budgetProject = this.getProjectById(projectSyncVo.getId());
            if (budgetProject == null) {
                log.error("接受科研的年度预算信息,项目不存在,项目编码:{}", projectSyncVo.getProjectNum());
                throw new RuntimeException("接受科研的年度预算信息,拒绝接受,项目编码:"+ projectSyncVo.getProjectNum());
            }
            //只处理创新工程
            List<SrpmsProjectTaskSyncVo> noInnovationTask = projectSyncVo.getSrpmsProjectTaskSyncVoList();
            if (noInnovationTask == null){
                log.error("项目已立项并推送,缺少预算信息,项目编码:{}",projectSyncVo.getProjectNum());
                throw new RuntimeException("项目已立项并推送,缺少预算信息,项目编码:"+ projectSyncVo.getProjectNum());
            }
            doInnovationBudget(budgetProject, noInnovationTask.get(0));
        }

        //预算调整
        if (SrpmsUpdateSyncFsscVo.TYPE_MODIFY_BUDGET_SYNC.equalsIgnoreCase(msgType)) {
            log.info("预算调整......");
            SrpmsProjectSyncVo projectSyncVo = vo.getSrpmsProjectSyncVo();
            BudgetProject budgetProject = this.getProjectById(projectSyncVo.getId());
            if (budgetProject == null) {
                log.error("接受科研的预算调整信息,项目不存在,项目编码:{},预算年度:{}", projectSyncVo.getProjectNum());
                throw new RuntimeException("接受科研的预算调整信息,,项目不存在,项目编码:{},预算年度:"+ projectSyncVo.getProjectNum());
            }
            if (CollectionUtils.isNotEmpty(projectSyncVo.getBudgetDetailVoList())){
                //非创新工程,可能会有多年预算
                List<SrpmsProjectBudgetDetailVo> innovationBudgetDetailVoList = projectSyncVo.getBudgetDetailVoList();
                for (SrpmsProjectBudgetDetailVo yearBudgetDetail : innovationBudgetDetailVoList){
                    doNoInnovationBudget(budgetProject, yearBudgetDetail);
                }
            }else{
                //创新工程,只有当年预算
                List<SrpmsProjectTaskSyncVo> noInnovationTask = projectSyncVo.getSrpmsProjectTaskSyncVoList();
                if (noInnovationTask == null){
                    log.error("项目已立项并推送,缺少预算信息,项目编码:{}",projectSyncVo.getProjectNum());
                    throw new RuntimeException("项目已立项并推送,缺少预算信息,项目编码:"+ projectSyncVo.getProjectNum());
                }
                doInnovationBudget(budgetProject, noInnovationTask.get(0));
            }
        }

        //绩效目标消息
        if (SrpmsUpdateSyncFsscVo.TYPE_BUDGET_TASK_SYNC.equals(msgType)) {
            log.info("绩效目标消息......");
            try {
                SrpmsBudgetTaskSyncVo budgetTask = vo.getBudgetTask();
                saveOrUpdatePerformance(budgetTask);
            }catch (Exception e){
                e.printStackTrace();
                log.error("绩效目标接受报错:{}",e.getMessage());
            }
        }
        log.info("科研项目接口结束....");
    }

    /**
     * 处理科研的任务
     *
     * @param budgetProject
     * @param taskVo
     * @return
     */
    private BudgetProject doTask(BudgetProject budgetProject, SrpmsProjectTaskSyncVo taskVo) {
        BudgetProject task = new BudgetProject();
        task.setProjectId(taskVo.getId());
        task.setParentFlag(FsscEums.NO.getValue());
        task.setParentId(StringUtil.castTolong(taskVo.getProjectId()));
        task.setProjectType(taskVo.getTaskCategoryDesc());
        task.setProjectName(taskVo.getTaskName());
        task.setProjectCode(taskVo.getProjectTaskNum() != null ? taskVo.getProjectTaskNum() : taskVo.getSerial());
        task.setOrgUnitId(budgetProject != null ? budgetProject.getOrgUnitId() : StringUtil.castTolong(taskVo.getDeptId()));
        task.setOrgUnitCode(budgetProject != null ? budgetProject.getOrgUnitCode() : taskVo.getDeptCode());
        task.setOrgUnitName(budgetProject != null ? budgetProject.getOrgUnitName() : taskVo.getDeptName());
        task.setProjectStatus(budgetProject.getProjectStatus());
        task.setProjectDuty(budgetProject.getProjectDuty());
        task.setProjectDutyId(budgetProject.getProjectDutyId());
        task.setResponsibleUnitId(task.getOrgUnitId());
        task.setResponsibleUnitCode(budgetProject.getOrgUnitCode());
        task.setResponsibleUnitName(budgetProject.getOrgUnitName());
        task.setSmallTypeName(taskVo.getTaskCategoryDesc());
        task.setProjectSmallType(taskVo.getTaskCategory());
        task.setProjectStatus(budgetProject.getProjectStatus());
        task.setExt1("1");
        task.setTotalAmount(new BigDecimal(taskVo.getBudgetAmount()));
        budgetProjectMapper.insert(task);
        return task;
    }

    /**
     * 创新工程预算
     * @param project
     * @param taskVo
     * @return
     */
    private void doInnovationBudget(BudgetProject project, SrpmsProjectTaskSyncVo taskVo) {
        List<BudgetDetailVo> budgetDetailVos = taskVo.getBudgetDetail();
        List<BudgetProjectBudget> taskProjectBudgetList = new ArrayList<>();
        for (BudgetDetailVo detailVo : budgetDetailVos) {
            //查询预算是否存在,处理预算变更的情况
            String budgetAccountCode = detailVo.getBudgetAccountCode() != null ?
                    detailVo.getBudgetAccountCode() : String.valueOf(detailVo.getSerial());
            BudgetProjectBudget annualProjectBudget = projectBudgetService.selectByKeyWord(project.getOrgUnitCode(),
                    project.getId(), budgetAccountCode, taskVo.getTaskYear());
            if (annualProjectBudget != null) {
                if (StringUtils.isNotBlank(detailVo.getAmount())) {
                    BigDecimal newSrmpsBudgetAmount = new BigDecimal(detailVo.getAmount()).multiply(new BigDecimal(10000));
                    BigDecimal oldSrmpsBudgetAmount = annualProjectBudget.getBudgetSrmpsAmount() != null
                            ? annualProjectBudget.getBudgetSrmpsAmount() : BigDecimal.ZERO;
                    BigDecimal diffAmount = newSrmpsBudgetAmount.subtract(oldSrmpsBudgetAmount);
                    BigDecimal newBudgetAmount = annualProjectBudget.getBudgetAmount().add(diffAmount);
                    BigDecimal newBudgetUsableAmount = annualProjectBudget.getBudgetUsableAmount().add(diffAmount);
                    annualProjectBudget.setBudgetAmount(newBudgetAmount);
                    annualProjectBudget.setBudgetUsableAmount(newBudgetUsableAmount);
                    annualProjectBudget.setBudgetSrmpsAmount(newSrmpsBudgetAmount);
                    taskProjectBudgetList.add(annualProjectBudget);
                    continue;
                }
            } else {
                annualProjectBudget = new BudgetProjectBudget();
                //预算金额
                if (detailVo.getAmount() != null) {
                    BigDecimal newSrmpsBudgetAmount = new BigDecimal(detailVo.getAmount()).multiply(new BigDecimal(10000));
                    annualProjectBudget.setBudgetSrmpsAmount(newSrmpsBudgetAmount);
                    annualProjectBudget.setBudgetAmount(newSrmpsBudgetAmount);
                }else{
                    annualProjectBudget.setBudgetAmount(BigDecimal.ZERO);
                    annualProjectBudget.setBudgetSrmpsAmount(BigDecimal.ZERO);
                }
                annualProjectBudget.setBudgetProjectId(project.getId());
                annualProjectBudget.setProjectCode(project.getProjectCode());
                annualProjectBudget.setProjectStatus(project.getProjectStatus());
                annualProjectBudget.setBudgetAccountCode(StringUtils.isNotBlank(detailVo.getBudgetAccountCode()) ?
                        detailVo.getBudgetAccountCode() : String.valueOf(detailVo.getSerial()));
                annualProjectBudget.setOrgUnitCode(project.getOrgUnitCode());
                annualProjectBudget.setOrgUnitId(project.getOrgUnitId());
                annualProjectBudget.setBudgetOccupyAmount(BigDecimal.ZERO);
                annualProjectBudget.setBudgetRemainAmount(BigDecimal.ZERO);
                annualProjectBudget.setBudgetUsableAmount(annualProjectBudget.getBudgetAmount());
                annualProjectBudget.setTaskCode(annualProjectBudget.getProjectCode());
                annualProjectBudget.setBudgetAccountName(detailVo.getName());
                Calendar calendar = Calendar.getInstance();
                //TODO 如果任务年度为空,将默认取当前年度
                if (taskVo.getTaskYear() != null) {
                    calendar.set(Calendar.YEAR, Integer.valueOf(taskVo.getTaskYear()));
                }else{
                    taskVo.setTaskYear(calendar.get(Calendar.YEAR) + "");
                }
                annualProjectBudget.setBudgetAnnual(taskVo.getTaskYear());
                BudgetProjectBudget periodProjectBudget = new BudgetProjectBudget();
                try {
                    periodProjectBudget = (BudgetProjectBudget) BeanUtils.copyProperties(annualProjectBudget, periodProjectBudget);
                } catch (Exception e) {
                    throw new RuntimeException("项目预算,复制对象失败");
                }
                annualProjectBudget.setTotalFlag(FsscEums.YES.getValue());
                //设置初始值
                annualProjectBudget.setBudgetSrmpsAmount(annualProjectBudget.getBudgetAmount());
                String budgetPeriod = DateUtil.dateToString(calendar.getTime(), DateUtil.FM_YYYY_MM);
                periodProjectBudget.setBudgetPeriod(budgetPeriod);
                taskProjectBudgetList.add(annualProjectBudget);
                taskProjectBudgetList.add(periodProjectBudget);
            }
        }
        projectBudgetService.saveBatch(taskProjectBudgetList);
    }


    /**
     * 非创新工程的预算
     * @param yearBudgetDetail
     * @param project
     */
    private void doNoInnovationBudget(BudgetProject project, SrpmsProjectBudgetDetailVo yearBudgetDetail) {
        if (CollectionUtils.isNotEmpty(yearBudgetDetail.getBudgetDetailVo())) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, yearBudgetDetail.getBudgetYear());
            String budgetPeriod = DateUtil.dateToString(calendar.getTime(), DateUtil.FM_YYYY_MM);
            for (BudgetDetailVo detailVo : yearBudgetDetail.getBudgetDetailVo()) {
                String budgetAccountCode = detailVo.getBudgetAccountCode() != null ?
                        detailVo.getBudgetAccountCode() : String.valueOf(detailVo.getSerial());
                //查询预算是否存在,处理预算变更的情况
                BudgetProjectBudget annualProjectBudget = projectBudgetService.selectByKeyWord(project.getOrgUnitCode(),
                        project.getId(), budgetAccountCode, String.valueOf(yearBudgetDetail.getBudgetYear()));
                if (annualProjectBudget != null) {
                    if (StringUtils.isNotBlank(detailVo.getAmount())) {
                        BigDecimal newSrmpsBudgetAmount = new BigDecimal(detailVo.getAmount()).multiply(new BigDecimal(10000));
                        BigDecimal oldSrmpsBudgetAmount = annualProjectBudget.getBudgetSrmpsAmount() != null
                                ? annualProjectBudget.getBudgetSrmpsAmount() : BigDecimal.ZERO;
                        BigDecimal diffAmount = newSrmpsBudgetAmount.subtract(oldSrmpsBudgetAmount);
                        BigDecimal newBudgetAmount = annualProjectBudget.getBudgetAmount().add(diffAmount);
                        BigDecimal newBudgetUsableAmount = annualProjectBudget.getBudgetUsableAmount().add(diffAmount);
                        annualProjectBudget.setBudgetAmount(newBudgetAmount);
                        annualProjectBudget.setBudgetUsableAmount(newBudgetUsableAmount);
                        annualProjectBudget.setBudgetSrmpsAmount(newSrmpsBudgetAmount);
                        projectBudgetService.updateById(annualProjectBudget);
                    }else{
                        log.error("非创新工程预算金额为空,项目编码:{},预算科目编码:{},预算科目名称:{}",
                                project.getProjectCode(),budgetAccountCode,detailVo.getName());
                        throw new RuntimeException("非创新工程预算金额为空,项目编码:"+ project.getProjectCode()
                                + ",预算科目编码:"+ budgetAccountCode +",预算科目名称:" + detailVo.getName());
                    }
                } else {
                    annualProjectBudget = new BudgetProjectBudget();
                    BigDecimal srmpsBudgetAmount = new BigDecimal(detailVo.getAmount()).multiply(new BigDecimal(10000));
                    annualProjectBudget.setBudgetAmount(srmpsBudgetAmount);
                    annualProjectBudget.setBudgetSrmpsAmount(srmpsBudgetAmount);
                    annualProjectBudget.setBudgetOccupyAmount(BigDecimal.ZERO);
                    annualProjectBudget.setBudgetRemainAmount(BigDecimal.ZERO);
                    annualProjectBudget.setBudgetUsableAmount(annualProjectBudget.getBudgetAmount());
                    annualProjectBudget.setExt1("1");
                    annualProjectBudget.setBudgetProjectId(project.getId());
                    annualProjectBudget.setProjectStatus(project.getProjectStatus());
                    annualProjectBudget.setProjectCode(project.getProjectCode());
                    annualProjectBudget.setTaskCode(project.getProjectCode());
                    annualProjectBudget.setBudgetAccountCode(StringUtils.isNotBlank(detailVo.getBudgetAccountCode()) ?
                            detailVo.getBudgetAccountCode() : String.valueOf(detailVo.getSerial()));
                    annualProjectBudget.setBudgetAccountName(detailVo.getName());
                    annualProjectBudget.setOrgUnitCode(project.getOrgUnitCode());
                    annualProjectBudget.setOrgUnitId(project.getOrgUnitId());
                    annualProjectBudget.setBudgetAnnual(String.valueOf(yearBudgetDetail.getBudgetYear()));
                    BudgetProjectBudget periodProjectBudget = new BudgetProjectBudget();
                    try {
                        periodProjectBudget = (BudgetProjectBudget) BeanUtils.copyProperties(annualProjectBudget, periodProjectBudget);
                    } catch (Exception e) {
                        throw new RuntimeException("项目预算,复制对象失败");
                    }
                    annualProjectBudget.setTotalFlag(FsscEums.YES.getValue());
                    budgetProjectBudgetMapper.insert(annualProjectBudget);
                    periodProjectBudget.setBudgetPeriod(budgetPeriod);
                    budgetProjectBudgetMapper.insert(periodProjectBudget);
                }
            }
        }
    }

    /**
     * 处理项目参与人员新增及变更
     *
     * @param newPersonJoinVoList
     * @param projectCode
     */
    private void doProjectJoinPerson(List<SrpmsProjectPersonJoinVo> newPersonJoinVoList, String projectCode) {
        if (CollectionUtils.isNotEmpty(newPersonJoinVoList)) {
            BudgetProjectJoinPersonQueryForm queryPersonForm = new BudgetProjectJoinPersonQueryForm();
            queryPersonForm.setProjectId(newPersonJoinVoList.get(0).getProjectId());
            List<BudgetProjectJoinPerson> hasJoinPersonList = projectJoinPersonService.selectList(queryPersonForm);
            Map<Long, BudgetProjectJoinPerson> hasJoinPersonMap = new HashMap(hasJoinPersonList.size());
            if (CollectionUtils.isNotEmpty(hasJoinPersonList)) {
                for (BudgetProjectJoinPerson joinPerson : hasJoinPersonList) {
                    hasJoinPersonMap.put(joinPerson.getPersonId(), joinPerson);
                }
            }
            Map<Long, SrpmsProjectPersonJoinVo> newJoinPersonMap = new HashMap<>(newPersonJoinVoList.size());
            for (SrpmsProjectPersonJoinVo personJoinVo : newPersonJoinVoList) {
                BudgetProjectJoinPerson projectJoinPerson;
                //处理新加入的参与人员
                if ((MapUtils.isNotEmpty(hasJoinPersonMap) && !hasJoinPersonMap.containsKey(personJoinVo.getPersonId()))
                        || MapUtils.isEmpty(hasJoinPersonMap)) {
                    projectJoinPerson = new BudgetProjectJoinPerson();
                    projectJoinPerson.setProjectId(personJoinVo.getProjectId());
                    projectJoinPerson.setProjectCode(projectCode);
                    projectJoinPerson.setPersonId(personJoinVo.getPersonId());
                    projectJoinPerson.setPersonName(personJoinVo.getPersonName());
                    projectJoinPerson.setValidFlag(FsscEums.VALID.getValue());
                    projectJoinPersonService.save(projectJoinPerson);
                }
                newJoinPersonMap.put(personJoinVo.getPersonId(), personJoinVo);
            }
            if (MapUtils.isNotEmpty(hasJoinPersonMap)) {
                for (Long joinedPersonId : hasJoinPersonMap.keySet()) {
                    if (!newJoinPersonMap.containsKey(joinedPersonId)) {
                        BudgetProjectJoinPerson unJoinPerson = hasJoinPersonMap.get(joinedPersonId);
                        unJoinPerson.setValidFlag(FsscEums.UN_VALID.getValue());
                        projectJoinPersonService.updateById(unJoinPerson);
                    }
                }
            }
        }
    }

    /**
     * 保存项目信息
     *
     * @param srpmsProjectSyncVo
     * @return
     */
    private BudgetProject doProject(SrpmsProjectSyncVo srpmsProjectSyncVo) {
        BudgetProject project = new BudgetProject();
        project.setProjectId(srpmsProjectSyncVo.getId());
        project.setProjectName(srpmsProjectSyncVo.getProjectName());
        project.setProjectCode(srpmsProjectSyncVo.getProjectNum());
        project.setProjectDutyId(String.valueOf(srpmsProjectSyncVo.getLeadPersonId()));
        JSONObject leadPerson = srpmsProjectSyncVo.getLeadPerson();
        if (leadPerson != null) {
            project.setProjectDuty(leadPerson.getString("name"));
        }
        project.setProjectStartTime(srpmsProjectSyncVo.getProjectActionDateStart());
        project.setProjectEndTime(srpmsProjectSyncVo.getProjectActionDateEnd());
        project.setProjectType(srpmsProjectSyncVo.getProjectType());
        project.setProjectStatus(srpmsProjectSyncVo.getStatus());
        project.setBelongToDepartId(srpmsProjectSyncVo.getLeadDeptId());
        Double budgetAmount = srpmsProjectSyncVo.getBudgetAmount();
        if (budgetAmount != null) {
            BigDecimal totalAmount = new BigDecimal(budgetAmount.toString()).multiply(new BigDecimal(10000));
            project.setTotalAmount(totalAmount);
        }
        project.setExt1("1");
        project.setIsAllowExpense(FsscEums.YES.getValue());
        JSONObject leadDept = srpmsProjectSyncVo.getLeadDept();
        if (leadDept != null) {
            project.setOrgUnitName(leadDept.getString("deptName"));
            project.setOrgUnitCode(leadDept.getString("deptCode"));
            project.setOrgUnitId(leadDept.getLong("deptId"));
            //TODO 暂时设置为一样的
            project.setResponsibleUnitCode(project.getOrgUnitCode());
            project.setResponsibleUnitId(project.getOrgUnitId());
            project.setResponsibleUnitName(project.getOrgUnitName());
        }
        //TODO 一期上线,只有项目预算,并不会细化到任务,所以默认为否
        project.setParentFlag(FsscEums.NO.getValue());
        //大中小类
        List<String> projectCategory = StringUtil.stringToList(srpmsProjectSyncVo.getProjectCategory());
        List<String> projectCategoryDesc = StringUtil.stringToList(srpmsProjectSyncVo.getProjectCategoryDesc());
        if (CollectionUtils.isNotEmpty(projectCategory)) {
            project.setProjectBigType(projectCategory.get(0));
            project.setProjectMiddleType(projectCategory.get(1));
            if (projectCategory.size() > 2) {
                project.setProjectSmallType(projectCategory.get(2));
            }
        }
        if (CollectionUtils.isNotEmpty(projectCategoryDesc)) {
            project.setBigTypeName(projectCategoryDesc.get(0));
            project.setMiddleTypeName(projectCategoryDesc.get(1));
            if (projectCategoryDesc.size() > 2) {
                project.setSmallTypeName(projectCategoryDesc.get(2));
            }
        }
        if (project.getId() != null) {
            this.saveOrUpdate(project);
        } else {
            this.save(project);
        }
        return project;
    }

    /**
     * 保存或更新绩效指标
     *
     * @param budgetTask
     */
    private void saveOrUpdatePerformance(SrpmsBudgetTaskSyncVo budgetTask) {
        if (budgetTask != null) {
            try {
                PerSelfAssessment assessment = new PerSelfAssessment();
                assessment.setProjectId(budgetTask.getProjectId());
                assessment.setProjectDutyId(budgetTask.getProjectFinancePersonId());
                JSONObject leadDept = JSON.parseObject(budgetTask.getLeadDept());
                if (leadDept != null) {
                    assessment.setDoUnitName(leadDept.getString("deptName"));
                    assessment.setDoUnitCode(leadDept.getString("deptCode"));
                    assessment.setDoUnitId(leadDept.getLong("deptId"));
                }
                assessment.setDocumentStatus(FsscEums.NEW.getValue());
                assessment.setFundSourceAmount(getBig(budgetTask.getFundSourceAmount()));
                assessment.setFundSourceAmountYear(getBig(budgetTask.getFundSourceAmountYear()));
                assessment.setFundSourceOther(getBig(budgetTask.getFundSourceOther()));
                assessment.setFundSourceOtherYear(getBig(budgetTask.getFundSourceOtherYear()));
                assessment.setFundSourceProject(getBig(budgetTask.getFundSourceProject()));
                assessment.setFundSourceProjectYear(getBig(budgetTask.getFundSourceProjectYear()));
                assessment.setProjectTarget(budgetTask.getProjectTarget());
                boolean b = perSelfAssessmentService.saveOrUpdate(assessment);
                if (b) {
                    String performanceIndicatorDetail = budgetTask.getPerformanceIndicatorDetail();
                    if (StringUtil.isNotEmpty(performanceIndicatorDetail)) {
                        JSONArray array = JSON.parseArray(performanceIndicatorDetail);
                        if (array != null && array.size() > 0) {
                            for (int i = 0; i < array.size(); i++) {
                                JSONObject jsonObject = array.getJSONObject(i);
                                String quotaValue = jsonObject.getString("quotaValue");
                                String quotaType = jsonObject.getString("quotaType");
                                PerSelfTarget target = new PerSelfTarget();
                                target.setDocumentId(assessment.getId());
                                target.setDocumentType(FsscTableNameEums.PER_SELF_ASSESSMENT.getValue());
                                if (StringUtil.isNotEmpty(quotaType)) {
                                    List<String> strings = JSONArray.parseArray(quotaType).toJavaList(String.class);
                                    try {
                                        target.setFirstPer(strings.get(0));
                                        target.setSecondPer(strings.get(1));
                                        target.setThiredPer(strings.get(2));
                                        target.setYearPerValue(quotaValue);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        log.error(ExceptionUtil.getStackTraceAsString(e));
                                    }
                                }
                                perSelfTargetService.save(target);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                log.error("接受绩效指标失败,项目id：{}", budgetTask.getProjectId());
                throw new RuntimeException();
            }
        }
    }


    public static void main(String[] args) {
        String ss = "[\"22\",\"33\",\"334\"]";
        JSONArray array = JSONArray.parseArray(ss);
        System.out.println(array);
    }


    private BigDecimal getBig(Double d) {
        if (d != null) {
            return new BigDecimal(d.toString());
        }
        return BigDecimal.ZERO;
    }


    /**
     * 通用查询
     */
    private QueryWrapper<BudgetProject> getQueryWrapper(QueryWrapper<BudgetProject> queryWrapper,
                                                        BudgetProjectQueryForm queryForm) {
        //条件拼接
        if (queryForm.getProjectId() != null) {
            queryWrapper.eq(BudgetProject.PROJECT_ID, queryForm.getProjectId());
        }
        if (StringUtils.isNotBlank(queryForm.getProjectCode())) {
            queryWrapper.eq(BudgetProject.PROJECT_CODE, queryForm.getProjectCode());
        }
        if (StringUtils.isNotBlank(queryForm.getProjectName())) {
            queryWrapper.eq(BudgetProject.PROJECT_NAME, queryForm.getProjectName());
        }
        if (StringUtils.isNotBlank(queryForm.getProjectType())) {
            queryWrapper.eq(BudgetProject.PROJECT_TYPE, queryForm.getProjectType());
        }
        if (StringUtils.isNotBlank(queryForm.getTypeName())) {
            queryWrapper.eq(BudgetProject.TYPE_NAME, queryForm.getTypeName());
        }
        if (StringUtils.isNotBlank(queryForm.getProjectBigType())) {
            queryWrapper.eq(BudgetProject.PROJECT_BIG_TYPE, queryForm.getProjectBigType());
        }
        if (StringUtils.isNotBlank(queryForm.getBigTypeName())) {
            queryWrapper.eq(BudgetProject.BIG_TYPE_NAME, queryForm.getBigTypeName());
        }
        if (StringUtils.isNotBlank(queryForm.getProjectMiddleType())) {
            queryWrapper.eq(BudgetProject.PROJECT_MIDDLE_TYPE, queryForm.getProjectMiddleType());
        }
        if (StringUtils.isNotBlank(queryForm.getMiddleTypeName())) {
            queryWrapper.eq(BudgetProject.MIDDLE_TYPE_NAME, queryForm.getMiddleTypeName());
        }
        if (StringUtils.isNotBlank(queryForm.getProjectSmallType())) {
            queryWrapper.eq(BudgetProject.PROJECT_SMALL_TYPE, queryForm.getProjectSmallType());
        }
        if (StringUtils.isNotBlank(queryForm.getSmallTypeName())) {
            queryWrapper.eq(BudgetProject.SMALL_TYPE_NAME, queryForm.getSmallTypeName());
        }
        if (StringUtils.isNotBlank(queryForm.getProjectDuty())) {
            queryWrapper.eq(BudgetProject.PROJECT_DUTY, queryForm.getProjectDuty());
        }
        if (StringUtils.isNotBlank(queryForm.getResponsibleUnitCode())) {
            queryWrapper
                    .eq(BudgetProject.RESPONSIBLE_UNIT_CODE, queryForm.getResponsibleUnitCode());
        }
        if (StringUtils.isNotBlank(queryForm.getResponsibleUnitName())) {
            queryWrapper
                    .eq(BudgetProject.RESPONSIBLE_UNIT_NAME, queryForm.getResponsibleUnitName());
        }
        if (queryForm.getBelongToDepartId() != null) {
            queryWrapper.eq(BudgetProject.BELONG_TO_DEPART_ID, queryForm.getBelongToDepartId());
        }
        if (StringUtils.isNotBlank(queryForm.getParentFlag())) {
            queryWrapper.eq(BudgetProject.PARENT_FLAG, queryForm.getParentFlag());
        }
        if (queryForm.getParentId() != null) {
            queryWrapper.eq(BudgetProject.PARENT_ID, queryForm.getParentId());
        }
        if (queryForm.getOrgUnitId() != null) {
            queryWrapper.eq(BudgetProject.ORG_UNIT_ID, queryForm.getOrgUnitId());
        }
        if (StringUtils.isNotBlank(queryForm.getOrgUnitCode())) {
            queryWrapper.eq(BudgetProject.ORG_UNIT_CODE, queryForm.getOrgUnitCode());
        }
        if (StringUtils.isNotBlank(queryForm.getOrgUnitName())) {
            queryWrapper.eq(BudgetProject.ORG_UNIT_NAME, queryForm.getOrgUnitName());
        }
        if (StringUtils.isNotBlank(queryForm.getOrgPath())) {
            queryWrapper.eq(BudgetProject.ORG_PATH, queryForm.getOrgPath());
        }
        if (StringUtils.isNotBlank(queryForm.getProjectStatus())) {
            queryWrapper.eq(BudgetProject.PROJECT_STATUS, queryForm.getProjectStatus());
        }
        if (StringUtils.isNotBlank(queryForm.getAccountCode())){
            queryWrapper.like(BudgetProject.ACCOUNT_CODE, queryForm.getAccountCode());
        }
        return queryWrapper;
    }
}

