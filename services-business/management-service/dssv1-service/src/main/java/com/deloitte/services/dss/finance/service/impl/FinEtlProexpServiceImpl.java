package com.deloitte.services.dss.finance.service.impl;

import com.deloitte.services.dss.finance.entity.FinEtlPretreatment;
import com.deloitte.services.dss.finance.mapper.FinEtlProexpMapper;
import com.deloitte.services.dss.finance.service.IFinEtlProexpService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
import java.util.Map;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-27
 * @Description :  DssFinEtlProexp服务实现类
 * @Modified :
 */
@Service
@Transactional
public class FinEtlProexpServiceImpl implements IFinEtlProexpService {


    @Autowired
    private FinEtlProexpMapper finEtlProexpMapper;


    @Override
    public List<FinEtlPretreatment> queryInfoForProExp(Map map) {
        List<FinEtlPretreatment> finEtlPretreatments = finEtlProexpMapper.queryInfoForProExp(map);
        return finEtlPretreatments;
    }
}

