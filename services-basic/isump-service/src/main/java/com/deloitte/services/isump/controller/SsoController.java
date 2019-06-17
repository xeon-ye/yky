package com.deloitte.services.isump.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.platform.api.isump.param.UserQueryParam;
import com.deloitte.platform.api.push.feign.SendMssFeignService;
import com.deloitte.platform.api.utils.UserUtil;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.isump.entity.LoginLog;
import com.deloitte.services.isump.entity.User;
import com.deloitte.services.isump.service.ILoginLogService;
import com.deloitte.services.isump.service.IUserService;
import com.deloitte.services.isump.util.CusAccessObjectUtil;
import com.deloitte.services.isump.util.PasswordEncoderUtil;
import com.deloitte.services.isump.util.SmsUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.*;

@RestController
@Slf4j
public class SsoController {
    @Autowired
    IUserService userService;
    @Autowired
    SendMssFeignService sendMssFeignService;
    @Autowired
    private ILoginLogService loginLogService;

    /**
     * 登录
     * @param param
     * @return
     */
    @RequestMapping("isump/login")
    @ResponseBody
    public Result login(@RequestBody UserQueryParam param, HttpServletRequest request) {

        String contentMessage = "，欢迎您使用中国医学科学院北京协和医学院MacRP系统！\n" +
                "温馨提示您，MacRP登陆网址为http://124.17.100.183:31080/portal/index.html，并且支持以下5种系统登陆方式：\n" +
                "1、手机号+动态短信验证码\n" +
                "2、用户名+密码\n" +
                "3、邮箱+密码\n" +
                "4、手机号+密码\n" +
                "5、身份证号+密码\n" +
                "手机号、用户名、邮箱、身份证号信息均可在MacRP系统个人信息功能查看与维护。\n" +
                "谢谢您的使用，祝您生活愉快！";

        if(param == null || StringUtils.isEmpty(param.getUsername())){
            return Result.fail(PlatformErrorType.NO_LOGIN);
        }
        //查询用户
        User user = userService.getByUserName(param.getUsername());
        if(user == null){
            return Result.fail(PlatformErrorType.NO_USER);
        }
        if(!"1".equals(user.getState())){
            return Result.fail(PlatformErrorType.USER_EXCEPTION);
        }
        //短信登录
        if("sms".equals(param.getLoginType())){
            if(!param.getUsername().equals(user.getPhone())){
                return Result.fail(PlatformErrorType.NO_USER);
            }
           boolean b = SmsUtil.verifySms(param.getUsername(),"login",param.getPassword());
            if(b) {
                Map<String, String> data = new HashMap();

                //添加登陆日志
                LoginLog loginLog = new LoginLog();
                loginLog.setLogname(param.getUsername());
                loginLog.setIpAddress(CusAccessObjectUtil.getIpAddress(request));
                loginLogService.save(loginLog);

                //第一次登陆发送欢迎短信
                if(StringUtils.isBlank(user.getFirstLogin()) || "0".equals(user.getFirstLogin())){
                    data.put("firstLog", "0");
                    user.setFirstLogin("1");
                    String reMessage ="";
                    if("男".equals(user.getGender()) || "Y".equals(user.getGender())){
                        reMessage = user.getName() +" 先生";
                    }else{
                        reMessage = user.getName() +" 女士";
                    }
                    reMessage += contentMessage;
                    sendMssFeignService.send(user.getPhone(), reMessage);
                }else{
                    data.put("firstLog", user.getFirstLogin());
                }
                //随机token
                String token = UUID.randomUUID().toString();
                user.setToken(token);
                userService.login(user);

                data.put("token", token);
                return Result.success(data);
            }
        }
        //密码登录
        else if(StringUtils.isNotEmpty(param.getPassword())
                && PasswordEncoderUtil.passwordVerify(param.getPassword(),user.getPassword())){

            Map<String,String> data = new HashMap();

            //添加登陆日志
            LoginLog loginLog = new LoginLog();
            loginLog.setLogname(param.getUsername());
            loginLog.setIpAddress(request.getRemoteAddr());
            loginLogService.save(loginLog);

            //第一次登陆发送欢迎短信
            if(StringUtils.isBlank(user.getFirstLogin()) || "0".equals(user.getFirstLogin())){
                data.put("firstLog", "0");
                user.setFirstLogin("1");
                String reMessage ="";
                if("男".equals(user.getGender()) || "Y".equals(user.getGender())){
                    reMessage = user.getName() +" 先生";
                }else{
                    reMessage = user.getName() +" 女士";
                }
                reMessage += contentMessage;
                sendMssFeignService.send(user.getPhone(), reMessage);
            }else{
                data.put("firstLog", user.getFirstLogin());
            }
            //随机token
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            userService.login(user);

            data.put("token",token);
            return Result.success(data);
        }
        return Result.fail(PlatformErrorType.USER_ERROR);

    }


    /**
     * 登出
     * @param token
     * @return
     */
    @RequestMapping("isump/logout")
    @ResponseBody
    public Result logout(String token) {
        //删除token
        userService.logout(token);
        //清除redis
        UserUtil.releaseToken("",token);
        return Result.success();
    }

    /**
     * 获取用户信息
     * @param principal
     * @return
     */
    @GetMapping("/oauth/user")
    public Principal user(Principal principal) {
        return principal;
    }

    /**
     * 发送短信
     * @param phone
     * @return
     */
    @GetMapping("/isump/send_sms/{phone}")
    public Result sendSms(@PathVariable("phone")String phone){
        log.info("发送短信登录验证码");
        //增加验证手机号码有没有注册过
        validatePhoneNumber(phone);
        //随机验证码
        Random random = new Random();
        //随机数
        String NONCE = String.valueOf(100000 + random.nextInt(899999));
        //发送验证码
        sendMssFeignService.send(phone,"您的登录验证码为（"+NONCE +"），有效时间为10分钟。");
        JSONObject object = new JSONObject();
        //状态码
        object.put("code","200");
        //验证码
        object.put("authCode",NONCE);
        //发送时间
        object.put("sendTime",String.valueOf((new Date()).getTime() / 1000L));
        if(SmsUtil.map == null){
            SmsUtil.map = new HashMap<>();
        }
        SmsUtil.map.put(phone+"login",object);
        //JSONObject object = SmsUtil.sendSms(phone,"login");
        if("200".equals(object.getString("code"))){
            return Result.success();
        }else{
            return Result.fail(PlatformErrorType.SYSTEM_ERROR,"短信验证码发送失败！");
        }

    }

    /**
     * 验证手机号码，如果未注册，返回错误信息
     * @param phone
     */
    private void validatePhoneNumber(String phone) {
        if(StringUtils.isNotEmpty(phone)){
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            User user = new User();
            user.setPhone(phone);
            userQueryWrapper.setEntity(user);
            User userVo = userService.getOne(userQueryWrapper);
            if(userVo == null) {
                throw new BaseException(PlatformErrorType.USER_PHONE_NOT_RESGISTER_ERROR);
            }
        }
    }


}
