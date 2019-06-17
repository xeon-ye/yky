package com.deloitte.services.fssc.budget.mq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.mq.param.SyncMsgQueryForm;
import com.deloitte.services.fssc.budget.mq.entity.SyncMsg;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-05-29
 * @Description : SyncMsg服务类接口
 * @Modified :
 */
public interface ISyncMsgService extends IService<SyncMsg> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<SyncMsg>
     */
    IPage<SyncMsg> selectPage(SyncMsgQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<SyncMsg>
     */
    List<SyncMsg> selectList(SyncMsgQueryForm queryForm);
}
