package com.deloitte.platform.api.srpmp.project.apply;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.param.SrpmsProjectApplySchStuQueryForm;
import com.deloitte.platform.api.srpmp.project.apply.vo.SrpmsProjectApplySchStuForm;
import com.deloitte.platform.api.srpmp.project.apply.vo.SrpmsProjectApplySchStuVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyInnPreSubmitVo;
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
 * @Author : caoyue
 * @Date : Create in 2019-02-21
 * @Description :  SrpmsProjectApplySchStu控制器接口
 * @Modified :
 */
public interface SrpmsProjectApplySchStuClient {

    public static final String path="/srpmp/project/apply/school/student";


    /**
     *  POST---保存
     * @param   Vo
     * @return
     */
    @PostMapping(value = path)
    Result save(@Valid @ModelAttribute SrpmsProjectApplySchStuSaveVo Vo, DeptVo deptVo);

    /**
     * 提交申请书
     * @param vo
     * @param deptVo
     * @return
     */
    @PostMapping(value = path+"/submit")
    Result submit(@Valid @ModelAttribute SrpmsProjectApplySchStuSaveVo vo,UserVo userVo, DeptVo deptVo);

//    /**
//    *  Delete---删除
//    * @param  id
//    * @return
//    */
//    @DeleteMapping(value = path+"/{id}")
//    Result delete(@PathVariable(value="id") long id);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<SrpmsProjectApplySchStuSaveVo> get(@PathVariable(value="id") Long id, UserVo user, DeptVo dept);


//    /**
//     *  POST----列表查询
//     * @param   srpmsProjectApplySchStuQueryForm
//     * @return
//     */
//    @PostMapping(value = path+"/list/conditions")
//    Result<List<SrpmsProjectApplySchStuVo>> list(@Valid @RequestBody SrpmsProjectApplySchStuQueryForm srpmsProjectApplySchStuQueryForm);
//
//
//    /**
//     *  POST----复杂查询
//     * @param  srpmsProjectApplySchStuQueryForm
//     * @return
//     */
//    @PostMapping(value = path+"/page/conditions")
//    Result<IPage<SrpmsProjectApplySchStuVo>> search(@Valid @RequestBody SrpmsProjectApplySchStuQueryForm srpmsProjectApplySchStuQueryForm);
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
    Result<SrpmsProjectApplySchStuSaveVo> importWord(@Valid @ModelAttribute WordImportReqVo reqVo);
}
