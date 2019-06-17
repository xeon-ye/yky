package com.deloitte.services.contract.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.isump.feign.DeptFeignService;
import com.deloitte.platform.api.isump.feign.UserFeignService;
import com.deloitte.platform.api.isump.param.DeptQueryForm;
import com.deloitte.platform.api.isump.param.UserQueryForm;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.isump.vo.ResourceVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.utils.UserUtil;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixin on 25/02/2019.
 */
@Api(tags = "8-系统基础信息接口")
@Slf4j
@RequestMapping("/contract")
@RestController
public class UserController {

    @Autowired
    UserFeignService userService;

    @Autowired
    DeptFeignService deptService;

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping(path = "/loginUser")
    public Result getUser() throws IOException {
        UserVo userVo = UserUtil.getUserVo();
        if(null != userVo){
            return Result.success(UserUtil.getUserVo());
        }else{
            return Result.fail();
        }
    }

    @GetMapping(path = "/loginUser/menu")
    public Result getMenu() throws IOException {
        ResourceVo resourceVo = UserUtil.getMenu();
        if(null != resourceVo){
            return Result.success(resourceVo.getItems());
        }else{
            return Result.fail();
        }
    }
    @GetMapping(path = "/loginUser/dept")
    public Result getDept() throws IOException {
        DeptVo deptVo = UserUtil.getDept();
        if(null != deptVo){
            return Result.success(deptVo);
        }else{
            return Result.fail();
        }
    }
    @GetMapping(path = "/loginUser/org")
    public Result getOrganization() throws IOException {
        OrganizationVo organization = UserUtil.getOrganization();
        if(null != organization){
            return Result.success(organization);
        }else{
            return Result.fail();
        }
    }
}