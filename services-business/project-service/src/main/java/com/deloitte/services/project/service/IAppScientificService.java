package com.deloitte.services.project.service;

import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.project.vo.ScientificResearchForm;
import com.deloitte.platform.api.project.vo.ScientificResearchVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author : JeaChen
 * @Date : Create in 2019/5/14 15:33
 * @Description : 科研修购项目申报业务接口类
 * @Modified:
 */
public interface IAppScientificService {

    /**
     * 保存科研修购信息
     * @param scientificResearchForm
     * @return
     */
    JSONObject saveScientific(ScientificResearchForm scientificResearchForm);


    /**
     * 删除
     * @param applicationId 申报书ID
     */
    void deleteScientific(String applicationId);

    /**
     * 获取科研修购信息
     * @param applicationId
     * @return
     */
    ScientificResearchVo getScientific(String applicationId);

    /**
     * 提交科研修购信息
     * @param scientificResearchForm
     */
    void submitScientific(ScientificResearchForm scientificResearchForm);

    /**
     * 科研修购PDF生成
     * @param applicationId
     * @param request
     * @param response
     */
    void generalScientificPDF(String applicationId, HttpServletRequest request, HttpServletResponse response);

}
