package com.deloitte.platform.api.srpmp.project.budget;

import com.deloitte.platform.api.fssc.performance.param.PerformanceIndexLibraryQueryForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformanceIndexLibraryVo;
import com.deloitte.platform.api.fssc.performance.vo.PerformanceIndexVo;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.BudgetTaskSubmitVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.WordImportReqVo;
import com.deloitte.platform.api.srpmp.project.budget.vo.ext.BudgetApplyVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-19
 * @Description :  SrpmsProjectBudgetTaskClient控制器接口
 * @Modified :
 */
public interface SrpmsProjectBudgetTaskClient {

    public static final String path="/srpmp/project/task/budget";

    /**
     *  POST---新增
     * @param vo
     * @return
     */
    @PostMapping(value = path)
    Result<Map<String, Object>> save(@ModelAttribute BudgetApplyVo vo);

    /**
     *  POST---提交
     * @param vo
     * @return
     */
    @PostMapping(value = path+"/submit")
    Result<Map<String, Object>> submit(@Valid @ModelAttribute BudgetApplyVo vo, UserVo userVo, DeptVo deptVo);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<BudgetTaskSubmitVo> get(@PathVariable(value = "id") long id);

    /**
     *  GET----导出任务预算书
     * @param  id
     * @return
     */
    @GetMapping(value = path+"/export/{id}")
    Result export(@PathVariable(value="id") Long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo);


    /**
     *  GET----导出任务预算书
     * @param  id
     * @return
     */
    @GetMapping(value = path+"/exportpdf/{id}")
    Result exportPdf(@PathVariable(value="id") Long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo);

    /**
     * 导入模板
     */
    @PostMapping(value = path+"/import")
    Result<BudgetApplyVo> importWord(@Valid @ModelAttribute WordImportReqVo reqVo);

    /**
     * 查询指标库
     * @param queryForm
     * @return
     */
    @PostMapping(value = path + "/performanceIndex/listLibrary")
    Result<List<PerformanceIndexLibraryVo>> listPerformanceIndexLibrary(@Valid @ModelAttribute PerformanceIndexLibraryQueryForm queryForm);

    /**
     * 根据指标库查询一、二、三级指标的数据型结构数据
     * @param libraryId
     * @return
     */
    @PostMapping(value = path+"/performanceIndex/searchByLibraryId/{libraryId}")
    Result<List<PerformanceIndexVo>> searchPerformanceIndex(@PathVariable(value="libraryId") Long libraryId);
}
