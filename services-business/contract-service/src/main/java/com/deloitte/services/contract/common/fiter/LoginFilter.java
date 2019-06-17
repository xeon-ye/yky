package com.deloitte.services.contract.common.fiter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.isump.feign.DeptFeignService;
import com.deloitte.platform.api.isump.feign.ResourceFeignService;
import com.deloitte.platform.api.isump.feign.RoleFeignService;
import com.deloitte.platform.api.isump.feign.UserFeignService;
import com.deloitte.platform.api.isump.vo.ProCategoryVo;
import com.deloitte.platform.api.isump.vo.RoleVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.services.contract.common.util.JSONConvert;
import com.deloitte.services.contract.common.util.ParameterRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class LoginFilter implements Filter {

    @Autowired
    UserFeignService userFeignService;

    @Autowired
    DeptFeignService deptFeignService;

    @Autowired
    RoleFeignService roleService;

    @Autowired
    com.deloitte.platform.api.isump.feign.ProCategoryFeignService proCategoryFeignService;

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
        HttpSession session = request.getSession();
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getRequestURL().toString();

        response.setHeader("Access-Control-Allow-Origin", "*");
        //允许的访问方法
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        //Access-Control-Max-Age 用于 CORS 相关配置的缓存
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");


        Map<String, String[]> m = new HashMap<String, String[]>(request.getParameterMap());
        if(null != request.getQueryString() && !"".equals(request.getQueryString())){
            Map<String,String > param = getUrlParams(request.getQueryString());
            Iterator<String> itParam = param.keySet().iterator();
            while (itParam.hasNext()) {
                String key = itParam.next();
                m.put(key, new String[]{param.get(key)});
            }
        }
        request = new ParameterRequestWrapper((HttpServletRequest) request, m);
        if( url.contains("downStampFile") || url.contains("getSysDictVoByTypes") || url.contains("uploadByUrl") ||
                url.contains("saveImprint") || url.contains("saveFinanceInfo")){
            filterChain.doFilter(request, servletResponse);
            return ;
        }
        //如果存在token
        String token = StringUtils.isNotEmpty(request.getParameter("token"))?request.getParameter("token"):(StringUtils.isNotEmpty(request.getHeader("token"))?request.getHeader("token"):session.getAttribute("token")+"");
        if(StringUtils.isNotEmpty(token)){
           /*if (redisTemplate.opsForValue().get(token+"_user") == null) {
                Result result = userFeignService.getLoginUser(token);
                if(result.isFail()){
                    toLogin(response);
                    return;
                }
           }*/
            Result result = userFeignService.getLoginUser(token);
            if(result.isFail()){
                toLogin(response);
                return;
            }
           session.setAttribute("token",token);
        } else {
            toLogin(response);
            return;
        }
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {

    }

    protected void toLogin(HttpServletResponse response) {
        //将实体对象转换为JSON Object转换
        JSONObject responseJSONObject = new JSONObject();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            responseJSONObject.put("code", "9999");
            responseJSONObject.put("url", "获取用户数据失败");
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

    public static Map<String, String> getUrlParams(String param) {
        Map<String, String> map = new HashMap<String, String>(0);
        if (StringUtils.isBlank(param)) {
            return map;
        }
        String[] params = param.split("&");
        for (int i = 0; i < params.length; i++) {
            String[] p = params[i].split("=");
            if (p.length == 2) {
                map.put(p[0], p[1]);
            }
        }
        return map;
    }
}