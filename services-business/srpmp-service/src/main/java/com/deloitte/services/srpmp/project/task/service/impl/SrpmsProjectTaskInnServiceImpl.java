package com.deloitte.services.srpmp.project.task.service.impl;

import static com.deloitte.services.srpmp.common.util.HTMLParseUtil.extractCellFromTable;
import static com.deloitte.services.srpmp.common.util.HTMLParseUtil.extractListFromTable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
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
import com.deloitte.platform.api.isump.feign.DictFeignService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectDeptJoinVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonJoinVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectTaskVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.WordImportReqVo;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetDetailVo;
import com.deloitte.platform.api.srpmp.project.task.vo.ext.SrpmsProjectTaskInnExtVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.common.enums.BudgetCategoryEnums;
import com.deloitte.services.srpmp.common.enums.DeptJoinWayEnums;
import com.deloitte.services.srpmp.common.enums.PersonJoinWayEnums;
import com.deloitte.services.srpmp.common.enums.ProjectCategoryEnums;
import com.deloitte.services.srpmp.common.enums.SrpmsProjectStatusEnums;
import com.deloitte.services.srpmp.common.enums.SysDictEnums;
import com.deloitte.services.srpmp.common.enums.TaskCategoryEnums;
import com.deloitte.services.srpmp.common.enums.VoucherTypeEnums;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.common.service.ISysDictService;
import com.deloitte.services.srpmp.common.service.impl.WordExportServiceImpl;
import com.deloitte.services.srpmp.common.util.JSONConvert;
import com.deloitte.services.srpmp.common.util.StringConvertUtil;
import com.deloitte.services.srpmp.common.util.WordConvertUtil;
import com.deloitte.services.srpmp.project.apply.entity.SrpmsProjectApplyInnBcoo;
import com.deloitte.services.srpmp.project.apply.entity.SrpmsProjectApplyInnCoo;
import com.deloitte.services.srpmp.project.apply.entity.SrpmsProjectApplyInnPre;
import com.deloitte.services.srpmp.project.apply.service.ISrpmsProjectApplyInnBcooService;
import com.deloitte.services.srpmp.project.apply.service.ISrpmsProjectApplyInnCooService;
import com.deloitte.services.srpmp.project.apply.service.ISrpmsProjectApplyInnPreService;
import com.deloitte.services.srpmp.project.apply.service.impl.SrpmsProjectApplyInnCommonServiceImpl;
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
import com.deloitte.services.srpmp.project.task.entity.SrpmsProjectTaskInn;
import com.deloitte.services.srpmp.project.task.mapper.SrpmsProjectTaskInnMapper;
import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskInnService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author : pengchao
 * @Date : Create in 2019-03-11
 * @Description : SrpmsProjectTaskInn服务实现类
 * @Modified :
 */
@Service
@Slf4j
@Transactional
public class SrpmsProjectTaskInnServiceImpl extends ServiceImpl<SrpmsProjectTaskInnMapper, SrpmsProjectTaskInn>
		implements ISrpmsProjectTaskInnService {

	@Autowired
	private ISrpmsProjectService projectService;

	@Autowired
	private ISrpmsProjectApplyInnBcooService innBcooService;

	@Autowired
	private ISrpmsProjectApplyInnPreService innPreService;

	@Autowired
	private ISrpmsProjectApplyInnCooService innCooService;

	@Autowired
	private ISrpmsProjectBudgetApplyService budgetApplyService;

	@Autowired
	private ISrpmsProjectDeptJoinService deptJoinService;

	@Autowired
	private ISrpmsProjectPersonJoinService personJoinService;

	@Autowired
	private ISrpmsProjectAttachmentService attachmentService;

	@Autowired
	private ISrpmsProjectTaskService taskService;

	@Autowired
	private SrpmsProjectApplyInnCommonServiceImpl commonService;
	@Autowired
	private ISrpmsProjectBudgetDetailService budgetDetailService;

	@Autowired
	private ISrpmsProjectFlowService flowServicel;

	@Autowired
	private WordExportServiceImpl wordExportService;

	@Autowired
	private FileOperatorFeignService fileOperatorFeignService;

	@Autowired
	private ISysDictService sysDictService;

	@Autowired
	private DictFeignService dictFeignService;

	@Autowired
	private PdfMergeServiceImpl pdfMergeService;

	@Override
	public Long saveOrUpdate(SrpmsProjectTaskInnExtVo inputVo, UserVo user) {

		// 项目ID
		long projectId = inputVo.getId();

		if (projectId == 0l) {
			throw new BaseException(SrpmsErrorType.PROJECT_NOT_FOUND);
		}

		SrpmsProject projectBaseInfo = projectService.getById(projectId);
		if (projectBaseInfo == null) {
			throw new BaseException(SrpmsErrorType.PROJECT_NOT_FOUND);
		}

		JSONObject leadPersonJson = JSONObject.parseObject(projectBaseInfo.getLeadPerson());
		JSONObject LeadDeptJson = JSONObject.parseObject(projectBaseInfo.getLeadDept());

		leadPersonJson.put("taskWorkPerYear", inputVo.getLeadPersonWorkTime());
		projectBaseInfo.setSubjectCategory(
				inputVo.getSubjectCategory() == null ? "" : JSONArray.toJSONString(inputVo.getSubjectCategory()));

		JSONObject bothTopExpertPerson = inputVo.getBothTopExpertPerson();
		if (bothTopExpertPerson != null) {
			projectBaseInfo.setBothTopExpertPerson(bothTopExpertPerson.toJSONString());
			if (bothTopExpertPerson.getString("id") != null && bothTopExpertPerson.getString("id").length() != 0) {
				projectBaseInfo.setBothTopExpertPersonId(bothTopExpertPerson.getLong("id"));
			} else {
				projectBaseInfo.setBothTopExpertPersonId(null);
			}
		}
		if (inputVo.getSubjectCategory() != null) {
			projectBaseInfo.setSubjectCategory(JSONArray.toJSONString(inputVo.getSubjectCategory()));
		}
		if (inputVo.getProjectActionDateStart() != null) {
			projectBaseInfo.setProjectActionDateStart(inputVo.getProjectActionDateStart());
		}
		if (inputVo.getProjectActionDateEnd() != null) {
			projectBaseInfo.setProjectActionDateEnd(inputVo.getProjectActionDateEnd());
		}
		if (StringUtils.isNotBlank(inputVo.getProjectName())) {
			projectBaseInfo.setProjectName(inputVo.getProjectName());
		}

		projectService.updateById(projectBaseInfo);

		String strJson = JSON.toJSONString(inputVo);
		SrpmsProjectTaskInn taskInnInfo = JSONObject.parseObject(strJson, SrpmsProjectTaskInn.class);
		JSONObject jsonTaskInnInfo = JSONObject.parseObject(strJson);

		if (taskInnInfo.getBothTopWorkTime() == null) {
			taskInnInfo.setBothTopWorkTime(0L);
		}
		this.saveOrUpdate(taskInnInfo);

		// 主要参与人员
		if (jsonTaskInnInfo.getJSONArray("mainMemberList") != null) {
			personJoinService.cleanAndSavePersonJoin(
					jsonTaskInnInfo.getJSONArray("mainMemberList").toJavaList(SrpmsProjectPersonJoinVo.class),
					PersonJoinWayEnums.TASK_MAIN_MEMBER, projectId);
		} else {
			personJoinService.cleanAndSavePersonJoin(null, PersonJoinWayEnums.TASK_MAIN_MEMBER, projectId);
		}

		// 国家合作单位信息
		if (jsonTaskInnInfo.getJSONArray("coopDeptList") != null) {
			deptJoinService.cleanAndSaveDeptJoin(
					jsonTaskInnInfo.getJSONArray("coopDeptList").toJavaList(SrpmsProjectDeptJoinVo.class),
					DeptJoinWayEnums.TASK_INNOVATE_COPPDEPT, projectId);
		} else {
			deptJoinService.cleanAndSaveDeptJoin(null, DeptJoinWayEnums.TASK_INNOVATE_COPPDEPT, projectId);
		}

		// 联合申请单位
		if (jsonTaskInnInfo.getJSONArray("jointApplicantUnit") != null) {
			deptJoinService.cleanAndSaveDeptJoin(
					jsonTaskInnInfo.getJSONArray("jointApplicantUnit").toJavaList(SrpmsProjectDeptJoinVo.class),
					DeptJoinWayEnums.TASK_INNOVATE_UNIT, projectId);
		} else {
			deptJoinService.cleanAndSaveDeptJoin(null, DeptJoinWayEnums.TASK_INNOVATE_UNIT, projectId);
		}

		// 年度预算
		if (jsonTaskInnInfo.getJSONArray("budgetPreYearList") != null) {
			budgetDetailService.cleanAndSaveBudgetDetail(
					jsonTaskInnInfo.getJSONArray("budgetPreYearList").toJavaList(SrpmsProjectBudgetDetailVo.class),
					BudgetCategoryEnums.TASK_INNOVATE_BUDGET_YEAR, projectId);
		} else {
			budgetDetailService.cleanAndSaveBudgetDetail(null, BudgetCategoryEnums.TASK_INNOVATE_BUDGET_YEAR,
					projectId);
		}

		// 附件 and 任务书签订各方意见及签章
		attachmentService.saveAttachmentListTaskType1(jsonTaskInnInfo, projectId);

		// 任务的年度任务、考核指标和时间节点
		if (jsonTaskInnInfo.getJSONArray("taskPreYearList") != null) {
			taskService.cleanAndSaveTask(
					jsonTaskInnInfo.getJSONArray("taskPreYearList").toJavaList(SrpmsProjectTaskVo.class),
					TaskCategoryEnums.TASK_INNOVATE_TASK_YEAR, projectId);
		} else {
			taskService.cleanAndSaveTask(null, TaskCategoryEnums.TASK_INNOVATE_TASK_YEAR, projectId);
		}

		// 任务的年度任务、考核指标和时间节点
		if (projectBaseInfo.getProjectType().equals(ProjectCategoryEnums.INNOVATE_PRE.getHeader())) {
			// 分解任务
			List<SrpmsProjectTaskVo> subTaskList = taskService
					.queryTaskListByTaskCategory(TaskCategoryEnums.TASK_INNOVATE_TASK_DECOMPOSITION, projectId);
			SrpmsProjectTaskVo subTask = null;
			if (subTaskList != null && subTaskList.size() != 0) {
				subTask = subTaskList.get(0);

			} else {
				subTask = new SrpmsProjectTaskVo();
			}
			subTaskList = new ArrayList<SrpmsProjectTaskVo>();

			subTask.setDeptId(LeadDeptJson.getString("deptId"));
			subTask.setLeadPerson(leadPersonJson.getString("id"));
			JSONObject taskLeadPerson = new JSONObject();
			taskLeadPerson.put("personId", leadPersonJson.getString("id"));
			taskLeadPerson.put("personName", leadPersonJson.getString("name"));
			taskLeadPerson.put("deptCode", LeadDeptJson.getString("deptCode"));
			taskLeadPerson.put("deptName", LeadDeptJson.getString("deptName"));
			subTask.setLeadPersonInfo(taskLeadPerson);

			subTask.setProjectId(projectId + "");
			subTask.setDeptName(LeadDeptJson.getString("deptName"));
			subTask.setTaskName(projectBaseInfo.getProjectName());

			subTask.setDeptCode(LeadDeptJson.getString("deptCode"));
			JSONArray joinPersonInfo = new JSONArray();
			JSONArray joinPerson = new JSONArray();
			if (jsonTaskInnInfo.getJSONArray("mainMemberList") != null) {
				for (int i = 0; i < jsonTaskInnInfo.getJSONArray("mainMemberList").size(); i++) {
					JSONObject member = jsonTaskInnInfo.getJSONArray("mainMemberList").getJSONObject(i);
					JSONObject taskJoinPerson = new JSONObject();
					taskJoinPerson.put("personId", member.getLong("personId"));
					taskJoinPerson.put("personName", member.getString("personName"));
					taskJoinPerson.put("deptCode", LeadDeptJson.getString("deptCode"));
					taskJoinPerson.put("deptName", LeadDeptJson.getString("deptName"));
					joinPersonInfo.add(taskJoinPerson);

					joinPerson.add(member.getString("personId"));
				}
			}
			subTask.setJoinPersonInfo(joinPersonInfo);
			subTask.setJoinPerson(joinPerson);
			subTask.setAllocatedAmount("100");
			subTaskList.add(subTask);
			commonService.setTaskAndBudgetList(subTaskList, projectId, true);
		} else {
			commonService.setTaskAndBudgetList(jsonTaskInnInfo.getJSONArray("taskDecompositionList"), projectId, true);
		}

		return projectId;
	}

	@Override
	public JSONObject queryById(Long projectId) {

		log.info("项目ID是" + projectId);

		SrpmsProject project = projectService.getById(projectId);
		if (project == null || project.getId() == 0l) {
			throw new BaseException(SrpmsErrorType.PROJECT_NOT_FOUND);
		}

		JSONObject relust = JSONObject.parseObject(JSONObject.toJSONString(project));

		if ("true".equals(project.getTaskFirstOpenFlg())) {
			relust.put("firstFlg", "true");
			project.setTaskFirstOpenFlg("false");
			projectService.updateById(project);
		} else {
			relust.put("firstFlg", "false");
		}

		relust.putAll(JSONObject.parseObject(JSON.toJSONString(this.getById(projectId))));

		// 国家合作单位信息
		List<SrpmsProjectDeptJoinVo> list3 = deptJoinService
				.queryDeptJoinListByJoinWay(DeptJoinWayEnums.TASK_INNOVATE_COPPDEPT, projectId);
		if (list3 != null && list3.size() != 0) {
			relust.put("coopDeptList", list3);
		}

		// 联合申请单位
		list3 = deptJoinService.queryDeptJoinListByJoinWay(DeptJoinWayEnums.TASK_INNOVATE_UNIT, projectId);
		if (list3 != null && list3.size() != 0) {
			relust.put("jointApplicantUnit", list3);
		}

		// 年度预算
		List<SrpmsProjectBudgetDetailVo> list2 = budgetDetailService
				.queryBudgetDetailListByCategory(BudgetCategoryEnums.TASK_INNOVATE_BUDGET_YEAR, projectId);
		if (list2 != null && list2.size() != 0) {
			relust.put("budgetPreYearList", list2);
		}

		// 附件
		JSONObject attachmentJson = attachmentService.queryAttachmentListTaskType1(projectId);
		relust.putAll(attachmentJson);

		// 年度任务
		List<SrpmsProjectTaskVo> list = taskService
				.queryTaskListByTaskCategory(TaskCategoryEnums.TASK_INNOVATE_TASK_YEAR, projectId);
		if (list != null && list.size() != 0) {
			relust.put("taskPreYearList", list);
		}

		// 年度分解
		boolean hasSubTask = false;
		list = taskService.queryTaskListByTaskCategory(TaskCategoryEnums.TASK_INNOVATE_TASK_DECOMPOSITION, projectId);
		if (list != null && list.size() != 0) {
			hasSubTask = true;
			relust.put("taskDecompositionList", list);
		}

		// 总预算表
		relust.put("budgetAllList",
				taskService.queryTaskListByTaskCategory(TaskCategoryEnums.TASK_INNOVATE_BUDGET_ALL, projectId));

		// 主要参与人员
		List<SrpmsProjectPersonJoinVo> list4 = personJoinService
				.queryPersonJoinListByJoinWay(PersonJoinWayEnums.TASK_MAIN_MEMBER, projectId);
		if (list4 != null && list4.size() != 0) {
			for (int i = 0; i < list4.size(); i++) {
				SrpmsProjectPersonJoinVo mianMember = list4.get(i);
				if (hasSubTask) {
					for (int j = 0; j < list.size(); j++) {
						if (mianMember.getPersonName().equals(list.get(j).getLeadPersonName())) {
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

		relust.put("subjectCategory", relust.getString("subjectCategory") == null ? ""
				: JSONArray.parseArray(relust.getString("subjectCategory")));

		JSONConvert.convertJson(relust);
		return relust;
	}

	@Override
	public void submit(SrpmsProjectTaskInnExtVo vo, UserVo user, DeptVo deptVo) {
		// 校验预算书
		long id = vo.getId();

		// 校验申请书状态，只有未提交的申请书才能提交
		SrpmsProject project = projectService.getById(id);
		if (!SrpmsProjectStatusEnums.PEROJECT_HAS_PUBLICITY.getCode().equals(project.getStatus())) {
			throw new BaseException(SrpmsErrorType.TASK_HAVE_SUBMITTED);
		}

		JSONObject budgetJson = budgetApplyService.queryById(id, true);
		if (budgetJson == null) {
			throw new BaseException(SrpmsErrorType.BUDGET_NOT_COMPLETE);
		}

		// 保存申请书
		this.saveOrUpdate(vo, null);

		JSONObject taskJson = this.queryById(id);
		ProjectCheckUtils.checkTask(project, taskJson, budgetJson);

		// 生成任务书pdf文档
		budgetApplyService.generateTaskBookPdf(project, user, deptVo);

		// 生成预算任务书
		budgetApplyService.generateBudgetTaskPdf(vo.getId(), user, deptVo);

		// 更新项目状态
		projectService.submitTaskProject(vo.getId());

		log.info("先导专项，提交任务书，已更新项目状态, projectId:{}", vo.getId());
		// 发起流程
		flowServicel.startAuditProcess(vo.getId(), VoucherTypeEnums.TASK_BOOK, user, deptVo);

		log.info("先导专项，提交申请书，已发起流程, projectId:{}", vo.getId());
	}

	@Override
	public String exportPdfTaskPre(Long projectId, UserVo userVo, DeptVo deptVo) throws Exception {
		String docxFilePath = this.exportTaskWord(projectId, userVo, deptVo);
		// 转格式
		String pdfFilePath = pdfMergeService.wordToPdf(docxFilePath);
		String pdfFinalName = docxFilePath.replace("docx", "pdf");
		log.info("PDF文件合并前路径：{}, 合并后路径：{}, projectId:{}", pdfFilePath, pdfFinalName, projectId);
		// 合并
		pdfMergeService.mergeAttachmentTaskType1(pdfFilePath, projectId, pdfFinalName);
		return pdfFinalName;
	}

	@Override
	public String exportPdfTaskBcoo(Long projectId, UserVo userVo, DeptVo deptVo) throws Exception {
		String docxFilePath = this.exportBcooTaskWord(projectId, userVo, deptVo);
		// 转格式
		String pdfFilePath = pdfMergeService.wordToPdf(docxFilePath);
		String pdfFinalName = docxFilePath.replace("docx", "pdf");
		log.info("PDF文件合并前路径：{}, 合并后路径：{}, projectId:{}", pdfFilePath, pdfFinalName, projectId);
		// 合并
		pdfMergeService.mergeAttachmentTaskType1(pdfFilePath, projectId, pdfFinalName);
		return pdfFinalName;
	}

	@Override
	public String exportPdfTaskCoo(Long projectId, UserVo userVo, DeptVo deptVo) throws Exception {
		String docxFilePath = this.exportCooTaskWord(projectId, userVo, deptVo);
		// 转格式
		String pdfFilePath = pdfMergeService.wordToPdf(docxFilePath);
		String pdfFinalName = docxFilePath.replace("docx", "pdf");
		log.info("PDF文件合并前路径：{}, 合并后路径：{}, projectId:{}", pdfFilePath, pdfFinalName, projectId);
		// 合并
		pdfMergeService.mergeAttachmentTaskType1(pdfFilePath, projectId, pdfFinalName);
		return pdfFinalName;
	}

	@Override
	public String exportTaskWord(Long projectId, UserVo userVo, DeptVo deptVo) {
		if (projectId == null) {
			throw new BaseException(SrpmsErrorType.PARAM_NULL);
		}
		InputStream fileIn = null;
		try {
			Map<String, Object> data = Maps.newHashMap();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
			DateTimeFormatter formatterLess = DateTimeFormatter.ofPattern("yyyy年MM月");
			DateTimeFormatter formatterBirth = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			if (projectId != 0) {

				SrpmsProject project = projectService.getById(projectId);
				data.put("project", project);

				JSONObject jsonObject = this.queryById(projectId);
				SrpmsProjectTaskInnExtVo taskInn = JSONObject.parseObject(jsonObject.toJSONString(),
						SrpmsProjectTaskInnExtVo.class);
				taskHtmlToText(taskInn);
				data.put("taskInn", taskInn);

				// 国家合作单位信息
				List<SrpmsProjectDeptJoinVo> deptJoinList = deptJoinService
						.queryDeptJoinListByJoinWay(DeptJoinWayEnums.TASK_INNOVATE_COPPDEPT, projectId);
				if (CollectionUtils.isNotEmpty(deptJoinList)) {
					data.put("deptJoinList", deptJoinList);
				}

				// 年度任务
				List<SrpmsProjectTaskVo> yearPlanList = taskService
						.queryTaskListByTaskCategory(TaskCategoryEnums.TASK_INNOVATE_TASK_YEAR, projectId);
				if (CollectionUtils.isNotEmpty(yearPlanList)) {
					for (SrpmsProjectTaskVo vo : yearPlanList) {
						vo.setFirstYearTarget(WordConvertUtil.htmlToText(vo.getFirstYearTarget()));
						vo.setExamMode(WordConvertUtil.htmlToText(vo.getExamMode()));
					}
					data.put("yearPlanList", yearPlanList);
				}

				SrpmsProjectApplyInnPre applyInnPre = innPreService.getById(projectId);

				// 算总经费
				Double applyFunds = NumberUtils.DOUBLE_ZERO;
				List<SrpmsProjectBudgetDetailVo> budgetPreYearList = taskInn.getBudgetPreYearList();
				if (!CollectionUtils.isEmpty(budgetPreYearList)) {
					for (SrpmsProjectBudgetDetailVo budgetVo : budgetPreYearList) {
						applyFunds += budgetVo.getBudgetAmount();
					}
				}
				applyInnPre.setApplyFunds(applyFunds);
				data.put("applyInnPre", applyInnPre);

				// 预期成果类型
				if (applyInnPre != null && StringUtils.isNotBlank(applyInnPre.getAchievementType())) {
					applyInnPre.setAchievementType(WordConvertUtil.htmlToText(applyInnPre.getAchievementType()));
					List<String> achList = JSONObject.parseArray(applyInnPre.getAchievementType(), String.class);
					Map<String, String> dictMap = sysDictService
							.getDictByCategory(SysDictEnums.PRO_RELUST_TYPE.getCode());
					List<String> achNameList = WordConvertUtil.parseCodeListToText(dictMap, achList);
					data.put("achNameList", achNameList);
				}

				// 人数统计
				// 主要参与人员
				List<SrpmsProjectPersonJoinVo> personJoinList = personJoinService
						.queryPersonJoinListByJoinWay(PersonJoinWayEnums.TASK_MAIN_MEMBER, projectId);
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
				if (CollectionUtils.isNotEmpty(personJoinList)) {
					for (SrpmsProjectPersonJoinVo personJoinVo : personJoinList) {
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

				data.put("personJoinList", personJoinList);

				data.put("memberAllCount", memberAllCount + "");
				data.put("memberZGJ", memberZGJ + "");
				data.put("memberFGJ", memberFGJ + "");
				data.put("memberZJ", memberZJ + "");
				data.put("memberCGJ", memberCGJ + "");
				data.put("memberCCJ", memberCCJ + "");
				data.put("xueweiXS", xueweiXS + "");
				data.put("xueweiSS", xueweiSS + "");
				data.put("xueweiBS", xueweiBS + "");
				data.put("xueliBK", xueliBK + "");
				data.put("xueliYJS", xueliYJS + "");
				data.put("xueliQT", xueliQT + "");
				data.put("memberBSH", memberBSH + "");

				// 格式化时间日期
				data.put("createTime", project.getCreateTime().format(formatter));
				data.put("projectActionDateStart", project.getProjectActionDateStart() == null ? ""
						: project.getProjectActionDateStart().format(formatterLess));
				data.put("projectActionDateEnd", project.getProjectActionDateEnd() == null ? ""
						: project.getProjectActionDateEnd().format(formatterLess));

				JSONObject leadPersonJson = JSONObject.parseObject(project.getLeadPerson());
				data.put("leadDept", JSONObject.parseObject(project.getLeadDept()));
				data.put("leadPerson", leadPersonJson);
			} else {
				data.put("leadPerson", userVo);
				data.put("leadDept", deptVo);
			}

			String filename = "先导专项任务书_" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS")
					+ RandomUtils.nextInt(0, 9999) + ".docx";
			String finalWordName = wordExportService.exportWord("template_task_inn_pre", data, filename);
			fileIn = new FileInputStream(finalWordName);
			return finalWordName;
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
	}

	/**
	 * 将任务富文本相关字段对应value 转化为纯文本
	 * 
	 * @param taskInn
	 */
	private SrpmsProjectTaskInnExtVo taskHtmlToText(SrpmsProjectTaskInnExtVo taskInn) {
		if (null == taskInn) {
			throw new BaseException(SrpmsErrorType.PROJECT_NOT_FOUND);
		}
		taskInn.setApprovalNecessay(WordConvertUtil.htmlToText(taskInn.getApprovalNecessay()));
		taskInn.setResearchTarget(WordConvertUtil.htmlToText(taskInn.getResearchTarget()));
		taskInn.setResearchContentMain(WordConvertUtil.htmlToText(taskInn.getResearchContentMain()));
		taskInn.setTaskMasterMethod(WordConvertUtil.htmlToText(taskInn.getTaskMasterMethod()));
		taskInn.setAchievementForm(WordConvertUtil.htmlToText(taskInn.getAchievementForm()));
		taskInn.setAchievementBenefit(WordConvertUtil.htmlToText(taskInn.getAchievementBenefit()));
		taskInn.setResearchContentMethod(WordConvertUtil.htmlToText(taskInn.getResearchContentMethod()));
		taskInn.setTaskOrgManageMode(WordConvertUtil.htmlToText(taskInn.getTaskOrgManageMode()));
		taskInn.setKnowledgeResultManage(WordConvertUtil.htmlToText(taskInn.getKnowledgeResultManage()));
		taskInn.setRiskAnalyzeManage(WordConvertUtil.htmlToText(taskInn.getRiskAnalyzeManage()));
		taskInn.setLeadPersonNote(WordConvertUtil.htmlToText(taskInn.getLeadPersonNote()));
		taskInn.setBothTopNote(WordConvertUtil.htmlToText(taskInn.getBothTopNote()));
		taskInn.setMainContents(WordConvertUtil.htmlToText(taskInn.getMainContents()));
		taskInn.setExchangeProgramme(WordConvertUtil.htmlToText(taskInn.getExchangeProgramme()));
		return taskInn;
	}

	@Override
	public String exportBcooTaskWord(Long projectId, UserVo userVo, DeptVo deptVo) {
		if (projectId == null) {
			throw new BaseException(SrpmsErrorType.PARAM_NULL);
		}
		InputStream fileIn = null;
		try {
			Map<String, Object> data = Maps.newHashMap();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
			DateTimeFormatter formatterLess = DateTimeFormatter.ofPattern("yyyy年MM月");
			DateTimeFormatter formatterBirth = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			if (projectId != 0) {
				JSONObject jsonObject = this.queryById(projectId);
				SrpmsProjectTaskInnExtVo taskInn = JSONObject.parseObject(jsonObject.toJSONString(),
						SrpmsProjectTaskInnExtVo.class);
				taskHtmlToText(taskInn);

				List<SrpmsProjectTaskVo> taskDecompositionList = taskInn.getTaskDecompositionList();
				if (CollectionUtils.isEmpty(taskDecompositionList)) {
					taskInn.setTaskDecompositionList(null);
				} else {
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
					}
				}

				// 年度任务
				List<SrpmsProjectTaskVo> yearPlanList = taskInn.getTaskPreYearList();
				if (CollectionUtils.isNotEmpty(yearPlanList)) {
					for (SrpmsProjectTaskVo vo : yearPlanList) {
						vo.setTaskTargetYear(WordConvertUtil.htmlToText(vo.getTaskTargetYear()));
						vo.setExamMode(WordConvertUtil.htmlToText(vo.getExamMode()));
					}
				}

				JSONObject newVoJson = new JSONObject();
				// 国家合作单位信息
				List<SrpmsProjectDeptJoinVo> deptJoinList = taskInn.getCoopDeptList();
				String coopDeptName = "";
				Integer deptJoinSize = NumberUtils.INTEGER_ZERO;
				if (CollectionUtils.isNotEmpty(deptJoinList)) {
					for (SrpmsProjectDeptJoinVo joinVo : deptJoinList) {
						coopDeptName = coopDeptName + joinVo.getDeptName() + ",";
					}
					deptJoinSize += deptJoinList.size();
				}

				List<SrpmsProjectDeptJoinVo> jointApplicantUnit = taskInn.getJointApplicantUnit();
				if (CollectionUtils.isEmpty(jointApplicantUnit)) {
					taskInn.setJointApplicantUnit(null);
				} else {
					deptJoinSize += jointApplicantUnit.size();
					for (SrpmsProjectDeptJoinVo joinVo : jointApplicantUnit) {
						coopDeptName = coopDeptName + joinVo.getDeptName() + ",";
					}
				}
				// 参与单位
				newVoJson.put("coopDeptName",
						coopDeptName.endsWith(",") ? coopDeptName.substring(0, coopDeptName.length() - 1)
								: coopDeptName);
				newVoJson.put("deptJoinSize", deptJoinSize);
				// 查询对应申请书信息
				SrpmsProjectApplyInnBcoo applyInnBcoo = innBcooService.getById(projectId);
				Double applyFunds = NumberUtils.DOUBLE_ZERO;
				List<SrpmsProjectBudgetDetailVo> budgetPreYearList = taskInn.getBudgetPreYearList();
				if (!CollectionUtils.isEmpty(budgetPreYearList)) {
					for (SrpmsProjectBudgetDetailVo budgetVo : budgetPreYearList) {
						applyFunds += budgetVo.getBudgetAmount();
					}
				}
				applyInnBcoo.setApplyFunds(applyFunds);
				data.put("applyInnBcoo", applyInnBcoo);
				// 预期成果类型
				String achievementType = applyInnBcoo.getAchievementType();
				if (StringUtils.isNotBlank(achievementType)) {
					List<String> achList = JSONArray.parseArray(achievementType, String.class);
					Map<String, String> dictMap = sysDictService
							.getDictByCategory(SysDictEnums.PRO_RELUST_TYPE.getCode());
					List<String> achNameList = WordConvertUtil.parseCodeListToText(dictMap, achList);
					newVoJson.put("achievementList", achNameList);

				}

				// 所属领域
				String belongDomain = applyInnBcoo.getBelongDomain();
				if (StringUtils.isNotBlank(belongDomain)) {
					List<String> achList = Lists.newArrayList(belongDomain);
					Map<String, String> dictMap = sysDictService.getDictByCategory(SysDictEnums.PRO_DOMAIN.getCode());
					List<String> achNameList = WordConvertUtil.parseCodeListToText(dictMap, achList);
					newVoJson.put("belongDomainList", achNameList);
				}

				// 活动类型
				String activeType = applyInnBcoo.getActiveType();
				if (StringUtils.isNotBlank(activeType)) {
					List<String> achList = JSONArray.parseArray(activeType, String.class);
					Map<String, String> dictMap = sysDictService
							.getDictByCategory(SysDictEnums.PRO_ACTIVE_TYPE.getCode());
					List<String> achNameList = WordConvertUtil.parseCodeListToText(dictMap, achList);
					newVoJson.put("activeTypeList", achNameList);
				}
				// 人数统计
				// 主要参与人员
				List<SrpmsProjectPersonJoinVo> personJoinList = taskInn.getMainMemberList();
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
				if (CollectionUtils.isNotEmpty(personJoinList)) {
					for (SrpmsProjectPersonJoinVo personJoinVo : personJoinList) {
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

				newVoJson.putAll(JSONObject.parseObject(JSONObject.toJSONString(taskInn)));
				data.put("p", newVoJson);

				// 计算总计
				data.put("memberAllCount", memberAllCount + "");
				data.put("memberZGJ", memberZGJ + "");
				data.put("memberFGJ", memberFGJ + "");
				data.put("memberZJ", memberZJ + "");
				data.put("memberCGJ", memberCGJ + "");
				data.put("memberCCJ", memberCCJ + "");
				data.put("xueweiXS", xueweiXS + "");
				data.put("xueweiSS", xueweiSS + "");
				data.put("xueweiBS", xueweiBS + "");
				data.put("xueliBK", xueliBK + "");
				data.put("xueliYJS", xueliYJS + "");
				data.put("xueliQT", xueliQT + "");
				data.put("memberBSH", memberBSH + "");

				// 获取项目信息格式化时间日期、首席专家 、共同首席专家
				SrpmsProject project = projectService.getById(projectId);
				data.put("project", project);

				data.put("createTime", project.getCreateTime().format(formatter));
				data.put("projectActionDateStart", project.getProjectActionDateStart() == null ? ""
						: project.getProjectActionDateStart().format(formatterBirth));
				data.put("projectActionDateEnd", project.getProjectActionDateEnd() == null ? ""
						: project.getProjectActionDateEnd().format(formatterBirth));

				JSONObject leadPersonJson = JSONObject.parseObject(project.getLeadPerson());
				data.put("leadDept", JSONObject.parseObject(project.getLeadDept()));
				leadPersonJson.put("birthDate",
						LocalDateUtils.parse(leadPersonJson.getString("birthDate"), LocalDateUtils.PARRERN_Y_M_D));
				data.put("leadPerson", leadPersonJson);

				JSONObject bothTopExpertPerson = JSONObject.parseObject(project.getBothTopExpertPerson());
				leadPersonJson.put("birthDate",
						LocalDateUtils.parse(bothTopExpertPerson.getString("birthDate"), LocalDateUtils.PARRERN_Y_M_D));
				data.put("bothTopExpertPerson", bothTopExpertPerson);

			} else {
				data.put("leadPerson", userVo);
				data.put("leadDept", deptVo);
				data.put("p", new JSONObject());
			}

			String filename = "重大协同项目任务书_" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS")
					+ RandomUtils.nextInt(0, 9999) + ".docx";
			String finalWordName = wordExportService.exportWord("template_task_inn_bcoo", data, filename);
			fileIn = new FileInputStream(finalWordName);
			return finalWordName;
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
	}

	@Override
	public String exportCooTaskWord(Long projectId, UserVo userVo, DeptVo deptVo) {
		if (projectId == null) {
			throw new BaseException(SrpmsErrorType.PARAM_NULL);
		}
		InputStream fileIn = null;
		try {
			Map<String, Object> data = Maps.newHashMap();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
			DateTimeFormatter formatterLess = DateTimeFormatter.ofPattern("yyyy年MM月");
			DateTimeFormatter formatterBirth = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			if (projectId != 0) {
				JSONObject jsonObject = this.queryById(projectId);
				SrpmsProjectTaskInnExtVo taskInn = JSONObject.parseObject(jsonObject.toJSONString(),
						SrpmsProjectTaskInnExtVo.class);
				taskHtmlToText(taskInn);

				List<SrpmsProjectDeptJoinVo> jointApplicantUnit = taskInn.getJointApplicantUnit();
				if (CollectionUtils.isEmpty(jointApplicantUnit)) {
					taskInn.setJointApplicantUnit(null);
				}

				List<SrpmsProjectTaskVo> taskDecompositionList = taskInn.getTaskDecompositionList();
				if (CollectionUtils.isEmpty(taskDecompositionList)) {
					taskInn.setTaskDecompositionList(null);
				} else {
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
					}
				}
				// 年度任务
				List<SrpmsProjectTaskVo> yearPlanList = taskInn.getTaskPreYearList();
				if (CollectionUtils.isNotEmpty(yearPlanList)) {
					for (SrpmsProjectTaskVo vo : yearPlanList) {
						vo.setTaskTargetYear(WordConvertUtil.htmlToText(vo.getTaskTargetYear()));
						vo.setExamMode(WordConvertUtil.htmlToText(vo.getExamMode()));
					}
				}

				JSONObject newVoJson = new JSONObject();
				// 国家合作单位信息
				List<SrpmsProjectDeptJoinVo> deptJoinList = taskInn.getCoopDeptList();
				String coopDeptName = "";
				if (CollectionUtils.isNotEmpty(deptJoinList)) {
					for (SrpmsProjectDeptJoinVo joinVo : deptJoinList) {
						coopDeptName = coopDeptName + joinVo.getDeptName() + ",";
					}
					newVoJson.put("deptJoinSize", deptJoinList.size());
				}
				// 参与单位
				newVoJson.put("coopDeptName", coopDeptName);

				// 查询对应申请书信息
				SrpmsProjectApplyInnCoo applyInnCoo = innCooService.getById(projectId);
				data.put("applyInnBcoo", applyInnCoo);

				// 人数统计
				// 主要参与人员
				List<SrpmsProjectPersonJoinVo> personJoinList = taskInn.getMainMemberList();
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
				if (CollectionUtils.isNotEmpty(personJoinList)) {
					for (SrpmsProjectPersonJoinVo personJoinVo : personJoinList) {
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

				// 所属领域
				String belongDomain = taskInn.getBelongDomain();
				if (StringUtils.isNotBlank(belongDomain)) {
					List<String> domainList = Lists.newArrayList(belongDomain);
					Map<String, String> domainMap = sysDictService.getDictByCategory(SysDictEnums.PRO_DOMAIN.getCode());
					List<String> domainNameList = WordConvertUtil.parseCodeListToText(domainMap, domainList);
					newVoJson.put("belongDomainList", domainNameList);
				}

				// 预算经费
				List<SrpmsProjectBudgetDetailVo> detailVoList = budgetDetailService.queryBudgetDetailListByCategory(
						BudgetCategoryEnums.TASK_INNOVATE_BUDGET_YEAR, taskInn.getId());
				double budgetAmount = 0;
				if (detailVoList != null && detailVoList.size() > 0) {
					for (SrpmsProjectBudgetDetailVo budgetDetailVo : detailVoList) {
						budgetAmount += budgetDetailVo.getBudgetAmount();
					}
				}
				newVoJson.put("budgetAmount", budgetAmount);

				newVoJson.putAll(JSONObject.parseObject(JSONObject.toJSONString(taskInn)));
				data.put("p", newVoJson);

				// 计算总计
				data.put("memberAllCount", memberAllCount + "");
				data.put("memberZGJ", memberZGJ + "");
				data.put("memberFGJ", memberFGJ + "");
				data.put("memberZJ", memberZJ + "");
				data.put("memberCGJ", memberCGJ + "");
				data.put("memberCCJ", memberCCJ + "");
				data.put("xueweiXS", xueweiXS + "");
				data.put("xueweiSS", xueweiSS + "");
				data.put("xueweiBS", xueweiBS + "");

				data.put("xueliBK", xueliBK + "");
				data.put("xueliYJS", xueliYJS + "");
				data.put("xueliQT", xueliQT + "");

				data.put("memberBSH", memberBSH + "");
				// 获取项目信息格式化时间日期、首席专家 、共同首席专家
				SrpmsProject project = projectService.getById(projectId);
				project.setApplyYear(StringConvertUtil.convertNumToWord(project.getApplyYear()));
				data.put("project", project);

				data.put("createTime", project.getCreateTime().format(formatter));
				data.put("projectActionDateStart", project.getProjectActionDateStart() == null ? ""
						: project.getProjectActionDateStart().format(formatterBirth));
				data.put("projectActionDateEnd", project.getProjectActionDateEnd() == null ? ""
						: project.getProjectActionDateEnd().format(formatterBirth));

				JSONObject leadPersonJson = JSONObject.parseObject(project.getLeadPerson());
				data.put("leadDept", JSONObject.parseObject(project.getLeadDept()));
				leadPersonJson.put("birthDate",
						LocalDateUtils.parse(leadPersonJson.getString("birthDate"), LocalDateUtils.PARRERN_Y_M_D));
				data.put("leadPerson", leadPersonJson);
				// 首席专家 人才计划
				JSONArray talentPlan = leadPersonJson.getJSONArray("talentPlan");
				if (!org.springframework.util.CollectionUtils.isEmpty(talentPlan)) {
					List<String> planList = JSONArray.parseArray(talentPlan.toJSONString(), String.class);
					Result dictMap = dictFeignService.getDictMap(SysDictEnums.TALENT_PLAN_TYPE.getCode());
					Object dictdata = dictMap.getData();
					if (!Objects.equals(null, dictdata) && dictdata instanceof Map) {
						List<String> leaderTalentPlanList = WordConvertUtil
								.parseCodeListToText((Map<String, String>) dictdata, planList);
						data.put("leaderTalentPlanList", leaderTalentPlanList);
					}
				}

				JSONObject bothTopExpertPerson = JSONObject.parseObject(project.getBothTopExpertPerson());
				leadPersonJson.put("birthDate",
						LocalDateUtils.parse(bothTopExpertPerson.getString("birthDate"), LocalDateUtils.PARRERN_Y_M_D));
				data.put("bothTopExpertPerson", bothTopExpertPerson);
				// 共同首席专家人才计划
				talentPlan = bothTopExpertPerson.getJSONArray("talentPlan");
				if (!org.springframework.util.CollectionUtils.isEmpty(talentPlan)) {
					List<String> planList = JSONArray.parseArray(talentPlan.toJSONString(), String.class);
					Result dictMap = dictFeignService.getDictMap(SysDictEnums.TALENT_PLAN_TYPE.getCode());
					Object dictdata = dictMap.getData();
					if (!Objects.equals(null, dictdata) && dictdata instanceof Map) {
						List<String> bothTalentPlanList = WordConvertUtil
								.parseCodeListToText((Map<String, String>) dictdata, planList);
						data.put("bothTalentPlanList", bothTalentPlanList);
					}
				}

			} else {
				data.put("leadPerson", userVo);
				data.put("leadDept", deptVo);
				data.put("p", new JSONObject());
			}

			String filename = "协同创新项目任务书_" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS")
					+ RandomUtils.nextInt(0, 9999) + ".docx";
			String finalWordName = wordExportService.exportWord("template_task_inn_coo", data, filename);
			fileIn = new FileInputStream(finalWordName);
			return finalWordName;
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
	}

	@Override
	public SrpmsProjectTaskInnExtVo importBcooWord(String wordFileUrl) {
		try {
			SrpmsProjectTaskInnExtVo taskInnExtVo = new SrpmsProjectTaskInnExtVo();
			String classPath = new File(ResourceUtils.getURL("").getPath()).getAbsoluteFile().toString();
			String htmlFilePath = WordConvertUtil.wordConvertToHtml(wordFileUrl, classPath);
			Document document = Jsoup.parse(new File(htmlFilePath), "utf-8");
			Elements tableElements = document.getElementsByTag("table");
			// 解析文档提取表格数据
			// 项目基本信息
			Element baseInfoTable = tableElements.get(0);
			// 项目负责人每年工作时间
			String leadPersonWorkTime = extractCellFromTable(baseInfoTable, 10, 5);
			taskInnExtVo.setLeadPersonWorkTime(NumberUtils.toLong(leadPersonWorkTime));

			// 共同首席专家每年工作时间
			String bothTopWorkTime = extractCellFromTable(baseInfoTable, 13, 5);
			taskInnExtVo.setBothTopWorkTime(NumberUtils.toLong(bothTopWorkTime));
			// 联合申请单位信息
			List<SrpmsProjectDeptJoinVo> jointApplicantUnit = new ArrayList<SrpmsProjectDeptJoinVo>();
			List<List<String>> joinUnitArray = extractListFromTable(baseInfoTable, 15, 0);
			Integer coopDeptRowID = 15;
			if (!org.springframework.util.CollectionUtils.isEmpty(joinUnitArray)) {
				for (List<String> rowData : joinUnitArray) {
					coopDeptRowID++;
					String coodeptTitle = rowData.get(0);
					if (StringUtils.contains(coodeptTitle, "国际合作单位信息")) {
						break;
					}
					if (StringUtils.isBlank(rowData.get(0)) || StringUtils.isBlank(rowData.get(1))) {
						continue;
					}
					SrpmsProjectDeptJoinVo joinUnitVo = new SrpmsProjectDeptJoinVo();
					joinUnitVo.setDeptName(rowData.get(1));
					joinUnitVo.setPeopleCount(rowData.get(2));
					joinUnitVo.setTaskLeaderName(rowData.get(3));
					joinUnitVo.setPhone(rowData.get(4));
					joinUnitVo.setEmail(rowData.get(5));
					jointApplicantUnit.add(joinUnitVo);
				}
			}
			taskInnExtVo.setJointApplicantUnit(jointApplicantUnit);
			// 国际合作单位信息
			List<SrpmsProjectDeptJoinVo> coopDeptList = new ArrayList<SrpmsProjectDeptJoinVo>();
			List<List<String>> coopDeptArray = extractListFromTable(baseInfoTable, coopDeptRowID, 0);
			Integer taskSettRowID = coopDeptRowID;
			if (!org.springframework.util.CollectionUtils.isEmpty(coopDeptArray)) {
				for (List<String> rowData : coopDeptArray) {
					taskSettRowID++;
					String taskSettingTitle = rowData.get(0);
					if (StringUtils.contains(taskSettingTitle, "任务设置")) {
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
			taskInnExtVo.setCoopDeptList(coopDeptList);
			// 任务设置
			List<SrpmsProjectTaskVo> taskSettingList = new ArrayList<SrpmsProjectTaskVo>();
			List<List<String>> taskSettingListArray = extractListFromTable(baseInfoTable, taskSettRowID, 0);
			if (!org.springframework.util.CollectionUtils.isEmpty(taskSettingListArray)) {
				for (List<String> rowData : taskSettingListArray) {
					if (StringUtils.isBlank(rowData.get(0)) || StringUtils.isBlank(rowData.get(1))) {
						continue;
					}
					SrpmsProjectTaskVo taskVo = new SrpmsProjectTaskVo();
					taskVo.setTaskName(rowData.get(1));
					taskVo.setLeadPersonName(rowData.get(2));
					taskVo.setDeptName(rowData.get(3));
					taskSettingList.add(taskVo);
				}
			}
			taskInnExtVo.setTaskSettingList(taskSettingList);
			// 立项依据
			Element necessayTable = tableElements.get(1);
			taskInnExtVo.setApprovalNecessay(extractCellFromTable(necessayTable, 1, 0, true));
			// 研究目标内容
			Element targetTable = tableElements.get(2);
			taskInnExtVo.setResearchTarget(extractCellFromTable(targetTable, 1, 0, true));
			taskInnExtVo.setResearchContentMain(extractCellFromTable(targetTable, 3, 0, true));
			taskInnExtVo.setTaskMasterMethod(extractCellFromTable(targetTable, 5, 0, true));
			// 任务分解
			Element taskDecompositionListTable = tableElements.get(4);
			List<SrpmsProjectTaskVo> taskDecompositionList = new ArrayList<SrpmsProjectTaskVo>();
			List<List<String>> taskDecompositionListArray = extractListFromTable(taskDecompositionListTable, 1, 0);
			if (!org.springframework.util.CollectionUtils.isEmpty(taskDecompositionListArray)) {
				for (List<String> rowData : taskDecompositionListArray) {
					if (StringUtils.isBlank(rowData.get(0)) || StringUtils.isBlank(rowData.get(1))) {
						break;
					}
					SrpmsProjectTaskVo taskVo = new SrpmsProjectTaskVo();
					taskVo.setTaskName(rowData.get(1));
					taskVo.setDeptName(rowData.get(2));
					taskVo.setTaskContent(rowData.get(3));
					taskVo.setThreeYearTarget(rowData.get(4));
					taskVo.setTargetPerYear(rowData.get(5));
					taskVo.setLeadPersonName(rowData.get(6));
					String joinPersonNames = rowData.get(7);
					if (StringUtils.isNotBlank(joinPersonNames)) {
						String[] split = joinPersonNames.split(",|，|\\s+");
						taskVo.setJoinPersonName(JSONObject.parseArray(JSONObject.toJSONString(split)));
					}
					taskVo.setBudgetAmount(rowData.get(8));
					taskDecompositionList.add(taskVo);
				}
			}
			taskInnExtVo.setTaskDecompositionList(taskDecompositionList);
			// 主要示范或产业化内容
			Element mainContentsTable = tableElements.get(5);
			taskInnExtVo.setApprovalNecessay(extractCellFromTable(mainContentsTable, 1, 0, true));
			// 项目成果的呈现形式及描述
			Element achievementFormTable = tableElements.get(6);
			taskInnExtVo.setAchievementForm(extractCellFromTable(achievementFormTable, 1, 0, true));
			// 项目成果的预期经济、社会效益
			taskInnExtVo.setAchievementBenefit(extractCellFromTable(achievementFormTable, 3, 0, true));
			// 研究方案
			Element researchTable = tableElements.get(7);
			taskInnExtVo.setResearchContentMethod(extractCellFromTable(researchTable, 1, 0, true));
			// 国际合作和交流方案
			taskInnExtVo.setExchangeProgramme(extractCellFromTable(researchTable, 3, 0, true));
			// 年度计划
			Element taskPreYearListArrayTable = tableElements.get(8);
			List<SrpmsProjectTaskVo> taskPreYearList = new ArrayList<SrpmsProjectTaskVo>();
			List<List<String>> taskPreYearListArray = extractListFromTable(taskPreYearListArrayTable, 1, 0);
			if (!org.springframework.util.CollectionUtils.isEmpty(taskPreYearListArray)) {
				for (List<String> rowData : taskPreYearListArray) {
					if (StringUtils.isBlank(rowData.get(0)) || StringUtils.isBlank(rowData.get(1))) {
						break;
					}
					SrpmsProjectTaskVo taskVo = new SrpmsProjectTaskVo();
					taskVo.setTaskYear(StringConvertUtil.convert(rowData.get(0)).toString());
					taskVo.setFirstYearTarget(rowData.get(1));
					taskVo.setExamMode(rowData.get(2));
					taskPreYearList.add(taskVo);
				}
			}
			taskInnExtVo.setTaskPreYearList(taskPreYearList);
			// 项目管理合作机制
			Element promanageTable = tableElements.get(9);
			taskInnExtVo.setTaskOrgManageMode(extractCellFromTable(promanageTable, 1, 0, true));
			taskInnExtVo.setKnowledgeResultManage(extractCellFromTable(promanageTable, 3, 0, true));
			taskInnExtVo.setRiskAnalyzeManage(extractCellFromTable(promanageTable, 5, 0, true));

			// 研究队伍
			Element teamTable = tableElements.get(10);
			taskInnExtVo.setResearchMemberSize(
					StringConvertUtil.convert(extractCellFromTable(teamTable, 0, 0)).longValue());
			taskInnExtVo.setResearchWorkTime(
					StringConvertUtil.convert(extractCellFromTable(teamTable, 0, 1)).doubleValue());
			taskInnExtVo.setLeadPersonNote(extractCellFromTable(teamTable, 1, 1, true));
			taskInnExtVo.setBothTopNote(extractCellFromTable(teamTable, 2, 1, true));
			// 解析项目参与人员 lixin 不解析参与人员
			// List<SrpmsProjectPersonJoinVo> mostMemberList = new ArrayList<>();
			// List<List<String>> mostMemberArray =
			// extractListFromTable(tableElements.get(11), 1, 4);
			// for (List<String> personStr : mostMemberArray) {
			// if (StringUtils.isBlank(personStr.get(0)) ||
			// StringUtils.isBlank(personStr.get(1))) {
			// break;
			// }
			// SrpmsProjectPersonJoinVo personJoinVo = new SrpmsProjectPersonJoinVo();
			// personJoinVo.setPersonName(personStr.get(1));
			// personJoinVo.setGender(personStr.get(2));
			// personJoinVo.setBirthDate(personStr.get(3));
			// personJoinVo.setPositionTitle(personStr.get(4));
			// personJoinVo.setDegree(personStr.get(5));
			// personJoinVo.setDeptName(personStr.get(6));
			// personJoinVo.setPhone(personStr.get(7));
			// personJoinVo.setIdCard(personStr.get(8));
			// if (StringUtils.isNotBlank(personStr.get(9)) &&
			// StringUtils.isNumeric(personStr.get(9))) {
			// personJoinVo.setWorkPerYear(NumberUtils.toInt(personStr.get(9)));
			// }
			// mostMemberList.add(personJoinVo);
			// }
			// taskInnExtVo.setMainMemberList(mostMemberList);

			// 解析经费预算 lixin 不解析经费预算
			// List<SrpmsProjectBudgetDetailVo> budgetPreYearList = new ArrayList<>();
			// for (int i = 12; i < tableElements.size(); i++) {
			// Element tableElement = tableElements.get(i);
			// // 获取header
			// String tableHeader = extractCellFromTable(tableElement, 0, 0);
			// if (StringUtils.isBlank(tableHeader) || !tableHeader.contains("预算科目")) {
			// break;
			// }
			//
			// SrpmsProjectBudgetDetailVo budgetDetailVo = new SrpmsProjectBudgetDetailVo();
			// budgetDetailVo.setBudgetAmount(NumberUtils.toDouble(extractCellFromTable(tableElement,
			// 1, 1)));
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
			// taskInnExtVo.setBudgetPreYearList(budgetPreYearList);

			return taskInnExtVo;
		} catch (Exception e) {
			log.error("解析word文件发生异常.", e);
			throw new BaseException(PlatformErrorType.IMPORT_TEMPLATE_ERROR);
		}
	}

	@Override
	public SrpmsProjectTaskInnExtVo importCooWord(String wordFileUrl) {
		try {
			SrpmsProjectTaskInnExtVo taskInnExtVo = new SrpmsProjectTaskInnExtVo();
			String classPath = new File(ResourceUtils.getURL("").getPath()).getAbsoluteFile().toString();
			String htmlFilePath = WordConvertUtil.wordConvertToHtml(wordFileUrl, classPath);
			Document document = Jsoup.parse(new File(htmlFilePath), "utf-8");
			Elements tableElements = document.getElementsByTag("table");
			// 解析文档提取表格数据
			// 项目基本信息
			Element baseInfoTable = tableElements.get(0);
			// 项目负责人每年工作时间
			String leadPersonWorkTime = extractCellFromTable(baseInfoTable, 7, 5);
			taskInnExtVo.setLeadPersonWorkTime(NumberUtils.toLong(leadPersonWorkTime));
			JSONObject bothTopExpertPerson = new JSONObject();
			bothTopExpertPerson.put("name", extractCellFromTable(baseInfoTable, 9, 1));

			// 共同首席专家每年工作时间
			String bothTopWorkTime = extractCellFromTable(baseInfoTable, 11, 5);
			taskInnExtVo.setBothTopWorkTime(NumberUtils.toLong(bothTopWorkTime));
			// 联合申请单位信息
			List<SrpmsProjectDeptJoinVo> jointApplicantUnit = new ArrayList<SrpmsProjectDeptJoinVo>();
			List<List<String>> joinUnitArray = extractListFromTable(baseInfoTable, 14, 0);
			Integer coopDeptRowID = 14;
			if (!org.springframework.util.CollectionUtils.isEmpty(joinUnitArray)) {
				for (List<String> rowData : joinUnitArray) {
					coopDeptRowID++;
					String coodeptTitle = rowData.get(0);
					if (StringUtils.contains(coodeptTitle, "国际合作单位信息")) {
						break;
					}
					if (StringUtils.isBlank(coodeptTitle) || StringUtils.isBlank(rowData.get(1))) {
						continue;
					}
					SrpmsProjectDeptJoinVo joinUnitVo = new SrpmsProjectDeptJoinVo();
					joinUnitVo.setDeptName(rowData.get(1));
					joinUnitVo.setPeopleCount(rowData.get(2));
					joinUnitVo.setTaskLeaderName(rowData.get(3));
					joinUnitVo.setPhone(rowData.get(4));
					joinUnitVo.setEmail(rowData.get(5));
					jointApplicantUnit.add(joinUnitVo);
				}
			}
			taskInnExtVo.setJointApplicantUnit(jointApplicantUnit);
			// 国际合作单位信息
			List<SrpmsProjectDeptJoinVo> coopDeptList = new ArrayList<SrpmsProjectDeptJoinVo>();
			List<List<String>> coopDeptArray = extractListFromTable(baseInfoTable, coopDeptRowID, 0);
			Integer taskSettRowID = coopDeptRowID;
			if (!org.springframework.util.CollectionUtils.isEmpty(coopDeptArray)) {
				for (List<String> rowData : coopDeptArray) {
					taskSettRowID++;
					String taskSettingTitle = rowData.get(0);
					if (StringUtils.contains(taskSettingTitle, "任务设置")) {
						break;
					}

					if (StringUtils.isBlank(taskSettingTitle) || StringUtils.isBlank(rowData.get(1))) {
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
			taskInnExtVo.setCoopDeptList(coopDeptList);
			// 任务设置
			List<SrpmsProjectTaskVo> taskSettingList = new ArrayList<SrpmsProjectTaskVo>();
			List<List<String>> taskSettingListArray = extractListFromTable(baseInfoTable, taskSettRowID, 0);
			if (!org.springframework.util.CollectionUtils.isEmpty(taskSettingListArray)) {
				for (List<String> rowData : taskSettingListArray) {
					if (StringUtils.isBlank(rowData.get(0)) || StringUtils.isBlank(rowData.get(1))) {
						continue;
					}
					SrpmsProjectTaskVo taskVo = new SrpmsProjectTaskVo();
					taskVo.setTaskName(rowData.get(1));
					taskVo.setLeadPersonName(rowData.get(2));
					taskVo.setDeptName(rowData.get(3));
					taskSettingList.add(taskVo);
				}
			}
			taskInnExtVo.setTaskSettingList(taskSettingList);
			// 立项依据
			Element necessayTable = tableElements.get(1);
			taskInnExtVo.setApprovalNecessay(extractCellFromTable(necessayTable, 1, 0));
			// 研究目标内容
			Element targetTable = tableElements.get(2);
			taskInnExtVo.setResearchTarget(extractCellFromTable(targetTable, 1, 0));
			taskInnExtVo.setResearchContentMain(extractCellFromTable(targetTable, 3, 0));
			taskInnExtVo.setTaskMasterMethod(extractCellFromTable(targetTable, 5, 0));
			// 任务分解
			Element taskDecompositionListTable = tableElements.get(4);
			List<SrpmsProjectTaskVo> taskDecompositionList = new ArrayList<SrpmsProjectTaskVo>();
			List<List<String>> taskDecompositionListArray = extractListFromTable(taskDecompositionListTable, 1, 0);
			if (!org.springframework.util.CollectionUtils.isEmpty(taskDecompositionListArray)) {
				for (List<String> rowData : taskDecompositionListArray) {
					if (StringUtils.isBlank(rowData.get(0)) || StringUtils.isBlank(rowData.get(1))) {
						continue;
					}

					SrpmsProjectTaskVo taskVo = new SrpmsProjectTaskVo();
					taskVo.setTaskName(rowData.get(1));
					taskVo.setDeptName(rowData.get(2));
					taskVo.setTaskContent(rowData.get(3));
					taskVo.setThreeYearTarget(rowData.get(4));
					taskVo.setTargetPerYear(rowData.get(5));
					taskVo.setLeadPersonName(rowData.get(6));
					String joinPersonNames = rowData.get(7);
					if (StringUtils.isNotBlank(joinPersonNames)) {
						String[] split = joinPersonNames.split(",|，|\\s+");
						taskVo.setJoinPersonName(JSONObject.parseArray(JSONObject.toJSONString(split)));
					}
					taskVo.setBudgetAmount(rowData.get(8));
					taskDecompositionList.add(taskVo);
				}
			}
			taskInnExtVo.setTaskDecompositionList(taskDecompositionList);
			// 主要示范或产业化内容
			Element mainContentsTable = tableElements.get(5);
			taskInnExtVo.setApprovalNecessay(extractCellFromTable(mainContentsTable, 1, 0));
			// 项目成果的呈现形式及描述
			Element achievementFormTable = tableElements.get(6);
			taskInnExtVo.setAchievementForm(extractCellFromTable(achievementFormTable, 1, 0));
			// 项目成果的预期经济、社会效益
			taskInnExtVo.setAchievementBenefit(extractCellFromTable(achievementFormTable, 3, 0));
			// 研究方案
			Element researchTable = tableElements.get(7);
			taskInnExtVo.setResearchContentMethod(extractCellFromTable(researchTable, 1, 0));
			// 国际合作和交流方案
			taskInnExtVo.setExchangeProgramme(extractCellFromTable(researchTable, 3, 0));
			// 年度计划
			Element taskPreYearListArrayTable = tableElements.get(8);
			List<SrpmsProjectTaskVo> taskPreYearList = new ArrayList<SrpmsProjectTaskVo>();
			List<List<String>> taskPreYearListArray = extractListFromTable(taskPreYearListArrayTable, 1, 0);
			if (!org.springframework.util.CollectionUtils.isEmpty(taskPreYearListArray)) {
				for (List<String> rowData : taskPreYearListArray) {
					SrpmsProjectTaskVo taskVo = new SrpmsProjectTaskVo();
					taskVo.setTaskYear(StringConvertUtil.convert(rowData.get(0)).toString());
					taskVo.setFirstYearTarget(rowData.get(1));
					taskVo.setExamMode(rowData.get(2));
					taskPreYearList.add(taskVo);
				}
			}
			taskInnExtVo.setTaskPreYearList(taskPreYearList);
			// 项目管理合作机制
			Element promanageTable = tableElements.get(9);
			taskInnExtVo.setTaskOrgManageMode(extractCellFromTable(promanageTable, 1, 0));
			taskInnExtVo.setKnowledgeResultManage(extractCellFromTable(promanageTable, 3, 0));
			taskInnExtVo.setRiskAnalyzeManage(extractCellFromTable(promanageTable, 5, 0));

			// 研究队伍
			Element teamTable = tableElements.get(10);
			taskInnExtVo.setResearchMemberSize(
					StringConvertUtil.convert(extractCellFromTable(teamTable, 0, 0)).longValue());
			taskInnExtVo.setResearchWorkTime(
					StringConvertUtil.convert(extractCellFromTable(teamTable, 0, 1)).doubleValue());
			taskInnExtVo.setLeadPersonNote(extractCellFromTable(teamTable, 1, 1));
			taskInnExtVo.setBothTopNote(extractCellFromTable(teamTable, 2, 1));
			// 解析项目参与人员 lixin 不解析参与人员
			// List<SrpmsProjectPersonJoinVo> mostMemberList = new ArrayList<>();
			// List<List<String>> mostMemberArray =
			// extractListFromTable(tableElements.get(11), 1, 4);
			// for (List<String> personStr : mostMemberArray) {
			// SrpmsProjectPersonJoinVo personJoinVo = new SrpmsProjectPersonJoinVo();
			// personJoinVo.setPersonName(personStr.get(1));
			// personJoinVo.setGender(personStr.get(2));
			// personJoinVo.setBirthDate(personStr.get(3));
			// personJoinVo.setPositionTitle(personStr.get(4));
			// personJoinVo.setDegree(personStr.get(5));
			// personJoinVo.setDeptName(personStr.get(6));
			// personJoinVo.setPhone(personStr.get(7));
			// personJoinVo.setIdCard(personStr.get(8));
			// if (StringUtils.isNotBlank(personStr.get(9)) &&
			// StringUtils.isNumeric(personStr.get(9))) {
			// personJoinVo.setWorkPerYear(NumberUtils.toInt(personStr.get(9)));
			// }
			// mostMemberList.add(personJoinVo);
			// }
			// taskInnExtVo.setMainMemberList(mostMemberList);

			// 解析经费预算 不解析预算经费
			// List<SrpmsProjectBudgetDetailVo> budgetPreYearList = new ArrayList<>();
			// for (int i = 12; i < tableElements.size(); i++) {
			// Element tableElement = tableElements.get(i);
			// // 获取header
			// String tableHeader = extractCellFromTable(tableElement, 0, 0);
			// if (StringUtils.isBlank(tableHeader) || !tableHeader.contains("预算科目")) {
			// break;
			// }
			//
			// SrpmsProjectBudgetDetailVo budgetDetailVo = new SrpmsProjectBudgetDetailVo();
			// budgetDetailVo.setBudgetAmount(NumberUtils.toDouble(extractCellFromTable(tableElement,
			// 1, 1)));
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
			// taskInnExtVo.setBudgetPreYearList(budgetPreYearList);

			return taskInnExtVo;
		} catch (Exception e) {
			log.error("解析word文件发生异常.", e);
			throw new BaseException(PlatformErrorType.IMPORT_TEMPLATE_ERROR);
		}
	}

	@Override
	public void generateTaskInnPreBookPdf(Long projectId, UserVo userVo, DeptVo deptVo) {
		File pdfFile = null;
		FileInputStream fileInputStream = null;
		SrpmsProject project = projectService.getById(projectId);
		try {
			// 生成任务书pdf文档
			// String docxFilePath = this.exportTaskWord(projectId, userVo, deptVo);
			// String jarPath = new
			// File(ResourceUtils.getURL("").getPath()).getAbsolutePath();
			// String pdfFilePath = jarPath+ "/"+project.getProjectName()+"任务书.pdf";
			// WordConvertUtil.wordConvertToPdf(docxFilePath, pdfFilePath);
			String pdfFilePath = this.exportPdfTaskPre(projectId, userVo, deptVo);
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
					projectFile.setTaskBookFileId(NumberUtils.toLong(fileInfoVo.getId()));
					projectFile.setTaskBookFileName(fileInfoVo.getFileName());
					projectFile.setTaskBookFileUrl(fileInfoVo.getFileUrl());
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

	@Override
	public void generateTaskInnBcooBookPdf(Long projectId, UserVo userVo, DeptVo deptVo) {
		File pdfFile = null;
		FileInputStream fileInputStream = null;
		SrpmsProject project = projectService.getById(projectId);
		try {
			// 生成任务书pdf文档
			// String docxFilePath = this.exportBcooTaskWord(projectId, userVo, deptVo);
			// String jarPath = new
			// File(ResourceUtils.getURL("").getPath()).getAbsolutePath();
			// String pdfFilePath = jarPath+ "/"+project.getProjectName()+"任务书.pdf";
			// WordConvertUtil.wordConvertToPdf(docxFilePath, pdfFilePath);
			String pdfFilePath = this.exportPdfTaskBcoo(projectId, userVo, deptVo);
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
					projectFile.setTaskBookFileId(NumberUtils.toLong(fileInfoVo.getId()));
					projectFile.setTaskBookFileName(fileInfoVo.getFileName());
					projectFile.setTaskBookFileUrl(fileInfoVo.getFileUrl());
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

	@Override
	public void generateTaskInnCooBookPdf(Long projectId, UserVo userVo, DeptVo deptVo) {
		File pdfFile = null;
		FileInputStream fileInputStream = null;
		SrpmsProject project = projectService.getById(projectId);
		try {
			// 生成任务书pdf文档
			// String docxFilePath = this.exportCooTaskWord(projectId, userVo, deptVo);
			// String jarPath = new
			// File(ResourceUtils.getURL("").getPath()).getAbsolutePath();
			// String pdfFilePath = jarPath+ "/"+project.getProjectName()+"任务书.pdf";
			// WordConvertUtil.wordConvertToPdf(docxFilePath, pdfFilePath);
			String pdfFilePath = this.exportPdfTaskCoo(projectId, userVo, deptVo);
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
					projectFile.setTaskBookFileId(NumberUtils.toLong(fileInfoVo.getId()));
					projectFile.setTaskBookFileName(fileInfoVo.getFileName());
					projectFile.setTaskBookFileUrl(fileInfoVo.getFileUrl());
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

	@Override
	public JSONObject importTaskWord(WordImportReqVo reqVo) {
		try {
			String localPath = new File(ResourceUtils.getURL("").getPath()).getAbsoluteFile().toString();
			String htmlFilePath = WordConvertUtil.wordConvertToHtml(reqVo.getFileUrl(), localPath);
			// 解析html文件
			Document document = Jsoup.parse(new File(htmlFilePath), "utf-8");
			// Document document = Jsoup.parse(new File("C:\\Users\\junlicq\\Documents\\Work
			// Files\\科研\\doc\\a.html"), "utf-8");
			Elements tableElements = document.getElementsByTag("table");
			if (tableElements.isEmpty()) {
				throw new BaseException(SrpmsErrorType.NO_ELEMENTS);
			}

			// 1、任务基本信息
			Element taskBaseTable = tableElements.get(0);

			SrpmsProjectTaskInnExtVo taskInnExtVo = new SrpmsProjectTaskInnExtVo();

			// 项目负责人每年工作时间
			String leadPersonWorkTime = extractCellFromTable(taskBaseTable, 8, 5);
			if (NumberUtils.isParsable(leadPersonWorkTime)) {
				taskInnExtVo.setLeadPersonWorkTime(NumberUtils.toLong(leadPersonWorkTime));
			}

			// 国际合作单位信息
			List<SrpmsProjectDeptJoinVo> coopDeptList = buildCoopDeptList(taskBaseTable);
			taskInnExtVo.setCoopDeptList(coopDeptList);

			// 2、立项依据
			// 结合国内外研究现状和发展趋势，简述开展任务的目的、意义、紧迫性、必要性以及所面向的国家需求。(限800字)
			Element necessayTable = tableElements.get(1);
			taskInnExtVo.setApprovalNecessay(extractCellFromTable(necessayTable, 0, 0));

			// 3、研究目标和内容
			// （一）研究目标：简述任务主要预期结果以及所达到效果(限500字)。
			Element targetTable = tableElements.get(2);
			taskInnExtVo.setResearchTarget(extractCellFromTable(targetTable, 0, 0));

			// 研究内容：简述任务要解决的主要问题、难点和主要创新点（限1000字）。
			Element contentMainTable = tableElements.get(3);
			taskInnExtVo.setResearchContentMain(extractCellFromTable(contentMainTable, 0, 0));

			// （三）任务总体考核指标及测评方式/方法（应有量化指标，可考核，可比较，可追溯。限500字以内）。
			Element masterMethodTable = tableElements.get(4);
			taskInnExtVo.setTaskMasterMethod(extractCellFromTable(masterMethodTable, 0, 0));

			// 4、预期成果
			// （一）项目成果的呈现形式与描述（限500字以内）
			Element achievementFormTable = tableElements.get(5);
			taskInnExtVo.setAchievementForm(extractCellFromTable(achievementFormTable, 0, 0));

			// （二）项目成果的预期经济、社会效益（限500字以内）
			Element achievementBenefitTable = tableElements.get(6);
			taskInnExtVo.setAchievementBenefit(extractCellFromTable(achievementBenefitTable, 0, 0));

			// 5、研究方案
			// 拟采取的研究方法、技术路线及其可行性分析（技术路线可以图表形式显示，限1000字以内）。
			Element researchContentMethodTable = tableElements.get(7);
			taskInnExtVo.setResearchContentMethod(extractCellFromTable(researchContentMethodTable, 0, 0));

			// 6、年度计划
			Element taskPreYearListTable = tableElements.get(8);
			List<SrpmsProjectTaskVo> projectTaskVoList = buildProjectTaskList(taskPreYearListTable);
			taskInnExtVo.setTaskPreYearList(projectTaskVoList);

			// 7、任务管理与合作机制
			// （一）任务组织管理机制、产学研结合、创新人才队伍的凝聚和培养等（限500字以内）。
			Element taskOrgManageModeTable = tableElements.get(9);
			taskInnExtVo.setTaskOrgManageMode(extractCellFromTable(taskOrgManageModeTable, 0, 0));

			// （二）知识产权对策、成果管理及合作权益分配（限500字以内）。
			Element knowledgeResultManageTable = tableElements.get(10);
			taskInnExtVo.setKnowledgeResultManage(extractCellFromTable(knowledgeResultManageTable, 0, 0));

			// （三）风险分析及对策
			Element riskAnalyzeManageTable = tableElements.get(11);
			taskInnExtVo.setRiskAnalyzeManage(extractCellFromTable(riskAnalyzeManageTable, 0, 0));

			// 8、研究队伍
			Element researchMemberTable = tableElements.get(12);
			taskInnExtVo.setResearchMemberSize(
					StringConvertUtil.convert(extractCellFromTable(researchMemberTable, 0, 0)).longValue());
			taskInnExtVo.setResearchWorkTime(
					StringConvertUtil.convert(extractCellFromTable(researchMemberTable, 0, 1)).doubleValue());
			taskInnExtVo.setLeadPersonNote(extractCellFromTable(researchMemberTable, 1, 1));

			// 任务组成员信息 不解析人员 lixin
			// Element personJoinTable = tableElements.get(13);
			// int currentRow = 0;
			// List<SrpmsProjectPersonJoinVo> personJoinList = Lists.newArrayList();
			// currentRow = buildPersonJoinList(personJoinTable, personJoinList,
			// currentRow);
			// taskInnExtVo.setMainMemberList(personJoinList);

			// 经费预算 不解析经费预算 lixin
			// List<SrpmsProjectBudgetDetailVo> budgetPreYearList = new ArrayList<>();
			// for (int i = 14; i < tableElements.size(); i++) {
			// Element tableElement = tableElements.get(i);
			// // 获取header
			// String tableHeader = extractCellFromTable(tableElement, 0, 0);
			// if (StringUtils.isBlank(tableHeader) || !tableHeader.contains("预算科目")) {
			// break;
			// }
			//
			// SrpmsProjectBudgetDetailVo budgetDetailVo = new SrpmsProjectBudgetDetailVo();
			// budgetDetailVo.setBudgetAmount(NumberUtils.toDouble(extractCellFromTable(tableElement,
			// 1, 1)));
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
			// taskInnExtVo.setBudgetPreYearList(budgetPreYearList);

			JSONObject taskInnExtJsonObj = JSONObject.parseObject(JSONObject.toJSONString(taskInnExtVo));
			JSONConvert.convertJson(taskInnExtJsonObj);
			return taskInnExtJsonObj;

		} catch (FileNotFoundException e) {
			throw new BaseException(SrpmsErrorType.FILE_NO_FOUND);
		} catch (IOException e) {
			log.error("转换html io异常：", e);
			throw new BaseException(SrpmsErrorType.TRANSFER_HTML_ERROR);
		} catch (Exception e) {
			log.error("导入中期绩效考评报告系统异常", e);
			throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
		}
	}

	// 构造年度计划
	private List<SrpmsProjectTaskVo> buildProjectTaskList(Element table) {
		List<SrpmsProjectTaskVo> projectTaskVoList = Lists.newArrayList();
		Elements rowList = table.getElementsByTag("tr");
		// 去除第一行，从第二行开始取内容
		for (int i = 1; i < rowList.size(); i++) {
			Element rowElement = rowList.get(i);
			Elements cellElements = rowElement.getElementsByTag("td");
			SrpmsProjectTaskVo vo = new SrpmsProjectTaskVo();
			vo.setFirstYearTarget(cellElements.get(1).text());
			vo.setExamMode(cellElements.get(2).text());
			projectTaskVoList.add(vo);
		}
		return projectTaskVoList;
	}

	// 构造国际合作单位信息
	private List<SrpmsProjectDeptJoinVo> buildCoopDeptList(Element table) {
		List<SrpmsProjectDeptJoinVo> coopDeptList = Lists.newArrayList();
		Elements rowList = table.getElementsByTag("tr");
		for (int i = 10; i < rowList.size(); i++) {
			Element rowElement = rowList.get(i);
			Elements cellElements = rowElement.getElementsByTag("td");
			SrpmsProjectDeptJoinVo vo = new SrpmsProjectDeptJoinVo();
			String sort = cellElements.get(0).text();
			if (NumberUtils.isParsable(sort)) {
				vo.setSort(NumberUtils.toInt(sort));
			}
			vo.setDeptName(cellElements.get(1).text());
			vo.setTaskLeaderName(cellElements.get(2).text());
			vo.setCountry(cellElements.get(3).text());
			vo.setEmail(cellElements.get(4).text());
			coopDeptList.add(vo);
		}
		return coopDeptList;
	}

	// 构造任务组成员信息
	private int buildPersonJoinList(Element table, List<SrpmsProjectPersonJoinVo> personJoinList, int currentRow) {
		// 从第二行开始取
		currentRow += 1;
		Elements rowList = table.getElementsByTag("tr");
		for (int i = currentRow; i < rowList.size(); i++) {
			Element rowElement = rowList.get(i);
			Elements cellElements = rowElement.getElementsByTag("td");
			// 匹配到“总人数”退出整个循环
			if (StringUtils.equals(cellElements.get(0).text(), "总人数")) {
				// 退出前赋值当前行
				currentRow = i;
				break;
			}
			SrpmsProjectPersonJoinVo personJoinVo = new SrpmsProjectPersonJoinVo();
			personJoinVo.setPersonName(cellElements.get(1).text());
			personJoinVo.setGender(cellElements.get(2).text());
			personJoinVo.setBirthDateString(cellElements.get(3).text());
			personJoinVo.setPositionTitle(cellElements.get(4).text());
			personJoinVo.setDegree(cellElements.get(5).text());
			personJoinVo.setDeptName(cellElements.get(6).text());
			personJoinVo.setPhone(cellElements.get(7).text());
			personJoinVo.setIdCard(cellElements.get(8).text());
			if (NumberUtils.isParsable(cellElements.get(9).text())) {
				personJoinVo.setWorkPerYear(NumberUtils.toInt(cellElements.get(9).text()));
			}
			// TODO 签字
			personJoinList.add(personJoinVo);
		}
		return currentRow;
	}
}
