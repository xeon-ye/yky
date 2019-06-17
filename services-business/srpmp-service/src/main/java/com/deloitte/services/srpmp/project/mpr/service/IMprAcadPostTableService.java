package com.deloitte.services.srpmp.project.mpr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.srpmp.project.mpr.param.MprAcadPostTableQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprAcadPostTable;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description : MprAcadPostTable服务类接口
 * @Modified :
 */
public interface IMprAcadPostTableService extends IService<MprAcadPostTable> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<MprAcadPostTable>
     */
    IPage<MprAcadPostTable> selectPage(MprAcadPostTableQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<MprAcadPostTable>
     */
    List<MprAcadPostTable> selectList(MprAcadPostTableQueryForm queryForm);
}
