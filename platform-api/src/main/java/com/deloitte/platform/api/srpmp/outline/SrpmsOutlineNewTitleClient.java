package com.deloitte.platform.api.srpmp.outline;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineNewTitleQueryForm;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineQueryParam;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineNewTitleFormList;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Author : pengchao
 * @Date : Create in 2019-02-15
 * @Description :  SrpmsOutlineAward控制器接口
 * @Modified :
 */
public interface SrpmsOutlineNewTitleClient {

    public static final String path="/srpmp/srpms-outline/new_title";

    /**
     *  GET----新获项目查询
     * @param  queryForm
     * @return
     */
    @CrossOrigin
    @GetMapping(value = path)
    Result searchSrpmsOutline(HttpServletRequest request, @Valid @ModelAttribute SrpmsOutlineNewTitleQueryForm queryForm, UserVo user, DeptVo dept);

    /**
     *  POST---新增
     * @param data
     * @return
     */
    @CrossOrigin
    @PostMapping(value = path)
    Result saveSrpmsOutline(@Valid @ModelAttribute SrpmsOutlineNewTitleFormList data, UserVo user);

    /**
     *导出报表
     *
     * @param response
     * @param queryParam
     * @return
     */
    @CrossOrigin
    @GetMapping(value = path+"/export/report/{type}")
    Result getExcelExportReport(HttpServletRequest request, HttpServletResponse response, @Valid @ModelAttribute SrpmsOutlineQueryParam queryParam, @PathVariable int type);

    /**
     *导出报表基础数据功能
     *
     * @param response
     * @param queryParam
     * @return
     */
    @CrossOrigin
    @GetMapping(value = path+"/export/data/{type}")
    Result getExportReportData(HttpServletResponse response, @Valid @ModelAttribute SrpmsOutlineQueryParam queryParam, @PathVariable int type);

    /**
     *导出基础数据功能
     *
     * @param response
     * @param queryForm
     * @return
     */
    @CrossOrigin
    @GetMapping(value = path+"/export/file")
    Result exportExeclFile(HttpServletRequest request, HttpServletResponse response, @Valid @ModelAttribute SrpmsOutlineNewTitleQueryForm queryForm, UserVo user, DeptVo dept);

    /**
     * POST---读取excel
     *
     * @param file
     * @return
     */
    @CrossOrigin
    @PostMapping(value = path+"/import")
    Result importExeclFile(MultipartFile file);

    /**
     * 各单位经费占比
     * @param queryForm
     * @return
     */
    @CrossOrigin
    @GetMapping(value = path+"/org")
    Result getReportProportionByOrg(@Valid @ModelAttribute SrpmsOutlineNewTitleQueryForm queryForm);
    /**
     * 各项目经费占比
     * @param queryForm
     * @return
     */
    @CrossOrigin
    @GetMapping(value = path+"/project")
    Result getReportProportionByProject(@Valid @ModelAttribute SrpmsOutlineNewTitleQueryForm queryForm);

    /**
     * 查询下拉列表
     *
     * @param user
     * @param dept
     * @return
     */
    @CrossOrigin
    @GetMapping(value = path+"/dept")
    Result searchSelectDept(UserVo user, DeptVo dept);
}