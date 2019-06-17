package com.deloitte.services.dss.finance.controller;

import com.deloitte.platform.api.dss.finance.vo.AcceptVo;
import com.deloitte.platform.api.dss.finance.vo.FinanceExpenditureVo;
import com.deloitte.platform.api.dss.finance.vo.IncomeBudgetVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.dss.finance.service.IFinExpenditureSerivice;
import com.deloitte.services.dss.util.TimeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.*;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-22
 * @Description : Expenditure服务类接口
 * @Modified :
 */
@Api(tags = "7.财政 支出操作接口")
@Slf4j
@RestController
public class ExpenditureController {

    @Autowired
    private IFinExpenditureSerivice iFinExpenditureSerivice;

    @ApiOperation(value = "当年总支出数据", notes = "当年总支出数据")
    @PostMapping("/selectTotalExp")
    public Result selectTotalExp(@Valid @RequestBody AcceptVo myData){
        Map map = new HashMap();
        map.put("month",TimeUtil.getTime());
        map.put("comCode",myData.getComCode());
        map.put("indexCode","FIND0036");
        List<IncomeBudgetVo> incomeBudgetVos = iFinExpenditureSerivice.selectTotalExp(map);
        Result result = Result.success(incomeBudgetVos);
        return result;
    }

    @ApiOperation(value = "各部门总支出", notes = "各部门总支出")
    @PostMapping("/selectComExp")
    public Result selectComExp(@Valid @RequestBody AcceptVo myData){
        myData.setPeriodCode(TimeUtil.getTime());
        List<List<IncomeBudgetVo>> list = iFinExpenditureSerivice.selectComExp(myData);
        Result result = Result.success(list);
        return result;
    }




}
