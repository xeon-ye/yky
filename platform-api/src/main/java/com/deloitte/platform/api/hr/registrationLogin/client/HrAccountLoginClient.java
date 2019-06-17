package com.deloitte.platform.api.hr.registrationLogin.client;

import com.deloitte.platform.api.hr.registrationLogin.param.HrAccountLoginQueryForm;
import com.deloitte.platform.api.hr.registrationLogin.vo.HrAccountLoginForm;
import com.deloitte.platform.api.hr.registrationLogin.vo.HrAccountLoginVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zengshuai
 * @Date : Create in 2019-04-19
 * @Description :  HrAccount控制器接口
 * @Modified :
 */
public interface HrAccountLoginClient {

    public static final String path="/hr/registrationLogin/hr-account";


    /**
     *  POST---新增
     * @param hrAccountLoginForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute HrAccountLoginForm hrAccountLoginForm);

    /**
     *  Delete---删除
     * @param  id
     * @return
     */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, hrAccountLoginForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody HrAccountLoginForm hrAccountLoginForm);

    /**
     *  GET----根据ID获取
     * @param  id
     * @return
     */
    @GetMapping(value = path+"/{id}")
    Result<HrAccountLoginVo> get(@PathVariable(value="id") long id);

    /**
     * GET----根据NAME获取
     * @param hrAccountLoginForm
     * @return
     */
    @PostMapping(value = path+"/getByName")
    Result<HrAccountLoginVo> getByName(@Valid @RequestBody HrAccountLoginForm hrAccountLoginForm );

    /**
     *  POST----列表查询
     * @param   hrAccountLoginQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<HrAccountLoginVo>> list(@Valid @RequestBody HrAccountLoginQueryForm hrAccountLoginQueryForm);


    /**
     *  POST----复杂查询
     * @param  hrAccountLoginQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<HrAccountLoginVo>> search(@Valid @RequestBody HrAccountLoginQueryForm hrAccountLoginQueryForm);

    /**
     *  POST---用户注册
     * @param hrAccountLoginForm
     * @return
     */
    @PostMapping(value = path+"/account/registry")
    Result registry(@Valid @ModelAttribute HrAccountLoginForm hrAccountLoginForm);

    /**
     * GET----用户登录
     * @param  hrAccountLoginQueryForm
     * @return
     */
    @PostMapping(value = path+"/account/login")
    Result login(@Valid @RequestBody HrAccountLoginQueryForm hrAccountLoginQueryForm);


    /**
     *  POST----忘记密码
     * @param   hrAccountLoginForm
     * @return
     */
    @PostMapping(value = path+"/account/forgetPassword")
    Result forgetPassword(@Valid @ModelAttribute HrAccountLoginForm hrAccountLoginForm);

    /**
     * POST----生成验证码
     * @param req
     * @param resp
     * @return
     */
    @GetMapping(value = path+"/account/getVerificationCode")
    Result getVerificationCode(HttpServletRequest req, HttpServletResponse resp,String randomCode) ;
}
