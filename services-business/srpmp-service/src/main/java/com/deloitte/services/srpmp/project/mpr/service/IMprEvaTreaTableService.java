package com.deloitte.services.srpmp.project.mpr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaTreaTableQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaTreaTable;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description : MprEvaTreaTable服务类接口
 * @Modified :
 */
public interface IMprEvaTreaTableService extends IService<MprEvaTreaTable> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<MprEvaTreaTable>
     */
    IPage<MprEvaTreaTable> selectPage(MprEvaTreaTableQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<MprEvaTreaTable>
     */
    List<MprEvaTreaTable> selectList(MprEvaTreaTableQueryForm queryForm);
}
