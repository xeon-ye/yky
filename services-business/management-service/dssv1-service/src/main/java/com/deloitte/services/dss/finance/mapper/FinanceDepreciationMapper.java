package com.deloitte.services.dss.finance.mapper;


import com.deloitte.platform.api.dss.finance.vo.FinanceDepreciationVo;

import java.util.List;

/**
 * <p>
 * 累计折旧率 Mapper 接口
 * </p>
 *
 * @author chitose
 * @since 2019-04-12
 */
public interface FinanceDepreciationMapper {

    /**
     * 查询折旧率
     * @param financeDepreciationVo
     * @return
     */
    List<FinanceDepreciationVo> queryDepreciation(FinanceDepreciationVo financeDepreciationVo);

}
