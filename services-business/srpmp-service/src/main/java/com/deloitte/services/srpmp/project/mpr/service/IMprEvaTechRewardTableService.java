package com.deloitte.services.srpmp.project.mpr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaTechRewardTableQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaTechRewardTable;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description : MprEvaTechRewardTable服务类接口
 * @Modified :
 */
public interface IMprEvaTechRewardTableService extends IService<MprEvaTechRewardTable> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<MprEvaTechRewardTable>
     */
    IPage<MprEvaTechRewardTable> selectPage(MprEvaTechRewardTableQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<MprEvaTechRewardTable>
     */
    List<MprEvaTechRewardTable> selectList(MprEvaTechRewardTableQueryForm queryForm);
}
