package com.deloitte.services.srpmp.project.update.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonJoinVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectTaskVo;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.common.entity.SrpmsCommonNclob;
import com.deloitte.services.srpmp.common.enums.PersonJoinWayEnums;
import com.deloitte.services.srpmp.common.enums.TaskCategoryEnums;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.common.service.ISrpmsCommonNclobService;
import com.deloitte.services.srpmp.project.apply.service.impl.SrpmsProjectApplyInnCommonServiceImpl;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectPersonJoinService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectTaskService;
import com.deloitte.services.srpmp.project.task.entity.SrpmsProjectTranLong;
import com.deloitte.services.srpmp.project.task.service.ISrpmsProjectTaskTranLongService;
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
public class SrpmsProjectUpdateMenberServiceImpl {

	@Autowired
	private ISrpmsProjectPersonJoinService projectPersonJoinService;

	@Autowired
	ISrpmsCommonNclobService nclobService;

	@Autowired
	ISrpmsProjectTaskTranLongService tranLongService;

	@Autowired
	private ISrpmsProjectTaskService taskService;

	@Autowired
	private SrpmsProjectApplyInnCommonServiceImpl commonService;

	public List<SrpmsProjectPersonJoinVo> select(List<SrpmsProjectTaskVo> taskList, SrpmsProject srpmsProject) {
		List<SrpmsProjectPersonJoinVo> oldMenberList = projectPersonJoinService
				.queryPersonJoinListByJoinWay(PersonJoinWayEnums.TASK_MAIN_MEMBER, srpmsProject.getId());

		String projectType = srpmsProject.getProjectType();
		if (SrpmsConstant.MODIFY_PRO_TYPE_10010101.equals(projectType)
				|| SrpmsConstant.MODIFY_PRO_TYPE_10010102.equals(projectType)) {

			List<SrpmsProjectPersonJoinVo> menberList = new ArrayList<SrpmsProjectPersonJoinVo>();
			for (int i = 0; i < oldMenberList.size(); i++) {
				if (oldMenberList.get(i).getBelongTask() != null
						&& oldMenberList.get(i).getBelongTask().length() != 0) {
					menberList.add(oldMenberList.get(i));
				}
			}
			for (int i = 0; i < menberList.size(); i++) {
				SrpmsProjectPersonJoinVo menber = menberList.get(i);
				menber.setPersonRole("否");
				for (int j = 0; j < taskList.size(); j++) {
					if (menber.getBelongTask().equals(taskList.get(j).getTaskName())
							&& Long.parseLong(taskList.get(j).getLeadPerson()) == menber.getPersonId().longValue()) {
						menber.setPersonRole("是");
					}
				}
			}
			return menberList;
		} else {
			return oldMenberList;
		}
	}

	public void checkOnSubmit(List<SrpmsProjectTaskVo> taskList, JSONArray oldMenberList) {
		StringBuffer errorMsg = new StringBuffer();
		errorMsg.append("<br/><br/>");
		JSONArray menberList = new JSONArray();

		// 去掉空行
		for (int j = 0; j < oldMenberList.size(); j++) {
			JSONObject menber = oldMenberList.getJSONObject(j);
			if (menber.getString("personName") != null && !"".equals(menber.getString("personName"))) {
				menberList.add(menber);
			}
		}
		// 检查所属研究任务和是否为负责人是否输入
		for (int j = 0; j < menberList.size(); j++) {
			JSONObject menber = menberList.getJSONObject(j);
			if (menber.getString("personRole") == null || "".equals(menber.getString("personRole"))) {
				errorMsg.append("参与人员【" + menber.getString("personName") + "】的是否为负责人没有输入。");
				throw new BaseException(SrpmsErrorType.INPUT_ERROR_9500, errorMsg.toString());
			}
			if (menber.getString("belongTask") == null || "".equals(menber.getString("belongTask"))) {
				errorMsg.append("参与人员【" + menber.getString("personName") + "】的所属研究任务没有输入。");
				throw new BaseException(SrpmsErrorType.INPUT_ERROR_9500, errorMsg.toString());
			}
		}

		// 检查每个任务是否有且仅有一个负责人
		for (int i = 0; i < taskList.size(); i++) {
			int leaderCount = 0;
			SrpmsProjectTaskVo task = taskList.get(i);
			for (int j = 0; j < menberList.size(); j++) {
				JSONObject menber = menberList.getJSONObject(j);
				if ("是".equals(menber.getString("personRole"))
						&& menber.getString("belongTask").equals(task.getTaskName())) {
					leaderCount++;
				}
			}
			if (leaderCount == 0) {
				errorMsg.append("任务【" + task.getTaskName() + "】的没有负责人。");
				throw new BaseException(SrpmsErrorType.INPUT_ERROR_9500, errorMsg.toString());
			}
			if (leaderCount > 1) {
				errorMsg.append("任务【" + task.getTaskName() + "】的有多个负责人。");
				throw new BaseException(SrpmsErrorType.INPUT_ERROR_9500, errorMsg.toString());
			}
		}
	}

	public JSONArray getMemberChangedList(JSONObject oldValue, JSONObject newValue, SrpmsProject srpmsProject) {

		log.info("新参与人员信息是：" + newValue.toJSONString());
		log.info("旧的人员参与信息是：" + oldValue.toJSONString());

		JSONArray oldMianMemberList = oldValue.getJSONArray("oldMianMemberList");
		JSONArray mianMemberList = newValue.getJSONArray("mianMemberList");

		JSONArray oldOutMemberList = oldValue.getJSONArray("oldOutMemberList");
		JSONArray OutMemberList = newValue.getJSONArray("outMemberList");

		JSONArray relust = null;

		if (SrpmsConstant.PROJECT_TYPE_1.equals(srpmsProject.getProjectFlag())) {
			relust = new JSONArray();
			JSONObject outMemberUpdate = this.getListDiff(oldOutMemberList, OutMemberList);

			if (outMemberUpdate != null) {
				outMemberUpdate.put("taskName", srpmsProject.getProjectName());
				outMemberUpdate.put("personRole", "院外参与人员");
				relust.add(outMemberUpdate);
			}

			JSONObject memberUpdate = this.getListDiff(oldMianMemberList, mianMemberList);

			if (memberUpdate != null) {
				memberUpdate.put("taskName", srpmsProject.getProjectName());
				memberUpdate.put("personRole", "院内参与人员");
				relust.add(memberUpdate);
			}

		} else {
			relust = this.getMemberChangedList(oldMianMemberList, mianMemberList, srpmsProject);
		}

		log.info("该项目的有变更的人员信息列表是：" + relust.toJSONString());
		return relust;
	}

	private JSONArray getMemberChangedList(JSONArray oldMianMemberList, JSONArray mianMemberList,
			SrpmsProject srpmsProject) {

		log.info("新参与人员信息是：" + mianMemberList.toJSONString());
		log.info("旧的人员参与信息是：" + oldMianMemberList.toJSONString());
		String projectType = srpmsProject.getProjectType();
		JSONArray relust = new JSONArray();

		JSONArray taskArr = new JSONArray();

		if (SrpmsConstant.MODIFY_PRO_TYPE_10010101.equals(projectType)
				|| SrpmsConstant.MODIFY_PRO_TYPE_10010102.equals(projectType)) {

			for (int i = 0; i < oldMianMemberList.size(); i++) {
				JSONObject oldMember = oldMianMemberList.getJSONObject(i);
				String taskName = oldMember.getString("belongTask");
				boolean addFlg = true;
				for (int j = 0; j < taskArr.size(); j++) {
					JSONObject task = taskArr.getJSONObject(j);
					if (task.getString("taskName").equals(taskName)) {
						addFlg = false;
						break;
					}
				}
				if (addFlg) {
					JSONObject task = new JSONObject();
					task.put("taskName", taskName);
					task.put("oldLeadPersonId", " ");
					task.put("leadPersonId", " ");
					task.put("oldLeadPersonName", " ");
					task.put("leadPersonName", " ");
					taskArr.add(task);
				}
			}

			for (int i = 0; i < oldMianMemberList.size(); i++) {
				JSONObject oldMember = oldMianMemberList.getJSONObject(i);
				if (oldMember.getString("personRole").equals("是")) {
					for (int j = 0; j < taskArr.size(); j++) {
						JSONObject task = taskArr.getJSONObject(j);
						if (task.getString("taskName").equals(oldMember.getString("belongTask"))) {
							task.put("oldLeadPersonId", oldMember.getString("personId"));
							task.put("oldLeadPersonName", oldMember.getString("personName"));
						}
					}
				}
			}

			for (int i = 0; i < oldMianMemberList.size(); i++) {
				JSONObject oldMember = oldMianMemberList.getJSONObject(i);
				if (oldMember.getString("personRole").equals("否")) {
					for (int j = 0; j < taskArr.size(); j++) {
						JSONObject task = taskArr.getJSONObject(j);
						if (oldMember.getString("belongTask").equals(task.getString("taskName"))) {
							if (task.getJSONArray("oldMemberArr") == null) {
								JSONArray oldMemberArr = new JSONArray();
								oldMemberArr.add(oldMember);
								task.put("oldMemberArr", oldMemberArr);
							} else {
								task.getJSONArray("oldMemberArr").add(oldMember);
							}
						}
					}

				}
			}

			for (int i = 0; i < mianMemberList.size(); i++) {
				JSONObject member = mianMemberList.getJSONObject(i);
				for (int j = 0; j < taskArr.size(); j++) {
					JSONObject task = taskArr.getJSONObject(j);
					if (member.getString("belongTask").equals(task.getString("taskName"))) {
						if (member.getString("personRole").equals("是")) {
							task.put("leadPersonId", member.getString("personId"));
							task.put("leadPersonName", member.getString("personName"));
						} else {
							if (task.getJSONArray("memberArr") == null) {
								JSONArray memberArr = new JSONArray();
								memberArr.add(member);
								task.put("memberArr", memberArr);
							} else {
								task.getJSONArray("memberArr").add(member);
							}
						}
					}
				}
			}
			log.info("该项目的人员和任务信息是：" + taskArr.toJSONString());
		} else {
			JSONObject task = new JSONObject();
			task.put("taskName", srpmsProject.getProjectName());
			task.put("memberArr", mianMemberList);
			task.put("oldMemberArr", oldMianMemberList);
			taskArr.add(task);
			log.info("该项目的人员和任务信息是：" + taskArr.toJSONString());
		}

		for (int i = 0; i < taskArr.size(); i++) {
			JSONObject task = taskArr.getJSONObject(i);

			if (task.getString("oldLeadPersonId") != null
					&& !task.getString("oldLeadPersonId").equals(task.getString("leadPersonId"))) {
				JSONObject changedItem = new JSONObject();
				changedItem.put("taskName", task.getString("taskName"));
				changedItem.put("personRole", "任务负责人");
				changedItem.put("oldValue", task.getString("oldLeadPersonName"));
				changedItem.put("value", task.getString("leadPersonName"));
				relust.add(changedItem);
			}

			StringBuffer sbRemoveMember = new StringBuffer();
			if (task.getJSONArray("oldMemberArr") != null) {
				for (int j = 0; j < task.getJSONArray("oldMemberArr").size(); j++) {
					boolean removeFlg = true;
					if (task.getJSONArray("memberArr") != null) {
						for (int k = 0; k < task.getJSONArray("memberArr").size(); k++) {
							if (task.getJSONArray("oldMemberArr").getJSONObject(j).getString("personId")
									.equals(task.getJSONArray("memberArr").getJSONObject(k).getString("personId"))) {
								removeFlg = false;
								break;
							}
						}
					}

					if (removeFlg) {
						log.info("该人员在新的人员列表中没有找到："
								+ task.getJSONArray("oldMemberArr").getJSONObject(j).getString("personName"));
						if (sbRemoveMember.length() != 0) {
							sbRemoveMember.append(",");
						}
						sbRemoveMember
								.append(task.getJSONArray("oldMemberArr").getJSONObject(j).getString("personName"));
					}
				}
			}

			StringBuffer sbAddMember = new StringBuffer();
			if (task.getJSONArray("memberArr") != null) {
				for (int j = 0; j < task.getJSONArray("memberArr").size(); j++) {
					boolean addFlg = true;
					if (task.getJSONArray("oldMemberArr") != null) {
						for (int k = 0; k < task.getJSONArray("oldMemberArr").size(); k++) {
							if (task.getJSONArray("oldMemberArr").getJSONObject(k).getString("personId")
									.equals(task.getJSONArray("memberArr").getJSONObject(j).getString("personId"))) {
								addFlg = false;
								break;
							}
						}
					}

					if (addFlg) {
						log.info("该人员在旧的人员列表中没有找到："
								+ task.getJSONArray("memberArr").getJSONObject(j).getString("personName"));
						if (sbAddMember.length() != 0) {
							sbAddMember.append(",");
						}
						sbAddMember.append(task.getJSONArray("memberArr").getJSONObject(j).getString("personName"));
					}
				}
			}

			if (sbRemoveMember.length() != 0 || sbAddMember.length() != 0) {
				JSONObject changedItem = new JSONObject();
				changedItem.put("taskName", task.getString("taskName"));
				changedItem.put("personRole", "参与人员");
				changedItem.put("oldValue", sbRemoveMember.toString());
				changedItem.put("value", sbAddMember.toString());
				relust.add(changedItem);
			}
		}
		//
		// boolean emptyTaskPerson = false;
		// for (int i = 0; i < oldMianMemberList.size(); i++) {
		// JSONObject item = oldMianMemberList.getJSONObject(i);
		// if (item.getString("belongTask") == null ||
		// item.getString("belongTask").trim().length() == 0) {
		// emptyTaskPerson = true;
		// break;
		// }
		// }
		//
		// for (int i = 0; i < mianMemberList.size(); i++) {
		// JSONObject item = mianMemberList.getJSONObject(i);
		// if (item.getString("belongTask") == null ||
		// item.getString("belongTask").trim().length() == 0) {
		// emptyTaskPerson = true;
		// break;
		// }
		// }
		//
		// if (emptyTaskPerson) {
		//
		// JSONObject changedItem = new JSONObject();
		// changedItem.put("taskName", "无任务");
		// changedItem.put("personRole", "参与人员");
		//
		// StringBuffer sbRemove = new StringBuffer();
		// for (int i = 0; i < oldMianMemberList.size(); i++) {
		// JSONObject item = oldMianMemberList.getJSONObject(i);
		// boolean removePersonFlg = false;
		// if (item.getString("belongTask") == null ||
		// item.getString("belongTask").trim().length() == 0) {
		//
		// removePersonFlg = true;
		// for (int j = 0; j < mianMemberList.size(); j++) {
		// JSONObject newItem = mianMemberList.getJSONObject(i);
		// if (newItem.getString("belongTask") == null
		// || newItem.getString("belongTask").trim().length() == 0) {
		// if (newItem.getString("personName").equals(item.getString("personName"))) {
		// removePersonFlg = false;
		// }
		// }
		// }
		// if (removePersonFlg) {
		// if (sbRemove.length() != 0) {
		// sbRemove.append(",");
		// }
		// sbRemove.append(item.getString("personName"));
		// }
		// }
		// }
		//
		// StringBuffer sbAdd = new StringBuffer();
		// for (int i = 0; i < mianMemberList.size(); i++) {
		// JSONObject item = mianMemberList.getJSONObject(i);
		// boolean removePersonFlg = false;
		// if (item.getString("belongTask") == null ||
		// item.getString("belongTask").trim().length() == 0) {
		//
		// removePersonFlg = true;
		// for (int j = 0; j < oldMianMemberList.size(); j++) {
		// JSONObject newItem = oldMianMemberList.getJSONObject(i);
		// if (newItem.getString("belongTask") == null
		// || newItem.getString("belongTask").trim().length() == 0) {
		// if (newItem.getString("personName").equals(item.getString("personName"))) {
		// removePersonFlg = false;
		// }
		// }
		// }
		// if (removePersonFlg) {
		// if (sbAdd.length() != 0) {
		// sbAdd.append(",");
		// }
		// sbAdd.append(item.getString("personName"));
		// }
		// }
		// }
		// changedItem.put("oldValue", sbRemove.toString());
		// changedItem.put("value", sbAdd.toString());
		// relust.add(changedItem);
		// }

		log.info("该项目的有变更的人员信息列表是：" + relust.toJSONString());
		return relust;
	}

	private JSONObject getListDiff(JSONArray oldMianMemberList, JSONArray mianMemberList) {

		log.info("新参与人员信息是：" + mianMemberList.toJSONString());
		log.info("旧的人员参与信息是：" + oldMianMemberList.toJSONString());

		StringBuffer sbRemoveMember = new StringBuffer();
		if (oldMianMemberList != null) {
			for (int j = 0; j < oldMianMemberList.size(); j++) {
				boolean removeFlg = true;
				if (mianMemberList != null) {
					for (int k = 0; k < mianMemberList.size(); k++) {
						if (oldMianMemberList.getJSONObject(j).getString("personId")
								.equals(mianMemberList.getJSONObject(k).getString("personId"))) {
							removeFlg = false;
							break;
						}
					}
				}

				if (removeFlg) {
					log.info("该人员在新的人员列表中没有找到：" + oldMianMemberList.getJSONObject(j).getString("personName"));
					if (sbRemoveMember.length() != 0) {
						sbRemoveMember.append(",");
					}
					sbRemoveMember.append(oldMianMemberList.getJSONObject(j).getString("personName"));
				}
			}
		}

		StringBuffer sbAddMember = new StringBuffer();
		if (mianMemberList != null) {
			for (int j = 0; j < mianMemberList.size(); j++) {
				boolean addFlg = true;
				if (oldMianMemberList != null) {
					for (int k = 0; k < oldMianMemberList.size(); k++) {
						if (oldMianMemberList.getJSONObject(k).getString("personId")
								.equals(mianMemberList.getJSONObject(j).getString("personId"))) {
							addFlg = false;
							break;
						}
					}
				}

				if (addFlg) {
					log.info("该人员在旧的人员列表中没有找到：" + mianMemberList.getJSONObject(j).getString("personName"));
					if (sbAddMember.length() != 0) {
						sbAddMember.append(",");
					}
					sbAddMember.append(mianMemberList.getJSONObject(j).getString("personName"));
				}
			}
		}
		if (sbRemoveMember.length() != 0 || sbAddMember.length() != 0) {
			JSONObject changedItem = new JSONObject();
			changedItem.put("oldValue", sbRemoveMember.toString());
			changedItem.put("value", sbAddMember.toString());

			log.info("该项目的院外参与人员变更信息是：" + changedItem.toJSONString());
			return changedItem;
		} else {
			log.info("该项目的院外参与人员No变更信息");
			return null;
		}
	}

	public void save(SrpmsProjectUpdate update, SrpmsProject project) {

		log.info("输入参数update值是：" + JSON.toJSONString(update));
		log.info("输入参数project值是：" + JSON.toJSONString(project));

		long oldClobId = Long.parseLong(update.getOldValue());
		SrpmsCommonNclob oldClobEntity = nclobService.getById(oldClobId);
		JSONObject oldValue = JSONObject.parseObject(oldClobEntity.getContent());
		JSONArray oldMianMemberList = oldValue.getJSONArray("oldMianMemberList");

		long clobId = Long.parseLong(update.getNewValue());
		SrpmsCommonNclob clobEntity = nclobService.getById(clobId);
		JSONObject newValue = JSONObject.parseObject(clobEntity.getContent());
		JSONArray mianMemberList = newValue.getJSONArray("mianMemberList");
		JSONArray newMianMemberList = new JSONArray();

		long projectId = project.getId();
		if (project.getProjectFlag() != null && !"".equals(project.getProjectFlag())) {
			log.info("横纵向项目院外人员变更", project.getId());
			SrpmsProjectTranLong projectTranLong = tranLongService.getById(projectId);
			if (newValue.getJSONArray("outMemberList") == null) {
				projectTranLong.setOutJoinPerson(null);
			} else {
				projectTranLong.setOutJoinPerson(newValue.getJSONArray("outMemberList").toJSONString());
			}
			tranLongService.updateById(projectTranLong);

			projectPersonJoinService.cleanAndSavePersonJoin(JSONArray
					.parseArray(newValue.getJSONArray("mianMemberList").toJSONString(), SrpmsProjectPersonJoinVo.class),
					PersonJoinWayEnums.TASK_MAIN_MEMBER, projectId);
		} else {
			String projectType = project.getProjectType();
			for (int i = 0; i < mianMemberList.size(); i++) {
				JSONObject newMember = mianMemberList.getJSONObject(i);
				boolean changedFlg = true;
				for (int j = 0; j < oldMianMemberList.size(); j++) {
					JSONObject oldMember = oldMianMemberList.getJSONObject(i);
					if (SrpmsConstant.MODIFY_PRO_TYPE_10010101.equals(projectType)
							|| SrpmsConstant.MODIFY_PRO_TYPE_10010102.equals(projectType)) {
						if (newMember.getString("personId").equals(oldMember.getString("personId"))
								&& newMember.getString("belongTask").equals(oldMember.getString("belongTask"))) {
							changedFlg = false;
							break;
						}
					} else {
						if (newMember.getString("personId").equals(oldMember.getString("personId"))) {
							changedFlg = false;
							break;
						}
					}
				}
				if (changedFlg) {
					newMember.put("changeFlag", "1");
					newMember.put("changeReason", update.getUpdateReason());
				}
				newMianMemberList.add(newMember);
			}

			// 更新主要参与人员列表
			projectPersonJoinService.cleanAndSavePersonJoin(
					JSONArray.parseArray(newMianMemberList.toJSONString(), SrpmsProjectPersonJoinVo.class),
					PersonJoinWayEnums.TASK_MAIN_MEMBER, projectId);

			// 重大或者协同创新的时候，通过参与人员列表更新任务分解的负责人和参与人
			if (SrpmsConstant.MODIFY_PRO_TYPE_10010101.equals(projectType)
					|| SrpmsConstant.MODIFY_PRO_TYPE_10010102.equals(projectType)) {

				List<SrpmsProjectTaskVo> taskList = taskService
						.queryTaskListByTaskCategory(TaskCategoryEnums.TASK_INNOVATE_TASK_DECOMPOSITION, projectId);
				for (int i = 0; i < taskList.size(); i++) {

					SrpmsProjectTaskVo taskVo = taskList.get(i);

					JSONArray joinPerson = new JSONArray();
					JSONArray joinPersonInfo = new JSONArray();
					for (int j = 0; j < mianMemberList.size(); j++) {
						JSONObject menber = mianMemberList.getJSONObject(j);
						if (taskVo.getTaskName().equals(menber.getString("belongTask"))) {

							if ("是".equals(menber.getString("personRole"))) {

								JSONObject taskLeadPerson = new JSONObject();
								taskLeadPerson.put("personId", menber.getString("personId"));
								taskLeadPerson.put("personName", menber.getString("personName"));
								taskLeadPerson.put("deptCode", menber.getString("deptCode"));
								taskLeadPerson.put("deptName", menber.getString("deptName"));

								taskVo.setDeptName(menber.getString("deptName"));
								taskVo.setDeptCode(menber.getString("deptCode"));
								taskVo.setLeadPerson(menber.getString("personId"));
								taskVo.setLeadPersonInfo(taskLeadPerson);
							} else {

								JSONObject taskJoinPerson = new JSONObject();
								taskJoinPerson.put("personId", menber.getString("personId"));
								taskJoinPerson.put("personName", menber.getString("personName"));
								taskJoinPerson.put("deptCode", menber.getString("deptCode"));
								taskJoinPerson.put("deptName", menber.getString("deptName"));
								joinPersonInfo.add(taskJoinPerson);

								joinPerson.add(menber.getString("personId"));
							}
						}
					}

					taskVo.setJoinPersonInfo(joinPersonInfo);
					taskVo.setJoinPerson(joinPerson);
				}

				taskService.cleanAndSaveTask(taskList, TaskCategoryEnums.TASK_INNOVATE_TASK_DECOMPOSITION, projectId);
				
				commonService.setTaskAndBudgetList(taskList, projectId, true);
			}
		}

		log.info("更新参与人员完成！！！");
	}
}