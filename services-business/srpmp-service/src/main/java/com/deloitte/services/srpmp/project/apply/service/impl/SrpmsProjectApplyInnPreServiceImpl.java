package com.deloitte.services.srpmp.project.apply.service.impl;

import static com.deloitte.services.srpmp.common.util.HTMLParseUtil.extractCellFromTable;
import static com.deloitte.services.srpmp.common.util.HTMLParseUtil.extractListFromTable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
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
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyInnPreExportVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyInnPreSubmitVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectDeptJoinVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonJoinVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectTaskVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
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
import com.deloitte.services.srpmp.common.util.DateParseUtil;
import com.deloitte.services.srpmp.common.util.JSONConvert;
import com.deloitte.services.srpmp.common.util.WordConvertUtil;
import com.deloitte.services.srpmp.project.apply.entity.SrpmsProjectApplyInnPre;
import com.deloitte.services.srpmp.project.apply.mapper.SrpmsProjectApplyInnPreMapper;
import com.deloitte.services.srpmp.project.apply.service.ISrpmsProjectApplyInnPreService;
import com.deloitte.services.srpmp.project.apply.util.ProjectCheckUtils;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectFlowService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectTaskService;
import com.deloitte.services.srpmp.project.base.service.impl.PdfMergeServiceImpl;
import com.deloitte.services.srpmp.project.budget.service.ISrpmsProjectBudgetApplyService;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-19
 * @Description : SrpmsProjectApplyInnPre服务实现类
 * @Modified :
 */
@Service
@Transactional
@Slf4j
public class SrpmsProjectApplyInnPreServiceImpl extends
		ServiceImpl<SrpmsProjectApplyInnPreMapper, SrpmsProjectApplyInnPre> implements ISrpmsProjectApplyInnPreService {

	@Autowired
	private ISrpmsProjectService projectService;

	@Autowired
	private ISrpmsProjectBudgetApplyService budgetApplyService;

	@Autowired
	SrpmsProjectApplyInnCommonServiceImpl commonService;

	@Autowired
	private ISysDictService sysDictService;

	@Autowired
	private WordExportServiceImpl wordExportService;

	@Autowired
	private FileOperatorFeignService fileOperatorFeignService;

	@Autowired
	private ISrpmsProjectTaskService taskService;

	@Autowired
	private ISrpmsProjectFlowService flowServicel;

	@Autowired
	private PdfMergeServiceImpl pdfMergeService;

	@Autowired
	private ISrpmsCommonNclobService commonNclobService;

	@Override
	public Long saveOrUpdateApplyInnPre(SrpmsProjectApplyInnPreSubmitVo vo, DeptVo deptVo) {

		// 项目ID
		long projectId = 0l;

		if (vo.getMainMemberList() != null && vo.getMainMemberList().size() != 0) {
			for (int i = 0; i < vo.getMainMemberList().size(); i++) {
				vo.getMainMemberList().get(i).setBelongTask(vo.getProjectName());
			}
		}
		// 重大创新项目信息
		SrpmsProjectApplyInnPre projectEntity = JSONObject.parseObject(JSON.toJSONString(vo),
				SrpmsProjectApplyInnPre.class);

		JSONObject projectJson = JSONObject.parseObject(JSONObject.toJSONString(vo));

		if (vo.getId() == null || vo.getId() == 0l) {
			projectId = commonService.insertCommonInfo(projectJson, ProjectCategoryEnums.INNOVATE_PRE, deptVo);
			projectEntity.setId(projectId);
			this.save(projectEntity);
		} else {
			// 校验项目状态 已提交的状态后将无修改保存
			SrpmsProject project = projectService.getById(vo.getId());
			if (!SrpmsProjectStatusEnums.PEROJECT_NOT_SUBMIT.getCode().equals(project.getStatus())) {
				throw new BaseException(SrpmsErrorType.APPLY_HAVE_SUBMITTED);
			}
			projectId = commonService.saveOrUpdateCommonInfo(projectJson, ProjectCategoryEnums.INNOVATE_PRE);
			this.updateById(projectEntity);
		}
		// 分解任务
		List<SrpmsProjectTaskVo> subTaskList = taskService
				.queryTaskListByTaskCategory(TaskCategoryEnums.APPLY_INNOVATE_TASK_DECOMPOSITION, projectId);
		SrpmsProjectTaskVo subTask = null;
		if (subTaskList != null && subTaskList.size() != 0) {
			subTask = subTaskList.get(0);

		} else {
			subTask = new SrpmsProjectTaskVo();
		}
		subTaskList = new ArrayList<SrpmsProjectTaskVo>();
		subTask.setDeptId(projectJson.getJSONObject("leadDept").getString("deptId"));
		subTask.setLeadPerson(projectJson.getJSONObject("leadPerson").getString("id"));
		JSONObject taskLeadPerson = new JSONObject();
		taskLeadPerson.put("personId", projectJson.getJSONObject("leadPerson").getString("id"));
		taskLeadPerson.put("personName", projectJson.getJSONObject("leadPerson").getString("name"));
		taskLeadPerson.put("deptCode", vo.getLeadDept().getString("deptCode"));
		taskLeadPerson.put("deptName", vo.getLeadDept().getString("deptName"));
		subTask.setLeadPersonInfo(taskLeadPerson);

		subTask.setProjectId(projectId + "");
		subTask.setDeptName(vo.getLeadDept().getString("deptName"));
		subTask.setTaskName(vo.getProjectName());
		subTask.setDeptCode(vo.getLeadDept().getString("deptCode"));

		JSONArray joinPersonInfo = new JSONArray();
		JSONArray joinPerson = new JSONArray();
		if (vo.getMainMemberList() != null) {
			for (int i = 0; i < vo.getMainMemberList().size(); i++) {
				SrpmsProjectPersonJoinVo member = vo.getMainMemberList().get(i);
				JSONObject taskJoinPerson = new JSONObject();
				taskJoinPerson.put("personId", member.getPersonId());
				taskJoinPerson.put("personName", member.getPersonName());
				taskJoinPerson.put("deptCode", vo.getLeadDept().getString("deptCode"));
				taskJoinPerson.put("deptName", vo.getLeadDept().getString("deptName"));
				joinPersonInfo.add(taskJoinPerson);

				joinPerson.add(member.getPersonId() + "");
			}
		}
		subTask.setJoinPersonInfo(joinPersonInfo);
		subTask.setJoinPerson(joinPerson);
		subTask.setAllocatedAmount("100");
		subTaskList.add(subTask);
		commonService.setTaskAndBudgetList(subTaskList, projectId);
		return projectId;
	}

	@Override
	public JSONObject queryApplyVoById(Long projectId, UserVo user, DeptVo dept) {

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

		return this.queryApplyVoById(projectId);
	}

	@Override
	public JSONObject queryApplyVoById(Long projectId) {

		JSONObject relust = commonService.queryCommonInfoById(projectId, ProjectCategoryEnums.INNOVATE_PRE);

		relust.putAll(JSONObject.parseObject(JSON.toJSONString(this.getById(projectId))));
		JSONConvert.convertJson(relust);

		// 总预算表
		relust.put("budgetAllList",
				taskService.queryTaskListByTaskCategory(TaskCategoryEnums.APPLY_INNOVATE_BUDGET_ALL, projectId));

		relust.put("subjectCategory", relust.getString("subjectCategory") == null ? ""
				: JSONArray.parseArray(relust.getString("subjectCategory")));
		return relust;
	}

	@Override
	public void submitApply(SrpmsProjectApplyInnPreSubmitVo vo, UserVo userVo, DeptVo deptVo) {

		if (vo.getId() == null) {
			throw new BaseException(SrpmsErrorType.BUDGET_NOT_COMPLETE);
		}
		// 校验预算书
		long id = vo.getId();

		// 校验申请书状态，只有未提交的申请书才能提交
		SrpmsProject project = projectService.getById(id);
		if (!SrpmsProjectStatusEnums.PEROJECT_NOT_SUBMIT.getCode().equals(project.getStatus())) {
			throw new BaseException(SrpmsErrorType.APPLY_HAVE_SUBMITTED);
		}

		// 保存申请书
		this.saveOrUpdateApplyInnPre(vo, deptVo);

		JSONObject budgetJson = budgetApplyService.queryBudgetApplyById(id);
		JSONObject applyJson = JSONObject.parseObject(JSON.toJSONString(vo));
		ProjectCheckUtils.checkApply(project, applyJson, budgetJson);

		// 更新项目状态
		projectService.submitProject(id);

		commonService.copyApplyToTaskInnPer(vo, budgetJson);

		// 生成申请书PDF
		this.generateApplyBookPdf(id, userVo, deptVo);

		// 生成预算书PDF
		budgetApplyService.generateBudgetApplyPdf(id, userVo, deptVo);

		log.info("先导专项，提交申请书，已更新项目状态, projectId:{}", id);
		// 发起流程
		flowServicel.startAuditProcess(id, VoucherTypeEnums.APPLY_BOOK, userVo, deptVo);

		log.info("先导专项，提交申请书，已发起流程, projectId{}", id);
	}

	/**
	 * word导入项目申请书
	 * 
	 * @param wordFileUrl
	 *            word文件URL地址
	 */
	@Override
	public SrpmsProjectApplyInnPreSubmitVo importWord(String wordFileUrl) {
		SrpmsProjectApplyInnPreSubmitVo vo = new SrpmsProjectApplyInnPreSubmitVo();
		try {
			// String classPath =
			// this.getClass().getClassLoader().getResource("").getPath();
			// File jarPath = new
			// File(ResourceUtils.getURL("").getPath()).getAbsoluteFile(); Person_Cat_APPLY

			String classPath = new File(ResourceUtils.getURL("").getPath()).getAbsoluteFile().toString();
			String htmlFilePath = WordConvertUtil.wordConvertToHtml(wordFileUrl, classPath);
			Document document = Jsoup.parse(new File(htmlFilePath), "utf-8");
			// 项目执行期限
			Elements actionTimeElements = document.getElementsMatchingOwnText("项目执行期限");
			if (actionTimeElements != null && actionTimeElements.size() > 0) {
				Element spanElement = actionTimeElements.get(0);
				if (spanElement.tagName().equals("span")) {
					Element parentElement = spanElement.parent();
					String parentText = parentElement.text();
					if (parentText != null && parentText.indexOf("：") > 0) {
						String actionTimeText = parentText.substring(parentText.indexOf("：") + 1);
						String[] actionTimeArr = actionTimeText.split("至");
						if (actionTimeArr.length == 2) {
							String actionTimeStart = actionTimeArr[0].trim();
							String actionTimeEnd = actionTimeArr[1].trim();
							vo.setProjectActionDateStart(DateParseUtil.parseLocalDateTime(actionTimeStart));
							vo.setProjectActionDateEnd(DateParseUtil.parseLocalDateTime(actionTimeEnd));
						}
					}
				}
			}

			Elements tableElements = document.getElementsByTag("table");

			// 项目基本信息
			Element projectBaseTable = tableElements.get(0);
			vo.setProjectName(extractCellFromTable(projectBaseTable, 0, 1));// 项目名称

			// 预期成果类型
			List<String> achievmentTypeCodeList = new ArrayList<>();
			String achievementTypeWordString = extractCellFromTable(projectBaseTable, 1, 1);
			if (StringUtils.isNotBlank(achievementTypeWordString)) {
				String[] achievArr = achievementTypeWordString.split("\\s+");

				for (String achiev : achievArr) {
					if (achiev != null && !achiev.startsWith("□")) {
						String achievmentTypeCode = sysDictService
								.selectCodeByValue(SysDictEnums.PRO_RELUST_TYPE_INN_PRE, achiev.substring(1));
						if (achievmentTypeCode != null) {
							achievmentTypeCodeList.add(achievmentTypeCode);
						}
					}
				}
				;
			}
			vo.setAchievementType(JSONObject.parseArray(JSONObject.toJSONString(achievmentTypeCodeList)));

			// 拟申请岗位数
			vo.setApplyPostNumber(NumberUtils.toLong(extractCellFromTable(projectBaseTable, 2, 1)));

			// 拟申请经费数
			vo.setApplyFunds(NumberUtils.toDouble(extractCellFromTable(projectBaseTable, 2, 3)));

			// 任务承担单位
			// SrpmsProjectDeptVo leadDept = new SrpmsProjectDeptVo();
			// leadDept.setDeptName(extractCellFromTable(projectBaseTable, 3, 2));
			// leadDept.setAddress(extractCellFromTable(projectBaseTable, 4,1));
			// leadDept.setZipCode(extractCellFromTable(projectBaseTable, 4,3));
			// leadDept.setContactsName(extractCellFromTable(projectBaseTable, 5,1));
			// leadDept.setPhone(extractCellFromTable(projectBaseTable, 5,3));
			// leadDept.setEmail(extractCellFromTable(projectBaseTable, 6, 1));
			// leadDept.setFaxNumber(extractCellFromTable(projectBaseTable, 6, 3));
			// vo.setLeadDept(leadDept);

			// 任务负责人信息
			// SrpmsProjectPersonVo leadPerson = new SrpmsProjectPersonVo();
			// leadPerson.setPersonName(extractCellFromTable(projectBaseTable, 7, 2));
			// leadPerson.setGender(extractCellFromTable(projectBaseTable, 7, 4));
			// leadPerson.setBirthDate(DateParseUtil.parseLocalDateTime(extractCellFromTable(projectBaseTable,
			// 8,1)));
			// leadPerson.setPositionTitle(sysDictService.selectCodeByValue(SysDictEnums.PRO_TITLE,
			// extractCellFromTable(projectBaseTable, 8, 3)));
			// leadPerson.setDegree(sysDictService.selectCodeByValue(SysDictEnums.PRO_DEGREE,
			// extractCellFromTable(projectBaseTable, 9, 1)));
			// leadPerson.setMajor(sysDictService.selectCodeByValue(SysDictEnums.PRO_MAJOR,
			// extractCellFromTable(projectBaseTable, 9, 3)));
			// leadPerson.setTelPhone(extractCellFromTable(projectBaseTable, 10, 1));
			// leadPerson.setMobile(extractCellFromTable(projectBaseTable, 10, 3));
			// leadPerson.setFaxNumber(extractCellFromTable(projectBaseTable, 11, 1));
			// leadPerson.setEmail(extractCellFromTable(projectBaseTable, 11, 3));
			// leadPerson.setIdCardType(extractCellFromTable(projectBaseTable, 12, 1));
			// leadPerson.setIdCard(extractCellFromTable(projectBaseTable, 12, 3));
			// vo.setLeadPerson(leadPerson);

			// 合作国家地区机构信息
			List<SrpmsProjectDeptJoinVo> coopDeptList = new ArrayList<>();
			List<List<String>> coopDeptArray = extractListFromTable(projectBaseTable, 14, 0);
			int cooIndex = 1;
			for (List<String> coopDept : coopDeptArray) {
				if (StringUtils.isBlank(coopDept.get(0))) {
					break;
				}
				SrpmsProjectDeptJoinVo deptJoinVo = new SrpmsProjectDeptJoinVo();
				deptJoinVo.setSort(cooIndex++);
				deptJoinVo.setCountry(coopDept.get(1));
				deptJoinVo.setDeptName(coopDept.get(2));
				deptJoinVo.setTaskLeaderName(coopDept.get(3));
				deptJoinVo.setEmail(coopDept.get(4));
				coopDeptList.add(deptJoinVo);
			}
			vo.setCoopDeptList(coopDeptList);

			// 主要参与人员 不解析主要参与人员
			// List<SrpmsProjectPersonJoinVo> mostMemberList = new ArrayList<>();
			// List<List<String>> mostMemberArray =
			// extractListFromTable(tableElements.get(1), 1, 3);
			// for (List<String> personStr: mostMemberArray){
			// if (StringUtils.isBlank(personStr.get(0))){
			// break;
			// }
			// SrpmsProjectPersonJoinVo personJoinVo = new SrpmsProjectPersonJoinVo();
			// personJoinVo.setPersonName(personStr.get(1));
			// personJoinVo.setGender(personStr.get(2));
			// personJoinVo.setBirthDate((personStr.get(3)));
			// personJoinVo.setPositionTitle(personStr.get(4));
			// personJoinVo.setPersonCategory(sysDictService.selectCodeByValue(SysDictEnums.Person_Cat_APPLY,
			// personStr.get(5)));
			// personJoinVo.setDegree(personStr.get(6));
			// personJoinVo.setDeptName(personStr.get(7));
			// personJoinVo.setPhone(personStr.get(8));
			// personJoinVo.setIdCard(personStr.get(9));
			// if (StringUtils.isNotBlank(personStr.get(10)) &&
			// StringUtils.isNumeric(personStr.get(10))){
			// personJoinVo.setWorkPerYear(NumberUtils.toInt(personStr.get(10)));
			// }
			// mostMemberList.add(personJoinVo);
			// }
			// vo.setMainMemberList(mostMemberList);

			// tableindex 5: 年度任务、考核指标和时间节点。
			List<SrpmsProjectTaskVo> taskPreYearList = new ArrayList<>();
			List<List<String>> taskPreYearStrList = extractListFromTable(tableElements.get(5), 1, 0);
			for (List<String> taskStr : taskPreYearStrList) {
				if (StringUtils.isBlank(taskStr.get(0))) {
					break;
				}
				SrpmsProjectTaskVo taskVo = new SrpmsProjectTaskVo();
				taskVo.setTaskYear(taskStr.get(0));
				taskVo.setTaskName(taskStr.get(1));
				taskVo.setTaskTargetYear(taskStr.get(2));
				taskVo.setImportantPoint(taskStr.get(3));
				taskPreYearList.add(taskVo);
			}
			vo.setTaskPreYearList(taskPreYearList);

			// tableindex 11: 任务概算及筹资方案 不解析年度预算
			// List<SrpmsProjectBudgetDetailVo> budgetPreYearList = new ArrayList<>();
			// for (int i = 11; i < tableElements.size(); i++){
			// Element tableElement = tableElements.get(i);
			// //获取header
			// String tableHeader = extractCellFromTable(tableElement, 0, 0);
			// if (StringUtils.isBlank(tableHeader) || !tableHeader.contains("年度经费概算")){
			// break;
			// }
			// SrpmsProjectBudgetDetailVo budgetDetailVo = new SrpmsProjectBudgetDetailVo();
			// budgetDetailVo.setBudgetAmount(NumberUtils.toDouble(extractCellFromTable(tableElement,
			// 2, 1)));
			// budgetDetailVo.setBudgetYear(NumberUtils.toInt(tableHeader.trim().substring(0,4)));
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
			// vo.setBudgetPreYearList(budgetPreYearList);

			vo.setProjectAbstract(extractCellFromTable(tableElements.get(2), 0, 0, true));// 任务摘要
			vo.setApprovalNecessay(extractCellFromTable(tableElements.get(3), 0, 0, true));// 立项必要性
			vo.setProjectTarget(extractCellFromTable(tableElements.get(4), 0, 0, true));// 任务目标
			vo.setResearchContentMain(extractCellFromTable(tableElements.get(6), 0, 0, true));// 主要研究内容
			vo.setResearchContentMethod(extractCellFromTable(tableElements.get(7), 0, 0, true));// 拟采取的研究方法、技术路线及其可行性分析
			vo.setAchievementForm(extractCellFromTable(tableElements.get(8), 0, 0, true));// 项目成果的呈现形式及描述
			vo.setAchievementBenefit(extractCellFromTable(tableElements.get(9), 0, 0, true));// 项目成果的预期经济、社会效益
			vo.setSuperiorityDeptBasic(extractCellFromTable(tableElements.get(10), 0, 0, true));// 简述前期基础及取得的成果等

			// 删除html临时文件
			WordConvertUtil.delHtmlFile(htmlFilePath);
			return vo;
		} catch (Exception e) {
			log.error("解析word文件发生异常.", e);
			throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
		}
	}

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

	public String exportWordFile(Long projectId, UserVo userVo, DeptVo deptVo) {
		InputStream fileIn = null;
		String fileUrl = null;
		try {
			DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
			DateTimeFormatter formatterLess = DateTimeFormatter.ofPattern("yyyy年MM月");
			DateTimeFormatter formatterBirth = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			JSONObject voJson = this.queryApplyVoById(projectId, userVo, deptVo);

			SrpmsProjectApplyInnPreExportVo vo = JSONObject.parseObject(voJson.toJSONString(),
					SrpmsProjectApplyInnPreExportVo.class);

			Map<String, Object> dataMap = new HashMap<>();

			vo.setProjectAbstract(WordConvertUtil.htmlToText(vo.getProjectAbstract()));
			vo.setApprovalNecessay(WordConvertUtil.htmlToText(vo.getApprovalNecessay()));
			vo.setProjectTarget(WordConvertUtil.htmlToText(vo.getProjectTarget()));
			vo.setResearchContentMain(WordConvertUtil.htmlToText(vo.getResearchContentMain()));
			vo.setAchievementForm(WordConvertUtil.htmlToText(vo.getAchievementForm()));
			vo.setAchievementBenefit(WordConvertUtil.htmlToText(vo.getAchievementBenefit()));
			vo.setSuperiorityDeptBasic(WordConvertUtil.htmlToText(vo.getSuperiorityDeptBasic()));
			vo.setResearchContentMethod(WordConvertUtil.htmlToText(vo.getResearchContentMethod()));

			// 预期成果类型
			List<String> achList = vo.getAchievementType();
			Map<String, String> dictMap = sysDictService
					.getDictByCategory(SysDictEnums.PRO_RELUST_TYPE_INN_PRE.getCode());
			List<String> achNameList = WordConvertUtil.parseCodeListToText(dictMap, achList);
			vo.setAchievementType(achNameList);

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

			// 年度预算明细
			if (vo.getBudgetPreYearList() == null || vo.getBudgetPreYearList().size() == 0) {
				vo.setBudgetPreYearList(
						BudgetDetailUtil.emptyBudgetListByCategory(ProjectCategoryEnums.INNOVATE_PRE.getHeader()));
			}

			JSONObject newVoJson = JSONObject.parseObject(JSONObject.toJSONString(vo));
			if (!projectId.equals(0L)) {
				SrpmsProject project = projectService.getById(projectId);
				newVoJson.putAll(JSONObject.parseObject(JSONObject.toJSONString(project), Map.class));
				newVoJson.put("createTime", project.getCreateTime().format(formatter));
				newVoJson.put("projectActionDateStart", project.getProjectActionDateStart() == null ? ""
						: project.getProjectActionDateStart().format(formatterLess));
				newVoJson.put("projectActionDateEnd", project.getProjectActionDateEnd() == null ? ""
						: project.getProjectActionDateEnd().format(formatterLess));
			}

			// 格式化时间日期
			dataMap.put("p", newVoJson);
			dataMap.put("memberAllCount", memberAllCount + "");
			dataMap.put("memberZGJ", memberZGJ + "");
			dataMap.put("memberFGJ", memberFGJ + "");
			dataMap.put("memberZJ", memberZJ + "");
			dataMap.put("memberCGJ", memberCGJ + "");
			dataMap.put("memberCCJ", memberCCJ + "");
			dataMap.put("xueweiXS", xueweiXS + "");
			dataMap.put("xueweiSS", xueweiSS + "");
			dataMap.put("xueweiBS", xueweiBS + "");

			dataMap.put("xueliBK", xueliBK + "");
			dataMap.put("xueliYJS", xueliYJS + "");
			dataMap.put("xueliQT", xueliQT + "");

			dataMap.put("memberBSH", memberBSH + "");

			JSONObject leadPersonJson = JSONObject.parseObject(vo.getLeadPerson());
			// leadPersonJson.put("birthDate",
			// leadPersonJson.getString("birthDate")==null?"":LocalDateTime.parse(leadPersonJson.getString("birthDate"),
			// parseFormatter).format(formatterBirth));
			leadPersonJson.put("birthDate", leadPersonJson.getObject("birthDate", LocalDateTime.class) == null ? ""
					: leadPersonJson.getObject("birthDate", LocalDateTime.class).format(formatterBirth));
			dataMap.put("leadDept", JSONObject.parseObject(vo.getLeadDept()));
			dataMap.put("leadPerson", leadPersonJson);

			String filename = "先导专项任务申请书_" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS")
					+ RandomUtils.nextInt(0, 9999) + ".docx";
			String finalWordName = wordExportService.exportWord("template_apply_inn_pre", dataMap, filename);

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
			String pdfFilePath = this.exportPdfFile(projectId, userVo, deptVo);
			// WordConvertUtil.wordConvertToPdf(docxFilePath, pdfFilePath);
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
				throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
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

	public static void main(String[] args) {
		// String s = "{ \"id\": \"1\", \"deptName\": \"中国医学科学院\", \"deptCode\":
		// \"1852352\", \"zipCode\": \"100730\", \"address\": \"北京市东城区东单三条一号\",
		// \"contactsName\": \"雷院长\", \"phone\": \"13820190307\", \"email\":
		// \"1233125@mac.com\", \"faxNumber\": \"1243324\", \"deptQuality\": \"中央\",
		// \"deptManDepartment\": \"中国医学科学院\", \"deptSubjection\": \"1\",
		// \"deptLegalPersonName\": \"雷院长\", \"provinceCode\": \"1\", \"cityCode\":
		// \"北京市\", \"countyCode\": \"1\", \"bankAccountNameLoose\": null,
		// \"bankNameLoose\": null, \"bankAccountNumberLoose\": \"1\",
		// \"bankAccountNameBase\": \"1\", \"bankNameBase\": \"1\",
		// \"bankAccountNumberBase\": \"1\", \"createTime\": null, \"createBy\": \"1\",
		// \"updateTime\": null, \"updateBy\": \"1\", \"sourceDeptId\": \"1\",
		// \"orgCode\": \"1852352\" }";
		// JSONObject json = JSONObject.parseObject(s, JSONObject.class);
		// System.out.println(json.toJSONString());

		JSONObject ob = JSONObject.parseObject(null);
		System.out.println(ob);
	}

}
