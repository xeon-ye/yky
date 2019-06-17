package com.deloitte.services.dss.finance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.dss.finance.entity.FinEtlPretreatment;
import com.deloitte.services.dss.finance.mapper.FinEtlLiabilitiesMapper;
import com.deloitte.services.dss.finance.service.IFinEtlLiabilitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * @Author : chitose
 * @Date : Create in 2019-04-12
 * @Description :  FinEtlLiabilities服务实现类
 * @Modified :
 */
@Service
@Transactional
public class FinEtlLiabilitiesServiceImpl extends ServiceImpl implements IFinEtlLiabilitiesService {


    @Autowired
    private FinEtlLiabilitiesMapper finEtlLiabilitiesMapper;

    @Override
    public List<FinEtlPretreatment> queryLiabilities(Map map) {
        List<FinEtlPretreatment> financeLiabilitiesVos = finEtlLiabilitiesMapper.queryLiabilities(map);
        return financeLiabilitiesVos;
    }

    @Override
    public List<FinEtlPretreatment> queryLiaAndDep(Map map) {
        List<FinEtlPretreatment> finEtlPretreatments = finEtlLiabilitiesMapper.queryLiaAndDep(map);
        return finEtlPretreatments;
    }
}

