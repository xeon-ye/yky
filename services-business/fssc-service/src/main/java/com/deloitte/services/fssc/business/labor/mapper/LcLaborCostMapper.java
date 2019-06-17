package com.deloitte.services.fssc.business.labor.mapper;

import com.deloitte.services.fssc.business.labor.entity.LcLaborCost;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 劳务费报账单头表 Mapper 接口
 * </p>
 *
 * @author qiliao
 * @since 2019-03-25
 */
public interface LcLaborCostMapper extends BaseMapper<LcLaborCost> {
    Long countByExpenseMainTypeIds(@Param("idList") List<Long> idList);
}
