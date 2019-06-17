package com.deloitte.services.srpmp.project.task.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyInnPreSubmitVo;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskSchStudentForm;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskSchStudentVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.srpmp.project.task.entity.SrpmsProjectTaskSchStudent;

/**
 * @Author : Apeng
 * @Date : Create in 2019-03-14
 * @Description : SrpmsProjectTaskSchStudent服务类接口
 * @Modified :
 */
public interface ISrpmsProjectTaskSchStudentService extends IService<SrpmsProjectTaskSchStudent> {

    /**
     * 根据ID查询基科费申请书service接口
     *
     * @param projectId
     * @return
     */
    SrpmsProjectTaskSchStudentVo queryById(Long projectId);

    /**
     * 基科费任务书保存操作service接口
     *
     * @param taskForm
     * @param taskFlag
     * @return
     */
    Result saveSrpmsProjectTask(SrpmsProjectTaskSchStudentForm taskForm, boolean taskFlag);

    /**
     * 基科费任务书提交操作service接口
     *
     * @param taskForm
     * @return
     */
    Result submitSrpmsProjectTask(SrpmsProjectTaskSchStudentForm taskForm, UserVo userVo, DeptVo deptVo);


    /**
     * word导出
     * @param projectId 项目ID
     */
    public String exportWordFile(Long projectId, String template, UserVo userVo, DeptVo deptVo);

    /**
     * word导入项目任务书
     * @param wordFileUrl word文件URL地址
     */
    SrpmsProjectTaskSchStudentForm importWord(String wordFileUrl);

    public String exportPdf(Long projectId, String template, UserVo userVo, DeptVo deptVo) throws Exception;
}
