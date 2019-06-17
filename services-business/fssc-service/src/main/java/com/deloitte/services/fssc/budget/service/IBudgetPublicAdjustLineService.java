package com.deloitte.services.fssc.budget.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.services.fssc.budget.entity.BudgetPublicAdjustLine;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-30
 * @Description : BudgetPublicAdjustLine服务类接口
 * @Modified :
 */
public interface IBudgetPublicAdjustLineService extends IService<BudgetPublicAdjustLine> {

    /**
     *  分页查询
     * @param documentId 单据ID
     * @return IPage<BudgetPublicAdjustLine>
     */
    IPage<BudgetPublicAdjustLine> selectPage(Long documentId,Integer currentPage, Integer pageSize);

    /**
     *  条件查询
     * @param documentId 单据ID
     * @return List<BudgetPublicAdjustLine>
     */
    List<BudgetPublicAdjustLine> selectList(Long documentId);

    /**
     * 根据预算科目编码Ids查询数量
     * @param ids
     * @return
     */
    Integer countByBudgetAccountIds(List<Long> ids);
}
