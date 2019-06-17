package com.deloitte.services.dss.finance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.dss.finance.vo.*;
import com.deloitte.services.dss.finance.mapper.FinGrowthMapper;
import com.deloitte.services.dss.finance.mapper.FinanceExecutionMapper;
import com.deloitte.services.dss.finance.service.IFinCompanyService;
import com.deloitte.services.dss.finance.service.IFinGrowthService;
import com.deloitte.services.dss.finance.service.IFinRateService;
import com.deloitte.services.dss.finance.service.IFinanceExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class FinGrowthServiceImpl extends ServiceImpl implements IFinGrowthService {

    @Autowired
    private FinGrowthMapper finGrowthMapper;

    @Autowired
    private IFinCompanyService iFinCompanyService;

    //拼接机构数组的方法
    public String getComCode(Map map){
        List<String> comCodeList = (List<String>) map.get("comCode");
        String comCode = "";
        if (null == comCodeList || (comCodeList != null && comCodeList.size() == 0)) {
            List<FinCompanyVo> finCompanyVos = iFinCompanyService.selectCompany();
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
    public List<GrowthVo> quesryGrowth(Map map) {
        map.put("comCode",getComCode(map));
        List<GrowthVo> GrowthVos = finGrowthMapper.quesryGrowth(map);
        return GrowthVos;
    }
    @Override
    public List<GrowthVo> quesryGrowthMonth(Map map) {
        List<GrowthVo> GrowthVos = finGrowthMapper.quesryGrowth(map);
        return GrowthVos;
    }
    @Override
    public List<GrowthVo> quesryGrowthPerMonth(Map map) {
        List<GrowthVo> GrowthVos = finGrowthMapper.quesryGrowthPerMonth(map);
        return GrowthVos;
    }

    @Override
    public List<GrowthVo> quesryComGrowth(Map map) {
        Map table = new HashMap();
        table.put("periodCode",map.get("month"));
        table.put("indexCode",map.get("indexCode"));
        String comCodes = this.getComCode(map);
        table.put("comCode",comCodes);
        List<GrowthVo> list = finGrowthMapper.quesryComGrowth(table);
        return list;
    }

    @Override
    public List<GrowthVo> quesryExpGrowth(Map map) {
        map.put("comCode",getComCode(map));
        List<GrowthVo> GrowthVos = finGrowthMapper.quesryExpGrowth(map);
        return GrowthVos;
    }

    @Override
    public List<List<GrowthVo>> quesryExpComGrowth(AcceptVo acceptVo) {
        List<List<GrowthVo>> lists = new ArrayList<List<GrowthVo>>();
        GrowthVo growthVo = new GrowthVo();
        growthVo.setPeriodCode(acceptVo.getPeriodCode());
        if(acceptVo.getComCode().isEmpty()){
            List<FinCompanyVo> finCompanyVos = iFinCompanyService.selectCompany();
            for(int i = 0 ; i < finCompanyVos.size(); i++){
                growthVo.setComCode(finCompanyVos.get(i).getComCode());
                List<GrowthVo> growthVos = finGrowthMapper.quesryExpComGrowth(growthVo);
                lists.add(growthVos);
            }
        }else {
            for(int i = 0; i < acceptVo.getComCode().size(); i++){
                growthVo.setComCode(acceptVo.getComCode().get(i));
                List<GrowthVo> growthVos = finGrowthMapper.quesryExpComGrowth(growthVo);
                lists.add(growthVos);
            }
        }
        return lists;
    }
}

