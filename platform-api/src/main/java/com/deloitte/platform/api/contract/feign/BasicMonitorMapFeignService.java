package com.deloitte.platform.api.contract.feign;


import com.deloitte.platform.api.contract.client.BasicMonitorMapClient;
import com.deloitte.platform.api.contract.param.BasicMonitorMapQueryForm;
import com.deloitte.platform.api.contract.vo.BasicMonitorMapForm;
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
 * @Date : Create in 2019-03-26
 * @Description :   BasicMonitorMap feign客户端
 * @Modified :
 */
@FeignClient(name = "basicMonitorMap-service", fallbackFactory = BasicMonitorMapFeignService.HystrixBasicMonitorMapService.class,primary = false)
public interface BasicMonitorMapFeignService extends BasicMonitorMapClient {

    @Component
    @Slf4j
    class HystrixBasicMonitorMapService implements FallbackFactory<BasicMonitorMapFeignService> {

        @Override
        public BasicMonitorMapFeignService create(Throwable throwable) {
            return new BasicMonitorMapFeignService() {
                @Override
                public Result add(@Valid @RequestBody BasicMonitorMapForm basicMonitorMapForm) {
                    log.error("BasicMonitorMapService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("BasicMonitorMapService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody BasicMonitorMapForm basicMonitorMapForm) {
                    log.error("BasicMonitorMapService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("BasicMonitorMapService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody BasicMonitorMapQueryForm basicMonitorMapQueryForm) {
                    log.error("BasicMonitorMapService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody BasicMonitorMapQueryForm basicMonitorMapQueryForm) {
                    log.error("BasicMonitorMapService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}