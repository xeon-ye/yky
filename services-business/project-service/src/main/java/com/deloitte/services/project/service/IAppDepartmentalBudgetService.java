package com.deloitte.services.project.service;

import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.project.vo.DeclarationsForm;
import com.deloitte.platform.api.project.vo.DeclarationsVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author : JeaChen
 * @Date : Create in 2019/5/14 15:31
 * @Description : 部门预算项目申报业务接口类
 * @Modified:
 */
public interface IAppDepartmentalBudgetService {

    /**
     * 保存项目预算
     * @param declarationsForm
     * @return
     */
    JSONObject saveDepartmentalBudget(DeclarationsForm declarationsForm);

    /**
     * 删除
     * @param applicationId
     */
    void deleteDepeartalBudget(String applicationId);

    /**
     * 获取部门预算信息
     * @param applicationId
     * @return
     */
    DeclarationsVO getDepartmentBudget(String applicationId);

    /**
     * 提交部门预算信息
     * @param declarationsForm
     */
    void submitDepartmentBudget(DeclarationsForm declarationsForm);

    /**
     * 部门预算PDF生成
     * @param applicationId
     * @param request
     * @param response
     * @return
     */
    JSONObject generalDepartmentBudgetPDF(String applicationId, HttpServletRequest request, HttpServletResponse response);

}
