package com.deloitte.services.srpmp.project.update.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskAcademyForm;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskAcademyVo;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskReformForm;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskReformVo;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskSchStudentForm;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskSchStudentVo;
import com.deloitte.services.srpmp.common.entity.SrpmsCommonNclob;
import com.deloitte.services.srpmp.common.enums.ProjectCategoryEnums;
import com.deloitte.services.srpmp.common.service.ISrpmsCommonNclobService;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectTask;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectTaskService;
import com.deloitte.services.srpmp.project.task.entity.SrpmsProjectTaskInn;
import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskAcademyService;
import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskInnService;
import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskReformService;
import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskSchStudentService;
import com.deloitte.services.srpmp.project.update.constant.ProjectContentConstant;
import com.deloitte.services.srpmp.project.update.entity.SrpmsProjectUpdate;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author : pengchao
 * @Date : Create in 2019-04-01
 * @Description : SrpmsProjectUpdateContent服务实现类
 * @Modified :
 */
@Service
@Slf4j
@Transactional
public class SrpmsProjectUpdateContentServiceImpl implements ProjectContentConstant {

	@Autowired
	private ISrpmsProjectTaskInnService taskInnService;

	@Autowired
	private ISrpmsProjectTaskService taskService;

	@Autowired
	private ISrpmsProjectTaskAcademyService taskAcademyService;

	@Autowired
	private ISrpmsProjectTaskReformService taskReformService;

	@Autowired
	private ISrpmsProjectTaskSchStudentService taskSchStudentService;

	@Autowired
	ISrpmsCommonNclobService nclobService;

	@Autowired
	private ISrpmsProjectService projectService;

	public JSONArray select(SrpmsProject project) {

		log.info("获取到的项目信息是：" + JSON.toJSONString(project));

		long projectId = project.getId();

		String projectType = project.getProjectType();
		JSONObject taskVoJson = null;
		JSONArray taskPreYearArr = null;

		if (projectType.equals(ProjectCategoryEnums.INNOVATE_BCOO.getHeader())
				|| projectType.equals(ProjectCategoryEnums.INNOVATE_COO.getHeader())
				|| projectType.equals(ProjectCategoryEnums.INNOVATE_PRE.getHeader())) {

			log.info("该项目是创新工程，开始获取任务书相关信息");
			taskVoJson = taskInnService.queryById(projectId);
			log.info("获取到的项目任务书信息是：" + taskVoJson.toJSONString());

			taskPreYearArr = taskVoJson.getJSONArray("taskPreYearList");

		} else if (projectType.equals(ProjectCategoryEnums.ACADEMY.getHeader())) {

			log.info("该项目是院基科费，开始获取任务书相关信息");
			SrpmsProjectTaskAcademyVo vo = taskAcademyService.queryById(projectId);
			log.info("获取到的项目任务书信息是：" + JSON.toJSONString(vo));

			taskPreYearArr = vo.getResearchYearPlan();

			taskVoJson = new JSONObject();
			taskVoJson.put("researchContentMain", vo.getContentTargetProblem());
			taskVoJson.put("researchTarget", vo.getProjectExpectTarget());
			taskVoJson.put("projectTechnicalInnovation", vo.getProjectTechnicalInnovation());
			taskVoJson.put("taskMasterMethod", vo.getAssessmentIndicators());

		} else if (projectType.equals(ProjectCategoryEnums.STRUCTURAL_REFORM.getHeader())) {// 科技体制改革

			log.info("该项目是科技体制改革，开始获取任务书相关信息");
			SrpmsProjectTaskReformVo vo = taskReformService.queryById(projectId);
			log.info("获取到的项目任务书信息是：" + JSON.toJSONString(vo));

			taskPreYearArr = vo.getResearchYearPlan();

			taskVoJson = new JSONObject();
			taskVoJson.put("researchContentMain", vo.getContentTargetProblem());
			taskVoJson.put("researchTarget", vo.getProjectExpectTarget());
			taskVoJson.put("projectTechnicalInnovation", vo.getProjectTechnicalInnovation());
			taskVoJson.put("taskMasterMethod", vo.getAssessmentIndicators());

		} else if (projectType.equals(ProjectCategoryEnums.SCHOOL_STU.getHeader())
				|| projectType.equals(ProjectCategoryEnums.SCHOOL_TEACH.getHeader())) {

			log.info("该项目是校基科费，开始获取任务书相关信息");
			SrpmsProjectTaskSchStudentVo vo = taskSchStudentService.queryById(projectId);
			log.info("获取到的项目任务书信息是：" + JSON.toJSONString(vo));

			taskPreYearArr = vo.getResearchYearPlan();
			taskVoJson = new JSONObject();
			taskVoJson.put("resultAssessmentIndicators", vo.getResultAssessmentIndicators());
		}

		log.info("该项目类型任务书的内容列表信息是：" + taskVoJson.toJSONString());

		JSONArray taskPreYearNameArr = new JSONArray();

		if (projectType.equals(ProjectCategoryEnums.INNOVATE_BCOO.getHeader())
				|| projectType.equals(ProjectCategoryEnums.INNOVATE_COO.getHeader())) {

			for (int i = 0; i < taskPreYearArr.size(); i++) {
				JSONObject item = taskPreYearArr.getJSONObject(i);
				String key = item.getString("id");
				JSONObject nameItem = new JSONObject();
				nameItem.put("key", "taskTargetYear" + key);
				StringBuffer sbName = new StringBuffer();
				sbName.append(item.getString("taskYear"));
				sbName.append("年度计划-预期目标及考核指标");
				nameItem.put("name", sbName.toString());
				if (item.getString("taskTargetYear") != null && item.getString("taskTargetYear").length() != 0) {
					nameItem.put("value", item.getString("taskTargetYear"));
				} else {
					nameItem.put("value", " ");
				}

				taskPreYearNameArr.add(nameItem);

				nameItem = new JSONObject();
				nameItem.put("key", "examMode" + key);
				sbName = new StringBuffer();
				sbName.append(item.getString("taskYear"));
				sbName.append("年度计划-考核方式");
				nameItem.put("name", sbName.toString());
				if (item.getString("examMode") != null && item.getString("examMode").length() != 0) {
					nameItem.put("value", item.getString("examMode"));
				} else {
					nameItem.put("value", " ");
				}
				taskPreYearNameArr.add(nameItem);
			}

		} else if (projectType.equals(ProjectCategoryEnums.SCHOOL_STU.getHeader())
				|| projectType.equals(ProjectCategoryEnums.SCHOOL_TEACH.getHeader())
				|| projectType.equals(ProjectCategoryEnums.STRUCTURAL_REFORM.getHeader())
				|| projectType.equals(ProjectCategoryEnums.ACADEMY.getHeader())) {

			if (taskPreYearArr != null) {
				for (int i = 0; i < taskPreYearArr.size(); i++) {
					JSONObject item = taskPreYearArr.getJSONObject(i);
					JSONArray timeArr = item.getJSONArray("time");
					String timeStart = null;
					String timeEnd = null;
					if (timeArr.getString(0).length() == 19) {
						timeStart = timeArr.getString(0).substring(0, 10);
					} else {
						timeStart = timeArr.getString(0);
					}

					if (timeArr.getString(1).length() == 19) {
						timeEnd = timeArr.getString(1).substring(0, 10);
					} else {
						timeEnd = timeArr.getString(1);
					}

					JSONObject nameItem = new JSONObject();
					nameItem.put("key", "taskPreYear" + i);
					StringBuffer sbName = new StringBuffer();
					sbName.append(timeStart);
					sbName.append("至");
					sbName.append(timeEnd);
					sbName.append("年度计划-研究计划及指标完成情况");
					nameItem.put("name", sbName.toString());
					if (item.getString("text") != null && item.getString("text").length() != 0) {
						nameItem.put("value", item.getString("text"));
					} else {
						nameItem.put("value", " ");
					}
					taskPreYearNameArr.add(nameItem);
				}
			}
		} else if (projectType.equals(ProjectCategoryEnums.INNOVATE_PRE.getHeader())) {

			for (int i = 0; i < taskPreYearArr.size(); i++) {
				JSONObject item = taskPreYearArr.getJSONObject(i);
				String key = item.getString("id");
				JSONObject nameItem = new JSONObject();
				nameItem.put("key", "firstYearTarget" + key);
				StringBuffer sbName = new StringBuffer();
				sbName.append(item.getString("taskYear"));
				sbName.append("年度计划-预期目标及考核指标");
				nameItem.put("name", sbName.toString());
				if (item.getString("firstYearTarget") != null && item.getString("firstYearTarget").length() != 0) {
					nameItem.put("value", item.getString("firstYearTarget"));
				} else {
					nameItem.put("value", " ");
				}
				taskPreYearNameArr.add(nameItem);

				nameItem = new JSONObject();
				nameItem.put("key", "examMode" + key);
				sbName = new StringBuffer();
				sbName.append(item.getString("taskYear"));
				sbName.append("年度计划-考核方式");
				nameItem.put("name", sbName.toString());
				if (item.getString("examMode") != null && item.getString("examMode").length() != 0) {
					nameItem.put("value", item.getString("examMode"));
				} else {
					nameItem.put("value", " ");
				}
				
				taskPreYearNameArr.add(nameItem);
			}
		}

		JSONArray details = new JSONArray();

		JSONObject jsonTemp = new JSONObject();
		jsonTemp.put("key", "projectName");
		jsonTemp.put("name", "项目名称");
		jsonTemp.put("value", project.getProjectName());
		details.add(jsonTemp);

		String[] keyArr = this.getKeyArrByProjectType(projectType);
		String[] nameArr = this.getNameArrByProjectType(projectType);
		for (int i = 0; i < keyArr.length; i++) {
			String key = keyArr[i];
			if (taskVoJson.containsKey(key)) {
				jsonTemp = new JSONObject();
				jsonTemp.put("key", key);
				jsonTemp.put("name", nameArr[i]);
				if (taskVoJson.getString(key) != null && taskVoJson.getString(key).length() != 0 ) {
					jsonTemp.put("value", taskVoJson.getString(key));
				} else {
					jsonTemp.put("value", " ");
				}

				details.add(jsonTemp);
			}
		}

		details.addAll(taskPreYearNameArr);

		JSONArray newDdetails = new JSONArray();
		for (int i = 0; i < details.size(); i++) {
			JSONObject json = details.getJSONObject(i);
			json.put("sortKey", i);
			newDdetails.add(json);
		}
		log.info("该项目类型可以改变的内容列表信息是：" + newDdetails.toJSONString());
		return details;
	}

	public void save(SrpmsProject srpmsProject, SrpmsProjectUpdate update) {

		log.info("输入参数srpmsProject值是：" + JSON.toJSONString(srpmsProject));

		String projectType = srpmsProject.getProjectType();

		Long projectId = srpmsProject.getId();
		long clobId = Long.parseLong(update.getNewValue());
		SrpmsCommonNclob clobEntity = nclobService.getById(clobId);
		JSONArray contentList = JSONArray.parseArray(clobEntity.getContent());

		for (int i = 0; i < contentList.size(); i++) {
			JSONObject json = contentList.getJSONObject(i);
			if ("projectName".equals(json.getString("key")) && json.getString("updateFlg") != null
					&& json.getString("updateFlg").equals("1")) {
				srpmsProject.setProjectName(json.getString("value"));
				projectService.updateById(srpmsProject);
			}
		}
		if (projectType.equals(ProjectCategoryEnums.INNOVATE_BCOO.getHeader())
				|| projectType.equals(ProjectCategoryEnums.INNOVATE_COO.getHeader())) {

			JSONObject taskBookJson = taskInnService.queryById(projectId);
			for (int i = 0; i < contentList.size(); i++) {
				JSONObject json = contentList.getJSONObject(i);
				if (!json.getString("key").contains("taskTargetYear")
						&& !json.getString("key").contains("examMode") 
						&& json.getString("updateFlg") != null
						&& json.getString("updateFlg").equals("1")) {
					taskBookJson.put(json.getString("key"), json.getString("value"));
				}
			}

			SrpmsProjectTaskInn taskBookVo = JSONObject.parseObject(taskBookJson.toJSONString(),
					SrpmsProjectTaskInn.class);
			taskInnService.updateById(taskBookVo);

			List<SrpmsProjectTask> taskVoList = new ArrayList<SrpmsProjectTask>();
			for (int i = 0; i < contentList.size(); i++) {
				JSONObject json = contentList.getJSONObject(i);
				String key = json.getString("key");
				if (key.contains("taskTargetYear") && json.getString("updateFlg") != null
						&& json.getString("updateFlg").equals("1")) {

					boolean hasFlg = false;
					key = key.replaceAll("taskTargetYear", "");
					for (int j = 0; j < taskVoList.size(); j++) {
						if (taskVoList.get(j).getId().toString().equals(key)) {
							taskVoList.get(j).setTaskRemark(json.getString("value"));
							hasFlg = true;
						}
					}
					if (!hasFlg) {
						SrpmsProjectTask taskVo = taskService.getById(key);
						taskVo.setTaskTargetYear(json.getString("value"));
						taskVoList.add(taskVo);
					}
				}

				if (key.contains("examMode") && json.getString("updateFlg") != null
						&& json.getString("updateFlg").equals("1")) {

					boolean hasFlg = false;
					key = key.replaceAll("examMode", "");
					for (int j = 0; j < taskVoList.size(); j++) {
						if (taskVoList.get(j).getId().toString().equals(key)) {
							taskVoList.get(j).setExamMode(json.getString("value"));
							hasFlg = true;
						}
					}
					if (!hasFlg) {
						SrpmsProjectTask taskVo = taskService.getById(key);
						taskVo.setExamMode(json.getString("value"));
						taskVoList.add(taskVo);
					}
				}
			}

			for (int i = 0; i < taskVoList.size(); i++) {
				taskService.updateById(taskVoList.get(i));
			}

		} else if (projectType.equals(ProjectCategoryEnums.INNOVATE_PRE.getHeader())) {

			JSONObject taskBookJson = taskInnService.queryById(projectId);
			for (int i = 0; i < contentList.size(); i++) {
				JSONObject json = contentList.getJSONObject(i);
				if (!json.getString("key").contains("firstYearTarget")
						&& !json.getString("key").contains("examMode") 
						&& json.getString("updateFlg") != null
						&& json.getString("updateFlg").equals("1")) {
					taskBookJson.put(json.getString("key"), json.getString("value"));
				}
			}

			SrpmsProjectTaskInn taskBookVo = JSONObject.parseObject(taskBookJson.toJSONString(),
					SrpmsProjectTaskInn.class);
			taskInnService.updateById(taskBookVo);

			List<SrpmsProjectTask> taskVoList = new ArrayList<SrpmsProjectTask>();
			for (int i = 0; i < contentList.size(); i++) {
				JSONObject json = contentList.getJSONObject(i);
				String key = json.getString("key");
				if (key.contains("firstYearTarget") && json.getString("updateFlg") != null
						&& json.getString("updateFlg").equals("1")) {

					boolean hasFlg = false;
					key = key.replaceAll("firstYearTarget", "");
					for (int j = 0; j < taskVoList.size(); j++) {
						if (taskVoList.get(j).getId().toString().equals(key)) {
							taskVoList.get(j).setTaskRemark(json.getString("value"));
							hasFlg = true;
						}
					}
					if (!hasFlg) {
						SrpmsProjectTask taskVo = taskService.getById(key);
						taskVo.setFirstYearTarget(json.getString("value"));
						taskVoList.add(taskVo);
					}
				}

				if (key.contains("examMode") && json.getString("updateFlg") != null
						&& json.getString("updateFlg").equals("1")) {

					boolean hasFlg = false;
					key = key.replaceAll("examMode", "");
					for (int j = 0; j < taskVoList.size(); j++) {
						if (taskVoList.get(j).getId().toString().equals(key)) {
							taskVoList.get(j).setExamMode(json.getString("value"));
							hasFlg = true;
						}
					}
					if (!hasFlg) {
						SrpmsProjectTask taskVo = taskService.getById(key);
						taskVo.setExamMode(json.getString("value"));
						taskVoList.add(taskVo);
					}
				}
			}

			for (int i = 0; i < taskVoList.size(); i++) {
				taskService.updateById(taskVoList.get(i));
			}

		} else if (projectType.equals(ProjectCategoryEnums.ACADEMY.getHeader())) {

			SrpmsProjectTaskAcademyVo vo = taskAcademyService.queryById(projectId);
			log.info("获取到的项目任务书信息是：" + JSON.toJSONString(vo));

			SrpmsProjectTaskAcademyForm taskForm = new SrpmsProjectTaskAcademyForm();

			taskForm.setId(projectId);

			JSONObject taskVoJson = new JSONObject();

			JSONArray taskPreYearArr = vo.getResearchYearPlan();

			for (int i = 0; i < contentList.size(); i++) {
				JSONObject json = contentList.getJSONObject(i);
				String key = json.getString("key");
				if (key.contains("taskPreYear") && json.getString("updateFlg") != null
						&& json.getString("updateFlg").equals("1")) {

					key = key.replaceAll("taskPreYear", "");

					int index = Integer.parseInt(key);
					JSONObject tempJson = taskPreYearArr.getJSONObject(index);
					tempJson.put("text", json.getString("value"));
					taskPreYearArr.set(index, tempJson);
				} else {
					taskVoJson.put(key, json.getString("value"));
				}
			}

			taskForm.setContentTargetProblem(taskVoJson.getString("researchContentMain"));
			taskForm.setProjectExpectTarget(taskVoJson.getString("researchTarget"));
			taskForm.setProjectTechnicalInnovation(taskVoJson.getString("projectTechnicalInnovation"));
			taskForm.setAssessmentIndicators(taskVoJson.getString("taskMasterMethod"));

			taskForm.setResearchYearPlan(taskPreYearArr);
			taskAcademyService.saveSrpmsProjectTask(taskForm, true);

		} else if (projectType.equals(ProjectCategoryEnums.STRUCTURAL_REFORM.getHeader())) {

			SrpmsProjectTaskReformVo vo = taskReformService.queryById(projectId);

			SrpmsProjectTaskReformForm taskForm = new SrpmsProjectTaskReformForm();

			taskForm.setId(projectId);

			JSONObject taskVoJson = new JSONObject();

			JSONArray taskPreYearArr = vo.getResearchYearPlan();

			for (int i = 0; i < contentList.size(); i++) {
				JSONObject json = contentList.getJSONObject(i);
				String key = json.getString("key");
				if (key.contains("taskPreYear") && json.getString("updateFlg") != null
						&& json.getString("updateFlg").equals("1")) {

					key = key.replaceAll("taskPreYear", "");

					int index = Integer.parseInt(key);
					JSONObject tempJson = taskPreYearArr.getJSONObject(index);
					tempJson.put("text", json.getString("value"));
					taskPreYearArr.set(index, tempJson);
				} else {
					taskVoJson.put(key, json.getString("value"));
				}
			}

			taskForm.setContentTargetProblem(taskVoJson.getString("researchContentMain"));
			taskForm.setProjectExpectTarget(taskVoJson.getString("researchTarget"));
			taskForm.setProjectTechnicalInnovation(taskVoJson.getString("projectTechnicalInnovation"));
			taskForm.setAssessmentIndicators(taskVoJson.getString("taskMasterMethod"));

			taskForm.setResearchYearPlan(taskPreYearArr);
			taskReformService.saveSrpmsProjectTask(taskForm, true);

		} else if (projectType.equals(ProjectCategoryEnums.SCHOOL_STU.getHeader())
				|| (projectType.equals(ProjectCategoryEnums.SCHOOL_TEACH.getHeader()))) {

			log.info("该项目是校基科费，开始获取任务书相关信息");
			SrpmsProjectTaskSchStudentVo vo = taskSchStudentService.queryById(projectId);
			log.info("获取到的项目任务书信息是：" + JSON.toJSONString(vo));

			SrpmsProjectTaskSchStudentForm taskForm = new SrpmsProjectTaskSchStudentForm();
			JSONArray taskPreYearArr = vo.getResearchYearPlan();
			JSONObject taskVoJson = new JSONObject();

			for (int i = 0; i < contentList.size(); i++) {
				JSONObject json = contentList.getJSONObject(i);
				String key = json.getString("key");
				if (key.contains("taskPreYear") && json.getString("updateFlg") != null
						&& json.getString("updateFlg").equals("1")) {

					key = key.replaceAll("taskPreYear", "");

					int index = Integer.parseInt(key);
					JSONObject tempJson = taskPreYearArr.getJSONObject(index);
					tempJson.put("text", json.getString("value"));
					taskPreYearArr.set(index, tempJson);
				} else {
					taskVoJson.put(key, json.getString("value"));
				}
			}
			taskForm.setResultAssessmentIndicators(taskVoJson.getString("resultAssessmentIndicators"));

			taskForm.setResearchYearPlan(taskPreYearArr);

			taskSchStudentService.saveSrpmsProjectTask(taskForm, true);
		}
		log.info("输入参数update值是：" + JSON.toJSONString(update));

	}

	public String[] getKeyArrByProjectType(String projectType) {
		log.info("输入projectType值是：" + projectType);
		String[] keyArr = null;
		if (projectType.equals(ProjectCategoryEnums.INNOVATE_BCOO.getHeader())
				|| projectType.equals(ProjectCategoryEnums.INNOVATE_COO.getHeader())) {
			keyArr = KEY_ARR_1;
		} else if (projectType.equals(ProjectCategoryEnums.INNOVATE_PRE.getHeader())) {
			keyArr = KEY_ARR_2;
		} else if (projectType.equals(ProjectCategoryEnums.ACADEMY.getHeader())
				|| projectType.equals(ProjectCategoryEnums.STRUCTURAL_REFORM.getHeader())) {
			keyArr = KEY_ARR_3;
		} else if (projectType.equals(ProjectCategoryEnums.SCHOOL_STU.getHeader())
				|| projectType.equals(ProjectCategoryEnums.SCHOOL_TEACH.getHeader())) {
			keyArr = KEY_ARR_4;
		} else {
			keyArr = KEY_ARR_10;
		}
		log.info("输出是：" + JSON.toJSONString(keyArr));
		return keyArr;
	}

	public String[] getNameArrByProjectType(String projectType) {
		log.info("输入projectType值是：" + projectType);
		String[] nameArr = null;
		if (projectType.equals(ProjectCategoryEnums.INNOVATE_BCOO.getHeader())
				|| projectType.equals(ProjectCategoryEnums.INNOVATE_COO.getHeader())) {
			nameArr = NAME_ARR_1;
		} else if (projectType.equals(ProjectCategoryEnums.INNOVATE_PRE.getHeader())) {
			nameArr = NAME_ARR_2;
		} else if (projectType.equals(ProjectCategoryEnums.ACADEMY.getHeader())
				|| projectType.equals(ProjectCategoryEnums.STRUCTURAL_REFORM.getHeader())) {
			nameArr = NAME_ARR_3;
		} else if (projectType.equals(ProjectCategoryEnums.SCHOOL_STU.getHeader())
				|| projectType.equals(ProjectCategoryEnums.SCHOOL_TEACH.getHeader())) {
			nameArr = NAME_ARR_4;
		} else {
			nameArr = NAME_ARR_10;
		}
		log.info("输出是：" + JSON.toJSONString(nameArr));
		return nameArr;
	}
}