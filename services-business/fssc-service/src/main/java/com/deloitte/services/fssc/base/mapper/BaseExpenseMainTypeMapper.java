package com.deloitte.services.fssc.base.mapper;

import com.deloitte.services.fssc.base.entity.BaseExpenseMainType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 支出大类 Mapper 接口
 * </p>
 *
 * @author hjy
 * @since 2019-02-27
 */
public interface BaseExpenseMainTypeMapper extends BaseMapper<BaseExpenseMainType> {

    /**
     * 清空关联的预算科目ID
     * @param ids
     * @return
     */
    Long clearBudgetAccountId(@Param("ids") List<Long> ids);

}
