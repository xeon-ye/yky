package com.deloitte.services.srpmp.project.mpr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaResearchTaskTableQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaResearchTaskTable;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description : MprEvaResearchTaskTable服务类接口
 * @Modified :
 */
public interface IMprEvaResearchTaskTableService extends IService<MprEvaResearchTaskTable> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<MprEvaResearchTaskTable>
     */
    IPage<MprEvaResearchTaskTable> selectPage(MprEvaResearchTaskTableQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<MprEvaResearchTaskTable>
     */
    List<MprEvaResearchTaskTable> selectList(MprEvaResearchTaskTableQueryForm queryForm);
}
