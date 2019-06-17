package com.deloitte.platform.api.hr.gcc.feign;


import com.deloitte.platform.api.hr.gcc.client.GccCheckResultClient;
import com.deloitte.platform.api.hr.gcc.param.GccCheckResultQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccCheckResultForm;
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
 * @Description :   GccCheckResult feign客户端
 * @Modified :
 */
@FeignClient(name = "hr-service", fallbackFactory = GccCheckResultFeignService.HystrixGccCheckResultService.class,primary = false)
public interface GccCheckResultFeignService extends GccCheckResultClient {

    @Component
    @Slf4j
    class HystrixGccCheckResultService implements FallbackFactory<GccCheckResultFeignService> {

        @Override
        public GccCheckResultFeignService create(Throwable throwable) {
            return new GccCheckResultFeignService() {
                @Override
                public Result add(@Valid @RequestBody GccCheckResultForm gccCheckResultForm) {
                    log.error("GccCheckResultService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("GccCheckResultService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody GccCheckResultForm gccCheckResultForm) {
                    log.error("GccCheckResultService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("GccCheckResultService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody GccCheckResultQueryForm gccCheckResultQueryForm) {
                    log.error("GccCheckResultService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody GccCheckResultQueryForm gccCheckResultQueryForm) {
                    log.error("GccCheckResultService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}