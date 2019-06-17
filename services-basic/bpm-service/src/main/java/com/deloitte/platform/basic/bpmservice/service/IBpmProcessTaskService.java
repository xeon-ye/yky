package com.deloitte.platform.basic.bpmservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryForm;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessImgInfoVo;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.basic.bpmservice.entity.BpmProcessTask;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.InputStream;
import java.util.List;

/**
 * @Author : jackliu
 * @Date : Create in 2019-02-28
 * @Description : BpmProcessTask服务类接口
 * @Modified :
 */
public interface IBpmProcessTaskService extends IService<BpmProcessTask> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BpmProcessTask>
     */
    IPage<BpmProcessTask> selectPage(BpmProcessTaskQueryForm queryForm);


    /**
     *  分页查询
     * @param queryWrapper
     * @return IPage<BpmProcessTask>
     */
    public IPage<BpmProcessTask> selectPageByWrapper(int currentPage,int pageSize ,QueryWrapper queryWrapper);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BpmProcessTask>
     */
    List<BpmProcessTask> selectList(BpmProcessTaskQueryForm queryForm);


    /**
     *  查询待办
     * @param queryForm
     * @return IPage<BpmProcessTask>
     */
    IPage<BpmProcessTask> selectBackLog(BpmProcessTaskQueryForm queryForm );


    /**
     *  查询已办
     * @param queryForm
     * @return IPage<BpmProcessTask>
     */
    IPage<BpmProcessTask> selectDnoe(BpmProcessTaskQueryForm queryForm );

    /**
     * 通过流程实例，更新单据审批状态
     * @param objectStatus
     * @param processId
     */
    void updateObjectStatusByProcessId(String objectStatus,String processId);

    /**
     * 通过上一个任务ID，修改此任务衍生的所有待办-》已办状态
     * @param taskStatus
     * @param previousTaskId
     * @param processInstanceId
     */
    void updateTaskStatusByPrevious(String taskStatus,String previousTaskId,String processInstanceId);

    /**
     *  查询流程图
     * @String processInstanceId
     * @return InputStream
     */
    InputStream getFlowImgByInstantId(String processInstanceId);

    /**
     *  查询流程图信息
     * @String processInstanceId
     * @return InputStream
     */
    List<BpmProcessImgInfoVo> getFlowImgInfoByInstantId(String processInstanceId);

    /**
     * 通过流程实例和任务id查询流程历史
     * @param processInstanceId
     * @param taskId
     * @return
     */
    BpmProcessTask findBpmProcessTask(String processInstanceId,String taskId);

    /**
     * 当前流程实例是否结束
     * @param processInstanceId
     * @return
     */
    boolean workflowEnd(String processInstanceId);

    /**
     * 当前任务是否审批完成
     * @param processInstanceId
     * @param taskId
     * @return
     */
    boolean taskIsEnd(String processInstanceId,String taskId);
}
