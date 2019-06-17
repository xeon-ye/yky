package com.deloitte.services.srpmp.project.apply.service.impl;

import static com.deloitte.services.srpmp.common.util.HTMLParseUtil.extractCellFromTable;
import static com.deloitte.services.srpmp.common.util.HTMLParseUtil.extractListFromTable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Validator;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomUtils;
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
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyInnBcooSaveVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectDeptJoinVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonJoinVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectTaskVo;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetDetailVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
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
import com.deloitte.services.srpmp.common.util.BudgetDetailUtil;
import com.deloitte.services.srpmp.common.util.JSONConvert;
import com.deloitte.services.srpmp.common.util.WordConvertUtil;
import com.deloitte.services.srpmp.outline.util.DateUtil;
import com.deloitte.services.srpmp.project.apply.entity.SrpmsProjectApplyInnBcoo;
import com.deloitte.services.srpmp.project.apply.mapper.SrpmsProjectApplyInnBcooMapper;
import com.deloitte.services.srpmp.project.apply.service.ISrpmsProjectApplyInnBcooService;
import com.deloitte.services.srpmp.project.apply.util.LocalDateUtils;
import com.deloitte.services.srpmp.project.apply.util.ProjectCheckUtils;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectDeptJoinService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectFlowService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectPersonJoinService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectTaskService;
import com.deloitte.services.srpmp.project.base.service.impl.PdfMergeServiceImpl;
import com.deloitte.services.srpmp.project.budget.service.ISrpmsProjectBudgetApplyService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-18
 * @Description : SrpmsProjectApplyInnBcoo服务实现类
 * @Modified :
 */
@Service
@Slf4j
@Transactional
public class SrpmsProjectApplyInnBcooServiceImpl
		extends ServiceImpl<SrpmsProjectApplyInnBcooMapper, SrpmsProjectApplyInnBcoo>
		implements ISrpmsProjectApplyInnBcooService {

	@Autowired
	private ISrpmsProjectDeptJoinService srpmsProjectDeptJoinServiceImpl;

	@Autowired
	private ISrpmsProjectPersonJoinService srpmsProjectPersonJoinServiceImpl;

	@Autowired
	SrpmsProjectApplyInnCommonServiceImpl commonService;

	@Autowired
	private ISrpmsProjectService projectService;

	@Autowired
	private ISrpmsProjectBudgetApplyService budgetApplyService;

	@Autowired
	private ISrpmsProjectTaskService taskService;

	@Autowired
	private Validator validator;

	@Autowired
	private ISysDictService sysDictService;

	@Autowired
	private WordExportServiceImpl wordExportService;

	@Autowired
	private FileOperatorFeignService fileOperatorFeignService;

	@Autowired
	private ISrpmsProjectFlowService flowServicel;

	@Autowired
	private PdfMergeServiceImpl pdfMergeService;

	@Autowired
	private ISrpmsCommonNclobService commonNclobService;

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
	public long saveOrUpdate(SrpmsProjectApplyInnBcooSaveVo pageData, DeptVo deptVo) {

		// 项目ID
		long projectId = 0l;

		JSONObject projectJson = JSONObject.parseObject(JSONObject.toJSONString(pageData));

		SrpmsProjectApplyInnBcoo projectEntity = JSONObject.parseObject(projectJson.toJSONString(),
				SrpmsProjectApplyInnBcoo.class);
		if (pageData.getId() == null) {

			projectId = commonService.insertCommonInfo(projectJson, ProjectCategoryEnums.INNOVATE_BCOO, deptVo);
			// 重大创新项目信息

			projectEntity.setId(projectId);
			this.save(projectEntity);

		} else {

			this.saveOrUpdate(projectEntity);
			projectId = commonService.saveOrUpdateCommonInfo(projectJson, ProjectCategoryEnums.INNOVATE_BCOO);

		}

		// 项目首席专家及研究骨干目前承担其它相关国家科技计划课题情况
		srpmsProjectPersonJoinServiceImpl.cleanAndSavePersonJoin(pageData.getTopMemberOtherTaskList(),
				PersonJoinWayEnums.APPLY_OTHER_PROJECT, projectId);

		// 联合申报单位任务分工情况
		taskService.cleanAndSaveTask(pageData.getJointUnitTask(), TaskCategoryEnums.APPLY_INNOVATE_TASK_JOINT_UNIT,
				projectId);
		// 联合申请单位信息
		srpmsProjectDeptJoinServiceImpl.cleanAndSaveDeptJoin(pageData.getJointApplicantUnit(),
				DeptJoinWayEnums.APPLY_INNOVATE_UNIT, projectId);

		// 项目任务分解情况和各任务之间的逻辑关系图示
		commonService.setTaskAndBudgetList(projectJson.getJSONArray("taskDecompositionList"), projectId);

		return projectId;
	}

	@Override
	public void submit(SrpmsProjectApplyInnBcooSaveVo pageData, UserVo userVo, DeptVo deptVo) {

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

		commonService.copyApplyToTaskInnBcoo(pageData, budgetJson);

		// 生成申请书pdf文档
		this.generateApplyBookPdf(id, userVo, deptVo);
		// 生成预算书PDF
		budgetApplyService.generateBudgetApplyPdf(id, userVo, deptVo);

		log.info("重大协同，提交申请书，已更新项目状态, projectId:{}", id);
		// 发起流程
		flowServicel.startAuditProcess(id, VoucherTypeEnums.APPLY_BOOK, userVo, deptVo);

		log.info("重大协同，提交申请书，已发起流程, projectId{}", id);
	}

	@Override
	public void generateApplyBookPdf(Long projectId, UserVo userVo, DeptVo deptVo) {
		// 生成申请书pdf文档
		File pdfFile = null;
		FileInputStream fileInputStream = null;
		try {
			// SrpmsProject project = projectService.getById(projectId);
			// String docxFilePath = this.exportWordFile(projectId,userVo, deptVo);
			// String jarPath = new
			// File(ResourceUtils.getURL("").getPath()).getAbsolutePath();
			// String pdfFilePath = jarPath+ "/"+project.getProjectName()+"申报书.pdf";
			// WordConvertUtil.wordConvertToPdf(docxFilePath, pdfFilePath);
			String pdfFilePath = this.exportPdfFile(projectId, userVo, deptVo);
			pdfFile = new File(pdfFilePath);
			fileInputStream = new FileInputStream(pdfFile);

			MultipartFile multipartFile = new MockMultipartFile("file", pdfFile.getName(),
					ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);

			Result<FileInfoVo> result = fileOperatorFeignService.uploadFile(multipartFile);
			// log.info("fileOperatorFeignService.uploadFile resp:" +
			// JSONObject.toJSONString(result));
			if (result.isSuccess()) {
				FileInfoVo fileInfoVo = result.getData();
				if (fileInfoVo != null) {
					SrpmsProject projectFile = new SrpmsProject();// 项目实体必须重新查询一次，以获取最新的数据
					projectFile.setId(projectId);
					projectFile.setApplyBookFileId(NumberUtils.toLong(fileInfoVo.getId()));
					projectFile.setApplyBookFileName(fileInfoVo.getFileName());
					projectFile.setApplyBookFileUrl(fileInfoVo.getFileUrl());
					projectService.updateById(projectFile);
				}
				pdfFile.delete();
			} else {
				log.error("申报书上传文件服务器失败。");
			}
		} catch (Exception e) {
			throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
		} finally {
			IOUtils.closeQuietly(fileInputStream);
			if (pdfFile != null && pdfFile.exists()) {
				pdfFile.delete();
			}
		}
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
			dept.setContactsName(null);
			dept.setFaxNumber(null);
			dept.setPhone(null);
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

		JSONObject relust = commonService.queryCommonInfoById(projectId, ProjectCategoryEnums.INNOVATE_BCOO);

		relust.putAll(JSONObject.parseObject(JSON.toJSONString(this.getById(projectId))));

		// 项目首席专家及研究骨干目前承担其它相关国家科技计划课题情况
		relust.put("topMemberOtherTaskList", srpmsProjectPersonJoinServiceImpl
				.queryPersonJoinListByJoinWay(PersonJoinWayEnums.APPLY_OTHER_PROJECT, projectId));

		// 总预算表
		relust.put("budgetAllList",
				taskService.queryTaskListByTaskCategory(TaskCategoryEnums.APPLY_INNOVATE_BUDGET_ALL, projectId));

		// 联合申请单位信息
		relust.put("jointApplicantUnit", srpmsProjectDeptJoinServiceImpl
				.queryDeptJoinListByJoinWay(DeptJoinWayEnums.APPLY_INNOVATE_UNIT, projectId));

		relust.put("jointUnitTask",
				taskService.queryTaskListByTaskCategory(TaskCategoryEnums.APPLY_INNOVATE_TASK_JOINT_UNIT, projectId));

		relust.put("subjectCategory", relust.getString("subjectCategory") == null ? ""
				: JSONArray.parseArray(relust.getString("subjectCategory")));

		JSONConvert.convertJson(relust);
		return relust;
	}

	@Override
	public String exportWordFile(Long projectId, UserVo userVo, DeptVo deptVo) {
		String templateName = "template_apply_inn_bcoo_2019";
		InputStream fileIn = null;
		String fileUrl = null;
		try {
			if (NumberUtils.LONG_ZERO == projectId) {
				Map dataMap = new HashMap();
				dataMap.put("leadDept", deptVo);
				JSONObject leadPersonJson = (JSONObject) JSON.toJSON(userVo);
				leadPersonJson.put("birthDate",
						LocalDateUtils.parse(leadPersonJson.getString("birthDate"), LocalDateUtils.PARRERN_Y_M));
				dataMap.put("leadPerson", leadPersonJson);

				JSONObject dataJson = new JSONObject();
				dataMap.put("p", dataJson);
				String filename = "重大协同项目申请书_" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS")
						+ RandomUtils.nextInt(0, 9999) + ".docx";
				fileUrl = wordExportService.exportWord(templateName, dataMap, filename);
				return fileUrl;
			}

			DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
			DateTimeFormatter formatterLess = DateTimeFormatter.ofPattern("yyyy年MM月");
			DateTimeFormatter formatterBirth = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			JSONObject voJson = this.get(projectId, userVo, deptVo);

			SrpmsProjectApplyInnBcooSaveVo vo = JSONObject.parseObject(voJson.toJSONString(),
					SrpmsProjectApplyInnBcooSaveVo.class);

			Map<String, Object> dataMap = new HashMap<>();

			vo.setProjectAbstract(WordConvertUtil.htmlToText(vo.getProjectAbstract()));
			vo.setApprovalNecessay(WordConvertUtil.htmlToText(vo.getApprovalNecessay()));
			vo.setProjectTarget(WordConvertUtil.htmlToText(vo.getProjectTarget()));
			vo.setResearchContentMain(WordConvertUtil.htmlToText(vo.getResearchContentMain()));
			vo.setAchievementForm(WordConvertUtil.htmlToText(vo.getAchievementForm()));
			vo.setAchievementBenefit(WordConvertUtil.htmlToText(vo.getAchievementBenefit()));
			vo.setSuperiorityDeptBasic(WordConvertUtil.htmlToText(vo.getSuperiorityDeptBasic()));
			vo.setResearchContentMethod(WordConvertUtil.htmlToText(vo.getResearchContentMethod()));
			vo.setResearchContentInterflow(WordConvertUtil.htmlToText(vo.getResearchContentInterflow()));
			vo.setResearchContentUsePlan(WordConvertUtil.htmlToText(vo.getResearchContentUsePlan()));
			vo.setResearchContentInnovate(WordConvertUtil.htmlToText(vo.getResearchContentInnovate()));
			vo.setProjectTarget(WordConvertUtil.htmlToText(vo.getProjectTarget()));
			vo.setSuperiorityDeptChoose(WordConvertUtil.htmlToText(vo.getSuperiorityDeptChoose()));
			vo.setSuperiorityDeptBasic(WordConvertUtil.htmlToText(vo.getSuperiorityDeptBasic()));
			vo.setSuperiorityDeptSuport(WordConvertUtil.htmlToText(vo.getSuperiorityDeptSuport()));
			vo.setSuperiorityDeptAbroad(WordConvertUtil.htmlToText(vo.getSuperiorityDeptAbroad()));
			vo.setMainstayMemberNote(WordConvertUtil.htmlToText(vo.getMainstayMemberNote()));
			vo.setManageGuarantee(WordConvertUtil.htmlToText(vo.getManageGuarantee()));
			vo.setManageKnowledgeRight(WordConvertUtil.htmlToText(vo.getManageKnowledgeRight()));
			vo.setManageRisk(WordConvertUtil.htmlToText(vo.getManageRisk()));

			List<SrpmsProjectTaskVo> taskDecompositionList = vo.getTaskDecompositionList();
			if (!CollectionUtils.isEmpty(taskDecompositionList)) {
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
			}
			// 年度计划格式化日期
			// List<SrpmsProjectTaskVo> taskPreYearList = vo.getTaskPreYearList();
			// if (!CollectionUtils.isEmpty(taskPreYearList)){
			// for (SrpmsProjectTaskVo taskVo :taskPreYearList) {
			// String importantPoint = taskVo.getImportantPoint();
			// if (StringUtils.isBlank(importantPoint)) {
			// continue;
			// }
			// taskVo.setImportantPoint(LocalDateUtils.parse(importantPoint,
			// LocalDateUtils.PARRERN_Y_M_D));
			// }
			// }
			int memberAllCount = 0; // 总人数
			int memberZGJ = 0; // 正高级人数
			int memberFGJ = 0; // 副高级人数
			int memberZJ = 0; // 中级人数
			int memberCGJ = 0; // 初高级人数
			int memberCCJ = 0; // 初初级
			int memberBSH = 0; // 博士后人数
			int memberCJ = 0;// 初级

			int xueweiXS = 0;// 学士
			int xueweiSS = 0;// 硕士
			int xueweiBS = 0;// 博士

			int xueliBK = 0;// 本科
			int xueliYJS = 0;// 研究生
			int xueliQT = 0; // 其他
			// 主要参与人员
			if (vo.getMainMemberList() != null) {
				for (SrpmsProjectPersonJoinVo personJoinVo : vo.getMainMemberList()) {
					memberAllCount++;
					if (personJoinVo.getPositionTitle() != null) {
						switch (personJoinVo.getPositionTitle()) {
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
					if (personJoinVo.getDegree() != null) {
						switch (personJoinVo.getDegree()) {
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
					if (personJoinVo.getEducation() != null) {
						switch (personJoinVo.getEducation()) {
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
					if (personJoinVo.getPersonCategory() != null && "3".equals(personJoinVo.getPersonCategory())) {
						memberBSH++;
					}
					personJoinVo.setPersonCategory(sysDictService.selectValueByCode(SysDictEnums.Person_Cat_APPLY,
							personJoinVo.getPersonCategory()));
					personJoinVo.setBirthDateString(personJoinVo.getBirthDate());
				}
			}

			// 项目首席专家及研究骨干目前承担其它相关国家科技计划课题情况 时间处理
			List<SrpmsProjectPersonJoinVo> topMemberOtherTaskList = vo.getTopMemberOtherTaskList();
			if (!CollectionUtils.isEmpty(topMemberOtherTaskList)) {
				for (SrpmsProjectPersonJoinVo joinVo : topMemberOtherTaskList) {
					joinVo.setOtherProjectDateStartStr(
							LocalDateUtils.format(joinVo.getOtherProjectDateStart(), LocalDateUtils.PARRERN_Y_M_D));
					joinVo.setOtherProjectDateEndStr(
							LocalDateUtils.format(joinVo.getOtherProjectDateEnd(), LocalDateUtils.PARRERN_Y_M_D));
					String role = null;
					if ("10".equals(joinVo.getOtherProjectRole())) {
						role = "牵头";
					} else if ("20".equals(joinVo.getOtherProjectRole())) {
						role = "参与";
					}
					joinVo.setOtherProjectRole(role);
				}
			}

			// 年度预算明细/计算总经费
			Double applyFunds = NumberUtils.DOUBLE_ZERO;
			List<SrpmsProjectBudgetDetailVo> budgetPreYearList = vo.getBudgetPreYearList();
			if (CollectionUtils.isEmpty(budgetPreYearList)) {
				vo.setBudgetPreYearList(
						BudgetDetailUtil.emptyBudgetListByCategory(ProjectCategoryEnums.INNOVATE_PRE.getHeader()));
			} else {
				for (SrpmsProjectBudgetDetailVo budgetVo : budgetPreYearList) {
					applyFunds += budgetVo.getBudgetAmount();
				}
			}
			vo.setApplyFunds(applyFunds);

			JSONObject newVoJson = JSONObject.parseObject(JSONObject.toJSONString(vo));
			if (!projectId.equals(0L)) {
				SrpmsProject project = projectService.getById(projectId);
				newVoJson.putAll(JSONObject.parseObject(JSONObject.toJSONString(project), Map.class));
				newVoJson.put("createTime", project.getCreateTime().format(formatter));
				newVoJson.put("projectActionDateStart", project.getProjectActionDateStart() == null ? ""
						: project.getProjectActionDateStart().format(formatterBirth));
				newVoJson.put("projectActionDateEnd", project.getProjectActionDateEnd() == null ? ""
						: project.getProjectActionDateEnd().format(formatterBirth));
				if (project.getProjectActionDateStart() != null
						&& project.getProjectActionDateStart().getYear() < 2019) {
					templateName = "template_apply_inn_bcoo";
				}
			}

			// 是否承担创新工程2016-2018年任务
			String isTaskOtherInnProjectStr = "□是 ▉否";
			boolean isTaskOtherInnProject = projectService.isManageProjectHistory(vo.getLeadPersonId());
			if (isTaskOtherInnProject) {
				isTaskOtherInnProjectStr = "▉是 □否";
			}
			newVoJson.put("isTaskOtherInnProjectStr", isTaskOtherInnProjectStr);

			// 预期成果类型
			List<String> achList = vo.getAchievementType().toJavaList(String.class);
			Map<String, String> dictMap = sysDictService.getDictByCategory(SysDictEnums.PRO_RELUST_TYPE.getCode());
			List<String> achNameList = WordConvertUtil.parseCodeListToText(dictMap, achList);
			newVoJson.put("achievementType", achNameList);

			// 所属领域
			String belongDomain = vo.getBelongDomain();
			if (StringUtils.isNotBlank(belongDomain)) {
				List<String> domainList = Lists.newArrayList(belongDomain);
				Map<String, String> domainMap = sysDictService.getDictByCategory(SysDictEnums.PRO_DOMAIN.getCode());
				List<String> domainNameList = WordConvertUtil.parseCodeListToText(domainMap, domainList);
				newVoJson.put("belongDomainList", domainNameList);
			}

			// 活动类型
			JSONArray activeType = vo.getActiveType();
			if (!CollectionUtils.isEmpty(activeType)) {
				List<String> activeList = JSONArray.parseArray(activeType.toJSONString(), String.class);
				Map<String, String> activeMap = sysDictService
						.getDictByCategory(SysDictEnums.PRO_ACTIVE_TYPE_BCOO_2019.getCode());
				List<String> activeNameList = WordConvertUtil.parseCodeListToText(activeMap, activeList);
				newVoJson.put("activeTypeList", activeNameList);
				if (activeNameList != null && activeNameList.size() == 4) {
					List<String> activeNameListA = activeNameList.subList(0, 2);
					List<String> activeNameListB = activeNameList.subList(2, 4);
					newVoJson.put("activeTypeListA", activeNameListA);
					newVoJson.put("activeTypeListB", activeNameListB);
				}
			}

			// 格式化时间日期
			dataMap.put("p", newVoJson);

			memberCJ = memberCGJ + memberCCJ;

			dataMap.put("memberAllCount", memberAllCount + "");
			dataMap.put("memberZGJ", memberZGJ + "");
			dataMap.put("memberFGJ", memberFGJ + "");
			dataMap.put("memberZJ", memberZJ + "");
			dataMap.put("memberCGJ", memberCGJ + "");
			dataMap.put("memberCCJ", memberCCJ + "");
			dataMap.put("memberCJ", memberCJ + "");
			dataMap.put("xueweiXS", xueweiXS + "");
			dataMap.put("xueweiSS", xueweiSS + "");
			dataMap.put("xueweiBS", xueweiBS + "");

			dataMap.put("xueliBK", xueliBK + "");
			dataMap.put("xueliYJS", xueliYJS + "");
			dataMap.put("xueliQT", xueliQT + "");

			dataMap.put("memberBSH", memberBSH + "");

			JSONObject leadPersonJson = vo.getLeadPerson();
			leadPersonJson.put("birthDate",
					LocalDateUtils.parse(leadPersonJson.getString("birthDate"), LocalDateUtils.PARRERN_Y_M_D));
			dataMap.put("leadPerson", leadPersonJson);
			dataMap.put("leadDept", vo.getLeadDept());

			JSONObject bothTopExpertPerson = vo.getBothTopExpertPerson();
			bothTopExpertPerson.put("birthDate",
					LocalDateUtils.parse(bothTopExpertPerson.getString("birthDate"), LocalDateUtils.PARRERN_Y_M_D));
			dataMap.put("bothTopExpertPerson", bothTopExpertPerson);

			String filename = "重大协同项目申请书_" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS")
					+ RandomUtils.nextInt(0, 9999) + ".docx";
			String finalWordName = wordExportService.exportWord(templateName, dataMap, filename);

			fileIn = new FileInputStream(finalWordName);
			fileUrl = finalWordName;
		} catch (Exception e) {
			log.error("导出word异常。", e);
			throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
		} finally {
			if (fileIn != null) {
				try {
					fileIn.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return fileUrl;
	}

	/**
	 * 导入数据
	 *
	 * @param wordFileUrl
	 *            word文件URL地址
	 * @return
	 */
	@Override
	public SrpmsProjectApplyInnBcooSaveVo importWord(String wordFileUrl) {
		try {
			SrpmsProjectApplyInnBcooSaveVo innBcooSaveVo = new SrpmsProjectApplyInnBcooSaveVo();
			String classPath = new File(ResourceUtils.getURL("").getPath()).getAbsoluteFile().toString();
			String htmlFilePath = WordConvertUtil.wordConvertToHtml(wordFileUrl, classPath);
			Document document = Jsoup.parse(new File(htmlFilePath), "utf-8");
			Elements tableElements = document.getElementsByTag("table");
			// 所属领域
			Elements domains = document.getElementsMatchingOwnText("所属领域");
			if (!CollectionUtils.isEmpty(domains)) {
				Element element = domains.get(0);
				String domainStr = StringUtils.trim(StringUtils.replace(element.parent().text(), "所属领域：", ""));
				if (StringUtils.isNotBlank(domainStr)) {
					Map<String, String> domainMap = sysDictService.getDictByCategory(SysDictEnums.PRO_DOMAIN.getCode());
					JSONArray array = WordConvertUtil.parseTextsToCodes(domainMap, domainStr);
					if (!CollectionUtils.isEmpty(array)) {
						innBcooSaveVo.setBelongDomain(array.getString(0));
					}
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
						innBcooSaveVo.setProjectActionDateStart(
								DateUtil.chinaToLocalDateTime(prodates[0], DateUtil.PATTERN_CLASSICAL_SIMPLE));
						innBcooSaveVo.setProjectActionDateEnd(
								DateUtil.chinaToLocalDateTime(prodates[1], DateUtil.PATTERN_CLASSICAL_SIMPLE));
					}
				}
			}
			// 解析文档提取表格数据
			// 项目基本信息
			Element baseInfoTable = tableElements.get(0);
			innBcooSaveVo.setProjectName(extractCellFromTable(baseInfoTable, 0, 1));
			innBcooSaveVo.setLeadPersonWorkTime(NumberUtils.toLong(extractCellFromTable(baseInfoTable, 8, 6)));
			innBcooSaveVo.setBothTopWorkTime(NumberUtils.toLong(extractCellFromTable(baseInfoTable, 14, 6)));
			// 联系人信息
			JSONObject leadDept = new JSONObject();
			leadDept.put("contactsName", extractCellFromTable(baseInfoTable, 5, 1));
			leadDept.put("phone", extractCellFromTable(baseInfoTable, 5, 3));
			leadDept.put("faxNumber", extractCellFromTable(baseInfoTable, 6, 1));
			innBcooSaveVo.setLeadDept(leadDept);
			// 项目活动类型
			Map<String, String> activeMap = sysDictService.getDictByCategory(SysDictEnums.PRO_ACTIVE_TYPE.getCode());
			innBcooSaveVo.setActiveType(
					WordConvertUtil.parseTextsToCodes(activeMap, extractCellFromTable(baseInfoTable, 1, 1)));

			// 预期成果类型CODE
			Map<String, String> dictMap = sysDictService.getDictByCategory(SysDictEnums.PRO_RELUST_TYPE.getCode());
			innBcooSaveVo.setAchievementType(
					WordConvertUtil.parseTextsToCodes(dictMap, extractCellFromTable(baseInfoTable, 2, 1)));
			/**
			 * 共同首席专家信息
			 * {"address":"","positionTitle":"","education":"","gender":"","idCard":"","projectCommitmentUnit":"","birthDate":"",
			 * "talentPlan":[],"telPhone":"","idCardType":"","major":"","phone":"","name":"","faxNumber":"","email":""}
			 */
			JSONObject bothTopExpertPerson = new JSONObject();
			bothTopExpertPerson.put("name", extractCellFromTable(baseInfoTable, 14, 2));
			bothTopExpertPerson.put("gender", extractCellFromTable(baseInfoTable, 14, 4));
			bothTopExpertPerson.put("birthDate", DateUtil
					.chinaToLocalDateTime(extractCellFromTable(baseInfoTable, 15, 1), LocalDateUtils.PARRERN_Y_M_D));
			bothTopExpertPerson.put("positionTitle", extractCellFromTable(baseInfoTable, 15, 3));
			bothTopExpertPerson.put("education", extractCellFromTable(baseInfoTable, 16, 1));
			bothTopExpertPerson.put("major", extractCellFromTable(baseInfoTable, 16, 3));
			bothTopExpertPerson.put("phone", extractCellFromTable(baseInfoTable, 17, 3));
			bothTopExpertPerson.put("faxNumber", extractCellFromTable(baseInfoTable, 18, 1));
			bothTopExpertPerson.put("email", extractCellFromTable(baseInfoTable, 18, 3));
			bothTopExpertPerson.put("idCardType", extractCellFromTable(baseInfoTable, 19, 1));
			bothTopExpertPerson.put("idCard", extractCellFromTable(baseInfoTable, 19, 3));
			innBcooSaveVo.setBothTopExpertPerson(bothTopExpertPerson);
			// 联合申请单位信息
			List<SrpmsProjectDeptJoinVo> jointApplicantUnit = new ArrayList<>();
			List<List<String>> joinUnitArray = extractListFromTable(baseInfoTable, 21, 1);
			Integer coopDeptRowID = 21;
			if (!CollectionUtils.isEmpty(joinUnitArray)) {
				for (List<String> rowData : joinUnitArray) {
					coopDeptRowID++;
					if (rowData.size() != 5) {
						break;
					}
					if (StringUtils.isBlank(rowData.get(0)) || StringUtils.isBlank(rowData.get(1))) {
						continue;
					}
					SrpmsProjectDeptJoinVo joinUnitVo = new SrpmsProjectDeptJoinVo();
					joinUnitVo.setDeptName(rowData.get(1));
					joinUnitVo.setTaskLeaderName(rowData.get(2));
					joinUnitVo.setPhone(rowData.get(3));
					joinUnitVo.setEmail(rowData.get(4));
					jointApplicantUnit.add(joinUnitVo);
				}
			}
			innBcooSaveVo.setJointApplicantUnit(jointApplicantUnit);
			// 国际合作单位信息
			List<SrpmsProjectDeptJoinVo> coopDeptList = new ArrayList<>();
			List<List<String>> coopDeptArray = extractListFromTable(baseInfoTable, coopDeptRowID, 1);
			if (!CollectionUtils.isEmpty(coopDeptArray)) {
				for (List<String> rowData : coopDeptArray) {
					if (rowData.size() != 5) {
						break;
					}
					if (StringUtils.isBlank(rowData.get(0)) || StringUtils.isBlank(rowData.get(1))) {
						continue;
					}
					SrpmsProjectDeptJoinVo coopVo = new SrpmsProjectDeptJoinVo();
					coopVo.setDeptName(rowData.get(1));
					coopVo.setTaskLeaderName(rowData.get(2));
					coopVo.setCountry(rowData.get(3));
					coopVo.setEmail(rowData.get(4));
					coopDeptList.add(coopVo);
				}
			}
			innBcooSaveVo.setCoopDeptList(coopDeptList);

			// 解析项目参与人员 lixin 不解析人员了
			// List<SrpmsProjectPersonJoinVo> mostMemberList = new ArrayList<>();
			// List<List<String>> mostMemberArray =
			// extractListFromTable(tableElements.get(1), 1, 3);
			// for (List<String> personStr : mostMemberArray) {
			// if (StringUtils.isBlank(personStr.get(0)) ||
			// StringUtils.isBlank(personStr.get(1))) {
			// break;
			// }
			// SrpmsProjectPersonJoinVo personJoinVo = new SrpmsProjectPersonJoinVo();
			// personJoinVo.setPersonName(StringConvertUtil.convertNull(personStr.get(1)));
			// personJoinVo.setGender(StringConvertUtil.convertNull(personStr.get(2)));
			// personJoinVo.setBirthDate(personStr.get(3));
			// personJoinVo.setPositionTitle(StringConvertUtil.convertNull(personStr.get(4)));
			// personJoinVo.setPersonCategory(sysDictService.selectCodeByLikeValue(SysDictEnums.Person_Cat_APPLY,
			// StringConvertUtil.convertNull(personStr.get(5))));
			// personJoinVo.setDegree(StringConvertUtil.convertNull(personStr.get(6)));
			// personJoinVo.setDeptName(StringConvertUtil.convertNull(personStr.get(7)));
			// personJoinVo.setPhone(StringConvertUtil.convertNull(personStr.get(8)));
			// personJoinVo.setIdCard(StringConvertUtil.convertNull(personStr.get(9)));
			// String workerTime = personStr.get(10);
			// if (StringUtils.isNotBlank(workerTime) && StringUtils.isNumeric(workerTime))
			// {
			// personJoinVo.setWorkPerYear(NumberUtils.toInt(workerTime));
			// }
			// personJoinVo.setBelongTask(StringConvertUtil.convertNull(personStr.get(11)));
			// personJoinVo.setPersonRole(StringConvertUtil.convertNull(personStr.get(12)));
			// mostMemberList.add(personJoinVo);
			// }
			// innBcooSaveVo.setMainMemberList(mostMemberList);
			// 摘要
			Element abstractTable = tableElements.get(2);
			innBcooSaveVo.setProjectAbstract(extractCellFromTable(abstractTable, 0, 0, true));

			// 立项必要性
			Element necessayTable = tableElements.get(3);
			innBcooSaveVo.setApprovalNecessay(extractCellFromTable(necessayTable, 0, 0, true));

			// 项目总体目标
			Element projectTargetTable = tableElements.get(4);
			innBcooSaveVo.setProjectTarget(extractCellFromTable(projectTargetTable, 0, 0, true));

			// 年度任务
			Element taskPreYearListTable = tableElements.get(5);
			List<SrpmsProjectTaskVo> taskPreYearList = new ArrayList<>();
			List<List<String>> taskPreYearListArray = extractListFromTable(taskPreYearListTable, 1, 0);
			if (!CollectionUtils.isEmpty(taskPreYearListArray)) {
				for (List<String> rowData : taskPreYearListArray) {
					if (StringUtils.isBlank(rowData.get(0)) || StringUtils.isBlank(rowData.get(1))) {
						continue;
					}
					SrpmsProjectTaskVo taskVo = new SrpmsProjectTaskVo();
					taskVo.setTaskYear(rowData.get(1));
					taskVo.setTaskName(rowData.get(2));
					taskVo.setTaskTargetYear(rowData.get(3));
					taskVo.setImportantPoint(rowData.get(4));
					taskPreYearList.add(taskVo);
				}
			}
			innBcooSaveVo.setTaskPreYearList(taskPreYearList);

			// （三）项目任务分解情况
			Element taskDecompositionListTable = tableElements.get(6);
			List<SrpmsProjectTaskVo> taskDecompositionList = new ArrayList<>();
			List<List<String>> taskDecompositionListArray = extractListFromTable(taskDecompositionListTable, 1, 0);
			if (!CollectionUtils.isEmpty(taskDecompositionListArray)) {
				for (List<String> rowData : taskDecompositionListArray) {
					if (StringUtils.isBlank(rowData.get(0)) || StringUtils.isBlank(rowData.get(1))) {
						continue;
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
			innBcooSaveVo.setTaskDecompositionList(taskDecompositionList);

			// 联合任务申报单位
			Element jointUnitTaskTable = tableElements.get(7);
			List<SrpmsProjectTaskVo> jointUnitTaskList = new ArrayList<>();
			List<List<String>> jointUnitTaskArray = extractListFromTable(jointUnitTaskTable, 1, 0);
			if (!CollectionUtils.isEmpty(jointUnitTaskArray)) {
				for (List<String> rowData : jointUnitTaskArray) {
					if (StringUtils.isBlank(rowData.get(0)) || StringUtils.isBlank(rowData.get(1))) {
						continue;
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
			innBcooSaveVo.setJointUnitTask(jointUnitTaskList);

			// 主要研究内容
			Element researchContentMainTable = tableElements.get(8);
			innBcooSaveVo.setResearchContentMain(extractCellFromTable(researchContentMainTable, 0, 0, true));
			// 拟采取的研究方法、技术路线及其可行性分析
			Element researchContentMethodTable = tableElements.get(9);
			innBcooSaveVo.setResearchContentMethod(extractCellFromTable(researchContentMethodTable, 0, 0, true));
			// 国际合作与交流方案
			Element researchContentInterflowTable = tableElements.get(10);
			innBcooSaveVo.setResearchContentInterflow(extractCellFromTable(researchContentInterflowTable, 0, 0, true));
			// 成果转化应用和科普方案
			Element researchContentUsePlanTable = tableElements.get(11);
			innBcooSaveVo.setResearchContentUsePlan(extractCellFromTable(researchContentUsePlanTable, 0, 0, true));
			// 项目预期的主要创新点
			Element researchContentInnovateTable = tableElements.get(12);
			innBcooSaveVo.setResearchContentInnovate(extractCellFromTable(researchContentInnovateTable, 0, 0, true));
			// 项目成果的呈现形式及描述
			Element achievementFormTable = tableElements.get(13);
			innBcooSaveVo.setAchievementForm(extractCellFromTable(achievementFormTable, 0, 0, true));
			// 项目成果的预期经济
			Element achievementBenefitTable = tableElements.get(14);
			innBcooSaveVo.setAchievementBenefit(extractCellFromTable(achievementBenefitTable, 0, 0, true));
			// 目参与单位的选择原因及其优势
			Element superiorityDeptChooseTable = tableElements.get(15);
			innBcooSaveVo.setSuperiorityDeptChoose(extractCellFromTable(superiorityDeptChooseTable, 0, 0, true));
			// 项目牵头和参加单位与课题实施相关的实力和基础
			Element superiorityDeptBasicTable = tableElements.get(16);
			innBcooSaveVo.setSuperiorityDeptBasic(extractCellFromTable(superiorityDeptBasicTable, 0, 0, true));
			// 申报单位相关科研条件支撑状况
			Element superiorityDeptSuportTable = tableElements.get(17);
			innBcooSaveVo.setSuperiorityDeptSuport(extractCellFromTable(superiorityDeptSuportTable, 0, 0, true));
			// 国际合作单位及团队情况
			Element superiorityDeptAbroadTable = tableElements.get(18);
			innBcooSaveVo.setSuperiorityDeptAbroad(extractCellFromTable(superiorityDeptAbroadTable, 0, 0, true));
			// 项目首席专家及骨干成员的情况
			Element mainstayMemberNoteTable = tableElements.get(19);
			innBcooSaveVo.setMainstayMemberNote(extractCellFromTable(mainstayMemberNoteTable, 0, 0, true));

			// 项目首席专家及研究骨干目前承担其它相关国家科技计划课题情况
			Element topMenberTable = tableElements.get(20);
			List<SrpmsProjectPersonJoinVo> topMemberOtherTaskList = new ArrayList<>();
			List<List<String>> topMenberArray = extractListFromTable(topMenberTable, 1, 0);
			if (!CollectionUtils.isEmpty(topMenberArray)) {
				Map<String, String> roleMap = Maps.newHashMap();
				roleMap.put("10", "牵头");
				roleMap.put("20", "参与");
				for (List<String> rowData : topMenberArray) {
					if (StringUtils.isBlank(rowData.get(0)) || StringUtils.isBlank(rowData.get(1))) {
						continue;
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
			innBcooSaveVo.setTopMemberOtherTaskList(topMemberOtherTaskList);

			// 项目组织管理、协同机制和保障措施
			Element manageGuaranteeTable = tableElements.get(21);
			innBcooSaveVo.setManageGuarantee(extractCellFromTable(manageGuaranteeTable, 0, 0, true));
			// 知识产权对策、成果管理及合作权益分配
			Element manageKnowledgeRightTable = tableElements.get(22);
			innBcooSaveVo.setManageKnowledgeRight(extractCellFromTable(manageKnowledgeRightTable, 0, 0, true));
			// 风险分析及对策
			Element manageRiskTable = tableElements.get(23);
			innBcooSaveVo.setManageRisk(extractCellFromTable(manageRiskTable, 0, 0, true));

			// 预算经费 lixin 不解析预算经费
			// List<SrpmsProjectBudgetDetailVo> budgetPreYearList = new ArrayList<>();
			// for (int i = 24; i < tableElements.size(); i++) {
			// Element tableElement = tableElements.get(i);
			// // 获取header
			// String tableHeader = extractCellFromTable(tableElement, 0, 0);
			// if (StringUtils.isBlank(tableHeader) || !tableHeader.contains("年度经费概算")) {
			// break;
			// }
			//
			// SrpmsProjectBudgetDetailVo budgetDetailVo = new SrpmsProjectBudgetDetailVo();
			// budgetDetailVo.setBudgetAmount(NumberUtils.toDouble(extractCellFromTable(tableElement,
			// 2, 1)));
			// budgetDetailVo.setBudgetYear(StringConvertUtil.convert(tableHeader));
			// // 生成科目明细字符串
			// List<Map<String, Object>> budgetSubjects = new ArrayList<>();
			// List<List<String>> budgetSubStrList = extractListFromTable(tableElement, 3,
			// 0);
			// int index = 1;
			// for (List<String> budgetSubStr : budgetSubStrList) {
			// Map<String, Object> subjectMap = new HashMap<>();
			// subjectMap.put("serial", index++);
			// subjectMap.put("name", WordImportUtil.subStringSerial(budgetSubStr.get(0)));
			// subjectMap.put("amount", StringUtils.isBlank(budgetSubStr.get(1)) ? null
			// :
			// budgetSubStr.get(1).trim().substring(budgetSubStr.get(1).trim().indexOf("\\.")
			// + 1));
			// budgetSubjects.add(subjectMap);
			// }
			// budgetDetailVo.setBudgetDetail(JSON.parseArray(JSON.toJSONString(budgetSubjects)));
			// budgetPreYearList.add(budgetDetailVo);
			// }
			// innBcooSaveVo.setBudgetPreYearList(budgetPreYearList);

			return innBcooSaveVo;

		} catch (Exception e) {
			log.error("解析word文件发生异常.", e);
			throw new BaseException(PlatformErrorType.IMPORT_TEMPLATE_ERROR);
		}
	}

	@Override
	public JSONObject get(long projectId) {

		return this.get(projectId, null, null);
	}
}
