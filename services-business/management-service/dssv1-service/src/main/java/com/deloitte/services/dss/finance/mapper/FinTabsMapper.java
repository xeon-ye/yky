package com.deloitte.services.dss.finance.mapper;


import com.deloitte.platform.api.dss.finance.vo.TabsVo;

import java.util.Map;

/**
 * <p>
 * tabs 接口
 * </p>
 *
 * @author chitose
 * @since 2019-04-27
 */
public interface FinTabsMapper {


    /**
     *
     * @param map
     * @return
     */
    TabsVo selectTabs(Map map);

}
