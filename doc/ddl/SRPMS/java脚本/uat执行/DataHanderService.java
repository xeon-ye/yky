package com.deloitte.services.srpmp.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.services.srpmp.common.enums.TaskCategoryEnums;
import com.deloitte.services.srpmp.project.apply.service.impl.SrpmsProjectApplyInnCommonServiceImpl;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectPersonJoin;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectTask;
import com.deloitte.services.srpmp.project.base.mapper.SrpmsProjectPersonJoinMapper;
import com.deloitte.services.srpmp.project.base.mapper.SrpmsProjectTaskMapper;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectTaskService;

import lombok.extern.slf4j.Slf4j;

/**
 * 文件转化为pdf 预览
 */
@Service
@Slf4j
@Transactional
public class DataHanderService {

	@Autowired
	public SrpmsProjectPersonJoinMapper personJoinMapper;

	@Autowired
	public ISrpmsProjectTaskService taskService;

	@Autowired
	public SrpmsProjectTaskMapper taskMapper;

	@Autowired
	public SrpmsProjectApplyInnCommonServiceImpl commonService;

	public void doWork1(SrpmsProject project) {

		long projectId = project.getId();

		QueryWrapper<SrpmsProjectTask> queryWrapper = new QueryWrapper<SrpmsProjectTask>();

		queryWrapper.eq(SrpmsProjectTask.PROJECT_ID, projectId);

		queryWrapper.eq(SrpmsProjectTask.TASK_CATEGORY, "02");

		List<SrpmsProjectTask> taskList = taskMapper.selectList(queryWrapper);

		log.info("taskList总长度是" + taskList.size());
		QueryWrapper<SrpmsProjectPersonJoin> queryWrapper2 = new QueryWrapper<SrpmsProjectPersonJoin>();

		queryWrapper2.eq(SrpmsProjectPersonJoin.PROJECT_ID, projectId);

		queryWrapper2.eq(SrpmsProjectPersonJoin.JOIN_WAY, "01");

		List<SrpmsProjectPersonJoin> personList = personJoinMapper.selectList(queryWrapper2);
		log.info("personList总长度是" + personList.size());

		for (int i = 0; i < taskList.size(); i++) {
			SrpmsProjectTask task = taskList.get(i);

			for (int j = 0; j < personList.size(); j++) {
				SrpmsProjectPersonJoin person = personList.get(j);

				if (task.getLeadPersonName() != null && task.getLeadPersonName().length() != 0) {
					if (task.getLeadPersonName().equals(person.getPersonName())) {
						task.setLeadPerson(person.getPersonId() + "");
						JSONObject taskLeadPerson = new JSONObject();
						taskLeadPerson.put("personId", person.getPersonId() + "");
						taskLeadPerson.put("personName", person.getPersonName());
						taskLeadPerson.put("deptCode", person.getDeptCode());
						taskLeadPerson.put("deptName", person.getDeptName());
						task.setLeadPersonInfo(taskLeadPerson.toJSONString());
					}
				}
			}

			if (task.getJoinPersonName() != null && task.getJoinPersonName().length() != 2) {

				JSONArray joinPPerson = JSONArray.parseArray(task.getJoinPersonName());
				JSONArray joinPPersonId = new JSONArray();
				JSONArray joinPPersonInfo = new JSONArray();
				for (int j = 0; j < joinPPerson.size(); j++) {
					String name = joinPPerson.getString(j);

					for (int k = 0; k < personList.size(); k++) {
						SrpmsProjectPersonJoin person = personList.get(k);

						if (name.equals(person.getPersonName())) {
							joinPPersonId.add(person.getPersonId());

							JSONObject joinPersonJson = new JSONObject();
							joinPersonJson.put("personId", person.getPersonId() + "");
							joinPersonJson.put("personName", person.getPersonName());
							joinPersonJson.put("deptCode", person.getDeptCode());
							joinPersonJson.put("deptName", person.getDeptName());
							joinPPersonInfo.add(joinPersonJson);
						}

					}
				}

				task.setJoinPerson(joinPPersonId.toJSONString());
				task.setJoinPersonInfo(joinPPersonInfo.toJSONString());
			}

			log.info("要更新的vos是" + JSON.toJSONString(task));
			taskService.updateById(task);
		}

	}

	public void doWork2(SrpmsProject project) {

		long projectId = project.getId();

		QueryWrapper<SrpmsProjectTask> queryWrapper = new QueryWrapper<SrpmsProjectTask>();

		queryWrapper.eq(SrpmsProjectTask.PROJECT_ID, projectId);

		queryWrapper.eq(SrpmsProjectTask.TASK_CATEGORY, "12");

		List<SrpmsProjectTask> taskList = taskMapper.selectList(queryWrapper);

		log.info("taskList总长度是" + taskList.size());
		QueryWrapper<SrpmsProjectPersonJoin> queryWrapper2 = new QueryWrapper<SrpmsProjectPersonJoin>();

		queryWrapper2.eq(SrpmsProjectPersonJoin.PROJECT_ID, projectId);

		queryWrapper2.eq(SrpmsProjectPersonJoin.JOIN_WAY, "11");

		List<SrpmsProjectPersonJoin> personList = personJoinMapper.selectList(queryWrapper2);
		log.info("personList总长度是" + personList.size());

		for (int i = 0; i < taskList.size(); i++) {
			SrpmsProjectTask task = taskList.get(i);

			for (int j = 0; j < personList.size(); j++) {
				SrpmsProjectPersonJoin person = personList.get(j);

				if (task.getLeadPersonName() != null && task.getLeadPersonName().length() != 0) {
					if (task.getLeadPersonName().equals(person.getPersonName())) {
						task.setLeadPerson(person.getPersonId() + "");
						JSONObject taskLeadPerson = new JSONObject();
						taskLeadPerson.put("personId", person.getPersonId() + "");
						taskLeadPerson.put("personName", person.getPersonName());
						taskLeadPerson.put("deptCode", person.getDeptCode());
						taskLeadPerson.put("deptName", person.getDeptName());
						task.setLeadPersonInfo(taskLeadPerson.toJSONString());
					}
				}
			}

			if (task.getJoinPersonName() != null && task.getJoinPersonName().length() != 2) {

				JSONArray joinPPerson = JSONArray.parseArray(task.getJoinPersonName());
				JSONArray joinPPersonId = new JSONArray();
				JSONArray joinPPersonInfo = new JSONArray();
				for (int j = 0; j < joinPPerson.size(); j++) {
					String name = joinPPerson.getString(j);

					for (int k = 0; k < personList.size(); k++) {
						SrpmsProjectPersonJoin person = personList.get(k);

						if (name.equals(person.getPersonName())) {
							joinPPersonId.add(person.getPersonId());

							JSONObject joinPersonJson = new JSONObject();
							joinPersonJson.put("personId", person.getPersonId() + "");
							joinPersonJson.put("personName", person.getPersonName());
							joinPersonJson.put("deptCode", person.getDeptCode());
							joinPersonJson.put("deptName", person.getDeptName());
							joinPPersonInfo.add(joinPersonJson);
						}

					}
				}

				task.setJoinPerson(joinPPersonId.toJSONString());
				task.setJoinPersonInfo(joinPPersonInfo.toJSONString());
			}

			log.info("要更新的vos是" + JSON.toJSONString(task));
			taskService.updateById(task);
		}

	}

	public void doWork3(SrpmsProject project) {

		long projectId = project.getId();

		commonService.setTaskAndBudgetList(
				taskService.queryTaskListByTaskCategory(TaskCategoryEnums.APPLY_INNOVATE_TASK_DECOMPOSITION, projectId),
				projectId);
	}

	public void doWork4(SrpmsProject project) {

		long projectId = project.getId();

		commonService.setTaskAndBudgetList(
				taskService.queryTaskListByTaskCategory(TaskCategoryEnums.TASK_INNOVATE_TASK_DECOMPOSITION, projectId),
				projectId, true);
	}
}
