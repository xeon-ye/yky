package com.deloitte.services.srpmp.project.mpr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaHighLevelTableQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaHighLevelTable;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description : MprEvaHighLevelTable服务类接口
 * @Modified :
 */
public interface IMprEvaHighLevelTableService extends IService<MprEvaHighLevelTable> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<MprEvaHighLevelTable>
     */
    IPage<MprEvaHighLevelTable> selectPage(MprEvaHighLevelTableQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<MprEvaHighLevelTable>
     */
    List<MprEvaHighLevelTable> selectList(MprEvaHighLevelTableQueryForm queryForm);
}
