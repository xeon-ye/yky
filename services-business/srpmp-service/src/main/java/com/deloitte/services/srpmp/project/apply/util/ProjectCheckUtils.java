package com.deloitte.services.srpmp.project.apply.util;

import java.math.BigDecimal;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectTaskVo;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetDeviceVo;
import com.deloitte.platform.api.srpmp.project.budget.vo.ext.BudgetApplyVo;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.services.srpmp.common.enums.ProjectCategoryEnums;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;

/**
 * @Author : pengchao
 * @Date : Create in 2019-03-08
 * @Description : CHECK共用类
 * @Modified :
 */
public class ProjectCheckUtils implements ProjectCheckConfig {

	public static void checkApply(SrpmsProject project, JSONObject json, JSONObject budgetJson) {

		StringBuffer errorMsg = new StringBuffer();

		if (ProjectCategoryEnums.INNOVATE_BCOO.getHeader().equals(project.getProjectType())) {

			errorMsg.append(doCheckEmpty(json, apply_Bcoo_not_empty));
			errorMsg.append(doCheckEmptyNumber(json, apply_Bcoo_not_empty_number));

		} else if (ProjectCategoryEnums.INNOVATE_COO.getHeader().equals(project.getProjectType())) {

			errorMsg.append(doCheckEmpty(json, apply_Coo_not_empty));
			errorMsg.append(doCheckEmptyNumber(json, apply_Coo_not_empty_number));

		} else if (ProjectCategoryEnums.INNOVATE_PRE.getHeader().equals(project.getProjectType())) {

			errorMsg.append(doCheckEmpty(json, apply_pre_not_empty));
			errorMsg.append(doCheckEmptyNumber(json, apply_pre_not_empty_number));

		}

		if (errorMsg.length() != 0) {
			throw new BaseException(SrpmsErrorType.INPUT_ERROR_9500, errorMsg.toString());
		}
		errorMsg.append(checkBudget(budgetJson));
		if (errorMsg.length() != 0) {
			throw new BaseException(SrpmsErrorType.INPUT_ERROR_9500, errorMsg.toString());
		}

		if (ProjectCategoryEnums.INNOVATE_BCOO.getHeader().equals(project.getProjectType())
				|| ProjectCategoryEnums.INNOVATE_PRE.getHeader().equals(project.getProjectType())) {

			// 检查任务书的经费预算和预算书的总预算的金额是否一致
			check001(json, budgetJson);

		}
	}

	public static void checkTask(SrpmsProject project, JSONObject taskJson, JSONObject budgetJson) {

		StringBuffer errorMsg = new StringBuffer();

		if (ProjectCategoryEnums.INNOVATE_BCOO.getHeader().equals(project.getProjectType())) {

			errorMsg.append(doCheckEmpty(taskJson, task_Bcoo_not_empty));
			errorMsg.append(doCheckEmptyNumber(taskJson, task_Bcoo_not_empty_number));

		} else if (ProjectCategoryEnums.INNOVATE_COO.getHeader().equals(project.getProjectType())) {

			errorMsg.append(doCheckEmpty(taskJson, task_Coo_not_empty));
			errorMsg.append(doCheckEmptyNumber(taskJson, task_Bcoo_not_empty_number));

		} else if (ProjectCategoryEnums.INNOVATE_PRE.getHeader().equals(project.getProjectType())) {

			errorMsg.append(doCheckEmpty(taskJson, task_pre_not_empty));
			errorMsg.append(doCheckEmptyNumber(taskJson, task_Bcoo_not_empty_number));

		}
		if (errorMsg.length() != 0) {
			throw new BaseException(SrpmsErrorType.INPUT_ERROR_9500, errorMsg.toString());
		}
		errorMsg.append(checkBudget(budgetJson));

		if (errorMsg.length() != 0) {
			throw new BaseException(SrpmsErrorType.INPUT_ERROR_9500, errorMsg.toString());
		}

		// 检查任务书的经费预算和预算书的总预算的金额是否一致
		check001(taskJson, budgetJson);
	}

	/**
	 * 检查任务书的经费预算和预算书的总预算的金额是否一致
	 */
	public static void check001(JSONObject taskJson, JSONObject budgetJson) {

		StringBuffer errorMsg = new StringBuffer();

		String applyFunds = taskJson.getString("applyFunds");
		JSONArray budgetAllList = budgetJson.getJSONArray("budgetAllList");
		String budgetAmount = budgetAllList.getJSONObject(0).getString("budgetAmount");

		if (new BigDecimal(applyFunds).compareTo(new BigDecimal(budgetAmount)) != 0) {
			errorMsg.append("<br/><br/>");
			errorMsg.append("基本信息中的经费预算和预算表中的的金额合计不一致。");
			throw new BaseException(SrpmsErrorType.INPUT_ERROR_9500, errorMsg.toString());
		}
	}

	private static StringBuffer doCheckEmpty(JSONObject json, String[] not_empty) {
		StringBuffer errorMsg = new StringBuffer();
		for (int i = 0; i < not_empty.length; i++) {

			String[] keyValue = not_empty[i].split(",");
			String key = keyValue[0];
			if (key.contains(".")) {
				String[] tempArr = key.split("\\.");
				String listName = tempArr[0];
				String filedName = tempArr[1];
				JSONArray tempJsonArr = json.getJSONArray(listName);
				if (tempJsonArr != null) {
					for (int j = 0; j < tempJsonArr.size(); j++) {
						JSONObject jsonObject = tempJsonArr.getJSONObject(j);
						String value = jsonObject.getString(filedName);
						checkEmpty(value, keyValue[1], errorMsg);
					}
				}
			} else {
				String value = json.getString(keyValue[0]);
				checkEmpty(value, keyValue[1], errorMsg);
			}
		}
		return errorMsg;
	}

	private static StringBuffer doCheckEmptyNumber(JSONObject json, String[] not_empty) {
		StringBuffer errorMsg = new StringBuffer();
		for (int i = 0; i < not_empty.length; i++) {

			String[] keyValue = not_empty[i].split(",");
			String key = keyValue[0];
			if (key.contains(".")) {
				String[] tempArr = key.split("\\.");
				String listName = tempArr[0];
				String filedName = tempArr[1];
				JSONArray tempJsonArr = json.getJSONArray(listName);
				if (tempJsonArr != null) {
					for (int j = 0; j < tempJsonArr.size(); j++) {
						JSONObject jsonObject = tempJsonArr.getJSONObject(j);
						String value = jsonObject.getString(filedName);
						checkNumberEmpty(value, keyValue[1], errorMsg);
					}
				}
			} else {
				String value = json.getString(keyValue[0]);
				checkNumberEmpty(value, keyValue[1], errorMsg);
			}
		}
		return errorMsg;
	}

	public static StringBuffer checkBudget(JSONObject json) {

		BudgetApplyVo vo = JSONObject.parseObject(json.toJSONString(), BudgetApplyVo.class);

		return checkBudget(vo);
	}

	public static StringBuffer checkBudget(BudgetApplyVo vo) {
		StringBuffer errorMsg = new StringBuffer();
		checkEmpty(vo.getBudgetActionDateStart(), "预算执行周期开始", errorMsg);
		checkEmpty(vo.getBudgetActionDateEnd(), "预算执行周期结束", errorMsg);

		checkEmpty(vo.getLeadDept().getString("bankAccountNameLoose"), "项目承担单位：零余额账户信息的单位开户名称", errorMsg);
		checkEmpty(vo.getLeadDept().getString("bankNameLoose"), "项目承担单位：零余额账户信息的开户银行(全称)", errorMsg);
		checkEmpty(vo.getLeadDept().getString("bankAccountNumberLoose"), "项目承担单位：零余额账户信息的银行账号", errorMsg);

		checkEmpty(vo.getLeadDept().getString("bankAccountNameBase"), "项目承担单位：基本账户的单位开户名称", errorMsg);
		checkEmpty(vo.getLeadDept().getString("bankNameBase"), "项目承担单位：基本账户的开户银行(全称)", errorMsg);
		checkEmpty(vo.getLeadDept().getString("bankAccountNumberBase"), "项目承担单位：基本账户的银行账号", errorMsg);

		String financePersonName = null;
		if (vo.getFinancePerson() != null) {
			financePersonName = vo.getFinancePerson().getPersonName();
		}
		checkEmpty(financePersonName, "财务部门负责人", errorMsg);

		String contactPersonName = null;
		if (vo.getContactPerson() != null) {
			contactPersonName = vo.getContactPerson().getPersonName();
		}
		checkEmpty(contactPersonName, "项目联系人", errorMsg);

		List<SrpmsProjectTaskVo> taskList = vo.getTaskDecompositionList();
		for (int i = 0; i < taskList.size(); i++) {
			SrpmsProjectTaskVo task = taskList.get(i);
			checkNumberEmpty(task.getBudgetAmount(), "任务【" + task.getTaskName() + "】的预算", errorMsg);
		}

		List<SrpmsProjectBudgetDeviceVo> deviceList = vo.getDeviceBudgetList();
		if (deviceList != null) {
			for (int i = 0; i < deviceList.size(); i++) {
				SrpmsProjectBudgetDeviceVo deviceVo = deviceList.get(i);
				checkEmpty(deviceVo.getDeviceName(), "设备费第" + (i + 1) + "行设备名称", errorMsg);
				checkEmpty(deviceVo.getDeviceCat(), "设备费第" + (i + 1) + "行设备分类", errorMsg);
				checkEmpty(deviceVo.getUnitPrice(), "设备费第" + (i + 1) + "行单价(元/台)", errorMsg);
				checkEmpty(deviceVo.getDeviceCount(), "设备费第" + (i + 1) + "行数量", errorMsg);
				checkEmpty(deviceVo.getDeviceType(), "设备费第" + (i + 1) + "行设备类型", errorMsg);
				checkEmpty(deviceVo.getDeviceNo(), "设备费第" + (i + 1) + "行购置设备型号", errorMsg);
				checkEmpty(deviceVo.getProducer(), "设备费第" + (i + 1) + "行设备生产国别与地区", errorMsg);
				checkEmpty(deviceVo.getTechQuota(), "设备费第" + (i + 1) + "行主要技术性能指标", errorMsg);
				checkEmpty(deviceVo.getUseage(), "设备费第" + (i + 1) + "行用途(与课题研究任务的关系)", errorMsg);
			}
		}

		List<SrpmsProjectBudgetDeviceVo> testList = vo.getTestBudgetList();
		if (testList != null) {
			for (int i = 0; i < testList.size(); i++) {
				SrpmsProjectBudgetDeviceVo testVo = testList.get(i);
				checkEmpty(testVo.getContent(), "化验加工费第" + (i + 1) + "行测试化验加工的内容", errorMsg);
				checkEmpty(testVo.getDeptName(), "化验加工费第" + (i + 1) + "行测试化验加工单位", errorMsg);
				checkEmpty(testVo.getMeasurementUnit(), "化验加工费第" + (i + 1) + "行计量单位", errorMsg);
				checkEmpty(testVo.getUnitPrice(), "化验加工费第" + (i + 1) + "行单价", errorMsg);
				checkEmpty(testVo.getDeviceCount(), "化验加工费第" + (i + 1) + "行数量", errorMsg);
			}
		}

		checkNumberEmpty(vo.getFundSourceAmount(), "资金总额", errorMsg);

		checkNumberEmpty(vo.getFundSourceAmountYear(), "全年资金总额", errorMsg);

		checkNumberEmpty(vo.getFundSourceProject(), "当年财务拨款", errorMsg);

		checkNumberEmpty(vo.getFundSourceProjectYear(), "全年财政拨款", errorMsg);

		checkEmpty(vo.getProjectTarget(), "项目总体目标", errorMsg);
		//
		// checkEmpty(vo.getPerformanceLibraryCode(), "绩效目标库", errorMsg);
		//
		// JSONArray performanceIndicatorDetail = vo.getPerformanceIndicatorDetail();
		//
		// // if (performanceIndicatorDetail == null ||
		// performanceIndicatorDetail.size()
		// // == 0) {
		// // errorMsg.append("<br/><br/>");
		// // errorMsg.append("项目绩效目标没有输入");
		// // }
		//
		// if (performanceIndicatorDetail != null) {
		// for (int i = 0; i < performanceIndicatorDetail.size(); i++) {
		// JSONObject item = performanceIndicatorDetail.getJSONObject(i);
		// checkEmpty(item.getString("quotaType"), "绩效目标第" + (i + 1) + "行指标类型",
		// errorMsg);
		// checkEmpty(item.getString("quotaValue"), "绩效目标第" + (i + 1) + "行指标值",
		// errorMsg);
		//
		// }
		// }
		//
		// checkEmpty(vo.getProjectTarget(), "项目总体目标", errorMsg);

		checkEmpty(vo.getSpecifSupport(), "预算说明第一项", errorMsg);

		checkEmpty(vo.getSpecifMoneyPlan(), "预算说明第二项", errorMsg);
		if (errorMsg.length() != 0) {

			throw new BaseException(SrpmsErrorType.INPUT_ERROR_9500, errorMsg.toString());
		}
		return errorMsg;
	}

	public static void checkEmpty(String str, String name, StringBuffer errorMsg) {

		if (str == null || str.length() == 0 || "[]".equals(str)) {
			errorMsg.append("<br/><br/>");
			errorMsg.append(name + "没有输入");
		}
	}

	public static void checkNumberEmpty(String value, String name, StringBuffer errorMsg) {

		if (value == null || value.length() == 0) {
			errorMsg.append("<br/><br/>");
			errorMsg.append(name + "没有输入");
			return;
		}

		BigDecimal longVale = null;
		try {
			longVale = new BigDecimal(value);
		} catch (NumberFormatException e) {
			errorMsg.append("<br/><br/>");
			errorMsg.append(name + "输入不正确");
			return;
		}

		if (longVale == null || longVale.compareTo(new BigDecimal(0)) == 0) {
			errorMsg.append("<br/><br/>");
			errorMsg.append(name + "输入不正确");
		}
	}
}
