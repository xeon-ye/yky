package com.deloitte.services.dss.scientific.controller;


import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.dss.scientific.service.IPersonnelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "科研总览科研人才数据统计接口")
@Slf4j
@RestController
@RequestMapping("/ecientific/personnel")
public class PersonnelController {

    @Autowired
    private IPersonnelService personnelService;

    @ApiOperation(value = "各职级人数占比", notes = "各职级人数占比")
    @PostMapping(value = "/queryAgeProp")
    public Result queryNumMap() {
        Map map = new HashMap();
        map.put("status",50);
        return Result.success(personnelService.queryAgeProp(map));
    }

    @ApiOperation(value = "科研人才柱状图数据", notes = "科研人才柱状图数据")
    @PostMapping(value = "/queryColumnarData")
    public Result queryColumnarData() {
        Map map = new HashMap();
        map.put("status",50);
        return Result.success(personnelService.queryColumnarData(map));
    }
}
