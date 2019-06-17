package com.deloitte.services.fssc.business.borrow.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.borrow.param.BorrowMoneyLineQueryForm;
import com.deloitte.services.fssc.business.borrow.entity.BorrowMoneyLine;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-03-04
 * @Description : BorrowMoneyLine服务类接口
 * @Modified :
 */
public interface IBorrowMoneyLineService extends IService<BorrowMoneyLine> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BorrowMoneyLine>
     */
    IPage<BorrowMoneyLine> selectPage(BorrowMoneyLineQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BorrowMoneyLine>
     */
    List<BorrowMoneyLine> selectList(BorrowMoneyLineQueryForm queryForm);

    /**
     * 根据主表ID查询行数据
     * @param borrowId
     * @return
     */
    List<BorrowMoneyLine> selectList(Long borrowId);

    /**
     * 根据主表ID及行号查询行数据
     * @param borrowId
     * @param lineNum
     * @return
     */
    BorrowMoneyLine getByDocumentAndLineNum(Long borrowId,Long lineNum);

    /**
     * 根据支出小类id查询是关联的借款单
     * @param expenseMainTypeIdList
     * @return
     */
    boolean existsByExpenseSubTypeIds(List<Long> expenseMainTypeIdList);
}
