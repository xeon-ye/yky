package com.deloitte.services.dss.finance.mapper;


import com.deloitte.platform.api.dss.finance.vo.IncomeBudgetVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资产负债率 Mapper 接口
 * </p>
 *
 * @author chitose
 * @since 2019-04-12
 */
public interface FinanceLiabilitiesMapper {

    /**
     * 整体资产负债率
     * @param map
     * @return
     */
    List<IncomeBudgetVo> queryLiabilities(Map map);
    //查询整体负债率和折旧率
    List<IncomeBudgetVo> selectLiabilities(Map map);

    /**
     * 查询单个机构年总收入详情
     * @param incomeBudgetVo
     * @return
     */
    List<IncomeBudgetVo> queryComLiabilities(IncomeBudgetVo incomeBudgetVo);

}
