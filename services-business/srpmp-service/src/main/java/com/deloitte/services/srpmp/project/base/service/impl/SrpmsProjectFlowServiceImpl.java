package com.deloitte.services.srpmp.project.base.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.entity.ContentType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fileservice.feign.FileOperatorFeignService;
import com.deloitte.platform.api.fileservice.vo.FileInfoVo;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.SrpmsProjectApplyInnUnitVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectApprovalForm;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.TaskNodeActionVO;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetDetailVo;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskAcademyVo;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskInnUnitVo;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskReformVo;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskSchStudentVo;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskTranLongVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.exception.ServiceException;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.srpmp.common.constant.NclobConstant;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.common.entity.SrpmsCommonNclob;
import com.deloitte.services.srpmp.common.enums.BudgetCategoryEnums;
import com.deloitte.services.srpmp.common.enums.EnumMprProcessStatus;
import com.deloitte.services.srpmp.common.enums.ProjectCategoryEnums;
import com.deloitte.services.srpmp.common.enums.SrpmsProjectStatusEnums;
import com.deloitte.services.srpmp.common.enums.SrpmsProjectUpdateStatusEnums;
import com.deloitte.services.srpmp.common.enums.VoucherStatusEnums;
import com.deloitte.services.srpmp.common.enums.VoucherTypeEnums;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.common.service.ISerialNoCenterService;
import com.deloitte.services.srpmp.common.service.ISrpmsCommonNclobService;
import com.deloitte.services.srpmp.common.service.impl.WordExportServiceImpl;
import com.deloitte.services.srpmp.common.util.JSONConvert;
import com.deloitte.services.srpmp.common.util.WordConvertUtil;
import com.deloitte.services.srpmp.outline.util.CommonUtil;
import com.deloitte.services.srpmp.project.accept.entity.SrpmsProjectAccept;
import com.deloitte.services.srpmp.project.accept.service.ISrpmsProjectAcceptService;
import com.deloitte.services.srpmp.project.apply.dto.ProjectApproveDeptDTO;
import com.deloitte.services.srpmp.project.apply.dto.ProjectApproveProjectDTO;
import com.deloitte.services.srpmp.project.apply.service.ISrpmsProjectApplyAcademyService;
import com.deloitte.services.srpmp.project.apply.service.ISrpmsProjectApplyInnBcooService;
import com.deloitte.services.srpmp.project.apply.service.ISrpmsProjectApplyInnCooService;
import com.deloitte.services.srpmp.project.apply.service.ISrpmsProjectApplyInnPreService;
import com.deloitte.services.srpmp.project.apply.service.ISrpmsProjectApplyInnUnitService;
import com.deloitte.services.srpmp.project.apply.service.ISrpmsProjectApplyReformService;
import com.deloitte.services.srpmp.project.apply.service.ISrpmsProjectApplySchStuService;
import com.deloitte.services.srpmp.project.apply.service.ISrpmsProjectApplySchTeachService;
import com.deloitte.services.srpmp.project.base.dto.ProjectDTO;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectApproval;
import com.deloitte.services.srpmp.project.base.entity.SrpmsVoucher;
import com.deloitte.services.srpmp.project.base.mapper.SrpmsProjectMapper;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectApprovalService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectAttachmentService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectBudgetDetailService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectExpertService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectFlowService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectSyncMsgService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsVoucherService;
import com.deloitte.services.srpmp.project.budget.entity.SrpmsProjectBudgetYear;
import com.deloitte.services.srpmp.project.budget.service.ISrpmsProjectBudgetApplyService;
import com.deloitte.services.srpmp.project.budget.service.ISrpmsProjectBudgetApplyYearService;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaBaseInfo;
import com.deloitte.services.srpmp.project.mpr.entity.MprUnitEvaInfo;
import com.deloitte.services.srpmp.project.mpr.service.IMprEvaBaseInfoService;
import com.deloitte.services.srpmp.project.mpr.service.IMprUnitEvaInfoService;
import com.deloitte.services.srpmp.project.task.entity.SrpmsProjectTaskInn;
import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskAcademyService;
import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskInnService;
import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskInnUnitService;
import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskReformService;
import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskSchStudentService;
import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskTranLongService;
import com.deloitte.services.srpmp.project.update.entity.SrpmsProjectUpdate;
import com.deloitte.services.srpmp.project.update.service.ISrpmsProjectUpdateService;
import com.deloitte.services.srpmp.project.update.service.impl.SrpmsProjectUpdateBudgetServiceImpl;
import com.deloitte.services.srpmp.project.update.service.impl.SrpmsProjectUpdateContentServiceImpl;
import com.deloitte.services.srpmp.project.update.service.impl.SrpmsProjectUpdateFileServiceImpl;
import com.deloitte.services.srpmp.project.update.service.impl.SrpmsProjectUpdateMenberServiceImpl;
import com.deloitte.services.srpmp.relust.entity.SrpmsResult;
import com.deloitte.services.srpmp.relust.entity.SrpmsResultNewTitle;
import com.deloitte.services.srpmp.relust.entity.SrpmsResultTrans;
import com.deloitte.services.srpmp.relust.mapper.SrpmsResultNewTitleMapper;
import com.deloitte.services.srpmp.relust.service.ISrpmsResultService;
import com.deloitte.services.srpmp.relust.service.ISrpmsResultTransService;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Multimaps;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author : pengchao
 * @Date : Create in 2019-03-11
 * @Description : SrpmsProject服务实现类
 * @Modified :
 */
@Service
@Transactional
@Slf4j
public class SrpmsProjectFlowServiceImpl extends ServiceImpl<SrpmsProjectMapper, SrpmsProject>
		implements ISrpmsProjectFlowService {

	@Autowired
	private ISerialNoCenterService serialNoService;

	@Autowired
	private SrpmsResultNewTitleMapper newTitleMapper;

	@Autowired
	private SrpmsProjectMapper srpmsProjectMapper;

	@Autowired
	public ISrpmsProjectAttachmentService attachmentService;

	@Autowired
	public ISrpmsProjectExpertService expertService;

	@Autowired
	public ISrpmsProjectApprovalService approvalService;

	@Autowired
	private WordExportServiceImpl wordExportService;

	@Autowired
	private SrpmsProjectBpmServiceImpl bpmService;

	@Autowired
	private ISrpmsVoucherService voucherService;

	@Autowired
	private FileOperatorFeignService fileOperatorFeignService;

	@Autowired
	private ISrpmsProjectService projectService;

	@Autowired
	private ISrpmsResultService resultService;

	@Autowired
	private ISrpmsResultTransService resultTransService;

	@Autowired
	private IMprEvaBaseInfoService baseInfoService;

	@Autowired
	private ISrpmsProjectBudgetApplyYearService budgetYearService;

	@Autowired
	private IMprUnitEvaInfoService mprUnitEvaInfoService;

	@Autowired
	SrpmsProjectUpdateContentServiceImpl projectUpdateContentService;

	@Autowired
	private ISrpmsProjectUpdateService updateService;

	@Autowired
	private ISrpmsProjectBudgetDetailService budgetDetailService;

	@Autowired
	private ISrpmsProjectSyncMsgService projectSyncMsgService;

	@Autowired
	private ISrpmsProjectAcceptService acceptService;

	@Autowired
	private SrpmsProjectUpdateFileServiceImpl projectUpdateFileService;

	@Autowired
	ISrpmsCommonNclobService nclobService;

	@Autowired
	SrpmsProjectUpdateMenberServiceImpl updateMenberService;

	@Autowired
	SrpmsProjectUpdateBudgetServiceImpl updateBudgetServiceImpl;

	@Autowired
	ISrpmsProjectApplyInnBcooService applyInnBcooService;

	@Autowired
	ISrpmsProjectApplyInnCooService applyInnCooService;

	@Autowired
	ISrpmsProjectApplyInnPreService applyInnPreService;

	@Autowired
	ISrpmsProjectApplyInnUnitService applyInnUnitService;

	@Autowired
	ISrpmsProjectApplyReformService applyReformService;

	@Autowired
	ISrpmsProjectApplySchStuService applySchStuService;

	@Autowired
	ISrpmsProjectApplySchTeachService applySchTeachService;

	@Autowired
	ISrpmsProjectApplyAcademyService applyAcademyService;

	@Autowired
	ISrpmsProjectBudgetApplyService budgetApplyService;

	@Autowired
	private ISrpmsProjectTaskInnService taskInnService;

	@Autowired
	private ISrpmsProjectTaskAcademyService taskAcademyService;

	@Autowired
	private ISrpmsProjectTaskReformService taskReformService;

	@Autowired
	private ISrpmsProjectTaskSchStudentService taskSchStudentService;

	@Autowired
	ISrpmsProjectTaskInnUnitService taskInnUnitService;

	@Autowired
	private ISrpmsProjectTaskTranLongService taskTranLongService;

	@Override
	public void agree(SrpmsProjectApprovalForm approvalForm) {
		// 查询项目
		SrpmsProject projectEntity = this.getById(approvalForm.getProjectId());
		if (!SrpmsProjectStatusEnums.PEROJECT_HAS_SUBMIT.getCode().equals(projectEntity.getStatus())) {
			throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
		}
		projectEntity.setStatus(SrpmsProjectStatusEnums.PEROJECT_HAS_APPROVE_PASS.getCode());
		projectEntity.setProjectNum(approvalForm.getProjectNum());
		this.updateById(projectEntity);
		SrpmsProjectApproval comments = new SrpmsProjectApproval();
		comments.setProjectId(approvalForm.getProjectId());
		comments.setApprovalComments(approvalForm.getApprovalComments());
		approvalService.save(comments);
	}

	@Override
	public void agreeWithBpm(TaskNodeActionVO actionVO, DeptVo deptVo) {
		boolean hasEnd = bpmService.auditApprove(actionVO, deptVo);
		SrpmsVoucher voucherEntity = voucherService.getById(actionVO.getObjectId());
		// 审批后更新单据撤回标识
		voucherEntity.setRecallFlag(1);

		if(StringUtils.isNotBlank(actionVO.getProjectNum())) {// 管理员审批后需要处长审批流程未结束，需要手动更新一下项目编号
			// 更新项目编号
			SrpmsProject srpmsProject = new SrpmsProject();
			srpmsProject.setId(voucherEntity.getProjectId());
			srpmsProject.setProjectNum(actionVO.getProjectNum());
			this.updateById(srpmsProject);
		}

		if (!hasEnd) { // 未结束，就返回
			voucherService.saveOrUpdate(voucherEntity);
			return;
		}

		SrpmsProject projectEntity = this.getById(voucherEntity.getProjectId());

		String projectType = projectEntity.getProjectType();

		long projectId = projectEntity.getId();

		if (voucherEntity.getVoucherType().equals(VoucherTypeEnums.APPLY_BOOK.getCode())) {
			if (!SrpmsProjectStatusEnums.PEROJECT_HAS_SUBMIT.getCode().equals(projectEntity.getStatus())) {
				throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
			}
			// 申请书评审
			projectEntity.setStatus(SrpmsProjectStatusEnums.PEROJECT_HAS_APPROVE_PASS.getCode());
//			projectEntity.setProjectNum(actionVO.getProjectNum());

			if (projectType.equals(ProjectCategoryEnums.INNOVATE_BCOO.getHeader())) {

				SrpmsCommonNclob clobEntity = new SrpmsCommonNclob();
				clobEntity.setProjectId(projectId);
				clobEntity.setType(NclobConstant.APPLY_BOOK);
			    JSONObject json = applyInnBcooService.get(projectId);
			    JSONConvert.convertJson(json);
				clobEntity.setContent(json.toJSONString());
				nclobService.save(clobEntity);

				clobEntity = new SrpmsCommonNclob();
				clobEntity.setProjectId(projectId);
				clobEntity.setType(NclobConstant.BUGGET_APPLY_BOOK);
				JSONObject budgetJson = budgetApplyService.queryBudgetApplyById(projectId);
				JSONConvert.convertJson(budgetJson);
				clobEntity.setContent(budgetJson.toJSONString());
				nclobService.save(clobEntity);

			} else if (projectType.equals(ProjectCategoryEnums.INNOVATE_COO.getHeader())) {

				SrpmsCommonNclob clobEntity = new SrpmsCommonNclob();
				clobEntity.setProjectId(projectId);
				clobEntity.setType(NclobConstant.APPLY_BOOK);
			    JSONObject json = applyInnCooService.get(projectId);
			    JSONConvert.convertJson(json);
				clobEntity.setContent(json.toJSONString());
				nclobService.save(clobEntity);

				clobEntity = new SrpmsCommonNclob();
				clobEntity.setProjectId(projectId);
				clobEntity.setType(NclobConstant.BUGGET_APPLY_BOOK);
				JSONObject budgetJson = budgetApplyService.queryBudgetApplyById(projectId);
				JSONConvert.convertJson(budgetJson);
				clobEntity.setContent(budgetJson.toJSONString());
				nclobService.save(clobEntity);

			} else if (projectType.equals(ProjectCategoryEnums.INNOVATE_PRE.getHeader())) {

				SrpmsCommonNclob clobEntity = new SrpmsCommonNclob();
				clobEntity.setProjectId(projectId);
				clobEntity.setType(NclobConstant.APPLY_BOOK);
			    JSONObject json = applyInnPreService.queryApplyVoById(projectId);
			    JSONConvert.convertJson(json);
				clobEntity.setContent(json.toJSONString());
				nclobService.save(clobEntity);

				clobEntity = new SrpmsCommonNclob();
				clobEntity.setProjectId(projectId);
				clobEntity.setType(NclobConstant.BUGGET_APPLY_BOOK);
				JSONObject budgetJson = budgetApplyService.queryBudgetApplyById(projectId);
				JSONConvert.convertJson(budgetJson);
				clobEntity.setContent(budgetJson.toJSONString());
				nclobService.save(clobEntity);

			} else if (projectType.equals(ProjectCategoryEnums.ACADEMY.getHeader())) {

				SrpmsCommonNclob clobEntity = new SrpmsCommonNclob();
				clobEntity.setProjectId(projectId);
				clobEntity.setType(NclobConstant.APPLY_BOOK);
			    JSONObject json = applyAcademyService.queryApplyVoById(projectId, null, null);
			    JSONConvert.convertJson(json);
				clobEntity.setContent(json.toJSONString());
				nclobService.save(clobEntity);

			} else if (projectType.equals(ProjectCategoryEnums.STRUCTURAL_REFORM.getHeader())) {

				SrpmsCommonNclob clobEntity = new SrpmsCommonNclob();
				clobEntity.setProjectId(projectId);
				clobEntity.setType(NclobConstant.APPLY_BOOK);
			    JSONObject json = applyReformService.queryApplyVoById(projectId, null, null);
			    JSONConvert.convertJson(json);
				clobEntity.setContent(json.toJSONString());
				nclobService.save(clobEntity);

			} else if (projectType.equals(ProjectCategoryEnums.ACADEMY_UNIT.getHeader())) {

				SrpmsCommonNclob clobEntity = new SrpmsCommonNclob();
				clobEntity.setProjectId(projectId);
				clobEntity.setType(NclobConstant.APPLY_BOOK);
				SrpmsProjectApplyInnUnitVo vo = applyInnUnitService.get(projectId);
				JSONObject json = JSONObject.parseObject(JSON.toJSONString(vo));
			    JSONConvert.convertJson(json);
				clobEntity.setContent(json.toJSONString());
				nclobService.save(clobEntity);

			} else if (projectType.equals(ProjectCategoryEnums.SCHOOL_STU.getHeader())) {

				SrpmsCommonNclob clobEntity = new SrpmsCommonNclob();
				clobEntity.setProjectId(projectId);
				clobEntity.setType(NclobConstant.APPLY_BOOK);
			    JSONObject json = applySchStuService.queryApplyVoById(projectId, null, null);
			    JSONConvert.convertJson(json);
				clobEntity.setContent(json.toJSONString());
				nclobService.save(clobEntity);

			} else if (projectType.equals(ProjectCategoryEnums.SCHOOL_TEACH.getHeader())) {

				SrpmsCommonNclob clobEntity = new SrpmsCommonNclob();
				clobEntity.setProjectId(projectId);
				clobEntity.setType(NclobConstant.APPLY_BOOK);
			    JSONObject json = applySchTeachService.queryApplyVoById(projectId, null, null);
			    JSONConvert.convertJson(json);
				clobEntity.setContent(json.toJSONString());
				nclobService.save(clobEntity);
			}

		} else if (voucherEntity.getVoucherType().equals(VoucherTypeEnums.TASK_BOOK.getCode())) {
			if (!SrpmsProjectStatusEnums.PEROJECT_TASK_SUBMIT.getCode().equals(projectEntity.getStatus())) {
				throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
			}
			// 任务书评审
			projectEntity.setStatus(SrpmsProjectStatusEnums.PEROJECT_TASK_PASS.getCode());

			if (projectType.equals(ProjectCategoryEnums.INNOVATE_BCOO.getHeader())
					|| projectType.equals(ProjectCategoryEnums.INNOVATE_COO.getHeader())
					|| projectType.equals(ProjectCategoryEnums.INNOVATE_COO.getHeader())) {

				SrpmsCommonNclob clobEntity = new SrpmsCommonNclob();
				clobEntity.setProjectId(projectId);
				clobEntity.setType(NclobConstant.TASK_BOOK);
				clobEntity.setContent(taskInnService.queryById(projectId).toJSONString());
				nclobService.save(clobEntity);

				clobEntity = new SrpmsCommonNclob();
				clobEntity.setProjectId(projectId);
				clobEntity.setType(NclobConstant.BUGGET_TASK_BOOK);
				clobEntity.setContent(budgetApplyService.queryBudgetTaskById(projectId).toJSONString());
				nclobService.save(clobEntity);

			} else if (projectType.equals(ProjectCategoryEnums.ACADEMY.getHeader())) {

				SrpmsCommonNclob clobEntity = new SrpmsCommonNclob();
				clobEntity.setProjectId(projectId);
				clobEntity.setType(NclobConstant.TASK_BOOK);
				SrpmsProjectTaskAcademyVo vo = taskAcademyService.queryById(projectId);
				clobEntity.setContent(JSON.toJSONString(vo));
				nclobService.save(clobEntity);

			} else if (projectType.equals(ProjectCategoryEnums.STRUCTURAL_REFORM.getHeader())) {

				SrpmsCommonNclob clobEntity = new SrpmsCommonNclob();
				clobEntity.setProjectId(projectId);
				clobEntity.setType(NclobConstant.TASK_BOOK);
				SrpmsProjectTaskReformVo vo = taskReformService.queryById(projectId);
				clobEntity.setContent(JSON.toJSONString(vo));
				nclobService.save(clobEntity);

			} else if (projectType.equals(ProjectCategoryEnums.ACADEMY_UNIT.getHeader())) {

				SrpmsCommonNclob clobEntity = new SrpmsCommonNclob();
				clobEntity.setProjectId(projectId);
				clobEntity.setType(NclobConstant.TASK_BOOK);
				SrpmsProjectTaskInnUnitVo vo = taskInnUnitService.get(projectId);
				clobEntity.setContent(JSON.toJSONString(vo));
				nclobService.save(clobEntity);

			} else if (projectType.equals(ProjectCategoryEnums.SCHOOL_STU.getHeader())
					|| projectType.equals(ProjectCategoryEnums.SCHOOL_TEACH.getHeader())) {

				SrpmsCommonNclob clobEntity = new SrpmsCommonNclob();
				clobEntity.setProjectId(projectId);
				clobEntity.setType(NclobConstant.TASK_BOOK);
				SrpmsProjectTaskSchStudentVo vo = taskSchStudentService.queryById(projectId);
				clobEntity.setContent(JSON.toJSONString(vo));
				nclobService.save(clobEntity);

			}

			// 同步立项消息到MQ
			projectSyncMsgService.syncProjectTaskPass(projectEntity);
			// 同步绩效目标到MQ
			projectSyncMsgService.syncBudgetTask(projectEntity);

		} else if (VoucherTypeEnums.TRAN_LONG_TASK_BOOK.getCode().equals(voucherEntity.getVoucherType())) {// 添加横纵向项目审批条件分支
			if (!SrpmsProjectStatusEnums.PEROJECT_TASK_SUBMIT.getCode().equals(projectEntity.getStatus())) {
				throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
			}

			SrpmsCommonNclob clobEntity = new SrpmsCommonNclob();
			clobEntity.setProjectId(projectId);
			clobEntity.setType(NclobConstant.TASK_BOOK);
			SrpmsProjectTaskTranLongVo vo = taskTranLongService.queryById(projectId);
			clobEntity.setContent(JSON.toJSONString(vo));
			nclobService.save(clobEntity);

			// 横纵向项目任务书评审
			projectEntity.setStatus(SrpmsProjectStatusEnums.PEROJECT_TASK_PASS.getCode());
		}
		// 更新项目状态
		this.updateById(projectEntity);
		// 更新单据状态
		voucherEntity.setVoucherStatus(VoucherStatusEnums.APPROVED.getCode());
		voucherService.saveOrUpdate(voucherEntity);

		// 横纵向审批通过写入题录-新获项目类似表，方便题录统计
		if (SrpmsConstant.PROJECT_TYPE_1.equals(projectEntity.getProjectFlag())) {
			Map<String, Object> paramMap = new HashMap<>();
			String deptCode = "";
			if (StringUtils.isNotBlank(projectEntity.getLeadDept())) {
				deptCode = JSONObject.parseObject(projectEntity.getLeadDept()).getString("deptCode");
			}
			paramMap.put("orgCode", deptCode);
			paramMap.put("proNum", projectEntity.getProjectNum() == null ? ""
					: CommonUtil.deleteStringSpace(projectEntity.getProjectNum()));
			if (newTitleMapper.getQueryCount(paramMap) > 0) {
				return;
			}

			SrpmsProjectTaskTranLongVo tranLongVo = taskTranLongService.getTranById(projectEntity.getId());
			SrpmsResultNewTitle resultNewTitle = new SrpmsResultNewTitle();
			LocalDateTime actionDate = projectEntity.getProjectActionDateStart();
			JSONObject jsonObject;
			if (actionDate != null) {
				resultNewTitle.setYear(CommonUtil.objectToString(actionDate.getYear()));
				resultNewTitle.setMonth(CommonUtil.objectToString(actionDate.getMonthValue()));
			}
			resultNewTitle.setId(projectEntity.getId());// 项目ID
			resultNewTitle.setProNum(projectEntity.getProjectNum());
			resultNewTitle.setProName(projectEntity.getProjectName());
			resultNewTitle.setProCategory(projectEntity.getProjectType());
			resultNewTitle.setDeptCode(CommonUtil.getLongValue(deptCode));
			resultNewTitle.setProEnterType(tranLongVo.getProEnterType());
			if (tranLongVo.getBudgetPreYearList() != null && tranLongVo.getBudgetPreYearList().size() > 0) {// 项目经费支出总计-总经费
				SrpmsProjectBudgetDetailVo detailVo = tranLongVo.getBudgetPreYearList().get(0);
				if (detailVo.getBudgetDetail() != null && detailVo.getBudgetDetail().size() > 0) {
					Double totalOutlay = 0.0;
					for (Iterator e = detailVo.getBudgetDetail().iterator(); e.hasNext();) {
						jsonObject = (JSONObject) e.next();
						totalOutlay += jsonObject.getDouble("amount") == null ? 0.0 : jsonObject.getDouble("amount");
					}
					resultNewTitle.setProTotalOutlay(totalOutlay);
				}
			}
			if (tranLongVo.getProFundsSource() != null && tranLongVo.getProFundsSource().size() > 0) {// 经费来源-到位经费
				Double receiveOutlay = 0.0;
				for (Iterator e = tranLongVo.getProFundsSource().iterator(); e.hasNext();) {
					jsonObject = (JSONObject) e.next();
					receiveOutlay += jsonObject.getDouble("amount") == null ? 0.0 : jsonObject.getDouble("amount");
				}
				resultNewTitle.setProReceiveOutlay(receiveOutlay);
			}

			// 状态设值
			if (SrpmsProjectStatusEnums.PEROJECT_ACCEPT.getCode().equals(projectEntity.getStatus())) {
				resultNewTitle.setProState(SrpmsConstant.OUTLINE_PRO_STAT_30);
			} else if (SrpmsProjectStatusEnums.PEROJECT_END.getCode().equals(projectEntity.getStatus())) {
				resultNewTitle.setProState(SrpmsConstant.OUTLINE_PRO_STAT_40);
			} else {
				resultNewTitle.setProState(SrpmsConstant.OUTLINE_PRO_STAT_20);
			}

			newTitleMapper.insert(resultNewTitle);
		}
	}

	@Override
	public void refuseWitBpm(TaskNodeActionVO actionVO) {
		bpmService.auditRefuse(actionVO);
		SrpmsVoucher voucherEntity = voucherService.getById(actionVO.getObjectId());
		SrpmsProject projectEntity = this.getById(voucherEntity.getProjectId());
		if (voucherEntity.getVoucherType().equals(VoucherTypeEnums.APPLY_BOOK.getCode())) {
			// 申请书评审
			projectEntity.setStatus(SrpmsProjectStatusEnums.PEROJECT_END.getCode());
		} else if (voucherEntity.getVoucherType().equals(VoucherTypeEnums.TASK_BOOK.getCode())) {
			// 任务书评审
			projectEntity.setStatus(SrpmsProjectStatusEnums.PEROJECT_END.getCode());
		} else if (VoucherTypeEnums.TRAN_LONG_TASK_BOOK.getCode().equals(voucherEntity.getVoucherType())) {// 添加横纵向项目审批条件分支
			// 横纵向项目任务书评审
			projectEntity.setStatus(SrpmsProjectStatusEnums.PEROJECT_END.getCode());
		}
		// 更新项目状态
		this.updateById(projectEntity);

        voucherEntity.setRecallFlag(1);
		// 更新单据状态
        voucherEntity.setVoucherStatus(VoucherStatusEnums.REFUSED.getCode());
        voucherService.saveOrUpdate(voucherEntity);
	}

	@Override
	public void startAuditProcess(Long projectId, VoucherTypeEnums typeEnums, UserVo userVo, DeptVo deptVo) {
		SrpmsVoucher voucher = voucherService.getSrpmsVoucherByReject(projectId);// 查询项目是否进行驳回操作
		if (voucher != null ) {
			this.againSubmit(voucher, deptVo, false);
		} else {
			// 新建一个单据
			voucher = voucherService.generateNewVoucher(projectId, typeEnums);
			bpmService.startProcess(voucher, userVo, deptVo, null);
		}
	}

	/**
	 * 变更记录新增发起流程操作接口
	 *
	 * @param update
	 * @param typeEnums
	 * @param userVo
	 * @param deptVo
	 */
	@Override
	public void startModifyAuditProcess(SrpmsProjectUpdate update, VoucherTypeEnums typeEnums, UserVo userVo,
			DeptVo deptVo) {
		SrpmsVoucher voucher = voucherService.getSrpmsVoucherByReject(update.getId());// 查询项目是否进行驳回操作
		if (voucher != null ) {
			this.againSubmit(voucher, deptVo, false);
		} else {
			// 新建一个单据
			voucher = voucherService.generateNewVoucherModify(update, typeEnums, userVo);
			bpmService.startProcess(voucher, userVo, deptVo, update);
		}
	}

	/**
	 * 变更记录新增发起流程操作接口
	 *
	 * @param budgetYear
	 * @param typeEnums
	 * @param userVo
	 * @param deptVo
	 */
	@Override
	public void startBudgetAuditProcess(SrpmsProjectBudgetYear budgetYear, VoucherTypeEnums typeEnums, UserVo userVo,
			DeptVo deptVo) {
		SrpmsVoucher voucher = voucherService.getSrpmsVoucherByReject(budgetYear.getId());// 查询项目是否进行驳回操作
		if (voucher != null ) {
			this.againSubmit(voucher, deptVo, false);
		} else {
			// 新建一个单据
			voucher = voucherService.generateNewVoucherBudget(budgetYear, typeEnums, userVo);
			bpmService.startProcess(voucher, userVo, deptVo, null);
		}
	}

	@Override
	public void agreeTask(SrpmsProjectApprovalForm approvalForm) {
		// 查询项目
		SrpmsProject projectEntity = this.getById(approvalForm.getProjectId());
		if (!SrpmsProjectStatusEnums.PEROJECT_TASK_SUBMIT.getCode().equals(projectEntity.getStatus())) {
			throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
		}
		projectEntity.setStatus(SrpmsProjectStatusEnums.PEROJECT_TASK_PASS.getCode());
		this.updateById(projectEntity);

		SrpmsProjectApproval comments = new SrpmsProjectApproval();
		comments.setProjectId(approvalForm.getProjectId());
		comments.setApprovalComments(approvalForm.getApprovalComments());
		approvalService.save(comments);
	}

	/**
	 * 项目变更审批通过service接口实现
	 *
	 * @param actionVO
	 * @param deptVo
	 */
	@Override
	public void modifyApprovePassBpm(TaskNodeActionVO actionVO, DeptVo deptVo) {
		boolean hasEnd = bpmService.auditApprove(actionVO, deptVo);
		SrpmsVoucher voucherEntity = voucherService.getById(actionVO.getObjectId());
		// 审批后更新单据撤回标识
		voucherEntity.setRecallFlag(1);

		if (!hasEnd) { // 未结束，就返回
			voucherService.saveOrUpdate(voucherEntity);
			return;
		}
		SrpmsProjectUpdate update = updateService.getById(voucherEntity.getProjectId());// 查询变更单据信息
		if (update != null
				&& !SrpmsProjectUpdateStatusEnums.PEROJECT_HAS_SUBMIT.getCode().equals(update.getUpdateState())) {
			throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
		}

		String updateNumber = update.getUpdateNumber();
		update.setUpdateState(SrpmsProjectUpdateStatusEnums.PEROJECT_HAS_APPROVE_PASS.getCode());// 设置状态
		// 更新项目变更状态
		updateService.updateById(update);
		// 更新单据状态
		voucherEntity.setVoucherStatus(VoucherStatusEnums.APPROVED.getCode());
		voucherService.saveOrUpdate(voucherEntity);

		SrpmsProject srpmsProject = projectService.getById(update.getProjectId());
		long clobId = Long.parseLong(update.getNewValue());
		switch (update.getUpdateType()) {// 根据变更类型不一样保存数据操作
		case SrpmsConstant.MODIFY_MODIFY_TYPE_10:// 参与人员变更

			// 初始化项目变更参与人员变更
			updateMenberService.save(update, srpmsProject);

			log.info("项目变更-参与人员变更,执行完成,开始生成批复件, updateId:{}", update.getId());
			projectUpdateFileService.generateApproveFile(update);
			log.info("项目变更-参与人员变更,生成批复件完成, updateId:{}", update.getId());

			// 同步消息到MQ
			projectSyncMsgService.syncModifyJoinPerson(update);

			break;
		case SrpmsConstant.MODIFY_MODIFY_TYPE_20:// 负责人变更
			// 更新项目负责人信息
			SrpmsCommonNclob clobEntity = nclobService.getById(clobId);
			JSONArray newValue = JSONArray.parseArray(clobEntity.getContent());
			JSONObject leadPerson = newValue.getJSONObject(0);

			srpmsProject.setLeadPerson(leadPerson.toJSONString());
			srpmsProject.setLeadPersonId(leadPerson.getLong("id"));
			projectService.saveOrUpdate(srpmsProject);

			SrpmsProjectTaskInn projectTaskInn = taskInnService.getById(srpmsProject.getId());
			if (projectTaskInn != null) {
				projectTaskInn.setLeadPersonWorkTime(leadPerson.getLong("workPerYear"));
				taskInnService.updateById(projectTaskInn);
			}

			log.info("项目变更-负责人变更,执行完成,开始生成批复件, updateId:{}", update.getId());
			projectUpdateFileService.generateApproveFile(update);
			log.info("项目变更-负责人变更,生成批复件完成, updateId:{}", update.getId());

			// 同步消息到MQ
			projectSyncMsgService.syncModifyLeaderPerson(update, leadPerson);
			break;

		case SrpmsConstant.MODIFY_MODIFY_TYPE_30:// 内容变更

			projectUpdateContentService.save(srpmsProject, update);

			log.info("项目变更-内容变更,执行完成,开始生成批复件, updateId:{}", update.getId());
			projectUpdateFileService.generateApproveFile(update);
			log.info("项目变更-内容变更,生成批复件完成, updateId:{}", update.getId());
			break;

		case SrpmsConstant.MODIFY_MODIFY_TYPE_40:// 预算变更

			updateBudgetServiceImpl.save(update, srpmsProject);

			log.info("项目变更-预算变更,执行完成,开始生成批复件, updateId:{}", update.getId());
			projectUpdateFileService.generateApproveFile(update);
			log.info("项目变更-预算变更,生成批复件完成, updateId:{}", update.getId());

			// 同步消息到MQ
			SrpmsCommonNclob clobObj = nclobService.getById(clobId);
			JSONArray newValueArray = JSONArray.parseArray(clobObj.getContent());
			JSONObject obj = newValueArray.getJSONObject(0);
			projectSyncMsgService.syncModifyBudget(obj.getLong("projectId"));
			break;
		case SrpmsConstant.MODIFY_MODIFY_TYPE_50:// 状态变更

			// 更新项目状态时间
			srpmsProject.setStatus(update.getNewValue());
			projectService.saveOrUpdate(srpmsProject);

			log.info("项目变更-状态变更,执行完成,开始生成批复件, updateId:{}", update.getId());
			projectUpdateFileService.generateApproveFile(update);
			log.info("项目变更-状态变更,生成批复件完成, updateId:{}", update.getId());

			// 同步消息到MQ
			projectSyncMsgService.syncModifyProjectStatus(srpmsProject);

			if (SrpmsConstant.PROJECT_TYPE_1.equals(srpmsProject.getProjectFlag())) {// 横纵向项目的时候更新状态
				SrpmsResultNewTitle resultNewTitle = newTitleMapper.selectById(srpmsProject.getId());
				if(resultNewTitle != null) {
					resultNewTitle.setProState(SrpmsConstant.OUTLINE_PRO_STAT_40);
					newTitleMapper.updateById(resultNewTitle);
				}
			}
			break;
		default:
			log.error("项目变更类型不存在");
			throw new BaseException(SrpmsErrorType.PARAM_NULL);
		}
	}

	/**
	 * 项目变更审批拒绝service接口
	 *
	 * @param actionVO
	 */
	@Override
	public void modifyRefuseBpm(TaskNodeActionVO actionVO) {
		bpmService.auditRefuse(actionVO);
		SrpmsVoucher voucherEntity = voucherService.getById(actionVO.getObjectId());
		SrpmsProjectUpdate update = updateService.getById(voucherEntity.getProjectId());// 查询变更单据信息
		if (!SrpmsProjectUpdateStatusEnums.PEROJECT_HAS_SUBMIT.getCode().equals(update.getUpdateState())) {
			throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
		}
		update.setUpdateState(SrpmsProjectUpdateStatusEnums.PEROJECT_APPROVE_NO.getCode());// 设置状态
		// 更新项目变更状态
		updateService.updateById(update);

		voucherEntity.setRecallFlag(1);
		// 更新单据状态
		voucherEntity.setVoucherStatus(VoucherStatusEnums.REFUSED.getCode());
		voucherService.saveOrUpdate(voucherEntity);
//		voucherService.updateStatus(CommonUtil.getLongValue(actionVO.getObjectId()), VoucherStatusEnums.REFUSED);
	}

	@Override
	public void acceptApprovePassBpm(TaskNodeActionVO actionVO, DeptVo deptVo) {
		boolean hasEnd = bpmService.auditApprove(actionVO, deptVo);
		SrpmsVoucher voucherEntity = voucherService.getById(actionVO.getObjectId());
		// 审批后更新单据撤回标识
		voucherEntity.setRecallFlag(1);

		if (!hasEnd) { // 未结束，就返回
			voucherService.saveOrUpdate(voucherEntity);
			return;
		}
		SrpmsProjectAccept accept = acceptService.getById(voucherEntity.getProjectId());// 查询验收单据信息
		if (accept != null && !SrpmsProjectUpdateStatusEnums.PEROJECT_HAS_SUBMIT.getCode().equals(accept.getStatus())) {
			throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
		}

		accept.setStatus(SrpmsProjectUpdateStatusEnums.PEROJECT_HAS_APPROVE_PASS.getCode());// 设置状态
		// 更新项目变更状态
		acceptService.updateById(accept);
		// 更新单据状态
		voucherEntity.setVoucherStatus(VoucherStatusEnums.APPROVED.getCode());
		voucherService.saveOrUpdate(voucherEntity);

		// 更新项目状态
		SrpmsProject srpmsProject = projectService.getById(accept.getProjectId());
		srpmsProject.setStatus(SrpmsProjectStatusEnums.PEROJECT_ACCEPT.getCode());
		projectService.saveOrUpdate(srpmsProject);

		if (SrpmsConstant.PROJECT_TYPE_1.equals(srpmsProject.getProjectFlag())) {// 横纵向项目的时候更新状态
			SrpmsResultNewTitle resultNewTitle = newTitleMapper.selectById(srpmsProject.getId());
			if(resultNewTitle != null) {
				resultNewTitle.setProState(SrpmsConstant.OUTLINE_PRO_STAT_30);
				newTitleMapper.updateById(resultNewTitle);
			}
		}
	}

	/**
	 * 项目验收审批拒绝service接口
	 *
	 * @param actionVO
	 */
	@Override
	public void acceptRefuseBpm(TaskNodeActionVO actionVO) {
		bpmService.auditRefuse(actionVO);
		SrpmsVoucher voucherEntity = voucherService.getById(actionVO.getObjectId());
		SrpmsProjectAccept accept = acceptService.getById(voucherEntity.getProjectId());// 查询验收单据信息
		if (!SrpmsProjectUpdateStatusEnums.PEROJECT_HAS_SUBMIT.getCode().equals(accept.getStatus())) {
			throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
		}
		accept.setStatus(SrpmsProjectUpdateStatusEnums.PEROJECT_APPROVE_NO.getCode());// 设置状态
		// 更新项目变更状态
		acceptService.updateById(accept);

		voucherEntity.setRecallFlag(1);
		// 更新单据状态
		voucherEntity.setVoucherStatus(VoucherStatusEnums.REFUSED.getCode());
		voucherService.saveOrUpdate(voucherEntity);
//		voucherService.updateStatus(CommonUtil.getLongValue(actionVO.getObjectId()), VoucherStatusEnums.REFUSED);
		// 更新项目状态
		SrpmsProject srpmsProject = projectService.getById(accept.getProjectId());
		srpmsProject.setStatus(SrpmsProjectStatusEnums.PEROJECT_END.getCode());
		projectService.saveOrUpdate(srpmsProject);
	}

	@Override
	public void refuse(SrpmsProjectApprovalForm approvalForm) {
		// 查询项目
		SrpmsProject projectEntity = this.getById(approvalForm.getProjectId());
		if (!SrpmsProjectStatusEnums.PEROJECT_HAS_SUBMIT.getCode().equals(projectEntity.getStatus())) {
			throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
		}
		projectEntity.setStatus(SrpmsProjectStatusEnums.PEROJECT_NOT_SUBMIT.getCode());
		this.updateById(projectEntity);

		SrpmsProjectApproval comments = new SrpmsProjectApproval();
		comments.setProjectId(approvalForm.getProjectId());
		comments.setApprovalComments(approvalForm.getApprovalComments());
		approvalService.save(comments);
	}

	@Override
	public void refuseTask(SrpmsProjectApprovalForm approvalForm) {
		// 查询项目
		SrpmsProject projectEntity = this.getById(approvalForm.getProjectId());
		if (!SrpmsProjectStatusEnums.PEROJECT_TASK_SUBMIT.getCode().equals(projectEntity.getStatus())) {
			throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
		}
		projectEntity.setStatus(SrpmsProjectStatusEnums.PEROJECT_HAS_PUBLICITY.getCode());
		this.updateById(projectEntity);

		SrpmsProjectApproval comments = new SrpmsProjectApproval();
		comments.setProjectId(approvalForm.getProjectId());
		comments.setApprovalComments(approvalForm.getApprovalComments());
		approvalService.save(comments);
	}

	@Override
	public void redo(SrpmsProjectApprovalForm approvalForm) {
		// 查询项目
		SrpmsProject projectEntity = this.getById(approvalForm.getProjectId());
		if (!SrpmsProjectStatusEnums.PEROJECT_HAS_SUBMIT.getCode().equals(projectEntity.getStatus())) {
			throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
		}
		projectEntity.setStatus(SrpmsProjectStatusEnums.PEROJECT_NOT_SUBMIT.getCode());
		this.updateById(projectEntity);

		SrpmsProjectApproval comments = new SrpmsProjectApproval();
		comments.setProjectId(approvalForm.getProjectId());
		comments.setApprovalComments(approvalForm.getApprovalComments());
		approvalService.save(comments);
	}

	@Override
	public void redoTask(SrpmsProjectApprovalForm approvalForm) {
		// 查询项目
		SrpmsProject projectEntity = projectService.getById(approvalForm.getProjectId());
		if (projectEntity != null
				&& !SrpmsProjectStatusEnums.PEROJECT_HAS_PUBLICITY.getCode().equals(projectEntity.getStatus())) {
			throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
		}
		projectEntity.setStatus(SrpmsProjectStatusEnums.PEROJECT_HAS_APPROVE_PASS.getCode());
		projectService.updateById(projectEntity);
	}

	@Override
	public JSONArray getApproveList() {
		QueryWrapper<SrpmsProject> queryWrapper = new QueryWrapper<SrpmsProject>();
		String[] arr = "10,40".split(",");
		queryWrapper.in(SrpmsProject.STATUS, arr);
		queryWrapper.ne(SrpmsProject.DEL_FLG, '1');
		queryWrapper.orderByDesc(SrpmsProject.UPDATE_TIME);
		List<SrpmsProject> list = srpmsProjectMapper.selectList(queryWrapper);

		JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(list));

		for (int i = 0; i < jsonArray.size(); i++) {

			JSONObject jsonObject = jsonArray.getJSONObject(i);
			JSONConvert.convertJson(jsonObject);
			if (jsonObject.get("leadPerson") != null && JSONObject.isValid(jsonObject.getString("leadPerson"))) {
				jsonObject.put("applyPerson", jsonObject.getJSONObject("leadPerson").getString("name"));
			}
			if (jsonObject.get("leadDept") != null && JSONObject.isValid(jsonObject.getString("leadDept"))) {
				jsonObject.put("applyOrg", jsonObject.getJSONObject("leadDept").getString("deptName"));
			}
		}

		return jsonArray;
	}

	@Override
	public void publicProject(List<SrpmsProjectVo> expertForm) {

		String year = DateFormatUtils.format(new Date(), "yyyy");
		String header = year.substring(2);
		String pudNum = serialNoService.getNextPUDNo(header);
		for (int i = 0; i < expertForm.size(); i++) {
			SrpmsProject srpmsProject = this.getById(expertForm.get(i).getId());
			srpmsProject.setStatus(SrpmsProjectStatusEnums.PEROJECT_HAS_PUBLICITY.getCode());
			srpmsProject.setPudNum(pudNum);
			srpmsProject.setPublicTime(LocalDateTime.now());
			// 已公示copy一份预算书
			// budgetTaskService.copyBudgetAaply(srpmsProject);
			this.saveOrUpdate(srpmsProject);
		}
	}

	@Override
	public void approvalProject(List<SrpmsProjectVo> expertForm) {

		if (judgeSameProjectType(expertForm)) {
			throw new BaseException(SrpmsErrorType.SAME_PROJECT_TYPE);
		}

		String year = DateFormatUtils.format(new Date(), "yyyy");
		String header = year.substring(2);
		String apdNum = serialNoService.getNextAPDNo(header);
		for (int i = 0; i < expertForm.size(); i++) {
			SrpmsProject srpmsProject = this.getById(expertForm.get(i).getId());
			srpmsProject.setStatus(SrpmsProjectStatusEnums.PEROJECT_SET_UP.getCode());
			srpmsProject.setApdNum(apdNum);
			srpmsProject.setApproveTime(LocalDateTime.now());
			this.saveOrUpdate(srpmsProject);
		}
	}

	@Override
	public String exportPublishFile(List<String> projectIds) {
		List<ProjectDTO> proList = new ArrayList<>();
		List<SrpmsProject> projectList = srpmsProjectMapper.selectBatchIds(projectIds);
		Map<String, Object> dataMap = new HashMap<>();
		String projectCategory = null;
		Set<String> categorySet = new HashSet<>();
		for (SrpmsProject project : projectList) {
			categorySet.add(JSONObject.parseArray(project.getProjectCategory()).getString(2));
			ProjectDTO dto = new ProjectDTO();
			projectCategory = project.getProjectCategory();
			for (int j = 0; j < ProjectCategoryEnums.values().length; j++) {
				if (project.getProjectCategory().equals(ProjectCategoryEnums.values()[j].getCode())) {
					project.setProjectCategory(ProjectCategoryEnums.values()[j].getDesc());
				}
			}
			String leadDept = project.getLeadDept() == null ? ""
					: JSONObject.parseObject(project.getLeadDept()).getString("deptName");
			String leadPerson = project.getLeadPerson() == null ? ""
					: JSONObject.parseObject(project.getLeadPerson()).getString("name");
			String bothLeadPerson = project.getBothTopExpertPerson() == null ? ""
					: JSONObject.parseObject(project.getBothTopExpertPerson()).getString("name");
			project.setLeadDept(leadDept);
			project.setLeadPerson(leadPerson);
			project.setBothTopExpertPerson(bothLeadPerson);

			BeanUtils.copyProperties(project, dto);
			dto.setProjectActionDateStartStr(
					LocalDateTimeUtils.formatTimeSafe(project.getProjectActionDateStart(), "yyyy年MM月dd日"));
			dto.setProjectActionDateEndStr(
					LocalDateTimeUtils.formatTimeSafe(project.getProjectActionDateEnd(), "yyyy年MM月dd日"));
			// 校基科费-统计年度预算
			if (ProjectCategoryEnums.SCHOOL_STU.getCode().equals(projectCategory)
					|| ProjectCategoryEnums.SCHOOL_TEACH.getCode().equals(projectCategory)) {
				List<SrpmsProjectBudgetDetailVo> detailVoList = budgetDetailService.queryBudgetDetailListByCategory(
						BudgetCategoryEnums.APPLY_INNOVATE_BUDGET_YEAR, project.getId());
				double budgetAmount = 0;
				if (detailVoList != null && detailVoList.size() > 0) {
					for (SrpmsProjectBudgetDetailVo budgetDetailVo : detailVoList) {
						budgetAmount += budgetDetailVo.getBudgetAmount();
					}
				}
				dto.setApplyFound(String.valueOf(budgetAmount));
			}
			proList.add(dto);
		}

		if (categorySet.size() > 1) {
			throw new ServiceException(PlatformErrorType.ARGUMENT_NOT_VALID, "一次项目公示只能勾选同一类项目");
		}

		Date now = new Date();
		String year = DateFormatUtils.format(now, "yyyy");
		String month = DateFormatUtils.format(now, "MM");
		String day = DateFormatUtils.format(now, "dd");

		dataMap.put("proList", proList);
		dataMap.put("year", year);
		dataMap.put("month", month);
		dataMap.put("day", day);

		String templateName = null;
		if (ProjectCategoryEnums.ACADEMY.getCode().equals(projectCategory)) {
			templateName = "template_academy_publish";
		} else if (ProjectCategoryEnums.SCHOOL_TEACH.getCode().equals(projectCategory)
				|| ProjectCategoryEnums.SCHOOL_STU.getCode().equals(projectCategory)) {
			templateName = "template_academy_school_publish";
		} else if (ProjectCategoryEnums.STRUCTURAL_REFORM.getCode().equals(projectCategory)) {
			templateName = "template_reform_publish";
		} else {
			templateName = "template_apply_innovate_publicity";
		}

		String finalFileName = null;
		String fileName = "项目公示文件_" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS")
				+ RandomUtils.nextInt(0, 9999);
		try {
			finalFileName = wordExportService.exportWord(templateName, dataMap, fileName + ".docx");
		} catch (Exception e) {
			log.error("生成项目公示文件异常。", e);
			throw new ServiceException(PlatformErrorType.SYSTEM_ERROR, "生成项目公示文件异常");
		}

		File pdfFile = null;
		FileInputStream fileInputStream = null;
		List<SrpmsProject> updateProjectList = new ArrayList<>();
		try {
			String jarPath = new File(ResourceUtils.getURL("").getPath()).getAbsolutePath();
			String pdfFilePath = jarPath + "/" + fileName.replace(".docx", "") + ".pdf";
			// 生成PDF文件
			WordConvertUtil.docx2PDF(finalFileName, pdfFilePath);
			pdfFile = new File(pdfFilePath);
			fileInputStream = new FileInputStream(pdfFile);
			MultipartFile multipartFile = new MockMultipartFile("file", pdfFile.getName(),
					ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
			Result<FileInfoVo> result = fileOperatorFeignService.uploadFile(multipartFile);
			if (result.isSuccess()) {
				FileInfoVo fileInfoVo = result.getData();
				if (fileInfoVo != null) {
					for (SrpmsProject project : projectList) {
						SrpmsProject updateEntity = new SrpmsProject();
						updateEntity.setId(project.getId());
						updateEntity.setPublishBookFileId(NumberUtils.toLong(fileInfoVo.getId()));
						updateEntity.setPublishBookFileName(fileInfoVo.getFileName());
						updateEntity.setPublishBookFileUrl(fileInfoVo.getFileUrl());
						updateEntity.updateById();
					}
				}
			} else {
				log.error("申请书上传文件服务器失败。rsp:{}", JSONObject.toJSONString(result));
			}
		} catch (Exception e) {
			log.error("将公示文件转换成PDF异常.", e);
			throw new ServiceException(PlatformErrorType.SYSTEM_ERROR);
		} finally {
			IOUtils.closeQuietly(fileInputStream);
			if (pdfFile != null && pdfFile.exists()) {
				pdfFile.delete();
			}
		}
		return finalFileName;
	}

	@Override
	public String exportApproveFile(List<String> projectIds) {
		List<SrpmsProject> projectList = srpmsProjectMapper.selectBatchIds(projectIds);
		Date now = new Date();
		Integer year = DateUtils.toCalendar(now).get(Calendar.YEAR);
		double budgetAmount = 0;
		int countBcoo = 0;
		int countCoo = 0;
		int countPre = 0;
		List<ProjectApproveProjectDTO> projectDTOList = new ArrayList<>();
		Set<String> categorySet = new HashSet<>();
		String projectCategory = null;
		for (SrpmsProject project : projectList) {
			categorySet.add(JSONObject.parseArray(project.getProjectCategory()).getString(2));
			if (ProjectCategoryEnums.INNOVATE_BCOO.getCode().equals(project.getProjectCategory())) {
				countBcoo++;
			} else if (ProjectCategoryEnums.INNOVATE_COO.getCode().equals(project.getProjectCategory())) {
				countCoo++;
			} else if (ProjectCategoryEnums.INNOVATE_PRE.getCode().equals(project.getProjectCategory())) {
				countPre++;
			}

			projectCategory = project.getProjectCategory();

			for (int j = 0; j < ProjectCategoryEnums.values().length; j++) {
				if (project.getProjectCategory().equals(ProjectCategoryEnums.values()[j].getCode())) {
					project.setProjectCategory(ProjectCategoryEnums.values()[j].getDesc());
				}
			}

			String leadDept = project.getLeadDept() == null ? ""
					: JSONObject.parseObject(project.getLeadDept()).getString("deptName");
			String leadPerson = project.getLeadPerson() == null ? ""
					: JSONObject.parseObject(project.getLeadPerson()).getString("name");

			ProjectApproveProjectDTO projectDTO = new ProjectApproveProjectDTO();
			projectDTOList.add(projectDTO);

			projectDTO.setId(project.getId());
			projectDTO.setDeptName(leadDept);
			projectDTO.setPersonName(leadPerson);
			projectDTO.setProjectCategory(project.getProjectCategory());
			projectDTO.setProjectName(project.getProjectName());
			projectDTO.setProjectNum(project.getProjectNum());
			projectDTO.setLeadDeptCode(JSONObject.parseObject(project.getLeadDept()).getString("deptCode"));
			// projectDTO.setDependDeptId(project.getLeadDeptId());
			projectDTO.setBudget(0);

			// 获取项目预算金额
			// QueryWrapper<SrpmsProjectTask> queryWapper = new
			// QueryWrapper<SrpmsProjectTask>();
			// queryWapper.eq(SrpmsProjectTask.PROJECT_ID, project.getId());
			// queryWapper.eq(SrpmsProjectTask.TASK_YEAR, year);
			// queryWapper.in(SrpmsProjectTask.TASK_CATEGORY,
			// TaskCategoryEnums.APPLY_INNOVATE_TASK_DECOMPOSITION.getCode());
			//
			// SrpmsProjectTask budgetDetail = taskService.getOne(queryWapper);
			// if (budgetDetail != null){
			// projectDTO.setBudget(budgetDetail.getBudgetAmount());
			// }

			if (categorySet.size() > 1) {
				throw new ServiceException(PlatformErrorType.ARGUMENT_NOT_VALID, "一次项目批复只能勾选同一类项目");
			}

			BudgetCategoryEnums categoryEnums = BudgetCategoryEnums.TASK_INNOVATE_BUDGET_YEAR;

			if (ProjectCategoryEnums.ACADEMY.getCode().equals(projectCategory)
					|| ProjectCategoryEnums.STRUCTURAL_REFORM.getCode().equals(projectCategory)) {
				categoryEnums = BudgetCategoryEnums.TASK_YEAR_PLAN_DETAIL;
			} else if (ProjectCategoryEnums.SCHOOL_TEACH.getCode().equals(projectCategory)
					|| ProjectCategoryEnums.SCHOOL_STU.getCode().equals(projectCategory)) {
				categoryEnums = BudgetCategoryEnums.TASK_YEAR_PLAN_DETAIL;
			} else {
				categoryEnums = BudgetCategoryEnums.TASK_INNOVATE_BUDGET_YEAR;
			}

			List<SrpmsProjectBudgetDetailVo> detailVoList = budgetDetailService
					.queryBudgetDetailListByCategory(categoryEnums, project.getId());
			double budget = 0;
			if (detailVoList != null && detailVoList.size() > 0) {
				for (SrpmsProjectBudgetDetailVo budgetDetailVo : detailVoList) {
					if (budgetDetailVo.getBudgetAmount() != null) {
						budget += budgetDetailVo.getBudgetAmount();
					}
				}
			}
			projectDTO.setBudget(budget);

			// 计算总金额
			budgetAmount += projectDTO.getBudget();
		}

		// 抽取单位维度
		ImmutableListMultimap<String, ProjectApproveProjectDTO> deptMap = Multimaps.index(projectDTOList,
				new Function<ProjectApproveProjectDTO, String>() {
					@Override
					public String apply(ProjectApproveProjectDTO projectApproveProjectDTO) {
						return projectApproveProjectDTO.getLeadDeptCode();
					}
				});

		List<ProjectApproveDeptDTO> deptDTOList = new ArrayList<>();
		for (String deptCode : deptMap.keySet()) {
			ImmutableList<ProjectApproveProjectDTO> list = deptMap.get(deptCode);
			ProjectApproveDeptDTO deptDTO = new ProjectApproveDeptDTO();
			deptDTO.setId(deptCode);
			deptDTO.setLeadDeptId(deptCode);
			double budget = 0;
			for (ProjectApproveProjectDTO projectDTO : list) {
				deptDTO.setDeptName(projectDTO.getDeptName());
				budget += projectDTO.getBudget();
			}
			deptDTO.setBudget(budget);
			deptDTO.setSize(list.size());
			deptDTO.setProjectList(list);
			deptDTOList.add(deptDTO);
		}

		String templateName = null;
		if (ProjectCategoryEnums.ACADEMY.getCode().equals(projectCategory)) {
			templateName = "template_project_approve_aca_s";
		} else if (ProjectCategoryEnums.SCHOOL_TEACH.getCode().equals(projectCategory)
				|| ProjectCategoryEnums.SCHOOL_STU.getCode().equals(projectCategory)) {
			templateName = "template_project_approve_sch_s";
		} else if (ProjectCategoryEnums.STRUCTURAL_REFORM.getCode().equals(projectCategory)) {
			templateName = "template_project_approve_reform_s";
		} else {
			templateName = "template_project_approve_s";
		}

		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("projectList", projectDTOList);
		dataMap.put("deptList", deptDTOList);
		dataMap.put("year", String.valueOf(year));
		dataMap.put("now", now);
		dataMap.put("allSize", projectIds.size());
		dataMap.put("budgetAmount", budgetAmount);
		dataMap.put("countBcoo", countBcoo);
		dataMap.put("countCoo", countCoo);
		dataMap.put("countPre", countPre);

		String finalFileName = null;
		String fileName = "项目批复文件_" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS")
				+ RandomUtils.nextInt(0, 9999) + ".docx";
		try {
			finalFileName = wordExportService.exportWord(templateName, dataMap, fileName);
		} catch (Exception e) {
			log.error("生成项目批复文件异常。", e);
			throw new ServiceException(PlatformErrorType.SYSTEM_ERROR, "生成项目批复文件异常");
		}

		File pdfFile = null;
		FileInputStream fileInputStream = null;
		List<SrpmsProject> updateProjectList = new ArrayList<>();
		try {
			String jarPath = new File(ResourceUtils.getURL("").getPath()).getAbsolutePath();
			String pdfFilePath = jarPath + "/" + fileName.replace(".docx", "") + ".pdf";
			// 生成PDF文件
			WordConvertUtil.docx2PDF(finalFileName, pdfFilePath);
			pdfFile = new File(pdfFilePath);
			fileInputStream = new FileInputStream(pdfFile);
			MultipartFile multipartFile = new MockMultipartFile("file", pdfFile.getName(),
					ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
			Result<FileInfoVo> result = fileOperatorFeignService.uploadFile(multipartFile);
			if (result.isSuccess()) {
				FileInfoVo fileInfoVo = result.getData();
				if (fileInfoVo != null) {
					for (SrpmsProject project : projectList) {
						SrpmsProject updateEntity = new SrpmsProject();
						updateEntity.setId(project.getId());
						updateEntity.setApplyBookFileId(NumberUtils.toLong(fileInfoVo.getId()));
						updateEntity.setApproveBookFileName(fileInfoVo.getFileName());
						updateEntity.setApproveBookFileUrl(fileInfoVo.getFileUrl());
						updateEntity.updateById();
					}
				}
			} else {
				log.error("批复文件上传文件服务器失败。rsp：{}", JSONObject.toJSONString(result));
			}
		} catch (Exception e) {
			log.error("将批复文件转换成PDF异常.", e);
			throw new ServiceException(PlatformErrorType.SYSTEM_ERROR);
		} finally {
			IOUtils.closeQuietly(fileInputStream);
			if (pdfFile != null && pdfFile.exists()) {
				pdfFile.delete();
			}
		}

		return finalFileName;
	}

	/**
	 * 任务书批复撤回service接口实现
	 *
	 * @param projectId
	 * @return
	 */
	@Override
	public String replyRecall(String projectId) {
		// 查询项目
		SrpmsProject projectEntity = projectService.getById(projectId);
		if (projectEntity != null
				&& !SrpmsProjectStatusEnums.PEROJECT_SET_UP.getCode().equals(projectEntity.getStatus())) {
			throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
		}
		projectEntity.setStatus(SrpmsProjectStatusEnums.PEROJECT_TASK_PASS.getCode());
		projectService.updateById(projectEntity);

		return projectId;
	}

	/**
	 * 审批撤回service接口实现
	 *
	 * @param actionVO
	 * @return
	 */
	@Override
	public String approveRecall(TaskNodeActionVO actionVO) {
		actionVO = bpmService.queryBpmProcessTask(actionVO);
		if(StringUtils.isBlank(actionVO.getId())) {
			throw new BaseException(SrpmsErrorType.PROCESS_SUBMIT_NOT_DATA);
		}
		bpmService.auditRefuse(actionVO);// 终止流程
		SrpmsVoucher voucherEntity = voucherService.getById(actionVO.getObjectId());
		if(voucherEntity == null) {
			throw new BaseException(SrpmsErrorType.PROCESS_VOUCHER_DATA);
		}
		Long projectId = voucherEntity.getProjectId();
		switch (voucherEntity.getVoucherType()) {// 单据类型
			case SrpmsConstant.APPROVE_APPLY_BOOK:// 申请书审批
				SrpmsProject srpmsProject = projectService.getById(projectId);// 查询项目
				if(srpmsProject == null) {
					throw new BaseException(SrpmsErrorType.PROJECT_NOT_FOUND);
				}
				if (!SrpmsProjectStatusEnums.PEROJECT_HAS_SUBMIT.getCode().equals(srpmsProject.getStatus())) {// 验证状态
					throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
				}
				// 申请书项目状态撤回到待申报，回到草稿箱
				srpmsProject.setStatus(SrpmsProjectStatusEnums.PEROJECT_NOT_SUBMIT.getCode());
				projectService.saveOrUpdate(srpmsProject);
				break;
			case SrpmsConstant.APPROVE_TASK_BOOK:// 任务书审批
				srpmsProject = projectService.getById(projectId);// 查询项目
				if(srpmsProject == null) {
					throw new BaseException(SrpmsErrorType.PROJECT_NOT_FOUND);
				}
				if (!SrpmsProjectStatusEnums.PEROJECT_TASK_SUBMIT.getCode().equals(srpmsProject.getStatus())) {// 验证状态
					throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
				}
				// 任务书项目状态撤回到已公示
				srpmsProject.setStatus(SrpmsProjectStatusEnums.PEROJECT_HAS_PUBLICITY.getCode());
				projectService.saveOrUpdate(srpmsProject);
				break;
			case SrpmsConstant.APPROVE_TRAN_LONG_TASK_BOOK:// 横纵向项目审批
				srpmsProject = projectService.getById(projectId);// 查询项目
				if(srpmsProject == null) {
					throw new BaseException(SrpmsErrorType.PROJECT_NOT_FOUND);
				}
				if (!SrpmsProjectStatusEnums.PEROJECT_TASK_SUBMIT.getCode().equals(srpmsProject.getStatus())) {// 验证状态
					throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
				}
				// 横纵向项目状态撤回到待申报
				srpmsProject.setStatus(SrpmsProjectStatusEnums.PEROJECT_NOT_SUBMIT.getCode());
				projectService.saveOrUpdate(srpmsProject);
				break;
			case SrpmsConstant.APPROVE_BUDGET_APPLY_BOOK:// 预算申请
				SrpmsProjectBudgetYear budgetYear = budgetYearService.getById(projectId);// 查询预算信息
				if(budgetYear == null) {
					throw new BaseException(SrpmsErrorType.BUDGET_YEAR_DATA);
				}
				if (!SrpmsProjectStatusEnums.PEROJECT_TASK_SUBMIT.getCode().equals(budgetYear.getStatus())) {// 验证状态
					throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
				}
				// 预算申请状态撤回到待申报
				budgetYear.setStatus(SrpmsProjectStatusEnums.PEROJECT_NOT_SUBMIT.getCode());
				budgetYearService.saveOrUpdate(budgetYear);
				break;
			case SrpmsConstant.APPROVE_UPDATE_JOIN_BOOK:// 项目参与人员变更审批
			case SrpmsConstant.APPROVE_UPDATE_PERSON_BOOK:// 项目负责人审批
			case SrpmsConstant.APPROVE_UPDATE_CONTENT_BOOK:// 项目内容变更审批
			case SrpmsConstant.APPROVE_UPDATE_BUDGET_BOOK:// 项目预算变更审批
			case SrpmsConstant.APPROVE_UPDATE_STATE_BOOK:// 项目状态变更审批
				SrpmsProjectUpdate update = updateService.getById(projectId);// 查询变更单据信息
				if(update == null) {
					throw new BaseException(SrpmsErrorType.UPDATE_NO_DATA);
				}
				if (!SrpmsProjectUpdateStatusEnums.PEROJECT_HAS_SUBMIT.getCode().equals(update.getUpdateState())) {
					throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
				}
				// 项目变更状态撤回到未提交
				update.setUpdateState(SrpmsProjectUpdateStatusEnums.PEROJECT_NOT_SUBMIT.getCode());// 设置状态
				updateService.saveOrUpdate(update);
				break;
			case SrpmsConstant.APPROVE_ACCEPT_BOOK:// 项目验收审批
				SrpmsProjectAccept accept = acceptService.getById(projectId);// 查询验收信息
				if(accept == null) {
					throw new BaseException(SrpmsErrorType.ACCEPT_NO_DATA);
				}
				if (!SrpmsProjectUpdateStatusEnums.PEROJECT_HAS_SUBMIT.getCode().equals(accept.getStatus())) {
					throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
				}
				// 验收状态撤回到未提交
				accept.setStatus(SrpmsProjectUpdateStatusEnums.PEROJECT_NOT_SUBMIT.getCode());// 设置状态
				acceptService.saveOrUpdate(accept);
				break;
			case SrpmsConstant.APPROVE_RESULT_PAPER:// 论文成果审批
			case SrpmsConstant.APPROVE_RESULT_PATENT:// 专利成果审批
			case SrpmsConstant.APPROVE_RESULT_BOOK:// 著作成果审批
			case SrpmsConstant.APPROVE_RESULT_ACA_EXC:// 学术交流成果审批
			case SrpmsConstant.APPROVE_RESULT_AWARD:// 奖励成果审批
			case SrpmsConstant.APPROVE_RESULT_MEDICAL:// 新药证书成果审批
			case SrpmsConstant.APPROVE_RESULT_APPLIANCE:// 医疗器械成果审批
			case SrpmsConstant.APPROVE_RESULT_SOFTWARE:// 软件成果审批
				SrpmsResult result = resultService.getById(projectId);// 查询成果管理信息
				if(result == null) {
					throw new BaseException(SrpmsErrorType.RESULT_NO_DATA);
				}
				if (!SrpmsProjectUpdateStatusEnums.PEROJECT_HAS_SUBMIT.getCode().equals(result.getStatus())) {
					throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
				}
				// 成果转化状态撤回到未提交
				result.setStatus(SrpmsProjectUpdateStatusEnums.PEROJECT_NOT_SUBMIT.getCode());// 设置状态
				resultService.saveOrUpdate(result);
				break;
			case SrpmsConstant.APPROVE_RESULT_TRANS_BOOK:// 成果转化审批
				SrpmsResultTrans resultTrans = resultTransService.getById(projectId);// 查询成果转化信息
				if(resultTrans == null) {
					throw new BaseException(SrpmsErrorType.RESULT_TRANS_NO_DATA);
				}
				if (!SrpmsProjectUpdateStatusEnums.PEROJECT_HAS_SUBMIT.getCode().equals(resultTrans.getStatus())) {
					throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
				}
				// 成果转化状态撤回到未提交
				resultTrans.setStatus(SrpmsProjectUpdateStatusEnums.PEROJECT_NOT_SUBMIT.getCode());// 设置状态
				resultTransService.saveOrUpdate(resultTrans);
				break;
			case SrpmsConstant.APPROVE_MPR_EVA_A:// 中期绩效报告审批A
				MprEvaBaseInfo baseInfo = baseInfoService.getById(projectId);
				if (!EnumMprProcessStatus.MPR_AUDIT.getCode().equals(baseInfo.getProcessStatus())) {
					throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
				}
				// 状态撤回到未提交
				baseInfo.setProcessStatus(EnumMprProcessStatus.MPR_NOT_SUBMIT.getCode());
				baseInfoService.saveOrUpdate(baseInfo);
				break;
			case SrpmsConstant.APPROVE_MPR_EVA_B:// 中期绩效报告审批B
				MprUnitEvaInfo unitEvaInfo = mprUnitEvaInfoService.getById(voucherEntity.getBizNumber());
				if (!EnumMprProcessStatus.MPR_AUDIT.getCode().equals(unitEvaInfo.getProcessStatus())) {
					throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
				}
				// 状态撤回到未提交
				unitEvaInfo.setProcessStatus(EnumMprProcessStatus.MPR_NOT_SUBMIT.getCode());
				mprUnitEvaInfoService.saveOrUpdate(unitEvaInfo);
				break;
			default:// 进度报告相关操作
				QueryWrapper<MprEvaBaseInfo> queryWrapper = new QueryWrapper <>();
				queryWrapper.eq(MprEvaBaseInfo.REPORT_ID, projectId);
				baseInfo = baseInfoService.getOne(queryWrapper);
				if (!EnumMprProcessStatus.MPR_AUDIT.getCode().equals(baseInfo.getProcessStatus())) {
					throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
				}
				// 状态撤回到未提交
				baseInfo.setProcessStatus(EnumMprProcessStatus.MPR_NOT_SUBMIT.getCode());
				baseInfoService.update(baseInfo, queryWrapper);
				break;
		}

		// 更新单据状态
		voucherService.updateStatus(Long.parseLong(actionVO.getObjectId()), VoucherStatusEnums.RECALL);
		return projectId.toString();
	}

    /**
     * @title approveReject
     * @description 审批驳回service接口实现
     * @auther Mr.Carlos
     * @date 2019.06.03 10:02
     * @param actionVO
     * @return
     */
	@Override
	public String approveReject(TaskNodeActionVO actionVO) {
        SrpmsVoucher voucherEntity = voucherService.getById(actionVO.getObjectId());
        if(voucherEntity == null) {
            throw new BaseException(SrpmsErrorType.PROCESS_VOUCHER_DATA);
        }
        bpmService.auditRejectToFirst(actionVO, voucherEntity);// 驳回到发起人
		Long projectId = voucherEntity.getProjectId();
		switch (voucherEntity.getVoucherType()) {// 单据类型
			case SrpmsConstant.APPROVE_APPLY_BOOK:// 申请书审批
				SrpmsProject srpmsProject = projectService.getById(projectId);// 查询项目
				if(srpmsProject == null) {
					throw new BaseException(SrpmsErrorType.PROJECT_NOT_FOUND);
				}
				if (!SrpmsProjectStatusEnums.PEROJECT_HAS_SUBMIT.getCode().equals(srpmsProject.getStatus())) {// 验证状态
					throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
				}
				// 申请书项目状态驳回到待申报，回到草稿箱
				srpmsProject.setStatus(SrpmsProjectStatusEnums.PEROJECT_NOT_SUBMIT.getCode());
				projectService.saveOrUpdate(srpmsProject);
				break;
			case SrpmsConstant.APPROVE_TASK_BOOK:// 任务书审批
				srpmsProject = projectService.getById(projectId);// 查询项目
				if(srpmsProject == null) {
					throw new BaseException(SrpmsErrorType.PROJECT_NOT_FOUND);
				}
				if (!SrpmsProjectStatusEnums.PEROJECT_TASK_SUBMIT.getCode().equals(srpmsProject.getStatus())) {// 验证状态
					throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
				}
				// 任务书项目状态驳回到已公示
				srpmsProject.setStatus(SrpmsProjectStatusEnums.PEROJECT_HAS_PUBLICITY.getCode());
				projectService.saveOrUpdate(srpmsProject);
				break;
			case SrpmsConstant.APPROVE_TRAN_LONG_TASK_BOOK:// 横纵向项目审批
				srpmsProject = projectService.getById(projectId);// 查询项目
				if(srpmsProject == null) {
					throw new BaseException(SrpmsErrorType.PROJECT_NOT_FOUND);
				}
				if (!SrpmsProjectStatusEnums.PEROJECT_TASK_SUBMIT.getCode().equals(srpmsProject.getStatus())) {// 验证状态
					throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
				}
				// 横纵向项目状态驳回到待申报
				srpmsProject.setStatus(SrpmsProjectStatusEnums.PEROJECT_NOT_SUBMIT.getCode());
				projectService.saveOrUpdate(srpmsProject);
				break;
			case SrpmsConstant.APPROVE_BUDGET_APPLY_BOOK:// 预算申请
				SrpmsProjectBudgetYear budgetYear = budgetYearService.getById(projectId);// 查询预算信息
				if(budgetYear == null) {
					throw new BaseException(SrpmsErrorType.BUDGET_YEAR_DATA);
				}
				if (!SrpmsProjectStatusEnums.PEROJECT_TASK_SUBMIT.getCode().equals(budgetYear.getStatus())) {// 验证状态
					throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
				}
				// 预算申请状态驳回到待申报
				budgetYear.setStatus(SrpmsProjectStatusEnums.PEROJECT_NOT_SUBMIT.getCode());
				budgetYearService.saveOrUpdate(budgetYear);
				break;
			case SrpmsConstant.APPROVE_UPDATE_JOIN_BOOK:// 项目参与人员变更审批
			case SrpmsConstant.APPROVE_UPDATE_PERSON_BOOK:// 项目负责人审批
			case SrpmsConstant.APPROVE_UPDATE_CONTENT_BOOK:// 项目内容变更审批
			case SrpmsConstant.APPROVE_UPDATE_BUDGET_BOOK:// 项目预算变更审批
			case SrpmsConstant.APPROVE_UPDATE_STATE_BOOK:// 项目状态变更审批
				SrpmsProjectUpdate update = updateService.getById(projectId);// 查询变更单据信息
				if(update == null) {
					throw new BaseException(SrpmsErrorType.UPDATE_NO_DATA);
				}
				if (!SrpmsProjectUpdateStatusEnums.PEROJECT_HAS_SUBMIT.getCode().equals(update.getUpdateState())) {
					throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
				}
				// 项目变更驳回到未提交
				update.setUpdateState(SrpmsProjectUpdateStatusEnums.PEROJECT_NOT_SUBMIT.getCode());// 设置状态
				updateService.saveOrUpdate(update);
				break;
			case SrpmsConstant.APPROVE_ACCEPT_BOOK:// 项目验收审批
				SrpmsProjectAccept accept = acceptService.getById(projectId);// 查询验收信息
				if(accept == null) {
					throw new BaseException(SrpmsErrorType.ACCEPT_NO_DATA);
				}
				if (!SrpmsProjectUpdateStatusEnums.PEROJECT_HAS_SUBMIT.getCode().equals(accept.getStatus())) {
					throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
				}
				// 验收状态驳回到未提交
				accept.setStatus(SrpmsProjectUpdateStatusEnums.PEROJECT_NOT_SUBMIT.getCode());// 设置状态
				acceptService.saveOrUpdate(accept);
				break;
			case SrpmsConstant.APPROVE_RESULT_PAPER:// 论文成果审批
			case SrpmsConstant.APPROVE_RESULT_PATENT:// 专利成果审批
			case SrpmsConstant.APPROVE_RESULT_BOOK:// 著作成果审批
			case SrpmsConstant.APPROVE_RESULT_ACA_EXC:// 学术交流成果审批
			case SrpmsConstant.APPROVE_RESULT_AWARD:// 奖励成果审批
			case SrpmsConstant.APPROVE_RESULT_MEDICAL:// 新药证书成果审批
			case SrpmsConstant.APPROVE_RESULT_APPLIANCE:// 医疗器械成果审批
			case SrpmsConstant.APPROVE_RESULT_SOFTWARE:// 软件成果审批
				SrpmsResult result = resultService.getById(projectId);// 查询成果管理信息
				if(result == null) {
					throw new BaseException(SrpmsErrorType.RESULT_NO_DATA);
				}
				if (!SrpmsProjectUpdateStatusEnums.PEROJECT_HAS_SUBMIT.getCode().equals(result.getStatus())) {
					throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
				}
				// 成果转化状态驳回到未提交
				result.setStatus(SrpmsProjectUpdateStatusEnums.PEROJECT_NOT_SUBMIT.getCode());// 设置状态
				resultService.saveOrUpdate(result);
				break;
			case SrpmsConstant.APPROVE_RESULT_TRANS_BOOK:// 成果转化审批
				SrpmsResultTrans resultTrans = resultTransService.getById(projectId);// 查询成果转化信息
				if(resultTrans == null) {
					throw new BaseException(SrpmsErrorType.RESULT_TRANS_NO_DATA);
				}
				if (!SrpmsProjectUpdateStatusEnums.PEROJECT_HAS_SUBMIT.getCode().equals(resultTrans.getStatus())) {
					throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
				}
				// 成果转化状态驳回到未提交
				resultTrans.setStatus(SrpmsProjectUpdateStatusEnums.PEROJECT_NOT_SUBMIT.getCode());// 设置状态
				resultTransService.saveOrUpdate(resultTrans);
				break;
			case SrpmsConstant.APPROVE_MPR_EVA_A:// 中期绩效报告审批A
				MprEvaBaseInfo baseInfo = baseInfoService.getById(projectId);
				if (!EnumMprProcessStatus.MPR_AUDIT.getCode().equals(baseInfo.getProcessStatus())) {
					throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
				}
				// 更新状态驳回到未提交
				baseInfo.setProcessStatus(EnumMprProcessStatus.MPR_NOT_SUBMIT.getCode());
				baseInfoService.saveOrUpdate(baseInfo);
				break;
			case SrpmsConstant.APPROVE_MPR_EVA_B:// 中期绩效报告审批B
				MprUnitEvaInfo unitEvaInfo = mprUnitEvaInfoService.getById(voucherEntity.getBizNumber());
				if (!EnumMprProcessStatus.MPR_AUDIT.getCode().equals(unitEvaInfo.getProcessStatus())) {
					throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
				}
				// 更新状态驳回到未提交
				unitEvaInfo.setProcessStatus(EnumMprProcessStatus.MPR_NOT_SUBMIT.getCode());
				mprUnitEvaInfoService.saveOrUpdate(unitEvaInfo);
				break;
			default:// 进度报告相关操作
				QueryWrapper<MprEvaBaseInfo> queryWrapper = new QueryWrapper <>();
				queryWrapper.eq(MprEvaBaseInfo.REPORT_ID, projectId);
				baseInfo = baseInfoService.getOne(queryWrapper);
				if (!EnumMprProcessStatus.MPR_AUDIT.getCode().equals(baseInfo.getProcessStatus())) {
					throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
				}
				// 更新状态驳回到未提交
				baseInfo.setProcessStatus(EnumMprProcessStatus.MPR_NOT_SUBMIT.getCode());
				baseInfoService.update(baseInfo, queryWrapper);
				break;
		}

		// 修改单据
		voucherEntity.setRecallFlag(1);
		voucherEntity.setVoucherStatus(VoucherStatusEnums.REJECT.getCode());
		voucherService.saveOrUpdate(voucherEntity);
		return projectId.toString();
	}

    /**
     * @title acceptClose
     * @description 关闭项目申请操作service接口实现
     * @auther Mr.Carlos
     * @date 2019.06.03 16:42
     * @param actionVO
     * @return
     */
	@Override
	public String approveClose(TaskNodeActionVO actionVO) {
		actionVO = bpmService.queryBpmProcessTask(actionVO);
		if(StringUtils.isBlank(actionVO.getId())) {
			throw new BaseException(SrpmsErrorType.PROCESS_SUBMIT_NOT_DATA);
		}
        SrpmsVoucher voucherEntity = voucherService.getById(actionVO.getObjectId());
        if(voucherEntity == null) {
			throw new BaseException(SrpmsErrorType.PROCESS_VOUCHER_DATA);
		}
		bpmService.auditRefuse(actionVO);// 终止流程

		Long projectId = voucherEntity.getProjectId();
		switch (voucherEntity.getVoucherType()) {// 单据类型
			case SrpmsConstant.APPROVE_APPLY_BOOK:// 申请书审批
			case SrpmsConstant.APPROVE_TRAN_LONG_TASK_BOOK:// 横纵向项目审批
				SrpmsProject srpmsProject = projectService.getById(projectId);// 查询项目
				if(srpmsProject == null) {
					throw new BaseException(SrpmsErrorType.PROJECT_NOT_FOUND);
				}
				// 删除项目
				srpmsProject.setDelFlg("1");
				projectService.updateById(srpmsProject);
				break;
			case SrpmsConstant.APPROVE_BUDGET_APPLY_BOOK:// 预算申请
				SrpmsProjectBudgetYear budgetYear = budgetYearService.getById(projectId);// 查询预算信息
				if(budgetYear == null) {
					throw new BaseException(SrpmsErrorType.BUDGET_YEAR_DATA);
				}
				// 删除项目
				budgetYear.setDelFlg("1");
				budgetYearService.updateById(budgetYear);
				break;
			case SrpmsConstant.APPROVE_UPDATE_JOIN_BOOK:// 项目参与人员变更审批
			case SrpmsConstant.APPROVE_UPDATE_PERSON_BOOK:// 项目负责人审批
			case SrpmsConstant.APPROVE_UPDATE_CONTENT_BOOK:// 项目内容变更审批
			case SrpmsConstant.APPROVE_UPDATE_BUDGET_BOOK:// 项目预算变更审批
			case SrpmsConstant.APPROVE_UPDATE_STATE_BOOK:// 项目状态变更审批
				SrpmsProjectUpdate update = updateService.getById(projectId);// 查询变更单据信息
				if(update == null) {
					throw new BaseException(SrpmsErrorType.UPDATE_NO_DATA);
				}
				// 项目变更状态更新为已关闭
				update.setUpdateState(SrpmsProjectUpdateStatusEnums.PEROJECT_APPROVE_CLOSE.getCode());// 设置状态
				updateService.saveOrUpdate(update);
				break;
			case SrpmsConstant.APPROVE_ACCEPT_BOOK:// 项目验收审批
				SrpmsProjectAccept accept = acceptService.getById(projectId);// 查询验收信息
				if(accept == null) {
					throw new BaseException(SrpmsErrorType.ACCEPT_NO_DATA);
				}
				// 验收状态更新为已关闭
				accept.setStatus(SrpmsProjectUpdateStatusEnums.PEROJECT_APPROVE_CLOSE.getCode());// 设置状态
				acceptService.saveOrUpdate(accept);
				break;
			case SrpmsConstant.APPROVE_RESULT_PAPER:// 论文成果审批
			case SrpmsConstant.APPROVE_RESULT_PATENT:// 专利成果审批
			case SrpmsConstant.APPROVE_RESULT_BOOK:// 著作成果审批
			case SrpmsConstant.APPROVE_RESULT_ACA_EXC:// 学术交流成果审批
			case SrpmsConstant.APPROVE_RESULT_AWARD:// 奖励成果审批
			case SrpmsConstant.APPROVE_RESULT_MEDICAL:// 新药证书成果审批
			case SrpmsConstant.APPROVE_RESULT_APPLIANCE:// 医疗器械成果审批
			case SrpmsConstant.APPROVE_RESULT_SOFTWARE:// 软件成果审批
				SrpmsResult result = resultService.getById(projectId);// 查询成果管理信息
				if(result == null) {
					throw new BaseException(SrpmsErrorType.RESULT_NO_DATA);
				}
				// 成果转化状态更新为已关闭
				result.setStatus(SrpmsProjectUpdateStatusEnums.PEROJECT_APPROVE_CLOSE.getCode());// 设置状态
				resultService.saveOrUpdate(result);
				break;
			case SrpmsConstant.APPROVE_RESULT_TRANS_BOOK:// 成果转化审批
				SrpmsResultTrans resultTrans = resultTransService.getById(projectId);// 查询成果转化信息
				if(resultTrans == null) {
					throw new BaseException(SrpmsErrorType.RESULT_TRANS_NO_DATA);
				}
				// 成果转化状态更新为已关闭
				resultTrans.setStatus(SrpmsProjectUpdateStatusEnums.PEROJECT_APPROVE_CLOSE.getCode());// 设置状态
				resultTransService.saveOrUpdate(resultTrans);
				break;
			case SrpmsConstant.APPROVE_MPR_EVA_A:// 中期绩效报告审批A
				MprEvaBaseInfo baseInfo = baseInfoService.getById(projectId);
				if (!EnumMprProcessStatus.MPR_AUDIT.getCode().equals(baseInfo.getProcessStatus())) {
					throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
				}
				// 更新状态已关闭
				baseInfo.setProcessStatus(EnumMprProcessStatus.MPR_CLOSE.getCode());
				baseInfoService.saveOrUpdate(baseInfo);
				break;
			case SrpmsConstant.APPROVE_MPR_EVA_B:// 中期绩效报告审批B
				MprUnitEvaInfo unitEvaInfo = mprUnitEvaInfoService.getById(voucherEntity.getBizNumber());
				if (!EnumMprProcessStatus.MPR_AUDIT.getCode().equals(unitEvaInfo.getProcessStatus())) {
					throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
				}
				// 更新状态已关闭
				unitEvaInfo.setProcessStatus(EnumMprProcessStatus.MPR_CLOSE.getCode());
				mprUnitEvaInfoService.saveOrUpdate(unitEvaInfo);
				break;
			default:// 进度报告相关操作
				QueryWrapper<MprEvaBaseInfo> queryWrapper = new QueryWrapper <>();
				queryWrapper.eq(MprEvaBaseInfo.REPORT_ID, projectId);
				baseInfo = baseInfoService.getOne(queryWrapper);
				// 更新状态已关闭
				baseInfo.setProcessStatus(EnumMprProcessStatus.MPR_CLOSE.getCode());
				baseInfoService.update(baseInfo, queryWrapper);
				break;
		}

		// 修改单据
		voucherEntity.setRecallFlag(1);
		voucherEntity.setVoucherStatus(VoucherStatusEnums.CLOSE.getCode());
		voucherService.saveOrUpdate(voucherEntity);

		return projectId.toString();
	}

	/**
	 * 判读公示批复选择的项目是否是同一项目类型
	 *
	 * @param projectVoList
	 * @return
	 */
	public boolean judgeSameProjectType(List<SrpmsProjectVo> projectVoList) {
		List<String> projectIds = new ArrayList<>();
		SrpmsProjectVo srpmsProjectVo;
		for (Iterator e = projectVoList.iterator(); e.hasNext();) {
			srpmsProjectVo = (SrpmsProjectVo) e.next();
			projectIds.add(CommonUtil.objectToString(srpmsProjectVo.getId()));
		}
		List<SrpmsProject> projectList = srpmsProjectMapper.selectBatchIds(projectIds);
		if (projectList != null && projectList.size() > 0) {
			SrpmsProject srpmsProject;
			int firstIndex = 0;
			int secondIndex = 0;
			int threeIndex = 0;
			int fourIndex = 0;
			for (Iterator e = projectList.iterator(); e.hasNext();) {
				srpmsProject = (SrpmsProject) e.next();
				switch (srpmsProject.getProjectType()) {
				case SrpmsConstant.MODIFY_PRO_TYPE_10010101:// 创新工程-重大协同创新
				case SrpmsConstant.MODIFY_PRO_TYPE_10010102:// 创新工程-协同创新团队
				case SrpmsConstant.MODIFY_PRO_TYPE_10010103:// 创新工程-先导专项
					firstIndex = 1;
					break;
				case SrpmsConstant.MODIFY_PRO_TYPE_10010201:// 院基科费
					threeIndex = 1;
					break;
				case SrpmsConstant.MODIFY_PRO_TYPE_10010401:// 改革经费
					secondIndex = 1;
					break;
				case SrpmsConstant.MODIFY_PRO_TYPE_10010301:// 校基科费-青年教师
				case SrpmsConstant.MODIFY_PRO_TYPE_10010302:// 校基科费-学生项目
					fourIndex = 1;
				default:
					break;
				}
			}
			if (firstIndex + secondIndex + threeIndex + fourIndex != 1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @title againSubmit
	 * @description 重新提交操作数据service接口实现
	 * @auther Mr.Carlos
	 * @date 2019.06.05 10:03
	 * @param voucher
	 * @param deptVo
	 */
	@Override
	public void againSubmit(SrpmsVoucher voucher, DeptVo deptVo, boolean stopFlag) {

		// 存在已驳回数据，查询审批节点数据
		TaskNodeActionVO actionVO = new TaskNodeActionVO();
		actionVO.setObjectId(CommonUtil.objectToString(voucher.getId()));
		actionVO = bpmService.queryBpmProcessTask(actionVO);
		if(StringUtils.isBlank(actionVO.getId())) {
			throw new BaseException(SrpmsErrorType.PROCESS_SUBMIT_NOT_DATA);
		}
		if(stopFlag) {// 删除项目终止流程
			bpmService.auditRefuse(actionVO);// 终止流程
			// 修改单据
			voucher.setRecallFlag(1);
			voucher.setVoucherStatus(VoucherStatusEnums.CLOSE.getCode());
			voucherService.saveOrUpdate(voucher);
		} else {// 重新提交
			actionVO = bpmService.queryBpmProcessAgainSubmitTask(actionVO);// 查询流程待重新提交的数据

			actionVO.setOpinion("请审批");
			bpmService.auditApprove(actionVO, deptVo);// 调用已通过的流程

			// 修改单据
			voucher.setRecallFlag(0);
			voucher.setVoucherStatus(VoucherStatusEnums.AUDITING.getCode());
			voucherService.saveOrUpdate(voucher);
		}
	}
}
