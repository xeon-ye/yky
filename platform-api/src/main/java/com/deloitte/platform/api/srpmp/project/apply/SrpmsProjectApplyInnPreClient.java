package com.deloitte.platform.api.srpmp.project.apply;


import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyInnPreSaveVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyInnPreSubmitVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.WordImportReqVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-19
 * @Description :  SrpmsProjectApplyInnPre控制器接口
 * @Modified :
 */
public interface SrpmsProjectApplyInnPreClient {

    String path="/srpmp/project/apply/innovate/pre";

    /**
     *  POST---保存
     * @param srpmsProjectApplyInnPreForm
     * @return
     */
    @PostMapping(value = path)
    Result<Map<String, Object>> save(@ModelAttribute SrpmsProjectApplyInnPreSubmitVo srpmsProjectApplyInnPreForm,  DeptVo deptVo);

    @PostMapping(value = path+"/submit")
    Result<Map<String, Object>> submit(@Valid @ModelAttribute SrpmsProjectApplyInnPreSubmitVo vo, UserVo userVo, DeptVo deptVo);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
   @GetMapping(value = path+"/{id}")
    Result<SrpmsProjectApplyInnPreSubmitVo> get(@PathVariable(value="id") long id, UserVo user, DeptVo dept);

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
    Result<SrpmsProjectApplyInnPreSubmitVo> importWord(@Valid @ModelAttribute WordImportReqVo reqVo);

}
