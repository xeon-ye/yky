package com.deloitte.services.srpmp.project.mpr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.srpmp.project.mpr.param.MprProjEvaInfoQueryForm;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprProjEvaInfoForm;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprProjEvaInfoVo;
import com.deloitte.services.srpmp.project.mpr.entity.MprProjEvaInfo;

import java.util.List;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description : MprProjEvaInfo服务类接口
 * @Modified :
 */
public interface IMprProjEvaInfoService extends IService<MprProjEvaInfo> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<MprProjEvaInfo>
     */
    IPage<MprProjEvaInfo> selectPage(MprProjEvaInfoQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<MprProjEvaInfo>
     */
    List<MprProjEvaInfo> selectList(MprProjEvaInfoQueryForm queryForm);

    /**
     * 导出Word文档
     * @param projectId
     * @return
     */
    String exportWord(Long projectId);

    /**
     * 导入Word文档
     * @param wordFileUrl
     * @return
     */
    MprProjEvaInfoVo importWord(String wordFileUrl);

    /**
     * 保存和更新
     * @param mprProjEvaInfoForm
     * @return
     */
    Long saveAndUpdate(MprProjEvaInfoForm mprProjEvaInfoForm);

    /**
     * 查询
     * @param projectId
     * @return
     */
    MprProjEvaInfoVo getOne(Long projectId);

}
