package com.deloitte.platform.api.srpmp.project.mpr;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprEvaFileInfoCompressDownForm;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprEvaFileInfoSaveOrUpdateForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-04-01
 * @Description :  MprEvaFileInfo控制器接口
 * @Modified :
 */
public interface MprEvaFileInfoClient {

    public static final String path="/srpmp/project/mpr/file";

    /**
     *  保存或者更新文件信息
     * @param mprEvaFileInfoSaveOrUpdateForm
     * @return
     */
    @PostMapping(value = path+"/saveOrUpdate")
    Result saveOrUpdate(@Valid @RequestBody MprEvaFileInfoSaveOrUpdateForm mprEvaFileInfoSaveOrUpdateForm);

    /**
    *  根据项目ID获取文件列表
    * @param  projectId
    * @return
    */
    @GetMapping(value = path+"/{projectId}")
    Result getByProjectId(@PathVariable(value = "projectId") Long projectId);

    /**
     * 导出模板
     * @param id
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = path + "/exportTemplate/{projectNo}/{id}")
    Result exportTemplate(@PathVariable("projectNo") Long projectNo, @PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo);

    /**
     * 下载文件（模板）
     * @param ids
     * @param projectIds
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = path + "/compressTemplateDownload/{ids}/{projectIds}")
    Result compressTemplateDownload(@PathVariable("ids") String ids, @PathVariable("projectIds") String projectIds, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo);

    /**
     * 下载文件 （附件）
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = path + "/compressEnclosureDownload")
    Result compressEnclosureDownload(@Valid @RequestBody MprEvaFileInfoCompressDownForm mprEvaFileInfoCompressDownForm, HttpServletRequest request, HttpServletResponse response);

    /**
     * 下载文件 （附件）
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = path + "/downloadZip")
    Result<String> downloadZip(@Valid @RequestBody MprEvaFileInfoCompressDownForm mprEvaFileInfoCompressDownForm, HttpServletRequest request, HttpServletResponse response);

}
