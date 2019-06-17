package com.deloitte.services.dss.scientific.controller;


import com.deloitte.platform.api.dss.scientific.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.dss.scientific.service.ISrpmsProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "科研项目统计接口")
@Slf4j
@RestController
@RequestMapping("/ecientific/srpmsproject")
public class SrpmsProjectController {


    @Autowired
    private ISrpmsProjectService srpmsProjectService;

    @ApiOperation(value = "科研项目各阶段统计")
    @PostMapping(value = "/state")
    public Result<SrpmsProjectVo> queryProjectStage() {
        Map param = new HashMap();
        param.put("category","100101");
        return new Result<SrpmsProjectVo>().success(srpmsProjectService.queryProjectStage(param));
    }


    @ApiOperation(value = "各单位项目申请情况")
    @PostMapping(value = "/deptapply")
    public Result queryDeptProFundDetail() {
        Map map = new HashMap();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        Integer year = Integer.parseInt(format.format(date));
        map.put("year", year);
        map.put("category","100101");
        return Result.success(srpmsProjectService.queryDeptApplyNumAndFunds(map));
    }

    @ApiOperation(value = "立项各单位项目数量及金额")
    @PostMapping(value = "/apprpvaldata")
    public Result queryApprovalNumAndFunds() {
        Map map = new HashMap();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        Integer year = Integer.parseInt(format.format(date));
        map.put("year", year);
        map.put("category","100101");
        return Result.success(srpmsProjectService.queryApprovalNumAndFunds(map));
    }



    @ApiOperation(value = "科研项目申请进度各阶段统计")
    @PostMapping(value = "/applyprocess")
    public Result<ProjectApplyNumVo> queryApplyNums() {
        Map param = new HashMap();
        param.put("category","100101");
        return new Result<ProjectApplyNumVo>().success(srpmsProjectService.queryApplyNums(param));
    }

    @ApiOperation(value = "各单位人均项目数和人均经费")
    @PostMapping(value = "/percapita")
    public Result queryDeptProjectPerson() {
        Map map = new HashMap();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        Integer year = Integer.parseInt(format.format(date));
        map.put("year", year);
        map.put("category","100101");
        return Result.success(srpmsProjectService.queryDeptProjectPerson(map));
    }


    @ApiOperation(value = "立项项目项目预算情况饼状图")
    @PostMapping(value = "/amountpercentage")
    public Result queryAmountPercentage() {
        Map map = new HashMap();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        Integer year = Integer.parseInt(format.format(date));
        map.put("year", year);
        map.put("category","100101");
        return Result.success(srpmsProjectService.queryAmountPercentage(map));
    }


    @ApiOperation(value = "立项项目成员比例饼状图")
    @PostMapping(value = "/personpercentage")
    public Result queryPersonPercentage() {
        Map map = new HashMap();
        Date date = new Date();
        map.put("category","100101");
        return Result.success(srpmsProjectService.queryPersonPercentage(map));
    }


    @ApiOperation(value = "执行项目-各院所项目至今经费执行情况")
    @PostMapping(value = "/allamountpay")
    public Result queryAllAmountPay() {
        Map map = new HashMap();
        map.put("category","100101");
        List<DeptAllAmountPayVo> allAmountPayVos = srpmsProjectService.queryAllAmountPay(map);
        return Result.success(allAmountPayVos);
    }


    @ApiOperation(value = "执行项目-各院所本年到位经费执行情况")
    @PostMapping(value = "/thisyear/revice")
    public Result reviceYear() {
        Map map = new HashMap();
        map.put("category","100101");
        List<DeptAllAmountPayVo> allAmountPayVos = srpmsProjectService.queryAmountThisYear();
        return Result.success(allAmountPayVos);
    }




    @ApiOperation(value = "各所院结题项目数量分布情况,预算金额及总到位金额情况")
    @PostMapping(value = "/conclude")
    public Result queryConclude() {
        List<DeptApplyProjectVo> project = srpmsProjectService.queryConclude();
        List<ConcludeProjectFundVo> funds = srpmsProjectService.queryConcludeFund();
        Map resultMap = new HashMap();
        resultMap.put("project", project);
        resultMap.put("funds", funds);
        return Result.success(resultMap);
    }

    @ApiOperation(value = "各所院成果转化情况,各所院论文发表情况")
    @PostMapping(value = "/achievement")
    public Result queryAchievement() {
        List<DeptAchievementVo> achievement = srpmsProjectService.queryAchievement();
        List<DeptPaperSCIVo> paper = srpmsProjectService.queryPaper();
        Map resultMap = new HashMap();
        resultMap.put("achievement", achievement);
        resultMap.put("paper", paper);
        return Result.success(resultMap);
    }
}
