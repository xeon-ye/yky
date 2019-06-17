package com.deloitte.services.srpmp.project.mpr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaMedicineTableQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaMedicineTable;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description : MprEvaMedicineTable服务类接口
 * @Modified :
 */
public interface IMprEvaMedicineTableService extends IService<MprEvaMedicineTable> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<MprEvaMedicineTable>
     */
    IPage<MprEvaMedicineTable> selectPage(MprEvaMedicineTableQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<MprEvaMedicineTable>
     */
    List<MprEvaMedicineTable> selectList(MprEvaMedicineTableQueryForm queryForm);
}
