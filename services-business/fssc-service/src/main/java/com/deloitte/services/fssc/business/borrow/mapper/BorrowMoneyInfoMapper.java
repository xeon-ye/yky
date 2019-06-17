package com.deloitte.services.fssc.business.borrow.mapper;

import com.deloitte.services.fssc.business.borrow.entity.BorrowMoneyInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 借款主表 Mapper 接口
 * </p>
 *
 * @author qiliao
 * @since 2019-03-04
 */
public interface BorrowMoneyInfoMapper extends BaseMapper<BorrowMoneyInfo> {

    Long countByExpenseMainTypeIds(@Param("expenseMainTypeIdList") List<Long> expenseMainTypeIdList);

}
