package com.deloitte.services.srpmp.project.mpr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaOutcomeQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaOutcome;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-04-02
 * @Description : MprEvaOutcome服务类接口
 * @Modified :
 */
public interface IMprEvaOutcomeService extends IService<MprEvaOutcome> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<MprEvaOutcome>
     */
    IPage<MprEvaOutcome> selectPage(MprEvaOutcomeQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<MprEvaOutcome>
     */
    List<MprEvaOutcome> selectList(MprEvaOutcomeQueryForm queryForm);
}
