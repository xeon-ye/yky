package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.ActBakClient;
import com.deloitte.platform.api.project.param.ActBakQueryForm;
import com.deloitte.platform.api.project.vo.ActBakForm;
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
 * @Date : Create in 2019-05-14
 * @Description :   ActBak feign客户端
 * @Modified :
 */
@FeignClient(name = "actBak-service", fallbackFactory = ActBakFeignService.HystrixActBakService.class,primary = false)
public interface ActBakFeignService extends ActBakClient {

    @Component
    @Slf4j
    class HystrixActBakService implements FallbackFactory<ActBakFeignService> {

        @Override
        public ActBakFeignService create(Throwable throwable) {
            return new ActBakFeignService() {
                @Override
                public Result add(@Valid @RequestBody ActBakForm actBakForm) {
                    log.error("ActBakService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ActBakService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ActBakForm actBakForm) {
                    log.error("ActBakService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ActBakService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ActBakQueryForm actBakQueryForm) {
                    log.error("ActBakService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ActBakQueryForm actBakQueryForm) {
                    log.error("ActBakService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}