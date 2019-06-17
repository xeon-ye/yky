package com.deloitte.platform.api.hr.gcc.feign;


import com.deloitte.platform.api.hr.gcc.client.GccSelectedNotifyClient;
import com.deloitte.platform.api.hr.gcc.param.GccSelectedNotifyQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccSelectedNotifyForm;
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
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :   GccSelectedNotify feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = GccSelectedNotifyFeignService.HystrixGccSelectedNotifyService.class,primary = false)
public interface GccSelectedNotifyFeignService extends GccSelectedNotifyClient {

    @Component
    @Slf4j
    class HystrixGccSelectedNotifyService implements FallbackFactory<GccSelectedNotifyFeignService> {

        @Override
        public GccSelectedNotifyFeignService create(Throwable throwable) {
            return new GccSelectedNotifyFeignService() {
                @Override
                public Result add(@Valid @RequestBody GccSelectedNotifyForm gccSelectedNotifyForm) {
                    log.error("GccSelectedNotifyService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("GccSelectedNotifyService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody GccSelectedNotifyForm gccSelectedNotifyForm) {
                    log.error("GccSelectedNotifyService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("GccSelectedNotifyService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody GccSelectedNotifyQueryForm gccSelectedNotifyQueryForm) {
                    log.error("GccSelectedNotifyService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody GccSelectedNotifyQueryForm gccSelectedNotifyQueryForm) {
                    log.error("GccSelectedNotifyService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}