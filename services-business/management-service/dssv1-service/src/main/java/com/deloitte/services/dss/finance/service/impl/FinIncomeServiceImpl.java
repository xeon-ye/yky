package com.deloitte.services.dss.finance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.dss.finance.vo.*;
import com.deloitte.services.dss.finance.mapper.FinIncomeMapper;
import com.deloitte.services.dss.finance.service.IFinCompanyService;
import com.deloitte.services.dss.finance.service.IFinExpenditureSerivice;
import com.deloitte.services.dss.finance.service.IFinIncomeService;
import com.deloitte.services.dss.finance.service.IFinRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;


/**
 * @Author : chitose
 * @Date : Create in 2019-04-09
 * @Description :  FinacenIncome服务实现类
 * @Modified :
 */
@Service
@Transactional
public class FinIncomeServiceImpl extends ServiceImpl implements IFinIncomeService {
    @Autowired
    private FinIncomeMapper finIncomeMapper;

    @Autowired
    private IFinCompanyService iFinCompanyService;

    @Autowired
    private IFinExpenditureSerivice finExpenditureSerivice;

    @Override
    public List<IncomeBudgetVo> selectTotalIncome(Map param) {
        Map map = new HashMap();
        map.put("periodCode",param.get("month"));
        map.put("indexCode", param.get("indexCode"));
        List<String> comCodeList = (List<String>) param.get("comCode");
        if (null == comCodeList || comCodeList.size() == 0) {
            List<FinCompanyVo> finCompanyVos = iFinCompanyService.selectCompany();
            String comCode = "";
            for (int i = 0; i < finCompanyVos.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCode + finCompanyVos.get(i).getComCode() + "'";
                } else {
                    comCode = comCode + ",'" + finCompanyVos.get(i).getComCode() + "'";
                }
            }
            map.put("comCode", comCode + ")");
            return finIncomeMapper.selectTotalIncome(map);
        } else {
            String comCode = "";
            for (int i = 0; i < comCodeList.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCodeList.get(i) + "'";
                } else {
                    comCode = comCode + ",'" + comCodeList.get(i) + "'";
                }
            }
            map.put("comCode", comCode + ")");
            return finIncomeMapper.selectTotalIncome(map);
        }
    }

    @Override
    public List<IncomeBudgetVo> selectIncomeTotal(Map param) {
        Map map = new HashMap();
        map.put("periodCode",param.get("month"));
        map.put("indexCode", param.get("indexCode"));
        List<String> comCodeList = (List<String>) param.get("comCode");
        if (null == comCodeList || comCodeList.size() == 0) {
            List<FinCompanyVo> finCompanyVos = iFinCompanyService.selectCompany();
            String comCode = "";
            for (int i = 0; i < finCompanyVos.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCode + finCompanyVos.get(i).getComCode() + "'";
                } else {
                    comCode = comCode + ",'" + finCompanyVos.get(i).getComCode() + "'";
                }
            }
            map.put("comCode", comCode + ")");
            return finIncomeMapper.selectIncomeTotal(map);
        } else {
            String comCode = "";
            for (int i = 0; i < comCodeList.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCodeList.get(i) + "'";
                } else {
                    comCode = comCode + ",'" + comCodeList.get(i) + "'";
                }
            }
            map.put("comCode", comCode + ")");
            return finIncomeMapper.selectIncomeTotal(map);
        }
    }
    @Override
    public List<IncomeBudgetVo> selectComIncome(Map param) {
        Map map = new HashMap();
        map.put("periodCode",param.get("month"));
        map.put("indexCode", param.get("indexCode"));
        List<String> comCodeList = (List<String>) param.get("comCode");
        if (null == comCodeList || comCodeList.size() == 0) {
            List<FinCompanyVo> finCompanyVos = iFinCompanyService.selectCompany();
            String comCode = "";
            for (int i = 0; i < finCompanyVos.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCode + finCompanyVos.get(i).getComCode() + "'";
                } else {
                    comCode = comCode + ",'" + finCompanyVos.get(i).getComCode() + "'";
                }
            }
            map.put("comCode", comCode + ")");
            return finIncomeMapper.selectComIncome(map);
        } else {
            String comCode = "";
            for (int i = 0; i < comCodeList.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCodeList.get(i) + "'";
                } else {
                    comCode = comCode + ",'" + comCodeList.get(i) + "'";
                }
            }
            map.put("comCode", comCode + ")");
            return finIncomeMapper.selectComIncome(map);
        }

        /*List<List<IncomeBudgetVo>> lists = new ArrayList<List<IncomeBudgetVo>>();
        IncomeBudgetVo incomeBudgetVo = new IncomeBudgetVo();
        incomeBudgetVo.setPeriodCode(acceptVo.getPeriodCode());
        if(acceptVo.getComCode().isEmpty()){
            List<FinCompanyVo> finCompanyVos = iFinCompanyService.selectCompany();
            for(int i = 0 ; i < finCompanyVos.size(); i++){
                incomeBudgetVo.setComCode(finCompanyVos.get(i).getComCode());
                List<IncomeBudgetVo> incomeBudgetVos1 = finIncomeMapper.selectComIncome(incomeBudgetVo);
                lists.add(incomeBudgetVos1);
            }
        }else {
            for(int i = 0; i < acceptVo.getComCode().size(); i++){
                incomeBudgetVo.setComCode(acceptVo.getComCode().get(i));
                List<IncomeBudgetVo> incomeBudgetVos = finIncomeMapper.selectComIncome(incomeBudgetVo);
                lists.add(incomeBudgetVos);
            }
        }
        return lists;*/
    }

    @Override
    public List<IncomeBudgetVo> selectIncomeMonth(Map map) {
        List<IncomeBudgetVo> incomeBudgetVos = finIncomeMapper.selectIncomeMonth(map);
        return incomeBudgetVos;
    }

    @Override
    public List<List<IncomeBudgetVo>> selectComIncomeMonth(AcceptVo acceptVo) {
        List<List<IncomeBudgetVo>> lists = new ArrayList<List<IncomeBudgetVo>>();
        Map<String,String> map = new Hashtable<String,String>();
        map.put("periodCodeNow",acceptVo.getPeriodCode());
        String periodCodeBefore = Integer.parseInt(acceptVo.getPeriodCode().split("-")[0]) + "-1";
        map.put("periodCodeBefore",periodCodeBefore);
        if(null== acceptVo.getComCode() ||  acceptVo.getComCode() .size() == 0){
            List<FinCompanyVo> finCompanyVos = iFinCompanyService.selectCompany();
            for(int i = 0 ; i < finCompanyVos.size(); i++){
                map.put("comCode",finCompanyVos.get(i).getComCode());
                List<IncomeBudgetVo> incomeBudgetVos1 = finIncomeMapper.selectComIncomeMonth(map);
                lists.add(incomeBudgetVos1);
            }
        }else {
            for(int i = 0; i < acceptVo.getComCode().size(); i++){
                map.put("comCode",acceptVo.getComCode().get(i));
                List<IncomeBudgetVo> incomeBudgetVos = finIncomeMapper.selectComIncomeMonth(map);
                lists.add(incomeBudgetVos);
            }
        }
        return lists;
    }

    @Override
    public List<IncomeBudgetVo> selectComIncomeMonth(Map param){
        Map map = new Hashtable<String,String>();
        map.put("periodCodeNow",param.get("month"));
        String periodCodeBefore = Integer.parseInt(param.get("month").toString().split("-")[0]) + "-1";
        map.put("periodCodeBefore",periodCodeBefore);
        map.put("indexCode",param.get("indexCode"));
        map.put("monthTotal",finExpenditureSerivice.selectMonth(param));
        List<String> comCodeList = (List<String>) param.get("comCode");
        if (null == comCodeList || (comCodeList != null && comCodeList.size() == 0)) {
            List<FinCompanyVo> finCompanyVos = iFinCompanyService.selectCompany();
            String comCode= "";
            for(int i = 0 ; i < finCompanyVos.size(); i++){
                if(i == 0){
                    comCode = "('"+comCode+finCompanyVos.get(i).getComCode()+"'";
                }else{
                    comCode = comCode+",'"+finCompanyVos.get(i).getComCode()+"'";
                }
            }
            map.put("comCode",comCode+")");
            return  finIncomeMapper.selectComIncomePerMonth(map);

        }else {
            String comCode= "";
            for(int i = 0; i < comCodeList.size(); i++){
                if(i == 0){
                    comCode = "('"+comCodeList.get(i)+"'";
                }else{
                    comCode = comCode+",'"+comCodeList.get(i)+"'";
                }
            }
            map.put("comCode",comCode+")");
            return  finIncomeMapper.selectComIncomePerMonth(map);
        }
    }
}

