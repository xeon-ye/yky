package com.deloitte.services.dss.scientific.controller;


import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.dss.scientific.service.IDeptFundService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "单位科研经费数据统计接口")
@Slf4j
@RestController
@RequestMapping("/ecientific/deptFund")
public class DeptFundController {


    @Autowired
    private IDeptFundService deptFundService;

    @ApiOperation(value = "单位科研经费", notes = "单位科研经费")
    @PostMapping(value = "/amountPay/{deptName}")
    @ApiImplicitParam(paramType = "path",name = "deptName",required = true)
    public Result queryDeptProAmountPay(@PathVariable String deptName) {
        Map map = new HashMap();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        Integer year = Integer.parseInt(format.format(date));
        map.put("year",year);
        map.put("deptName",deptName);
        return Result.success(deptFundService.queryDeptProAmountPay(map));
    }


    @ApiOperation(value = "单位科研经费明细执行率", notes = "单位科研经费明细执行率")
    @PostMapping(value = "/amountPay/{deptName}/{projectNum}")
    @ApiImplicitParam(paramType = "path",name = "deptName",required = true)
    public Result queryDeptProFundDetail(@PathVariable String deptName,@PathVariable String projectNum) {
        Map map = new HashMap();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        Integer year = Integer.parseInt(format.format(date));
        map.put("year",year);
        map.put("deptName",deptName);
        map.put("projectNum",projectNum);
        return Result.success(deptFundService.queryDeptProFundDetail(map));
    }

}
