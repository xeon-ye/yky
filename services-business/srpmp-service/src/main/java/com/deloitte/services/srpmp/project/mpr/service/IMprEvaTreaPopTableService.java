package com.deloitte.services.srpmp.project.mpr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaTreaPopTableQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaTreaPopTable;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description : MprEvaTreaPopTable服务类接口
 * @Modified :
 */
public interface IMprEvaTreaPopTableService extends IService<MprEvaTreaPopTable> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<MprEvaTreaPopTable>
     */
    IPage<MprEvaTreaPopTable> selectPage(MprEvaTreaPopTableQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<MprEvaTreaPopTable>
     */
    List<MprEvaTreaPopTable> selectList(MprEvaTreaPopTableQueryForm queryForm);
}
