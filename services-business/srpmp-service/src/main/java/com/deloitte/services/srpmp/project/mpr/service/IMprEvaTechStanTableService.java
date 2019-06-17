package com.deloitte.services.srpmp.project.mpr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaTechStanTableQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaTechStanTable;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description : MprEvaTechStanTable服务类接口
 * @Modified :
 */
public interface IMprEvaTechStanTableService extends IService<MprEvaTechStanTable> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<MprEvaTechStanTable>
     */
    IPage<MprEvaTechStanTable> selectPage(MprEvaTechStanTableQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<MprEvaTechStanTable>
     */
    List<MprEvaTechStanTable> selectList(MprEvaTechStanTableQueryForm queryForm);
}
