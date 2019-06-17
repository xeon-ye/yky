package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.ApprovalProcessQueryForm;
import com.deloitte.services.project.entity.ApprovalProcess;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-22
 * @Description : ApprovalProcess服务类接口
 * @Modified :
 */
public interface IApprovalProcessService extends IService<ApprovalProcess> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<ApprovalProcess>
     */
    IPage<ApprovalProcess> selectPage(ApprovalProcessQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<ApprovalProcess>
     */
    List<ApprovalProcess> selectList(ApprovalProcessQueryForm queryForm);
}
