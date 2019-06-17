package com.deloitte.services.srpmp.relust.controller;

import javax.validation.Valid;

import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.TaskNodeActionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.relust.client.SrpmsResultClient;
import com.deloitte.platform.api.srpmp.relust.param.SrpmsResultQueryForm;
import com.deloitte.platform.api.srpmp.relust.vo.SrpmsResultForm;
import com.deloitte.platform.api.srpmp.relust.vo.SrpmsResultVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.srpmp.relust.entity.SrpmsResult;
import com.deloitte.services.srpmp.relust.service.ISrpmsResultService;

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
 * @Description : SrpmsResult控制器实现类
 * @Modified :
 */
@Api(tags = "SrpmsResult操作接口")
@Slf4j
@RestController
public class SrpmsResultController implements SrpmsResultClient {

	@Autowired
	public ISrpmsResultService srpmsResultService;

	@Override
	@ApiOperation(value = "新增SrpmsResult", notes = "新增一个SrpmsResult")
	@ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
	public Result save(
			@Valid @RequestBody @ApiParam(name = "srpmsResultForm", value = "新增SrpmsResult的form表单", required = true) SrpmsResultForm srpmsResultForm, UserVo userVo, DeptVo deptVo) {
		log.info("form:", srpmsResultForm.toString());
		return Result.success(srpmsResultService.saveOrUpdate(srpmsResultForm, userVo, deptVo));
	}

	@Override
	@ApiOperation(value = "获取SrpmsResult", notes = "获取指定ID的SrpmsResult信息")
	@ApiImplicitParam(paramType = "path", name = "id", value = "SrpmsResult的ID", required = true, dataType = "long")
	public Result<SrpmsResultVo> get(@PathVariable long id) {
		log.info("get with id:{}", id);
		return Result.success(srpmsResultService.queryById(id));
	}

	@Override
	@ApiOperation(value = "根据ID删除成果管理信息", notes = "根据ID删除成果管理信息")
	@ApiImplicitParam(paramType = "path", name = "id", value = "SrpmsResult的ID", required = true, dataType = "long")
	public Result<SrpmsResultVo> delete(@PathVariable long id) {
		log.info("get with id:{}", id);
		srpmsResultService.delete(id);
		return Result.success("");
	}

	@Override
	@ApiOperation(value = "分页查询SrpmsResult", notes = "根据条件查询SrpmsResult分页数据")
	public Result<IPage<SrpmsResultVo>> search(
			@Valid @RequestBody @ApiParam(name = "srpmsResultQueryForm", value = "SrpmsResult查询参数", required = true) SrpmsResultQueryForm srpmsResultQueryForm,
			UserVo userVo, DeptVo deptVo) {
		log.info("search with srpmsResultQueryForm:", srpmsResultQueryForm.toString());
		return new Result<IPage<SrpmsResultVo>>().sucess(srpmsResultService.selectPage(srpmsResultQueryForm, userVo, deptVo));
	}

	@Override
	@ApiOperation(value = "成果管理审批通过(BPM)", notes = "成果管理审批通过(BPM)")
	public Result auditApprove(
			@RequestBody @ApiParam(name = "TaskNodeActionVO", value = "TaskNodeActionVO", required = true) TaskNodeActionVO actionVO,
			DeptVo deptVo) {
		srpmsResultService.approvePassBpm(actionVO, deptVo);
		return Result.success("");
	}

	@Override
	@ApiOperation(value = "成果管理审批拒绝(BPM)", notes = "成果管理审批拒绝(BPM)")
	public Result refuse(
			@RequestBody @ApiParam(name = "TaskNodeActionVO", value = "TaskNodeActionVO", required = true) TaskNodeActionVO actionVO) {
		srpmsResultService.refuseBpm(actionVO);
		return Result.success("");
	}

	@Override
	public Result submit(@Valid @RequestBody @ApiParam(name = "srpmsResultForm", value = "新增SrpmsResult的form表单", required = true) SrpmsResultForm srpmsResultForm, UserVo userVo, DeptVo deptVo) {
		log.info("form:", srpmsResultForm.toString());
		srpmsResultService.submit(srpmsResultForm, userVo, deptVo);
		return Result.success();
	}
}
