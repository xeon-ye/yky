package com.deloitte.services.srpmp.project.apply.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyInnPreSaveVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyInnPreSubmitVo;
import com.deloitte.services.srpmp.project.apply.entity.SrpmsProjectApplyInnPre;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-19
 * @Description : SrpmsProjectApplyInnPre服务类接口
 * @Modified :
 */
public interface ISrpmsProjectApplyInnPreService extends IService<SrpmsProjectApplyInnPre> {

    Long saveOrUpdateApplyInnPre(SrpmsProjectApplyInnPreSubmitVo vo,  DeptVo deptVo);

    JSONObject queryApplyVoById(Long projectId, UserVo user, DeptVo dept);

    JSONObject queryApplyVoById(Long projectId);
    void submitApply(SrpmsProjectApplyInnPreSubmitVo vo, UserVo userVo, DeptVo deptVo);

    /**
     * word导入项目申请书
     * @param wordFileUrl word文件URL地址
     */
    SrpmsProjectApplyInnPreSubmitVo importWord(String wordFileUrl);

    /**
     * word导出
     * @param projectId 项目ID
     */
    public String exportWordFile(Long projectId, UserVo userVo, DeptVo deptVo);

    public String exportPdfFile(Long projectId, UserVo userVo, DeptVo deptVo) throws Exception;

    /**
     * 生成申请书PDF文档
     * @param projectId
     * @param userVo
     * @param deptVo
     */
    public void generateApplyBookPdf(Long projectId, UserVo userVo, DeptVo deptVo);

}
