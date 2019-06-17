package com.deloitte.services.srpmp.common.fiter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.isump.feign.RoleFeignService;
import com.deloitte.platform.api.isump.feign.UserFeignService;
import com.deloitte.platform.api.isump.vo.ProCategoryVo;
import com.deloitte.platform.api.isump.vo.RoleVo;
import com.deloitte.platform.api.utils.UserUtil;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.services.srpmp.common.util.ParameterRequestWrapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class loginFilter implements Filter {

	static final String[] NOT_FILTER_URI_ARRAY = { "swagger", "api-docs", "/srpmp/project/feign",
			"/srpmp/project/budget/account" };

	@Autowired
	UserFeignService userFeignService;

	@Autowired
	RoleFeignService roleService;

	@Autowired
	com.deloitte.platform.api.isump.feign.ProCategoryFeignService proCategoryFeignService;

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

		response.setHeader("Access-Control-Allow-Origin", "*");
		// 允许的访问方法
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
		// Access-Control-Max-Age 用于 CORS 相关配置的缓存
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		// 如果存在token
		String token = StringUtils.isNotEmpty(request.getHeader("token")) ? request.getHeader("token")
				: request.getParameter("token");
		JSONObject userJson = null;
		JSONObject deptJson = null;
		log.info("-----请求获取到的token为-------" + token);
		if (StringUtils.isNotEmpty(token)) {

			// 查询用户是否登录
			Result result = userFeignService.getLoginUser(token);

			if (result.isSuccess()) {

				// 查询项目类型属性
				String proType = null;
				Result<List<ProCategoryVo>> proCategory = proCategoryFeignService
						.getByDeputyAccountId(UserUtil.getCurrentDeputyAccount().getId());
				if (proCategory.isSuccess()) {
					StringBuffer sbProType = new StringBuffer();
					for (int i = 0; i < proCategory.getData().size(); i++) {
						ProCategoryVo item = proCategory.getData().get(i);
						if (i != 0) {
							sbProType.append(",");
						}
						sbProType.append(item.getCode());
					}
					proType = sbProType.toString();
					log.info("当前用户的项目类型权限是：" + proType);
				} else {
					log.info(proCategory.getCode());
					log.info(proCategory.getMesg());
					throw new BaseException();
				}

				// 获取角色
				Result<List<RoleVo>> role = roleService.getByDeputyAccountId(UserUtil.getCurrentDeputyAccount().getId(),
						"data", "srpmp");
				String roleCode = "pi";
				if (role.isSuccess()) {
					if (role.getData()!=null && role.getData().size() > 0){
						roleCode = role.getData().get(0).getCode();
					}
				} else {
					log.info(role.getCode());
					log.info(role.getMesg());
					throw new BaseException();
				}

				userJson = JSONObject.parseObject(JSON.toJSONString(UserUtil.getUserVo()));

				userJson.put("honor", roleCode);
				userJson.put("remark", proType);

				deptJson = JSONObject.parseObject(JSON.toJSONString(UserUtil.getDept()));
			} else {
				log.info("-----该token没有登陆-------" + token);
				log.info(result.getCode());
				log.info(result.getMesg());
				responseOutWithJson(response);
				return;
			}

			if (userJson != null) {
				log.info("登陆用户信息为" + userJson.toJSONString());
				log.info("登陆部门信息为" + deptJson.toJSONString());
				if (userJson != null) {
					Map<String, String[]> m = new HashMap<String, String[]>(request.getParameterMap());
					if (userJson != null) {
						Iterator<String> itr = userJson.keySet().iterator();
						while (itr.hasNext()) {
							String key = itr.next();
							if ("fsscUser".equals(key)){
								continue;
							}
							Object obj = userJson.get(key);
							if (obj instanceof String) {
								String[] valueArr = new String[] { userJson.getString(key) };
								m.put(key, valueArr);
							} else if (obj instanceof Integer) {
								String[] valueArr = new String[] { userJson.getString(key) };
								m.put(key, valueArr);
							} else if (obj instanceof Long) {
								String[] valueArr = new String[] { userJson.getString(key) };
								m.put(key, valueArr);
							} else if (obj instanceof Double) {
								String[] valueArr = new String[] { userJson.getString(key) };
								m.put(key, valueArr);
							} else if (obj instanceof List) {
								JSONArray jsonArr = JSONArray.parseArray(JSONArray.toJSONString(obj));
								for (int i = 0; i < jsonArr.size(); i++) {
									JSONObject jsonObject = jsonArr.getJSONObject(i);
									Iterator<String> itr2 = jsonObject.keySet().iterator();

									while (itr2.hasNext()) {
										String key2 = itr2.next();
										Object obj2 = jsonObject.get(key2);
										String[] valueArr = new String[] { jsonObject.getString(key2) };
										m.put(key + "[" + i + "]." + key2, valueArr);
									}
								}
							}
						}
					}

					if (deptJson != null) {
						Iterator<String> itr = deptJson.keySet().iterator();
						while (itr.hasNext()) {
							String key = itr.next();
							if (StringUtils.isNotBlank(key) && key.equals("id")) {
								m.put("deptId", new String[] { deptJson.getString(key) });
							} else if (StringUtils.isNotBlank(key) && key.equals("email")) {
								m.put("deptEmail", new String[] { deptJson.getString(key) });
							} else if (StringUtils.isNotBlank(key) && key.equals("phone")) {
								m.put("deptPhone", new String[] { deptJson.getString(key) });
							} else {
								m.put(key, new String[] { deptJson.getString(key) });
							}
						}
					}
					request = new ParameterRequestWrapper((HttpServletRequest) request, m);
				}
			}
			filterChain.doFilter(request, servletResponse);
		} else {
			boolean notFilterFlag = false;
			for (String notFilterUri : NOT_FILTER_URI_ARRAY) {
				if (url.contains(notFilterUri)) {
					notFilterFlag = true;
				}
			}
			if (notFilterFlag) {
				filterChain.doFilter(request, servletResponse);
			} else {
				responseOutWithJson(response);
				return;
			}
		}
	}

	@Override
	public void destroy() {

	}

	protected void responseOutWithJson(HttpServletResponse response) {
		// 将实体对象转换为JSON Object转换
		JSONObject responseJSONObject = new JSONObject();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		try {
			responseJSONObject.put("code", "9999");
			// responseJSONObject.put("url", "http://124.17.100.183:30209/login.html");
			responseJSONObject.put("url", "http://124.17.100.183:31080/srpmp/index.html#/");
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