package com.deloitte.services.srpmp.relust.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.platform.api.bpmservice.feign.BpmOperatorFeignService;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessOperatorFormStart;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskFormStart;
import com.deloitte.platform.api.bpmservice.vo.NextNodeParamVo;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.accept.param.SrpmsProjectAcceptQueryForm;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.TaskNodeActionVO;
import com.deloitte.platform.api.srpmp.relust.param.SrpmsResultTransQueryForm;
import com.deloitte.platform.api.srpmp.relust.vo.SrpmsResultTransForm;
import com.deloitte.platform.api.srpmp.relust.vo.SrpmsResultTransVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.services.srpmp.common.constant.ProcessConstant;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.common.enums.SrpmsProjectUpdateStatusEnums;
import com.deloitte.services.srpmp.common.enums.VoucherAuditModeEnums;
import com.deloitte.services.srpmp.common.enums.VoucherStatusEnums;
import com.deloitte.services.srpmp.common.enums.VoucherTypeEnums;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.common.service.ISerialNoCenterService;
import com.deloitte.services.srpmp.outline.util.CommonUtil;
import com.deloitte.services.srpmp.project.accept.entity.SrpmsProjectAccept;
import com.deloitte.services.srpmp.project.base.entity.SrpmsVoucher;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectAttachmentService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectFlowService;
import com.deloitte.services.srpmp.project.base.service.ISrpmsVoucherService;
import com.deloitte.services.srpmp.project.base.service.impl.SrpmsProjectBpmServiceImpl;
import com.deloitte.services.srpmp.relust.entity.SrpmsResult;
import com.deloitte.services.srpmp.relust.mapper.SrpmsResultMapper;
import com.deloitte.services.srpmp.relust.service.ISrpmsResultService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.srpmp.relust.entity.SrpmsResultTrans;
import com.deloitte.services.srpmp.relust.mapper.SrpmsResultTransMapper;
import com.deloitte.services.srpmp.relust.service.ISrpmsResultTransService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author : pengchao
 * @Date : Create in 2019-04-27
 * @Description : SrpmsResultTrans服务实现类
 * @Modified :
 */
@Service
@Slf4j
@Transactional
public class SrpmsResultTransServiceImpl extends ServiceImpl<SrpmsResultTransMapper, SrpmsResultTrans>
		implements ISrpmsResultTransService {

	@Autowired
	private SrpmsResultTransMapper resultTransMapper;

	@Autowired
	private SrpmsResultMapper srpmsResultMapper;

	@Autowired
	private ISrpmsVoucherService voucherService;

	@Autowired
	private SrpmsProjectBpmServiceImpl bpmService;

	@Autowired
	private BpmOperatorFeignService bpmOperatorFeignService;

	@Autowired
	private ISrpmsProjectAttachmentService attachmentService;

	@Autowired
	private ISerialNoCenterService serialNoService;

	@Autowired
	private ISrpmsProjectFlowService flowService;

	/**
	 * 根据成果转化ID
	 *
	 * @param id
	 * @return
	 */
	@Override
	public SrpmsResultTransVo queryById(Long id) {
		SrpmsResultTrans trans = this.getById(id);
		SrpmsResultTransVo transVo = new SrpmsResultTransVo();
		if(trans == null) {
			throw new BaseException(SrpmsErrorType.RESULT_NO_DATA);
		}

		BeanUtils.copyProperties(trans, transVo);
		transVo.setId(CommonUtil.objectToString(id));
		transVo.setResultId(CommonUtil.objectToString(trans.getResultId()));

		//查询审批历史记录
		if(!SrpmsProjectUpdateStatusEnums.PEROJECT_NOT_SUBMIT.getCode().equals(transVo.getStatus())) {
			SrpmsVoucher voucherEntity = voucherService.getSrpmsVoucherByUpdateId(id);
			if(voucherEntity != null) {
				transVo.setApproveHistory(bpmService.queryAuditHistory(CommonUtil.objectToString(voucherEntity.getId())));
			}
		}

		// 相关附件
		transVo.setAttachmentFile(attachmentService.queryAttachmentListAccept(id));
		return transVo;
	}

	/**
	 * 根据成果ID查询
	 *
	 * @param resultId
	 * @return
	 */
	@Override
	public SrpmsResultTransVo queryByResultId(Long resultId) {
		SrpmsResultTransQueryForm form = new SrpmsResultTransQueryForm();
		QueryWrapper<SrpmsResultTrans> queryWrapper = new QueryWrapper<>();
		form.setResultId(resultId);
		getQueryWrapper(queryWrapper, form);
		SrpmsResultTrans trans = resultTransMapper.selectOne(queryWrapper);
		SrpmsResultTransVo transVo = new SrpmsResultTransVo();
		if(trans == null) {
			transVo.setAttachmentFile(new ArrayList<>());
			return transVo;
		}

		BeanUtils.copyProperties(trans, transVo);
		transVo.setId(CommonUtil.objectToString(trans.getId()));
		transVo.setResultId(CommonUtil.objectToString(trans.getResultId()));

		// 相关附件
		transVo.setAttachmentFile(attachmentService.queryAttachmentListAccept(trans.getId()));
		return transVo;
	}

	@Override
	public String saveOrUpdate(SrpmsResultTransForm form) {

		Long id = form.getId();

		SrpmsResultTrans entity = new SrpmsResultTrans();
		BeanUtils.copyProperties(form, entity);
		if(id == null || id == 0L) {// 执行保存操作

			entity.setStatus(SrpmsConstant.NOT_SUBMIT);
			entity.setId(null);
			this.save(entity);
			id = entity.getId();

		} else {// 执行更新操作
			this.saveOrUpdate(entity);
		}
		// 附件
		if (form.getAttachmentFile() != null && form.getAttachmentFile().size() > 0) {
			attachmentService.saveAttachmentListAccept(form.getAttachmentFile(), id);
		}
		return id.toString();
	}

	@Override
	public void submit(SrpmsResultTransForm form, UserVo userVo, DeptVo deptVo) {

		SrpmsResult srpmsResult = srpmsResultMapper.selectById(form.getResultId());
		if(srpmsResult == null) {
			throw new BaseException(SrpmsErrorType.RESULT_NO_DATA);
		}
		srpmsResult.setTransFlag("1");
		srpmsResultMapper.updateById(srpmsResult);

		String id = this.saveOrUpdate(form);
		SrpmsResultTrans resultTrans = this.getById(id);
		if(resultTrans == null) {
			throw new BaseException(SrpmsErrorType.RESULT_NO_DATA);
		}
		if (!SrpmsProjectUpdateStatusEnums.PEROJECT_NOT_SUBMIT.getCode().equals(resultTrans.getStatus())) {
			throw new BaseException(SrpmsErrorType.RESULT_HAVE_SUBMITTED);
		}
		String year = DateFormatUtils.format(new Date(), "yyyy");
		String header = year.concat(srpmsResult.getResultType());
		String transNum = serialNoService.getNextResultTransNo(header);
		resultTrans.setResultTransNum(transNum);
		resultTrans.setStatus(SrpmsConstant.HAS_SUBMIT);
		this.saveOrUpdate(resultTrans);

		SrpmsVoucher voucherEntity = voucherService.getSrpmsVoucherByReject(srpmsResult.getId());// 查询项目是否进行驳回操作
		if (voucherEntity != null ) {
			flowService.againSubmit(voucherEntity, deptVo, false);
		} else {
			// 新建一个单据
			voucherEntity = new SrpmsVoucher();
			voucherEntity.setBizNumber(transNum);
			voucherEntity.setVoucherName(VoucherTypeEnums.RESULT_TRANS_BOOK.getCode());
			voucherEntity.setPersonName(userVo.getName());
			voucherEntity.setUserId(Long.parseLong(userVo.getId()));
			voucherEntity.setVoucherStatus(VoucherStatusEnums.AUDITING.getCode());
			voucherEntity.setStartTime(LocalDateTime.now());
			voucherEntity.setVoucherType(VoucherTypeEnums.RESULT_TRANS_BOOK.getCode());
			//成果的承担单位CODE 就是当前登录用户的单位CODE
			voucherEntity.setLeadDeptCode(deptVo.getDeptCode());
			voucherEntity.setAuditMode(VoucherAuditModeEnums.SELF_ONLY.getCode());
			voucherEntity.setProjectId(resultTrans.getId());

			voucherService.save(voucherEntity);

			BpmProcessOperatorFormStart startForm = new BpmProcessOperatorFormStart();
//			String processKey = ProcessConstant.SRPMP_SHARING_GENERAL_PROCESS;
			String processKey = ProcessConstant.APPLY_AUDIT_PROCESS_SEC_NEW;
			BpmProcessTaskFormStart processConfig = new BpmProcessTaskFormStart();
			processConfig.setProcessDefineKey(processKey);
			processConfig.setSubmitterCode(String.valueOf(userVo.getId()));// 提交人账号
			processConfig.setSubmitterName(userVo.getName());// 提交人姓名
			processConfig.setSubmitterStation("PI");// 提交人岗位
			processConfig.setObjectId(String.valueOf(voucherEntity.getId()));// 审批对象ID
			processConfig.setObjectOrderNum(voucherEntity.getBizNumber());// 审批对象业务单据编号
			processConfig.setObjectType(voucherEntity.getVoucherType());
			processConfig.setSourceSystem(ProcessConstant.BPM_CLIENT_NAME);
			ArrayList<NextNodeParamVo> nextNodeParamVo = bpmService.getNextNodeParamVo(voucherEntity,
					deptVo.getDeptCode(), processKey, "start");

			// 流程变量
			Map<String, String> processVariables = new HashMap<>();

			startForm.setBpmProcessTaskForm(processConfig);
			startForm.setNextNodeParamVo(nextNodeParamVo);
			startForm.setProcessVariables(processVariables);
			log.info("[BPM]bpmOperatorFeignService.startProcess req:{}", JSONObject.toJSONString(startForm));
			Result result = bpmOperatorFeignService.startProcess(startForm);
			log.info("[BPM]bpmOperatorFeignService.startProcess rsp:{}", JSONObject.toJSONString(result));
		}

	}

	@Override
	public void approvePassBpm(TaskNodeActionVO actionVO, DeptVo deptVo) {
		boolean hasEnd = bpmService.auditApprove(actionVO, deptVo);
		SrpmsVoucher voucherEntity = voucherService.getById(actionVO.getObjectId());
		// 审批后更新单据撤回标识
		voucherEntity.setRecallFlag(1);

		if (!hasEnd) { // 未结束，就返回
			voucherService.saveOrUpdate(voucherEntity);
			return;
		}
		SrpmsResultTrans resultTrans = this.getById(voucherEntity.getProjectId());// 查询信息
		if (resultTrans != null
				&& !SrpmsProjectUpdateStatusEnums.PEROJECT_HAS_SUBMIT.getCode().equals(resultTrans.getStatus())) {
			throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
		}

		resultTrans.setStatus(SrpmsProjectUpdateStatusEnums.PEROJECT_HAS_APPROVE_PASS.getCode());// 设置状态
		// 更新状态
		this.updateById(resultTrans);
		// 更新单据状态
		voucherEntity.setVoucherStatus(VoucherStatusEnums.APPROVED.getCode());
		voucherService.saveOrUpdate(voucherEntity);
	}

	@Override
	public void refuseBpm(TaskNodeActionVO actionVO) {

		bpmService.auditRefuse(actionVO);
		SrpmsVoucher voucherEntity = voucherService.getById(actionVO.getObjectId());
		SrpmsResultTrans resultTrans = this.getById(voucherEntity.getProjectId());// 查询信息
		if (resultTrans != null
				&& !SrpmsProjectUpdateStatusEnums.PEROJECT_HAS_SUBMIT.getCode().equals(resultTrans.getStatus())) {
			throw new BaseException(SrpmsErrorType.PROJECT_STATUS_NG);
		}
		resultTrans.setStatus(SrpmsProjectUpdateStatusEnums.PEROJECT_APPROVE_NO.getCode());// 设置状态
		// 更新状态
		this.updateById(resultTrans);

		voucherEntity.setRecallFlag(1);
		// 更新单据状态
		voucherEntity.setVoucherStatus(VoucherStatusEnums.REFUSED.getCode());
		voucherService.saveOrUpdate(voucherEntity);
//		voucherService.updateStatus(CommonUtil.getLongValue(actionVO.getObjectId()), VoucherStatusEnums.REFUSED);

	}

	public QueryWrapper<SrpmsResultTrans> getQueryWrapper(QueryWrapper<SrpmsResultTrans> queryWrapper,
																SrpmsResultTransQueryForm queryForm) {
		if (queryForm.getResultId() != null) {
			queryWrapper.eq(SrpmsResultTrans.RESULT_ID, queryForm.getResultId());
		}
		return queryWrapper;
	}
}
