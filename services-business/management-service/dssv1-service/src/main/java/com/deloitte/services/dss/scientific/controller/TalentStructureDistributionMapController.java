package com.deloitte.services.dss.scientific.controller;

import com.deloitte.platform.api.dss.scientific.vo.TalentStructureDistributionMapVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.dss.scientific.service.ITalentStructureDistributionMapService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
 * @ClassName: TalentStructureDistributionMapController
 * @Description:
 * @Auther: wangyanyun
 * @Date: 2019-03-11
 * @version: v1.0
 */
@Api(tags = "科研人才接口")
@Slf4j
@RestController
@RequestMapping("/research/person")
public class TalentStructureDistributionMapController {

    @Autowired
    private ITalentStructureDistributionMapService mapService;

    @ApiOperation(value = "各类型项目人才分布")
    @PostMapping(value = "/project/talent")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result<TalentStructureDistributionMapVo> getProjectTalentStructureDistribution() {

        return new Result<TalentStructureDistributionMapVo>().sucess(mapService.getProjectTalentStructureDistribution());
    }

    @ApiOperation(value = "人才结构分布图")
    @PostMapping(value = "/talent/{category}")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ApiImplicitParam(paramType = "path", name = "category", required = true)
    public Result<TalentStructureDistributionMapVo> getTalentStructureDistribution(@PathVariable String category) {

        TalentStructureDistributionMapVo talentStructureDistribution = mapService.getTalentStructureDistribution(category);
        if (null == talentStructureDistribution){
            talentStructureDistribution  =  new TalentStructureDistributionMapVo();
        }
        return new Result<TalentStructureDistributionMapVo>().sucess(talentStructureDistribution);
    }

    @ApiOperation(value = "各依托单位人才结构分布图")
    @PostMapping(value = "/dept/talent/{category}")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ApiImplicitParam(paramType = "path", name = "category", required = true)
    public Result<List<TalentStructureDistributionMapVo>> getDeptTalentStructureDistribution(@PathVariable String category) {

        return new Result<List<TalentStructureDistributionMapVo>>().sucess(mapService.getDeptTalentStructureDistribution(category));
    }


}
