package com.deloitte.services.fssc.business.general.mapper;

import com.deloitte.services.fssc.business.general.entity.GeGeneralExpenseLine;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 通用报账单行明细 Mapper 接口
 * </p>
 *
 * @author jaws
 * @since 2019-03-14
 */
public interface GeGeneralExpenseLineMapper extends BaseMapper<GeGeneralExpenseLine> {

    /**
     * 关联支出小类
     * @param ExpenseSubTypeList
     * @return
     */
    Long existsByExpenseSubTypeIds(@Param("ExpenseSubTypeList") List<Long> ExpenseSubTypeList);
}
