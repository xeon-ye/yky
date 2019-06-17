package com.deloitte.services.srpmp.project.mpr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaFundsBudgetQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaFundsBudget;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-04-12
 * @Description : MprEvaFundsBudget服务类接口
 * @Modified :
 */
public interface IMprEvaFundsBudgetService extends IService<MprEvaFundsBudget> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<MprEvaFundsBudget>
     */
    IPage<MprEvaFundsBudget> selectPage(MprEvaFundsBudgetQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<MprEvaFundsBudget>
     */
    List<MprEvaFundsBudget> selectList(MprEvaFundsBudgetQueryForm queryForm);
}
