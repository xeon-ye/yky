package com.deloitte.platform.api.srpmp.project.task;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.WordImportReqVo;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskReformForm;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskReformVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Date : Create in 2019-03-13
 * @Description :  SrpmsProjectTaskReformClient控制器接口
 * @Modified :
 */
public interface SrpmsProjectTaskReformClient {

    public static final String path="/srpmp/project/task/reform";

    /**
     *  GET----根据ID获取
     * @param  id
     * @return
     */
    @GetMapping(value = path+"/{id}")
    Result get(@PathVariable(value = "id") long id);

    /**
     * POST---保存
     *
     * @param taskForm
     * @return
     */
    @PostMapping(value = path)
    Result save(@ModelAttribute SrpmsProjectTaskReformForm taskForm);

    /**
     *  POST---提交
     * @param taskFo
     * @return
     */
    @PostMapping(value = path+"/submit")
    Result submit(@Valid @ModelAttribute SrpmsProjectTaskReformForm taskFo, UserVo userVo, DeptVo deptVorm);


    /**
     * 导出任务书 tanwx
     * @param id
     * @param request
     * @param response
     * @param userVo
     * @param deptVo
     * @return
     */
    @GetMapping(value = path+"/export/{id}")
    public Result export(@PathVariable(value = "id") Long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo);

    /**
     * 导出任务书 tanwx
     * @param id
     * @param request
     * @param response
     * @param userVo
     * @param deptVo
     * @return
     */
    @GetMapping(value = path+"/exportpdf/{id}")
    public Result exportPdf(@PathVariable(value = "id") Long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo);


    /**
     * 导入任务书 tanwx
     * @param importReqVo
     * @return
     */
    @PostMapping(value = path+"/import")
    Result<SrpmsProjectTaskReformVo> importWord(@Valid @ModelAttribute WordImportReqVo importReqVo);
}
