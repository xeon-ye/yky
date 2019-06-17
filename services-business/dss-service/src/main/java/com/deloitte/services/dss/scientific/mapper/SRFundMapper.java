package com.deloitte.services.dss.scientific.mapper;

import com.deloitte.platform.api.dss.scientific.vo.FundBaseVo;
import com.deloitte.platform.api.dss.scientific.vo.FundColumnarVo;
import com.deloitte.platform.api.dss.scientific.vo.SRFundVo;

import java.util.List;
import java.util.Map;

public interface SRFundMapper {

    /**
     *  国家级&省部级&横向&其他 年度经费
     * @param
     * @return
     */
    List<SRFundVo> queryOutlineFund(Integer year);

    /**
     *  院校级创新工程 年度经费
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




    /**************************************修改版 v20190305****************************************************************************/
    /**
     * 查询本年度总经费 和到位经费
     * @param
     * @return
     */
    FundBaseVo queryTotalMoney(Map map);


    /**
     * 查询本年创新工程支出总计
     * @param
     * @return
     */
    FundBaseVo queryPayMoney(Map map);



    /**
     * 查询年度柱状图数据
     * @param
     * @return
     */
    List<FundColumnarVo> queryColumnalData(Map map);

    /**************************************修改版 v20190305****************************************************************************/

}
