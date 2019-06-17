package com.deloitte.platform.api.contract.feign;


import com.deloitte.platform.api.contract.client.SetupInfoClient;
import com.deloitte.platform.api.contract.param.SetupInfoQueryForm;
import com.deloitte.platform.api.contract.vo.SetupInfoForm;
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
 * @Author : hemingzheng
 * @Date : Create in 2019-04-13
 * @Description :   SetupInfo feign客户端
 * @Modified :
 */
@FeignClient(name = "setupInfo-service", fallbackFactory = SetupInfoFeignService.HystrixSetupInfoService.class,primary = false)
public interface SetupInfoFeignService extends SetupInfoClient {

    @Component
    @Slf4j
    class HystrixSetupInfoService implements FallbackFactory<SetupInfoFeignService> {

        @Override
        public SetupInfoFeignService create(Throwable throwable) {
            return new SetupInfoFeignService() {
                @Override
                public Result add(@Valid @RequestBody SetupInfoForm setupInfoForm) {
                    log.error("SetupInfoService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("SetupInfoService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody SetupInfoForm setupInfoForm) {
                    log.error("SetupInfoService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("SetupInfoService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody SetupInfoQueryForm setupInfoQueryForm) {
                    log.error("SetupInfoService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody SetupInfoQueryForm setupInfoQueryForm) {
                    log.error("SetupInfoService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}