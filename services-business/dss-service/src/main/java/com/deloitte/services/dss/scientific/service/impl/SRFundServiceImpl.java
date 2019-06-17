package com.deloitte.services.dss.scientific.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.dss.scientific.vo.FundBaseVo;
import com.deloitte.platform.api.dss.scientific.vo.FundColumnarVo;
import com.deloitte.platform.api.dss.scientific.vo.SRFundVo;
import com.deloitte.services.dss.scientific.mapper.SRFundMapper;
import com.deloitte.services.dss.scientific.service.ISRFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class SRFundServiceImpl extends ServiceImpl implements ISRFundService {


    @Autowired
    private SRFundMapper srFundMapper;


    @Override
    public List<SRFundVo> queryOutlineFund(Integer year) {
        return srFundMapper.queryOutlineFund(year);
    }

    @Override
    public List<SRFundVo> queryInnFund(Integer year) {
        return srFundMapper.queryInnFund(year);
    }

    @Override
    public List<SRFundVo> queryBaseFund(Integer year) {
        return srFundMapper.queryBaseFund(year);
    }

    @Override
    public Map yearFundView(Integer year) {
        Long thisYearTotalFund = 0L;   //当年所获经费
        Long thisYearReceiveFund = 0L;  //当年到位经费

        List<SRFundVo> outlineFund = queryOutlineFund(year);
        for (SRFundVo srFundVo : outlineFund) {
            thisYearTotalFund += srFundVo.getTotalFund();
            thisYearReceiveFund += srFundVo.getReceiveFund();
        }
        List<SRFundVo> baseFund = queryBaseFund(year);
        for (SRFundVo srFundVo : baseFund) {
            thisYearTotalFund += srFundVo.getTotalFund();
            thisYearReceiveFund += srFundVo.getReceiveFund();
        }
        List<SRFundVo> innFund = queryInnFund(year);
        for (SRFundVo srFundVo : innFund) {
            thisYearTotalFund += srFundVo.getTotalFund();
            thisYearReceiveFund += srFundVo.getReceiveFund();
        }

        Map map  = new HashMap();
        map.put("thisYearTotalFund",thisYearTotalFund);
        map.put("thisYearReceiveFund",thisYearReceiveFund);
        map.put(year,outlineFund);
        List<SRFundVo> outlineFund1 = queryOutlineFund(year - 1);
        map.put(year-1,outlineFund1);
        List<SRFundVo> outlineFund2 = queryOutlineFund(year - 2);
        map.put(year-2,outlineFund2);
        List<SRFundVo> outlineFund3 = queryOutlineFund(year - 3);
        map.put(year-3,outlineFund3);
        return map;
    }

    @Override
    public Map queryData(Map map) {
        Map resultMap = new HashMap();

        FundBaseVo fundBaseVo = srFundMapper.queryTotalMoney(map);
        FundBaseVo payMoney = srFundMapper.queryPayMoney(map);
        if(fundBaseVo!=null&&payMoney!=null) {
            fundBaseVo.setPayAmount(payMoney.getPayAmount());
        }
        resultMap.put("baseVo",fundBaseVo);
        List<FundColumnarVo> fundColumnarVos = srFundMapper.queryColumnalData(map);
        resultMap.put("columnarVo",fundColumnarVos);

        return resultMap;
    }
}
