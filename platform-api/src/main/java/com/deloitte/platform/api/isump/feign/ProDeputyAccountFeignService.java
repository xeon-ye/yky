package com.deloitte.platform.api.isump.feign;


import com.deloitte.platform.api.isump.ProDeputyAccountClient;
import com.deloitte.platform.api.isump.param.ProDeputyAccountQueryForm;
import com.deloitte.platform.api.isump.vo.ProDeputyAccountForm;
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
 * @Date : Create in 2019-03-22
 * @Description :   ProDeputyAccount feign客户端
 * @Modified :
 */
@FeignClient(name = "service-isump", fallbackFactory = ProDeputyAccountFeignService.HystrixProDeputyAccountService.class,primary = false)
public interface ProDeputyAccountFeignService extends ProDeputyAccountClient {

    @Component
    @Slf4j
    class HystrixProDeputyAccountService implements FallbackFactory<ProDeputyAccountFeignService> {

        @Override
        public ProDeputyAccountFeignService create(Throwable throwable) {
            return new ProDeputyAccountFeignService() {
                @Override
                public Result add(@Valid @RequestBody ProDeputyAccountForm proDeputyAccountForm) {
                    log.error("ProDeputyAccountService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ProDeputyAccountService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ProDeputyAccountForm proDeputyAccountForm) {
                    log.error("ProDeputyAccountService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ProDeputyAccountService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ProDeputyAccountQueryForm proDeputyAccountQueryForm) {
                    log.error("ProDeputyAccountService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ProDeputyAccountQueryForm proDeputyAccountQueryForm) {
                    log.error("ProDeputyAccountService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}