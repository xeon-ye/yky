package com.deloitte.services.dss.finance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.dss.finance.entity.FinEtlPretreatment;
import com.deloitte.services.dss.finance.mapper.FinEtlFactMapper;
import com.deloitte.services.dss.finance.service.IFinEtlFactService;
import com.deloitte.services.dss.finance.service.IFinEtlPretreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-17
 * @Description :  FinEtlFact服务实现类
 * @Modified :
 */
@Service
@Transactional
public class FinEtlFactServiceImpl extends ServiceImpl implements IFinEtlFactService {

    @Autowired
    private IFinEtlPretreatmentService iFinEtlPretreatmentService;

    @Autowired
    private FinEtlFactMapper finEtlFactMapper;

    @Override
    public void insertProToFact(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlPretreatmentService.queryGroupBy(finEtlPretreatment);
        this.deleteData(finEtlPretreatments);
        for(FinEtlPretreatment temp : finEtlPretreatments){
            finEtlFactMapper.insertProToFact(temp);
        }
    }

    @Override
    public void insertProToFactIncome(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlPretreatmentService.queryGroupByIncome(finEtlPretreatment);
        this.deleteData(finEtlPretreatments);
        for(FinEtlPretreatment temp : finEtlPretreatments){
            finEtlFactMapper.insertProToFact(temp);
        }
    }

    @Override
    public void insertProToFactIncomeBud(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlPretreatmentService.queryGroupByIncomeBud(finEtlPretreatment);
        this.deleteData(finEtlPretreatments);
        for(FinEtlPretreatment temp : finEtlPretreatments){
            finEtlFactMapper.insertProToFact(temp);
        }
    }

    @Override
    public void insertProToFactExe(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlPretreatmentService.queryGroupByExe(finEtlPretreatment);
        this.deleteData(finEtlPretreatments);
        for(FinEtlPretreatment temp : finEtlPretreatments){
            finEtlFactMapper.insertProToFact(temp);
        }
    }

    @Override
    public void insertProToFactGrowth(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlPretreatmentService.queryGroupByGrowth(finEtlPretreatment);
        this.deleteData(finEtlPretreatments);
        for(FinEtlPretreatment temp : finEtlPretreatments){
            finEtlFactMapper.insertProToFact(temp);
        }
    }

    @Override
    public void deleteData(List<FinEtlPretreatment> finEtlPretreatments) {
        Set<FinEtlPretreatment> set = new HashSet<FinEtlPretreatment>();
        for(FinEtlPretreatment indexCode : finEtlPretreatments){
            FinEtlPretreatment temp = new FinEtlPretreatment();
            temp.setIndexCode(indexCode.getIndexCode());
            temp.setPeriodCode(indexCode.getPeriodCode());
            set.add(temp);
        }
        for(FinEtlPretreatment resuilt : set){
            finEtlFactMapper.deleteData(resuilt);
        }
    }

    @Override
    public void insertProToFactExpBud(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlPretreatmentService.queryGroupByExpBud(finEtlPretreatment);
        this.deleteData(finEtlPretreatments);
        for(FinEtlPretreatment temp : finEtlPretreatments){
            finEtlFactMapper.insertProToFact(temp);
        }
    }

    @Override
    public void insertProToFactExp(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlPretreatmentService.queryGroupByExp(finEtlPretreatment);
        this.deleteData(finEtlPretreatments);
        for(FinEtlPretreatment temp : finEtlPretreatments){
            finEtlFactMapper.insertProToFact(temp);
        }
    }

    @Override
    public void insertProToFactExpExe(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlPretreatmentService.queryGroupByExpExe(finEtlPretreatment);
        this.deleteData(finEtlPretreatments);
        for(FinEtlPretreatment temp : finEtlPretreatments){
            finEtlFactMapper.insertProToFact(temp);
        }
    }

    @Override
    public void insertProToFactAchive(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlPretreatmentService.queryGroupByAchive(finEtlPretreatment);
        this.deleteData(finEtlPretreatments);
        for(FinEtlPretreatment temp : finEtlPretreatments){
            finEtlFactMapper.insertProToFact(temp);
        }
    }

    @Override
    public void insertProToFactComAchive(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlPretreatmentService.queryGroupByComAchive(finEtlPretreatment);
        this.deleteData(finEtlPretreatments);
        for(FinEtlPretreatment temp : finEtlPretreatments){
            finEtlFactMapper.insertProToFact(temp);
        }
    }

    @Override
    public void insertProToFactAvgAchive(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlPretreatmentService.queryGroupByAvgAchive(finEtlPretreatment);
        this.deleteData(finEtlPretreatments);
        for(FinEtlPretreatment temp : finEtlPretreatments){
            finEtlFactMapper.insertProToFact(temp);
        }
    }

    @Override
    public void insertProToFactAvgSciAchive(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlPretreatmentService.queryGroupByAvgSciAchive(finEtlPretreatment);
        this.deleteData(finEtlPretreatments);
        for(FinEtlPretreatment temp : finEtlPretreatments){
            finEtlFactMapper.insertProToFact(temp);
        }
    }

    @Override
    public void insertProToFactIncomeRate(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlPretreatmentService.queryGroupByIncomeRate(finEtlPretreatment);
        this.deleteData(finEtlPretreatments);
        for(FinEtlPretreatment temp : finEtlPretreatments){
            finEtlFactMapper.insertProToFact(temp);
        }
    }

    @Override
    public void insertProToFactExpRate(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlPretreatmentService.queryGroupByExpRate(finEtlPretreatment);
        this.deleteData(finEtlPretreatments);
        for(FinEtlPretreatment temp : finEtlPretreatments){
            finEtlFactMapper.insertProToFact(temp);
        }
    }

    @Override
    public void insertProToFactExpGrowth(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlPretreatmentService.queryGroupByExpGrowth(finEtlPretreatment);
        this.deleteData(finEtlPretreatments);
        for(FinEtlPretreatment temp : finEtlPretreatments){
            finEtlFactMapper.insertProToFact(temp);
        }
    }

    @Override
    public void insertProToFactLiab(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlPretreatmentService.queryGroupByLiab(finEtlPretreatment);
        this.deleteData(finEtlPretreatments);
        for(FinEtlPretreatment temp : finEtlPretreatments){
            finEtlFactMapper.insertProToFact(temp);
        }
    }

    @Override
    public void insertProToFactProExpBud(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlPretreatmentService.queryGroupByProExpBud(finEtlPretreatment);
        this.deleteData(finEtlPretreatments);
        for(FinEtlPretreatment temp : finEtlPretreatments){
            finEtlFactMapper.insertProToFact(temp);
        }
    }

    @Override
    public void insertProToFactProExp(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlPretreatmentService.queryGroupByProExp(finEtlPretreatment);
        this.deleteData(finEtlPretreatments);
        for(FinEtlPretreatment temp : finEtlPretreatments){
            finEtlFactMapper.insertProToFact(temp);
        }
    }

    @Override
    public void insertProToFactProExpBudExe(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlPretreatmentService.queryGroupByProExpBudExe(finEtlPretreatment);
        this.deleteData(finEtlPretreatments);
        for(FinEtlPretreatment temp : finEtlPretreatments){
            finEtlFactMapper.insertProToFact(temp);
        }
    }

    @Override
    public void insertProToFactProExpBudExeD(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlPretreatmentService.queryGroupByProExpBudExeD(finEtlPretreatment);
        this.deleteData(finEtlPretreatments);
        for(FinEtlPretreatment temp : finEtlPretreatments){
            finEtlFactMapper.insertProToFact(temp);
        }
    }

    @Override
    public void insertProToFactLiaAndDep(FinEtlPretreatment finEtlPretreatment) {
        List<FinEtlPretreatment> finEtlPretreatments = iFinEtlPretreatmentService.queryGroupByLiaAndDep(finEtlPretreatment);
        this.deleteData(finEtlPretreatments);
        for(FinEtlPretreatment temp : finEtlPretreatments){
            finEtlFactMapper.insertProToFact(temp);
        }
    }

}

