package com.deloitte.services.dss.scientific.controller;


import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.dss.scientific.service.IHarvestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "科研成果接口")
@Slf4j
@RestController
@RequestMapping("/ecientific/harvest")
public class HarvestController {


    @Autowired
    private IHarvestService harvestService;

    @ApiOperation(value = "年度折线数据", notes = "年度折线数据")
    @PostMapping(value = "/line")
    public Result queryBrokenLineData() {
        Map map = new HashMap();
        return Result.success(harvestService.queryBrokenLineData(map));
    }


    @ApiOperation(value = "各单位论文数量", notes = "各单位论文数量")
    @PostMapping(value = "/paper")
    public Result queryDeptPaper() {
        Map map = new HashMap();
        return Result.success(harvestService.queryDeptPaper(map));
    }


    @ApiOperation(value = "各单位成果及专利数量", notes = "各单位成果及专利数量")
    @PostMapping(value = "/patentResult")
    public Result queryDeptPatentResult() {
        Map map = new HashMap();
        return Result.success(harvestService.queryDeptPatentResult(map));
    }
}
