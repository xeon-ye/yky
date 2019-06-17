package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.ValueClient;
import com.deloitte.platform.api.project.param.ValueQueryForm;
import com.deloitte.platform.api.project.vo.ValueForm;
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
 * @Date : Create in 2019-05-23
 * @Description :   Value feign客户端
 * @Modified :
 */
@FeignClient(name = "value-service", fallbackFactory = ValueFeignService.HystrixValueService.class,primary = false)
public interface ValueFeignService extends ValueClient {

    @Component
    @Slf4j
    class HystrixValueService implements FallbackFactory<ValueFeignService> {

        @Override
        public ValueFeignService create(Throwable throwable) {
            return new ValueFeignService() {
                @Override
                public Result add(@Valid @RequestBody ValueForm valueForm) {
                    log.error("ValueService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ValueService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ValueForm valueForm) {
                    log.error("ValueService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ValueService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ValueQueryForm valueQueryForm) {
                    log.error("ValueService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ValueQueryForm valueQueryForm) {
                    log.error("ValueService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}