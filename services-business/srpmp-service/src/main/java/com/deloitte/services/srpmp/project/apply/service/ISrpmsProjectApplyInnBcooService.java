package com.deloitte.services.srpmp.project.apply.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyInnBcooSaveVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyInnPreSubmitVo;
import com.deloitte.services.srpmp.project.apply.entity.SrpmsProjectApplyInnBcoo;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-18
 * @Description : SrpmsProjectApplyInnBcoo服务类接口
 * @Modified :
 */
public interface ISrpmsProjectApplyInnBcooService extends IService<SrpmsProjectApplyInnBcoo> {

    /**
     *  条件查询
     * @param queryForm
     * @return List<SrpmsProjectApplyInnBcoo>
     */
    long saveOrUpdate(SrpmsProjectApplyInnBcooSaveVo queryForm, DeptVo deptVo);

    /**
     *  条件查询
     * @param queryForm
     * @return List<SrpmsProjectApplyInnBcoo>
     */
    void submit(SrpmsProjectApplyInnBcooSaveVo queryForm,UserVo userVo, DeptVo deptVo);

    /**
     *  查询
     * @param id
     * @return List<SrpmsProjectApplyInnBcoo>
     */
    JSONObject get(long id);

    public JSONObject get(long projectId, UserVo user, DeptVo dept);


    public void generateApplyBookPdf(Long projectId, UserVo userVo, DeptVo deptVo);

    /**
     * word导出
     * @param projectId 项目ID
     */
    public String exportWordFile(Long projectId, UserVo userVo, DeptVo deptVo);

    public String exportPdfFile(Long projectId, UserVo userVo, DeptVo deptVo) throws Exception;


    /**
     * word导入项目申请书
     * @param wordFileUrl word文件URL地址
     */
    SrpmsProjectApplyInnBcooSaveVo importWord(String wordFileUrl);
}
