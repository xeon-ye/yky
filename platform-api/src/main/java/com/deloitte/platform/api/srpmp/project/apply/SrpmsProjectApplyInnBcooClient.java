package com.deloitte.platform.api.srpmp.project.apply;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyInnBcooSaveVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyInnPreSubmitVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.WordImportReqVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-18
 * @Description :  SrpmsProjectApplyInnBcoo控制器接口
 * @Modified :
 */
public interface SrpmsProjectApplyInnBcooClient {

    public static final String path="/srpmp/project/apply/innovate/innBcoo";

    /**
     *  POST---新增
     * @param srpmsProjectApplyInnBcooForm
     * @return
     */
    @PostMapping(value = path)
    Result save(@Valid @ModelAttribute SrpmsProjectApplyInnBcooSaveVo srpmsProjectApplyInnBcooForm,  DeptVo deptVo);

    /**
     *  POST---新增
     * @param srpmsProjectApplyInnBcooForm
     * @return
     */
    @PostMapping(value = path + "/submit")
    Result submit(@Valid @ModelAttribute SrpmsProjectApplyInnBcooSaveVo srpmsProjectApplyInnBcooForm, UserVo userVo, DeptVo deptVo);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<SrpmsProjectApplyInnBcooSaveVo> get(@PathVariable(value="id") long id, UserVo user, DeptVo dept);


    /**
     *  GET----导出
     * @param  id
     * @return
     */
    @GetMapping(value = path+"/export/{id}")
    Result export(@PathVariable(value="id") Long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo);

    /**
     *  GET----导出PDF
     * @param  id
     * @return
     */
    @GetMapping(value = path+"/exportpdf/{id}")
    Result exportPdf(@PathVariable(value="id") Long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo);



    /**
     * 导入模板
     */
    @PostMapping(value = path+"/import")
    Result<SrpmsProjectApplyInnBcooSaveVo> importWord(@Valid @ModelAttribute WordImportReqVo reqVo);

}
