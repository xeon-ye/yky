package com.deloitte.platform.api.contract.feign;


import com.deloitte.platform.api.contract.client.ProcessClient;
import com.deloitte.platform.api.contract.param.ProcessQueryForm;
import com.deloitte.platform.api.contract.vo.ProcessForm;
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
 * @Date : Create in 2019-03-30
 * @Description :   Process feign客户端
 * @Modified :
 */
@FeignClient(name = "process-service", fallbackFactory = ProcessFeignService.HystrixProcessService.class,primary = false)
public interface ProcessFeignService extends ProcessClient {

    @Component
    @Slf4j
    class HystrixProcessService implements FallbackFactory<ProcessFeignService> {

        @Override
        public ProcessFeignService create(Throwable throwable) {
            return new ProcessFeignService() {
                @Override
                public Result add(@Valid @RequestBody ProcessForm processForm) {
                    log.error("ProcessService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ProcessService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ProcessForm processForm) {
                    log.error("ProcessService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ProcessService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ProcessQueryForm processQueryForm) {
                    log.error("ProcessService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ProcessQueryForm processQueryForm) {
                    log.error("ProcessService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}