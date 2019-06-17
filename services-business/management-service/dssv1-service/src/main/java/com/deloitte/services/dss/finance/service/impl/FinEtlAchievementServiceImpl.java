package com.deloitte.services.dss.finance.service.impl;

import com.deloitte.services.dss.finance.entity.FinEtlPretreatment;
import com.deloitte.services.dss.finance.mapper.FinEtlAchievementMapper;
import com.deloitte.services.dss.finance.service.IFinEtlAchievementService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;


/**
 * @Author : chitose
 * @Date : Create in 2019-04-27
 * @Description :  DssFinEtlAchievement服务实现类
 * @Modified :
 */
@Service
@Transactional
public class FinEtlAchievementServiceImpl  implements IFinEtlAchievementService {


    @Autowired
    private FinEtlAchievementMapper finEtlAchievementMapper;


    @Override
    public List<FinEtlPretreatment> queryInfoForAchive(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = finEtlAchievementMapper.queryInfoForAchive(finEtlPretreatment);
        for(int i = 0; i < finEtlPretreatments.size(); i++){
            finEtlPretreatments.get(i).setIndexCode("FINC0091");
        }
        return finEtlPretreatments;
    }

    @Override
    public List<FinEtlPretreatment> queryInfoForComAchive(Map map) {
        List<FinEtlPretreatment> finEtlPretreatments = finEtlAchievementMapper.queryInfoForComAchive(map);
        return finEtlPretreatments;
    }

    @Override
    public List<FinEtlPretreatment> queryInfoForAvgAchive(FinEtlPretreatment finEtlPretreatment) {
        String periodBefore = (Integer.parseInt(finEtlPretreatment.getPeriodCode()) - 1) + "";
        Map map = new Hashtable();
        map.put("periodNow",finEtlPretreatment.getPeriodCode());
        map.put("periodBefore", periodBefore);
        List<FinEtlPretreatment> finEtlPretreatments = finEtlAchievementMapper.queryInfoForAvgAchive(map);
        for(int i = 0 ; i < finEtlPretreatments.size(); i++){
            finEtlPretreatments.get(i).setIndexCode("FINC0093");
        }
        return finEtlPretreatments;
    }

    @Override
    public List<FinEtlPretreatment> queryInfoForAvgSciAchive(FinEtlPretreatment finEtlPretreatment) {
        Map map = new Hashtable();
        map.put("periodNow",finEtlPretreatment.getPeriodCode());
        map.put("periodBefore",(Integer.parseInt(finEtlPretreatment.getPeriodCode()) - 1) + "");
        List<FinEtlPretreatment> finEtlPretreatments = finEtlAchievementMapper.queryInfoForAvgSciAchive(map);
        return finEtlPretreatments;
    }
}

