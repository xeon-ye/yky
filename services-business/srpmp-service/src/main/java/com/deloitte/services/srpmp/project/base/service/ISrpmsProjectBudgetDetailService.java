package com.deloitte.services.srpmp.project.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.srpmp.project.budget.param.SrpmsProjectBudgetDetailQueryForm;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetDetailVo;
import com.deloitte.services.srpmp.common.enums.BudgetCategoryEnums;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectBudgetDetail;

import java.util.List;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-19
 * @Description : SrpmsProjectBudgetDetail服务类接口
 * @Modified :
 */
public interface ISrpmsProjectBudgetDetailService extends IService<SrpmsProjectBudgetDetail> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<SrpmsProjectBudgetDetail>
     */
    IPage<SrpmsProjectBudgetDetail> selectPage(SrpmsProjectBudgetDetailQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<SrpmsProjectBudgetDetail>
     */
    List<SrpmsProjectBudgetDetail> selectList(SrpmsProjectBudgetDetailQueryForm queryForm);


    void cleanAndSaveBudgetDetail(List<SrpmsProjectBudgetDetailVo> budgetDetailVoList, BudgetCategoryEnums budgetCategoryEnums, Long projectId);


    List<SrpmsProjectBudgetDetailVo> queryBudgetDetailListByCategory(BudgetCategoryEnums budgetCategoryEnums, Long projectId);

}
