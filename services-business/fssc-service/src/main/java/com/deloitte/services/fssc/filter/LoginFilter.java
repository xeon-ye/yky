package com.deloitte.services.fssc.filter;

import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.isump.feign.DeptFeignService;
import com.deloitte.platform.api.isump.feign.RoleFeignService;
import com.deloitte.platform.api.isump.feign.UserFeignService;
import com.deloitte.platform.api.isump.vo.ResourceVo;
import com.deloitte.platform.api.isump.vo.RoleVo;
import com.deloitte.platform.api.utils.UserUtil;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.fssc.common.constant.FsscConstants;
import com.deloitte.services.fssc.eums.FsscErrorType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Component
@Slf4j
public class LoginFilter implements Filter {

    static final String[] NOT_FILTER_URI_ARRAY = {
            "swagger",
            "api-docs",
            "receiveFinance",
            "receiveBasic",
            "/fssc/dockingEbs",
            "performance-index",
            "performance-index-library",
            "getProjectBudgetSummary",
            "/budget/projectBudget",
            "uploadTemplateFile",
            "performance-prj-main-type",
            "performance-prj-sub-type",
            "/fssc/dss-scientific-pay"
    };

    @Autowired
    UserFeignService userFeignService;

    @Autowired
    DeptFeignService deptFeignService;

    @Autowired
    RoleFeignService roleService;

    @Autowired
    RedisTemplate redisTemplate;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURL().toString();
        response.setHeader("Access-Control-Allow-Origin", "*");
        //允许的访问方法
        response.setHeader("Access-Control-Allow-Methods",
                "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        //Access-Control-Max-Age 用于 CORS 相关配置的缓存
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
                "Origin, X-Requested-With, Content-Type, Accept,Authorization,token");
        String token = StringUtils.isNotEmpty(request.getHeader("token")) ?
                request.getHeader("token") : request.getParameter("token");
        if (StringUtils.isBlank(token)) {
            //token = "123";
            boolean notFilterFlag = false;
            for (String notFilterUri : NOT_FILTER_URI_ARRAY) {
                if (url.contains(notFilterUri)) {
                    notFilterFlag = true;
                }
            }
            if (notFilterFlag) {
                filterChain.doFilter(request, servletResponse);
            } else {
                responseOutWithJson(response, token,"");
                return;
            }
        }
        else {
            request.setAttribute("token", token);
            log.info("-----请求获取到的token为-------" + token);
            if (StringUtils.isNotEmpty(token)) {
                if (redisTemplate.opsForValue().get(token + "user") == null) {
                    log.info("没有在缓存中找到该token，调用4A服务重新获取");
                    //查询用户是否登录
                    Result result = userFeignService.getLoginUser(token);
                    if (result.isSuccess()) {
                        //获取角色
                        List<RoleVo> roleVoList = UserUtil.getRoles(FsscConstants.SYS_NAME_FOR_4A);
                        if (CollectionUtils.isEmpty(roleVoList)) {
                            responseOutWithJson(response, token, FsscErrorType.USER_NO_ROLE_FOR_FSSC.getMsg());
                            return;
                        }
                        // 获取菜单
                        ResourceVo rootResource = UserUtil.getMenu(FsscConstants.SYS_NAME_FOR_4A);
                        if (rootResource == null){
                            responseOutWithJson(response, token,FsscErrorType.USER_NO_AUTHORIZATION_FOR_FSSC.getMsg());
                            return;
                        }
                        //用户
                        if (UserUtil.getUserVo() == null){
                            responseOutWithJson(response, token,FsscErrorType.GET_USER_NOT_EXIST.getMsg());
                        }
                        // 查询单位信息
                        if (UserUtil.getDept()  == null){
                            responseOutWithJson(response, token,FsscErrorType.GET_DEPT_NOT_EXIST.getMsg());
                            return;
                        }
                        // 部门
                        if (UserUtil.getOrganization()  == null){
                            responseOutWithJson(response, token,FsscErrorType.GET_ORG_NOT_EXIST.getMsg());
                            return;
                        }
                    } else {
                        log.info("-----该token没有登陆-------" + token);
                        log.error("错误编码:{},错误信息:{}", result.getCode(), result.getMesg());
                        responseOutWithJson(response, token,"");
                        return;
                    }
                }
            } else {
                responseOutWithJson(response, token,"");
                return;
            }
            filterChain.doFilter(request, servletResponse);
        }
    }

    protected void responseOutWithJson(HttpServletResponse response, String token, String message) {
        //将实体对象转换为JSON Object转换
        JSONObject responseJSONObject = new JSONObject();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            responseJSONObject.put("code", "9999");
            out = response.getWriter();
            if (StringUtils.isNotBlank(message)){
                responseJSONObject.put("mesg", message);
            }else {
                if (StringUtils.isNotBlank(token)) {
                    responseJSONObject.put("mesg", "登陆超时,请重新登录");
                } else {
                    responseJSONObject.put("mesg", "Token为空,请重新登录");
                }
            }
            responseJSONObject.put("url", "http://124.17.100.183:30080/fssc/index.html#/");
            out.append(responseJSONObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    @Override
    public void destroy() {

    }
}