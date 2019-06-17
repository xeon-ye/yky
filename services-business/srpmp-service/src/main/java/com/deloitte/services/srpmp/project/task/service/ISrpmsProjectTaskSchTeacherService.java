package com.deloitte.services.srpmp.project.task.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskSchTeacherForm;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskSchTeacherVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.srpmp.project.task.entity.SrpmsProjectTaskSchTeacher;

/**
 * @Author : Apeng
 * @Date : Create in 2019-03-14
 * @Description : SrpmsProjectTaskSchTeacher服务类接口
 * @Modified :
 */
public interface ISrpmsProjectTaskSchTeacherService extends IService<SrpmsProjectTaskSchTeacher> {

    /**
     * 根据ID查询基科费申请书service接口
     *
     * @param projectId
     * @return
     */
    SrpmsProjectTaskSchTeacherVo queryById(Long projectId);

    /**
     * 基科费任务书保存操作service接口
     *
     * @param taskForm
     * @param taskFlag
     * @return
     */
    Result saveSrpmsProjectTask(SrpmsProjectTaskSchTeacherForm taskForm, boolean taskFlag);

    /**
     * 基科费任务书提交操作service接口
     *
     * @param taskForm
     * @return
     */
    Result submitSrpmsProjectTask(SrpmsProjectTaskSchTeacherForm taskForm, UserVo userVo, DeptVo deptVo);

}
