package com.deloitte.services.dss.scientific.controller;


import com.deloitte.platform.api.dss.scientific.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.dss.scientific.service.ICategoryService;
import com.deloitte.services.dss.scientific.service.ISrpmsProjectService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "科研项目接口")
@Slf4j
@RestController
@RequestMapping("/ecientific/srpmsproject")
public class SrpmsProjectController {

    @Autowired
    private ISrpmsProjectService srpmsProjectService;
    @Autowired
    private ICategoryService categoryService;

    @ApiOperation(value = "项目池各阶段项目分布")
    @PostMapping(value = "/state/{category}")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ApiImplicitParam(paramType = "path", name = "category", required = true)
    public Result<SrpmsProjectVo> queryProjectStage(@PathVariable String category) {
//        Map param = new HashMap();
//        param.put("category",category);
        String categoryData = categoryService.getCategory(category);
        return new Result<SrpmsProjectVo>().success(srpmsProjectService.queryProjectStage(categoryData));
    }


    @ApiOperation(value = "各所院项目本年申请金额及数量情况")
    @PostMapping(value = "/deptapply/{category}")
    @ApiImplicitParam(paramType = "path", name = "category", required = true)
    public Result queryDeptProFundDetail(@PathVariable String category) {
        Map map = new HashMap();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        Integer year = Integer.parseInt(format.format(date));
        map.put("year", year);
        map.put("category",category);
        return Result.success(srpmsProjectService.queryDeptApplyNumAndFunds(category));
    }

    @ApiOperation(value = "立项各单位项目数量及金额")
    @PostMapping(value = "/apprpvaldata/{category}")
    @ApiImplicitParam(paramType = "path", name = "category", required = true)
    public Result queryApprovalNumAndFunds(@PathVariable String category) {
        Map map = new HashMap();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        Integer year = Integer.parseInt(format.format(date));
        map.put("year", year);
        map.put("category",category);
        return Result.success(srpmsProjectService.queryApprovalNumAndFunds(category));
    }



    @ApiOperation(value = "项目申请进度数量情况")
    @PostMapping(value = "/applyprocess/{category}")
    @ApiImplicitParam(paramType = "path", name = "category", required = true)
    public Result<ProjectApplyNumVo> queryApplyNums(@PathVariable String category) {
        Map param = new HashMap();
        param.put("category",category);
        return new Result<ProjectApplyNumVo>().success(srpmsProjectService.queryApplyNums(category));
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
    @PostMapping(value = "/amountpercentage/{category}")
    @ApiImplicitParam(paramType = "path", name = "category", required = true)
    public Result queryAmountPercentage(@PathVariable String category) {
        Map map = new HashMap();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        Integer year = Integer.parseInt(format.format(date));
        map.put("year", year);
        map.put("category",category);
        return Result.success(srpmsProjectService.queryAmountPercentage(category));
    }


    @ApiOperation(value = "立项项目成员比例饼状图")
    @PostMapping(value = "/personpercentage/{category}")
    @ApiImplicitParam(paramType = "path", name = "category", required = true)
    public Result queryPersonPercentage(@PathVariable String category) {
        Map map = new HashMap();
        Date date = new Date();
        map.put("category",category);
        return Result.success(srpmsProjectService.queryPersonPercentage(category));
    }


    @ApiOperation(value = "执行项目-各院所项目至今经费执行情况")
    @PostMapping(value = "/allamountpay/{category}")
    @ApiImplicitParam(paramType = "path", name = "category", required = true)
    public Result queryAllAmountPay(@PathVariable String category) {
        Map map = new HashMap();
        map.put("category",category);
        List<DeptAllAmountPayVo> allAmountPayVos = srpmsProjectService.queryAllAmountPay(category);
        return Result.success(allAmountPayVos);
    }


    @ApiOperation(value = "执行项目-各院所本年到位经费执行情况")
    @PostMapping(value = "/thisyear/revice/{category}")
    @ApiImplicitParam(paramType = "path", name = "category", required = true)
    public Result reviceYear(@PathVariable String category) {
        Map map = new HashMap();
        map.put("category",category);
        List<DeptAllAmountPayVo> allAmountPayVos = srpmsProjectService.queryAmountThisYear(category);
        return Result.success(allAmountPayVos);
    }




    @ApiOperation(value = "各所院结题项目数量分布情况,预算金额及总到位金额情况")
    @PostMapping(value = "/conclude/{category}")
    @ApiImplicitParam(paramType = "path", name = "category", required = true)
    public Result queryConclude(@PathVariable String category) {
        List<DeptApplyProjectVo> project = srpmsProjectService.queryConclude(category);
        List<ConcludeProjectFundVo> funds = srpmsProjectService.queryConcludeFund(category);
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
