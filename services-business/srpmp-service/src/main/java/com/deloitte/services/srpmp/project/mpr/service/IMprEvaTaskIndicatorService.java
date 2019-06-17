package com.deloitte.services.srpmp.project.mpr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaTaskIndicatorQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaTaskIndicator;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-04-02
 * @Description : MprEvaTaskIndicator服务类接口
 * @Modified :
 */
public interface IMprEvaTaskIndicatorService extends IService<MprEvaTaskIndicator> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<MprEvaTaskIndicator>
     */
    IPage<MprEvaTaskIndicator> selectPage(MprEvaTaskIndicatorQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<MprEvaTaskIndicator>
     */
    List<MprEvaTaskIndicator> selectList(MprEvaTaskIndicatorQueryForm queryForm);
}
