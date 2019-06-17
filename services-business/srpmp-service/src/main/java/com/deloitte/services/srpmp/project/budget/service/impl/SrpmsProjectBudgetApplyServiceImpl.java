package com.deloitte.services.srpmp.project.budget.service.impl;

import static com.deloitte.services.srpmp.common.util.HTMLParseUtil.extractCellFromTable;
import static com.deloitte.services.srpmp.common.util.HTMLParseUtil.extractListFromTable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
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
import org.springframework.beans.BeanUtils;
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
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fileservice.feign.FileOperatorFeignService;
import com.deloitte.platform.api.fileservice.vo.FileInfoVo;
import com.deloitte.platform.api.fssc.performance.feign.PerformanceIndexFeignService;
import com.deloitte.platform.api.fssc.performance.vo.PerformanceIndexVo;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectDeptJoinVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonJoinVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectTaskVo;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetDeviceVo;
import com.deloitte.platform.api.srpmp.project.budget.vo.ext.BudgetApplyVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.srpmp.common.DateTimeFormatUtil;
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
import com.deloitte.services.srpmp.common.util.BudgetDetailUtil;
import com.deloitte.services.srpmp.common.util.JSONConvert;
import com.deloitte.services.srpmp.common.util.StringConvertUtil;
import com.deloitte.services.srpmp.common.util.WordConvertUtil;
import com.deloitte.services.srpmp.outline.util.DateUtil;
import com.deloitte.services.srpmp.project.apply.service.ISrpmsProjectApplyInnBcooService;
import com.deloitte.services.srpmp.project.apply.service.ISrpmsProjectApplyInnCooService;
import com.deloitte.services.srpmp.project.apply.service.ISrpmsProjectApplyInnPreService;
import com.deloitte.services.srpmp.project.apply.service.impl.SrpmsProjectApplyInnCommonServiceImpl;
import com.deloitte.services.srpmp.project.apply.util.LocalDateUtils;
import com.deloitte.services.srpmp.project.apply.util.ProjectCheckUtils;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectPerson;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectDeptJoinService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectFlowService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectPersonJoinService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectPersonService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectTaskService;
import com.deloitte.services.srpmp.project.budget.dto.DeviceBudgetStatisDTO;
import com.deloitte.services.srpmp.project.budget.dto.TestBudgetStatisDTO;
import com.deloitte.services.srpmp.project.budget.entity.SrpmsProjectBudgetApply;
import com.deloitte.services.srpmp.project.budget.entity.SrpmsProjectBudgetTask;
import com.deloitte.services.srpmp.project.budget.mapper.SrpmsProjectBudgetApplyMapper;
import com.deloitte.services.srpmp.project.budget.service.ISrpmsProjectBudgetApplyService;
import com.deloitte.services.srpmp.project.budget.service.ISrpmsProjectBudgetDeviceService;
import com.deloitte.services.srpmp.project.budget.service.ISrpmsProjectBudgetTaskService;
import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskInnService;

import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-19
 * @Description : SrpmsProjectBudgetApply服务实现类
 * @Modified :
 */
@Slf4j
@Service
@Transactional
public class SrpmsProjectBudgetApplyServiceImpl extends
		ServiceImpl<SrpmsProjectBudgetApplyMapper, SrpmsProjectBudgetApply> implements ISrpmsProjectBudgetApplyService {

	@Autowired
	private ISrpmsProjectPersonService personService;

	@Autowired
	private ISrpmsProjectPersonJoinService personJoinService;

	@Autowired
	private ISrpmsProjectService projectService;

	@Autowired
	private ISrpmsProjectBudgetDeviceService deviceService;

	@Autowired
	private ISrpmsProjectApplyInnBcooService innBcooService;

	@Autowired
	private ISrpmsProjectApplyInnPreService innPreService;

	@Autowired
	private ISrpmsProjectApplyInnCooService innCooService;

	@Autowired
	private ISrpmsProjectTaskInnService taskInnService;

	@Autowired
	private ISrpmsProjectBudgetTaskService budgetTaskService;

	@Autowired
	private ISrpmsProjectBudgetApplyService budgetApplyService;

	@Autowired
	private ISrpmsProjectTaskService taskService;

	@Autowired
	SrpmsProjectApplyInnCommonServiceImpl commonService;

	@Autowired
	private ISrpmsProjectFlowService flowServicel;

	@Autowired
	private ISysDictService sysDictService;

	@Autowired
	private WordExportServiceImpl wordExportService;

	@Autowired
	private FileOperatorFeignService fileOperatorFeignService;

	@Autowired
	private PerformanceIndexFeignService performanceIndexFeignService;

	@Autowired
	private ISrpmsProjectDeptJoinService srpmsProjectDeptJoinServiceImpl;

	@Override
	public JSONObject queryById(Long projectId, UserVo user, DeptVo dept) {

		JSONObject relust = this.queryBudgetApplyById(projectId);

		JSONConvert.convertJson(relust);
		return relust;
	}

	@Override
	public JSONObject queryBudgetApplyById(Long projectId) {
		return this.queryById(projectId, false);
	}

	@Override
	public JSONObject queryBudgetTaskById(Long projectId) {
		return this.queryById(projectId, true);
	}

	@Override
	public JSONObject queryById(Long projectId, boolean taskFlg) {
		JSONObject relust = new JSONObject();

		boolean perFlg = false;
		// 获取项目信息
		SrpmsProject projectEntity = projectService.getById(projectId);
		if (projectEntity == null) {
			throw new BaseException(PlatformErrorType.NO_DATA_FOUND);
		}
		relust.putAll(JSONObject.parseObject(JSON.toJSONString(projectEntity)));

		// 获取预算表信息
		if (taskFlg) {
			if ("true".equals(projectEntity.getTaskBudFirstOpenFlg())) {
				relust.put("firstFlg", "true");
				projectEntity.setTaskBudFirstOpenFlg("false");
				projectService.updateById(projectEntity);
			} else {
				relust.put("firstFlg", "false");
			}
			SrpmsProjectBudgetTask budgetTask = budgetTaskService.getById(projectId);
			relust.putAll(JSONObject.parseObject(JSON.toJSONString(budgetTask)));
		} else {
			SrpmsProjectBudgetApply budgetApplyEntity = this.getById(projectId);
			if (budgetApplyEntity != null) {
				relust.putAll(JSONObject.parseObject(JSON.toJSONString(budgetApplyEntity)));
				relust.put("firstFlg", "false");
			} else {

				if ("true".equals(projectEntity.getBudFirstOpenFlg())) {
					relust.put("firstFlg", "true");
					projectEntity.setBudFirstOpenFlg("false");
					projectService.updateById(projectEntity);
				} else {
					relust.put("firstFlg", "false");
				}
			}
		}

		PersonJoinWayEnums personJoinWay = PersonJoinWayEnums.APPLY_MAIN_MEMBER;
		BudgetCategoryEnums budgetCategoryDevice = null;
		BudgetCategoryEnums budgetCategoryTest = null;
		TaskCategoryEnums taskCategory = TaskCategoryEnums.APPLY_INNOVATE_TASK_DECOMPOSITION;
		TaskCategoryEnums budgetDept = TaskCategoryEnums.APPLY_INNOVATE_BUDGET_DEPT;
		TaskCategoryEnums budgetAll = TaskCategoryEnums.APPLY_INNOVATE_BUDGET_ALL;
		DeptJoinWayEnums coopDept = DeptJoinWayEnums.APPLY_INNOVATE_COPPDEPT;
		if (taskFlg) {
			coopDept = DeptJoinWayEnums.TASK_INNOVATE_COPPDEPT;
			personJoinWay = PersonJoinWayEnums.TASK_MAIN_MEMBER;
			budgetCategoryDevice = BudgetCategoryEnums.TASK_INNOVATE_BUDGET_DEVICE;
			budgetCategoryTest = BudgetCategoryEnums.TASK_INNOVATE_BUDGET_TEST;
			taskCategory = TaskCategoryEnums.TASK_INNOVATE_TASK_DECOMPOSITION;
			budgetDept = TaskCategoryEnums.TASK_INNOVATE_BUDGET_DEPT;
			budgetAll = TaskCategoryEnums.TASK_INNOVATE_BUDGET_ALL;
		} else {
			budgetCategoryDevice = BudgetCategoryEnums.APPLY_INNOVATE_BUDGET_DEVICE;
			budgetCategoryTest = BudgetCategoryEnums.APPLY_INNOVATE_BUDGET_TEST;
		}

		// 国家合作单位信息
		List<SrpmsProjectDeptJoinVo> coopDeptList = srpmsProjectDeptJoinServiceImpl.queryDeptJoinListByJoinWay(coopDept,
				projectId);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < coopDeptList.size(); i++) {
			if (i != 0) {
				sb.append(",");
			}
			sb.append(coopDeptList.get(i).getDeptName());
			// 合作单位
		}
		relust.put("coopDeptName", sb.toString());

		// 项目联系人
		if (relust.get("projectContactPersonId") != null && relust.getLong("projectContactPersonId") != 0L) {
			SrpmsProjectPerson personEntity = personService.getById(relust.getLong("projectContactPersonId"));

			if (personEntity != null) {
				relust.put("contactPerson", personEntity);
			}
		}

		// 项目负责人
		if (relust.get("projectFinancePersonId") != null && relust.getLong("projectFinancePersonId") != 0L) {
			SrpmsProjectPerson personEntity = personService.getById(relust.getLong("projectFinancePersonId"));
			if (personEntity != null) {
				relust.put("financePerson", personEntity);
			}
		}

		// 分任务预算表
		relust.put("taskDecompositionList", taskService.queryTaskListByTaskCategory(taskCategory, projectId));

		// 单位预算表
		relust.put("budgetDeptList", taskService.queryTaskListByTaskCategory(budgetDept, projectId));

		// 总预算表
		relust.put("budgetAllList", taskService.queryTaskListByTaskCategory(budgetAll, projectId));

		// 设备预算明细
		relust.put("deviceBudgetList", deviceService.queryBudgetDeviceList(budgetCategoryDevice, projectId));

		// 测试预算明细
		relust.put("testBudgetList", deviceService.queryBudgetDeviceList(budgetCategoryTest, projectId));

		// 项目参加人员
		List<SrpmsProjectPersonJoinVo> personList = personJoinService.queryPersonJoinListByJoinWay(personJoinWay,
				projectId);
		List<SrpmsProjectPersonJoinVo> allPersonList = new ArrayList<>();
		if (personList != null && personList.size() > 0) {
			for (int i = 0; i < personList.size(); i++) {
				allPersonList.add(personList.get(i));
			}
		}
		for (int i = 0; i < allPersonList.size(); i++) {
			SrpmsProjectPersonJoinVo person = allPersonList.get(i);
			person.setPersonRole("其他研究人员");
			JSONArray taskDecompositionList = relust.getJSONArray("taskDecompositionList");
			if (taskDecompositionList != null && taskDecompositionList.size() > 0) {
				for (int j = 0; j < taskDecompositionList.size(); j++) {
					if (taskDecompositionList.getJSONObject(j).getLong("leadPerson") == null) {
						continue;
					}
					if (person.getPersonId() == null) {
						continue;
					}
					if (person.getPersonId().longValue() == projectEntity.getLeadPersonId().longValue()) {
						person.setPersonRole("项目负责人");
						continue;
					}
					if (person.getPersonId().longValue() == taskDecompositionList.getJSONObject(j).getLong("leadPerson")
							.longValue()) {
						person.setPersonRole("任务负责人");
						continue;
					}
				}
			}
		}
		relust.put("mainMemberList", allPersonList);
		JSONConvert.convertJson(relust);
		return relust;
	}

	@Override
	public String saveOrUpdateBudgetApply(BudgetApplyVo vo) {

		return this.saveOrUpdateBudgetApply(vo, false);
	}

	@Override
	public String saveOrUpdateBudgetApply(BudgetApplyVo vo, boolean taskFlg) {

		long projectId = vo.getId();
		String strJson = JSON.toJSONString(vo);
		JSONObject json = JSONObject.parseObject(strJson);

		// 获取项目信息
		SrpmsProject projectEntity = projectService.getById(projectId);
		if (projectEntity == null) {
			throw new BaseException(PlatformErrorType.NO_DATA_FOUND);
		}

		SrpmsProjectPerson contact = new SrpmsProjectPerson();
		if (vo.getContactPerson() != null) {
			BeanUtils.copyProperties(vo.getContactPerson(), contact);
			personService.saveOrUpdate(contact);
		}

		SrpmsProjectPerson finance = new SrpmsProjectPerson();
		if (vo.getFinancePerson() != null) {
			BeanUtils.copyProperties(vo.getFinancePerson(), finance);
			personService.saveOrUpdate(finance);
		}

		PersonJoinWayEnums personJoinWay = PersonJoinWayEnums.APPLY_MAIN_MEMBER;
		BudgetCategoryEnums budgetCategoryDevice = BudgetCategoryEnums.APPLY_INNOVATE_BUDGET_DEVICE;
		BudgetCategoryEnums budgetCategoryTest = BudgetCategoryEnums.APPLY_INNOVATE_BUDGET_TEST;
		TaskCategoryEnums taskCategory = TaskCategoryEnums.APPLY_INNOVATE_TASK_DECOMPOSITION;
		TaskCategoryEnums budgetDept = TaskCategoryEnums.APPLY_INNOVATE_BUDGET_DEPT;
		TaskCategoryEnums budgetAll = TaskCategoryEnums.APPLY_INNOVATE_BUDGET_ALL;
		if (taskFlg) {
			JSONConvert.convertJson(json);
			SrpmsProjectBudgetTask entity = JSONObject.parseObject(json.toJSONString(), SrpmsProjectBudgetTask.class);

			entity.setProjectContactPersonId(contact.getId());
			entity.setProjectFinancePersonId(finance.getId());
			// 更新预算书基本字段
			budgetTaskService.saveOrUpdate(entity);

			personJoinWay = PersonJoinWayEnums.TASK_MAIN_MEMBER;
			budgetCategoryDevice = BudgetCategoryEnums.TASK_INNOVATE_BUDGET_DEVICE;
			budgetCategoryTest = BudgetCategoryEnums.TASK_INNOVATE_BUDGET_TEST;
			taskCategory = TaskCategoryEnums.TASK_INNOVATE_TASK_DECOMPOSITION;
			budgetDept = TaskCategoryEnums.TASK_INNOVATE_BUDGET_DEPT;
			budgetAll = TaskCategoryEnums.TASK_INNOVATE_BUDGET_ALL;
		} else {

			JSONConvert.convertJson(json);
			SrpmsProjectBudgetApply entity = JSONObject.parseObject(json.toJSONString(), SrpmsProjectBudgetApply.class);
			entity.setProjectContactPersonId(contact.getId());
			entity.setProjectFinancePersonId(finance.getId());
			// 更新预算书基本字段
			this.saveOrUpdate(entity);
		}

		// 设备预算明细
		deviceService.cleanAndSaveBudgetDevice(vo.getDeviceBudgetList(), budgetCategoryDevice, projectId);

		// 测试预算明细
		deviceService.cleanAndSaveBudgetDevice(vo.getTestBudgetList(), budgetCategoryTest, projectId);

		// 项目参与人员
		JSONObject leadPersonJson = JSONObject.parseObject(projectEntity.getLeadPerson());
		JSONObject bothTopExpertPersonJson = null;
		if (projectEntity.getBothTopExpertPersonId() != null) {
			bothTopExpertPersonJson = JSONObject.parseObject(projectEntity.getBothTopExpertPerson());
		}

		boolean projectUpdateFlg = false;
		if (leadPersonJson != null) {
			projectEntity.setLeadPerson(leadPersonJson.toJSONString());
			projectUpdateFlg = true;
		}
		if (json.getString("leadDept") != null) {
			projectEntity.setLeadDept(json.getString("leadDept"));
		}
		if (bothTopExpertPersonJson != null) {
			projectEntity.setBothTopExpertPerson(bothTopExpertPersonJson.toJSONString());
			projectUpdateFlg = true;
		}
		if (projectUpdateFlg) {
			projectService.saveOrUpdate(projectEntity);
		}
		personJoinService.cleanAndSavePersonJoin(vo.getMainMemberList(), personJoinWay, projectId);

		// 分任务预算表
		taskService.cleanAndSaveTask(vo.getTaskDecompositionList(), taskCategory, projectId);

		// 分单位预算表
		taskService.cleanAndSaveTask(vo.getBudgetDeptList(), budgetDept, projectId);

		// 总预算表
		taskService.cleanAndSaveTask(vo.getBudgetAllList(), budgetAll, projectId);
		return projectId + "";
	}

	@Override
	public String exportApplyWordFile(Long projectId, UserVo userVo, DeptVo deptVo) {
		String fileUrl = null;
		String filename = "创新工程项目预算申请书_" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS")
				+ RandomUtils.nextInt(0, 9999) + ".docx";
		try {
			Map<String, Object> dataMap = new HashMap<>();
			JSONObject voJson = null;
			if (projectId != null && projectId.equals(0L)) {
				voJson = new JSONObject();
				voJson.put("leadDept", JSONObject.parseObject(JSONObject.toJSONString(deptVo)));
				voJson.put("leadPerson", JSONObject.parseObject(JSONObject.toJSONString(userVo)));
				voJson.put("mainMemberList", new ArrayList<>());
				voJson.put("tempMemberCount", 0);
				voJson.put("performanceIndicatorDetail", new ArrayList<>());
				voJson.put("taskDecompositionList", new ArrayList<>());
			} else {
				voJson = this.queryBudgetApplyById(projectId);
			}
			fileUrl = exportFromVoJson(voJson, filename);
		} catch (Exception e) {
			log.error("导出word异常。", e);
			throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
		}
		return fileUrl;
	}


	@Override
	public String exportApplyPdfFile(Long projectId, UserVo userVo, DeptVo deptVo) {
		String docxFilePath = this.exportApplyWordFile(projectId, userVo, deptVo);
		String pdfFinalName = docxFilePath.replace("docx", "pdf");
		// 转格式
		WordConvertUtil.docx2PDF(docxFilePath, pdfFinalName);
		return pdfFinalName;
	}


	@Override
	public String exportTaskWordFile(Long projectId, UserVo userVo, DeptVo deptVo) {
		String fileUrl = null;
		String filename = "创新工程项目预算任务书_" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS")
				+ RandomUtils.nextInt(0, 9999) + ".docx";
		try {
			Map<String, Object> dataMap = new HashMap<>();
			JSONObject voJson = null;
			if (projectId != null && projectId.equals(0L)) {
				voJson = new JSONObject();
				voJson.put("leadDept", JSONObject.parseObject(JSONObject.toJSONString(deptVo)));
				voJson.put("leadPerson", JSONObject.parseObject(JSONObject.toJSONString(userVo)));
				voJson.put("mainMemberList", new ArrayList<>());
				voJson.put("tempMemberCount", 0);
				voJson.put("performanceIndicatorDetail", new ArrayList<>());
				voJson.put("taskDecompositionList", new ArrayList<>());
			} else {
				voJson = this.queryBudgetTaskById(projectId);
			}
			fileUrl = exportFromVoJson(voJson, filename);
		} catch (Exception e) {
			log.error("导出word异常。", e);
			throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
		}
		return fileUrl;
	}

	@Override
	public String exportTaskPdfFile(Long projectId, UserVo userVo, DeptVo deptVo) {
		String docxFilePath = this.exportTaskWordFile(projectId, userVo, deptVo);
		String pdfFinalName = docxFilePath.replace("docx", "pdf");
		// 转格式
		WordConvertUtil.docx2PDF(docxFilePath, pdfFinalName);
		return pdfFinalName;
	}

	@Override
	public String exportFromVoJson(JSONObject voJson, String fileName)
			throws IOException, InterruptedException, TemplateException {
		Map<String, Object> dataMap = new HashMap<>();
		InputStream fileIn = null;
		String fileUrl = null;

		// 项目类别
		String projectTypeChk = "重大协同创新项目□/协同创新团队项目□/先导专项□";
		String projectType = voJson.getString("projectType");
		if (ProjectCategoryEnums.INNOVATE_PRE.getHeader().equals(projectType)) {
			projectTypeChk = "重大协同创新项目□/协同创新团队项目□/先导专项▉";
		} else if (ProjectCategoryEnums.INNOVATE_BCOO.getHeader().equals(projectType)) {
			projectTypeChk = "重大协同创新项目▉/协同创新团队项目□/先导专项□";
		} else if (ProjectCategoryEnums.INNOVATE_COO.getHeader().equals(projectType)) {
			projectTypeChk = "重大协同创新项目□/协同创新团队项目▉/先导专项□";
		}

		// 分任务预算明细
		// List<SrpmsProjectTaskVo> taskBudgetList =
		// voJson.getObject("taskDecompositionList",
		// new TypeReference<List<SrpmsProjectTaskVo>>() {
		// });

		// 计算总预算明细
		SrpmsProjectTaskVo budgetDetailAmount = new SrpmsProjectTaskVo();
		budgetDetailAmount.setBudgetAmount("");

		JSONArray budgetAllListArr = voJson.getJSONArray("budgetAllList");

		if (budgetAllListArr == null || budgetAllListArr.size() == 0) {
			// TODO 未根据类型初始化明细
			JSONArray budgetDetailAll = BudgetDetailUtil
					.emptyBudgetDetailByCategroy(ProjectCategoryEnums.INNOVATE_PRE.getHeader());
			budgetDetailAmount.setBudgetDetail(budgetDetailAll);
		} else {
			// List<JSONArray> taskDetaiList = new ArrayList<>();
			// for (SrpmsProjectTaskVo taskVo : taskBudgetList) {
			// if (taskVo.getBudgetDetail() == null) {
			// taskVo.setBudgetDetail(BudgetDetailUtil.emptyBudgetDetailByCategroy(projectType));
			// }
			// taskDetaiList.add(taskVo.getBudgetDetail());
			//
			// JSONArray joinPersonArr = taskVo.getJoinPersonInfo();
			// List<String> joinPersonNameArr = Lists.newArrayList();
			// if (joinPersonArr!=null && joinPersonArr.size() > 0){
			// for (int i = 0 ; i < joinPersonArr.size(); i++){
			// JSONObject obj = joinPersonArr.getJSONObject(i);
			// if (obj != null && obj.getString("personName") != null){
			// joinPersonNameArr.add(obj.getString("personName"));
			// }
			// }
			// }
			// if (joinPersonNameArr.size()>0){
			// taskVo.setJoinPersonNameStr(StringUtils.join(joinPersonNameArr, ","));
			// }
			// if (taskVo.getLeadPersonInfo() != null){
			// taskVo.setLeadPersonName(taskVo.getLeadPersonInfo().getString("personName"));
			// }
			// }
			// budgetDetailAmount = BudgetDetailUtil.mergeBudgetDetail(taskDetaiList);
			JSONObject budgetAll = budgetAllListArr.getJSONObject(0);
			budgetDetailAmount.setBudgetAmount(budgetAll.getString("budgetAmount"));
			budgetDetailAmount.setBudgetDetail(budgetAll.getJSONArray("budgetDetail"));
			budgetDetailAmount.setBudgetRemark(budgetAll.getString("budgetRemark"));
		}

		// 计算分单位预算明细
		// List<SrpmsProjectTaskVo> deptBudgetList = new ArrayList<>();
		// ImmutableListMultimap<String, SrpmsProjectTaskVo> taskDeptMap =
		// Multimaps.index(taskBudgetList,
		// new Function<SrpmsProjectTaskVo, String>() {
		// @Override
		// public String apply(SrpmsProjectTaskVo srpmsProjectTaskVo) {
		// if (srpmsProjectTaskVo.getDeptName() == null) {
		// srpmsProjectTaskVo.setDeptName("");
		// }
		// return srpmsProjectTaskVo.getDeptName();
		// }
		// });
		// for (String deptName : taskDeptMap.keySet()) {
		// if (!"".equals(deptName)) {
		// SrpmsProjectTaskVo deptTaskVo = new SrpmsProjectTaskVo();
		// ImmutableList<SrpmsProjectTaskVo> sublist = taskDeptMap.get(deptName);
		// List<JSONArray> deptDetailList = new ArrayList<>();
		// Double amount = new Double(0);
		// for (SrpmsProjectTaskVo taskVo : sublist) {
		// deptDetailList.add(taskVo.getBudgetDetail());
		// if (NumberUtils.isParsable(taskVo.getBudgetAmount())) {
		// amount = (Double) MathUtils.add(amount,
		// NumberUtils.toDouble(taskVo.getBudgetAmount(), 0));
		// }
		// // 填充信息
		// BeanUtils.copyProperties(taskVo, deptTaskVo);
		// }
		// // 覆盖有区别的信息
		// JSONArray deptBudgetAll =
		// BudgetDetailUtil.mergeBudgetDetailReturnDetail(deptDetailList);
		// deptTaskVo.setBudgetDetail(deptBudgetAll);
		// deptTaskVo.setDeptName(deptName);
		// deptTaskVo.setBudgetAmount(String.valueOf(amount));
		// String deptQuality = "参与";
		// if (voJson.getJSONObject("leadDept") != null
		// && deptName.equals(voJson.getJSONObject("leadDept").getString("deptName"))) {
		// deptQuality = "牵头";
		// }
		// deptTaskVo.setDeptQuality(deptQuality);
		// deptBudgetList.add(deptTaskVo);
		// }
		// }

		// 分单位预算表，计算参与类型
		JSONArray deptBudgetList = voJson.getJSONArray("budgetDeptList");
		JSONArray deptBudgetListNew = new JSONArray();
		if (deptBudgetList != null && deptBudgetList.size() > 0) {
			for (int i = 0; i < deptBudgetList.size(); i++) {
				JSONObject deptBudget = deptBudgetList.getJSONObject(i);
				String deptName = deptBudget.getString("deptName");
				String deptQuality = "参与单位";
				if (voJson.getJSONObject("leadDept") != null && deptName != null
						&& deptName.equals(voJson.getJSONObject("leadDept").getString("deptName"))) {
					deptQuality = "牵头单位";
				}
				deptBudget.put("deptQuality", deptQuality);
				deptBudgetListNew.add(deptBudget);
			}
		}

		// 统计设备明细
		DeviceBudgetStatisDTO deviceStatis = new DeviceBudgetStatisDTO();
		deviceStatis.setCountDeviceLess(
				voJson.getInteger("countDeviceLess") == null ? 0 : voJson.getInteger("countDeviceLess"));
		deviceStatis.setCountReagentLess(
				voJson.getInteger("countReagentLess") == null ? 0 : voJson.getInteger("countReagentLess"));
		deviceStatis.setPriceDeviceLess(
				voJson.getDouble("priceDeviceLess") == null ? 0D : voJson.getDouble("priceDeviceLess"));
		deviceStatis.setPriceReagentLess(
				voJson.getDouble("priceReagentLess") == null ? 0D : voJson.getDouble("priceReagentLess"));
		List<SrpmsProjectBudgetDeviceVo> deviceBudgetList = voJson.getObject("deviceBudgetList",
				new TypeReference<List<SrpmsProjectBudgetDeviceVo>>() {
				});
		if (deviceBudgetList != null) {
			for (SrpmsProjectBudgetDeviceVo deviceVo : deviceBudgetList) {
				deviceVo.setDeviceType(
						sysDictService.selectValueByCode(SysDictEnums.DEVICE_TYPE, deviceVo.getDeviceType()));
				String priceStr = deviceVo.getUnitPrice();
				String countStr = deviceVo.getDeviceCount();
				if (!NumberUtils.isParsable(priceStr) || !NumberUtils.isParsable(countStr)) {
					break;
				}
				double price = NumberUtils.toDouble(priceStr);
				double count = NumberUtils.toDouble(countStr);
				if (deviceVo.getDeviceCat() != null && deviceVo.getDeviceCat().equals("2")) { // 试剂
					if (price >= 100000) {
						deviceStatis.setCountReagentMore(deviceStatis.getCountReagentMore() + (int) count);
						deviceStatis.setPriceReagentMore(deviceStatis.getPriceReagentMore() + count * price / 10000);
					}
					// else{
					// deviceStatis.setCountReagentLess(deviceStatis.getCountReagentLess() +
					// (int)count);
					// deviceStatis.setPriceReagentLess(deviceStatis.getPriceReagentLess() + count *
					// price / 10000);
					// }
				} else { // 设备
					if (price >= 100000) {
						deviceStatis.setCountDeviceMore(deviceStatis.getCountDeviceMore() + (int) count);
						deviceStatis.setPriceDeviceMore(deviceStatis.getPriceDeviceMore() + count * price / 10000);
					}
					// else{
					// deviceStatis.setCountDeviceLess(deviceStatis.getCountDeviceLess() +
					// (int)count);
					// deviceStatis.setPriceDeviceLess(deviceStatis.getPriceDeviceLess() + count *
					// price / 10000);
					// }
				}
			}
			Integer countAmount = deviceStatis.getCountDeviceLess() + deviceStatis.getCountDeviceMore()
					+ deviceStatis.getCountReagentLess() + deviceStatis.getCountReagentMore();
			Double priceAmount = deviceStatis.getPriceDeviceLess() + deviceStatis.getPriceDeviceMore()
					+ deviceStatis.getPriceReagentLess() + deviceStatis.getPriceReagentMore();
			deviceStatis.setCountAmount(countAmount);
			deviceStatis.setPriceAmount(priceAmount);
		} else {
			deviceBudgetList = new ArrayList<>();
		}

		// 统计测试费用明细
		TestBudgetStatisDTO testStatis = new TestBudgetStatisDTO();
		testStatis
				.setAmountOther(voJson.getDouble("otherTestAmount") == null ? 0D : voJson.getDouble("otherTestAmount"));
		List<SrpmsProjectBudgetDeviceVo> testBudgetList = voJson.getObject("testBudgetList",
				new TypeReference<List<SrpmsProjectBudgetDeviceVo>>() {
				});
		if (testBudgetList != null) {
			for (SrpmsProjectBudgetDeviceVo deviceVo : testBudgetList) {
				String amountStr = deviceVo.getAmount();
				if (!NumberUtils.isParsable(amountStr)) {
					break;
				}
				double amount = NumberUtils.toDouble(amountStr);
				if (amount >= 5) {
					testStatis.setAmountMore(testStatis.getAmountMore() + amount);
				}
				// else{
				// testStatis.setAmountOther(testStatis.getAmountOther() + amount);
				// }
			}
			testStatis.setAmountAll(testStatis.getAmountMore() + testStatis.getAmountOther());
		} else {
			testBudgetList = new ArrayList<>();
		}

		// // 绩效指标字典值替换
		// JSONArray performanceIndicatorDetail =
		// voJson.getJSONArray("performanceIndicatorDetail");
		// if (performanceIndicatorDetail != null && performanceIndicatorDetail.size() >
		// 0) {
		// for (int i = 0; i < performanceIndicatorDetail.size(); i++) {
		// JSONObject performanceObj = performanceIndicatorDetail.getJSONObject(i);
		// List<String> quotaTypeList = performanceObj.getObject("quotaType", new
		// TypeReference<List<String>>() {
		// });
		// if (quotaTypeList == null || quotaTypeList.size() == 0) {
		// quotaTypeList = Lists.newArrayList("", "", "");
		// } else {
		// String libraryId = voJson.getString("performanceLibraryCode");
		// Result<List<PerformanceIndexVo>> indexResult = performanceIndexFeignService
		// .search(Long.parseLong(libraryId));
		// if (!CollectionUtils.isEmpty(indexResult.getData())) {
		// Map<String, String> valueMap = Maps.newHashMap();
		// getQuotaTypeValue(indexResult.getData(), valueMap);
		// for (int j = 0; j < quotaTypeList.size(); j++) {
		// String code = quotaTypeList.get(j);
		// // String value = sysDictService.selectValueByCode(SysDictEnums.BE_CURRENT,
		// // code);
		// String value = MapUtils.getString(valueMap, code);
		// quotaTypeList.set(j, value);
		// }
		// }
		//
		// }
		// performanceObj.put("quotaType", quotaTypeList);
		// }
		// }

		// 日期格式化
		DateTimeFormatUtil.formatInnerDateTime(voJson, "yyyy年MM月dd日", "projectActionDateStart", "projectActionDateEnd",
				"budgetActionDateStart", "budgetActionDateEnd");

		// HTML格式转换
		voJson.put("projectTarget", WordConvertUtil.htmlToText(voJson.getString("projectTarget")));
		WordConvertUtil.batchHtmlToText(voJson, "projectTarget", "specifSupport", "specifMoneyPlan", "specifFacility",
				"specifMaterial", "specifTest", "specifFuel", "specifTravel", "specifPublish", "specifLabour",
				"specifConsult", "specifOther");

		if (voJson.get("tempMemberCount") == null) {
			voJson.put("tempMemberCount", 0);
		}

		String tempMemberCount = voJson.getString("tempMemberCount");
		int tempMemberCountInt = NumberUtils.toInt(tempMemberCount, 0);
		voJson.put("tempMemberCount", tempMemberCountInt);

		dataMap.put("p", voJson);
		// 承担单位
		dataMap.put("leadDept", voJson.getJSONObject("leadDept"));
		// 责任人
		dataMap.put("leadPerson", voJson.getJSONObject("leadPerson"));
		// 联系人
		dataMap.put("contactPerson", voJson.getJSONObject("contactPerson"));
		// 财务负责人
		dataMap.put("financePerson", voJson.getJSONObject("financePerson"));
		// 总预算明细
		dataMap.put("budgetDetailAmount", budgetDetailAmount);
		// 分单位预算表
		dataMap.put("deptBudgetList", deptBudgetListNew);
		// 分任务预算表
		dataMap.put("taskBudgetList", voJson.getJSONArray("taskDecompositionList"));
		// 设备统计
		dataMap.put("deviceStatis", deviceStatis);
		// 设备列表
		dataMap.put("deviceBudgetList", deviceBudgetList);
		// 测试化验加工费预算明细表
		dataMap.put("testBudgetList", testBudgetList);
		// 测试化验加工费预算明细表统计信息
		dataMap.put("testStatis", testStatis);
		dataMap.put("projectTypeChk", projectTypeChk);

		String filename = "创新工程项目预算申请书_" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS")
				+ RandomUtils.nextInt(0, 9999) + ".docx";
		String finalWordName = wordExportService.exportWord("template_budget_apply_v2", dataMap, filename);

		fileIn = new FileInputStream(finalWordName);
		fileUrl = finalWordName;

		IOUtils.closeQuietly(fileIn);

		return fileUrl;
	}

	// public static void main(String[] args) {
	//
	// PerformanceIndexVo vo1 = new PerformanceIndexVo();
	// vo1.setCode("1");
	// vo1.setName("1name");
	// PerformanceIndexVo vo2 = new PerformanceIndexVo();
	// vo2.setCode("2");
	// vo2.setName("2name");
	//
	// PerformanceIndexVo vo3 = new PerformanceIndexVo();
	// vo3.setCode("3");
	// vo3.setName("3name");
	// List<PerformanceIndexVo> list3 = Lists.newArrayList(vo3);
	// vo2.setChildIndexVoList(list3);
	// List<PerformanceIndexVo> list2 = Lists.newArrayList(vo2);
	// vo1.setChildIndexVoList(list2);
	// List<PerformanceIndexVo> list = Lists.newArrayList();
	// list.add(vo1);
	// PerformanceIndexVo vo4 = new PerformanceIndexVo();
	// vo4.setCode("4");
	// vo4.setName("4name");
	// list.add(vo4);
	// Map<String, String> res = Maps.newHashMap();
	// getQuotaTypeValue(list, res);
	// }

	private Map<String, String> getQuotaTypeValue(List<PerformanceIndexVo> indexVoList, Map<String, String> result) {
		for (PerformanceIndexVo indexVo : indexVoList) {
			result.put(indexVo.getCode(), indexVo.getName());
			if (!CollectionUtils.isEmpty(indexVo.getChildIndexVoList())) {
				getQuotaTypeValue(indexVo.getChildIndexVoList(), result);
			}
		}
		return result;
	}

	@Override
	public void generateBudgetApplyPdf(Long projectId, UserVo userVo, DeptVo deptVo) {
		File pdfFile = null;
		FileInputStream fileInputStream = null;
		SrpmsProject project = projectService.getById(projectId);
		try {
			// 生成预算申请书pdf文档
			String docxFilePath = this.exportApplyWordFile(projectId, userVo, deptVo);
			String jarPath = new File(ResourceUtils.getURL("").getPath()).getAbsolutePath();
			String pdfFilePath = jarPath + "/" + project.getProjectName() + "预算申请书.pdf";
			WordConvertUtil.docx2PDF(docxFilePath, pdfFilePath);
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
					projectFile.setBudgetBookFileId(NumberUtils.toLong(fileInfoVo.getId()));
					projectFile.setBudgetBookFileName(fileInfoVo.getFileName());
					projectFile.setBudgetBookFileUrl(fileInfoVo.getFileUrl());
					projectFile.updateById();
				}
				pdfFile.delete();
			} else {
				log.error("预算书上传文件服务器失败。");
				throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
			}
		} catch (Exception e) {
			log.error("生成预算书pdf异常.", e);
			throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
		} finally {
			IOUtils.closeQuietly(fileInputStream);
			if (pdfFile != null && pdfFile.exists()) {
				pdfFile.delete();
			}
		}

	}

	@Override
	public void generateBudgetTaskPdf(Long projectId, UserVo userVo, DeptVo deptVo) {
		File pdfFile = null;
		FileInputStream fileInputStream = null;
		SrpmsProject project = projectService.getById(projectId);
		try {
			// 生成预算任务书pdf文档
			String docxFilePath = this.exportTaskWordFile(projectId, userVo, deptVo);
			String jarPath = new File(ResourceUtils.getURL("").getPath()).getAbsolutePath();
			String pdfFilePath = jarPath + "/" + project.getProjectName() + "预算任务书.pdf";
			WordConvertUtil.docx2PDF(docxFilePath, pdfFilePath);
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
					projectFile.setBudgetTaskBookFileId(NumberUtils.toLong(fileInfoVo.getId()));
					projectFile.setBudgetTaskBookFileName(fileInfoVo.getFileName());
					projectFile.setBudgetTaskBookFileUrl(fileInfoVo.getFileUrl());
					projectFile.updateById();
				}
				pdfFile.delete();
			} else {
				log.error("预算书上传文件服务器失败。");
				throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
			}
		} catch (Exception e) {
			log.error("生成预算书pdf异常.", e);
			throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
		} finally {
			IOUtils.closeQuietly(fileInputStream);
			if (pdfFile != null && pdfFile.exists()) {
				pdfFile.delete();
			}
		}

	}

	@Override
	public void submitBudgetApply(BudgetApplyVo vo, UserVo userVo, DeptVo deptVo) {
		log.info("submitBudgetApply 提交预算申请书, 开始.projectId:{}", vo.getId());
		long projectId = vo.getId();
		// 查询项目
		SrpmsProject projectEntity = projectService.getById(projectId);
		if (projectEntity == null) {
			throw new BaseException(PlatformErrorType.NO_DATA_FOUND);
		}
		if (!SrpmsProjectStatusEnums.PEROJECT_NOT_SUBMIT.getCode().equals(projectEntity.getStatus())) {
			throw new BaseException(SrpmsErrorType.APPLY_HAVE_SUBMITTED);
		}

		// 更新预算书信息
		this.saveOrUpdateBudgetApply(vo);
		log.info("submitBudgetApply 提交预算申请书, 已更新预算书信息.projectId:{}", vo.getId());
		JSONObject budgetJson = JSONObject.parseObject(JSONObject.toJSONString(vo));
		JSONObject applyJson = null;
		if (ProjectCategoryEnums.INNOVATE_PRE.getCode().equals(projectEntity.getProjectCategory())) {

			applyJson = innPreService.queryApplyVoById(projectId);

		} else if (ProjectCategoryEnums.INNOVATE_BCOO.getCode().equals(projectEntity.getProjectCategory())) {

			applyJson = innBcooService.get(projectId);

		} else if (ProjectCategoryEnums.INNOVATE_COO.getCode().equals(projectEntity.getProjectCategory())) {

			applyJson = innCooService.get(projectId);
		}

		ProjectCheckUtils.checkApply(projectEntity, applyJson, budgetJson);
		log.info("submitBudgetApply 提交预算申请书, 已校验参数.projectId:{}", vo.getId());

		// 更新项目状态
		projectService.submitProject(vo.getId());

		log.info("submitBudgetApply 提交预算申请书, 已更新项目状态.projectId:{}", vo.getId());

		commonService.copyApplyToTask(applyJson, budgetJson);

		log.info("submitBudgetApply 提交预算申请书, 已将申请书复制到任务书.projectId:{}", vo.getId());

		// 生成申请书PDF
		this.generateApplyBookPdf(projectEntity, userVo, deptVo);

		log.info("submitBudgetApply 提交预算申请书, 已生成申请书PDF.projectId:{}", vo.getId());

		// 生成预算书PDF
		this.generateBudgetApplyPdf(projectId, userVo, deptVo);

		log.info("先导专项，提交预算申请书，已生成预算书PDF, projectId:{}", vo.getId());
		// 发起流程
		flowServicel.startAuditProcess(vo.getId(), VoucherTypeEnums.APPLY_BOOK, userVo, deptVo);

		log.info("先导专项，提交预算申请书，已发起流程, projectId{}", vo.getId());

	}

	private void generateApplyBookPdf(SrpmsProject projectEntity, UserVo userVo, DeptVo deptVo) {
		if (ProjectCategoryEnums.INNOVATE_PRE.getCode().equals(projectEntity.getProjectCategory())) {
			// 先导
			innPreService.generateApplyBookPdf(projectEntity.getId(), userVo, deptVo);

		} else if (ProjectCategoryEnums.INNOVATE_BCOO.getCode().equals(projectEntity.getProjectCategory())) {
			innBcooService.generateApplyBookPdf(projectEntity.getId(), userVo, deptVo);

		} else if (ProjectCategoryEnums.INNOVATE_COO.getCode().equals(projectEntity.getProjectCategory())) {
			innCooService.generateApplyBookPdf(projectEntity.getId(), userVo, deptVo);
		}
	}

	public void generateTaskBookPdf(SrpmsProject projectEntity, UserVo userVo, DeptVo deptVo) {
		if (ProjectCategoryEnums.INNOVATE_PRE.getCode().equals(projectEntity.getProjectCategory())) {
			// 先导
			taskInnService.generateTaskInnPreBookPdf(projectEntity.getId(), userVo, deptVo);
		} else if (ProjectCategoryEnums.INNOVATE_BCOO.getCode().equals(projectEntity.getProjectCategory())) {
			taskInnService.generateTaskInnBcooBookPdf(projectEntity.getId(), userVo, deptVo);
		} else if (ProjectCategoryEnums.INNOVATE_COO.getCode().equals(projectEntity.getProjectCategory())) {
			taskInnService.generateTaskInnCooBookPdf(projectEntity.getId(), userVo, deptVo);
		}
	}

	@Override
	public BudgetApplyVo importWord(String wordFileUrl) {
		BudgetApplyVo budgetApplyVo = new BudgetApplyVo();
		try {
			String classPath = new File(ResourceUtils.getURL("").getPath()).getAbsoluteFile().toString();
			String htmlFilePath = WordConvertUtil.wordConvertToHtml(wordFileUrl, classPath);
			Document document = Jsoup.parse(new File(htmlFilePath), "utf-8");
			Elements tableElements = document.getElementsByTag("table");
			// 预算执行周期
			Element baseInfoTable = tableElements.get(0);
			String startEndDate = extractCellFromTable(baseInfoTable, 2, 3);
			if (StringUtils.isNotBlank(startEndDate)) {
				String[] prodates = startEndDate.split("-");
				if (prodates.length == 2) {
					LocalDateTime dataBudgetActionDateStart = DateUtil.chinaToLocalDateTime(prodates[0],
							LocalDateUtils.PARRERN_YMD);
					budgetApplyVo.setBudgetActionDateStart(LocalDateTimeUtils.formatTime(dataBudgetActionDateStart,
							LocalDateUtils.PARRERN_Y_M_D_H_M_S));

					LocalDateTime dataBudgetActionDateEnd = DateUtil.chinaToLocalDateTime(prodates[1],
							LocalDateUtils.PARRERN_YMD);
					budgetApplyVo.setBudgetActionDateEnd(
							LocalDateTimeUtils.formatTime(dataBudgetActionDateEnd, LocalDateUtils.PARRERN_Y_M_D_H_M_S));
				}
			}
			// 单位账户
			JSONObject leadDept = new JSONObject();
			leadDept.put("bankAccountNameLoose", extractCellFromTable(baseInfoTable, 9, 1));
			leadDept.put("bankNameLoose", extractCellFromTable(baseInfoTable, 10, 1));
			leadDept.put("bankAccountNumberLoose", extractCellFromTable(baseInfoTable, 11, 1));
			leadDept.put("bankAccountNameBase", extractCellFromTable(baseInfoTable, 9, 2));
			leadDept.put("bankNameBase", extractCellFromTable(baseInfoTable, 10, 2));
			leadDept.put("bankAccountNumberBase", extractCellFromTable(baseInfoTable, 11, 2));
			budgetApplyVo.setLeadDept(leadDept);

			// 项目联系人
			SrpmsProjectPersonVo contactPerson = new SrpmsProjectPersonVo();
			contactPerson.setPersonName(extractCellFromTable(baseInfoTable, 22, 2));
			contactPerson.setTelPhone(extractCellFromTable(baseInfoTable, 23, 1));
			contactPerson.setMobile(extractCellFromTable(baseInfoTable, 23, 3));
			contactPerson.setFaxNumber(extractCellFromTable(baseInfoTable, 24, 1));
			contactPerson.setEmail(extractCellFromTable(baseInfoTable, 25, 1));
			budgetApplyVo.setContactPerson(contactPerson);
			// 财务部门负责人
			SrpmsProjectPersonVo financePerson = new SrpmsProjectPersonVo();
			financePerson.setPersonName(extractCellFromTable(baseInfoTable, 26, 2));
			financePerson.setIdCard(extractCellFromTable(baseInfoTable, 27, 1));
			financePerson.setTelPhone(extractCellFromTable(baseInfoTable, 28, 1));
			financePerson.setMobile(extractCellFromTable(baseInfoTable, 28, 3));
			financePerson.setEmail(extractCellFromTable(baseInfoTable, 29, 1));
			budgetApplyVo.setFinancePerson(financePerson);

			// 项目参与人员
			List<SrpmsProjectPersonJoinVo> mainMemberList = new ArrayList<>();
			Element mainMenber = tableElements.get(1);
			Elements mainMenberRowList = mainMenber.getElementsByTag("tr");
			List<List<String>> mostMemberArray = extractListFromTable(mainMenber, 3, 3);
			for (List<String> personStr : mostMemberArray) {
				if (StringUtils.isBlank(personStr.get(0)) || StringUtils.isBlank(personStr.get(1))) {
					break;
				}
				SrpmsProjectPersonJoinVo personJoinVo = new SrpmsProjectPersonJoinVo();
				personJoinVo.setPersonName(StringConvertUtil.convertNull(personStr.get(1)));
				personJoinVo.setIdCard(StringConvertUtil.convertNull(personStr.get(2)));
				personJoinVo.setDeptName(StringConvertUtil.convertNull(personStr.get(3)));
				personJoinVo.setPositionTitle(StringConvertUtil.convertNull(personStr.get(4)));
				personJoinVo.setWorkPerYear(NumberUtils.toInt(StringConvertUtil.convertNull(personStr.get(5))));
				String hasSalary = StringUtils.trim(StringConvertUtil.convertNull(personStr.get(6)));
				if (StringUtils.isNotBlank(hasSalary) && StringUtils.equals(hasSalary, "是")) {
					personJoinVo.setHasSalaryInput(NumberUtils.INTEGER_ONE.toString());
				}
				if (StringUtils.isNotBlank(hasSalary) && StringUtils.equals(hasSalary, "否")) {
					personJoinVo.setHasSalaryInput("2");
				}
				personJoinVo.setPersonRole(StringConvertUtil.convertNull(personStr.get(7)));
				mainMemberList.add(personJoinVo);
			}
			budgetApplyVo.setTempMemberCount(extractCellFromTable(mainMenber, mainMenberRowList.size() - 2, 1));
			budgetApplyVo.setMainMemberList(mainMemberList);
			// 设备费——购置/试制设备预算明细表
			Elements deviceBuyText = document.getElementsMatchingOwnText("设备费——购置");
			if (!CollectionUtils.isEmpty(deviceBuyText)) {
				Element element = deviceBuyText.get(0);
				if (null != element) {
					// 设备费和测试化验加工费预算明细表
					Elements deviceTables = element.parent().parent().getElementsByTag("table");
					if (!CollectionUtils.isEmpty(deviceTables) && deviceTables.size() == 2) {
						// 购置设备费
						Element deviceTable1 = deviceTables.get(0);
						budgetApplyVo.setDeviceBudgetList(buildBudgetDevice(deviceTable1));
						budgetApplyVo.setTestBudgetList(buildTestBudget(deviceTables.get(1)));
						fillDeviceCount(deviceTable1, budgetApplyVo);
						fillTestCount(deviceTables.get(1), budgetApplyVo);
					}
				}
			}
			// 项目支出绩效目标申报表
			Elements performanceIndicator = document.getElementsMatchingOwnText("项目支出绩效目标申报表");
			if (!CollectionUtils.isEmpty(performanceIndicator)) {
				Element element = performanceIndicator.get(0);
				Element performTable = element.parent().nextElementSibling();
				if (null != performTable) {
					budgetApplyVo.setFundSourceAmount(extractCellFromTable(performTable, 3, 1));
					budgetApplyVo.setFundSourceProject(extractCellFromTable(performTable, 4, 1));
					budgetApplyVo.setFundSourceOther(extractCellFromTable(performTable, 4, 3));
					budgetApplyVo.setProjectTarget(extractCellFromTable(performTable, 5, 1));
					// update by lijun 指标库从财务获取，导入不做处理
					// budgetApplyVo.setPerformanceIndicatorDetail(buildPerformTarget(performTable));
				}
			}
			// 创新工程项目预算说明书
			// 单位支撑条件说明
			Elements specifSupport = document.getElementsMatchingOwnText("提出社会共享的方案");
			if (!CollectionUtils.isEmpty(specifSupport)) {
				Element element = specifSupport.get(0);
				Element specifSupportTable = element.nextElementSibling();
				if (null != specifSupportTable && StringUtils.equals(specifSupportTable.tagName(), "table")) {
					budgetApplyVo.setSpecifSupport(extractCellFromTable(specifSupportTable, 0, 0, true));
				}
			}
			// 经费安排说明
			Elements specifMoneyPlan = document.getElementsMatchingOwnText("承担单位经费安排进行详细说明");
			if (!CollectionUtils.isEmpty(specifMoneyPlan)) {
				Element element = specifMoneyPlan.get(0);
				Element specifSupportTable = element.nextElementSibling();
				if (null != specifSupportTable && StringUtils.equals(specifSupportTable.tagName(), "table")) {
					budgetApplyVo.setSpecifMoneyPlan(extractCellFromTable(specifSupportTable, 0, 0, true));
				}
			}

			// 设备费
			Elements specifFacility = document.getElementsMatchingOwnText("请说明购置或试制单台");
			if (!CollectionUtils.isEmpty(specifFacility)) {
				Element element = specifFacility.get(0);
				Element specifFacilityTable = element.nextElementSibling();
				if (null != specifFacilityTable && StringUtils.equals(specifFacilityTable.tagName(), "table")) {
					budgetApplyVo.setSpecifFacility(extractCellFromTable(specifFacilityTable, 0, 0, true));
				}
			}
			// 材料费
			Elements specifMaterial = document.getElementsMatchingOwnText("各种材料与项目研究任务");
			if (!CollectionUtils.isEmpty(specifMaterial)) {
				Element element = specifMaterial.get(0);
				Element specifMaterialTable = element.nextElementSibling();
				if (null != specifMaterialTable && StringUtils.equals(specifMaterialTable.tagName(), "table")) {
					budgetApplyVo.setSpecifMaterial(extractCellFromTable(specifMaterialTable, 0, 0, true));
				}
			}

			// 测试化验收加工费
			Elements specifTest = document.getElementsMatchingOwnText("各种测试化验与加工项目与项目研究任务");
			if (!CollectionUtils.isEmpty(specifTest)) {
				Element element = specifTest.get(0);
				Element specifTestTable = element.nextElementSibling();
				if (null != specifTestTable && StringUtils.equals(specifTestTable.tagName(), "table")) {
					budgetApplyVo.setSpecifTest(extractCellFromTable(specifTestTable, 0, 0, true));
				}
			}

			// 燃料动力费
			Elements specifFuel = document.getElementsMatchingOwnText("各种燃料与项目研究任务的相关性");
			if (!CollectionUtils.isEmpty(specifFuel)) {
				Element element = specifFuel.get(0);
				Element specifFuelTable = element.nextElementSibling();
				if (null != specifFuelTable && StringUtils.equals(specifFuelTable.tagName(), "table")) {
					budgetApplyVo.setSpecifFuel(extractCellFromTable(specifFuelTable, 0, 0, true));
				}
			}

			// 差旅费
			Elements specifTravel = document.getElementsMatchingOwnText("提供预算测算依据");
			if (!CollectionUtils.isEmpty(specifTravel)) {
				Element element = specifTravel.get(0);
				Element specifTravelTable = element.nextElementSibling();
				if (null != specifTravelTable && StringUtils.equals(specifTravelTable.tagName(), "table")) {
					budgetApplyVo.setSpecifTravel(extractCellFromTable(specifTravelTable, 0, 0, true));
				}
			}

			// 出版费
			Elements specifPublish = document.getElementsMatchingOwnText("各项预算与项目研究任务的关系");
			if (!CollectionUtils.isEmpty(specifPublish)) {
				Element element = specifPublish.get(0);
				Element specifPublishTable = element.nextElementSibling();
				if (null != specifPublishTable && StringUtils.equals(specifPublishTable.tagName(), "table")) {
					budgetApplyVo.setSpecifPublish(extractCellFromTable(specifPublishTable, 0, 0, true));
				}
			}
			// 劳务费
			Elements specifLabour = document.getElementsMatchingOwnText("各种聘用人员在项目研究中承担的任务");
			if (!CollectionUtils.isEmpty(specifLabour)) {
				Element element = specifLabour.get(0);
				Element specifLabourTable = element.nextElementSibling();
				if (null != specifLabourTable && StringUtils.equals(specifLabourTable.tagName(), "table")) {
					budgetApplyVo.setSpecifLabour(extractCellFromTable(specifLabourTable, 0, 0, true));
				}
			}
			// 专家咨询费
			Elements specifConsult = document.getElementsMatchingOwnText("预算的咨询专家与项目研究任务的关系和必要性");
			if (!CollectionUtils.isEmpty(specifConsult)) {
				Element element = specifConsult.get(0);
				Element specifConsultTable = element.nextElementSibling();
				if (null != specifConsultTable && StringUtils.equals(specifConsultTable.tagName(), "table")) {
					budgetApplyVo.setSpecifConsult(extractCellFromTable(specifConsultTable, 0, 0, true));
				}
			}
			// 其他支出
			Elements specifOther = document.getElementsMatchingOwnText("其他支出的各项支出与项目研究任务");
			if (!CollectionUtils.isEmpty(specifOther)) {
				Element element = specifOther.get(0);
				Element specifOtherTable = element.nextElementSibling();
				if (null != specifOtherTable && StringUtils.equals(specifOtherTable.tagName(), "table")) {
					budgetApplyVo.setSpecifOther(extractCellFromTable(specifOtherTable, 0, 0, true));
				}
			}
			return budgetApplyVo;
		} catch (Exception e) {
			log.error("解析word文件发生异常.", e);
			throw new BaseException(PlatformErrorType.IMPORT_TEMPLATE_ERROR);
		}
	}

	/**
	 * 项目支出绩效目标申报表
	 * 
	 * @param performTable
	 * @return
	 */
	private JSONArray buildPerformTarget(Element performTable) {
		JSONArray performanceIndicatorDetail = new JSONArray();
		List<List<String>> performList = extractListFromTable(performTable, 7, 0);
		if (CollectionUtils.isEmpty(performList)) {
			return null;
		}
		for (List<String> data : performList) {
			String first = data.get(0);
			String sec = data.get(1);
			String three = data.get(2);
			String quotaValue = data.get(3);
			if (StringUtils.isBlank(first) || StringUtils.isBlank(sec) || StringUtils.isBlank(three)
					|| StringUtils.isBlank(quotaValue)) {
				break;
			}
			List<String> quotaTypes = new ArrayList<>();
			quotaTypes.add(sysDictService.selectCodeByLikeValue(SysDictEnums.BE_CURRENT, first));
			quotaTypes.add(sysDictService.selectCodeByLikeValue(SysDictEnums.BE_CURRENT, sec));
			quotaTypes.add(sysDictService.selectCodeByLikeValue(SysDictEnums.BE_CURRENT, three));
			JSONObject quota = new JSONObject();
			quota.put("quotaType", quotaTypes);
			quota.put("quotaValue", quotaValue);
			performanceIndicatorDetail.add(quota);
		}
		return performanceIndicatorDetail;
	}

	/**
	 * 测试化验加工费预算明细表
	 * 
	 * @param testTable
	 * @return
	 */
	private List<SrpmsProjectBudgetDeviceVo> buildTestBudget(Element testTable) {
		List<SrpmsProjectBudgetDeviceVo> testBudgetList = new ArrayList<>();
		List<List<String>> testList = extractListFromTable(testTable, 3, 3);
		if (CollectionUtils.isEmpty(testList)) {
			return null;
		}
		for (List<String> deviceData : testList) {
			// 序号和设备名称不能为空
			if (StringUtils.isBlank(deviceData.get(0)) || StringUtils.isBlank(deviceData.get(1))) {
				break;
			}
			SrpmsProjectBudgetDeviceVo budgetDeviceVo = new SrpmsProjectBudgetDeviceVo();
			budgetDeviceVo.setContent(deviceData.get(1));
			budgetDeviceVo.setDeptName(deviceData.get(2));
			budgetDeviceVo.setMeasurementUnit(deviceData.get(3));
			budgetDeviceVo.setUnitPrice(deviceData.get(4));
			budgetDeviceVo.setDeviceCount(deviceData.get(5));
			budgetDeviceVo.setAmount(deviceData.get(6));
			testBudgetList.add(budgetDeviceVo);
		}
		return testBudgetList;
	}

	/**
	 * 获取模板中设备费相关信息
	 * 
	 * @param deviceTable1
	 * @return
	 */
	private List<SrpmsProjectBudgetDeviceVo> buildBudgetDevice(Element deviceTable1) {
		List<SrpmsProjectBudgetDeviceVo> deviceBudgetList = new ArrayList<>();
		List<List<String>> deviceList = extractListFromTable(deviceTable1, 3, 5);
		if (CollectionUtils.isEmpty(deviceList)) {
			return null;
		}
		for (List<String> deviceData : deviceList) {
			// 序号和设备名称不能为空
			if (StringUtils.isBlank(deviceData.get(0)) || StringUtils.isBlank(deviceData.get(1))) {
				break;
			}
			SrpmsProjectBudgetDeviceVo budgetDeviceVo = new SrpmsProjectBudgetDeviceVo();
			budgetDeviceVo.setDeviceName(deviceData.get(1));
			budgetDeviceVo.setDeviceCat(StringUtils.contains(deviceData.get(2), "试剂") ? "2" : "1");
			budgetDeviceVo.setUnitPrice(deviceData.get(3));
			budgetDeviceVo.setDeviceCount(deviceData.get(4));
			budgetDeviceVo.setAmount(deviceData.get(5));
			budgetDeviceVo
					.setDeviceType(sysDictService.selectCodeByLikeValue(SysDictEnums.DEVICE_TYPE, deviceData.get(6)));
			budgetDeviceVo.setDeviceNo(deviceData.get(7));
			budgetDeviceVo.setProducer(deviceData.get(8));
			budgetDeviceVo.setTechQuota(deviceData.get(9));
			budgetDeviceVo.setUseage(deviceData.get(10));
			deviceBudgetList.add(budgetDeviceVo);
		}
		return deviceBudgetList;
	}

	public void fillDeviceCount(Element table, BudgetApplyVo budgetApplyVo) {
		if (table == null) {
			throw new IllegalArgumentException("element cannot be null.");
		}
		if (!table.tagName().equals("table")) {
			throw new IllegalArgumentException("element is not a table.");
		}
		// 获取所有的行
		Elements rowList = table.getElementsByTag("tr");
		if (rowList.size() < 8) {
			return;
		}
		int size = rowList.size();
		// 单价10万元以上试制设备金额及数量
		Element rowLessTest = rowList.get(size - 2);
		Elements cellsLessTest = rowLessTest.getElementsByTag("td");
		budgetApplyVo.setCountReagentLess(NumberUtils.toDouble(cellsLessTest.get(3).text()));
		budgetApplyVo.setPriceReagentLess(NumberUtils.toDouble(cellsLessTest.get(4).text()));

		//// 单价10万元以下购置设备金额及数量
		Element rowLessDevice = rowList.get(size - 3);
		Elements cellsLessDevice = rowLessDevice.getElementsByTag("td");
		budgetApplyVo.setCountDeviceLess(NumberUtils.toDouble(cellsLessDevice.get(3).text()));
		budgetApplyVo.setPriceDeviceLess(NumberUtils.toDouble(cellsLessDevice.get(4).text()));
	}

	/**
	 * 填充测试化验加工费
	 * 
	 * @param table
	 * @param budgetApplyVo
	 */
	public void fillTestCount(Element table, BudgetApplyVo budgetApplyVo) {
		if (table == null) {
			throw new IllegalArgumentException("element cannot be null.");
		}
		if (!table.tagName().equals("table")) {
			throw new IllegalArgumentException("element is not a table.");
		}
		// 获取所有的行
		Elements rowList = table.getElementsByTag("tr");
		if (rowList.size() < 6) {
			return;
		}
		int size = rowList.size();
		Element rowOtherTest = rowList.get(size - 2);
		Elements cellsLessTest = rowOtherTest.getElementsByTag("td");
		budgetApplyVo.setOtherTestAmount(NumberUtils.toDouble(cellsLessTest.get(5).text()));
	}

	@Override
	public void submitTask(BudgetApplyVo vo, UserVo userVo, DeptVo deptVo) {
		long projectId = vo.getId();
		// 查询项目
		SrpmsProject projectEntity = projectService.getById(projectId);
		if (projectEntity == null) {
			throw new BaseException(PlatformErrorType.NO_DATA_FOUND);
		}
		if (!SrpmsProjectStatusEnums.PEROJECT_HAS_PUBLICITY.getCode().equals(projectEntity.getStatus())) {
			throw new BaseException(SrpmsErrorType.PUBLICED_CAN_SUBMITTED);
		}

		// 更新预算书信息
		this.saveOrUpdateBudgetApply(vo);

		JSONObject taskJson = taskInnService.queryById(projectId);
		JSONObject budgetJson = budgetApplyService.queryById(projectId, true);
		if (budgetJson == null) {
			throw new BaseException(SrpmsErrorType.BUDGET_NOT_COMPLETE);
		}
		ProjectCheckUtils.checkTask(projectEntity, taskJson, budgetJson);

		// 更新项目状态
		projectService.submitTaskProject(vo.getId());

		// 生成任务书PDF
		this.generateTaskBookPdf(projectEntity, userVo, deptVo);

		// 生成预算任务书PDF
		this.generateBudgetTaskPdf(projectEntity.getId(), userVo, deptVo);

		log.info("任务预算书，提交，已更新项目状态, projectId:{}", vo.getId());
		// 发起流程
		flowServicel.startAuditProcess(vo.getId(), VoucherTypeEnums.TASK_BOOK, userVo, deptVo);

		log.info("任务预算书，提交，已发起流程, projectId:{}", vo.getId());
	}

}