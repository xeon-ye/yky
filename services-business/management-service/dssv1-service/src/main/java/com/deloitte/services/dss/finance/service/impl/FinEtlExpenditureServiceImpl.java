package com.deloitte.services.dss.finance.service.impl;

import com.deloitte.services.dss.finance.entity.FinEtlPretreatment;
import com.deloitte.services.dss.finance.mapper.FinEtlExpenditureMapper;
import com.deloitte.services.dss.finance.service.IFinEtlExpenditureService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;


/**
 * @Author : chitose
 * @Date : Create in 2019-04-22
 * @Description :  FinEtlExpenditure服务实现类
 * @Modified :
 */
@Service
@Transactional
public class FinEtlExpenditureServiceImpl implements IFinEtlExpenditureService {


    @Autowired
    private FinEtlExpenditureMapper finEtlExpenditureMapper;


    @Override
    public List<FinEtlPretreatment> queryInfoExpBud(Map map) {
        List<FinEtlPretreatment> finEtlPretreatments = finEtlExpenditureMapper.queryInfoExpBud(map);
        return finEtlPretreatments;
    }

    @Override
    public List<FinEtlPretreatment> queryInfoExp(Map map) {
        List<FinEtlPretreatment> finEtlPretreatments = finEtlExpenditureMapper.queryInfoExp(map);
        return finEtlPretreatments;
    }

    @Override
    public List<FinEtlPretreatment> queryInfoForExpEt(Map map) {
        List<FinEtlPretreatment> finEtlPretreatments = finEtlExpenditureMapper.queryInfoForExpEt(map);
        return finEtlPretreatments;
    }

    @Override
    public List<FinEtlPretreatment> queryInfoForExeExp(Map map) {
        List<FinEtlPretreatment> finEtlPretreatments = finEtlExpenditureMapper.queryInfoForExeExp(map);
        return finEtlPretreatments;
    }

    @Override
    public List<FinEtlPretreatment> queryInfoForExpRate(FinEtlPretreatment finEtlPretreatment) {
        Map map = new Hashtable();
        String periodBefore = Integer.parseInt(finEtlPretreatment.getPeriodCode().split("-")[0]) - 1
                + "-" + finEtlPretreatment.getPeriodCode().split("-")[1];
        map.put("periodNow",finEtlPretreatment.getPeriodCode());
        map.put("periodBefore",periodBefore);
        List<FinEtlPretreatment> finEtlPretreatments = finEtlExpenditureMapper.queryInfoForExpRate(map);
        return finEtlPretreatments;
    }

    @Override
    public List<FinEtlPretreatment> queryExpGrowth(FinEtlPretreatment finEtlPretreatment) {
        Map map = new Hashtable();
        String periodBefore = Integer.parseInt(finEtlPretreatment.getPeriodCode().split("-")[0]) - 1 + "-12";
        String periodNow = Integer.parseInt(finEtlPretreatment.getPeriodCode().split("-")[0]) + "-1";
        map.put("periodNow",periodNow);
        map.put("periodBefore",periodBefore);
        List<FinEtlPretreatment> finEtlPretreatments = finEtlExpenditureMapper.queryExpGrowth(map);
        return finEtlPretreatments;
    }
}

