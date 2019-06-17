package com.deloitte.services.dss.finance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.dss.finance.vo.AcceptVo;
import com.deloitte.platform.api.dss.finance.vo.FinCompanyVo;
import com.deloitte.platform.api.dss.finance.vo.IncomeBudgetVo;
import com.deloitte.services.dss.finance.mapper.FinRateMapper;
import com.deloitte.services.dss.finance.service.IFinCompanyService;
import com.deloitte.services.dss.finance.service.IFinExpenditureSerivice;
import com.deloitte.services.dss.finance.service.IFinRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * @Author : chitose
 * @Date : Create in 2019-04-09
 * @Description :  FinanceExecution服务实现类
 * @Modified :
 */
@Service
@Transactional
public class FinRateServiceImpl extends ServiceImpl implements IFinRateService {

    @Autowired
    private FinRateMapper finRateMapper;

    @Autowired
    private IFinCompanyService iFinCompanyService;

    @Autowired
    private IFinCompanyService finCompanyService;
    @Autowired
    private IFinExpenditureSerivice finExpenditureSerivice;

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
    public List<IncomeBudgetVo> selectRate(Map map) {
        Map table = new HashMap();
        table.put("periodCode",map.get("month"));
        table.put("indexCode",map.get("indexCode"));
        String comCodes = this.getComCode(map);
        table.put("comCode",comCodes);
        List<IncomeBudgetVo> list = finRateMapper.selectRate(table);
        return list;
    }

    @Override
    public List<IncomeBudgetVo> selectComRate(Map map) {
        Map table = new HashMap();
        table.put("periodCode",map.get("month"));
        table.put("indexCode",map.get("indexCode"));
        String comCodes = this.getComCode(map);
        table.put("comCode",comCodes);
        List<IncomeBudgetVo> list = finRateMapper.selectComRate(table);
        return list;
    }

    @Override
    public List<IncomeBudgetVo> selectExpRate(Map map) {
        Map table = new HashMap();
        table.put("periodCode",map.get("month"));
        table.put("indexCode",map.get("indexCode"));
        String comCodes = this.getComCode(map);
        table.put("comCode",comCodes);
        List<IncomeBudgetVo> rate = finRateMapper.selectExpRate(table);
        return rate;
    }

    @Override
    public List<IncomeBudgetVo> selectComExpRate(Map map) {
        Map table = new HashMap();
        table.put("periodCode",map.get("month"));
        table.put("indexCode",map.get("indexCode"));
        String comCodes = this.getComCode(map);
        table.put("comCode",comCodes);
        List<IncomeBudgetVo> rate = finRateMapper.selectComExpRate(table);
        return rate;
    }

    @Override
    public List<IncomeBudgetVo> selectRateMonth(Map map) {
        List<IncomeBudgetVo> list = finRateMapper.selectRateMonth(map);
        return list;
    }

    @Override
    public List<IncomeBudgetVo> selectComRateMonth(Map table) {
        Map map = new Hashtable();
        map.put("periodCodeNow",table.get("month"));
        String periodCodeBefore = Integer.parseInt(table.get("month").toString().split("-")[0]) + "-1";
        map.put("periodCodeBefore",periodCodeBefore);
        map.put("monthTotal",finExpenditureSerivice.selectMonth(table));
        if(table.get("indexCode") != null)
            map.put("indexCode",table.get("indexCode"));
        String comCode = this.getComCode(table);
        map.put("comCode",comCode);
        List<IncomeBudgetVo> list = finRateMapper.selectComRateMonth(map);
        return list;
    }

    @Override
    public List<IncomeBudgetVo> selectComYearRate(Map map) {
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
            return finRateMapper.selectComYearRate(table);
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
            return finRateMapper.selectComYearRate(table);
        }
    }


}

