package com.deloitte.services.srpmp.project.mpr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.srpmp.project.mpr.param.MprJouPostTableQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprJouPostTable;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description : MprJouPostTable服务类接口
 * @Modified :
 */
public interface IMprJouPostTableService extends IService<MprJouPostTable> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<MprJouPostTable>
     */
    IPage<MprJouPostTable> selectPage(MprJouPostTableQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<MprJouPostTable>
     */
    List<MprJouPostTable> selectList(MprJouPostTableQueryForm queryForm);
}
