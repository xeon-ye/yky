package com.deloitte.platform.api.oaservice.fastway.feign;


import com.deloitte.platform.api.oaservice.fastway.client.FastWayClient;
import com.deloitte.platform.api.oaservice.fastway.param.FastWayQueryForm;
import com.deloitte.platform.api.oaservice.fastway.vo.FastWayForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @Author : yidaojun
 * @Date : Create in 2019-05-17
 * @Description :   FastWay feign客户端
 * @Modified :
 */
@FeignClient(name = "fastWay-service", fallbackFactory = FastWayFeignService.HystrixFastWayService.class, primary = false)
public interface FastWayFeignService extends FastWayClient {

    @Component
    @Slf4j
    class HystrixFastWayService implements FallbackFactory<FastWayFeignService> {

        @Override
        public FastWayFeignService create(Throwable throwable) {
            return new FastWayFeignService() {
                @Override
                public Result add(@Valid @RequestBody FastWayForm fastWayForm) {
                    log.error("FastWayService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("FastWayService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody FastWayForm fastWayForm) {
                    log.error("FastWayService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("FastWayService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody FastWayQueryForm fastWayQueryForm) {
                    log.error("FastWayService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody FastWayQueryForm fastWayQueryForm) {
                    log.error("FastWayService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE, throwable.getMessage());
                }
            };
        }
    }
}