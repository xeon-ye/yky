package com.deloitte.services.dss.scientific.controller;

import com.deloitte.platform.api.dss.scientific.vo.ProjectAnnualNumberVo;
import com.deloitte.platform.api.dss.scientific.vo.ResearchProjectVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.dss.scientific.service.IResearchProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: ResearchProjectController
 * @Description: 科研项目
 * @Auther: wangyanyun
 * @Date: 2019-02-28
 * @version: v1.0
 */
@Api(tags = "科研项目数据统计接口")
@Slf4j
@RestController
@RequestMapping("/ecientific/project")
public class ResearchProjectController {


    @Autowired
    private IResearchProjectService researchProjectService;




    /**
     * 年度项目数量
     * @return
     */
    @ApiOperation(value = "项目年度数量")
    @PostMapping(value = "/queryProjectNum")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result<List<ProjectAnnualNumberVo>> annualProjectQuantity(){
        List<ProjectAnnualNumberVo> numberVoList = researchProjectService.queryAnnualProjectNum();
        return new Result<List<ProjectAnnualNumberVo>>().sucess(numberVoList);
    };


    /**
     * 获取项目跟金额百分比
     * @return
     */
    @ApiOperation(value = "项目跟金额比")
    @PostMapping(value = "/queryNumAndFundRatio")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result<List<ResearchProjectVo>> projectAndFundRatio(){
        List<ResearchProjectVo> list = researchProjectService.queryNumAndFundRatio();
        return new Result<List<ResearchProjectVo>>().sucess(list);
    };


}
