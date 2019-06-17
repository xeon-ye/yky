package com.deloitte.platform.api.srpmp.project.mpr;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.WordImportReqVo;
import com.deloitte.platform.api.srpmp.project.mpr.param.MprUnitEvaInfoQueryForm;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprUnitEvaInfoForm;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprUnitEvaInfoVo;
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
 * @Description :  MprUnitEvaInfo控制器接口
 * @Modified :
 */
public interface MprUnitEvaInfoClient {

    public static final String path="/srpmp/project/mpr/unit";

    /**
     * GET---导出文档（附件九）
     * @param id
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = path + "/export/{id}")
    Result exportWord(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response);

    /**
     *  POST---导入文档（附件九）
     * @param reqVo
     * @return
     */
    @PostMapping(value = path + "/import")
    Result<MprUnitEvaInfoVo> importWord(@Valid @ModelAttribute WordImportReqVo reqVo);

    /**
     * POST---保存和更新（附件九）
     * @param unitEvaInfoForm
     * @return
     */
    @PostMapping(value = path + "/save")
    Result saveAndUpdate(@Valid @ModelAttribute MprUnitEvaInfoForm unitEvaInfoForm, UserVo user, DeptVo dept);

    /**
     * 获取数据
     * @param unitEvaInfoQueryForm
     * @param dept
     * @return
     */
    @PostMapping(value = path + "/get")
    Result<MprUnitEvaInfoVo> getOne(@ModelAttribute MprUnitEvaInfoQueryForm unitEvaInfoQueryForm, UserVo user, DeptVo dept);

}
