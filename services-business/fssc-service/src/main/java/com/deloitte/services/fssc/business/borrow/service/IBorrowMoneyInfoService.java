package com.deloitte.services.fssc.business.borrow.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.fssc.borrow.param.BorrowMoneyInfoQueryForm;
import com.deloitte.platform.api.fssc.borrow.vo.BorrowMoneyInfoVo;
import com.deloitte.services.fssc.business.borrow.entity.BorrowMoneyInfo;

import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-03-04
 * @Description : BorrowMoneyInfo服务类接口
 * @Modified :
 */
public interface IBorrowMoneyInfoService extends IService<BorrowMoneyInfo> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BorrowMoneyInfo>
     */
    IPage<BorrowMoneyInfo> selectPage(BorrowMoneyInfoQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BorrowMoneyInfo>
     */
    List<BorrowMoneyInfo> selectList(BorrowMoneyInfoQueryForm queryForm);

    /**
     * 找到借款
     * @param borrowId
     * @return
     */
    BorrowMoneyInfoVo findInfoById(Long borrowId);



    /**
     * 根据支出大类id查询是关联的借款单
     * @param expenseMainTypeIdList
     * @return
     */
    boolean existsByExpenseMainTypeIds(List<Long> expenseMainTypeIdList);



}
