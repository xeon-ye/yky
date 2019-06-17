package com.deloitte.services.dss.finance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.dss.finance.entity.FinEtlPretreatment;
import com.deloitte.services.dss.finance.mapper.FinEtlIncomeMapper;
import com.deloitte.services.dss.finance.service.IFinEtlIncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;


/**
 * @Author : chitose
 * @Date : Create in 2019-04-09
 * @Description :  FinacenIncome服务实现类
 * @Modified :
 */
@Service
@Transactional
public class FinEtlIncomeServiceImpl extends ServiceImpl implements IFinEtlIncomeService{

    @Autowired
    private FinEtlIncomeMapper finEtlIncomeMapper;


    @Override
    public String queryIndexCode(Map map) {
        String indexCode = finEtlIncomeMapper.queryIndexCode(map);
        return indexCode;
    }

    @Override
    public List<FinEtlPretreatment> queryInfoForIncome(Map map) {
        List<FinEtlPretreatment> finEtlPretreatments = finEtlIncomeMapper.queryInfoForIncome(map);
        return finEtlPretreatments;
    }

    @Override
    public List<FinEtlPretreatment> queryInfoForIncomeEt(Map map) {
        List<FinEtlPretreatment> finEtlPretreatments = finEtlIncomeMapper.queryInfoForIncomeEt(map);
        return finEtlPretreatments;
    }

    @Override
    public List<FinEtlPretreatment> queryInfoForIncomeBud(Map map) {
        List<FinEtlPretreatment> finEtlPretreatments = finEtlIncomeMapper.queryInfoForIncomeBud(map);
        return finEtlPretreatments;
    }

    @Override
    public List<FinEtlPretreatment> queryInfoForExe(Map map) {
        List<FinEtlPretreatment> finEtlPretreatments = finEtlIncomeMapper.queryInfoForExe(map);
        return finEtlPretreatments;
    }

    @Override
    public List<FinEtlPretreatment> queryInfoForRate(FinEtlPretreatment finEtlPretreatment) {
        Map map = new Hashtable();
        String periodBefore = Integer.parseInt(finEtlPretreatment.getPeriodCode().split("-")[0]) - 1
                + "-" + finEtlPretreatment.getPeriodCode().split("-")[1];
        map.put("periodNow",finEtlPretreatment.getPeriodCode());
        map.put("periodBefore",periodBefore);
        List<FinEtlPretreatment> finEtlPretreatments = finEtlIncomeMapper.queryInfoForRate(map);
        return finEtlPretreatments;
    }

    @Override
    public List<FinEtlPretreatment> queryGrowth(FinEtlPretreatment finEtlPretreatment) {
        Map map = new Hashtable();
        String periodBefore = Integer.parseInt(finEtlPretreatment.getPeriodCode().split("-")[0]) - 1 + "-12";
        String periodNow = Integer.parseInt(finEtlPretreatment.getPeriodCode().split("-")[0]) + "-1";
        map.put("periodNow",periodNow);
        map.put("periodBefore",periodBefore);
        List<FinEtlPretreatment> finEtlPretreatments = finEtlIncomeMapper.queryGrowth(map);
        return finEtlPretreatments;
    }
}

