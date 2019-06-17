package com.deloitte.platform.api.srpmp.project.mpr;

import com.deloitte.platform.api.srpmp.project.base.vo.ext.WordImportReqVo;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprProjEvaInfoForm;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprProjEvaInfoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description :  MprProjEvaInfo控制器接口
 * @Modified :
 */
public interface MprProjEvaInfoClient {

    public static final String path="/srpmp/project/mpr/proj";

    /**
     * GET---导出文档（附件六）
     * @param projectId
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = path + "/export/{projectId}")
    Result exportWord(@PathVariable("projectId") Long projectId, HttpServletRequest request, HttpServletResponse response);

    /**
     *  POST---导入文档（附件六）
     * @param reqVo
     * @return
     */
    @PostMapping(value = path + "/import")
    Result<MprProjEvaInfoVo> importWord(@Valid @ModelAttribute WordImportReqVo reqVo);

    /**
     * POST---保存和更新（附件六）
     * @param projEvaInfoForm
     * @return
     */
    @PostMapping(value = path + "/save")
    Result saveAndUpdate(@Valid @ModelAttribute MprProjEvaInfoForm projEvaInfoForm);

    /**
     * 获取数据
     * @param projectNo
     * @return
     */
    @PostMapping(value = path + "/get/{projectId}")
    Result<MprProjEvaInfoVo> getOne(@PathVariable("projectId") Long projectNo);

}
