package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.ChangeBudgetBakClient;
import com.deloitte.platform.api.project.param.ChangeBudgetBakQueryForm;
import com.deloitte.platform.api.project.vo.ChangeBudgetBakForm;
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
 * @Date : Create in 2019-05-23
 * @Description :   ChangeBudgetBak feign客户端
 * @Modified :
 */
@FeignClient(name = "changeBudgetBak-service", fallbackFactory = ChangeBudgetBakFeignService.HystrixChangeBudgetBakService.class,primary = false)
public interface ChangeBudgetBakFeignService extends ChangeBudgetBakClient {

    @Component
    @Slf4j
    class HystrixChangeBudgetBakService implements FallbackFactory<ChangeBudgetBakFeignService> {

        @Override
        public ChangeBudgetBakFeignService create(Throwable throwable) {
            return new ChangeBudgetBakFeignService() {
                @Override
                public Result add(@Valid @RequestBody ChangeBudgetBakForm changeBudgetBakForm) {
                    log.error("ChangeBudgetBakService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ChangeBudgetBakService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ChangeBudgetBakForm changeBudgetBakForm) {
                    log.error("ChangeBudgetBakService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ChangeBudgetBakService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ChangeBudgetBakQueryForm changeBudgetBakQueryForm) {
                    log.error("ChangeBudgetBakService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ChangeBudgetBakQueryForm changeBudgetBakQueryForm) {
                    log.error("ChangeBudgetBakService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}