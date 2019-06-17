package com.deloitte.services.dss.finance.service.impl;

import com.deloitte.services.dss.finance.entity.FinEtlPretreatment;
import com.deloitte.services.dss.finance.mapper.FinEtlNationalMapper;
import com.deloitte.services.dss.finance.service.IFinEtlNationalService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * @Author : chitose
 * @Date : Create in 2019-04-27
 * @Description :  DssFinEtlNational服务实现类
 * @Modified :
 */
@Service
@Transactional
public class FinEtlNationalServiceImpl implements IFinEtlNationalService {


    @Autowired
    private FinEtlNationalMapper finEtlNationalMapper;

    @Override
    public List<FinEtlPretreatment> queryInfoForNational(Map map) {
        List<FinEtlPretreatment> finEtlPretreatments = finEtlNationalMapper.queryInfoForNational(map);
        return finEtlPretreatments;
    }

    @Override
    public List<FinEtlPretreatment> queryInfoForNationalExeN(Map map) {
        List<FinEtlPretreatment> finEtlPretreatments = finEtlNationalMapper.queryInfoForNationalExeN(map);
        return finEtlPretreatments;
    }

    @Override
    public List<FinEtlPretreatment> queryInfoForNationalExeD(Map map) {
        List<FinEtlPretreatment> finEtlPretreatments = finEtlNationalMapper.queryInfoForNationalExeD(map);
        return finEtlPretreatments;
    }
}

