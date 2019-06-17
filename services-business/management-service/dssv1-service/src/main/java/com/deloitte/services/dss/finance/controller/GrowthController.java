package com.deloitte.services.dss.finance.controller;

import com.deloitte.platform.api.dss.finance.vo.AcceptVo;
import com.deloitte.platform.api.dss.finance.vo.GrowthVo;
import com.deloitte.platform.api.dss.finance.vo.IncomeBudgetVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.dss.finance.service.IFinBudgetService;
import com.deloitte.services.dss.finance.service.IFinGrowthService;
import com.deloitte.services.dss.util.TimeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-09
 * @Description : Budget服务类接口
 * @Modified :
 */
@Api(tags = "5.财政 计划增长操作接口")
@Slf4j
@RestController
@RequestMapping("/dss/finance")
public class GrowthController {

    @Autowired
    private IFinGrowthService iFinGrowthService;

    @ApiOperation(value = "收入整体计划增长率数据",notes = "收入整体计划增长率数据")
    @PostMapping("/quesryGrowth")
    public Result quesryGrowth(@Valid @RequestBody AcceptVo myData){
        Map map = new Hashtable();
        map.put("periodCode",myData.getPeriodCode());
        List<GrowthVo> growthVos = iFinGrowthService.quesryGrowth(map);
        Result result = Result.success(growthVos);
        return result;
    }

    @ApiOperation(value = "机构收入计划增长率数据",notes = "机构收入计划增长率数据")
    @PostMapping("/quesryComGrowth")
    public Result quesryComGrowth(@Valid @RequestBody AcceptVo myData){
        Map map = new HashMap();
        map.put("comCode", myData.getComCode());
        map.put("month", TimeUtil.getTime());
        List<GrowthVo> lists = iFinGrowthService.quesryComGrowth(map);
        Result result = Result.success(lists);
        return result;
    }

    @ApiOperation(value = "支出整体计划增长率数据",notes = "支出整体计划增长率数据")
    @PostMapping("/quesryExpGrowth")
    public Result quesryExpGrowth(@Valid @RequestBody AcceptVo myData){
        Map map = new Hashtable();
        map.put("periodCode",myData.getPeriodCode());
        List<GrowthVo> growthVos = iFinGrowthService.quesryExpGrowth(map);
        Result result = Result.success(growthVos);
        return result;
    }

    @ApiOperation(value = "机构支出计划增长率数据",notes = "机构支出计划增长率数据")
    @PostMapping("/quesryExpComGrowth")
    public Result quesryExpComGrowth(@Valid @RequestBody AcceptVo myData){
        List<List<GrowthVo>> lists = iFinGrowthService.quesryExpComGrowth(myData);
        Result result = Result.success(lists);
        return result;
    }
}
