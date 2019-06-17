package com.deloitte.services.srpmp.project.budget.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.entity.ContentType;
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
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fileservice.feign.FileOperatorFeignService;
import com.deloitte.platform.api.fileservice.vo.FileInfoVo;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectDeptJoinVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonJoinVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectTaskVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.TaskNodeActionVO;
import com.deloitte.platform.api.srpmp.project.budget.param.SrpmsProjectBudgetYearQueryForm;
import com.deloitte.platform.api.srpmp.project.budget.vo.ext.BudgetApplyOutVo;
import com.deloitte.platform.api.srpmp.project.budget.vo.ext.BudgetApplyVo;
import com.deloitte.platform.api.srpmp.project.budget.vo.ext.BudgetSimpleOutVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.common.enums.BudgetCategoryEnums;
import com.deloitte.services.srpmp.common.enums.DeptJoinWayEnums;
import com.deloitte.services.srpmp.common.enums.PersonJoinWayEnums;
import com.deloitte.services.srpmp.common.enums.SrpmsProjectUpdateStatusEnums;
import com.deloitte.services.srpmp.common.enums.TaskCategoryEnums;
import com.deloitte.services.srpmp.common.enums.VoucherStatusEnums;
import com.deloitte.services.srpmp.common.enums.VoucherTypeEnums;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.common.service.ISerialNoCenterService;
import com.deloitte.services.srpmp.common.service.ISysDictService;
import com.deloitte.services.srpmp.common.util.CheckUtils;
import com.deloitte.services.srpmp.common.util.JSONConvert;
import com.deloitte.services.srpmp.common.util.WordConvertUtil;
import com.deloitte.services.srpmp.project.apply.util.LocalDateUtils;
import com.deloitte.services.srpmp.project.apply.util.ProjectCheckUtils;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectPerson;
import com.deloitte.services.srpmp.project.base.entity.SrpmsVoucher;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectDeptJoinService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectFlowService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectPersonJoinService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectPersonService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectSyncMsgService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectTaskService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsVoucherService;
import com.deloitte.services.srpmp.project.base.service.impl.SrpmsProjectBpmServiceImpl;
import com.deloitte.services.srpmp.project.budget.entity.SrpmsProjectBudgetTask;
import com.deloitte.services.srpmp.project.budget.entity.SrpmsProjectBudgetYear;
import com.deloitte.services.srpmp.project.budget.mapper.SrpmsProjectBudgetYearMapper;
import com.deloitte.services.srpmp.project.budget.service.ISrpmsProjectBudgetApplyService;
import com.deloitte.services.srpmp.project.budget.service.ISrpmsProjectBudgetApplyYearService;
import com.deloitte.services.srpmp.project.budget.service.ISrpmsProjectBudgetDeviceService;
import com.deloitte.services.srpmp.project.budget.service.ISrpmsProjectBudgetTaskService;

import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author : pengchao
 * @Date : Create in 2019-04-18
 * @Description : 创新 工程年度预算申请服务实现类
 * @Modified :
 */
@Slf4j
@Service
@Transactional
public class SrpmsProjectBudgetApplyYearServiceImpl
		extends ServiceImpl<SrpmsProjectBudgetYearMapper, SrpmsProjectBudgetYear>
		implements ISrpmsProjectBudgetApplyYearService {

	@Autowired
	private SrpmsProjectBudgetYearMapper budgetYearMapper;

	@Autowired
	private ISrpmsProjectPersonService personService;

	@Autowired
	private ISrpmsProjectPersonJoinService personJoinService;

	@Autowired
	private ISrpmsProjectService projectService;

	@Autowired
	private ISrpmsProjectDeptJoinService deptJoinService;

	@Autowired
	private ISrpmsProjectBudgetDeviceService deviceService;

	@Autowired
	private ISrpmsProjectBudgetApplyService budgetApplyService;

	@Autowired
	private ISrpmsProjectBudgetTaskService budgetTaskService;

	@Autowired
	private ISerialNoCenterService serialNoService;

	@Autowired
	private ISrpmsProjectTaskService taskService;

	@Autowired
	private ISrpmsProjectFlowService flowServicel;

	@Autowired
	private ISysDictService sysDictService;

	@Autowired
	private FileOperatorFeignService fileOperatorFeignService;

	@Autowired
	private SrpmsProjectBpmServiceImpl bpmService;

	@Autowired
	private ISrpmsVoucherService voucherService;

	@Autowired
	private ISrpmsProjectSyncMsgService syncMsgService;

	@Override
	public BudgetApplyOutVo queryByProjectId(Long projectId, UserVo user, DeptVo dept) {

		BudgetApplyOutVo outVo = new BudgetApplyOutVo();

		// 获取项目信息
		SrpmsProject projectEntity = projectService.getById(projectId);

		// 如果该id不是项目id，则代表是预算申请是预算书的id，调用预算申请的接口
		if (projectEntity == null) {
			throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
		}

		List<BudgetSimpleOutVo> list = this.queryAllById(projectId);
		// 如果该项目 有正在编辑的预算书，则直接返回改正在编辑的预算书信息
		for (int i = 1; i < list.size(); i++) {

			BudgetSimpleOutVo item = list.get(i);
			if (!SrpmsConstant.APPROVE_PASS.equals(item.getStatus())) {
				return this.queryById(Long.parseLong(item.getId()));
			}
		}

		SrpmsProjectBudgetTask budgetTask = budgetTaskService.getById(projectId);

		// 项目id
		outVo.setProjectId(projectId + "");
		// 项目名称
		outVo.setProjectName(projectEntity.getProjectName());
		// 项目编号
		outVo.setProjectNum(projectEntity.getProjectNum());
		// 项目类型
		outVo.setProjectType(projectEntity.getProjectType());
		// 项目开始时间
		outVo.setProjectActionDateStart(projectEntity.getProjectActionDateStart());
		// 项目结束时间
		outVo.setProjectActionDateEnd(projectEntity.getProjectActionDateEnd());

		// 项目联系人和财务负责人
		long projectContactPersonId = budgetTask.getProjectContactPersonId();
		long projectFinancePersonId = budgetTask.getProjectFinancePersonId();
		SrpmsProjectPerson contactPerson = personService.getById(projectContactPersonId);
		contactPerson.setId(null);
		SrpmsProjectPerson financePerson = personService.getById(projectFinancePersonId);
		financePerson.setId(null);
		outVo.setContactPerson(JSONObject.parseObject(JSON.toJSONString(contactPerson), SrpmsProjectPersonVo.class));
		outVo.setFinancePerson(JSONObject.parseObject(JSON.toJSONString(financePerson), SrpmsProjectPersonVo.class));

		// 项目负责人
		outVo.setLeadPerson(JSONObject.parseObject(projectEntity.getLeadPerson()));
		// 共同首席专家
		outVo.setBothTopExpertPerson(JSONObject.parseObject(projectEntity.getBothTopExpertPerson()));
		// 项目承担单位
		outVo.setLeadDept(JSONObject.parseObject(projectEntity.getLeadDept()));

		// 合作单位
		List<SrpmsProjectDeptJoinVo> coopDeptList = deptJoinService
				.queryDeptJoinListByJoinWay(DeptJoinWayEnums.TASK_INNOVATE_COPPDEPT, projectId);
		if (coopDeptList != null && coopDeptList.size() != 0) {

			StringBuilder sb = new StringBuilder();
			if (coopDeptList != null) {
				for (int i = 0; i < coopDeptList.size(); i++) {
					if (i != 0) {
						sb.append(",");
					}
					sb.append(coopDeptList.get(i).getDeptName());
				}

				outVo.setCoopDeptName(sb.toString());
			}
		}

		// 项目参加人员
		// 取得最新的项目参与人员列表
		List<SrpmsProjectPersonJoinVo> allPersonList = personJoinService
				.queryPersonJoinListByJoinWay(PersonJoinWayEnums.TASK_MAIN_MEMBER, projectId);
		// 将任务书的是否有工资行收入复制到最新的项目参与人员列表中
		outVo.setMainMemberList(allPersonList);

		// 分任务预算表
		List<SrpmsProjectTaskVo> taskDecompositionList = taskService
				.queryTaskListByTaskCategory(TaskCategoryEnums.TASK_INNOVATE_TASK_DECOMPOSITION, projectId);
		for (int i = 0; i < taskDecompositionList.size(); i++) {
			SrpmsProjectTaskVo item = taskDecompositionList.get(i);
			item.setBudgetAmount(null);
			item.setBudgetRemark(null);
			item.setTaskRemark(null);
			for (int j = 0; j < allPersonList.size(); j++) {
				SrpmsProjectPersonJoinVo personItem = allPersonList.get(j);
				personItem.setHasSalaryInput(null);
				if ("是".equals(personItem.getPersonRole()) && item.getTaskName().equals(personItem.getBelongTask())) {
					item.setLeadPersonName(personItem.getPersonName());
					item.setLeadPersonId(personItem.getPersonId());
				}
			}
			JSONArray newBudgetDetail = new JSONArray();
			JSONArray budgetDetail = item.getBudgetDetail();
			log.info("budgetDetail: " + budgetDetail.toJSONString());
			for (int j = 0; j < budgetDetail.size(); j++) {
				JSONObject budgetSubject = budgetDetail.getJSONObject(j);
				budgetSubject.put("amount", "");
				budgetSubject.put("remark", "");
				newBudgetDetail.add(budgetSubject);
			}
			log.info("newBudgetDetail: " + newBudgetDetail.toJSONString());
			item.setBudgetDetail(newBudgetDetail);
		}
		outVo.setTaskDecompositionList(taskDecompositionList);

		// 单位预算表
		List<SrpmsProjectTaskVo> budgetDeptList = taskService
				.queryTaskListByTaskCategory(TaskCategoryEnums.TASK_INNOVATE_BUDGET_DEPT, projectId);
		outVo.setBudgetDeptList(budgetDeptList);

		for (int i = 0; i < budgetDeptList.size(); i++) {
			SrpmsProjectTaskVo item = budgetDeptList.get(i);
			item.setBudgetRemark(null);
			item.setTaskRemark(null);
			item.setBudgetAmount(null);
			JSONArray newBudgetDetail = new JSONArray();
			JSONArray budgetDetail = item.getBudgetDetail();
			for (int j = 0; j < budgetDetail.size(); j++) {
				JSONObject budgetSubject = budgetDetail.getJSONObject(j);
				budgetSubject.put("amount", "");
				budgetSubject.put("remark", "");
				newBudgetDetail.add(budgetSubject);
			}
			item.setBudgetDetail(newBudgetDetail);
		}

		// 总预算表
		List<SrpmsProjectTaskVo> budgetAllList = taskService
				.queryTaskListByTaskCategory(TaskCategoryEnums.TASK_INNOVATE_BUDGET_ALL, projectId);
		outVo.setBudgetAllList(budgetAllList);
		for (int i = 0; i < budgetAllList.size(); i++) {
			SrpmsProjectTaskVo item = budgetAllList.get(i);
			item.setBudgetRemark(null);
			item.setTaskRemark(null);
			item.setBudgetAmount(null);
			JSONArray newBudgetDetail = new JSONArray();
			JSONArray budgetDetail = item.getBudgetDetail();
			for (int j = 0; j < budgetDetail.size(); j++) {
				JSONObject budgetSubject = budgetDetail.getJSONObject(j);
				budgetSubject.put("amount", "");
				budgetSubject.put("remark", "");
				newBudgetDetail.add(budgetSubject);
			}
			item.setBudgetDetail(newBudgetDetail);
		}

		for (int i = 0; i < allPersonList.size(); i++) {
			SrpmsProjectPersonJoinVo person = allPersonList.get(i);

			if ("是".equals(person.getPersonRole())) {
				person.setPersonRole("任务负责人");
			} else {
				person.setPersonRole("其他研究人员");
			}
			if (person.getPersonId() == projectEntity.getLeadPersonId()) {
				person.setPersonRole("项目负责人");
				continue;
			}
		}

		return outVo;
	}

	@Override
	public BudgetApplyOutVo queryById(Long id) {

		SrpmsProjectBudgetYear budgetYear = this.getById(id);
		if (budgetYear == null) {
			throw new BaseException(PlatformErrorType.NO_DATA_FOUND);
		}

		// 获取项目id
		long projectId = budgetYear.getProjectId();

		// 获取项目信息
		SrpmsProject projectEntity = projectService.getById(projectId);
		if (projectEntity == null) {
			throw new BaseException(PlatformErrorType.NO_DATA_FOUND);
		}

		String performanceIndicatorDetail = budgetYear.getPerformanceIndicatorDetail();
		budgetYear.setPerformanceIndicatorDetail(null);
		BudgetApplyOutVo outVo = JSONObject.parseObject(JSON.toJSONString(budgetYear), BudgetApplyOutVo.class);

		outVo.setBudgetActionDateStart(
				LocalDateUtils.format(budgetYear.getBudgetActionDateStart(), LocalDateUtils.PARRERN_Y_M_D_H_M_S));
		outVo.setBudgetActionDateEnd(
				LocalDateUtils.format(budgetYear.getBudgetActionDateEnd(), LocalDateUtils.PARRERN_Y_M_D_H_M_S));

		outVo.setPerformanceIndicatorDetail(JSONArray.parseArray(performanceIndicatorDetail));
		outVo.setId(id);

		// 项目id
		outVo.setProjectId(projectId + "");
		// 项目名称
		outVo.setProjectName(projectEntity.getProjectName());
		// 项目编号
		outVo.setProjectNum(projectEntity.getProjectNum());
		// 项目类型
		outVo.setProjectType(projectEntity.getProjectType());
		// 项目开始时间
		outVo.setProjectActionDateStart(projectEntity.getProjectActionDateStart());
		// 项目结束时间
		outVo.setProjectActionDateEnd(projectEntity.getProjectActionDateEnd());

		outVo.setCreateTime(budgetYear.getCreateTime());

		// 项目联系人和财务负责人
		long projectContactPersonId = budgetYear.getProjectContactPersonId();
		long projectFinancePersonId = budgetYear.getProjectFinancePersonId();
		SrpmsProjectPerson contactPerson = personService.getById(projectContactPersonId);
		SrpmsProjectPerson financePerson = personService.getById(projectFinancePersonId);
		contactPerson.setId(null);
		financePerson.setId(null);
		outVo.setContactPerson(JSONObject.parseObject(JSON.toJSONString(contactPerson), SrpmsProjectPersonVo.class));
		outVo.setFinancePerson(JSONObject.parseObject(JSON.toJSONString(financePerson), SrpmsProjectPersonVo.class));

		// 项目负责人
		outVo.setLeadPerson(JSONObject.parseObject(projectEntity.getLeadPerson()));
		// 共同首席专家
		outVo.setBothTopExpertPerson(JSONObject.parseObject(projectEntity.getBothTopExpertPerson()));
		// 项目承担单位
		outVo.setLeadDept(JSONObject.parseObject(projectEntity.getLeadDept()));

		// 合作单位
		List<SrpmsProjectDeptJoinVo> coopDeptList = deptJoinService
				.queryDeptJoinListByJoinWay(DeptJoinWayEnums.TASK_INNOVATE_COPPDEPT, projectId);
		if (coopDeptList != null && coopDeptList.size() != 0) {

			StringBuilder sb = new StringBuilder();
			if (coopDeptList != null) {
				for (int i = 0; i < coopDeptList.size(); i++) {
					if (i != 0) {
						sb.append(",");
					}
					sb.append(coopDeptList.get(i).getDeptName());
				}

				outVo.setCoopDeptName(sb.toString());
			}
		}

		// 设备预算明细
		outVo.setDeviceBudgetList(
				deviceService.queryBudgetDeviceList(BudgetCategoryEnums.TASK_INNOVATE_BUDGET_DEVICE, id));

		// 测试预算明细
		outVo.setTestBudgetList(deviceService.queryBudgetDeviceList(BudgetCategoryEnums.TASK_INNOVATE_BUDGET_TEST, id));

		// 分任务预算表
		List<SrpmsProjectTaskVo> taskDecompositionList = taskService
				.queryTaskListByTaskCategory(TaskCategoryEnums.TASK_INNOVATE_TASK_DECOMPOSITION, id);
		outVo.setTaskDecompositionList(taskDecompositionList);

		// 单位预算表
		outVo.setBudgetDeptList(
				taskService.queryTaskListByTaskCategory(TaskCategoryEnums.TASK_INNOVATE_BUDGET_DEPT, id));

		// 总预算表
		outVo.setBudgetAllList(taskService.queryTaskListByTaskCategory(TaskCategoryEnums.TASK_INNOVATE_BUDGET_ALL, id));

		// 项目参加人员
		List<SrpmsProjectPersonJoinVo> allPersonList = personJoinService
				.queryPersonJoinListByJoinWay(PersonJoinWayEnums.TASK_MAIN_MEMBER, id);

		outVo.setMainMemberList(allPersonList);
		return outVo;
	}

	@Override
	public String saveOrUpdate(BudgetApplyVo vo, UserVo user, DeptVo dept) {

		log.info("输入参数vo：" + JSON.toJSONString(vo));
		long projectId = Long.valueOf(vo.getProjectId());
		String strJson = JSON.toJSONString(vo);
		JSONObject json = JSONObject.parseObject(strJson);

		// 获取项目信息
		SrpmsProject projectEntity = projectService.getById(projectId);
		if (projectEntity == null) {
			throw new BaseException(PlatformErrorType.NO_DATA_FOUND);
		}

		SrpmsProjectPerson contactPerson = new SrpmsProjectPerson();
		if (vo.getContactPerson() != null) {
			contactPerson = JSONObject.parseObject(JSON.toJSONString(vo.getContactPerson()), SrpmsProjectPerson.class);
			personService.saveOrUpdate(contactPerson);
		}

		SrpmsProjectPerson finance = new SrpmsProjectPerson();
		if (vo.getFinancePerson() != null) {
			finance = JSONObject.parseObject(JSON.toJSONString(vo.getFinancePerson()), SrpmsProjectPerson.class);
			personService.saveOrUpdate(finance);
		}

		JSONConvert.convertJson(json);
		SrpmsProjectBudgetYear entity = JSONObject.parseObject(json.toJSONString(), SrpmsProjectBudgetYear.class);
		entity.setProjectContactPersonId(contactPerson.getId());
		entity.setProjectFinancePersonId(finance.getId());

		if (vo.getId() == null || vo.getId() == 0L) {
			entity.setProjectType(projectEntity.getProjectType());
			entity.setProjectNum(projectEntity.getProjectNum());
			entity.setProjectName(projectEntity.getProjectName());
			String year = DateFormatUtils.format(new Date(), "yyyy");
			String header = year.substring(2) + projectEntity.getProjectType();
			JSONObject serNo = serialNoService.getNextAPLNo(header, dept);
			entity.setBudNum(serNo.getString("budNum"));
			entity.setStatus("0");
			entity.setDelFlg("0");
			entity.setCreateBy(user.getId() + "");
			entity.setCreatePersonName(user.getName());
			entity.setCreateDeptName(dept.getDeptName());
			entity.setDeptId(Long.parseLong(dept.getDeptId()));
		}
		// 更新预算书基本字段
		this.saveOrUpdate(entity);

		long id = entity.getId();

		// 设备预算明细
		deviceService.cleanAndSaveBudgetDevice(vo.getDeviceBudgetList(),
				BudgetCategoryEnums.TASK_INNOVATE_BUDGET_DEVICE, id);

		// 测试预算明细
		deviceService.cleanAndSaveBudgetDevice(vo.getTestBudgetList(), BudgetCategoryEnums.TASK_INNOVATE_BUDGET_TEST,
				id);

		// 分任务预算表
		taskService.cleanAndSaveTask(vo.getTaskDecompositionList(), TaskCategoryEnums.TASK_INNOVATE_TASK_DECOMPOSITION,
				id);

		// 分单位预算表
		taskService.cleanAndSaveTask(vo.getBudgetDeptList(), TaskCategoryEnums.TASK_INNOVATE_BUDGET_DEPT, id);

		// 总预算表
		taskService.cleanAndSaveTask(vo.getBudgetAllList(), TaskCategoryEnums.TASK_INNOVATE_BUDGET_ALL, id);

		// 项目参与人员
		personJoinService.cleanAndSavePersonJoin(vo.getMainMemberList(), PersonJoinWayEnums.TASK_MAIN_MEMBER, id);

		return id + "";
	}

	@Override
	public List<BudgetSimpleOutVo> queryAllById(Long projectId) {

		List<BudgetSimpleOutVo> relust = new ArrayList<BudgetSimpleOutVo>();
		SrpmsProjectBudgetTask budgetTask = budgetTaskService.getById(projectId);
		SrpmsProject projectEntity = projectService.getById(projectId);
		BudgetSimpleOutVo outVo = new BudgetSimpleOutVo();

		outVo.setId(String.valueOf(projectId));
		String budgetYearString = null;

		if (!StringUtils.isBlank(projectEntity.getProjectFlag())) {
			outVo.setBudgetActionDateStart(
					LocalDateUtils.format(projectEntity.getProjectActionDateStart(), LocalDateUtils.PARRERN_Y_M_D));
			outVo.setBudgetActionDateEnd(
					LocalDateUtils.format(projectEntity.getProjectActionDateEnd(), LocalDateUtils.PARRERN_Y_M_D));
			budgetYearString = LocalDateUtils.format(projectEntity.getProjectActionDateStart(),
					LocalDateUtils.PARRERN_YMD) + "-"
					+ LocalDateUtils.format(projectEntity.getProjectActionDateEnd(), LocalDateUtils.PARRERN_YMD);
		} else {
			outVo.setBudgetActionDateStart(
					LocalDateUtils.format(budgetTask.getBudgetActionDateStart(), LocalDateUtils.PARRERN_Y_M_D));
			outVo.setBudgetActionDateEnd(
					LocalDateUtils.format(budgetTask.getBudgetActionDateEnd(), LocalDateUtils.PARRERN_Y_M_D));
			budgetYearString = LocalDateUtils.format(budgetTask.getBudgetActionDateStart(), LocalDateUtils.PARRERN_YMD)
					+ "-" + LocalDateUtils.format(budgetTask.getBudgetActionDateEnd(), LocalDateUtils.PARRERN_YMD);
		}

		outVo.setBudgetYear(budgetYearString);
		outVo.setBudNum(projectEntity.getBudNum());
		relust.add(outVo);

		QueryWrapper<SrpmsProjectBudgetYear> queryWrapper = new QueryWrapper<SrpmsProjectBudgetYear>();
		queryWrapper.eq(SrpmsProjectBudgetYear.PROJECT_ID, projectId);
		queryWrapper.eq(SrpmsProjectBudgetYear.STATUS,
				SrpmsProjectUpdateStatusEnums.PEROJECT_HAS_APPROVE_PASS.getCode());
		queryWrapper.eq(SrpmsProjectBudgetYear.DEL_FLG, SrpmsConstant.RECORD_NOT_DELETED);
		List<SrpmsProjectBudgetYear> budgetYearList = budgetYearMapper.selectList(queryWrapper);
		for (int i = 0; i < budgetYearList.size(); i++) {
			SrpmsProjectBudgetYear budgetYear = budgetYearList.get(i);
			outVo = new BudgetSimpleOutVo();

			outVo.setId(String.valueOf(budgetYear.getId()));
			outVo.setBudgetActionDateStart(
					LocalDateUtils.format(budgetYear.getBudgetActionDateStart(), LocalDateUtils.PARRERN_Y_M_D));
			outVo.setBudgetActionDateEnd(
					LocalDateUtils.format(budgetYear.getBudgetActionDateEnd(), LocalDateUtils.PARRERN_Y_M_D));

			budgetYearString = LocalDateUtils.format(budgetYear.getBudgetActionDateStart(), LocalDateUtils.PARRERN_YMD)
					+ "-" + LocalDateUtils.format(budgetYear.getBudgetActionDateEnd(), LocalDateUtils.PARRERN_YMD);
			outVo.setBudgetYear(budgetYearString);
			outVo.setBudNum(budgetYear.getBudNum());
			outVo.setBudgetAmount(
					budgetYear.getFundSourceAmount() == null ? "" : budgetYear.getFundSourceAmount().toString());
			outVo.setStatus(budgetYear.getStatus());
			relust.add(outVo);
		}
		return relust;
	}

	@Override
	public List<BudgetSimpleOutVo> queryAllById2(Long projectId) {
		List<BudgetSimpleOutVo> result = Lists.newArrayList();
		SrpmsProjectBudgetTask budgetTask = budgetTaskService.getById(projectId);
		SrpmsProject projectEntity = projectService.getById(projectId);
		BudgetSimpleOutVo outVo = new BudgetSimpleOutVo();

		outVo.setId(String.valueOf(projectId));
		String budgetYearString = "";

		if (!StringUtils.isBlank(projectEntity.getProjectFlag())) {
			outVo.setBudgetActionDateStart(LocalDateUtils.format(projectEntity.getProjectActionDateStart(), LocalDateUtils.PARRERN_Y_M_D));
			outVo.setBudgetActionDateEnd(LocalDateUtils.format(projectEntity.getProjectActionDateEnd(), LocalDateUtils.PARRERN_Y_M_D));
			budgetYearString = LocalDateUtils.format(projectEntity.getProjectActionDateStart(), LocalDateUtils.PARRERN_Y);
		} else {
			if (null != budgetTask) {
				outVo.setBudgetActionDateStart(LocalDateUtils.format(budgetTask.getBudgetActionDateStart(), LocalDateUtils.PARRERN_Y_M_D));
				outVo.setBudgetActionDateEnd(LocalDateUtils.format(budgetTask.getBudgetActionDateEnd(), LocalDateUtils.PARRERN_Y_M_D));
				budgetYearString = LocalDateUtils.format(budgetTask.getBudgetActionDateStart(), LocalDateUtils.PARRERN_Y);

			}
		}

		List<SrpmsProjectTaskVo> taskVoList = taskService.queryTaskListByTaskCategory(TaskCategoryEnums.APPLY_INNOVATE_BUDGET_ALL, projectId);

		outVo.setBudgetYear(budgetYearString);
		outVo.setBudNum(projectEntity.getBudNum());
		if (CollectionUtils.isNotEmpty(taskVoList)) {
			outVo.setBudgetAmount(taskVoList.get(0).getBudgetAmount());
		}
		result.add(outVo);

		QueryWrapper<SrpmsProjectBudgetYear> queryWrapper = new QueryWrapper<SrpmsProjectBudgetYear>();
		queryWrapper.eq(SrpmsProjectBudgetYear.PROJECT_ID, projectId);
		queryWrapper.eq(SrpmsProjectBudgetYear.STATUS, SrpmsProjectUpdateStatusEnums.PEROJECT_HAS_APPROVE_PASS.getCode());
		queryWrapper.eq(SrpmsProjectBudgetYear.DEL_FLG, SrpmsConstant.RECORD_NOT_DELETED);
		List<SrpmsProjectBudgetYear> budgetYearList = budgetYearMapper.selectList(queryWrapper);
		for (SrpmsProjectBudgetYear budgetYear : budgetYearList) {
			List<SrpmsProjectTaskVo> yearTaskVoList = taskService.queryTaskListByTaskCategory(TaskCategoryEnums.APPLY_INNOVATE_BUDGET_ALL, budgetYear.getId());
			outVo = new BudgetSimpleOutVo();
			outVo.setId(String.valueOf(budgetYear.getId()));
			outVo.setBudgetActionDateStart(LocalDateUtils.format(budgetYear.getBudgetActionDateStart(), LocalDateUtils.PARRERN_Y_M_D));
			outVo.setBudgetActionDateEnd(LocalDateUtils.format(budgetYear.getBudgetActionDateEnd(), LocalDateUtils.PARRERN_Y_M_D));
			budgetYearString = LocalDateUtils.format(budgetYear.getBudgetActionDateStart(), LocalDateUtils.PARRERN_Y);
			outVo.setBudgetYear(budgetYearString);
			outVo.setBudNum(budgetYear.getBudNum());
			if (CollectionUtils.isNotEmpty(yearTaskVoList)) {
				outVo.setBudgetAmount(yearTaskVoList.get(0).getBudgetAmount());
			}
			outVo.setStatus(budgetYear.getStatus());
			result.add(outVo);
		}
		return result;
	}

	/**
	 * 查询变更记录service接口实现
	 *
	 * @param queryForm
	 * @return
	 */
	@Override
	public JSONObject list(SrpmsProjectBudgetYearQueryForm queryForm, UserVo user, DeptVo dept) {

		String roleCode = user.getHonor();
		String deptCode = dept.getDeptCode();
		String proType = user.getRemark();

		queryForm.setDeptId(null);
		int tableFlg = queryForm.getTableFlag();

		if (tableFlg == 0) {
			if (SrpmsConstant.SRPMS_ADMIN.equals(roleCode) || SrpmsConstant.SRPMS_LEADER.equals(roleCode)) {
				tableFlg = 2;
			} else {
				tableFlg = 1;
				queryForm.setStatus(SrpmsProjectUpdateStatusEnums.PEROJECT_NOT_SUBMIT.getCode());
			}
		}
		queryForm.setTableFlag(tableFlg);

		log.info("tableFlg的值是：" + tableFlg);
		if (tableFlg == 1) {

			queryForm.setCreateBy(user.getId());

		} else if (tableFlg == 2) {

			queryForm.setStatus(SrpmsProjectUpdateStatusEnums.PEROJECT_HAS_APPROVE_PASS.getCode());

			if (proType == null) {
				return getEmptyRelust();
			}

			if (StringUtils.isBlank(queryForm.getProjectType())) {
				queryForm.setProjectType(proType);
			} else {
				String[] proTypeArr = proType.split(",");
				boolean hasTypeFlg = false;
				for (int i = 0; i < proTypeArr.length; i++) {
					if (proTypeArr[i].equals(queryForm.getProjectType())) {
						hasTypeFlg = true;
					}
				}
				if (hasTypeFlg) {
					queryForm.setProjectType(queryForm.getProjectType());
				} else {
					return getEmptyRelust();
				}
			}

			if (!SrpmsConstant.SRPMS_FIRST_LEVEL_DEPT_CODE.equals(deptCode)) {
				queryForm.setDeptId(dept.getDeptId());
			}
		}

		QueryWrapper<SrpmsProjectBudgetYear> queryWrapper = new QueryWrapper<>();
		queryWrapper = this.getQueryWrapper(queryWrapper, queryForm);

		IPage<SrpmsProjectBudgetYear> page = budgetYearMapper
				.selectPage(new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()), queryWrapper);
		List<SrpmsProjectBudgetYear> updateList = page.getRecords();
		List<BudgetSimpleOutVo> updateVoList = new ArrayList<>();

		Map<String, String> dictMap = sysDictService
				.getSysDictMap(new String[] { SrpmsConstant.PRO_CATEGORY, SrpmsConstant.PRO_UPADATE_STATE });
		if (updateList != null && updateList.size() > 0) {
			SrpmsProjectBudgetYear budgetYear;
			BudgetSimpleOutVo budgetYearVo;
			for (Iterator e = updateList.iterator(); e.hasNext();) {

				budgetYear = (SrpmsProjectBudgetYear) e.next();

				SrpmsProject projectBase = projectService.getById(budgetYear.getProjectId());

				budgetYearVo = new BudgetSimpleOutVo();

				budgetYearVo.setId(budgetYear.getId() + "");

				budgetYearVo.setProjectTypeName(
						dictMap.get(SrpmsConstant.PRO_CATEGORY.concat(budgetYear.getProjectType())));
				budgetYearVo.setStatusName(dictMap.get(SrpmsConstant.PRO_UPADATE_STATE.concat(budgetYear.getStatus())));
				budgetYearVo.setStatus(budgetYear.getStatus());
				budgetYearVo.setBudNum(budgetYear.getBudNum());
				budgetYearVo.setProjectNum(budgetYear.getProjectNum());
				budgetYearVo.setProjectName(budgetYear.getProjectName());

				budgetYearVo.setProjectActionDateStart(
						LocalDateUtils.format(projectBase.getProjectActionDateStart(), LocalDateUtils.PARRERN_Y_M_D));
				budgetYearVo.setProjectActionDateEnd(
						LocalDateUtils.format(projectBase.getProjectActionDateEnd(), LocalDateUtils.PARRERN_Y_M_D));

				StringBuffer sbProjectActionDate = new StringBuffer();
				sbProjectActionDate.append(" 起 ");
				sbProjectActionDate.append(budgetYearVo.getProjectActionDateStart());
				sbProjectActionDate.append(" 止 ");
				sbProjectActionDate.append(budgetYearVo.getProjectActionDateEnd());
				budgetYearVo.setProjectActionDate(sbProjectActionDate.toString());

				budgetYearVo.setLeadPersonName(JSONObject.parseObject(projectBase.getLeadPerson()).getString("name"));
				budgetYearVo.setDeptName(JSONObject.parseObject(projectBase.getLeadDept()).getString("deptName"));

				budgetYearVo.setBudgetActionDateStart(
						LocalDateUtils.format(budgetYear.getBudgetActionDateStart(), LocalDateUtils.PARRERN_Y_M_D));
				budgetYearVo.setBudgetActionDateEnd(
						LocalDateUtils.format(budgetYear.getBudgetActionDateEnd(), LocalDateUtils.PARRERN_Y_M_D));

				StringBuffer sbBudgetActionDate = new StringBuffer();
				if (budgetYearVo.getBudgetActionDateStart() != null
						&& budgetYearVo.getBudgetActionDateStart().length() != 0) {
					sbBudgetActionDate.append(" 起 ");
					sbBudgetActionDate.append(budgetYearVo.getBudgetActionDateStart());
				}

				if (budgetYearVo.getBudgetActionDateEnd() != null
						&& budgetYearVo.getBudgetActionDateEnd().length() != 0) {
					sbBudgetActionDate.append(" 止 ");
					sbBudgetActionDate.append(budgetYearVo.getBudgetActionDateEnd());
				}

				budgetYearVo.setBudgetActionDate(sbBudgetActionDate.toString());

				updateVoList.add(budgetYearVo);
			}
		}
		JSONObject json = new JSONObject();
		json.put("current", page.getCurrent());
		json.put("pages", page.getPages());
		json.put("records", updateVoList);
		json.put("searchCount", page.isSearchCount());
		json.put("size", page.getSize());
		json.put("total", page.getTotal());
		json.put("tableFlag", tableFlg);
		return json;
	}

	/**
	 * 创新工程预算审批通过service接口实现
	 *
	 * @param actionVO
	 * @param deptVo
	 */
	@Override
	public void budgetApprovePassBpm(TaskNodeActionVO actionVO, DeptVo deptVo) {
		boolean hasEnd = bpmService.auditApprove(actionVO, deptVo);
		SrpmsVoucher voucherEntity = voucherService.getById(actionVO.getObjectId());
		// 审批后更新单据撤回标识
		voucherEntity.setRecallFlag(1);

		if (!hasEnd) { // 未结束，就返回
			voucherService.saveOrUpdate(voucherEntity);
			return;
		}
		SrpmsProjectBudgetYear budgetYear = this.getById(voucherEntity.getProjectId());// 查询预算信息
		if (budgetYear != null
				&& !SrpmsProjectUpdateStatusEnums.PEROJECT_HAS_SUBMIT.getCode().equals(budgetYear.getStatus())) {
			throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
		}

		budgetYear.setStatus(SrpmsProjectUpdateStatusEnums.PEROJECT_HAS_APPROVE_PASS.getCode());// 设置状态
		// 更新预算状态
		this.updateById(budgetYear);
		// 更新单据状态
		voucherEntity.setVoucherStatus(VoucherStatusEnums.APPROVED.getCode());
		voucherService.saveOrUpdate(voucherEntity);
		// 同步MQ
		syncMsgService.syncBudgetYear(budgetYear);
	}

	/**
	 * 创新工程预算审批拒绝service接口
	 *
	 * @param actionVO
	 */
	@Override
	public void budgetRefuseBpm(TaskNodeActionVO actionVO) {
		bpmService.auditRefuse(actionVO);
		SrpmsVoucher voucherEntity = voucherService.getById(actionVO.getObjectId());
		SrpmsProjectBudgetYear budgetYear = this.getById(voucherEntity.getProjectId());// 查询预算信息
		if (budgetYear != null
				&& !SrpmsProjectUpdateStatusEnums.PEROJECT_HAS_SUBMIT.getCode().equals(budgetYear.getStatus())) {
			throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
		}
		budgetYear.setStatus(SrpmsProjectUpdateStatusEnums.PEROJECT_APPROVE_NO.getCode());// 设置状态
		// 更新预算状态
		this.updateById(budgetYear);

		voucherEntity.setRecallFlag(1);
		// 更新单据状态
		voucherEntity.setVoucherStatus(VoucherStatusEnums.REFUSED.getCode());
		voucherService.saveOrUpdate(voucherEntity);
		// voucherService.updateStatus(CommonUtil.getLongValue(actionVO.getObjectId()),
		// VoucherStatusEnums.REFUSED);
	}

	/**
	 * 通用查询
	 *
	 * @param queryWrapper,queryForm
	 * @return
	 */
	private QueryWrapper<SrpmsProjectBudgetYear> getQueryWrapper(QueryWrapper<SrpmsProjectBudgetYear> queryWrapper,
			SrpmsProjectBudgetYearQueryForm queryForm) {

		// 条件拼接
		if (CheckUtils.notEmpty(queryForm.getProjectName())) {
			queryWrapper.eq(SrpmsProjectBudgetYear.PROJECT_NAME, queryForm.getProjectName());
		}

		if (CheckUtils.notEmpty(queryForm.getProjectNum())) {
			queryWrapper.eq(SrpmsProjectBudgetYear.PROJECT_NUM, queryForm.getProjectNum());
		}

		if (StringUtils.isNotBlank(queryForm.getProjectType())) {
			if (queryForm.getProjectType().contains(",")) {
				String[] arr = queryForm.getProjectType().split(",");
				queryWrapper.in(SrpmsProjectBudgetYear.PROJECT_TYPE, arr);
			} else {
				queryWrapper.eq(SrpmsProjectBudgetYear.PROJECT_TYPE, queryForm.getProjectType());
			}
		}

		queryWrapper.eq(SrpmsProjectBudgetYear.STATUS, queryForm.getStatus());

		if (StringUtils.isNotBlank(queryForm.getDeptId())) {
			queryWrapper.eq(SrpmsProjectBudgetYear.DEPT_ID, queryForm.getDeptId());
		}
		if (StringUtils.isNotBlank(queryForm.getCreateBy())) {
			queryWrapper.eq(SrpmsProjectBudgetYear.CREATE_BY, queryForm.getCreateBy());
		}
		queryWrapper.eq(SrpmsProjectBudgetYear.DEL_FLG, SrpmsConstant.RECORD_NOT_DELETED);
		queryWrapper.orderByDesc(SrpmsProjectBudgetYear.UPDATE_TIME);

		return queryWrapper;
	}

	@Override
	public String exportWordFile(Long projectId, String token)
			throws IOException, InterruptedException, TemplateException {
		BudgetApplyOutVo outVo = this.queryById(projectId);
		JSONObject voJson = JSONObject.parseObject(JSON.toJSONString(outVo));
		String fileName = "创新工程项目预算任务书_" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS")
				+ RandomUtils.nextInt(0, 9999) + ".docx";
		return budgetApplyService.exportFromVoJson(voJson, fileName);
	}

	@Override
	public String exportPdfFile(Long projectId, String token)
			throws IOException, InterruptedException, TemplateException {
		String docxFilePath = this.exportWordFile(projectId, token);
		String pdfFinalName = docxFilePath.replace("docx", "pdf");
		// 转格式
		WordConvertUtil.docx2PDF(docxFilePath, pdfFinalName);
		return pdfFinalName;
	}

	@Override
	public void submit(BudgetApplyVo vo, UserVo userVo, DeptVo deptVo) {

		ProjectCheckUtils.checkBudget(vo);
		long projectId = Long.valueOf(vo.getProjectId());
		log.info("submitBudgetApply 提交预算申请书, 开始.projectId:{}", projectId);

		// 查询项目
		SrpmsProject projectEntity = projectService.getById(projectId);
		if (projectEntity == null) {
			throw new BaseException(PlatformErrorType.NO_DATA_FOUND);
		}

		// 更新预算书信息
		long id = Long.valueOf(this.saveOrUpdate(vo, userVo, deptVo));
		log.info("submitBudgetApply 提交预算申请书, 已更新预算书信息.projectId:{}", vo.getId());
		SrpmsProjectBudgetYear budgetYear = this.getById(id);
		budgetYear.setStatus("10");
		this.saveOrUpdate(budgetYear);
		log.info("submitBudgetApply 提交预算申请书, 已校验参数.projectId:{}", vo.getId());

		log.info("生成pdf文件开始");
		this.generateBudgetPdf(id, userVo, deptVo);
		log.info("生成pdf文件结束");

		log.info("创新工程，提交预算申请书，已更新项目状态, projectId:{}", id);
		// 发起流程
		flowServicel.startBudgetAuditProcess(budgetYear, VoucherTypeEnums.BUDGET_APPLY_BOOK, userVo, deptVo);

		log.info("创新工程，提交预算申请书，已发起流程, projectId{}", id);

	}

	private void generateBudgetPdf(Long projectId, UserVo userVo, DeptVo deptVo) {
		File pdfFile = null;
		FileInputStream fileInputStream = null;
		SrpmsProjectBudgetYear projectBudgetYear = this.getById(projectId);

		try {
			// 生成预算申请书pdf文档
			BudgetApplyOutVo outVo = this.queryById(projectId);
			JSONObject voJson = JSONObject.parseObject(JSON.toJSONString(outVo));
			String fileName = "创新工程项目预算任务书_" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS")
					+ RandomUtils.nextInt(0, 9999) + ".docx";
			String docxFilePath = budgetApplyService.exportFromVoJson(voJson, fileName);
			String jarPath = new File(ResourceUtils.getURL("").getPath()).getAbsolutePath();
			String pdfFilePath = jarPath + "/" + projectBudgetYear.getProjectName() + "预算申请书.pdf";
			WordConvertUtil.docx2PDF(docxFilePath, pdfFilePath);
			pdfFile = new File(pdfFilePath);
			fileInputStream = new FileInputStream(pdfFile);

			MultipartFile multipartFile = new MockMultipartFile("file", pdfFile.getName(),
					ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);

			Result<FileInfoVo> result = fileOperatorFeignService.uploadFile(multipartFile);
			if (result.isSuccess()) {
				FileInfoVo fileInfoVo = result.getData();
				if (fileInfoVo != null) {

					projectBudgetYear.setBudgetBookFileId(NumberUtils.toLong(fileInfoVo.getId()) + "");
					projectBudgetYear.setBudgetBookFileName(fileInfoVo.getFileName());
					projectBudgetYear.setBudgetBookFileUrl(fileInfoVo.getFileUrl());
					this.updateById(projectBudgetYear);
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

	private JSONObject getEmptyRelust() {
		JSONObject json = new JSONObject();
		json.put("current", 1);
		json.put("pages", 1);
		json.put("records", new JSONArray());
		json.put("total", 0);
		json.put("tableFlag", 2);
		return json;
	}

	@Override
	public void delete(Long projectId) {
		SrpmsProjectBudgetYear projectBudgetYear = this.getById(projectId);
		projectBudgetYear.setDelFlg(SrpmsConstant.RECORD_DELETED);
		this.updateById(projectBudgetYear);
	}
}