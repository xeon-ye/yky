package com.deloitte.services.dss.scientific.controller;


import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.dss.scientific.service.IFundViewService;
import com.deloitte.services.dss.scientific.service.IResultService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @Author : hmz
 * @Date : Create in 2019-03-01
 */

@Api(tags = "科研总览科研成果数据统计接口")
@Slf4j
@RestController
@RequestMapping("/scientific/Result")
public class ResultController {
    @Autowired
    private IResultService IResultService;
    @Autowired
    private IFundViewService iFundViewService;
    @ApiOperation(value = "分类总数量", notes = "分类总数量")
    @PostMapping(value = "/queryTotal")
    public Result queryTotal() {

        return Result.success(IResultService.queryResult());
    }
    @ApiOperation(value = "科研成果柱状图数据", notes = "科研成果柱状图数据")
    @PostMapping(value = "/quertColumnTotal")
    public Result quertColumnTotal(){
        return  Result.success(IResultService.queryResultColumn());
    }

    @ApiOperation(value ="依托单位", notes = "依托单位")
    @PostMapping(value = "/queryDept")
    public Result queryDept(){
        return  Result.success(iFundViewService.queryDept());
    }


}
