package com.deloitte.platform.api.contract.feign;


import com.deloitte.platform.api.contract.client.ExecuteWaringClient;
import com.deloitte.platform.api.contract.param.ExecuteWaringQueryForm;
import com.deloitte.platform.api.contract.vo.ExecuteWaringForm;
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
 * @Author : yangyq
 * @Date : Create in 2019-04-28
 * @Description :   ExecuteWaring feign客户端
 * @Modified :
 */
@FeignClient(name = "executeWaring-service", fallbackFactory = ExecuteWaringFeignService.HystrixExecuteWaringService.class,primary = false)
public interface ExecuteWaringFeignService extends ExecuteWaringClient {

    @Component
    @Slf4j
    class HystrixExecuteWaringService implements FallbackFactory<ExecuteWaringFeignService> {

        @Override
        public ExecuteWaringFeignService create(Throwable throwable) {
            return new ExecuteWaringFeignService() {
                @Override
                public Result add(@Valid @RequestBody ExecuteWaringForm executeWaringForm) {
                    log.error("ExecuteWaringService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ExecuteWaringService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ExecuteWaringForm executeWaringForm) {
                    log.error("ExecuteWaringService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ExecuteWaringService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ExecuteWaringQueryForm executeWaringQueryForm) {
                    log.error("ExecuteWaringService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ExecuteWaringQueryForm executeWaringQueryForm) {
                    log.error("ExecuteWaringService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}