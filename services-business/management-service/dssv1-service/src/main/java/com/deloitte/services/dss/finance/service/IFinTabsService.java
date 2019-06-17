package com.deloitte.services.dss.finance.service;


import com.deloitte.platform.api.dss.finance.vo.IncomeBudgetVo;
import com.deloitte.platform.api.dss.finance.vo.TabsVo;

import java.util.List;
import java.util.Map;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-17
 * @Description : FinRate服务类接口
 * @Modified :
 */
public interface IFinTabsService {

    /**
     *
     * @param map
     * @return
     */
    TabsVo selectTabs(Map map);

}
