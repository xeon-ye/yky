package com.deloitte.services.srpmp.project.apply.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.deloitte.platform.api.srpmp.project.apply.vo.SrpmsProjectApplyInnUnitForm;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskInnUnitForm;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.srpmp.project.task.entity.SrpmsProjectTaskInnUnit;
import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskInnUnitService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyInnBcooSaveVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyInnCooSaveVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyInnPreSubmitVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectDeptJoinVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonJoinVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectTaskVo;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetDetailVo;
import com.deloitte.platform.api.srpmp.project.budget.vo.ext.BudgetApplyVo;
import com.deloitte.platform.api.srpmp.project.task.vo.ext.SrpmsProjectTaskInnExtVo;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.common.enums.BudgetCategoryEnums;
import com.deloitte.services.srpmp.common.enums.DeptJoinWayEnums;
import com.deloitte.services.srpmp.common.enums.PersonJoinWayEnums;
import com.deloitte.services.srpmp.common.enums.ProjectCategoryEnums;
import com.deloitte.services.srpmp.common.enums.SrpmsProjectStatusEnums;
import com.deloitte.services.srpmp.common.enums.TaskCategoryEnums;
import com.deloitte.services.srpmp.common.service.ISerialNoCenterService;
import com.deloitte.services.srpmp.common.util.BudgetDetailUtil;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectAttachmentService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectBudgetDetailService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectDeptJoinService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectPersonJoinService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectTaskService;
import com.deloitte.services.srpmp.project.budget.service.ISrpmsProjectBudgetApplyService;
import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskInnService;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author : pengchao
 * @Date : Create in 2019-03-04
 * @Description : SrpmsProjectApplyInnBcoo服务实现类
 * @Modified :
 */
@Service
@Slf4j
@Transactional
public class SrpmsProjectApplyInnCommonServiceImpl {

	@Autowired
	private ISrpmsProjectDeptJoinService srpmsProjectDeptJoinServiceImpl;

	@Autowired
	private ISrpmsProjectPersonJoinService personJoinService;

	@Autowired
	private ISrpmsProjectService projectService;

	@Autowired
	private ISrpmsProjectAttachmentService attachmentService;

	@Autowired
	private ISrpmsProjectTaskService taskService;

	@Autowired
	private ISrpmsProjectBudgetDetailService budgetDetailService;

	@Autowired
	private ISerialNoCenterService serialNoService;

	@Autowired
	private ISrpmsProjectTaskInnService taskInnService;

	@Autowired
	private ISrpmsProjectBudgetApplyService budgetApplyService;

	@Autowired
	private ISrpmsProjectTaskInnUnitService taskInnUnitService;

	public Long saveOrUpdateCommonInfo(JSONObject inputVo, ProjectCategoryEnums type) {

		SrpmsProject projectBaseInfo = JSONObject.parseObject(inputVo.toJSONString(), SrpmsProject.class);
		projectService.saveOrUpdate(projectBaseInfo);
		saveOrUpdate(inputVo, type, projectBaseInfo.getId());
		return projectBaseInfo.getId();
	}

	public Long insertCommonInfo(JSONObject inputVo, ProjectCategoryEnums type, DeptVo deptVo) {

		String year = DateFormatUtils.format(new Date(), "yyyy");
		String header = year.substring(2) + type.getHeader();
		JSONObject serNo = serialNoService.getNextAPLNo(header, deptVo);
		inputVo.putAll(serNo);

		SrpmsProject projectBaseInfo = JSONObject.parseObject(inputVo.toJSONString(), SrpmsProject.class);
		projectBaseInfo.setId(null);
		projectBaseInfo.setApplyYear(year);
		projectBaseInfo.setStatus(SrpmsProjectStatusEnums.PEROJECT_NOT_SUBMIT.getCode());
		projectBaseInfo.setProjectType(type.getHeader());
		projectBaseInfo.setProjectCategory(type.getCode());
		projectBaseInfo.setDelFlg("0");
		projectBaseInfo.setBudFirstOpenFlg("true");
		projectBaseInfo.setTaskFirstOpenFlg("true");
		projectBaseInfo.setTaskBudFirstOpenFlg("true");
		if (StringUtils.isNotBlank(inputVo.getString("projectFlag"))) {// 横纵向项目设置项目标识
			projectBaseInfo.setProjectFlag(inputVo.getString("projectFlag"));
		}
		projectBaseInfo.setSubjectCategory(inputVo.getString("subjectCategory"));
		projectService.save(projectBaseInfo);

		long projectId = projectBaseInfo.getId();
		inputVo.put("id", projectId);
		saveOrUpdate(inputVo, type, projectId);
		return projectId;
	}

	private void saveOrUpdate(JSONObject inputVo, ProjectCategoryEnums type, long projectId) {

		JSONObject leadPerson = inputVo.getJSONObject("leadPerson");
		JSONObject leadDept = inputVo.getJSONObject("leadDept");
		JSONObject bothTopExpertPerson = inputVo.getJSONObject("bothTopExpertPerson");

		if (leadPerson == null) {

			throw new BaseException();
		}
		if (leadDept == null) {

			throw new BaseException();
		}
		SrpmsProject projectBaseInfo = projectService.getById(projectId);
		projectBaseInfo.setUpdateTime(LocalDateTime.now());
		projectBaseInfo.setLeadPersonId(leadPerson.getLong("id"));
		projectBaseInfo.setLeadDeptId(leadDept.getLong("deptId"));
		if (bothTopExpertPerson != null) {
			projectBaseInfo.setBothTopExpertPersonId(bothTopExpertPerson.getLong("id"));
			inputVo.put("bothTopExpertPersonId", bothTopExpertPerson.getLong("id"));
		}

		inputVo.put("leadPersonId", leadPerson.getLong("id"));
		inputVo.put("leadDeptId", leadPerson.getLong("deptId"));
		projectService.saveOrUpdate(projectBaseInfo);

		PersonJoinWayEnums joinWayEnums = PersonJoinWayEnums.APPLY_MAIN_MEMBER;
		if (SrpmsConstant.PROJECT_TYPE_1.equals(inputVo.getString("projectFlag"))) {// 横纵向项目设置参与人员类型
			joinWayEnums = PersonJoinWayEnums.TASK_MAIN_MEMBER;
		}
		if (StringUtils.equals(type.getHeader(), ProjectCategoryEnums.ACADEMY_UNIT.getHeader())) {
			joinWayEnums = PersonJoinWayEnums.APPLY_UNIT_PERSON;
		}
		// 主要参与人员
		JSONArray mainMemberJsonArr = inputVo.getJSONArray("mainMemberList");
		JSONArray removeArr = new JSONArray();
		if (mainMemberJsonArr != null) {

			// for (int i= 0; i < mainMemberJsonArr.size(); i ++) {
			// JSONObject mainMember = mainMemberJsonArr.getJSONObject(i);
			// if (mainMember.getString("personName") == null ||
			// "".equals(mainMember.getString("personName"))) {
			// removeArr.add(mainMember);
			// }
			// }
			// for (int i= 0; i < removeArr.size(); i ++) {
			// mainMemberJsonArr.remove(removeArr.getJSONObject(i));
			// }
			personJoinService.cleanAndSavePersonJoin(
					inputVo.getJSONArray("mainMemberList").toJavaList(SrpmsProjectPersonJoinVo.class), joinWayEnums,
					projectId);
		} else {
			personJoinService.cleanAndSavePersonJoin(null, joinWayEnums, projectId);
		}

		// 国家合作单位信息
		if (inputVo.getJSONArray("coopDeptList") != null) {
			srpmsProjectDeptJoinServiceImpl.cleanAndSaveDeptJoin(
					inputVo.getJSONArray("coopDeptList").toJavaList(SrpmsProjectDeptJoinVo.class),
					DeptJoinWayEnums.APPLY_INNOVATE_COPPDEPT, projectId);
		} else {
			srpmsProjectDeptJoinServiceImpl.cleanAndSaveDeptJoin(null, DeptJoinWayEnums.APPLY_INNOVATE_COPPDEPT,
					projectId);
		}

		// 任务的年度任务、考核指标和时间节点
		if (inputVo.getJSONArray("taskPreYearList") != null) {
			taskService.cleanAndSaveTask(inputVo.getJSONArray("taskPreYearList").toJavaList(SrpmsProjectTaskVo.class),
					TaskCategoryEnums.APPLY_INNOVATE_TASK_YEAR, projectId);
		} else {
			taskService.cleanAndSaveTask(null, TaskCategoryEnums.APPLY_INNOVATE_TASK_YEAR, projectId);
		}

		// 年度预算
		BudgetCategoryEnums budgetCategoryEnums;
		if (SrpmsConstant.PROJECT_TYPE_1.equals(inputVo.getString("projectFlag"))) {// 横纵向项目设置项目标识
			budgetCategoryEnums = BudgetCategoryEnums.TASK_TRAN_LONG_DETAIL;
		} else {
			budgetCategoryEnums = BudgetCategoryEnums.APPLY_INNOVATE_BUDGET_YEAR;
		}
		if (inputVo.getJSONArray("budgetPreYearList") != null) {
			budgetDetailService.cleanAndSaveBudgetDetail(
					inputVo.getJSONArray("budgetPreYearList").toJavaList(SrpmsProjectBudgetDetailVo.class),
					budgetCategoryEnums, projectId);
		} else {
			budgetDetailService.cleanAndSaveBudgetDetail(null, budgetCategoryEnums, projectId);
		}

		attachmentService.saveAttachmentListApply(inputVo, projectId);
	}

	public void setTaskAndBudgetList(JSONArray taskDecompositionListJsonArray, long projectId, boolean taskFlg) {

		if (taskDecompositionListJsonArray != null) {
			log.info("输入参数taskDecompositionListJsonArray是" + taskDecompositionListJsonArray.toJSONString());

			List<SrpmsProjectTaskVo> taskDecompositionList = taskDecompositionListJsonArray
					.toJavaList(SrpmsProjectTaskVo.class);

			this.setTaskAndBudgetList(taskDecompositionList, projectId, taskFlg);
		} else {
			log.info("输入参数taskDecompositionListJsonArray是null");
			taskService.cleanAndSaveTask(null, TaskCategoryEnums.APPLY_INNOVATE_TASK_DECOMPOSITION, projectId);
			taskService.cleanAndSaveTask(null, TaskCategoryEnums.APPLY_INNOVATE_BUDGET_DEPT, projectId);
			taskService.cleanAndSaveTask(null, TaskCategoryEnums.APPLY_INNOVATE_BUDGET_ALL, projectId);
		}
	}

	public void setTaskAndBudgetList(JSONArray taskDecompositionListJsonArray, long projectId) {

		if (taskDecompositionListJsonArray != null) {
			log.info("输入参数taskDecompositionListJsonArray是" + taskDecompositionListJsonArray.toJSONString());

			List<SrpmsProjectTaskVo> taskDecompositionList = taskDecompositionListJsonArray
					.toJavaList(SrpmsProjectTaskVo.class);

			this.setTaskAndBudgetList(taskDecompositionList, projectId);
		} else {
			log.info("输入参数taskDecompositionListJsonArray是null");
			taskService.cleanAndSaveTask(null, TaskCategoryEnums.APPLY_INNOVATE_TASK_DECOMPOSITION, projectId);
			taskService.cleanAndSaveTask(null, TaskCategoryEnums.APPLY_INNOVATE_BUDGET_DEPT, projectId);
			taskService.cleanAndSaveTask(null, TaskCategoryEnums.APPLY_INNOVATE_BUDGET_ALL, projectId);
		}
	}

	public void setTaskAndBudgetList(List<SrpmsProjectTaskVo> taskDecompositionList, long projectId) {

		this.setTaskAndBudgetList(taskDecompositionList, projectId, false);
	}

	public void setTaskAndBudgetList(List<SrpmsProjectTaskVo> allTaskDecompositionList, long projectId,
			boolean taskFlg) {

		TaskCategoryEnums taskEnums = TaskCategoryEnums.APPLY_INNOVATE_TASK_DECOMPOSITION;
		TaskCategoryEnums deptEnums = TaskCategoryEnums.APPLY_INNOVATE_BUDGET_DEPT;
		TaskCategoryEnums allEnums = TaskCategoryEnums.APPLY_INNOVATE_BUDGET_ALL;

		if (taskFlg) {
			taskEnums = TaskCategoryEnums.TASK_INNOVATE_TASK_DECOMPOSITION;
			deptEnums = TaskCategoryEnums.TASK_INNOVATE_BUDGET_DEPT;
			allEnums = TaskCategoryEnums.TASK_INNOVATE_BUDGET_ALL;
		}

		if (allTaskDecompositionList != null && allTaskDecompositionList.size() != 0) {

			// 任务列表设置承担单位
			for (int i = 0; i < allTaskDecompositionList.size(); i++) {
				SrpmsProjectTaskVo taskVo = allTaskDecompositionList.get(i);
				if (taskVo.getBudgetDetail() == null) {
					taskVo.setBudgetDetail(BudgetDetailUtil.emptyBudgetDetailInn());
				}
			}
			taskService.cleanAndSaveTask(allTaskDecompositionList, taskEnums, projectId);

		} else {
			log.info("输入参数taskDecompositionListJsonArray是null");
			taskService.cleanAndSaveTask(null, taskEnums, projectId);
		}

		List<SrpmsProjectTaskVo> taskDecompositionList = new ArrayList<SrpmsProjectTaskVo>();
		for (int i = 0; i < allTaskDecompositionList.size(); i++) {
			if (allTaskDecompositionList.get(i).getLeadPerson() != null
					&& allTaskDecompositionList.get(i).getLeadPerson().length() != 0) {
				taskDecompositionList.add(allTaskDecompositionList.get(i));
			}
		}
		if (taskDecompositionList != null && taskDecompositionList.size() != 0) {

			List<SrpmsProjectTaskVo> oldOudgetDeptList = taskService.queryTaskListByTaskCategory(deptEnums, projectId);

			log.info("分单位预算是" + JSON.toJSONString(oldOudgetDeptList));
			List<SrpmsProjectTaskVo> newOudgetDeptList = new ArrayList<SrpmsProjectTaskVo>();

			// 任务列表设置承担单位
			for (int i = 0; i < taskDecompositionList.size(); i++) {
				SrpmsProjectTaskVo taskVo = taskDecompositionList.get(i);
				if (taskVo.getBudgetDetail() == null) {
					taskVo.setBudgetDetail(BudgetDetailUtil.emptyBudgetDetailInn());
				}
				taskVo.setDeptName(taskVo.getLeadPersonInfo().getString("deptName"));
			}

			// 如果单位预算列表中的单位在新的任务列表中找不到对应的单位，就删除
			for (int i = 0; i < oldOudgetDeptList.size(); i++) {
				SrpmsProjectTaskVo taskDeptVo = oldOudgetDeptList.get(i);

				boolean removeFlg = true;
				for (int j = 0; j < taskDecompositionList.size(); j++) {
					if (taskDeptVo.getDeptName().equals(taskDecompositionList.get(j).getDeptName())) {
						removeFlg = false;
						break;
					}
				}
				if (!removeFlg) {
					newOudgetDeptList.add(taskDeptVo);
				}
			}
			log.info("分单位预算是" + JSON.toJSONString(newOudgetDeptList));
			// 如果新的任务列表中有单位在旧的按单位预算列表中的单位找不到，就追加一个新的单位预算
			for (int i = 0; i < taskDecompositionList.size(); i++) {

				SrpmsProjectTaskVo taskVo = taskDecompositionList.get(i);
				boolean addFlg = true;
				for (int j = 0; j < newOudgetDeptList.size(); j++) {
					SrpmsProjectTaskVo taskDeptVo = newOudgetDeptList.get(j);
					if (taskDeptVo.getDeptName().equals(taskVo.getDeptName())) {
						addFlg = false;
						break;
					}
				}

				if (addFlg) {
					SrpmsProjectTaskVo taskDeptVo = new SrpmsProjectTaskVo();
					taskDeptVo.setProjectId(taskVo.getProjectId());
					taskDeptVo.setDeptName(taskVo.getDeptName());
					taskDeptVo.setBudgetDetail(BudgetDetailUtil.emptyBudgetDetailInn());

					newOudgetDeptList.add(taskDeptVo);
				}
			}
			log.info("分单位预算是" + JSON.toJSONString(newOudgetDeptList));

			for (int i = 0; i < newOudgetDeptList.size(); i++) {
				SrpmsProjectTaskVo taskDeptVo = newOudgetDeptList.get(i);

				StringBuffer sbLeadPerson = new StringBuffer();
				StringBuffer sbTaskName = new StringBuffer();
				for (int j = 0; j < taskDecompositionList.size(); j++) {

					SrpmsProjectTaskVo taskVo = taskDecompositionList.get(j);

					if (taskDeptVo.getDeptName().equals(taskDecompositionList.get(j).getDeptName())) {
						taskDeptVo.setDeptCode(taskVo.getLeadPersonInfo().getString("deptCode"));
						if (sbLeadPerson.length() != 0) {
							sbLeadPerson.append(",");
						}
						sbLeadPerson.append(taskVo.getLeadPersonInfo().getString("personName"));

						if (sbTaskName.length() != 0) {
							sbTaskName.append(",");
						}
						sbTaskName.append(taskVo.getTaskName());
					}
				}
				taskDeptVo.setLeadPersonName(sbLeadPerson.toString());
				taskDeptVo.setTaskName(sbTaskName.toString());
			}

			for (int i = 0; i < newOudgetDeptList.size(); i++) {
				SrpmsProjectTaskVo taskDeptVo = newOudgetDeptList.get(i);
				BigDecimal lBudgetAmount = new BigDecimal(0);
				JSONArray detail = taskDeptVo.getBudgetDetail();
				for (int j = 0; j < detail.size(); j++) {
					JSONObject item = detail.getJSONObject(j);
					item.put("amount", 0);
					detail.set(j, item);
				}
				for (int j = 0; j < taskDecompositionList.size(); j++) {
					if (taskDeptVo.getDeptName().equals(taskDecompositionList.get(j).getDeptName())) {
						if (taskDecompositionList.get(j).getBudgetAmount() != null
								&& taskDecompositionList.get(j).getBudgetAmount().length() != 0) {
							lBudgetAmount = lBudgetAmount
									.add(new BigDecimal(taskDecompositionList.get(j).getBudgetAmount()));
						}
						JSONArray tempDetail = taskDecompositionList.get(j).getBudgetDetail();
						if (tempDetail != null) {
							for (int k = 0; k < detail.size(); k++) {
								JSONObject item = detail.getJSONObject(k);
								JSONObject tempItem = tempDetail.getJSONObject(k);
								long l1 = 0L;
								long l2 = 0L;
								if (item.getLong("amount") != null) {
									l1 = item.getLong("amount");
								}
								if (tempItem.getLong("amount") != null) {
									l2 = tempItem.getLong("amount");
								}
								item.put("amount", l1 + l2);
								detail.set(k, item);
							}
						}
					}
				}
				taskDeptVo.setBudgetAmount(String.valueOf(lBudgetAmount));
			}
			log.info("分单位预算是" + JSON.toJSONString(newOudgetDeptList));

			// 计算总预算表
			List<SrpmsProjectTaskVo> budgetAllList = taskService.queryTaskListByTaskCategory(allEnums, projectId);
			SrpmsProjectTaskVo budgetAllVo = null;
			if (budgetAllList.size() == 0) {
				budgetAllVo = new SrpmsProjectTaskVo();
				budgetAllVo.setProjectId(projectId + "");
				budgetAllVo.setBudgetDetail(BudgetDetailUtil.emptyBudgetDetailInn());
				budgetAllList.add(budgetAllVo);
			} else {
				budgetAllVo = budgetAllList.get(0);
			}

			BigDecimal lBudgetAmount = new BigDecimal(0);
			JSONArray detail = budgetAllVo.getBudgetDetail();
			for (int j = 0; j < detail.size(); j++) {
				JSONObject item = detail.getJSONObject(j);
				item.put("amount", 0);
				detail.set(j, item);
			}
			for (int j = 0; j < taskDecompositionList.size(); j++) {

				if (taskDecompositionList.get(j).getBudgetAmount() != null
						&& taskDecompositionList.get(j).getBudgetAmount().length() != 0) {
					lBudgetAmount = lBudgetAmount.add(new BigDecimal(taskDecompositionList.get(j).getBudgetAmount()));

				}
				detail = budgetAllVo.getBudgetDetail();
				JSONArray tempDetail = taskDecompositionList.get(j).getBudgetDetail();
				if (tempDetail != null) {
					for (int k = 0; k < detail.size(); k++) {
						JSONObject item = detail.getJSONObject(k);
						JSONObject tempItem = tempDetail.getJSONObject(k);
						long l1 = 0L;
						long l2 = 0L;
						if (item.getLong("amount") != null) {
							l1 = item.getLong("amount");
						}
						if (tempItem.getLong("amount") != null) {
							l2 = tempItem.getLong("amount");
						}
						item.put("amount", l1 + l2);
						detail.set(k, item);
					}
				}
			}
			budgetAllVo.setBudgetAmount(String.valueOf(lBudgetAmount));

			taskService.cleanAndSaveTask(newOudgetDeptList, deptEnums, projectId);
			taskService.cleanAndSaveTask(budgetAllList, allEnums, projectId);

		} else {
			log.info("输入参数taskDecompositionListJsonArray是null");
			taskService.cleanAndSaveTask(null, deptEnums, projectId);
			taskService.cleanAndSaveTask(null, allEnums, projectId);
		}
	}

	public JSONObject queryCommonInfoById(Long projectId, ProjectCategoryEnums type) {
		JSONObject relust = JSONObject.parseObject(JSONObject.toJSONString(projectService.getById(projectId)));

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
		List<SrpmsProjectPersonJoinVo> list4 = personJoinService
				.queryPersonJoinListByJoinWay(PersonJoinWayEnums.APPLY_MAIN_MEMBER, projectId);
		if (list4 != null && list4.size() != 0) {
			for (int i = 0; i < list4.size(); i++) {
				SrpmsProjectPersonJoinVo mianMember = list4.get(i);
				if (hasSubTask) {
					for (int j = 0; j < list5.size(); j++) {
						SrpmsProjectTaskVo taskVo = list5.get(j);
						if (mianMember.getPersonId() != null
								&& mianMember.getPersonId().toString().equals(taskVo.getLeadPerson())) {
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

		// 附件
		relust.putAll(attachmentService.queryAttachmentListApply(projectId));

		return relust;
	}

	public void copyApplyToTaskInnUnit(SrpmsProjectApplyInnUnitForm innUnitForm) {
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
		// 学科分类信息
		List<String> subjectCategoryList = Lists.newArrayList(innUnitForm.getSubjectCategory());
		project.setSubjectCategory(JSONArray.toJSONString(subjectCategoryList));

		projectService.saveOrUpdate(project);

		// 更新固定科技人员
		personJoinService.cleanAndSavePersonJoin(innUnitForm.getMainMemberList(), PersonJoinWayEnums.TASK_UNIT_PERSON,
				projectId);

		// 更新近5年承担的重要科研项目清单
		personJoinService.cleanAndSavePersonJoin(innUnitForm.getTopMemberOtherTaskList(),
				PersonJoinWayEnums.TASK_UNIT_PROJECT, projectId);

		// 保存/更新创新单元任务书
		SrpmsProjectTaskInnUnit unit = new BeanUtils<SrpmsProjectTaskInnUnit>().copyObj(innUnitForm,
				SrpmsProjectTaskInnUnit.class);
		taskInnUnitService.saveOrUpdate(unit);

	}

	public void copyApplyToTaskInnBcoo(SrpmsProjectApplyInnBcooSaveVo pageData, JSONObject budgetJson) {

		SrpmsProjectTaskInnExtVo inputVo = JSONObject.parseObject(JSON.toJSONString(pageData),
				SrpmsProjectTaskInnExtVo.class);

		// 任务总体考核指标及测评方式方法
		inputVo.setTaskMasterMethod(pageData.getProjectTarget());
		// 项目成果的呈现形式及描述
		inputVo.setAchievementForm(pageData.getAchievementForm());
		// 项目成果的预期经济、社会效益
		inputVo.setAchievementBenefit(pageData.getAchievementBenefit());
		// 拟采取的研究方法、技术路线及其可行性分析
		inputVo.setResearchContentMethod(pageData.getResearchContentMethod());
		// 国际合作和交流方案
		inputVo.setExchangeProgramme(pageData.getResearchContentInterflow());
		// 任务组织管理机制、产学研结合、创新人才队伍的凝聚和培养等
		inputVo.setTaskOrgManageMode(pageData.getManageGuarantee());
		// 知识产权对策、成果管理及合作权益分配
		inputVo.setKnowledgeResultManage(pageData.getManageKnowledgeRight());
		// 风险分析及对策
		inputVo.setRiskAnalyzeManage(pageData.getManageRisk());

		inputVo.setApprovalNecessay(null);

		inputVo.setResearchContentMain(null);

		inputVo.setResearchTarget(null);

		inputVo.setAttachmentFile(null);

		inputVo.setSubjectCategory(pageData.getSubjectCategory());

		inputVo.setMainMemberList(pageData.getMainMemberList());

		inputVo.setCoopDeptList(pageData.getCoopDeptList());

		inputVo.setTaskPreYearList(pageData.getTaskPreYearList());

		inputVo.setBudgetPreYearList(pageData.getBudgetPreYearList());

		inputVo.setJointApplicantUnit(pageData.getJointApplicantUnit());

		inputVo.setTaskDecompositionList(pageData.getTaskDecompositionList());

		inputVo.setAttachmentFile(null);

		inputVo.setAttachmentCommittee(null);
		
		inputVo.setAttachmentAudit(null);
		
		inputVo.setAttachmentStatement(null);

		taskInnService.saveOrUpdate(inputVo, null);

		BudgetApplyVo vo = JSONObject.parseObject(budgetJson.toJSONString(), BudgetApplyVo.class);
		budgetApplyService.saveOrUpdateBudgetApply(vo, true);
	}

	public void copyApplyToTaskInnCoo(SrpmsProjectApplyInnCooSaveVo pageData, JSONObject budgetJson) {

		SrpmsProjectTaskInnExtVo inputVo = JSONObject.parseObject(JSON.toJSONString(pageData),
				SrpmsProjectTaskInnExtVo.class);

		// 研究内容
		inputVo.setResearchContentMain(pageData.getResearchContentInnovate());
		// 任务总体考核指标及测评方式方法
		inputVo.setTaskMasterMethod(pageData.getProjectTarget());
		// 项目成果的呈现形式及描述
		inputVo.setAchievementForm(pageData.getAchievementForm());
		// 项目成果的预期经济、社会效益
		inputVo.setAchievementBenefit(pageData.getAchievementBenefit());
		// 拟采取的研究方法、技术路线及其可行性分析
		inputVo.setResearchContentMethod(pageData.getResearchPlan());
		// 国际合作和交流方案
		inputVo.setExchangeProgramme(pageData.getResearchContentInterflow());
		// 任务组织管理机制、产学研结合、创新人才队伍的凝聚和培养等
		inputVo.setTaskOrgManageMode(pageData.getManageGuarantee());
		// 知识产权对策、成果管理及合作权益分配
		inputVo.setKnowledgeResultManage(pageData.getManageKnowledgeRight());
		// 风险分析及对策
		inputVo.setRiskAnalyzeManage(pageData.getManageRisk());

		inputVo.setAttachmentFile(null);

		inputVo.setAttachmentCommittee(null);
		
		inputVo.setAttachmentAudit(null);
		
		inputVo.setAttachmentStatement(null);
		
		inputVo.setSubjectCategory(pageData.getSubjectCategory());

		inputVo.setMainMemberList(pageData.getMainMemberList());

		inputVo.setCoopDeptList(pageData.getCoopDeptList());

		inputVo.setTaskPreYearList(pageData.getTaskPreYearList());

		inputVo.setBudgetPreYearList(pageData.getBudgetPreYearList());

		inputVo.setTaskDecompositionList(pageData.getTaskDecompositionList());

		taskInnService.saveOrUpdate(inputVo, null);

		BudgetApplyVo vo = JSONObject.parseObject(budgetJson.toJSONString(), BudgetApplyVo.class);
		budgetApplyService.saveOrUpdateBudgetApply(vo, true);
	}

	public void copyApplyToTaskInnPer(SrpmsProjectApplyInnPreSubmitVo innPerVo, JSONObject budgetJson) {
		SrpmsProjectTaskInnExtVo inputVo = JSONObject.parseObject(JSON.toJSONString(innPerVo),
				SrpmsProjectTaskInnExtVo.class);

		JSONObject json = innPerVo.getLeadPerson();
		inputVo.setLeadPersonWorkTime(json.getLong("workPerYear"));

		// 立项依据
		inputVo.setApprovalNecessay(innPerVo.getProjectAbstract() + innPerVo.getApprovalNecessay());
		// 研究目标
		inputVo.setResearchTarget(innPerVo.getProjectTarget());
		// 研究内容
		inputVo.setResearchContentMain(innPerVo.getResearchContentMain());
		// 拟采取的研究方法、技术路线及其可行性分析
		inputVo.setResearchContentMethod(innPerVo.getResearchContentMethod());
		// 项目成果的呈现形式及描述
		inputVo.setAchievementForm(innPerVo.getAchievementForm());
		// 项目成果的预期经济、社会效益
		inputVo.setAchievementBenefit(innPerVo.getAchievementBenefit());

		inputVo.setAttachmentFile(null);

		inputVo.setAttachmentCommittee(null);
		
		inputVo.setAttachmentAudit(null);
		
		inputVo.setAttachmentStatement(null);
		
		taskInnService.saveOrUpdate(inputVo, null);

		BudgetApplyVo vo = JSONObject.parseObject(budgetJson.toJSONString(), BudgetApplyVo.class);
		budgetApplyService.saveOrUpdateBudgetApply(vo, true);
	}

	public void copyApplyToTask(JSONObject applyJson, JSONObject budgetJson) {
		SrpmsProjectTaskInnExtVo inputVo = JSONObject.parseObject(applyJson.toJSONString(),
				SrpmsProjectTaskInnExtVo.class);
		if (applyJson.getString("projectType").equals(ProjectCategoryEnums.INNOVATE_BCOO.getHeader())) {

			// 任务总体考核指标及测评方式方法
			inputVo.setTaskMasterMethod(applyJson.getString("projectTarget"));
			// 项目成果的呈现形式及描述
			inputVo.setAchievementForm(applyJson.getString("achievementForm"));
			// 项目成果的预期经济、社会效益
			inputVo.setAchievementBenefit(applyJson.getString("achievementBenefit"));
			// 拟采取的研究方法、技术路线及其可行性分析
			inputVo.setResearchContentMethod(applyJson.getString("researchContentMethod"));
			// 国际合作和交流方案
			inputVo.setExchangeProgramme(applyJson.getString("researchContentInterflow"));
			// 任务组织管理机制、产学研结合、创新人才队伍的凝聚和培养等
			inputVo.setTaskOrgManageMode(applyJson.getString("manageGuarantee"));
			// 知识产权对策、成果管理及合作权益分配
			inputVo.setKnowledgeResultManage(applyJson.getString("manageKnowledgeRight"));
			// 风险分析及对策
			inputVo.setRiskAnalyzeManage(applyJson.getString("manageRisk"));

			inputVo.setApprovalNecessay(null);

			inputVo.setResearchContentMain(null);

			inputVo.setResearchTarget(null);

		} else if (applyJson.getString("projectType").equals(ProjectCategoryEnums.INNOVATE_COO.getHeader())) {

			// 研究内容
			inputVo.setResearchContentMain(applyJson.getString("researchContentInnovate"));
			// 任务总体考核指标及测评方式方法
			inputVo.setTaskMasterMethod(applyJson.getString("projectTarget"));
			// 项目成果的呈现形式及描述
			inputVo.setAchievementForm(applyJson.getString("achievementForm"));
			// 项目成果的预期经济、社会效益
			inputVo.setAchievementBenefit(applyJson.getString("achievementBenefit"));
			// 拟采取的研究方法、技术路线及其可行性分析
			inputVo.setResearchContentMethod(applyJson.getString("researchPlan"));
			// 国际合作和交流方案
			inputVo.setExchangeProgramme(applyJson.getString("researchContentInterflow"));
			// 任务组织管理机制、产学研结合、创新人才队伍的凝聚和培养等
			inputVo.setTaskOrgManageMode(applyJson.getString("manageGuarantee"));
			// 知识产权对策、成果管理及合作权益分配
			inputVo.setKnowledgeResultManage(applyJson.getString("manageKnowledgeRight"));
			// 风险分析及对策
			inputVo.setRiskAnalyzeManage(applyJson.getString("manageRisk"));

		} else if (applyJson.getString("projectType").equals(ProjectCategoryEnums.INNOVATE_PRE.getHeader())) {
			JSONObject json = applyJson.getJSONObject("leadPerson");
			inputVo.setLeadPersonWorkTime(json.getLong("workPerYear"));

			// 立项依据
			inputVo.setApprovalNecessay(
					applyJson.getString("projectAbstract") + applyJson.getString("approvalNecessay"));
			// 研究目标
			inputVo.setResearchTarget(applyJson.getString("projectTarget"));
			// 研究内容
			inputVo.setResearchContentMain(applyJson.getString("researchContentMain"));
			// 拟采取的研究方法、技术路线及其可行性分析
			inputVo.setResearchContentMethod(applyJson.getString("researchContentMethod"));
			// 项目成果的呈现形式及描述
			inputVo.setAchievementForm(applyJson.getString("achievementForm"));
			// 项目成果的预期经济、社会效益
			inputVo.setAchievementBenefit(applyJson.getString("achievementBenefit"));
		}


		inputVo.setAttachmentFile(null);

		inputVo.setAttachmentCommittee(null);
		
		inputVo.setAttachmentAudit(null);
		
		inputVo.setAttachmentStatement(null);

		taskInnService.saveOrUpdate(inputVo, null);

		BudgetApplyVo vo = JSONObject.parseObject(budgetJson.toJSONString(), BudgetApplyVo.class);
		budgetApplyService.saveOrUpdateBudgetApply(vo, true);
	}
}