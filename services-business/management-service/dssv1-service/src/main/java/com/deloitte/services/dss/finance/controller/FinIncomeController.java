package com.deloitte.services.dss.finance.controller;

import com.deloitte.platform.api.dss.finance.vo.AcceptVo;
import com.deloitte.platform.api.dss.finance.vo.IncomeBudgetVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.dss.finance.service.IFinIncomeService;
import com.deloitte.services.dss.util.TimeUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.*;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-09
 * @Description :   Income控制器实现类
 * @Modified :
 */
@Api(tags = "2.财务 收入操作接口")
@Slf4j
@RestController
@RequestMapping("/dss/finance/income")
public class FinIncomeController {

    @Autowired
    public IFinIncomeService iFinIncomeService;

    @ApiOperation(value = "当年总收入数据 及 同比", notes = "当年总收入数据 及 同比")
    @PostMapping("/selectTotalIncome")
    public Result selectTotalIncome(@Valid @RequestBody AcceptVo myData){
        Map map = new HashMap<String,String>();
        map.put("comCode",myData.getComCode());
        map.put("month",TimeUtil.getTime());
        map.put("indexCode","FIND0007");
        List<IncomeBudgetVo> incomeBudgetVos = iFinIncomeService.selectTotalIncome(map);
        Result result = Result.success(incomeBudgetVos);
        return result;
    }

    @ApiOperation(value = "各部门总收入", notes = "各部门总收入")
    @PostMapping("/selectComIncome")
    public Result selectComIncome(@Valid @RequestBody AcceptVo myData){
        Map map = new HashMap();
        map.put("comCode",myData.getComCode());
        map.put("month",TimeUtil.getTime());
        map.put("indexCode","FIND0007");
        List<IncomeBudgetVo> list = iFinIncomeService.selectComIncome(map);
        Result result = Result.success(list);
        return result;
    }

    @ApiOperation(value = "截止当月总收入 数据", notes = "截止当月总收入 数据")
    @PostMapping("/selectIncomeMonth")
    public Result selectIncomeMonth(@Valid @RequestBody AcceptVo myData){
        Map<String,String> map = new HashMap<String,String>();
        if(myData.getPeriodCode() == null || "".equals(myData.getPeriodCode())){
            map.put("periodCode",TimeUtil.getTime());
        }else {
            map.put("periodCode",myData.getPeriodCode());
        }
        List<IncomeBudgetVo> incomeBudgetVos = iFinIncomeService.selectIncomeMonth(map);
        Result result = Result.success(incomeBudgetVos);
        return result;
    }

    @ApiOperation(value = "各部门 截止当月总收入同比 数据", notes = "各部门 截止当月总收入同比 数据")
    @PostMapping("/selectComIncomeMonth")
    public Result selectComIncomeMonth(@Valid @RequestBody AcceptVo myData){
        Map map = new HashMap();
        map.put("month",TimeUtil.getTime());
        map.put("comCode",myData.getComCode());
        map.put("indexCode","FIND0007");
        List<IncomeBudgetVo> list = iFinIncomeService.selectComIncomeMonth(map);
        Result result = Result.success(list);
        return result;
    }

}



