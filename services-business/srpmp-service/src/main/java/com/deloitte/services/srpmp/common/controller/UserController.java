package com.deloitte.services.srpmp.common.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.isump.feign.DeptFeignService;
import com.deloitte.platform.api.isump.feign.OrganizationFeignService;
import com.deloitte.platform.api.isump.feign.UserFeignService;
import com.deloitte.platform.api.isump.param.DeptQueryForm;
import com.deloitte.platform.api.isump.param.OrganizationQueryForm;
import com.deloitte.platform.api.isump.param.UserQueryForm;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.DeputyAccountVo;
import com.deloitte.platform.api.isump.vo.ResourceVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.utils.UserUtil;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lixin on 25/02/2019.
 */
@Api(value = "UserController", description = "用户API")
@Slf4j
@RequestMapping("/srpmp")
@RestController
public class UserController {

    @Autowired
    UserFeignService userService;

    @Autowired
    DeptFeignService deptService;

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping(path = "/loginUser")
    public Result getUser(UserVo user, DeptVo dept) throws IOException {

        JSONObject json = new JSONObject();
        json.put("user", user);
        json.put("dept", dept);
        return Result.success(json);
    }

    @GetMapping(path = "/loginUser/menu")
    public Result getMenu(UserVo user) throws IOException {
        ResourceVo vo = UserUtil.getMenu();
        return Result.success(vo.getItems());
    }

    @GetMapping(path = "/org/{id}")
    public Result getOrg(@PathVariable long id) throws IOException {
        Result result = userService.get(id);
        if (result.isSuccess()) {
            return Result.success(userService.get(id).getData());
        } else {
            return Result.fail();
        }
    }

    @GetMapping(path = "/user/{id}")
    public Result getUser(@PathVariable long id) {

        return Result.success(userService.get(id).getData());
    }

    @PostMapping(path = "/user/specialist")
    public Result getSpecialist(@RequestBody UserQueryForm UserQueryForm) {

        return Result.success(userService.randSpecialist(UserQueryForm).getData());
    }

    @PostMapping(path = "/user/list/conditions")
    public Result getUserlist(@RequestBody UserQueryForm UserQueryForm) {

        return Result.success(userService.list(UserQueryForm).getData());
    }

    @PostMapping(path = "/dept/list/conditions")
    public Result getorglist(@RequestBody DeptQueryForm queryForm) {

        return Result.success(deptService.list(queryForm).getData());
    }

    @GetMapping(path = "/dept/Dict")
    public Result getorgDict() {
        UserQueryForm queryForm = new UserQueryForm();
        Result<List<UserVo>> result = userService.list(queryForm);
        JSONArray jsonArray = new JSONArray();
        if (result.isSuccess()) {
            List<String> strList = new ArrayList<String>();
            List<UserVo> list = result.getData();
            for (int i = 0; i < list.size(); i++) {

                UserVo vo = list.get(i);

                if (!strList.contains(vo.getProjectCommitmentUnit())) {
                    if (vo.getProjectCommitmentUnit() != null && !"".equals(vo.getProjectCommitmentUnit())) {
                        strList.add(vo.getProjectCommitmentUnit());
                    }
                }

            }
            for (int j = 0; j < strList.size(); j++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", strList.get(j));
                jsonObject.put("label", strList.get(j));
                jsonArray.add(jsonObject);
            }
        }
        return Result.success(jsonArray);
    }
}