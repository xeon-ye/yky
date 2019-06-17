package com.deloitte.services.dss.finance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.dss.finance.vo.AcceptVo;
import com.deloitte.platform.api.dss.finance.vo.FinCompanyVo;
import com.deloitte.platform.api.dss.finance.vo.IncomeBudgetVo;
import com.deloitte.services.dss.finance.entity.FinEtlPretreatment;
import com.deloitte.services.dss.finance.mapper.FinAchievementMapper;
import com.deloitte.services.dss.finance.mapper.FinRateMapper;
import com.deloitte.services.dss.finance.service.IFinAchivementService;
import com.deloitte.services.dss.finance.service.IFinCompanyService;
import com.deloitte.services.dss.finance.service.IFinRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Hashtable;
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
public class FinAchivementServiceImpl extends ServiceImpl implements IFinAchivementService {

    @Autowired
    private FinAchievementMapper finAchievementMapper;
    @Autowired
    private IFinCompanyService finCompanyService;

    @Override
    public List<IncomeBudgetVo> selectAvgAchive(Map map) {
        Map table = new Hashtable();
        table.put("periodCode", map.get("year"));
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
            return finAchievementMapper.selectAvgAchive(table);
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
            return finAchievementMapper.selectAvgAchive(table);
        }
    }

    @Override
    public List<IncomeBudgetVo> selectAvgAchiveRate(Map map) {
        Map table = new Hashtable();
        table.put("periodCode", map.get("year"));
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
            return finAchievementMapper.selectAvgAchiveRate(table);
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
            return finAchievementMapper.selectAvgAchiveRate(table);
        }
    }
}

