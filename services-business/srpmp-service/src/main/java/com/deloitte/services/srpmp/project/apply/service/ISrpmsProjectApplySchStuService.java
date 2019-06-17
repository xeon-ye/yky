package com.deloitte.services.srpmp.project.apply.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.param.SrpmsProjectApplySchStuQueryForm;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplySchStuSaveVo;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskSchStudentForm;
import com.deloitte.services.srpmp.common.enums.ProjectCategoryEnums;
import com.deloitte.services.srpmp.common.util.JSONConvert;
import com.deloitte.services.srpmp.project.apply.entity.SrpmsProjectApplySchStu;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.catalina.User;

import java.util.List;

/**
 * @Author : caoyue
 * @Date : Create in 2019-02-21
 * @Description : SrpmsProjectApplySchStu服务类接口
 * @Modified :
 */
public interface ISrpmsProjectApplySchStuService extends IService<SrpmsProjectApplySchStu> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<SrpmsProjectApplySchStu>
     */
    IPage<SrpmsProjectApplySchStu> selectPage(SrpmsProjectApplySchStuQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<SrpmsProjectApplySchStu>
     */
    List<SrpmsProjectApplySchStu> selectList(SrpmsProjectApplySchStuQueryForm queryForm);


    /**
     * 保存
     * @param Vo
     * @return
     */
    long saveOrUpdateApplySchStu(SrpmsProjectApplySchStuSaveVo Vo, DeptVo deptVo);

    /**
     * 根据ID删除
     * @param id
     */
    void deleteApplyById(long id);

    void submitApply(SrpmsProjectApplySchStuSaveVo vo, UserVo userVo, DeptVo deptVo);


    /**
     * 根据Id获取
     * @param id
     * @return
     */
    SrpmsProjectApplySchStuSaveVo getApplyById(Long id);

    public JSONObject queryApplyVoById(Long projectId, UserVo user, DeptVo dept);


    /**
     * word导出
     * @param projectId 项目ID
     */
    public String exportWordFile(Long projectId,UserVo user, DeptVo dept);


    /**
     * word导入项目申请书
     * @param wordFileUrl word文件URL地址
     */
    SrpmsProjectApplySchStuSaveVo importWord(String wordFileUrl);

    public String exportPdfFile(Long projectId, UserVo userVo, DeptVo deptVo) throws Exception;

    /**
     * 获取学科分类code
     * @param subject
     * @return
     */
    JSONArray getSubjectCodes (String subject);
}
