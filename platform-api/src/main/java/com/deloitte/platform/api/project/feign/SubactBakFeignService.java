package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.SubactBakClient;
import com.deloitte.platform.api.project.param.SubactBakQueryForm;
import com.deloitte.platform.api.project.vo.SubactBakForm;
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
 * @Date : Create in 2019-05-24
 * @Description :   SubactBak feign客户端
 * @Modified :
 */
@FeignClient(name = "subactBak-service", fallbackFactory = SubactBakFeignService.HystrixSubactBakService.class,primary = false)
public interface SubactBakFeignService extends SubactBakClient {

    @Component
    @Slf4j
    class HystrixSubactBakService implements FallbackFactory<SubactBakFeignService> {

        @Override
        public SubactBakFeignService create(Throwable throwable) {
            return new SubactBakFeignService() {
                @Override
                public Result add(@Valid @RequestBody SubactBakForm subactBakForm) {
                    log.error("SubactBakService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("SubactBakService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody SubactBakForm subactBakForm) {
                    log.error("SubactBakService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("SubactBakService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody SubactBakQueryForm subactBakQueryForm) {
                    log.error("SubactBakService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody SubactBakQueryForm subactBakQueryForm) {
                    log.error("SubactBakService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}