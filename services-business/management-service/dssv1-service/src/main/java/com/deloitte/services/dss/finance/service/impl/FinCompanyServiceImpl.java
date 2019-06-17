package com.deloitte.services.dss.finance.service.impl;

import com.deloitte.platform.api.dss.finance.vo.FinCompanyVo;
import com.deloitte.services.dss.finance.mapper.FinCompanyMapper;
import com.deloitte.services.dss.finance.service.IFinCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FinCompanyServiceImpl implements IFinCompanyService {

    @Autowired
    private FinCompanyMapper  finCompanyMapper;

    @Override
    public List<FinCompanyVo> selectCompany() {
        List<FinCompanyVo> finCompanyVos = finCompanyMapper.selectCompany();
        return finCompanyVos;
    }
}
