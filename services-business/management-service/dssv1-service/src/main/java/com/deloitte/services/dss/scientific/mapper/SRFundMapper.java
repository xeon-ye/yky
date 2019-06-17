package com.deloitte.services.dss.scientific.mapper;

import com.deloitte.platform.api.dss.scientific.vo.FundBaseVo;
import com.deloitte.platform.api.dss.scientific.vo.FundColumnarVo;
import com.deloitte.platform.api.dss.scientific.vo.SRFundVo;

import java.util.List;
import java.util.Map;

public interface SRFundMapper {

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

    //查询本年度新获经费
    FundBaseVo queryProMoney(Map map);
    //查询本年到位经费
    FundBaseVo queryReciveMoney(Map map);



    /**
     * 查询年度柱状图数据
     * @param
     * @return
     */
    List<FundColumnarVo> queryColumnalData(Map map);

    /**************************************修改版 v20190305****************************************************************************/

}
