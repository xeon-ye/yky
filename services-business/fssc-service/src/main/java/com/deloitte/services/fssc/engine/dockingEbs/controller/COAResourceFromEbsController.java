package com.deloitte.services.fssc.engine.dockingEbs.controller;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.engine.automatic.entity.AvAccountElement;
import com.deloitte.services.fssc.engine.automatic.entity.AvAccountElementRele;
import com.deloitte.services.fssc.engine.automatic.entity.AvBaseElement;
import com.deloitte.services.fssc.engine.automatic.entity.AvChartOfAccount;
import com.deloitte.services.fssc.engine.automatic.mapper.AvAccountElementReleMapper;
import com.deloitte.services.fssc.engine.automatic.service.IAvAccountElementReleService;
import com.deloitte.services.fssc.engine.automatic.service.IAvAccountElementService;
import com.deloitte.services.fssc.engine.automatic.service.IAvChartOfAccountService;
import com.deloitte.services.fssc.engine.dockingEbs.entity.AvBaseFromEbs;
import com.deloitte.services.fssc.engine.dockingEbs.entity.AvCOAFromEbs;
import com.deloitte.services.fssc.engine.dockingEbs.returnEntity.AvCOAReturnEbs;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : chenx
 * @Date : Create in 2019-04-10
 * @Description :   EBS推送COA数据到财务系统
 * @Modified :
 */
@Api(tags = "EBS端提供COA段到财务系统中")
@Slf4j
@RestController
@RequestMapping("/fssc/dockingEbs/coa")
public class COAResourceFromEbsController {

    @Autowired
    private IAvAccountElementService avAccountElementService;
    @Autowired
    private IAvAccountElementReleService avAccountElementReleService;
    @Autowired
    private IAvChartOfAccountService avChartOfAccountService;
    @Autowired
    private AvAccountElementReleMapper avAccountElementReleMapper;

    @ApiOperation(value = "获取得到EBS基础的数据包含COA段", notes = "EBS获取得到")
    @PostMapping(value = "/getListFromEbs")
    public Result searchBaseElement(@Valid @RequestBody @ApiParam(name="jsonData",value="获取得到EBS数据",required=true) JSONArray jsonData) {
        //  List<AvBaseFromEbs>  list= JSON.parseArray(jsonData, AvBaseFromEbs.class);
        List<AvCOAFromEbs> list = (ArrayList<AvCOAFromEbs>) JSONArray.parseArray(jsonData.toString(),AvCOAFromEbs.class);
        List<AvAccountElement> aBEList =new BeanUtils<AvAccountElement>().copyObjs(list,AvAccountElement.class);
        List<AvCOAReturnEbs> resultList = new ArrayList<AvCOAReturnEbs>();
        List<AvChartOfAccount> ledgerList =avChartOfAccountService.list();
        List<AvAccountElementRele> releList =avAccountElementReleService.list();
        Map<Long,Long> ledgerMap = new HashMap<Long,Long>();
        for(AvChartOfAccount c:ledgerList){
            ledgerMap.put(c.getChartOfAccountsId(),c.getLedgerId());
        }
        for(AvAccountElement p : aBEList){
            try{
                if(p.getSegmentCode()==null||p.getSegmentDesc()==null){
                    continue;
                }
                p.setType("COA");
                p.setStatus("Y");
                QueryWrapper<AvAccountElement> queryWrapper = new QueryWrapper <AvAccountElement>();
                queryWrapper.eq(AvAccountElement.CHART_OF_ACCOUNTS_ID,p.getChartOfAccountsId());
                queryWrapper.eq(AvAccountElement.SEGMENT_CODE,p.getSegmentCode());
                List<AvAccountElement>  oldlist  = avAccountElementService.list(queryWrapper);

                if(oldlist.size()>0){
                    p.setId(oldlist.get(0).getId());
                    avAccountElementService.updateById(p);
                }else {
                    p.setCreateDate(LocalDateTime.now());
                    avAccountElementService.save(p);
                }
                //把账薄信息和COA段进行关联
                if(ledgerMap.containsKey(p.getChartOfAccountsId())){
                    QueryWrapper<AvAccountElementRele> queryWrapperRele = new QueryWrapper <AvAccountElementRele>();
                    Long ledgerId = ledgerMap.get(p.getChartOfAccountsId());
                    Integer count =  avAccountElementReleMapper.getRele(p.getChartOfAccountsId(),p.getSegmentCode());
                    if(count<=0){
                        AvAccountElementRele rele = new AvAccountElementRele();
                        rele.setLedgerId(ledgerId);
                        rele.setElementId(p.getId());
                        rele.setCreateDate(LocalDateTime.now());
                        avAccountElementReleService.save(rele);
                    }

                }


            }catch (Exception e){
                log.error("EBS传输的数据错误"+e);
                AvCOAReturnEbs entity = new AvCOAReturnEbs();
                entity.setChartOfAccountsId(p.getChartOfAccountsId());
                entity.setSegmentCode(p.getSegmentCode());
                entity.setErrorMessage("财务系统保存失败");
                entity.setImportFlag("E");
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
