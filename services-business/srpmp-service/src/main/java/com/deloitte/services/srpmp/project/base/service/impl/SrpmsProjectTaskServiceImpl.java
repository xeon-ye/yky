package com.deloitte.services.srpmp.project.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.srpmp.project.base.param.SrpmsProjectTaskQueryForm;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonJoinVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectTaskVo;
import com.deloitte.platform.common.core.util.UUIDUtil;
import com.deloitte.services.srpmp.common.enums.PersonJoinWayEnums;
import com.deloitte.services.srpmp.common.enums.TaskCategoryEnums;
import com.deloitte.services.srpmp.common.util.JSONConvert;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectPersonJoin;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectTask;
import com.deloitte.services.srpmp.project.base.mapper.SrpmsProjectTaskMapper;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectTaskService;
import com.deloitte.services.srpmp.project.budget.service.impl.SrpmsProjectBudgetApplyServiceImpl;

import lombok.extern.slf4j.Slf4j;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-19
 * @Description : SrpmsProjectTask服务实现类
 * @Modified :
 */
@Service
@Slf4j
@Transactional
public class SrpmsProjectTaskServiceImpl extends ServiceImpl<SrpmsProjectTaskMapper, SrpmsProjectTask>
		implements ISrpmsProjectTaskService {

	@Autowired
	private SrpmsProjectTaskMapper srpmsProjectTaskMapper;

	@Autowired
	private ISrpmsProjectService projectService;

	@Override
	public IPage<SrpmsProjectTask> selectPage(SrpmsProjectTaskQueryForm queryForm) {
		QueryWrapper<SrpmsProjectTask> queryWrapper = new QueryWrapper<SrpmsProjectTask>();
		// getQueryWrapper(queryWrapper,queryForm);
		return srpmsProjectTaskMapper.selectPage(
				new Page<SrpmsProjectTask>(queryForm.getCurrentPage(), queryForm.getPageSize()), queryWrapper);

	}

	@Override
	public List<SrpmsProjectTask> selectList(SrpmsProjectTaskQueryForm queryForm) {
		QueryWrapper<SrpmsProjectTask> queryWrapper = new QueryWrapper<SrpmsProjectTask>();
		// getQueryWrapper(queryWrapper,queryForm);
		return srpmsProjectTaskMapper.selectList(queryWrapper);
	}

	@Override
	public void cleanAndSaveTask(List<SrpmsProjectTaskVo> taskVoList, TaskCategoryEnums categoryEnum, Long projectId) {
		if (categoryEnum == null)
			return;
		UpdateWrapper<SrpmsProjectTask> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq(SrpmsProjectTask.TASK_CATEGORY, categoryEnum.getCode());
		updateWrapper.eq(SrpmsProjectTask.PROJECT_ID, projectId);
		this.remove(updateWrapper);

		saveTask(taskVoList, categoryEnum, projectId);
	}

	public static void main(String[] args) {
		System.out.println(UUIDUtil.getRandom32PK());
	}
	@Override
	public void saveTask(List<SrpmsProjectTaskVo> taskVoList, TaskCategoryEnums categoryEnum, Long projectId) {
		if (taskVoList == null) {
			return;
		}
		List<SrpmsProjectTask> taskEntityList = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(taskVoList)) {
			SrpmsProject project = projectService.getById(projectId);
			for (int i = 0; i < taskVoList.size(); i++) {
				SrpmsProjectTask taskEntity = JSONObject.parseObject(JSONObject.toJSONString(taskVoList.get(i)), SrpmsProjectTask.class);
				taskEntity.setId(null);
				taskEntity.setTaskCategory(categoryEnum.getCode());
				taskEntity.setProjectId(projectId);
				if (taskEntity.getTaskYear() != null && taskEntity.getTaskYear().length() > 4) {
					taskEntity.setTaskYear(taskEntity.getTaskYear().substring(0, 4));
				}
				if (null != project) {
					String projectNum = project.getProjectNum();
					if (StringUtils.isBlank(projectNum)) {
						projectNum = String.valueOf(projectId);
					}
					if (i < 9) {
						taskEntity.setProjectTaskNum(projectNum + "-0" + (i + 1));
					} else {
						taskEntity.setProjectTaskNum(projectNum + "-" + (i + 1));
					}
				}

				taskEntityList.add(taskEntity);
			}
//			for (SrpmsProjectTaskVo taskVo : taskVoList) {
//				SrpmsProjectTask taskEntity = JSONObject.parseObject(JSONObject.toJSONString(taskVo),
//						SrpmsProjectTask.class);
//				taskEntity.setId(null);
//				taskEntity.setTaskCategory(categoryEnum.getCode());
//				taskEntity.setProjectId(projectId);
//				if (taskEntity.getTaskYear() != null && taskEntity.getTaskYear().length() > 4) {
//					taskEntity.setTaskYear(taskEntity.getTaskYear().substring(0, 4));
//				}
//				taskEntityList.add(taskEntity);
//			}
			this.saveBatch(taskEntityList);
		}

	}

	@Override
	public List<SrpmsProjectTaskVo> queryTaskListByTaskCategory(TaskCategoryEnums taskCategory, Long projectId) {
		List<SrpmsProjectTaskVo> voList = new ArrayList<>();
		if (taskCategory == null) {
			return voList;
		}
		QueryWrapper<SrpmsProjectTask> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq(SrpmsProjectTask.TASK_CATEGORY, taskCategory.getCode());
		queryWrapper.eq(SrpmsProjectTask.PROJECT_ID, projectId);
		queryWrapper.orderByAsc(SrpmsProjectTask.ID);
		List<SrpmsProjectTask> entityListR = srpmsProjectTaskMapper.selectList(queryWrapper);
		List<SrpmsProjectTask> entityList = new com.deloitte.platform.common.core.util.BeanUtils<SrpmsProjectTask>().copyObjs(entityListR, SrpmsProjectTask.class);
		for (SrpmsProjectTask TaskEntity : entityList) {
			String joinPersonName = TaskEntity.getJoinPersonName();
			String joinPerson = TaskEntity.getJoinPerson();
			String joinPersonInfo = TaskEntity.getJoinPersonInfo();
			String leadPersonInfo = TaskEntity.getLeadPersonInfo();
			String budgetDetail = TaskEntity.getBudgetDetail();
			log.info(taskCategory.getCode() + "joinPersonName是" + joinPersonName);
			log.info(taskCategory.getCode() + "joinPerson是" + joinPerson);
			log.info(taskCategory.getCode() + "joinPersonInfo是" + joinPersonInfo);
			log.info(taskCategory.getCode() + "leadPersonInfo是" + leadPersonInfo);
			log.info(taskCategory.getCode() + "budgetDetail是" + budgetDetail);

			TaskEntity.setJoinPerson(null);
			TaskEntity.setJoinPersonName(null);
			TaskEntity.setJoinPersonInfo(null);
			TaskEntity.setLeadPersonInfo(null);
			TaskEntity.setBudgetDetail(null);

			SrpmsProjectTaskVo TaskVo = JSONObject.parseObject(JSON.toJSONBytes(TaskEntity), SrpmsProjectTaskVo.class);
			TaskVo.setJoinPerson(JSONArray.parseArray(joinPerson));
			TaskVo.setJoinPersonInfo(JSONArray.parseArray(joinPersonInfo));
			TaskVo.setJoinPersonName(JSONArray.parseArray(joinPersonName));
			TaskVo.setLeadPersonInfo(JSONObject.parseObject(leadPersonInfo));
			TaskVo.setBudgetDetail(JSONArray.parseArray(budgetDetail));

			StringBuffer sbJoinPersonName = new StringBuffer();
			JSONArray joinPersonNameInfo = TaskVo.getJoinPersonInfo();
			if (joinPersonNameInfo != null) {
				for (int j = 0; j < joinPersonNameInfo.size(); j++) {
					if (sbJoinPersonName.length() != 0) {
						sbJoinPersonName.append(",");
					}
					sbJoinPersonName.append(joinPersonNameInfo.getJSONObject(j).getString("personName"));
				}
			}
			if (TaskVo.getLeadPersonInfo() != null) {
				TaskVo.setLeadPersonName(TaskVo.getLeadPersonInfo().getString("personName"));
			}
			TaskVo.setJoinPersonNameStr(sbJoinPersonName.toString());
			log.info("TaskVo是" + JSON.toJSONString(TaskVo));
			voList.add(TaskVo);
		}
		return voList;
	}

	/**
	 * 通用查询
	 * 
	 * @param queryWrapper,queryForm
	 * @return public QueryWrapper<SrpmsProjectTask>
	 *         getQueryWrapper(QueryWrapper<SrpmsProjectTask>
	 *         queryWrapper,BaseQueryForm<SrpmsProjectTaskQueryParam> queryForm){
	 *         //条件拼接 if(StringUtils.isNotBlank(srpmsProjectTask.getProjectId())){
	 *         queryWrapper.like(SrpmsProjectTask.PROJECT_ID,srpmsProjectTask.getProjectId());
	 *         } if(StringUtils.isNotBlank(srpmsProjectTask.getTaskName())){
	 *         queryWrapper.like(SrpmsProjectTask.TASK_NAME,srpmsProjectTask.getTaskName());
	 *         } if(StringUtils.isNotBlank(srpmsProjectTask.getTaskCategory())){
	 *         queryWrapper.like(SrpmsProjectTask.TASK_CATEGORY,srpmsProjectTask.getTaskCategory());
	 *         } if(StringUtils.isNotBlank(srpmsProjectTask.getTaskYear())){
	 *         queryWrapper.like(SrpmsProjectTask.TASK_YEAR,srpmsProjectTask.getTaskYear());
	 *         } if(StringUtils.isNotBlank(srpmsProjectTask.getSerial())){
	 *         queryWrapper.like(SrpmsProjectTask.SERIAL,srpmsProjectTask.getSerial());
	 *         } if(StringUtils.isNotBlank(srpmsProjectTask.getTaskTargetYear())){
	 *         queryWrapper.like(SrpmsProjectTask.TASK_TARGET_YEAR,srpmsProjectTask.getTaskTargetYear());
	 *         } if(StringUtils.isNotBlank(srpmsProjectTask.getImportantPoint())){
	 *         queryWrapper.like(SrpmsProjectTask.IMPORTANT_POINT,srpmsProjectTask.getImportantPoint());
	 *         } if(StringUtils.isNotBlank(srpmsProjectTask.getTaskContent())){
	 *         queryWrapper.like(SrpmsProjectTask.TASK_CONTENT,srpmsProjectTask.getTaskContent());
	 *         } if(StringUtils.isNotBlank(srpmsProjectTask.getThreeYearTarget())){
	 *         queryWrapper.like(SrpmsProjectTask.THREE_YEAR_TARGET,srpmsProjectTask.getThreeYearTarget());
	 *         } if(StringUtils.isNotBlank(srpmsProjectTask.getTargetPerYear())){
	 *         queryWrapper.like(SrpmsProjectTask.TARGET_PER_YEAR,srpmsProjectTask.getTargetPerYear());
	 *         } if(StringUtils.isNotBlank(srpmsProjectTask.getLeadPersonId())){
	 *         queryWrapper.like(SrpmsProjectTask.LEAD_PERSON_ID,srpmsProjectTask.getLeadPersonId());
	 *         } if(StringUtils.isNotBlank(srpmsProjectTask.getJoinPersonId())){
	 *         queryWrapper.like(SrpmsProjectTask.JOIN_PERSON_ID,srpmsProjectTask.getJoinPersonId());
	 *         } if(StringUtils.isNotBlank(srpmsProjectTask.getBudgetAllotRatio())){
	 *         queryWrapper.like(SrpmsProjectTask.BUDGET_ALLOT_RATIO,srpmsProjectTask.getBudgetAllotRatio());
	 *         } if(StringUtils.isNotBlank(srpmsProjectTask.getDeptName())){
	 *         queryWrapper.like(SrpmsProjectTask.DEPT_NAME,srpmsProjectTask.getDeptName());
	 *         }
	 *         if(StringUtils.isNotBlank(srpmsProjectTask.getGroupLeaderMember())){
	 *         queryWrapper.like(SrpmsProjectTask.GROUP_LEADER_MEMBER,srpmsProjectTask.getGroupLeaderMember());
	 *         } if(StringUtils.isNotBlank(srpmsProjectTask.getCreateTime())){
	 *         queryWrapper.like(SrpmsProjectTask.CREATE_TIME,srpmsProjectTask.getCreateTime());
	 *         } if(StringUtils.isNotBlank(srpmsProjectTask.getCreateBy())){
	 *         queryWrapper.like(SrpmsProjectTask.CREATE_BY,srpmsProjectTask.getCreateBy());
	 *         } if(StringUtils.isNotBlank(srpmsProjectTask.getUpdateTime())){
	 *         queryWrapper.like(SrpmsProjectTask.UPDATE_TIME,srpmsProjectTask.getUpdateTime());
	 *         } if(StringUtils.isNotBlank(srpmsProjectTask.getUpdateBy())){
	 *         queryWrapper.like(SrpmsProjectTask.UPDATE_BY,srpmsProjectTask.getUpdateBy());
	 *         } return queryWrapper; }
	 */
}
