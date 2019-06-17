package com.deloitte.services.srpmp.project.update.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectTaskVo;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetDetailVo;
import com.deloitte.platform.api.srpmp.project.budget.vo.ext.BudgetSimpleOutVo;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.common.entity.SrpmsCommonNclob;
import com.deloitte.services.srpmp.common.enums.BudgetCategoryEnums;
import com.deloitte.services.srpmp.common.enums.TaskCategoryEnums;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.common.service.ISrpmsCommonNclobService;
import com.deloitte.services.srpmp.project.apply.service.impl.SrpmsProjectApplyInnCommonServiceImpl;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectBudgetDetailService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectTaskService;
import com.deloitte.services.srpmp.project.budget.service.ISrpmsProjectBudgetApplyYearService;
import com.deloitte.services.srpmp.project.update.entity.SrpmsProjectUpdate;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author : pengchao
 * @Date : Create in 2019-04-01
 * @Description : SrpmsProjectUpdateBudget服务实现类
 * @Modified :
 */
@Service
@Slf4j
@Transactional
public class SrpmsProjectUpdateBudgetServiceImpl {

	@Autowired
	private ISrpmsProjectBudgetApplyYearService budgetApplyYearService;

	@Autowired
	private ISrpmsProjectTaskService taskService;

	@Autowired
	private ISrpmsProjectBudgetDetailService budgetDetailService;

	@Autowired
	SrpmsProjectApplyInnCommonServiceImpl commonService;

	@Autowired
	ISrpmsCommonNclobService nclobService;

	public void save(SrpmsProjectUpdate update, SrpmsProject project) {

		log.info("输入参数update值是：" + JSON.toJSONString(update));
		log.info("输入参数project值是：" + JSON.toJSONString(project));
		long clobId = Long.parseLong(update.getNewValue());
		SrpmsCommonNclob clobEntity = nclobService.getById(clobId);
		JSONArray budgetList = JSONArray.parseArray(clobEntity.getContent());
		String projectType = project.getProjectType();
		long projectId = project.getId();

		if (SrpmsConstant.MODIFY_PRO_TYPE_10010101.equals(projectType)
				|| SrpmsConstant.MODIFY_PRO_TYPE_10010102.equals(projectType)
				|| SrpmsConstant.MODIFY_PRO_TYPE_10010103.equals(projectType)) {

			commonService.setTaskAndBudgetList(budgetList, projectId, true);

		} else if (SrpmsConstant.MODIFY_PRO_TYPE_10010201.equals(projectType)
				|| SrpmsConstant.MODIFY_PRO_TYPE_10010301.equals(projectType)
				|| SrpmsConstant.MODIFY_PRO_TYPE_10010302.equals(projectType)
				|| SrpmsConstant.MODIFY_PRO_TYPE_10010401.equals(projectType)) {

			budgetDetailService.cleanAndSaveBudgetDetail(
					JSONArray.parseArray(budgetList.toJSONString(), SrpmsProjectBudgetDetailVo.class),
					BudgetCategoryEnums.TASK_YEAR_PLAN_DETAIL, projectId);
		}

		if (project.getProjectFlag() != null && !"".equals(project.getProjectFlag())) {

			budgetDetailService.cleanAndSaveBudgetDetail(
					JSONArray.parseArray(budgetList.toJSONString(), SrpmsProjectBudgetDetailVo.class),
					BudgetCategoryEnums.TASK_TRAN_LONG_DETAIL, projectId);
		}

		log.info("更新预算完成！！！");
	}

	public JSONArray select(SrpmsProject srpmsProject) {

		log.info("输入参数是：" + JSON.toJSONString(srpmsProject));

		JSONArray relust = new JSONArray();

		long projectId = srpmsProject.getId();
		String budNum = null;
		String projectType = srpmsProject.getProjectType();
		String today = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
		log.info("今天的日期是" + today);
		if (SrpmsConstant.MODIFY_PRO_TYPE_10010101.equals(projectType)
				|| SrpmsConstant.MODIFY_PRO_TYPE_10010102.equals(projectType)
				|| SrpmsConstant.MODIFY_PRO_TYPE_10010103.equals(projectType)) {
			log.info("该项目是创新工程。取该项目所有的预算书信息。");
			List<BudgetSimpleOutVo> budgetApplyYearList = budgetApplyYearService.queryAllById(projectId);
			log.info("该项目所有的预算书信息是" + JSON.toJSONString(budgetApplyYearList));

			for (int i = 0; i < budgetApplyYearList.size(); i++) {
				BudgetSimpleOutVo budgetApplyYear = budgetApplyYearList.get(i);

				if (today.compareTo(budgetApplyYear.getBudgetActionDateStart()) > 0
						&& today.compareTo(budgetApplyYear.getBudgetActionDateEnd()) < 0) {

					budNum = budgetApplyYear.getBudNum();
					log.info("当前 有效的 预算书编号是" + budNum);
					// 分任务预算表
					List<SrpmsProjectTaskVo> taskDecompositionList = taskService
							.queryTaskListByTaskCategory(TaskCategoryEnums.TASK_INNOVATE_TASK_DECOMPOSITION, projectId);
					log.info("当前 项目的分任务预算表信息是：" + JSON.toJSONString(taskDecompositionList));

					for (int j = 0; j < taskDecompositionList.size(); j++) {
						SrpmsProjectTaskVo tempTask = taskDecompositionList.get(j);
						JSONObject budgetJson = JSONObject.parseObject(JSONObject.toJSONString(budgetApplyYear));
						JSONObject taskJson = JSONObject.parseObject(JSONObject.toJSONString(tempTask));
						budgetJson.putAll(taskJson);
						budgetJson.put("budgetYear", budgetApplyYear.getBudgetActionDateStart().substring(0, 4));
						relust.add(budgetJson);
					}
					return relust;
				}
			}

			throw new BaseException(SrpmsErrorType.NOT_IN_BUDGET_ACTION_DATE);
		} else if (SrpmsConstant.MODIFY_PRO_TYPE_10010201.equals(projectType)
				|| SrpmsConstant.MODIFY_PRO_TYPE_10010301.equals(projectType)
				|| SrpmsConstant.MODIFY_PRO_TYPE_10010302.equals(projectType)
				|| SrpmsConstant.MODIFY_PRO_TYPE_10010401.equals(projectType)) {

			String year = DateFormatUtils.format(new Date(), "yyyy");

			List<SrpmsProjectBudgetDetailVo> budgetDetailList = budgetDetailService
					.queryBudgetDetailListByCategory(BudgetCategoryEnums.TASK_YEAR_PLAN_DETAIL, projectId);
			for (int i = 0; i < budgetDetailList.size(); i++) {
				SrpmsProjectBudgetDetailVo budgetDetail = budgetDetailList.get(i);
				if (year.equals(budgetDetail.getBudgetYear() + "")) {
					budNum = year + srpmsProject.getProjectNum();

					JSONObject budgetJson = JSONObject.parseObject(JSON.toJSONString(budgetDetail));
					budgetJson.put("budNum", budNum);
					budgetJson.put("budgetActionDateStart", year + "-01-01");
					budgetJson.put("budgetActionDateEnd", year + "-12-31");
					relust.add(budgetJson);
					return relust;
				}
			}
			throw new BaseException(SrpmsErrorType.NOT_IN_BUDGET_ACTION_DATE);
		}

		if (SrpmsConstant.PROJECT_TYPE_1.equals(srpmsProject.getProjectFlag())) {

			List<SrpmsProjectBudgetDetailVo> budgetDetailList = budgetDetailService
					.queryBudgetDetailListByCategory(BudgetCategoryEnums.TASK_TRAN_LONG_DETAIL, projectId);
			for (int i = 0; i < budgetDetailList.size(); i++) {
				SrpmsProjectBudgetDetailVo budgetDetail = budgetDetailList.get(i);

				budNum = srpmsProject.getProjectNum();

				JSONObject budgetJson = JSONObject.parseObject(JSON.toJSONString(budgetDetail));
				budgetJson.put("budNum", budNum);
				relust.add(budgetJson);
			}
			return relust;
		}

		return null;
	}

	public void checkbudgetUpdateOnTime(SrpmsProjectUpdate updateEntity, SrpmsProject projectEntity) {

		long oldClobId = Long.parseLong(updateEntity.getOldValue());
		SrpmsCommonNclob oldClobEntity = nclobService.getById(oldClobId);
		JSONArray oldArray = JSONArray.parseArray(oldClobEntity.getContent());

		long clobId = Long.parseLong(updateEntity.getNewValue());
		SrpmsCommonNclob clobEntity = nclobService.getById(clobId);
		JSONArray newArray = JSONArray.parseArray(clobEntity.getContent());

		if (!oldArray.getJSONObject(0).getString("budNum").equals(newArray.getJSONObject(0).getString("budNum"))) {
			log.error("该预算变更已经过了预算的执行期限。");
			throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
		}
	}

	public JSONArray getChangedbudgetList(SrpmsProjectUpdate updateEntity, SrpmsProject projectEntity) {

		JSONArray relust = new JSONArray();
		long oldClobId = Long.parseLong(updateEntity.getOldValue());
		SrpmsCommonNclob oldClobEntity = nclobService.getById(oldClobId);
		JSONArray oldArray = JSONArray.parseArray(oldClobEntity.getContent());

		long clobId = Long.parseLong(updateEntity.getNewValue());
		SrpmsCommonNclob clobEntity = nclobService.getById(clobId);
		JSONArray newArray = JSONArray.parseArray(clobEntity.getContent());

		String projectType = projectEntity.getProjectType();

		for (int i = 0; i < newArray.size(); i++) {
			JSONObject newJson = newArray.getJSONObject(i);
			JSONObject oldJson = oldArray.getJSONObject(i);
			JSONArray newBudgetDetail = newJson.getJSONArray("budgetDetail");
			JSONArray oldBudgetDetail = oldJson.getJSONArray("budgetDetail");
			if (SrpmsConstant.MODIFY_PRO_TYPE_10010101.equals(projectType)
					|| SrpmsConstant.MODIFY_PRO_TYPE_10010102.equals(projectType)
					|| SrpmsConstant.MODIFY_PRO_TYPE_10010103.equals(projectType)) {
				for (int j = 0; j < newBudgetDetail.size(); j++) {
					JSONObject newBudgetDetailJson = newBudgetDetail.getJSONObject(j);
					JSONObject oldBudgetDetailJson = oldBudgetDetail.getJSONObject(j);
					if (!newBudgetDetailJson.getString("amount").equals(oldBudgetDetailJson.getString("amount"))) {
						JSONObject budget = new JSONObject();
						if (SrpmsConstant.MODIFY_PRO_TYPE_10010101.equals(projectType)
								|| SrpmsConstant.MODIFY_PRO_TYPE_10010102.equals(projectType)) {
							StringBuffer sb = new StringBuffer();
							sb.append("任务名称【");
							sb.append(newJson.getString("taskName"));
							sb.append("】，科目名称【");
							sb.append(oldBudgetDetailJson.getString("name"));
							sb.append("】");
							budget.put("budgetName", sb.toString());
						} else {
							budget.put("budgetName", oldBudgetDetailJson.getString("name"));
						}

						budget.put("oldValue", oldBudgetDetailJson.getString("amount"));
						budget.put("value", newBudgetDetailJson.getString("amount"));
						relust.add(budget);
					}
				}
			} else if ("1".equals(projectEntity.getProjectFlag())) {
				for (int j = 0; j < newBudgetDetail.size(); j++) {
					JSONObject newBudgetDetailJson = newBudgetDetail.getJSONObject(j);
					JSONObject oldBudgetDetailJson = oldBudgetDetail.getJSONObject(j);
					if (!newBudgetDetailJson.getString("amount").equals(oldBudgetDetailJson.getString("amount"))) {
						JSONObject budget = new JSONObject();
						budget.put("budgetName", oldBudgetDetailJson.getString("name"));
						budget.put("oldValue", oldBudgetDetailJson.getString("amount"));
						budget.put("value", newBudgetDetailJson.getString("amount"));
						relust.add(budget);
					}
				}
			}
			else {
				for (int j = 0; j < newBudgetDetail.size(); j++) {
					JSONArray newChild = newBudgetDetail.getJSONObject(j).getJSONArray("child");
					JSONArray oldChild = oldBudgetDetail.getJSONObject(j).getJSONArray("child");
					String fatherName = newBudgetDetail.getJSONObject(j).getString("name");
					for (int k = 0; k < newChild.size(); k++) {
						JSONObject newBudgetDetailJson = newChild.getJSONObject(k);
						JSONObject oldBudgetDetailJson = oldChild.getJSONObject(k);

						if (!newBudgetDetailJson.getString("amount").equals(oldBudgetDetailJson.getString("amount"))) {
							JSONObject budget = new JSONObject();

							budget.put("budgetName", "【" + fatherName + "】" + oldBudgetDetailJson.getString("name"));
							budget.put("oldValue", oldBudgetDetailJson.getString("amount"));
							budget.put("value", newBudgetDetailJson.getString("amount"));
							relust.add(budget);
						}
					}

				}
			}
		}

		return relust;
	}
}