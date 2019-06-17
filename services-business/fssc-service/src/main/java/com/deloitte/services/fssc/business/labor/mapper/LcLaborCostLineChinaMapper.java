package com.deloitte.services.fssc.business.labor.mapper;

import com.deloitte.services.fssc.business.labor.entity.LcLaborCostLineChina;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 劳务费报账单行表--中国籍 Mapper 接口
 * </p>
 *
 * @author qiliao
 * @since 2019-03-25
 */
public interface LcLaborCostLineChinaMapper extends BaseMapper<LcLaborCostLineChina> {
    /**
     * 关联支出小类
     * @param ExpenseSubTypeList
     * @return
     */
    Long existsByExpenseSubTypeIds(@Param("ExpenseSubTypeList") List<Long> ExpenseSubTypeList);

}
