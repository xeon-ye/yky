package com.deloitte.services.srpmp.project.budget.service;

import java.io.IOException;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyInnCooSaveVo;
import com.deloitte.platform.api.srpmp.project.budget.vo.ext.BudgetApplyVo;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.budget.entity.SrpmsProjectBudgetApply;

import freemarker.template.TemplateException;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-19
 * @Description : SrpmsProjectBudgetApply服务类接口
 * @Modified :
 */
public interface ISrpmsProjectBudgetApplyService extends IService<SrpmsProjectBudgetApply> {
	//
	// /**
	// * 从申请书更新预算书
	// * @param innPre
	// */
	// void updateBudgetFromApplyPre(SrpmsProjectApplyInnPre innPre);

	/**
	 * 根据项目ID查询项目预算书
	 * 
	 * @param projectId
	 */
	JSONObject queryBudgetApplyById(Long projectId);

	/**
	 * 查询任务预算书
	 * 
	 * @param projectId
	 * @return
	 */
	JSONObject queryBudgetTaskById(Long projectId);

	/**
	 * 保存或更新预算申请书
	 * 
	 * @param vo
	 * @return
	 */
	String saveOrUpdateBudgetApply(BudgetApplyVo vo);

	/**
	 *
	 * @param vo
	 */
	void submitBudgetApply(BudgetApplyVo vo, UserVo userVo, DeptVo deptVo);

	/**
	 *
	 * @param vo
	 */
	void submitTask(BudgetApplyVo vo, UserVo userVo, DeptVo deptVo);

	public JSONObject queryById(Long projectId, UserVo user, DeptVo dept);

	public JSONObject queryById(Long projectId, boolean taskFlg);

	public String saveOrUpdateBudgetApply(BudgetApplyVo vo, boolean taskFlg);

	/**
	 * word导出
	 * 
	 * @param projectId
	 *            项目ID
	 */
	public String exportApplyWordFile(Long projectId, UserVo userVo, DeptVo deptVo);


	public String exportApplyPdfFile(Long projectId, UserVo userVo, DeptVo deptVo);

	/**
	 * 导出任务预算书
	 * 
	 * @param projectId
	 *            项目ID
	 */
	public String exportTaskWordFile(Long projectId, UserVo userVo, DeptVo deptVo);


	public String exportTaskPdfFile(Long projectId, UserVo userVo, DeptVo deptVo);

	/**
	 * 生成预算申请书PDF文档
	 * 
	 * @param projectId
	 */
	public void generateBudgetApplyPdf(Long projectId, UserVo userVo, DeptVo deptVo);

	/**
	 * 生成预算任务书PDF文档
	 * 
	 * @param projectId
	 * @param userVo
	 * @param deptVo
	 */
	public void generateBudgetTaskPdf(Long projectId, UserVo userVo, DeptVo deptVo);

	// 生成任务书PDF
	public void generateTaskBookPdf(SrpmsProject projectEntity, UserVo userVo, DeptVo deptVo);

	/**
	 * word导入项目预算书
	 * 
	 * @param wordFileUrl
	 *            word文件URL地址
	 */
	BudgetApplyVo importWord(String wordFileUrl);

	/**
	 * word导入项目预算书
	 * 
	 * @param voJson
	 *            word文件URL地址
	 */
	public String exportFromVoJson(JSONObject voJson, String fileName) throws IOException, InterruptedException, TemplateException ;
}
