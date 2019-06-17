package com.deloitte.services.project.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.project.param.ProjectsQueryForm;
import com.deloitte.platform.api.project.vo.CancelProjectFrom;
import com.deloitte.platform.api.project.vo.ProjectLibraryForm;
import com.deloitte.platform.api.project.vo.ProjectsForm;
import com.deloitte.services.project.entity.Projects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-26
 * @Description : Projects服务类接口
 * @Modified :
 */
public interface IProjectsService extends IService<Projects> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<Projects>
     */
    IPage<Projects> selectPage(ProjectsQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<Projects>
     */
    List<Projects> selectList(ProjectsQueryForm queryForm);

    /**
     * 项目取消--保存
     * @param cancelProjectFrom
     */
    void saveCancelProject(CancelProjectFrom cancelProjectFrom);

    /**
     * 项目取消--提交
     * @param cancelProjectFrom
     */
    void submitCancelProject(CancelProjectFrom cancelProjectFrom);

    /**
     * 项目库Excel导出
     * @param queryForm
     * @param request
     * @param response
     * @return
     */
    JSONObject exportExcelForProLib(ProjectLibraryForm queryForm, HttpServletRequest request, HttpServletResponse response);

    /**
     * 根据项目ID更改项目状态
     * @param projectId
     * @param projectTypeCode
     */
    JSONArray exchangeProjectStatus(String projectId, String projectTypeCode);

}
