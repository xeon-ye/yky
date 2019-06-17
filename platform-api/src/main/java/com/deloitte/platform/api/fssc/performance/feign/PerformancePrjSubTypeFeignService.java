package com.deloitte.platform.api.fssc.performance.feign;


import com.deloitte.platform.api.fssc.performance.client.PerformancePrjSubTypeClient;
import com.deloitte.platform.api.fssc.performance.param.PerformancePrjSubTypeQueryForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformancePrjSubTypeForm;
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
 * @Description :   PerformancePrjSubType feign客户端
 * @Modified :
 */
@FeignClient(name = "service-fssc", fallbackFactory = PerformancePrjSubTypeFeignService.HystrixPerformancePrjSubTypeService.class, primary = false)
public interface PerformancePrjSubTypeFeignService extends PerformancePrjSubTypeClient {

    @Component
    @Slf4j
    class HystrixPerformancePrjSubTypeService implements
            FallbackFactory<PerformancePrjSubTypeFeignService> {

        @Override
        public PerformancePrjSubTypeFeignService create(Throwable throwable) {
            return new PerformancePrjSubTypeFeignService() {
                @Override
                public Result addOrUpdateSubType(
                        @Valid @RequestBody List<PerformancePrjSubTypeForm> subTypeFormList) {
                    log.error("PerformancePrjSubTypeService addOrUpdateSubType服务不可用......");
                    throwable.printStackTrace();
                    return Result
                            .fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result delete(@RequestBody List<Long> ids) {
                    log.error("PerformancePrjSubTypeService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result
                            .fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }


                @Override
                public Result get(@PathVariable long id) {
                    log.error("PerformancePrjSubTypeService get服务不可用......");
                    throwable.printStackTrace();
                    return Result
                            .fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result list(
                        @Valid @RequestBody PerformancePrjSubTypeQueryForm performancePrjSubTypeQueryForm) {
                    log.error("PerformancePrjSubTypeService list服务不可用......");
                    throwable.printStackTrace();
                    return Result
                            .fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result search(
                        @Valid @RequestBody PerformancePrjSubTypeQueryForm performancePrjSubTypeQueryForm) {
                    log.error("PerformancePrjSubTypeService search服务不可用......");
                    throwable.printStackTrace();
                    return Result
                            .fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }
            };
        }
    }
}