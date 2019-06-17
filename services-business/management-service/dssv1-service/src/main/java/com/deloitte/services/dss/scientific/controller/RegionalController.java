package com.deloitte.services.dss.scientific.controller;


import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.dss.scientific.service.IRegionalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "科研总览地区分布统计接口")
@Slf4j
@RestController
@RequestMapping("/ecientific/regional")
public class RegionalController {

    @Autowired
    private IRegionalService regionalService;

    @ApiOperation(value = "各类型项目本年科研概况", notes = "各类型项目本年科研概况")
    @PostMapping(value = "/queryCurYearData/{category}")
    @ApiImplicitParam(paramType = "path",name = "category",required = true)
    public Result queryNumMap(@PathVariable String category) {
        Map map = new HashMap();
        map.put("status",50);
        map.put("proviceCode","");
        map.put("category",getCategory(category));
        return Result.success(regionalService.queryCurYearData(map));
    }

    @ApiOperation(value = "根据城市筛选柱状图数据", notes = "根据城市筛选柱状图数据")
    @PostMapping(value = "/queryColumnarData/{provice}")
    @ApiImplicitParam(paramType = "path",name = "provice",required = true)
    public Result queryColumnarData(@PathVariable String provice) {
        Map map = new HashMap();
        map.put("status",50);
        map.put("proviceCode",provice);
        return Result.success(regionalService.queryColumnarData(map));
    }

    public String getCategory(String category){
        if("100101".equals(category)){
            return category;
        }else{
            String[] categoryList = category.split("_");
            String categoryString= "";
            for(int i = 0; i < categoryList.length; i++){
                if(i == 0){
                    categoryString = "('"+categoryList[i]+"'";
                }else{
                    categoryString = categoryString+",'"+categoryList[i]+"'";
                }
            }
            categoryString = categoryString+")";
            return categoryString;
        }
    }
}
