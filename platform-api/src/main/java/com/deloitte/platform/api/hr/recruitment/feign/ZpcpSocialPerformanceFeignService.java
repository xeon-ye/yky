package com.deloitte.platform.api.hr.recruitment.feign;


import com.deloitte.platform.api.hr.recruitment.client.ZpcpSocialPerformanceClient;
import com.deloitte.platform.api.hr.recruitment.param.ZpcpSocialPerformanceQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.DeleteForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpSocialPerformanceForm;
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
 * @Author : tankui
 * @Date : Create in 2019-04-23
 * @Description :   ZpcpSocialPerformance feign客户端
 * @Modified :
 */
@FeignClient(name = "zpcpSocialPerformance-service", fallbackFactory = ZpcpSocialPerformanceFeignService.HystrixZpcpSocialPerformanceService.class,primary = false)
public interface ZpcpSocialPerformanceFeignService extends ZpcpSocialPerformanceClient {

    @Component
    @Slf4j
    class HystrixZpcpSocialPerformanceService implements FallbackFactory<ZpcpSocialPerformanceFeignService> {

        @Override
        public ZpcpSocialPerformanceFeignService create(Throwable throwable) {
            return new ZpcpSocialPerformanceFeignService() {
                @Override
                public Result add(@Valid @RequestBody ZpcpSocialPerformanceForm zpcpSocialPerformanceForm) {
                    log.error("ZpcpSocialPerformanceService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ZpcpSocialPerformanceService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ZpcpSocialPerformanceForm zpcpSocialPerformanceForm) {
                    log.error("ZpcpSocialPerformanceService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ZpcpSocialPerformanceService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ZpcpSocialPerformanceQueryForm zpcpSocialPerformanceQueryForm) {
                    log.error("ZpcpSocialPerformanceService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ZpcpSocialPerformanceQueryForm zpcpSocialPerformanceQueryForm) {
                    log.error("ZpcpSocialPerformanceService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result addOrUpdateList(List<ZpcpSocialPerformanceForm> socialPerformanceForms) {
                    log.error("ZpcpSocialPerformanceService addOrUpdateList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result deleteList(DeleteForm deleteForm) {
                    log.error("ZpcpDrugDeviceService deleteList服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}