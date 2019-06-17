package com.deloitte.services.fssc.business.contract.mapper;

import com.deloitte.platform.api.fssc.general.param.GeExpenseBorrowPrepayQueryForm;
import com.deloitte.platform.api.fssc.general.vo.BorrowPrepayListVo;
import com.deloitte.services.fssc.business.contract.entity.CrbContractReimburseBill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hjy
 * @since 2019-03-26
 */
public interface CrbContractReimburseBillMapper extends BaseMapper<CrbContractReimburseBill> {
    Long countByExpenseMainTypeIds(@Param("idList") List<Long> idList);
    List<BorrowPrepayListVo> findBorrowPrepayList(@Param("form") GeExpenseBorrowPrepayQueryForm form);


}
