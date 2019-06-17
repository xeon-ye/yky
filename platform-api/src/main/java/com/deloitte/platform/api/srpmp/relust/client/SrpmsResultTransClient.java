package com.deloitte.platform.api.srpmp.relust.client;

import javax.validation.Valid;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.TaskNodeActionVO;
import org.springframework.web.bind.annotation.*;

import com.deloitte.platform.api.srpmp.relust.vo.SrpmsResultTransForm;
import com.deloitte.platform.api.srpmp.relust.vo.SrpmsResultTransVo;
import com.deloitte.platform.common.core.entity.vo.Result;

/**
 * @Author : pengchao
 * @Date : Create in 2019-04-27
 * @Description : SrpmsResultTrans控制器接口
 * @Modified :
 */
public interface SrpmsResultTransClient {

	public static final String path = "/srpmp/result/trans";

	/**
	 * POST---新增
	 * 
	 * @param srpmsResultTransForm
	 * @return
	 */
	@PostMapping(value = path)
	Result save(@Valid @ModelAttribute SrpmsResultTransForm srpmsResultTransForm);

	/**
	 * POST---新增
	 * 
	 * @param srpmsResultTransForm
	 * @return
	 */
	@PostMapping(value = path + "/submit")
	Result submit(@Valid @ModelAttribute SrpmsResultTransForm srpmsResultTransForm, UserVo userVo, DeptVo deptVo);

	/**
	 * GET----根据ID获取
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = path + "/search/{id}")
	Result<SrpmsResultTransVo> get(@PathVariable(value = "id") long id);

	@GetMapping(value = path + "/{id}")
	Result<SrpmsResultTransVo> getById(@PathVariable(value = "id") long id);

	/**
	 * 成果管理审批通过操作
	 *
	 * @param actionVO
	 * @param deptVo
	 * @return
	 */
	@PostMapping(value = path+"/audit/approve")
	Result auditApprove(@Valid @RequestBody TaskNodeActionVO actionVO, DeptVo deptVo);

	/**
	 * 成果管理审批拒绝操作
	 *
	 * @param actionVO
	 * @return
	 */
	@PostMapping(value = path+"/refuse")
	Result refuse(@Valid @RequestBody TaskNodeActionVO actionVO);
}
