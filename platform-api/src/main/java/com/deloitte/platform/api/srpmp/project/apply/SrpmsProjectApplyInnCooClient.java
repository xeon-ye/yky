package com.deloitte.platform.api.srpmp.project.apply;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyInnBcooSaveVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyInnCooSaveVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.WordImportReqVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Author : pengchao
 * @Date : Create in 2019-03-04
 * @Description :  SrpmsProjectApplyInnCoo控制器接口
 * @Modified :
 */
public interface SrpmsProjectApplyInnCooClient {

    public static final String path="/srpmp/project/apply/innovate/cooperation";

    /**
     *  POST---保存
     * @param pageData
     * @return
     */
    @PostMapping(value = path)
    Result save(@Valid @ModelAttribute SrpmsProjectApplyInnCooSaveVo pageData,  DeptVo deptVo);

    /**
     *  POST--提交
     * @param pageData
     * @return
     */
    @PostMapping(value = path + "/submit")
    Result submit(@Valid @ModelAttribute SrpmsProjectApplyInnCooSaveVo pageData,UserVo user , DeptVo deptVo);

    /**
     *  GET----根据ID获取
     * @param  id
     * @return
     */
    @GetMapping(value = path+"/{id}")
    Result<SrpmsProjectApplyInnCooSaveVo> get(@PathVariable(value="id") long id, UserVo user, DeptVo dept);


    /**
     *  导出----根据ID获取
     * @param  id
     * @return
     */
    @GetMapping(value = path+"/export/{id}")
    Result export(@PathVariable(value="id") long id, HttpServletRequest request, HttpServletResponse response, UserVo user, DeptVo dept);

    /**
     *  导出----根据ID获取
     * @param  id
     * @return
     */
    @GetMapping(value = path+"/exportpdf/{id}")
    Result exportPdf(@PathVariable(value="id") long id, HttpServletRequest request, HttpServletResponse response, UserVo user, DeptVo dept);

    /**
     * 导入模板
     */
    @PostMapping(value = path+"/import")
    Result<SrpmsProjectApplyInnCooSaveVo> importWord(@Valid @ModelAttribute WordImportReqVo reqVo);
}
