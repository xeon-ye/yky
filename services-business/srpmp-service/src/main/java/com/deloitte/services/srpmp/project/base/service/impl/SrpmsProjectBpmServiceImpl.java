package com.deloitte.services.srpmp.project.base.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.bpmservice.feign.BpmApprovalMatrixFeignService;
import com.deloitte.platform.api.bpmservice.feign.BpmOperatorFeignService;
import com.deloitte.platform.api.bpmservice.feign.BpmProcessTaskFeignService;
import com.deloitte.platform.api.bpmservice.param.BpmApprovalMatrixQueryFormForApproval;
import com.deloitte.platform.api.bpmservice.param.BpmConductListQueryForm;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryForm;
import com.deloitte.platform.api.bpmservice.vo.BpmApprovalMatrixVo;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessOperatorApprove;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessOperatorFormStart;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskFormApprove;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskFormStart;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.bpmservice.vo.NextNodeParamVo;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.TaskNodeActionVO;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.TaskNodeVO;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.srpmp.common.constant.ProcessConstant;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.common.enums.ProjectCategoryEnums;
import com.deloitte.services.srpmp.common.enums.VoucherTypeEnums;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.entity.SrpmsVoucher;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectService;
import com.deloitte.services.srpmp.project.update.entity.SrpmsProjectUpdate;
import com.deloitte.services.srpmp.project.update.service.ISrpmsProjectUpdateService;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by lixin on 16/03/2019. 跟BPM对接的service类
 */
@Service
@Transactional
@Slf4j
public class SrpmsProjectBpmServiceImpl implements ProcessConstant {

	@Autowired
	private BpmProcessTaskFeignService bpmProcessTaskFeignService;

	@Autowired
	private BpmOperatorFeignService bpmOperatorFeignService;

	@Autowired
	private BpmApprovalMatrixFeignService bpmApprovalMatrixFeignService;

	@Autowired
	private SrpmsVoucherServiceImpl voucherService;

	@Autowired
	private ISrpmsProjectService projectService;

	@Autowired
	private ISrpmsProjectUpdateService updateService;

	@Value("${srpms.audit.url:http://124.17.100.183:30080/srpmp/index.html#/}")
	private String SRPMS_AUDIT_URL;

	// 任务书-医科院二级审批流程key
	// private String TASK_AUDIT_PROCESS_SEC = "srpmp_process_task_audit_sec";

	/**
	 * 查询待办列表
	 */
	public IPage<TaskNodeVO> queryBackLogPage(BaseQueryForm pageForm, UserVo userVo) {
		IPage<TaskNodeVO> taskNodePage = new Page<>();
		log.info("[BPM]bpmProcessTaskFeignService.searchBackLog req: userId={}", userVo.getId());
		BpmConductListQueryForm queryForm = new BpmConductListQueryForm();
		queryForm.setApproverAcount(userVo.getId());
		queryForm.setPageSize(pageForm.getPageSize());
		queryForm.setCurrentPage(pageForm.getCurrentPage());
		queryForm.setSourceSystem(BPM_CLIENT_NAME);
		Result<GdcPage<BpmProcessTaskVo>> result = bpmProcessTaskFeignService.searchBackLog(queryForm);
		log.info("[BPM]bpmProcessTaskFeignService.searchBackLog rsp: {}", JSONObject.toJSONString(result));
		if (result != null && result.isSuccess()) {
			GdcPage<BpmProcessTaskVo> taskPage = result.getData();
			if (taskPage != null) {
				taskNodePage = new com.deloitte.platform.common.core.util.BeanUtils<TaskNodeVO>().copyPageObjs(taskPage,
						TaskNodeVO.class);
			}
		}else{
			log.warn("[BPM]查询待办列表，BPM服务不可用");
		}
		fillVoucherDateToTaskNode(taskNodePage);
		return taskNodePage;
	}

	/**
	 * 查询已办列表
	 */
	public IPage<TaskNodeVO> queryDonePage(BaseQueryForm pageForm, UserVo userVo) {
		IPage<TaskNodeVO> taskNodePage = new Page<>();
		log.info("[BPM]bpmProcessTaskFeignService.searchDone req: userId={}", userVo.getId());
		BpmConductListQueryForm queryForm = new BpmConductListQueryForm();
		queryForm.setApproverAcount(userVo.getId());
		queryForm.setTaskKey("not in (start)");
		queryForm.setPageSize(pageForm.getPageSize());
		queryForm.setCurrentPage(pageForm.getCurrentPage());
		queryForm.setSourceSystem(BPM_CLIENT_NAME);
		Result<GdcPage<BpmProcessTaskVo>> result = bpmProcessTaskFeignService.searchDone(queryForm);
		log.info("[BPM]bpmProcessTaskFeignService.searchDone rsp: {}", JSONObject.toJSONString(result));
		if (result != null && result.isSuccess()) {
			GdcPage<BpmProcessTaskVo> taskPage = result.getData();
			if (taskPage != null) {
				taskNodePage = new com.deloitte.platform.common.core.util.BeanUtils<TaskNodeVO>().copyPageObjs(taskPage,
						TaskNodeVO.class);
			}
		}else{
			log.warn("[BPM]查询已办列表，BPM服务不可用");
		}
		fillVoucherDateToTaskNode(taskNodePage);
		return taskNodePage;
	}

	/**
	 * 为待办填充单据数据
	 */
	private void fillVoucherDateToTaskNode(IPage<TaskNodeVO> taskNodeVOIPage) {
		List<TaskNodeVO> taskNodeVOList = taskNodeVOIPage.getRecords();
		for (TaskNodeVO taskNodeVO : taskNodeVOList) {
			String voucherId = taskNodeVO.getObjectId();
			SrpmsVoucher voucherEntity = voucherService.getById(voucherId);
			if (voucherEntity != null) {
				taskNodeVO.setObjectType(voucherEntity.getVoucherType());
				taskNodeVO.setStartTime(voucherEntity.getStartTime());
				taskNodeVO.setApplyPersonName(voucherEntity.getPersonName());
				taskNodeVO.setProjectId(String.valueOf(voucherEntity.getProjectId()));
				taskNodeVO.setProjectFlag(voucherEntity.getProjectFlag());
				taskNodeVO.setProjectType(voucherEntity.getProjectType());
			}
		}
	}

	/**
	 * 发起流程
	 * 
	 * @param voucher
	 *            单据
	 */
	public void startProcess(SrpmsVoucher voucher, UserVo userVo, DeptVo deptVo, SrpmsProjectUpdate update) {
		String deptCode = voucher.getLeadDeptCode();
		if (voucher == null || userVo == null || deptVo == null) {

		}
		BpmProcessOperatorFormStart startForm = new BpmProcessOperatorFormStart();
		String processKey = getProcessDefKey(voucher, deptCode);
		if (update != null) {
			processKey = getModifyProcessDefKey(voucher.getVoucherType(), deptCode, update);
		}
		BpmProcessTaskFormStart processConfig = new BpmProcessTaskFormStart();
		processConfig.setProcessDefineKey(processKey);
		processConfig.setSubmitterCode(String.valueOf(userVo.getId()));// 提交人账号
		processConfig.setSubmitterName(userVo.getName());// 提交人姓名
		processConfig.setSubmitterStation("PI");// 提交人岗位
		processConfig.setObjectId(String.valueOf(voucher.getId()));// 审批对象ID
		processConfig.setObjectOrderNum(voucher.getBizNumber());// 审批对象业务单据编号
		processConfig.setObjectType("[科研]"+voucher.getVoucherType());
		processConfig.setSourceSystem(BPM_CLIENT_NAME);
		ArrayList<NextNodeParamVo> nextNodeParamVo = this.getNextNodeParamVo(voucher, deptCode, processKey,
				"start");

		// 流程变量
		Map<String, String> processVariables = new HashMap<>();
		processVariables.put("deptCode", deptCode);
		processVariables.put("auditMode", voucher.getAuditMode());

		startForm.setBpmProcessTaskForm(processConfig);
		startForm.setNextNodeParamVo(nextNodeParamVo);
		startForm.setProcessVariables(processVariables);
		log.info("[BPM]bpmOperatorFeignService.startProcess req:{}", JSONObject.toJSONString(startForm));
		Result result = bpmOperatorFeignService.startProcess(startForm);
		log.info("[BPM]bpmOperatorFeignService.startProcess rsp:{}", JSONObject.toJSONString(result));
		if (result == null || !result.isSuccess() || !"000000".equals(result.getCode())){
			throw new BaseException(SrpmsErrorType.BPM_NOT_AVAIABLE);
		}
	}

	/**
	 * 审核通过
	 * 
	 * @return 流程是否结束
	 */
	public boolean auditApprove(TaskNodeActionVO actionVO, DeptVo deptVo) {
		// 查询下一个节点审核者
		SrpmsVoucher voucher = voucherService.getById(actionVO.getObjectId());
		String deptCode = voucher.getLeadDeptCode();
		String processKey = getProcessDefKey(voucher, deptVo.getDeptCode());
		SrpmsProjectUpdate update = updateService.getById(voucher.getProjectId());
		if (update != null) {
			processKey = getModifyProcessDefKey(voucher.getVoucherType(), deptVo.getDeptCode(), update);
		}
		ArrayList<NextNodeParamVo> nextNodeParamVoList = this.getNextNodeParamVo(voucher, deptCode, processKey, actionVO.getTaskId());
		BpmProcessOperatorApprove approveReq = new BpmProcessOperatorApprove();

		BpmProcessTaskFormApprove taskPrarm = new BpmProcessTaskFormApprove();
		taskPrarm.setId(Long.parseLong(actionVO.getId()));
		taskPrarm.setOpinion(actionVO.getOpinion());

		approveReq.setBpmProcessTaskForm(taskPrarm);
		approveReq.setNextNodeParamVo(nextNodeParamVoList);
		log.info("[BPM]bpmOperatorFeignService.approveProcess req:{}", JSONObject.toJSONString(approveReq));
		Result result = bpmOperatorFeignService.approveProcess(approveReq);
		log.info("[BPM]bpmOperatorFeignService.approveProcess rsp:{}", JSONObject.toJSONString(result));
		if (result != null && result.isSuccess()) {
			String data = result.getData().toString();
			if ("审批结束".equals(data)) {
				return true;
			}
		}else{
			throw new BaseException(SrpmsErrorType.BPM_NOT_AVAIABLE);
		}
		return false;
	}

	/**
	 * 审核拒绝
	 */
	public void auditRefuse(TaskNodeActionVO actionVO) {
		BpmProcessTaskFormApprove taskReq = new BpmProcessTaskFormApprove();
		taskReq.setId(Long.parseLong(actionVO.getId()));
		taskReq.setOpinion(actionVO.getOpinion());
		log.info("[BPM]bpmOperatorFeignService.stopProcess req:{}", JSONObject.toJSONString(taskReq));
		Result result = bpmOperatorFeignService.stopProcess(taskReq);
		log.info("[BPM]bpmOperatorFeignService.stopProcess rsp:{}", JSONObject.toJSONString(result));
		if (result == null || !result.isSuccess() || !"000000".equals(result.getCode())){
			throw new BaseException(SrpmsErrorType.BPM_NOT_AVAIABLE);
		}

	}

	/**
	 * 驳回到发起人
	 * 
	 * @param actionVO
	 * @param voucher
	 */
	public void auditBackToFirst(TaskNodeActionVO actionVO, SrpmsVoucher voucher) {
		BpmProcessOperatorApprove approve = new BpmProcessOperatorApprove();
		ArrayList<NextNodeParamVo> nextNodeParamVoList = Lists.newArrayList();
		NextNodeParamVo nextNode = new NextNodeParamVo();
		nextNode.setAcountId(String.valueOf(voucher.getUserId()));
		nextNode.setAcountName(voucher.getPersonName());
		nextNodeParamVoList.add(nextNode);
		approve.setNextNodeParamVo(nextNodeParamVoList);

		BpmProcessTaskFormApprove reject = new BpmProcessTaskFormApprove();
		reject.setId(Long.parseLong(actionVO.getId()));
		reject.setOpinion(actionVO.getOpinion());
		approve.setBpmProcessTaskForm(reject);

		log.info("[BPM]bpmOperatorFeignService.auditBackToFirst req:{}", JSONObject.toJSONString(approve));
		Result result = bpmOperatorFeignService.rejectToFirstProcess(approve);
		log.info("[BPM]bpmOperatorFeignService.auditBackToFirst rsp:{}", JSONObject.toJSONString(result));
		if (result == null || !result.isSuccess() || !"000000".equals(result.getCode())){
			throw new BaseException(SrpmsErrorType.BPM_NOT_AVAIABLE);
		}
	}

	/**
	 * 驳回到发起人
	 *
	 * @param actionVO
	 * @param voucher
	 */
	public void auditRejectToFirst(TaskNodeActionVO actionVO, SrpmsVoucher voucher) {
		BpmProcessOperatorApprove approve = new BpmProcessOperatorApprove();

		NextNodeParamVo nextNode = new NextNodeParamVo();// 下一环节审批参数
		nextNode.setAcountId(String.valueOf(voucher.getUserId()));
		nextNode.setAcountName(voucher.getPersonName());

		BpmProcessTaskFormApprove reject = new BpmProcessTaskFormApprove();// 流程参数对象
		reject.setId(Long.parseLong(actionVO.getId()));
		reject.setOpinion(actionVO.getOpinion());

		ArrayList<NextNodeParamVo> nextNodeParamVoList = Lists.newArrayList();
		nextNodeParamVoList.add(nextNode);

		approve.setNextNodeParamVo(nextNodeParamVoList);
		approve.setBpmProcessTaskForm(reject);

		log.info("[BPM]bpmOperatorFeignService.auditBackToFirst req:{}", JSONObject.toJSONString(approve));
		Result result = bpmOperatorFeignService.rejectToFirstProcess(approve);
		log.info("[BPM]bpmOperatorFeignService.auditBackToFirst rsp:{}", JSONObject.toJSONString(result));
		if (result == null || !result.isSuccess()){
			throw new BaseException(SrpmsErrorType.BPM_NOT_AVAIABLE);
		}
	}

	/**
	 * 审核驳回
	 */
	public boolean auditBack() {
		return false;
	}

	/**
	 * 查询审核历史
	 */
	public List<TaskNodeVO> queryAuditHistory(String objectId) {
		List<TaskNodeVO> list = new ArrayList<>();
		BpmProcessTaskQueryForm queryForm = new BpmProcessTaskQueryForm();
		queryForm.setObjectId(objectId);

		log.info("[BPM]bpmProcessTaskFeignService.list req: objectId={}", objectId);
		Result<List<BpmProcessTaskVo>> result = bpmProcessTaskFeignService.list(queryForm);
		log.info("[BPM]bpmProcessTaskFeignService.list rsp: {}", JSONObject.toJSONString(result));
		if (result != null && result.isSuccess()) {
			List<BpmProcessTaskVo> resultData = result.getData();
			if (resultData!=null){
				for (BpmProcessTaskVo taskVo : resultData) {
					TaskNodeVO nodeVO = new TaskNodeVO();
					BeanUtils.copyProperties(taskVo, nodeVO);
					list.add(nodeVO);
				}
			}
		}else{
			log.warn("[BPM]查询审批历史，BPM服务不可用");
		}
		return list;
	}

	/**
	 * 查询审批请求的ID设值
	 *
	 * @param actionVO
	 * @return
	 */
	public TaskNodeActionVO queryBpmProcessTask(TaskNodeActionVO actionVO) {
		BpmProcessTaskQueryForm queryForm = new BpmProcessTaskQueryForm();
		queryForm.setObjectId(actionVO.getObjectId());
		queryForm.setTaskKey("start");
		queryForm.setSourceSystem(BPM_CLIENT_NAME);
		Result<List<BpmProcessTaskVo>> result = bpmProcessTaskFeignService.list(queryForm);
		log.info("[BPM]bpmProcessTaskFeignService.list rsp: {}", JSONObject.toJSONString(result));
		if (result != null && result.isSuccess()) {
			List<BpmProcessTaskVo> list = result.getData();
			if (list != null && list.size() > 0) {
				actionVO.setId(list.get(0).getId());
			}
		}else{
			throw new BaseException(SrpmsErrorType.BPM_NOT_AVAIABLE);
		}
		return actionVO;
	}

	public TaskNodeActionVO queryBpmProcessAgainSubmitTask(TaskNodeActionVO actionVO) {
		BpmProcessTaskQueryForm queryForm = new BpmProcessTaskQueryForm();
		queryForm.setObjectId(actionVO.getObjectId());
		queryForm.setTaskKey("start");
		queryForm.setSourceSystem(BPM_CLIENT_NAME);
		queryForm.setTaskStauts("待重新提交");
		Result<List<BpmProcessTaskVo>> result = bpmProcessTaskFeignService.list(queryForm);
		log.info("[BPM]bpmProcessTaskFeignService.list rsp: {}", JSONObject.toJSONString(result));
		if (result != null && result.isSuccess()) {
			List<BpmProcessTaskVo> list = result.getData();
			if (list != null && list.size() > 0) {
				BpmProcessTaskVo taskVo = list.get(0);
				actionVO.setId(taskVo.getId());
				actionVO.setTaskId(taskVo.getTaskId());
			}
		}else{
			throw new BaseException(SrpmsErrorType.BPM_NOT_AVAIABLE);
		}
		return actionVO;
	}

	/**
	 * 获取下一个节点审核者
	 */
	public ArrayList<NextNodeParamVo> getNextNodeParamVo(SrpmsVoucher voucher, String deptCode,
			String processDefineKey, String taskId) {
		ArrayList<NextNodeParamVo> nextList = new ArrayList<>();
		BpmApprovalMatrixQueryFormForApproval param = new BpmApprovalMatrixQueryFormForApproval();
		String chargeBusiness = getBusinessType(voucher.getProjectId(), voucher.getVoucherType());
		// if (processDefineKey.endsWith("sec") && taskId.equals("start")){
		// bizType = voucher.getVoucherType();
		// }else{
		// bizType = getBusinessType(voucher.getProjectId(), voucher.getVoucherType());
		// }
		param.setChargeBusiness(chargeBusiness);
		param.setChargeOrg(deptCode);
		param.setProcessDefineKey(processDefineKey);
		param.setTaskID(taskId);
		Map<String, String> processVariables = new HashMap<>();
		processVariables.put("deptCode", deptCode);
		processVariables.put("auditMode", voucher.getAuditMode());

		param.setProcessVariables(processVariables);

		log.info("[BPM]bpmApprovalMatrixFeignService.findNextApprover req:{}", JSONObject.toJSONString(param));
		Result<List<BpmApprovalMatrixVo>> result = bpmApprovalMatrixFeignService.findNextApprover(param);
		log.info("[BPM]bpmApprovalMatrixFeignService.findNextApprover rsp:{}", JSONObject.toJSONString(result));
		if (result != null && result.isSuccess()) {
			List<BpmApprovalMatrixVo> matrixVoList = result.getData();
			if (matrixVoList != null){
				for (BpmApprovalMatrixVo matrixVo : matrixVoList) {
					NextNodeParamVo nodeParamVo = new NextNodeParamVo();
					nodeParamVo.setAcountId(matrixVo.getAccountNum());
					nodeParamVo.setAcountName(matrixVo.getAccountName());
					nodeParamVo.setObjectUrl(getObjectUrlByVoucherType(voucher));
					nextList.add(nodeParamVo);
				}
			}
		}else{
			throw new BaseException(SrpmsErrorType.BPM_NOT_AVAIABLE);
		}
		// 如果没有审批人 加一个空对象
		if (nextList.size() == 0) {
			nextList.add(new NextNodeParamVo());
		}
		return nextList;
	}



	private String getObjectUrlByVoucherType(SrpmsVoucher voucher){
		String routerName = getRouterNameByVoucherType(voucher);
		String url = SRPMS_AUDIT_URL + routerName + "?projectId=" + voucher.getProjectId();
		return url;
	}


	private String getRouterNameByVoucherType(SrpmsVoucher voucher){
		String routerName = "";
		String projectFlag = voucher.getProjectFlag();
		String projectType = voucher.getProjectType();
		if (VoucherTypeEnums.UPDATE_JOIN_BOOK.getCode().equals(voucher.getVoucherType())){
			if ("1".equals(projectFlag)){
				routerName = "change-across-participant-check";
			}else{
				routerName = "change-participant-check";
			}
		}else if (VoucherTypeEnums.ACCEPT_BOOK.getCode().equals(voucher.getVoucherType())){
			if ("1".equals(projectFlag)){
				routerName = "hz-project-report-check";
			}else{
				switch(projectType){
					case "10010401":
					case "10010201":
						routerName = "academy-report-check";
						break;
					case "10010301":
					case "10010302":
						routerName = "school-report-check";
						break;
					case "10010101":
					case "10010102":
					case "10010103":
						routerName = "innovate-report-check";
						break;
				}
			}
		}else{
			VoucherTypeEnums routerEnum = VoucherTypeEnums.getEnumByCode(voucher.getVoucherType());
			if (routerEnum != null){
				routerName = routerEnum.getAuditRouteName();
			}
		}
		return routerName;
	}


	/**
	 * 根据人员和单位信息获取流程定义key
	 */
	private String getProcessDefKey(SrpmsVoucher voucher, String deptCode) {
		String voucherType = voucher.getVoucherType();
		String projectType = voucher.getProjectType();
		// 流程参数对象
		String processKey = "";
		if (voucherType.equals(VoucherTypeEnums.APPLY_BOOK.getCode())) {
			// 申请书
			processKey = APPLY_AUDIT_PROCESS_SEC_NEW;
		} else if (voucherType.equals(VoucherTypeEnums.TASK_BOOK.getCode())) {
			processKey = TASK_AUDIT_PROCESS_TOP;
		} else if (voucherType.equals(VoucherTypeEnums.MPR_EVA_A.getCode())) {
//			//如果一级PI提交，走2级节点
//			if (SrpmsConstant.SRPMS_FIRST_LEVEL_DEPT_CODE.equals(deptCode)) {
//				processKey = MPR_EVA_PROCESS_B;
//			} else {
//				processKey = MPR_EVA_PROCESS_A;
//			}
			//使用统一的节点
			processKey = APPLY_AUDIT_PROCESS_SEC_NEW;
		} else if (voucherType.equals(VoucherTypeEnums.MPR_EVA_B.getCode())) {
//			processKey = MPR_EVA_PROCESS_B;
			processKey = APPLY_AUDIT_PROCESS_SEC_NEW;
		} else if (voucherType.equals(VoucherTypeEnums.TRAN_LONG_TASK_BOOK.getCode())// 横纵向项目
				|| voucherType.equals(VoucherTypeEnums.ACCEPT_BOOK.getCode()) // 项目验收
				|| voucherType.equals(VoucherTypeEnums.BUDGET_APPLY_BOOK.getCode())// 预算申请
				|| voucherType.equals(VoucherTypeEnums.RESULT_PAPER.getCode())// 论文成果
				|| voucherType.equals(VoucherTypeEnums.RESULT_PATENT.getCode())// 专利成果
				|| voucherType.equals(VoucherTypeEnums.RESULT_BOOK.getCode())// 著作成果
				|| voucherType.equals(VoucherTypeEnums.RESULT_ACA_EXC.getCode())// 学术交流成果
				|| voucherType.equals(VoucherTypeEnums.RESULT_AWARD.getCode())// 奖励成果
				|| voucherType.equals(VoucherTypeEnums.RESULT_MEDICAL.getCode())// 新药器械成果
				|| voucherType.equals(VoucherTypeEnums.RESULT_APPLIANCE.getCode())// 医疗器械成果
				|| voucherType.equals(VoucherTypeEnums.RESULT_SOFTWARE.getCode())// 软件成果
				|| voucherType.equals(VoucherTypeEnums.RESULT_TRANS_BOOK.getCode())// 成果转化
				|| voucherType.equals(VoucherTypeEnums.MPR_EVA_YEAR.getCode())//年度报告
				|| voucherType.equals(VoucherTypeEnums.MPR_EVA_MID.getCode())//中期报告
				|| voucherType.equals(VoucherTypeEnums.MPR_EVA_OTHER.getCode())) {
			processKey = APPLY_AUDIT_PROCESS_SEC_NEW;
		}
		return processKey;
	}

	/**
	 * 根据项目类型，人员和单位信息获取流程定义key
	 */
	private String getModifyProcessDefKey(String voucherType, String deptCode, SrpmsProjectUpdate update) {
		// 流程参数对象
		String processKey = "";

		switch (update.getProjectType()) {// 项目类型
		case SrpmsConstant.MODIFY_PRO_TYPE_10010101:// 创新工程-重大协同创新
			processKey = setProcessKey(voucherType, deptCode, processKey);
			break;
		case SrpmsConstant.MODIFY_PRO_TYPE_10010102:// 创新工程-协同创新团队
			processKey = setProcessKey(voucherType, deptCode, processKey);
			break;
		case SrpmsConstant.MODIFY_PRO_TYPE_10010103:// 创新工程-先导专项
			processKey = setProcessKey(voucherType, deptCode, processKey);
			break;
		case SrpmsConstant.MODIFY_PRO_TYPE_10010401:// 改革经费
			processKey = setProcessKey(voucherType, deptCode, processKey);
			break;
		case SrpmsConstant.MODIFY_PRO_TYPE_10010201:// 院基科费
			processKey = setProcessKey(voucherType, deptCode, processKey);
			break;
		case SrpmsConstant.MODIFY_PRO_TYPE_10010301:// 校基科费-青年教师
			processKey = setProcessKey(voucherType, deptCode, processKey);
			break;
		case SrpmsConstant.MODIFY_PRO_TYPE_10010302:// 校基科费-学生项目
			processKey = setProcessKey(voucherType, deptCode, processKey);
			break;
		default:
			processKey = setProcessKey(voucherType, deptCode, processKey);// 横纵向项目
			break;
		}
		return processKey;
	}

	public String setProcessKey(String voucherType, String deptCode, String processKey) {
//		if (voucherType.equals(VoucherTypeEnums.UPDATE_JOIN_BOOK.getCode())) {// 参与人员变更
//			if (SrpmsConstant.SRPMS_FIRST_LEVEL_DEPT_CODE.equals(deptCode)) {
//				processKey = srpmp_process_modify_this;
//			} else {
//				processKey = srpmp_process_modify_parent;
//			}
//		} else if (voucherType.equals(VoucherTypeEnums.UPDATE_PERSON_BOOK.getCode())) {// 负责人变更
//			if (SrpmsConstant.SRPMS_FIRST_LEVEL_DEPT_CODE.equals(deptCode)) {
//				processKey = srpmp_process_modify_this;
//			} else {
//				processKey = srpmp_process_modify_parent;
//			}
//		} else if (voucherType.equals(VoucherTypeEnums.UPDATE_CONTENT_BOOK.getCode())) {// 内容变更
//			if (SrpmsConstant.SRPMS_FIRST_LEVEL_DEPT_CODE.equals(deptCode)) {
//				processKey = srpmp_process_modify_this;
//			} else {
//				processKey = srpmp_process_modify_parent;
//			}
//		} else if (voucherType.equals(VoucherTypeEnums.UPDATE_BUDGET_BOOK.getCode())) {// 预算变更
//			if (SrpmsConstant.SRPMS_FIRST_LEVEL_DEPT_CODE.equals(deptCode)) {
//				processKey = srpmp_process_modify_this;
//			} else {
//				processKey = srpmp_process_modify_parent;
//			}
//		} else if (voucherType.equals(VoucherTypeEnums.UPDATE_STATE_BOOK.getCode())) {// 状态变更
//			if (SrpmsConstant.SRPMS_FIRST_LEVEL_DEPT_CODE.equals(deptCode)) {
//				processKey = srpmp_process_modify_this;
//			} else {
//				processKey = srpmp_process_modify_parent;
//			}
//		}
		processKey = APPLY_AUDIT_PROCESS_SEC_NEW;
		return processKey;
	}

	/**
	 * 拼装业务类型
	 */
	private String getBusinessType(Long projectId, String voucherType) {
		// 流程参数对象
		String busType = "pro_type";
//		if (StringUtils.equals(voucherType, VoucherTypeEnums.MPR_EVA_A.getCode())
//				|| StringUtils.equals(voucherType, VoucherTypeEnums.MPR_EVA_B.getCode())
//				|| StringUtils.equals(voucherType, VoucherTypeEnums.ACCEPT_BOOK.getCode())// 项目验收
//				|| StringUtils.equals(voucherType, VoucherTypeEnums.RESULT_PAPER.getCode())// 论文成果
//				|| StringUtils.equals(voucherType, VoucherTypeEnums.RESULT_PATENT.getCode())// 专利成果
//				|| StringUtils.equals(voucherType, VoucherTypeEnums.RESULT_BOOK.getCode())// 著作成果
//				|| StringUtils.equals(voucherType, VoucherTypeEnums.RESULT_ACA_EXC.getCode())// 学术交流成果
//				|| StringUtils.equals(voucherType, VoucherTypeEnums.RESULT_AWARD.getCode())// 奖励成果
//				|| StringUtils.equals(voucherType, VoucherTypeEnums.RESULT_MEDICAL.getCode())// 新药器械成果
//				|| StringUtils.equals(voucherType, VoucherTypeEnums.RESULT_APPLIANCE.getCode())// 医疗器械成果
//				|| StringUtils.equals(voucherType, VoucherTypeEnums.RESULT_SOFTWARE.getCode())// 软件成果
//				|| StringUtils.equals(voucherType, VoucherTypeEnums.RESULT_TRANS_BOOK.getCode())// 成果转化
//				|| StringUtils.equals(voucherType, VoucherTypeEnums.BUDGET_APPLY_BOOK.getCode())
//				|| StringUtils.equals(voucherType, VoucherTypeEnums.MPR_EVA_YEAR.getCode())
//				|| StringUtils.equals(voucherType, VoucherTypeEnums.MPR_EVA_MID.getCode())
//				|| StringUtils.equals(voucherType, VoucherTypeEnums.MPR_EVA_OTHER.getCode())) {
//			return busType;
//		}
		String projectType = "";
		SrpmsProject projectEntity = projectService.getById(projectId);
		if (projectEntity != null) {
			projectType = projectEntity.getProjectType();
		}
		if (ProjectCategoryEnums.INNOVATE_PRE.getHeader().equals(projectType)) {
			busType = busType + "_inn_pre";
		} else if (ProjectCategoryEnums.INNOVATE_BCOO.getHeader().equals(projectType)) {
			busType = busType + "_inn_bcoo";
		} else if (ProjectCategoryEnums.INNOVATE_COO.getHeader().equals(projectType)) {
			busType = busType + "_inn_coo";
		} else if (ProjectCategoryEnums.ACADEMY.getHeader().equals(projectType)) {
			busType = busType + "_aca_col";
		} else if (ProjectCategoryEnums.SCHOOL_TEACH.getHeader().equals(projectType)) {
			busType = busType + "_aca_tea";
		} else if (ProjectCategoryEnums.SCHOOL_STU.getHeader().equals(projectType)) {
			busType = busType + "_aca_stu";
		} else if (ProjectCategoryEnums.STRUCTURAL_REFORM.getHeader().equals(projectType)) {
			busType = busType + "_reform";
		}else if (ProjectCategoryEnums.ACADEMY_UNIT.getHeader().equals(projectType)) {
			busType = busType + "_inn_unit";
		}else if(projectType.equals("100501")){
			//横向项目
			busType = busType + "_cros";
		}else if(projectType.startsWith("1002")){
			//纵向-国家级
			busType = busType + "_len_country";
		}else if(projectType.startsWith("1003")){
			//纵向-省部级
			busType = busType + "_len_city";
		}
		return busType;
	}

}
