package com.deloitte.platform.api.contract.feign;


import com.deloitte.platform.api.contract.client.MonitorInfoClient;
import com.deloitte.platform.api.contract.param.MonitorInfoQueryForm;
import com.deloitte.platform.api.contract.vo.MonitorInfoForm;
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
 * @Author : yangyq
 * @Date : Create in 2019-04-20
 * @Description :   MonitorInfo feign客户端
 * @Modified :
 */
@FeignClient(name = "monitorInfo-service", fallbackFactory = MonitorInfoFeignService.HystrixMonitorInfoService.class,primary = false)
public interface MonitorInfoFeignService extends MonitorInfoClient {

    @Component
    @Slf4j
    class HystrixMonitorInfoService implements FallbackFactory<MonitorInfoFeignService> {

        @Override
        public MonitorInfoFeignService create(Throwable throwable) {
            return new MonitorInfoFeignService() {
                @Override
                public Result add(@Valid @RequestBody MonitorInfoForm monitorInfoForm) {
                    log.error("MonitorInfoService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("MonitorInfoService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody MonitorInfoForm monitorInfoForm) {
                    log.error("MonitorInfoService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("MonitorInfoService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody MonitorInfoQueryForm monitorInfoQueryForm) {
                    log.error("MonitorInfoService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody MonitorInfoQueryForm monitorInfoQueryForm) {
                    log.error("MonitorInfoService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}