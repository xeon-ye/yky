package com.deloitte.services.fssc.budget.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.budget.param.BudgetDetailingAdjustHeadQueryForm;
import com.deloitte.platform.api.fssc.budget.vo.BudgetDetailingAdjustHeadVo;
import com.deloitte.services.fssc.budget.entity.BudgetDetailingAdjustHead;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-05-06
 * @Description : BudgetDetailingAdjustHead服务类接口
 * @Modified :
 */
public interface IBudgetDetailingAdjustHeadService extends IService<BudgetDetailingAdjustHead> {

    /**
     * 根据ID查询
     * @param adjustId 预算调整单据的id
     * @return
     */
    BudgetDetailingAdjustHeadVo findInfoById(Long adjustId);

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BudgetDetailingAdjustHead>
     */
    IPage<BudgetDetailingAdjustHead> selectPage(BudgetDetailingAdjustHeadQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BudgetDetailingAdjustHead>
     */
    List<BudgetDetailingAdjustHead> selectList(BudgetDetailingAdjustHeadQueryForm queryForm);
}
