package com.deloitte.services.dss.scientific.controller;


import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.dss.scientific.service.ISRFundService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Api(tags = "科研总览科研经费数据统计接口")
@Slf4j
@RestController
@RequestMapping("/ecientific/fund")
public class SRFundController {


    @Autowired
    private ISRFundService srFundService;

    /*@ApiOperation(value = "科研总览科研经费", notes = "科研总览科研经费")
    @PostMapping(value = "/querySRFund")
    public Result queryYearFund() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        Integer year = Integer.parseInt(format.format(date));
        return Result.success(srFundService.yearFundView(year));
    }*/


    @ApiOperation(value = "科研总览科研经费", notes = "科研总览科研经费")
    @PostMapping(value = "/data")
    public Result queryData() {
        return Result.success(srFundService.queryData(new HashMap()));
    }

}
