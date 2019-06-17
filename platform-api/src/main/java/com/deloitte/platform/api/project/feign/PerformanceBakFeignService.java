package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.PerformanceBakClient;
import com.deloitte.platform.api.project.param.PerformanceBakQueryForm;
import com.deloitte.platform.api.project.vo.PerformanceBakForm;
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
 * @Date : Create in 2019-04-24
 * @Description :   PerformanceBak feign客户端
 * @Modified :
 */
@FeignClient(name = "performanceBak-service", fallbackFactory = PerformanceBakFeignService.HystrixPerformanceBakService.class,primary = false)
public interface PerformanceBakFeignService extends PerformanceBakClient {

    @Component
    @Slf4j
    class HystrixPerformanceBakService implements FallbackFactory<PerformanceBakFeignService> {

        @Override
        public PerformanceBakFeignService create(Throwable throwable) {
            return new PerformanceBakFeignService() {
                @Override
                public Result add(@Valid @RequestBody PerformanceBakForm performanceBakForm) {
                    log.error("PerformanceBakService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("PerformanceBakService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody PerformanceBakForm performanceBakForm) {
                    log.error("PerformanceBakService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("PerformanceBakService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody PerformanceBakQueryForm performanceBakQueryForm) {
                    log.error("PerformanceBakService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody PerformanceBakQueryForm performanceBakQueryForm) {
                    log.error("PerformanceBakService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}