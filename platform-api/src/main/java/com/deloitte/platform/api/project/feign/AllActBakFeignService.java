package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.AllActBakClient;
import com.deloitte.platform.api.project.param.AllActBakQueryForm;
import com.deloitte.platform.api.project.vo.AllActBakForm;
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
 * @Date : Create in 2019-04-24
 * @Description :   AllActBak feign客户端
 * @Modified :
 */
@FeignClient(name = "allActBak-service", fallbackFactory = AllActBakFeignService.HystrixAllActBakService.class,primary = false)
public interface AllActBakFeignService extends AllActBakClient {

    @Component
    @Slf4j
    class HystrixAllActBakService implements FallbackFactory<AllActBakFeignService> {

        @Override
        public AllActBakFeignService create(Throwable throwable) {
            return new AllActBakFeignService() {
                @Override
                public Result add(@Valid @RequestBody AllActBakForm allActBakForm) {
                    log.error("AllActBakService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("AllActBakService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody AllActBakForm allActBakForm) {
                    log.error("AllActBakService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("AllActBakService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody AllActBakQueryForm allActBakQueryForm) {
                    log.error("AllActBakService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody AllActBakQueryForm allActBakQueryForm) {
                    log.error("AllActBakService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}