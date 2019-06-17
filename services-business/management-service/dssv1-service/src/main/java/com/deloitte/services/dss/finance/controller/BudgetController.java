package com.deloitte.services.dss.finance.controller;

import com.deloitte.platform.api.dss.finance.vo.AcceptVo;
import com.deloitte.platform.api.dss.finance.vo.IncomeBudgetVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.dss.finance.service.IFinBudgetService;
import com.deloitte.services.dss.util.TimeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-09
 * @Description : Budget服务类接口
 * @Modified :
 */
@Api(tags = "3.财政 预算操作接口")
@Slf4j
@RestController
@RequestMapping("/dss/finance")
public class BudgetController {

    @Autowired
    private IFinBudgetService iFinBudgetService;

    @ApiOperation(value = "年收入预算数据", notes = "年收入预算数据")
    @PostMapping("/selectBudget")
    public Result selectBudget(@Valid @RequestBody AcceptVo myData) {
        Map map = new HashMap();
        map.put("month",TimeUtil.getTime());
        map.put("comCode",myData.getComCode());
        List<List<IncomeBudgetVo>> lists = iFinBudgetService.selectBudget(map);
        Result result = Result.success(lists);
        return result;
    }

    @ApiOperation(value = "机构年收入预算数据", notes = "机构年收入预算数据")
    @PostMapping("/selectComBudget")
    public Result selectComBudget(@Valid @RequestBody AcceptVo myData) {
        Map map = new HashMap();
        map.put("comCode",myData.getComCode());
        map.put("month",TimeUtil.getTime());
        map.put("indexCode","FIND0001");
        List<IncomeBudgetVo> lists = iFinBudgetService.selectComBudget(map);
        Result result = Result.success(lists);
        return result;
    }

    @ApiOperation(value = "年支出预算数据", notes = "年支出预算数据")
    @PostMapping("/selectExpBudget")
    public Result selectExpBudget(@Valid @RequestBody AcceptVo myData) {
        IncomeBudgetVo incomeBudgetVo = new IncomeBudgetVo();
        incomeBudgetVo.setPeriodCode(TimeUtil.getTime());
        incomeBudgetVo.setComCodes(myData.getComCode());
        List<List<IncomeBudgetVo>> lists = iFinBudgetService.selectExpBudget(incomeBudgetVo);
        Result result = Result.success(lists);
        return result;
    }

    @ApiOperation(value = "机构年支出预算数据", notes = "机构年支出预算数据")
    @PostMapping("/selectComExpBudget")
    public Result selectComExpBudget(@Valid @RequestBody AcceptVo myData) {
        Map paramMap = new HashMap();
        Map result = new HashMap();
        List<IncomeBudgetVo> list = new ArrayList<IncomeBudgetVo>();
        List<List<IncomeBudgetVo>> resultList = new ArrayList<List<IncomeBudgetVo>>();
        String year = TimeUtil.getYear();

        String period = year + "-1";
        paramMap.put("month",period);
        paramMap.put("indexCode","FIND0013");
        paramMap.put("comCode",myData.getComCode());
        list = iFinBudgetService.selectComExpBudget(paramMap);
        for(int i = 0; i < list.size(); i++){
            list.get(i).setPeriodCode(year);
        }
        resultList.add(list);

        list = new ArrayList<IncomeBudgetVo>();
        period = Integer.parseInt(year) - 1 + "-1";
        paramMap.put("month",period);
        list = iFinBudgetService.selectComExpBudget(paramMap);
        if(list.size() == 0){
            IncomeBudgetVo temp = new IncomeBudgetVo();
            temp.setPeriodCode(Integer.parseInt(year) - 1 + "");
            temp.setYtd(BigDecimal.valueOf(0));
            list.add(temp);
        }
        resultList.add(list);

        list = new ArrayList<IncomeBudgetVo>();
        period = Integer.parseInt(year) - 2 + "-1";
        paramMap.put("year",period);
        list = iFinBudgetService.selectComExpBudget(paramMap);
        if(list.size() == 0){
            IncomeBudgetVo temp = new IncomeBudgetVo();
            temp.setPeriodCode(Integer.parseInt(year) - 1 + "");
            temp.setYtd(BigDecimal.valueOf(0));
            list.add(temp);
        }
        resultList.add(list);
        result.put("expPerMonth",resultList);

        return new Result().sucess(result);
    }


}
