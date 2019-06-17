package com.deloitte.services.srpmp.project.mpr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaMonoTableQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaMonoTable;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description : MprEvaMonoTable服务类接口
 * @Modified :
 */
public interface IMprEvaMonoTableService extends IService<MprEvaMonoTable> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<MprEvaMonoTable>
     */
    IPage<MprEvaMonoTable> selectPage(MprEvaMonoTableQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<MprEvaMonoTable>
     */
    List<MprEvaMonoTable> selectList(MprEvaMonoTableQueryForm queryForm);
}
