package com.deloitte.services.fssc.engine.dockingEbs.controller;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.engine.automatic.entity.AvBaseElement;
import com.deloitte.services.fssc.engine.automatic.entity.AvChartOfAccount;
import com.deloitte.services.fssc.engine.automatic.service.IAvChartOfAccountService;
import com.deloitte.services.fssc.engine.dockingEbs.entity.AvBaseFromEbs;
import com.deloitte.services.fssc.engine.dockingEbs.entity.AvLedgerFromEbs;
import com.deloitte.services.fssc.engine.dockingEbs.returnEntity.AvLedgerReturnEbs;
import com.deloitte.services.fssc.engine.dockingEbs.returnEntity.BaseResultReturnEbs;
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
 * @Date : Create in 2019-03-23
 * @Description :   EBS推送数据到财务系统
 * @Modified :
 */
@Api(tags = "EBS端提供账薄信息到到财务系统中")
@Slf4j
@RestController
@RequestMapping("/fssc/dockingEbs/ledger")
public class LedgerResourceFromEbsController {
    @Autowired
    private IAvChartOfAccountService avChartOfAccountService;

    @ApiOperation(value = "获取得到EBS基础的账薄信息数据", notes = "EBS获取得到")
    @PostMapping(value = "/getListFromEbs")
    public Result searchBaseElement(@Valid @RequestBody @ApiParam(name="jsonData",value="获取得到EBS数据",required=true) JSONArray jsonData) {
        //  List<AvBaseFromEbs>  list= JSON.parseArray(jsonData, AvBaseFromEbs.class);
        List<AvLedgerFromEbs> list = (ArrayList<AvLedgerFromEbs>) JSONArray.parseArray(jsonData.toString(),AvLedgerFromEbs.class);
        List<AvChartOfAccount> aBEList =new BeanUtils<AvChartOfAccount>().copyObjs(list,AvChartOfAccount.class);
        List<AvLedgerReturnEbs> resultList = new ArrayList<AvLedgerReturnEbs>();
        for(AvChartOfAccount p : aBEList){
            try{
                QueryWrapper<AvChartOfAccount> queryWrapper = new QueryWrapper <AvChartOfAccount>();
                queryWrapper.eq(AvChartOfAccount.LEDGER_ID,p.getLedgerId());
                List<AvChartOfAccount>  oldlist  = avChartOfAccountService.list(queryWrapper);
                p.setStatus("Y");
                if(oldlist.size()>0){
                    p.setId(oldlist.get(0).getId());
                    avChartOfAccountService.updateById(p);
                }else {
                    p.setCreateDate(LocalDateTime.now());
                    avChartOfAccountService.save(p);
                }
            }catch (Exception e){
                log.error("EBS传输的数据错误"+e);
                AvLedgerReturnEbs entity = new AvLedgerReturnEbs();
                entity.setErrorMessage("财务系统保存账薄信息失败");
                entity.setImportFlag("E");
                entity.setLedgerId(p.getLedgerId());
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
