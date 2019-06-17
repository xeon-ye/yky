package com.deloitte.services.dss.scientific.controller;

import com.deloitte.platform.api.dss.scientific.vo.BudgetAmountImplementationRateVo;
import com.deloitte.platform.api.dss.scientific.vo.ProjectDetailInformationVo;
import com.deloitte.platform.api.dss.scientific.vo.ResearchProjectInPlaceFundVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.dss.scientific.service.ICategoryService;
import com.deloitte.services.dss.scientific.service.IResearchProjectInPlaceFundService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: ResearchProjectInPlaceFundController
 * @Description:
 * @Auther: wangyanyun
 * @Date: 2019-03-11
 * @version: v1.0
 */
@Api(tags = "科研经费接口")
@Slf4j
@RestController
@RequestMapping("/research/project")
public class ResearchProjectInPlaceFundController {


    @Autowired
    private IResearchProjectInPlaceFundService fundService;
    @Autowired
    private ICategoryService categoryService;

    @ApiOperation(value = "各类科研项目到位经费情况")
    @PostMapping(value = "/getAnnualInPlaceFund")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result<List<ResearchProjectInPlaceFundVo>> getAnnualInPlaceFund() {

        return new Result<List<ResearchProjectInPlaceFundVo>>().sucess(fundService.getAnnualInPlaceFund());
    }

    @ApiOperation(value = "小类项目本年到位经费延续和新获项目分布情况")
    @PostMapping(value = "/getSubclassNewAndContinuationFund/{category}")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ApiImplicitParam(paramType = "path", name = "category", required = true)
    public Result<List<ResearchProjectInPlaceFundVo>> getSubclassNewAndContinuationFund(@PathVariable String category) {
        String categoryData = categoryService.getCategoryData(category);

        return new Result<List<ResearchProjectInPlaceFundVo>>().sucess(fundService.getSubclassNewAndContinuationFund(categoryData));
    }

    @ApiOperation(value = "小类科研项目本年到位经费结题和在研项目分布情况")
    @PostMapping(value = "/getSubclassResearchAndConclusionFund/{category}")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ApiImplicitParam(paramType = "path", name = "category", required = true)
    public Result<List<ResearchProjectInPlaceFundVo>> getSubclassResearchAndConclusionFund(@PathVariable String category) {

        return new Result<List<ResearchProjectInPlaceFundVo>>().sucess(fundService.getSubclassResearchAndConclusionFund(category));
    }

    @ApiOperation(value = "单位预算和支出")
    @PostMapping(value = "/getDeptBudgetFund/{category}")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ApiImplicitParam(paramType = "path", name = "category", required = true)
    public Result<List<BudgetAmountImplementationRateVo>> getDeptBudgetAndExpendFund(@PathVariable String category) {

        return new Result<List<BudgetAmountImplementationRateVo>>().sucess(fundService.getDeptBudgetAndExpendFund(category));
    }


    @ApiOperation(value = "项目预算和支出")
    @PostMapping(value = "/getProjectBudgetFundByDept/{category}/{deptId}")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "category", required = true),
            @ApiImplicitParam(paramType = "path", name = "deptId", required = true)
    })
    public Result<List<BudgetAmountImplementationRateVo>> getProjectBudgetFundByDept(@PathVariable String category,@PathVariable Long deptId) {

        return new Result<List<BudgetAmountImplementationRateVo>>().sucess(fundService.getProjectBudgetAndExpendFundByDept(category, deptId));
    }



    @ApiOperation(value = "项目详情")
    @PostMapping(value = "/getProjectDetail/{projectNum}")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ApiImplicitParam(paramType = "path", name = "projectNum", required = true)
    public Result<ProjectDetailInformationVo> getProjectDetail(@PathVariable String projectNum) {

        return new Result<ProjectDetailInformationVo>().sucess(fundService.getProjectDetail(projectNum));
    }

    @ApiOperation(value = "PI承担数量")
    @PostMapping(value = "/queryPItotal/{projectNum}")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ApiImplicitParam(paramType = "path", name = "projectNum", required = true)
    public Result<ProjectDetailInformationVo> queryPItotal(@PathVariable String projectNum) {

        return new Result<ProjectDetailInformationVo>().sucess(fundService.queryPItotal(projectNum));
    }


}
