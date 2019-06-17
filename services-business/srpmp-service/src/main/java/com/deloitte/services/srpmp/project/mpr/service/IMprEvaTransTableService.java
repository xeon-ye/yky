package com.deloitte.services.srpmp.project.mpr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaTransTableQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaTransTable;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description : MprEvaTransTable服务类接口
 * @Modified :
 */
public interface IMprEvaTransTableService extends IService<MprEvaTransTable> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<MprEvaTransTable>
     */
    IPage<MprEvaTransTable> selectPage(MprEvaTransTableQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<MprEvaTransTable>
     */
    List<MprEvaTransTable> selectList(MprEvaTransTableQueryForm queryForm);
}
