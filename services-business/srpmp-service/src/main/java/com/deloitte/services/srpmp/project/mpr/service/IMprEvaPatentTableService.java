package com.deloitte.services.srpmp.project.mpr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaPatentTableQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaPatentTable;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description : MprEvaPatentTable服务类接口
 * @Modified :
 */
public interface IMprEvaPatentTableService extends IService<MprEvaPatentTable> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<MprEvaPatentTable>
     */
    IPage<MprEvaPatentTable> selectPage(MprEvaPatentTableQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<MprEvaPatentTable>
     */
    List<MprEvaPatentTable> selectList(MprEvaPatentTableQueryForm queryForm);
}
