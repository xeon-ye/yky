package com.deloitte.services.srpmp.project.update.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.update.client.SrpmsProjectUpdateClient;
import com.deloitte.platform.api.srpmp.project.update.param.SrpmsProjectUpdateQueryForm;
import com.deloitte.platform.api.srpmp.project.update.vo.SrpmsProjectUpdateForm;
import com.deloitte.platform.api.srpmp.project.update.vo.SrpmsProjectUpdateVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.srpmp.project.update.service.ISrpmsProjectUpdateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author : Apeng
 * @Date : Create in 2019-04-01
 * @Description : SrpmsProjectUpdate控制器实现类
 * @Modified :
 */
@Api(tags = "项目变更操作接口", value = "项目变更操作接口")
@Slf4j
@RestController
public class SrpmsProjectUpdateController implements SrpmsProjectUpdateClient {

	@Autowired
	public ISrpmsProjectUpdateService srpmsProjectUpdateService;

	/**
	 * 获取变更记录数据
	 *
	 * @param form
	 * @return
	 */
	@Override
	@ApiOperation(value = "获取变更记录数据接口", notes = "获取变更记录数据接口")
	public Result list(@Valid SrpmsProjectUpdateQueryForm form, UserVo user, DeptVo dept) {
		return new Result<IPage<SrpmsProjectUpdateVo>>().success(srpmsProjectUpdateService.list(form, user, dept));
	}

	/**
	 * 根据ID获取变更记录明细
	 *
	 * @param id
	 * @return
	 */
	@Override
	@ApiOperation(value = "根据ID获取变更记录接口", notes = "根据ID获取变更记录接口")
	public Result getById(@PathVariable long id) {
		return Result.success(srpmsProjectUpdateService.queryById(id));
	}

	/**
	 * 保存操作
	 *
	 * @param form
	 * @param user
	 * @return
	 */
	@Override
	@ApiOperation(value = "保存变更记录接口", notes = "保存变更记录接口")
	@ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
	public Result save(
			@RequestBody @ApiParam(name = "SrpmsProjectUpdateForm", value = "新增SrpmsProjectUpdate的form表单", required = true) SrpmsProjectUpdateForm form,
			UserVo user, DeptVo dept) {
		return Result.success(srpmsProjectUpdateService.saveOrUpdate(form, user, dept));
	}

	/**
	 * 点击项目变更查询最新版本数据操作
	 *
	 * @param form
	 * @return
	 */
	@Override
	@ApiOperation(value = "点击项目变更查询最新版本数据操作", notes = "点击项目变更查询最新版本数据操作")
	public Result addNewRecord(@Valid SrpmsProjectUpdateQueryForm form, UserVo user) {
		return Result.success(srpmsProjectUpdateService.addNewRecord(form, user));
	}

	/**
	 * 提交操作
	 *
	 * @param form
	 * @param user
	 * @return
	 */
	@Override
	@ApiOperation(value = "提交变更记录接口", notes = "提交变更记录接口")
	@ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
	public Result submit(
			@RequestBody @ApiParam(name = "SrpmsProjectUpdateForm", value = "新增SrpmsProjectUpdate的form表单", required = true) SrpmsProjectUpdateForm form,
			UserVo user, DeptVo dept) {
		return Result.success(srpmsProjectUpdateService.submit(form, user, dept));
	}

	/**
	 * 根据ID获取变更记录明细画面所需数据格式
	 *
	 * @param id
	 * @return
	 */
	@Override
	@ApiOperation(value = "根据ID获取变更记录接口", notes = "根据ID获取变更记录接口")
	public Result show(@PathVariable long id) {
		//srpmsProjectUpdateService.show(id)
		return Result.success();
	}
	

	/**
	 * 根据ID获取变更记录明细画面所需数据格式
	 *
	 * @param id
	 * @return
	 */
	@Override
	@ApiOperation(value = "根据ID获取变更记录接口", notes = "根据ID获取变更记录接口")
	public Result delete(@PathVariable long id) {
		srpmsProjectUpdateService.delete(id);
		return Result.success();
	}
}