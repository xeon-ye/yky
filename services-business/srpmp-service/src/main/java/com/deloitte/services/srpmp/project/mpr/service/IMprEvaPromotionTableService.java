package com.deloitte.services.srpmp.project.mpr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaPromotionTableQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaPromotionTable;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description : MprEvaPromotionTable服务类接口
 * @Modified :
 */
public interface IMprEvaPromotionTableService extends IService<MprEvaPromotionTable> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<MprEvaPromotionTable>
     */
    IPage<MprEvaPromotionTable> selectPage(MprEvaPromotionTableQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<MprEvaPromotionTable>
     */
    List<MprEvaPromotionTable> selectList(MprEvaPromotionTableQueryForm queryForm);
}
