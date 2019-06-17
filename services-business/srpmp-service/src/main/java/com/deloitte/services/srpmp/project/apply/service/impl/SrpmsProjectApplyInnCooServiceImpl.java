package com.deloitte.services.srpmp.project.apply.service.impl;

import static com.deloitte.services.srpmp.common.util.HTMLParseUtil.extractCellFromTable;
import static com.deloitte.services.srpmp.common.util.HTMLParseUtil.extractListFromTable;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.entity.ContentType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fileservice.feign.FileOperatorFeignService;
import com.deloitte.platform.api.fileservice.vo.FileInfoVo;
import com.deloitte.platform.api.isump.feign.DictFeignService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyInnCooSaveVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectDeptJoinVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonJoinVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectTaskVo;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetDetailVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.srpmp.common.enums.BudgetCategoryEnums;
import com.deloitte.services.srpmp.common.enums.DeptJoinWayEnums;
import com.deloitte.services.srpmp.common.enums.PersonJoinWayEnums;
import com.deloitte.services.srpmp.common.enums.ProjectCategoryEnums;
import com.deloitte.services.srpmp.common.enums.SrpmsProjectStatusEnums;
import com.deloitte.services.srpmp.common.enums.SysDictEnums;
import com.deloitte.services.srpmp.common.enums.TaskCategoryEnums;
import com.deloitte.services.srpmp.common.enums.VoucherTypeEnums;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.common.service.ISrpmsCommonNclobService;
import com.deloitte.services.srpmp.common.service.ISysDictService;
import com.deloitte.services.srpmp.common.service.impl.WordExportServiceImpl;
import com.deloitte.services.srpmp.common.util.JSONConvert;
import com.deloitte.services.srpmp.common.util.StringConvertUtil;
import com.deloitte.services.srpmp.common.util.WordConvertUtil;
import com.deloitte.services.srpmp.common.util.WordImportUtil;
import com.deloitte.services.srpmp.outline.util.DateUtil;
import com.deloitte.services.srpmp.project.apply.entity.SrpmsProjectApplyInnCoo;
import com.deloitte.services.srpmp.project.apply.mapper.SrpmsProjectApplyInnCooMapper;
import com.deloitte.services.srpmp.project.apply.service.ISrpmsProjectApplyInnCooService;
import com.deloitte.services.srpmp.project.apply.util.LocalDateUtils;
import com.deloitte.services.srpmp.project.apply.util.ProjectCheckUtils;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectAttachmentService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectBudgetDetailService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectDeptJoinService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectFlowService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectPersonJoinService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectTaskService;
import com.deloitte.services.srpmp.project.base.service.impl.PdfMergeServiceImpl;
import com.deloitte.services.srpmp.project.budget.service.ISrpmsProjectBudgetApplyService;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author : pengchao
 * @Date : Create in 2019-03-04
 * @Description : SrpmsProjectApplyInnCoo服务实现类
 * @Modified :
 */
@Service
@Slf4j
@Transactional
public class SrpmsProjectApplyInnCooServiceImpl extends
		ServiceImpl<SrpmsProjectApplyInnCooMapper, SrpmsProjectApplyInnCoo> implements ISrpmsProjectApplyInnCooService {

	@Autowired
	private ISrpmsProjectPersonJoinService srpmsProjectPersonJoinServiceImpl;

	@Autowired
	private ISrpmsProjectBudgetApplyService budgetApplyService;

	@Autowired
	private ISrpmsProjectDeptJoinService srpmsProjectDeptJoinServiceImpl;

	@Autowired
	private ISrpmsProjectTaskService taskService;

	@Autowired
	private ISrpmsProjectService projectService;

	@Autowired
	SrpmsProjectApplyInnCommonServiceImpl commonService;

	@Autowired
	private WordExportServiceImpl wordExportService;

	@Autowired
	private ISysDictService sysDictService;

	@Autowired
	private FileOperatorFeignService fileOperatorFeignService;

	@Autowired
	private ISrpmsProjectBudgetDetailService budgetDetailService;

	@Autowired
	private DictFeignService dictFeignService;

	@Autowired
	private ISrpmsProjectFlowService flowServicel;

	@Autowired
	private PdfMergeServiceImpl pdfMergeService;

	@Autowired
	private ISrpmsProjectAttachmentService attachmentService;

	@Autowired
	private ISrpmsCommonNclobService commonNclobService;

	@Override
	public long saveOrUpdate(SrpmsProjectApplyInnCooSaveVo pageData, DeptVo deptVo) {

		// 项目ID
		long projectId = 0l;
		// 重大协同项目信息
		JSONObject projectJson = JSONObject.parseObject(JSONObject.toJSONString(pageData));
		SrpmsProjectApplyInnCoo projectEntity = JSONObject.parseObject(projectJson.toJSONString(),
				SrpmsProjectApplyInnCoo.class);
		if (pageData.getId() == null) {

			projectId = commonService.insertCommonInfo(projectJson, ProjectCategoryEnums.INNOVATE_COO, deptVo);
			projectEntity.setId(projectId);
			this.save(projectEntity);

		} else {
			this.saveOrUpdate(projectEntity);
			projectId = commonService.saveOrUpdateCommonInfo(projectJson, ProjectCategoryEnums.INNOVATE_COO);
		}

		// 项目首席专家及研究骨干目前承担其它相关国家科技计划课题情况
		srpmsProjectPersonJoinServiceImpl.cleanAndSavePersonJoin(pageData.getTopMemberOtherTaskList(),
				PersonJoinWayEnums.APPLY_OTHER_PROJECT, projectId);
		// 联合申报单位任务分工情况
		taskService.cleanAndSaveTask(pageData.getJointUnitTask(), TaskCategoryEnums.APPLY_INNOVATE_TASK_JOINT_UNIT,
				projectId);
		// 项目任务分解情况和各任务之间的逻辑关系图示
		commonService.setTaskAndBudgetList(projectJson.getJSONArray("taskDecompositionList"), projectId);
		return projectId;
	}

	@Override
	public void submit(SrpmsProjectApplyInnCooSaveVo pageData, UserVo user, DeptVo deptVo) {

		if (pageData.getId() == null) {
			throw new BaseException(SrpmsErrorType.BUDGET_NOT_COMPLETE);
		}
		// 校验预算书
		long id = pageData.getId();

		// 校验申请书状态，只有未提交的申请书才能提交
		SrpmsProject project = projectService.getById(id);
		if (!SrpmsProjectStatusEnums.PEROJECT_NOT_SUBMIT.getCode().equals(project.getStatus())) {
			throw new BaseException(SrpmsErrorType.APPLY_HAVE_SUBMITTED);
		}

		// 保存申请书
		this.saveOrUpdate(pageData, deptVo);

		JSONObject budgetJson = budgetApplyService.queryBudgetApplyById(id);
		JSONObject applyJson = JSONObject.parseObject(JSON.toJSONString(pageData));
		ProjectCheckUtils.checkApply(project, applyJson, budgetJson);

		// 更新项目状态
		projectService.submitProject(id);

		commonService.copyApplyToTaskInnCoo(pageData, budgetJson);

		// 生成申请书PDF
		this.generateApplyBookPdf(id, user, deptVo);

		// 生成预算书PDF
		budgetApplyService.generateBudgetApplyPdf(id, user, deptVo);

		log.info("协同创新，提交申请书，已更新项目状态, projectId:{}", id);
		// 发起流程
		flowServicel.startAuditProcess(id, VoucherTypeEnums.APPLY_BOOK, user, deptVo);

		log.info("协同创新，提交申请书，已发起流程, projectId{}", id);
	}

	@Override
	public JSONObject get(long projectId, UserVo user, DeptVo dept) {

		log.info("项目ID是" + projectId);
		log.info("登陆用户是" + user);
		log.info("登陆用户单位是" + dept);
		if (projectId == 0) {
			dept.setBankAccountNameBase(null);
			dept.setBankAccountNameLoose(null);
			dept.setBankAccountNumberBase(null);
			dept.setBankAccountNumberLoose(null);
			dept.setBankNameBase(null);
			dept.setBankNameLoose(null);
			JSONObject relust = new JSONObject();
			user.setWorkPerYear(null);
			relust.put("leadPerson", user);
			relust.put("leadDept", dept);
			JSONConvert.convertJson(relust);
			return relust;
		}

		String jsonStr = commonNclobService.getApplyVo(projectId);
		if (jsonStr != null && jsonStr.length() != 0) {
			return JSONObject.parseObject(jsonStr);
		}

		// 附件
		JSONObject relust = JSONObject.parseObject(JSONObject.toJSONString(projectService.getById(projectId)));

		relust.putAll(JSONObject.parseObject(JSON.toJSONString(this.getById(projectId))));

		// 国家合作单位信息
		List<SrpmsProjectDeptJoinVo> list3 = srpmsProjectDeptJoinServiceImpl
				.queryDeptJoinListByJoinWay(DeptJoinWayEnums.APPLY_INNOVATE_COPPDEPT, projectId);
		if (list3 != null && list3.size() != 0) {
			relust.put("coopDeptList", list3);
		}

		// 项目的年度任务、考核指标和时间节点
		List<SrpmsProjectTaskVo> list = taskService
				.queryTaskListByTaskCategory(TaskCategoryEnums.APPLY_INNOVATE_TASK_YEAR, projectId);
		if (list != null && list.size() != 0) {
			relust.put("taskPreYearList", list);
		}

		// 年度预算
		List<SrpmsProjectBudgetDetailVo> list2 = budgetDetailService
				.queryBudgetDetailListByCategory(BudgetCategoryEnums.APPLY_INNOVATE_BUDGET_YEAR, projectId);
		if (list2 != null && list2.size() != 0) {
			relust.put("budgetPreYearList", JSONArray.parseArray(JSON.toJSONString(list2)));
		}

		boolean hasSubTask = false;
		// 项目任务分解情况和各任务之间的逻辑关系图示
		List<SrpmsProjectTaskVo> list5 = taskService
				.queryTaskListByTaskCategory(TaskCategoryEnums.APPLY_INNOVATE_TASK_DECOMPOSITION, projectId);
		if (list5 != null && list5.size() != 0) {
			hasSubTask = true;
			relust.put("taskDecompositionList", list5);
		}

		// 主要参与人员
		List<SrpmsProjectPersonJoinVo> list4 = srpmsProjectPersonJoinServiceImpl
				.queryPersonJoinListByJoinWay(PersonJoinWayEnums.APPLY_MAIN_MEMBER, projectId);
		if (list4 != null && list4.size() != 0) {
			for (int i = 0; i < list4.size(); i++) {
				SrpmsProjectPersonJoinVo mianMember = list4.get(i);
				if (hasSubTask) {
					for (int j = 0; j < list5.size(); j++) {
						if (mianMember.getPersonId().equals(list5.get(j).getLeadPerson())) {
							mianMember.setPersonRole("是");
							break;
						} else {
							mianMember.setPersonRole("否");
						}
					}
				} else {
					mianMember.setPersonRole("否");
				}
			}
			relust.put("mainMemberList", list4);
		}

		// 总预算表
		relust.put("budgetAllList",
				taskService.queryTaskListByTaskCategory(TaskCategoryEnums.APPLY_INNOVATE_BUDGET_ALL, projectId));

		// 项目首席专家及研究骨干目前承担其它相关国家科技计划课题情况
		relust.put("topMemberOtherTaskList", srpmsProjectPersonJoinServiceImpl
				.queryPersonJoinListByJoinWay(PersonJoinWayEnums.APPLY_OTHER_PROJECT, projectId));

		// 项目任务分解情况和各任务之间的逻辑关系图示
		relust.put("jointUnitTask",
				taskService.queryTaskListByTaskCategory(TaskCategoryEnums.APPLY_INNOVATE_TASK_JOINT_UNIT, projectId));

		relust.put("subjectCategory", relust.getString("subjectCategory") == null ? ""
				: JSONArray.parseArray(relust.getString("subjectCategory")));

		// 附件
		relust.putAll(attachmentService.queryAttachmentListApply(projectId));
		JSONConvert.convertJson(relust);
		return relust;
	}

	@Override
	public String exportWordFile(Long projectId, UserVo user, DeptVo dept) {
		String fileUrl = null;
		try {
			if (NumberUtils.LONG_ZERO == projectId) {
				Map dataMap = new HashMap();
				JSONObject dataJson = new JSONObject();
				dataJson.put("applyYear", StringConvertUtil.convertNumToWord(LocalDateTimeUtils.formatNow("yyyy")));
				dataJson.put("leadDept", dept);
				JSONObject leadPersonJson = (JSONObject) JSON.toJSON(user);
				leadPersonJson.put("birthDate",
						LocalDateUtils.parse(leadPersonJson.getString("birthDate"), LocalDateUtils.PARRERN_Y_M));
				dataJson.put("leadPerson", leadPersonJson);
				dataMap.put("data", dataJson);
				String filename = "协同创新申请书_" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS")
						+ RandomUtils.nextInt(0, 9999) + ".docx";
				fileUrl = wordExportService.exportWord("template_apply_inn_coo", dataMap, filename);
				return fileUrl;
			}

			JSONObject dataJSON = this.get(projectId, user, dept);
			// JSONObject dataJSON =
			// JSONObject.parseObject(WordConvertUtil.htmlToText(relust.toJSONString()));
			dataJSON.put("achievementBenefit", WordConvertUtil.htmlToText(dataJSON.getString("achievementBenefit")));
			dataJSON.put("achievementForm", WordConvertUtil.htmlToText(dataJSON.getString("achievementForm")));
			dataJSON.put("guaranteePlan", WordConvertUtil.htmlToText(dataJSON.getString("guaranteePlan")));
			dataJSON.put("manageGuarantee", WordConvertUtil.htmlToText(dataJSON.getString("manageGuarantee")));
			dataJSON.put("manageKnowledgeRight",
					WordConvertUtil.htmlToText(dataJSON.getString("manageKnowledgeRight")));
			dataJSON.put("manageRisk", WordConvertUtil.htmlToText(dataJSON.getString("manageRisk")));
			dataJSON.put("performanceLately", WordConvertUtil.htmlToText(dataJSON.getString("performanceLately")));
			dataJSON.put("projectTarget", WordConvertUtil.htmlToText(dataJSON.getString("projectTarget")));
			dataJSON.put("researchContentInnovate",
					WordConvertUtil.htmlToText(dataJSON.getString("researchContentInnovate")));
			dataJSON.put("researchContentInterflow",
					WordConvertUtil.htmlToText(dataJSON.getString("researchContentInterflow")));
			dataJSON.put("researchPlan", WordConvertUtil.htmlToText(dataJSON.getString("researchPlan")));
			dataJSON.put("taskDiagram", WordConvertUtil.htmlToText(dataJSON.getString("taskDiagram")));
			dataJSON.put("teamConstitute", WordConvertUtil.htmlToText(dataJSON.getString("teamConstitute")));
			dataJSON.put("teamMemberIntro", WordConvertUtil.htmlToText(dataJSON.getString("teamMemberIntro")));
			dataJSON.put("topExpertPersonIntro",
					WordConvertUtil.htmlToText(dataJSON.getString("topExpertPersonIntro")));

			dataJSON.put("teamEnName", StringEscapeUtils.escapeHtml4(dataJSON.getString("teamEnName")));

			JSONObject leadPersonJson = dataJSON.getJSONObject("leadPerson");
			leadPersonJson.put("birthDate",
					LocalDateUtils.parse(leadPersonJson.getString("birthDate"), LocalDateUtils.PARRERN_Y_M));
			dataJSON.put("leadPerson", leadPersonJson);
			JSONArray subTaskList = dataJSON.getJSONArray("subTaskList");
			if (CollectionUtils.isEmpty(subTaskList)) {
				dataJSON.put("subTaskList", null);
			}
			// 首席专家 人才计划
			JSONArray talentPlan = leadPersonJson.getJSONArray("talentPlan");
			if (!CollectionUtils.isEmpty(talentPlan)) {
				List<String> planList = JSONArray.parseArray(talentPlan.toJSONString(), String.class);
				Result dictMap = dictFeignService.getDictMap(SysDictEnums.TALENT_PLAN_TYPE.getCode());
				Object data = dictMap.getData();
				if (!Objects.equals(null, data) && data instanceof Map) {
					List<String> leaderTalentPlanList = WordConvertUtil.parseCodeListToText((Map<String, String>) data,
							planList);
					dataJSON.put("leaderTalentPlanList", leaderTalentPlanList);
				}
			}
			// 共同首席专家人才计划
			JSONObject bothTopExpertPersonJson = dataJSON.getJSONObject("bothTopExpertPerson");
			bothTopExpertPersonJson.put("birthDate",
					LocalDateUtils.parse(bothTopExpertPersonJson.getString("birthDate"), LocalDateUtils.PARRERN_Y_M));
			dataJSON.put("bothTopExpertPerson", bothTopExpertPersonJson);
			talentPlan = bothTopExpertPersonJson.getJSONArray("talentPlan");
			if (!CollectionUtils.isEmpty(talentPlan)) {
				List<String> planList = JSONArray.parseArray(talentPlan.toJSONString(), String.class);
				Result dictMap = dictFeignService.getDictMap(SysDictEnums.TALENT_PLAN_TYPE.getCode());
				Object data = dictMap.getData();
				if (!Objects.equals(null, data) && data instanceof Map) {
					List<String> bothTalentPlanList = WordConvertUtil.parseCodeListToText((Map<String, String>) data,
							planList);
					dataJSON.put("bothTalentPlanList", bothTalentPlanList);
				}
			}

			List<SrpmsProjectTaskVo> taskDecompositionList = JSONArray
					.parseArray(dataJSON.getString("taskDecompositionList"), SrpmsProjectTaskVo.class);
			if (!org.springframework.util.CollectionUtils.isEmpty(taskDecompositionList)) {
				for (SrpmsProjectTaskVo taskVo : taskDecompositionList) {
					JSONArray joinPersonArr = taskVo.getJoinPersonInfo();
					List<String> joinPersonNameArr = Lists.newArrayList();
					if (joinPersonArr != null && joinPersonArr.size() > 0) {
						for (int i = 0; i < joinPersonArr.size(); i++) {
							JSONObject obj = joinPersonArr.getJSONObject(i);
							if (obj != null && obj.getString("personName") != null) {
								joinPersonNameArr.add(obj.getString("personName"));
							}
						}
					}
					if (joinPersonNameArr.size() > 0) {
						taskVo.setJoinPersonNameStr(StringUtils.join(joinPersonNameArr, ","));
					}
					if (taskVo.getLeadPersonInfo() != null) {
						taskVo.setLeadPersonName(taskVo.getLeadPersonInfo().getString("personName"));
					}
				}
				dataJSON.put("taskDecompositionList", taskDecompositionList);
			}

			// 所属领域
			/*
			 * String belongDomain = vo.getBelongDomain(); if
			 * (StringUtils.isNotBlank(belongDomain)){ List<String> domainList =
			 * Lists.newArrayList(belongDomain); Map<String, String> domainMap =
			 * sysDictService.getDictByCategory(SysDictEnums.PRO_DOMAIN.getCode());
			 * List<String> domainNameList = WordConvertUtil.parseCodeListToText(domainMap,
			 * domainList); newVoJson.put("belongDomainList",domainNameList); }
			 */

			// 所属领域
			String belongDomain = dataJSON.getString("belongDomain");
			if (StringUtils.isNotBlank(belongDomain)) {
				List<String> domainList = Lists.newArrayList(belongDomain);
				Map<String, String> domainMap = sysDictService.getDictByCategory(SysDictEnums.PRO_DOMAIN.getCode());
				List<String> domainNameList = WordConvertUtil.parseCodeListToText(domainMap, domainList);
				dataJSON.put("belongDomainList", domainNameList);
			}

			dataJSON.put("applyYear", StringConvertUtil.convertNumToWord(dataJSON.getString("applyYear")));
			DateTimeFormatter parseFormatterIn = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			DateTimeFormatter parseFormatterOut = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			String projectActionDateStart = dataJSON.getString("projectActionDateStart");
			if (projectActionDateStart != null && projectActionDateStart.length() != 0) {
				dataJSON.put("projectActionDateStart",
						LocalDateTime.parse(projectActionDateStart, parseFormatterIn).format(parseFormatterOut));
			} else {
				dataJSON.put("projectActionDateStart", "");
			}
			String projectActionDateEnd = dataJSON.getString("projectActionDateEnd");
			if (projectActionDateEnd != null && projectActionDateEnd.length() != 0) {
				dataJSON.put("projectActionDateEnd",
						LocalDateTime.parse(projectActionDateEnd, parseFormatterIn).format(parseFormatterOut));
			} else {
				dataJSON.put("projectActionDateEnd", "");
			}

			String createTime = dataJSON.getString("createTime");
			if (createTime != null && createTime.length() != 0) {
				dataJSON.put("createTime", LocalDateTime.parse(createTime, parseFormatterIn).format(parseFormatterOut));
			} else {
				dataJSON.put("createTime", "");
			}

			int memberAllCount = 0; // 总人数
			int memberZGJ = 0; // 正高级人数
			int memberFGJ = 0; // 副高级人数
			int memberZJ = 0; // 中级人数
			int memberCGJ = 0; // 初高级人数
			int memberCCJ = 0; // 初初级
			int memberBSH = 0; // 博士后人数

			int xueweiXS = 0;// 学士
			int xueweiSS = 0;// 硕士
			int xueweiBS = 0;// 博士

			int xueliBK = 0;// 本科
			int xueliYJS = 0;// 研究生
			int xueliQT = 0; // 其他

			JSONArray mainMemberArrJson = new JSONArray();
			// 主要参与人员
			if (dataJSON.getJSONArray("mainMemberList") != null
					&& dataJSON.getJSONArray("mainMemberList").size() != 0) {
				JSONArray mainMemberList = dataJSON.getJSONArray("mainMemberList");
				for (int i = 0; i < mainMemberList.size(); i++) {
					JSONObject mainMember = dataJSON.getJSONArray("mainMemberList").getJSONObject(i);
					memberAllCount++;
					if (mainMember.getString("positionTitle") != null) {
						String positionTitle = mainMember.getString("positionTitle");
						switch (positionTitle) {
						case "正高级":
							memberZGJ++;
							break;
						case "副高级":
							memberFGJ++;
							break;
						case "中级":
							memberZJ++;
							break;
						case "初高级":
							memberCGJ++;
							break;
						case "初初级":
							memberCCJ++;
							break;
						}
					}
					if (mainMember.getString("degree") != null) {
						String positionTitle = mainMember.getString("degree");
						switch (positionTitle) {
						case "学士":
							xueweiXS++;
							break;
						case "硕士":
							xueweiSS++;
							break;
						case "博士":
							xueweiBS++;
							break;
						}
					}
					if (mainMember.getString("education") != null) {
						String positionTitle = mainMember.getString("education");
						switch (positionTitle) {
						case "本科":
						case "本科生":
							xueliBK++;
							break;
						case "研究生":
						case "硕士":
						case "博士":
							xueliYJS++;
							break;
						default:
							xueliQT++;
							break;
						}
					}
					if (mainMember.getString("personCategory") != null
							&& "3".equals(mainMember.getString("personCategory"))) {
						memberBSH++;
					}
					String birthDate = mainMember.getString("birthDate");
					if (StringUtils.isNotBlank(birthDate)) {
						birthDate = StringUtils.substring(birthDate, 0, 10);
					}
					mainMember.put("birthDateStr", birthDate);
					mainMemberArrJson.add(mainMember);
				}
			}
			dataJSON.put("mainMemberList", mainMemberArrJson);

			// 项目首席专家及研究骨干目前承担其它相关国家科技计划课题情况
			JSONArray dictJson = sysDictService.selectByCodes("ROLE").getJSONArray("ROLE");
			JSONArray topMemberOtherTaskArrJson = new JSONArray();
			if (dataJSON.getJSONArray("topMemberOtherTaskList") != null
					&& dataJSON.getJSONArray("topMemberOtherTaskList").size() != 0) {
				JSONArray topMemberOtherTasklist = dataJSON.getJSONArray("topMemberOtherTaskList");
				for (int i = 0; i < topMemberOtherTasklist.size(); i++) {
					JSONObject topMemberOtherTask = dataJSON.getJSONArray("topMemberOtherTaskList").getJSONObject(i);

					String startTime = topMemberOtherTask.getString("otherProjectDateStart").substring(0, 10);
					String startEnd = topMemberOtherTask.getString("otherProjectDateEnd").substring(0, 10);
					topMemberOtherTask.put("otherProjectDur", startTime + "至" + startEnd);
					topMemberOtherTaskArrJson.add(topMemberOtherTask);

					for (int j = 0; j < dictJson.size(); j++) {
						if (topMemberOtherTask.getString("otherProjectRole") != null && topMemberOtherTask
								.getString("otherProjectRole").equals(dictJson.getJSONObject(j).getString("id"))) {
							topMemberOtherTask.put("otherProjectRole", dictJson.getJSONObject(j).getString("label"));
						}
					}
				}
			}
			dataJSON.put("topMemberOtherTaskList", topMemberOtherTaskArrJson);

			dataJSON.put("memberAllCount", memberAllCount + "");
			dataJSON.put("memberZGJ", memberZGJ + "");
			dataJSON.put("memberFGJ", memberFGJ + "");
			dataJSON.put("memberZJ", memberZJ + "");
			dataJSON.put("memberCGJ", memberCGJ + "");
			dataJSON.put("memberCCJ", memberCCJ + "");
			dataJSON.put("xueweiXS", xueweiXS + "");
			dataJSON.put("xueweiSS", xueweiSS + "");
			dataJSON.put("xueweiBS", xueweiBS + "");

			dataJSON.put("xueliBK", xueliBK + "");
			dataJSON.put("xueliYJS", xueliYJS + "");
			dataJSON.put("xueliQT", xueliQT + "");

			dataJSON.put("memberBSH", memberBSH + "");

			/*
			 * Iterator<String> itr = dataJSON.keySet().iterator(); while (itr.hasNext()) {
			 * String key = itr.next(); if (dataJSON.getString(key) != null &&
			 * !JSONObject.isValidObject(dataJSON.getString(key)) &&
			 * !JSONObject.isValidArray(dataJSON.getString(key))) { // dataJSON.put(key,
			 * WordConvertUtil.htmlToText(dataJSON.getString(key))); } }
			 */

			Map dataMap = new HashMap();
			dataMap.put("data", dataJSON);
			String filename = "协同创新申请书_" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS")
					+ RandomUtils.nextInt(0, 9999) + ".docx";
			String finalWordName = wordExportService.exportWord("template_apply_inn_coo", dataMap, filename);
			fileUrl = finalWordName;
		} catch (Exception e) {
			log.error("导出word异常。", e);
			throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
		} finally {
		}
		return fileUrl;
	}

	/**
	 * 解析人才计划
	 * 
	 * @param talnetplanStr
	 * @return
	 */
	private String getUserTalnetPlan(String talnetplanStr) {
		if (StringUtils.isBlank(talnetplanStr)) {
			return null;
		}
		Result dictMap = dictFeignService.getDictMap(SysDictEnums.TALENT_PLAN_TYPE.getCode());
		Object data = dictMap.getData();
		if (Objects.equals(null, data) || !(data instanceof Map)) {
			return null;
		}

		Map<String, String> allSysDict = (Map<String, String>) data;
		String[] talnetPlanArray = talnetplanStr.split("\\s+");
		JSONArray talnetPlans = new JSONArray();
		for (String talnet : talnetPlanArray) {
			if (StringUtils.isBlank(talnet) || talnet.startsWith("□")) {
				continue;
			}
			for (Map.Entry<String, String> entry : allSysDict.entrySet()) {
				String value = entry.getValue();
				String code = entry.getKey();
				if (StringUtils.contains(talnet, value)) {
					talnetPlans.add(code);
				}
			}
		}
		return talnetPlans.toJSONString();
	}

	/**
	 * 导入数据
	 * 
	 * @param wordFileUrl
	 *            word文件URL地址
	 * @return
	 */
	@Override
	public SrpmsProjectApplyInnCooSaveVo importWord(String wordFileUrl) {
		try {
			SrpmsProjectApplyInnCooSaveVo innCooSaveVo = new SrpmsProjectApplyInnCooSaveVo();
			String classPath = new File(ResourceUtils.getURL("").getPath()).getAbsoluteFile().toString();
			String htmlFilePath = WordConvertUtil.wordConvertToHtml(wordFileUrl, classPath);
			Document document = Jsoup.parse(new File(htmlFilePath), "utf-8");

			// 所属领域
			Elements domains = document.getElementsMatchingOwnText("所属领域");
			if (!CollectionUtils.isEmpty(domains)) {
				Element element = domains.get(0);
				String domainStr = StringUtils.trim(StringUtils.replace(element.parent().text(), "所属领域：", ""));
				if (StringUtils.isNotBlank(domainStr)) {
					Map<String, String> domainMap = sysDictService.getDictByCategory(SysDictEnums.PRO_DOMAIN.getCode());
					JSONArray array = WordConvertUtil.parseTextsToCodes(domainMap, domainStr);
					if (!CollectionUtils.isEmpty(array)) {
						innCooSaveVo.setBelongDomain(array.getString(0));
					}
				}
			}

			// 项目名称
			Elements projectNames = document.getElementsMatchingOwnText("项目名称：");
			if (!CollectionUtils.isEmpty(projectNames)) {
				Element element = projectNames.get(0);
				Element projectName = element.parent();
				if (null != projectName) {
					String text = projectName.text();
					innCooSaveVo.setProjectName(StringUtils.replace(text, "项目名称：", ""));
				}
			}
			// 研究方向
			Elements dircetions = document.getElementsMatchingOwnText("研究方向：");
			if (!CollectionUtils.isEmpty(dircetions)) {
				Element element = dircetions.get(0);
				Element dirc = element.parent();
				if (null != dirc) {
					String text = dirc.text();
					innCooSaveVo.setDirection(StringUtils.replace(text, "研究方向：", ""));
				}
			}

			// 项目期限
			Elements startEndDate = document.getElementsMatchingOwnText("项目执行期限：");
			if (!CollectionUtils.isEmpty(startEndDate)) {
				Element element = startEndDate.get(0);
				String text = element.parent().text();
				if (StringUtils.isNotBlank(text)) {
					String[] prodates = text.replace("项目执行期限：", "").split("至");
					if (prodates.length == 2) {
						innCooSaveVo.setProjectActionDateStart(
								DateUtil.chinaToLocalDateTime(prodates[0], DateUtil.PATTERN_CLASSICAL_SIMPLE));
						innCooSaveVo.setProjectActionDateEnd(
								DateUtil.chinaToLocalDateTime(prodates[1], DateUtil.PATTERN_CLASSICAL_SIMPLE));
					}
				}
			}

			Elements tableElements = document.getElementsByTag("table");
			// 解析文档提取表格数据
			// 项目基本信息
			Element baseInfoTable = tableElements.get(0);
			innCooSaveVo.setLeadPersonWorkTime(NumberUtils.toLong(extractCellFromTable(baseInfoTable, 5, 3)));
			innCooSaveVo.setBothTopWorkTime(NumberUtils.toLong(extractCellFromTable(baseInfoTable, 14, 3)));

			JSONObject leadPersonJson = new JSONObject();
			leadPersonJson.put("area", extractCellFromTable(baseInfoTable, 8, 1)); // 8
			innCooSaveVo.setLeadPerson(leadPersonJson);

			/**
			 * 共同首席专家信息
			 * {"zipCode":"100005","officeName":"中西医结合基础","country":"中国","positionTitle":"正高级","education":"硕士","gender":"男","nation":"汉族",
			 * "subject":"0852-生物医学工程","idCard":"821302198908310000","honor":"院士","type":"1","expertise":"生物医学工程","educationYear":"2014",
			 * "password":"666666","idCardType":"身份证","workPerYear":6537,"major":"中西医结合基础","updateBy":"null","educationCountry":"中国",
			 * "tel":"010-12157","id":"5","state":"1","fax":"010-12195","liveBaseName":"中国医科院","email":"safsaaag@126.com","address":"东单三条","projectCommitmentUnit":"阜外医院",
			 * "degree":"博士","empNo":"100004","dept":"1007","birthDate":"1985-01-02T22:17:43","sourcePersonId":"101011","createBy":"null",
			 * "talentPlan":"[\"1\",\"6\"]","phone":"13700000199","createTime":"2019-02-19T10:35:58.507339","name":"黎明"}
			 *
			 */
			JSONObject bothTopExpertPerson = new JSONObject();
			bothTopExpertPerson.put("area", extractCellFromTable(baseInfoTable, 17, 1));
			bothTopExpertPerson.put("name", extractCellFromTable(baseInfoTable, 9, 2));
			// 其余信息根据名字带出来
			bothTopExpertPerson.put("gender", extractCellFromTable(baseInfoTable, 9, 4));
			bothTopExpertPerson.put("birthDate", DateUtil
					.chinaToLocalDateTime(extractCellFromTable(baseInfoTable, 9, 6), LocalDateUtils.PARRERN_Y_M_D));
			bothTopExpertPerson.put("positionTitle", extractCellFromTable(baseInfoTable, 10, 1));
			bothTopExpertPerson.put("education", extractCellFromTable(baseInfoTable, 10, 3));
			bothTopExpertPerson.put("educationCountry", extractCellFromTable(baseInfoTable, 10, 5));
			bothTopExpertPerson.put("major", extractCellFromTable(baseInfoTable, 11, 1));
			bothTopExpertPerson.put("tel", extractCellFromTable(baseInfoTable, 11, 3));
			bothTopExpertPerson.put("faxNumber", extractCellFromTable(baseInfoTable, 11, 5));
			bothTopExpertPerson.put("phone", extractCellFromTable(baseInfoTable, 12, 1));
			bothTopExpertPerson.put("email", extractCellFromTable(baseInfoTable, 12, 3));
			bothTopExpertPerson.put("idCardType", extractCellFromTable(baseInfoTable, 13, 1));
			bothTopExpertPerson.put("idCard", extractCellFromTable(baseInfoTable, 13, 3));
			bothTopExpertPerson.put("projectCommitmentUnit", extractCellFromTable(baseInfoTable, 14, 1));
			Integer zipCode = StringConvertUtil.convert(extractCellFromTable(baseInfoTable, 15, 1));
			if (null != zipCode) {
				bothTopExpertPerson.put("address",
						StringUtils.replace(extractCellFromTable(baseInfoTable, 15, 1), zipCode.toString(), ""));
			} else {
				bothTopExpertPerson.put("address", extractCellFromTable(baseInfoTable, 15, 1));
			}
			bothTopExpertPerson.put("zipCode", zipCode);
			// 入选人才计划
			String talnetplanStr = extractCellFromTable(baseInfoTable, 16, 1);
			bothTopExpertPerson.put("talentPlan", getUserTalnetPlan(talnetplanStr));

			bothTopExpertPerson.put("liveBaseName", extractCellFromTable(baseInfoTable, 17, 1));

			innCooSaveVo.setBothTopExpertPerson(bothTopExpertPerson);
			// 团队组成情况
			innCooSaveVo.setTeamDirection(extractCellFromTable(baseInfoTable, 18, 2));
			innCooSaveVo.setTeamEnName(extractCellFromTable(baseInfoTable, 19, 1));
			// 任务分解
			List<SrpmsProjectTaskVo> subTaskList = new ArrayList<>();
			List<List<String>> subTaskListArray = extractListFromTable(baseInfoTable, 21, 0);
			Integer coopDeptRowID = 21;
			if (!CollectionUtils.isEmpty(subTaskListArray)) {
				for (List<String> rowData : subTaskListArray) {
					coopDeptRowID++;
					if (rowData.size() != 5) {
						break;
					}
					String serial = rowData.get(0);
					String taskName = rowData.get(1);
					if (StringUtils.isBlank(serial) || StringUtils.isBlank(taskName)) {
						continue;
					}
					SrpmsProjectTaskVo subTask = new SrpmsProjectTaskVo();
					subTask.setTaskName(taskName);
					subTask.setDeptName(rowData.get(2));
					subTask.setGroupLeaderMember(rowData.get(3));
					subTask.setBudgetAllotRatio(rowData.get(4));
					subTaskList.add(subTask);
				}
			}
			innCooSaveVo.setSubTaskList(subTaskList);
			// 国际合作单位信息
			List<SrpmsProjectDeptJoinVo> coopDeptList = new ArrayList<>();
			List<List<String>> coopDeptArray = extractListFromTable(baseInfoTable, coopDeptRowID, 0);
			if (!CollectionUtils.isEmpty(coopDeptArray)) {
				for (List<String> rowData : coopDeptArray) {
					String serial = rowData.get(0);
					String deptName = rowData.get(1);
					if (rowData.size() != 5) {
						break;
					}

					if (StringUtils.isBlank(serial) || StringUtils.isBlank(deptName)) {
						continue;
					}
					SrpmsProjectDeptJoinVo coopVo = new SrpmsProjectDeptJoinVo();
					coopVo.setDeptName(deptName);
					coopVo.setTaskLeaderName(rowData.get(2));
					coopVo.setCountry(rowData.get(3));
					coopVo.setEmail(rowData.get(4));
					coopDeptList.add(coopVo);
				}
			}
			innCooSaveVo.setCoopDeptList(coopDeptList);

			// 解析项目参与人员 lixin 不解析主要参与人员
			// List<SrpmsProjectPersonJoinVo> mostMemberList = new ArrayList<>();
			// List<List<String>> mostMemberArray =
			// extractListFromTable(tableElements.get(1), 1, 2);
			// for (List<String> personStr: mostMemberArray){
			// if (StringUtils.isBlank(personStr.get(0)) ||
			// StringUtils.isBlank(personStr.get(1))){
			// break;
			// }
			// SrpmsProjectPersonJoinVo personJoinVo = new SrpmsProjectPersonJoinVo();
			// personJoinVo.setPersonName(StringConvertUtil.convertNull(personStr.get(1)));
			// personJoinVo.setGender(StringConvertUtil.convertNull(personStr.get(2)));
			// personJoinVo.setBirthDate((personStr.get(3)));
			// personJoinVo.setPositionTitle(StringConvertUtil.convertNull(personStr.get(4)));
			// personJoinVo.setDegree(StringConvertUtil.convertNull(personStr.get(5)));
			// personJoinVo.setDeptName(StringConvertUtil.convertNull(personStr.get(6)));
			// personJoinVo.setPhone(StringConvertUtil.convertNull(personStr.get(7)));
			// personJoinVo.setIdCard(StringConvertUtil.convertNull(personStr.get(8)));
			// String workYear = personStr.get(9);
			// if (StringUtils.isNotBlank(workYear) && StringUtils.isNumeric(workYear)){
			// personJoinVo.setWorkPerYear(NumberUtils.toInt(workYear));
			// }
			// personJoinVo.setBelongTask(StringConvertUtil.convertNull(personStr.get(10)));
			// mostMemberList.add(personJoinVo);
			// }
			// innCooSaveVo.setMainMemberList(mostMemberList);
			// 团队组成情况
			Element teamConsTable = tableElements.get(2);
			innCooSaveVo.setTeamConstitute(extractCellFromTable(teamConsTable, 0, 0, true));

			// 首席专家简介
			Element topexpTable = tableElements.get(3);
			innCooSaveVo.setTopExpertPersonIntro(extractCellFromTable(topexpTable, 0, 0, true));

			// 团队主要成员简介
			Element teamMemberIntroTable = tableElements.get(4);
			innCooSaveVo.setTeamMemberIntro(extractCellFromTable(teamMemberIntroTable, 0, 0, true));

			// 项目首席专家及研究骨干目前承担其它相关国家科技计划课题情况
			Element topMenberTable = tableElements.get(5);
			List<SrpmsProjectPersonJoinVo> topMemberOtherTaskList = new ArrayList<>();
			List<List<String>> topMenberArray = extractListFromTable(topMenberTable, 1, 0);
			if (!CollectionUtils.isEmpty(topMenberArray)) {
				Map<String, String> roleMap = sysDictService.getDictByCategory(SysDictEnums.ROLE.getCode());
				for (List<String> rowData : topMenberArray) {
					if (StringUtils.isBlank(rowData.get(0)) || StringUtils.isBlank(rowData.get(1))) {
						break;
					}
					SrpmsProjectPersonJoinVo personJoinVo = new SrpmsProjectPersonJoinVo();
					personJoinVo.setPersonName(rowData.get(1));
					String role = StringUtils.trim(rowData.get(2));
					personJoinVo.setOtherProjectRole(WordConvertUtil.getRoleCode(roleMap, role));
					personJoinVo.setOtherProjectName(rowData.get(3));
					personJoinVo.setOtherProjectSource(rowData.get(4));
					String otherProjectDate = rowData.get(5);
					if (StringUtils.isNotBlank(otherProjectDate)) {
						String[] prodates = otherProjectDate.split("至");
						if (!ArrayUtils.isEmpty(prodates) && prodates.length == 2) {
							personJoinVo.setOtherProjectDateStart(DateUtil.strToLocalDateTime(prodates[0]));
							personJoinVo.setOtherProjectDateEnd(DateUtil.strToLocalDateTime(prodates[1]));
						}
					}
					personJoinVo.setOtherProjectAmount(rowData.get(6));
					topMemberOtherTaskList.add(personJoinVo);
				}
			}
			innCooSaveVo.setTopMemberOtherTaskList(topMemberOtherTaskList);
			// 五年取得的主要学术成绩
			Element latelyTable = tableElements.get(6);
			innCooSaveVo.setPerformanceLately(extractCellFromTable(latelyTable, 0, 0, true));
			// 团队研究目标及考核指标与测评方式/方法
			Element projectTargetTable = tableElements.get(7);
			innCooSaveVo.setProjectTarget(extractCellFromTable(projectTargetTable, 0, 0, true));
			// 阐述拟开展的研究工作方案及其可行性
			Element researchPlanTable = tableElements.get(8);
			innCooSaveVo.setResearchPlan(extractCellFromTable(researchPlanTable, 0, 0, true));
			// 团队国际合作与交流计划
			Element researchFlowTable = tableElements.get(9);
			innCooSaveVo.setResearchContentInterflow(extractCellFromTable(researchFlowTable, 0, 0, true));
			// 年度任务
			Element taskPreYearListTable = tableElements.get(10);
			List<SrpmsProjectTaskVo> taskPreYearList = new ArrayList<>();
			List<List<String>> taskPreYearListArray = extractListFromTable(taskPreYearListTable, 1, 0);
			if (!CollectionUtils.isEmpty(taskPreYearListArray)) {
				for (List<String> rowData : taskPreYearListArray) {
					if (StringUtils.isBlank(rowData.get(0)) || StringUtils.isBlank(rowData.get(1))) {
						break;
					}
					SrpmsProjectTaskVo taskVo = new SrpmsProjectTaskVo();
					taskVo.setTaskYear(rowData.get(1));
					taskVo.setTaskName(rowData.get(2));
					taskVo.setTaskTargetYear(rowData.get(3));
					taskVo.setImportantPoint(rowData.get(4));
					taskPreYearList.add(taskVo);
				}
			}
			innCooSaveVo.setTaskPreYearList(taskPreYearList);

			// （三）项目任务分解情况
			Element taskDecompositionListTable = tableElements.get(11);
			List<SrpmsProjectTaskVo> taskDecompositionList = new ArrayList<>();
			List<List<String>> taskDecompositionListArray = extractListFromTable(taskDecompositionListTable, 1, 0);
			if (!CollectionUtils.isEmpty(taskDecompositionListArray)) {
				for (List<String> rowData : taskDecompositionListArray) {
					if (StringUtils.isBlank(rowData.get(0)) || StringUtils.isBlank(rowData.get(1))) {
						break;
					}
					SrpmsProjectTaskVo taskVo = new SrpmsProjectTaskVo();
					taskVo.setTaskName(rowData.get(1));
					taskVo.setTaskContent(rowData.get(2));
					taskVo.setTaskTargetYear(rowData.get(3));
					taskVo.setTargetPerYear(rowData.get(4));
					taskVo.setLeadPersonName(rowData.get(5));
					String joinPersonNames = rowData.get(6);
					if (StringUtils.isNotBlank(joinPersonNames)) {
						String[] split = joinPersonNames.split(",|，|\\s+");
						taskVo.setJoinPersonName(JSONObject.parseArray(JSONObject.toJSONString(split)));
					}
					taskVo.setAllocatedAmount(rowData.get(7));
					taskDecompositionList.add(taskVo);
				}
			}
			innCooSaveVo.setTaskDecompositionList(taskDecompositionList);

			// 联合任务申报单位
			Element jointUnitTaskTable = tableElements.get(12);
			List<SrpmsProjectTaskVo> jointUnitTaskList = new ArrayList<>();
			List<List<String>> jointUnitTaskArray = extractListFromTable(jointUnitTaskTable, 1, 0);
			if (!CollectionUtils.isEmpty(jointUnitTaskArray)) {
				for (List<String> rowData : jointUnitTaskArray) {
					if (StringUtils.isBlank(rowData.get(0)) || StringUtils.isBlank(rowData.get(1))) {
						break;
					}
					SrpmsProjectTaskVo taskVo = new SrpmsProjectTaskVo();
					taskVo.setDeptName(rowData.get(1));
					taskVo.setTaskContent(rowData.get(2));
					taskVo.setTaskTargetYear(rowData.get(3));
					taskVo.setTargetPerYear(rowData.get(4));
					taskVo.setGroupLeaderMember(rowData.get(5));
					taskVo.setBudgetAllotRatio(rowData.get(6));
					jointUnitTaskList.add(taskVo);
				}
			}
			innCooSaveVo.setJointUnitTask(jointUnitTaskList);

			// 项目预期的主要创新点
			Element researchInnovateTable = tableElements.get(13);
			innCooSaveVo.setResearchContentInnovate(extractCellFromTable(researchInnovateTable, 0, 0, true));
			// 项目成果的呈现形式及描述
			Element achievementFormTable = tableElements.get(14);
			innCooSaveVo.setAchievementForm(extractCellFromTable(achievementFormTable, 0, 0, true));
			// 项目成果的预期经济、社会效益
			Element achievementBenefitTable = tableElements.get(15);
			innCooSaveVo.setAchievementBenefit(extractCellFromTable(achievementBenefitTable, 0, 0, true));
			// 项目组织管理、协同机制和保障措施
			Element manageGuaranteeTable = tableElements.get(16);
			innCooSaveVo.setManageGuarantee(extractCellFromTable(manageGuaranteeTable, 0, 0, true));
			// 知识产权对策、成果管理及合作权益分配
			Element researchContentInnovateTable = tableElements.get(17);
			innCooSaveVo.setManageKnowledgeRight(extractCellFromTable(researchContentInnovateTable, 0, 0, true));
			// 风险分析及对策
			Element manageRiskTable = tableElements.get(18);
			innCooSaveVo.setManageRisk(extractCellFromTable(manageRiskTable, 0, 0, true));
			// 保障条件
			Element guaranteePlanTable = tableElements.get(19);
			innCooSaveVo.setGuaranteePlan(extractCellFromTable(guaranteePlanTable, 0, 0, true));

			// 预算经费 不解析预算经费
			// List<SrpmsProjectBudgetDetailVo> budgetPreYearList = new ArrayList<>();
			// for (int i = 20; i < tableElements.size(); i++){
			// Element tableElement = tableElements.get(i);
			// //获取header
			// String tableHeader = extractCellFromTable(tableElement, 0, 0);
			// if (StringUtils.isBlank(tableHeader) || !tableHeader.contains("年度经费概算")){
			// break;
			// }
			//
			// SrpmsProjectBudgetDetailVo budgetDetailVo = new SrpmsProjectBudgetDetailVo();
			// budgetDetailVo.setBudgetAmount(NumberUtils.toDouble(extractCellFromTable(tableElement,
			// 2, 1)));
			// budgetDetailVo.setBudgetYear(StringConvertUtil.convert(tableHeader));
			// //生成科目明细字符串
			// List<Map<String, Object>> budgetSubjects = new ArrayList<>();
			// List<List<String>> budgetSubStrList = extractListFromTable(tableElement, 3,
			// 0);
			// int index = 1;
			// for (List<String> budgetSubStr: budgetSubStrList){
			// Map<String, Object> subjectMap = new HashMap<>();
			// subjectMap.put("serial",index++);
			// subjectMap.put("name", WordImportUtil.subStringSerial(budgetSubStr.get(0)));
			// subjectMap.put("amount",
			// StringUtils.isBlank(budgetSubStr.get(1))?null:budgetSubStr.get(1).trim().substring(budgetSubStr.get(1).trim().indexOf("\\.")+1));
			// budgetSubjects.add(subjectMap);
			// }
			// budgetDetailVo.setBudgetDetail(JSON.parseArray(JSON.toJSONString(budgetSubjects)));
			// budgetPreYearList.add(budgetDetailVo);
			// }
			// innCooSaveVo.setBudgetPreYearList(budgetPreYearList);

			return innCooSaveVo;

		} catch (Exception e) {
			log.error("解析word文件发生异常.", e);
			throw new BaseException(PlatformErrorType.IMPORT_TEMPLATE_ERROR);
		}
	}

	@Override
	public void generateApplyBookPdf(Long projectId, UserVo userVo, DeptVo deptVo) {
		File pdfFile = null;
		FileInputStream fileInputStream = null;
		SrpmsProject project = projectService.getById(projectId);
		try {
			// 生成申请书pdf文档
			// String docxFilePath = this.exportWordFile(projectId, userVo, deptVo);
			// String jarPath = new
			// File(ResourceUtils.getURL("").getPath()).getAbsolutePath();
			// String pdfFilePath = jarPath+ "/"+project.getProjectName()+"申请书.pdf";
			// WordConvertUtil.wordConvertToPdf(docxFilePath, pdfFilePath);
			String pdfFilePath = this.exportPdfFile(projectId, userVo, deptVo);
			pdfFile = new File(pdfFilePath);
			fileInputStream = new FileInputStream(pdfFile);

			MultipartFile multipartFile = new MockMultipartFile("file", pdfFile.getName(),
					ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);

			Result<FileInfoVo> result = fileOperatorFeignService.uploadFile(multipartFile);
			if (result.isSuccess()) {
				FileInfoVo fileInfoVo = result.getData();
				if (fileInfoVo != null) {
					SrpmsProject projectFile = new SrpmsProject();
					projectFile.setId(project.getId());
					projectFile.setApplyBookFileId(NumberUtils.toLong(fileInfoVo.getId()));
					projectFile.setApplyBookFileName(fileInfoVo.getFileName());
					projectFile.setApplyBookFileUrl(fileInfoVo.getFileUrl());
					projectService.updateById(projectFile);
				}
			} else {
				log.error("申请书上传文件服务器失败。");
			}
		} catch (Exception e) {
			log.error("生成pdf异常.", e);
			throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
		} finally {
			IOUtils.closeQuietly(fileInputStream);
			if (pdfFile != null && pdfFile.exists()) {
				pdfFile.delete();
			}
		}
	}

	@Override
	public String exportPdfFile(Long projectId, UserVo userVo, DeptVo deptVo) throws Exception {
		String docxFilePath = this.exportWordFile(projectId, userVo, deptVo);
		// 转格式
		String pdfFilePath = pdfMergeService.wordToPdf(docxFilePath);
		String pdfFinalName = docxFilePath.replace("docx", "pdf");
		log.info("PDF文件合并前路径：{}, 合并后路径：{}, projectId:{}", pdfFilePath, pdfFinalName, projectId);
		// 合并
		pdfMergeService.mergeAttachmentApply(pdfFilePath, projectId, pdfFinalName);
		return pdfFinalName;
	}

	@Override
	public JSONObject get(long projectId) {

		return this.get(projectId, null, null);
	}
}