package com.deloitte.services.dss.finance.service.impl;

import com.deloitte.platform.api.dss.finance.vo.*;
import com.deloitte.services.dss.finance.mapper.FinanceBudgetMapper;
import com.deloitte.services.dss.finance.mapper.FinanceExecutionMapper;
import com.deloitte.services.dss.finance.mapper.FinanceIncomeMapper;
import com.deloitte.services.dss.finance.service.IFinBudgetService;
import com.deloitte.services.dss.finance.service.IFinCompanyService;
import com.deloitte.services.dss.finance.service.IFinGrowthService;
import com.deloitte.services.dss.finance.service.IFinanceExecutionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.dss.util.TimeUtil;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author : chitose
 * @Date : Create in 2019-04-09
 * @Description :  FinanceExecution服务实现类
 * @Modified :
 */
@Service
@Transactional
public class FinanceExecutionServiceImpl extends ServiceImpl implements IFinanceExecutionService {

    @Autowired
    private FinanceExecutionMapper financeExecutionMapper;

    @Autowired
    private IFinCompanyService iFinCompanyService;

    @Autowired
    private IFinGrowthService iFinGrowthService;

    @Autowired
    private IFinBudgetService iFinBudgetService;

    @Autowired
    private IFinCompanyService finCompanyService;

    //拼接机构数组的方法
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
            comCode = comCode + ")";
        } else {
            for (int i = 0; i < comCodeList.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCodeList.get(i) + "'";
                } else {
                    comCode = comCode + ",'" + comCodeList.get(i) + "'";
                }
            }
            comCode = comCode + ")";
        }
        return comCode;
    }

    @Override
    public List<FinanceExecutionVo> queryExecution(Map map) {
        Map table = new HashMap();
        table.put("periodCode",map.get("month"));
        table.put("indexCode",map.get("indexCode"));
        String comCodes = this.getComCode(map);
        table.put("comCode",comCodes);
        List<FinanceExecutionVo> list = financeExecutionMapper.queryExecution(table);
        return list;
    }

    @Override
    public List<FinanceExecutionVo> queryComExecution(Map map) {
        Map table = new HashMap();
        table.put("periodCode",map.get("month"));
        table.put("indexCode",map.get("indexCode"));
        String comCodes = this.getComCode(map);
        table.put("comCode",comCodes);
        List<FinanceExecutionVo> list = financeExecutionMapper.queryComExecution(table);
        return list;
    }

    @Override
    public List<FinanceExecutionVo> queryExpExecution(Map map) {
        Map table = new HashMap();
        table.put("indexCode",map.get("indexCode"));
        table.put("periodCode",map.get("periodCode"));
        String comCode = this.getComCode(map);
        table.put("comCode",comCode);
        List<FinanceExecutionVo> financeExecutionVos = financeExecutionMapper.queryExpExecution(table);
        return financeExecutionVos;
    }

    @Override
    public List<List<FinanceExecutionVo>> queryExpComExecution(AcceptVo acceptVo) {
        List<List<FinanceExecutionVo>> lists = new ArrayList<List<FinanceExecutionVo>>();
        FinanceExecutionVo financeExecutionVo = new FinanceExecutionVo();
        financeExecutionVo.setPeriodCode(acceptVo.getPeriodCode());
        financeExecutionVo.setIndexCode(acceptVo.getIndexCode());
        if(acceptVo.getComCode() == null || (acceptVo.getComCode() != null && acceptVo.getComCode().size() == 0)){
            List<FinCompanyVo> finCompanyVos = iFinCompanyService.selectCompany();
            for(int i = 0 ; i < finCompanyVos.size(); i++){
                financeExecutionVo.setComCode(finCompanyVos.get(i).getComCode());
                List<FinanceExecutionVo> list = financeExecutionMapper.queryExpComExecution(financeExecutionVo);
                lists.add(list);
            }
        }else {
            for(int i = 0; i < acceptVo.getComCode().size(); i++){
                financeExecutionVo.setComCode(acceptVo.getComCode().get(i));
                List<FinanceExecutionVo> list = financeExecutionMapper.queryExpComExecution(financeExecutionVo);
                lists.add(list);
            }
        }
        return lists;
    }

    @Override
    public List<IncomeBudgetVo> selectComBudgetAll(Map map) {
        map.put("indexCode","FIND0001");
        List<IncomeBudgetVo> budgets = iFinBudgetService.selectComBudget(map);
        map.put("indexCode","FINC0001");
        List<FinanceExecutionVo> executions = this.queryComExecution(map);
        map.put("indexCode","FINC0013");
        List<GrowthVo> growthes = iFinGrowthService.quesryComGrowth(map);
        if(null!=budgets && budgets.size()>0 && null != growthes && growthes.size()>0){
            for(GrowthVo growthVo : growthes){
                for(int i=0;i<budgets.size();i++){
                    if(budgets.get(i).getComCode().equals(growthVo.getComCode())){
                        budgets.get(i).setRate(growthVo.getExecution());
                    }
                }
            }
        }
        if(null!=budgets && budgets.size()>0 && null!= executions && executions.size()>0){
            for(FinanceExecutionVo financeExecutionVo:executions){
                for(int i=0;i<budgets.size();i++){
                    if(budgets.get(i).getComCode().equals(financeExecutionVo.getComCode())){
                        budgets.get(i).setExecution(financeExecutionVo.getExecution());
                    }
                }
            }
        }


//        for(int i = 0 ; i < budgets.size(); i++){
//            budgets.get(i).setRate(growthes.get(i).getExecution());
//        }
//        for(int i = 0 ; i < budgets.size(); i++){
//            budgets.get(i).setExecution(executions.get(i).getExecution());
//        }
        return budgets;
    }

    @Override
    public List<IncomeBudgetVo> selectComExpBudgetAll(AcceptVo acceptVo) {
        Map map = new HashMap();
        map.put("comCode",acceptVo.getComCode());
        map.put("month", TimeUtil.getTime());
        acceptVo.setIndexCode("FINC0022");
        List<List<FinanceExecutionVo>> executions = this.queryExpComExecution(acceptVo);
        acceptVo.setIndexCode("FINC0068");
        List<List<GrowthVo>> growthes = iFinGrowthService.quesryExpComGrowth(acceptVo);
        map.put("indexCode","FIND0013");
        List<IncomeBudgetVo> budgets = iFinBudgetService.selectComExpBudget(map);
        if(null != growthes && growthes.size()>0 && null != budgets && budgets.size()>0){
            for(List<GrowthVo> list:growthes ){
                if(null != list && list.size()>0){
                    for(GrowthVo grotheslist:list){
                        for(int i=0;i<budgets.size();i++){
                            if(budgets.get(i).getComCode().equals(grotheslist.getComCode())){
                                budgets.get(i).setRate(grotheslist.getYtdN()
                                        .divide(grotheslist.getYtdD(),4,BigDecimal.ROUND_HALF_UP)
                                        .subtract(BigDecimal.valueOf(1)));
                            }
                        }
                    }
                }
            }
        }

        if(null !=executions && executions.size()>0 && null != budgets && budgets.size()>0){
            for(List<FinanceExecutionVo> list: executions){
                if(null!=list && list.size()>0){
                    for(FinanceExecutionVo financeExecutionVo:list ){
                       for(int i=0;i<budgets.size();i++){
                           if(budgets.get(i).getComCode().equals(financeExecutionVo.getComCode())){
                               budgets.get(i).setExecution(financeExecutionVo.getYtdN()
                                       .divide(financeExecutionVo.getYtdD(),4,BigDecimal.ROUND_HALF_UP));
                           }
                       }
                    }
                }
            }
        }
//        budgets.get(0).setRate(growthes.get(0).get(0).getYtdN()
//                .divide(growthes.get(0).get(0).getYtdD(),4,BigDecimal.ROUND_HALF_UP)
//                .subtract(BigDecimal.valueOf(1)));
//        budgets.get(0).setExecution(executions.get(0).get(0).getYtdN()
//                .divide(executions.get(0).get(0).getYtdD(),4,BigDecimal.ROUND_HALF_UP));
        return budgets;
    }

    @Override
    public List<FinanceExecutionVo> selectExecutionMonth(Map map) {
        List<FinanceExecutionVo> list = financeExecutionMapper.selectExecutionMonth(map);
        return list;
    }

    @Override
    public List<FinanceExecutionVo> selectComExecutionMonth(Map map) {
        /*if(null == map.get("comCode")){

        }*/
        List<FinanceExecutionVo> list = financeExecutionMapper.selectComExecutionMonth(map);
        return list;
    }
}

