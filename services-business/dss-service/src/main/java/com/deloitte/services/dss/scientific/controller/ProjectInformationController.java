package com.deloitte.services.dss.scientific.controller;

import com.deloitte.platform.api.dss.scientific.vo.ProjectDetailInformationVo;
import com.deloitte.platform.api.dss.scientific.vo.ProjectMemberInformationVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.dss.scientific.service.IProjectInformationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @ClassName: ProjectInformationController
 * @Description:
 * @Auther: wangyanyun
 * @Date: 2019-03-04
 * @version: v1.0
 */
@Api(tags = "项目信息列表")
@Slf4j
@RestController
@RequestMapping("/project/information")
public class ProjectInformationController {

    @Autowired
    private IProjectInformationService informationService;

    /**
     * 年度项目数量
     * @return
     */
    @ApiOperation(value = "项目年度数量")
    @PostMapping(value = "/detail/{projectNum}")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ApiImplicitParam(paramType = "path",name = "projectNum",required = true)
    public Result<ProjectDetailInformationVo> queryProjectDetailInformation(@PathVariable  String projectNum){
        ProjectDetailInformationVo projectDetailInformationVo = informationService.queryProjectDetailInformation(projectNum);
        return new  Result<ProjectDetailInformationVo>().success(projectDetailInformationVo);
    }


    /**
     * 获取项目跟金额百分比
     * @return
     */
    @ApiOperation(value = "项目跟金额比")
    @PostMapping(value = "/member/{projectNum}")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ApiImplicitParam(paramType = "path",name = "projectNum",required = true)
    public Result<List<ProjectMemberInformationVo>> queryProjectMemberInformation(@PathVariable String projectNum){
        List<ProjectMemberInformationVo> projectMemberInformationVoList = informationService.queryProjectMemberInformation(projectNum);
        return new Result<List<ProjectMemberInformationVo>>().sucess(projectMemberInformationVoList);
    }

}
