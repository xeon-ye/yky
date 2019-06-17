package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.PerformanceClient;
import com.deloitte.platform.api.project.param.PerformanceQueryForm;
import com.deloitte.platform.api.project.vo.PerformanceForm;
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
 * @Description :   Performance feign客户端
 * @Modified :
 */
@FeignClient(name = "performance-service", fallbackFactory = PerformanceFeignService.HystrixPerformanceService.class,primary = false)
public interface PerformanceFeignService extends PerformanceClient {

    @Component
    @Slf4j
    class HystrixPerformanceService implements FallbackFactory<PerformanceFeignService> {

        @Override
        public PerformanceFeignService create(Throwable throwable) {
            return new PerformanceFeignService() {
                @Override
                public Result add(@Valid @RequestBody PerformanceForm performanceForm) {
                    log.error("PerformanceService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("PerformanceService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody PerformanceForm performanceForm) {
                    log.error("PerformanceService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("PerformanceService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody PerformanceQueryForm performanceQueryForm) {
                    log.error("PerformanceService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody PerformanceQueryForm performanceQueryForm) {
                    log.error("PerformanceService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}