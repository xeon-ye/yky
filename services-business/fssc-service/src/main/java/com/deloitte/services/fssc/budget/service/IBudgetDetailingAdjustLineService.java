package com.deloitte.services.fssc.budget.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.budget.param.BudgetDetailingAdjustLineQueryForm;
import com.deloitte.services.fssc.budget.entity.BudgetDetailingAdjustLine;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-05-06
 * @Description : BudgetDetailingAdjustLine服务类接口
 * @Modified :
 */
public interface IBudgetDetailingAdjustLineService extends IService<BudgetDetailingAdjustLine> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BudgetDetailingAdjustLine>
     */
    IPage<BudgetDetailingAdjustLine> selectPage(BudgetDetailingAdjustLineQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BudgetDetailingAdjustLine>
     */
    List<BudgetDetailingAdjustLine> selectList(BudgetDetailingAdjustLineQueryForm queryForm);

    /**
     *  条件查询
     * @param documentId 单据ID
     * @return List<BudgetPublicAdjustLine>
     */
    List<BudgetDetailingAdjustLine> selectList(Long documentId);

    /**
     * 根据预算科目编码Ids查询数量
     * @param ids
     * @return
     */
    Integer countByBudgetAccountIds(List<Long> ids);
}
