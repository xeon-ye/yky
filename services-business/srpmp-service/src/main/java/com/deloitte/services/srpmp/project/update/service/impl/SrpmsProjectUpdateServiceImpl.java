package com.deloitte.services.srpmp.project.update.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectAttachmentVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonJoinVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectTaskVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.TaskNodeVO;
import com.deloitte.platform.api.srpmp.project.update.param.SrpmsProjectUpdateQueryForm;
import com.deloitte.platform.api.srpmp.project.update.vo.SrpmsProjectUpdateForm;
import com.deloitte.platform.api.srpmp.project.update.vo.SrpmsProjectUpdateVo;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.common.entity.SrpmsCommonNclob;
import com.deloitte.services.srpmp.common.enums.SrpmsProjectUpdateStatusEnums;
import com.deloitte.services.srpmp.common.enums.SysDictEnums;
import com.deloitte.services.srpmp.common.enums.TaskCategoryEnums;
import com.deloitte.services.srpmp.common.enums.VoucherTypeEnums;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.common.service.ISerialNoCenterService;
import com.deloitte.services.srpmp.common.service.ISrpmsCommonNclobService;
import com.deloitte.services.srpmp.common.service.ISysDictService;
import com.deloitte.services.srpmp.common.util.JSONConvert;
import com.deloitte.services.srpmp.outline.util.CommonUtil;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.entity.SrpmsVoucher;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectAttachmentService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectFlowService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectTaskService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsVoucherService;
import com.deloitte.services.srpmp.project.base.service.impl.SrpmsProjectBpmServiceImpl;
import com.deloitte.services.srpmp.project.task.entity.SrpmsProjectTaskInn;
import com.deloitte.services.srpmp.project.task.entity.SrpmsProjectTranLong;
import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskInnService;
import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskTranLongService;
import com.deloitte.services.srpmp.project.update.entity.SrpmsProjectUpdate;
import com.deloitte.services.srpmp.project.update.mapper.SrpmsProjectUpdateMapper;
import com.deloitte.services.srpmp.project.update.service.ISrpmsProjectUpdateService;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author : Apeng
 * @Date : Create in 2019-04-01
 * @Description : SrpmsProjectUpdate服务实现类
 * @Modified :
 */
@Service
@Slf4j
@Transactional
public class SrpmsProjectUpdateServiceImpl extends ServiceImpl<SrpmsProjectUpdateMapper, SrpmsProjectUpdate>
		implements ISrpmsProjectUpdateService {

	@Autowired
	private SrpmsProjectUpdateMapper srpmsProjectUpdateMapper;

	@Autowired
	private ISrpmsProjectFlowService flowServicel;

	@Autowired
	private ISrpmsProjectService projectService;

	@Autowired
	private ISysDictService sysDictService;

	@Autowired
	private ISerialNoCenterService serialNoCenterService;

	@Autowired
	private ISrpmsProjectAttachmentService attachmentService;

	@Autowired
	private SrpmsProjectUpdateBudgetServiceImpl projectUpdateBudgetService;

	@Autowired
	private SrpmsProjectUpdateContentServiceImpl projectUpdateContentService;

	@Autowired
	private ISrpmsProjectTaskService taskService;

	@Autowired
	public SrpmsProjectBpmServiceImpl bpmService;

	@Autowired
	private ISrpmsVoucherService voucherService;

	@Autowired
	ISrpmsProjectTaskTranLongService tranLongService;

	@Autowired
	SrpmsProjectUpdateMenberServiceImpl updateMenberService;

	@Autowired
	ISrpmsCommonNclobService nclobService;

	@Autowired
	ISrpmsProjectTaskInnService projectTaskInnService;

	/**
	 * 查询变更记录service接口实现
	 *
	 * @param queryForm
	 * @return
	 */
	@Override
	public JSONObject list(SrpmsProjectUpdateQueryForm queryForm, UserVo user, DeptVo dept) {

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
				queryForm.setUpdateState(SrpmsProjectUpdateStatusEnums.PEROJECT_NOT_SUBMIT.getCode());
			}
		}

		queryForm.setTableFlag(tableFlg);

		log.info("tableFlg的值是：" + tableFlg);
		if (tableFlg == 1) {

			queryForm.setCreateBy(user.getId());

		} else if (tableFlg == 2) {

			queryForm.setUpdateState(SrpmsProjectUpdateStatusEnums.PEROJECT_HAS_APPROVE_PASS.getCode());

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

		QueryWrapper<SrpmsProjectUpdate> queryWrapper = new QueryWrapper<>();
		getQueryWrapper(queryWrapper, queryForm);
		IPage<SrpmsProjectUpdate> page = srpmsProjectUpdateMapper
				.selectPage(new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()), queryWrapper);
		List<SrpmsProjectUpdate> updateList = page.getRecords();
		List<SrpmsProjectUpdateVo> updateVoList = new ArrayList<>();

		Map<String, String> dictMap = sysDictService
				.getSysDictMap(new String[] { SrpmsConstant.PRO_UPADATE_STATE, SrpmsConstant.PRO_UPADATE_TYPE });
		Map<String, String> dictCategoryMap = sysDictService.getSysDictTranLong(SrpmsConstant.PRO_CATEGORY);

		if (updateList != null && updateList.size() > 0) {
			SrpmsProjectUpdate update;
			SrpmsProjectUpdateVo updateVo;
			for (Iterator e = updateList.iterator(); e.hasNext();) {
				update = (SrpmsProjectUpdate) e.next();
				updateVo = new SrpmsProjectUpdateVo();
				BeanUtils.copyProperties(update, updateVo);
				if (dictCategoryMap != null) {
					if (StringUtils.isNotBlank(update.getProjectType())) {
						updateVo.setProjectTypeName(dictCategoryMap.get(update.getProjectType()));
					}
				}
				if (dictMap != null) {
					if (StringUtils.isNotBlank(update.getUpdateState())) {
						updateVo.setUpdateStateName(
								dictMap.get(SrpmsConstant.PRO_UPADATE_STATE.concat(update.getUpdateState())));
					}
					if (StringUtils.isNotBlank(update.getUpdateType())) {
						updateVo.setUpdateTypeName(
								dictMap.get(SrpmsConstant.PRO_UPADATE_TYPE.concat(update.getUpdateType())));
					}
				}
				updateVo.setFileId(update.getFileId() + "");
				updateVo.setId(CommonUtil.objectToString(update.getId()));
				updateVo.setProjectId(CommonUtil.objectToString(update.getProjectId()));
				updateVoList.add(updateVo);
			}
		}
		JSONObject json = new JSONObject();
		json.put("current", page.getCurrent());
		json.put("pages", page.getPages());
		json.put("records", updateVoList);
		json.put("searchCount", page.isSearchCount());
		json.put("size", page.getSize());
		json.put("total", page.getTotal());
		json.put("tableFlag", queryForm.getTableFlag());
		return json;
	}

	/**
	 * 根据ID查询项目变更记录
	 *
	 * @param id
	 * @return
	 */
	@Override
	public JSONObject queryById(Long id) {
		log.info("输入参数id的值是：" + id);

		SrpmsProjectUpdate update = this.getById(id);
		log.info("根据id查询到的数据实体是：" + JSON.toJSONString(update));
		if (update == null) {
			throw new BaseException(SrpmsErrorType.UPDATE_NO_DATA);
		}
		String updateType = update.getUpdateType();
		String projectType = update.getProjectType();
		JSONObject relustJson = new JSONObject();
		relustJson.put("projectCode", update.getProjectCode());
		relustJson.put("projectName", update.getProjectName());
		relustJson.put("projectType", projectType);
		relustJson.put("projectFlag", update.getProjectFlag());

		Map<String, String> dictMap = sysDictService.getSysDictTranLong(SrpmsConstant.PRO_CATEGORY);
		if (dictMap != null) {
			if (StringUtils.isNotBlank(update.getProjectType())) {
				relustJson.put("projectTypeName", dictMap.get(update.getProjectType()));
			}
		}
		relustJson.put("updateNumber", update.getUpdateNumber());
		relustJson.put("updateReason", update.getUpdateReason());
		relustJson.put("updateState", update.getUpdateState());
		relustJson.put("fileId", update.getFileId() + "");
		relustJson.put("fileName", update.getFileName());
		relustJson.put("fileUrl", update.getFileUrl());

		relustJson.put("budNum", update.getBudNum());
		relustJson.put("projectId", update.getProjectId());
		relustJson.put("updateType", update.getUpdateType());

		// 附件
		List<SrpmsProjectAttachmentVo> attachmentFileList = attachmentService.queryAttachmentListAccept(id);
		log.info("查询到的附件列表数据是：" + JSON.toJSONString(attachmentFileList));
		relustJson.put("attachmentFile", JSONObject.parseArray(JSONObject.toJSONString(attachmentFileList)));

		// 查询审批历史记录
		SrpmsVoucher voucherEntity = voucherService.getSrpmsVoucherByUpdateId(id);
		List<TaskNodeVO> list = new ArrayList<>();
		if (voucherEntity != null) {
			list = bpmService.queryAuditHistory(CommonUtil.objectToString(voucherEntity.getId()));
		}
		log.info("查询到的审批历史记录数据是：" + JSON.toJSONString(list));
		relustJson.put("approveHistory", list);

		long projectId = update.getProjectId();
		if (projectId == 0L) {
			throw new BaseException(SrpmsErrorType.PARAM_NULL);
		}
		SrpmsProject srpmsProject = projectService.getById(projectId);

		if (SrpmsConstant.MODIFY_MODIFY_TYPE_40.equals(updateType)) {

			long clobId = Long.parseLong(update.getNewValue());
			SrpmsCommonNclob clobEntity = nclobService.getById(clobId);
			JSONArray budgetList = JSONArray.parseArray(clobEntity.getContent());
			relustJson.put("budgetList", budgetList);
			JSONArray changedList = projectUpdateBudgetService.getChangedbudgetList(update, srpmsProject);
			relustJson.put("changedList", changedList);
			JSONConvert.convertdate(relustJson);
			log.info("获取预算变更记录完成。获取到的数据是：" + relustJson.toJSONString());
			return relustJson;
		}

		if (SrpmsConstant.MODIFY_MODIFY_TYPE_50.equals(updateType)) {
			String oldStatusName = sysDictService.selectValueByCode(SysDictEnums.PRO_STAT, update.getOldValue());
			String statusName = sysDictService.selectValueByCode(SysDictEnums.PRO_STAT, update.getNewValue());
			relustJson.put("oldStatus", oldStatusName);
			relustJson.put("status", update.getNewValue());
			relustJson.put("statusName", statusName);
			JSONConvert.convertdate(relustJson);
			log.info("获取状态变更记录完成。获取到的数据是：" + relustJson.toJSONString());
			return relustJson;
		}

		if (SrpmsConstant.MODIFY_MODIFY_TYPE_20.equals(updateType)) {

			long oldClobId = Long.parseLong(update.getOldValue());
			SrpmsCommonNclob oldClobEntity = nclobService.getById(oldClobId);
			JSONArray oldLeadPerson = JSONArray.parseArray(oldClobEntity.getContent());
			relustJson.put("oldLeadPerson", oldLeadPerson);

			long clobId = Long.parseLong(update.getNewValue());
			SrpmsCommonNclob clobEntity = nclobService.getById(clobId);
			JSONArray leadPerson = JSONArray.parseArray(clobEntity.getContent());
			relustJson.put("leadPerson", leadPerson);
			JSONConvert.convertdate(relustJson);
			log.info("获取负责人变更记录完成。获取到的数据是：" + relustJson.toJSONString());
			return relustJson;
		}

		if (SrpmsConstant.MODIFY_MODIFY_TYPE_30.equals(updateType)) {

			log.info("获取内容变更记录完成。获取到的数据是：" + relustJson.toJSONString());
			long oldClobId = Long.parseLong(update.getOldValue());
			SrpmsCommonNclob oldClobEntity = nclobService.getById(oldClobId);
			JSONArray oldContentList = JSONArray.parseArray(oldClobEntity.getContent());
			relustJson.put("oldContentList", oldContentList);

			long newClobId = Long.parseLong(update.getNewValue());
			SrpmsCommonNclob newClobEntity = nclobService.getById(newClobId);
			JSONArray contentList = JSONArray.parseArray(newClobEntity.getContent());
			relustJson.put("contentList", contentList);

			JSONArray selectedContentList = new JSONArray();
			for (int i = 0; i < contentList.size(); i++) {
				JSONObject item = contentList.getJSONObject(i);
				if (item.getString("updateFlg") != null && "1".equals(item.getString("updateFlg"))) {
					JSONObject newItem = new JSONObject();
					newItem.put("key", item.getString("key"));
					newItem.put("name", item.getString("name"));
					selectedContentList.add(newItem);
				}
			}
			relustJson.put("selectedContentList", selectedContentList);
			JSONConvert.convertdate(relustJson);
			log.info("获取内容变更记录完成。获取到的数据是：" + relustJson.toJSONString());
			return relustJson;

		}

		// 任务分解
		List<SrpmsProjectTaskVo> taskList = null;
		if (SrpmsConstant.MODIFY_PRO_TYPE_10010101.equals(projectType)
				|| SrpmsConstant.MODIFY_PRO_TYPE_10010102.equals(projectType)) {
			taskList = taskService.queryTaskListByTaskCategory(TaskCategoryEnums.TASK_INNOVATE_TASK_DECOMPOSITION,
					projectId);
			log.info("该项目的任务分解列表是：" + JSON.toJSONString(taskList));
		}

		if (SrpmsConstant.MODIFY_MODIFY_TYPE_10.equals(updateType)) {

			relustJson.put("taskDecompositionList", taskList);

			long oldClobId = Long.parseLong(update.getOldValue());
			SrpmsCommonNclob oldClobEntity = nclobService.getById(oldClobId);
			JSONObject oldValue = JSONObject.parseObject(oldClobEntity.getContent());
			JSONArray oldMianMemberList = oldValue.getJSONArray("oldMianMemberList");
			relustJson.put("oldMianMemberList", oldMianMemberList);

			long clobId = Long.parseLong(update.getNewValue());
			SrpmsCommonNclob clobEntity = nclobService.getById(clobId);
			JSONObject newValue = JSONObject.parseObject(clobEntity.getContent());
			JSONArray mianMemberList = newValue.getJSONArray("mianMemberList");
			relustJson.put("mianMemberList", mianMemberList);

			JSONArray changedList = updateMenberService.getMemberChangedList(oldValue, newValue, srpmsProject);
			relustJson.put("changedList", changedList);

			if (SrpmsConstant.PROJECT_TYPE_1.equals(srpmsProject.getProjectFlag())) {// 横纵向院外参与人员

				log.info("该项目的院外人员列表是：" + oldValue.getString("oldOutMemberList"));
				relustJson.put("oldOutMemberList", oldValue.getJSONArray("oldOutMemberList"));
				relustJson.put("outMemberList", newValue.getJSONArray("outMemberList"));
				JSONConvert.convertdate(relustJson);
				log.info("获取人员变更记录完成。获取到的数据是：" + relustJson.toJSONString());
				return relustJson;
			} else {
				JSONConvert.convertdate(relustJson);
				log.info("获取人员变更记录完成。获取到的数据是：" + relustJson.toJSONString());
				return relustJson;
			}

		}

		log.error("项目变更类型不存在");
		throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
	}

	/**
	 * 点击变更查询最新版本操作数据service接口实现
	 *
	 * @param queryForm
	 * @return
	 */
	@Override
	public JSONObject addNewRecord(SrpmsProjectUpdateQueryForm queryForm, UserVo user) {

		log.info("输入参数SrpmsProjectUpdateQueryForm值是：" + JSON.toJSONString(queryForm));
		log.info("输入参数UserVo值是：" + JSON.toJSONString(user));
		long projectId = queryForm.getProjectId();
		if (projectId == 0L) {
			throw new BaseException(SrpmsErrorType.PARAM_NULL);
		}
		SrpmsProject srpmsProject = projectService.getById(projectId);
		if (srpmsProject == null) {
			throw new BaseException(SrpmsErrorType.UPDATE_NO_DATA);
		}
		String projectType = srpmsProject.getProjectType();

		log.info("查询出是否有相同条件的未完成的项目变更记录");
		String updateType = queryForm.getUpdateType();
		QueryWrapper<SrpmsProjectUpdate> queryWrapper = new QueryWrapper<SrpmsProjectUpdate>();
		queryWrapper.eq(SrpmsProjectUpdate.PROJECT_ID, projectId);
		queryWrapper.eq(SrpmsProjectUpdate.UPDATE_TYPE, updateType);
		queryWrapper.in(SrpmsProjectUpdate.UPDATE_STATE,
				new String[] { SrpmsConstant.APPROVE_REDO, SrpmsConstant.HAS_SUBMIT, SrpmsConstant.NOT_SUBMIT });
		queryWrapper.eq(SrpmsProjectUpdate.DEL_FLAG, SrpmsConstant.RECORD_NOT_DELETED);

		List<SrpmsProjectUpdate> updateHistoryList = srpmsProjectUpdateMapper.selectList(queryWrapper);

		log.info("有相同条件的未完成的项目变更记录是：" + JSON.toJSONString(updateHistoryList));
		if (updateHistoryList != null && updateHistoryList.size() != 0) {
			log.info("存在还没有完成的相同条件的的变更记录，不能在新增变更记录。异常返回。");
			throw new BaseException(SrpmsErrorType.UPDATE_RECORD_AREADY_EXIST);
		}

		JSONObject relustJson = new JSONObject();
		relustJson.put("projectCode", srpmsProject.getProjectNum());
		relustJson.put("projectName", srpmsProject.getProjectName());
		relustJson.put("projectType", projectType);

		Map<String, String> dictMap = sysDictService.getSysDictTranLong(SrpmsConstant.PRO_CATEGORY);
		if (dictMap != null) {
			if (StringUtils.isNotBlank(projectType)) {
				relustJson.put("projectTypeName", dictMap.get(projectType));
			}
		}

		if (SrpmsConstant.MODIFY_MODIFY_TYPE_40.equals(updateType)) {
			JSONArray budgetList = projectUpdateBudgetService.select(srpmsProject);
			relustJson.put("budgetList", budgetList);
			relustJson.put("oldBudgetList", budgetList);
			log.info("获取预算变更记录完成。获取到的数据是：" + relustJson.toJSONString());
			return relustJson;
		}

		if (SrpmsConstant.MODIFY_MODIFY_TYPE_50.equals(updateType)) {

			String statusName = sysDictService.selectValueByCode(SysDictEnums.PRO_STAT, srpmsProject.getStatus());
			relustJson.put("oldStatus", statusName);
			log.info("获取状态变更记录完成。获取到的数据是：" + relustJson.toJSONString());
			return relustJson;
		}

		if (SrpmsConstant.MODIFY_MODIFY_TYPE_20.equals(updateType)) {

			JSONArray leadPerson = new JSONArray();
			JSONObject leadPersonJson = JSONObject.parseObject(srpmsProject.getLeadPerson());

			SrpmsProjectTaskInn projectTaskInn = projectTaskInnService.getById(projectId);

			if (projectTaskInn != null) {
				leadPersonJson.put("workPerYear", projectTaskInn.getLeadPersonWorkTime());
			}
			String birthDay = leadPersonJson.getString("birthDate");
			if (birthDay != null && birthDay.length() == 19) {
				birthDay = birthDay.substring(0, 10);
			}
			leadPersonJson.put("birthDate", birthDay);
			leadPerson.add(leadPersonJson);
			relustJson.put("oldLeadPerson", leadPerson);
			relustJson.put("leadPerson", leadPerson);
			log.info("获取负责人变更记录完成。获取到的数据是：" + relustJson.toJSONString());
			return relustJson;
		}

		if (SrpmsConstant.MODIFY_MODIFY_TYPE_30.equals(updateType)) {
			log.info("获取内容变更记录完成。获取到的数据是：" + relustJson.toJSONString());
			JSONArray contentList = null;
			if (srpmsProject.getProjectFlag() != null && srpmsProject.getProjectFlag().length() != 0) {

				queryWrapper = new QueryWrapper<SrpmsProjectUpdate>();
				queryWrapper.eq(SrpmsProjectUpdate.PROJECT_ID, projectId);
				queryWrapper.eq(SrpmsProjectUpdate.UPDATE_TYPE, updateType);
				queryWrapper.eq(SrpmsProjectUpdate.UPDATE_STATE, SrpmsConstant.APPROVE_PASS);
				queryWrapper.eq(SrpmsProjectUpdate.DEL_FLAG, SrpmsConstant.RECORD_NOT_DELETED);

				queryWrapper.orderByAsc(SrpmsProjectUpdate.UPDATE_TIME);
				List<SrpmsProjectUpdate> updateContentList = srpmsProjectUpdateMapper.selectList(queryWrapper);
				contentList = new JSONArray();

				JSONObject jsonTemp = new JSONObject();
				jsonTemp.put("key", "content");
				jsonTemp.put("name", "横纵向内容变更");
				if (updateContentList.size() == 0) {
					jsonTemp.put("value", " ");

				} else {
					long clobId = Long.parseLong(updateContentList.get(0).getNewValue());
					SrpmsCommonNclob clobEntity = nclobService.getById(clobId);
					JSONArray oldContentList = JSONArray.parseArray(clobEntity.getContent());
					jsonTemp.put("value", oldContentList.getJSONObject(0).getString("value"));
				}
				contentList.add(jsonTemp);
			} else {
				contentList = projectUpdateContentService.select(srpmsProject);
			}

			relustJson.put("contentList", contentList);
			relustJson.put("oldContentList", contentList);
			return relustJson;
		}

		// 任务分解
		List<SrpmsProjectTaskVo> taskList = null;
		if (SrpmsConstant.MODIFY_PRO_TYPE_10010101.equals(projectType)
				|| SrpmsConstant.MODIFY_PRO_TYPE_10010102.equals(projectType)) {
			taskList = taskService.queryTaskListByTaskCategory(TaskCategoryEnums.TASK_INNOVATE_TASK_DECOMPOSITION,
					projectId);
			log.info("该项目的任务分解列表是：" + JSON.toJSONString(taskList));
		}

		if (SrpmsConstant.MODIFY_MODIFY_TYPE_10.equals(updateType)) {

			List<SrpmsProjectPersonJoinVo> menberList = updateMenberService.select(taskList, srpmsProject);
			log.info("该项目的主要参与人员列表是：" + JSON.toJSONString(menberList));

			relustJson.put("taskDecompositionList", taskList);
			relustJson.put("oldMianMemberList", menberList);
			relustJson.put("mianMemberList", menberList);

			if (SrpmsConstant.PROJECT_TYPE_1.equals(srpmsProject.getProjectFlag())) {// 横纵向院外参与人员
				SrpmsProjectTranLong tranLong = tranLongService.getById(projectId);
				String outJoinPerson = tranLong.getOutJoinPerson();
				log.info("该项目的院外人员列表是：" + outJoinPerson);
				JSONArray arr = JSONArray.parseArray(outJoinPerson);
				relustJson.put("oldOutMemberList", arr);
				relustJson.put("outMemberList", arr);
				log.info("获取负责人变更记录完成。获取到的数据是：" + relustJson.toJSONString());
				return relustJson;
			}
			log.info("获取人员变更记录完成。获取到的数据是：" + relustJson.toJSONString());
			return relustJson;
		}

		log.error("项目变更类型不存在");
		throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
	}

	/**
	 * 变更记录保存service接口实现
	 *
	 * @param form
	 * @param user
	 * @return
	 */
	@Override
	public String saveOrUpdate(SrpmsProjectUpdateForm form, UserVo user, DeptVo dept) {

		log.info("输入参数是：" + JSON.toJSONString(form));

		Long id = form.getId();
		Long projectId = form.getProjectId();
		String budNum = form.getBudNum();
		SrpmsProject projectEntity = projectService.getById(projectId);
		log.info("查询出项目信息是：" + JSON.toJSONString(projectEntity));

		String updateType = form.getUpdateType();
		SrpmsProjectUpdate update = new SrpmsProjectUpdate();

		if (id == null || id == 0L) {// 执行保存操作

			update.setUpdateNumber(serialNoCenterService.getNextBJNo("BJ", projectEntity.getProjectNum()));
			update.setDeptId(CommonUtil.getLongValue(dept.getDeptId()));
			update.setCreateBy(user.getId());
			update.setUpdateBy(user.getId());
			if (StringUtils.isNotBlank(projectEntity.getLeadPerson())) {// 项目负责人设值
				JSONObject leadPasonJson = JSONObject.parseObject(projectEntity.getLeadPerson());
				if (StringUtils.isNotBlank(leadPasonJson.getString("name"))) {
					update.setLeadPersonName(leadPasonJson.getString("name"));
				}
			}
			update.setProjectId(projectId);
			update.setProjectType(projectEntity.getProjectType());
			update.setProjectCode(projectEntity.getProjectNum());
			if (StringUtils.isNotBlank(form.getProjectName())) {
				update.setProjectName(form.getProjectName());
			} else {
				update.setProjectName(projectEntity.getProjectName());
			}
			update.setBudNum(budNum);
			update.setProjectFlag(projectEntity.getProjectFlag());
			update.setUpdateType(form.getUpdateType());
			update.setUpdateState(SrpmsProjectUpdateStatusEnums.PEROJECT_NOT_SUBMIT.getCode());
			update.setDelFlag(SrpmsConstant.RECORD_NOT_DELETED);

			SrpmsProjectUpdateQueryForm queryForm = new SrpmsProjectUpdateQueryForm();
			queryForm.setProjectId(projectId);
			queryForm.setUpdateType(updateType);
			JSONObject oldInfo = this.addNewRecord(queryForm, user);

			if (SrpmsConstant.MODIFY_MODIFY_TYPE_10.equals(updateType)) {

				log.info("参与人员变更");
				JSONObject menber = new JSONObject();
				menber.put("oldMianMemberList", oldInfo.getJSONArray("oldMianMemberList"));
				menber.put("oldOutMemberList", oldInfo.getJSONArray("oldOutMemberList"));

				SrpmsCommonNclob clobEntity = new SrpmsCommonNclob();
				clobEntity.setContent(menber.toJSONString());
				nclobService.save(clobEntity);
				update.setOldValue(clobEntity.getId() + "");

			} else if (SrpmsConstant.MODIFY_MODIFY_TYPE_20.equals(updateType)) {

				log.info("负责人变更");
				SrpmsCommonNclob clobEntity = new SrpmsCommonNclob();
				clobEntity.setContent(oldInfo.getJSONArray("oldLeadPerson").toJSONString());
				nclobService.save(clobEntity);
				update.setOldValue(clobEntity.getId() + "");

			} else if (SrpmsConstant.MODIFY_MODIFY_TYPE_30.equals(updateType)) {

				log.info("内容变更");
				SrpmsCommonNclob clobEntity = new SrpmsCommonNclob();
				clobEntity.setContent(oldInfo.getJSONArray("oldContentList").toJSONString());
				nclobService.save(clobEntity);
				update.setOldValue(clobEntity.getId() + "");

			} else if (SrpmsConstant.MODIFY_MODIFY_TYPE_40.equals(updateType)) {

				log.info("预算变更");
				SrpmsCommonNclob clobEntity = new SrpmsCommonNclob();
				clobEntity.setContent(oldInfo.getJSONArray("oldBudgetList").toJSONString());
				nclobService.save(clobEntity);
				update.setOldValue(clobEntity.getId() + "");

			} else if (SrpmsConstant.MODIFY_MODIFY_TYPE_50.equals(updateType)) {

				log.info("状态变更");
				update.setOldValue(projectEntity.getStatus());

			} else {
				log.error("项目变更类型不存在");
				throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
			}

		} else {// 执行更新操作
			update = this.getById(id);
		}

		if (SrpmsConstant.MODIFY_MODIFY_TYPE_10.equals(updateType)) {

			log.info("参与人员变更");
			JSONObject menberInfo = new JSONObject();

			JSONArray mianMemberList = new JSONArray();

			JSONArray outMemberList = new JSONArray();

			// 去掉空行
			if (form.getMianMemberList() != null) {
				for (int j = 0; j < form.getMianMemberList().size(); j++) {
					JSONObject menber = form.getMianMemberList().getJSONObject(j);
					if (menber.getString("personName") != null && !"".equals(menber.getString("personName"))) {
						mianMemberList.add(menber);
					}
				}
			}

			// 去掉空行
			if (form.getOutMemberList() != null) {
				for (int j = 0; j < form.getOutMemberList().size(); j++) {
					JSONObject menber = form.getOutMemberList().getJSONObject(j);
					if (menber.getString("personName") != null && !"".equals(menber.getString("personName"))) {
						outMemberList.add(menber);
					}
				}
			}

			menberInfo.put("mianMemberList", mianMemberList);
			menberInfo.put("outMemberList", outMemberList);
			if (update.getNewValue() != null) {
				long clobId = Long.parseLong(update.getNewValue());
				nclobService.removeById(clobId);
			}

			SrpmsCommonNclob clobEntity = new SrpmsCommonNclob();
			clobEntity.setContent(menberInfo.toJSONString());
			nclobService.save(clobEntity);
			update.setNewValue(clobEntity.getId() + "");
		} else if (SrpmsConstant.MODIFY_MODIFY_TYPE_20.equals(updateType)) {

			log.info("负责人变更");
			if (update.getNewValue() != null) {
				long clobId = Long.parseLong(update.getNewValue());
				nclobService.removeById(clobId);
			}
			JSONObject leadPersonJson = form.getLeadPerson().getJSONObject(0);
			String birthDay = leadPersonJson.getString("birthDate");
			if (birthDay != null && birthDay.length() == 19) {
				birthDay = birthDay.substring(0, 10);
			}
			leadPersonJson.put("birthDate", birthDay);

			JSONArray newArr = new JSONArray();
			newArr.add(leadPersonJson);
			SrpmsCommonNclob clobEntity = new SrpmsCommonNclob();
			clobEntity.setContent(newArr.toJSONString());
			nclobService.save(clobEntity);
			update.setNewValue(clobEntity.getId() + "");

		} else if (SrpmsConstant.MODIFY_MODIFY_TYPE_30.equals(updateType)) {
			log.info("内容变更");

			if (update.getNewValue() != null) {
				long clobId = Long.parseLong(update.getNewValue());
				nclobService.removeById(clobId);
			}
			SrpmsCommonNclob clobEntity = new SrpmsCommonNclob();
			clobEntity.setContent(form.getContentList().toJSONString());
			nclobService.save(clobEntity);
			update.setNewValue(clobEntity.getId() + "");

		} else if (SrpmsConstant.MODIFY_MODIFY_TYPE_40.equals(updateType)) {
			log.info("预算变更");

			if (update.getNewValue() != null) {
				long clobId = Long.parseLong(update.getNewValue());
				nclobService.removeById(clobId);
			}
			SrpmsCommonNclob clobEntity = new SrpmsCommonNclob();

			String strbudget = form.getBudgetList().toJSONString();
			clobEntity.setContent(strbudget.replaceAll("\"amount\":\"\"", "\"amount\":0"));
			nclobService.save(clobEntity);
			update.setNewValue(clobEntity.getId() + "");

		} else if (SrpmsConstant.MODIFY_MODIFY_TYPE_50.equals(updateType)) {
			log.info("状态变更");
			update.setNewValue(form.getStatus());
		} else {
			log.error("项目变更类型不存在");
			throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
		}

		update.setUpdateReason(form.getUpdateReason());
		this.saveOrUpdate(update);

		// 项目变更附件
		if (form.getAttachmentFile() != null && form.getAttachmentFile().size() > 0) {
			attachmentService.saveAttachmentListAccept(form.getAttachmentFile(), update.getId());
		} else {
			attachmentService.saveAttachmentListAccept(new ArrayList<SrpmsProjectAttachmentVo>(), update.getId());
		}
		return update.getId().toString();
	}

	/**
	 * 提交变更记录service接口实现
	 *
	 * @param form
	 * @param user
	 * @return
	 */
	@Override
	public String submit(SrpmsProjectUpdateForm form, UserVo user, DeptVo dept) {

		this.saveOrUpdate(form, user, dept);
		Long id = form.getId();
		if (id == null) {
			throw new BaseException(SrpmsErrorType.UPDATE_SAVE_BEFORE_SUBMIT);
		}

		// 校验变更记录
		SrpmsProjectUpdate update = srpmsProjectUpdateMapper.selectById(id);
		if (update == null) {
			throw new BaseException(SrpmsErrorType.UPDATE_NO_DATA);
		}
		if (!SrpmsProjectUpdateStatusEnums.PEROJECT_NOT_SUBMIT.getCode().equals(update.getUpdateState())) {
			throw new BaseException(SrpmsErrorType.UPDATE_HAVE_SUBMITTED);
		}

		VoucherTypeEnums voucherTypeEnums;

		long projectId = update.getProjectId();
		SrpmsProject srpmsProject = projectService.getById(projectId);
		if (srpmsProject == null) {
			throw new BaseException(SrpmsErrorType.UPDATE_NO_DATA);
		}
		String projectType = srpmsProject.getProjectType();

		switch (update.getUpdateType()) {
		case SrpmsConstant.MODIFY_MODIFY_TYPE_10:
			// 任务分解
			List<SrpmsProjectTaskVo> taskList = null;
			if (SrpmsConstant.MODIFY_PRO_TYPE_10010101.equals(projectType)
					|| SrpmsConstant.MODIFY_PRO_TYPE_10010102.equals(projectType)) {
				taskList = taskService.queryTaskListByTaskCategory(TaskCategoryEnums.TASK_INNOVATE_TASK_DECOMPOSITION,
						projectId);
				log.info("该项目的任务分解列表是：" + JSON.toJSONString(taskList));
				long clobId = Long.parseLong(update.getNewValue());
				SrpmsCommonNclob clobEntity = nclobService.getById(clobId);
				JSONObject newValue = JSONObject.parseObject(clobEntity.getContent());
				JSONArray mianMemberList = newValue.getJSONArray("mianMemberList");
				updateMenberService.checkOnSubmit(taskList, mianMemberList);
			}

			voucherTypeEnums = VoucherTypeEnums.UPDATE_JOIN_BOOK;
			break;
		case SrpmsConstant.MODIFY_MODIFY_TYPE_20:
			voucherTypeEnums = VoucherTypeEnums.UPDATE_PERSON_BOOK;
			break;
		case SrpmsConstant.MODIFY_MODIFY_TYPE_30:
			voucherTypeEnums = VoucherTypeEnums.UPDATE_CONTENT_BOOK;
			break;
		case SrpmsConstant.MODIFY_MODIFY_TYPE_40:

			long clobId = Long.parseLong(update.getOldValue());
			SrpmsCommonNclob clobEntity = nclobService.getById(clobId);
			JSONArray budgetList = JSONArray.parseArray(clobEntity.getContent());

			long newClobId = Long.parseLong(update.getNewValue());
			SrpmsCommonNclob newClobEntity = nclobService.getById(newClobId);
			JSONArray newBudgetList = JSONArray.parseArray(newClobEntity.getContent());

			// TODO
			BigDecimal oldAmount = new BigDecimal(0);
			for (int i =0; i < budgetList.size(); i++) {
				JSONObject item = budgetList.getJSONObject(i);
				if (item.getString("") != null) {
					
				}
			}
			voucherTypeEnums = VoucherTypeEnums.UPDATE_BUDGET_BOOK;
			break;
		case SrpmsConstant.MODIFY_MODIFY_TYPE_50:
			voucherTypeEnums = VoucherTypeEnums.UPDATE_STATE_BOOK;
			break;
		default:
			log.error("项目变更类型不存在");
			throw new BaseException(SrpmsErrorType.PARAM_NULL);
		}

		SrpmsProjectUpdate projectUpdateEntity = this.getById(id);
		projectUpdateEntity.setUpdateState(SrpmsConstant.HAS_SUBMIT);
		// 执行保存操作
		this.updateById(projectUpdateEntity);

		// 调用流程
		log.info("项目变更，提交变更记录，已更新项目变更状态, updateId:{}", id);
		// 发起流程
		flowServicel.startModifyAuditProcess(update, voucherTypeEnums, user, dept);

		log.info("项目变更，提交变更记录，已发起流程, updateId:{}", id);
		return id.toString();
	}

	public QueryWrapper<SrpmsProjectUpdate> getQueryWrapper(QueryWrapper<SrpmsProjectUpdate> queryWrapper,
			SrpmsProjectUpdateQueryForm queryForm) {
		if (StringUtils.isNotBlank(queryForm.getUpdateNumber())) {
			queryWrapper.eq(SrpmsProjectUpdate.UPDATE_NUMBER, queryForm.getUpdateNumber());
		}
		if (queryForm.getProjectId() != null && queryForm.getProjectId() != 0L) {
			queryWrapper.eq(SrpmsProjectUpdate.PROJECT_ID, queryForm.getProjectId());
		}
		if (StringUtils.isNotBlank(queryForm.getProjectCode())) {
			queryWrapper.like(SrpmsProjectUpdate.PROJECT_CODE, queryForm.getProjectCode());
		}
		if (StringUtils.isNotBlank(queryForm.getProjectName())) {
			queryWrapper.like(SrpmsProjectUpdate.PROJECT_NAME, queryForm.getProjectName());
		}
		if (StringUtils.isNotBlank(queryForm.getProjectType())) {
			if (queryForm.getProjectType().contains(",")) {
				String[] arr = queryForm.getProjectType().split(",");
				queryWrapper.in(SrpmsProjectUpdate.PROJECT_TYPE, arr);
			} else {
				queryWrapper.eq(SrpmsProjectUpdate.PROJECT_TYPE, queryForm.getProjectType());
			}
		}
		if (StringUtils.isNotBlank(queryForm.getUpdateType())) {
			queryWrapper.eq(SrpmsProjectUpdate.UPDATE_TYPE, queryForm.getUpdateType());
		}
		if (queryForm.getTableFlag() == 1) {
			if (StringUtils.isNotBlank(queryForm.getUpdateState())) {
				if (queryForm.getUpdateState().contains(",")) {
					String[] arr = queryForm.getUpdateState().split(",");
					queryWrapper.in(SrpmsProjectUpdate.UPDATE_STATE, arr);
				} else {
					queryWrapper.eq(SrpmsProjectUpdate.UPDATE_STATE, queryForm.getUpdateState());
				}
			} else {
				queryWrapper.notIn(SrpmsProjectUpdate.UPDATE_STATE,
						SrpmsProjectUpdateStatusEnums.PEROJECT_HAS_APPROVE_PASS.getCode());
			}
		} else if (queryForm.getTableFlag() == 2) {
			queryWrapper.eq(SrpmsProjectUpdate.UPDATE_STATE,
					SrpmsProjectUpdateStatusEnums.PEROJECT_HAS_APPROVE_PASS.getCode());
		}
		if (StringUtils.isNotBlank(queryForm.getDeptId())) {
			queryWrapper.eq(SrpmsProjectUpdate.DEPT_ID, queryForm.getDeptId());
		}
		if (StringUtils.isNotBlank(queryForm.getCreateBy())) {
			queryWrapper.eq(SrpmsProjectUpdate.CREATE_BY, queryForm.getCreateBy());
		}
		queryWrapper.eq(SrpmsProjectUpdate.DEL_FLAG, SrpmsConstant.RECORD_NOT_DELETED);
		queryWrapper.orderByDesc(SrpmsProjectUpdate.CREATE_TIME);
		return queryWrapper;
	}

	@Override
	public void delete(Long id) {
		SrpmsProjectUpdate projectUpdateEntity = this.getById(id);
		projectUpdateEntity.setDelFlag(SrpmsConstant.RECORD_DELETED);
		this.updateById(projectUpdateEntity);
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
}