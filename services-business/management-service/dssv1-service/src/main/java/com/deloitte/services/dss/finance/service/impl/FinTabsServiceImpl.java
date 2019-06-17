package com.deloitte.services.dss.finance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.dss.finance.vo.FinCompanyVo;
import com.deloitte.platform.api.dss.finance.vo.ProjectVo;
import com.deloitte.platform.api.dss.finance.vo.TabsVo;
import com.deloitte.services.dss.finance.mapper.FinProjectMapper;
import com.deloitte.services.dss.finance.mapper.FinTabsMapper;
import com.deloitte.services.dss.finance.service.IFinCompanyService;
import com.deloitte.services.dss.finance.service.IFinProjectService;
import com.deloitte.services.dss.finance.service.IFinTabsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;


/**
 * @Author : chitose
 * @Date : Create in 2019-04-09
 * @Description :  FinanceExecution服务实现类
 * @Modified :
 */
@Service
@Transactional
public class FinTabsServiceImpl extends ServiceImpl implements IFinTabsService {

    @Autowired
    private FinTabsMapper finTabsMapper;


    @Override
    public TabsVo selectTabs(Map map) {
        TabsVo tabsVos = finTabsMapper.selectTabs(map);
        return tabsVos;
    }
}

