package com.deloitte.services.fssc.business.rep.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.ppc.vo.IncomeClaimedVo;
import com.deloitte.platform.api.fssc.rep.param.RecieveLineMsgQueryForm;
import com.deloitte.services.fssc.business.rep.entity.RecieveLineMsg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 收款单收款信息 Mapper 接口
 * </p>
 *
 * @author qiliao
 * @since 2019-04-10
 */
public interface RecieveLineMsgMapper extends BaseMapper<RecieveLineMsg> {

    List<IncomeClaimedVo> conditionsRecie(@Param("page") IPage<IncomeClaimedVo> page,
                                          @Param("form") RecieveLineMsgQueryForm recievePaymentQueryForm);
}
