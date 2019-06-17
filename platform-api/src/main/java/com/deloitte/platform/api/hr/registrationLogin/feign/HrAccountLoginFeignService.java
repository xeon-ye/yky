package com.deloitte.platform.api.hr.registrationLogin.feign;


import com.deloitte.platform.api.hr.registrationLogin.client.HrAccountLoginClient;
import com.deloitte.platform.api.hr.registrationLogin.param.HrAccountLoginQueryForm;
import com.deloitte.platform.api.hr.registrationLogin.vo.HrAccountLoginForm;
import com.deloitte.platform.api.hr.registrationLogin.vo.HrAccountLoginVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author : zengshuai
 * @Date : Create in 2019-04-19
 * @Description :   HrAccount feign客户端
 * @Modified :
 */
@FeignClient(name = "hrAccount-service", fallbackFactory = HrAccountLoginFeignService.HystrixHrAccountService.class,primary = false)
public interface HrAccountLoginFeignService extends HrAccountLoginClient {

    @Component
    @Slf4j
    class HystrixHrAccountService implements FallbackFactory<HrAccountLoginFeignService> {

        @Override
        public HrAccountLoginFeignService create(Throwable throwable) {
            return new HrAccountLoginFeignService() {
                @Override
                public Result add(@Valid @RequestBody HrAccountLoginForm hrAccountLoginForm) {
                    log.error("HrAccountService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("HrAccountService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody HrAccountLoginForm hrAccountLoginForm) {
                    log.error("HrAccountService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("HrAccountService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<HrAccountLoginVo> getByName(@Valid @RequestBody HrAccountLoginForm hrAccountLoginForm) {
                    log.error("HrAccountService getByName服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody HrAccountLoginQueryForm hrAccountLoginQueryForm) {
                    log.error("HrAccountService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody HrAccountLoginQueryForm hrAccountLoginQueryForm) {
                    log.error("HrAccountService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result registry(HrAccountLoginForm hrAccountLoginForm) {
                    log.error("HrAccountService registry服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result login(HrAccountLoginQueryForm hrAccountLoginQueryForm) {
                    log.error("HrAccountService login服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result forgetPassword(HrAccountLoginForm hrAccountLoginForm) {
                    log.error("HrAccountService forgetPassword服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result getVerificationCode(HttpServletRequest req, HttpServletResponse resp,String randomCode) {
                    log.error("HrAccountService getVerificationCode服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}