package com.deloitte.services.dss.finance.service.impl;

import com.deloitte.services.dss.finance.entity.FinEtlProjectPret;
import com.deloitte.services.dss.finance.mapper.FinEtlProjectLibMapper;
import com.deloitte.services.dss.finance.service.IFinEtlProjectLibService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @Author : chitose
 * @Date : Create in 2019-04-29
 * @Description :  DssFinEtlProjectLib服务实现类
 * @Modified :
 */
@Service
@Transactional
public class FinEtlProjectLibServiceImpl implements IFinEtlProjectLibService {


    @Autowired
    private FinEtlProjectLibMapper finEtlProjectLibMapper;


    @Override
    public List<FinEtlProjectPret> queryInfo() {
        List<FinEtlProjectPret> finEtlProjectPrets = finEtlProjectLibMapper.queryInfo();
        return finEtlProjectPrets;
    }
}

