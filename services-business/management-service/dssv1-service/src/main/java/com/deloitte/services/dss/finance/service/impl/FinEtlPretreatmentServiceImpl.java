package com.deloitte.services.dss.finance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.dss.finance.entity.FinEtlPretreatment;
import com.deloitte.services.dss.finance.mapper.FinEtlPretreatmentMapper;
import com.deloitte.services.dss.finance.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-17
 * @Description :  FinEtlPretreatment服务实现类
 * @Modified :
 */
@Service
@Transactional
public class FinEtlPretreatmentServiceImpl extends ServiceImpl<FinEtlPretreatmentMapper, FinEtlPretreatment> implements
        IFinEtlPretreatmentService {

    @Autowired
    private FinEtlPretreatmentMapper finEtlPretreatmentMapper;

    @Autowired
    private IFinEtlIncomeService iFinEtlIncomeService;

    @Autowired
    private IFinEtlExpenditureService iFinEtlExpenditureService;

    @Autowired
    private IFinEtlLiabilitiesService iFinEtlLiabilitiesService;

    @Autowired
    private IFinEtlProexpService iFinEtlProexpService;

    @Autowired
    private IFinEtlNationalService iFinEtlNationalService;

    @Autowired
    private IFinEtlAchievementService iFinEtlAchievementService;

    @Override
    public boolean insertSrcToProIncome(FinEtlPretreatment finEtlPretreatment) {
        Map map = new Hashtable();
        /*List<FinEtlPretreatment> finEtlPretreatments = new ArrayList<FinEtlPretreatment>();*/
        Integer month = Integer.parseInt(finEtlPretreatment.getPeriodCode().split("-")[1]);
        if(month == 1){
            map.put("period",finEtlPretreatment.getPeriodCode());
            List<FinEtlPretreatment> finEtlPretreatments = iFinEtlIncomeService.queryInfoForIncome(map);
            this.deleteDate(finEtlPretreatments);
            return this.saveBatch(finEtlPretreatments);
        }else{
            map.put("periodNow",finEtlPretreatment.getPeriodCode());
            String period = finEtlPretreatment.getPeriodCode().split("-")[0] + "-"
                    + (Integer.parseInt(finEtlPretreatment.getPeriodCode().split("-")[1]) - 1);
            map.put("periodBefore",period );
            List<FinEtlPretreatment> finEtlPretreatments = iFinEtlIncomeService.queryInfoForIncomeEt(map);
            this.deleteDate(finEtlPretreatments);
            return this.saveBatch(finEtlPretreatments);
        }
    }

    @Override
    public boolean insertScrToProBud(Map map) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlIncomeService.queryInfoForIncomeBud(map);
        this.deleteDate(finEtlPretreatments);
        return this.saveBatch(finEtlPretreatments);
    }

    @Override
    public boolean insertSrcToProExe(Map map) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlIncomeService.queryInfoForExe(map);
        this.deleteDate(finEtlPretreatments);
        return  this.saveBatch(finEtlPretreatments);
    }

    @Override
    public boolean insertSrcToProRate(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlIncomeService.queryInfoForRate(finEtlPretreatment);
        deleteDate(finEtlPretreatments);
        return this.saveBatch(finEtlPretreatments);
    }

    @Override
    public boolean insertSrcToProExpBud(Map map) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlExpenditureService.queryInfoExpBud(map);
        deleteDate(finEtlPretreatments);
        return this.saveBatch(finEtlPretreatments);
    }

    @Override
    public boolean insertSrcToProExp(FinEtlPretreatment finEtlPretreatment) {
        Map map = new Hashtable();
        List<FinEtlPretreatment> finEtlPretreatments = new ArrayList<FinEtlPretreatment>();
        Integer month = Integer.parseInt(finEtlPretreatment.getPeriodCode().split("-")[1]);
        if(month == 1){
            map.put("period",finEtlPretreatment.getPeriodCode());
            finEtlPretreatments = iFinEtlExpenditureService.queryInfoExp(map);
        }else{
            map.put("periodNow",finEtlPretreatment.getPeriodCode());
            String period = finEtlPretreatment.getPeriodCode().split("-")[0] + "-"
                    + (Integer.parseInt(finEtlPretreatment.getPeriodCode().split("-")[1]) - 1);
            map.put("periodBefore",period );
            finEtlPretreatments = iFinEtlExpenditureService.queryInfoForExpEt(map);
        }
        this.deleteDate(finEtlPretreatments);
        return this.saveBatch(finEtlPretreatments);
    }

    @Override
    public boolean insertSrcToProExeExp(Map map) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlExpenditureService.queryInfoForExeExp(map);
        deleteDate(finEtlPretreatments);
        return  this.saveBatch(finEtlPretreatments);

    }

    @Override
    public boolean insertSrcToProGrowth(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlIncomeService.queryGrowth(finEtlPretreatment);
        for(int i = 0; i < finEtlPretreatments.size(); i++){
            finEtlPretreatments.get(i).setPeriodCode(finEtlPretreatment.getPeriodCode());
        }
        this.deleteDate(finEtlPretreatments);
        return this.saveBatch(finEtlPretreatments);
    }

    @Override
    public boolean insertSrcToProExpRate(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlExpenditureService.queryInfoForExpRate(finEtlPretreatment);
        this.deleteDate(finEtlPretreatments);
        return this.saveBatch(finEtlPretreatments);
    }

    @Override
    public boolean insertSrcToProExpGrowth(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlExpenditureService.queryExpGrowth(finEtlPretreatment);
        for(int i = 0; i < finEtlPretreatments.size(); i++){
            finEtlPretreatments.get(i).setPeriodCode(finEtlPretreatment.getPeriodCode());
        }
        this.deleteDate(finEtlPretreatments);
        return this.saveBatch(finEtlPretreatments);
    }

    @Override
    public boolean insertSrcToProLiabilities(Map map) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlLiabilitiesService.queryLiabilities(map);
        this.deleteDate(finEtlPretreatments);
        return this.saveBatch(finEtlPretreatments);
    }

    @Override
    public boolean insertSrcToProProExp(Map map) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlProexpService.queryInfoForProExp(map);
        this.deleteDate(finEtlPretreatments);
        return this.saveBatch(finEtlPretreatments);
    }

    @Override
    public boolean insertSrcToProNational(Map map) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlNationalService.queryInfoForNational(map);
        this.deleteDate(finEtlPretreatments);
        return this.saveBatch(finEtlPretreatments);
    }

    @Override
    public boolean insertSrcToProNationalExeN(Map map) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlNationalService.queryInfoForNationalExeN(map);
        this.deleteDate(finEtlPretreatments);
        return this.saveBatch(finEtlPretreatments);
    }

    @Override
    public boolean insertSrcToProNationalExeD(Map map) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlNationalService.queryInfoForNationalExeD(map);
        this.deleteDate(finEtlPretreatments);
        return this.saveBatch(finEtlPretreatments);
    }

    @Override
    public boolean insertSrcToProComAchive(Map map) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlAchievementService.queryInfoForComAchive(map);
        this.deleteDate(finEtlPretreatments);
        return this.saveBatch(finEtlPretreatments);
    }

    @Override
    public boolean insertSrcToProAchive(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlAchievementService.queryInfoForAchive(finEtlPretreatment);
        this.deleteDate(finEtlPretreatments);
        return this.saveBatch(finEtlPretreatments);
    }

    @Override
    public boolean insertSrcToProAvgAchive(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlAchievementService.queryInfoForAvgAchive(finEtlPretreatment);
        this.deleteDate(finEtlPretreatments);
        return this.saveBatch(finEtlPretreatments);
    }

    @Override
    public boolean insertSrcToProAvgSciAchive(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlAchievementService.queryInfoForAvgSciAchive(finEtlPretreatment);
        this.deleteDate(finEtlPretreatments);
        return this.saveBatch(finEtlPretreatments);
    }

    @Override
    public boolean insertSrcToLiaAndDep(Map map) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlLiabilitiesService.queryLiaAndDep(map);
        this.deleteDate(finEtlPretreatments);
        return this.saveBatch(finEtlPretreatments);
    }

    @Override
    public List<FinEtlPretreatment> queryGroupBy(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = finEtlPretreatmentMapper.queryGroupBy(finEtlPretreatment);
        return finEtlPretreatments;
    }

    @Override
    public List<FinEtlPretreatment> queryGroupByExe(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = finEtlPretreatmentMapper.queryGroupByExe(finEtlPretreatment);
        return finEtlPretreatments;
    }

    @Override
    public List<FinEtlPretreatment> queryGroupByIncomeBud(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = finEtlPretreatmentMapper.queryGroupByIncomeBud(finEtlPretreatment);
        return finEtlPretreatments;
    }

    @Override
    public List<FinEtlPretreatment> queryGroupByIncome(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = finEtlPretreatmentMapper.queryGroupByIncome(finEtlPretreatment);
        return finEtlPretreatments;
    }

    @Override
    public List<FinEtlPretreatment> queryGroupByExp(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = finEtlPretreatmentMapper.queryGroupByExp(finEtlPretreatment);
        return finEtlPretreatments;
    }

    @Override
    public List<FinEtlPretreatment> queryGroupByExpExe(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = finEtlPretreatmentMapper.queryGroupByExpExe(finEtlPretreatment);
        return finEtlPretreatments;
    }

    @Override
    public List<FinEtlPretreatment> queryGroupByAchive(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = finEtlPretreatmentMapper.queryGroupByAchive(finEtlPretreatment);
        return finEtlPretreatments;
    }

    @Override
    public List<FinEtlPretreatment> queryGroupByComAchive(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = finEtlPretreatmentMapper.queryGroupByComAchive(finEtlPretreatment);
        return finEtlPretreatments;
    }

    @Override
    public List<FinEtlPretreatment> queryGroupByAvgAchive(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = finEtlPretreatmentMapper.queryGroupByAvgAchive(finEtlPretreatment);
        return finEtlPretreatments;
    }

    @Override
    public List<FinEtlPretreatment> queryGroupByAvgSciAchive(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = finEtlPretreatmentMapper.queryGroupByAvgSciAchive(finEtlPretreatment);
        return finEtlPretreatments;
    }

    @Override
    public List<FinEtlPretreatment> queryGroupByIncomeRate(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = finEtlPretreatmentMapper.queryGroupByIncomeRate(finEtlPretreatment);
        return finEtlPretreatments;
    }

    @Override
    public List<FinEtlPretreatment> queryGroupByExpRate(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = finEtlPretreatmentMapper.queryGroupByExpRate(finEtlPretreatment);
        return finEtlPretreatments;
    }

    @Override
    public List<FinEtlPretreatment> queryGroupByExpGrowth(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = finEtlPretreatmentMapper.queryGroupByExpGrowth(finEtlPretreatment);
        return finEtlPretreatments;
    }

    @Override
    public List<FinEtlPretreatment> queryGroupByLiab(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = finEtlPretreatmentMapper.queryGroupByLiab(finEtlPretreatment);
        return finEtlPretreatments;
    }

    @Override
    public List<FinEtlPretreatment> queryGroupByProExpBud(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = finEtlPretreatmentMapper.queryGroupByProExpBud(finEtlPretreatment);
        return finEtlPretreatments;
    }

    @Override
    public List<FinEtlPretreatment> queryGroupByProExp(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = finEtlPretreatmentMapper.queryGroupByProExp(finEtlPretreatment);
        return finEtlPretreatments;
    }

    @Override
    public List<FinEtlPretreatment> queryGroupByProExpBudExe(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = finEtlPretreatmentMapper.queryGroupByProExpBudExe(finEtlPretreatment);
        return finEtlPretreatments;
    }

    @Override
    public List<FinEtlPretreatment> queryGroupByProExpBudExeD(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = finEtlPretreatmentMapper.queryGroupByProExpBudExeD(finEtlPretreatment);
        return finEtlPretreatments;
    }

    @Override
    public List<FinEtlPretreatment> queryGroupByLiaAndDep(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = finEtlPretreatmentMapper.queryGroupByLiaAndDep(finEtlPretreatment);
        return finEtlPretreatments;
    }

    @Override
    public List<FinEtlPretreatment> queryGroupByGrowth(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = finEtlPretreatmentMapper.queryGroupByGrowth(finEtlPretreatment);
        return finEtlPretreatments;
    }

    @Override
    public List<FinEtlPretreatment> queryGroupByExpBud(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = finEtlPretreatmentMapper.queryGroupByExpBud(finEtlPretreatment);
        return finEtlPretreatments;
    }

    @Override
    public void deleteDate(List<FinEtlPretreatment> finEtlPretreatments) {
        for(FinEtlPretreatment indexCode : finEtlPretreatments){
            QueryWrapper<FinEtlPretreatment> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(FinEtlPretreatment.PERIOD_CODE,indexCode.getPeriodCode());
            queryWrapper.eq(FinEtlPretreatment.INDEX_CODE,indexCode.getIndexCode());
            finEtlPretreatmentMapper.delete(queryWrapper);
        }
    }
}

