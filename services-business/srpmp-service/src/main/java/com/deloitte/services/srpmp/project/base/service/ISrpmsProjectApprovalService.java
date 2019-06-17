package com.deloitte.services.srpmp.project.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.srpmp.project.base.param.SrpmsProjectApprovalQueryForm;
import com.deloitte.platform.api.srpmp.project.base.param.SrpmsProjectExpertQueryForm;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectApproval;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectExpert;

import java.util.List;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-27
 * @Description : SrpmsProjectApproval服务类接口
 * @Modified :
 */
public interface ISrpmsProjectApprovalService extends IService<SrpmsProjectApproval> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<SrpmsProjectApproval>
     */
    IPage<SrpmsProjectApproval> selectPage(SrpmsProjectApprovalQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<SrpmsProjectApproval>
     */
    List<SrpmsProjectApproval> selectList(SrpmsProjectApprovalQueryForm queryForm);

    List<SrpmsProjectApproval> queryListByProjectId(Long projectId);
}
