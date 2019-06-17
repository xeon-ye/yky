package com.deloitte.services.dss.finance.mapper;


import com.deloitte.platform.api.dss.finance.vo.FinCompanyVo;

import java.util.List;

public interface FinCompanyMapper {

    List<FinCompanyVo> selectCompany();
}
