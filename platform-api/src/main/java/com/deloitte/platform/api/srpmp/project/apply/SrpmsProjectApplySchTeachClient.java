package com.deloitte.platform.api.srpmp.project.apply;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.param.SrpmsProjectApplySchTeachQueryForm;
import com.deloitte.platform.api.srpmp.project.apply.vo.SrpmsProjectApplySchTeachForm;
import com.deloitte.platform.api.srpmp.project.apply.vo.SrpmsProjectApplySchTeachVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyAcademySaveVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplySchStuSaveVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplySchTeachSaveVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.WordImportReqVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-20
 * @Description :  SrpmsProjectApplySchTeach控制器接口
 * @Modified :
 */
public interface SrpmsProjectApplySchTeachClient {

    public static final String path="/srpmp/project/apply/school/teach";

    /**
     * POST---保存
     * @param vo
     * @return
     */
    @PostMapping(value = path)
    Result save(@Valid @ModelAttribute SrpmsProjectApplySchTeachSaveVo vo, DeptVo dept);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    @GetMapping(value = path+"/{id}")
    Result<SrpmsProjectApplySchTeachSaveVo> get(@PathVariable(value = "id") long id, UserVo user, DeptVo dept);

    /**
     * 提交申请书
     * @param vo
     * @param deptVo
     * @return
     */
    @PostMapping(value = path+"/submit")
    Result submit(@Valid @ModelAttribute SrpmsProjectApplySchTeachSaveVo vo,UserVo userVo, DeptVo deptVo);


    /**
     * Delete---删除
     * @param id
     * @return
     */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);



    /**
     *  导出----根据ID获取
     * @param  id
     * @return
     */
    @GetMapping(value = path+"/export/{id}")
    Result export(@PathVariable(value="id") long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo);

    /**
     *  导出----根据ID获取
     * @param  id
     * @return
     */
    @GetMapping(value = path+"/exportpdf/{id}")
    Result exportPdf(@PathVariable(value="id") long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo);

    /**
     * 导入模板
     */
    @PostMapping(value = path+"/import")
    Result<SrpmsProjectApplySchTeachSaveVo> importWord(@Valid @ModelAttribute WordImportReqVo reqVo);
    /**
     *  POST---新增
     * @param srpmsProjectApplySchTeachForm
     * @return
     *//*
     @PostMapping(value = path)
     Result add(@Valid @ModelAttribute SrpmsProjectApplySchTeachForm srpmsProjectApplySchTeachForm);

     *  Patch----部分更新
     * @param  id, srpmsProjectApplySchTeachForm
     * @return
     *//*
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody SrpmsProjectApplySchTeachForm srpmsProjectApplySchTeachForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    *//*
    @GetMapping(value = path+"/{id}")
    Result<SrpmsProjectApplySchTeachVo> get(@PathVariable(value = "id") long id);
*/



/**
     *  POST----列表查询
     * @param   srpmsProjectApplySchTeachForm
     * @return
     *//*

    @PostMapping(value = path+"/list/conditions")
    Result<List<SrpmsProjectApplySchTeachVo>> list(@Valid @RequestBody SrpmsProjectApplySchTeachQueryForm srpmsProjectApplySchTeachQueryForm);


    */
/**
     *  POST----复杂查询
     * @param  srpmsProjectApplySchTeachQueryForm
     * @return
     *//*

    @PostMapping(value = path+"/page/conditions")
    Result<IPage<SrpmsProjectApplySchTeachVo>> search(@Valid @RequestBody SrpmsProjectApplySchTeachQueryForm srpmsProjectApplySchTeachQueryForm);
*/

}
