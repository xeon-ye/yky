package com.deloitte.services.srpmp.project.budget.controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.deloitte.platform.api.fssc.budget.feign.ProjectBudgetFeignService;
import com.deloitte.platform.api.fssc.budget.vo.ProjectBudgetSummaryVo;
import com.deloitte.platform.api.srpmp.project.budget.param.SrpmsGetProjectBudgetSummaryQueryForm;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectService;
import com.netflix.discovery.converters.Auto;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.param.SrpmsProjectQueryForm;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.TaskNodeActionVO;
import com.deloitte.platform.api.srpmp.project.budget.SrpmsProjectBudgetYearClient;
import com.deloitte.platform.api.srpmp.project.budget.param.SrpmsProjectBudgetYearQueryForm;
import com.deloitte.platform.api.srpmp.project.budget.vo.ext.BudgetApplyOutVo;
import com.deloitte.platform.api.srpmp.project.budget.vo.ext.BudgetApplyVo;
import com.deloitte.platform.api.srpmp.project.budget.vo.ext.BudgetSimpleOutVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.common.enums.ProjectCategoryEnums;
import com.deloitte.services.srpmp.common.enums.SrpmsProjectStatusEnums;
import com.deloitte.services.srpmp.project.budget.service.ISrpmsProjectBudgetApplyYearService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-19
 * @Description : SrpmsProjectBudgetApply控制器实现类
 * @Modified :
 */
@Api(tags = "预算申请（年度）-创新工程操作接口", value = "预算书（年度）-创新工程操作接口")
@Slf4j
@RestController
public class SrpmsProjectBudgetYearController implements SrpmsProjectBudgetYearClient {

	@Autowired
	public ISrpmsProjectBudgetApplyYearService budgetYearService;

	@Autowired
	public ISrpmsProjectService projectService;

	@Autowired
	private ProjectBudgetFeignService projectBudgetFeignService;

	@Override
	public Result save(
			@RequestBody @ApiParam(name = "srpmsProjectBudgetApplyForm", value = "新增SrpmsProjectBudgetApply的form表单", required = true) BudgetApplyVo vo,
			UserVo user, DeptVo dept) {
		log.info("输入参数是" + JSON.toJSONString(vo));
		String id = budgetYearService.saveOrUpdate(vo, user, dept);
		return Result.success(id);
	}

	@Override
	public Result submit(
			@RequestBody @ApiParam(name = "srpmsProjectBudgetApplyForm", value = "新增SrpmsProjectBudgetApply的form表单", required = true) BudgetApplyVo vo,
			UserVo userVo, DeptVo deptVo) {
		log.info("输入参数是" + JSON.toJSONString(vo));
		budgetYearService.submit(vo, userVo, deptVo);
		return Result.success();
	}

	@Override
	public Result<BudgetApplyOutVo> queryByProjectId(@PathVariable long id, UserVo user, DeptVo dept) {
		log.info("输入参数是" + id);
		BudgetApplyOutVo outVo = budgetYearService.queryByProjectId(id, user, dept);
		return Result.success(outVo);
	}

	@Override
	public Result delete(@PathVariable(value = "id") long id, UserVo user, DeptVo dept) {
		budgetYearService.delete(id);
		return Result.success();
	}

	@Override
	public Result<BudgetApplyOutVo> queryById(@PathVariable Long id, UserVo userVo, DeptVo deptVo) {
		log.info("输入参数是" + id);
		BudgetApplyOutVo outVo = budgetYearService.queryById(id);
		return Result.success(outVo);
	}

	@Override
	public Result<BudgetApplyOutVo> queryAllByProjectId(@PathVariable long id, UserVo user, DeptVo dept) {
		List<BudgetSimpleOutVo> result = budgetYearService.queryAllById2(id);
		return new Result<BudgetApplyOutVo>().sucess(result);
	}

	@Override
	public Result list(SrpmsProjectBudgetYearQueryForm queryForm, UserVo user, DeptVo dept) {

		return Result.success(budgetYearService.list(queryForm, user, dept));
	}

	/**
	 * 创新工程预算审批通过操作
	 *
	 * @param actionVO
	 * @param deptVo
	 * @return
	 */
	@Override
	@ApiOperation(value = "创新工程预算审批通过(BPM)", notes = "创新工程预算审批通过(BPM)")
	public Result auditApproveBudget(
			@RequestBody @ApiParam(name = "TaskNodeActionVO", value = "TaskNodeActionVO", required = true) TaskNodeActionVO actionVO,
			DeptVo deptVo) {
		budgetYearService.budgetApprovePassBpm(actionVO, deptVo);
		return Result.success("");
	}

	/**
	 * 创新工程预算审批拒绝操作
	 *
	 * @param actionVO
	 * @return
	 */
	@Override
	@ApiOperation(value = "创新工程预算审批拒绝(BPM)", notes = "创新工程预算审批拒绝(BPM)")
	public Result refuseBudget(
			@RequestBody @ApiParam(name = "TaskNodeActionVO", value = "TaskNodeActionVO", required = true) TaskNodeActionVO actionVO) {
		budgetYearService.budgetRefuseBpm(actionVO);
		return Result.success("");
	}

	@Override
	public Result export(@PathVariable(value = "id") long budId, String token, HttpServletRequest request,
			HttpServletResponse response) {

		response.setCharacterEncoding("utf-8");
		response.setContentType("application/octet-stream");
		byte[] buffer = new byte[1024];
		InputStream is = null;
		BufferedInputStream bis = null;
		OutputStream os = null;
		try {
			String fileUrl = budgetYearService.exportWordFile(budId, token);

			String downName = "创新工程项目预算申请书_" + DateFormatUtils.format(new Date(), "yyyyMMdd") + ".docx";

			response.setHeader("Content-Disposition",
					"attachment;fileName=" + java.net.URLEncoder.encode(downName, "UTF-8"));
			is = new FileInputStream(fileUrl);
			bis = new BufferedInputStream(is);
			os = response.getOutputStream();
			int i = bis.read(buffer);
			while (i != -1) {
				os.write(buffer, 0, i);
				i = bis.read(buffer);
			}
		} catch (Exception e) {
			log.error("文件读取异常！", e);
			throw new BaseException(PlatformErrorType.SYSTEM_ERROR, e.getMessage(), e.getCause());
		} finally {
			IOUtils.closeQuietly(os);
			IOUtils.closeQuietly(bis);
			IOUtils.closeQuietly(is);
		}
		return Result.success();
	}


	@Override
	public Result exportPdf(@PathVariable(value = "id") long budId, String token, HttpServletRequest request,
						 HttpServletResponse response) {

		response.setCharacterEncoding("utf-8");
		response.setContentType("application/octet-stream");
		byte[] buffer = new byte[1024];
		InputStream is = null;
		BufferedInputStream bis = null;
		OutputStream os = null;
		try {
			String fileUrl = budgetYearService.exportPdfFile(budId, token);

			String downName = "创新工程项目预算申请书_" + DateFormatUtils.format(new Date(), "yyyyMMdd") + ".pdf";

			response.setHeader("Content-Disposition",
					"attachment;fileName=" + java.net.URLEncoder.encode(downName, "UTF-8"));
			is = new FileInputStream(fileUrl);
			bis = new BufferedInputStream(is);
			os = response.getOutputStream();
			int i = bis.read(buffer);
			while (i != -1) {
				os.write(buffer, 0, i);
				i = bis.read(buffer);
			}
		} catch (Exception e) {
			log.error("文件读取异常！", e);
			throw new BaseException(PlatformErrorType.SYSTEM_ERROR, e.getMessage(), e.getCause());
		} finally {
			IOUtils.closeQuietly(os);
			IOUtils.closeQuietly(bis);
			IOUtils.closeQuietly(is);
		}
		return Result.success();
	}

	@Override
	@ApiOperation(value = "项目变更查询SrpmsProject", notes = "根据条件查询科研变更项目分页数据")
	public Result projectList(SrpmsProjectBudgetYearQueryForm queryForm, UserVo user, DeptVo dept) {
		SrpmsProjectQueryForm srpmsProjectQueryForm = new SrpmsProjectQueryForm();
		log.info("search with srpmsProjectQueryForm:", srpmsProjectQueryForm.toString());

		srpmsProjectQueryForm.setProjectName(queryForm.getProjectName());

		srpmsProjectQueryForm.setProjectNum(queryForm.getProjectNum());

		if (queryForm.getProjectType() != null && !"".equals(queryForm.getProjectType())) {
			srpmsProjectQueryForm.setProjectType(queryForm.getProjectType());
		} else {
			StringBuffer sbProjectType = new StringBuffer();
			sbProjectType.append(ProjectCategoryEnums.INNOVATE_BCOO.getHeader());
			sbProjectType.append(",");
			sbProjectType.append(ProjectCategoryEnums.INNOVATE_COO.getHeader());
			sbProjectType.append(",");
			sbProjectType.append(ProjectCategoryEnums.INNOVATE_PRE.getHeader());
			srpmsProjectQueryForm.setProjectType(sbProjectType.toString());
		}

		srpmsProjectQueryForm.setStatus(SrpmsProjectStatusEnums.PEROJECT_TASK_PASS.getCode().concat(",")
				.concat(SrpmsProjectStatusEnums.PEROJECT_SET_UP.getCode()));
		srpmsProjectQueryForm.setTableFlag(SrpmsConstant.TABLE_FLAG_1);
		return new Result<IPage<SrpmsProjectVo>>()
				.sucess(projectService.selectPage(srpmsProjectQueryForm, user, dept));
	}

	@Override
	@ApiOperation(value = "预算支出情况统计", notes = "预算支出情况统计")
	public Result<ProjectBudgetSummaryVo> getProjectBudgetSummary(@RequestBody @Valid SrpmsGetProjectBudgetSummaryQueryForm queryForm) {
		SrpmsProject project = projectService.getById(queryForm.getProjectId());
		if (project == null) {
			throw new BaseException(SrpmsErrorType.PROJECT_NOT_FOUND);
		}
		Result<ProjectBudgetSummaryVo> summaryResult = projectBudgetFeignService.getProjectBudgetSummary(project.getProjectNum(), "", queryForm.getYear());
		return new Result<ProjectBudgetSummaryVo>().sucess(summaryResult.getData());
	}
}