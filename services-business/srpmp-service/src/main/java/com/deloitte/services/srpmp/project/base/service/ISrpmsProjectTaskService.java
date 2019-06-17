package com.deloitte.services.srpmp.project.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.srpmp.project.base.param.SrpmsProjectTaskQueryForm;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectTaskVo;
import com.deloitte.services.srpmp.common.enums.TaskCategoryEnums;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectTask;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-19
 * @Description : SrpmsProjectTask服务类接口
 * @Modified :
 */
public interface ISrpmsProjectTaskService extends IService<SrpmsProjectTask> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<SrpmsProjectTask>
     */
    IPage<SrpmsProjectTask> selectPage(SrpmsProjectTaskQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<SrpmsProjectTask>
     */
    List<SrpmsProjectTask> selectList(SrpmsProjectTaskQueryForm queryForm);

    void cleanAndSaveTask(List<SrpmsProjectTaskVo> taskVoList, TaskCategoryEnums categoryEnum, Long projectId);

    void saveTask(List<SrpmsProjectTaskVo> taskVoList, TaskCategoryEnums categoryEnum, Long projectId);

    List<SrpmsProjectTaskVo> queryTaskListByTaskCategory(TaskCategoryEnums taskCategory, Long projectId);
}
