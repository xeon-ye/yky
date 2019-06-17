package com.deloitte.services.dss.finance.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.dss.finance.vo.FinanceDepreciationVo;

import java.util.List;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-12
 * @Description : FinanceDepreciation服务类接口
 * @Modified :
 */
public interface IFinanceDepreciationService extends IService {


    /**
     * 查询折旧率
     * @param financeDepreciationVo
     * @return
     */
    List<FinanceDepreciationVo> queryDepreciation(FinanceDepreciationVo financeDepreciationVo);

    /**
     * 计算整体折旧率
     * @param financeDepreciationVo
     * @return
     */
    Double queryAvgDepreciation(FinanceDepreciationVo financeDepreciationVo);

    /**
     * 计算整体折旧率往年同比
     * @param financeDepreciationVo
     * @return
     */
    Double queryRate(FinanceDepreciationVo financeDepreciationVo);

}
