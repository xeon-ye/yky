package com.deloitte.services.dss.scientific.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.dss.scientific.vo.FundBaseVo;
import com.deloitte.platform.api.dss.scientific.vo.FundColumnarVo;
import com.deloitte.platform.api.dss.scientific.vo.SRFundVo;

import java.util.List;
import java.util.Map;

public interface ISRFundService extends IService {

    /**
     * 查询科研经费数据
     * @param map
     * @return
     */
    Map  queryData (Map map);

}
