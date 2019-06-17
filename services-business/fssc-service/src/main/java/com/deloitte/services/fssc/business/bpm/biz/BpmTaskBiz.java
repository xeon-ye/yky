package com.deloitte.services.fssc.business.bpm.biz;

import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.fssc.bpm.param.TaskQueryForm;
import com.deloitte.platform.common.core.util.GdcPage;

import java.util.List;

/**
 * 工作流 任务相关接口
 */
public interface BpmTaskBiz {

    /**
     * 查询审批历史
     * @param documentId
     * @param documentType
     * @return
     */
    List<BpmProcessTaskVo> findHistory(Long documentId, String documentType);

    /**
     * 无异常查询审批历史
     * @param documentId
     * @param documentType
     * @return
     */
    List<BpmProcessTaskVo> findHistoryNoException(Long documentId, String documentType);

    /**
     * 查询我的待办
     *
     * @param queryForm@return
     */
    GdcPage<BpmProcessTaskVo> findMyToDo(TaskQueryForm queryForm);

    /**
     * 查询我的已办
     *
     * @param queryForm@return
     */
    GdcPage<BpmProcessTaskVo> findMyDone(TaskQueryForm queryForm);
}
