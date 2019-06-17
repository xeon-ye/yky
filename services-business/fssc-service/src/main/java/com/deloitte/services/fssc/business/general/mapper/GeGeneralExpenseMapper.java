package com.deloitte.services.fssc.business.general.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deloitte.platform.api.fssc.general.param.GeExpenseBorrowPrepayQueryForm;
import com.deloitte.platform.api.fssc.general.vo.BorrowPrepayListVo;
import com.deloitte.services.fssc.business.general.entity.GeGeneralExpense;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 通用报账单主表 Mapper 接口
 * </p>
 *
 * @author jaws
 * @since 2019-03-14
 */
public interface GeGeneralExpenseMapper extends BaseMapper<GeGeneralExpense> {
    Long countByExpenseMainTypeIds(@Param("idList") List<Long> idList);

    List<BorrowPrepayListVo> findBorrowPrepayList(@Param("form") GeExpenseBorrowPrepayQueryForm form);
}
