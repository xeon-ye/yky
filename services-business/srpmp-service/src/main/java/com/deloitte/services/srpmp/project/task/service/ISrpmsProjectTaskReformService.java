package com.deloitte.services.srpmp.project.task.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskReformForm;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskReformVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.srpmp.project.task.entity.SrpmsProjectTaskReform;

/**
 * @Author : zc
 * @Date : Create in 2019-03-13
 * @Description : SrpmsProjectTaskAcademy服务类接口
 * @Modified :
 */
public interface ISrpmsProjectTaskReformService extends IService<SrpmsProjectTaskReform> {

    /**
     * 根据ID查询基科费申请书service接口
     *
     * @param projectId
     * @return
     */
    SrpmsProjectTaskReformVo queryById(Long projectId);

    /**
     * 基科费任务书保存操作service接口
     *
     * @param taskForm
     * @param taskFlag
     * @return
     */
    Result saveSrpmsProjectTask(SrpmsProjectTaskReformForm taskForm, boolean taskFlag);

    /**
     * 基科费任务书提交操作service接口
     *
     * @param taskForm
     * @return
     */
    Result submitSrpmsProjectTask(SrpmsProjectTaskReformForm taskForm, UserVo userVo, DeptVo deptVo);

    /**
     * 导出任务书word tanwx
     * @param projectId
     * @param userVo
     * @param deptVo
     * @return
     */
    String exportWordFile(Long projectId, UserVo userVo, DeptVo deptVo);

    /**
     * 导入任务书
     * @param wordFileUrl
     * @return
     */
    SrpmsProjectTaskReformVo importWord(String wordFileUrl);

    /**tanwx
     * 生成pdf
     * @param projectId
     * @param userVo
     * @param deptVo
     */
    void generateApplyBookPdf(Long projectId, UserVo userVo, DeptVo deptVo) ;

    public String exportPdf(Long projectId, UserVo userVo, DeptVo deptVo) throws Exception;
}
