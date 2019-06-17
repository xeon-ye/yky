package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.ServiceNumClient;
import com.deloitte.platform.api.project.param.ServiceNumQueryForm;
import com.deloitte.platform.api.project.vo.ServiceNumForm;
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
 * @Description :   ServiceNum feign客户端
 * @Modified :
 */
@FeignClient(name = "serviceNum-service", fallbackFactory = ServiceNumFeignService.HystrixServiceNumService.class,primary = false)
public interface ServiceNumFeignService extends ServiceNumClient {

    @Component
    @Slf4j
    class HystrixServiceNumService implements FallbackFactory<ServiceNumFeignService> {

        @Override
        public ServiceNumFeignService create(Throwable throwable) {
            return new ServiceNumFeignService() {
                @Override
                public Result add(@Valid @RequestBody ServiceNumForm serviceNumForm) {
                    log.error("ServiceNumService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ServiceNumService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ServiceNumForm serviceNumForm) {
                    log.error("ServiceNumService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ServiceNumService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ServiceNumQueryForm serviceNumQueryForm) {
                    log.error("ServiceNumService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ServiceNumQueryForm serviceNumQueryForm) {
                    log.error("ServiceNumService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}