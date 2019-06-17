package com.deloitte.platform.api.contract.feign;


import com.deloitte.platform.api.contract.client.BasicProjectMapClient;
import com.deloitte.platform.api.contract.param.BasicProjectMapQueryForm;
import com.deloitte.platform.api.contract.vo.BasicProjectMapForm;
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
 * @Description :   BasicProjectMap feign客户端
 * @Modified :
 */
@FeignClient(name = "basicProjectMap-service", fallbackFactory = BasicProjectMapFeignService.HystrixBasicProjectMapService.class,primary = false)
public interface BasicProjectMapFeignService extends BasicProjectMapClient {

    @Component
    @Slf4j
    class HystrixBasicProjectMapService implements FallbackFactory<BasicProjectMapFeignService> {

        @Override
        public BasicProjectMapFeignService create(Throwable throwable) {
            return new BasicProjectMapFeignService() {
                @Override
                public Result add(@Valid @RequestBody BasicProjectMapForm basicProjectMapForm) {
                    log.error("BasicProjectMapService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("BasicProjectMapService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody BasicProjectMapForm basicProjectMapForm) {
                    log.error("BasicProjectMapService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("BasicProjectMapService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody BasicProjectMapQueryForm basicProjectMapQueryForm) {
                    log.error("BasicProjectMapService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody BasicProjectMapQueryForm basicProjectMapQueryForm) {
                    log.error("BasicProjectMapService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}