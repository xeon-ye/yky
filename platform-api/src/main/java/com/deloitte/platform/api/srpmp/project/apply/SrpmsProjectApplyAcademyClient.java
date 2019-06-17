package com.deloitte.platform.api.srpmp.project.apply;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.param.SrpmsProjectApplyAcademyQueryForm;
import com.deloitte.platform.api.srpmp.project.apply.vo.SrpmsProjectApplyAcademyForm;
import com.deloitte.platform.api.srpmp.project.apply.vo.SrpmsProjectApplyAcademyVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyAcademySaveVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyInnPreSubmitVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.WordImportReqVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;
import java.util.Map;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-25
 * @Description :  SrpmsProjectApplyAcademy控制器接口
 * @Modified :
 */
public interface SrpmsProjectApplyAcademyClient {

    public static final String path="/srpmp/project/apply/academy";

    /**
     * 保存更新
     * @param vo
     * @return
     */
    @PostMapping(value = path)
    Result save( @RequestBody SrpmsProjectApplyAcademySaveVo vo, DeptVo deptVo);

    /**
     * 提交申请书
     * @param vo
     * @param deptVo
     * @return
     */
    @PostMapping(value = path+"/submit")
    Result submit(@Valid @ModelAttribute SrpmsProjectApplyAcademySaveVo vo, UserVo userVo, DeptVo deptVo);


    /**
     *  POST---新增
     * @param srpmsProjectApplyAcademyForm
     * @return
     */
   /* @PostMapping(value = path)
    Result add(@Valid @ModelAttribute SrpmsProjectApplyAcademyForm srpmsProjectApplyAcademyForm);

    *//**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

   /**
     *  Patch----部分更新
     * @param  id, srpmsProjectApplyAcademyForm
     * @return
     *//*
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody SrpmsProjectApplyAcademyForm srpmsProjectApplyAcademyForm);

    *//**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<SrpmsProjectApplyAcademySaveVo> get(@PathVariable(value = "id") long id, UserVo user, DeptVo dept);


    /**
     *  POST----列表查询
     * @param   srpmsProjectApplyAcademyForm
     * @return
     *//*
    @PostMapping(value = path+"/list/conditions")
    Result<List<SrpmsProjectApplyAcademyVo>> list(@Valid @RequestBody SrpmsProjectApplyAcademyQueryForm srpmsProjectApplyAcademyQueryForm);


    *//**
     *  POST----复杂查询
     * @param  srpmsProjectApplyAcademyQueryForm
     * @return
     *//*
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<SrpmsProjectApplyAcademyVo>> search(@Valid @RequestBody SrpmsProjectApplyAcademyQueryForm srpmsProjectApplyAcademyQueryForm);
*/
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
    Result<SrpmsProjectApplyAcademySaveVo> importWord(@Valid @ModelAttribute WordImportReqVo importReqVo);
}
