package com.deloitte.services.dss.finance.controller;

import com.deloitte.platform.api.dss.finance.vo.AcceptVo;
import com.deloitte.platform.api.dss.finance.vo.IncomeBudgetVo;
import com.deloitte.platform.api.dss.finance.vo.TabsVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.dss.finance.service.IFinCompanyService;
import com.deloitte.services.dss.finance.service.IFinTabsService;
import com.deloitte.services.dss.finance.service.IFinanceLiabilitiesService;
import com.deloitte.services.dss.util.TimeUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


/**
 * @Author : chitose
 * @Date : Create in 2019-04-12
 * @Description :   FinanceLiabilities控制器实现类
 * @Modified :
 */
@Api(tags = "8.财务 负债率与折旧率操作接口")
@Slf4j
@RestController
public class FinanceLiabilitiesController{

    @Autowired
    public IFinanceLiabilitiesService financeLiabilitiesService;
    @Autowired
    private IFinCompanyService finCompanyService;
    @Autowired
    private IFinTabsService finTabsService;

    @ApiOperation(value = "整体负债率、折旧率及同比" ,notes = "整体负债率、折旧率及同比")
    @PostMapping(value = "/queryLiabilities")
    public Result queryLiabilities(@Valid @RequestBody AcceptVo myData) {
        Map result = new HashMap();
        Map paramMap = new HashMap();
        String month = TimeUtil.getTime();
        String year = TimeUtil.getYear();
        Integer company = finCompanyService.selectCompany().size();
        List<String> comCode = myData.getComCode();

        paramMap.put("comCode",comCode);
        paramMap.put("month",month);
        List<IncomeBudgetVo> incomeBudgetVos = financeLiabilitiesService.queryLiabilities(paramMap);
        List<IncomeBudgetVo> incomeBudgetVoList = financeLiabilitiesService.selectLiabilities(paramMap);
        //整体负债率和折旧率
        result.put("incomeBudgetVoList",incomeBudgetVoList);
        //计算同比
//        String monthBefore = Integer.parseInt(year) - 1 +"-"+ month.split("-")[1];
        StringBuffer str =  new StringBuffer();
        String monthBefore = str.append(Integer.parseInt(year) - 1).append("-"+month.split("-")[1]).toString();
        paramMap.put("month",monthBefore);
        List<IncomeBudgetVo> before = financeLiabilitiesService.queryLiabilities(paramMap);
        if(null == before || (null != before && before.size() == 0)){
            for(int i = 0 ; i < incomeBudgetVos.size(); i++){
                incomeBudgetVos.get(i).setGrowthRate(incomeBudgetVos.get(i).getRate());
            }
        }else {
            for(int i = 0 ; i < incomeBudgetVos.size(); i++){
                incomeBudgetVos.get(i).setGrowthRate(
                        incomeBudgetVos.get(i).getRate()
                        .subtract(before.get(i).getRate())
                );
            }
        }
        result.put("incomeBudgetVos",incomeBudgetVos);

        /*筛选标签*/
        myData.setPeriodCode(TimeUtil.getTime());
        List<List<IncomeBudgetVo>> lists = financeLiabilitiesService.queryComLiabilities(myData);
        Integer size = lists.size();
        Map table = new Hashtable();
        table.put("indexCode","FINC0095");
        //机构负债率 比较 整体负债率
        Integer count = 0;
        for(int i = 0; i < lists.size(); i++){
            if(null != lists.get(i) && lists.get(i).size() > 0 && null!= lists.get(i).get(0)){
                BigDecimal n = lists.get(i).get(0).getYtdN();
                BigDecimal d = lists.get(i).get(0).getYtdD();
                BigDecimal rate = n.divide(d,3,BigDecimal.ROUND_HALF_UP);
                int i1 = rate.compareTo(incomeBudgetVos.get(0).getRate());
                if(i1 == -1){
                    count++;
                }
            }
        }
        if(count == 0){
            table.put("type",1);
            TabsVo tabsVo = finTabsService.selectTabs(table);
            tabsVo.setDes(size + tabsVo.getDes());
            result.put("tabsUp",tabsVo);
        }else{
            table.put("type",-1);
            TabsVo tabsVo = finTabsService.selectTabs(table);
            tabsVo.setDes(count + tabsVo.getDes());
            result.put("tabsUp",tabsVo);
        }

        //机构折旧率 比较 整体 折旧率
        count =0;
        table.put("indexCode","FINC0096");
        for(int i = 0; i < lists.size(); i++){
            if(null != lists.get(i) && lists.get(i).size() > 0 && null!= lists.get(i).get(1)){
                BigDecimal n = lists.get(i).get(1).getYtdN();
                BigDecimal d = lists.get(i).get(1).getYtdD();
                BigDecimal rate = n.divide(d,3,BigDecimal.ROUND_HALF_UP);
                int i1 = rate.compareTo(incomeBudgetVos.get(1).getRate());
                if(i1 == -1){
                    count++;
                }
            }
        }
        if(count == 0){
            table.put("type",1);
            TabsVo tabsVo = finTabsService.selectTabs(table);
            tabsVo.setDes(size + tabsVo.getDes());
            result.put("tabsDown",tabsVo);
        }else{
            table.put("type",-1);
            TabsVo tabsVo = finTabsService.selectTabs(table);
            tabsVo.setDes(count + tabsVo.getDes());
            result.put("tabsDown",tabsVo);
        }




        /*TabsVo tabsVo = new TabsVo();

        tabsVo.setDes("12家机构资产负债率高于整体资产负债率。");
        tabsVo.setColor("yellow");
        result.put("tabsUp",tabsVo);

        tabsVo = new TabsVo();
        tabsVo.setDes("6家机构累计折旧率高于整体累计折旧率。");
        tabsVo.setColor("yellow");
        result.put("tabsDown",tabsVo);*/
        return Result.success(result);
    }

    @ApiOperation(value = "各机构负债率、折旧率" ,notes = "各机构负债率、折旧率")
    @PostMapping(value = "/queryComLiabilities")
    public Result queryComLiabilities(@Valid @RequestBody AcceptVo myData) {
        myData.setPeriodCode(TimeUtil.getTime());
        List<List<IncomeBudgetVo>> lists = financeLiabilitiesService.queryComLiabilities(myData);
        Result result = Result.success(lists);
        return result;
    }
    @ApiOperation(value = "各机构负债率、折旧率平均值统计及同比" ,notes = "各机构负债率、折旧率平均值统计及同比")
    @PostMapping(value = "/queryComLiabilitiesRate")
    public Result queryComLiabilitiesRate(@Valid @RequestBody AcceptVo myData) {
        List<List<IncomeBudgetVo>> lists = financeLiabilitiesService.queryComLiabilitiesSum(myData);
        Result result = Result.success(lists);
        return result;
    }



}



