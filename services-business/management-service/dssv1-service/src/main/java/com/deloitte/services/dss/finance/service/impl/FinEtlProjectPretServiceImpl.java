package com.deloitte.services.dss.finance.service.impl;

import com.deloitte.services.dss.finance.entity.FinEtlProjectPret;
import com.deloitte.services.dss.finance.mapper.FinEtlProjectPretMapper;
import com.deloitte.services.dss.finance.service.IFinEtlProjectLibService;
import com.deloitte.services.dss.finance.service.IFinEtlProjectPretService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-29
 * @Description :  DssFinEtlProjectPret服务实现类
 * @Modified :
 */
@Service
@Transactional
public class FinEtlProjectPretServiceImpl implements IFinEtlProjectPretService {


    @Autowired
    private FinEtlProjectPretMapper finEtlProjectPretMapper;

    @Autowired
    private IFinEtlProjectLibService iFinEtlProjectLibService;

    @Override
    public void deleteData(List<FinEtlProjectPret> finEtlProjectPrets) {
        Set<FinEtlProjectPret> set = new HashSet<FinEtlProjectPret>();
        for(FinEtlProjectPret indexCode : finEtlProjectPrets){
            FinEtlProjectPret temp = new FinEtlProjectPret();
            temp.setIndexCode(indexCode.getIndexCode());
            temp.setComCode(indexCode.getComCode());
            set.add(temp);
        }
        for(FinEtlProjectPret resuilt : set){
            finEtlProjectPretMapper.deleteData(resuilt);
        }
    }

    @Override
    public void insertIntoPret() {
        List<FinEtlProjectPret> finEtlProjectPrets = iFinEtlProjectLibService.queryInfo();
        this.deleteData(finEtlProjectPrets);
        for(FinEtlProjectPret temp : finEtlProjectPrets){
            finEtlProjectPretMapper.insertIntoPret(temp);
        }
    }

    @Override
    public List<FinEtlProjectPret> queryGroupBy() {
        List<FinEtlProjectPret> finEtlProjectPrets = finEtlProjectPretMapper.queryGroupBy();
        return finEtlProjectPrets;
    }


}

