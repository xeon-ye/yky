package com.deloitte.services.dss.finance.controller;

import com.deloitte.platform.api.dss.finance.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.dss.finance.service.IFinanceExecutionService;
import com.deloitte.services.dss.util.TimeUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


/**
 * @Author : chitose
 * @Date : Create in 2019-04-09
 * @Description :   FinanceExecution控制器实现类
 * @Modified :
 */
@Api(tags = "4.财务 预算执行率操作接口")
@Slf4j
@RestController
public class FinanceExecutionController {

    @Autowired
    public IFinanceExecutionService iFinanceExecutionService;

    @ApiOperation(value = "当年总收入预算执行率数据", notes = "当年总收入预算执行率数据")
    @PostMapping("/queryExecution")
    public Result queryExecution(@Valid @RequestBody AcceptVo myData){
        Map map = new Hashtable();
        map.put("month", TimeUtil.getTime());
        List<FinanceExecutionVo> financeExecutionVos = iFinanceExecutionService.queryExecution(map);
        Result result = Result.success(financeExecutionVos);
        return result;
    }

    @ApiOperation(value = "机构当年总收入预算执行率数据", notes = "机构当年总收入预算执行率数据")
    @PostMapping("/queryComExecution")
    public Result queryComExecution(@Valid @RequestBody AcceptVo myData){
        Map map = new Hashtable();
        map.put("periodCode", myData.getPeriodCode());
        map.put("comCode",myData.getComCode());
        List<FinanceExecutionVo> lists = iFinanceExecutionService.queryComExecution(map);
        Result result = Result.success(lists);
        return result;
    }

    @ApiOperation(value = "当年总支出预算执行率数据", notes = "当年总支出预算执行率数据")
    @PostMapping("/queryExpExecution")
    public Result queryExpExecution(@Valid @RequestBody AcceptVo myData){
        Map map = new Hashtable();
        map.put("periodCode", myData.getPeriodCode());
        List<FinanceExecutionVo> financeExecutionVos = iFinanceExecutionService.queryExpExecution(map);
        Result result = Result.success(financeExecutionVos);
        return result;
    }

    @ApiOperation(value = "机构当年总支出预算执行率数据", notes = "机构当年总支出预算执行率数据")
    @PostMapping("/queryExpComExecution")
    public Result queryExpComExecution(@Valid @RequestBody AcceptVo myData){
        List<List<FinanceExecutionVo>> lists = iFinanceExecutionService.queryExpComExecution(myData);
        Result result = Result.success(lists);
        return result;
    }

    @ApiOperation(value = "机构收入预算整体数据", notes = "机构收入预算整体数据")
    @PostMapping("/selectComBudgetAll")
    public Result selectComBudgetAll(@Valid @RequestBody AcceptVo myData) {
        Map map = new HashMap();
        map.put("comCode",myData.getComCode());
        map.put("month",TimeUtil.getTime());
        List<IncomeBudgetVo> lists = iFinanceExecutionService.selectComBudgetAll(map);
        Result result = Result.success(lists);
        return result;
    }

    @ApiOperation(value = "机构支出预算整体数据", notes = "机构支出预算整体数据")
    @PostMapping("/selectComExpBudgetAll")
    public Result selectComExpBudgetAll(@Valid @RequestBody AcceptVo myData) {
        myData.setPeriodCode(TimeUtil.getTime());
        List<IncomeBudgetVo> list = iFinanceExecutionService.selectComExpBudgetAll(myData);
        Result result = Result.success(list);
        return result;
    }


}



