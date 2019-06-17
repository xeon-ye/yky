package com.deloitte.services.dss.finance.service.impl;

import com.deloitte.services.dss.finance.entity.FinEtlAchievementFact;
import com.deloitte.services.dss.finance.mapper.FinEtlAchievementFactMapper;
import com.deloitte.services.dss.finance.service.IFinEtlAchievementFactService;
import com.deloitte.services.dss.finance.service.IFinEtlAchievementService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-27
 * @Description :  DssFinEtlAchievementFact服务实现类
 * @Modified :
 */
@Service
@Transactional
public class FinEtlAchievementFactServiceImpl implements IFinEtlAchievementFactService {


    @Autowired
    private FinEtlAchievementFactMapper finEtlAchievementFactMapper;

    @Autowired
    private IFinEtlAchievementService iFinEtlAchievementService;


   /* @Override
    public void insertIntoAchiFact(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlAchievementService.queryInfoForAchive(finEtlPretreatment);
        *//*for(FinEtlPretreatment temp : finEtlPretreatments){
            temp.setIndexCode("FINC0091");
            finEtlAchievementFactMapper.insertIntoAchiFact(temp);
        }*//*
    }*/

    @Override
    public void insertIntoAchiFact(FinEtlAchievementFact finEtlAchievementFact) {

    }

    @Override
    public void deleteData(List<FinEtlAchievementFact> finEtlAchievementFacts) {
        for(FinEtlAchievementFact temp : finEtlAchievementFacts){
            finEtlAchievementFactMapper.deleteData(temp);
        }
    }
}

