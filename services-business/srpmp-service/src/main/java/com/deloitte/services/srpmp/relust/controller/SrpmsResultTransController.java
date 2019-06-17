package com.deloitte.services.srpmp.relust.controller;

import javax.validation.Valid;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.TaskNodeActionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.platform.api.srpmp.relust.client.SrpmsResultTransClient;
import com.deloitte.platform.api.srpmp.relust.vo.SrpmsResultTransForm;
import com.deloitte.platform.api.srpmp.relust.vo.SrpmsResultTransVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.srpmp.relust.entity.SrpmsResultTrans;
import com.deloitte.services.srpmp.relust.service.ISrpmsResultTransService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author : pengchao
 * @Date : Create in 2019-04-27
 * @Description : SrpmsResultTrans控制器实现类
 * @Modified :
 */
@Api(tags = "SrpmsResultTrans操作接口")
@Slf4j
@RestController
public class SrpmsResultTransController implements SrpmsResultTransClient {

	@Autowired
	public ISrpmsResultTransService srpmsResultTransService;

	@Override
	@ApiOperation(value = "新增SrpmsResultTrans", notes = "新增一个SrpmsResultTrans")
	@ApiResponses(
			@ApiResponse(code = 200, message = "处理成功", response = Result.class)
	)
	public Result save(
			@Valid @RequestBody @ApiParam(name = "srpmsResultTransForm", value = "新增SrpmsResultTrans的form表单", required = true) SrpmsResultTransForm srpmsResultTransForm) {
		log.info("form:", srpmsResultTransForm.toString());
		return Result.success(srpmsResultTransService.saveOrUpdate(srpmsResultTransForm));
	}

	@Override
	@ApiOperation(value = "获取SrpmsResultTrans", notes = "获取成果ID的SrpmsResultTrans信息")
	@ApiImplicitParam(paramType = "path", name = "id", value = "SrpmsResultTrans的ID", required = true, dataType = "long")
	public Result<SrpmsResultTransVo> get(@PathVariable long id) {
		log.info("get with id:{}", id);
		return Result.success(srpmsResultTransService.queryByResultId(id));
	}

	@Override
	@ApiOperation(value = "获取SrpmsResultTrans", notes = "获取指定ID的SrpmsResultTrans信息")
	@ApiImplicitParam(paramType = "path", name = "id", value = "SrpmsResultTrans的ID", required = true, dataType = "long")
	public Result<SrpmsResultTransVo> getById(@PathVariable long id) {
		return Result.success(srpmsResultTransService.queryById(id));
	}

	@Override
	@ApiResponses(
			@ApiResponse(code = 200, message = "处理成功", response = Result.class)
	)
	public Result submit(@Valid @RequestBody @ApiParam(name = "srpmsResultTransForm", value = "新增SrpmsResultTrans的form表单", required = true)SrpmsResultTransForm srpmsResultTransForm, UserVo userVo, DeptVo deptVo) {
		srpmsResultTransService.submit(srpmsResultTransForm, userVo, deptVo);
		return Result.success("");
	}

	@Override
	@ApiOperation(value = "成果管理审批通过(BPM)", notes = "成果管理审批通过(BPM)")
	public Result auditApprove(
			@RequestBody @ApiParam(name = "TaskNodeActionVO", value = "TaskNodeActionVO", required = true) TaskNodeActionVO actionVO,
			DeptVo deptVo) {
		srpmsResultTransService.approvePassBpm(actionVO, deptVo);
		return Result.success("");
	}

	@Override
	@ApiOperation(value = "成果管理审批拒绝(BPM)", notes = "成果管理审批拒绝(BPM)")
	public Result refuse(
			@RequestBody @ApiParam(name = "TaskNodeActionVO", value = "TaskNodeActionVO", required = true) TaskNodeActionVO actionVO) {
		srpmsResultTransService.refuseBpm(actionVO);
		return Result.success("");
	}
}
