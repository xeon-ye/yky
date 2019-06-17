package com.deloitte.platform.api.fssc.performance.feign;


import com.deloitte.platform.api.fssc.performance.client.PerformanceIndexLibraryClient;
import com.deloitte.platform.api.fssc.performance.param.PerformanceIndexLibraryQueryForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformanceIndexLibraryForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-03
 * @Description :   PerformanceIndexLibrary feign客户端
 * @Modified :
 */
@FeignClient(name = "service-fssc", fallbackFactory = PerformanceIndexLibraryFeignService.HystrixPerformanceIndexLibraryService.class, primary = false)
public interface PerformanceIndexLibraryFeignService extends PerformanceIndexLibraryClient {

    @Component
    @Slf4j
    class HystrixPerformanceIndexLibraryService implements
            FallbackFactory<PerformanceIndexLibraryFeignService> {

        @Override
        public PerformanceIndexLibraryFeignService create(Throwable throwable) {
            return new PerformanceIndexLibraryFeignService() {
                @Override
                public Result addOrUpdateIndexLibrary(
                        @Valid @RequestBody List<PerformanceIndexLibraryForm> indexLibraryFormList) {
                    log.error("PerformanceIndexLibraryService add服务不可用......");
                    throwable.printStackTrace();
                    return Result
                            .fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result delete(@RequestBody List<Long> ids) {
                    log.error("PerformanceIndexLibraryService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result
                            .fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("PerformanceIndexLibraryService get服务不可用......");
                    throwable.printStackTrace();
                    return Result
                            .fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result list(
                        @Valid @RequestBody PerformanceIndexLibraryQueryForm queryForm) {
                    log.error("PerformanceIndexLibraryService list服务不可用......");
                    throwable.printStackTrace();
                    return Result
                            .fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result search(
                        @Valid @RequestBody PerformanceIndexLibraryQueryForm queryForm) {
                    log.error("PerformanceIndexLibraryService search服务不可用......");
                    throwable.printStackTrace();
                    return Result
                            .fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }
            };
        }
    }
}