package com.deloitte.services.srpmp.relust.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.TaskNodeActionVO;
import com.deloitte.platform.api.srpmp.relust.param.SrpmsResultQueryForm;
import com.deloitte.platform.api.srpmp.relust.vo.SrpmsResultForm;
import com.deloitte.platform.api.srpmp.relust.vo.SrpmsResultVo;
import com.deloitte.services.srpmp.relust.entity.SrpmsResult;

/**
 * @Author : pengchao
 * @Date : Create in 2019-04-27
 * @Description : SrpmsResult服务类接口	
 * @Modified :
 */
public interface ISrpmsResultService extends IService<SrpmsResult> {

	/**
	 * 分页查询
	 * 
	 * @param queryForm
	 * @return IPage<SrpmsResult>
	 */
	JSONObject selectPage(SrpmsResultQueryForm queryForm, UserVo userVo, DeptVo dept);

	SrpmsResultVo queryById(Long id);

	/**
	 * 保存
	 * 
	 * @param form
	 * @return
	 */
	String saveOrUpdate(SrpmsResultForm form, UserVo userVo, DeptVo deptVo);

	/**
	 * 提交
	 *
	 * @param form
	 * @return
	 */
	void submit(SrpmsResultForm form, UserVo userVo, DeptVo deptVo);

	void approvePassBpm(TaskNodeActionVO actionVO, DeptVo deptVo);

	void refuseBpm(TaskNodeActionVO actionVO);

	/**
	 * 根据ID删除数据
	 *
	 * @param id
	 */
	void delete(long id);

}
