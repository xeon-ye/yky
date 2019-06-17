package com.deloitte.services.srpmp.project.base.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.param.SrpmsProjectQueryForm;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectVo;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;

import java.util.List;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-19
 * @Description : SrpmsProject服务类接口
 * @Modified :
 */
public interface ISrpmsProjectService extends IService<SrpmsProject> {

    public String getLeadDeptCodeByProjectId(Long id);


    public boolean isManageProjectHistory(Long leadPersonId);

    /**
     * 分页查询项目，内部系统使用
     * @param queryForm
     * @return IPage<SrpmsProjectVo>
     */
    IPage<SrpmsProjectVo> searchNoUser(SrpmsProjectQueryForm queryForm);

    /**
     * 根据projectId查询项目数据，内部系统使用
     * @param projectIds
     * @return
     */
    List<SrpmsProjectVo> listByProjectIds(String projectIds);

    /**
     *  分页查询项目
     * @param queryForm
     * @return IPage<SrpmsProject>
     */
    JSONObject selectPage(SrpmsProjectQueryForm queryForm, UserVo user, DeptVo dept);

    /**
     *  分页查询要填写任务书的项目
     * @param queryForm
     * @return IPage<SrpmsProject>
     */
    JSONObject queryTaskPage(UserVo user, DeptVo dept);

    /**
     * 删除项目
     * @param id
     */
    void delete(Long id);

    /**
     * 提交项目申请书
     * @param id
     */
    void submitProject(Long id);

    /**
     * 提交项目任务书
     * @param id
     */
    void submitTaskProject(Long id);
    
    /**
     * 审批的时候获取申请书信息
     * @param id
     */
    JSONObject getProjectApply(Long id);

    /**
     * 审批的时候获取任务书信息
     * @param id
     */
    JSONObject getProjectTask(Long id);

    /**
     * 年度报告查询项目详情
     * @param queryForm
     */
    SrpmsProject getProjectInfoForReport(SrpmsProjectQueryForm queryForm);
}
