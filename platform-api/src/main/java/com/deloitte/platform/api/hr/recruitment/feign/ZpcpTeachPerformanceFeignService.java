package com.deloitte.platform.api.hr.recruitment.feign;


import com.deloitte.platform.api.hr.recruitment.client.ZpcpTeachPerformanceClient;
import com.deloitte.platform.api.hr.recruitment.param.ZpcpTeachPerformanceQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.DeleteForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpTeachPerformanceForm;
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
 * @Description :   ZpcpTeachPerformance feign客户端
 * @Modified :
 */
@FeignClient(name = "zpcpTeachPerformance-service", fallbackFactory = ZpcpTeachPerformanceFeignService.HystrixZpcpTeachPerformanceService.class,primary = false)
public interface ZpcpTeachPerformanceFeignService extends ZpcpTeachPerformanceClient {

    @Component
    @Slf4j
    class HystrixZpcpTeachPerformanceService implements FallbackFactory<ZpcpTeachPerformanceFeignService> {

        @Override
        public ZpcpTeachPerformanceFeignService create(Throwable throwable) {
            return new ZpcpTeachPerformanceFeignService() {
                @Override
                public Result add(@Valid @RequestBody ZpcpTeachPerformanceForm zpcpTeachPerformanceForm) {
                    log.error("ZpcpTeachPerformanceService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ZpcpTeachPerformanceService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ZpcpTeachPerformanceForm zpcpTeachPerformanceForm) {
                    log.error("ZpcpTeachPerformanceService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ZpcpTeachPerformanceService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ZpcpTeachPerformanceQueryForm zpcpTeachPerformanceQueryForm) {
                    log.error("ZpcpTeachPerformanceService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ZpcpTeachPerformanceQueryForm zpcpTeachPerformanceQueryForm) {
                    log.error("ZpcpTeachPerformanceService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result addOrUpdateList(List<ZpcpTeachPerformanceForm> teachPerformanceForms) {
                    log.error("ZpcpTeachPerformanceService addOrUpdateList服务不可用......");
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