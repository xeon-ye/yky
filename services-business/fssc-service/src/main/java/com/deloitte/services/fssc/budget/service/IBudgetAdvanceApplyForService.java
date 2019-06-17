package com.deloitte.services.fssc.budget.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.budget.param.BudgetAdvanceApplyForQueryForm;
import com.deloitte.services.fssc.budget.entity.BudgetAdvanceApplyFor;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-12
 * @Description : BudgetAdvanceApplyFor服务类接口
 * @Modified :
 */
public interface IBudgetAdvanceApplyForService extends IService<BudgetAdvanceApplyFor> {

    /**
     * 根据单据获取预算信息
     * @param documentId
     * @param documentType
     * @return
     */
    BudgetAdvanceApplyFor getByDocument(Long documentId,String documentType);

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BudgetAdvanceApplyFor>
     */
    IPage<BudgetAdvanceApplyFor> selectPage(BudgetAdvanceApplyForQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BudgetAdvanceApplyFor>
     */
    List<BudgetAdvanceApplyFor> selectList(BudgetAdvanceApplyForQueryForm queryForm);
}
