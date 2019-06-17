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
    public Map queryData(Map map) {
        Map resultMap = new HashMap();
        //新货项目经费
        FundBaseVo fundBaseVo = srFundMapper.queryProMoney(map);
        //到位经费
        FundBaseVo reciveFundVo = srFundMapper.queryReciveMoney(map);
        if(reciveFundVo!=null) {
            fundBaseVo.setReciveAmount(reciveFundVo.getReciveAmount());
        }
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
