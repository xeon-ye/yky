package com.deloitte.services.oaservice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.isump.feign.DeptFeignService;
import com.deloitte.platform.api.isump.feign.OrganizationFeignService;
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
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by fq on 11/04/2019.
 */
@Api(value = "UserController", description = "用户API")
@Slf4j
@RequestMapping("/oaservice")
@RestController
public class UserController {

    @Autowired
    UserFeignService userService;

    @Autowired
    DeptFeignService deptService;

    @Autowired
    OrganizationFeignService organizationFeignService;

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping (path = "/addressBookAsync")
    public Result getAddressBook(@RequestParam(name = "nodeId") @ApiParam(name = "各类型的主键参数") String nodeId, @RequestParam(name = "idType") @ApiParam(name = "id的类型，org(组织),dept（单位）") String idType, @RequestParam(name = "needUser") @ApiParam(name = "是否获取组织下的用户") boolean needUser, @RequestParam(name = "hasDiyOrg") @ApiParam(name = "是否获取自定义群组") boolean hasDiyOrg) {
        Map<String, List> map = new HashMap<String, List>();
        if (nodeId == null || "".equals(nodeId)) {
            //表示第一次请求，则返回第一层的数据，如果需要包含自定义的分组，则将分组放在list的前面
            if ("first".equals(idType)) {
                DeptQueryForm deptQueryForm = new DeptQueryForm();
                Result<List<DeptVo>> deptListResult = deptService.list(deptQueryForm);
                if (deptListResult.isSuccess()) {
                    map.put("dept", deptListResult.getData());
                }
            }
            if (hasDiyOrg) {
                List diyOrgList = new ArrayList();
                JSONObject obj = new JSONObject();
                obj.put("id","all");
                obj.put("idType","diyOrg");
                obj.put("orgName","全院人员");
                diyOrgList.add(obj);
                map.put("diyOrg", diyOrgList);
            }
        } else {
            //idType 如果为 org,表示取组织，且需要取该组织下面的人员；idType如果为dept则取单位，idType为user,则不取数
            if ("dept".equals(idType)) {
                Result<DeptVo> deptResult = deptService.get(Long.valueOf(nodeId));
                if (deptResult.isSuccess()) {
//                    OrganizationQueryForm organizationQueryForm = new OrganizationQueryForm();
//                    organizationQueryForm.setCode(deptResult.getData().getOrgCode());
                    Result<List<OrganizationVo>> orgListResult = organizationFeignService.getOrgTreeByCode(deptResult.getData().getDeptCode());
                    if (orgListResult.isSuccess()) {
                        map.put("org", orgListResult.getData());
                    }
                }
            }
            if ("org".equals(idType)) {
                Result<OrganizationVo> orgResult = organizationFeignService.get(Long.valueOf(nodeId));
                if (orgResult.isSuccess()) {
                    OrganizationVo org = orgResult.getData();
                    Result<List<OrganizationVo>> orgListResult = organizationFeignService.getOrgTreeByCode(org.getCode());
                    if (orgResult.isSuccess()) {
                        map.put("org", orgListResult.getData());
                    }
                    if (needUser) {
                        Result<List<UserVo>> userListResult = userService.getByOrgCode(org.getCode());
                        if (userListResult.isSuccess()) {
                            map.put("user", userListResult.getData());
                        }
                    }
                }
            }
        }
        return Result.success(map);
    }


    @GetMapping(path = "/loginUser")
    public Result getUser() throws IOException {
        JSONObject json = new JSONObject();
        json.put("user", UserUtil.getUserVo());
        json.put("dept", UserUtil.getDept());
        return Result.success(json);
    }

    @GetMapping(path = "/loginUser/menu")
    public Result getMenu(UserVo user) throws IOException {
        List<ResourceVo> rs = new ArrayList<>();
//        Object object =  UserUtil.getRedis().opsForValue().get("oaservice_"+UserUtil.getToken() + "_menuForOaService");
//        if(object!=null){
//            rs = JSONObject.parseObject(String.valueOf(object),ResourceVo.class).getChildren();
//            return Result.success(rs);
//        }
        ResourceVo menu = UserUtil.getMenu("oaservice");

        if(menu!=null){
            /* List<ResourceVo> menuChildren = menu.getChildren();
             Map<String,String> map = new HashMap<String,String>();
            if (menuChildren != null) {
                for (int i = 0; i < menuChildren.size(); i ++) {
                    //循环子菜单
                    ResourceVo children = menuChildren.get(i);
                    ResourceVo newChildren = new ResourceVo();
                    //判断此菜单是否存在，如果存在则进入下一个循环
                    if(map.get(children.getId())!=null){
                        continue;
                    }else{
                        map.put(children.getId()+"",children.getName());
                    }

                    newChildren.setName(children.getName());
                    newChildren.setId(children.getId());
                    newChildren.setIcon(children.getIcon());
                    newChildren.setTitle(children.getTitle());
                    newChildren.setUrl(children.getUrl());
                    newChildren.setUri(children.getUri());
                    newChildren.setType(children.getType());
                    newChildren.setReserve(children.getReserve());
                    newChildren.setPerms(children.getPerms());
                    if (children.getChildren() != null) {
                        List<ResourceVo> subMenuTemp = children.getChildren();
                        List<ResourceVo> subMenuNew = new ArrayList<ResourceVo>();
                        for (int k = 0; k < subMenuTemp.size(); k ++) {
                            ResourceVo subTempJson = subMenuTemp.get(k);
                            ResourceVo subNewJson = new ResourceVo();
                            //判断此菜单是否存在，如果存在则进入下一个循环
                            if(map.get(subTempJson.getId())!=null){
                                continue;
                            }else{
                                map.put(subTempJson.getId().toString(),subTempJson.getName());
                            }
                            subNewJson.setName(subTempJson.getName());
                            subNewJson.setId(subTempJson.getId());
                            subNewJson.setIcon(subTempJson.getIcon());
                            subNewJson.setTitle(subTempJson.getTitle());
                            subNewJson.setUri(subTempJson.getUri());
                            subNewJson.setUrl(subTempJson.getUrl());
                            subNewJson.setType(subTempJson.getType());
                            subNewJson.setReserve(subTempJson.getReserve());
                            subNewJson.setPerms(subTempJson.getPerms());
                            subMenuNew.add(subNewJson);
                        }
                        newChildren.setItems(subMenuNew);
                        newChildren.setChildren(subMenuNew);
                    }
                    rs.add(newChildren);
                }
            }
            menu.setChildren(rs);
            menu.setItems(rs);
            UserUtil.getRedis().opsForValue().set( "oaservice_" + UserUtil.getToken() + "_menuForOaService" ,JSON.toJSONString(menu),1, TimeUnit.HOURS);*/
            rs = menu.getChildren();
            return Result.success(rs);
        } else {
            return Result.fail("获取菜单失败");
        }
    }

    @PostMapping(path="/logout/{token}")
    public Result logout(@PathVariable String token){
        UserUtil.releaseToken("oaserivce",token);
        UserUtil.releaseToken("",token);
        return Result.success();
    }

    @GetMapping(path = "/org/{id}")
    public Result getOrg(@PathVariable long id) throws IOException {
        Result result = organizationFeignService.get(id);
        if (result.isSuccess()) {
            return Result.success(result.getData());
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