package com.deloitte.platform.api.fssc.performance.feign;


import com.deloitte.platform.api.fssc.performance.client.PerformanceIndexClient;
import com.deloitte.platform.api.fssc.performance.param.PerformanceIndexQueryForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformanceIndexForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformanceIndexVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-08
 * @Description :   PerformanceIndex feign客户端
 * @Modified :
 */
@FeignClient(name = "service-fssc", fallbackFactory = PerformanceIndexFeignService.HystrixPerformanceIndexService.class,primary = false)
public interface PerformanceIndexFeignService extends PerformanceIndexClient {

    @Component
    @Slf4j
    class HystrixPerformanceIndexService implements FallbackFactory<PerformanceIndexFeignService> {

        @Override
        public PerformanceIndexFeignService create(Throwable throwable) {
            return new PerformanceIndexFeignService() {
                @Override
                public Result addOrUpdateIndex(@Valid @RequestBody List<PerformanceIndexForm> indexFormList) {
                    log.error("PerformanceIndexService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(List<Long> ids) {
                    log.error("PerformanceIndexService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("PerformanceIndexService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody PerformanceIndexQueryForm performanceIndexQueryForm) {
                    log.error("PerformanceIndexService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody PerformanceIndexQueryForm performanceIndexQueryForm) {
                    log.error("PerformanceIndexService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result<List<PerformanceIndexVo>> search(Long libraryId) {
                    log.error("PerformanceIndexService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}