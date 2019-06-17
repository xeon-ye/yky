package com.deloitte.services.srpmp.project.mpr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaCoopResultTableQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaCoopResultTable;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description : MprEvaCoopResultTable服务类接口
 * @Modified :
 */
public interface IMprEvaCoopResultTableService extends IService<MprEvaCoopResultTable> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<MprEvaCoopResultTable>
     */
    IPage<MprEvaCoopResultTable> selectPage(MprEvaCoopResultTableQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<MprEvaCoopResultTable>
     */
    List<MprEvaCoopResultTable> selectList(MprEvaCoopResultTableQueryForm queryForm);
}
