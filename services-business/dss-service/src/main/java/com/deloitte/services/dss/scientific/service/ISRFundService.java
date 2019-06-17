package com.deloitte.services.dss.scientific.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.dss.scientific.vo.FundBaseVo;
import com.deloitte.platform.api.dss.scientific.vo.FundColumnarVo;
import com.deloitte.platform.api.dss.scientific.vo.SRFundVo;

import java.util.List;
import java.util.Map;

public interface ISRFundService extends IService {

    /**
     *  国家级&省部级&横向&其他 年度经费情况
     * @param
     * @return
     */
    List<SRFundVo> queryOutlineFund(Integer year);

    /**
     *  院校级创新工程年度经费情况
     * @param
     * @return
     */
    List<SRFundVo> queryInnFund(Integer year);


    /**
     * 院校级基科费 年度经费
     * @param
     * @return
     */
    List<SRFundVo> queryBaseFund(Integer year);


    /**
     * 年度经费总览
     * @param
     * @return
     */
    Map yearFundView(Integer year);


    /**
     * 查询科研经费数据
     * @param map
     * @return
     */
    Map  queryData (Map map);

}
