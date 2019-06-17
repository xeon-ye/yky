package com.deloitte.services.fssc.business.advance.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.advance.param.AdvancePaymentLineQueryForm;
import com.deloitte.services.fssc.business.advance.entity.AdvancePaymentLine;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-03-12
 * @Description : BmAdvancePaymentLine服务类接口
 * @Modified :
 */
public interface IAdvancePaymentLineService extends IService<AdvancePaymentLine> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<AdvancePaymentLine>
     */
    IPage<AdvancePaymentLine> selectPage(AdvancePaymentLineQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<AdvancePaymentLine>
     */
    List<AdvancePaymentLine> selectList(AdvancePaymentLineQueryForm queryForm);


    /**
     * 获取对公预付款明细
     * @param advancePaymentId
     * @return
     */
    List<AdvancePaymentLine> selectList(Long advancePaymentId);


    /**
     * 根据支出小类id查询是关联的对公预付款单
     * @param expenseMainTypeIdList
     * @return
     */
    boolean existsByExpenseSubApTypeIds(List<Long> expenseMainTypeIdList);
}
