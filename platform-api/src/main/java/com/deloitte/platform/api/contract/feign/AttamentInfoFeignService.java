package com.deloitte.platform.api.contract.feign;


import com.deloitte.platform.api.contract.client.AttamentInfoClient;
import com.deloitte.platform.api.contract.param.AttamentInfoQueryForm;
import com.deloitte.platform.api.contract.vo.AttamentInfoForm;
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
 * @Description :   AttamentInfo feign客户端
 * @Modified :
 */
@FeignClient(name = "attamentInfo-service", fallbackFactory = AttamentInfoFeignService.HystrixAttamentInfoService.class,primary = false)
public interface AttamentInfoFeignService extends AttamentInfoClient {

    @Component
    @Slf4j
    class HystrixAttamentInfoService implements FallbackFactory<AttamentInfoFeignService> {

        @Override
        public AttamentInfoFeignService create(Throwable throwable) {
            return new AttamentInfoFeignService() {
                @Override
                public Result add(@Valid @RequestBody AttamentInfoForm attamentInfoForm) {
                    log.error("AttamentInfoService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("AttamentInfoService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody AttamentInfoForm attamentInfoForm) {
                    log.error("AttamentInfoService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("AttamentInfoService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody AttamentInfoQueryForm attamentInfoQueryForm) {
                    log.error("AttamentInfoService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody AttamentInfoQueryForm attamentInfoQueryForm) {
                    log.error("AttamentInfoService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}