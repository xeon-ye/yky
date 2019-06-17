package com.deloitte.platform.api.srpmp.project.base;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyInnPreSubmitVo;
import com.deloitte.platform.api.srpmp.project.task.vo.ext.SrpmsProjectTaskInnExtVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.WordImportReqVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @Author : pengchao
 * @Date : Create in 2019-03-11
 * @Description :  SrpmsProjectTaskInn控制器接口
 * @Modified :
 */
public interface SrpmsProjectTaskInnClient {

    public static final String path="/srpmp/project/task/inn";

    /**
     *  POST---保存
     * @param vo
     * @return
     */
    @PostMapping(value = path)
    Result save(@ModelAttribute SrpmsProjectTaskInnExtVo vo, UserVo user);

    /**
     *  POST---提交
     * @param vo
     * @return
     */
    @PostMapping(value = path+"/submit")
    Result submit(@Valid @ModelAttribute SrpmsProjectTaskInnExtVo vo, UserVo user, DeptVo deptVo);


    /**
     *  GET----根据ID获取
     * @param  id
     * @return
     */
    @GetMapping(value = path+"/{id}")
    Result<SrpmsProjectTaskInnExtVo> get(@PathVariable(value = "id") long id);

    /**
     *  GET----根据ID获取
     * @param  id
     * @return
     */
    @GetMapping(value = path+"/export/{id}")
    Result export(@PathVariable(value="id") long id, HttpServletRequest request, HttpServletResponse response);

    /**
     * 导入模板
     */
    @PostMapping(value = path+"/import")
    Result<SrpmsProjectApplyInnPreSubmitVo> importWord(@Valid @ModelAttribute WordImportReqVo reqVo);

    /**
     * 导入先导专项任务书
     * @param reqVo
     * @return
     */
    @PostMapping(value = path+"/importTask")
    Result<SrpmsProjectTaskInnExtVo> importTaskWord(@Valid @RequestBody WordImportReqVo reqVo);

    /**
     * 导入重大协同任务书
     * @param reqVo
     * @return
     */
    @PostMapping(value = path+"/import/bcoo")
    Result<SrpmsProjectTaskInnExtVo> importBcooTaskWord(@Valid @RequestBody WordImportReqVo reqVo);

    /**
     * 导入协同创新任务书
     * @param reqVo
     * @return
     */
    @PostMapping(value = path+"/import/coo")
    Result<SrpmsProjectTaskInnExtVo> importCooTaskWord(@Valid @RequestBody WordImportReqVo reqVo);


    /**
     * 导出先导专项任务书
     * @param id
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = path+"/exportTask/{id}")
    Result exportTaskWord(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo);

    /**
     *  导出----根据ID获取
     * @param  id
     * @return
     */
    @GetMapping(value = path+"/export/bcoo/{id}")
    Result exportBcoo(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo);


    /**
     *  导出----根据ID获取
     * @param  id
     * @return
     */
    @GetMapping(value = path+"/export/coo/{id}")
    Result exportCoo(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo);


    /**
     * 导出先导专项任务书PDF
     * @param id
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = path+"/exportpdfTask/{id}")
    Result exportTaskWordPdf(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo);

    /**
     *  导出----根据ID获取PDF
     * @param  id
     * @return
     */
    @GetMapping(value = path+"/exportpdf/bcoo/{id}")
    Result exportBcooPdf(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo);


    /**
     *  导出----根据ID获取PDF
     * @param  id
     * @return
     */
    @GetMapping(value = path+"/exportpdf/coo/{id}")
    Result exportCooPdf(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo);

}
