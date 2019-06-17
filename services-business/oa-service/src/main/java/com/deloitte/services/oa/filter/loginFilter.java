package com.deloitte.services.oa.filter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.isump.feign.DeptFeignService;
import com.deloitte.platform.api.isump.feign.ResourceFeignService;
import com.deloitte.platform.api.isump.feign.RoleFeignService;
import com.deloitte.platform.api.isump.feign.UserFeignService;
import com.deloitte.platform.api.isump.vo.ProCategoryVo;
import com.deloitte.platform.api.isump.vo.RoleVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.utils.UserUtil;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.services.oa.util.JSONConvert;
import com.deloitte.services.oa.util.ParameterRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class loginFilter implements Filter {

    @Autowired
    UserFeignService userFeignService;

    @Autowired
    DeptFeignService deptFeignService;

    @Autowired
    RoleFeignService roleService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    ResourceFeignService resourceFeignService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getRequestURL().toString();
        log.info("----------------  请求IP为-------------" + request.getRemoteAddr());
        log.info("-----请求URL为-------" + url);
        boolean jumpFlg = false;
        if (!url.contains("oaservice")) {
            jumpFlg = true;
        }
        if (url.contains("oaservice/mss/save/oaMssInfo")) {
            jumpFlg = true;
        }
        if (url.contains("oaservice/mss/save/oaMssSendInfo")) {
            jumpFlg = true;
        }
        if (url.contains("localhost")) {
            jumpFlg = true;
        }
        if (url.contains("websocket")) {
            jumpFlg = true;
            log.info("-----请求websocket为-------" + url);
        }
        if (url.contains("127.0.0.1")) {
            jumpFlg = true;
        }
        if (jumpFlg) {
            log.info("-----请求会跳过4A认证-------");
        } else {
            log.info("-----请求会进行4A认证-------");
        }
        //暂时屏蔽用户验证信息
//        if(url.contains("oaservice")){
//            log.info("-----请求会跳过4A认证-------");
//            filterChain.doFilter(request, servletResponse);
//            return;
//        }
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        //允许的访问方法
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        //Access-Control-Max-Age 用于 CORS 相关配置的缓存
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("XDomainRequestAllowed","1");
        //如果存在token
        String token = StringUtils.isNotEmpty(request.getHeader("token"))?request.getHeader("token"):request.getParameter("token");
        log.info("-----请求获取到的token为-------" + token);

        if(StringUtils.isNotEmpty(token)){
            UserVo user = UserUtil.getUserVo();
            if(user==null){
                responseOutWithJson(response);
                return;
            }else if(user.getId()==null||"".equals(user.getId())){
                responseOutWithJson(response);
                return;
            }
        } else {
            if (!jumpFlg) {
                responseOutWithJson(response);
                return;
            }else{
                filterChain.doFilter(request, servletResponse);
                return;
            }
        }
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {

    }

    protected void responseOutWithJson(HttpServletResponse response) {
        //将实体对象转换为JSON Object转换
        JSONObject responseJSONObject = new JSONObject();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            responseJSONObject.put("code", "9999");
            responseJSONObject.put("url", "/");
            out = response.getWriter();
            out.append(responseJSONObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}