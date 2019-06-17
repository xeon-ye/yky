package com.deloitte.platform.api.fssc.performance.feign;


import com.deloitte.platform.api.fssc.performance.client.PerformanceIndexValueClient;
import com.deloitte.platform.api.fssc.performance.param.PerformanceIndexValueQueryForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformanceIndexValueForm;
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
 * @Description :   PerformanceIndex feign客户端
 * @Modified :
 */
@FeignClient(name = "service-fssc", fallbackFactory = PerformanceIndexValueFeignService.HystrixPerformanceIndexService.class, primary = false)
public interface PerformanceIndexValueFeignService extends PerformanceIndexValueClient {

    @Component
    @Slf4j
    class HystrixPerformanceIndexService implements
            FallbackFactory<PerformanceIndexValueFeignService> {

        @Override
        public PerformanceIndexValueFeignService create(Throwable throwable) {
            return new PerformanceIndexValueFeignService() {
                @Override
                public Result addOrUpdateIndexValue(
                        @Valid @RequestBody List<PerformanceIndexValueForm> performanceIndexFormList) {
                    log.error("PerformanceIndexService addOrUpdateIndex......");
                    throwable.printStackTrace();
                    return Result
                            .fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result delete(@RequestBody List<Long> ids) {
                    log.error("PerformanceIndexService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result
                            .fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("PerformanceIndexService get服务不可用......");
                    throwable.printStackTrace();
                    return Result
                            .fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result list(
                        @Valid @RequestBody PerformanceIndexValueQueryForm queryForm) {
                    log.error("PerformanceIndexService list服务不可用......");
                    throwable.printStackTrace();
                    return Result
                            .fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result search(
                        @Valid @RequestBody PerformanceIndexValueQueryForm queryForm) {
                    log.error("PerformanceIndexService search服务不可用......");
                    throwable.printStackTrace();
                    return Result
                            .fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }
            };
        }
    }
}