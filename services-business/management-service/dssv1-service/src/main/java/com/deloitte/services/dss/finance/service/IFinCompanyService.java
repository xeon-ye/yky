package com.deloitte.services.dss.finance.service;

import com.deloitte.platform.api.dss.finance.vo.FinCompanyVo;

import java.util.List;

public interface IFinCompanyService {

    List<FinCompanyVo> selectCompany();
}
