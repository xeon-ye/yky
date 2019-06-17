package com.deloitte.services.fssc.business.travle.mapper;

import com.deloitte.services.fssc.business.travle.entity.TasCostInformationLine;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hjy
 * @since 2019-04-01
 */
public interface TasCostInformationLineMapper extends BaseMapper<TasCostInformationLine> {
    /**
     * 关联支出小类
     * @param ExpenseSubTypeList
     * @return
     */
    Long existsByExpenseSubTypeIds(@Param("ExpenseSubTypeList") List<Long> ExpenseSubTypeList);

}
