package com.deloitte.services.fssc.business.advance.mapper;

import com.deloitte.services.fssc.business.advance.entity.AdvancePaymentLine;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hjy
 * @since 2019-03-12
 */
public interface AdvancePaymentLineMapper extends BaseMapper<AdvancePaymentLine> {
    /**
     * 关联支出小类
     * @param ExpenseSubTypeList
     * @return
     */
    Long existsByExpenseSubTypeIds(@Param("ExpenseSubTypeList") List<Long> ExpenseSubTypeList);
}
