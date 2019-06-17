package com.deloitte.services.srpmp.project.apply.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.SrpmsProjectApplyInnUnitForm;
import com.deloitte.platform.api.srpmp.project.apply.vo.SrpmsProjectApplyInnUnitVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectAttachmentVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonJoinVo;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.srpmp.common.enums.*;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.common.service.ISrpmsCommonNclobService;
import com.deloitte.services.srpmp.project.apply.entity.SrpmsProjectApplyInnUnit;
import com.deloitte.services.srpmp.project.apply.mapper.SrpmsProjectApplyInnUnitMapper;
import com.deloitte.services.srpmp.project.apply.service.ISrpmsProjectApplyInnUnitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectAttachment;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectAttachmentService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectFlowService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectPersonJoinService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectService;
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
 * @Description :  SrpmsProjectApplyInnUnit服务实现类
 * @Modified :
 */
@Slf4j
@Service
@Transactional
public class SrpmsProjectApplyInnUnitServiceImpl extends ServiceImpl<SrpmsProjectApplyInnUnitMapper, SrpmsProjectApplyInnUnit> implements ISrpmsProjectApplyInnUnitService {

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
	private ISrpmsCommonNclobService commonNclobService;
	
    @Override
    public Long save(SrpmsProjectApplyInnUnitForm innUnitForm, DeptVo deptVo) {
        // 项目ID
        long projectId;
        JSONObject projectJson = JSONObject.parseObject(JSONObject.toJSONString(innUnitForm));
        List<String> subjectCategoryList = Lists.newArrayList(innUnitForm.getSubjectCategory());
        SrpmsProjectApplyInnUnit applyInnUnit = JSONObject.parseObject(projectJson.toJSONString(), SrpmsProjectApplyInnUnit.class);

        projectJson.put("subjectCategory", JSONArray.toJSONString(subjectCategoryList));

        if (innUnitForm.getId() == null) {
            projectId = commonService.insertCommonInfo(projectJson, ProjectCategoryEnums.ACADEMY_UNIT, deptVo);
            applyInnUnit.setId(projectId);
            this.save(applyInnUnit);
        } else {
            this.saveOrUpdate(applyInnUnit);
            projectId = commonService.saveOrUpdateCommonInfo(projectJson, ProjectCategoryEnums.ACADEMY_UNIT);
        }
        // 近5年承担的重要科研项目清单
        personJoinService.cleanAndSavePersonJoin(innUnitForm.getTopMemberOtherTaskList(), PersonJoinWayEnums.APPLY_UNIT_PROJECT, projectId);
        return projectId;
    }

    @Override
    public void submit(SrpmsProjectApplyInnUnitForm innUnitForm, UserVo userVo, DeptVo deptVo) {

        // 保存申请书
        Long id = this.save(innUnitForm, deptVo);
        innUnitForm.setId(id);

        // 校验申请书状态，只有未提交的申请书才能提交
        SrpmsProject project = projectService.getById(id);
        if (!SrpmsProjectStatusEnums.PEROJECT_NOT_SUBMIT.getCode().equals(project.getStatus())) {
            throw new BaseException(SrpmsErrorType.APPLY_HAVE_SUBMITTED);
        }

        // 更新项目状态
        projectService.submitProject(id);
        commonService.copyApplyToTaskInnUnit(innUnitForm);
        log.info("创新单元，提交申请书，已更新项目状态, projectId:{}", id);

        // 发起流程
        flowService.startAuditProcess(id, VoucherTypeEnums.APPLY_BOOK, userVo, deptVo);
        log.info("创新单元，提交申请书，已发起流程, projectId{}", id);
    }

    @Override
    public SrpmsProjectApplyInnUnitVo get(Long id) {
 
        // 项目信息
        SrpmsProject project = projectService.getById(id);
        if (null == project) {
            throw new BaseException(SrpmsErrorType.PROJECT_NOT_FOUND);
        }

		String jsonStr = commonNclobService.getApplyVo(id);
		if (jsonStr != null && jsonStr.length() != 0) {
			return JSONObject.parseObject(jsonStr, SrpmsProjectApplyInnUnitVo.class);
		}

        SrpmsProjectApplyInnUnit unit = this.getById(id);

        SrpmsProjectApplyInnUnitVo resultVo = new BeanUtils<SrpmsProjectApplyInnUnitVo>().copyObj(unit, SrpmsProjectApplyInnUnitVo.class);

        if (StringUtils.isNotBlank(project.getLeadPerson())) {
            resultVo.setLeadPerson(JSONObject.parseObject(project.getLeadPerson()));
        }

        if (StringUtils.isNotBlank(project.getLeadDept())) {
            resultVo.setLeadDept(JSONObject.parseObject(project.getLeadDept()));
        }

        // 固定科技人员名单
        List<SrpmsProjectPersonJoinVo> mainMemberList = personJoinService.queryPersonJoinListByJoinWay(PersonJoinWayEnums.APPLY_UNIT_PERSON, id);
        resultVo.setMainMemberList(mainMemberList);
        // 近5年承担的重要科研项目清单
        List<SrpmsProjectPersonJoinVo> topMemberOtherTaskList = personJoinService.queryPersonJoinListByJoinWay(PersonJoinWayEnums.APPLY_UNIT_PROJECT, id);
        resultVo.setTopMemberOtherTaskList(topMemberOtherTaskList);

        // 附件
        List<SrpmsProjectAttachment> attachmentFile = attachmentService.query(AttachmentCategoryEnums.ATTACHMENT_APPLY_NORMAL, id);
        List<SrpmsProjectAttachmentVo> attachmentFileVoList = new BeanUtils<SrpmsProjectAttachmentVo>().copyObjs(attachmentFile, SrpmsProjectAttachmentVo.class);
        resultVo.setAttachmentFile(attachmentFileVoList);

        // 依托单位学术委员会意见
        List<SrpmsProjectAttachment> attachmentCommittee = attachmentService.query(AttachmentCategoryEnums.ATTACHMENT_APPLY_01, id);
        List<SrpmsProjectAttachmentVo> attachmentCommitteeVoList = new BeanUtils<SrpmsProjectAttachmentVo>().copyObjs(attachmentCommittee, SrpmsProjectAttachmentVo.class);
        if (attachmentCommitteeVoList != null && attachmentCommitteeVoList.size() != 0) {
            resultVo.setAttachmentCommittee(attachmentCommitteeVoList.get(0));
        }

        // 依托单位的意见
        List<SrpmsProjectAttachment> attachmentAudit = attachmentService.query(AttachmentCategoryEnums.ATTACHMENT_APPLY_02, id);
        List<SrpmsProjectAttachmentVo> attachmentAuditVoList = new BeanUtils<SrpmsProjectAttachmentVo>().copyObjs(attachmentAudit, SrpmsProjectAttachmentVo.class);
        if (attachmentAuditVoList != null && attachmentAuditVoList.size() != 0) {
            resultVo.setAttachmentAudit(attachmentAuditVoList.get(0));
        }

        // 依托单位诚信申报承诺
        List<SrpmsProjectAttachment> attachmentStatement = attachmentService.query(AttachmentCategoryEnums.ATTACHMENT_APPLY_03, id);
        List<SrpmsProjectAttachmentVo> attachmentStatementVoList = new BeanUtils<SrpmsProjectAttachmentVo>().copyObjs(attachmentStatement, SrpmsProjectAttachmentVo.class);
        if (attachmentStatementVoList != null && attachmentStatementVoList.size() != 0) {
            resultVo.setAttachmentStatement(attachmentStatementVoList.get(0));
        }

        // 中国医学科学院意见
        List<SrpmsProjectAttachment> attachmentDept = attachmentService.query(AttachmentCategoryEnums.ATTACHMENT_APPLY_04, id);
        List<SrpmsProjectAttachmentVo> attachmentDeptVoList = new BeanUtils<SrpmsProjectAttachmentVo>().copyObjs(attachmentDept, SrpmsProjectAttachmentVo.class);
        if (attachmentDeptVoList != null && attachmentDeptVoList.size() != 0) {
            resultVo.setAttachmentDept(attachmentDeptVoList.get(0));
        }
        return resultVo;
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<SrpmsProjectApplyInnUnit> getQueryWrapper(QueryWrapper<SrpmsProjectApplyInnUnit> queryWrapper,SrpmsProjectApplyInnUnitQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getProjectName())){
            queryWrapper.eq(SrpmsProjectApplyInnUnit.PROJECT_NAME,queryForm.getProjectName());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectEgName())){
            queryWrapper.eq(SrpmsProjectApplyInnUnit.PROJECT_EG_NAME,queryForm.getProjectEgName());
        }
        if(StringUtils.isNotBlank(queryForm.getSubjectCategory())){
            queryWrapper.eq(SrpmsProjectApplyInnUnit.SUBJECT_CATEGORY,queryForm.getSubjectCategory());
        }
        if(StringUtils.isNotBlank(queryForm.getSubjectName())){
            queryWrapper.eq(SrpmsProjectApplyInnUnit.SUBJECT_NAME,queryForm.getSubjectName());
        }
        if(StringUtils.isNotBlank(queryForm.getAchievementType())){
            queryWrapper.eq(SrpmsProjectApplyInnUnit.ACHIEVEMENT_TYPE,queryForm.getAchievementType());
        }
        if(StringUtils.isNotBlank(queryForm.getLeadPersonEducationExp())){
            queryWrapper.eq(SrpmsProjectApplyInnUnit.LEAD_PERSON_EDUCATION_EXP,queryForm.getLeadPersonEducationExp());
        }
        if(StringUtils.isNotBlank(queryForm.getLeadPersonResearchExp())){
            queryWrapper.eq(SrpmsProjectApplyInnUnit.LEAD_PERSON_RESEARCH_EXP,queryForm.getLeadPersonResearchExp());
        }
        if(StringUtils.isNotBlank(queryForm.getLeadPersonIntro())){
            queryWrapper.eq(SrpmsProjectApplyInnUnit.LEAD_PERSON_INTRO,queryForm.getLeadPersonIntro());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectTarget())){
            queryWrapper.eq(SrpmsProjectApplyInnUnit.PROJECT_TARGET,queryForm.getProjectTarget());
        }
        if(StringUtils.isNotBlank(queryForm.getSituationAndBenifit())){
            queryWrapper.eq(SrpmsProjectApplyInnUnit.SITUATION_AND_BENIFIT,queryForm.getSituationAndBenifit());
        }
        if(StringUtils.isNotBlank(queryForm.getDomainSituation())){
            queryWrapper.eq(SrpmsProjectApplyInnUnit.DOMAIN_SITUATION,queryForm.getDomainSituation());
        }
        if(StringUtils.isNotBlank(queryForm.getBenchMarking())){
            queryWrapper.eq(SrpmsProjectApplyInnUnit.BENCH_MARKING,queryForm.getBenchMarking());
        }
        if(StringUtils.isNotBlank(queryForm.getProspect())){
            queryWrapper.eq(SrpmsProjectApplyInnUnit.PROSPECT,queryForm.getProspect());
        }
        if(StringUtils.isNotBlank(queryForm.getTeamDirection())){
            queryWrapper.eq(SrpmsProjectApplyInnUnit.TEAM_DIRECTION,queryForm.getTeamDirection());
        }
        if(StringUtils.isNotBlank(queryForm.getDevelopmentGoal())){
            queryWrapper.eq(SrpmsProjectApplyInnUnit.DEVELOPMENT_GOAL,queryForm.getDevelopmentGoal());
        }
        if(StringUtils.isNotBlank(queryForm.getBudgetPlan())){
            queryWrapper.eq(SrpmsProjectApplyInnUnit.BUDGET_PLAN,queryForm.getBudgetPlan());
        }
        if(StringUtils.isNotBlank(queryForm.getCurrentConditions())){
            queryWrapper.eq(SrpmsProjectApplyInnUnit.CURRENT_CONDITIONS,queryForm.getCurrentConditions());
        }
        if(StringUtils.isNotBlank(queryForm.getFutureConditions())){
            queryWrapper.eq(SrpmsProjectApplyInnUnit.FUTURE_CONDITIONS,queryForm.getFutureConditions());
        }
        if(StringUtils.isNotBlank(queryForm.getTeamIntro())){
            queryWrapper.eq(SrpmsProjectApplyInnUnit.TEAM_INTRO,queryForm.getTeamIntro());
        }
        if(StringUtils.isNotBlank(queryForm.getAttachmentCommittee())){
            queryWrapper.eq(SrpmsProjectApplyInnUnit.ATTACHMENT_COMMITTEE,queryForm.getAttachmentCommittee());
        }
        if(StringUtils.isNotBlank(queryForm.getAttachmentAudit())){
            queryWrapper.eq(SrpmsProjectApplyInnUnit.ATTACHMENT_AUDIT,queryForm.getAttachmentAudit());
        }
        if(StringUtils.isNotBlank(queryForm.getAttachmentStatement())){
            queryWrapper.eq(SrpmsProjectApplyInnUnit.ATTACHMENT_STATEMENT,queryForm.getAttachmentStatement());
        }
        if(StringUtils.isNotBlank(queryForm.getAttachmentDept())){
            queryWrapper.eq(SrpmsProjectApplyInnUnit.ATTACHMENT_DEPT,queryForm.getAttachmentDept());
        }
        if(StringUtils.isNotBlank(queryForm.getOtherProjectAmout())){
            queryWrapper.eq(SrpmsProjectApplyInnUnit.OTHER_PROJECT_AMOUT,queryForm.getOtherProjectAmout());
        }
        if(StringUtils.isNotBlank(queryForm.getOtherBudgetAmout())){
            queryWrapper.eq(SrpmsProjectApplyInnUnit.OTHER_BUDGET_AMOUT,queryForm.getOtherBudgetAmout());
        }
        if(StringUtils.isNotBlank(queryForm.getOtherYearBudget())){
            queryWrapper.eq(SrpmsProjectApplyInnUnit.OTHER_YEAR_BUDGET,queryForm.getOtherYearBudget());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(SrpmsProjectApplyInnUnit.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(SrpmsProjectApplyInnUnit.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(SrpmsProjectApplyInnUnit.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(SrpmsProjectApplyInnUnit.UPDATE_BY,queryForm.getUpdateBy());
        }
        return queryWrapper;
    }
     */
}

