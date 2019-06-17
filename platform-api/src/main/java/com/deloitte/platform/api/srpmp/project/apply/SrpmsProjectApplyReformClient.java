package com.deloitte.platform.api.srpmp.project.apply;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.SrpmsProjectApplyReformForm;
import com.deloitte.platform.api.srpmp.project.apply.vo.SrpmsProjectApplyReformVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.WordImportReqVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 科技体制改革项目
 */
public interface SrpmsProjectApplyReformClient {

    public static final String path="/srpmp/project/apply/reform";

    /**
     * 保存更新
     * @param vo
     * @return
     */
    @PostMapping(value = path)
    Result save(@RequestBody SrpmsProjectApplyReformForm vo, DeptVo deptVo);

    /**
     * 提交申请书
     * @param vo
     * @param deptVo
     * @return
     */
    @PostMapping(value = path+"/submit")
    Result submit(@Valid @ModelAttribute SrpmsProjectApplyReformForm vo, UserVo userVo, DeptVo deptVo);

    /**
     *  GET----根据ID获取
     * @param  id
     * @return
     */
    @GetMapping(value = path+"/{id}")
    Result<SrpmsProjectApplyReformVo> get(@PathVariable(value = "id") long id, UserVo user, DeptVo dept);


    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);
    /**
     * word导出 tanwx
     */
    @GetMapping(value = path+"/export/{id}")
    Result export(Long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo);

    /**
     * word导出 tanwx
     */
    @GetMapping(value = path+"/exportpdf/{id}")
    Result exportPdf(Long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo);

    /**
     * 导入模板
     */
    @PostMapping(value = path+"/import")
    Result<SrpmsProjectApplyReformVo> importWord(@Valid @ModelAttribute WordImportReqVo importReqVo);
}
