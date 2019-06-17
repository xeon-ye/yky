package com.deloitte.services.fssc.engine.dockingEbs.controller;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.engine.automatic.entity.AvChartOfAccount;
import com.deloitte.services.fssc.engine.automatic.entity.AvDailyRates;
import com.deloitte.services.fssc.engine.automatic.service.IAvDailyRatesService;
import com.deloitte.services.fssc.engine.dockingEbs.entity.AvLedgerFromEbs;
import com.deloitte.services.fssc.engine.dockingEbs.entity.AvRatesFromEAbs;
import com.deloitte.services.fssc.engine.dockingEbs.returnEntity.AvLedgerReturnEbs;
import com.deloitte.services.fssc.engine.dockingEbs.returnEntity.AvRatesReturnEbs;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : chenx
 * @Date : Create in 2019-04-10
 * @Description :   EBS推送数据到财务系统
 * @Modified :
 */
@Api(tags = "EBS端提供账薄信息到到财务系统中")
@Slf4j
@RestController
@RequestMapping("/fssc/dockingEbs/rates")
public class RatesResourceFromEbsController {
    @Autowired
    private IAvDailyRatesService avDailyRatesService;

    @ApiOperation(value = "获取得到EBS基础的汇率信息数据", notes = "EBS获取得到")
    @PostMapping(value = "/getListFromEbs")
    public Result searchBaseElement(@Valid @RequestBody @ApiParam(name="jsonData",value="获取得到EBS数据",required=true) JSONArray jsonData) {
        //  List<AvBaseFromEbs>  list= JSON.parseArray(jsonData, AvBaseFromEbs.class);
        List<AvRatesFromEAbs> list = (ArrayList<AvRatesFromEAbs>) JSONArray.parseArray(jsonData.toString(),AvRatesFromEAbs.class);
        List<AvDailyRates> aBEList =new BeanUtils<AvDailyRates>().copyObjs(list,AvDailyRates.class);
        List<AvRatesReturnEbs> resultList = new ArrayList<AvRatesReturnEbs>();
        for(AvDailyRates p : aBEList){
            try{
                QueryWrapper<AvDailyRates> queryWrapper = new QueryWrapper <AvDailyRates>();
                queryWrapper.eq(AvDailyRates.CONVERSION_DATE,p.getConversionDate());
                queryWrapper.eq(AvDailyRates.TO_CURRENCY,p.getToCurrency());
                List<AvDailyRates>  oldlist  = avDailyRatesService.list(queryWrapper);
                if(oldlist.size()>0){
                    p.setId(oldlist.get(0).getId());
                    avDailyRatesService.updateById(p);
                }else {
                    p.setCreateDate(LocalDateTime.now());
                    avDailyRatesService.save(p);
                }
            }catch (Exception e){
                log.error("EBS传输的数据错误"+e);
                AvRatesReturnEbs entity = new AvRatesReturnEbs();
                entity.setErrorMessage("财务系统保存账薄信息失败");
                entity.setImportFlag("E");
                entity.setConversionDate(p.getConversionDate());
                entity.setToCurrency(p.getToCurrency());
                entity.setFromCurrency(p.getFromCurrency());
                resultList.add(entity);
                continue;
            }
        }
        if(resultList.size()>0){
            return  Result.fail(resultList);
        }
        return  Result.success(resultList);
    }
}
