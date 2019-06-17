package com.deloitte.services.dss.finance.controller;

import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.dss.finance.entity.FinEtlPretreatment;
import com.deloitte.services.dss.finance.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Hashtable;
import java.util.Map;
@Api(tags = "调试 操作接口")
@Slf4j
@RestController
@RequestMapping("/dss/finance")
public class TestController {

    @Autowired
    private IFinEtlPretreatmentService iFinEtlPretreatmentService;

    @Autowired
    private IFinEtlFactService iFinEtlFactService;

    @Autowired
    private IFinEtlProjectFactService iFinEtlProjectFactService;

    @Autowired
    private IFinEtlProjectPretService iFinEtlProjectPretService;

    @ApiOperation(value = "收入TEST",notes = "收入TEST")
    @PostMapping("/testmethod1")
    public Result testmethod1(FinEtlPretreatment finEtlPretreatment){
        boolean result = iFinEtlPretreatmentService.insertSrcToProIncome(finEtlPretreatment);
        iFinEtlFactService.insertProToFactIncome(finEtlPretreatment);
        return Result.success(result);
    }

    @ApiOperation(value = "收入预算TEST",notes = "收入预算TEST")
    @PostMapping("/testmethod2")
    public Result testmethod2(String period){
        Map map = new Hashtable();
        map.put("period",period);
        boolean result = iFinEtlPretreatmentService.insertScrToProBud(map);
        FinEtlPretreatment finEtlPretreatment = new FinEtlPretreatment();
        finEtlPretreatment.setPeriodCode(period);
        finEtlPretreatment.setIndexCode("FIND0001");
        iFinEtlFactService.insertProToFactIncomeBud(finEtlPretreatment);
        return Result.success(result);
    }

    @ApiOperation(value = "收入预算执行率TEST",notes = "收入预算执行率TEST")
    @PostMapping("/testmethod3")
    public Result testmethod3(String period){
        Map map = new Hashtable();
        map.put("period",period);
        boolean result = iFinEtlPretreatmentService.insertSrcToProExe(map);
        FinEtlPretreatment finEtlPretreatment = new FinEtlPretreatment();
        finEtlPretreatment.setPeriodCode(period);
        finEtlPretreatment.setIndexCode("FINC0001");
        iFinEtlFactService.insertProToFactExe(finEtlPretreatment);
        return Result.success(result);
    }

    @ApiOperation(value = "支出预算TEST",notes = "支出预算TEST")
    @PostMapping("/testmethod4")
    public Result testmethod4(String period){
        Map map = new Hashtable();
        map.put("period",period);
        boolean result = iFinEtlPretreatmentService.insertSrcToProExpBud(map);
        FinEtlPretreatment finEtlPretreatment = new FinEtlPretreatment();
        finEtlPretreatment.setPeriodCode(period);
        iFinEtlFactService.insertProToFactExpBud(finEtlPretreatment);
        return Result.success(result);
    }


    @ApiOperation(value = "支出TEST",notes = "支出TEST")
    @PostMapping("/testmethod5")
    public Result testmethod5(FinEtlPretreatment finEtlPretreatment){
        boolean result = iFinEtlPretreatmentService.insertSrcToProExp(finEtlPretreatment);
        iFinEtlFactService.insertProToFactExp(finEtlPretreatment);
        return Result.success(result);
    }

    @ApiOperation(value = "支出预算执行率TEST",notes = "支出预算执行率TEST")
    @PostMapping("/testmethod6")
    public Result testmethod6(String period){
        Map map = new Hashtable();
        map.put("period",period);
        boolean result = iFinEtlPretreatmentService.insertSrcToProExeExp(map);
        FinEtlPretreatment finEtlPretreatment = new FinEtlPretreatment();
        finEtlPretreatment.setPeriodCode(period);
        //finEtlPretreatment.setIndexCode("FINC0022");
        iFinEtlFactService.insertProToFactExpExe(finEtlPretreatment);
        return Result.success(result);
    }

    @ApiOperation(value = "收入同比TEST",notes = "收入同比TEST")
    @PostMapping("/testmethod7")
    public Result testmethod7(FinEtlPretreatment finEtlPretreatment){
        boolean result = iFinEtlPretreatmentService.insertSrcToProRate(finEtlPretreatment);
        finEtlPretreatment.setPeriodCode(finEtlPretreatment.getPeriodCode());
        finEtlPretreatment.setIndexCode("FINC0007");
        iFinEtlFactService.insertProToFactIncomeRate(finEtlPretreatment);
        return Result.success(result);
    }

    @ApiOperation(value = "收入计划增长TEST",notes = "收入计划增长TEST")
    @PostMapping("/testmethod8")
    public Result testmethod8(FinEtlPretreatment finEtlPretreatment){
        boolean result = iFinEtlPretreatmentService.insertSrcToProGrowth(finEtlPretreatment);
        finEtlPretreatment.setPeriodCode(finEtlPretreatment.getPeriodCode());
        finEtlPretreatment.setIndexCode("FINC0013");
        iFinEtlFactService.insertProToFactGrowth(finEtlPretreatment);
        return Result.success(result);
    }

    @ApiOperation(value = "支出同比TEST",notes = "支出同比TEST")
    @PostMapping("/testmethod9")
    public Result testmethod9(FinEtlPretreatment finEtlPretreatment){
        boolean result = iFinEtlPretreatmentService.insertSrcToProExpRate(finEtlPretreatment);
        finEtlPretreatment.setPeriodCode(finEtlPretreatment.getPeriodCode());
        finEtlPretreatment.setIndexCode("FINC0045");
        iFinEtlFactService.insertProToFactExpRate(finEtlPretreatment);
        return Result.success(result);
    }

    @ApiOperation(value = "支出计划增长率TEST",notes = "支出计划增长率TEST")
    @PostMapping("/testmethod10")
    public Result testmethod10(FinEtlPretreatment finEtlPretreatment){
        boolean result = iFinEtlPretreatmentService.insertSrcToProExpGrowth(finEtlPretreatment);
        finEtlPretreatment.setPeriodCode(finEtlPretreatment.getPeriodCode());
        finEtlPretreatment.setIndexCode("FINC0045");
        iFinEtlFactService.insertProToFactExpGrowth(finEtlPretreatment);
        return Result.success(result);
    }

    @ApiOperation(value = "资产TEST",notes = "资产TEST")
    @PostMapping("/testmethod11")
    public Result testmethod11(String period){
        Map map = new Hashtable();
        map.put("period",period);
        boolean result = iFinEtlPretreatmentService.insertSrcToProLiabilities(map);
        FinEtlPretreatment finEtlPretreatment = new FinEtlPretreatment();
        finEtlPretreatment.setPeriodCode(period);
        finEtlPretreatment.setIndexCode("FINC0045");
        iFinEtlFactService.insertProToFactLiab(finEtlPretreatment);
        return Result.success(result);
    }

    @ApiOperation(value = "项目支出预算TEST",notes = "项目支出预算TEST")
    @PostMapping("/testmethod12")
    public Result testmethod12(String period){
        Map map = new Hashtable();
        map.put("period",period);
        boolean result = iFinEtlPretreatmentService.insertSrcToProProExp(map);
        FinEtlPretreatment finEtlPretreatment = new FinEtlPretreatment();
        finEtlPretreatment.setPeriodCode(period);
        finEtlPretreatment.setIndexCode("FINC0045");
        iFinEtlFactService.insertProToFactProExpBud(finEtlPretreatment);
        return Result.success(result);
    }

    @ApiOperation(value = "国库系统（实际项目支出）TEST",notes = "国库系统（实际项目支出）TEST")
    @PostMapping("/testmethod13")
    public Result testmethod13(String period){
        Map map = new Hashtable();
        map.put("period",period);
        boolean result = iFinEtlPretreatmentService.insertSrcToProNational(map);
        FinEtlPretreatment finEtlPretreatment = new FinEtlPretreatment();
        finEtlPretreatment.setPeriodCode(period);
        finEtlPretreatment.setIndexCode("FINC0045");
        iFinEtlFactService.insertProToFactProExp(finEtlPretreatment);
        return Result.success(result);
    }

    @ApiOperation(value = "项目支出预算执行率TEST",notes = "项目支出预算执行率TEST")
    @PostMapping("/testmethod14")
    public Result testmethod14(String period){
        Map map = new Hashtable();
        map.put("period",period);
        iFinEtlPretreatmentService.insertSrcToProNationalExeN(map);
        iFinEtlPretreatmentService.insertSrcToProNationalExeD(map);
        FinEtlPretreatment finEtlPretreatment = new FinEtlPretreatment();
        finEtlPretreatment.setPeriodCode(period);
        iFinEtlFactService.insertProToFactProExpBudExe(finEtlPretreatment);
        return Result.success();
    }

    /*@ApiOperation(value = "项目支出预算执行率（分母）TEST",notes = "项目支出预算执行率（分母）TEST")
    @PostMapping("/testmethod15")
    public Result testmethod15(String period){
        Map map = new Hashtable();
        map.put("period",period);
        boolean result = iFinEtlPretreatmentService.insertSrcToProNationalExeD(map);
        FinEtlPretreatment finEtlPretreatment = new FinEtlPretreatment();
        finEtlPretreatment.setPeriodCode(period);
        finEtlPretreatment.setIndexCode("FINC0045");
        iFinEtlFactService.insertProToFactProExpBudExeD(finEtlPretreatment);
        return Result.success(result);
    }*/

    @ApiOperation(value = "整体项目预算绩效TEST",notes = "整体项目预算绩效TEST")
    @PostMapping("/testmethod16")
    public Result testmethod16(FinEtlPretreatment finEtlPretreatment){
        boolean achive = iFinEtlPretreatmentService.insertSrcToProAchive(finEtlPretreatment);
        iFinEtlFactService.insertProToFactAchive(finEtlPretreatment);
        return Result.success(achive);
    }

    @ApiOperation(value = "各类型项目预算绩效TEST",notes = "各类型项目预算绩效TEST")
    @PostMapping("/testmethod17")
    public Result testmethod17(String period){
        Map map = new Hashtable();
        map.put("period",period);
        boolean result = iFinEtlPretreatmentService.insertSrcToProComAchive(map);
        FinEtlPretreatment finEtlPretreatment = new FinEtlPretreatment();
        finEtlPretreatment.setPeriodCode(period);
        iFinEtlFactService.insertProToFactComAchive(finEtlPretreatment);
        return Result.success(result);
    }

    @ApiOperation(value = "平均项目预算绩效同比增长TEST",notes = "平均项目预算绩效同比增长TEST")
    @PostMapping("/testmethod18")
    public Result testmethod18(FinEtlPretreatment finEtlPretreatment){
        boolean result = iFinEtlPretreatmentService.insertSrcToProAvgAchive(finEtlPretreatment);
        iFinEtlFactService.insertProToFactAvgAchive(finEtlPretreatment);
        return Result.success(result);
    }

    @ApiOperation(value = "平均科研项目预算绩效TEST",notes = "平均科研项目预算绩效TEST")
    @PostMapping("/testmethod19")
    public Result testmethod19(FinEtlPretreatment finEtlPretreatment){
        boolean result = iFinEtlPretreatmentService.insertSrcToProAvgSciAchive(finEtlPretreatment);
        iFinEtlFactService.insertProToFactAvgSciAchive(finEtlPretreatment);
        return Result.success(result);
    }


    @ApiOperation(value = "三年项目库TEST",notes = "三年项目库TEST")
    @PostMapping("/testmethod20")
    public Result testmethod20(){
        iFinEtlProjectPretService.insertIntoPret();
        iFinEtlProjectFactService.insertIntoFact();
        return Result.success();
    }

    @ApiOperation(value = "资产负债率与累计折旧率", notes = "资产负债率与累计折旧率")
    @PostMapping("/testmethod21")
    public Result testmethod21(String period){
        Map map = new Hashtable();
        map.put("period",period);
        iFinEtlPretreatmentService.insertSrcToLiaAndDep(map);
        FinEtlPretreatment finEtlPretreatment = new FinEtlPretreatment();
        finEtlPretreatment.setPeriodCode(period);
        iFinEtlFactService.insertProToFactLiaAndDep(finEtlPretreatment);
        return Result.success();
    }
}
