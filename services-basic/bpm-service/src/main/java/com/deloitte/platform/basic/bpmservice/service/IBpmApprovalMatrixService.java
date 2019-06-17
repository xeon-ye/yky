package com.deloitte.platform.basic.bpmservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.bpmservice.param.BpmApprovalMatrixQueryForm;
import com.deloitte.platform.api.bpmservice.param.BpmApprovalMatrixQueryFormForApproval;
import com.deloitte.platform.basic.bpmservice.entity.BpmApprovalMatrix;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.common.core.util.GdcPage;

import java.util.List;

/**
 * @Author : jackliu
 * @Date : Create in 2019-03-16
 * @Description : BpmApprovalMatrix服务类接口
 * @Modified :
 */
public interface IBpmApprovalMatrixService extends IService<BpmApprovalMatrix> {

    /**
     * 分页查询
     *
     * @param queryForm
     * @return IPage<BpmApprovalMatrix>
     */
    IPage<BpmApprovalMatrix> selectPage(BpmApprovalMatrixQueryForm queryForm);

    /**
     * 条件查询
     *
     * @param queryForm
     * @return List<BpmApprovalMatrix>
     */
    List<BpmApprovalMatrix> selectList(BpmApprovalMatrixQueryForm queryForm);


    /**
     * 查询审批人
     *
     * @param queryForm
     * @param isNextPersion  查询当前人员，还是查询下一个节点的人员
     * @return BpmApprovalMatrix
     */
    GdcPage<BpmApprovalMatrix> findApprover(BpmApprovalMatrixQueryFormForApproval queryForm, boolean isNextPersion);

}
