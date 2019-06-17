package com.deloitte.services.fssc.engine.dockingEbs.controller;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.engine.automatic.entity.AvDailyRates;
import com.deloitte.services.fssc.engine.automatic.entity.AvLedgerUnitRelation;
import com.deloitte.services.fssc.engine.automatic.service.IAvDailyRatesService;
import com.deloitte.services.fssc.engine.automatic.service.IAvLedgerUnitRelationService;
import com.deloitte.services.fssc.engine.dockingEbs.entity.AvRatesFromEAbs;
import com.deloitte.services.fssc.engine.dockingEbs.entity.AvUnitLedgerRelationFromEbs;
import com.deloitte.services.fssc.engine.dockingEbs.returnEntity.AvRatesReturnEbs;
import com.deloitte.services.fssc.engine.dockingEbs.returnEntity.AvUnitLedgerRelationReturnEbs;
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
 * @Description :   EBS推送主体段值与账簿对应关系到财务系统
 * @Modified :
 */
@Api(tags = "EBS端提供主体段值与账簿对应关系到财务系统中")
@Slf4j
@RestController
@RequestMapping("/fssc/dockingEbs/unitRelationLedger")
public class UnitRelationLedgerFromEbsController {
    @Autowired
    private IAvLedgerUnitRelationService avLedgerUnitRelationService;

    @ApiOperation(value = "获取得到EBS基础的汇率信息数据", notes = "EBS获取得到")
    @PostMapping(value = "/getListFromEbs")
    public Result searchBaseElement(@Valid @RequestBody @ApiParam(name="jsonData",value="获取得到EBS数据",required=true) JSONArray jsonData) {
        //  List<AvBaseFromEbs>  list= JSON.parseArray(jsonData, AvBaseFromEbs.class);
        List<AvUnitLedgerRelationFromEbs> list = (ArrayList<AvUnitLedgerRelationFromEbs>) JSONArray.parseArray(jsonData.toString(),AvUnitLedgerRelationFromEbs.class);
        List<AvLedgerUnitRelation> aBEList =new BeanUtils<AvLedgerUnitRelation>().copyObjs(list,AvLedgerUnitRelation.class);
        List<AvUnitLedgerRelationReturnEbs> resultList = new ArrayList<AvUnitLedgerRelationReturnEbs>();
        for(AvLedgerUnitRelation p : aBEList){
            try{
                QueryWrapper<AvLedgerUnitRelation> queryWrapper = new QueryWrapper <AvLedgerUnitRelation>();
                queryWrapper.eq(AvLedgerUnitRelation.BALANCE_CODE,p.getBalanceCode());
                queryWrapper.eq(AvLedgerUnitRelation.LEDGER_ID,p.getLedgerId());
                List<AvLedgerUnitRelation>  oldlist  = avLedgerUnitRelationService.list(queryWrapper);
                if(oldlist.size()>0){
                    p.setId(oldlist.get(0).getId());
                    avLedgerUnitRelationService.updateById(p);
                }else {
                    p.setCreateDate(LocalDateTime.now());
                    avLedgerUnitRelationService.save(p);
                }
            }catch (Exception e){
                log.error("EBS传输的数据错误"+e);
                AvUnitLedgerRelationReturnEbs entity = new AvUnitLedgerRelationReturnEbs();
                entity.setErrorMessage("财务系统保存账薄和单位关联关系信息失败");
                entity.setImportFlag("E");
                entity.setBalanceCode(p.getBalanceCode());
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
