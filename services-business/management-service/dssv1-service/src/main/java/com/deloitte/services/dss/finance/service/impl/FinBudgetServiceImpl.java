package com.deloitte.services.dss.finance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.dss.finance.vo.AcceptVo;
import com.deloitte.platform.api.dss.finance.vo.FinCompanyVo;
import com.deloitte.platform.api.dss.finance.vo.GrowthVo;
import com.deloitte.platform.api.dss.finance.vo.IncomeBudgetVo;
import com.deloitte.services.dss.finance.mapper.FinBudgetMapper;
import com.deloitte.services.dss.finance.service.IFinBudgetService;
import com.deloitte.services.dss.finance.service.IFinCompanyService;
import com.deloitte.services.dss.finance.service.IFinExecutionService;
import com.deloitte.services.dss.finance.service.IFinGrowthService;
import com.deloitte.services.dss.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-17
 * @Description :  FinBudget服务实现类
 * @Modified :
 */
@Service
@Transactional
public class FinBudgetServiceImpl extends ServiceImpl implements IFinBudgetService {

    @Autowired
    private FinBudgetMapper finBudgetMapper;

    @Autowired
    private IFinCompanyService finCompanyService;

    //获取机构的方法
    public String getComCode(Map map){
        List<String> comCodeList = (List<String>) map.get("comCode");
        String comCode = "";
        if (null == comCodeList || (comCodeList != null && comCodeList.size() == 0)) {
            List<FinCompanyVo> finCompanyVos = finCompanyService.selectCompany();
            for (int i = 0; i < finCompanyVos.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCode + finCompanyVos.get(i).getComCode() + "'";
                } else {
                    comCode = comCode + ",'" + finCompanyVos.get(i).getComCode() + "'";
                }
            }
        } else {
            for (int i = 0; i < comCodeList.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCodeList.get(i) + "'";
                } else {
                    comCode = comCode + ",'" + comCodeList.get(i) + "'";
                }
            }
        }
        return comCode + ")";
    }

    @Override
    public List<List<IncomeBudgetVo>> selectBudget(Map map) {
        List<List<IncomeBudgetVo>> list = new ArrayList<List<IncomeBudgetVo>>();
        Map table = new Hashtable();
        table.put("periodCode", map.get("month"));
        List<String> comCodeList = (List<String>) map.get("comCode");
        if (null == comCodeList || (comCodeList != null && comCodeList.size() == 0)) {
            List<FinCompanyVo> finCompanyVos = finCompanyService.selectCompany();
            String comCode = "";
            for (int i = 0; i < finCompanyVos.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCode + finCompanyVos.get(i).getComCode() + "'";
                } else {
                    comCode = comCode + ",'" + finCompanyVos.get(i).getComCode() + "'";
                }
            }
            table.put("comCode", comCode + ")");
        } else {
            String comCode = "";
            for (int i = 0; i < comCodeList.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCodeList.get(i) + "'";
                } else {
                    comCode = comCode + ",'" + comCodeList.get(i) + "'";
                }
            }
            table.put("comCode", comCode + ")");
        }
        //当年
        List<IncomeBudgetVo> incomeBudgetVos = finBudgetMapper.selectBudget(table);
        for (int i = 0; i < incomeBudgetVos.size(); i++) {
            incomeBudgetVos.get(i).setPeriodCode(map.get("month").toString());
        }
        list.add(incomeBudgetVos);

        //前一年
        String b1 = Integer.parseInt(map.get("month").toString().split("-")[0]) - 1 + "-" + map.get("month").toString().split("-")[1];
        table.put("periodCode",b1);
        List<IncomeBudgetVo> incomeBudgetVosb1 = finBudgetMapper.selectBudget(table);
        if(!incomeBudgetVosb1.isEmpty()) {
            for (int i = 0; i < incomeBudgetVosb1.size(); i++) {
                incomeBudgetVosb1.get(i).setPeriodCode(b1);
            }
        } else {
            incomeBudgetVosb1 = finBudgetMapper.selectIncomeIndexCode();
            for (int i = 0; i < incomeBudgetVosb1.size(); i++) {
                incomeBudgetVosb1.get(i).setPeriodCode(b1);
            }
        }
        list.add(incomeBudgetVosb1);

        String b2 = Integer.parseInt(b1.split("-")[0]) - 1 + "-" + b1.split("-")[1];
        table.put("periodCode",b2);
        List<IncomeBudgetVo> incomeBudgetVosb2 = finBudgetMapper.selectBudget(table);
        if(!incomeBudgetVosb2.isEmpty()) {
            for (int i = 0; i < incomeBudgetVosb2.size(); i++) {
                incomeBudgetVosb2.get(i).setPeriodCode(b2);
            }
        } else {
            incomeBudgetVosb2 = finBudgetMapper.selectIncomeIndexCode();
            for (int i = 0; i < incomeBudgetVosb2.size(); i++) {
                incomeBudgetVosb2.get(i).setPeriodCode(b2);
            }
        }
        list.add(incomeBudgetVosb2);
        return list;
    }

    @Override
    public List<IncomeBudgetVo> selectComBudget(Map map) {
        Map table = new Hashtable();
        table.put("periodCode", map.get("month"));
        table.put("indexCode",map.get("indexCode"));
        List<String> comCodeList = (List<String>) map.get("comCode");
        if (null == comCodeList || (comCodeList != null && comCodeList.size() == 0)) {
            List<FinCompanyVo> finCompanyVos = finCompanyService.selectCompany();
            String comCode = "";
            for (int i = 0; i < finCompanyVos.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCode + finCompanyVos.get(i).getComCode() + "'";
                } else {
                    comCode = comCode + ",'" + finCompanyVos.get(i).getComCode() + "'";
                }
            }
            table.put("comCode", comCode + ")");
            return finBudgetMapper.selectComBudget(table);
        } else {
            String comCode = "";
            for (int i = 0; i < comCodeList.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCodeList.get(i) + "'";
                } else {
                    comCode = comCode + ",'" + comCodeList.get(i) + "'";
                }
            }
            table.put("comCode", comCode + ")");
            return finBudgetMapper.selectComBudget(table);
        }
    }

    @Override
    public List<List<IncomeBudgetVo>> selectExpBudget(IncomeBudgetVo incomeBudgetVo) {
        List<List<IncomeBudgetVo>> list = new ArrayList<List<IncomeBudgetVo>>();
        Map map = new HashMap();
        map.put("comCode",incomeBudgetVo.getComCodes());
        String comCode = this.getComCode(map);
        incomeBudgetVo.setComCode(comCode);
        List<IncomeBudgetVo> incomeBudgetVos = finBudgetMapper.selectExpBudget(incomeBudgetVo);
        for(int i = 0; i < incomeBudgetVos.size(); i++){
            incomeBudgetVos.get(i).setPeriodCode(incomeBudgetVo.getPeriodCode());
        }
        list.add(incomeBudgetVos);

        Integer b1 = Integer.parseInt(incomeBudgetVo.getPeriodCode().split("-")[0]) - 1;
        incomeBudgetVo.setPeriodCode( b1 + "-" + incomeBudgetVo.getPeriodCode().split("-")[1]);
        List<IncomeBudgetVo> incomeBudgetVosb1 = finBudgetMapper.selectExpBudget(incomeBudgetVo);
        if(!incomeBudgetVosb1.isEmpty()) {
            for (int i = 0; i < incomeBudgetVosb1.size(); i++) {
                incomeBudgetVosb1.get(i).setPeriodCode(incomeBudgetVo.getPeriodCode());
            }
        }else{
            incomeBudgetVosb1 = finBudgetMapper.selectExpIndexCode();
            for(int i = 0; i < incomeBudgetVosb1.size() ; i++){
                incomeBudgetVosb1.get(i).setPeriodCode(incomeBudgetVo.getPeriodCode());
            }
        }
        list.add(incomeBudgetVosb1);

        Integer b2 = b1 - 1;
        incomeBudgetVo.setPeriodCode( b2 + "-" + incomeBudgetVo.getPeriodCode().split("-")[1]);
        List<IncomeBudgetVo> incomeBudgetVosb2 = new ArrayList<IncomeBudgetVo>();
        incomeBudgetVosb2 = finBudgetMapper.selectExpBudget(incomeBudgetVo);
        if(!incomeBudgetVosb2.isEmpty()){
            for(int i = 0; i < incomeBudgetVosb2.size(); i++){
                incomeBudgetVosb2.get(i).setPeriodCode(incomeBudgetVo.getPeriodCode());
            }
        }else{
            incomeBudgetVosb2 = finBudgetMapper.selectExpIndexCode();
            for(int i = 0; i < incomeBudgetVosb2.size() ; i++){
                incomeBudgetVosb2.get(i).setPeriodCode(incomeBudgetVo.getPeriodCode());
            }
        }
        list.add(incomeBudgetVosb2);

        return list;
    }

    /*@Override
    public List<List<IncomeBudgetVo>> selectComExpBudget(AcceptVo acceptVo) {
        List<List<IncomeBudgetVo>> lists = new ArrayList<List<IncomeBudgetVo>>();
        IncomeBudgetVo incomeBudgetVo = new IncomeBudgetVo();
        incomeBudgetVo.setPeriodCode(acceptVo.getPeriodCode());
        if(acceptVo.getComCode().isEmpty()){
            List<FinCompanyVo> finCompanyVos = iFinCompanyService.selectCompany();
            for(int i = 0 ; i < finCompanyVos.size(); i++){
                incomeBudgetVo.setComCode(finCompanyVos.get(i).getComCode());
                List<IncomeBudgetVo> incomeBudgetVos = finBudgetMapper.selectComExpBudget(incomeBudgetVo);
                lists.add(incomeBudgetVos);
            }
        }else {
            for(int i = 0; i < acceptVo.getComCode().size(); i++){
                incomeBudgetVo.setComCode(acceptVo.getComCode().get(i));
                List<IncomeBudgetVo> incomeBudgetVos = finBudgetMapper.selectComExpBudget(incomeBudgetVo);
                lists.add(incomeBudgetVos);
            }
        }
        return lists;
    }*/

    @Override
    public List<IncomeBudgetVo> selectComExpBudget(Map map) {
        Map table = new Hashtable();
        table.put("periodCode", map.get("month"));
        table.put("indexCode", map.get("indexCode"));
        List<String> comCodeList = (List<String>) map.get("comCode");
        if (null == comCodeList || (comCodeList != null && comCodeList.size() == 0)) {
            List<FinCompanyVo> finCompanyVos = finCompanyService.selectCompany();
            String comCode = "";
            for (int i = 0; i < finCompanyVos.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCode + finCompanyVos.get(i).getComCode() + "'";
                } else {
                    comCode = comCode + ",'" + finCompanyVos.get(i).getComCode() + "'";
                }
            }
            table.put("comCode", comCode + ")");
            return finBudgetMapper.selectComExpBudget(table);
        } else {
            String comCode = "";
            for (int i = 0; i < comCodeList.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCodeList.get(i) + "'";
                } else {
                    comCode = comCode + ",'" + comCodeList.get(i) + "'";
                }
            }
            table.put("comCode", comCode + ")");
            return finBudgetMapper.selectComExpBudget(table);
        }
    }
}

