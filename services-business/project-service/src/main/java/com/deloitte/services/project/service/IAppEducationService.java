package com.deloitte.services.project.service;

import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.project.vo.EducationForm;
import com.deloitte.platform.api.project.vo.EducationVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author : JeaChen
 * @Date : Create in 2019/5/14 15:35
 * @Description : 教育修购项目申报业务接口类
 * @Modified:
 */
public interface IAppEducationService {

    /**
     * 保存教育修购信息
     * @param educationForm
     * @return
     */
    JSONObject saveEducation(EducationForm educationForm);

    /**
     * 删除
     * @param applicationId 申报书ID
     */
    void deleteEducation(String applicationId);

    /**
     * 获取教育修购信息
     * @param applicationId
     * @return
     */
    EducationVo getEducation(String applicationId);

    /**
     * 提交教育信息
     * @param educationForm
     */
    void submitEducation(EducationForm educationForm);

    /**
     * 教育修购PDF生成
     * @param applicationId
     * @param request
     * @param response
     */
    void generalEducationPDF(String applicationId, HttpServletRequest request, HttpServletResponse response);

}
