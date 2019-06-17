package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.ExecutionClient;
import com.deloitte.platform.api.project.param.ExecutionQueryForm;
import com.deloitte.platform.api.project.vo.ExecutionForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description :   Execution feign客户端
 * @Modified :
 */
@FeignClient(name = "execution-service", fallbackFactory = ExecutionFeignService.HystrixExecutionService.class,primary = false)
public interface ExecutionFeignService extends ExecutionClient {

    @Component
    @Slf4j
    class HystrixExecutionService implements FallbackFactory<ExecutionFeignService> {

        @Override
        public ExecutionFeignService create(Throwable throwable) {
            return new ExecutionFeignService() {
                @Override
                public Result add(@Valid @RequestBody ExecutionForm executionForm) {
                    log.error("ExecutionService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ExecutionService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ExecutionForm executionForm) {
                    log.error("ExecutionService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ExecutionService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ExecutionQueryForm executionQueryForm) {
                    log.error("ExecutionService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ExecutionQueryForm executionQueryForm) {
                    log.error("ExecutionService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}