package com.deloitte.services.srpmp.project.budget.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.srpmp.project.budget.param.SrpmsProjectBudgetAccountQueryForm;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetAccountForm;
import com.deloitte.services.srpmp.project.budget.entity.SrpmsProjectBudgetAccount;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-04-19
 * @Description : SrpmsProjectBudgetAccount服务类接口
 * @Modified :
 */
public interface ISrpmsProjectBudgetAccountService extends IService<SrpmsProjectBudgetAccount> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<SrpmsProjectBudgetAccount>
     */
    IPage<SrpmsProjectBudgetAccount> selectPage(SrpmsProjectBudgetAccountQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<SrpmsProjectBudgetAccount>
     */
    List<SrpmsProjectBudgetAccount> selectList(SrpmsProjectBudgetAccountQueryForm queryForm);

    /**
     * 新增或者保存预算科目
     * @param form
     */
    Long saveOrUpdateBudgetAccount(SrpmsProjectBudgetAccountForm form);
}
