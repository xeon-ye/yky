package com.deloitte.services.fssc.business.advance.mapper;

import com.deloitte.services.fssc.business.advance.entity.AdvancePaymentInfo;
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
public interface AdvancePaymentInfoMapper extends BaseMapper<AdvancePaymentInfo> {
    Long countByExpenseMainTypeIds(@Param("expenseMainTypeIdList") List<Long> expenseMainTypeIdList);

}
