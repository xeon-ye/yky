package com.deloitte.platform.api.srpmp.project.budget;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.deloitte.platform.api.fssc.budget.vo.ProjectBudgetSummaryVo;
import com.deloitte.platform.api.fssc.carryforward.vo.DssScientificPayVo;
import com.deloitte.platform.api.srpmp.project.budget.param.SrpmsGetProjectBudgetSummaryQueryForm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.TaskNodeActionVO;
import com.deloitte.platform.api.srpmp.project.budget.param.SrpmsProjectBudgetYearQueryForm;
import com.deloitte.platform.api.srpmp.project.budget.vo.ext.BudgetApplyOutVo;
import com.deloitte.platform.api.srpmp.project.budget.vo.ext.BudgetApplyVo;
import com.deloitte.platform.common.core.entity.vo.Result;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-19
 * @Description : SrpmsProjectBudgetApply控制器接口
 * @Modified :
 */
public interface SrpmsProjectBudgetYearClient {

	public static final String path = "/srpmp/project/budget/year";

	/**
	 * 保存预算申请
	 * 
	 * @param vo
	 * @return
	 */
	@PostMapping(value = path)
	Result<Map<String, Object>> save(@ModelAttribute BudgetApplyVo vo, UserVo userVo, DeptVo deptVo);

	/**
	 * 提交预算申请
	 * 
	 * @param vo
	 * @return
	 */
	@PostMapping(value = path + "/submit")
	Result<Map<String, Object>> submit(@Valid @ModelAttribute BudgetApplyVo vo, UserVo userVo, DeptVo deptVo);

	/**
	 * 根据项目id新增一个项目的预算申请
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = path + "/project/{id}")
	Result<BudgetApplyOutVo> queryByProjectId(@PathVariable(value = "id") long id, UserVo user, DeptVo dept);

	/**
	 * 根据项目id新增一个项目的预算申请
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = path + "/delete/{id}")
	Result delete(@PathVariable(value = "id") long id, UserVo user, DeptVo dept);

	/**
	 * 根据项目id查询一个项目所有的预算申请
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = path + "/all/{id}")
    Result queryAllByProjectId(@PathVariable(value = "id") long id, UserVo user, DeptVo dept);

	/**
	 * 根据id查询一个预算申请
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = path + "/{id}")
	Result<BudgetApplyOutVo> queryById(@PathVariable(value = "id") Long id, UserVo userVo, DeptVo deptVo);

	/**
	 * 导出预算书word文件
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = path + "/export/{id}")
	Result<BudgetApplyOutVo> export(@PathVariable(value = "id") long id, String token, HttpServletRequest request,
			HttpServletResponse response);


	/**
	 * 导出预算书PDF文件
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(value = path + "/exportpdf/{id}")
	Result<BudgetApplyOutVo> exportPdf(@PathVariable(value = "id") long id, String token, HttpServletRequest request,
									HttpServletResponse response);

	/**
	 * 查询预算申请列表
	 *
	 * @param queryForm
	 * @param userVo
	 * @param deptVo
	 * @return
	 */
	@GetMapping(value = path + "/list")
	Result<BudgetApplyOutVo> list(SrpmsProjectBudgetYearQueryForm queryForm, UserVo userVo, DeptVo deptVo);

	/**
	 * 创新工程预算审批通过操作
	 *
	 * @param actionVO
	 * @param deptVo
	 * @return
	 */
	@PostMapping(value = path + "/audit/approve/budget")
	Result auditApproveBudget(@Valid @RequestBody TaskNodeActionVO actionVO, DeptVo deptVo);

	/**
	 * 创新工程预算审批拒绝操作
	 *
	 * @param actionVO
	 * @return
	 */
	@PostMapping(value = path + "/refuse/budget")
	Result refuseBudget(@Valid @RequestBody TaskNodeActionVO actionVO);

	/**
	 * 查询预算申请列表
	 *
	 * @param queryForm
	 * @param userVo
	 * @param deptVo
	 * @return
	 */
	@GetMapping(value = path + "/projectList")
	Result<IPage<SrpmsProjectVo>> projectList(SrpmsProjectBudgetYearQueryForm queryForm, UserVo userVo, DeptVo dept);

	/**
	 * 预算支出情况
	 * @param queryForm
	 * @return
	 */
	@PostMapping(value = path + "/getProjectBudgetSummary")
	Result<ProjectBudgetSummaryVo> getProjectBudgetSummary(@Valid @RequestBody SrpmsGetProjectBudgetSummaryQueryForm queryForm);

	/**
	 * 预算支出明细
	 * @param queryForm
	 * @return
	 */
	@PostMapping(value = path + "/getProjectBudgetDetail")
	Result<IPage<DssScientificPayVo>> getProjectBudgetDetail(@Valid @RequestBody SrpmsGetProjectBudgetSummaryQueryForm queryForm);

}