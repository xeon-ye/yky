package com.deloitte.services.fssc.business.advance.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.advance.param.AdvancePaymentInfoQueryForm;
import com.deloitte.platform.api.fssc.advance.vo.AdvancePaymentInfoVo;
import com.deloitte.services.fssc.business.advance.entity.AdvancePaymentInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.services.fssc.business.advance.entity.AdvancePaymentLine;
import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-03-12
 * @Description : BmAdvancePaymentInfo服务类接口
 * @Modified :
 */
public interface IAdvancePaymentInfoService extends IService<AdvancePaymentInfo> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<AdvancePaymentInfo>
     */
    IPage<AdvancePaymentInfo> selectPage(AdvancePaymentInfoQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<AdvancePaymentInfo>
     */
    List<AdvancePaymentInfo> selectList(AdvancePaymentInfoQueryForm queryForm);


    AdvancePaymentInfoVo findInfoById(Long advanceId);

    /**
     * 根据支出大类id查询是关联的对公预付款单
     * @param expenseMainTypeIdList
     * @return
     */
    boolean existsByExpenseMainTypeAdvIds(List<Long> expenseMainTypeIdList);
}
