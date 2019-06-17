package com.deloitte.services.project.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.isump.feign.DeptFeignService;
import com.deloitte.platform.api.isump.feign.DeputyAccountFeignService;
import com.deloitte.platform.api.isump.feign.OrganizationFeignService;
import com.deloitte.platform.api.isump.feign.UserFeignService;
import com.deloitte.platform.api.isump.param.DeptQueryForm;
import com.deloitte.platform.api.isump.param.DeputyAccountQueryForm;
import com.deloitte.platform.api.isump.param.OrganizationQueryForm;
import com.deloitte.platform.api.isump.param.UserQueryForm;
import com.deloitte.platform.api.isump.vo.*;
import com.deloitte.platform.api.utils.UserUtil;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixin on 25/02/2019.
 */
@Api(tags = "系统基础信息接口")
@Slf4j
@RequestMapping("/project")
@RestController
public class UserController {

    @Autowired
    UserFeignService userService;

    @Autowired
    DeptFeignService deptService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    DeputyAccountFeignService deputyAccountFeignService;

    @Autowired
    OrganizationFeignService organizationFeignService;
    @Autowired
    DeptFeignService deptFeignService;
    @ApiOperation(value = "9-1-1 获取当前用户信息", notes = "9-1-1 获取当前用户信息")
    @GetMapping(path = "/loginUser")
    public Result getUser() throws IOException {
        UserVo userVo = UserUtil.getUserVo();
        if(null != userVo){
            return Result.success(UserUtil.getUserVo());
        }else{
            return Result.fail();
        }
    }

    @ApiOperation(value = "9-1-2 获取当前用户、当前service的菜单", notes = "9-1-2 获取当前用户、当前service的菜单")
    @GetMapping(path = "/loginUser/menu")
    public Result getMenu() throws IOException {
        ResourceVo resourceVo = UserUtil.getMenu();
        if(null != resourceVo){
            return Result.success(resourceVo.getItems());
        }else{
            return Result.fail();
        }
    }
    @ApiOperation(value = "9-1-3 获取当前用户的单位", notes = "9-1-3 获取当前用户的单位")
    @GetMapping(path = "/loginUser/dept")
    public Result getDept() throws IOException {
        DeptVo deptVo = UserUtil.getDept();
        if(null != deptVo){
            return Result.success(deptVo);
        }else{
            return Result.fail();
        }
    }
    @ApiOperation(value = "9-1-4 获取当前用户的部门", notes = "9-1-4 获取当前用户的部门")
    @GetMapping(path = "/loginUser/org")
    public Result getOrg() throws IOException {
        OrganizationVo organizationVo = UserUtil.getOrganization();
        if(null != organizationVo){
            return Result.success(organizationVo);
        }else{
            return Result.fail();
        }
    }
    @ApiOperation(value = "9-1-5 获取当前用户的所有单位信息", notes = "9-1-5 获取当前用户的所有单位信息")
    @PostMapping(path = "/loginUser/deptList")
    public Result getDeptList() throws IOException {
        List<DeputyAccountVo> DeputyAccountVoList = UserUtil.getDeputyAccounts();
        List<DeptVo> deptVoList = new ArrayList<>();
        for(DeputyAccountVo deputyAccountVo :DeputyAccountVoList){
            if(null != deputyAccountVo && null != deputyAccountVo.getOrgId()){
                Result<OrganizationVo> organizationResult = organizationFeignService.get(deputyAccountVo.getOrgId());
                if(organizationResult.isSuccess() && null != organizationResult.getData()){
                    String deptCode = organizationResult.getData().getCode().substring(0,4);
                    DeptQueryForm deptQueryForm = new DeptQueryForm();
                    deptQueryForm.setDeptCode(deptCode);
                    Result<List<DeptVo>>  deptVoListResult = deptFeignService.list(deptQueryForm);
                    if(deptVoListResult.isSuccess() && null != deptVoListResult.getData() &&  deptVoListResult.getData().size() > 0 ){
                        deptVoList.addAll(deptVoListResult.getData());
                    }
                }
            }
        }
        return Result.success(deptVoList) ;
    }
    @ApiOperation(value = "9-1-6 获取当前用户单位下的所有人员分页", notes = "9-1-6 获取当前用户单位下的所有人员分页")
    @PostMapping("/loginUser/orgUser")
    public Result getOrgUser(@Valid @RequestBody UserQueryForm userQueryForm) throws IOException {
        DeptVo deptVo = UserUtil.getDept();
        if(null != deptVo){
            userQueryForm.setOrgCode(deptVo.getDeptCode());
            Result<GdcPage<UserVo>> result = userService.getByOrgCodeListPage(userQueryForm);
            if(result.isSuccess()){
                if(null != result.getData() && null != result.getData().getContent() && result.getData().getContent().size() > 0){
                    List<UserVo> userVoList = result.getData().getContent();
                    for(UserVo userVo : userVoList){
                        if(null != userVo.getDeputyAccountId()){
                            Result<DeputyAccountVo> deputyResult = deputyAccountFeignService.get(userVo.getDeputyAccountId());
                            if(deputyResult.isSuccess() && null != deputyResult.getData()){
                                Result<OrganizationVo> orgResult = organizationFeignService.get(deputyResult.getData().getOrgId());
                                if(orgResult.isSuccess() && null != orgResult.getData()){
                                    userVo.setOrgCode(orgResult.getData().getCode());
                                    userVo.setOrgName(orgResult.getData().getName());
                                }
                            }
                        }else{
                            DeputyAccountQueryForm form = new DeputyAccountQueryForm();
                            form.setUserId(Long.valueOf(userVo.getId()));
                            Result<List<DeputyAccountVo>> deputyResult = deputyAccountFeignService.list(form);
                            if(deputyResult.isSuccess() && null != deputyResult.getData() && deputyResult.getData().size() > 0){
                                Result<OrganizationVo> orgResult = organizationFeignService.get(deputyResult.getData().get(0).getOrgId());
                                if(orgResult.isSuccess() && null != orgResult.getData()){
                                    userVo.setOrgCode(orgResult.getData().getCode());
                                    userVo.setOrgName(orgResult.getData().getName());
                                }
                            }
                        }
                    }
                }
            }
            return result;
        }else{
            return Result.fail();
        }
    }
}