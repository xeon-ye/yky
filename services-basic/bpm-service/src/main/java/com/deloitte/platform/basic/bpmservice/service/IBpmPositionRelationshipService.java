package com.deloitte.platform.basic.bpmservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.bpmservice.param.BpmPositionRelationshipQueryForm;
import com.deloitte.platform.basic.bpmservice.entity.BpmPositionRelationship;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jackliu
 * @Date : Create in 2019-02-18
 * @Description : BpmPositionRelationship服务类接口
 * @Modified :
 */
public interface IBpmPositionRelationshipService extends IService<BpmPositionRelationship> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BpmPositionRelationship>
     */
    IPage<BpmPositionRelationship> selectPage(BpmPositionRelationshipQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BpmPositionRelationship>
     */
    List<BpmPositionRelationship> selectList(BpmPositionRelationshipQueryForm queryForm);
}
