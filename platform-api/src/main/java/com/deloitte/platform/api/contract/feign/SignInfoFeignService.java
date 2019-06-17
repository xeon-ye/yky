package com.deloitte.platform.api.contract.feign;


import com.deloitte.platform.api.contract.client.SignInfoClient;
import com.deloitte.platform.api.contract.param.SignInfoQueryForm;
import com.deloitte.platform.api.contract.vo.SignInfoForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :   SignInfo feign客户端
 * @Modified :
 */
@FeignClient(name = "signInfo-service", fallbackFactory = SignInfoFeignService.HystrixSignInfoService.class,primary = false)
public interface SignInfoFeignService extends SignInfoClient {

    @Component
    @Slf4j
    class HystrixSignInfoService implements FallbackFactory<SignInfoFeignService> {

        @Override
        public SignInfoFeignService create(Throwable throwable) {
            return new SignInfoFeignService() {
                @Override
                public Result add(@Valid @RequestBody SignInfoForm signInfoForm) {
                    log.error("SignInfoService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("SignInfoService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody SignInfoForm signInfoForm) {
                    log.error("SignInfoService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("SignInfoService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody SignInfoQueryForm signInfoQueryForm) {
                    log.error("SignInfoService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody SignInfoQueryForm signInfoQueryForm) {
                    log.error("SignInfoService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}