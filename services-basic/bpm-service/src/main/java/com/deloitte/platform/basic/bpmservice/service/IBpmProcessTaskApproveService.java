package com.deloitte.platform.basic.bpmservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.basic.bpmservice.entity.BpmProcessTaskApprove;

import java.util.List;

public interface IBpmProcessTaskApproveService extends IService<BpmProcessTaskApprove> {

    /**
     *  条件查询
     * @param queryForm
     * @return List<BpmProcessTask>
     */
    List<BpmProcessTaskApprove> selectList(BpmProcessTaskApprove queryForm);
}
