package com.deloitte.platform.api.fssc.trade.client;

import com.deloitte.platform.api.fssc.trade.param.TradeInformationQueryForm;
import com.deloitte.platform.api.fssc.trade.vo.TradeInformationForm;
import com.deloitte.platform.api.fssc.trade.vo.TradeInformationVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-05-13
 * @Description :  TradeInformation控制器接口
 * @Modified :
 */
public interface TradeInformationClient {

   String path="/fssc/trade-information";

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<TradeInformationVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   tradeInformationQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<TradeInformationVo>> list(@Valid @RequestBody TradeInformationQueryForm tradeInformationQueryForm);


    /**
     *  POST----复杂查询
     * @param  tradeInformationQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<TradeInformationVo>> search(@Valid @RequestBody TradeInformationQueryForm tradeInformationQueryForm);
}
