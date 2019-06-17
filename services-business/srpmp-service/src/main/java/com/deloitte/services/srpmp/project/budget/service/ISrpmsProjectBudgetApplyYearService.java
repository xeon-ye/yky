package com.deloitte.services.srpmp.project.budget.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.TaskNodeActionVO;
import com.deloitte.platform.api.srpmp.project.budget.param.SrpmsProjectBudgetYearQueryForm;
import com.deloitte.platform.api.srpmp.project.budget.vo.ext.BudgetApplyOutVo;
import com.deloitte.platform.api.srpmp.project.budget.vo.ext.BudgetApplyVo;
import com.deloitte.platform.api.srpmp.project.budget.vo.ext.BudgetSimpleOutVo;
import com.deloitte.services.srpmp.project.budget.entity.SrpmsProjectBudgetYear;

import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.List;

/**
 * @Author : pengchao
 * @Date : Create in 2019-04-17
 * @Description : SrpmsProjectBudgetApply服务类接口
 * @Modified :
 */
public interface ISrpmsProjectBudgetApplyYearService extends IService<SrpmsProjectBudgetYear> {

	/**
	 * 根据项目ID查询项目预算书
	 * 
	 * @param projectId
	 */
	BudgetApplyOutVo queryById(Long projectId);

	void delete(Long projectId);

	/**
	 * 根据项目ID查询项目预算书
	 * 
	 * @param projectId
	 */
	List<BudgetSimpleOutVo> queryAllById(Long projectId);

	/**
	 * 根据项目ID查询项目预算书
	 *
	 * @param projectId
	 */
	List<BudgetSimpleOutVo> queryAllById2(Long projectId);

	/**
	 * 根据项目ID查询项目预算书
	 * 
	 * @param projectId
	 */
	BudgetApplyOutVo queryByProjectId(Long projectId, UserVo user, DeptVo dept);

	/**
	 * 保存或更新预算申请书
	 * 
	 * @param vo
	 * @return
	 */
	String saveOrUpdate(BudgetApplyVo vo, UserVo user, DeptVo dept);

	/**
	 *
	 * @param vo
	 */
	void submit(BudgetApplyVo vo, UserVo userVo, DeptVo deptVo);

	JSONObject list(SrpmsProjectBudgetYearQueryForm queryForm, UserVo user, DeptVo dept);

	void budgetApprovePassBpm(TaskNodeActionVO actionVO, DeptVo deptVo);

	void budgetRefuseBpm(TaskNodeActionVO actionVO);

	/**
	 * word导出
	 * 
	 * @param projectId
	 *            项目ID
	 */
	public String exportWordFile(Long projectId, String token)
			throws IOException, InterruptedException, TemplateException;


	/**
	 * word导出
	 *
	 * @param projectId
	 *            项目ID
	 */
	public String exportPdfFile(Long projectId, String token)
			throws IOException, InterruptedException, TemplateException;
}
