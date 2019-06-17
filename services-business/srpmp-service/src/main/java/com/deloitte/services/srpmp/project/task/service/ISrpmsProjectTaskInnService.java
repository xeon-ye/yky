package com.deloitte.services.srpmp.project.task.service;

import com.alibaba.fastjson.JSONObject;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyInnBcooSaveVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyInnPreSubmitVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.WordImportReqVo;
import com.deloitte.platform.api.srpmp.project.task.vo.ext.SrpmsProjectTaskInnExtVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.srpmp.project.task.entity.SrpmsProjectTaskInn;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * @Author : pengchao
 * @Date : Create in 2019-03-11
 * @Description : SrpmsProjectTaskInn服务类接口
 * @Modified :
 */
public interface ISrpmsProjectTaskInnService extends IService<SrpmsProjectTaskInn> {

    Long saveOrUpdate(SrpmsProjectTaskInnExtVo vo, UserVo user);

    JSONObject queryById(Long projectId);

    void submit(SrpmsProjectTaskInnExtVo vo, UserVo user, DeptVo deptVo);

    JSONObject importTaskWord(WordImportReqVo reqVo);

    String exportTaskWord(Long projectId, UserVo userVo, DeptVo deptVo);


    /**
     * 重大协同任务书导出
     * @param projectId
     * @param userVo
     * @param deptVo
     * @return
     */
    String exportBcooTaskWord(Long projectId, UserVo userVo, DeptVo deptVo);


    /**
     * 协同创新任务书导出
     * @param projectId
     * @param userVo
     * @param deptVo
     * @return
     */
    String exportCooTaskWord(Long projectId, UserVo userVo, DeptVo deptVo);



    /**
     * 重大协同任务导入
     * @param wordFileUrl word文件URL地址
     */
    SrpmsProjectTaskInnExtVo importBcooWord(String wordFileUrl);


    /**
     * 协同创新任务导入
     * @param wordFileUrl word文件URL地址
     */
    SrpmsProjectTaskInnExtVo importCooWord(String wordFileUrl);

    /**
     * 生成申请书PDF文档-先导
     * @param projectId
     * @param userVo
     * @param deptVo
     */
    public void generateTaskInnPreBookPdf(Long projectId, UserVo userVo, DeptVo deptVo);

    /**
     * 生成申请书PDF文档-重大
     * @param projectId
     * @param userVo
     * @param deptVo
     */
    public void generateTaskInnBcooBookPdf(Long projectId, UserVo userVo, DeptVo deptVo);

    /**
     * 生成申请书PDF文档-协同
     * @param projectId
     * @param userVo
     * @param deptVo
     */
    public void generateTaskInnCooBookPdf(Long projectId, UserVo userVo, DeptVo deptVo);

    public String exportPdfTaskPre(Long projectId, UserVo userVo, DeptVo deptVo) throws Exception;
    public String exportPdfTaskBcoo(Long projectId, UserVo userVo, DeptVo deptVo) throws Exception;
    public String exportPdfTaskCoo(Long projectId, UserVo userVo, DeptVo deptVo) throws Exception;




}
