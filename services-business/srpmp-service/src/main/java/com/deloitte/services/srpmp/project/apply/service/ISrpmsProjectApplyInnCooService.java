package com.deloitte.services.srpmp.project.apply.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyInnBcooSaveVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyInnCooSaveVo;
import com.deloitte.services.srpmp.project.apply.entity.SrpmsProjectApplyInnCoo;

/**
 * @Author : wangyanyun
 * @Date : Create in 2019-02-16
 * @Description : SrpmsProjectApplyInnCoo服务类接口
 * @Modified :
 */
public interface ISrpmsProjectApplyInnCooService extends IService<SrpmsProjectApplyInnCoo> {

    /**
     *  条件查询
     * @param queryForm
     * @return List<SrpmsProjectApplyInnBcoo>
     */
    long saveOrUpdate(SrpmsProjectApplyInnCooSaveVo queryForm, DeptVo deptVo);

    /**
     *  条件查询
     * @param queryForm
     * @return List<SrpmsProjectApplyInnBcoo>
     */
    void submit(SrpmsProjectApplyInnCooSaveVo queryForm, UserVo user, DeptVo deptVo);

    /**
     *  查询
     * @param id
     * @return List<SrpmsProjectApplyInnBcoo>
     */
    JSONObject get(long id);

    public JSONObject get(long projectId, UserVo user, DeptVo dept);


    /**
     * word导出
     * @param projectId 项目ID
     */
    public String exportWordFile(Long projectId, UserVo user, DeptVo dept);

    public void generateApplyBookPdf(Long projectId, UserVo userVo, DeptVo deptVo);


    /**
     * word导入项目申请书
     * @param wordFileUrl word文件URL地址
     */
    SrpmsProjectApplyInnCooSaveVo importWord(String wordFileUrl);

    public String exportPdfFile(Long projectId, UserVo userVo, DeptVo deptVo) throws Exception;

}
