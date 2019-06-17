package com.deloitte.services.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.contract.param.ApprovalOpinionQueryForm;
import com.deloitte.services.contract.entity.ApprovalOpinion;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-01
 * @Description : ApprovalOpinion服务类接口
 * @Modified :
 */
public interface IApprovalOpinionService extends IService<ApprovalOpinion> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<ApprovalOpinion>
     */
    IPage<ApprovalOpinion> selectPage(ApprovalOpinionQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<ApprovalOpinion>
     */
    List<ApprovalOpinion> selectList(ApprovalOpinionQueryForm queryForm);
}
