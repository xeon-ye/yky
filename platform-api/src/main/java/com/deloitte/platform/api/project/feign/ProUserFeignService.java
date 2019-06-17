package com.deloitte.platform.api.project.feign;


import com.deloitte.platform.api.project.client.ProUserClient;
import com.deloitte.platform.api.project.param.ProUserQueryForm;
import com.deloitte.platform.api.project.vo.ProUserForm;
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
 * @Description :   ProUser feign客户端
 * @Modified :
 */
@FeignClient(name = "proUser-service", fallbackFactory = ProUserFeignService.HystrixProUserService.class,primary = false)
public interface ProUserFeignService extends ProUserClient {

    @Component
    @Slf4j
    class HystrixProUserService implements FallbackFactory<ProUserFeignService> {

        @Override
        public ProUserFeignService create(Throwable throwable) {
            return new ProUserFeignService() {
                @Override
                public Result add(@Valid @RequestBody ProUserForm proUserForm) {
                    log.error("ProUserService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("ProUserService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody ProUserForm proUserForm) {
                    log.error("ProUserService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("ProUserService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody ProUserQueryForm proUserQueryForm) {
                    log.error("ProUserService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody ProUserQueryForm proUserQueryForm) {
                    log.error("ProUserService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}