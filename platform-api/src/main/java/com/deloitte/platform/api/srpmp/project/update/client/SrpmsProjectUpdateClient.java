package com.deloitte.platform.api.srpmp.project.update.client;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.update.param.SrpmsProjectUpdateQueryForm;
import com.deloitte.platform.api.srpmp.project.update.vo.SrpmsProjectUpdateForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * @Author : Apeng
 * @Date : Create in 2019-04-01
 * @Description : SrpmsProjectUpdate控制器接口
 * @Modified :
 */
public interface SrpmsProjectUpdateClient {

	public static final String path = "/srpmp/project/update";

	/**
	 * 查看变更记录
	 *
	 * @param
	 * @return
	 */
	@GetMapping(value = path)
	Result list(@Valid @ModelAttribute SrpmsProjectUpdateQueryForm form, UserVo user, DeptVo dept);

	/**
	 * 查看变更明细
	 *
	 * @param
	 * @return
	 */
	@GetMapping(value = path + "/{id}")
	Result getById(@PathVariable(value = "id") long id);

	/**
	 * POST---保存
	 * 
	 * @param form
	 * @return
	 */
	@PostMapping(value = path)
	Result save(@ModelAttribute SrpmsProjectUpdateForm form, UserVo user, DeptVo dept);

	/**
	 * 点击变更操作
	 *
	 * @param form
	 * @return
	 */
	@GetMapping(value = path + "/search")
	Result addNewRecord(@ModelAttribute SrpmsProjectUpdateQueryForm form, UserVo user);

	/**
	 * POST---提交
	 * 
	 * @param form
	 * @return
	 */
	@PostMapping(value = path + "/submit")
	Result submit(@Valid @ModelAttribute SrpmsProjectUpdateForm form, UserVo user, DeptVo dept);

	/**
	 * GET---查看页面，审批页面，历史记录页面的数据接口
	 * 
	 * @param form
	 * @return
	 */
	@GetMapping(value = path + "/changedData/{id}")
	Result show(@PathVariable(value = "id") long id);

	/**
	 * GET---查看页面，审批页面，历史记录页面的数据接口
	 * 
	 * @param form
	 * @return
	 */
	@GetMapping(value = path + "/delete/{id}")
	Result delete(@PathVariable(value = "id") long id);
}
