package com.deloitte.services.dss.finance.service.impl;

import com.deloitte.services.dss.finance.entity.FinEtlProjectPret;
import com.deloitte.services.dss.finance.mapper.FinEtlProjectFactMapper;
import com.deloitte.services.dss.finance.service.IFinEtlProjectFactService;
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
 * @Description :  DssFinEtlProjectFact服务实现类
 * @Modified :
 */
@Service
@Transactional
public class FinEtlProjectFactServiceImpl implements IFinEtlProjectFactService {


    @Autowired
    private FinEtlProjectFactMapper finEtlProjectFactMapper;

    @Autowired
    private IFinEtlProjectPretService iFinEtlProjectPretService;

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
            finEtlProjectFactMapper.deleteData(resuilt);
        }
    }

    @Override
    public void insertIntoFact() {
        List<FinEtlProjectPret> finEtlProjectPrets = iFinEtlProjectPretService.queryGroupBy();
        this.deleteData(finEtlProjectPrets);
        for(FinEtlProjectPret temp : finEtlProjectPrets){
            finEtlProjectFactMapper.insertIntoFact(temp);
        }
    }
}

