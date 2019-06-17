package com.deloitte.services.srpmp.project.mpr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprEvaFileInfoCompressDownForm;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprEvaFileInfoSaveOrUpdateForm;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprEvaFileInfoVoExt;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaFileInfo;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-04-01
 * @Description : MprEvaFileInfo服务类接口
 * @Modified :
 */
public interface IMprEvaFileInfoService extends IService<MprEvaFileInfo> {

    MprEvaFileInfoVoExt getByProjectId(Long projectId);

    void saveOrUpdateFileInfo(MprEvaFileInfoSaveOrUpdateForm mprEvaFileInfoSaveOrUpdateForm);

    String exportTemplate(Long id, Long projectId, UserVo userVo, DeptVo deptVo);

    Map<String, String> getTemplateNameFileName(Long id);

    List<File> compressTemplateDownload(String ids, String projectIds, UserVo userVo, DeptVo deptVo);

    List<File> compressEnclosureDownload(MprEvaFileInfoCompressDownForm mprEvaFileInfoCompressDownForm);

    String downloadZip(MprEvaFileInfoCompressDownForm mprEvaFileInfoCompressDownForm);
}
