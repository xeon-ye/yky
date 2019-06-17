package com.deloitte.platform.basic.bpmservice.mapper;

import com.deloitte.platform.basic.bpmservice.entity.BpmProcessTask;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jackliu
 * @since 2019-04-02
 */
public interface BpmProcessTaskMapper extends BaseMapper<BpmProcessTask> {

    void updateObjectStatusByProcessId(@Param(value="objectStatus")String objectStatus,@Param(value="processId")String processId);

    void updateTaskStatusByPrevious(@Param(value="taskStatus")String taskStatus,@Param(value="previousTaskId")String previousTaskId,@Param(value="processId")String processId);

    BpmProcessTask findBpmProcessTask(@Param(value="processInstanceId")String processInstanceId,@Param(value="taskId")String taskId);

    int countBpmProcessTask(@Param(value="processInstanceId")String processInstanceId,@Param(value="objectStatus")String objectStatus);

}
