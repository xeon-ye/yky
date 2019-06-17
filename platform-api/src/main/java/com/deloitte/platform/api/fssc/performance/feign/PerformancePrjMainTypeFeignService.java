package com.deloitte.platform.api.fssc.performance.feign;


import com.deloitte.platform.api.fssc.performance.client.PerformancePrjMainTypeClient;
import com.deloitte.platform.api.fssc.performance.param.PerformancePrjMainTypeQueryForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformancePrjMainTypeForm;
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
 * @Description :   PerformancePrjMainType feign客户端
 * @Modified :
 */
@FeignClient(name = "service-fssc", fallbackFactory = PerformancePrjMainTypeFeignService.HystrixPerformancePrjMainTypeService.class, primary = false)
public interface PerformancePrjMainTypeFeignService extends PerformancePrjMainTypeClient {

    @Component
    @Slf4j
    class HystrixPerformancePrjMainTypeService implements
            FallbackFactory<PerformancePrjMainTypeFeignService> {

        @Override
        public PerformancePrjMainTypeFeignService create(Throwable throwable) {
            return new PerformancePrjMainTypeFeignService() {
                @Override
                public Result addOrUpdateMainType(
                        @Valid @RequestBody List<PerformancePrjMainTypeForm> mainTypeFormList) {
                    log.error("PerformancePrjMainTypeService addOrUpdateMainType服务不可用......");
                    throwable.printStackTrace();
                    return Result
                            .fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result delete(@RequestBody List<Long> ids) {
                    log.error("PerformancePrjMainTypeService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result
                            .fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("PerformancePrjMainTypeService get服务不可用......");
                    throwable.printStackTrace();
                    return Result
                            .fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result list(
                        @Valid @RequestBody PerformancePrjMainTypeQueryForm performancePrjMainTypeQueryForm) {
                    log.error("PerformancePrjMainTypeService list服务不可用......");
                    throwable.printStackTrace();
                    return Result
                            .fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result search(
                        @Valid @RequestBody PerformancePrjMainTypeQueryForm performancePrjMainTypeQueryForm) {
                    log.error("PerformancePrjMainTypeService search服务不可用......");
                    throwable.printStackTrace();
                    return Result
                            .fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }
            };
        }
    }
}