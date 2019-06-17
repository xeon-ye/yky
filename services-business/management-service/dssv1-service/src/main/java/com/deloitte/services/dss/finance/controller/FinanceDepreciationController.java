package com.deloitte.services.dss.finance.controller;

import com.deloitte.platform.api.dss.finance.vo.FinanceDepreciationVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.dss.finance.service.IFinanceDepreciationService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;



/**
 * @Author : chitose
 * @Date : Create in 2019-04-12
 * @Description :   FinanceDepreciation控制器实现类
 * @Modified :
 */
@Api(tags = "财务折旧率操作接口")
@Slf4j
@RestController
public class FinanceDepreciationController{

    @Autowired
    public IFinanceDepreciationService financeDepreciationService;

    @ApiOperation(value = "部门累计折旧率数据" ,notes = "部门累计折旧率数据")
    @PostMapping("/queryDepreciation")
    public Result queryDepreciation ( FinanceDepreciationVo financeDepreciationVo){
        List<FinanceDepreciationVo> financeDepreciationVos = financeDepreciationService.queryDepreciation(financeDepreciationVo);
        return Result.success(financeDepreciationVos);
    }

    @ApiOperation(value = "整体折旧率数据" ,notes = "整体折旧率数据")
    @PostMapping("/queryAvgDepreciation")
    public Result queryAvgDepreciation ( FinanceDepreciationVo financeDepreciationVo){
        Double aDouble = financeDepreciationService.queryAvgDepreciation(financeDepreciationVo);
        return Result.success(aDouble);
    }

    @ApiOperation(value = "折旧率往年同比" ,notes = "折旧率往年同比")
    @PostMapping("/queryDepreciationRate")
    public Result queryDepreciationRate ( FinanceDepreciationVo financeDepreciationVo){
        Double aDouble = financeDepreciationService.queryRate(financeDepreciationVo);
        return Result.success(aDouble);
    }

}



