package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.ExePerformanceClient;
import com.deloitte.platform.api.project.param.ExePerformanceQueryForm;
import com.deloitte.platform.api.project.vo.ExePerformanceForm;
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
 * @Date : Create in 2019-05-20
 * @Description :   ExePerformance feign客户端
 * @Modified :
 */
@FeignClient(name = "exePerformance-service", fallbackFactory = ExePerformanceFeignService.HystrixExePerformanceService.class,primary = false)
public interface ExePerformanceFeignService extends ExePerformanceClient {

    @Component
    @Slf4j
    class HystrixExePerformanceService implements FallbackFactory<ExePerformanceFeignService> {

        @Override
        public ExePerformanceFeignService create(Throwable throwable) {
            return new ExePerformanceFeignService() {
                @Override
                public Result add(@Valid @RequestBody ExePerformanceForm exePerformanceForm) {
                    log.error("ExePerformanceService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ExePerformanceService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ExePerformanceForm exePerformanceForm) {
                    log.error("ExePerformanceService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ExePerformanceService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ExePerformanceQueryForm exePerformanceQueryForm) {
                    log.error("ExePerformanceService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ExePerformanceQueryForm exePerformanceQueryForm) {
                    log.error("ExePerformanceService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}