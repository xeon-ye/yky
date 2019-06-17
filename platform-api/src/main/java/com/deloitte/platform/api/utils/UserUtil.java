package com.deloitte.platform.api.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.hr.employee.vo.EmployeeBaseVo;
import com.deloitte.platform.api.isump.feign.*;
import com.deloitte.platform.api.isump.param.DeputyAccountQueryForm;
import com.deloitte.platform.api.isump.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 用户工具类
 */
@Component
@Log4j
public class UserUtil {

    private static RedisTemplate redisTemplate ;
    @Resource
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        UserUtil.redisTemplate = redisTemplate;
    }

    private static UserFeignService userFeignService;
    @Resource
    public void setUserFeignService(UserFeignService userFeignService){
        UserUtil.userFeignService = userFeignService;
    }

    private static DeputyAccountFeignService deputyAccountFeignService;
    @Resource
    public void setDeputyAccountFeignService(DeputyAccountFeignService deputyAccountFeignService){
        UserUtil.deputyAccountFeignService = deputyAccountFeignService;
    }

    private static RoleFeignService roleFeignService;
    @Resource
    public void setRoleFeignService(RoleFeignService roleFeignService){
        UserUtil.roleFeignService = roleFeignService;
    }

    private static ResourceFeignService resourceFeignService;
    @Resource
    public void setResourceFeignService(ResourceFeignService resourceFeignService){
        UserUtil.resourceFeignService = resourceFeignService;
    }

    private static DeptFeignService deptFeignService;
    @Resource
    public void setDeptFeignService(DeptFeignService deptFeignService){
        UserUtil.deptFeignService = deptFeignService;
    }

    private static OrganizationFeignService organizationFeignService;
    @Resource
    public void setOrganizationFeignService(OrganizationFeignService organizationFeignService){
        UserUtil.organizationFeignService = organizationFeignService;
    }
    private static Environment environment;
    @Resource
    public void setEnvironment(Environment environment){
        UserUtil.environment = environment;
    }
    /**
     * 获取一个request对象的方法
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return (HttpServletRequest) attrs.getRequest();
    }

    public static RedisTemplate<String,Object> getRedis(){
        return UserUtil.redisTemplate;
    }
    /**
     * 获取session 的方法
     *
     * @return HttpSession
     */
    public static HttpSession getSession() {
        HttpSession session = null;
        try {
            session = getRequest().getSession();
        } catch (Exception e) {
        }
        return session;
    }

    public static String getToken(){
        HttpServletRequest request = getRequest();
        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)){
            token = request.getParameter("token");
        }
        if (StringUtils.isEmpty(token)){
            token = null != request.getAttribute("token")? request.getAttribute("token").toString()+"" : "" ;
        }
        if(StringUtils.isEmpty(token)){
            token = null != request.getSession().getAttribute("token")? request.getSession().getAttribute("token")+"": "" ;
        }
        return token;
    }
    /**
     * 获取用户信息
     * @return
     */
    public static UserVo getUserVo(){
        //获取token
        String token = getToken();
        if(StringUtils.isNotEmpty(token)){
            ValueOperations<String,Object> ops = redisTemplate.opsForValue();
            Object object = ops.get(token + "_user");
            if(object != null){
                UserVo user = JSONObject.parseObject(String.valueOf(object),UserVo.class);
                return user;
            }else{
                Result<UserVo> result = userFeignService.getLoginUser(token);
                if(result.isSuccess()){
                   UserUtil.putRedis("","user",JSON.toJSONString(result.getData()));
                    UserVo user = JSONObject.parseObject(JSON.toJSONString(result.getData()),UserVo.class);
                    return user;
                }
            }
        }
//        UserVo vo = new UserVo();
//        vo.setId("236");
//        vo.setName("用户未登录");
//        vo.setEmpNo("HR001");
        return null;
    }

    /**
     * 获取副账号列表
     * @return
     */
    public static List<DeputyAccountVo> getDeputyAccounts(){
        //获取token
        String token = getToken();
        if(StringUtils.isNotEmpty(token)){
            Object object = getRedis().opsForValue().get(token + "_deputy_accounts");
            if(object != null){
                List<DeputyAccountVo> accounts = JSONArray.parseArray(String.valueOf(object), DeputyAccountVo.class);
                return accounts;
            }else{
                DeputyAccountQueryForm form = new DeputyAccountQueryForm();
                UserVo user = getUserVo();
                if(user==null||user.getId()==null||"".equals(user.getId())){
                    return new ArrayList<>();
                }
                form.setUserId(Long.valueOf(user.getId()));
                Result<List<DeputyAccountVo>> result = deputyAccountFeignService.list(form);
                if(result.isSuccess()){
                    UserUtil.putRedis("","deputy_accounts",JSON.toJSONString(result.getData()));
                    List<DeputyAccountVo> list = JSONArray.parseArray(JSON.toJSONString(result.getData()),DeputyAccountVo.class);
                    return list;
                }
            }
        }
        return new ArrayList<>();
    }

    /**
     * 获取当前副账号
     * @return
     */
    public static DeputyAccountVo getCurrentDeputyAccount(){
        //获取token
        String token = getToken();
        if(StringUtils.isNotEmpty(token)){
            Object object = getRedis().opsForValue().get(token + "_current_account");
            if(object != null){
                DeputyAccountVo account = JSONObject.parseObject(String.valueOf(object), DeputyAccountVo.class);
                return account;
            }else{
                UserVo user = getUserVo();
                if(user==null||user.getId()==null||"".equals(user.getId())){
                    return new DeputyAccountVo();
                }
                if(user.getDeputyAccountId() != null){
                    Result<DeputyAccountVo> result = deputyAccountFeignService.get(user.getDeputyAccountId());
                    if(result.isSuccess()){
                        UserUtil.putRedis("","current_account",JSON.toJSONString(result.getData()));
                        DeputyAccountVo vo = JSONObject.parseObject(JSON.toJSONString(result.getData()),DeputyAccountVo.class);
                        return vo;
                    }
                }else{
                    List<DeputyAccountVo> list = getDeputyAccounts();
                    if(list != null && list.size() > 0){
                        return list.get(0);
                    }
                }


            }
        }
        return new DeputyAccountVo();
    }

    public static List<RoleVo> getRoles(String serviceName){
        //获取token
        String token = getToken();
        String service ;
        if(serviceName==null||"".equals(serviceName)){
            service = getService();
        }else{
            service = serviceName;
        }
        if(StringUtils.isNotEmpty(token)){
            Object object = getRedis().opsForValue().get(service + "_" + token + "_roles");
            if(object != null){
                List<RoleVo> roles = JSONArray.parseArray(String.valueOf(object), RoleVo.class);
                return roles;
            }else{
                DeputyAccountVo vo = getCurrentDeputyAccount();
                Result<List<RoleVo>> result = roleFeignService.getByDeputyAccountId(vo.getId(),"",service);
                if(result.isSuccess()){
                    UserUtil.putRedis(service,"roles",JSON.toJSONString(result.getData()));
                    List<RoleVo> list = JSONArray.parseArray(JSON.toJSONString(result.getData()),RoleVo.class);
                    return list;
                }
            }
        }
        return new ArrayList<>();
    }

    /**
     * 获取角色列表
     * @return
     */
    public static List<RoleVo> getRoles(){
        return getRoles("");
    }

    /**
     * 获取HR系统员工基本信息（如果为空，只能自己到hr系统里查询，没做feign查询）
     * @return
     */
    public static EmployeeBaseVo getEmployee(){
        //获取token
        String token = getToken();
        if(StringUtils.isNotEmpty(token)){
            Object object = getRedis().opsForValue().get(token + "_employee");
            if(object != null){
                EmployeeBaseVo employee = JSONObject.parseObject(String.valueOf(object), EmployeeBaseVo.class);
                return employee;
            }
        }
        return new EmployeeBaseVo();
    }

    /**
     * 获取菜单
     * @param serviceName
     * @return
     */
    public static ResourceVo getMenu(String serviceName){
        //获取token
        String token = getToken();
        String service ;
        if(serviceName==null||"".equals(serviceName)){
            service = getService();
        }else{
            service = serviceName;
        }
        if(StringUtils.isNotEmpty(token)){
            Object object = getRedis().opsForValue().get(service + "_" + token + "_menu");
            if(object != null){
                ResourceVo resource = JSONObject.parseObject(String.valueOf(object), ResourceVo.class);
                return resource;
            }else{
                // 获取菜单
                Result<ResourceVo> result = resourceFeignService.tree(getCurrentDeputyAccount().getId(), service);
                if(result.isSuccess()){
                    UserUtil.putRedis(service,"menu",JSON.toJSONString(result.getData()));
                    ResourceVo vo = JSONObject.parseObject(JSON.toJSONString(result.getData()),ResourceVo.class);
                    return vo;
                }
            }
        }
        return new ResourceVo();
    }
    /**
     * 获取菜单
     * @return
     */
    public static ResourceVo getMenu(){
        return getMenu("");
    }
    /**
     * 获取单位信息
     * @return
     */
    public static DeptVo getDept(){
        //获取token
        String token = getToken();
        if(StringUtils.isNotEmpty(token)){
            Object object = getRedis().opsForValue().get(token + "_dept");
            if(object != null){
                DeptVo dept = JSONObject.parseObject(String.valueOf(object), DeptVo.class);
                return dept;
            }else{
                UserVo user = getUserVo();
                if(user==null||user.getId()==null||"".equals(user.getId())){
                    return new DeptVo();
                }
                Result<DeptVo> result = deptFeignService.getByCode(getUserVo().getDept());
                if(result.isSuccess()){
                    UserUtil.putRedis("","dept",JSON.toJSONString(result.getData()));
                    DeptVo vo = JSONObject.parseObject(JSON.toJSONString(result.getData()),DeptVo.class);
                    return vo;
                }
            }
        }
        return new DeptVo();
    }

    /**
     * 获取部门信息
     * @return
     */
    public static OrganizationVo getOrganization(){
        //获取token
        String token = getToken();
        if(StringUtils.isNotEmpty(token)){
            Object object = getRedis().opsForValue().get(token + "_org");
            if(object != null){
                OrganizationVo organization = JSONObject.parseObject(String.valueOf(object), OrganizationVo.class);
                return organization;
            }else{
                if(getCurrentDeputyAccount().getOrgId()!=null){
                    Result<OrganizationVo> result = organizationFeignService.get(getCurrentDeputyAccount().getOrgId());
                    if(result.isSuccess()){
                        UserUtil.putRedis("","org",JSON.toJSONString(result.getData()));
                        OrganizationVo vo = JSONObject.parseObject(JSON.toJSONString(result.getData()),OrganizationVo.class);
                        return vo;
                    }
                }
            }
        }
        return new OrganizationVo();
    }

    /**
     * 获取访问系统名称
     * @return
     */
    public static String getService(){
        //获取请求地址中的对应系统名称
        String service = environment.getProperty("spring.application.name");
        if(StringUtils.isNotEmpty(service)){
            service = service.replace("service-","");
        }else{
            service = "cams";
        }
        return service;
    }

    public static void releaseToken(String service,String token){
        String serviceName = "";
        if(service==null||"".equals(service)){
            serviceName = getService();
        }else{
            serviceName = service;
        }
        try {
            getRedis().delete(token + "_" + "user");
            getRedis().delete(token);
            getRedis().delete(serviceName + "_" + token + "_" + "org");
            getRedis().delete(serviceName + "_" + token + "_" + "dept");
            getRedis().delete(serviceName + "_" + token + "_" + "menu");
            getRedis().delete(serviceName + "_" + token + "_" + "employee");
            getRedis().delete(serviceName + "_" + token + "_" + "roles");
            getRedis().delete(serviceName + "_" + token + "_" + "deputy_accounts");
            getRedis().delete(serviceName + "_" + token + "_" + "user");
            getRedis().delete(token + "_" + "org");
            getRedis().delete(token + "_" + "dept");
            getRedis().delete(token + "_" + "menu");
            getRedis().delete(token + "_" + "employee");
            getRedis().delete(token + "_" + "roles");
            getRedis().delete(token + "_" + "deputy_accounts");
            getRedis().delete(token + "_" + "current_account");
            getRedis().delete(token + "_" + "employee");

        }catch (Exception e){

        }
    }

    public static void release(String key){
        getRedis().delete(key);
    }

    public static void putRedis(String key,Object value){
        putRedis("",key,value);
    }

    /**
     * 保存数据到redis，默认保存时间3小时
     * @param key 名称
     * @param value 值
     */
    public static void putRedis(String service,String key,Object value){
        String serviceName = "";
        if(service==null||"".equals(service)){
            serviceName = getService();
        }else{
            serviceName = service;
        }
        //角色列表、菜单列表不共用
        if("roles".equals(key) || "menu".equals(key)){
            getRedis().opsForValue().set(serviceName + "_" + getToken() + "_" + key,value,3, TimeUnit.HOURS);
        }else{
            getRedis().opsForValue().set( getToken() + "_" + key,value,3, TimeUnit.HOURS);
        }

    }

}
