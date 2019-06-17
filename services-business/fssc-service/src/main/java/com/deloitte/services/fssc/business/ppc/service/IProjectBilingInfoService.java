package com.deloitte.services.fssc.business.ppc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.ppc.param.ProjectBilingInfoQueryForm;
import com.deloitte.services.fssc.business.ppc.entity.ProjectBilingInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-01
 * @Description : ProjectBilingInfo服务类接口
 * @Modified :
 */
public interface IProjectBilingInfoService extends IService<ProjectBilingInfo> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<ProjectBilingInfo>
     */
    IPage<ProjectBilingInfo> selectPage(ProjectBilingInfoQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<ProjectBilingInfo>
     */
    List<ProjectBilingInfo> selectList(ProjectBilingInfoQueryForm queryForm);
}
