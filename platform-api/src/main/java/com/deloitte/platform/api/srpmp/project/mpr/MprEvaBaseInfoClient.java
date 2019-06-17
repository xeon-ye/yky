package com.deloitte.platform.api.srpmp.project.mpr;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.TaskNodeActionVO;
import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaBaseInfoQueryForm;
import com.deloitte.platform.api.srpmp.project.mpr.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description :  MprEvaBaseInfo控制器接口
 * @Modified :
 */
public interface MprEvaBaseInfoClient {

    public static final String path="/srpmp/project/mpr/base";


    /**
     * 根据项目ID获取基本信息
     * @param  projectId
     * @return
     */
    @GetMapping(value = path+"/{projectId}")
    Result<MprEvaBaseInfoVo> getMprBaseInfo(@PathVariable(value = "projectId") Long projectId);

    /**
     * 保存或者更新附件一
     * @param saveOrUpdateAnnexOneForm
     * @return
     */
    @PostMapping(value = path+"/annexOne/saveOrUpdate")
    Result saveOrUpdate(@Valid @ModelAttribute MprSaveOrUpdateAnnexOneForm saveOrUpdateAnnexOneForm, UserVo user, DeptVo deptVo);

    /**
    * 根据项目ID获取附件一
    * @param  projectId
    * @return
    */
    @GetMapping(value = path+"/annexOne/{projectId}")
    Result<MprSaveOrUpdateAnnexOneVo> getAnnexOne(@PathVariable(value = "projectId") Long projectId);

    /**
     * 导出附件一为EXCEL
     * @param projectId
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = path+"/exportAnnexOneExcel/{projectId}")
    Result exportAnnexOneExcel(@PathVariable("projectId") Long projectId, HttpServletRequest request, HttpServletResponse response);

    // 以下代码：wuhebiao新增

    /**
     * 查询年度报告详情
     * @param  mprEvaBaseInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/getCondtions")
    Result getByConditions(@Valid @RequestBody MprEvaBaseInfoQueryForm mprEvaBaseInfoQueryForm);

    /**
     *  判断年度报告是否存在
     * @param  mprEvaBaseInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/checkReportExists")
    Result checkReportExists(@Valid @RequestBody MprEvaBaseInfoQueryForm mprEvaBaseInfoQueryForm);

    /**
     * 我的申请、我的报告、报告列表
     * @param   mprEvaBaseInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<IPage<MprEvaBaseInfoVo>> selectList(@Valid @RequestBody MprEvaBaseInfoQueryForm mprEvaBaseInfoQueryForm, UserVo user, DeptVo dept);
//
//    /**
//     *  POST----列表查询：我的报告
//     * @param   mprEvaBaseInfoQueryForm
//     * @return
//     */
//    @PostMapping(value = path+"/oneList/conditions")
//    Result<List<MprEvaBaseInfoVo>> selectOneList(@Valid @RequestBody MprEvaBaseInfoQueryForm mprEvaBaseInfoQueryForm, UserVo user, DeptVo dept);

//    /**
//     *  POST----列表查询：我的部门或所有部门报告
//     * @param   mprEvaBaseInfoQueryForm
//     * @return
//     */
//    @PostMapping(value = path+"/deptList/conditions")
//    Result<List<MprEvaBaseInfoVo>> selectAllDeptList(@Valid @RequestBody MprEvaBaseInfoQueryForm mprEvaBaseInfoQueryForm, UserVo user, DeptVo dept);
//
//    /**
//     *  POST----复杂查询
//     * @param  mprEvaBaseInfoQueryForm
//     * @return
//     */
//    @PostMapping(value = path+"/page/conditions")
//    Result<IPage<MprEvaBaseInfoVo>> search(@Valid @RequestBody MprEvaBaseInfoQueryForm mprEvaBaseInfoQueryForm, UserVo user, DeptVo dept);

    /**
     * 保存或者更新年度报告
     * @param saveOrUpdateForm
     * @return
     */
    @PostMapping(value = path+"/saveOrUpdate")
    Result saveOrUpdate(@Valid @ModelAttribute MprSaveOrUpdateForm saveOrUpdateForm, UserVo user, DeptVo dept);

    /**
     * 提交年度报告
     * @param mprSaveOrUpdateForm
     * @param user
     * @param dept
     * @return
     */
    @PostMapping(value = path+"/submitReport")
    Result submitReport(@Valid @ModelAttribute MprSaveOrUpdateForm mprSaveOrUpdateForm, UserVo user, DeptVo dept);

    /**
     * 审核通过年度报告
     * @param actionVO
     * @param deptVo
     * @return
     */
    @PostMapping(value = path+"/agreeReport")
    Result agreeReport(@Valid @ModelAttribute TaskNodeActionVO actionVO, DeptVo deptVo);

    /**
     * 拒绝年度报告
     * @param actionVO
     * @return
     */
    @PostMapping(value = path+"/refuseReport")
    Result refuseReport(@Valid @ModelAttribute TaskNodeActionVO actionVO);

//    /**
//     * 驳回年度报告
//     * @param actionVO
//     * @return
//     */
//    @PostMapping(value = path+"/rejectReport")
//    Result rejectReport(@Valid @ModelAttribute TaskNodeActionVO actionVO);
}
