package com.deloitte.services.fssc.system.bank.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.trade.param.TradeInformationQueryForm;
import com.deloitte.services.fssc.system.bank.entity.TradeInformation;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-05-13
 * @Description : TradeInformation服务类接口
 * @Modified :
 */
public interface ITradeInformationService extends IService<TradeInformation> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<TradeInformation>
     */
    IPage<TradeInformation> selectPage(TradeInformationQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<TradeInformation>
     */
    List<TradeInformation> selectList(TradeInformationQueryForm queryForm);

    /**
     * 根据交易流水号查询
     * @param serialNum
     * @return
     */
    Integer countByTradeSerialNum(String serialNum);
}
