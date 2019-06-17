package com.deloitte.services.dss.scientific.controller;

import com.deloitte.services.dss.scientific.service.IFundViewService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.deloitte.platform.common.core.entity.vo.Result;


@Api(tags = "科研总体地区分布-依托单位（创新工程）")
@Slf4j
@RestController
@RequestMapping("/scientific/FundView")
public class FundViewController {
    @Autowired
    private IFundViewService iFundViewService;
    @ApiOperation(value = "历年到位经费", notes = "历年到位经费")
    @PostMapping(value = "/queryFundTotal/{dependDeptId}")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ApiImplicitParam(paramType = "path",name = "dependDeptId",required = true)
    public Result queryFundTotal(@PathVariable Integer dependDeptId){
        return Result.success(iFundViewService.queryFund(dependDeptId));
    }

    @ApiOperation(value = "明细经费",notes = "明细经费")
    @PostMapping(value = "/queryFundDetail/{dependDeptId}")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ApiImplicitParam(paramType = "path",name = "dependDeptId",required = true)
    public Result queryFundDetail(@PathVariable Integer dependDeptId){
        return Result.success(iFundViewService.queryFundDetail(dependDeptId));
    }

    @ApiOperation(value = "科研成果",notes = "科研成果")
    @PostMapping(value = "/queryResult/{dependDeptId}")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ApiImplicitParam(paramType = "path",name = "dependDeptId",required = true)
    public Result queryResult(@PathVariable Integer dependDeptId){
        return Result.success(iFundViewService.queryResult1(dependDeptId));
    }

    @ApiOperation(value = "明细项目类型",notes = "明细项目类型")
    @PostMapping(value = "/queryProjectDetail/{dependDeptId}")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ApiImplicitParam(paramType = "path",name = "dependDeptId",required = true)
    public  Result queryProjectDetail(@PathVariable Integer dependDeptId){
        return Result.success(iFundViewService.queryProjectDetail(dependDeptId));
    }
}
