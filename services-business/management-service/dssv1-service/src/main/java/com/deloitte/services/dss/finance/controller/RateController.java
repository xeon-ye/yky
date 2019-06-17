package com.deloitte.services.dss.finance.controller;

import com.deloitte.platform.api.dss.finance.vo.AcceptVo;
import com.deloitte.platform.api.dss.finance.vo.IncomeBudgetVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.dss.finance.service.IFinRateService;
import com.deloitte.services.dss.util.TimeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@Api(tags = "6.同比增长率 操作接口")
@Slf4j
@RestController
public class RateController {

    @Autowired
    private IFinRateService iFinRateService;

    @ApiOperation(value = "总收入同比增长率数据", notes = "总收入同比增长率数据")
    @PostMapping("/selectRate")
    public Result selectRate(@Valid @RequestBody AcceptVo myData){
        Map map = new Hashtable();
        map.put("indexCode","FINC0007");
        map.put("month",TimeUtil.getTime());
        map.put("comCode",myData.getComCode());
        List<IncomeBudgetVo> rate = iFinRateService.selectRate(map);
        Result success = Result.success(rate);
        return success;
    }

    @ApiOperation(value = "各部门收入同比增长率数据", notes = "各部门收入同比增长率数据")
    @PostMapping("/selectComRate")
    public Result selectComRate(@Valid @RequestBody AcceptVo myData){
        Map paramMap = new HashMap();
        paramMap.put("month",TimeUtil.getTime());
        paramMap.put("indexCode","FINC0007");
        paramMap.put("comCode",myData.getComCode());
        List<IncomeBudgetVo> list = iFinRateService.selectComRate(paramMap);
        Result result = Result.success(list);
        return result;
    }

    @ApiOperation(value = "总支出同比增长率数据", notes = "总支出同比增长率数据")
    @PostMapping("/selectExpRate")
    public Result selectExpRate(@Valid @RequestBody AcceptVo myData){
        Map map = new Hashtable();
        map.put("month",TimeUtil.getTime());
        map.put("indexCode","FINC0045");
        map.put("comCode",myData.getComCode());
        List<IncomeBudgetVo> rate = iFinRateService.selectExpRate(map);
        Result success = Result.success(rate);
        return success;
    }

    @ApiOperation(value = "各部门支出同比增长率数据", notes = "各部门支出同比增长率数据")
    @PostMapping("/selectComExpRate")
    public Result selectComExpRate(@Valid @RequestBody AcceptVo myData){
        Map map = new Hashtable();
        map.put("month",TimeUtil.getTime());
        map.put("indexCode","FINC0045");
        map.put("comCode",myData.getComCode());
        List<IncomeBudgetVo> list = iFinRateService.selectComExpRate(map);
        Result result = Result.success(list);
        return result;
    }

    @ApiOperation(value = "截止当月总收入同比 数据", notes = "截止当月总收入同比 数据")
    @PostMapping("/selectIncomeMonth")
    public Result selectIncomeMonth(@Valid @RequestBody AcceptVo myData){
        Map<String,String> map = new HashMap<String,String>();
        if(myData.getPeriodCode() == null || "".equals(myData.getPeriodCode())){
            map.put("periodCode",TimeUtil.getTime());
        }else {
            map.put("periodCode",myData.getPeriodCode());
        }
        List<IncomeBudgetVo> incomeBudgetVos = iFinRateService.selectRateMonth(map);
        Result result = Result.success(incomeBudgetVos);
        return result;
    }

    /*@ApiOperation(value = "各部门 截止当月总收入 数据", notes = "各部门 截止当月总收入 数据")
    @PostMapping("/selectComIncomeMonth")
    public Result selectComIncomeMonth(@Valid @RequestBody AcceptVo myData){
        Map table = new Hashtable();
        table.put("periodCode",TimeUtil.getTime());
        table.put("comCode",myData.getComCode());
        List<List<IncomeBudgetVo>> list = iFinRateService.selectComRateMonth(table);
        Result result = Result.success(list);
        return result;
    }*/


}
