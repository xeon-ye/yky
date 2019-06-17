package com.deloitte.services.srpmp.project.task.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.SrpmsProjectApplyInnUnitVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectAttachmentVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonJoinVo;
import com.deloitte.platform.api.srpmp.project.task.param.SrpmsProjectTaskInnUnitQueryForm;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskInnUnitForm;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskInnUnitVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.srpmp.common.enums.*;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.project.apply.entity.SrpmsProjectApplyInnUnit;
import com.deloitte.services.srpmp.project.apply.service.ISrpmsProjectApplyInnUnitService;
import com.deloitte.services.srpmp.project.apply.service.impl.SrpmsProjectApplyInnCommonServiceImpl;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectAttachment;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectAttachmentService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectFlowService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectPersonJoinService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectService;
import com.deloitte.services.srpmp.project.task.entity.SrpmsProjectTaskInnUnit;
import com.deloitte.services.srpmp.project.task.entity.SrpmsProjectTaskSchStudent;
import com.deloitte.services.srpmp.project.task.mapper.SrpmsProjectTaskInnUnitMapper;
import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskInnUnitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : lixin
 * @Date : Create in 2019-05-22
 * @Description :  SrpmsProjectTaskInnUnit服务实现类
 * @Modified :
 */
@Slf4j
@Service
@Transactional
public class SrpmsProjectTaskInnUnitServiceImpl extends ServiceImpl<SrpmsProjectTaskInnUnitMapper, SrpmsProjectTaskInnUnit> implements ISrpmsProjectTaskInnUnitService {


    @Autowired
    SrpmsProjectApplyInnCommonServiceImpl commonService;

    @Autowired
    private ISrpmsProjectService projectService;

    @Autowired
    private ISrpmsProjectFlowService flowService;

    @Autowired
    private ISrpmsProjectPersonJoinService personJoinService;

    @Autowired
    private ISrpmsProjectAttachmentService attachmentService;

    @Autowired
    private ISrpmsProjectApplyInnUnitService applyInnUnitService;

    @Override
    public void submitTaskInnUnit(SrpmsProjectTaskInnUnitForm   innUnitForm, UserVo userVo, DeptVo deptVo) {

        long id = innUnitForm.getId();

        // 校验申请书状态，只有未提交的申请书才能提交
        SrpmsProject project = projectService.getById(id);
        if (null == project) {
            throw new BaseException(SrpmsErrorType.PROJECT_NOT_FOUND);
        }
        if (!SrpmsProjectStatusEnums.PEROJECT_HAS_PUBLICITY.getCode().equals(project.getStatus())) {
            throw new BaseException(SrpmsErrorType.TASK_HAVE_SUBMITTED);
        }

        // 保存任务书
        SrpmsProjectTaskInnUnit taskInnUnit = new BeanUtils<SrpmsProjectTaskInnUnit>().copyObj(innUnitForm, SrpmsProjectTaskInnUnit.class);
        this.saveOrUpdate(taskInnUnit);

        // 更新项目状态
        projectService.submitTaskProject(id);
        log.info("创新单元，提交任务书，已更新项目状态, projectId:{}", innUnitForm.getId());
        // 发起流程
        flowService.startAuditProcess(innUnitForm.getId(), VoucherTypeEnums.TASK_BOOK, userVo, deptVo);
        log.info("创新单元，提交任务书，已发起流程, projectId:{}", userVo.getId());
    }

    @Override
    public SrpmsProjectTaskInnUnitVo get(Long id) {
        // 项目信息
        SrpmsProject project = projectService.getById(id);
        if (null == project) {
            throw new BaseException(SrpmsErrorType.PROJECT_NOT_FOUND);
        }

        SrpmsProjectTaskInnUnit unit = this.getById(id);

        SrpmsProjectTaskInnUnitVo resultVo = new BeanUtils<SrpmsProjectTaskInnUnitVo>().copyObj(unit, SrpmsProjectTaskInnUnitVo.class);

        if (StringUtils.isNotBlank(project.getLeadPerson())) {
            resultVo.setLeadPerson(JSONObject.parseObject(project.getLeadPerson()));
        }

        if (StringUtils.isNotBlank(project.getLeadDept())) {
            resultVo.setLeadDept(JSONObject.parseObject(project.getLeadDept()));
        }

        // 固定科技人员名单
        List<SrpmsProjectPersonJoinVo> mainMemberList = personJoinService.queryPersonJoinListByJoinWay(PersonJoinWayEnums.TASK_UNIT_PERSON, id);
        resultVo.setMainMemberList(mainMemberList);
        // 近5年承担的重要科研项目清单
        List<SrpmsProjectPersonJoinVo> topMemberOtherTaskList = personJoinService.queryPersonJoinListByJoinWay(PersonJoinWayEnums.TASK_UNIT_PROJECT, id);
        resultVo.setTopMemberOtherTaskList(topMemberOtherTaskList);

        // 附件
        List<SrpmsProjectAttachment> attachmentFile = attachmentService.query(AttachmentCategoryEnums.ATTACHMENT_TASK_NORMAL, id);
        List<SrpmsProjectAttachmentVo> attachmentFileVoList = new BeanUtils<SrpmsProjectAttachmentVo>().copyObjs(attachmentFile, SrpmsProjectAttachmentVo.class);
        resultVo.setAttachmentFile(attachmentFileVoList);

        // 依托单位学术委员会意见
        List<SrpmsProjectAttachment> attachmentCommittee = attachmentService.query(AttachmentCategoryEnums.ATTACHMENT_TASK_11, id);
        List<SrpmsProjectAttachmentVo> attachmentCommitteeVoList = new BeanUtils<SrpmsProjectAttachmentVo>().copyObjs(attachmentCommittee, SrpmsProjectAttachmentVo.class);
        if (attachmentCommitteeVoList != null && attachmentCommitteeVoList.size() != 0) {
            resultVo.setAttachmentCommittee(attachmentCommitteeVoList.get(0));
        }

        // 依托单位的意见
        List<SrpmsProjectAttachment> attachmentAudit = attachmentService.query(AttachmentCategoryEnums.ATTACHMENT_TASK_12, id);
        List<SrpmsProjectAttachmentVo> attachmentAuditVoList = new BeanUtils<SrpmsProjectAttachmentVo>().copyObjs(attachmentAudit, SrpmsProjectAttachmentVo.class);
        if (attachmentAuditVoList != null && attachmentAuditVoList.size() != 0) {
            resultVo.setAttachmentAudit(attachmentAuditVoList.get(0));
        }

        // 依托单位诚信申报承诺
        List<SrpmsProjectAttachment> attachmentStatement = attachmentService.query(AttachmentCategoryEnums.ATTACHMENT_TASK_13, id);
        List<SrpmsProjectAttachmentVo> attachmentStatementVoList = new BeanUtils<SrpmsProjectAttachmentVo>().copyObjs(attachmentStatement, SrpmsProjectAttachmentVo.class);
        if (attachmentStatementVoList != null && attachmentStatementVoList.size() != 0) {
            resultVo.setAttachmentStatement(attachmentStatementVoList.get(0));
        }

        // 中国医学科学院意见
        List<SrpmsProjectAttachment> attachmentDept = attachmentService.query(AttachmentCategoryEnums.ATTACHMENT_TASK_14, id);
        List<SrpmsProjectAttachmentVo> attachmentDeptVoList = new BeanUtils<SrpmsProjectAttachmentVo>().copyObjs(attachmentDept, SrpmsProjectAttachmentVo.class);
        if (attachmentDeptVoList != null && attachmentDeptVoList.size() != 0) {
            resultVo.setAttachmentDept(attachmentDeptVoList.get(0));
        }
        return resultVo;
    }

    @Override
    public Long saveTask(SrpmsProjectTaskInnUnitForm innUnitForm) {
        long projectId = innUnitForm.getId();

        SrpmsProject project = projectService.getById(projectId);
        if (project == null) {
            throw new BaseException(PlatformErrorType.NO_DATA_FOUND);
        }
        // 更新单元主任信息
        if (innUnitForm.getLeadPerson() != null) {
            project.setLeadPerson(JSONObject.toJSONString(innUnitForm.getLeadPerson()));
        }
        // 更新项目依托单位信息
        if (innUnitForm.getLeadDept() != null) {
            project.setLeadDept(JSONObject.toJSONString(innUnitForm.getLeadDept()));
        }

        if (StringUtils.isNotBlank(innUnitForm.getProjectName())) {
            project.setProjectName(innUnitForm.getProjectName());

            //项目名字同步到申请书
            SrpmsProjectApplyInnUnit applyInnUnit = applyInnUnitService.getById(projectId);
            if (null != applyInnUnit) {
                applyInnUnit.setProjectName(innUnitForm.getProjectName());
                applyInnUnit.setProjectEgName(innUnitForm.getProjectEgName());
                applyInnUnitService.updateById(applyInnUnit);
            }
        }
        // 学科分类信息
        List<String> subjectCategoryList = Lists.newArrayList(innUnitForm.getSubjectCategory());
        project.setSubjectCategory(JSONArray.toJSONString(subjectCategoryList));

        projectService.saveOrUpdate(project);



        // 更新固定科技人员
        personJoinService.cleanAndSavePersonJoin(innUnitForm.getMainMemberList(), PersonJoinWayEnums.TASK_UNIT_PERSON, projectId);

        // 更新近5年承担的重要科研项目清单
        personJoinService.cleanAndSavePersonJoin(innUnitForm.getTopMemberOtherTaskList(), PersonJoinWayEnums.TASK_UNIT_PROJECT, projectId);

        // 保存/更新创新单元任务书
        SrpmsProjectTaskInnUnit unit = new BeanUtils<SrpmsProjectTaskInnUnit>().copyObj(innUnitForm, SrpmsProjectTaskInnUnit.class);
        this.saveOrUpdate(unit);

        // 附件 and 任务书签订各方意见及签章
        JSONObject attachmentVoJson = new JSONObject();
        attachmentVoJson.put("attachmentCommittee", innUnitForm.getAttachmentCommittee());
        attachmentVoJson.put("attachmentAudit", innUnitForm.getAttachmentAudit());
        attachmentVoJson.put("attachmentStatement", innUnitForm.getAttachmentStatement());
        attachmentVoJson.put("attachmentDept", innUnitForm.getAttachmentStatement());

        attachmentService.saveAttachmentListTask(attachmentVoJson, projectId);

        return projectId;
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<SrpmsProjectTaskInnUnit> getQueryWrapper(QueryWrapper<SrpmsProjectTaskInnUnit> queryWrapper,SrpmsProjectTaskInnUnitQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getProjectName())){
            queryWrapper.eq(SrpmsProjectTaskInnUnit.PROJECT_NAME,queryForm.getProjectName());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectEgName())){
            queryWrapper.eq(SrpmsProjectTaskInnUnit.PROJECT_EG_NAME,queryForm.getProjectEgName());
        }
        if(StringUtils.isNotBlank(queryForm.getSubjectCategory())){
            queryWrapper.eq(SrpmsProjectTaskInnUnit.SUBJECT_CATEGORY,queryForm.getSubjectCategory());
        }
        if(StringUtils.isNotBlank(queryForm.getSubjectName())){
            queryWrapper.eq(SrpmsProjectTaskInnUnit.SUBJECT_NAME,queryForm.getSubjectName());
        }
        if(StringUtils.isNotBlank(queryForm.getAchievementType())){
            queryWrapper.eq(SrpmsProjectTaskInnUnit.ACHIEVEMENT_TYPE,queryForm.getAchievementType());
        }
        if(StringUtils.isNotBlank(queryForm.getLeadPersonEducationExp())){
            queryWrapper.eq(SrpmsProjectTaskInnUnit.LEAD_PERSON_EDUCATION_EXP,queryForm.getLeadPersonEducationExp());
        }
        if(StringUtils.isNotBlank(queryForm.getLeadPersonResearchExp())){
            queryWrapper.eq(SrpmsProjectTaskInnUnit.LEAD_PERSON_RESEARCH_EXP,queryForm.getLeadPersonResearchExp());
        }
        if(StringUtils.isNotBlank(queryForm.getLeadPersonIntro())){
            queryWrapper.eq(SrpmsProjectTaskInnUnit.LEAD_PERSON_INTRO,queryForm.getLeadPersonIntro());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectTarget())){
            queryWrapper.eq(SrpmsProjectTaskInnUnit.PROJECT_TARGET,queryForm.getProjectTarget());
        }
        if(StringUtils.isNotBlank(queryForm.getSituationAndBenifit())){
            queryWrapper.eq(SrpmsProjectTaskInnUnit.SITUATION_AND_BENIFIT,queryForm.getSituationAndBenifit());
        }
        if(StringUtils.isNotBlank(queryForm.getDomainSituation())){
            queryWrapper.eq(SrpmsProjectTaskInnUnit.DOMAIN_SITUATION,queryForm.getDomainSituation());
        }
        if(StringUtils.isNotBlank(queryForm.getBenchMarking())){
            queryWrapper.eq(SrpmsProjectTaskInnUnit.BENCH_MARKING,queryForm.getBenchMarking());
        }
        if(StringUtils.isNotBlank(queryForm.getProspect())){
            queryWrapper.eq(SrpmsProjectTaskInnUnit.PROSPECT,queryForm.getProspect());
        }
        if(StringUtils.isNotBlank(queryForm.getTeamDirection())){
            queryWrapper.eq(SrpmsProjectTaskInnUnit.TEAM_DIRECTION,queryForm.getTeamDirection());
        }
        if(StringUtils.isNotBlank(queryForm.getDevelopmentGoal())){
            queryWrapper.eq(SrpmsProjectTaskInnUnit.DEVELOPMENT_GOAL,queryForm.getDevelopmentGoal());
        }
        if(StringUtils.isNotBlank(queryForm.getBudgetPlan())){
            queryWrapper.eq(SrpmsProjectTaskInnUnit.BUDGET_PLAN,queryForm.getBudgetPlan());
        }
        if(StringUtils.isNotBlank(queryForm.getCurrentConditions())){
            queryWrapper.eq(SrpmsProjectTaskInnUnit.CURRENT_CONDITIONS,queryForm.getCurrentConditions());
        }
        if(StringUtils.isNotBlank(queryForm.getFutureConditions())){
            queryWrapper.eq(SrpmsProjectTaskInnUnit.FUTURE_CONDITIONS,queryForm.getFutureConditions());
        }
        if(StringUtils.isNotBlank(queryForm.getTeamIntro())){
            queryWrapper.eq(SrpmsProjectTaskInnUnit.TEAM_INTRO,queryForm.getTeamIntro());
        }
        if(StringUtils.isNotBlank(queryForm.getAttachmentCommittee())){
            queryWrapper.eq(SrpmsProjectTaskInnUnit.ATTACHMENT_COMMITTEE,queryForm.getAttachmentCommittee());
        }
        if(StringUtils.isNotBlank(queryForm.getAttachmentAudit())){
            queryWrapper.eq(SrpmsProjectTaskInnUnit.ATTACHMENT_AUDIT,queryForm.getAttachmentAudit());
        }
        if(StringUtils.isNotBlank(queryForm.getAttachmentStatement())){
            queryWrapper.eq(SrpmsProjectTaskInnUnit.ATTACHMENT_STATEMENT,queryForm.getAttachmentStatement());
        }
        if(StringUtils.isNotBlank(queryForm.getAttachmentDept())){
            queryWrapper.eq(SrpmsProjectTaskInnUnit.ATTACHMENT_DEPT,queryForm.getAttachmentDept());
        }
        if(StringUtils.isNotBlank(queryForm.getOtherProjectAmout())){
            queryWrapper.eq(SrpmsProjectTaskInnUnit.OTHER_PROJECT_AMOUT,queryForm.getOtherProjectAmout());
        }
        if(StringUtils.isNotBlank(queryForm.getOtherBudgetAmout())){
            queryWrapper.eq(SrpmsProjectTaskInnUnit.OTHER_BUDGET_AMOUT,queryForm.getOtherBudgetAmout());
        }
        if(StringUtils.isNotBlank(queryForm.getOtherYearBudget())){
            queryWrapper.eq(SrpmsProjectTaskInnUnit.OTHER_YEAR_BUDGET,queryForm.getOtherYearBudget());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(SrpmsProjectTaskInnUnit.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(SrpmsProjectTaskInnUnit.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(SrpmsProjectTaskInnUnit.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(SrpmsProjectTaskInnUnit.UPDATE_BY,queryForm.getUpdateBy());
        }
        return queryWrapper;
    }
     */
}

