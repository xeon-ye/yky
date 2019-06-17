package com.deloitte.platform.basic.bpmservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.bpmservice.param.BpmPositionQueryForm;
import com.deloitte.platform.basic.bpmservice.entity.BpmPosition;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jackliu
 * @Date : Create in 2019-02-18
 * @Description : BpmPosition服务类接口
 * @Modified :
 */
public interface IBpmPositionService extends IService<BpmPosition> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BpmPosition>
     */
    IPage<BpmPosition> selectPage(BpmPositionQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BpmPosition>
     */
    List<BpmPosition> selectList(BpmPositionQueryForm queryForm);
}
