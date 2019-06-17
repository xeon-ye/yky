package com.deloitte.services.srpmp.project.mpr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaTheoTechProTableQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaTheoTechProTable;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description : MprEvaTheoTechProTable服务类接口
 * @Modified :
 */
public interface IMprEvaTheoTechProTableService extends IService<MprEvaTheoTechProTable> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<MprEvaTheoTechProTable>
     */
    IPage<MprEvaTheoTechProTable> selectPage(MprEvaTheoTechProTableQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<MprEvaTheoTechProTable>
     */
    List<MprEvaTheoTechProTable> selectList(MprEvaTheoTechProTableQueryForm queryForm);
}
