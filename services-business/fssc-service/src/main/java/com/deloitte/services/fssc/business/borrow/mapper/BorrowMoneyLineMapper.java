package com.deloitte.services.fssc.business.borrow.mapper;

import com.deloitte.services.fssc.business.borrow.entity.BorrowMoneyLine;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 借款行表 Mapper 接口
 * </p>
 *
 * @author qiliao
 * @since 2019-03-04
 */
public interface BorrowMoneyLineMapper extends BaseMapper<BorrowMoneyLine> {
    /**
     * 关联支出小类
     * @param ExpenseSubTypeList
     * @return
     */
    Long existsByExpenseSubTypeIds(@Param("ExpenseSubTypeList") List<Long> ExpenseSubTypeList);
}
